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
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TrainingClassInfoDao {
	private static Logger log = LoggerFactory.getLogger(TrainingClassInfoDao.class);

	public int insertTrainingClassInfo(TrainingClassInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_class_info ( class_name, organization_id, training_year, training_type, class_people_quantity, major_type_id, major_id, training_pose, province, city, region, training_begin_time, training_end_time, registration_end_time, training_item_1, training_item_2, class_status, auth_organization_id, auth_status, auth_time, sync_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getClassName());
			ps.setInt(2, bean.getOrganizationId());
			ps.setString(3, bean.getTrainingYear());
			ps.setInt(4, bean.getTrainingType());
			ps.setInt(5, bean.getClassPeopleQuantity());
			ps.setInt(6, bean.getMajorTypeId());
			ps.setInt(7, bean.getMajorId());
			ps.setString(8, bean.getTrainingPose());
			ps.setString(9, bean.getProvince());
			ps.setString(10, bean.getCity());
			ps.setString(11, bean.getRegion());
			ps.setTimestamp(12, bean.getTrainingBeginTime());
			ps.setTimestamp(13, bean.getTrainingEndTime());
			ps.setTimestamp(14, bean.getRegistrationEndTime());
			ps.setString(15, bean.getTrainingItem1());
			ps.setString(16, bean.getTrainingItem2());
			ps.setString(17, bean.getClassStatus());
			ps.setInt(18, bean.getAuthOrganizationId());
			ps.setString(19, bean.getAuthStatus());
			ps.setTimestamp(20, bean.getAuthTime());
			ps.setInt(21, bean.getSyncStatus());
			ps.setInt(22, bean.getStatus());
			ps.setInt(23, bean.getCreateUser());
			ps.setString(24, bean.getMemo());

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

	public int insertTrainingClassInfoWithCreateTime(TrainingClassInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_class_info ( class_name, organization_id, training_year, training_type, class_people_quantity, major_type_id, major_id, training_pose, province, city, region, training_begin_time, training_end_time, registration_end_time, training_item_1, training_item_2, class_status, auth_organization_id, auth_status, auth_time, sync_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getClassName());
			ps.setInt(2, bean.getOrganizationId());
			ps.setString(3, bean.getTrainingYear());
			ps.setInt(4, bean.getTrainingType());
			ps.setInt(5, bean.getClassPeopleQuantity());
			ps.setInt(6, bean.getMajorTypeId());
			ps.setInt(7, bean.getMajorId());
			ps.setString(8, bean.getTrainingPose());
			ps.setString(9, bean.getProvince());
			ps.setString(10, bean.getCity());
			ps.setString(11, bean.getRegion());
			ps.setTimestamp(12, bean.getTrainingBeginTime());
			ps.setTimestamp(13, bean.getTrainingEndTime());
			ps.setTimestamp(14, bean.getRegistrationEndTime());
			ps.setString(15, bean.getTrainingItem1());
			ps.setString(16, bean.getTrainingItem2());
			ps.setString(17, bean.getClassStatus());
			ps.setInt(18, bean.getAuthOrganizationId());
			ps.setString(19, bean.getAuthStatus());
			ps.setTimestamp(20, bean.getAuthTime());
			ps.setInt(21, bean.getSyncStatus());
			ps.setInt(22, bean.getStatus());
			ps.setTimestamp(23, bean.getCreateTime());
			ps.setInt(24, bean.getCreateUser());
			ps.setString(25, bean.getMemo());

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

	public int updateTrainingClassInfo(TrainingClassInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update training_class_info set class_name = ?, organization_id = ?, training_year = ?, training_type = ?, class_people_quantity = ?, major_type_id = ?, major_id = ?, training_pose = ?, province = ?, city = ?, region = ?, training_begin_time = ?, training_end_time = ?, registration_end_time = ?, training_item_1 = ?, training_item_2 = ?, class_status = ?, auth_organization_id = ?, auth_status = ?, auth_time = ?, sync_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where class_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getClassName());
			ps.setInt(2, bean.getOrganizationId());
			ps.setString(3, bean.getTrainingYear());
			ps.setInt(4, bean.getTrainingType());
			ps.setInt(5, bean.getClassPeopleQuantity());
			ps.setInt(6, bean.getMajorTypeId());
			ps.setInt(7, bean.getMajorId());
			ps.setString(8, bean.getTrainingPose());
			ps.setString(9, bean.getProvince());
			ps.setString(10, bean.getCity());
			ps.setString(11, bean.getRegion());
			ps.setTimestamp(12, bean.getTrainingBeginTime());
			ps.setTimestamp(13, bean.getTrainingEndTime());
			ps.setTimestamp(14, bean.getRegistrationEndTime());
			ps.setString(15, bean.getTrainingItem1());
			ps.setString(16, bean.getTrainingItem2());
			ps.setString(17, bean.getClassStatus());
			ps.setInt(18, bean.getAuthOrganizationId());
			ps.setString(19, bean.getAuthStatus());
			ps.setTimestamp(20, bean.getAuthTime());
			ps.setInt(21, bean.getSyncStatus());
			ps.setInt(22, bean.getStatus());
			ps.setInt(23, bean.getModifyUser());
			ps.setString(24, bean.getMemo());

			ps.setInt(25, bean.getClassId());

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

	public TrainingClassInfoBean getTrainingClassInfoByKey(int classId) {
		TrainingClassInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_info where class_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, classId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingClassInfoBean();
				bean = BeanKit.resultSet2Bean(rs, TrainingClassInfoBean.class);
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

	public TrainingClassInfoBean getTrainingClassInfoByMemo(String memo) {
		TrainingClassInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_info where memo = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, memo);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingClassInfoBean();
				bean = BeanKit.resultSet2Bean(rs, TrainingClassInfoBean.class);
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

	public PageResultBean<TrainingClassInfoBean> getTrainingClassInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<TrainingClassInfoBean> result = new PageResultBean<TrainingClassInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String className = condMap.get("className");
			if (StringKit.isValid(className)) {
				condList.add("class_name = ?");
				paramList.add(className);
			}
			String trainingType = condMap.get("trainingType");
			if (StringKit.isValid(trainingType)) {
				condList.add("training_type = ?");
				paramList.add(trainingType);
			}
			String trainingYear = condMap.get("trainingYear");
			if (StringKit.isValid(trainingYear)) {
				condList.add("training_year = ?");
				paramList.add(trainingYear);
			}
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
			String authStatus = condMap.get("authStatus");
			if (StringKit.isValid(authStatus)) {
				condList.add("auth_status = ?");
				paramList.add(authStatus);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String organizationId = condMap.get("organizationId");
			if (StringKit.isValid(organizationId)) {
				condList.add("organization_id = ?");
				paramList.add(organizationId);
			}
			String majorTypeId = condMap.get("majorTypeId");
			if (StringKit.isValid(majorTypeId)) {
				condList.add("major_type_id = ?");
				paramList.add(majorTypeId);
			}
			String majorId = condMap.get("majorId");
			if (StringKit.isValid(majorId)) {
				condList.add("major_id = ?");
				paramList.add(majorId);
			}
			String orderQuery = "order by class_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TrainingClassInfoBean.class, pageBean, db);
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

	public List<TrainingClassInfoBean> getTrainingClassInfoListByMap(HashMap<String, String> condMap) {
		List<TrainingClassInfoBean> result = new ArrayList<TrainingClassInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String className = condMap.get("className");
			if (StringKit.isValid(className)) {
				condList.add("class_name = ?");
				paramList.add(className);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
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
			String organizationId = condMap.get("organizationId");
			if (StringKit.isValid(organizationId)) {
				condList.add("organization_id = ?");
				paramList.add(organizationId);
			}
			String trainingType = condMap.get("trainingType");
			if (StringKit.isValid(trainingType)) {
				condList.add("training_type = ?");
				paramList.add(trainingType);
			}
			String trainingYear = condMap.get("trainingYear");
			if (StringKit.isValid(trainingYear)) {
				condList.add("training_year = ?");
				paramList.add(trainingYear);
			}

			String authStatus = condMap.get("authStatus");
			if (StringKit.isValid(authStatus)) {
				condList.add("auth_status = ?");
				paramList.add(authStatus);
			}

			String majorTypeId = condMap.get("majorTypeId");
			if (StringKit.isValid(majorTypeId)) {
				condList.add("major_type_id = ?");
				paramList.add(majorTypeId);
			}
			String majorId = condMap.get("majorId");
			if (StringKit.isValid(majorId)) {
				condList.add("major_id = ?");
				paramList.add(majorId);
			}
			String orderQuery = "order by class_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TrainingClassInfoBean.class, db);
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

	public int getTrainingClassInfoRecordResultByMap(HashMap<String, String> condMap) {
		int result = 0;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
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
			String organizationId = condMap.get("organizationId");
			if (StringKit.isValid(organizationId)) {
				condList.add("organization_id = ?");
				paramList.add(organizationId);
			}
			String trainingType = condMap.get("trainingType");
			if (StringKit.isValid(trainingType)) {
				condList.add("training_type = ?");
				paramList.add(trainingType);
			}
			String trainingYear = condMap.get("trainingYear");
			if (StringKit.isValid(trainingYear)) {
				condList.add("training_year = ?");
				paramList.add(trainingYear);
			}

			String authStatus = condMap.get("authStatus");
			if (StringKit.isValid(authStatus)) {
				condList.add("auth_status = ?");
				paramList.add(authStatus);
			}

			String majorTypeId = condMap.get("majorTypeId");
			if (StringKit.isValid(majorTypeId)) {
				condList.add("major_type_id = ?");
				paramList.add(majorTypeId);
			}
			String majorId = condMap.get("majorId");
			if (StringKit.isValid(majorId)) {
				condList.add("major_id = ?");
				paramList.add(majorId);
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
