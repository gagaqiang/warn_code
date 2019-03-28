package com.controller.JobTest.invoicetest;

import com.service.job.invoice.InvoiceImsReturnsFailedJobTask;
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
@RequestMapping("/InvoiceImsReturnsFailedController")
@Api("发票接口，IMS返回失败（YJ00046）")
public class InvoiceImsReturnsFailedController {

    @Autowired
    private InvoiceImsReturnsFailedJobTask invoiceImsReturnsFailedJobTask;

    @ApiOperation(value = "抓取-->YJ00046", notes = "抓取-->YJ00046")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        invoiceImsReturnsFailedJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00046", notes = "发送-->YJ00046")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        invoiceImsReturnsFailedJobTask.sendMsg();
        return "SUCCESS";
    }
}
