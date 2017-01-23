package com.lpmas.declare.admin.business;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.cache.AdminPrivilegeInfoCache;
import com.lpmas.declare.admin.cache.AdminUserInfoCache;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.CookiesKit;
import com.lpmas.framework.web.HttpResponseKit;

public class AdminUserHelper {
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;

	private int userId = 0;
	private AdminUserInfoBean userInfo = null;

	private HashSet<String> userPrivilegeCodeSet = null;

	public AdminUserHelper(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public AdminUserHelper(HttpServletRequest request) {
		this.request = request;
	}

	public AdminUserHelper(int userId) {
		this.userId = userId;
	}

	public boolean isAdminUserLogon() {
		String key = CookiesKit.getCookies(request, DeclareAdminConfig.ADMIN_USER_KEY);
		if (getAdminUserId(key) > 0) {
			return true;
		}
		return false;
	}

	private int getAdminUserId(String key) {
		int result = 0;
		if (StringKit.isValid(key)) {
			result = Integer.parseInt(AdminLogonUtil.decryptLogonSign(key));
		}
		return result;
	}

	public int getAdminUserId() {
		if (userId <= 0) {
			String key = CookiesKit.getCookies(request, DeclareAdminConfig.ADMIN_USER_KEY);
			userId = getAdminUserId(key);
		}
		return userId;
	}

	public AdminUserInfoBean getAdminUserInfo() {
		if (userInfo == null) {
			if (getAdminUserId() > 0) {
				AdminUserInfoCache cache = new AdminUserInfoCache();
				userInfo = cache.getAdminUserInfoByKey(getAdminUserId());
			}
		}
		return userInfo;
	}

	public boolean isSuperAdminUser() {
		boolean result = false;
		AdminUserInfoBean bean = getAdminUserInfo();
		if (bean.getAdminUserType() == DeclareAdminConfig.ADMIN_TYPE_ADMIN) {
			result = true;
		}

		return result;
	}

	public HashSet<String> getUserPrivilegeCodeSet() {
		if (userPrivilegeCodeSet == null) {
			AdminPrivilegeInfoCache cache = new AdminPrivilegeInfoCache();
			userPrivilegeCodeSet = cache.getAdminPrivilegeCodeSetByUserId(getAdminUserId());
		}
		return userPrivilegeCodeSet;
	}

	public boolean hasPermission(String resourceCode, String operationCode) {
		if (isSuperAdminUser()) {
			return true;
		}

		String key = AdminUtil.getPrivilegeCode(resourceCode, operationCode);
		if (getUserPrivilegeCodeSet().contains(key)) {
			return true;
		}
		return false;
	}

	public boolean hasAnyPermission(String[][] permissionArray) {
		if (isSuperAdminUser()) {
			return true;
		}

		for (int i = 0; i < permissionArray.length; i++) {
			String key = AdminUtil.getPrivilegeCode(permissionArray[i][0], permissionArray[i][1]);
			if (getUserPrivilegeCodeSet().contains(key)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasAnyPermission(List<String[]> permissionList) {
		if (isSuperAdminUser()) {
			return true;
		}

		for (String[] permissionArray : permissionList) {
			String key = AdminUtil.getPrivilegeCode(permissionArray[0], permissionArray[1]);
			if (getUserPrivilegeCodeSet().contains(key)) {
				return true;
			}
		}
		return false;
	}

	public boolean checkPermission(String resourceCode, String operationCode) {
		if (!hasPermission(resourceCode, operationCode)) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return false;
		} else {
			return true;
		}
	}
	
}
