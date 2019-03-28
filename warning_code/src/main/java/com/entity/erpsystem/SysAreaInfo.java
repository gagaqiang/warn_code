package com.entity.erpsystem;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * SysArea
 */
@Alias("SysArea")
public class SysAreaInfo {
	private Long id;
	private Date createDate;
	private Date modifyDate;
	private int version;
	private boolean del;
	private String code;
	private String name;
	private String zip;
	private Long pid;

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

	public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
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

	public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

	public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

}
