package com.controller.JobTest.order;

import com.service.job.order.WlbModelErrorJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/27 14:40
 * @Description:TC订单机型错抓(正向) 从平台抓取的退货单和从物流宝抓取的退货单做比对，数量、订单明细（商品）
 */
@RestController
@RequestMapping("/wlbModelErrorController")
@Api("从平台抓取的退货单和从物流宝抓取的退货单做比对，数量、订单明细（商品）")
public class WlbModelErrorController {


    @Autowired
    private WlbModelErrorJobTask wlbModelErrorJobTask;

    @ApiOperation(value = "抓取物流宝抓取的退货单错误", notes = "抓取物流宝抓取的退货单错误")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        wlbModelErrorJobTask.getWlbModelErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送物流宝抓取的退货单错误", notes = "发送物流宝抓取的退货单错误")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        wlbModelErrorJobTask.sendMsg();
        return "SUCCESS";
    }
}

