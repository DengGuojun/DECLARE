package com.lpmas.declare.migrate.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.declare.migrate.bean.PersonInfoBean;
import com.lpmas.declare.migrate.bean.PersonInfoMajor2Bean;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class PersonInfoMajor2Dao {
	private static Logger log = LoggerFactory.getLogger(PersonInfoMajor2Dao.class);

	public int insertPersonInfoMajor2(PersonInfoMajor2Bean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into PersonInfo_Major2 ( user_id, fill_date, name, gender, birth_year, birth_month, nation, education_degree, major, politics_status, id_card_no, phone_no, email, qq_no, wechat, imgpath, famile_address, home_address, contact_address, work_kind, work_name, work_year, year_income, work_type, region_id, has_new_type_train_certificate, has_green_certificate, has_new_type_certificate, has_national_certificate, post_name_1, post_grade_1, post_name_2, post_grade_2, has_no_certificate, is_join_train, other_train, train_exp, type, state, remarks, create_time, upadte_time, year, checked) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getUserId());
			ps.setTimestamp(2, bean.getFillDate());
			ps.setString(3, bean.getName());
			ps.setInt(4, bean.getGender());
			ps.setInt(5, bean.getBirthYear());
			ps.setInt(6, bean.getBirthMonth());
			ps.setInt(7, bean.getNation());
			ps.setInt(8, bean.getEducationDegree());
			ps.setString(9, bean.getMajor());
			ps.setInt(10, bean.getPoliticsStatus());
			ps.setString(11, bean.getIdCardNo());
			ps.setString(12, bean.getPhoneNo());
			ps.setString(13, bean.getEmail());
			ps.setString(14, bean.getQqNo());
			ps.setString(15, bean.getWechat());
			ps.setString(16, bean.getImgpath());
			ps.setInt(17, bean.getFamileAddress());
			ps.setString(18, bean.getHomeAddress());
			ps.setString(19, bean.getContactAddress());
			ps.setInt(20, bean.getWorkKind());
			ps.setInt(21, bean.getWorkName());
			ps.setString(22, bean.getWorkYear());
			ps.setString(23, bean.getYearIncome());
			ps.setInt(24, bean.getWorkType());
			ps.setString(25, bean.getRegionId());
			ps.setInt(26, bean.getHasNewTypeTrainCertificate());
			ps.setInt(27, bean.getHasGreenCertificate());
			ps.setInt(28, bean.getHasNewTypeCertificate());
			ps.setInt(29, bean.getHasNationalCertificate());
			ps.setString(30, bean.getPostName1());
			ps.setString(31, bean.getPostGrade1());
			ps.setString(32, bean.getPostName2());
			ps.setString(33, bean.getPostGrade2());
			ps.setInt(34, bean.getHasNoCertificate());
			ps.setInt(35, bean.getIsJoinTrain());
			ps.setInt(36, bean.getOtherTrain());
			ps.setString(37, bean.getTrainExp());
			ps.setInt(38, bean.getType());
			ps.setInt(39, bean.getState());
			ps.setString(40, bean.getRemarks());
			ps.setTimestamp(41, bean.getUpadteTime());
			ps.setInt(42, bean.getYear());
			ps.setInt(43, bean.getChecked());

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

	public int updatePersonInfoMajor2(PersonInfoMajor2Bean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update PersonInfo_Major2 set user_id = ?, fill_date = ?, name = ?, gender = ?, birth_year = ?, birth_month = ?, nation = ?, education_degree = ?, major = ?, politics_status = ?, id_card_no = ?, phone_no = ?, email = ?, qq_no = ?, wechat = ?, imgpath = ?, famile_address = ?, home_address = ?, contact_address = ?, work_kind = ?, work_name = ?, work_year = ?, year_income = ?, work_type = ?, region_id = ?, has_new_type_train_certificate = ?, has_green_certificate = ?, has_new_type_certificate = ?, has_national_certificate = ?, post_name_1 = ?, post_grade_1 = ?, post_name_2 = ?, post_grade_2 = ?, has_no_certificate = ?, is_join_train = ?, other_train = ?, train_exp = ?, type = ?, state = ?, remarks = ?, upadte_time = ?, year = ?, checked = ? where id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getUserId());
			ps.setTimestamp(2, bean.getFillDate());
			ps.setString(3, bean.getName());
			ps.setInt(4, bean.getGender());
			ps.setInt(5, bean.getBirthYear());
			ps.setInt(6, bean.getBirthMonth());
			ps.setInt(7, bean.getNation());
			ps.setInt(8, bean.getEducationDegree());
			ps.setString(9, bean.getMajor());
			ps.setInt(10, bean.getPoliticsStatus());
			ps.setString(11, bean.getIdCardNo());
			ps.setString(12, bean.getPhoneNo());
			ps.setString(13, bean.getEmail());
			ps.setString(14, bean.getQqNo());
			ps.setString(15, bean.getWechat());
			ps.setString(16, bean.getImgpath());
			ps.setInt(17, bean.getFamileAddress());
			ps.setString(18, bean.getHomeAddress());
			ps.setString(19, bean.getContactAddress());
			ps.setInt(20, bean.getWorkKind());
			ps.setInt(21, bean.getWorkName());
			ps.setString(22, bean.getWorkYear());
			ps.setString(23, bean.getYearIncome());
			ps.setInt(24, bean.getWorkType());
			ps.setString(25, bean.getRegionId());
			ps.setInt(26, bean.getHasNewTypeTrainCertificate());
			ps.setInt(27, bean.getHasGreenCertificate());
			ps.setInt(28, bean.getHasNewTypeCertificate());
			ps.setInt(29, bean.getHasNationalCertificate());
			ps.setString(30, bean.getPostName1());
			ps.setString(31, bean.getPostGrade1());
			ps.setString(32, bean.getPostName2());
			ps.setString(33, bean.getPostGrade2());
			ps.setInt(34, bean.getHasNoCertificate());
			ps.setInt(35, bean.getIsJoinTrain());
			ps.setInt(36, bean.getOtherTrain());
			ps.setString(37, bean.getTrainExp());
			ps.setInt(38, bean.getType());
			ps.setInt(39, bean.getState());
			ps.setString(40, bean.getRemarks());
			ps.setTimestamp(41, bean.getUpadteTime());
			ps.setInt(42, bean.getYear());
			ps.setInt(43, bean.getChecked());

			ps.setString(44, bean.getId());

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

	public PersonInfoMajor2Bean getPersonInfoMajor2ByKey(String id) {
		PersonInfoMajor2Bean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from PersonInfo_Major2 where id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, id);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new PersonInfoMajor2Bean();
				bean = BeanKit.resultSet2Bean(rs, PersonInfoMajor2Bean.class);
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

	public PersonInfoMajor2Bean getPersonInfoMajor2BeanByInfoBean(PersonInfoBean personInfoBean) {
		PersonInfoMajor2Bean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from PersonInfo_Major2 where user_id = ? and name = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, personInfoBean.getId());
			ps.setString(2, personInfoBean.getName());

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new PersonInfoMajor2Bean();
				bean = BeanKit.resultSet2Bean(rs, PersonInfoMajor2Bean.class);
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

	public PageResultBean<PersonInfoMajor2Bean> getPersonInfoMajor2PageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<PersonInfoMajor2Bean> result = new PageResultBean<PersonInfoMajor2Bean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from PersonInfo_Major2";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String imgpath = condMap.get("imgpath");
			if (StringKit.isValid(imgpath)) {
				condList.add("imgpath like ?");
				paramList.add("%" + imgpath + "%");
			}
			String userId = condMap.get("userId");
			if (StringKit.isValid(userId)) {
				condList.add("user_Id = ?");
				paramList.add(userId);
			}
			String name = condMap.get("name");
			if (StringKit.isValid(name)) {
				condList.add("name like ?");
				paramList.add("%" + name + "%");
			}
			String orderQuery = "order by id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, PersonInfoMajor2Bean.class,
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

}
