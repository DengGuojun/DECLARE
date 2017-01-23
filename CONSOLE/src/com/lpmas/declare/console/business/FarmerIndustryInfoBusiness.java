package com.lpmas.declare.console.business;

import com.lpmas.declare.bean.FarmerIndustryInfoBean;
import com.lpmas.declare.console.dao.FarmerIndustryInfoDao;

public class FarmerIndustryInfoBusiness {

	public FarmerIndustryInfoBean getFarmerIndustryInfoByKey(int declareId) {
		FarmerIndustryInfoDao dao = new FarmerIndustryInfoDao();
		return dao.getFarmerIndustryInfoByKey(declareId);
	}

}