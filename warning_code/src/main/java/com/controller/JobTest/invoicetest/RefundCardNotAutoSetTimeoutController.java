package com.controller.JobTest.invoicetest;

import com.service.job.invoice.RefundCardNotAutoSetTimeoutJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/28 13:39
 * @Description:
 */
@RestController
@RequestMapping("/RefundCardNotAutoSetTimeoutController")
@Api("退款单正确，超24小时未自动结算（YJ00037）")
public class RefundCardNotAutoSetTimeoutController {

    @Autowired
    private RefundCardNotAutoSetTimeoutJobTask refundCardNotAutoSetTimeoutJobTask;

    @ApiOperation(value = "抓取-->YJ00037", notes = "抓取-->YJ00037")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        refundCardNotAutoSetTimeoutJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00037", notes = "发送-->YJ00037")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        refundCardNotAutoSetTimeoutJobTask.sendMsg();
        return "SUCCESS";
    }
}
