package com.controller.JobTest.base;

import com.service.job.base.WarningStatisticsTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yihao on 2018/11/20.
 */

@RestController
@RequestMapping("/warningStatistics")
@Api("WarningHeaderTaskController相关api")
public class WarningStatisticsController {


    @Autowired
    private WarningStatisticsTask warningStatisticsTask;


    @ApiOperation(value = "全部数据", notes = "全部数据")
    @RequestMapping(value = "/getAllErrorMsg", method = RequestMethod.GET)
    public String getAllErrorMsg(){
        warningStatisticsTask.getWarningStatisticsAndSendMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "it数据", notes = "it数据")
    @RequestMapping(value = "/getITErrorMsg", method = RequestMethod.GET)
    public String getITErrorMsg(){
        warningStatisticsTask.getITWarningStatisticsAndSendMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "yw数据", notes = "yw数据")
    @RequestMapping(value = "/getywErrorMsg", method = RequestMethod.GET)
    public String getywErrorMsg(){
        warningStatisticsTask.getYWWarningStatisticsAndSendMsg();
        return "SUCCESS";
    }

}
