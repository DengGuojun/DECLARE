package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.AdminUserGroupBean;
import com.lpmas.declare.admin.dao.AdminUserGroupDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class AdminUserGroupBusiness {
	public int addAdminUserGroup(AdminUserGroupBean bean) {
		AdminUserGroupDao dao = new AdminUserGroupDao();
		return dao.insertAdminUserGroup(bean);
	}

	public int updateAdminUserGroup(AdminUserGroupBean bean) {
		AdminUserGroupDao dao = new AdminUserGroupDao();
		return dao.updateAdminUserGroup(bean);
	}

	public AdminUserGroupBean getAdminUserGroupByKey(int groupId) {
		AdminUserGroupDao dao = new AdminUserGroupDao();
		return dao.getAdminUserGroupByKey(groupId);
	}

	public AdminUserGroupBean getAdminUserGroupByKey(String province, String city, String region) {
		AdminUserGroupDao dao = new AdminUserGroupDao();
		return dao.getAdminUserGroupByKey(province, city, region);
	}

	public PageResultBean<AdminUserGroupBean> getAdminUserGroupPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		AdminUserGroupDao dao = new AdminUserGroupDao();
		return dao.getAdminUserGroupPageListByMap(condMap, pageBean);
	}
	
	public List<AdminUserGroupBean> getAdminUserGroupListByMap(HashMap<String, String> condMap) {
		AdminUserGroupDao dao = new AdminUserGroupDao();
		return dao.getAdminUserGroupListByMap(condMap);
	}
	
	public List<AdminUserGroupBean> getAdminUserGroupAllList() {
		AdminUserGroupDao dao = new AdminUserGroupDao();
		return dao.getAdminUserGroupAllList();
	}

	public boolean isDuplicateUserGroup(int groupId, String province, String city, String region) {
		AdminUserGroupBean bean = getAdminUserGroupByKey(province, city, region);
		if (bean == null) {
			return false;
		} else {
			if (groupId > 0 && groupId == bean.getGroupId()) {
				return false;
			}
		}
		return true;
	}

}