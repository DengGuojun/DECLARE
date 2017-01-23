package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.SmsUserBean;
import com.lpmas.declare.admin.dao.SmsUserDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class SmsUserBusiness {
	public int addSmsUser(SmsUserBean bean) {
		SmsUserDao dao = new SmsUserDao();
		return dao.insertSmsUser(bean);
	}

	public int updateSmsUser(SmsUserBean bean) {
		SmsUserDao dao = new SmsUserDao();
		return dao.updateSmsUser(bean);
	}

	public SmsUserBean getSmsUserByKey(int smsUserId) {
		SmsUserDao dao = new SmsUserDao();
		return dao.getSmsUserByKey(smsUserId);
	}
	
	public SmsUserBean getSmsUserByName(String name) {
		SmsUserDao dao = new SmsUserDao();
		return dao.getSmsUserByName(name);
	}

	public PageResultBean<SmsUserBean> getSmsUserPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		SmsUserDao dao = new SmsUserDao();
		return dao.getSmsUserPageListByMap(condMap, pageBean);
	}
	
	public List<SmsUserBean> getSmsUserListByMap(HashMap<String, String> condMap) {
		SmsUserDao dao = new SmsUserDao();
		return dao.getSmsUserListByMap(condMap);
	}

}