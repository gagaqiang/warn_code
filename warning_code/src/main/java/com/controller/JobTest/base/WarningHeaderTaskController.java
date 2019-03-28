package com.controller.JobTest.base;

import com.service.job.base.WarningHeaderTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 数据更新
 */
@RestController
@RequestMapping("/warningHeader")
@Api("WarningHeaderTaskController相关api")
public class WarningHeaderTaskController {

    @Autowired
    private WarningHeaderTask warningHeaderTask;


    @ApiOperation(value = "数据更新", notes = "数据更新")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        warningHeaderTask.getUserErrorMsg();
        return "SUCCESS";
    }


}
