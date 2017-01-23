package com.lpmas.declare.portal.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.declare.invoker.bean.ClassRoomMemberAddBean;
import com.lpmas.declare.portal.dao.TrainingClassUserDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class TrainingClassUserBusiness {
	public int addTrainingClassUser(TrainingClassUserBean bean) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.insertTrainingClassUser(bean);
	}

	public int updateTrainingClassUser(TrainingClassUserBean bean) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.updateTrainingClassUser(bean);
	}

	public TrainingClassUserBean getTrainingClassUserByKey(int classId, int declareId) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserByKey(classId, declareId);
	}

	public PageResultBean<TrainingClassUserBean> getTrainingClassUserPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserPageListByMap(condMap, pageBean);
	}

	public List<TrainingClassUserBean> getTrainingClassUserListByMap(HashMap<String, String> condMap) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserListByMap(condMap);
	}

	public List<TrainingClassUserBean> getTrainingClassUserListByClass(int classId) {
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("classId", String.valueOf(classId));
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserListByMap(condMap);
	}
	
	public ClassRoomMemberAddBean trainingClassUser2MemberAddBean(TrainingClassUserBean bean) {
		ClassRoomMemberAddBean memberAddBean = new ClassRoomMemberAddBean();
		memberAddBean.setClassroomId(String.valueOf(bean.getClassId()));
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(bean.getDeclareId());
		if (declareInfoBean != null) {
			memberAddBean.setUserId(String.valueOf(declareInfoBean.getUserId()));
		}
		return memberAddBean;
	}

	public ClassRoomMemberAddBean trainingClassUser2MemberDeleteBean(TrainingClassUserBean bean) {
		ClassRoomMemberAddBean memberAddBean = new ClassRoomMemberAddBean();
		memberAddBean.setClassroomId(String.valueOf(bean.getClassId()));
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(bean.getDeclareId());
		if (declareInfoBean != null) {
			memberAddBean.setUserId(String.valueOf(declareInfoBean.getUserId()));
		}
		return memberAddBean;
	}

}
