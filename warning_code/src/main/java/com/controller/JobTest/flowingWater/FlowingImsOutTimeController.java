package com.controller.JobTest.flowingWater;

import com.service.job.flowingWater.FlowingImsOutTimeJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/10/9 10:49
 * @Description:
 */
@RestController
@RequestMapping("/FlowingImsOutTimeController")
@Api("流水入账,IMS超24小时未返回--相关api")
public class FlowingImsOutTimeController {

    @Autowired
    private FlowingImsOutTimeJobTask flowingImsOutTimeOrErrorJobTask;

    @ApiOperation(value = "抓取流水入账,IMS超24小时未返回", notes = "抓取流水入账,IMS超24小时未返回")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        flowingImsOutTimeOrErrorJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送流水入账,IMS超24小时未返回", notes = "发送流水入账,IMS超24小时未返回")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        flowingImsOutTimeOrErrorJobTask.sendMsg();
        return "SUCCESS";
    }
}
