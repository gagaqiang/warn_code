package com.entity.warning;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("WarningSendMessageLog")
public class WarningSendMessageLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.warning_header_id
     *
     * @mbg.generated
     */
    private Integer warningHeaderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.warning_code
     *
     * @mbg.generated
     */
    private String warningCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.group_id
     *
     * @mbg.generated
     */
    private Integer groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.group_code
     *
     * @mbg.generated
     */
    private String groupCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.warn_type
     *
     * @mbg.generated
     */
    private Integer warnType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.send_time
     *
     * @mbg.generated
     */
    private Date sendTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.note
     *
     * @mbg.generated
     */
    private String note;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.attribuate1
     *
     * @mbg.generated
     */
    private String attribuate1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.attribuate2
     *
     * @mbg.generated
     */
    private String attribuate2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.status
     *
     * @mbg.generated
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column warning_send_message_log.version
     *
     * @mbg.generated
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.id
     *
     * @return the value of warning_send_message_log.id
     *
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.id
     *
     * @param id the value for warning_send_message_log.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.warning_header_id
     *
     * @return the value of warning_send_message_log.warning_header_id
     *
     * @mbg.generated
     */
    public Integer getWarningHeaderId() {
        return warningHeaderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.warning_header_id
     *
     * @param warningHeaderId the value for warning_send_message_log.warning_header_id
     *
     * @mbg.generated
     */
    public void setWarningHeaderId(Integer warningHeaderId) {
        this.warningHeaderId = warningHeaderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.warning_code
     *
     * @return the value of warning_send_message_log.warning_code
     *
     * @mbg.generated
     */
    public String getWarningCode() {
        return warningCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.warning_code
     *
     * @param warningCode the value for warning_send_message_log.warning_code
     *
     * @mbg.generated
     */
    public void setWarningCode(String warningCode) {
        this.warningCode = warningCode == null ? null : warningCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.group_id
     *
     * @return the value of warning_send_message_log.group_id
     *
     * @mbg.generated
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.group_id
     *
     * @param groupId the value for warning_send_message_log.group_id
     *
     * @mbg.generated
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.group_code
     *
     * @return the value of warning_send_message_log.group_code
     *
     * @mbg.generated
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.group_code
     *
     * @param groupCode the value for warning_send_message_log.group_code
     *
     * @mbg.generated
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.warn_type
     *
     * @return the value of warning_send_message_log.warn_type
     *
     * @mbg.generated
     */
    public Integer getWarnType() {
        return warnType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.warn_type
     *
     * @param warnType the value for warning_send_message_log.warn_type
     *
     * @mbg.generated
     */
    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.send_time
     *
     * @return the value of warning_send_message_log.send_time
     *
     * @mbg.generated
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.send_time
     *
     * @param sendTime the value for warning_send_message_log.send_time
     *
     * @mbg.generated
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.user_name
     *
     * @return the value of warning_send_message_log.user_name
     *
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.user_name
     *
     * @param userName the value for warning_send_message_log.user_name
     *
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.note
     *
     * @return the value of warning_send_message_log.note
     *
     * @mbg.generated
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.note
     *
     * @param note the value for warning_send_message_log.note
     *
     * @mbg.generated
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.create_time
     *
     * @return the value of warning_send_message_log.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.create_time
     *
     * @param createTime the value for warning_send_message_log.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.attribuate1
     *
     * @return the value of warning_send_message_log.attribuate1
     *
     * @mbg.generated
     */
    public String getAttribuate1() {
        return attribuate1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.attribuate1
     *
     * @param attribuate1 the value for warning_send_message_log.attribuate1
     *
     * @mbg.generated
     */
    public void setAttribuate1(String attribuate1) {
        this.attribuate1 = attribuate1 == null ? null : attribuate1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.attribuate2
     *
     * @return the value of warning_send_message_log.attribuate2
     *
     * @mbg.generated
     */
    public String getAttribuate2() {
        return attribuate2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.attribuate2
     *
     * @param attribuate2 the value for warning_send_message_log.attribuate2
     *
     * @mbg.generated
     */
    public void setAttribuate2(String attribuate2) {
        this.attribuate2 = attribuate2 == null ? null : attribuate2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.status
     *
     * @return the value of warning_send_message_log.status
     *
     * @mbg.generated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.status
     *
     * @param status the value for warning_send_message_log.status
     *
     * @mbg.generated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column warning_send_message_log.version
     *
     * @return the value of warning_send_message_log.version
     *
     * @mbg.generated
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column warning_send_message_log.version
     *
     * @param version the value for warning_send_message_log.version
     *
     * @mbg.generated
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}