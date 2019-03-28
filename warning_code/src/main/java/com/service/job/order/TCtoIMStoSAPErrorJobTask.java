package com.service.job.order;


import com.ds.DS;
import com.enums.WarningEnum;
import com.service.warn.TcErrorMsgService;
import com.service.warn.WarningSendMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单-TC传IMS正常，IMS未传SAP
 */
@Component
public class TCtoIMStoSAPErrorJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(TCtoIMStoSAPErrorJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 10 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getTCtoIMStoSAPErrorMsg(){
        _logger.info("抓取订单-TC传IMS正常，IMS未传SAP>>>>");
        try{
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_IT_TC_TO_IMS_SAP.getId(), false);
        }catch (Exception e){
            _logger.error("抓取订单-TC传IMS正常，IMS未传SAP失败!!!!!"+ e.getMessage());
        }
        _logger.info("抓取订单-TC传IMS正常，IMS未传SAP结束!!!!");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */15 * * * ?")
    @DS(value = "tc_warning")
    public void sendTCtoIMStoSAPErrorMsg(){
        _logger.info("开始推送订单-TC传IMS正常，IMS未传SAP >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_TC_TO_IMS_SAP.getId());
        }catch (Exception e){
            _logger.error("推送订单-TC传IMS正常，IMS未传SAP!!!"+e.getMessage());
        }
        _logger.info("推送订单-TC传IMS正常，IMS未传SAP......");
    }
}
