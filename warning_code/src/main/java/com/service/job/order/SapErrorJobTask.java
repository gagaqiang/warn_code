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
 * sap转入失败
 */
@Component
public class SapErrorJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(SapErrorJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron ="0 3 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getSapErrorErrorMsg(){
        _logger.info("抓取 <sap转入失败>");
        try{
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_IT_TO_SAP_ERROR.getId(), false);
        }catch (Exception e){
            _logger.error("抓取<sap转入失败> 失败!!!!!"+ e.getMessage());
        }
        _logger.info("抓取<sap转入失败> 结束!!!!");
    }


    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */10 * * * ?")
    @DS(value = "tc_warning")
    public void sendSapErrorErrorMsg(){
        _logger.info("开始推送 <sap转入失败> ");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_TO_SAP_ERROR.getId());
        }catch (Exception e){
            _logger.error("推送<sap转入>失败!!!"+e.getMessage());
        }
        _logger.info("推送 <sap转入失败>结束......");
    }

}
