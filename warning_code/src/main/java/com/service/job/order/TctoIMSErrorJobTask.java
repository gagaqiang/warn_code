package com.service.job.order;


import com.ds.DS;
import com.entity.warning.WarningGroup;
import com.entity.warning.WarningHeader;
import com.enums.WarningEnum;
import com.service.warn.TcErrorMsgService;
import com.service.warn.WarningSendMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 订单IT预警传输速度慢-TC订单未传IMS(YJ00019)或SAP(YJ00018)未返回
 */
@Component
public class TctoIMSErrorJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(TctoIMSErrorJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    @Scheduled(cron = "0 8 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getTctoIMSErrorMsg(){
        _logger.info("订单 <IT预警传输速度慢-IMS超时未返回或SAP超时未返回>");
        try{
            tcErrorMsgService.dealOrderTimeOutMsg(WarningEnum.WARNING_IT_DD_IMS_SLOW.getId(), 2);

            tcErrorMsgService.dealOrderTimeOutMsg(WarningEnum.WARNING_IT_DD_SAP_SLOW.getId(), 4);

        }catch (Exception e){
            _logger.error("抓取 <订单IT预警传输速度慢-IMS超时未返回或SAP超时未返回> 异常!!!!!"+ e.getMessage());
        }
        _logger.info("抓取 <订单IT预警传输速度慢-IMS超时未返回或SAP超时未返回> 结束!!!!");
    }



    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */13 * * * ?")
    @DS(value = "tc_warning")
    public void sendTctoIMSErrorMsg(){
        _logger.info("开始 < 推送订单IT预警传输速度慢-IMS超时未返回或SAP超时未返回 >");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_DD_IMS_SLOW.getId());

            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_DD_SAP_SLOW.getId());
        }catch (Exception e){
            _logger.error("推送 <订单IT预警传输速度慢-IMS超时未返回或SAP超时未返回> 失败!!!"+e.getMessage());
        }
        _logger.info("推送 <订单IT预警传输速度慢-IMS超时未返回或SAP超时未返回> 结束......");
    }

}
