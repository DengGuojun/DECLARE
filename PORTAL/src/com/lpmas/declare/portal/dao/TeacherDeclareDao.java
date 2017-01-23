package com.lpmas.declare.portal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.TeacherDeclareBean;
import com.lpmas.declare.portal.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TeacherDeclareDao {
	private static Logger log = LoggerFactory.getLogger(TeacherDeclareDao.class);

	public int insertTeacherDeclare(TeacherDeclareBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into teacher_declare ( user_id, teacher_id, teacher_name, teacher_number, teacher_type, teacher_image, teacher_gender, teacher_birthday, province, city, region, identity_number, company, technical_title, technical_grade, major_type_id, major_id, courses_offer, phone, email, relative_material, declare_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getUserId());
			ps.setInt(2, bean.getTeacherId());
			ps.setString(3, bean.getTeacherName());
			ps.setString(4, bean.getTeacherNumber());
			ps.setString(5, bean.getTeacherType());
			ps.setString(6, bean.getTeacherImage());
			ps.setInt(7, bean.getTeacherGender());
			ps.setDate(8, bean.getTeacherBirthday());
			ps.setString(9, bean.getProvince());
			ps.setString(10, bean.getCity());
			ps.setString(11, bean.getRegion());
			ps.setString(12, bean.getIdentityNumber());
			ps.setString(13, bean.getCompany());
			ps.setString(14, bean.getTechnicalTitle());
			ps.setString(15, bean.getTechnicalGrade());
			ps.setInt(16, bean.getMajorTypeId());
			ps.setInt(17, bean.getMajorId());
			ps.setString(18, bean.getCoursesOffer());
			ps.setString(19, bean.getPhone());
			ps.setString(20, bean.getEmail());
			ps.setString(21, bean.getRelativeMaterial());
			ps.setString(22, bean.getDeclareStatus());
			ps.setInt(23, bean.getStatus());
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

	public int updateTeacherDeclare(TeacherDeclareBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update teacher_declare set user_id = ?, teacher_id = ?, teacher_name = ?, teacher_number = ?, teacher_type = ?, teacher_image = ?, teacher_gender = ?, teacher_birthday = ?, province = ?, city = ?, region = ?, identity_number = ?, company = ?, technical_title = ?, technical_grade = ?, major_type_id = ?, major_id = ?, courses_offer = ?, phone = ?, email = ?, relative_material = ?, declare_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where declare_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getUserId());
			ps.setInt(2, bean.getTeacherId());
			ps.setString(3, bean.getTeacherName());
			ps.setString(4, bean.getTeacherNumber());
			ps.setString(5, bean.getTeacherType());
			ps.setString(6, bean.getTeacherImage());
			ps.setInt(7, bean.getTeacherGender());
			ps.setDate(8, bean.getTeacherBirthday());
			ps.setString(9, bean.getProvince());
			ps.setString(10, bean.getCity());
			ps.setString(11, bean.getRegion());
			ps.setString(12, bean.getIdentityNumber());
			ps.setString(13, bean.getCompany());
			ps.setString(14, bean.getTechnicalTitle());
			ps.setString(15, bean.getTechnicalGrade());
			ps.setInt(16, bean.getMajorTypeId());
			ps.setInt(17, bean.getMajorId());
			ps.setString(18, bean.getCoursesOffer());
			ps.setString(19, bean.getPhone());
			ps.setString(20, bean.getEmail());
			ps.setString(21, bean.getRelativeMaterial());
			ps.setString(22, bean.getDeclareStatus());
			ps.setInt(23, bean.getStatus());
			ps.setInt(24, bean.getModifyUser());
			ps.setString(25, bean.getMemo());

			ps.setInt(26, bean.getDeclareId());

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

	public TeacherDeclareBean getTeacherDeclareByKey(int teacherId) {
		TeacherDeclareBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_declare where teacher_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, teacherId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TeacherDeclareBean();
				bean = BeanKit.resultSet2Bean(rs, TeacherDeclareBean.class);
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

	public TeacherDeclareBean getTeacherDeclareByUserId(int userId) {
		TeacherDeclareBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_declare where user_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TeacherDeclareBean();
				bean = BeanKit.resultSet2Bean(rs, TeacherDeclareBean.class);
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

	public PageResultBean<TeacherDeclareBean> getTeacherDeclarePageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<TeacherDeclareBean> result = new PageResultBean<TeacherDeclareBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_declare";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by teacher_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TeacherDeclareBean.class, pageBean,
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

}
