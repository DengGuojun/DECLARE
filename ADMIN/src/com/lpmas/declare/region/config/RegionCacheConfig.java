package com.lpmas.declare.region.config;

public class RegionCacheConfig {
	public static final String COUNTRY_INFO_ALL_LIST_KEY = "COUNTRY_INFO_ALL_LIST";
	public static final String PROVINCE_INFO_LIST_KEY = "PROVINCE_INFO_LIST_";
	public static final String PROVINCE_INFO_LIST_BY_NAME_KEY = "PROVINCE_INFO_LIST_NAME_";
	public static final String CITY_INFO_LIST_KEY = "CITY_INFO_LIST_";
	public static final String CITY_INFO_LIST_BY_NAME_KEY = "CITY_INFO_LIST_NAME_";
	public static final String REGION_INFO_LIST_KEY = "REGION_INFO_LIST_";
	public static final String REGION_INFO_LIST_BY_NAME_KEY = "REGION_INFO_LIST_NAME_";

	public static String getProvinceInfoListKey(int countryId) {
		return PROVINCE_INFO_LIST_KEY + countryId;
	}

	public static String getProvinceInfoListByNameKey(String countryName) {
		return PROVINCE_INFO_LIST_BY_NAME_KEY + countryName;
	}

	public static String getCityInfoListKey(int provinceId) {
		return CITY_INFO_LIST_KEY + provinceId;
	}

	public static String getCityInfoListByProvinceNameKey(String provinceName) {
		return CITY_INFO_LIST_BY_NAME_KEY + provinceName;
	}

	public static String getRegionInfoListKey(int cityId) {
		return REGION_INFO_LIST_KEY + cityId;
	}

	public static String getRegionInfoListByCityNameKey(String cityName) {
		return REGION_INFO_LIST_BY_NAME_KEY + cityName;
	}
}
