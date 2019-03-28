package com.controller.JobTest.invoicetest;

import com.service.job.invoice.InvoiceImsAndSapNoReturnsTimeoutJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/28 13:36
 * @Description:
 */
@RestController
@RequestMapping("/InvoiceImsAndSapNoReturnsTimeoutController")
@Api("发票接口，IMS、SAP超24小时未返回（YJ00045）")
public class InvoiceImsAndSapNoReturnsTimeoutController {

    @Autowired
    private InvoiceImsAndSapNoReturnsTimeoutJobTask invoiceImsAndSapNoReturnsTimeoutJobTask;


    @ApiOperation(value = "抓取-->YJ00045", notes = "抓取-->YJ00045")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        invoiceImsAndSapNoReturnsTimeoutJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00045", notes = "发送-->YJ00045")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        invoiceImsAndSapNoReturnsTimeoutJobTask.sendMsg();
        return "SUCCESS";
    }
}
