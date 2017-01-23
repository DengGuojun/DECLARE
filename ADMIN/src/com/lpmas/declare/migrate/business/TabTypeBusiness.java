package com.lpmas.declare.migrate.business;

import java.util.HashMap;

import com.lpmas.declare.migrate.bean.TabTypeBean;
import com.lpmas.declare.migrate.dao.TabTypeDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TabTypeBusiness {
	public int addTabType(TabTypeBean bean) {
		TabTypeDao dao = new TabTypeDao();
		return dao.insertTabType(bean);
	}

	public int updateTabType(TabTypeBean bean) {
		TabTypeDao dao = new TabTypeDao();
		return dao.updateTabType(bean);
	}

	public TabTypeBean getTabTypeByKey(int id) {
		TabTypeDao dao = new TabTypeDao();
		return dao.getTabTypeByKey(id);
	}

	public TabTypeBean getTabTypeByCondition(int pid, int sort, int type) {
		TabTypeDao dao = new TabTypeDao();
		return dao.getTabTypeByCondition(pid, sort, type);
	}

	public PageResultBean<TabTypeBean> getTabTypePageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		TabTypeDao dao = new TabTypeDao();
		return dao.getTabTypePageListByMap(condMap, pageBean);
	}

}