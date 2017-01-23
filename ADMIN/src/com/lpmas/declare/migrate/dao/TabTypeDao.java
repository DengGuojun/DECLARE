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
import com.lpmas.declare.migrate.bean.TabTypeBean;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class TabTypeDao {
	private static Logger log = LoggerFactory.getLogger(TabTypeDao.class);

	public int insertTabType(TabTypeBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into tab_type ( pid, name, sort, state, type, remark) value( ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getPid());
			ps.setString(2, bean.getName());
			ps.setInt(3, bean.getSort());
			ps.setInt(4, bean.getState());
			ps.setInt(5, bean.getType());
			ps.setString(6, bean.getRemark());

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

	public int updateTabType(TabTypeBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update tab_type set pid = ?, name = ?, sort = ?, state = ?, type = ?, remark = ? where id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getPid());
			ps.setString(2, bean.getName());
			ps.setInt(3, bean.getSort());
			ps.setInt(4, bean.getState());
			ps.setInt(5, bean.getType());
			ps.setString(6, bean.getRemark());

			ps.setInt(7, bean.getId());

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

	public TabTypeBean getTabTypeByKey(int id) {
		TabTypeBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from tab_type where id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TabTypeBean();
				bean = BeanKit.resultSet2Bean(rs, TabTypeBean.class);
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

	public TabTypeBean getTabTypeByCondition(int pid, int sort, int type) {
		TabTypeBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from tab_type where pid = ? and sort = ? and type = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, pid);
			ps.setInt(2, sort);
			ps.setInt(3, type);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new TabTypeBean();
				bean = BeanKit.resultSet2Bean(rs, TabTypeBean.class);
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

	public PageResultBean<TabTypeBean> getTabTypePageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		PageResultBean<TabTypeBean> result = new PageResultBean<TabTypeBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from tab_type";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, TabTypeBean.class, pageBean, db);
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
