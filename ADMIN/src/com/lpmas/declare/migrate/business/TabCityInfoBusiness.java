package com.lpmas.declare.migrate.business;

import java.util.HashMap;

import com.lpmas.declare.migrate.bean.TabCityInfoBean;
import com.lpmas.declare.migrate.dao.TabCityInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TabCityInfoBusiness {
	public int addTabCityInfo(TabCityInfoBean bean) {
		TabCityInfoDao dao = new TabCityInfoDao();
		return dao.insertTabCityInfo(bean);
	}

	public int updateTabCityInfo(TabCityInfoBean bean) {
		TabCityInfoDao dao = new TabCityInfoDao();
		return dao.updateTabCityInfo(bean);
	}

	public TabCityInfoBean getTabCityInfoByKey(int cityId) {
		TabCityInfoDao dao = new TabCityInfoDao();
		return dao.getTabCityInfoByKey(cityId);
	}

	public PageResultBean<TabCityInfoBean> getTabCityInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TabCityInfoDao dao = new TabCityInfoDao();
		return dao.getTabCityInfoPageListByMap(condMap, pageBean);
	}

	public TabCityInfoBean getCityByCode(String cityCode) {
		TabCityInfoDao dao = new TabCityInfoDao();
		return dao.getTabCityInfoByCityCode(cityCode);
	}

}