package com.controller.JobTest.invoicetest;

import com.service.job.invoice.InvoiceTimeOut888JobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api("发票888接口（YJ00014）")
@RestController
@RequestMapping("/invoiceTimeOut888")
public class InvoiceTimeOut888Controller {

    @Autowired
    private InvoiceTimeOut888JobTask timeOut888JobTask;

    @ApiOperation(value = "抓取-->YJ00014", notes = "抓取-->YJ00014")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        timeOut888JobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00014", notes = "发送-->YJ00014")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        timeOut888JobTask.sendMsg();
        return "SUCCESS";
    }
}
