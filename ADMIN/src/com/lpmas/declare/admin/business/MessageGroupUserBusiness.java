package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.MessageGroupUserBean;
import com.lpmas.declare.admin.dao.MessageGroupUserDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class MessageGroupUserBusiness {
	public int addMessageGroupUser(MessageGroupUserBean bean) {
		MessageGroupUserDao dao = new MessageGroupUserDao();
		return dao.insertMessageGroupUser(bean);
	}

	public int updateMessageGroupUser(MessageGroupUserBean bean) {
		MessageGroupUserDao dao = new MessageGroupUserDao();
		return dao.updateMessageGroupUser(bean);
	}
	
	public int removeMessageGroupUser(MessageGroupUserBean bean) {
		MessageGroupUserDao dao = new MessageGroupUserDao();
		return dao.removeMessageGroupUser(bean);
	}

	public MessageGroupUserBean getMessageGroupUserByKey(int groupId, int userId) {
		MessageGroupUserDao dao = new MessageGroupUserDao();
		return dao.getMessageGroupUserByKey(groupId, userId);
	}

	public PageResultBean<MessageGroupUserBean> getMessageGroupUserPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		MessageGroupUserDao dao = new MessageGroupUserDao();
		return dao.getMessageGroupUserPageListByMap(condMap, pageBean);
	}
	
	public List<MessageGroupUserBean> getMessageGroupUserByGroupId(int groupId) {
		MessageGroupUserDao dao = new MessageGroupUserDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("groupId", String.valueOf(groupId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getMessageGroupUserByMap(condMap);
	}

}