package com.controller.JobTest.order;

import com.service.job.order.WlbDeliverySynchronizationErrorJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/27 15:07
 * @Description:
 */
@RestController
@RequestMapping("/wlbDeliverySynchronizationErrorController")
@Api("物流宝发货信息同步TC失败(从物流宝抓取的退货单)")
public class WlbDeliverySynchronizationErrorController {


    @Autowired
    private WlbDeliverySynchronizationErrorJobTask wlbDeliverySynchronizationErrorJobTask;

    @ApiOperation(value = "抓取-->物流宝发货信息同步TC失败(从物流宝抓取的退货单)错误", notes = "抓取-->物流宝发货信息同步TC失败(从物流宝抓取的退货单)错误")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        wlbDeliverySynchronizationErrorJobTask.getCKErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->物流宝发货信息同步TC失败(从物流宝抓取的退货单)错误", notes = "发送-->物流宝发货信息同步TC失败(从物流宝抓取的退货单)错误")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        wlbDeliverySynchronizationErrorJobTask.sendMsg();
        return "SUCCESS";
    }
}


