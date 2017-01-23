package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class AdminUserInfoDao {
	private static Logger log = LoggerFactory.getLogger(AdminUserInfoDao.class);

	public int insertAdminUserInfo(AdminUserInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into admin_user_info ( login_id, login_password, admin_user_name, group_id, admin_user_type, admin_user_gender, admin_user_birthday, admin_user_pose, admin_user_department, admin_user_level, province, city, region, admin_user_telephone, admin_user_phone, admin_user_fax, admin_user_email, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getLoginId());
			ps.setString(2, bean.getLoginPassword());
			ps.setString(3, bean.getAdminUserName());
			ps.setInt(4, bean.getGroupId());
			ps.setInt(5, bean.getAdminUserType());
			ps.setInt(6, bean.getAdminUserGender());
			ps.setDate(7, bean.getAdminUserBirthday());
			ps.setString(8, bean.getAdminUserPose());
			ps.setString(9, bean.getAdminUserDepartment());
			ps.setString(10, bean.getAdminUserLevel());
			ps.setString(11, bean.getProvince());
			ps.setString(12, bean.getCity());
			ps.setString(13, bean.getRegion());
			ps.setString(14, bean.getAdminUserTelephone());
			ps.setString(15, bean.getAdminUserPhone());
			ps.setString(16, bean.getAdminUserFax());
			ps.setString(17, bean.getAdminUserEmail());
			ps.setInt(18, bean.getStatus());
			ps.setInt(19, bean.getCreateUser());
			ps.setString(20, bean.getMemo());

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

	public int updateAdminUserInfo(AdminUserInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update admin_user_info set login_id = ?, login_password = ?, admin_user_name = ?, group_id = ?, admin_user_type = ?, admin_user_gender = ?, admin_user_birthday = ?, admin_user_pose = ?, admin_user_department = ?, admin_user_level = ?, province = ?, city = ?, region = ?, admin_user_telephone = ?, admin_user_phone = ?, admin_user_fax = ?, admin_user_email = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where user_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getLoginId());
			ps.setString(2, bean.getLoginPassword());
			ps.setString(3, bean.getAdminUserName());
			ps.setInt(4, bean.getGroupId());
			ps.setInt(5, bean.getAdminUserType());
			ps.setInt(6, bean.getAdminUserGender());
			ps.setDate(7, bean.getAdminUserBirthday());
			ps.setString(8, bean.getAdminUserPose());
			ps.setString(9, bean.getAdminUserDepartment());
			ps.setString(10, bean.getAdminUserLevel());
			ps.setString(11, bean.getProvince());
			ps.setString(12, bean.getCity());
			ps.setString(13, bean.getRegion());
			ps.setString(14, bean.getAdminUserTelephone());
			ps.setString(15, bean.getAdminUserPhone());
			ps.setString(16, bean.getAdminUserFax());
			ps.setString(17, bean.getAdminUserEmail());
			ps.setInt(18, bean.getStatus());
			ps.setInt(19, bean.getModifyUser());
			ps.setString(20, bean.getMemo());

			ps.setInt(21, bean.getUserId());

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

	public AdminUserInfoBean getAdminUserInfoByKey(int userId) {
		AdminUserInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_info where user_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new AdminUserInfoBean();
				bean = BeanKit.resultSet2Bean(rs, AdminUserInfoBean.class);
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

	public AdminUserInfoBean getAdminUserInfoByName(String name) {
		AdminUserInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_info where admin_user_name = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, name);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new AdminUserInfoBean();
				bean = BeanKit.resultSet2Bean(rs, AdminUserInfoBean.class);
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

	public AdminUserInfoBean getAdminUserInfoByLoginId(String loginId) {
		AdminUserInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_info where login_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, loginId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new AdminUserInfoBean();
				bean = BeanKit.resultSet2Bean(rs, AdminUserInfoBean.class);
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

	public PageResultBean<AdminUserInfoBean> getAdminUserInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<AdminUserInfoBean> result = new PageResultBean<AdminUserInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String loginId = condMap.get("loginId");
			if (StringKit.isValid(loginId)) {
				condList.add("login_id like ?");
				paramList.add("%" + loginId + "%");
			}
			String adminUserName = condMap.get("adminUserName");
			if (StringKit.isValid(adminUserName)) {
				condList.add("admin_user_name like ?");
				paramList.add("%" + adminUserName + "%");
			}
			String adminUserTelephone = condMap.get("adminUserTelephone");
			if (StringKit.isValid(adminUserTelephone)) {
				condList.add("admin_user_telephone = ?");
				paramList.add(adminUserTelephone);
			}
			String groupId = condMap.get("groupId");
			if (StringKit.isValid(groupId)) {
				condList.add("group_id = ?");
				paramList.add(groupId);
			}
			String adminUserType = condMap.get("adminUserType");
			if (StringKit.isValid(adminUserType)) {
				condList.add("admin_user_type = ?");
				paramList.add(adminUserType);
			}
			String adminUserLevel = condMap.get("adminUserLevel");
			if (StringKit.isValid(adminUserLevel)) {
				condList.add("admin_user_level = ?");
				paramList.add(adminUserLevel);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by user_id asc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, AdminUserInfoBean.class, pageBean, db);
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

	public List<AdminUserInfoBean> getAdminUserInfoListByMap(HashMap<String, String> condMap) {
		List<AdminUserInfoBean> result = new ArrayList<AdminUserInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from admin_user_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String groupId = condMap.get("groupId");
			if (StringKit.isValid(groupId)) {
				condList.add("group_id = ?");
				paramList.add(groupId);
			}
			String adminUserType = condMap.get("adminUserType");
			if (StringKit.isValid(adminUserType)) {
				condList.add("admin_user_type = ?");
				paramList.add(adminUserType);
			}
			String adminUserLevel = condMap.get("adminUserLevel");
			if (StringKit.isValid(adminUserLevel)) {
				condList.add("admin_user_level = ?");
				paramList.add(adminUserLevel);
			}
			String loginId = condMap.get("loginId");
			if (StringKit.isValid(loginId)) {
				condList.add("login_id = ?");
				paramList.add(loginId);
			}
			String adminUserName = condMap.get("adminUserName");
			if (StringKit.isValid(adminUserName)) {
				condList.add("admin_user_name like ?");
				paramList.add("%" + adminUserName + "%");
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String orderQuery = "order by user_id asc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, AdminUserInfoBean.class, db);
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

}
