package com.controller.JobTest.invoicetest;


import com.service.job.invoice.IntegralOrderNotImportJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/IntegralOrderNotImport")
@Api("积分订单是否导入相关api")
public class IntegralOrderNotImportController {

    public final static Logger _logger = LoggerFactory.getLogger(IntegralOrderNotImportController.class);

    @Autowired
    private IntegralOrderNotImportJobTask integralOrderNotImportJobTask;

    @ApiOperation(value = "抓取积分订单是否导入相关", notes = "抓取积分订单是否导入相关")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        integralOrderNotImportJobTask.getIntegralOrderNotImportErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送积分订单是否导入相关", notes = "发送积分订单是否导入相关")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        integralOrderNotImportJobTask.sendIntegralOrderNotImportMsg();
        return "SUCCESS";
    }
}
