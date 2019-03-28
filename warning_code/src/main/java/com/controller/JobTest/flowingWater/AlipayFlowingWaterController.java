package com.controller.JobTest.flowingWater;

import com.service.job.flowingWater.AlipayFlowingWaterTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/9/28 14:31
 * @Description:YJ00034
 */
@RestController
@RequestMapping("/AlipayFlowingWaterController")
@Api("支付宝流水漏抓(YJ00034)")
public class AlipayFlowingWaterController {

    @Autowired
    private AlipayFlowingWaterTask alipayFlowingWaterTask;

    @ApiOperation(value = "抓取-->YJ00034", notes = "抓取-->YJ00034")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        alipayFlowingWaterTask.getAlipayFlowingWater();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送-->YJ00034", notes = "发送-->YJ00034")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        alipayFlowingWaterTask.sendMsg();
        return "SUCCESS";
    }

}
