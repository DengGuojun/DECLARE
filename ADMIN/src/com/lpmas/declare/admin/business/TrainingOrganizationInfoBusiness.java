package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.dao.TrainingOrganizationInfoDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.NumberKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;

public class TrainingOrganizationInfoBusiness {
	public int addTrainingOrganizationInfo(TrainingOrganizationInfoBean bean) {
		TrainingOrganizationInfoDao dao = new TrainingOrganizationInfoDao();
		return dao.insertTrainingOrganizationInfo(bean);
	}
	public int addTrainingOrganizationInfoWithCreateTime(TrainingOrganizationInfoBean bean) {
		TrainingOrganizationInfoDao dao = new TrainingOrganizationInfoDao();
		return dao.insertTrainingOrganizationInfoWithCreateTime(bean);
	}

	public int updateTrainingOrganizationInfo(TrainingOrganizationInfoBean bean) {
		TrainingOrganizationInfoDao dao = new TrainingOrganizationInfoDao();
		return dao.updateTrainingOrganizationInfo(bean);
	}

	public TrainingOrganizationInfoBean getTrainingOrganizationInfoByKey(int organizationId) {
		TrainingOrganizationInfoDao dao = new TrainingOrganizationInfoDao();
		return dao.getTrainingOrganizationInfoByKey(organizationId);
	}

	public PageResultBean<TrainingOrganizationInfoBean> getTrainingOrganizationInfoPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		TrainingOrganizationInfoDao dao = new TrainingOrganizationInfoDao();
		return dao.getTrainingOrganizationInfoPageListByMap(condMap, pageBean);
	}

	public List<TrainingOrganizationInfoBean> getTrainingOrganizationInfoListByMap(HashMap<String, String> condMap) {
		TrainingOrganizationInfoDao dao = new TrainingOrganizationInfoDao();
		return dao.getTrainingOrganizationInfoListByMap(condMap);
	}

	/**
	 * 判断是否存在相同的组织结构
	 * 
	 * @param bean
	 * @return
	 */
	public boolean isExistTrainingOrganizationInfo(TrainingOrganizationInfoBean bean) {
		TrainingOrganizationInfoDao dao = new TrainingOrganizationInfoDao();
		TrainingOrganizationInfoBean existsBean = dao.getTrainingOrganization(bean);
		if (existsBean == null) {
			return false;
		} else {
			if (bean.getOrganizationId() == existsBean.getOrganizationId()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证输入的数据的正确性
	 * 
	 * @param bean
	 * @return
	 */
	public ReturnMessageBean verifyTrainingOrganizationInfo(TrainingOrganizationInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getOrganizationName().trim())) {
			result.setMessage("名称必须填写");
		} else if (!StringKit.isValid(bean.getTrainingYear().trim())) {
			result.setMessage("培训年份必须填写");
		} else if (!StringKit.isValid(bean.getOrganizationType().trim())) {
			result.setMessage("类型必须填写");
		} else if (!StringKit.isValid(bean.getRepresentativeName().trim())) {
			result.setMessage("法人代表必须填写");
		} else if (!StringKit.isValid(bean.getAddress().trim())) {
			result.setMessage("地址必须填写");
		} else if (!NumberKit.isAllDigit(bean.getZipCode()) || bean.getZipCode().length() != 6) {
			result.setMessage("邮政编码必须是6位数字");
		} else if (bean.getStatus() == Constants.STATUS_VALID) {
			if (isExistTrainingOrganizationInfo(bean)) {
				result.setMessage("已存在相同的名称");
			}
		}
		return result;
	}
	
	public int getTrainingOrganizationCountByMap(Map<String, String> condMap){
		TrainingOrganizationInfoDao dao = new TrainingOrganizationInfoDao();
		return dao.getTrainingOrganizationCountByMap(condMap);
	}

}