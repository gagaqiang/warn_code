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
 * 【TC-基础管理-店铺管理-店铺信息】未维护匹配
 */
@Component
public class ShopmsgJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(ShopmsgJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 5 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getShopErrorMsg(){
        _logger.info("抓取tc店铺信息未维护信息>>>>>");
        try{
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_YW_DIANPU_UNWEIHU.getId(), false);
        }catch (Exception e){
            _logger.error("抓取tc店铺信息未维护信息失败!!!!!"+ e.getMessage());
        }
        _logger.info("抓取tc店铺信息未维护信息结束!!!!");
    }


    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */16 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg(){
        _logger.info("开始推送 tc店铺信息未维护 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_DIANPU_UNWEIHU.getId());
        }catch (Exception e){
            _logger.error("推送 tc店铺信息未维护失败!!!"+e.getMessage());
        }
        _logger.info("推送 tc店铺信息未维护 结束......");
    }
}
