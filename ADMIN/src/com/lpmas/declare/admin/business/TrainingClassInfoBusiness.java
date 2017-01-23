package com.lpmas.declare.admin.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.lpmas.declare.admin.bean.TrainingItemContentBean;
import com.lpmas.declare.admin.dao.TrainingClassInfoDao;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.TrainingClassInfoConfig;
import com.lpmas.declare.invoker.bean.ClassRoomAddBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.region.bean.CityInfoBean;
import com.lpmas.region.bean.ProvinceInfoBean;
import com.lpmas.region.bean.RegionInfoBean;
import com.lpmas.region.client.RegionServiceClient;

public class TrainingClassInfoBusiness {
	public int addTrainingClassInfo(TrainingClassInfoBean bean) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.insertTrainingClassInfo(bean);
	}
	public int addTrainingClassInfoWithCreateTime(TrainingClassInfoBean bean) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.insertTrainingClassInfoWithCreateTime(bean);
	}

	public int updateTrainingClassInfo(TrainingClassInfoBean bean) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.updateTrainingClassInfo(bean);
	}

	public TrainingClassInfoBean getTrainingClassInfoByKey(int classId) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.getTrainingClassInfoByKey(classId);
	}

	public PageResultBean<TrainingClassInfoBean> getTrainingClassInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.getTrainingClassInfoPageListByMap(condMap, pageBean);
	}
	
	public TrainingClassInfoBean getTrainingClassInfoByMemo(String memo) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.getTrainingClassInfoByMemo(memo);
	}

	public ReturnMessageBean verifyTrainingClassInfo(TrainingClassInfoBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (!StringKit.isValid(bean.getTrainingYear())) {
			result.setMessage("项目年度必须填写");
		} else if (bean.getOrganizationId() == 0) {
			result.setMessage("培训单位必须填写");
		} else if (bean.getMajorTypeId() == 0 || bean.getMajorId() == 0) {
			result.setMessage("培训产业必须填写");
		} else if (bean.getClassPeopleQuantity() == 0) {
			result.setMessage("培训人数必须填写");
		} else if (!StringKit.isValid(bean.getTrainingItem1())) {
			result.setMessage("项目实施第一年必须填写");
		} else if (!StringKit.isValid(bean.getTrainingItem2())) {
			result.setMessage("项目实施第二年必须填写");
		}
		return result;
	}

	public ReturnMessageBean assemblyTrainingItemContentBean(HttpServletRequest request, TrainingClassInfoBean bean) {
		ReturnMessageBean message = new ReturnMessageBean();
		// 组装项目实施内容
		List<TrainingItemContentBean> contentList = null;
		String education1 = ParamKit.getParameter(request, TrainingClassInfoConfig.EDUCATION1, "0");
		String practice1 = ParamKit.getParameter(request, TrainingClassInfoConfig.PRACTICE1, "0");
		String entrepreneurship1 = ParamKit.getParameter(request, TrainingClassInfoConfig.ENTREPRENEURSHIP1, "0");
		String identify1 = ParamKit.getParameter(request, TrainingClassInfoConfig.IDENTIFY1, "0");
		String track1 = ParamKit.getParameter(request, TrainingClassInfoConfig.TRACK1, "0");
		String education2 = ParamKit.getParameter(request, TrainingClassInfoConfig.EDUCATION2, "0");
		String practice2 = ParamKit.getParameter(request, TrainingClassInfoConfig.PRACTICE2, "0");
		String entrepreneurship2 = ParamKit.getParameter(request, TrainingClassInfoConfig.ENTREPRENEURSHIP2, "0");
		String identify2 = ParamKit.getParameter(request, TrainingClassInfoConfig.IDENTIFY2, "0");
		String track2 = ParamKit.getParameter(request, TrainingClassInfoConfig.TRACK2, "0");
		if (education1.equals("0") && practice1.equals("0") && entrepreneurship1.equals("0") && identify1.equals("0")
				&& track1.equals("0")) {
			message.setMessage("项目实施第一年必须填写");
		} else if (education2.equals("0") && practice2.equals("0") && entrepreneurship2.equals("0")
				&& identify2.equals("0") && track2.equals("0")) {
			message.setMessage("项目实施第二年必须填写");
		}
		TrainingItemContentBean contentBean = null;
		contentList = new ArrayList<TrainingItemContentBean>();
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.EDUCATION1);
		contentBean.setValue(education1);
		contentBean.setName("教育培训");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.PRACTICE1);
		contentBean.setValue(practice1);
		contentBean.setName("实践培训");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.ENTREPRENEURSHIP1);
		contentBean.setValue(entrepreneurship1);
		contentBean.setName("创业指导和孵化");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.IDENTIFY1);
		contentBean.setValue(identify1);
		contentBean.setName("认定管理");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.TRACK1);
		contentBean.setValue(track1);
		contentBean.setName("跟踪服务");
		contentList.add(contentBean);
		bean.setTrainingItem1(JsonKit.toJson(contentList));
		contentList = new ArrayList<TrainingItemContentBean>();
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.EDUCATION2);
		contentBean.setValue(education2);
		contentBean.setName("教育培训");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.PRACTICE2);
		contentBean.setValue(practice2);
		contentBean.setName("实践培训");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.ENTREPRENEURSHIP2);
		contentBean.setValue(entrepreneurship2);
		contentBean.setName("创业指导和孵化");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.IDENTIFY2);
		contentBean.setValue(identify2);
		contentBean.setName("认定管理");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		contentBean.setKey(TrainingClassInfoConfig.TRACK2);
		contentBean.setValue(track2);
		contentBean.setName("跟踪服务");
		contentList.add(contentBean);
		bean.setTrainingItem2(JsonKit.toJson(contentList));
		return message;
	}

	public List<TrainingClassInfoBean> getTrainingClassInfoListByMap(HashMap<String, String> condMap) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.getTrainingClassInfoListByMap(condMap);
	}

	public List<TrainingClassInfoBean> getTrainingClassInfoListByOrganization(int organizationId) {
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("organizationId", String.valueOf(organizationId));
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.getTrainingClassInfoListByMap(condMap);
	}
	
	public ClassRoomAddBean trainingClassInfo2ClassRoomAddBean(TrainingClassInfoBean bean) {
		ClassRoomAddBean classRoomAddBean = new ClassRoomAddBean();
		classRoomAddBean.setTitle(bean.getClassName());
		classRoomAddBean
				.setCategory(MapKit.getValueFromMap(bean.getTrainingType(), DeclareInfoConfig.DECLARE_TYPE_MAP));
		classRoomAddBean.setAbout("");
		classRoomAddBean.setClassroomId(String.valueOf(bean.getClassId()));
		classRoomAddBean.setType(String.valueOf(bean.getTrainingType()));
		Date startDate = (Date) bean.getTrainingBeginTime();
		classRoomAddBean.setStartTime(String.valueOf(startDate.getTime() / 1000));
		Date endDate = (Date) bean.getTrainingEndTime();
		classRoomAddBean.setEndTime(String.valueOf(endDate.getTime() / 1000));
		RegionServiceClient client = new RegionServiceClient();
		ProvinceInfoBean provinceBean = client.getProvinceInfoByName(bean.getProvince());
		classRoomAddBean.setProvinceCode(provinceBean != null ? provinceBean.getProvinceCode() : "");
		CityInfoBean cityBean = client.getCityInfoByName(bean.getCity());
		classRoomAddBean.setCityCode(cityBean != null ? cityBean.getCityCode() : "");
		RegionInfoBean regionBean = client.getRegionInfoByName(bean.getRegion());
		classRoomAddBean.setRegionCode(regionBean != null ? regionBean.getRegionCode() : "");
		classRoomAddBean.setEducateYear(bean.getTrainingYear());
		return classRoomAddBean;
	}
		
	public int getTrainingClassInfoRecordResultByMap(HashMap<String, String> condMap) {
		TrainingClassInfoDao dao = new TrainingClassInfoDao();
		return dao.getTrainingClassInfoRecordResultByMap(condMap);
	}
}
