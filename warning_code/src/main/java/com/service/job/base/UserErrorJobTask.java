package com.service.job.base;


import com.ds.DS;
import com.enums.WarningEnum;
import com.service.warn.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 用户信息未维护
 */
@Component
public class UserErrorJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(WarehouseJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    @Scheduled(cron = "0 13 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getUserErrorMsg(){
        _logger.info("开始抓取 < 订单推IMS失败！客户代码不匹配>");
        try {
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_YW_CUS.getId(), false);
        }catch (Exception e){
            _logger.error("抓取 < 订单推IMS失败！客户代码不匹配>失败!!!" + e.getMessage());
        }
        _logger.info("抓取 < 订单推IMS失败！客户代码不匹配> 结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */18 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg(){
        _logger.info("开始推送  < 订单推IMS失败！客户代码不匹配 >");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_CUS.getId());
        }catch (Exception e){
            _logger.error("推送 < 订单推IMS失败！客户代码不匹配> 失败!!!"+e.getMessage());
        }
        _logger.info("推送 < 订单推IMS失败！客户代码不匹配> 结束......");
    }
}
