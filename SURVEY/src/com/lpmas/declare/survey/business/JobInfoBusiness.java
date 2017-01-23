package com.lpmas.declare.survey.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.JobInfoBean;
import com.lpmas.declare.survey.dao.JobInfoDao;
import com.lpmas.framework.config.Constants;

public class JobInfoBusiness {
	public List<JobInfoBean> getJobInfoListByTypeId(int typeId) {
		JobInfoDao dao = new JobInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("typeId", String.valueOf(typeId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getJobInfoListByMap(condMap);
	}

	public JobInfoBean getJobInfoByKey(int jobId) {
		JobInfoDao dao = new JobInfoDao();
		return dao.getJobInfoByKey(jobId);
	}
}