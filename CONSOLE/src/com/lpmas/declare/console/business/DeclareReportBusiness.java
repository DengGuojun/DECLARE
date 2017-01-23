package com.lpmas.declare.console.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.console.dao.DeclareReportDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
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

	public PageResultBean<DeclareReportBean> getDeclareReportPageListByMap(HashMap<String, String> condMap,
			HashMap<String, HashMap<String, String>> scopeMap, PageBean pageBean) throws Exception {
		DeclareReportDao dao = new DeclareReportDao();
		// 条件处理
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		String userName = condMap.get("userName");
		if (StringKit.isValid(userName)) {
			queryMap.put("userName", userName);
		}

		return dao.getDeclareReportPageListByMap(queryMap, pageBean);
	}

	public List<DeclareReportBean> getDeclareReportListByMap(HashMap<String, String> condMap,
			HashMap<String, HashMap<String, String>> scopeMap) throws Exception {
		DeclareReportDao dao = new DeclareReportDao();
		// 条件处理
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		String userName = condMap.get("userName");
		if (StringKit.isValid(userName)) {
			queryMap.put("userName", userName);
		}
		return dao.getDeclareReportListByMap(queryMap);
	}

}
