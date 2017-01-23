package com.lpmas.declare.console.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.console.dao.DeclareInfoDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class DeclareInfoBusiness {

	public List<DeclareInfoBean> getDeclareInfoAllList() {
		DeclareInfoDao dao = new DeclareInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getDeclareInfoListByMap(condMap);
	}
	
	public PageResultBean<DeclareInfoBean> getDeclareInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoPageListByMap(condMap, pageBean);
	}

}