package com.service.job.flowingWater;

import com.dao.erpreport.AlipayAccountOrderMapper;
import com.ds.DS;

import com.entity.erptc.ErrorMsg;
import com.entity.warning.WarningHeader;
import com.enums.WarningEnum;
import com.service.warn.WarningGroupService;
import com.service.warn.WarningHeaderService;
import com.service.warn.WarningSendMsgService;
import com.utils.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class AlipayFlowingWaterTask {

    public final static Logger _logger = LoggerFactory.getLogger(AlipayFlowingWaterTask.class);

    @Autowired
    private WarningGroupService warningGroupService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;
    @Autowired
    private WarningHeaderService warningHeaderService;
    @Autowired
    private AlipayAccountOrderMapper alipayAccountOrderMapper;

    /**
     * 抓取并记录
     * 每天上午8.30, 10.30整抓取
     */
    @Scheduled(cron = "0 30 8,10 * * ?")
    @DS(value = "erp_report")
    public void getAlipayFlowingWater() {
        try {
            _logger.info("开始抓取支付宝漏抓流水消息Job>>>>>>");
            //获取昨天时间
            String startDate = DateUtil.fmtDateToStr(DateUtils.addDays(new Date(), -1), "yyyy-MM-dd");
            //获取今天时间
            String endDate = DateUtil.fmtDateToStr(DateUtils.addDays(new Date(), 0), "yyyy-MM-dd");

            int sum = alipayAccountOrderMapper.findLeakageOfWater(startDate, endDate);

            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(WarningEnum.WARNING_YW_ALIPAY_FLOWING.getId());

            if (null != wh) {
                //int num = 30000;
                //此时预警
                ErrorMsg err = new ErrorMsg();
                if (sum == 0) {
                    //记录日志
                    String data = "时间：【" + startDate + "】，支付宝流水时间段流水漏抓。";
                    err.setCode(WarningEnum.WARNING_YW_ALIPAY_FLOWING.getId());
                    err.setErrorDetail(data);
                    //加入预警明细
                    warningGroupService.addWarningGroup(wh, err, Boolean.TRUE);
                } else {
                    warningGroupService.updateHasPlatfromCodeStatus(err, wh, false);
                    //_logger.info("没有支付宝漏抓流水消息>>>>>>");
                }
            } else {

                _logger.info("没有对应的预警项，或已暂停!!!");
            }
        } catch (Exception e) {
            _logger.error("读取支付宝账单流水数据JOB失败 >>> " + e);
        }
    }


    /**
     * 发送消息
     * 每天上午九点整发送
     */
    @Scheduled(cron = "0 0 9 * * ?")
    @Transactional
    public void sendMsg() {
        _logger.info("开始发送支付宝漏抓流水消息Job>>>>>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_ALIPAY_FLOWING.getId());
        } catch (Exception e) {
            _logger.error("发送支付宝漏抓流水消息JOB失败 >>>" + e.getMessage());
        }
        _logger.info("发送支付宝漏抓流水消息JOB执行完成。。。。。");
    }


//    /**
//     * 抓取并记录
//     */
//    @Scheduled(cron = "0 0 8 ? * *")
//    public void getAlipayFlowingWater() {
//        try {
//            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(WarningEnum.WARNING_YW_ALIPAY_FLOWING.getId());
//            if(null != wh){
//                AlipayUtil alipayUtil = new AlipayUtil();
////                List<AlipayAccountOrderDTO> list = null;
//                List<AlipayAccountOrderDTO> list = alipayUtil.getAlipayFlowingWater();
//                _logger.error("-----读取支付宝账单流水数据-----" + JSON.toJSONString(list) + " --- " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
//                //账单流水对比
//                List listStr = new ArrayList();
//
//                //已抓取到支付宝流水
//                //业务流水对比未做
//
//                //此时预警
//                if (listStr.size() > 0) {
//                    //记录日志
//                    String data = DateUtil.fmtDateToStr(DateUtils.addDays(new Date(), -1),"yyyy-MM-dd") +
//                            ",支付宝漏抓流水号为:" + JSON.toJSONString(listStr);
//                    ErrorMsg err = new ErrorMsg();
//                    err.setCode( WarningEnum.WARNING_YW_ALIPAY_FLOWING.getId());
//                    err.setErrorDetail(data);
//                    //warningJobDataLogService.addJobDataLog(data, WarningEnum.WARNING_YW_ALIPAY_FLOWING.getId(), null, null, null);
//                    //加入预警明细
//                    warningGroupService.addWarningGroup(wh, err, true);
//                } else {
//                    //处理已解决问题
//                    warningGroupService.updateHasPlatfromCodeStatus(null, wh);
//                }
//            }else{
//                _logger.info("没有对应的预警项，或已暂停!!!");
//            }
//        } catch (Exception e) {
//            _logger.error("读取支付宝账单流水数据JOB失败 >>> " + e.getMessage());
//        }
//    }


}
