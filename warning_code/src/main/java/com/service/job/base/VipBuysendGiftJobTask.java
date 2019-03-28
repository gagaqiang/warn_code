package com.service.job.base;

import com.dao.erpbase.VipBuysendGiftMapper;
import com.ds.DS;
import com.entity.erptc.ErrorMsg;
import com.entity.warning.*;
import com.enums.WarningEnum;
import com.service.warn.WarningGroupService;
import com.service.warn.WarningHeaderService;
import com.service.warn.WarningSendMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 金卡数量不足预警
 */
@Component
public class VipBuysendGiftJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(VipBuysendGiftJobTask.class);
    @Autowired
    private VipBuysendGiftMapper vipBuysendGiftDao;
    @Autowired
    private WarningGroupService warningGroupService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;
    @Autowired
    private WarningHeaderService warningHeaderService;

    /**
     * 抓取并记录
     */
    @Scheduled(cron = "0 15 8 ? * MON")
    @DS(value = "erp_base")
    public void getVipBuysendLog() {
        try {
            int num = vipBuysendGiftDao.getGitLeftNum();
            WarningHeader wh = warningHeaderService.getWarningHeaderByCode(WarningEnum.WARNING_YW_YCSH.getId());
            if(null != wh){
                //int num = 30000;
                _logger.error("-----读取金卡剩余数量-----" + num + " --- " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
                //此时预警
                ErrorMsg err = new ErrorMsg();
                if (num < 3000) {
                    //记录日志
                    String data = "金卡数量不足3000，现在剩余:" + num;
                    err.setCode( WarningEnum.WARNING_YW_YCSH.getId());
                    err.setErrorDetail(data);
                    //加入预警明细
                    warningGroupService.addWarningGroup(wh, err, Boolean.TRUE);
                }
            }else{
                _logger.info("没有对应的预警项，或已暂停!!!");
            }
        } catch (Exception e) {
            _logger.error("抓取金卡JOB失败 >>> " + e.getMessage());
        }
    }


    /**
     * 发送消息
     */
    @Scheduled(cron = "0 30 8 * * ?")
    @DS(value = "tc_warning")
    public void sendVipSendMsgJob() {
        _logger.info("开始执行发送消息金卡Job>>>>>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_YCSH.getId());
        } catch (Exception e) {
            _logger.error("发送消息金卡JOB失败 >>>" + e.getMessage());
        }
        _logger.info("发送消息金卡JOB执行完成。。。。。");
    }

}

