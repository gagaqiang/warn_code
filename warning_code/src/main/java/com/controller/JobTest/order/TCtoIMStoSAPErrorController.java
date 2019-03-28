package com.controller.JobTest.order;

import com.service.job.order.TCtoIMStoSAPErrorJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TCtoIMStoSAP")
@Api("TCtoIMStoSAPErrorController相关api")
public class TCtoIMStoSAPErrorController {

    public final static Logger _logger = LoggerFactory.getLogger(TCtoIMStoSAPErrorController.class);

    @Autowired
    private TCtoIMStoSAPErrorJobTask tCtoIMStoSAPErrorJobTask;

    @ApiOperation(value = "抓取订单-TC传IMS正常，IMS未传SAP", notes = "抓取订单-TC传IMS正常，IMS未传SAP")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        tCtoIMStoSAPErrorJobTask.getTCtoIMStoSAPErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送订单-TC传IMS正常，IMS未传SAP", notes = "发送订单-TC传IMS正常，IMS未传SAP")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        tCtoIMStoSAPErrorJobTask.sendTCtoIMStoSAPErrorMsg();
        return "SUCCESS";
    }
}
