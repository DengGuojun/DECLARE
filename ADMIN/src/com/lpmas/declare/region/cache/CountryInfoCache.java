package com.lpmas.declare.region.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.region.business.CountryInfoBusiness;
import com.lpmas.declare.region.config.RegionCacheConfig;
import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.region.bean.CountryInfoBean;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class CountryInfoCache {

	private static final Logger log = LoggerFactory.getLogger(CountryInfoCache.class);

	public List<CountryInfoBean> getCountryInfoAllList() {
		List<CountryInfoBean> list = new ArrayList<CountryInfoBean>();
		Object obj = null;
		String key = RegionCacheConfig.COUNTRY_INFO_ALL_LIST_KEY;
		LocalCache localCache = LocalCache.getInstance();

		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean update = false;
			try {
				CountryInfoBusiness business = new CountryInfoBusiness();
				obj = business.getCountryInfoAllList();
				if (obj != null) {
					localCache.set(key, obj, Constants.CACHE_TIME_2_HOUR);
					update = true;
				}
			} catch (Exception e) {
				log.error("", e);
			} finally {
				if (!update) {
					obj = nre.getCacheContent();
					localCache.cancelUpdate(key);
				}
			}
		}
		if (obj != null) {
			list = (List<CountryInfoBean>) obj;
		}
		return list;
	}

	public static void main(String[] args) {
		CountryInfoCache cache = new CountryInfoCache();
		System.out.println(cache.getCountryInfoAllList());
	}
}
