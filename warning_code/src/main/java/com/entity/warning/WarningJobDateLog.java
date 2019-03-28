package com.entity.warning;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("WarningJobDateLog")
public class WarningJobDateLog {

    private Integer id;

    private Integer warningHeaderId;

    private String headerCode;

    private Date createTime;

    private Date lastTime;

    private Integer version;

    private String attribuate1;

    private String attribuate2;

    private String attribuate3;

    private String date;

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

    public String getHeaderCode() {
        return headerCode;
    }

    public void setHeaderCode(String headerCode) {
        this.headerCode = headerCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }
}