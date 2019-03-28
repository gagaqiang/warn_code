package com.entity.warning;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("WarningUserShop")
public class WarningUserShop {

    private Integer id;

    private Integer warningHeaderId;

    private Integer userId;

    private Integer shopId;

    private String shopName;

    private Date createDate;

    private String attribuate1;

    private String attribuate2;

    private String attribuate3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWarningHeaderId() {
        return warningHeaderId;
    }

    public void setWarningHeaderId(Integer warningHeaderId) {
        this.warningHeaderId = warningHeaderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
}