package com.lpmas.declare.admin.business;

import java.util.HashMap;

import com.lpmas.declare.admin.bean.MessageInfoBean;
import com.lpmas.declare.admin.dao.MessageInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class MessageInfoBusiness {
	public int addMessageInfo(MessageInfoBean bean) {
		MessageInfoDao dao = new MessageInfoDao();
		return dao.insertMessageInfo(bean);
	}

	public int updateMessageInfo(MessageInfoBean bean) {
		MessageInfoDao dao = new MessageInfoDao();
		return dao.updateMessageInfo(bean);
	}

	public MessageInfoBean getMessageInfoByKey(int messageId) {
		MessageInfoDao dao = new MessageInfoDao();
		return dao.getMessageInfoByKey(messageId);
	}

	public PageResultBean<MessageInfoBean> getMessageInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		MessageInfoDao dao = new MessageInfoDao();
		return dao.getMessageInfoPageListByMap(condMap, pageBean);
	}

}