package com.lpmas.declare.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.MessageReceiverBean;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.framework.db.DBExecutor;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;

public class MessageReceiverDao {
	private static Logger log = LoggerFactory.getLogger(MessageReceiverDao.class);

	public int insertMessageReceiver(MessageReceiverBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "insert into message_receiver ( message_id, receiver_id, message_title, is_read, status, create_time, create_user, memo) value( ?, ?, ?, ?, ?, now(), ?, ?)";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, bean.getMessageId());
			ps.setInt(2, bean.getReceiverId());
			ps.setString(3, bean.getMessageTitle());
			ps.setInt(4, bean.getIsRead());
			ps.setInt(5, bean.getStatus());
			ps.setInt(6, bean.getCreateUser());
			ps.setString(7, bean.getMemo());

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

	public int updateMessageReceiver(MessageReceiverBean bean) {
		int result = -1;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			String sql = "update message_receiver set message_title = ?, is_read = ?, status = ?, modify_time = now(), modify_user = ?, memo = ? where message_id = ? and receiver_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setString(1, bean.getMessageTitle());
			ps.setInt(2, bean.getIsRead());
			ps.setInt(3, bean.getStatus());
			ps.setInt(4, bean.getModifyUser());
			ps.setString(5, bean.getMemo());

			ps.setInt(6, bean.getMessageId());
			ps.setInt(7, bean.getReceiverId());

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

	public MessageReceiverBean getMessageReceiverByKey(int messageId, int receiverId) {
		MessageReceiverBean bean = null;
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from message_receiver where message_id = ? and receiver_id = ?";
			PreparedStatement ps = db.getPreparedStatement(sql);
			ps.setInt(1, messageId);
			ps.setInt(2, receiverId);

			ResultSet rs = db.executePstmtQuery();
			if (rs.next()) {
				bean = new MessageReceiverBean();
				bean = BeanKit.resultSet2Bean(rs, MessageReceiverBean.class);
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

	public PageResultBean<MessageReceiverBean> getMessageReceiverPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PageResultBean<MessageReceiverBean> result = new PageResultBean<MessageReceiverBean>();
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectR();
			String sql = "select * from message_receiver";

			List<String> condList = new ArrayList<String>();
			List<String> paramList = new ArrayList<String>();
			// 条件处理
			String receiverId = condMap.get("receiverId");
			if (StringKit.isValid(receiverId)) {
				condList.add("receiver_id = ?");
				paramList.add(receiverId);
			}
			String senderId = condMap.get("senderId");
			if (StringKit.isValid(senderId)) {
				condList.add("create_user = ?");
				paramList.add(senderId);
			}
			String messageTitle = condMap.get("messageTitle");
			if (StringKit.isValid(messageTitle)) {
				condList.add("message_title like ?");
				paramList.add("%" + messageTitle + "%");
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

			String orderQuery = "order by receiver_id,message_id desc";
			String orderBy = condMap.get("orderBy");
			if (StringKit.isValid(orderBy)) {
				orderQuery = " order by " + orderBy;
			}

			DBExecutor dbExecutor = dbFactory.getDBExecutor();
			result = dbExecutor.getPageResult(sql, orderQuery, condList, paramList, MessageReceiverBean.class,
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
