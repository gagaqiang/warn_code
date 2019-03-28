package com.controller.JobTest.invoicetest;

import com.service.job.invoice.InvoiceALiFailedJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/28 13:33
 * @Description:
 */
@RestController
@RequestMapping("/InvoiceALiFailedController")
@Api("阿里开票失败（YJ00043）")
public class InvoiceALiFailedController {

    @Autowired
    private InvoiceALiFailedJobTask invoiceALiFailedJobTask;

    @ApiOperation(value = "抓取-->YJ00043", notes = "抓取-->YJ00043")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        invoiceALiFailedJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00043", notes = "发送-->YJ00043")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        invoiceALiFailedJobTask.sendMsg();
        return "SUCCESS";
    }

}
