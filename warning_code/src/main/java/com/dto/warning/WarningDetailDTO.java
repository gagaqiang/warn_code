package com.dto.warning;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @Auther: hsl
 * @Date: 2018/9/6 16:04
 * @Description:
 */
public class WarningDetailDTO implements Serializable {

    private Integer id;
    private Integer groupId;
    private String platfromCode;
    private Integer shopId;
    private String shopName;
    private Integer status;
    private String timeConsuming;
    private Integer mins;
    private Integer isClose;
    private String createTime;
    private String touchTime;
    private String sendTime;
    private String delTime;
    private String time;
    private String users;
    private String statusMean;
    private String isCloseMean;
    private String operUser;

    private String attribuate1;
    public String getAttribuate1() {
        return attribuate1;
    }

    public void setAttribuate1(String attribuate1) {
        this.attribuate1 = attribuate1;
    }

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

    public String getStatusMean() {
        return statusMean;
    }

    public void setStatusMean(String statusMean) {
        this.statusMean = statusMean;
    }

    public String getIsCloseMean() {
        return isCloseMean;
    }

    public void setIsCloseMean(String isCloseMean) {
        this.isCloseMean = isCloseMean;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getPlatfromCode() {
        return platfromCode;
    }

    public void setPlatfromCode(String platfromCode) {
        this.platfromCode = platfromCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(String timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public Integer getMins() {
        return mins;
    }

    public void setMins(Integer mins) {
        this.mins = mins;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTouchTime() {
        return touchTime;
    }

    public void setTouchTime(String touchTime) {
        this.touchTime = touchTime;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }
}
