package com.entity.erpbase;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * VipBuysendGift
 */
@Alias("VipBuysendGift")
public class VipBuysendGiftInfo {
    private Long id;
    private Date createDate;
    private Date modifyDate;
    private int version;
    private Long tenantId;
    private Long pid;
    private Long skuId;
    private String qty;
    private Double originPrice;
    private String itemName;
    private String itemSimpleName;
    private String itemSkuName;
    private String itemCode;
    private String itemSkuCode;
    private boolean del;
    private String buyactivityQty;
    private String giveQty;

    public String getBuyactivityQty() {
        return buyactivityQty == null ? "0" : buyactivityQty;
    }

    public void setBuyactivityQty(String buyactivityQty) {
        this.buyactivityQty = buyactivityQty;
    }

    public String getGiveQty() {
        return giveQty == null ? "0" : giveQty;
    }

    public void setGiveQty(String giveQty) {
        this.giveQty = giveQty;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getQty() {
        return qty == null ? "0" : qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Double originPrice) {
        this.originPrice = originPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSimpleName() {
        return itemSimpleName;
    }

    public void setItemSimpleName(String itemSimpleName) {
        this.itemSimpleName = itemSimpleName;
    }

    public String getItemSkuName() {
        return itemSkuName;
    }

    public void setItemSkuName(String itemSkuName) {
        this.itemSkuName = itemSkuName;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemSkuCode() {
        return itemSkuCode;
    }

    public void setItemSkuCode(String itemSkuCode) {
        this.itemSkuCode = itemSkuCode;
    }
}
