package com.controller.JobTest.base;


import com.service.job.base.UserErrorJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userErrorJob")
@Api("UserErrorJobController测试")
public class UserErrorJobController {

    public final static Logger _logger = LoggerFactory.getLogger(GoodsErrorJobController.class);

    @Autowired
    private UserErrorJobTask userErrorJobTask;


    @ApiOperation(value = "抓取用户信息未维护错误", notes = "抓取用户信息未维护错误")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        _logger.info("UserErrorJobController >>> + 抓取错误 ");
        userErrorJobTask.getUserErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送用户信息未维护错误", notes = "发送用户信息未维护错误")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        _logger.info("UserErrorJobController >>> + 发送错误 ");
        userErrorJobTask.sendMsg();
        return "SUCCESS";
    }
}
