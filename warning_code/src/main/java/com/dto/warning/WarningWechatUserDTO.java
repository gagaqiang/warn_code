package com.dto.warning;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @Auther: hsl
 * @Date: 2018/9/7 10:04
 * @Description:
 */
public class WarningWechatUserDTO implements Serializable {

    private Integer id;

    private String name;

    private String jobNumber;

    private String wechatUserId;

    private String phoneNumber;

    private String wechat;

    private Integer enabledFlag;

    private String createTime;

    private String lastTime;

    private Integer version;

    private String operUser;

    public String getOperUser() {
        if (StringUtils.isNotBlank(operUser)){
            try {
                operUser = java.net.URLDecoder.decode(operUser,"UTF-8");
            } catch (UnsupportedEncodingException e) {
            }
        }
        return operUser;
    }

    public void setOperUser(String operUser) {
        this.operUser = operUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(String wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
