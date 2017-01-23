package com.lpmas.declare.bean;

import java.sql.Timestamp;
import com.lpmas.framework.annotation.FieldTag;

public class TrainingClassInfoBean {
	@FieldTag(name = "培训班ID")
	private int classId = 0;
	@FieldTag(name = "培训班名称")
	private String className = "";
	@FieldTag(name = "培训机构ID")
	private int organizationId = 0;
	@FieldTag(name = "培育年份")
	private String trainingYear = "";
	@FieldTag(name = "培训类型")
	private int trainingType = 0;
	@FieldTag(name = "培训班人数")
	private int classPeopleQuantity = 0;
	@FieldTag(name = "专业类型ID")
	private int majorTypeId = 0;
	@FieldTag(name = "专业ID")
	private int majorId = 0;
	@FieldTag(name = "培训工种")
	private String trainingPose = "";
	@FieldTag(name = "省")
	private String province = "";
	@FieldTag(name = "市")
	private String city = "";
	@FieldTag(name = "区")
	private String region = "";
	@FieldTag(name = "培训开始时间")
	private Timestamp trainingBeginTime = null;
	@FieldTag(name = "培训结束时间")
	private Timestamp trainingEndTime = null;
	@FieldTag(name = "报名截止时间")
	private Timestamp registrationEndTime = null;
	@FieldTag(name = "实施项目1")
	private String trainingItem1 = "";
	@FieldTag(name = "实施项目2")
	private String trainingItem2 = "";
	@FieldTag(name = "培训班状态")
	private String classStatus = "";
	@FieldTag(name = "认定机构ID")
	private int authOrganizationId = 0;
	@FieldTag(name = "认定状态")
	private String authStatus = "";
	@FieldTag(name = "认定时间")
	private Timestamp authTime = null;
	@FieldTag(name = "同步状态")
	private int syncStatus = 0;
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

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getTrainingYear() {
		return trainingYear;
	}

	public void setTrainingYear(String trainingYear) {
		this.trainingYear = trainingYear;
	}

	public int getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(int trainingType) {
		this.trainingType = trainingType;
	}

	public int getClassPeopleQuantity() {
		return classPeopleQuantity;
	}

	public void setClassPeopleQuantity(int classPeopleQuantity) {
		this.classPeopleQuantity = classPeopleQuantity;
	}

	public int getMajorTypeId() {
		return majorTypeId;
	}

	public void setMajorTypeId(int majorTypeId) {
		this.majorTypeId = majorTypeId;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public String getTrainingPose() {
		return trainingPose;
	}

	public void setTrainingPose(String trainingPose) {
		this.trainingPose = trainingPose;
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

	public Timestamp getTrainingBeginTime() {
		return trainingBeginTime;
	}

	public void setTrainingBeginTime(Timestamp trainingBeginTime) {
		this.trainingBeginTime = trainingBeginTime;
	}

	public Timestamp getTrainingEndTime() {
		return trainingEndTime;
	}

	public void setTrainingEndTime(Timestamp trainingEndTime) {
		this.trainingEndTime = trainingEndTime;
	}

	public Timestamp getRegistrationEndTime() {
		return registrationEndTime;
	}

	public void setRegistrationEndTime(Timestamp registrationEndTime) {
		this.registrationEndTime = registrationEndTime;
	}

	public String getTrainingItem1() {
		return trainingItem1;
	}

	public void setTrainingItem1(String trainingItem1) {
		this.trainingItem1 = trainingItem1;
	}

	public String getTrainingItem2() {
		return trainingItem2;
	}

	public void setTrainingItem2(String trainingItem2) {
		this.trainingItem2 = trainingItem2;
	}

	public String getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(String classStatus) {
		this.classStatus = classStatus;
	}

	public int getAuthOrganizationId() {
		return authOrganizationId;
	}

	public void setAuthOrganizationId(int authOrganizationId) {
		this.authOrganizationId = authOrganizationId;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public Timestamp getAuthTime() {
		return authTime;
	}

	public void setAuthTime(Timestamp authTime) {
		this.authTime = authTime;
	}

	public int getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(int syncStatus) {
		this.syncStatus = syncStatus;
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