package com.controller.JobTest.base;

import com.service.job.base.WarehouseJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/warehouseJob")
@Api("WarehouseJobController相关api")
public class WarehouseJobController {

    @Autowired
    private WarehouseJobTask warehouseJobService;

    @ApiOperation(value = "抓取【TC-基础管理仓库管理-仓库对照关系】未匹配大小仓", notes = "抓取【TC-基础管理仓库管理-仓库对照关系】未匹配大小仓")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        warehouseJobService.getCKErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送【TC-基础管理仓库管理-仓库对照关系】未匹配大小仓", notes = "发送【TC-基础管理仓库管理-仓库对照关系】未匹配大小仓")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        warehouseJobService.sendMsg();
        return "SUCCESS";
    }
}
