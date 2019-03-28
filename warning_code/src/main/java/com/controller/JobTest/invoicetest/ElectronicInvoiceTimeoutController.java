package com.controller.JobTest.invoicetest;


import com.service.job.invoice.ElectronicInvoiceTimeoutJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ElectronicInvoiceTimeout")
@Api("已签收订单电子发票开票超15天未开预警api(YJ00039)")
public class ElectronicInvoiceTimeoutController {

    @Autowired
    private ElectronicInvoiceTimeoutJobTask invoiceTimeoutJobTask;


    @ApiOperation(value = "抓取已签收订单电子发票开票超15天未开预警", notes = "抓取已签收订单电子发票开票超15天未开预警")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        invoiceTimeoutJobTask.getInvoiceTimeoutErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送已签收订单电子发票开票超15天未开预警", notes = "发送已签收订单电子发票开票超15天未开预警")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        invoiceTimeoutJobTask.sendInvoiceTimeoutMsg();
        return "SUCCESS";
    }
}
