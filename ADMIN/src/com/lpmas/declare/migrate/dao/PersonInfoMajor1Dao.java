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
import com.lpmas.declare.migrate.bean.PersonInfoMajor1Bean;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class PersonInfoMajor1Dao {
	private static Logger log = LoggerFactory.getLogger(PersonInfoMajor1Dao.class);

	public int insertPersonInfoMajor1(PersonInfoMajor1Bean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into PersonInfo_Major1 ( user_id, fill_date, name, gender, birth_year, birth_month, nation, education_degree, major, politic_status, id_card_no, phone_no, email, qq_no, wechat, imgpath, family_num, home_address, contact_address, person_type, is_join_train, other_train, train_exp, has_tech_host, tech_host, has_national_certificate, national_certificate, certificate_grade, has_new_type_train_cetificate, has_green_cetificate, has_new_type_cetificate, has_no_cetificate, region_id, home_work_num, effect_fram_num, area_type, economy_type, main_industry_1, main_scale_1, scale_unit_1, work_year_1, main_industry_2, main_scale_2, scale_unit_2, work_year_2, main_industry_3, main_scale_3, scale_unit_3, work_year_3, income_last_year, total_income, type, state, remark, create_time, update_time, year, checked, apply_type, indentity_grade, indentity_time, identity_department, register, model_farm, farm_grade, farm_type, land_scale, work_address, post_type, post_name, work_year, service_scale, post_income) value( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
			ps.setInt(10, bean.getPoliticStatus());
			ps.setString(11, bean.getIdCardNo());
			ps.setString(12, bean.getPhoneNo());
			ps.setString(13, bean.getEmail());
			ps.setString(14, bean.getQqNo());
			ps.setString(15, bean.getWechat());
			ps.setString(16, bean.getImgpath());
			ps.setInt(17, bean.getFamilyNum());
			ps.setString(18, bean.getHomeAddress());
			ps.setString(19, bean.getContactAddress());
			ps.setInt(20, bean.getPersonType());
			ps.setInt(21, bean.getIsJoinTrain());
			ps.setInt(22, bean.getOtherTrain());
			ps.setString(23, bean.getTrainExp());
			ps.setInt(24, bean.getHasTechHost());
			ps.setString(25, bean.getTechHost());
			ps.setInt(26, bean.getHasNationalCertificate());
			ps.setString(27, bean.getNationalCertificate());
			ps.setString(28, bean.getCertificateGrade());
			ps.setInt(29, bean.getHasNewTypeTrainCetificate());
			ps.setInt(30, bean.getHasGreenCetificate());
			ps.setInt(31, bean.getHasNewTypeCetificate());
			ps.setInt(32, bean.getHasNoCetificate());
			ps.setString(33, bean.getRegionId());
			ps.setInt(34, bean.getHomeWorkNum());
			ps.setInt(35, bean.getEffectFramNum());
			ps.setInt(36, bean.getAreaType());
			ps.setInt(37, bean.getEconomyType());
			ps.setString(38, bean.getMainIndustry1());
			ps.setString(39, bean.getMainScale1());
			ps.setInt(40, bean.getScaleUnit1());
			ps.setString(41, bean.getWorkYear1());
			ps.setString(42, bean.getMainIndustry2());
			ps.setString(43, bean.getMainScale2());
			ps.setInt(44, bean.getScaleUnit2());
			ps.setString(45, bean.getWorkYear2());
			ps.setString(46, bean.getMainIndustry3());
			ps.setString(47, bean.getMainScale3());
			ps.setInt(48, bean.getScaleUnit3());
			ps.setString(49, bean.getWorkYear3());
			ps.setString(50, bean.getIncomeLastYear());
			ps.setString(51, bean.getTotalIncome());
			ps.setInt(52, bean.getType());
			ps.setInt(53, bean.getState());
			ps.setString(54, bean.getRemark());
			ps.setTimestamp(55, bean.getUpdateTime());
			ps.setInt(56, bean.getYear());
			ps.setInt(57, bean.getChecked());
			ps.setInt(58, bean.getApplyType());
			ps.setString(59, bean.getIndentityGrade());
			ps.setString(60, bean.getIndentityTime());
			ps.setString(61, bean.getIdentityDepartment());
			ps.setInt(62, bean.getRegister());
			ps.setInt(63, bean.getModelFarm());
			ps.setInt(64, bean.getFarmGrade());
			ps.setInt(65, bean.getFarmType());
			ps.setString(66, bean.getLandScale());
			ps.setString(67, bean.getWorkAddress());
			ps.setString(68, bean.getPostType());
			ps.setString(69, bean.getPostName());
			ps.setString(70, bean.getWorkYear());
			ps.setString(71, bean.getServiceScale());
			ps.setString(72, bean.getPostIncome());

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

	public int updatePersonInfoMajor1(PersonInfoMajor1Bean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update PersonInfo_Major1 set user_id = ?, fill_date = ?, name = ?, gender = ?, birth_year = ?, birth_month = ?, nation = ?, education_degree = ?, major = ?, politic_status = ?, id_card_no = ?, phone_no = ?, email = ?, qq_no = ?, wechat = ?, imgpath = ?, family_num = ?, home_address = ?, contact_address = ?, person_type = ?, is_join_train = ?, other_train = ?, train_exp = ?, has_tech_host = ?, tech_host = ?, has_national_certificate = ?, national_certificate = ?, certificate_grade = ?, has_new_type_train_cetificate = ?, has_green_cetificate = ?, has_new_type_cetificate = ?, has_no_cetificate = ?, region_id = ?, home_work_num = ?, effect_fram_num = ?, area_type = ?, economy_type = ?, main_industry_1 = ?, main_scale_1 = ?, scale_unit_1 = ?, work_year_1 = ?, main_industry_2 = ?, main_scale_2 = ?, scale_unit_2 = ?, work_year_2 = ?, main_industry_3 = ?, main_scale_3 = ?, scale_unit_3 = ?, work_year_3 = ?, income_last_year = ?, total_income = ?, type = ?, state = ?, remark = ?, update_time = ?, year = ?, checked = ?, apply_type = ?, indentity_grade = ?, indentity_time = ?, identity_department = ?, register = ?, model_farm = ?, farm_grade = ?, farm_type = ?, land_scale = ?, work_address = ?, post_type = ?, post_name = ?, work_year = ?, service_scale = ?, post_income = ? where id = ?";
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
			ps.setInt(10, bean.getPoliticStatus());
			ps.setString(11, bean.getIdCardNo());
			ps.setString(12, bean.getPhoneNo());
			ps.setString(13, bean.getEmail());
			ps.setString(14, bean.getQqNo());
			ps.setString(15, bean.getWechat());
			ps.setString(16, bean.getImgpath());
			ps.setInt(17, bean.getFamilyNum());
			ps.setString(18, bean.getHomeAddress());
			ps.setString(19, bean.getContactAddress());
			ps.setInt(20, bean.getPersonType());
			ps.setInt(21, bean.getIsJoinTrain());
			ps.setInt(22, bean.getOtherTrain());
			ps.setString(23, bean.getTrainExp());
			ps.setInt(24, bean.getHasTechHost());
			ps.setString(25, bean.getTechHost());
			ps.setInt(26, bean.getHasNationalCertificate());
			ps.setString(27, bean.getNationalCertificate());
			ps.setString(28, bean.getCertificateGrade());
			ps.setInt(29, bean.getHasNewTypeTrainCetificate());
			ps.setInt(30, bean.getHasGreenCetificate());
			ps.setInt(31, bean.getHasNewTypeCetificate());
			ps.setInt(32, bean.getHasNoCetificate());
			ps.setString(33, bean.getRegionId());
			ps.setInt(34, bean.getHomeWorkNum());
			ps.setInt(35, bean.getEffectFramNum());
			ps.setInt(36, bean.getAreaType());
			ps.setInt(37, bean.getEconomyType());
			ps.setString(38, bean.getMainIndustry1());
			ps.setString(39, bean.getMainScale1());
			ps.setInt(40, bean.getScaleUnit1());
			ps.setString(41, bean.getWorkYear1());
			ps.setString(42, bean.getMainIndustry2());
			ps.setString(43, bean.getMainScale2());
			ps.setInt(44, bean.getScaleUnit2());
			ps.setString(45, bean.getWorkYear2());
			ps.setString(46, bean.getMainIndustry3());
			ps.setString(47, bean.getMainScale3());
			ps.setInt(48, bean.getScaleUnit3());
			ps.setString(49, bean.getWorkYear3());
			ps.setString(50, bean.getIncomeLastYear());
			ps.setString(51, bean.getTotalIncome());
			ps.setInt(52, bean.getType());
			ps.setInt(53, bean.getState());
			ps.setString(54, bean.getRemark());
			ps.setTimestamp(55, bean.getUpdateTime());
			ps.setInt(56, bean.getYear());
			ps.setInt(57, bean.getChecked());
			ps.setInt(58, bean.getApplyType());
			ps.setString(59, bean.getIndentityGrade());
			ps.setString(60, bean.getIndentityTime());
			ps.setString(61, bean.getIdentityDepartment());
			ps.setInt(62, bean.getRegister());
			ps.setInt(63, bean.getModelFarm());
			ps.setInt(64, bean.getFarmGrade());
			ps.setInt(65, bean.getFarmType());
			ps.setString(66, bean.getLandScale());
			ps.setString(67, bean.getWorkAddress());
			ps.setString(68, bean.getPostType());
			ps.setString(69, bean.getPostName());
			ps.setString(70, bean.getWorkYear());
			ps.setString(71, bean.getServiceScale());
			ps.setString(72, bean.getPostIncome());

			ps.setString(73, bean.getId());

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

	public PersonInfoMajor1Bean getPersonInfoMajor1ByKey(String id) {
		PersonInfoMajor1Bean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from PersonInfo_Major1 where id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, id);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new PersonInfoMajor1Bean();
				bean = BeanKit.resultSet2Bean(rs, PersonInfoMajor1Bean.class);
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

	public PersonInfoMajor1Bean getPersonInfoMajor1ByInfoBean(PersonInfoBean personInfoBean) {
		PersonInfoMajor1Bean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from PersonInfo_Major1 where user_id = ? and name = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, personInfoBean.getId());
			ps.setString(2, personInfoBean.getName());

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new PersonInfoMajor1Bean();
				bean = BeanKit.resultSet2Bean(rs, PersonInfoMajor1Bean.class);
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

	public PageResultBean<PersonInfoMajor1Bean> getPersonInfoMajor1PageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<PersonInfoMajor1Bean> result = new PageResultBean<PersonInfoMajor1Bean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from PersonInfo_Major1";

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
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, PersonInfoMajor1Bean.class,
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
