package com.service.job.base;

import com.ds.DS;
import com.enums.WarningEnum;
import com.service.warn.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GoodsErrorJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(GoodsErrorJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    @Scheduled(cron = "0 0 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getGoodsErrorMsg(){
        _logger.info("抓取tc商品信息未维护、未匹配>>>>>");
        try{
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_YW_GOODS.getId(), Boolean.FALSE);
        }catch (Exception e){
            _logger.error("抓取tc商品信息未维护、未匹配失败!!!!!" + e.getMessage());
        }
        _logger.info("抓取tc商品信息未维护、未匹配结束!!!!");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */15 * * * ?")
    @DS(value = "tc_warning")
    //@Async
    public void sendMsg(){
        _logger.info("开始推送 tc商品信息未维护、未匹配 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_GOODS.getId());
        }catch (Exception e){
            _logger.error("推送 tc商品信息未维护、未匹配失败!!!"+e.getMessage());
        }
        _logger.info("推送 tc商品信息未维护、未匹配结束......");
    }
}
