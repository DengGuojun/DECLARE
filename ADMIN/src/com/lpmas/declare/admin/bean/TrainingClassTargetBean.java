package com.lpmas.declare.admin.bean;

import java.sql.Timestamp;

import com.lpmas.framework.annotation.FieldTag;

public class TrainingClassTargetBean {
	@FieldTag(name = "指标ID")
	private int targetId = 0;
	@FieldTag(name = "培育年份")
	private String trainingYear = "";
	@FieldTag(name = "国家")
	private String country = "";
	@FieldTag(name = "省")
	private String province = "";
	@FieldTag(name = "市")
	private String city = "";
	@FieldTag(name = "区")
	private String region = "";
	@FieldTag(name = "培训机构ID")
	private int organizationId = 0;
	@FieldTag(name = "培育指标数量1")
	private int targetQuantity1 = 0;
	@FieldTag(name = "培育指标数量1")
	private int targetQuantity2 = 0;
	@FieldTag(name = "培育指标数量1")
	private int targetQuantity3 = 0;
	@FieldTag(name = "培育指标数量1")
	private int targetQuantity4 = 0;
	@FieldTag(name = "培育指标数量1")
	private int targetQuantity5 = 0;
	@FieldTag(name = "状态")
	private int status = 0;
	@FieldTag(name = "创建时间")
	private Timestamp createTime = null;
	@FieldTag(name = "创建用户")
	private int createUser = 0;
	@FieldTag(name = "修改时间")
	private Timestamp modifyTime = null;
	@FieldTag(name = "修改用户")
	private int modifyUser = 0;
	@FieldTag(name = "备注")
	private String memo = "";

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public String getTrainingYear() {
		return trainingYear;
	}

	public void setTrainingYear(String trainingYear) {
		this.trainingYear = trainingYear;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public int getTargetQuantity1() {
		return targetQuantity1;
	}

	public void setTargetQuantity1(int targetQuantity1) {
		this.targetQuantity1 = targetQuantity1;
	}

	public int getTargetQuantity2() {
		return targetQuantity2;
	}

	public void setTargetQuantity2(int targetQuantity2) {
		this.targetQuantity2 = targetQuantity2;
	}

	public int getTargetQuantity3() {
		return targetQuantity3;
	}

	public void setTargetQuantity3(int targetQuantity3) {
		this.targetQuantity3 = targetQuantity3;
	}

	public int getTargetQuantity4() {
		return targetQuantity4;
	}

	public void setTargetQuantity4(int targetQuantity4) {
		this.targetQuantity4 = targetQuantity4;
	}

	public int getTargetQuantity5() {
		return targetQuantity5;
	}

	public void setTargetQuantity5(int targetQuantity5) {
		this.targetQuantity5 = targetQuantity5;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(int modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}