package com.lpmas.declare.region.business;

import java.util.List;

import com.lpmas.region.bean.ProvinceInfoBean;
import com.lpmas.region.client.RegionServiceClient;

public class ProvinceInfoBusiness {

	public List<ProvinceInfoBean> getProvinceInfoListByCountryId(int countryId) {
		RegionServiceClient client = new RegionServiceClient();
		return client.getProvinceInfoListByCountryId(countryId);
	}

	public List<ProvinceInfoBean> getProvinceInfoListByCountryName(String countryName) {
		RegionServiceClient client = new RegionServiceClient();
		return client.getProvinceInfoListByCountryName(countryName);
	}
}
