package com.controller;

import com.service.demo.RedisTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("redis整合springboot测试类")
@RequestMapping("/redisTest")
@RestController
public class RedisDemoController {

    public final static Logger _logger = LoggerFactory.getLogger(RedisDemoController.class);


    @Autowired
    private RedisTestService redisTestService;


    @ApiOperation(value = "setRedis", notes = "setRedis")
    @RequestMapping(value = "/setRedis", method = RequestMethod.GET)
    @ApiImplicitParam(name = "val", value = "val", paramType = "query", required = true, dataType = "String")
    public String setRedis(@RequestParam("val") String val) {
        redisTestService.setJedisVal(val);
        return "Redis success !";
    }

    @ApiOperation(value = "getRedisVal", notes = "getRedisVal")
    @RequestMapping(value = "/getRedisVal", method = RequestMethod.GET)
    public String getRedisVal() {
        return  redisTestService.getVal();
    }



    @ApiOperation(value = "setSerializeObjectTest", notes = "setSerializeObjectTest")
    @RequestMapping(value = "/setSerializeObjectTest", method = RequestMethod.GET)
    public String serializeTest() {
        redisTestService.setSerializeObjectTest();
        return "Redis success !";
    }

    @ApiOperation(value = "getSerializeObjectTest", notes = "getSerializeObjectTest")
    @RequestMapping(value = "/getSerializeObjectTest", method = RequestMethod.GET)
    public String getserializeTest() {
        redisTestService.getSerializeObjectTest();
        return "SUCCESS";
    }




}
