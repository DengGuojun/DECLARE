package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.SmsGroupUserBean;
import com.lpmas.declare.admin.dao.SmsGroupUserDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class SmsGroupUserBusiness {
	public int addSmsGroupUser(SmsGroupUserBean bean) {
		SmsGroupUserDao dao = new SmsGroupUserDao();
		return dao.insertSmsGroupUser(bean);
	}

	public int updateSmsGroupUser(SmsGroupUserBean bean) {
		SmsGroupUserDao dao = new SmsGroupUserDao();
		return dao.updateSmsGroupUser(bean);
	}

	public SmsGroupUserBean getSmsGroupUserByKey(int groupId, int smsUserId) {
		SmsGroupUserDao dao = new SmsGroupUserDao();
		return dao.getSmsGroupUserByKey(groupId, smsUserId);
	}

	public PageResultBean<SmsGroupUserBean> getSmsGroupUserPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		SmsGroupUserDao dao = new SmsGroupUserDao();
		return dao.getSmsGroupUserPageListByMap(condMap, pageBean);
	}
	
	public List<SmsGroupUserBean> getSmsGroupUserByGroupId(int groupId) {
		SmsGroupUserDao dao = new SmsGroupUserDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("groupId", String.valueOf(groupId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getSmsGroupUserByMap(condMap);
	}
	
	public int removeSmsGroupUser(SmsGroupUserBean bean) {
		SmsGroupUserDao dao = new SmsGroupUserDao();
		return dao.removeSmsGroupUser(bean);
	}

}