package com.lpmas.declare.admin.business;

import java.util.HashMap;

import com.lpmas.declare.admin.bean.RecommendInfoBean;
import com.lpmas.declare.admin.dao.RecommendInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;


public class RecommendInfoBusiness {
	public int addRecommendInfo(RecommendInfoBean bean) {
		RecommendInfoDao dao = new RecommendInfoDao();
		return dao.insertRecommendInfo(bean);
	}

	public int updateRecommendInfo(RecommendInfoBean bean) {
		RecommendInfoDao dao = new RecommendInfoDao();
		return dao.updateRecommendInfo(bean);
	}

	public RecommendInfoBean getRecommendInfoByKey(int recommendId, String recommendType,String province) {
		RecommendInfoDao dao = new RecommendInfoDao();
		return dao.getRecommendInfoByKey(recommendId, recommendType,province);
	}

	public PageResultBean<RecommendInfoBean> getRecommendInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		RecommendInfoDao dao = new RecommendInfoDao();
		return dao.getRecommendInfoPageListByMap(condMap, pageBean);
	}

}