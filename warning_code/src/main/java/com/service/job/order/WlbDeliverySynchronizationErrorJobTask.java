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
 * @Auther: hsl
 * @Date: 2018/9/17 16:57
 * @Description:物流宝发货信息同步TC失败(从物流宝抓取的退货单)
 */
@Component
public class WlbDeliverySynchronizationErrorJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(WlbDeliverySynchronizationErrorJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 30 6,13,21 * * ?")
    @DS(value = "erp_tc")
    public void getCKErrorMsg() {
        _logger.info("开始抓取>同步物流宝的发货信息>>>");
        try {
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_YW_WLB_DELIVERY_SYNCHRONIZATION.getId(), false);
        } catch (Exception e) {
            _logger.error("抓取>同步物流宝的发货信息 失败!!!" + e.getMessage());
        }
        _logger.info("抓取>同步物流宝的发货信息 结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */15 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg() {
        _logger.info("开始推送>-同步物流宝的发货信息 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_WLB_DELIVERY_SYNCHRONIZATION.getId());
        } catch (Exception e) {
            _logger.error("推送>-同步物流宝的发货信息 失败!!!" + e.getMessage());
        }
        _logger.info("推送>-同步物流宝的发货信息 结束......");
    }

}
