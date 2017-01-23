package com.lpmas.declare.survey.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.survey.dao.DeclareReportDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

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

	public PageResultBean<DeclareReportBean> getDeclareReportPageListByMap(HashMap<String, String> condMap,
			HashMap<String, List<String>> scopeMap, PageBean pageBean) throws Exception {
		DeclareReportDao dao = new DeclareReportDao();
		// 条件处理
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		String userName = condMap.get("userName");
		if (StringKit.isValid(userName)) {
			queryMap.put("userName", userName);
		}
		String declareStatus = condMap.get("declareStatus");
		if (StringKit.isValid(declareStatus)) {
			queryMap.put("declareStatus", declareStatus);
		} else {
			// 范围条件处理
			List<String> listStatus = scopeMap.get("declareStatusList");
			if (listStatus != null && !listStatus.isEmpty()) {
				BasicDBList searchQueryCondition = new BasicDBList();
				for (String queryStatus : listStatus) {
					BasicDBObject cond = new BasicDBObject();
					cond.append("declareStatus", queryStatus);
					searchQueryCondition.add(cond);
				}
				queryMap.put("$or", searchQueryCondition);
			}
		}
		return dao.getDeclareReportPageListByMap(queryMap, pageBean);
	}
}
