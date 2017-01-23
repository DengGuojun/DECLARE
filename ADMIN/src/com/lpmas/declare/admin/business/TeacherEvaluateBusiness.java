package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.Map;

import com.lpmas.declare.admin.bean.TeacherEvaluateBean;
import com.lpmas.declare.admin.dao.TeacherEvaluateDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;

public class TeacherEvaluateBusiness {
	public int addTeacherEvaluate(TeacherEvaluateBean bean) {
		TeacherEvaluateDao dao = new TeacherEvaluateDao();
		return dao.insertTeacherEvaluate(bean);
	}

	public int addTeacherEvaluateWithCreateTime(TeacherEvaluateBean bean) {
		TeacherEvaluateDao dao = new TeacherEvaluateDao();
		return dao.insertTeacherEvaluateWithCreateTime(bean);
	}

	public int updateTeacherEvaluate(TeacherEvaluateBean bean) {
		TeacherEvaluateDao dao = new TeacherEvaluateDao();
		return dao.updateTeacherEvaluate(bean);
	}

	public TeacherEvaluateBean getTeacherEvaluateByKey(int evaluateId) {
		TeacherEvaluateDao dao = new TeacherEvaluateDao();
		return dao.getTeacherEvaluateByKey(evaluateId);
	}

	public PageResultBean<TeacherEvaluateBean> getTeacherEvaluatePageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		TeacherEvaluateDao dao = new TeacherEvaluateDao();
		return dao.getTeacherEvaluatePageListByMap(condMap, pageBean);
	}

	public PageResultBean<TeacherEvaluateBean> getTeacherEvaluatePageListByRegion(String province, String city, String region, PageBean pageBean) {
		TeacherEvaluateDao dao = new TeacherEvaluateDao();
		return dao.getTeacherEvaluatePageListByRegion(province, city, region, pageBean);
	}

	public ReturnMessageBean verifyTeacherEvaluate(TeacherEvaluateBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getTrainingOrganization())) {
			result.setMessage("培训机构不能为空");
		} else if (!StringKit.isValid(bean.getTrainingAddress())) {
			result.setMessage("培训地点不能为空");
		} else if (!StringKit.isValid(bean.getTeacherComment())) {
			result.setMessage("教师评语不能为空");
		} else if (!StringKit.isValid(bean.getTrainingTime())) {
			result.setMessage("培训时间不能为空");
		} else if (bean.getEvaluateScore() > 5 || bean.getEvaluateScore() < 0) {
			result.setMessage("学员评价分数的范围为0—5");
		} else if (!StringKit.isValid(bean.getClassName())) {
			result.setMessage("课程不能为空");
		}
		return result;
	}

	public Map<Integer, Float> getTeacherEvaluateMap() {
		TeacherEvaluateDao dao = new TeacherEvaluateDao();
		return dao.getTeacherEvaluateMap();
	}
}