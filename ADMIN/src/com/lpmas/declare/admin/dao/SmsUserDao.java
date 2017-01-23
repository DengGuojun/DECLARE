package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.SmsUserBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class SmsUserDao {
	private static Logger log = LoggerFactory.getLogger(SmsUserDao.class);

	public int insertSmsUser(SmsUserBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into sms_user ( sms_user_name, sms_user_province, sms_user_mobile, sms_user_corporate, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getSmsUserName());
			ps.setString(2, bean.getSmsUserProvince());
			ps.setString(3, bean.getSmsUserMobile());
			ps.setString(4, bean.getSmsUserCorporate());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getCreateUser());
			ps.setString(7, bean.getMemo());

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

	public int updateSmsUser(SmsUserBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update sms_user set sms_user_name = ?, sms_user_province = ?, sms_user_mobile = ?, sms_user_corporate = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where sms_user_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getSmsUserName());
			ps.setString(2, bean.getSmsUserProvince());
			ps.setString(3, bean.getSmsUserMobile());
			ps.setString(4, bean.getSmsUserCorporate());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getModifyUser());
			ps.setString(7, bean.getMemo());

			ps.setInt(8, bean.getSmsUserId());

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

	public SmsUserBean getSmsUserByKey(int smsUserId) {
		SmsUserBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from sms_user where sms_user_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, smsUserId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new SmsUserBean();
				bean = BeanKit.resultSet2Bean(rs, SmsUserBean.class);
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
	
	public SmsUserBean getSmsUserByName(String name) {
		SmsUserBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from sms_user where sms_user_name = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, name);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new SmsUserBean();
				bean = BeanKit.resultSet2Bean(rs, SmsUserBean.class);
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

	public PageResultBean<SmsUserBean> getSmsUserPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<SmsUserBean> result = new PageResultBean<SmsUserBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from sms_user";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by sms_user_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, SmsUserBean.class, pageBean, db);
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
	
	public List<SmsUserBean> getSmsUserListByMap(HashMap<String, String> condMap) {
		List<SmsUserBean> list = new ArrayList<SmsUserBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql =  "select * from sms_user";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String smsUserName = condMap.get("smsUserName");
			if (StringKit.isValid(smsUserName)) {
				condList.add("sms_user_name like ?");
				paramList.add("%" + smsUserName + "%");
			}
			String smsUserCorporate = condMap.get("smsUserCorporate");
			if (StringKit.isValid(smsUserCorporate)) {
				condList.add("sms_user_corporate = ?");
				paramList.add(smsUserCorporate);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String orderQuery = "order by sms_user_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			list = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, SmsUserBean.class, db);
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
