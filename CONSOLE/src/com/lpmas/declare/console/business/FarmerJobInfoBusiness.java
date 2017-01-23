package com.lpmas.declare.console.business;

import com.lpmas.declare.bean.FarmerJobInfoBean;
import com.lpmas.declare.console.dao.FarmerJobInfoDao;

public class FarmerJobInfoBusiness {

	public FarmerJobInfoBean getFarmerJobInfoByKey(int declareId) {
		FarmerJobInfoDao dao = new FarmerJobInfoDao();
		return dao.getFarmerJobInfoByKey(declareId);
	}

}