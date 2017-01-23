package com.lpmas.declare.admin.bean;

import java.sql.Timestamp;
import com.lpmas.framework.annotation.FieldTag;

public class TrainingMaterialInfoBean {
	@FieldTag(name = "教材ID")
	private int materialId = 0;
	@FieldTag(name = "教材名称")
	private String materialName = "";
	@FieldTag(name = "教材类型")
	private String materialType = "";
	@FieldTag(name = "培育年份")
	private String trainingYear = "";
	@FieldTag(name = "省")
	private String province = "";
	@FieldTag(name = "市")
	private String city = "";
	@FieldTag(name = "区")
	private String region = "";
	@FieldTag(name = "组编单位")
	private String compileOrganization = "";
	@FieldTag(name = "出版社")
	private String publishingCompany = "";
	@FieldTag(name = "出版年份")
	private String publishingYeah = "";
	@FieldTag(name = "出版月份")
	private String publishingMonth = "";
	@FieldTag(name = "字数(千字)")
	private int wordQuantity = 0;
	@FieldTag(name = "印张(张)")
	private int paperQuantity = 0;
	@FieldTag(name = "价格(元)")
	private double price = 0;
	@FieldTag(name = "产业")
	private String industry = "";
	@FieldTag(name = "链接")
	private String link = "";
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

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getTrainingYear() {
		return trainingYear;
	}

	public void setTrainingYear(String trainingYear) {
		this.trainingYear = trainingYear;
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

	public String getCompileOrganization() {
		return compileOrganization;
	}

	public void setCompileOrganization(String compileOrganization) {
		this.compileOrganization = compileOrganization;
	}

	public String getPublishingCompany() {
		return publishingCompany;
	}

	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}

	public String getPublishingYeah() {
		return publishingYeah;
	}

	public void setPublishingYeah(String publishingYeah) {
		this.publishingYeah = publishingYeah;
	}

	public String getPublishingMonth() {
		return publishingMonth;
	}

	public void setPublishingMonth(String publishingMonth) {
		this.publishingMonth = publishingMonth;
	}

	public int getWordQuantity() {
		return wordQuantity;
	}

	public void setWordQuantity(int wordQuantity) {
		this.wordQuantity = wordQuantity;
	}

	public int getPaperQuantity() {
		return paperQuantity;
	}

	public void setPaperQuantity(int paperQuantity) {
		this.paperQuantity = paperQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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