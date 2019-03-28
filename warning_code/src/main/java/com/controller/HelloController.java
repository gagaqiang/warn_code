package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.TenantInfo;
import com.entity.warning.WarningGroup;
import com.entity.warning.WarningHeader;
import com.enums.ShopEnum;
import com.enums.WarningEnum;
import com.service.demo.SysService;
import com.service.warn.WarningSendMsgService;
import com.utils.web.RetCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@Api("swaggerTestController相关api")
public class HelloController {

    private final static Logger _logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private SysService sysService;
    @Autowired
    private WarningSendMsgService warningSendMsgService;


    @ApiOperation(value = "测试枚举", notes = "测试枚举")
    @RequestMapping(value = "/getWarnEnum", method = RequestMethod.GET)
    public Map getWarnEnum() {
        _logger.error("----WarnEnum---");
        return WarningEnum.getWarningMap();
    }



    @ApiOperation(value = "测试枚举", notes = "测试枚举")
    @RequestMapping(value = "/getShop", method = RequestMethod.GET)
    public Map getShop() {
        _logger.error("----ShopEnum---");
        return ShopEnum.getShopMap();
    }


    @ApiOperation(value = "测试swagger", notes = "hello-测试")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        _logger.error("----111111111111-2222---");
        return "Hello Spring Boot!";
    }

    @ApiOperation(value = "测试@RequestParam(\"id\")", notes = "测试@RequestParam(\"id\")")
    @ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true, dataType = "Long")
    @RequestMapping(value = "/getEntity", method = RequestMethod.GET)
    public RetCode<TenantInfo> getEntity(@RequestParam("id") Long id){
        RetCode<TenantInfo> r = new  RetCode<TenantInfo>();
        TenantInfo t = sysService.getEntity(id);
        r.setResult(t);
        r.setMsg("success");
        r.setRetCode("200");
        return r;
    }


    @ApiOperation(value = "测试注解--/users/{id}-PathVariable", notes = "测试注解-/users/{id}--PathVariable")
    @RequestMapping(value="/getName/{id}", method = RequestMethod.GET)
    public String getDetail(@PathVariable Long id){
        return  sysService.getName(id);
    }



    @ApiOperation(value = "测试add", notes = "测试add--数据库")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(){
        sysService.add();
        return  "ok";
    }


    @ApiOperation(value = "测试事物", notes = "测试事物--数据库")
    @RequestMapping(value = "/testTran", method = RequestMethod.POST)
    public String testTran(){
        sysService.testAdd();
        return  "ok";
    }


    @ApiOperation(value = "测试多参数", notes = "测试多参数")
    @RequestMapping(value = "/addEntity", method = RequestMethod.POST)
    public String addEntity(@ModelAttribute TenantInfo tenantInfo){
        _logger.info("测试多参数:"+ JSONObject.toJSONString(tenantInfo));
        sysService.testAddEntity(tenantInfo);
        return "success";
    }

    @ApiOperation(value="删除用户-传递数组", notes="删除对象，传递数组")
    @RequestMapping(value="/users/deleteByIds", method = RequestMethod.DELETE)
    public void deleteUser(@ApiParam("用户ID数组") @RequestParam Integer[] ids) {
        for (int id:ids){
            sysService.del(id);
        }
    }


    @ApiOperation(value = "测试多个数据库", notes = "多个数据库")
    @RequestMapping(value="/getTest", method = RequestMethod.GET)
    public String getTest(){
        sysService.test();
        return "SUCCESS";
    }


    @ApiOperation(value = "测试OA", notes = "测试OA")
    @RequestMapping(value="/sendOATest", method = RequestMethod.GET)
    public String sendOATest(){
        WarningHeader wh = new WarningHeader();
        wh.setId(52);
        wh.setType(3);
        wh.setUsers("小花");
        WarningGroup wg = new WarningGroup();
        wg.setId(18);
        warningSendMsgService.sendOAMsg(wh,wg);
        return "SUCCESS";
    }


}
