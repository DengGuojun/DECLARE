package com.lpmas.declare.migrate.business;

import java.util.HashMap;

import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.dao.TabRegionInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TabRegionInfoBusiness {
	public int addTabRegionInfo(TabRegionInfoBean bean) {
		TabRegionInfoDao dao = new TabRegionInfoDao();
		return dao.insertTabRegionInfo(bean);
	}

	public int updateTabRegionInfo(TabRegionInfoBean bean) {
		TabRegionInfoDao dao = new TabRegionInfoDao();
		return dao.updateTabRegionInfo(bean);
	}

	public TabRegionInfoBean getTabRegionInfoByKey(int regionId) {
		TabRegionInfoDao dao = new TabRegionInfoDao();
		return dao.getTabRegionInfoByKey(regionId);
	}

	public PageResultBean<TabRegionInfoBean> getTabRegionInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TabRegionInfoDao dao = new TabRegionInfoDao();
		return dao.getTabRegionInfoPageListByMap(condMap, pageBean);
	}

	public TabRegionInfoBean getTabRegionInfoByCode(String regionCode) {
		TabRegionInfoDao dao = new TabRegionInfoDao();
		return dao.getTabRegionInfoByRegionCode(regionCode);
	}

}