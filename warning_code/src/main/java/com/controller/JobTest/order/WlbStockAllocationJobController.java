package com.controller.JobTest.order;

import com.service.job.order.BigWarehouseTransferJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/14 11:32
 * @Description:
 */
@RestController
@RequestMapping("/wlbStockAllocationJob")
@Api("WlbStockAllocationJobController相关api")
public class WlbStockAllocationJobController {

    @Autowired
    private BigWarehouseTransferJobTask wlbStockAllocationJobService;

    @ApiOperation(value = "抓取物流宝调拨单错误", notes = "抓取物流宝调拨单错误")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        wlbStockAllocationJobService.getWlbStockLog();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送物流宝调拨单错误", notes = "发送物流宝调拨单错误")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        wlbStockAllocationJobService.sendMsg();
        return "SUCCESS";
    }
}
