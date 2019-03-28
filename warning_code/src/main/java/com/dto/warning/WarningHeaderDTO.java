package com.dto.warning;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @Auther: hsl
 * @Date: 2018/8/23 16:44
 * @Description:
 */
@ApiModel
public class WarningHeaderDTO  implements Serializable {

    private List<WarningUserDTO> warningUserDTOList;
    private Integer warningHeaderId;
    private String times;

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
    private String program;
    private Integer status;
    private Integer total;
    private Integer processedNum;
    private Integer unProcessedNum;
    private Integer totalOrderNumber;
    private Integer currentOrderNumber;
    private Integer version;
    private String users;
    private String startTime;
    private String endTime;
    private String createTime;
    private String lastTime;
    private String codeList;
    private String programFileName;
    private String programFileUrl;
    private String programFileKey;
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

    public String getCodeList() {
        return codeList;
    }

    public void setCodeList(String codeList) {
        this.codeList = codeList;
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
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
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
        this.users = users;
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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Integer getWarningHeaderId() {
        return warningHeaderId;
    }

    public void setWarningHeaderId(Integer warningHeaderId) {
        this.warningHeaderId = warningHeaderId;
    }


    public List<WarningUserDTO> getWarningUserDTOList() {
        return warningUserDTOList;
    }

    public void setWarningUserDTOList(List<WarningUserDTO> warningUserDTOList) {
        this.warningUserDTOList = warningUserDTOList;
    }
}
