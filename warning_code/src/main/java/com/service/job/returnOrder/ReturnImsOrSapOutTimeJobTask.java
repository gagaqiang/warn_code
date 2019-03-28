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
 * @Date: 2018/10/10 14:26
 * @Description: 退货单，IMS(YJ00010),SAP(YJ00020)超24小时未返回
 */
@Component
public class ReturnImsOrSapOutTimeJobTask {


    public final static Logger _logger = LoggerFactory.getLogger(ReturnImsOrSapOutTimeJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    @Scheduled(cron = "0 8 3,9,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getErrorMsg(){
        _logger.info("订单 <退货单，IMS,SAP超24小时未返回>");
        try{
            tcErrorMsgService.dealReturnTimeOutMsg(WarningEnum.WARNING_IT_RETURN_IMS_SLOW.getId(), 2);

            tcErrorMsgService.dealReturnTimeOutMsg(WarningEnum.WARNING_IT_RETURN_SAP_SLOW.getId(), 4);

        }catch (Exception e){
            _logger.error("抓取 <退货单，IMS,SAP超24小时未返回> 异常!!!!!"+ e.getMessage());
        }
        _logger.info("抓取 <退货单，IMS,SAP超24小时未返回> 结束!!!!");
    }



    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */13 * * * ?")
    @DS(value = "tc_warning")
    public void sendErrorMsg(){
        _logger.info("开始 < 退货单，IMS,SAP超24小时未返回 >");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_RETURN_IMS_SLOW.getId());

            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_RETURN_SAP_SLOW.getId());
        }catch (Exception e){
            _logger.error("推送 <退货单，IMS,SAP超24小时未返回> 失败!!!"+e.getMessage());
        }
        _logger.info("推送 <退货单，IMS,SAP超24小时未返回> 结束......");
    }

}

