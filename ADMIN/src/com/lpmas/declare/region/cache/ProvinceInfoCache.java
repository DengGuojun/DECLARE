package com.lpmas.declare.region.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.region.business.ProvinceInfoBusiness;
import com.lpmas.declare.region.config.RegionCacheConfig;
import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.region.bean.ProvinceInfoBean;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class ProvinceInfoCache {
	private static Logger log = LoggerFactory.getLogger(ProvinceInfoCache.class);

	public List<ProvinceInfoBean> getProvinceInfoListByCountryId(int countryId) {
		List<ProvinceInfoBean> list = new ArrayList<ProvinceInfoBean>();
		Object obj = null;
		String key = RegionCacheConfig.getProvinceInfoListKey(countryId);
		LocalCache localCache = LocalCache.getInstance();

		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean update = false;
			try {
				ProvinceInfoBusiness business = new ProvinceInfoBusiness();
				obj = business.getProvinceInfoListByCountryId(countryId);
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
			list = (List<ProvinceInfoBean>) obj;
		}
		return list;
	}

	public List<ProvinceInfoBean> getProvinceInfoListByCountryName(String countryName) {
		List<ProvinceInfoBean> list = new ArrayList<ProvinceInfoBean>();
		Object obj = null;
		String key = RegionCacheConfig.getProvinceInfoListByNameKey(countryName);
		LocalCache localCache = LocalCache.getInstance();

		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean update = false;
			try {
				ProvinceInfoBusiness business = new ProvinceInfoBusiness();
				obj = business.getProvinceInfoListByCountryName(countryName);
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
			list = (List<ProvinceInfoBean>) obj;
		}
		return list;
	}

}
