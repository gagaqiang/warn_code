package com.utils.smsALiyun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.service.redis.WarnHeadRedis;
import com.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: hsl
 * @Date: 2018/10/12 09:31
 * @Description:阿里云发送短信接口
 */
@Service
public class SmsALiUtil {

    private static final Logger log = LoggerFactory.getLogger(SmsALiUtil.class);

    @Autowired
    private WarnHeadRedis redisUtil;


    /**
     * @param phoneNumbers 电话号码  支持多个，以逗号隔开
     * @param SignName     短信签名 签名目前固定 在配置文件里
     * @param templateCode 阿里的短信模版
     * @param map          短信模版中的参数值（具体请查看短信模板）
     * @description: 阿里云短信发送
     * @author: lag
     * @date: 2018/1/11 14:07
     */
    public SMSResponse sendSms(String phoneNumbers, String SignName, String templateCode, Map map) {
        //设置返回对像
        SMSResponse smsResponse = new SMSResponse();
        //isSuccess 默认是失败的
        String mapstr = JSON.toJSONString(map);
        smsResponse.setPhoneNumbers(phoneNumbers);
        smsResponse.setSignName(SignName);
        smsResponse.setTemplateCode(templateCode);
        smsResponse.setParamValue(mapstr);
        try {
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", SMSConstants.getAccessKeyId(), SMSConstants.getAccessKeySecret());
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", SMSConstants.getProduct(), SMSConstants.getDomain());
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phoneNumbers);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(SignName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(mapstr);

            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");

            String outId = "TC_WARNING:" + redisUtil.getIncrement("key:smskey");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者 (TC 代表系统， 后台为唯一判断是哪条短信)
            request.setOutId(outId);

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse != null) {
                smsResponse.setBizId(sendSmsResponse.getBizId());
                smsResponse.setCode(sendSmsResponse.getCode());
                smsResponse.setMessage(sendSmsResponse.getMessage());
                smsResponse.setOutId(outId);
                smsResponse.setIsSuccess("OK".equals(sendSmsResponse.getCode()));
            }
        } catch (Exception e) {
            log.error("短信发送异常 号码【" + phoneNumbers + "】 短信模版【" + templateCode + "】 参数值 【" + mapstr + "】" + e.getMessage());
        }
        return smsResponse;
    }

    public boolean send(List<String> list, String message) {
        StringBuffer sb = new StringBuffer();
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    sb.append(list.get(0));
                } else {
                    sb.append(",").append(list.get(i));
                }
            }
        }
        Map<String, Object> smsparamMap = new HashMap<>();
        smsparamMap.put("yjmessage", message);
        SMSResponse smsResponse = sendSms(sb.toString(), SMSConstants.getAuxSignName(), Constants.ALI_SMS_CODE, smsparamMap);
        log.info("阿里云发送短信返回结果==》"+JSONObject.toJSONString(smsResponse));
        if (smsResponse != null && smsResponse.getIsSuccess()) {
            return  true;
        }else {
            return  false;
        }
    }

}
