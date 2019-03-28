package com.controller.JobTest.flowingWater;

import com.service.job.flowingWater.AccountJobTask;
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
@RequestMapping("/AccountController")
@Api("支付宝流水【账务类型=转账】手工入账预警")
public class AccountController {

    @Autowired
    private AccountJobTask accountJobTask;

    @ApiOperation(value = "抓取支付宝流水【账务类型=转账】手工入账信息", notes = "抓取支付宝流水【账务类型=转账】手工入账信息")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        accountJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送支付宝流水【账务类型=转账】手工入账信息", notes = "发送支付宝流水【账务类型=转账】手工入账信息")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        accountJobTask.sendMsg();
        return "SUCCESS";
    }
}

