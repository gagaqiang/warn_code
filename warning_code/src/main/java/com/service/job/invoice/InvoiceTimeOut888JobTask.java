package com.service.job.invoice;

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
 * 888超时订单
 */
@Component
public class InvoiceTimeOut888JobTask {

    public final static Logger _logger = LoggerFactory.getLogger(InvoiceTimeOut888JobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    /**
     * 超时未生成888订单
     */
    @Scheduled(cron = "0 32 6,13,21 * * ?")
    @DS(value = "erp_tc")
    public void getErrorMsg() {
        _logger.info("开始抓取>超时未生成888订单>>>");
        try {
            tcErrorMsgService.dealOrder888TimeoutMsg(WarningEnum.WARNING_YW_OVER_6_DAYS.getId());
        } catch (Exception e) {
            _logger.error("抓取>超时未生成888订单>失败!!!" + e.getMessage());
        }
        _logger.info("抓取>超时未生成888订单>结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */14 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg() {
        _logger.info("开始推送>-发票接口，IMS返回失败信息 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_OVER_6_DAYS.getId());
        } catch (Exception e) {
            _logger.error("推送>-发票接口，IMS返回失败信息>失败!!!" + e.getMessage());
        }
        _logger.info("推送>-发票接口，IMS返回失败信息>结束......");
    }
}
