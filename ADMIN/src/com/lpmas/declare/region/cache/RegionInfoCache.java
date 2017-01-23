package com.lpmas.declare.region.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.region.business.RegionInfoBusiness;
import com.lpmas.declare.region.config.RegionCacheConfig;
import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.region.bean.RegionInfoBean;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class RegionInfoCache {
	private static Logger log = LoggerFactory.getLogger(RegionInfoCache.class);

	public List<RegionInfoBean> getRegionInfoListByCityId(int cityId) {
		List<RegionInfoBean> list = new ArrayList<RegionInfoBean>();
		Object obj = null;
		String key = RegionCacheConfig.getRegionInfoListKey(cityId);
		LocalCache localCache = LocalCache.getInstance();

		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean update = false;
			try {
				RegionInfoBusiness business = new RegionInfoBusiness();
				obj = business.getRegionInfoListByCityId(cityId);
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
			list = (List<RegionInfoBean>) obj;
		}
		return list;
	}

	public List<RegionInfoBean> getRegionInfoListByCityName(String cityName) {
		List<RegionInfoBean> list = new ArrayList<RegionInfoBean>();
		Object obj = null;
		String key = RegionCacheConfig.getRegionInfoListByCityNameKey(cityName);
		LocalCache localCache = LocalCache.getInstance();

		try {
			obj = localCache.get(key);
		} catch (NeedsRefreshException nre) {
			boolean update = false;
			try {
				RegionInfoBusiness business = new RegionInfoBusiness();
				obj = business.getRegionInfoListByCityName(cityName);
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
			list = (List<RegionInfoBean>) obj;
		}
		return list;
	}
}
