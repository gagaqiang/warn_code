package com.utils.smsGY;

import com.alibaba.fastjson.JSONObject;
import com.utils.Constants;
import gy.lib.sms.BaseResponse;
import gy.lib.sms.request.GetBalanceRequest;
import gy.lib.sms.request.SendRequest;
import gy.lib.sms.response.GetBalanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: hsl
 * @Date: 2018/10/11 16:27
 * @Description:管易发送短信接口
 */
public class SmsGYUtil {

    public final static Logger _logger = LoggerFactory.getLogger(SmsGYUtil.class);

    // 发送短信
    public static boolean sendMsg(String content, List<String> params) {
        if (params.isEmpty() || params == null) {
            _logger.error("发送人手机号为空");
            return false;
        }
        for (String mobile : params) {
            SendRequest request = new SendRequest();
            request.setUserId(Constants.GY_SMS_USERID);//GY-SMS-01899
            request.setPwd(Constants.GY_SMS_PASSWD);//146764

//            request.setUserId("GY-SMS-01301");//GY-SMS-01301
//            request.setPwd("652038");//652038
            request.setMobile(mobile);
            request.setContent(content);
            request.setType("1");
            BaseResponse rsp = request.getResponse();
            _logger.info("rsp==>>" + JSONObject.toJSONString(rsp));
            if (rsp.getStatus() == 0) {
                _logger.info("手机号为:" + mobile + ",信息发送成功");
            } else {
                _logger.error("发送人手机号为空" + rsp.getError().getMessage());
                return false;
            }
        }
        return true;
    }

    // 查询短信余额
    public static Integer getBalance() {
        GetBalanceRequest request = new GetBalanceRequest();
        request.setUserId(Constants.GY_SMS_USERID);//GY-SMS-01899
        request.setPwd(Constants.GY_SMS_PASSWD);//146764
        GetBalanceResponse rsp = request.getResponse();
        if (rsp.getStatus() == 0) {
            _logger.info("rsp==>>" + JSONObject.toJSONString(rsp));
            return rsp.getBalance().getQty();
        } else {
            _logger.info("rsp==>>" + rsp.getError().getMessage());
            return -1;
        }
    }

    public static void main(String[] args) {
        String content = "测试管易发送短信接口";
        List<String> params = new ArrayList<>();
        params.add("18826137374");
        try {
            sendMsg(content,params);
            getBalance();
        } catch (Exception e) {
        }
    }


}
