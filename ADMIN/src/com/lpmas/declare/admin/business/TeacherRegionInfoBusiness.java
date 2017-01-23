package com.lpmas.declare.admin.business;

import java.util.List;
import java.util.Map;

import com.lpmas.declare.admin.bean.TeacherRegionInfoBean;
import com.lpmas.declare.admin.dao.TeacherRegionInfoDao;

public class TeacherRegionInfoBusiness {
	public int addTeacherRegionInfo(TeacherRegionInfoBean bean) {
		TeacherRegionInfoDao dao = new TeacherRegionInfoDao();
		return dao.insertTeacherRegionInfo(bean);
	}

	public int updateTeacherRegionInfo(TeacherRegionInfoBean bean) {
		TeacherRegionInfoDao dao = new TeacherRegionInfoDao();
		return dao.updateTeacherRegionInfo(bean);
	}

	public TeacherRegionInfoBean getTeacherRegionInfoByKey(int teacherId, String level, String province, String city,
			String region) {
		TeacherRegionInfoDao dao = new TeacherRegionInfoDao();
		return dao.getTeacherRegionInfoByKey(teacherId, level, province, city, region);
	}

	public List<TeacherRegionInfoBean> getTeacherRegionInfoPageListByMap(Map<String, String> condMap) {
		TeacherRegionInfoDao dao = new TeacherRegionInfoDao();
		return dao.getTeacherRegionInfoPageListByMap(condMap);
	}

	public int removeTeacherRegionInfo(int infoId) {
		TeacherRegionInfoDao dao = new TeacherRegionInfoDao();
		return dao.removeTeacherRegionInfo(infoId);
	}

	public int getTeacherRegionInfoRecordResultByMap(Map<String, String> condMap) {
		TeacherRegionInfoDao dao = new TeacherRegionInfoDao();
		return dao.getTeacherRegionInfoRecordResultByMap(condMap);
	}
}