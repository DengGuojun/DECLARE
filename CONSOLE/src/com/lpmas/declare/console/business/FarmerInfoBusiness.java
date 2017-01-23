package com.lpmas.declare.console.business;

import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.console.dao.FarmerInfoDao;

public class FarmerInfoBusiness {

	public FarmerInfoBean getFarmerInfoByKey(int declareId) {
		FarmerInfoDao dao = new FarmerInfoDao();
		return dao.getFarmerInfoByKey(declareId);
	}

}