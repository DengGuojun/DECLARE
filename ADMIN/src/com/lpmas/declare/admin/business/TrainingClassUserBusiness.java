package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.dao.TrainingClassUserDao;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.IndustryInfoBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.declare.invoker.bean.ClassRoomMemberAddBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.region.bean.CityInfoBean;
import com.lpmas.region.bean.ProvinceInfoBean;
import com.lpmas.region.bean.RegionInfoBean;
import com.lpmas.region.client.RegionServiceClient;

public class TrainingClassUserBusiness {
	public int addTrainingClassUser(TrainingClassUserBean bean) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.insertTrainingClassUser(bean);
	}

	public int addTrainingClassUserWithCreateTime(TrainingClassUserBean bean) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.insertTrainingClassUserWithCreateTime(bean);
	}

	public int updateTrainingClassUser(TrainingClassUserBean bean) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.updateTrainingClassUser(bean);
	}

	public TrainingClassUserBean getTrainingClassUserByKey(int classId, int declareId) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserByKey(classId, declareId);
	}

	public TrainingClassUserBean getTrainingClassUserByMemo(String memo) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserByMemo(memo);
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

	public int getTrainingClassUserCountByCondition(String province, String city, String region, String trainingYear,
			String userStatus,int declareType) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserCountByCondition(province, city, region, trainingYear, userStatus,declareType);
	}

	public int getTrainingClassUserRecordResultByMap(HashMap<String, String> condMap) {
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserRecordResultByMap(condMap);
	}

	public List<TrainingClassUserBean> getTrainingClassUserListByClass(int classId) {
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("classId", String.valueOf(classId));
		TrainingClassUserDao dao = new TrainingClassUserDao();
		return dao.getTrainingClassUserListByMap(condMap);
	}

	public ClassRoomMemberAddBean trainingClassUser2MemberAddBean(TrainingClassUserBean bean) throws Exception {
		ClassRoomMemberAddBean memberAddBean = new ClassRoomMemberAddBean();
		memberAddBean.setClassroomId(String.valueOf(bean.getClassId()));
		DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
		DeclareReportBean declareReportBean = declareReportBusiness.getDeclareReportByKey(String.valueOf(bean.getDeclareId()));
		if (declareReportBean != null) {
			memberAddBean.setUserId(String.valueOf(declareReportBean.getUserId()));
		}
		memberAddBean.setIdcard(declareReportBean.getIdentityNumber());
		memberAddBean.setTrueName(declareReportBean.getUserName());
		RegionServiceClient client = new RegionServiceClient();
		ProvinceInfoBean provinceBean = client.getProvinceInfoByName(declareReportBean.getProvince());
		memberAddBean.setProvinceCode(provinceBean != null ? provinceBean.getProvinceCode() : "");
		CityInfoBean cityBean = client.getCityInfoByName(declareReportBean.getCity());
		memberAddBean.setCityCode(cityBean != null ? cityBean.getCityCode() : "");
		RegionInfoBean regionBean = client.getRegionInfoByName(declareReportBean.getRegion());
		memberAddBean.setCountyCode(regionBean != null ? regionBean.getRegionCode() : "");
		IndustryInfoBusiness industryInfoBusiness = new IndustryInfoBusiness();
		IndustryInfoBean industryInfoBean = industryInfoBusiness.getIndustryInfoByKey(declareReportBean.getIndustryId1());
		String industryName = industryInfoBean != null ? industryInfoBean.getIndustryName() : "";
		memberAddBean.setTradeName(industryName);
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
