package com.service.job.flowingWater;

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
 * @Date: 2018/10/11 15:37
 * @Description:保证金退款预警
 *
 */
@Component
public class MarginRefundJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(MarginRefundJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 1 10,13,15,20 * * ?")
    @DS(value = "erp_report")
    public void getErrorMsg(){
        _logger.info("抓取<保证金退款>");
        try{
            tcErrorMsgService.listMarginOrder(WarningEnum.WARNING_YW_MARGIN_REFUND.getId());
        }catch (Exception e){
            _logger.error("抓取<保证金退款>失败!!!!!" + e.getMessage());
        }
        _logger.info("抓取<保证金退款>结束!!!!");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */11 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg(){
        _logger.info("开始推送 <保证金退款>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_MARGIN_REFUND.getId());
        }catch (Exception e){
            _logger.error("推送 <保证金退款>失败!!!"+e.getMessage());
        }
        _logger.info("推送 <保证金退款>结束......");
    }
}

