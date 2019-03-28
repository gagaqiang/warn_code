package com.dto;

/**
 * Mysql 服务器
 */
public final class MysqlSrvDTO {

    private static final int hashMultiplier = 31;
    private String taskId;
    private String id;
    private String ip;
    private Integer port;
    private String user;
    private String pwd;
    private String db;
    private String type;
    private String status;
    private String nameTask;
    private String note;
    private String serverType;
    private Long groupId;
    private String groupName;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        int hashedCode = hashMultiplier;
        if (taskId != null)
            hashedCode ^= taskId.hashCode() * hashMultiplier;
        if (id != null)
            hashedCode ^= id.hashCode() * hashMultiplier;
        if (ip != null)
            hashedCode ^= ip.hashCode() * hashMultiplier;
        if (port != null)
            hashedCode ^= port.hashCode() * hashMultiplier;
        if (user != null)
            hashedCode ^= user.hashCode() * hashMultiplier;
        if (pwd != null)
            hashedCode ^= pwd.hashCode() * hashMultiplier;
        if (db != null)
            hashedCode ^= db.hashCode() * hashMultiplier;
        if (type != null)
            hashedCode ^= type.hashCode() * hashMultiplier;
        if (status != null)
            hashedCode ^= status.hashCode() * hashMultiplier;
        if (nameTask != null)
            hashedCode ^= nameTask.hashCode() * hashMultiplier;
        if (note != null)
            hashedCode ^= note.hashCode() * hashMultiplier;
        if (serverType != null)
            hashedCode ^= serverType.hashCode() * hashMultiplier;
        if (groupId != null)
            hashedCode ^= groupId.hashCode() * hashMultiplier;
        if (groupName != null)
            hashedCode ^= groupName.hashCode() * hashMultiplier;
        return hashedCode;
    }
}
