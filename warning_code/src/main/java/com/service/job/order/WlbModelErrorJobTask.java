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
 * @Date: 2018/9/17 16:44
 * @Description:TC订单机型错抓(正向) 从平台抓取的退货单和从物流宝抓取的退货单做比对，数量、订单明细（商品）
 */
@Component
public class WlbModelErrorJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(WlbModelErrorJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 25 6,13,21 * * ?")
    @DS(value = "erp_tc")
    public void getWlbModelErrorMsg() {
        _logger.info("开始抓取>退货单-TC订单机型错抓(正向)>>>");
        try {
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_YW_WLB_MODEL.getId(), Boolean.FALSE);
        } catch (Exception e) {
            _logger.error("抓取>退货单-TC订单机型错抓(正向) 失败!!!" + e.getMessage());
        }
        _logger.info("抓取>退货单-TC订单机型错抓(正向) 结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */14 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg() {
        _logger.info("开始推送>-退货单>-TC订单机型错抓(正向) >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_WLB_MODEL.getId());
        } catch (Exception e) {
            _logger.error("推送>-退货单>-TC订单机型错抓(正向)失败!!!" + e.getMessage());
        }
        _logger.info("推送>-退货单>-TC订单机型错抓(正向) 结束......");
    }


}
