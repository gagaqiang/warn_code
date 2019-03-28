package com.controller.JobTest.invoicetest;

import com.service.job.invoice.InvoiceAmountIsZeroJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/28 13:56
 * @Description:金额正常而开票金额为0(YJ00035)
 */
@RestController
@RequestMapping("/InvoiceAmountIsZeroController")
@Api("金额正常而开票金额为0(YJ00035)")
public class InvoiceAmountIsZeroController {

    @Autowired
    private InvoiceAmountIsZeroJobTask invoiceAmountIsZeroJobTask;

    @ApiOperation(value = "抓取-->YJ00035", notes = "抓取-->YJ00035")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        invoiceAmountIsZeroJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00035", notes = "发送-->YJ00035")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        invoiceAmountIsZeroJobTask.sendMsg();
        return "SUCCESS";
    }

}

