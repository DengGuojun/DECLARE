package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.dao.JobInfoDao;
import com.lpmas.declare.bean.JobInfoBean;
import com.lpmas.framework.config.Constants;

public class JobInfoBusiness {
	public List<JobInfoBean> getJobInfoListByTypeId(int typeId) {
		JobInfoDao dao = new JobInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("typeId", String.valueOf(typeId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getJobInfoListByMap(condMap);
	}

	public JobInfoBean getJobInfoByName(String jobName,int typeId) {
		JobInfoDao dao = new JobInfoDao();
		return dao.getJobInfoByName(jobName,typeId);
	}

}