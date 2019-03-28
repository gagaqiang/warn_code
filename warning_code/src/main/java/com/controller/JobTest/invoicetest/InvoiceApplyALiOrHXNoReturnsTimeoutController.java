package com.controller.JobTest.invoicetest;

import com.service.job.invoice.InvoiceApplyALiOrHXNoReturnsTimeoutJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/28 13:35
 * @Description:
 */
@RestController
@RequestMapping("/InvoiceApplyALiOrHXNoReturnsTimeoutController")
@Api("阿里或者航信开票超24小时未返回（YJ00044）")
public class InvoiceApplyALiOrHXNoReturnsTimeoutController {

    @Autowired
    private InvoiceApplyALiOrHXNoReturnsTimeoutJobTask invoiceApplyALiOrHXNoReturnsTimeoutJobTask;

    @ApiOperation(value = "抓取-->YJ00044", notes = "抓取-->YJ00044")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        invoiceApplyALiOrHXNoReturnsTimeoutJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00044", notes = "发送-->YJ00044")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        invoiceApplyALiOrHXNoReturnsTimeoutJobTask.sendMsg();
        return "SUCCESS";
    }
}
