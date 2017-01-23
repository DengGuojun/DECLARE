package com.lpmas.declare.portal.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.portal.dao.DeclareReportDao;
import com.lpmas.framework.util.StringKit;

public class DeclareReportBusiness {
	public int insertDeclareReport(DeclareReportBean bean) {
		DeclareReportDao dao = new DeclareReportDao();
		return dao.insertDeclareReport(bean);
	}

	public long updateDeclareReport(DeclareReportBean bean) {
		DeclareReportDao dao = new DeclareReportDao();
		return dao.updateDeclareReport(bean);
	}

	public DeclareReportBean getDeclareReportByKey(String id) throws Exception {
		DeclareReportDao dao = new DeclareReportDao();
		return dao.getDeclareReportByKey(id);
	}

	public List<DeclareReportBean> getDeclareReportListByMap(HashMap<String, String> condMap) throws Exception {
		DeclareReportDao dao = new DeclareReportDao();
		// 条件处理
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		String userId = condMap.get("userId");
		if (StringKit.isValid(userId)) {
			queryMap.put("userId", Integer.parseInt(userId));
		}
		String declareType = condMap.get("declareType");
		if (StringKit.isValid(declareType)) {
			queryMap.put("declareType", Integer.parseInt(declareType));
		}
		String declareStatus = condMap.get("declareStatus");
		if (StringKit.isValid(declareStatus)) {
			queryMap.put("declareStatus", declareStatus);
		}
		String province = condMap.get("province");
		if (StringKit.isValid(province)) {
			queryMap.put("province", province);
		}
		String city = condMap.get("city");
		if (StringKit.isValid(city)) {
			queryMap.put("city", city);
		}
		String region = condMap.get("region");
		if (StringKit.isValid(region)) {
			queryMap.put("region", region);
		}
		String declareYear = condMap.get("declareYear");
		if (StringKit.isValid(declareYear)) {
			queryMap.put("declareYear", declareYear);
		}
		String identityNumber = condMap.get("identityNumber");
		if (StringKit.isValid(identityNumber)) {
			queryMap.put("identityNumber", identityNumber);
		}
		String authStatus = condMap.get("authStatus");
		if (StringKit.isValid(authStatus)) {
			queryMap.put("authStatus", authStatus);
		}
		String status = condMap.get("status");
		if (StringKit.isValid(status)) {
			queryMap.put("status", Integer.parseInt(status));
		}
		return dao.getDeclareReportListByMap(queryMap);
	}
}
