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

import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TeacherInfoDao {
	private static Logger log = LoggerFactory.getLogger(TeacherInfoDao.class);

	public int insertTeacherInfo(TeacherInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into teacher_info ( teacher_name, teacher_number, teacher_type, teacher_image, teacher_gender, teacher_birthday, province, city, region, identity_number, company, technical_title, technical_grade, major_type_id, major_id, courses_offer, phone, email, relative_material, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getTeacherName());
			ps.setString(2, bean.getTeacherNumber());
			ps.setString(3, bean.getTeacherType());
			ps.setString(4, bean.getTeacherImage());
			ps.setInt(5, bean.getTeacherGender());
			ps.setDate(6, bean.getTeacherBirthday());
			ps.setString(7, bean.getProvince());
			ps.setString(8, bean.getCity());
			ps.setString(9, bean.getRegion());
			ps.setString(10, bean.getIdentityNumber());
			ps.setString(11, bean.getCompany());
			ps.setString(12, bean.getTechnicalTitle());
			ps.setString(13, bean.getTechnicalGrade());
			ps.setInt(14, bean.getMajorTypeId());
			ps.setInt(15, bean.getMajorId());
			ps.setString(16, bean.getCoursesOffer());
			ps.setString(17, bean.getPhone());
			ps.setString(18, bean.getEmail());
			ps.setString(19, bean.getRelativeMaterial());
			ps.setInt(20, bean.getStatus());
			ps.setInt(21, bean.getCreateUser());
			ps.setString(22, bean.getMemo());

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

	public int updateTeacherInfo(TeacherInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update teacher_info set teacher_name = ?, teacher_number = ?, teacher_type = ?, teacher_image = ?, teacher_gender = ?, teacher_birthday = ?, province = ?, city = ?, region = ?, identity_number = ?, company = ?, technical_title = ?, technical_grade = ?, major_type_id = ?, major_id = ?, courses_offer = ?, phone = ?, email = ?, relative_material = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where teacher_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getTeacherName());
			ps.setString(2, bean.getTeacherNumber());
			ps.setString(3, bean.getTeacherType());
			ps.setString(4, bean.getTeacherImage());
			ps.setInt(5, bean.getTeacherGender());
			ps.setDate(6, bean.getTeacherBirthday());
			ps.setString(7, bean.getProvince());
			ps.setString(8, bean.getCity());
			ps.setString(9, bean.getRegion());
			ps.setString(10, bean.getIdentityNumber());
			ps.setString(11, bean.getCompany());
			ps.setString(12, bean.getTechnicalTitle());
			ps.setString(13, bean.getTechnicalGrade());
			ps.setInt(14, bean.getMajorTypeId());
			ps.setInt(15, bean.getMajorId());
			ps.setString(16, bean.getCoursesOffer());
			ps.setString(17, bean.getPhone());
			ps.setString(18, bean.getEmail());
			ps.setString(19, bean.getRelativeMaterial());
			ps.setInt(20, bean.getStatus());
			ps.setInt(21, bean.getModifyUser());
			ps.setString(22, bean.getMemo());

			ps.setInt(23, bean.getTeacherId());

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

	public int insertTeacherInfoWithCreateTime(TeacherInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into teacher_info ( teacher_name, teacher_number, teacher_type, teacher_image, teacher_gender, teacher_birthday, identity_number, company, technical_title, technical_grade, major_type_id, major_id, courses_offer, phone, email, relative_material, status, create_time, create_user, memo, province, city, region) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getTeacherName());
			ps.setString(2, bean.getTeacherNumber());
			ps.setString(3, bean.getTeacherType());
			ps.setString(4, bean.getTeacherImage());
			ps.setInt(5, bean.getTeacherGender());
			ps.setDate(6, bean.getTeacherBirthday());
			ps.setString(7, bean.getIdentityNumber());
			ps.setString(8, bean.getCompany());
			ps.setString(9, bean.getTechnicalTitle());
			ps.setString(10, bean.getTechnicalGrade());
			ps.setInt(11, bean.getMajorTypeId());
			ps.setInt(12, bean.getMajorId());
			ps.setString(13, bean.getCoursesOffer());
			ps.setString(14, bean.getPhone());
			ps.setString(15, bean.getEmail());
			ps.setString(16, bean.getRelativeMaterial());
			ps.setInt(17, bean.getStatus());
			ps.setTimestamp(18, bean.getCreateTime());
			ps.setInt(19, bean.getCreateUser());
			ps.setString(20, bean.getMemo());
			ps.setString(21, bean.getProvince());
			ps.setString(22, bean.getCity());
			ps.setString(23, bean.getRegion());

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

	public TeacherInfoBean getTeacherInfoByKey(int teacherId) {
		TeacherInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_info where teacher_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, teacherId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TeacherInfoBean();
				bean = BeanKit.resultSet2Bean(rs, TeacherInfoBean.class);
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

	public PageResultBean<TeacherInfoBean> getTeacherInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<TeacherInfoBean> result = new PageResultBean<TeacherInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;

		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_info";

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

			String teacherName = condMap.get("teacherName");
			if (StringKit.isValid(teacherName)) {
				condList.add("teacher_name like ?");
				paramList.add("%" + teacherName + "%");
			}
			// 身份证号码
			String identityNumber = condMap.get("identityNumber");
			if (StringKit.isValid(identityNumber)) {
				condList.add("identity_number = ?");
				paramList.add(identityNumber);
			}

			// 教师类型
			String teacherType = condMap.get("teacherType");
			if (StringKit.isValid(teacherType)) {
				condList.add("teacher_type = ?");
				paramList.add(teacherType);
			}

			// 专业类型
			String majorTypeId = condMap.get("majorTypeId");
			if (StringKit.isValid(majorTypeId)) {
				condList.add("major_type_id = ?");
				paramList.add(majorTypeId);
			}

			// 专业
			String majorId = condMap.get("majorId");
			if (StringKit.isValid(majorId)) {
				condList.add("major_id = ?");
				paramList.add(majorId);
			}

			// 主授课程
			String coursesOffer = condMap.get("coursesOffer");
			if (StringKit.isValid(coursesOffer)) {
				condList.add("courses_offer = ?");
				paramList.add(coursesOffer);
			}
			// 主授课程
			String phone = condMap.get("phone");
			if (StringKit.isValid(phone)) {
				condList.add("phone = ?");
				paramList.add(phone);
			}
			String orderQuery = "order by teacher_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor
					.getPageResult(sql, orderQuery, condList, paramList, TeacherInfoBean.class, pageBean, db);
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

	public PageResultBean<TeacherInfoBean> getTeacherRegionInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<TeacherInfoBean> result = new PageResultBean<TeacherInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;

		try {
			db = dbFactory.getDBObjectR();
			String sql = "SELECT teacher_info.teacher_id,teacher_name,teacher_number,teacher_type,teacher_image,teacher_gender,teacher_birthday,identity_number,company,technical_title,technical_grade,major_type_id,major_id,courses_offer,phone,email,relative_material,teacher_info.province,teacher_info.city,teacher_info.region"
					+ " FROM teacher_info,teacher_region_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("teacher_info.status = ?");
				paramList.add(status);
			}

			String teacherName = condMap.get("teacherName");
			if (StringKit.isValid(teacherName)) {
				condList.add("teacher_name like ?");
				paramList.add("%" + teacherName + "%");
			}
			// 身份证号码
			String identityNumber = condMap.get("identityNumber");
			if (StringKit.isValid(identityNumber)) {
				condList.add("identity_number = ?");
				paramList.add(identityNumber);
			}

			// 教师类型
			String teacherType = condMap.get("teacherType");
			if (StringKit.isValid(teacherType)) {
				condList.add("teacher_type = ?");
				paramList.add(teacherType);
			}

			// 专业
			String majorTypeId = condMap.get("majorTypeId");
			if (StringKit.isValid(majorTypeId)) {
				condList.add("major_type_id = ?");
				paramList.add(majorTypeId);
			}

			// 专业
			String majorId = condMap.get("majorId");
			if (StringKit.isValid(majorId)) {
				condList.add("major_id = ?");
				paramList.add(majorId);
			}

			// 主授课程
			String coursesOffer = condMap.get("coursesOffer");
			if (StringKit.isValid(coursesOffer)) {
				condList.add("courses_offer = ?");
				paramList.add(coursesOffer);
			}
			// 主授课程
			String phone = condMap.get("phone");
			if (StringKit.isValid(phone)) {
				condList.add("phone = ?");
				paramList.add(phone);
			}

			String province = condMap.get("province");
			if (StringKit.isValid(province)) {
				condList.add("teacher_region_info.province = ?");
				paramList.add(province);
			}
			String city = condMap.get("city");
			if (StringKit.isValid(city)) {
				condList.add("teacher_region_info.city = ?");
				paramList.add(city);
			}
			String region = condMap.get("region");
			if (StringKit.isValid(region)) {
				condList.add("teacher_region_info.region = ?");
				paramList.add(region);
			}
			String country = condMap.get("country");
			if (StringKit.isValid(country)) {
				condList.add("teacher_region_info.country = ?");
				paramList.add(country);
			}
			String level = condMap.get("level");
			if (StringKit.isValid(level)) {
				condList.add("teacher_region_info.level = ?");
				paramList.add(level);
			}

			condList.add("teacher_info.teacher_id = teacher_region_info.teacher_id");

			String orderQuery = "order by teacher_info.teacher_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor
					.getPageResult(sql, orderQuery, condList, paramList, TeacherInfoBean.class, pageBean, db);

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
	
	public List<TeacherInfoBean> getTeacherRegionInfoListByMap(HashMap<String, String> condMap) {
		List<TeacherInfoBean> result = new ArrayList<TeacherInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;

		try {
			db = dbFactory.getDBObjectR();
			String sql = "SELECT teacher_info.teacher_id,teacher_name,teacher_number,teacher_type,teacher_image,teacher_gender,teacher_birthday,identity_number,company,technical_title,technical_grade,major_type_id,major_id,courses_offer,phone,email,relative_material,teacher_info.province,teacher_info.city,teacher_info.region"
					+ " FROM teacher_info,teacher_region_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("teacher_info.status = ?");
				paramList.add(status);
			}
			String province = condMap.get("province");
			if (StringKit.isValid(province)) {
				condList.add("teacher_region_info.province = ?");
				paramList.add(province);
			}
			String city = condMap.get("city");
			if (StringKit.isValid(city)) {
				condList.add("teacher_region_info.city = ?");
				paramList.add(city);
			}
			String region = condMap.get("region");
			if (StringKit.isValid(region)) {
				condList.add("teacher_region_info.region = ?");
				paramList.add(region);
			}
			String country = condMap.get("country");
			if (StringKit.isValid(country)) {
				condList.add("teacher_region_info.country = ?");
				paramList.add(country);
			}
			String level = condMap.get("level");
			if (StringKit.isValid(level)) {
				condList.add("teacher_region_info.level = ?");
				paramList.add(level);
			}

			condList.add("teacher_info.teacher_id = teacher_region_info.teacher_id");

			String orderQuery = "order by teacher_info.teacher_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TeacherInfoBean.class, db);

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

	public int getTeacherRegionCountByMap(Map<String, String> condMap) {
		int result = 0;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;

		try {
			db = dbFactory.getDBObjectR();
			String sql = "SELECT 1 FROM teacher_info,teacher_region_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("teacher_info.status = ?");
				paramList.add(status);
			}

			String province = condMap.get("province");
			if (StringKit.isValid(province)) {
				condList.add("teacher_region_info.province = ?");
				paramList.add(province);
			}
			String city = condMap.get("city");
			if (StringKit.isValid(city)) {
				condList.add("teacher_region_info.city = ?");
				paramList.add(city);
			}
			String region = condMap.get("region");
			if (StringKit.isValid(region)) {
				condList.add("teacher_region_info.region = ?");
				paramList.add(region);
			}
			String level = condMap.get("level");
			if (StringKit.isValid(level)) {
				condList.add("teacher_region_info.level = ?");
				paramList.add(level);
			}
			String beginTime = condMap.get("beginTime");
			if (StringKit.isValid(beginTime)) {
				condList.add("teacher_info.create_time >= ?");
				paramList.add(beginTime);
			}
			String endTime = condMap.get("endTime");
			if (StringKit.isValid(endTime)) {
				condList.add("teacher_info.create_time <= ?");
				paramList.add(endTime);
			}
			condList.add("teacher_info.teacher_id = teacher_region_info.teacher_id");

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

	public TeacherInfoBean getTeacherInfoByNameAndStatus(int status, String identityNumber) {
		TeacherInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_info where  status = ? and identity_number = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, identityNumber);
			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TeacherInfoBean();
				bean = BeanKit.resultSet2Bean(rs, TeacherInfoBean.class);
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

	public List<TeacherInfoBean> getTeacherInfoListByMap(HashMap<String, String> condMap) {
		List<TeacherInfoBean> result = new ArrayList<TeacherInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from teacher_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
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
			String memo = condMap.get("memo");
			if (StringKit.isValid(memo)) {
				condList.add("memo = ?");
				paramList.add(memo);
			}
			String orderQuery = "order by teacher_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, TeacherInfoBean.class, db);
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
