package com.controller.JobTest.returnOrder;


import com.controller.JobTest.order.SapErrorJobController;
import com.service.job.returnOrder.ReturnOrderExceptionJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 【订单管理-退货管理-异常退货】中超45天未审核预警
 */
@RestController
@RequestMapping("/returnOrderException")
@Api("ReturnOrderExceptionController相关api")
public class ReturnOrderExceptionController {


    public final static Logger _logger = LoggerFactory.getLogger(SapErrorJobController.class);

    @Autowired
    private ReturnOrderExceptionJobTask returnOrderExceptionJobTask;

    @ApiOperation(value = "抓取【订单管理-退货管理-异常退货】中超45天未审核预警", notes = "抓取【订单管理-退货管理-异常退货】中超45天未审核预警")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        returnOrderExceptionJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送【订单管理-退货管理-异常退货】中超45天未审核预警", notes = "发送【订单管理-退货管理-异常退货】中超45天未审核预警")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        returnOrderExceptionJobTask.sendErrorMsg();
        return "SUCCESS";
    }
}
