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
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class PersonInfoDao {
	private static Logger log = LoggerFactory.getLogger(PersonInfoDao.class);

	public int insertPersonInfo(PersonInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into person_info ( name, id_card_no, phone_no, base_info_type, status, regist_type, server_id, year, create_time, update_time, remark) value( ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getIdCardNo());
			ps.setString(3, bean.getPhoneNo());
			ps.setInt(4, bean.getBaseInfoType());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getRegistType());
			ps.setString(7, bean.getServerId());
			ps.setInt(8, bean.getYear());
			ps.setTimestamp(9, bean.getUpdateTime());
			ps.setString(10, bean.getRemark());

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

	public int updatePersonInfo(PersonInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update person_info set name = ?, id_card_no = ?, phone_no = ?, base_info_type = ?, status = ?, regist_type = ?, server_id = ?, year = ?, update_time = ?, remark = ? where id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getIdCardNo());
			ps.setString(3, bean.getPhoneNo());
			ps.setInt(4, bean.getBaseInfoType());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getRegistType());
			ps.setString(7, bean.getServerId());
			ps.setInt(8, bean.getYear());
			ps.setTimestamp(9, bean.getUpdateTime());
			ps.setString(10, bean.getRemark());

			ps.setString(11, bean.getId());

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

	public PersonInfoBean getPersonInfoByKey(String id) {
		PersonInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from person_info where id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, id);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new PersonInfoBean();
				bean = BeanKit.resultSet2Bean(rs, PersonInfoBean.class);
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

	public PageResultBean<PersonInfoBean> getPersonInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<PersonInfoBean> result = new PageResultBean<PersonInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from person_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, PersonInfoBean.class, pageBean, db);
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
