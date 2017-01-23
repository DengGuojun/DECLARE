package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TrainingOrganizationInfoDao {
	private static Logger log = LoggerFactory.getLogger(TrainingOrganizationInfoDao.class);

	public int insertTrainingOrganizationInfo(TrainingOrganizationInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_organization_info ( organization_name, organization_type, training_year, organization_category, organization_level, province, city, region, representative_name, telephone, mobile, address, zip_code, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getOrganizationName());
			ps.setString(2, bean.getOrganizationType());
			ps.setString(3, bean.getTrainingYear());
			ps.setString(4, bean.getOrganizationCategory());
			ps.setString(5, bean.getOrganizationLevel());
			ps.setString(6, bean.getProvince());
			ps.setString(7, bean.getCity());
			ps.setString(8, bean.getRegion());
			ps.setString(9, bean.getRepresentativeName());
			ps.setString(10, bean.getTelephone());
			ps.setString(11, bean.getMobile());
			ps.setString(12, bean.getAddress());
			ps.setString(13, bean.getZipCode());
			ps.setInt(14, bean.getStatus());
			ps.setInt(15, bean.getCreateUser());
			ps.setString(16, bean.getMemo());

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

	public int insertTrainingOrganizationInfoWithCreateTime(TrainingOrganizationInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into training_organization_info ( organization_name, organization_type, training_year, organization_category, organization_level, province, city, region, representative_name, telephone, mobile, address, zip_code, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getOrganizationName());
			ps.setString(2, bean.getOrganizationType());
			ps.setString(3, bean.getTrainingYear());
			ps.setString(4, bean.getOrganizationCategory());
			ps.setString(5, bean.getOrganizationLevel());
			ps.setString(6, bean.getProvince());
			ps.setString(7, bean.getCity());
			ps.setString(8, bean.getRegion());
			ps.setString(9, bean.getRepresentativeName());
			ps.setString(10, bean.getTelephone());
			ps.setString(11, bean.getMobile());
			ps.setString(12, bean.getAddress());
			ps.setString(13, bean.getZipCode());
			ps.setInt(14, bean.getStatus());
			ps.setTimestamp(15, bean.getCreateTime());
			ps.setInt(16, bean.getCreateUser());
			ps.setString(17, bean.getMemo());

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

	public int updateTrainingOrganizationInfo(TrainingOrganizationInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update training_organization_info set organization_name = ?, organization_type = ?, training_year = ?, organization_category = ?, organization_level = ?, province = ?, city = ?, region = ?, representative_name = ?, telephone = ?, mobile = ?, address = ?, zip_code = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where organization_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getOrganizationName());
			ps.setString(2, bean.getOrganizationType());
			ps.setString(3, bean.getTrainingYear());
			ps.setString(4, bean.getOrganizationCategory());
			ps.setString(5, bean.getOrganizationLevel());
			ps.setString(6, bean.getProvince());
			ps.setString(7, bean.getCity());
			ps.setString(8, bean.getRegion());
			ps.setString(9, bean.getRepresentativeName());
			ps.setString(10, bean.getTelephone());
			ps.setString(11, bean.getMobile());
			ps.setString(12, bean.getAddress());
			ps.setString(13, bean.getZipCode());
			ps.setInt(14, bean.getStatus());
			ps.setInt(15, bean.getModifyUser());
			ps.setString(16, bean.getMemo());

			ps.setInt(17, bean.getOrganizationId());

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

	public TrainingOrganizationInfoBean getTrainingOrganizationInfoByKey(int organizationId) {
		TrainingOrganizationInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_organization_info where organization_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, organizationId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TrainingOrganizationInfoBean();
				bean = BeanKit.resultSet2Bean(rs, TrainingOrganizationInfoBean.class);
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

	// 获取是否存在指定的培训机构信息 验证是否存在相同节目
	public TrainingOrganizationInfoBean getTrainingOrganization(TrainingOrganizationInfoBean bean) {
		TrainingOrganizationInfoBean trainingOrganizationInfoBean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_organization_info where organization_name = ?  and  province = ? and city = ? and region = ? and organization_type = ?  and status = ? ";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getOrganizationName());
			ps.setString(2, bean.getProvince());
			ps.setString(3, bean.getCity());
			ps.setString(4, bean.getRegion());
			ps.setString(5, bean.getOrganizationType());
			ps.setInt(6, Constants.STATUS_VALID); // 暂时设定为存在数据库的状态。
			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				trainingOrganizationInfoBean = new TrainingOrganizationInfoBean();
				trainingOrganizationInfoBean = BeanKit.resultSet2Bean(rs, TrainingOrganizationInfoBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			trainingOrganizationInfoBean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return trainingOrganizationInfoBean;
	}

	public PageResultBean<TrainingOrganizationInfoBean> getTrainingOrganizationInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<TrainingOrganizationInfoBean> result = new PageResultBean<TrainingOrganizationInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_organization_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String organizationName = condMap.get("organizationName");
			if (StringKit.isValid(organizationName)) {
				condList.add("organization_name like ?");
				paramList.add("%" + organizationName + "%");
			}

			String trainingYear = condMap.get("trainingYear");
			if (StringKit.isValid(trainingYear)) {
				condList.add("training_year = ?");
				paramList.add(trainingYear);
			}
			// 机构类型
			String organizationCategory = condMap.get("organizationCategory");
			if (StringKit.isValid(organizationCategory)) {
				condList.add("organization_category = ?");
				paramList.add(organizationCategory);
			}
			String organizationType = condMap.get("organizationType");
			if (StringKit.isValid(organizationType)) {
				condList.add("organization_type = ?");
				paramList.add(organizationType);
			}

			String organizationLevel = condMap.get("organizationLevel");
			if (StringKit.isValid(organizationLevel)) {
				condList.add("organization_level = ?");
				paramList.add(organizationLevel);
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

			String orderQuery = "order by organization_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TrainingOrganizationInfoBean.class, pageBean, db);
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

	public int getTrainingOrganizationCountByMap(Map<String, String> condMap) {
		int result = 0;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select 1 from training_organization_info";

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
			String level = condMap.get("level");
			if (StringKit.isValid(level)) {
				condList.add("organization_level = ?");
				paramList.add(level);
			}
			String organizationType = condMap.get("organizationType");
			if (StringKit.isValid(organizationType)) {
				condList.add("organization_type = ?");
				paramList.add(organizationType);
			}
			String beginTime = condMap.get("beginTime");
			if (StringKit.isValid(beginTime)) {
				condList.add("create_time >= ?");
				paramList.add(beginTime);
			}
			String endTime = condMap.get("endTime");
			if (StringKit.isValid(endTime)) {
				condList.add("create_time <= ?");
				paramList.add(endTime);
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

	public List<TrainingOrganizationInfoBean> getTrainingOrganizationInfoListByMap(HashMap<String, String> condMap) {
		List<TrainingOrganizationInfoBean> result = new ArrayList<TrainingOrganizationInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from training_organization_info";

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
			String memo = condMap.get("memo");
			if (StringKit.isValid(memo)) {
				condList.add("memo = ?");
				paramList.add(memo);
			}
			String organizationType = condMap.get("organizationType");
			if (StringKit.isValid(organizationType)) {
				condList.add("organization_type = ?");
				paramList.add(organizationType);
			}
			String organizationLevel = condMap.get("organizationLevel");
			if (StringKit.isValid(organizationLevel)) {
				condList.add("organization_level = ?");
				paramList.add(organizationLevel);
			}
			String trainingYear = condMap.get("trainingYear");
			if (StringKit.isValid(trainingYear)) {
				condList.add("training_year = ?");
				paramList.add(trainingYear);
			}
			String organizationCategory = condMap.get("organizationCategory");
			if (StringKit.isValid(organizationCategory)) {
				condList.add("organization_category = ?");
				paramList.add(organizationCategory);
			}
			String orderQuery = "order by organization_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TrainingOrganizationInfoBean.class, db);
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
