package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.dao.DeclareReportDao;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class DeclareReportBusiness {

	public int insertDeclareReport(DeclareReportBean bean) throws Exception {
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

	public int getDeclareReportCountByMap(HashMap<String, Object> condMap) {
		DeclareReportDao dao = new DeclareReportDao();
		return dao.getDeclareReportCountByMap(condMap);
	}

	public PageResultBean<DeclareReportBean> getDeclareReportPageListByMap(HashMap<String, String> condMap,
			HashMap<String, List<String>> scopeMap, PageBean pageBean) throws Exception {
		DeclareReportDao dao = new DeclareReportDao();
		// 条件处理
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		String identityNumber = condMap.get("identityNumber");
		if (StringKit.isValid(identityNumber)) {
			queryMap.put("identityNumber", identityNumber);
		}
		String userMobile = condMap.get("userMobile");
		if (StringKit.isValid(userMobile)) {
			queryMap.put("userMobile", userMobile);
		}
		String userName = condMap.get("userName");
		if (StringKit.isValid(userName)) {
			queryMap.put("userName", userName);
		}
		String userId = condMap.get("userId");
		if (StringKit.isValid(userId)) {
			queryMap.put("userId", Integer.parseInt(userId));
		}
		String createUser = condMap.get("createUser");
		if (StringKit.isValid(createUser)) {
			queryMap.put("createUser", Integer.parseInt(createUser));
		}
		String declareType = condMap.get("declareType");
		if (StringKit.isValid(declareType)) {
			queryMap.put("declareType", Integer.parseInt(declareType));
		}
		String registryType = condMap.get("registryType");
		if (StringKit.isValid(registryType)) {
			queryMap.put("registryType", Integer.parseInt(registryType));
		}
		String declareStatus = condMap.get("declareStatus");
		if (StringKit.isValid(declareStatus)) {
			queryMap.put("declareStatus", declareStatus);
		}
		String authStatus = condMap.get("authStatus");
		if (StringKit.isValid(authStatus)) {
			queryMap.put("authStatus", authStatus);
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
		String industryTypeId = condMap.get("industryTypeId");
		if (StringKit.isValid(industryTypeId)) {
			queryMap.put("industryTypeId1", Integer.parseInt(industryTypeId));
		}
		String industryId = condMap.get("industryId");
		if (StringKit.isValid(industryId)) {
			queryMap.put("industryId1", Integer.parseInt(industryId));
		}
		String declareYear = condMap.get("declareYear");
		if (StringKit.isValid(declareYear)) {
			queryMap.put("declareYear", declareYear);
		}
		String status = condMap.get("status");
		if (StringKit.isValid(status)) {
			queryMap.put("status", Integer.parseInt(status));
		}
		// 范围条件处理
		List<String> listStatus = scopeMap.get("declareStatus");
		if (listStatus != null && !listStatus.isEmpty()) {
			BasicDBList searchQueryCondition = new BasicDBList();
			for (String queryStatus : listStatus) {
				BasicDBObject cond = new BasicDBObject();
				cond.append("declareStatus", queryStatus);
				searchQueryCondition.add(cond);
			}
			queryMap.put("$or", searchQueryCondition);
		}
		List<String> userAddStatus = scopeMap.get(TrainingClassUserConfig.USER_STATUS_ADD);
		if (userAddStatus != null && !userAddStatus.isEmpty()) {
			queryMap.put("trainingClassInfoList", Integer.parseInt(userAddStatus.get(0)));
		}

		List<String> userNotAddStatus = scopeMap.get(TrainingClassUserConfig.USER_STATUS_NOT_ADD);
		if (userNotAddStatus != null && !userNotAddStatus.isEmpty()) {
			BasicDBList searchQueryCondition = new BasicDBList();
			for (String queryStatus : userNotAddStatus) {
				BasicDBObject cond = new BasicDBObject();
				cond.append("$ne", Integer.parseInt(queryStatus));
				searchQueryCondition.add(cond);
			}
			queryMap.put("trainingClassInfoList", searchQueryCondition);
		}

		if (scopeMap.containsKey("existClass")) {
			BasicDBObject cond = new BasicDBObject();
			cond.append("$gte", Constants.STATUS_VALID);
			queryMap.put("trainingClassInfoList", cond);
		}

		if (scopeMap.containsKey("existNoClass")) {
			BasicDBList searchQueryCondition = new BasicDBList();
			BasicDBObject cond = new BasicDBObject();
			cond.append("trainingClassInfoList", null);
			searchQueryCondition.add(cond);
			cond = new BasicDBObject();
			cond.append("$size", Constants.STATUS_NOT_VALID);
			BasicDBObject condOther = new BasicDBObject();
			condOther.append("trainingClassInfoList", cond);
			searchQueryCondition.add(condOther);
			queryMap.put("$or", searchQueryCondition);
		}
		return dao.getDeclareReportPageListByMap(queryMap, pageBean);
	}

	public List<DeclareReportBean> getDeclareReportListByMap(HashMap<String, String> condMap,
			HashMap<String, List<String>> scopeMap) throws Exception {
		DeclareReportDao dao = new DeclareReportDao();
		// 条件处理
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		String userId = condMap.get("userId");
		if (StringKit.isValid(userId)) {
			queryMap.put("userId", Integer.parseInt(userId));
		}
		String userName = condMap.get("userName");
		if (StringKit.isValid(userName)) {
			queryMap.put("userName", userName);
		}
		String userMobile = condMap.get("userMobile");
		if (StringKit.isValid(userMobile)) {
			queryMap.put("userMobile", userMobile);
		}
		String declareType = condMap.get("declareType");
		if (StringKit.isValid(declareType)) {
			queryMap.put("declareType", Integer.parseInt(declareType));
		}
		String declareStatus = condMap.get("declareStatus");
		if (StringKit.isValid(declareStatus)) {
			queryMap.put("declareStatus", declareStatus);
		}
		String level = condMap.get("level");
		if (StringKit.isValid(level)) {
			queryMap.put("level", level);
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
		// 范围条件处理
		List<String> listStatus = scopeMap.get("declareStatus");
		if (listStatus != null && !listStatus.isEmpty()) {
			BasicDBList searchQueryCondition = new BasicDBList();
			for (String queryStatus : listStatus) {
				BasicDBObject cond = new BasicDBObject();
				cond.append("declareStatus", queryStatus);
				searchQueryCondition.add(cond);
			}
			queryMap.put("$or", searchQueryCondition);
		}
		return dao.getDeclareReportListByMap(queryMap);
	}

}
