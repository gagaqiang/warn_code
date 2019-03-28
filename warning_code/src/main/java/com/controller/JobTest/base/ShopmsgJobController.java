package com.controller.JobTest.base;

import com.service.job.base.ShopmsgJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopmsgJob")
@Api("ShopmsgJobController相关api")
public class ShopmsgJobController {

    public final static Logger _logger = LoggerFactory.getLogger(ShopmsgJobController.class);

    @Autowired
    private ShopmsgJobTask shopmsgJobService;


    @ApiOperation(value = "抓取TC-基础管理-店铺管理-店铺信息】未维护匹配", notes = "抓取TC-基础管理-店铺管理-店铺信息】未维护匹配")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        shopmsgJobService.getShopErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送TC-基础管理-店铺管理-店铺信息】未维护匹配", notes = "发送TC-基础管理-店铺管理-店铺信息】未维护匹配")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        shopmsgJobService.sendMsg();
        return "SUCCESS";
    }

}
