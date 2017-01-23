package com.lpmas.declare.region.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.region.business.CityInfoBusiness;
import com.lpmas.declare.region.config.RegionCacheConfig;
import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.region.bean.CityInfoBean;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class CityInfoCache {
	private static Logger log = LoggerFactory.getLogger(CityInfoCache.class);

	public List<CityInfoBean> getCityInfoListByProvinceId(int provinceId) {
		List<CityInfoBean> list = new ArrayList<CityInfoBean>();
		Object obj = null;
		String key = RegionCacheConfig.getCityInfoListKey(provinceId);
		LocalCache localCache = LocalCache.getInstance();

		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean update = false;
			try {
				CityInfoBusiness business = new CityInfoBusiness();
				obj = business.getCityInfoListByProvinceId(provinceId);
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
			list = (List<CityInfoBean>) obj;
		}
		return list;
	}

	public List<CityInfoBean> getCityInfoListByProvinceName(String provinceName) {
		List<CityInfoBean> list = new ArrayList<CityInfoBean>();
		Object obj = null;
		String key = RegionCacheConfig.getCityInfoListByProvinceNameKey(provinceName);
		LocalCache localCache = LocalCache.getInstance();

		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean update = false;
			try {
				CityInfoBusiness business = new CityInfoBusiness();
				obj = business.getCityInfoListByProvinceName(provinceName);
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
			list = (List<CityInfoBean>) obj;
		}
		return list;
	}
}
