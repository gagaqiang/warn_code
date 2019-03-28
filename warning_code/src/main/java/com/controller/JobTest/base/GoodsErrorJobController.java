package com.controller.JobTest.base;


import com.service.job.base.GoodsErrorJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goodsErrorJob")
@Api("GoodsErrorJobController相关api")
public class GoodsErrorJobController {

    public final static Logger _logger = LoggerFactory.getLogger(GoodsErrorJobController.class);

    @Autowired
    private GoodsErrorJobTask goodsErrorJobTask;

    @ApiOperation(value = "抓取商品信息未维护、未匹配", notes = "抓取商品信息未维护、未匹配")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        goodsErrorJobTask.getGoodsErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送商品信息未维护、未匹配", notes = "发送商品信息未维护、未匹配")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        goodsErrorJobTask.sendMsg();
        return "SUCCESS";
    }
}
