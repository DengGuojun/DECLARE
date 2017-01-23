package com.lpmas.declare.portal.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.TrainingClassItemBean;
import com.lpmas.declare.portal.dao.TrainingClassItemDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TrainingClassItemBusiness {

	public TrainingClassItemBean getTrainingClassItemByKey(int itemId) {
		TrainingClassItemDao dao = new TrainingClassItemDao();
		return dao.getTrainingClassItemByKey(itemId);
	}

	public PageResultBean<TrainingClassItemBean> getTrainingClassItemPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TrainingClassItemDao dao = new TrainingClassItemDao();
		return dao.getTrainingClassItemPageListByMap(condMap, pageBean);
	}

	public List<TrainingClassItemBean> getTrainingClassItemListByMap(HashMap<String, String> condMap) {
		TrainingClassItemDao dao = new TrainingClassItemDao();
		return dao.getTrainingClassItemListByMap(condMap);
	}

}
