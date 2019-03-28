package com.controller.JobTest.base;

import com.service.job.base.SmsJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smsJob")
@Api("SmsJobController相关api")
public class SmsJobController {
    public final static Logger _logger = LoggerFactory.getLogger(SmsJobController.class);

    @Autowired
    private SmsJobTask smsJobService;


    @ApiOperation(value = "抓取错误", notes = "抓取错误")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        smsJobService.getBalance();
        return "SUCCESS";
    }
}
