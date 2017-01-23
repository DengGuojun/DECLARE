package com.lpmas.declare.region.business;

import java.util.List;

import com.lpmas.region.bean.CountryInfoBean;
import com.lpmas.region.client.RegionServiceClient;

public class CountryInfoBusiness {

	public List<CountryInfoBean> getCountryInfoAllList() {
		RegionServiceClient client = new RegionServiceClient();
		return client.getCountryInfoAllList();
	}
}
