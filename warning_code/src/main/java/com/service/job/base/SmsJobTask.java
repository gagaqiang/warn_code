package com.service.job.base;

import com.dao.warning.WarningDetailMapper;
import com.dao.warning.WarningGroupMapper;
import com.dao.warning.WarningHeaderMapper;
import com.ds.DS;
import com.entity.erptc.ErrorMsg;
import com.entity.warning.WarningDetail;
import com.entity.warning.WarningGroup;
import com.entity.warning.WarningHeader;
import com.enums.WarningEnum;
import com.service.warn.WarningGroupService;
import com.service.warn.WarningHeaderService;
import com.service.warn.WarningSendMsgService;
import com.utils.smsGY.SmsGYUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 短信数量预警
 */
@Component
public class SmsJobTask {
    public final static Logger _logger = LoggerFactory.getLogger(SmsJobTask.class);

    @Autowired
    private WarningGroupService warningGroupService;
    @Autowired
    private WarningHeaderMapper warningHeaderMapper;
    @Autowired
    private WarningSendMsgService warningSendMsgService;
    @Autowired
    private WarningGroupMapper warningGroupMapper;
    @Autowired
    private WarningDetailMapper warningDetailMapper;
    @Autowired
    private WarningHeaderService warningHeaderService;

    /**
     * 抓取并记录
     */
    @Scheduled(cron = "0 0 8 ? * *")
    public void getBalance() {
        try {
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(WarningEnum.WARNING_IT_SMS.getId());
            if(null != wh){

                int num = SmsGYUtil.getBalance();
                _logger.error("-----读取管易短信数量-----" + num + " --- " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
                //小于200000预警
                ErrorMsg err = new ErrorMsg();
                if (num < 200000) {
                    //记录日志
                    String data = "短信数量不足200000，现在剩余:" + num;
                    err.setCode( WarningEnum.WARNING_IT_SMS.getId());
                    err.setErrorDetail(data);
                    //加入预警明细
                    warningGroupService.addWarningGroup(wh, err, true);
                }
            }else{
                _logger.info("没有对应的预警项，或已暂停!!!");
            }
        } catch (Exception e) {
            _logger.error("读取短信数量JOB失败 >>> " + e.getMessage());
        }
    }


    /**
     * 发送消息
     * 每天上午九点整发送
     */
    @Scheduled(cron = "0 */16 * * * ?")
    @DS(value = "tc_warning")
    @Transactional
    public void sendVipSendMsgJob() {
        _logger.info("开始执行短信数量Job>>>>>>");
        try {
            WarningHeader wh = getWarnHeader(WarningEnum.WARNING_IT_SMS.getId());
            if (null != wh){
                //获取当前没有完成的明细组
                WarningGroup wg = warningGroupMapper.getWarningGroupByHeaderIdAndStatus(wh.getId(), 2);
                if (null != wh && wg != null /*&& warningSendMsgService.isCheckTimeToSend(wh, wg)*/){
                    _logger.info("有未完成的明细>>>>>>>");
                    if (wg.getSendTime() == null) {
                        wg.setSendTime(new Date());
                    }
                    int groupId = wg.getId();
                    //未处理
                    List<WarningDetail> wdList = warningDetailMapper.getWarningDetailDto(groupId, 2, null);
                    wdList.get(0).setSendTime(new Date());

                    warningGroupMapper.updateByPrimaryKeySelective(wg);
                    warningDetailMapper.updateByPrimaryKeySelective(wdList.get(0));

                    warningSendMsgService.sendWecathMsg(wh, wg);
                }else{
                    _logger.info("没有需要发送短信数量的信息>>>");
                }
            }else{
                _logger.info("没有对应的预警项，或已暂停!!!");
            }
        } catch (Exception e) {
            _logger.error("发送消息短信数量JOB失败 >>>" + e.getMessage());
        }
        _logger.info("发送消息短信数量JOB执行完成。。。。。");
    }


    private WarningHeader getWarnHeader(String code) {
        return warningHeaderMapper.getWarningHeaderByCode(code);
    }
}
