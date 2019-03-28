package com.service.warn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dao.warning.*;
import com.dto.weChat.WeChatResuDto;
import com.entity.warning.*;
import com.enums.WarningEnum;
import com.google.gson.Gson;
import com.utils.DateUtil;
import com.utils.OaSystemUtil;
import com.utils.smsALiyun.SmsALiUtil;
import com.utils.WarnOnceCodeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.utils.WeiXinUtil.sendMsg;

/**
 * 消息推送
 */
@Service
public class WarningSendMsgService {

    public final static Logger _logger = LoggerFactory.getLogger(WarningSendMsgService.class);

    @Autowired
    private OaSystemUtil oaSystemUtil;
    @Autowired
    private SmsALiUtil smsALiUtil;
    @Autowired
    private WarningGroupMapper warningGroupMapper;
    @Autowired
    private WarningUserMapper warningUserMapper;
    @Autowired
    private WarningSendMessageLogMapper warningSendMessageLogMapper;
    @Autowired
    private WarningDetailMapper warningDetailMapper;
    @Autowired
    private WarningHeaderService warningHeaderService;
    //默认非公平锁
    private Lock lock = new ReentrantLock();

    /**
     * 校验当前时间是否可以发送消息
     *
     * @param wh
     * @param wg
     * @return
     */
    public boolean isCheckTimeToSend(WarningHeader wh, WarningGroup wg) throws ParseException {

        _logger.info(wh.getCode()+" : "+WarningEnum.getName(wh.getCode())+ ": 是否可以发送JOB>>>>>>>>");
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm");

        //1:间隔时间，2：每天
        int type = wh.getFrequencyType();
        Integer day = wh.getDays();
        Integer hours = wh.getHours();
        Integer mins = wh.getMins();

        //时间毫秒
        long times = (hours * 60 + mins) * 60 * 1000 ;

        //时间范围
        String start = wh.getStartTime();
        String end = wh.getEndTime();

        long nowTime = DateUtil.getHHMMtoTimes(df.format(now));

        //获取最近一次推送消息的记录
        WarningSendMessageLog ws = warningSendMessageLogMapper.getLastSendMeaasgeLogByGroupId(wg.getId(), wh.getId());
        if(null == ws){
            if (type == 2){
                String temH = String.valueOf(hours).length() == 1?"0"+hours:hours.toString();
                String temM = String.valueOf(mins).length() == 1?"0"+mins:mins.toString();
                return DateUtil.isCheckTimesInMins(temH+":"+temM, df.format(now), 20, df);
            }else {
                if(null != start && null != end){
                    long startTime = DateUtil.getHHMMtoTimes(start);
                    long endTime = DateUtil.getHHMMtoTimes(end);
                    if(nowTime >= startTime && nowTime <= endTime){
                        return true;
                    }
                    return false;
                }
            }
        }else{
            //转化发送时间
            Date sendTime = ws.getSendTime();
            String par = df.format(sendTime);
            long parTime = DateUtil.getHHMMtoTimes(par);

            if(type == 2){
                if(DateUtil.fmtDateToStr(sendTime, "yyyy-MM-dd")
                        .equals(DateUtil.fmtDateToStr(now, "yyyy-MM-dd"))){
                    _logger.info("今天已经推送了");
                    return false;
                }
                String temH = String.valueOf(hours).length() == 1?"0"+hours:hours.toString();
                String temM = String.valueOf(mins).length() == 1?"0"+mins:mins.toString();
                boolean flag = DateUtil.isCheckTimesInMins(temH+":"+temM, df.format(now), 20, df);
                _logger.info("job >>>8> " + flag);
                return flag;
            }

            //判断时间是否同一天推送
            if(!DateUtil.fmtDateToStr(sendTime, "yyyy-MM-dd")
                    .equals(DateUtil.fmtDateToStr(now, "yyyy-MM-dd"))){

                long startTime = DateUtil.getHHMMtoTimes(start);
                long endTime = DateUtil.getHHMMtoTimes(end);
                if(nowTime < startTime || nowTime > endTime){
                    _logger.info("job >>>2-1 非同一天的推送时间> false");
                    return false;
                }
                _logger.info("job >>>2-2 非同一天的推送时间> true");
                return true;
            }

            //开始、结束时间都不为空
            if(null != start && null != end){
                long startTime = DateUtil.getHHMMtoTimes(start);
                long endTime = DateUtil.getHHMMtoTimes(end);
                if(nowTime < startTime || nowTime > endTime){
                    _logger.info("job >>>2> false");
                    return false;
                }
                if(parTime+times >= startTime && parTime+times <= endTime
                        && nowTime - parTime >= times){
                    _logger.info("job >>>3> true");
                    return true;
                }
            }
            if(null == start && null == end
                    && parTime+times > nowTime){
                _logger.info("job >>>4> true");
                return true;
            }
            if(null != start && null == end){
                long startTime = df.parse(start).getTime();
                if(parTime+times > startTime){
                    _logger.info("job >>>5> true");
                    return true;
                }
            }
            if(null == start && null != end){
                long endTime = df.parse(end).getTime();
                if(parTime+times < endTime){
                    _logger.info("job >>>6> true");
                    return true;
                }
            }
        }
        _logger.info("job >>>7> false");
        return false;
    }



    /**
     * 发消息
     * @param wh
     * @param wg
     */
    public void sendWecathMsg(WarningHeader wh, WarningGroup wg) {
        if(wh.getWarnType() == 1){
            sendQiyeWechat(wh, wg);
        }else if (wh.getWarnType() == 2){
            sendOAMsg(wh, wg);
        }else if (wh.getWarnType() == 3){
            sendSMS(wh, wg);
        }
    }

