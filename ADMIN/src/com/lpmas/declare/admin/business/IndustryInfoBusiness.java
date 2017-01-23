package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.dao.IndustryInfoDao;
import com.lpmas.declare.bean.IndustryInfoBean;
import com.lpmas.framework.config.Constants;

public class IndustryInfoBusiness {
	public List<IndustryInfoBean> getIndustryInfoListByTypeId(int typeId) {
		IndustryInfoDao dao = new IndustryInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("typeId", String.valueOf(typeId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getIndustryInfoListByMap(condMap);
	}

	public IndustryInfoBean getIndustryInfoByKey(int industryId) {
		IndustryInfoDao dao = new IndustryInfoDao();
		return dao.getIndustryInfoByKey(industryId);
	}
	
	public IndustryInfoBean getIndustryInfoByName(String industryName) {
		IndustryInfoDao dao = new IndustryInfoDao();
		return dao.getIndustryInfoByName(industryName);
	}

	public List<IndustryInfoBean> getIndustryInfoAllList() {
		IndustryInfoDao dao = new IndustryInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getIndustryInfoListByMap(condMap);
	}

}
