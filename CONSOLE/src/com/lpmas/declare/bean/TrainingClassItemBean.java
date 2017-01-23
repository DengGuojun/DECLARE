package com.lpmas.declare.bean;

import java.sql.Timestamp;

import com.lpmas.framework.annotation.FieldTag;

public class TrainingClassItemBean {
	@FieldTag(name = "课程ID")
	private int itemId = 0;
	@FieldTag(name = "培训班名称")
	private String itemName = "";
	@FieldTag(name = "培训班ID")
	private int classId = 0;
	@FieldTag(name = "课程类型")
	private String classType = "";
	@FieldTag(name = "是否必修课")
	private int isRequiredCourse = 0;
	@FieldTag(name = "学时")
	private String classHour = "";
	@FieldTag(name = "培训教材")
	private String trainingMaterial = "";
	@FieldTag(name = "培训师资")
	private String trainingTeacher = "";
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

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getIsRequiredCourse() {
		return isRequiredCourse;
	}

	public void setIsRequiredCourse(int isRequiredCourse) {
		this.isRequiredCourse = isRequiredCourse;
	}

	public String getClassHour() {
		return classHour;
	}

	public void setClassHour(String classHour) {
		this.classHour = classHour;
	}

	public String getTrainingMaterial() {
		return trainingMaterial;
	}

	public void setTrainingMaterial(String trainingMaterial) {
		this.trainingMaterial = trainingMaterial;
	}

	public String getTrainingTeacher() {
		return trainingTeacher;
	}

	public void setTrainingTeacher(String trainingTeacher) {
		this.trainingTeacher = trainingTeacher;
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
