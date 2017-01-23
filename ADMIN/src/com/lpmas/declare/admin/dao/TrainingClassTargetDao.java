package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.TrainingClassTargetBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TrainingClassTargetDao {
	private static Logger log = LoggerFactory.getLogger(TrainingClassTargetDao.class);

	public int insertTrainingClassTarget(TrainingClassTargetBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_class_target ( training_year, country, province, city, region, organization_id, target_quantity_1, target_quantity_2, target_quantity_3, target_quantity_4, target_quantity_5, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getTrainingYear());
			ps.setString(2, bean.getCountry());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setString(5, bean.getRegion());
			ps.setInt(6, bean.getOrganizationId());
			ps.setInt(7, bean.getTargetQuantity1());
			ps.setInt(8, bean.getTargetQuantity2());
			ps.setInt(9, bean.getTargetQuantity3());
			ps.setInt(10, bean.getTargetQuantity4());
			ps.setInt(11, bean.getTargetQuantity5());
			ps.setInt(12, bean.getStatus());
			ps.setInt(13, bean.getCreateUser());
			ps.setString(14, bean.getMemo());

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

	public int updateTrainingClassTarget(TrainingClassTargetBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update training_class_target set training_year = ?, country = ?, province = ?, city = ?, region = ?, organization_id = ?, target_quantity_1 = ?, target_quantity_2 = ?, target_quantity_3 = ?, target_quantity_4 = ?, target_quantity_5 = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where target_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getTrainingYear());
			ps.setString(2, bean.getCountry());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setString(5, bean.getRegion());
			ps.setInt(6, bean.getOrganizationId());
			ps.setInt(7, bean.getTargetQuantity1());
			ps.setInt(8, bean.getTargetQuantity2());
			ps.setInt(9, bean.getTargetQuantity3());
			ps.setInt(10, bean.getTargetQuantity4());
			ps.setInt(11, bean.getTargetQuantity5());
			ps.setInt(12, bean.getStatus());
			ps.setInt(13, bean.getModifyUser());
			ps.setString(14, bean.getMemo());

			ps.setInt(15, bean.getTargetId());

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

	public TrainingClassTargetBean getTrainingClassTargetByKey(int targetId) {
		TrainingClassTargetBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_target where target_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, targetId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingClassTargetBean();
				bean = BeanKit.resultSet2Bean(rs, TrainingClassTargetBean.class);
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

	public TrainingClassTargetBean getTrainingClassTargetByCondition(String province, String city, String region,
			String trainingYear, int organizationId) {
		TrainingClassTargetBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_target "
					+ " where  training_year = ? and organization_id = ? and status = ? and province = ? and city = ? and region = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, trainingYear);
			ps.setInt(2, organizationId);
			ps.setInt(3, Constants.STATUS_VALID);
			ps.setString(4, province);
			ps.setString(5, city);
			ps.setString(6, region);
			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = BeanKit.resultSet2Bean(rs, TrainingClassTargetBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return bean;
	}
	
	public TrainingClassTargetBean getCountryTrainingClassTargetOfYear(String trainingYear) {
		TrainingClassTargetBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select sum(target_quantity_1),sum(target_quantity_2),sum(target_quantity_3),sum(target_quantity_4),sum(target_quantity_5) from training_class_target "
					+ " where  training_year = ?  and status = 1 and city = ? and region = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, trainingYear);
			ps.setString(2, "");
			ps.setString(3, "");
			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingClassTargetBean();
				bean.setTargetQuantity1(rs.getInt(1));
				bean.setTargetQuantity2(rs.getInt(2));
				bean.setTargetQuantity3(rs.getInt(3));
				bean.setTargetQuantity4(rs.getInt(4));
				bean.setTargetQuantity5(rs.getInt(5));
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return bean;
	}

	public PageResultBean<TrainingClassTargetBean> getTrainingClassTargetPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<TrainingClassTargetBean> result = new PageResultBean<TrainingClassTargetBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_target";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by target_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TrainingClassTargetBean.class,
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

	public List<TrainingClassTargetBean> getTrainingClassTargetListByMap(HashMap<String, String> condMap) {
		List<TrainingClassTargetBean> result = new ArrayList<TrainingClassTargetBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_target";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String trainingYear = condMap.get("trainingYear");
			if (StringKit.isValid(trainingYear)) {
				condList.add("training_year = ?");
				paramList.add(trainingYear);
			}
			String trainingType = condMap.get("trainingType");
			if (StringKit.isValid(trainingType)) {
				condList.add("training_type = ?");
				paramList.add(trainingType);
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
			String orderQuery = "order by target_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TrainingClassTargetBean.class,
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

	public int getTrainingClassTargetRecordResultByMap(HashMap<String, String> condMap) {
		int result = 0;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_target";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String trainingYear = condMap.get("trainingYear");
			if (StringKit.isValid(trainingYear)) {
				condList.add("training_year = ?");
				paramList.add(trainingYear);
			}
			String trainingType = condMap.get("trainingType");
			if (StringKit.isValid(trainingType)) {
				condList.add("training_type = ?");
				paramList.add(trainingType);
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
			// region 不为空
			String region = condMap.get("region");
			condList.add("region != ?");
			paramList.add(region);

			String organizationId = condMap.get("organizationId");
			if (StringKit.isValid(organizationId)) {
				condList.add("organization_id = ?");
				paramList.add(organizationId);
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
