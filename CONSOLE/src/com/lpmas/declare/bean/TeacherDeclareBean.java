package com.lpmas.declare.bean;

import java.sql.Date;
import java.sql.Timestamp;
import com.lpmas.framework.annotation.FieldTag;

public class TeacherDeclareBean {
	@FieldTag(name = "申报ID")
	private int declareId = 0;
	@FieldTag(name = "用户ID")
	private int userId = 0;
	@FieldTag(name = "师资ID")
	private int teacherId = 0;
	@FieldTag(name = "师资名称")
	private String teacherName = "";
	@FieldTag(name = "师资编号")
	private String teacherNumber = "";
	@FieldTag(name = "师资类型")
	private String teacherType = "";
	@FieldTag(name = "师资照片")
	private String teacherImage = "";
	@FieldTag(name = "师资性别")
	private int teacherGender = 0;
	@FieldTag(name = "出生日期")
	private Date teacherBirthday = null;
	@FieldTag(name = "省")
	private String province = "";
	@FieldTag(name = "市")
	private String city = "";
	@FieldTag(name = "区")
	private String region = "";
	@FieldTag(name = "身份证")
	private String identityNumber = "";
	@FieldTag(name = "工作单位")
	private String company = "";
	@FieldTag(name = "职称名称")
	private String technicalTitle = "";
	@FieldTag(name = "职称级别")
	private String technicalGrade = "";
	@FieldTag(name = "专业类型ID")
	private int majorTypeId = 0;
	@FieldTag(name = "专业ID")
	private int majorId = 0;
	@FieldTag(name = "主授课程")
	private String coursesOffer = "";
	@FieldTag(name = "用户手机")
	private String phone = "";
	@FieldTag(name = "用户邮箱")
	private String email = "";
	@FieldTag(name = "相关资料")
	private String relativeMaterial = "";
	@FieldTag(name = "申报状态")
	private String declareStatus = "";
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

	public int getDeclareId() {
		return declareId;
	}

	public void setDeclareId(int declareId) {
		this.declareId = declareId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherNumber() {
		return teacherNumber;
	}

	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	public String getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(String teacherType) {
		this.teacherType = teacherType;
	}

	public String getTeacherImage() {
		return teacherImage;
	}

	public void setTeacherImage(String teacherImage) {
		this.teacherImage = teacherImage;
	}

	public int getTeacherGender() {
		return teacherGender;
	}

	public void setTeacherGender(int teacherGender) {
		this.teacherGender = teacherGender;
	}

	public Date getTeacherBirthday() {
		return teacherBirthday;
	}

	public void setTeacherBirthday(Date teacherBirthday) {
		this.teacherBirthday = teacherBirthday;
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

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTechnicalTitle() {
		return technicalTitle;
	}

	public void setTechnicalTitle(String technicalTitle) {
		this.technicalTitle = technicalTitle;
	}

	public String getTechnicalGrade() {
		return technicalGrade;
	}

	public void setTechnicalGrade(String technicalGrade) {
		this.technicalGrade = technicalGrade;
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

	public String getCoursesOffer() {
		return coursesOffer;
	}

	public void setCoursesOffer(String coursesOffer) {
		this.coursesOffer = coursesOffer;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRelativeMaterial() {
		return relativeMaterial;
	}

	public void setRelativeMaterial(String relativeMaterial) {
		this.relativeMaterial = relativeMaterial;
	}

	public String getDeclareStatus() {
		return declareStatus;
	}

	public void setDeclareStatus(String declareStatus) {
		this.declareStatus = declareStatus;
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