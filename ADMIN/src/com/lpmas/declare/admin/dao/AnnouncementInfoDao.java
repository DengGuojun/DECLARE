package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AnnouncementInfoBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class AnnouncementInfoDao {
	private static Logger log = LoggerFactory.getLogger(AnnouncementInfoDao.class);

	public int insertAnnouncementInfo(AnnouncementInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into announcement_info ( announcement_title, announcement_content, receiver, announcement_status, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getAnnouncementTitle());
			ps.setString(2, bean.getAnnouncementContent());
			ps.setString(3, bean.getReceiver());
			ps.setString(4, bean.getAnnouncementStatus());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getCreateUser());
			ps.setString(7, bean.getMemo());

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

	public int updateAnnouncementInfo(AnnouncementInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update announcement_info set announcement_title = ?, announcement_content = ?, receiver = ?, announcement_status = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where announcement_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getAnnouncementTitle());
			ps.setString(2, bean.getAnnouncementContent());
			ps.setString(3, bean.getReceiver());
			ps.setString(4, bean.getAnnouncementStatus());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getModifyUser());
			ps.setString(7, bean.getMemo());

			ps.setInt(8, bean.getAnnouncementId());

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

	public AnnouncementInfoBean getAnnouncementInfoByKey(int announcementId) {
		AnnouncementInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from announcement_info where announcement_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, announcementId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new AnnouncementInfoBean();
				bean = BeanKit.resultSet2Bean(rs, AnnouncementInfoBean.class);
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

	public PageResultBean<AnnouncementInfoBean> getAnnouncementInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<AnnouncementInfoBean> result = new PageResultBean<AnnouncementInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from announcement_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String receiver = condMap.get("receiver");
			if (StringKit.isValid(receiver)) {
				condList.add("(receiver like ? or receiver like ?)");
				paramList.add("%" + receiver + ",%");
				paramList.add("%" + receiver + "]%");
			}
			String announcementStatus = condMap.get("announcementStatus");
			if (StringKit.isValid(announcementStatus)) {
				condList.add("announcement_status = ?");
				paramList.add(announcementStatus);
			}
			String createUser = condMap.get("createUser");
			if (StringKit.isValid(createUser)) {
				condList.add("create_user = ?");
				paramList.add(createUser);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}

			String orderQuery = "order by announcement_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, AnnouncementInfoBean.class,
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

	public List<AnnouncementInfoBean> getAnnouncementInfoListByMap(HashMap<String, String> condMap) {
		List<AnnouncementInfoBean> result = new ArrayList<AnnouncementInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from announcement_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String announcementTitle = condMap.get("announcementTitle");
			if (StringKit.isValid(announcementTitle)) {
				condList.add("announcement_title = ?");
				paramList.add(announcementTitle);
			}
			String status = condMap.get("status");
			if (StringKit.isValid(status)) {
				condList.add("status = ?");
				paramList.add(status);
			}
			String receiver = condMap.get("receiver");
			if (StringKit.isValid(receiver)) {
				condList.add("(receiver like ? or receiver like ?)");
				paramList.add("%" + receiver + ",%");
				paramList.add("%" + receiver + "]%");
			}
			String announcementStatus = condMap.get("announcementStatus");
			if (StringKit.isValid(announcementStatus)) {
				condList.add("announcement_status = ?");
				paramList.add(announcementStatus);
			}
			String createUser = condMap.get("createUser");
			if (StringKit.isValid(createUser)) {
				condList.add("create_user = ?");
				paramList.add(createUser);
			}
			String orderQuery = "order by announcement_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, AnnouncementInfoBean.class,
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
