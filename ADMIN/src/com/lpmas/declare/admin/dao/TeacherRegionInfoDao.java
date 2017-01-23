package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.TeacherRegionInfoBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TeacherRegionInfoDao {
	private static Logger log = LoggerFactory.getLogger(TeacherRegionInfoDao.class);

	public int insertTeacherRegionInfo(TeacherRegionInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into teacher_region_info ( teacher_id, level, province, city, region) value( ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getTeacherId());
			ps.setString(2, bean.getLevel());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setString(5, bean.getRegion());

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

	public int updateTeacherRegionInfo(TeacherRegionInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update teacher_region_info set teacher_id = ?, level = ?, province = ?, city = ?, region = ? where info_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getTeacherId());
			ps.setString(2, bean.getLevel());
			ps.setString(3, bean.getProvince());
			ps.setString(4, bean.getCity());
			ps.setString(5, bean.getRegion());

			ps.setInt(6, bean.getInfoId());

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

	public TeacherRegionInfoBean getTeacherRegionInfoByKey(int teacherId, String level, String province, String city,
			String region) {
		TeacherRegionInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_region_info where teacher_id = ? and level = ? and province = ? and city = ? and region = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, teacherId);
			ps.setString(2, level);
			ps.setString(3, province);
			ps.setString(4, city);
			ps.setString(5, region);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TeacherRegionInfoBean();
				bean = BeanKit.resultSet2Bean(rs, TeacherRegionInfoBean.class);
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

	public List<TeacherRegionInfoBean> getTeacherRegionInfoPageListByMap(Map<String, String> condMap) {
		List<TeacherRegionInfoBean> result = new ArrayList<TeacherRegionInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_region_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String country = condMap.get("country");
			if (StringKit.isValid(country)) {
				condList.add("country = ?");
				paramList.add(country);
			}

			String teacherId = condMap.get("teacherId");
			if (StringKit.isValid(teacherId)) {
				condList.add("teacher_id = ?");
				paramList.add(teacherId);
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
				condList.add("level = ?");
				paramList.add(level);
			}

			String orderQuery = "order by info_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();

			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TeacherRegionInfoBean.class,
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

	public int removeTeacherRegionInfo(int infoId) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "delete from teacher_region_info where info_id = ? ";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, infoId);
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

	public int getTeacherRegionInfoRecordResultByMap(Map<String, String> condMap) {
		int result = 0;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_region_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String country = condMap.get("country");
			if (StringKit.isValid(country)) {
				condList.add("country = ?");
				paramList.add(country);
			}

			String teacherId = condMap.get("teacherId");
			if (StringKit.isValid(teacherId)) {
				condList.add("teacher_id = ?");
				paramList.add(teacherId);
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
				condList.add("level = ?");
				paramList.add(level);
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
