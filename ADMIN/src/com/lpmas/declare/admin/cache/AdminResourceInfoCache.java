package com.lpmas.declare.admin.cache;

import java.util.HashMap;

import com.lpmas.declare.admin.business.AdminResourceInfoBusiness;
import com.lpmas.declare.admin.config.AdminCacheConfig;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;

public class AdminResourceInfoCache {
	public HashMap<Integer, String> getAdminResourceNameAllMap() {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String key = AdminCacheConfig.ADMIN_RESOURCE_NAME_ALL_MAP_KEY;

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(DeclareAdminConfig.APP_ID, key);
		if (obj != null) {
			map = (HashMap<Integer, String>) obj;
		} else {
			AdminResourceInfoBusiness business = new AdminResourceInfoBusiness();
			map = business.getAdminResourceNameAllMap();
			if (map != null) {
				remoteCache.set(DeclareAdminConfig.APP_ID, key, map, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return map;
	}

	public boolean refreshAdminResourceNameAllMap() {
		String key = AdminCacheConfig.ADMIN_RESOURCE_NAME_ALL_MAP_KEY;
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(DeclareAdminConfig.APP_ID, key);
	}

	public HashMap<Integer, String> getAdminResourceCodeAllMap() {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String key = AdminCacheConfig.ADMIN_RESOURCE_CODE_ALL_MAP_KEY;

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(DeclareAdminConfig.APP_ID, key);
		if (obj != null) {
			map = (HashMap<Integer, String>) obj;
		} else {
			AdminResourceInfoBusiness business = new AdminResourceInfoBusiness();
			map = business.getAdminResourceCodeAllMap();
			if (map != null) {
				remoteCache.set(DeclareAdminConfig.APP_ID, key, map, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return map;
	}

	public boolean refreshAdminResourceCodeAllMap() {
		String key = AdminCacheConfig.ADMIN_RESOURCE_CODE_ALL_MAP_KEY;
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(DeclareAdminConfig.APP_ID, key);
	}
	
}
