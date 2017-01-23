package com.lpmas.declare.region.business;

import java.util.List;

import com.lpmas.region.bean.RegionInfoBean;
import com.lpmas.region.client.RegionServiceClient;

public class RegionInfoBusiness {

	public List<RegionInfoBean> getRegionInfoListByCityId(int cityId) {
		RegionServiceClient client = new RegionServiceClient();
		return client.getRegionInfoListByCityId(cityId);
	}

	public List<RegionInfoBean> getRegionInfoListByCityName(String cityName) {
		RegionServiceClient client = new RegionServiceClient();
		return client.getRegionInfoListByCityName(cityName);
	}
}
