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
 * @Date: 2018/10/9 10:35
 * @Description:流水入账,SAP超24小时未返回、返回失败
 */
@Component
public class FlowingSapOutTimeJobTask {


    public final static Logger _logger = LoggerFactory.getLogger(FlowingSapOutTimeJobTask.class);


    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 21 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_report")
    public void getErrorMsg(){
        _logger.info("抓取<流水入账,SAP超24小时未返回、返回失败>");
        try{
            tcErrorMsgService.dealFlowingSapOutTimeOrError(WarningEnum.WARNING_IT_FLOWING_TO_SAP_OUTTIME.getId());
        }catch (Exception e){
            _logger.error("抓取<流水入账,IMS超24小时未返回、返回失败>失败!!!!!" + e.getMessage());
        }
        _logger.info("抓取<流水入账,IMS超24小时未返回、返回失败>结束!!!!");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */11 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg(){
        _logger.info("开始推送 <流水入账,SAP超24小时未返回、返回失败>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_IT_FLOWING_TO_SAP_OUTTIME.getId());
        }catch (Exception e){
            _logger.error("推送 <流水入账,SAP超24小时未返回、返回失败>失败!!!"+e.getMessage());
        }
        _logger.info("推送 <流水入账,SAP超24小时未返回、返回失败>结束......");
    }
}
