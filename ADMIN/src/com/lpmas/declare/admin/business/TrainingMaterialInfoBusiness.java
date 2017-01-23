package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.declare.admin.bean.TrainingMaterialInfoBean;
import com.lpmas.declare.admin.dao.TrainingMaterialInfoDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.NumberKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;

public class TrainingMaterialInfoBusiness {
	public int addTrainingMaterialInfo(TrainingMaterialInfoBean bean) {
		TrainingMaterialInfoDao dao = new TrainingMaterialInfoDao();
		return dao.insertTrainingMaterialInfo(bean);
	}
	public int addTrainingMaterialInfoWithCreateTime(TrainingMaterialInfoBean bean) {
		TrainingMaterialInfoDao dao = new TrainingMaterialInfoDao();
		return dao.insertTrainingMaterialInfoWithCreateTime(bean);
	}

	public int updateTrainingMaterialInfo(TrainingMaterialInfoBean bean) {
		TrainingMaterialInfoDao dao = new TrainingMaterialInfoDao();
		return dao.updateTrainingMaterialInfo(bean);
	}

	public TrainingMaterialInfoBean getTrainingMaterialInfoByKey(int materialId) {
		TrainingMaterialInfoDao dao = new TrainingMaterialInfoDao();
		return dao.getTrainingMaterialInfoByKey(materialId);
	}

	public PageResultBean<TrainingMaterialInfoBean> getTrainingMaterialInfoPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		TrainingMaterialInfoDao dao = new TrainingMaterialInfoDao();
		return dao.getTrainingMaterialInfoPageListByMap(condMap, pageBean);
	}

	public TrainingMaterialInfoBean getTrainingMaterialInfoBean(TrainingMaterialInfoBean bean) {
		TrainingMaterialInfoDao dao = new TrainingMaterialInfoDao();
		return dao.getTrainingMaterialInfo(bean);
	}
	
	public int getTrainingMaterialCountByMap(Map<String, String> condMap){
		TrainingMaterialInfoDao dao = new TrainingMaterialInfoDao();
		return dao.getTrainingMaterialCountByMap(condMap);
	}

	// 根据前台传回的bean 判断是否存在相同的教材信息
	public ReturnMessageBean verifyTrainingMaterial(TrainingMaterialInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getMaterialName().trim())) {
			result.setMessage("教材名称必须填写");
		} else if (!StringKit.isValid(bean.getMaterialType().trim())) {
			result.setMessage("教材类型必须填写");
		} else if (!StringKit.isValid(bean.getTrainingYear().trim())) {
			result.setMessage("培训年份必须填写");
		} else if (!StringKit.isValid(bean.getCompileOrganization().trim())) {
			result.setMessage("组编单位必须填写");
		} else if (!StringKit.isValid(bean.getCompileOrganization().trim())) {
			result.setMessage("出版社必须填写");
		} else if (!StringKit.isValid(bean.getPublishingYeah().trim())) {
			result.setMessage("出版年份必须填写");
		} else if (!StringKit.isValid(bean.getPublishingMonth().trim())) {
			result.setMessage("出版月份必须填写");
		} else if (!StringKit.isValid(bean.getLink().trim())) {
			result.setMessage("链接必须填写");
		} else if (bean.getPrice() <= 0) {
			result.setMessage("请填写正确的价格");
		} else if (!NumberKit.isPositiveInteger(String.valueOf(bean.getPaperQuantity()))) {
			result.setMessage("请填写正确的纸张页数");
		} else if (!NumberKit.isAllDigit(String.valueOf(bean.getWordQuantity()))) {
			result.setMessage("请填写正确的字数");
		} else if (bean.getStatus() == Constants.STATUS_VALID) {
			if (isExistsTrainingMaterial(bean)) {
				result.setMessage("已存在相同的教材信息");
			}
		}
		return result;
	}

	public List<TrainingMaterialInfoBean> getAllTrainingMaterial(HashMap<String, String> condMap) {
		TrainingMaterialInfoDao dao = new TrainingMaterialInfoDao();
		return dao.getAllTrainingMaterial(condMap);
	}

	// 判断是否存在已有的教材 时间/地点/种类
	public boolean isExistsTrainingMaterial(TrainingMaterialInfoBean bean) {
		TrainingMaterialInfoBean existsBean = getTrainingMaterialInfoBean(bean);
		if (existsBean == null) {
			return false;
		} else {
			if (bean.getMaterialId() == existsBean.getMaterialId()) {
				return false;
			}
		}
		return true;
	}

}