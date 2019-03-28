package com.utils.smsALiyun;

import com.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lag
 * @desc 短信相关常量类
 * @create 2018-01-12 10:08
 **/
public class SMSConstants {
    private static final Logger log = LoggerFactory.getLogger(SMSConstants.class);

    //产品名称:云通信短信API产品,开发者无需替换
    private static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    private static final String domain = "dysmsapi.aliyuncs.com";
    //阿里云账号的密钥
    private static String accessKeyId;
    private static String accessKeySecret;
    //短信签名 奥克斯空调
    private static String auxSignName;
    private static Map<String, String> smsErrorMap = new HashMap<String, String>();

    static {
        log.info(" ... init  static  SMSConstants   ...   ");
        smsErrorMap.put("OK", "请求成功");
        smsErrorMap.put("isp.RAM_PERMISSION_DENY", "RAM权限DENY");
        smsErrorMap.put("isv.OUT_OF_SERVICE", "业务停机");
        smsErrorMap.put("isv.PRODUCT_UN_SUBSCRIPT", "未开通云通信产品的阿里云客户");
        smsErrorMap.put("isv.PRODUCT_UNSUBSCRIBE", "产品未开通");
        smsErrorMap.put("isv.ACCOUNT_NOT_EXISTS", "账户不存在");
        smsErrorMap.put("isv.ACCOUNT_ABNORMAL", "账户异常");
        smsErrorMap.put("isv.SMS_TEMPLATE_ILLEGAL", "短信模板不合法");
        smsErrorMap.put("isv.SMS_SIGNATURE_ILLEGAL", "短信签名不合法");
        smsErrorMap.put("isv.INVALID_PARAMETERS", "参数异常");
        smsErrorMap.put("isp.SYSTEM_ERROR", "系统错误");
        smsErrorMap.put("isv.MOBILE_NUMBER_ILLEGAL", "非法手机号");
        smsErrorMap.put("isv.MOBILE_COUNT_OVER_LIMIT", "手机号码数量超过限制");
        smsErrorMap.put("isv.TEMPLATE_MISSING_PARAMETERS", "模板缺少变量");
        smsErrorMap.put("isv.BUSINESS_LIMIT_CONTROL", "业务限流");
        smsErrorMap.put("isv.INVALID_JSON_PARAM", "JSON参数不合法，只接受字符串值");
        smsErrorMap.put("isv.BLACK_KEY_CONTROL_LIMIT", "黑名单管控");
        smsErrorMap.put("isv.PARAM_LENGTH_LIMIT", "参数超出长度限制");
        smsErrorMap.put("isv.PARAM_NOT_SUPPORT_URL", "不支持URL");
        smsErrorMap.put("isv.AMOUNT_NOT_ENOUGH", "账户余额不足");

        accessKeyId = Constants.accessKeyId;
        accessKeySecret = Constants.accessKeySecret;
        auxSignName = Constants.auxSignName;
    }


    public static String getProduct() {
        return product;
    }

    public static String getDomain() {
        return domain;
    }

    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public static String getAuxSignName() {
        return auxSignName;
    }

    public static Map<String, String> getSmsErrorMap() {
        return smsErrorMap;
    }

    /**
     * @description: 查询错误信息
     * @author: lag
     * @date: 2018/1/12 14:44
     */
    public static String getErrorMsg(String errorCode) {
        return smsErrorMap.get(errorCode);
    }


}
