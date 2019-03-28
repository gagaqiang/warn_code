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
 * @Auther: hsl
 * @Date: 2018/9/28 13:53
 * @Description:金额正常而开票金额为0(YJ00035)
 */
@Component
public class InvoiceAmountIsZeroJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(InvoiceAmountIsZeroJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 15 8,10,12,14,20 * * ?")
    @DS(value = "erp_tc")
    public void getErrorMsg() {
        _logger.info("开始抓取>金额正常而开票金额为0信息>>>");
        try {
            tcErrorMsgService.listNoCreateInvoiceOrder(WarningEnum.WARNING_IT_IINVOICE_AMOUNT_IS_ZERO.getId());
        } catch (Exception e) {
            _logger.error("抓取>金额正常而开票金额为0信息>失败!!!" + e.getMessage());
        }
        _logger.info("抓取>金额正常而开票金额为0信息>结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */15 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg() {
        _logger.info("开始推送>-金额正常而开票金额为0信息 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_IINVOICE_ALI_FAILED.getId());
        } catch (Exception e) {
            _logger.error("推送>-金额正常而开票金额为0信息>失败!!!" + e.getMessage());
        }
        _logger.info("推送>-金额正常而开票金额为0信息>结束......");
    }
}





