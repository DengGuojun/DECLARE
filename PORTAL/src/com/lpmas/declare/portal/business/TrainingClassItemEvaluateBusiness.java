package com.lpmas.declare.portal.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.TrainingClassItemEvaluateBean;
import com.lpmas.declare.portal.dao.TrainingClassItemEvaluateDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TrainingClassItemEvaluateBusiness {

	public int addTrainingClassItemEvaluate(TrainingClassItemEvaluateBean bean) {
		TrainingClassItemEvaluateDao dao = new TrainingClassItemEvaluateDao();
		return dao.insertTrainingClassItemEvaluate(bean);
	}

	public int updateTrainingClassItemEvaluate(TrainingClassItemEvaluateBean bean) {
		TrainingClassItemEvaluateDao dao = new TrainingClassItemEvaluateDao();
		return dao.updateTrainingClassItemEvaluate(bean);
	}

	public TrainingClassItemEvaluateBean getTrainingClassItemEvaluateByKey(int evaluateId) {
		TrainingClassItemEvaluateDao dao = new TrainingClassItemEvaluateDao();
		return dao.getTrainingClassItemEvaluateByKey(evaluateId);
	}

	public PageResultBean<TrainingClassItemEvaluateBean> getTrainingClassItemEvaluatePageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		TrainingClassItemEvaluateDao dao = new TrainingClassItemEvaluateDao();
		return dao.getTrainingClassItemEvaluatePageListByMap(condMap, pageBean);
	}

	public List<TrainingClassItemEvaluateBean> getTrainingClassItemEvaluateListByMap(HashMap<String, String> condMap) {
		TrainingClassItemEvaluateDao dao = new TrainingClassItemEvaluateDao();
		return dao.getTrainingClassItemEvaluateListByMap(condMap);
	}

}