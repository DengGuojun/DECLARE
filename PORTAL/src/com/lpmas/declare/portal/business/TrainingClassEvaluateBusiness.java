package com.lpmas.declare.portal.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.TrainingClassEvaluateBean;
import com.lpmas.declare.portal.dao.TrainingClassEvaluateDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TrainingClassEvaluateBusiness {
	
	public int addTrainingClassEvaluate(TrainingClassEvaluateBean bean) {
		TrainingClassEvaluateDao dao = new TrainingClassEvaluateDao();
		return dao.insertTrainingClassEvaluate(bean);
	}

	public int updateTrainingClassEvaluate(TrainingClassEvaluateBean bean) {
		TrainingClassEvaluateDao dao = new TrainingClassEvaluateDao();
		return dao.updateTrainingClassEvaluate(bean);
	}

	public TrainingClassEvaluateBean getTrainingClassEvaluateByKey(int evaluateId) {
		TrainingClassEvaluateDao dao = new TrainingClassEvaluateDao();
		return dao.getTrainingClassEvaluateByKey(evaluateId);
	}

	public PageResultBean<TrainingClassEvaluateBean> getTrainingClassEvaluatePageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		TrainingClassEvaluateDao dao = new TrainingClassEvaluateDao();
		return dao.getTrainingClassEvaluatePageListByMap(condMap, pageBean);
	}
	
	public List<TrainingClassEvaluateBean> getTrainingClassEvaluateListByMap(HashMap<String, String> condMap) {
		TrainingClassEvaluateDao dao = new TrainingClassEvaluateDao();
		return dao.getTrainingClassEvaluateListByMap(condMap);
	}

}