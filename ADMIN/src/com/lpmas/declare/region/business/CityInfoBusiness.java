package com.lpmas.declare.region.business;

import java.util.List;

import com.lpmas.region.bean.CityInfoBean;
import com.lpmas.region.client.RegionServiceClient;

public class CityInfoBusiness {

	public List<CityInfoBean> getCityInfoListByProvinceId(int provinceId) {
		RegionServiceClient client = new RegionServiceClient();
		return client.getCityInfoListByProvinceId(provinceId);
	}

	public List<CityInfoBean> getCityInfoListByProvinceName(String provinceName) {
		RegionServiceClient client = new RegionServiceClient();
		return client.getCityInfoListByProvinceName(provinceName);
	}
}
