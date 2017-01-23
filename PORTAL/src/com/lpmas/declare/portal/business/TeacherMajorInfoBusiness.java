package com.lpmas.declare.portal.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.TeacherMajorInfoBean;
import com.lpmas.declare.portal.dao.TeacherMajorInfoDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;

public class TeacherMajorInfoBusiness {
	public int addTeacherMajorInfo(TeacherMajorInfoBean bean) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.insertTeacherMajorInfo(bean);
	}

	public int updateTeacherMajorInfo(TeacherMajorInfoBean bean) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.updateTeacherMajorInfo(bean);
	}

	public TeacherMajorInfoBean getTeacherMajorInfoByKey(int majorId) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.getTeacherMajorInfoByKey(majorId);
	}

	public PageResultBean<TeacherMajorInfoBean> getTeacherMajorInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.getTeacherMajorInfoPageListByMap(condMap, pageBean);
	}

	public List<TeacherMajorInfoBean> getTeacherMajorInfoListByTypeId(int typeId) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("typeId", String.valueOf(typeId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getTeacherMajorInfoListByMap(condMap);
	}

	public ReturnMessageBean verifyTeacherMajorInfo(TeacherMajorInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getMajorName())) {
			result.setMessage("专业名称不能为空");
		} else if (bean.getTypeId() <= 0) {
			result.setMessage("专业类型必须选择");
		} else if (bean.getStatus() == Constants.STATUS_VALID) {
			// 修改状态
			if (isExistsTeacherMajorInfoName(bean)) {
				result.setMessage("已存在相同的专业信息");
			}
		} else if (bean.getStatus() == Constants.STATUS_NOT_VALID) {
			// 修改为无效状态 判断是否有补习班在引用 training_class_info
		}
		return result;
	}

	// 判断是否存在相同的专业信息
	public boolean isExistsTeacherMajorInfoName(TeacherMajorInfoBean bean) {
		TeacherMajorInfoBean existsBean = getTeacherMajorInfoByNameAndStatus(bean.getMajorName(), bean.getTypeId());
		if (existsBean == null) {
			return false;
		} else {
			if (existsBean.getMajorId() == bean.getMajorId()) {
				return false;
			}
		}
		return true;
	}

	// 查询是否存在相同的专业(在同一专业类型下)
	public TeacherMajorInfoBean getTeacherMajorInfoByNameAndStatus(String majorName, int typeId) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.getTeacherMajorInfoByNameAndStatus(majorName, typeId);
	}

	public List<TeacherMajorInfoBean> getMajorInfoAllList() {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getTeacherMajorInfoListByMap(condMap);
	}

	public List<TeacherMajorInfoBean> getMajorInfoListByMap(HashMap<String, String> condMap) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getTeacherMajorInfoListByMap(condMap);
	}

}