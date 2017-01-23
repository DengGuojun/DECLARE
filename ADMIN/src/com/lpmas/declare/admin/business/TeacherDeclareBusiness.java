package com.lpmas.declare.admin.business;

import java.util.HashMap;

import com.lpmas.declare.admin.dao.TeacherDeclareDao;
import com.lpmas.declare.bean.TeacherDeclareBean;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TeacherDeclareBusiness {
	public int addTeacherDeclare(TeacherDeclareBean bean) {
		TeacherDeclareDao dao = new TeacherDeclareDao();
		return dao.insertTeacherDeclare(bean);
	}

	public int updateTeacherDeclare(TeacherDeclareBean bean) {
		TeacherDeclareDao dao = new TeacherDeclareDao();
		return dao.updateTeacherDeclare(bean);
	}

	public TeacherDeclareBean getTeacherDeclareByKey(int declareId) {
		TeacherDeclareDao dao = new TeacherDeclareDao();
		return dao.getTeacherDeclareByKey(declareId);
	}

	public PageResultBean<TeacherDeclareBean> getTeacherDeclarePageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TeacherDeclareDao dao = new TeacherDeclareDao();
		return dao.getTeacherDeclarePageListByMap(condMap, pageBean);
	}

}