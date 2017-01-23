package com.lpmas.declare.console.business;

import com.lpmas.declare.bean.FarmerContactInfoBean;
import com.lpmas.declare.console.dao.FarmerContactInfoDao;

public class FarmerContactInfoBusiness {
	public FarmerContactInfoBean getFarmerContactInfoByKey(int declareId) {
		FarmerContactInfoDao dao = new FarmerContactInfoDao();
		return dao.getFarmerContactInfoByKey(declareId);
	}

}