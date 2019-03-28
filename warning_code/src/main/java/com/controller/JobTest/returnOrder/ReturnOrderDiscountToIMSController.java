package com.controller.JobTest.returnOrder;


import com.service.job.returnOrder.ReturnOrderDiscountToIMSTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 退货单处理后折扣转让信息同步给IMS异常
 */
@RestController
@RequestMapping("/returnOrderDiscountToIMS")
@Api("ReturnOrderDiscountToIMSController相关api")
public class ReturnOrderDiscountToIMSController {

    public final static Logger _logger = LoggerFactory.getLogger(ReturnOrderDiscountToIMSController.class);
    @Autowired
    private ReturnOrderDiscountToIMSTask returnOrderDiscountToIMSTask;

    @ApiOperation(value = "抓取【退货单处理后折扣转让信息同步给IMS异常", notes = "抓取退货单处理后折扣转让信息同步给IMS异常")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        returnOrderDiscountToIMSTask.getrErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送退货单处理后折扣转让信息同步给IMS异常", notes = "发送退货单处理后折扣转让信息同步给IMS异常")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        returnOrderDiscountToIMSTask.sendErrorMsg();
        return "SUCCESS";
    }
}
