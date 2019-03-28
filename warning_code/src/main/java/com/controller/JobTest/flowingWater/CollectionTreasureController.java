package com.controller.JobTest.flowingWater;

import com.service.job.flowingWater.CollectionTreasureJobTask;
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
@RequestMapping("/CollectionTreasureController")
@Api("支付宝流水【账务类型=集分宝】手工入账预警")
public class CollectionTreasureController {

    @Autowired
    private CollectionTreasureJobTask collectionTreasureJobTask;

    @ApiOperation(value = "抓取支付宝流水【账务类型=集分宝】手工入账信息", notes = "抓取支付宝流水【账务类型=集分宝】手工入账信息")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        collectionTreasureJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送支付宝流水【账务类型=集分宝】手工入账信息", notes = "发送支付宝流水【账务类型=集分宝】手工入账信息")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        collectionTreasureJobTask.sendMsg();
        return "SUCCESS";
    }
}

