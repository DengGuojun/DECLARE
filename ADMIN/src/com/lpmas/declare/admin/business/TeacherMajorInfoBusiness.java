package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.dao.TeacherMajorInfoDao;
import com.lpmas.declare.bean.TeacherMajorInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;

public class TeacherMajorInfoBusiness {
	public int addMajorInfo(TeacherMajorInfoBean bean) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.insertMajorInfo(bean);
	}

	public int updateMajorInfo(TeacherMajorInfoBean bean) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.updateMajorInfo(bean);
	}

	public TeacherMajorInfoBean getMajorInfoByKey(int majorId) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.getMajorInfoByKey(majorId);
	}

	public PageResultBean<TeacherMajorInfoBean> getMajorInfoPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.getMajorInfoPageListByMap(condMap, pageBean);
	}

	public List<TeacherMajorInfoBean> getMajorInfoListByTypeId(int typeId) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("typeId", String.valueOf(typeId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getMajorInfoListByMap(condMap);
	}

	public ReturnMessageBean verifyMajorInfo(TeacherMajorInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getMajorName())) {
			result.setMessage("专业名称不能为空");
		} else if (bean.getTypeId() <= 0) {
			result.setMessage("专业类型必须选择");
		} else if (bean.getStatus() == Constants.STATUS_VALID) {
			// 修改状态
			if (isExistsMajorInfoName(bean)) {
				result.setMessage("已存在相同的专业信息");
			}
		} else if (bean.getStatus() == Constants.STATUS_NOT_VALID) {
			// 修改为无效状态 判断是否有补习班在引用 training_class_info
		}
		return result;
	}

	// 判断是否存在相同的专业信息
	public boolean isExistsMajorInfoName(TeacherMajorInfoBean bean) {
		TeacherMajorInfoBean existsBean = getMajorInfoByNameAndSatus(bean.getMajorName(), bean.getTypeId());
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
	public TeacherMajorInfoBean getMajorInfoByNameAndSatus(String majorName, int typeId) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		return dao.getMajorInfoByNameAndSatus(majorName, typeId);
	}

	public List<TeacherMajorInfoBean> getMajorInfoAllList() {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getMajorInfoListByMap(condMap);
	}

	public List<TeacherMajorInfoBean> getMajorInfoListByMap(HashMap<String, String> condMap) {
		TeacherMajorInfoDao dao = new TeacherMajorInfoDao();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getMajorInfoListByMap(condMap);
	}

}