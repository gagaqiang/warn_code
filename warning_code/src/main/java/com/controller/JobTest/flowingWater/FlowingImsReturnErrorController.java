package com.controller.JobTest.flowingWater;

import com.service.job.flowingWater.FlowingImsReturnErrorJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/10/9 13:06
 * @Description:
 */
@RestController
@RequestMapping("/FlowingImsReturnErrorController")
@Api("流水入账，IMS返回失败--相关api")
public class FlowingImsReturnErrorController {

    @Autowired
    private FlowingImsReturnErrorJobTask flowingImsReturnErrorJobTask;

    @ApiOperation(value = "抓取流水入账,IMS返回失败", notes = "抓取流水入账,IMS返回失败")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg() {
        flowingImsReturnErrorJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送流水入账,IMS返回失败", notes = "发送流水入账,IMS返回失败")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg() {
        flowingImsReturnErrorJobTask.sendMsg();
        return "SUCCESS";
    }
}
