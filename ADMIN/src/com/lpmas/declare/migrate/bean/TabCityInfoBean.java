package com.lpmas.declare.migrate.bean;

import com.lpmas.framework.annotation.FieldTag;

public class TabCityInfoBean {
	@FieldTag(name = "城市ID")
	private int cityId = 0;
	@FieldTag(name = "国家ID")
	private int countryId = 0;
	@FieldTag(name = "省份ID")
	private int provinceId = 0;
	@FieldTag(name = "城市名称")
	private String cityName = "";
	@FieldTag(name = "城市代码")
	private String cityCode = "";

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}