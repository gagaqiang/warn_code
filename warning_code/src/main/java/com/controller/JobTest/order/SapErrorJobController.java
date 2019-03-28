package com.controller.JobTest.order;


import com.service.job.order.SapErrorJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sapErrorJob")
@Api("SapErrorJobController相关api")
public class SapErrorJobController {

    public final static Logger _logger = LoggerFactory.getLogger(SapErrorJobController.class);

    @Autowired
    private SapErrorJobTask sapErrorJobTask;


    @ApiOperation(value = "抓取订单sap转入失败", notes = "抓取订单sap转入失败")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        sapErrorJobTask.getSapErrorErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送订单sap转入失败", notes = "发送订单sap转入失败")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        sapErrorJobTask.sendSapErrorErrorMsg();
        return "SUCCESS";
    }
}
