package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserGroupBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class AdminUserGroupDao {
	private static Logger log = LoggerFactory.getLogger(AdminUserGroupDao.class);

	public int insertAdminUserGroup(AdminUserGroupBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into admin_user_group ( group_name, admin_group_level, province, city, region, address, zip_code, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getGroupName());
			ps.setString(2, bean.getAdminGroupLevel());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setString(5, bean.getRegion());
			ps.setString(6, bean.getAddress());
			ps.setString(7, bean.getZipCode());
			ps.setInt(8, bean.getStatus());
			ps.setInt(9, bean.getCreateUser());
			ps.setString(10, bean.getMemo());

			result = db.executePstmtInsert();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public int updateAdminUserGroup(AdminUserGroupBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update admin_user_group set group_name = ?, admin_group_level = ?, province = ?, city = ?, region = ?, address = ?, zip_code = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where group_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getGroupName());
			ps.setString(2, bean.getAdminGroupLevel());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setString(5, bean.getRegion());
			ps.setString(6, bean.getAddress());
			ps.setString(7, bean.getZipCode());
			ps.setInt(8, bean.getStatus());
			ps.setInt(9, bean.getModifyUser());
			ps.setString(10, bean.getMemo());

			ps.setInt(11, bean.getGroupId());

			result = db.executePstmtUpdate();
		} catch (Exception e) {
			log.error("", e);
			result = -1;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public AdminUserGroupBean getAdminUserGroupByKey(int groupId) {
		AdminUserGroupBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_group where group_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, groupId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new AdminUserGroupBean();
				bean = BeanKit.resultSet2Bean(rs, AdminUserGroupBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			bean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return bean;
	}

	public AdminUserGroupBean getAdminUserGroupByKey(String province, String city, String region) {
		AdminUserGroupBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_group where province = ? and city = ? and region = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, province);
			ps.setString(2, city);
			ps.setString(3, region);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new AdminUserGroupBean();
				bean = BeanKit.resultSet2Bean(rs, AdminUserGroupBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			bean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return bean;
	}

	public PageResultBean<AdminUserGroupBean> getAdminUserGroupPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<AdminUserGroupBean> result = new PageResultBean<AdminUserGroupBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_group";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String province = condMap.get("province");
			if (StringKit.isValid(province)) {
				condList.add("province = ?");
				paramList.add(province);
			}
			String city = condMap.get("city");
			if (StringKit.isValid(city)) {
				condList.add("city = ?");
				paramList.add(city);
			}
			String region = condMap.get("region");
			if (StringKit.isValid(region)) {
				condList.add("region = ?");
				paramList.add(region);
			}
			String adminGroupLevel = condMap.get("adminGroupLevel");
			if (StringKit.isValid(adminGroupLevel)) {
				condList.add("admin_group_level = ?");
				paramList.add(adminGroupLevel);
			}
			String groupName = condMap.get("groupName");
			if (StringKit.isValid(groupName)) {
				condList.add("group_name = ?");
				paramList.add(groupName);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by group_id asc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, AdminUserGroupBean.class, pageBean,
					db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

	public List<AdminUserGroupBean> getAdminUserGroupListByMap(HashMap<String, String> condMap) {
		List<AdminUserGroupBean> list = new ArrayList<AdminUserGroupBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_group";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String province = condMap.get("province");
			if (StringKit.isValid(province)) {
				condList.add("province = ?");
				paramList.add(province);
			}
			String city = condMap.get("city");
			if (StringKit.isValid(city)) {
				condList.add("city = ?");
				paramList.add(city);
			}
			String region = condMap.get("region");
			if (StringKit.isValid(region)) {
				condList.add("region = ?");
				paramList.add(region);
			}
			String adminGroupLevel = condMap.get("adminGroupLevel");
			if (StringKit.isValid(adminGroupLevel)) {
				condList.add("admin_group_level = ?");
				paramList.add(adminGroupLevel);
			}
			String groupName = condMap.get("groupName");
			if (StringKit.isValid(groupName)) {
				condList.add("group_name = ?");
				paramList.add(groupName);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by group_id asc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			list = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, AdminUserGroupBean.class, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return list;
	}

	public List<AdminUserGroupBean> getAdminUserGroupAllList() {
		List<AdminUserGroupBean> list = new ArrayList<AdminUserGroupBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_group";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String orderQuery = "order by group_id asc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			list = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, AdminUserGroupBean.class, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return list;
	}
}
