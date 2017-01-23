package com.lpmas.declare.migrate.bean;

import com.lpmas.framework.annotation.FieldTag;

public class TabProvinceInfoBean {
	@FieldTag(name = "省份ID")
	private int provinceId = 0;
	@FieldTag(name = "国家ID")
	private int countryId = 0;
	@FieldTag(name = "省份名称")
	private String provinceName = "";
	@FieldTag(name = "省份代码")
	private String provinceCode = "";

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
}