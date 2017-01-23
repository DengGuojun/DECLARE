package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.MessageGroupUserBean;
import com.lpmas.declare.admin.bean.SmsGroupUserBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class MessageGroupUserDao {
	private static Logger log = LoggerFactory.getLogger(MessageGroupUserDao.class);

	public int insertMessageGroupUser(MessageGroupUserBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into message_group_user ( group_id, user_id) value( ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getGroupId());
			ps.setInt(2, bean.getUserId());

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

	public int updateMessageGroupUser(MessageGroupUserBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update message_group_user set  where group_id = ? and user_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);

			ps.setInt(1, bean.getGroupId());
			ps.setInt(2, bean.getUserId());

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
	
	public int removeMessageGroupUser(MessageGroupUserBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "delete from message_group_user where group_id = ? and user_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getGroupId());
			ps.setInt(2, bean.getUserId());

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

	public MessageGroupUserBean getMessageGroupUserByKey(int groupId, int userId) {
		MessageGroupUserBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from message_group_user where group_id = ? and user_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, groupId);
			ps.setInt(2, userId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new MessageGroupUserBean();
				bean = BeanKit.resultSet2Bean(rs, MessageGroupUserBean.class);
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

	public PageResultBean<MessageGroupUserBean> getMessageGroupUserPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<MessageGroupUserBean> result = new PageResultBean<MessageGroupUserBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from message_group_user";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理

			String orderQuery = "order by group_id,user_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, MessageGroupUserBean.class,
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
	
	public List<MessageGroupUserBean> getMessageGroupUserByMap(HashMap<String, String> condMap) {
		List<MessageGroupUserBean> list = new ArrayList<MessageGroupUserBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql =  "select * from message_group_user";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String groupId = condMap.get("groupId");
			if (StringKit.isValid(groupId)) {
				condList.add("group_id = ?");
				paramList.add(groupId);
			}
			String orderQuery = "order by group_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			list = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, MessageGroupUserBean.class, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return list;
	}
	
	public List<SmsGroupUserBean> getSmsGroupUserByMap(HashMap<String, String> condMap) {
		List<SmsGroupUserBean> list = new ArrayList<SmsGroupUserBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql =  "select * from sms_group_user";
			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			String groupId = condMap.get("groupId");
			if (StringKit.isValid(groupId)) {
				condList.add("group_id = ?");
				paramList.add(groupId);
			}
			String orderQuery = "order by group_id desc";

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			list = dbExecutor.getRecordListResult(sql, orderQuery, condList, paramList, SmsGroupUserBean.class, db);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}
		return list;
	}

}
