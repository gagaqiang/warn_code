package com.service.job.order;

import com.ds.DS;
import com.service.warn.TcErrorMsgService;
import com.service.warn.WarningSendMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: hsl
 * @Date: 2018/9/10 14:23
 * @Description:大仓之间调拨
 */
@Component
public class BigWarehouseTransferJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(BigWarehouseTransferJobTask.class);

    private final static String WARN_JINKA_CODE = "YJ00004";

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    /**
     * 抓取并记录
     */
    @Scheduled(cron = "0 20 6,13,21 * * ?")
    @DS(value = "erp_stock")
    public void getWlbStockLog() {
        _logger.info("开始抓取>大仓之间调拨-信息>>>");
        try {
            tcErrorMsgService.select(WARN_JINKA_CODE);
        } catch (Exception e) {
            _logger.error("抓取>大仓之间调拨-信息 失败!!!" + e.getMessage());
        }
        _logger.info("抓取>大仓之间调拨-信息 结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */13 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg(){
        _logger.info("开始推送 大仓之间调拨-信息 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WARN_JINKA_CODE);
        }catch (Exception e){
            _logger.error("推送 大仓之间调拨-信息!!!"+e.getMessage());
        }
        _logger.info("推送 大仓之间调拨-信息 结束......");
    }

}
