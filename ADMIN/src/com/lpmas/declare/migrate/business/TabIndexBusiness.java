package com.lpmas.declare.migrate.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.migrate.bean.TabIndexBean;
import com.lpmas.declare.migrate.dao.TabIndexDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TabIndexBusiness {
	public int addTabIndex(TabIndexBean bean) {
		TabIndexDao dao = new TabIndexDao();
		return dao.insertTabIndex(bean);
	}

	public int updateTabIndex(TabIndexBean bean) {
		TabIndexDao dao = new TabIndexDao();
		return dao.updateTabIndex(bean);
	}

	public TabIndexBean getTabIndexByKey(int indexId) {
		TabIndexDao dao = new TabIndexDao();
		return dao.getTabIndexByKey(indexId);
	}

	public PageResultBean<TabIndexBean> getTabIndexPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		TabIndexDao dao = new TabIndexDao();
		return dao.getTabIndexPageListByMap(condMap, pageBean);
	}

	public List<TabIndexBean> getTabIndexListByMap(HashMap<String, String> condMap) {
		TabIndexDao dao = new TabIndexDao();
		return dao.getTabIndexListByMap(condMap);
	}

}