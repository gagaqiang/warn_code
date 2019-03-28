package com.controller.JobTest.invoicetest;

import com.service.job.invoice.InvoiceHXFailedJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/28 14:00
 * @Description:航信开票失败（YJ00041）
 */
@RestController
@RequestMapping("/InvoiceHXFailedController")
@Api("航信开票失败（YJ00041）")
public class InvoiceHXFailedController {

    @Autowired
    private InvoiceHXFailedJobTask invoiceHXFailedJobTask;

    @ApiOperation(value = "抓取-->YJ00041", notes = "抓取-->YJ00041")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        invoiceHXFailedJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00041", notes = "发送-->YJ00041")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        invoiceHXFailedJobTask.sendMsg();
        return "SUCCESS";
    }

}

