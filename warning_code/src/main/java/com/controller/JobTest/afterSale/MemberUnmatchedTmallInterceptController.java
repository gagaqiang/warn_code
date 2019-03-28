package com.controller.JobTest.afterSale;

import com.service.job.afterSale.MemberUnmatchedTmallInterceptJobTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: hsl
 * @Date: 2018/10/12 14:40
 * @Description:
 */
@RestController
@RequestMapping("/MemberUnmatchedTmallInterceptController")
@Api("派工协议配置中会员代码未匹配导致天猫供销订单拦截")
public class MemberUnmatchedTmallInterceptController {
    @Autowired
    private MemberUnmatchedTmallInterceptJobTask memberUnmatchedTmallInterceptJobTask;

    @ApiOperation(value = "抓取派工协议配置中会员代码未匹配导致天猫供销订单拦截信息",
            notes = "抓取派工协议配置中会员代码未匹配导致天猫供销订单拦截信息")
    @RequestMapping(value = "/getErrorMsg", method = RequestMethod.GET)
    public String getErrorMsg(){
        memberUnmatchedTmallInterceptJobTask.getErrorMsg();
        return "SUCCESS";
    }


    @ApiOperation(value = "发送派工协议配置中会员代码未匹配导致天猫供销订单拦截信息",
            notes = "发送派工协议配置中会员代码未匹配导致天猫供销订单拦截信息")
    @RequestMapping(value = "/sendErrorMsg", method = RequestMethod.GET)
    public String sendErrorMsg(){
        memberUnmatchedTmallInterceptJobTask.sendMsg();
        return "SUCCESS";
    }
}


