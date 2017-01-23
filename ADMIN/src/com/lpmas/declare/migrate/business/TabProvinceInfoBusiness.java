package com.lpmas.declare.migrate.business;

import java.util.HashMap;

import com.lpmas.declare.migrate.bean.TabProvinceInfoBean;
import com.lpmas.declare.migrate.dao.TabProvinceInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TabProvinceInfoBusiness {
	public int addTabProvinceInfo(TabProvinceInfoBean bean) {
		TabProvinceInfoDao dao = new TabProvinceInfoDao();
		return dao.insertTabProvinceInfo(bean);
	}

	public int updateTabProvinceInfo(TabProvinceInfoBean bean) {
		TabProvinceInfoDao dao = new TabProvinceInfoDao();
		return dao.updateTabProvinceInfo(bean);
	}

	public TabProvinceInfoBean getTabProvinceInfoByKey(int provinceId) {
		TabProvinceInfoDao dao = new TabProvinceInfoDao();
		return dao.getTabProvinceInfoByKey(provinceId);
	}

	public PageResultBean<TabProvinceInfoBean> getTabProvinceInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TabProvinceInfoDao dao = new TabProvinceInfoDao();
		return dao.getTabProvinceInfoPageListByMap(condMap, pageBean);
	}
	
	public TabProvinceInfoBean getProvinceByCode(String provinceCode){
		TabProvinceInfoDao dao = new TabProvinceInfoDao();
		return dao.getTabProvinceInfoByProvinceCode(provinceCode) ;
	}

}