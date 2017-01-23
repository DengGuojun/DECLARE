package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.AdminRoleInfoBean;
import com.lpmas.declare.admin.dao.AdminRoleInfoDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class AdminRoleInfoBusiness {
	public int addAdminRoleInfo(AdminRoleInfoBean bean) {
		AdminRoleInfoDao dao = new AdminRoleInfoDao();
		return dao.insertAdminRoleInfo(bean);
	}

	public int updateAdminRoleInfo(AdminRoleInfoBean bean) {
		AdminRoleInfoDao dao = new AdminRoleInfoDao();
		return dao.updateAdminRoleInfo(bean);
	}

	public AdminRoleInfoBean getAdminRoleInfoByKey(int roleId) {
		AdminRoleInfoDao dao = new AdminRoleInfoDao();
		return dao.getAdminRoleInfoByKey(roleId);
	}
	
	public AdminRoleInfoBean getAdminRoleInfoByName(String roleName) {
		AdminRoleInfoDao dao = new AdminRoleInfoDao();
		return dao.getAdminRoleInfoByName(roleName);
	}

	public PageResultBean<AdminRoleInfoBean> getAdminRoleInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		AdminRoleInfoDao dao = new AdminRoleInfoDao();
		return dao.getAdminRoleInfoPageListByMap(condMap, pageBean);
	}
	
	public List<AdminRoleInfoBean> getAdminRoleInfoAllList() {
		AdminRoleInfoDao dao = new AdminRoleInfoDao();
		return dao.getAdminRoleInfoListByStatus(Constants.STATUS_VALID);
	}
	
	public boolean isDuplicateRoleName(int roleId, String roleName) {
		AdminRoleInfoBean bean = getAdminRoleInfoByName(roleName);
		if (bean == null) {
			return false;
		} else {
			if (roleId > 0 && roleId == bean.getRoleId()) {
				return false;
			}
		}
		return true;
	}

}