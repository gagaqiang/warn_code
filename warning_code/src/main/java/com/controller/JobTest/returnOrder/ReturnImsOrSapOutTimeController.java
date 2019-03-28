package com.controller.JobTest.returnOrder;

import com.service.job.returnOrder.ReturnImsOrSapOutTimeJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/10/10 14:39
 * @Description:
 */
@RestController
@RequestMapping("/ReturnImsOrSapOutTimeController")
@Api("退货单，IMS,SAP超24小时未返回--相关api")
public class ReturnImsOrSapOutTimeController {

    @Autowired
    private ReturnImsOrSapOutTimeJobTask returnImsOrSapOutTimeJobTask;

    @ApiOperation(value = "抓取退货单，IMS,SAP超24小时未返回", notes = "抓取退货单，IMS,SAP超24小时未返回")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        returnImsOrSapOutTimeJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送退货单，IMS,SAP超24小时未返回", notes = "发送退货单，IMS,SAP超24小时未返回")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        returnImsOrSapOutTimeJobTask.sendErrorMsg();
        return "SUCCESS";
    }
}
