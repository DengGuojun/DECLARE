package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.MessageInfoBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class MessageInfoDao {
	private static Logger log = LoggerFactory.getLogger(MessageInfoDao.class);

	public int insertMessageInfo(MessageInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into message_info ( title, content, sender_id, receiver_list, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getTitle());
			ps.setString(2, bean.getContent());
			ps.setInt(3, bean.getSenderId());
			ps.setString(4, bean.getReceiverList());
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

	public int updateMessageInfo(MessageInfoBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update message_info set title = ?, content = ?, sender_id = ?, receiver_list = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where message_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getTitle());
			ps.setString(2, bean.getContent());
			ps.setInt(3, bean.getSenderId());
			ps.setString(4, bean.getReceiverList());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getModifyUser());
			ps.setString(7, bean.getMemo());

			ps.setInt(8, bean.getMessageId());

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

	public MessageInfoBean getMessageInfoByKey(int messageId) {
		MessageInfoBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from message_info where message_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, messageId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new MessageInfoBean();
				bean = BeanKit.resultSet2Bean(rs, MessageInfoBean.class);
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

	public PageResultBean<MessageInfoBean> getMessageInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<MessageInfoBean> result = new PageResultBean<MessageInfoBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from message_info";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String title = condMap.get("title");
			if (StringKit.isValid(title)) {
				condList.add("title like ?");
				paramList.add("%" + title + "%");
			}
			String senderId = condMap.get("senderId");
			if (StringKit.isValid(senderId)) {
				condList.add("sender_id = ?");
				paramList.add(senderId);
			}
			String createTimeFrom = condMap.get("createTimeFrom");
			if (StringKit.isValid(createTimeFrom)) {
				condList.add("create_time > ?");
				paramList.add(createTimeFrom);
			}
			String createTimeTo = condMap.get("createTimeTo");
			if (StringKit.isValid(createTimeTo)) {
				condList.add("create_time < ?");
				paramList.add(createTimeTo);
			}
			String orderQuery = "order by message_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor
					.getPageResult(sql, orderQuery, condList, paramList, MessageInfoBean.class, pageBean, db);
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
