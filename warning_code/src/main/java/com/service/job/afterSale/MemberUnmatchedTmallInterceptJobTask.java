package com.service.job.afterSale;

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
 * @Date: 2018/10/12 14:34
 * @Description:派工协议配置中会员代码未匹配导致天猫供销订单拦截(YJ00013)
 */
@Component
public class MemberUnmatchedTmallInterceptJobTask {

    public final static Logger _logger = LoggerFactory.getLogger(MemberUnmatchedTmallInterceptJobTask.class);

    @Autowired
    private TcErrorMsgService tcErrorMsgService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;

    @Scheduled(cron = "0 5 6,9,13,15,21 * * ?")
    @DS(value = "erp_tc")
    public void getErrorMsg() {
        _logger.info("开始抓取>派工协议配置中会员代码未匹配导致天猫供销订单拦截信息>>>");
        try {
            tcErrorMsgService.dealTcErrorMsg(WarningEnum.WARNING_YW_MEMBER_UNMATCHED.getId(), false);
        } catch (Exception e) {
            _logger.error("抓取>派工协议配置中会员代码未匹配导致天猫供销订单拦截信息>失败!!!" + e.getMessage());
        }
        _logger.info("抓取>派工协议配置中会员代码未匹配导致天猫供销订单拦截信息>结束......");
    }

    /**
     * 推送消息
     */
    //@Scheduled(cron = "0 15 10 15 * ?")
    @Scheduled(cron = "0 */15 * * * ?")
    @DS(value = "tc_warning")
    public void sendMsg() {
        _logger.info("开始推送>-派工协议配置中会员代码未匹配导致天猫供销订单拦截信息 >>>");
        try {
            warningSendMsgService.updateWarnAndSendMsg(WarningEnum.WARNING_YW_MEMBER_UNMATCHED.getId());
        } catch (Exception e) {
            _logger.error("推送>-派工协议配置中会员代码未匹配导致天猫供销订单拦截信息>失败!!!" + e.getMessage());
        }
        _logger.info("推送>-派工协议配置中会员代码未匹配导致天猫供销订单拦截信息>结束......");
    }
}

