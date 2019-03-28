package com.entity.erptc;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("TradeOrderHeader")
public class TradeOrderHeader {
    private Long id;
    private Date createDate;
    private Long shopId;
    private String platformCode;
    private int imsOrderPushSign;   //订单信息是否推送到IMS
    private String imsOrderPushMsg;   //订单推送IMS返回消息
    private Date imsOrderPushTime;   //订单推送IMS返回时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public int getImsOrderPushSign() {
        return imsOrderPushSign;
    }

    public void setImsOrderPushSign(int imsOrderPushSign) {
        this.imsOrderPushSign = imsOrderPushSign;
    }

    public String getImsOrderPushMsg() {
        return imsOrderPushMsg;
    }

    public void setImsOrderPushMsg(String imsOrderPushMsg) {
        this.imsOrderPushMsg = imsOrderPushMsg;
    }

    public Date getImsOrderPushTime() {
        return imsOrderPushTime;
    }

    public void setImsOrderPushTime(Date imsOrderPushTime) {
        this.imsOrderPushTime = imsOrderPushTime;
    }
}
