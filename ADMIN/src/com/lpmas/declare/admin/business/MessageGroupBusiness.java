package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.MessageGroupBean;
import com.lpmas.declare.admin.dao.MessageGroupDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class MessageGroupBusiness {
	public int addMessageGroup(MessageGroupBean bean) {
		MessageGroupDao dao = new MessageGroupDao();
		return dao.insertMessageGroup(bean);
	}

	public int updateMessageGroup(MessageGroupBean bean) {
		MessageGroupDao dao = new MessageGroupDao();
		return dao.updateMessageGroup(bean);
	}

	public MessageGroupBean getMessageGroupByKey(int groupId) {
		MessageGroupDao dao = new MessageGroupDao();
		return dao.getMessageGroupByKey(groupId);
	}

	public PageResultBean<MessageGroupBean> getMessageGroupPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		MessageGroupDao dao = new MessageGroupDao();
		return dao.getMessageGroupPageListByMap(condMap, pageBean);
	}

	public List<MessageGroupBean> getMessageGroupByOwnerId(int ownerId) {
		MessageGroupDao dao = new MessageGroupDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("ownerId", String.valueOf(ownerId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getMessageGroupByMap(condMap);
	}

}