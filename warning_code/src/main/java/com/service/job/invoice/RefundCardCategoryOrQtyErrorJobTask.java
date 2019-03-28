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
 * @Date: 2018/9/26 10:50
 * @Description:退款单（种类、数量）错误（YJ00036）
 */
@Component
public class RefundCardCategoryOrQtyErrorJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(RefundCardCategoryOrQtyErrorJobTask.class);


    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 2 9,12,21 * * ?")
    @DS(value = "erp_tc")
    public void getErrorMsg() {
        _logger.info("开始抓取>退款单（种类、数量）错误信息>>>");
        try {
            tcErrorMsgService.listErrorTypeRefund(WarningEnum.WARNING_YW_REFUNDCARD_CATEGORY_OR_QTY_ERROR.getId());
        } catch (Exception e) {
            _logger.error("抓取>退款单（种类、数量）错误信息>失败!!!" + e.getMessage());
        }
        _logger.info("抓取>退款单（种类、数量）错误信息>结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */11 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg() {
        _logger.info("开始推送>-退款单（种类、数量）错误信息 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_REFUNDCARD_CATEGORY_OR_QTY_ERROR.getId());
        } catch (Exception e) {
            _logger.error("推送>-退款单（种类、数量）错误信息>失败!!!" + e.getMessage());
        }
        _logger.info("推送>-退款单（种类、数量）错误信息>结束......");
    }
}

