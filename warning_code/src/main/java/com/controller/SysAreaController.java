package com.controller;


import com.entity.erpsystem.SysAreaInfo;
import com.service.erpsystem.SysAreaService;
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

@RestController("/sysArea")
@Api("swaggerTestController相关api")
public class SysAreaController {

    public final static Logger _logger = LoggerFactory.getLogger(SysAreaController.class);

    @Autowired
    private SysAreaService sysAreaService;

    @ApiOperation(value = "测试getEntity", notes = "测试getEntity--数据库")
    @ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true, dataType = "Long")
    @RequestMapping(value = "/getSysAreaEntity", method = RequestMethod.GET)
    public SysAreaInfo getSysAreaEntity(@RequestParam("id") Long id){
        return  sysAreaService.getEntity(id);
    }

}
