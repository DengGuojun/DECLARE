package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.TrainingClassUserConfigBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TrainingClassUserConfigDao {
	private static Logger log = LoggerFactory.getLogger(TrainingClassUserConfigDao.class);

	public int insertTrainingClassUserConfig(TrainingClassUserConfigBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_class_user_config ( province, city, region, config_mode, config_frequency, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getProvince());
			ps.setString(2, bean.getCity());
			ps.setString(3, bean.getRegion());
			ps.setString(4, bean.getConfigMode());
			ps.setString(5, bean.getConfigFrequency());
			ps.setInt(6, bean.getStatus());
			ps.setInt(7, bean.getCreateUser());
			ps.setString(8, bean.getMemo());

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

	public int updateTrainingClassUserConfig(TrainingClassUserConfigBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update training_class_user_config set province = ?, city = ?, region = ?, config_mode = ?, config_frequency = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where config_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getProvince());
			ps.setString(2, bean.getCity());
			ps.setString(3, bean.getRegion());
			ps.setString(4, bean.getConfigMode());
			ps.setString(5, bean.getConfigFrequency());
			ps.setInt(6, bean.getStatus());
			ps.setInt(7, bean.getModifyUser());
			ps.setString(8, bean.getMemo());

			ps.setInt(9, bean.getConfigId());

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

	public TrainingClassUserConfigBean getTrainingClassUserConfigByKey(int configId) {
		TrainingClassUserConfigBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_user_config where config_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, configId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingClassUserConfigBean();
				bean = BeanKit.resultSet2Bean(rs, TrainingClassUserConfigBean.class);
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

	public PageResultBean<TrainingClassUserConfigBean> getTrainingClassUserConfigPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<TrainingClassUserConfigBean> result = new PageResultBean<TrainingClassUserConfigBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_user_config";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String province = condMap.get("province");
			if (!StringKit.isNull(province)) {
				condList.add("province = ?");
				paramList.add(province);
			}
			
			String city = condMap.get("city");
			if (!StringKit.isNull(city)) {
				condList.add("city = ?");
				paramList.add(city);
			}
			
			String region = condMap.get("region");
			if (!StringKit.isNull(region)) {
				condList.add("region = ?");
				paramList.add(region);
			}
			
			
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by config_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TrainingClassUserConfigBean.class,
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

	public TrainingClassUserConfigBean getTrainingClassUserConfigBeanByAreaAndSatus(TrainingClassUserConfigBean bean) {
		TrainingClassUserConfigBean trainingClassUserConfigBean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_user_config where status = ? and province = ? and city = ? and region = ? ";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getStatus());
			ps.setString(2, bean.getProvince());
			ps.setString(3, bean.getCity());
			ps.setString(4, bean.getRegion());
			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				trainingClassUserConfigBean = new TrainingClassUserConfigBean();
				trainingClassUserConfigBean = BeanKit.resultSet2Bean(rs, TrainingClassUserConfigBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			trainingClassUserConfigBean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return trainingClassUserConfigBean;
	}

	public List<TrainingClassUserConfigBean> getTrainingClassUserConfigPageListByMap(HashMap<String, String> condMap) {
		List<TrainingClassUserConfigBean> result = new ArrayList<TrainingClassUserConfigBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_class_user_config";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String province = condMap.get("province");
			if (!StringKit.isNull(province)) {
				condList.add("province = ?");
				paramList.add(province);
			}
			
			String city = condMap.get("city");
			if (!StringKit.isNull(city)) {
				condList.add("city = ?");
				paramList.add(city);
			}
			
			String region = condMap.get("region");
			if (!StringKit.isNull(region)) {
				condList.add("region = ?");
				paramList.add(region);
			}
			
			
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by config_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TrainingClassUserConfigBean.class, db);
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
