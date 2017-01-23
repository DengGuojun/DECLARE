package com.lpmas.declare.portal.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.portal.dao.TrainingClassInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TrainingClassInfoBusiness {

	public TrainingClassInfoBean getTrainingClassInfoByKey(int classId) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.getTrainingClassInfoByKey(classId);
	}

	public PageResultBean<TrainingClassInfoBean> getTrainingClassInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.getTrainingClassInfoPageListByMap(condMap, pageBean);
	}

	public List<TrainingClassInfoBean> getTrainingClassInfoListByMap(HashMap<String, String> condMap) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.getTrainingClassInfoListByMap(condMap);
	}

}
