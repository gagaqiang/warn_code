package com.dto.warning;

public class WarningGroupDTO {

    //预警项ID
    private Integer headId;
    //预警项编码
    private String code;
    //名称
    private String name;
    //预警对象
    private String users;
    //分组ID
    private Integer groupId;
    //分组-明细编码
    private String groupCode;
    //发送时间
    private String sendTime;
    //状态
    private String status;
    //状态 汉字
    private String statusName;
    //单量
    private Integer num;
    //处理完成时间
    private String delTime;
    //处理耗时
    private String timeCon;
    //方案
    private String program;
    //已处理数
    private Integer dealNum;
    //未处理数
    private Integer nuDealNum;
    //总影响数
    private Integer allNum;
    //方案文件名
    private String programFileName;
    //方案文件地址
    private String programFileUrl;
    //方案文件在阿里云服务器的key
    private String programFileKey;

    private String operUser;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOperUser() {
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getDealNum() {
        return dealNum;
    }

    public void setDealNum(Integer dealNum) {
        this.dealNum = dealNum;
    }

    public Integer getNuDealNum() {
        return nuDealNum;
    }

    public void setNuDealNum(Integer nuDealNum) {
        this.nuDealNum = nuDealNum;
    }

    public Integer getAllNum() {
        return allNum;
    }

    public void setAllNum(Integer allNum) {
        this.allNum = allNum;
    }

    public Integer getHeadId() {
        return headId;
    }

    public void setHeadId(Integer headId) {
        this.headId = headId;
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

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDelTime() {
        return delTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public String getTimeCon() {
        return timeCon;
    }

    public void setTimeCon(String timeCon) {
        this.timeCon = timeCon;
    }
}
