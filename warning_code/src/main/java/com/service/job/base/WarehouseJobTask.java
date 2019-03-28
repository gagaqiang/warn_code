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
 * 【TC-基础管理仓库管理-仓库对照关系】未匹配大小仓
 */
@Component
public class WarehouseJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(WarehouseJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    @Scheduled(cron = "0 15 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getCKErrorMsg(){
        _logger.info("开始抓取>仓库维护-未匹配大小仓>>>");
        try {
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_YW_CK.getId(), Boolean.FALSE);
        }catch (Exception e){
            _logger.error("抓取>仓库维护-未匹配大小仓 失败!!!"+ e.getMessage());
        }
        _logger.info("抓取>仓库维护-未匹配大小仓 结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */8 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg(){
        _logger.info("开始推送 仓库维护-未匹配大小仓 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_CK.getId());
        }catch (Exception e){
            _logger.error("推送 仓库维护-未匹配大小仓失败!!!"+e.getMessage());
        }
        _logger.info("推送 仓库维护-未匹配大小仓 结束......");
    }
}
