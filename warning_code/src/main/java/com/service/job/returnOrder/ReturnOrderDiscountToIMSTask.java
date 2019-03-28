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
 * 退货单处理后折扣转让信息同步给IMS异常
 */
@Component
public class ReturnOrderDiscountToIMSTask {

    public final static Logger _logger = LoggerFactory.getLogger(ReturnOrderDiscountToIMSTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron ="0 4 7,14,19,22 * * ?")
    @DS(value = "erp_tc")
    public void getrErrorMsg(){
        _logger.info("抓取 <退货单处理后折扣转让信息同步给IMS异常>");
        try{
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_YW_SYNCHRONIZED_IMS_EXCEPTION.getId(), false);
        }catch (Exception e){
            _logger.error("抓取<退货单处理后折扣转让信息同步给IMS异常> 失败!!!!!"+ e.getMessage());
        }
        _logger.info("抓取<退货单处理后折扣转让信息同步给IMS异常> 结束!!!!");
    }


    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */7 * * * ?")
    @DS(value = "tc_warning")
    public void sendErrorMsg(){
        _logger.info("开始推送 <退货单处理后折扣转让信息同步给IMS异常> ");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_SYNCHRONIZED_IMS_EXCEPTION.getId());
        }catch (Exception e){
            _logger.error("推送<退货单处理后折扣转让信息同步给IMS异常>失败!!!"+e.getMessage());
        }
        _logger.info("推送 <退货单处理后折扣转让信息同步给IMS异常>结束......");
    }

}
