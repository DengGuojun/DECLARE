package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.dao.TeacherInfoDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.EmailKit;
import com.lpmas.framework.util.NumberKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;

public class TeacherInfoBusiness {
	public int addTeacherInfo(TeacherInfoBean bean) {
		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.insertTeacherInfo(bean);
	}

	public int addTeacherInfoWithCreateTime(TeacherInfoBean bean) {
		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.insertTeacherInfoWithCreateTime(bean);
	}

	public int updateTeacherInfo(TeacherInfoBean bean) {
		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.updateTeacherInfo(bean);
	}

	public TeacherInfoBean getTeacherInfoByKey(int teacherId) {
		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.getTeacherInfoByKey(teacherId);
	}

	public PageResultBean<TeacherInfoBean> getTeacherInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.getTeacherInfoPageListByMap(condMap, pageBean);
	}
	

	public TeacherInfoBean getTeacherInfoByNameAndStatus(int status, String identityNumber) {
		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.getTeacherInfoByNameAndStatus(status, identityNumber);
	}

	// 判断是否存在相同的专业信息
	public boolean isExistsTeacherInfo(TeacherInfoBean bean) {
		TeacherInfoDao dao = new TeacherInfoDao();
		TeacherInfoBean existsBean = dao.getTeacherInfoByNameAndStatus(Constants.STATUS_VALID,
				bean.getIdentityNumber());
		if (existsBean == null) {
			return false;
		} else {
			if (existsBean.getTeacherId() == bean.getTeacherId()) {
				return false;
			}
		}
		return true;
	}

	// 验证教师信息的正确性
	public ReturnMessageBean verifyTeacherInfo(TeacherInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getTeacherName())) {
			result.setMessage("请输入教师姓名");
		} else if (!StringKit.isValid(bean.getTeacherNumber())) {
			result.setMessage("请输入教师编号");
		} else if (!StringKit.isValid(bean.getIdentityNumber())) {
			result.setMessage("身份证号码不能为空");
		} else if (!StringKit.isValid(bean.getCompany())) {
			result.setMessage("工作单位不能为空");
		}
		// else if (!StringKit.isValid(bean.getTechnicalGrade())) {
		// result.setMessage("职称等级不能为空");
		// }
		else if (!StringKit.isValid(bean.getTechnicalTitle())) {
			result.setMessage("职称名称不能为空");
		} else if (!StringKit.isValid(bean.getEmail())) {
			result.setMessage("教师邮箱不能为空");
		} else if (!EmailKit.isValidEmailAddress(bean.getEmail())) {
			result.setMessage("错误的邮箱地址");
		} else if (!StringKit.isValid(bean.getPhone())) {
			result.setMessage("手机号码不能为空");
		} else if (!NumberKit.isAllDigit(bean.getPhone())) {
			result.setMessage("手机号码必须是数字");
		} else if (!StringKit.isValid(bean.getCoursesOffer())) {
			result.setMessage("主授课程不能为空,如果没有请填写无");
		} else if (isExistsTeacherInfo(bean)) {
			result.setMessage("该教师的身份证已存在,无法录入");
		}
		return result;
	}

	public PageResultBean<TeacherInfoBean> getTeacherRegionInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {

		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.getTeacherRegionInfoPageListByMap(condMap, pageBean);
	}
	
	public List<TeacherInfoBean> getTeacherRegionInfoListByMap(HashMap<String, String> condMap) {
		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.getTeacherRegionInfoListByMap(condMap);
	}


	public int getTeacherRegionCountByMap(Map<String, String> condMap) {
		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.getTeacherRegionCountByMap(condMap);
	}

	public List<TeacherInfoBean> getTeacherInfoListByMap(HashMap<String, String> condMap) {
		TeacherInfoDao dao = new TeacherInfoDao();
		return dao.getTeacherInfoListByMap(condMap);
	}

}