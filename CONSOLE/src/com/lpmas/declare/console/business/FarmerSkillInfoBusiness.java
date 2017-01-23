package com.lpmas.declare.console.business;

import com.lpmas.declare.bean.FarmerSkillInfoBean;
import com.lpmas.declare.console.dao.FarmerSkillInfoDao;

public class FarmerSkillInfoBusiness {

	public FarmerSkillInfoBean getFarmerSkillInfoByKey(int declareId) {
		FarmerSkillInfoDao dao = new FarmerSkillInfoDao();
		return dao.getFarmerSkillInfoByKey(declareId);
	}

}