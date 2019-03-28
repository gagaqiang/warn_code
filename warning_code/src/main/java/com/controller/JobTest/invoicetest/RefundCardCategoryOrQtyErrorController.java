package com.controller.JobTest.invoicetest;

import com.service.job.invoice.RefundCardCategoryOrQtyErrorJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/28 13:38
 * @Description:
 */
@RestController
@RequestMapping("/RefundCardCategoryOrQtyErrorController")
@Api("退款单（种类、数量）错误（YJ00036）")
public class RefundCardCategoryOrQtyErrorController {

    @Autowired
    private RefundCardCategoryOrQtyErrorJobTask refundCardCategoryOrQtyErrorJobTask;

    @ApiOperation(value = "抓取-->YJ00036", notes = "抓取-->YJ00036")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        refundCardCategoryOrQtyErrorJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00036", notes = "发送-->YJ00036")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        refundCardCategoryOrQtyErrorJobTask.sendMsg();
        return "SUCCESS";
    }
}

