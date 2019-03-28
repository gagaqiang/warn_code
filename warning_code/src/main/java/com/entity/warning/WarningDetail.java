package com.entity.warning;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("WarningDetail")
public class WarningDetail {

    private Integer id;

    private Integer groupId;

    private String platfromCode;

    private Integer shopId;

    private String shopName;

    private Date touchTime;

    private Date sendTime;

    private Date delTime;

    private Integer status;

    private String timeConsuming;

    private Integer mins;

    private Integer isClose;

    private Date createTime;

    private String attribuate1;

    private String attribuate2;

    private String attribuate3;

    private Integer version;

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
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
        this.platfromCode = platfromCode == null ? null : platfromCode.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Date getTouchTime() {
        return touchTime;
    }

    public void setTouchTime(Date touchTime) {
        this.touchTime = touchTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
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
        this.timeConsuming = timeConsuming == null ? null : timeConsuming.trim();
    }

    public Integer getMins() {
        return mins;
    }

    public void setMins(Integer mins) {
        this.mins = mins;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAttribuate1() {
        return attribuate1;
    }

    public void setAttribuate1(String attribuate1) {
        this.attribuate1 = attribuate1 == null ? null : attribuate1.trim();
    }

    public String getAttribuate2() {
        return attribuate2;
    }

    public void setAttribuate2(String attribuate2) {
        this.attribuate2 = attribuate2 == null ? null : attribuate2.trim();
    }


    public String getAttribuate3() {
        return attribuate3;
    }

    public void setAttribuate3(String attribuate3) {
        this.attribuate3 = attribuate3 == null ? null : attribuate3.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsClose() {
        return isClose;
    }

    public void setIsClose(Integer isClose) {
        this.isClose = isClose;
    }
}