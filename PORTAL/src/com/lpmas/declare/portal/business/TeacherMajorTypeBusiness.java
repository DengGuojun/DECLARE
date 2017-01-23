package com.lpmas.declare.portal.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.TeacherMajorInfoBean;
import com.lpmas.declare.bean.TeacherMajorTypeBean;
import com.lpmas.declare.portal.dao.TeacherMajorTypeDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;

public class TeacherMajorTypeBusiness {
	public int addTeacherMajorType(TeacherMajorTypeBean bean) {
		TeacherMajorTypeDao dao = new TeacherMajorTypeDao();
		return dao.insertTeacherMajorType(bean);
	}

	public int updateTeacherMajorType(TeacherMajorTypeBean bean) {
		TeacherMajorTypeDao dao = new TeacherMajorTypeDao();
		return dao.updateTeacherMajorType(bean);
	}

	public TeacherMajorTypeBean getTeacherMajorTypeByKey(int majorId) {
		TeacherMajorTypeDao dao = new TeacherMajorTypeDao();
		return dao.getTeacherMajorTypeByKey(majorId);
	}

	public PageResultBean<TeacherMajorTypeBean> getTeacherMajorTypePageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		TeacherMajorTypeDao dao = new TeacherMajorTypeDao();
		return dao.getTeacherMajorTypePageListByMap(condMap, pageBean);
	}

	public ReturnMessageBean verifyTeacherMajorType(TeacherMajorTypeBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getMajorName())) {
			result.setMessage("专业类型名不能为空");
		} else if (!StringKit.isValid(bean.getMajorYear())) {
			result.setMessage("培训年份不能为空");
		} else if (bean.getStatus() == Constants.STATUS_VALID) {
			if (isExistsTeacherMajorTypeName(bean)) {
				result.setMessage("已存在相同的专业类型");
			}
		} else if (bean.getStatus() == Constants.STATUS_NOT_VALID) {
			TeacherMajorInfoBusiness teacherMajorInfoBusiness = new TeacherMajorInfoBusiness();
			List<TeacherMajorInfoBean> teacherMajorInfoList = teacherMajorInfoBusiness.getTeacherMajorInfoListByTypeId(bean.getMajorId());
			if (!teacherMajorInfoList.isEmpty()) {
				result.setMessage("该专业类型下包含专业信息，不能设置为无效");
			}
		}
		return result;
	}

	public List<TeacherMajorTypeBean> getTeacherMajorTypeAllList() {
		TeacherMajorTypeDao dao = new TeacherMajorTypeDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getTeacherMajorTypeListByMap(condMap);
	}

	// 判断是否存在相同的专业信息
	public boolean isExistsTeacherMajorTypeName(TeacherMajorTypeBean bean) {
		TeacherMajorTypeBean existsBean = getTeacherMajorTypeByNameAndStatus(bean);
		if (existsBean == null) {
			return false;
		} else {
			if (existsBean.getMajorId() == bean.getMajorId()) {
				return false;
			}
		}
		return true;
	}

	// 查询是否存在相同的专业(在同一年份下,同一地方)
	public TeacherMajorTypeBean getTeacherMajorTypeByNameAndStatus(TeacherMajorTypeBean bean) {
		TeacherMajorTypeDao dao = new TeacherMajorTypeDao();
		return dao.getTeacherMajorTypeByNameAndStatus(bean);
	}

	public List<TeacherMajorTypeBean> getTeacherMajorTypeListByMap(HashMap<String, String> condMap) {
		TeacherMajorTypeDao dao = new TeacherMajorTypeDao();
		return dao.getTeacherMajorTypeListByMap(condMap);
	}

}