package com.lpmas.declare.console.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.DeclareImageBean;
import com.lpmas.declare.console.factory.DeclareDBFactory;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.util.BeanKit;

public class DeclareImageDao {
	private static Logger log = LoggerFactory.getLogger(DeclareImageDao.class);

	public DeclareImageBean getDeclareImageByKey(int declareId, String imageType) {
		DeclareImageBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from declare_image where declare_id = ? and image_type = ? and status = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, declareId);
			ps.setString(2, imageType);
			ps.setInt(3, Constants.STATUS_VALID);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new DeclareImageBean();
				bean = BeanKit.resultSet2Bean(rs, DeclareImageBean.class);
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
