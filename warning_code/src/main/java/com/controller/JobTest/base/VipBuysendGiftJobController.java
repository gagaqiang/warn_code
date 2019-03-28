package com.controller.JobTest.base;

import com.service.job.base.VipBuysendGiftJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vipBuysendGiftJob")
@Api("VipBuysendGiftJobController相关api")
public class VipBuysendGiftJobController {

    public final static Logger _logger = LoggerFactory.getLogger(VipBuysendGiftJobController.class);

    @Autowired
    private VipBuysendGiftJobTask vipBuysendGiftJobService;


    @ApiOperation(value = "抓取金卡数量不足预警错误", notes = "抓取金卡数量不足预警错误")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        vipBuysendGiftJobService.getVipBuysendLog();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送金卡数量不足预警错误", notes = "发送金卡数量不足预警错误")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        vipBuysendGiftJobService.sendVipSendMsgJob();
        return "SUCCESS";
    }

}
