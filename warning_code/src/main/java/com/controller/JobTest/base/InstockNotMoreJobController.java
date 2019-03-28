package com.controller.JobTest.base;

import com.service.job.base.InstockNotMoreJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 仓库维护-库存量不足 YJ00006
 */
@RestController
@RequestMapping("/InstockNotMoreJobController")
@Api("仓库维护-库存量不足 相关api")
public class InstockNotMoreJobController {

    @Autowired
    private InstockNotMoreJobTask instockNotMoreJobTask;

    @ApiOperation(value = "抓取商仓库维护-库存量不足", notes = "抓取仓库维护-库存量不足")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        instockNotMoreJobTask.getInstockNotMoreErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送仓库维护-库存量不足", notes = "发送仓库维护-库存量不足")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        instockNotMoreJobTask.sendInstockNotMoreErrorMsg();
        return "SUCCESS";
    }
}
