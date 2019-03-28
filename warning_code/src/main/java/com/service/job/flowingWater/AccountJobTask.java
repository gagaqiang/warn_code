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
 * @Date: 2018/10/11 15:42
 * @Description:支付宝流水【账务类型=转账】手工入账
 */
@Component
public class AccountJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(AccountJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 0 8 * * ?")
    @DS(value = "erp_report")
    public void getErrorMsg(){
        _logger.info("抓取<支付宝流水【账务类型=转账】手工入账>");
        try{
            tcErrorMsgService.listTransferOrderToErrorMsg(WarningEnum.WARNING_YW_FLOWING_ACCOUNT.getId());
        }catch (Exception e){
            _logger.error("抓取<支付宝流水【账务类型=转账】手工入账>失败!!!!!" + e.getMessage());
        }
        _logger.info("抓取<支付宝流水【账务类型=转账】手工入账>结束!!!!");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */11 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg(){
        _logger.info("开始推送 <支付宝流水【账务类型=转账】手工入账>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_FLOWING_ACCOUNT.getId());
        }catch (Exception e){
            _logger.error("推送 <支付宝流水【账务类型=转账】手工入账>失败!!!"+e.getMessage());
        }
        _logger.info("推送 <支付宝流水【账务类型=转账】手工入账>结束......");
    }
}


