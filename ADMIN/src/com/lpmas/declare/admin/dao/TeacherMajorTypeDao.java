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
import com.lpmas.declare.bean.TeacherMajorTypeBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TeacherMajorTypeDao {
	private static Logger log = LoggerFactory.getLogger(TeacherMajorTypeDao.class);

	public int insertTeacherMajorType(TeacherMajorTypeBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into teacher_major_type ( major_name, major_level, major_year, province, city, region, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getMajorName());
			ps.setString(2, bean.getMajorLevel());
			ps.setString(3, bean.getMajorYear());
			ps.setString(4, bean.getProvince());
			ps.setString(5, bean.getCity());
			ps.setString(6, bean.getRegion());
			ps.setInt(7, bean.getStatus());
			ps.setInt(8, bean.getCreateUser());
			ps.setString(9, bean.getMemo());

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

	public int updateTeacherMajorType(TeacherMajorTypeBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update teacher_major_type set major_name = ?, major_level = ?, major_year = ?, province = ?, city = ?, region = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where major_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getMajorName());
			ps.setString(2, bean.getMajorLevel());
			ps.setString(3, bean.getMajorYear());
			ps.setString(4, bean.getProvince());
			ps.setString(5, bean.getCity());
			ps.setString(6, bean.getRegion());
			ps.setInt(7, bean.getStatus());
			ps.setInt(8, bean.getModifyUser());
			ps.setString(9, bean.getMemo());

			ps.setInt(10, bean.getMajorId());

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

	public TeacherMajorTypeBean getTeacherMajorTypeByKey(int majorId) {
		TeacherMajorTypeBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_major_type where major_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, majorId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TeacherMajorTypeBean();
				bean = BeanKit.resultSet2Bean(rs, TeacherMajorTypeBean.class);
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

	public PageResultBean<TeacherMajorTypeBean> getTeacherMajorTypePageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<TeacherMajorTypeBean> result = new PageResultBean<TeacherMajorTypeBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_major_type";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String majorName = condMap.get("majorName");
			if (StringKit.isValid(majorName)) {
				condList.add("major_name like ? ");
				paramList.add("%" + majorName + "%");
			}

			String orderQuery = "order by major_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TeacherMajorTypeBean.class, pageBean, db);
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

	public List<TeacherMajorTypeBean> getTeacherMajorTypeListByMap(HashMap<String, String> condMap) {
		List<TeacherMajorTypeBean> result = new ArrayList<TeacherMajorTypeBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_major_type";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String memo = condMap.get("memo");
			if (StringKit.isValid(memo)) {
				condList.add("memo = ?");
				paramList.add(memo);
			}
			String majorName = condMap.get("majorName");
			if (StringKit.isValid(majorName)) {
				condList.add("major_name = ?");
				paramList.add(majorName);
			}
			String orderQuery = "order by major_id asc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TeacherMajorTypeBean.class, db);
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

	public TeacherMajorTypeBean getTeacherMajorTypeByNameAndStatus(TeacherMajorTypeBean TeacherMajorTypeBean) {
		TeacherMajorTypeBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_major_type where major_name = ? and status = ? and major_year = ? and province = ? and city = ? and region = ?  ;";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, TeacherMajorTypeBean.getMajorName());
			ps.setInt(2, Constants.STATUS_VALID);
			ps.setString(3, TeacherMajorTypeBean.getMajorYear());
			ps.setString(4, TeacherMajorTypeBean.getProvince());
			ps.setString(5, TeacherMajorTypeBean.getCity());
			ps.setString(6, TeacherMajorTypeBean.getRegion());
			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TeacherMajorTypeBean();
				bean = BeanKit.resultSet2Bean(rs, TeacherMajorTypeBean.class);
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

	public List<TeacherMajorTypeBean> getTeacherMajorTypeRepeatList() {
		List<TeacherMajorTypeBean> result = new ArrayList<TeacherMajorTypeBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_major_type";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();

			String orderQuery = "group by major_name having count(major_name) >1 ";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TeacherMajorTypeBean.class, db);
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

	public int deleteTeacherMajorType(int majorId) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "delete from teacher_major_type where major_id = ? ";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, majorId);
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
}
