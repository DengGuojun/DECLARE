package com.lpmas.declare.migrate.bean;

import com.lpmas.framework.annotation.FieldTag;

public class TabRegionInfoBean {
	@FieldTag(name = "地区ID")
	private int regionId = 0;
	@FieldTag(name = "国家ID")
	private int countryId = 0;
	@FieldTag(name = "省份ID")
	private int provinceId = 0;
	@FieldTag(name = "城市ID")
	private int cityId = 0;
	@FieldTag(name = "地区名称")
	private String regionName = "";
	@FieldTag(name = "地区代码")
	private String regionCode = "";

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
}