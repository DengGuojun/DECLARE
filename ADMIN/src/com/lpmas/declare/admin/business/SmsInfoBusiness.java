package com.lpmas.declare.admin.business;

import java.util.HashMap;

import com.lpmas.declare.admin.bean.SmsInfoBean;
import com.lpmas.declare.admin.dao.SmsInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class SmsInfoBusiness {
	public int addSmsInfo(SmsInfoBean bean) {
		SmsInfoDao dao = new SmsInfoDao();
		return dao.insertSmsInfo(bean);
	}

	public int updateSmsInfo(SmsInfoBean bean) {
		SmsInfoDao dao = new SmsInfoDao();
		return dao.updateSmsInfo(bean);
	}

	public SmsInfoBean getSmsInfoByKey(int smsId) {
		SmsInfoDao dao = new SmsInfoDao();
		return dao.getSmsInfoByKey(smsId);
	}

	public PageResultBean<SmsInfoBean> getSmsInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		SmsInfoDao dao = new SmsInfoDao();
		return dao.getSmsInfoPageListByMap(condMap, pageBean);
	}

}