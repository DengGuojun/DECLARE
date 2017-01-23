package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.GovernmentOrganizationInfoBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class GovernmentOrganizationInfoDao {
	private static Logger log = LoggerFactory.getLogger(GovernmentOrganizationInfoDao.class);

	public int insertGovernmentOrganizationInfo(GovernmentOrganizationInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into government_organization_info ( organization_name, training_year, department, responsible_person_name, responsible_person_mobile, operator_name, operator_mobile, fax, organization_level, province, city, region, address, zip_code, is_demonstration_area, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getOrganizationName());
			ps.setString(2, bean.getTrainingYear());
			ps.setString(3, bean.getDepartment());
			ps.setString(4, bean.getResponsiblePersonName());
			ps.setString(5, bean.getResponsiblePersonMobile());
			ps.setString(6, bean.getOperatorName());
			ps.setString(7, bean.getOperatorMobile());
			ps.setString(8, bean.getFax());
			ps.setString(9, bean.getOrganizationLevel());
			ps.setString(10, bean.getProvince());
			ps.setString(11, bean.getCity());
			ps.setString(12, bean.getRegion());
			ps.setString(13, bean.getAddress());
			ps.setString(14, bean.getZipCode());
			ps.setInt(15, bean.getIsDemonstrationArea());
			ps.setInt(16, bean.getStatus());
			ps.setInt(17, bean.getCreateUser());
			ps.setString(18, bean.getMemo());

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
	public int insertGovernmentOrganizationInfoWithCreateTime(GovernmentOrganizationInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into government_organization_info ( organization_name, training_year, department, responsible_person_name, responsible_person_mobile, operator_name, operator_mobile, fax, organization_level, province, city, region, address, zip_code, is_demonstration_area, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getOrganizationName());
			ps.setString(2, bean.getTrainingYear());
			ps.setString(3, bean.getDepartment());
			ps.setString(4, bean.getResponsiblePersonName());
			ps.setString(5, bean.getResponsiblePersonMobile());
			ps.setString(6, bean.getOperatorName());
			ps.setString(7, bean.getOperatorMobile());
			ps.setString(8, bean.getFax());
			ps.setString(9, bean.getOrganizationLevel());
			ps.setString(10, bean.getProvince());
			ps.setString(11, bean.getCity());
			ps.setString(12, bean.getRegion());
			ps.setString(13, bean.getAddress());
			ps.setString(14, bean.getZipCode());
			ps.setInt(15, bean.getIsDemonstrationArea());
			ps.setInt(16, bean.getStatus());
			ps.setTimestamp(17, bean.getCreateTime());
			ps.setInt(18, bean.getCreateUser());
			ps.setString(19, bean.getMemo());
			
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

	public int updateGovernmentOrganizationInfo(GovernmentOrganizationInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update government_organization_info set organization_name = ?, training_year = ?, department = ?, responsible_person_name = ?, responsible_person_mobile = ?, operator_name = ?, operator_mobile = ?, fax = ?, organization_level = ?, province = ?, city = ?, region = ?, address = ?, zip_code = ?, is_demonstration_area = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where organization_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getOrganizationName());
			ps.setString(2, bean.getTrainingYear());
			ps.setString(3, bean.getDepartment());
			ps.setString(4, bean.getResponsiblePersonName());
			ps.setString(5, bean.getResponsiblePersonMobile());
			ps.setString(6, bean.getOperatorName());
			ps.setString(7, bean.getOperatorMobile());
			ps.setString(8, bean.getFax());
			ps.setString(9, bean.getOrganizationLevel());
			ps.setString(10, bean.getProvince());
			ps.setString(11, bean.getCity());
			ps.setString(12, bean.getRegion());
			ps.setString(13, bean.getAddress());
			ps.setString(14, bean.getZipCode());
			ps.setInt(15, bean.getIsDemonstrationArea());
			ps.setInt(16, bean.getStatus());
			ps.setInt(17, bean.getModifyUser());
			ps.setString(18, bean.getMemo());

			ps.setInt(19, bean.getOrganizationId());

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

	public GovernmentOrganizationInfoBean getGovernmentOrganizationInfoByKey(int organizationId) {
		GovernmentOrganizationInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from government_organization_info where organization_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, organizationId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new GovernmentOrganizationInfoBean();
				bean = BeanKit.resultSet2Bean(rs, GovernmentOrganizationInfoBean.class);
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

	public PageResultBean<GovernmentOrganizationInfoBean> getGovernmentOrganizationInfoPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<GovernmentOrganizationInfoBean> result = new PageResultBean<GovernmentOrganizationInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from government_organization_info";

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
				condList.add("city =  ? ");
				paramList.add(city);
			}
			String region = condMap.get("region");
			if (StringKit.isValid(region)) {
				condList.add("region = ?");
				paramList.add(region);
			}

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String organizationLevel = condMap.get("organizationLevel");
			if (StringKit.isValid(organizationLevel)) {
				condList.add("organization_level = ?");
				paramList.add(organizationLevel);
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

			String orderQuery = "order by organization_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList,
					GovernmentOrganizationInfoBean.class, pageBean, db);
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

	public List<GovernmentOrganizationInfoBean> getGovernmentOrganizationInfoListByMap(HashMap<String, String> condMap) {
		List<GovernmentOrganizationInfoBean> list = new ArrayList<GovernmentOrganizationInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from government_organization_info";
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
				condList.add("city =  ? ");
				paramList.add(city);
			}
			String region = condMap.get("region");
			if (StringKit.isValid(region)) {
				condList.add("region = ?");
				paramList.add(region);
			}

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String organizationLevel = condMap.get("organizationLevel");
			if (StringKit.isValid(organizationLevel)) {
				condList.add("organization_level = ?");
				paramList.add(organizationLevel);
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

			String orderQuery = "order by organization_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			list = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList,
					GovernmentOrganizationInfoBean.class, db);
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

	/**
	 * 查询对应地区 姓名的主管部门
	 * 
	 * @param bean
	 * @return
	 */
	public GovernmentOrganizationInfoBean getGovernmentOrganizationInfo(GovernmentOrganizationInfoBean bean) {
		GovernmentOrganizationInfoBean governmentOrganizationInfoBean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from government_organization_info where organization_name = ? and  province = ? and city = ? and region = ?   and status = ? and department = ? and training_year = ? and department = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getOrganizationName());
			ps.setString(2, bean.getProvince());
			ps.setString(3, bean.getCity());
			ps.setString(4, bean.getRegion());
			ps.setInt(5, Constants.STATUS_VALID); // 暂时设定为存在数据库的状态。
			ps.setString(6, bean.getDepartment());
			ps.setString(7, bean.getTrainingYear());
			ps.setString(8, bean.getDepartment());
			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				governmentOrganizationInfoBean = new GovernmentOrganizationInfoBean();
				governmentOrganizationInfoBean = BeanKit.resultSet2Bean(rs, GovernmentOrganizationInfoBean.class);
			}
			rs.close();
		} catch (Exception e) {
			log.error("", e);
			governmentOrganizationInfoBean = null;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return governmentOrganizationInfoBean;
	}
}
