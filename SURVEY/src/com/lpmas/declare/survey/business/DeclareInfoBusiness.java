package com.lpmas.declare.survey.business;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.config.DeclareImageInfoConfig;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.survey.dao.DeclareInfoDao;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;

public class DeclareInfoBusiness {
	public int addDeclareInfo(DeclareInfoBean bean) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.insertDeclareInfo(bean);
	}

	public int updateDeclareInfo(DeclareInfoBean bean) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.updateDeclareInfo(bean);
	}

	public DeclareInfoBean getDeclareInfoByKey(int declareId) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoByKey(declareId);
	}

	public DeclareInfoBean getDeclareInfoByCondition(int userId, int declareType) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoByCondition(userId, declareType);
	}

	public List<DeclareInfoBean> getDeclareInfoListByUserId(int userId) {
		DeclareInfoDao dao = new DeclareInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("userId", String.valueOf(userId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getDeclareInfoListByMap(condMap);
	}

	public PageResultBean<DeclareInfoBean> getDeclareInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoPageListByMap(condMap, pageBean);
	}

	public LinkedHashMap<String, Boolean> getModuleFinishedMapByCondition(int declareId, int declareType) {
		LinkedHashMap<String, Boolean> moduleFinishedMap = new LinkedHashMap<String, Boolean>();
		Boolean isFinished = false;
		// 获取信息的BUSSINESS
		FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
		FarmerContactInfoBusiness farmerContactInfoBusiness = new FarmerContactInfoBusiness();
		FarmerSkillInfoBusiness farmerSkillInfoBusiness = new FarmerSkillInfoBusiness();
		FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();
		FarmerJobInfoBusiness farmerJobInfoBusiness = new FarmerJobInfoBusiness();
		DeclareImageBusiness declareImageBusiness = new DeclareImageBusiness();
		// 农民信息
		if (farmerInfoBusiness.getFarmerInfoByKey(declareId) != null && declareImageBusiness
				.getDeclareImageByKey(declareId, DeclareImageInfoConfig.IMG_TYPE_ONE_INCH) != null) {
			isFinished = true;
		}
		moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_INFO, isFinished);
		isFinished = false;
		// 农民联系
		if (farmerContactInfoBusiness.getFarmerContactInfoByKey(declareId) != null) {
			isFinished = true;
		}
		moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_CONTACT_INFO, isFinished);
		isFinished = false;
		// 专业技能
		if (farmerSkillInfoBusiness.getFarmerSkillInfoByKey(declareId) != null) {
			isFinished = true;
		}
		moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_SKILL_INFO, isFinished);
		isFinished = false;
		// 是专业的就要农务工作信息，其他要填农务经营情况
		if (declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER
				|| declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {
			// 要填农务工作信息
			if (farmerJobInfoBusiness.getFarmerJobInfoByKey(declareId) != null) {
				isFinished = true;
			}
			moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_JOB_INFO, isFinished);
			isFinished = false;
		} else {
			// 要填农务经营情况
			if (farmerIndustryInfoBusiness.getFarmerIndustryInfoByKey(declareId) != null) {
				isFinished = true;
			}
			moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_INDUSTRY_INFO, isFinished);
			isFinished = false;
		}

		return moduleFinishedMap;
	}

	public String verifyModule(Map<String, Boolean> finishMap) {
		StringBuffer sb = new StringBuffer();
		for (Entry<String, Boolean> entry : finishMap.entrySet()) {
			if (!entry.getValue()) {
				sb.append("[" + MapKit.getValueFromMap(entry.getKey(), DeclareInfoConfig.DECLARE_MODULE_MAP) + "]"
						+ "模块未完成!");
				break;
			}
		}
		return sb.toString();
	}

}