package com.controller.JobTest.flowingWater;

import com.service.job.flowingWater.MarginRefundJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/10/11 15:46
 * @Description:
 */
@RestController
@RequestMapping("/MarginRefundController")
@Api("保证金退款预警")
public class MarginRefundController {

    @Autowired
    private MarginRefundJobTask marginRefundJobTask;

    @ApiOperation(value = "抓取保证金退款信息", notes = "抓取保证金退款信息")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        marginRefundJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送保证金退款信息", notes = "发送保证金退款信息")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        marginRefundJobTask.sendMsg();
        return "SUCCESS";
    }
}

