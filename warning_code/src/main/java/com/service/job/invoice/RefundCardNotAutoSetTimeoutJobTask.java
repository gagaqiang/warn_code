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
 * @Date: 2018/9/26 11:08
 * @Description:退款单正确，超24小时未自动结算（YJ00037）
 */
@Component
public class RefundCardNotAutoSetTimeoutJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(RefundCardNotAutoSetTimeoutJobTask.class);


    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 30 6,13,21 * * ?")
    @DS(value = "erp_tc")
    public void getErrorMsg() {
        _logger.info("开始抓取>退款单正确，超24小时未自动结算信息>>>");
        try {
            tcErrorMsgService.listNoAgreeRefund(WarningEnum.WARNING_IT_REFUNDCARD_NOT_AUTO_SET_OVER24HOURS.getId());
        } catch (Exception e) {
            _logger.error("抓取>退款单正确，超24小时未自动结算信息>失败!!!" + e);
        }
        _logger.info("抓取>退款单正确，超24小时未自动结算信息>结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */13 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg() {
        _logger.info("开始推送>-退款单正确，超24小时未自动结算信息 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_REFUNDCARD_NOT_AUTO_SET_OVER24HOURS.getId());
        } catch (Exception e) {
            _logger.error("推送>-退款单正确，超24小时未自动结算信息>失败!!!" + e.getMessage());
        }
        _logger.info("推送>-退款单正确，超24小时未自动结算信息>结束......");
    }
}


