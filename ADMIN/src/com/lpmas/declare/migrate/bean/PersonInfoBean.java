package com.lpmas.declare.migrate.bean;

import java.sql.Timestamp;

import com.lpmas.framework.annotation.FieldTag;

public class PersonInfoBean {
	@FieldTag(name = "")
	private String id = "";
	@FieldTag(name = "")
	private String name = "";
	@FieldTag(name = "")
	private String idCardNo = "";
	@FieldTag(name = "")
	private String phoneNo = "";
	@FieldTag(name = "")
	private int baseInfoType = 0;
	@FieldTag(name = "")
	private int status = 0;
	@FieldTag(name = "")
	private int registType = 0;
	@FieldTag(name = "")
	private String serverId = "";
	@FieldTag(name = "")
	private int year = 0;
	@FieldTag(name = "")
	private Timestamp createTime = null;
	@FieldTag(name = "")
	private Timestamp updateTime = null;
	@FieldTag(name = "")
	private String remark = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getBaseInfoType() {
		return baseInfoType;
	}

	public void setBaseInfoType(int baseInfoType) {
		this.baseInfoType = baseInfoType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRegistType() {
		return registType;
	}

	public void setRegistType(int registType) {
		this.registType = registType;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}