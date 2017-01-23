package com.lpmas.declare.admin.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.config.AdminCacheConfig;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.JsonKit;

public class AdminUserInfoCache {
	private static Logger log = LoggerFactory.getLogger(AdminUserInfoCache.class);

	public AdminUserInfoBean getAdminUserInfoByKey(int userId) {
		AdminUserInfoBean bean = null;
		String key = AdminCacheConfig.getAdminUserInfoKey(userId);

		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(DeclareAdminConfig.APP_ID, key);
		if (obj != null) {
			log.info("get AdminUserInfoBean from remote cache");
			bean = JsonKit.toBean((String) obj, AdminUserInfoBean.class);
		} else {
			log.info("set AdminUserInfoBean from remote cache");
			AdminUserInfoBusiness business = new AdminUserInfoBusiness();
			bean = business.getAdminUserInfoByKey(userId);
			if (bean != null) {
				remoteCache.set(DeclareAdminConfig.APP_ID, key, JsonKit.toJson(bean), Constants.CACHE_TIME_2_HOUR);
			}
		}
		return bean;
	}

	public boolean refreshAdminUserInfoByKey(int userId) {
		String key = AdminCacheConfig.getAdminUserInfoKey(userId);
		RemoteCache remoteCache = RemoteCache.getInstance();
		return remoteCache.delete(DeclareAdminConfig.APP_ID, key);
	}
}
