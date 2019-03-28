package com.service.job.returnOrder;

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
 * @Date: 2018/10/10 14:30
 * @Description:退货单，SAP返回失败(YJ00012)
 */
@Component
public class ReturnSapErrorJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(ReturnSapErrorJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    @Scheduled(cron = "0 30 6,13,21 * * ?")
    @DS(value = "erp_tc")
    public void getErrorMsg() {
        _logger.info("开始抓取>退货单，SAP返回失败信息>>>");
        try {
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_IT_RETURN_SAP_ERROR.getId(), false);
        } catch (Exception e) {
            _logger.error("抓取>退货单，SAP返回失败信息>失败!!!" + e.getMessage());
        }
        _logger.info("抓取>退货单，SAP返回失败信息>结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */12 * * * ?")
    @DS(value = "tc_warning")
    public void sendErrorMsg() {
        _logger.info("开始推送>-退货单，SAP返回失败信息 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_RETURN_SAP_ERROR.getId());
        } catch (Exception e) {
            _logger.error("推送>-退货单，SAP返回失败信息>失败!!!" + e.getMessage());
        }
        _logger.info("推送>-退货单，SAP返回失败信息>结束......");
    }
}


