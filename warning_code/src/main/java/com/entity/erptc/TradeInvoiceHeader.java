package com.entity.erptc;

import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

@Alias("TradeInvoiceHeader")
public class TradeInvoiceHeader {
    private Long id;
    private Date createDate;
    private Long shopId;
    private String platfromCode;
    private BigDecimal amount;
    private int imsInvoicePushSign;   //发票信息是否推送到IMS
    private String imsInvoicePushMsg;   //发票推送IMS返回消息
    private Date imsInvoicePushTime;   //发票推送IMS返回时间

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

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

    public String getPlatfromCode() {
        return platfromCode;
    }

    public void setPlatfromCode(String platfromCode) {
        this.platfromCode = platfromCode;
    }

    public int getImsInvoicePushSign() {
        return imsInvoicePushSign;
    }

    public void setImsInvoicePushSign(int imsInvoicePushSign) {
        this.imsInvoicePushSign = imsInvoicePushSign;
    }

    public String getImsInvoicePushMsg() {
        return imsInvoicePushMsg;
    }

    public void setImsInvoicePushMsg(String imsInvoicePushMsg) {
        this.imsInvoicePushMsg = imsInvoicePushMsg;
    }

    public Date getImsInvoicePushTime() {
        return imsInvoicePushTime;
    }

    public void setImsInvoicePushTime(Date imsInvoicePushTime) {
        this.imsInvoicePushTime = imsInvoicePushTime;
    }
}
