package com.utils;

import java.util.ResourceBundle;

/**
 * 常量设置类
 */
public class Constants {

    /** 文件存储*/
    public static final String FILE ="file";



    //http请求常量
    public static final String DEFAULT_CHARSET_UTF8 = "UTF-8";
    public static final String DEFAULT_CHARSET_GBK = "GBK";
    public static final String DEFAULT_CONTENT_TYPE = "application/json";
    public static final String REQUEST_METHOD = "post";

    //企业号消息接口常量
    public static final String CHAR_TYPE_SINGLE = "UTF-8";


    public static final String WX_TEXT_URL;
    public static final String WX_NEWS_URL;
    public static final String WX_MULNEWS_URL;
    public static final String WX_BTEXT_URL;
    public static final String WX_BNEWS_URL;
    public static final String WX_BMULNEWS_URL;

    public static final String GY_SMS_URL;
    public static final String GY_SMS_USERID;
    public static final String GY_SMS_PASSWD;
    public static final String GY_SMS_METHOD;


    public static final String ALIPAY_APPID;
    public static final String ALIPAY_PRIVATE_KEY;
    public static final String ALIPAY_PUBLIC_KEY;
    public static final String DOWNLOAD_PATH;
    public static final String ALIPAY_FLOWING_WATER_URL;
    public static final String AO_SEND_MSG_URL;

    public static final String accessKeyId;
    public static final String accessKeySecret;
    public static final String auxSignName;
    public static final String ALI_SMS_CODE;

    static {
        ResourceBundle resource = ResourceBundle.getBundle("config");
        WX_TEXT_URL = resource.getString("WX_TEXT_URL");
        WX_NEWS_URL = resource.getString("WX_NEWS_URL");
        WX_MULNEWS_URL = resource.getString("WX_MULNEWS_URL");
        WX_BTEXT_URL = resource.getString("WX_BTEXT_URL");
        WX_BNEWS_URL = resource.getString("WX_BNEWS_URL");
        WX_BMULNEWS_URL = resource.getString("WX_BMULNEWS_URL");

        GY_SMS_URL = resource.getString("GY_SMS_URL");
        GY_SMS_USERID = resource.getString("GY_SMS_USERID");
        GY_SMS_PASSWD = resource.getString("GY_SMS_PASSWD");
        GY_SMS_METHOD = resource.getString("GY_SMS_METHOD");

        ALIPAY_APPID = resource.getString("ALIPAY_APPID");
        ALIPAY_PRIVATE_KEY = resource.getString("ALIPAY_PRIVATE_KEY");
        ALIPAY_PUBLIC_KEY = resource.getString("ALIPAY_PUBLIC_KEY");
        DOWNLOAD_PATH = resource.getString("DOWNLOAD_PATH");
        ALIPAY_FLOWING_WATER_URL = resource.getString("ALIPAY_FLOWING_WATER_URL");
        AO_SEND_MSG_URL = resource.getString("AO_SEND_MSG_URL");

        accessKeyId = resource.getString("accessKeyId");
        accessKeySecret = resource.getString("accessKeySecret");
        auxSignName = resource.getString("auxSignName");
        ALI_SMS_CODE = resource.getString("ALI_SMS_CODE");

    }


    /**
     * 获取文件的后缀名
     */
    public static String getFileSuffixName(StringBuilder originalFilename){
        return originalFilename.substring(originalFilename.indexOf("."));
    }
}
