package com.lpmas.declare.console.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.FarmerSkillInfoBean;
import com.lpmas.declare.console.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.util.BeanKit;

public class FarmerSkillInfoDao {
	private static Logger log = LoggerFactory.getLogger(FarmerSkillInfoDao.class);

	public FarmerSkillInfoBean getFarmerSkillInfoByKey(int declareId) {
		FarmerSkillInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from farmer_skill_info where declare_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, declareId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new FarmerSkillInfoBean();
				bean = BeanKit.resultSet2Bean(rs, FarmerSkillInfoBean.class);
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
}
