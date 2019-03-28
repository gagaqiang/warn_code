package com.service.job.invoice;

import com.ds.DS;
import com.entity.erptc.ErrorMsg;
import com.entity.warning.WarningHeader;
import com.enums.WarningEnum;
import com.service.warn.TcErrorMsgService;
import com.service.warn.WarningGroupService;
import com.service.warn.WarningHeaderService;
import com.service.warn.WarningSendMsgService;
import com.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 积分订单是否导入
 */
@Component
public class IntegralOrderNotImportJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(IntegralOrderNotImportJobTask.class);


    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;
    @Autowired
    private WarningHeaderService warningHeaderService;
    @Autowired
    private WarningGroupService warningGroupService;

    /**
     * 每月15号 10点执行
     */
    @Scheduled(cron = "0 0 10 15 * ?")
    @DS(value = "erp_tc")
    public void getIntegralOrderNotImportErrorMsg() {
        _logger.info("开始抓取>积分订单是否导入 信息>>>");
        try {
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(WarningEnum.WARNING_YW_INTEGRAORDER_NOT_IMPORT.getId());
            ErrorMsg err = new ErrorMsg();
            if(tcErrorMsgService.getIntegralOrderNotImportCount() && null != wh){
                //记录日志
                err.setCode(WarningEnum.WARNING_YW_INTEGRAORDER_NOT_IMPORT.getId());
                err.setErrorDetail(DateUtil.getLastMonth() + " 月没有积分导入");
                //加入预警明细
                warningGroupService.addWarningGroup(wh, err, Boolean.TRUE);
            }
        } catch (Exception e) {
            _logger.error("抓取>积分订单是否导入 信息>失败!!!" + e.getMessage());
        }
        _logger.info("抓取>积分订单是否导入 信息>结束......");
    }

    /**
     * 推送消息
     */
    @Scheduled(cron = "0 30 10 15 * ?")
    @DS(value = "tc_warning")
    public void sendIntegralOrderNotImportMsg() {
        _logger.info("开始推送>-积分订单是否导入 信息 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_INTEGRAORDER_NOT_IMPORT.getId());
        } catch (Exception e) {
            _logger.error("推送>-积分订单是否导入 信息>失败!!!" + e.getMessage());
        }
        _logger.info("推送>-积分订单是否导入 信息>结束......");
    }
}
