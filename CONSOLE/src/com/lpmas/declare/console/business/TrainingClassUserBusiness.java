package com.lpmas.declare.console.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.console.bean.TrainingClassUserBean;
import com.lpmas.declare.console.dao.TrainingClassUserDao;
import com.lpmas.framework.config.Constants;

public class TrainingClassUserBusiness {

	public List<TrainingClassUserBean> getTrainingClassUserListByMap(HashMap<String, String> condMap) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserListByMap(condMap);
	}

	public List<TrainingClassUserBean> getTrainingClassUserListByDeclare(int declareId) {
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("declareId", String.valueOf(declareId));
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserListByMap(condMap);
	}

}
