package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.TeacherEvaluateBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TeacherEvaluateDao {
	private static Logger log = LoggerFactory.getLogger(TeacherEvaluateDao.class);

	public int insertTeacherEvaluate(TeacherEvaluateBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into teacher_evaluate ( teacher_id, class_name, training_organization, training_time, evaluate_score, training_address, teacher_comment, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getTeacherId());
			ps.setString(2, bean.getClassName());
			ps.setString(3, bean.getTrainingOrganization());
			ps.setString(4, bean.getTrainingTime());
			ps.setDouble(5, bean.getEvaluateScore());
			ps.setString(6, bean.getTrainingAddress());
			ps.setString(7, bean.getTeacherComment());
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

	public int insertTeacherEvaluateWithCreateTime(TeacherEvaluateBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into teacher_evaluate ( teacher_id, class_name, training_organization, training_time, evaluate_score, training_address, teacher_comment, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getTeacherId());
			ps.setString(2, bean.getClassName());
			ps.setString(3, bean.getTrainingOrganization());
			ps.setString(4, bean.getTrainingTime());
			ps.setDouble(5, bean.getEvaluateScore());
			ps.setString(6, bean.getTrainingAddress());
			ps.setString(7, bean.getTeacherComment());
			ps.setInt(8, bean.getStatus());
			ps.setTimestamp(9, bean.getCreateTime());
			ps.setInt(10, bean.getCreateUser());
			ps.setString(11, bean.getMemo());

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

	public int updateTeacherEvaluate(TeacherEvaluateBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update teacher_evaluate set teacher_id = ?, class_name = ?, training_organization = ?, training_time = ?, evaluate_score = ?, training_address = ?, teacher_comment = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where evaluate_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getTeacherId());
			ps.setString(2, bean.getClassName());
			ps.setString(3, bean.getTrainingOrganization());
			ps.setString(4, bean.getTrainingTime());
			ps.setDouble(5, bean.getEvaluateScore());
			ps.setString(6, bean.getTrainingAddress());
			ps.setString(7, bean.getTeacherComment());
			ps.setInt(8, bean.getStatus());
			ps.setInt(9, bean.getModifyUser());
			ps.setString(10, bean.getMemo());
			ps.setInt(11, bean.getEvaluateId());

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

	public TeacherEvaluateBean getTeacherEvaluateByKey(int evaluateId) {
		TeacherEvaluateBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_evaluate where evaluate_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, evaluateId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TeacherEvaluateBean();
				bean = BeanKit.resultSet2Bean(rs, TeacherEvaluateBean.class);
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

	public HashMap<Integer, Float> getTeacherEvaluateMap() {
		HashMap<Integer, Float> teacherEvaluateMap = new HashMap<Integer, Float>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select teacher_id,AVG(evaluate_score) from teacher_evaluate GROUP BY teacher_id";
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				teacherEvaluateMap.put(rs.getInt(1), rs.getFloat(2));
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
		return teacherEvaluateMap;
	}

	public PageResultBean<TeacherEvaluateBean> getTeacherEvaluatePageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<TeacherEvaluateBean> result = new PageResultBean<TeacherEvaluateBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_evaluate";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String teacherId = condMap.get("teacherId");
			if (StringKit.isValid(teacherId)) {
				condList.add("teacher_id = ?");
				paramList.add(teacherId);
			}

			// 课程名字
			String className = condMap.get("className");
			if (StringKit.isValid(className)) {
				condList.add("class_name = ?");
				paramList.add(className);
			}

			// 培训单位
			String trainingOrganization = condMap.get("trainingOrganization");
			if (StringKit.isValid(trainingOrganization)) {
				condList.add("training_organization = ?");
				paramList.add(trainingOrganization);
			}

			String orderQuery = "order by evaluate_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TeacherEvaluateBean.class, pageBean, db);
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

	public PageResultBean<TeacherEvaluateBean> getTeacherEvaluatePageListByRegion(String province, String city, String region, PageBean pageBean) {
		PageResultBean<TeacherEvaluateBean> result = new PageResultBean<TeacherEvaluateBean>();
		List<TeacherEvaluateBean> resultList = new ArrayList<TeacherEvaluateBean>();
		TeacherEvaluateBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String regionSql = "";
			if (StringKit.isValid(province)) {
				regionSql += "where province = '" + province + "'";
			}
			if (StringKit.isValid(city)) {
				regionSql += " and city = '" + city + "'";
			}
			if (StringKit.isValid(region)) {
				regionSql += " and region = '" + region + "'";
			}
			String sql = "SELECT * FROM teacher_evaluate WHERE teacher_id in (SELECT DISTINCT teacher_id FROM teacher_region_info " + regionSql
					+ " ) and status = 1  ORDER BY evaluate_score DESC LIMIT " + pageBean.getStartRecordNumber() + ","
					+ (pageBean.getEndRecordNumber() - pageBean.getStartRecordNumber());
			ResultSet rs = db.executeQuery(sql);
			while (rs.next()) {
				bean = BeanKit.resultSet2Bean(rs, TeacherEvaluateBean.class);
				resultList.add(bean);
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
			result.setRecordList(resultList);
			result.setTotalRecordNumber(resultList.size());
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return result;
	}

}
