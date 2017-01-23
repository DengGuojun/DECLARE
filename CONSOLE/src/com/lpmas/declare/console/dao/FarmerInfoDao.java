package com.lpmas.declare.console.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.console.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.util.BeanKit;

public class FarmerInfoDao {
	private static Logger log = LoggerFactory.getLogger(FarmerInfoDao.class);

	public FarmerInfoBean getFarmerInfoByKey(int declareId) {
		FarmerInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from farmer_info where declare_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, declareId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new FarmerInfoBean();
				bean = BeanKit.resultSet2Bean(rs, FarmerInfoBean.class);
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