    /**
     * 推送SMS
     * @param wh
     * @param wg
     */
    public void sendSMS(WarningHeader wh, WarningGroup wg){
        List<String> users = getUsers(wh);
        _logger.info(" 开始推送 SMS消息 人员>"+ JSONObject.toJSONString(users));
        if (smsALiUtil.send(users, wh.getMessage())){
            //消息记录
            addSendMsgLog("200", wh, wg);
        }
    }

    /**
     * 推送OA
     * @param wh
     * @param wg
     */
    public void sendOAMsg(WarningHeader wh, WarningGroup wg){
        JSONObject params = new JSONObject();
        params.put("subject","预警消息："+ wh.getMessage());
        List<String> users = getUsers(wh);
        _logger.info(" 开始推送 OA消息 人员>"+ JSONObject.toJSONString(users));
        String result = JSON.toJSONString(oaSystemUtil.sendMsgToAo(params, users));
        _logger.info(" 推送 OA 消息返回值 >>>>> ：" + result);
        Gson gson = new Gson();
        WeChatResuDto we = gson.fromJson(result, WeChatResuDto.class);
        //消息记录
        addSendMsgLog(we.getRetCode(), wh, wg);
    }


    /**
     * 推送企业微信号
     * @param wh
     * @param wg
     */
    private void sendQiyeWechat(WarningHeader wh, WarningGroup wg){
        JSONObject params = new JSONObject();
        String content = wh.getMessage();
        params.put("content", "预警消息："+content);
        params.put("userIds", getUsers(wh));

        _logger.info(" 开始推送 企业微信号消息 >>>参数："+JSONObject.toJSONString(params));
        String result = JSON.toJSONString(sendMsg("BTEXT", params));
        _logger.info(" 推送 企业微信号 消息返回值 >>>>> ：" + result);
        Gson gson = new Gson();
        WeChatResuDto we = gson.fromJson(result, WeChatResuDto.class);
        //消息记录
        addSendMsgLog(we.getRetCode(), wh, wg);
    }

    private List<String> getUsers(WarningHeader wh){

        List<WarningUser> li = warningUserMapper.getWarningUserByWarnHeaderId(wh.getId());
        //店铺对应人员
        List<String> users = warningUserMapper.getUserIdsByHeaderId(wh.getId());

        List list = new ArrayList();
        List list1 = new ArrayList();
        for (int i = 0; i < li.size(); i++) {
            list.add(li.get(i).getUserId());
            list1.add(li.get(i).getPhoneNumber());
        }

        int warnType = wh.getWarnType();
        if(wh.getType() == 3){
        return warnType == 3?list1:list;
    }

        //1:业务预警，2：IT预警
        if(CollectionUtils.isEmpty(users) && wh.getType() == 2){
            return warnType == 3?list1:users;
        }else{
            if(wh.getType() == 2){
                return warnType == 3?list1:list;
            }else{
                if (warnType == 3)
                    return list1;
                else
                    return CollectionUtils.isEmpty(users)?list:users;
            }
        }
    }


    /**
     * 记录推送日志
     * @param code
     * @param warningHeader
     * @param wg
     */
    private void addSendMsgLog(String code, WarningHeader warningHeader, WarningGroup wg){
        WarningSendMessageLog sm = new WarningSendMessageLog();
        sm.setWarningHeaderId(warningHeader.getId());
        sm.setWarningCode(warningHeader.getCode());
        sm.setGroupId(wg.getId());
        sm.setGroupCode(wg.getDetailCode());
        sm.setWarnType(warningHeader.getWarnType());
        sm.setSendTime(new Date());
        sm.setUserName(warningHeader.getUsers());
        sm.setNote(warningHeader.getMessage());
        sm.setCreateTime(new Date());
        sm.setVersion(1);
        sm.setStatus(code.equals("200")?1:0);

        warningSendMessageLogMapper.insertSelective(sm);
    }


    /**
     * 统一处理 发送信息业务 、发送消息
     * @param warnCode
     * @throws ParseException
     */
    @Transactional
    public void updateWarnAndSendMsg(String warnCode) {
        try {
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(warnCode);
            if (null != wh) {
                //获取当前没有完成的明细组
                WarningGroup wg = warningGroupMapper.getWarningGroupByHeaderIdAndStatus(wh.getId(), 2);
                if(null != wg){
                    boolean flag ;
                    if (WarnOnceCodeUtil.ONLY_ONCE_CODE.contains(warnCode)){
                        flag = true;
                    }else {
                        flag = isCheckTimeToSend(wh, wg);
                    }
                    if (flag) {
                        _logger.info("有未完成的明细>>>>>>>");
                        if (wg.getSendTime() == null) {
                            wg.setSendTime(new Date());
                        }
                        int groupId = wg.getId();
                        //未处理
                        warningDetailMapper.updateSendTimeByGroupId(groupId);
                        warningGroupMapper.updateByPrimaryKeySelective(wg);
                        //发送
                        sendWecathMsg(wh, wg);
                    } else {
                        _logger.info("当前时间不在推送消息时间内>>>");
                    }
                }else {
                    _logger.info("未找到需要推送的消息>");
                }
            } else {
                _logger.info("没有对应的 预警编号：<" + warnCode + " 预警项，或已暂停> !!!");
            }
        }catch (ParseException e){
            _logger.info("获取资源失败!!!" + e.getMessage());
        }

    }
}
