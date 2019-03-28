package com.controller.JobTest.invoicetest;


import com.service.job.invoice.PaperInvoiceTimeoutJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PaperInvoiceTimeout")
@Api("已签收订单纸票开票超30天未开预警API")
public class PaperInvoiceTimeoutController {

    public final static Logger _logger = LoggerFactory.getLogger(PaperInvoiceTimeoutController.class);

    @Autowired
    private PaperInvoiceTimeoutJobTask paperInvoiceTimeoutJobTask;


    @ApiOperation(value = "抓取已签收订单纸票开票超30天未开预警", notes = "抓取已签收订单纸票开票超30天未开预警")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        paperInvoiceTimeoutJobTask.getInvoiceTimeoutErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送已签收订单纸票开票超30天未开预警", notes = "发送已签收订单纸票开票超30天未开预警")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        paperInvoiceTimeoutJobTask.sendInvoiceTimeoutMsg();
        return "SUCCESS";
    }

}
