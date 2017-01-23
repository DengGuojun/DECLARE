package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.AnnouncementInfoBean;
import com.lpmas.declare.admin.dao.AnnouncementInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class AnnouncementInfoBusiness {
	public int addAnnouncementInfo(AnnouncementInfoBean bean) {
		AnnouncementInfoDao dao = new AnnouncementInfoDao();
		return dao.insertAnnouncementInfo(bean);
	}

	public int updateAnnouncementInfo(AnnouncementInfoBean bean) {
		AnnouncementInfoDao dao = new AnnouncementInfoDao();
		return dao.updateAnnouncementInfo(bean);
	}

	public AnnouncementInfoBean getAnnouncementInfoByKey(int announcementId) {
		AnnouncementInfoDao dao = new AnnouncementInfoDao();
		return dao.getAnnouncementInfoByKey(announcementId);
	}

	public PageResultBean<AnnouncementInfoBean> getAnnouncementInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		AnnouncementInfoDao dao = new AnnouncementInfoDao();
		return dao.getAnnouncementInfoPageListByMap(condMap, pageBean);
	}

	public List<AnnouncementInfoBean> getAnnouncementInfoListByMap(HashMap<String, String> condMap) {
		AnnouncementInfoDao dao = new AnnouncementInfoDao();
		return dao.getAnnouncementInfoListByMap(condMap);
	}

}