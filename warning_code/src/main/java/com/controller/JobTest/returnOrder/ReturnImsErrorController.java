package com.controller.JobTest.returnOrder;

import com.service.job.returnOrder.ReturnImsErrorJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/10/10 14:46
 * @Description:
 */
@RestController
@RequestMapping("/ReturnImsErrorController")
@Api("退货单，SAP返回失败--相关api")
public class ReturnImsErrorController {

    @Autowired
    private ReturnImsErrorJobTask returnImsErrorJobTask;

    @ApiOperation(value = "抓取退货单，SAP返回失败", notes = "抓取退货单，SAP返回失败")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        returnImsErrorJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送退货单，SAP返回失败", notes = "发送退货单，SAP返回失败")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        returnImsErrorJobTask.sendErrorMsg();
        return "SUCCESS";
    }
}

