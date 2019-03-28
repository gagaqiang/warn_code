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
 * 已签收订单纸票开票超30天未开预警(YJ00040)
 */
@Component
public class PaperInvoiceTimeoutJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(PaperInvoiceTimeoutJobTask.class);


    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    @Scheduled(cron = "0 23 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getInvoiceTimeoutErrorMsg(){
        _logger.info("抓取<已签收订单纸票开票超30天未开预警>");
        try{
            tcErrorMsgService.mapperTradeInvoiceHasSignNoPushImsForPaper(WarningEnum.WARNING_YW_IINVOICE_30_TIMEOUT.getId());
        }catch (Exception e){
            _logger.error("抓取<已签收订单纸票开票超30天未开预警>失败!!!!!" + e.getMessage());
        }
        _logger.info("抓取<已签收订单纸票开票超30天未开预警>结束!!!!");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */12 * * * ?")
    @DS(value = "tc_warning")
    public void sendInvoiceTimeoutMsg(){
        _logger.info("开始推送 <已签收订单纸票开票超30天未开预警>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_IINVOICE_30_TIMEOUT.getId());
        }catch (Exception e){
            _logger.error("推送 <已签收订单纸票开票超30天未开预警>失败!!!"+e.getMessage());
        }
        _logger.info("推送 <已签收订单纸票开票超30天未开预警>结束......");
    }
}
