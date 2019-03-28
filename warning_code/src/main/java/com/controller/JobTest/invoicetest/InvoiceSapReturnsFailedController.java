package com.controller.JobTest.invoicetest;

import com.service.job.invoice.InvoiceSapReturnsFailedJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/28 13:37
 * @Description:
 */
@RestController
@RequestMapping("/InvoiceSapReturnsFailedController")
@Api("发票接口，SAP返回失败（YJ00047）")
public class InvoiceSapReturnsFailedController {

    @Autowired
    private InvoiceSapReturnsFailedJobTask invoiceSapReturnsFailedJobTask;

    @ApiOperation(value = "抓取-->YJ00047", notes = "抓取-->YJ00047")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        invoiceSapReturnsFailedJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00047", notes = "发送-->YJ00047")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        invoiceSapReturnsFailedJobTask.sendMsg();
        return "SUCCESS";
    }
}
