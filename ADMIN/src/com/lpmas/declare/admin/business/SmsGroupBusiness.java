package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.SmsGroupBean;
import com.lpmas.declare.admin.dao.MessageGroupDao;
import com.lpmas.declare.admin.dao.SmsGroupDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class SmsGroupBusiness {
	public int addSmsGroup(SmsGroupBean bean) {
		SmsGroupDao dao = new SmsGroupDao();
		return dao.insertSmsGroup(bean);
	}

	public int updateSmsGroup(SmsGroupBean bean) {
		SmsGroupDao dao = new SmsGroupDao();
		return dao.updateSmsGroup(bean);
	}

	public SmsGroupBean getSmsGroupByKey(int groupId) {
		SmsGroupDao dao = new SmsGroupDao();
		return dao.getSmsGroupByKey(groupId);
	}

	public PageResultBean<SmsGroupBean> getSmsGroupPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		SmsGroupDao dao = new SmsGroupDao();
		return dao.getSmsGroupPageListByMap(condMap, pageBean);
	}
	
	public List<SmsGroupBean> getSmsGroupByOwnerId(int ownerId) {
		MessageGroupDao dao = new MessageGroupDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("ownerId", String.valueOf(ownerId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getSmsGroupByMap(condMap);
	}

}