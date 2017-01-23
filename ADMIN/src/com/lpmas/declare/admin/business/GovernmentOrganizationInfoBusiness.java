package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.GovernmentOrganizationInfoBean;
import com.lpmas.declare.admin.dao.GovernmentOrganizationInfoDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.NumberKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;

public class GovernmentOrganizationInfoBusiness {
	public int addGovernmentOrganizationInfo(GovernmentOrganizationInfoBean bean) {
		GovernmentOrganizationInfoDao dao = new GovernmentOrganizationInfoDao();
		return dao.insertGovernmentOrganizationInfo(bean);
	}
	public int addGovernmentOrganizationInfoWithCreateTime(GovernmentOrganizationInfoBean bean) {
		GovernmentOrganizationInfoDao dao = new GovernmentOrganizationInfoDao();
		return dao.insertGovernmentOrganizationInfoWithCreateTime(bean);
	}

	public int updateGovernmentOrganizationInfo(GovernmentOrganizationInfoBean bean) {
		GovernmentOrganizationInfoDao dao = new GovernmentOrganizationInfoDao();
		return dao.updateGovernmentOrganizationInfo(bean);
	}

	public GovernmentOrganizationInfoBean getGovernmentOrganizationInfoByKey(int organizationId) {
		GovernmentOrganizationInfoDao dao = new GovernmentOrganizationInfoDao();
		return dao.getGovernmentOrganizationInfoByKey(organizationId);
	}

	public PageResultBean<GovernmentOrganizationInfoBean> getGovernmentOrganizationInfoPageListByMap(
			HashMap<String, String> condMap, PageBean pageBean) {
		GovernmentOrganizationInfoDao dao = new GovernmentOrganizationInfoDao();
		return dao.getGovernmentOrganizationInfoPageListByMap(condMap, pageBean);
	}
	
	public List<GovernmentOrganizationInfoBean> getGovernmentOrganizationInfoListByMap(HashMap<String, String> condMap) {
		GovernmentOrganizationInfoDao dao = new GovernmentOrganizationInfoDao();
		return dao.getGovernmentOrganizationInfoListByMap(condMap);
	}

	/**
	 * 判断是否存在相同的主管部门
	 * 
	 * @param bean
	 * @return
	 */
	public boolean isExistGovernmentOrganizationInfoBean(GovernmentOrganizationInfoBean bean) {
		GovernmentOrganizationInfoDao dao = new GovernmentOrganizationInfoDao();
		GovernmentOrganizationInfoBean existsBean = dao.getGovernmentOrganizationInfo(bean);
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
	 * 验证输入数据的正确性
	 */
	public ReturnMessageBean verifyGovernmentOrganizationInfo(GovernmentOrganizationInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getOrganizationName().trim())) {
			result.setMessage("主管部门名字不能为空");
		} else if (!StringKit.isValid(bean.getDepartment().trim())) {
			result.setMessage("主管科室不能为空");
		} else if (!StringKit.isValid(bean.getResponsiblePersonName().trim())) {
			result.setMessage("主管科室负责人姓名不能为空");
		} else if (!StringKit.isValid(bean.getResponsiblePersonMobile().trim())) {
			result.setMessage("主管科室负责人电话号码不能为空");
		} else if (!StringKit.isValid(bean.getOperatorName().trim())) {
			result.setMessage("主管科室经办人姓名不能为空");
		} else if (!StringKit.isValid(bean.getOperatorMobile().trim())) {
			result.setMessage("主管科室经办人电话号码不能为空");
		} else if (!StringKit.isValid(bean.getAddress().trim())) {
			result.setMessage("地址必须填写");
		} else if (!NumberKit.isAllDigit(bean.getZipCode()) || bean.getZipCode().length() != 6) {
			result.setMessage("邮政编码必须为6位的数字");
		} else if (bean.getStatus() == Constants.STATUS_VALID) {
			if (isExistGovernmentOrganizationInfoBean(bean)) {
				result.setMessage("该年份已经存在该主管部门,且存在相同的主管科室");
			}
		}
		return result;

	}
}