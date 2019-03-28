package com.entity.warning;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("WarningHeader")
public class WarningHeader implements Serializable {

    private Integer id;

    private Integer type;

    private String code;

    private String name;

    private String notes;

    private String message;

    private Integer warnType;

    private Integer frequencyType;

    private Integer days;

    private Integer hours;

    private Integer mins;

    private String startTime;

    private String endTime;

    private String program;

    private String programFileName;

    private String programFileUrl;

    private String programFileKey;

    private Date createTime;

    private Date lastTime;

    private Integer status;

    private Integer total;

    private Integer processedNum;

    private Integer unProcessedNum;

    private Integer totalOrderNumber;

    private Integer currentOrderNumber;

    private Integer version;

    private String users;

    private String attribuate1;

    private String attribuate2;

    private String attribuate3;

    public String getProgramFileKey() {
        return programFileKey;
    }

    public void setProgramFileKey(String programFileKey) {
        this.programFileKey = programFileKey;
    }

    public String getProgramFileName() {
        return programFileName;
    }

    public void setProgramFileName(String programFileName) {
        this.programFileName = programFileName;
    }

    public String getProgramFileUrl() {
        return programFileUrl;
    }

    public void setProgramFileUrl(String programFileUrl) {
        this.programFileUrl = programFileUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getWarnType() {
        return warnType;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public Integer getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(Integer frequencyType) {
        this.frequencyType = frequencyType;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMins() {
        return mins;
    }

    public void setMins(Integer mins) {
        this.mins = mins;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program == null ? null : program.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getProcessedNum() {
        return processedNum;
    }

    public void setProcessedNum(Integer processedNum) {
        this.processedNum = processedNum;
    }

    public Integer getUnProcessedNum() {
        return unProcessedNum;
    }

    public void setUnProcessedNum(Integer unProcessedNum) {
        this.unProcessedNum = unProcessedNum;
    }

    public Integer getTotalOrderNumber() {
        return totalOrderNumber;
    }

    public void setTotalOrderNumber(Integer totalOrderNumber) {
        this.totalOrderNumber = totalOrderNumber;
    }

    public Integer getCurrentOrderNumber() {
        return currentOrderNumber;
    }

    public void setCurrentOrderNumber(Integer currentOrderNumber) {
        this.currentOrderNumber = currentOrderNumber;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users == null ? null : users.trim();
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