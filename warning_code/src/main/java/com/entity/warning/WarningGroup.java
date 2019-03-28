package com.entity.warning;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("WarningGroup")
public class WarningGroup {

    private Integer id;

    private Integer warningHeaderId;

    private String detailCode;

    private Integer status;

    private Integer num;

    private Date createTime;

    private Date sendTime;

    private Date delTime;

    private String timeConsuming;

    private Integer version;

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

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode == null ? null : detailCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(String timeConsuming) {
        this.timeConsuming = timeConsuming == null ? null : timeConsuming.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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