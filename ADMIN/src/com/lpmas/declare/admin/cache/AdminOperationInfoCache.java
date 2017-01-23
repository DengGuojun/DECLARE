package com.lpmas.declare.admin.cache;

import java.util.HashMap;

import com.lpmas.declare.admin.business.AdminOperationInfoBusiness;
import com.lpmas.declare.admin.config.AdminCacheConfig;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;

public class AdminOperationInfoCache {
	public HashMap<Integer, String> getAdminOperationNameAllMap() {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String key = AdminCacheConfig.ADMIN_OPERATION_NAME_ALL_MAP_KEY;

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(DeclareAdminConfig.APP_ID, key);
		if (obj != null) {
			map = (HashMap<Integer, String>) obj;
		} else {
			AdminOperationInfoBusiness business = new AdminOperationInfoBusiness();
			map = business.getAdminOperationNameAllMap();
			if (map != null) {
				remoteCache.set(DeclareAdminConfig.APP_ID, key, map, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return map;
	}

	public boolean refreshAdminOperationNameAllMap() {
		String key = AdminCacheConfig.ADMIN_OPERATION_NAME_ALL_MAP_KEY;
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(DeclareAdminConfig.APP_ID, key);
	}

	public HashMap<Integer, String> getAdminOperationCodeAllMap() {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		String key = AdminCacheConfig.ADMIN_OPERATION_CODE_ALL_MAP_KEY;

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(DeclareAdminConfig.APP_ID, key);
		if (obj != null) {
			map = (HashMap<Integer, String>) obj;
		} else {
			AdminOperationInfoBusiness business = new AdminOperationInfoBusiness();
			map = business.getAdminOperationCodeAllMap();
			if (map != null) {
				remoteCache.set(DeclareAdminConfig.APP_ID, key, map, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return map;
	}

	public boolean refreshAdminOperationCodeAllMap() {
		String key = AdminCacheConfig.ADMIN_OPERATION_CODE_ALL_MAP_KEY;
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(DeclareAdminConfig.APP_ID, key);
	}

}
