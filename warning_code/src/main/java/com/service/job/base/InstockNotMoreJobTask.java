package com.service.job.base;


import com.ds.DS;
import com.service.warn.TcErrorMsgService;
import com.service.warn.WarningSendMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 仓库维护-库存量不足 YJ00006
 */
@Component
public class InstockNotMoreJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(InstockNotMoreJobTask.class);

    private final static String WARN_CODE = "YJ00006";

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron ="0 18 3,6,9,12,15,19,23 * * ?")
    @DS(value = "erp_tc")
    public void getInstockNotMoreErrorMsg(){
        _logger.info("抓取 <仓库维护-库存量不足>");
        try{
            tcErrorMsgService.dealTcErrorMsg(WARN_CODE, Boolean.FALSE);
        }catch (Exception e){
            _logger.error("抓取<仓库维护-库存量不足> 失败!!!!!"+ e.getMessage());
        }
        _logger.info("抓取<仓库维护-库存量不足> 结束!!!!");
    }


    /**
     * 推送消息
     */
    @Scheduled(cron = "0 */9 * * * ?")
    @DS(value = "tc_warning")
    public void sendInstockNotMoreErrorMsg(){
        _logger.info("开始推送 <仓库维护-库存量不足> ");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WARN_CODE);
        }catch (Exception e){
            _logger.error("推送<仓库维护-库存量不足>失败!!!"+e.getMessage());
        }
        _logger.info("推送 <仓库维护-库存量不足>结束......");
    }
}
