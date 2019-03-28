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
 * 【订单管理-退货管理-异常退货】中超45天未审核预警
 */
@Component
public class ReturnOrderExceptionJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(ReturnOrderExceptionJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    @Scheduled(cron ="0 11 7,13,18,23 * * ?")
    @DS(value = "erp_tc")
    public void getErrorMsg(){
        _logger.info("抓取 <【订单管理-退货管理-异常退货】中超45天未审核预警>");
        try{
            tcErrorMsgService.listExceptionReturnOrder(WarningEnum.WARNING_YW_NO_MORE_THAN_45_DAYS.getId());
        }catch (Exception e){
            _logger.error("抓取<【订单管理-退货管理-异常退货】中超45天未审核预警> 失败!!!!!"+ e.getMessage());
        }
        _logger.info("抓取<【订单管理-退货管理-异常退货】中超45天未审核预警> 结束!!!!");
    }


    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */10 * * * ?")
    @DS(value = "tc_warning")
    public void sendErrorMsg(){
        _logger.info("开始推送 <【订单管理-退货管理-异常退货】中超45天未审核预警> ");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_NO_MORE_THAN_45_DAYS.getId());
        }catch (Exception e){
            _logger.error("推送<【订单管理-退货管理-异常退货】中超45天未审核预警>失败!!!"+e.getMessage());
        }
        _logger.info("推送 <【订单管理-退货管理-异常退货】中超45天未审核预警>结束......");
    }

}
