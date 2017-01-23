package com.lpmas.declare.portal.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.NationalCertificationBean;
import com.lpmas.declare.portal.dao.NationalCertificationDao;
import com.lpmas.framework.config.Constants;

public class NationalCertificationBusiness {

	public List<NationalCertificationBean> getNationalCertificationAllList(){
		NationalCertificationDao dao = new NationalCertificationDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getNationalCertificationListByMap(condMap);
	}

}