package com.controller.JobTest.order;

import com.service.job.order.TctoIMSErrorJobTask;
import com.service.warn.TcTradeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/tctoIMSErrorJob")
@Api("TctoIMSErrorJobController相关api")
public class TctoIMSErrorJobController {

    public final static Logger _logger = LoggerFactory.getLogger(TctoIMSErrorJobController.class);

    @Autowired
    private TctoIMSErrorJobTask tctoIMSErrorJobTask;
    @Autowired
    private TcTradeOrderService tcTradeOrderService;

    @ApiOperation(value = "抓取TC订单未传IMS或IMS未返回错误", notes = "抓取TC订单未传IMS或IMS未返回错误")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        tctoIMSErrorJobTask.getTctoIMSErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送TC订单未传IMS或IMS未返回错误", notes = "发送TC订单未传IMS或IMS未返回错误")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        tctoIMSErrorJobTask.sendTctoIMSErrorMsg();
        return "SUCCESS";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object getTest() {
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        list.add(0);
        list.add(1);
        return tcTradeOrderService.mapperTradeReturnOrderToErrorMsg("1","1", list,"2018-09-18 00:00:00", 1);
    }
}
