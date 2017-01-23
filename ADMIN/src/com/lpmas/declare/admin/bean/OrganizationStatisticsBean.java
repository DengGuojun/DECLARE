package com.lpmas.declare.admin.bean;

import org.bson.types.ObjectId;

import com.lpmas.framework.nosql.mongodb.MongoDBDocumentBean;

public class OrganizationStatisticsBean extends MongoDBDocumentBean<ObjectId> {

	private String province = ""; // 省
	private String city = ""; // 市
	private String region = ""; // 区
	private String level = ""; // 区域等级
	private boolean isSum = false;
	private String declareYear = ""; // 培育年份
	private String trainingType = ""; // 培训班类型
	private String organizationType = ""; // 组织类型

	private int totalTraining = 0; // 培训总数
	private int agriculturalSchool = 0; // 农广校
	private int agricultural_vocational_college = 0; // 农业职业院校
	private int school_of_Agricultural_Mechanization = 0; // 农机化学院
	private int agricultural_extension_services = 0; // 农技推广服务机构
	private int agricultural_efficiency = 0; // 农业高效
	private int academy_of_Agricultural_Sciences = 0; // 农科院所(站)
	private int agricultural_administrative_departments = 0; // 农业行政主管部门
	private int peasant_cooperatives = 0; // 农民合作社
	private int agricultural_leading_enterprises = 0; // 农业龙头企业
	private int other_public_institutions = 0; // 其他公办机构
	private int other_private_organizations = 0; // 其他民办机构

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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isSum() {
		return isSum;
	}

	public void setSum(boolean isSum) {
		this.isSum = isSum;
	}

	public String getDeclareYear() {
		return declareYear;
	}

	public void setDeclareYear(String declareYear) {
		this.declareYear = declareYear;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}

	public String getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}

	public int getTotalTraining() {
		return totalTraining;
	}

	public void setTotalTraining(int totalTraining) {
		this.totalTraining = totalTraining;
	}

	public int getAgriculturalSchool() {
		return agriculturalSchool;
	}

	public void setAgriculturalSchool(int agriculturalSchool) {
		this.agriculturalSchool = agriculturalSchool;
	}

	public int getAgricultural_vocational_college() {
		return agricultural_vocational_college;
	}

	public void setAgricultural_vocational_college(int agricultural_vocational_college) {
		this.agricultural_vocational_college = agricultural_vocational_college;
	}

	public int getSchool_of_Agricultural_Mechanization() {
		return school_of_Agricultural_Mechanization;
	}

	public void setSchool_of_Agricultural_Mechanization(int school_of_Agricultural_Mechanization) {
		this.school_of_Agricultural_Mechanization = school_of_Agricultural_Mechanization;
	}

	public int getAgricultural_extension_services() {
		return agricultural_extension_services;
	}

	public void setAgricultural_extension_services(int agricultural_extension_services) {
		this.agricultural_extension_services = agricultural_extension_services;
	}

	public int getAgricultural_efficiency() {
		return agricultural_efficiency;
	}

	public void setAgricultural_efficiency(int agricultural_efficiency) {
		this.agricultural_efficiency = agricultural_efficiency;
	}

	public int getAcademy_of_Agricultural_Sciences() {
		return academy_of_Agricultural_Sciences;
	}

	public void setAcademy_of_Agricultural_Sciences(int academy_of_Agricultural_Sciences) {
		this.academy_of_Agricultural_Sciences = academy_of_Agricultural_Sciences;
	}

	public int getAgricultural_administrative_departments() {
		return agricultural_administrative_departments;
	}

	public void setAgricultural_administrative_departments(int agricultural_administrative_departments) {
		this.agricultural_administrative_departments = agricultural_administrative_departments;
	}

	public int getPeasant_cooperatives() {
		return peasant_cooperatives;
	}

	public void setPeasant_cooperatives(int peasant_cooperatives) {
		this.peasant_cooperatives = peasant_cooperatives;
	}

	public int getAgricultural_leading_enterprises() {
		return agricultural_leading_enterprises;
	}

	public void setAgricultural_leading_enterprises(int agricultural_leading_enterprises) {
		this.agricultural_leading_enterprises = agricultural_leading_enterprises;
	}

	public int getOther_public_institutions() {
		return other_public_institutions;
	}

	public void setOther_public_institutions(int other_public_institutions) {
		this.other_public_institutions = other_public_institutions;
	}

	public int getOther_private_organizations() {
		return other_private_organizations;
	}

	public void setOther_private_organizations(int other_private_organizations) {
		this.other_private_organizations = other_private_organizations;
	}

}
