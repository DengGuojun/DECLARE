package com.lpmas.declare.migrate.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.migrate.bean.TabRegionBean;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.dao.TabRegionDao;
import com.lpmas.declare.migrate.dao.TabRegionInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TabRegionBusiness {
	public int addTabRegion(TabRegionBean bean) {
		TabRegionDao dao = new TabRegionDao();
		return dao.insertTabRegion(bean);
	}

	public int updateTabRegion(TabRegionBean bean) {
		TabRegionDao dao = new TabRegionDao();
		return dao.updateTabRegion(bean);
	}

	public TabRegionBean getTabRegionByKey(int id) {
		TabRegionDao dao = new TabRegionDao();
		return dao.getTabRegionByKey(id);
	}

	public PageResultBean<TabRegionBean> getTabRegionPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		TabRegionDao dao = new TabRegionDao();
		return dao.getTabRegionPageListByMap(condMap, pageBean);
	}

	public List<TabRegionBean> getTabRegionListByMap(HashMap<String, String> condMap) {
		TabRegionDao dao = new TabRegionDao();
		return dao.getTabRegionListByMap(condMap);
	}

	public TabRegionInfoBean getRregionByCode(String regionCode) {
		TabRegionInfoDao dao = new TabRegionInfoDao();
		return dao.getTabRegionInfoByRegionCode(regionCode);
	}

	public TabRegionBean getTabRegionByServerId(String serverId) {
		TabRegionDao dao = new TabRegionDao();
		return dao.getTabRegionByServerId(serverId);
	}
}