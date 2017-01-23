package com.lpmas.declare.admin.business;

import java.util.HashMap;

import com.lpmas.declare.admin.bean.MessageReceiverBean;
import com.lpmas.declare.admin.dao.MessageReceiverDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class MessageReceiverBusiness {
	public int addMessageReceiver(MessageReceiverBean bean) {
		MessageReceiverDao dao = new MessageReceiverDao();
		return dao.insertMessageReceiver(bean);
	}

	public int updateMessageReceiver(MessageReceiverBean bean) {
		MessageReceiverDao dao = new MessageReceiverDao();
		return dao.updateMessageReceiver(bean);
	}

	public MessageReceiverBean getMessageReceiverByKey(int messageId, int receiverId) {
		MessageReceiverDao dao = new MessageReceiverDao();
		return dao.getMessageReceiverByKey(messageId, receiverId);
	}

	public PageResultBean<MessageReceiverBean> getMessageReceiverPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		MessageReceiverDao dao = new MessageReceiverDao();
		return dao.getMessageReceiverPageListByMap(condMap, pageBean);
	}

}