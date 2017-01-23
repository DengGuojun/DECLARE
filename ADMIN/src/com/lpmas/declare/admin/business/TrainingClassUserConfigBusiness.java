package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.TrainingClassUserConfigBean;
import com.lpmas.declare.admin.dao.TrainingClassUserConfigDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;

public class TrainingClassUserConfigBusiness {
	public int addTrainingClassUserConfig(TrainingClassUserConfigBean bean) {
		TrainingClassUserConfigDao dao = new TrainingClassUserConfigDao();
		return dao.insertTrainingClassUserConfig(bean);
	}

	public int updateTrainingClassUserConfig(TrainingClassUserConfigBean bean) {
		TrainingClassUserConfigDao dao = new TrainingClassUserConfigDao();
		return dao.updateTrainingClassUserConfig(bean);
	}

	public TrainingClassUserConfigBean getTrainingClassUserConfigByKey(int configId) {
		TrainingClassUserConfigDao dao = new TrainingClassUserConfigDao();
		return dao.getTrainingClassUserConfigByKey(configId);
	}

	public PageResultBean<TrainingClassUserConfigBean> getTrainingClassUserConfigPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		TrainingClassUserConfigDao dao = new TrainingClassUserConfigDao();
		return dao.getTrainingClassUserConfigPageListByMap(condMap, pageBean);
	}
	
	public List<TrainingClassUserConfigBean> getTrainingClassUserConfigPageListByMap(
			HashMap<String, String> condMap) {
		TrainingClassUserConfigDao dao = new TrainingClassUserConfigDao();
		return dao.getTrainingClassUserConfigPageListByMap(condMap);
	}

	public ReturnMessageBean verifyTrainingClassUserConfig(TrainingClassUserConfigBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getConfigFrequency())) {
			result.setMessage("录入频率不能为空");
		} else if (!StringKit.isValid(bean.getConfigMode())) {
			result.setMessage("配置模式不能为空");
		} else if (!StringKit.isValid(bean.getProvince())) {
			result.setMessage("省份不能为空");
		}
//		else if (isExistsTrainingClassUserConfigBean(bean)) {
//			result.setMessage("该地区的设置已经存在不能插入");
//		}

		return result;
	}

	// 判断是否存在相同地区的配置信息
	public boolean isExistsTrainingClassUserConfigBean(TrainingClassUserConfigBean bean) {
		TrainingClassUserConfigBean existsBean = getTrainingClassUserConfigBeanByAreaAndSatus(bean);
		if (existsBean == null) {
			return false;
		} else {
			if (existsBean.getConfigId() == bean.getConfigId()) {
				return false;
			}
		}
		return true;
	}

	// 查询是否存在相同的地区配置(在同一地方)
	public TrainingClassUserConfigBean getTrainingClassUserConfigBeanByAreaAndSatus(TrainingClassUserConfigBean bean) {
		TrainingClassUserConfigDao dao = new TrainingClassUserConfigDao();
		return dao.getTrainingClassUserConfigBeanByAreaAndSatus(bean);
	}
}