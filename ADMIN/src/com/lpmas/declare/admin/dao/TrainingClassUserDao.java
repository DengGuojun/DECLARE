package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TrainingClassUserDao {
	private static Logger log = LoggerFactory.getLogger(TrainingClassUserDao.class);

	public int insertTrainingClassUser(TrainingClassUserBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_class_user ( class_id, declare_id, sign_up_time, exam_result, has_certification, user_status, sync_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getClassId());
			ps.setInt(2, bean.getDeclareId());
			ps.setTimestamp(3, bean.getSignUpTime());
			ps.setInt(4, bean.getExamResult());
			ps.setInt(5, bean.getHasCertification());
			ps.setString(6, bean.getUserStatus());
			ps.setInt(7, bean.getSyncStatus());
			ps.setInt(8, bean.getStatus());
			ps.setInt(9, bean.getCreateUser());
			ps.setString(10, bean.getMemo());

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

	public int insertTrainingClassUserWithCreateTime(TrainingClassUserBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_class_user ( class_id, declare_id, sign_up_time, exam_result, has_certification, user_status, sync_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getClassId());
			ps.setInt(2, bean.getDeclareId());
			ps.setTimestamp(3, bean.getSignUpTime());
			ps.setInt(4, bean.getExamResult());
			ps.setInt(5, bean.getHasCertification());
			ps.setString(6, bean.getUserStatus());
			ps.setInt(7, bean.getSyncStatus());
			ps.setInt(8, bean.getStatus());
			ps.setTimestamp(9, bean.getCreateTime());
			ps.setInt(10, bean.getCreateUser());
			ps.setString(11, bean.getMemo());

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

	public int updateTrainingClassUser(TrainingClassUserBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update training_class_user set sign_up_time = ?, exam_result = ?, has_certification = ?, user_status = ?, sync_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where class_id = ? and declare_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setTimestamp(1, bean.getSignUpTime());
			ps.setInt(2, bean.getExamResult());
			ps.setInt(3, bean.getHasCertification());
			ps.setString(4, bean.getUserStatus());
			ps.setInt(5, bean.getSyncStatus());
			ps.setInt(6, bean.getStatus());
			ps.setInt(7, bean.getModifyUser());
			ps.setString(8, bean.getMemo());

			ps.setInt(9, bean.getClassId());
			ps.setInt(10, bean.getDeclareId());

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

	public TrainingClassUserBean getTrainingClassUserByKey(int classId, int declareId) {
		TrainingClassUserBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_user where class_id = ? and declare_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, classId);
			ps.setInt(2, declareId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingClassUserBean();
				bean = BeanKit.resultSet2Bean(rs, TrainingClassUserBean.class);
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

	public TrainingClassUserBean getTrainingClassUserByMemo(String memo) {
		TrainingClassUserBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_user where memo = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, memo);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingClassUserBean();
				bean = BeanKit.resultSet2Bean(rs, TrainingClassUserBean.class);
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

	public PageResultBean<TrainingClassUserBean> getTrainingClassUserPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<TrainingClassUserBean> result = new PageResultBean<TrainingClassUserBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_user";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String classId = condMap.get("classId");
			if (StringKit.isValid(classId)) {
				condList.add("class_id = ?");
				paramList.add(classId);
			}
			String orderQuery = "order by declare_id,class_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TrainingClassUserBean.class,
					pageBean, db);
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

	public int getTrainingClassUserCountByCondition(String province, String city, String region, String trainingYear,
			String userStatus, int declareType) {
		int result = 0;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;

		try {
			db = dbFactory.getDBObjectR();
			String sql = "select count(1) from training_class_user u,declare_info d where "
					+ " user_status = ? and u.status = ? and u.declare_id = d.declare_id and d.declare_type = ?"
					+ " and  class_id in (select class_id from training_class_info where training_year = ?";
			if(StringKit.isValid(province)){
				sql +=" and province = ?";
			}
			if(StringKit.isValid(city)){
				sql +=" and city = ?";
			}
			if(StringKit.isValid(region)){
				sql +=" and region = ?";
			}
			sql+=")";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, userStatus);
			ps.setInt(2, Constants.STATUS_VALID);
			ps.setInt(3, declareType);
			ps.setString(4, trainingYear);
			int count = 4;
			if(StringKit.isValid(province)){
				count++;
				ps.setString(count, province);
			}
			if(StringKit.isValid(city)){
				count++;
				ps.setString(count, city);
			}
			if(StringKit.isValid(region)){
				count++;
				ps.setString(count, region);
			}
			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
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

	public List<TrainingClassUserBean> getTrainingClassUserListByMap(HashMap<String, String> condMap) {
		List<TrainingClassUserBean> result = new ArrayList<TrainingClassUserBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_user";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String classId = condMap.get("classId");
			if (StringKit.isValid(classId)) {
				condList.add("class_id = ?");
				paramList.add(classId);
			}
			String orderQuery = "order by declare_id,class_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TrainingClassUserBean.class,
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

	public int getTrainingClassUserRecordResultByMap(HashMap<String, String> condMap) {
		int result = 0;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_user";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String classId = condMap.get("classId");
			if (StringKit.isValid(classId)) {
				condList.add("class_id = ?");
				paramList.add(classId);
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getTotalRecordResult(sql, condList, paramList, db);
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
