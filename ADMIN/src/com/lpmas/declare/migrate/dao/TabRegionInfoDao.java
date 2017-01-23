package com.lpmas.declare.migrate.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TabRegionInfoDao {
	private static Logger log = LoggerFactory.getLogger(TabRegionInfoDao.class);

	public int insertTabRegionInfo(TabRegionInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into tab_region_info ( country_id, province_id, city_id, region_name, region_code) value( ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getCountryId());
			ps.setInt(2, bean.getProvinceId());
			ps.setInt(3, bean.getCityId());
			ps.setString(4, bean.getRegionName());
			ps.setString(5, bean.getRegionCode());

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

	public int updateTabRegionInfo(TabRegionInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update tab_region_info set country_id = ?, province_id = ?, city_id = ?, region_name = ?, region_code = ? where region_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getCountryId());
			ps.setInt(2, bean.getProvinceId());
			ps.setInt(3, bean.getCityId());
			ps.setString(4, bean.getRegionName());
			ps.setString(5, bean.getRegionCode());

			ps.setInt(6, bean.getRegionId());

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

	public TabRegionInfoBean getTabRegionInfoByKey(int regionId) {
		TabRegionInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from tab_region_info where region_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, regionId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TabRegionInfoBean();
				bean = BeanKit.resultSet2Bean(rs, TabRegionInfoBean.class);
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

	public PageResultBean<TabRegionInfoBean> getTabRegionInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<TabRegionInfoBean> result = new PageResultBean<TabRegionInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from tab_region_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by region_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TabRegionInfoBean.class, pageBean,
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

	public TabRegionInfoBean getTabRegionInfoByRegionCode(String regionCode) {
		TabRegionInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from tab_region_info where region_code = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, regionCode);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TabRegionInfoBean();
				bean = BeanKit.resultSet2Bean(rs, TabRegionInfoBean.class);
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
