package com.utils.smsALiyun;

import java.io.Serializable;

/**
 * @author lag
 * @desc 短信返回的结果
 * @create 2018-01-13 10:03
 **/
public class SMSResponse implements Serializable {

    private String bizId;//发送回执ID,可根据该ID查询具体的发送状态
    private String code;//状态码-返回OK代表请求成功,其他错误码详见错误码列表
    private String message; //状态码的描述
    private String outId;// (TC 代表系统， 后台为唯一判断是哪条短信)
    private Boolean isSuccess = false; //是否发送成功，只是发送到阿里云，不代码客户接收成功
    private String phoneNumbers;
    private String SignName;
    private String templateCode;
    private String paramValue;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getSignName() {
        return SignName;
    }

    public void setSignName(String signName) {
        SignName = signName;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
