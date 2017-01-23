package com.lpmas.declare.admin.bean;

import org.bson.types.ObjectId;

import com.lpmas.framework.nosql.mongodb.MongoDBDocumentBean;

public class TeacherStatisticsBean extends MongoDBDocumentBean<ObjectId> {

	private String province = ""; // 省
	private String city = ""; // 市
	private String region = ""; // 区
	private String level = ""; // 区域等级
	private boolean isSum = false;
	private int count = 0; // 总数
	private int seniorCount = 0; // 正高级人数
	private int subSeniorCount = 0; // 副高级人数
	private int primaryCount = 0; // 初级人数
	private int middleCount = 0; // 中级人数
	private int otherLevelCount = 0; // 其余等级
	private int typeBreedCount = 0; // 养殖业
	private int typeModernAgricultureCount = 0;// 现代农业
	private int typePublicFoundationCount = 0; // 公共基础
	private int typeEngineeringAndServerCount = 0; // 农村工程与服务
	private int typePublicManageCount = 0; // 农村经营与农村管理
	private int typeOtherCount = 0; // 农村经营与农村管理
	private int typePlantCount = 0; //种植业

	public int getOtherLevelCount() {
		return otherLevelCount;
	}

	public void setOtherLevelCount(int otherLevelCount) {
		this.otherLevelCount = otherLevelCount;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSeniorCount() {
		return seniorCount;
	}

	public void setSeniorCount(int seniorCount) {
		this.seniorCount = seniorCount;
	}

	public int getSubSeniorCount() {
		return subSeniorCount;
	}

	public void setSubSeniorCount(int subSeniorCount) {
		this.subSeniorCount = subSeniorCount;
	}

	public int getPrimaryCount() {
		return primaryCount;
	}

	public void setPrimaryCount(int primaryCount) {
		this.primaryCount = primaryCount;
	}

	public int getMiddleCount() {
		return middleCount;
	}

	public void setMiddleCount(int middleCount) {
		this.middleCount = middleCount;
	}

	public int getTypePlantCount() {
		return typePlantCount;
	}

	public void setTypePlantCount(int typePlantCount) {
		this.typePlantCount = typePlantCount;
	}

	public int getTypeBreedCount() {
		return typeBreedCount;
	}

	public void setTypeBreedCount(int typeBreedCount) {
		this.typeBreedCount = typeBreedCount;
	}

	public int getTypeModernAgricultureCount() {
		return typeModernAgricultureCount;
	}

	public void setTypeModernAgricultureCount(int typeModernAgricultureCount) {
		this.typeModernAgricultureCount = typeModernAgricultureCount;
	}

	public int getTypePublicFoundationCount() {
		return typePublicFoundationCount;
	}

	public void setTypePublicFoundationCount(int typePublicFoundationCount) {
		this.typePublicFoundationCount = typePublicFoundationCount;
	}

	public int getTypeEngineeringAndServerCount() {
		return typeEngineeringAndServerCount;
	}

	public void setTypeEngineeringAndServerCount(int typeEngineeringAndServerCount) {
		this.typeEngineeringAndServerCount = typeEngineeringAndServerCount;
	}

	public int getTypePublicManageCount() {
		return typePublicManageCount;
	}

	public void setTypePublicManageCount(int typePublicManageCount) {
		this.typePublicManageCount = typePublicManageCount;
	}

	public int getTypeOtherCount() {
		return typeOtherCount;
	}

	public void setTypeOtherCount(int typeOtherCount) {
		this.typeOtherCount = typeOtherCount;
	}

}
