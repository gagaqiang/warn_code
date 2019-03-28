package com.controller;

import com.utils.smsALiyun.SmsALiUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: hsl
 * @Date: 2018/10/12 10:27
 * @Description:
 */
@RestController
@RequestMapping("/SmsALiUtilController")
@Api("阿里云发送短信接口相关api")
public class SmsALiUtilController {

    @Autowired
    private SmsALiUtil smsALiUtil;

    @ApiOperation(value = "阿里云发送短信接口相关api", notes = "阿里云发送短信接口相关api")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getTEST(){

        List<String> list = new ArrayList<>();
        list.add("13638648812");
        list.add("18668269823");
        smsALiUtil.send(list,"支付宝流水漏抓");
        return "SUCCESS";
    }
}
