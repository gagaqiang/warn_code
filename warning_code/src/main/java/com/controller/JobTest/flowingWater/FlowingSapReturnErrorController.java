package com.controller.JobTest.flowingWater;

import com.service.job.flowingWater.FlowingSapReturnErrorJobTask;
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
@RequestMapping("/FlowingSapReturnErrorController")
@Api("流水入账,SAP超24小时未返回、返回失败--相关api")
public class FlowingSapReturnErrorController {

    @Autowired
    private FlowingSapReturnErrorJobTask flowingSapReturnErrorJobTask;

    @ApiOperation(value = "抓取流水入账,SAP返回失败信息", notes = "抓取流水入账,SAP返回失败信息")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        flowingSapReturnErrorJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送流水入账,SAP返回失败信息", notes = "发送流水入账,SAP返回失败信息")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        flowingSapReturnErrorJobTask.sendMsg();
        return "SUCCESS";
    }
}
