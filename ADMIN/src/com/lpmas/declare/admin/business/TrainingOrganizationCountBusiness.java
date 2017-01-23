package com.lpmas.declare.admin.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.framework.config.Constants;

public class TrainingOrganizationCountBusiness {
	// 组装培训机构数目的List
	public List<String> getTrainingOrganizationCountList(TrainingOrganizationInfoBean trainingOrganizationInfoBean,
			String level) {
		List<String> countList = new ArrayList<String>();
		countList.add(level);
		countList.add(String.valueOf(Constants.STATUS_VALID));
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NGX)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NMZYXY)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NJHXX)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NJTGFWJG)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NYGX)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NKYS)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NYXZZGBM)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_HZS)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_LYENLQY)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_QIGBJG)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_QIMBJG)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		return countList;
	}

	public List<String> getTrainingOrganizationCountList(TrainingOrganizationInfoBean trainingOrganizationInfoBean,
			List<String> countList) {
		countList.set(1, String.valueOf(Integer.parseInt(countList.get(1)) + 1));
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NGX)) {
			countList.set(2, String.valueOf(Integer.parseInt(countList.get(2)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NMZYXY)) {
			countList.set(3, String.valueOf(Integer.parseInt(countList.get(3)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NJHXX)) {
			countList.set(4, String.valueOf(Integer.parseInt(countList.get(4)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NJTGFWJG)) {
			countList.set(5, String.valueOf(Integer.parseInt(countList.get(5)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NYGX)) {
			countList.set(6, String.valueOf(Integer.parseInt(countList.get(6)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NKYS)) {
			countList.set(7, String.valueOf(Integer.parseInt(countList.get(7)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NYXZZGBM)) {
			countList.set(8, String.valueOf(Integer.parseInt(countList.get(8)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_HZS)) {
			countList.set(9, String.valueOf(Integer.parseInt(countList.get(9)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_LYENLQY)) {
			countList.set(10, String.valueOf(Integer.parseInt(countList.get(10)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_QIGBJG)) {
			countList.set(11, String.valueOf(Integer.parseInt(countList.get(11)) + 1));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_QIMBJG)) {
			countList.set(12, String.valueOf(Integer.parseInt(countList.get(12)) + 1));
		}
		return countList;
	}

	public List<String> getTrainingOrganizationNumberCountList(
			TrainingOrganizationInfoBean trainingOrganizationInfoBean, String level, int trainingType) {
		List<String> countList = new ArrayList<String>();
		countList.add(level);
		int trainingOrganizationNumber = 0;
		if (trainingOrganizationInfoBean.getOrganizationType()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)) {
			TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
			TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			if (trainingType != 0) {
				condMap.put("trainingType", String.valueOf(trainingType));
			}
			condMap.put("organizationId", String.valueOf(trainingOrganizationInfoBean.getOrganizationId()));
			List<TrainingClassInfoBean> trainingClassInfoList = trainingClassInfoBusiness
					.getTrainingClassInfoListByMap(condMap);
			for (TrainingClassInfoBean trainingClassInfoBean : trainingClassInfoList) {
				HashMap<String, String> userMap = new HashMap<String, String>();
				userMap.put("status", String.valueOf(Constants.STATUS_VALID));
				userMap.put("classId", String.valueOf(trainingClassInfoBean.getClassId()));
				trainingOrganizationNumber = trainingOrganizationNumber
						+ trainingClassUserBusiness.getTrainingClassUserRecordResultByMap(userMap);
			}
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			if (trainingType != 0) {
				condMap.put("declareType", String.valueOf(trainingType));
			}
			condMap.put("authOrganizationId", String.valueOf(trainingOrganizationInfoBean.getOrganizationId()));
			trainingOrganizationNumber = declareInfoBusiness.getDeclareInfoRecordResultByMap(condMap);
			countList.add(String.valueOf(trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NGX)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NMZYXY)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NJHXX)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NJTGFWJG)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NYGX)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NKYS)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NYXZZGBM)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_HZS)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_LYENLQY)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_QIGBJG)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_QIMBJG)) {
			countList.add(String.valueOf(trainingOrganizationNumber));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		return countList;
	}

	public List<String> getTrainingOrganizationNumberCountList(
			TrainingOrganizationInfoBean trainingOrganizationInfoBean, List<String> countList, int trainingType) {
		int trainingOrganizationNumber = 0;
		if (trainingOrganizationInfoBean.getOrganizationType()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)) {
			TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
			TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			if (trainingType != 0) {
				condMap.put("trainingType", String.valueOf(trainingType));
			}
			condMap.put("organizationId", String.valueOf(trainingOrganizationInfoBean.getOrganizationId()));
			List<TrainingClassInfoBean> trainingClassInfoList = trainingClassInfoBusiness
					.getTrainingClassInfoListByMap(condMap);
			for (TrainingClassInfoBean trainingClassInfoBean : trainingClassInfoList) {
				HashMap<String, String> userMap = new HashMap<String, String>();
				userMap.put("status", String.valueOf(Constants.STATUS_VALID));
				userMap.put("classId", String.valueOf(trainingClassInfoBean.getClassId()));
				trainingOrganizationNumber = trainingOrganizationNumber
						+ trainingClassUserBusiness.getTrainingClassUserRecordResultByMap(userMap);
			}
			countList.set(1, String.valueOf(Integer.parseInt(countList.get(1)) + trainingOrganizationNumber));
		} else {
			DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			if (trainingType != 0) {
				condMap.put("declareType", String.valueOf(trainingType));
			}
			condMap.put("authOrganizationId", String.valueOf(trainingOrganizationInfoBean.getOrganizationId()));
			trainingOrganizationNumber = declareInfoBusiness.getDeclareInfoRecordResultByMap(condMap);
			countList.set(1, String.valueOf(Integer.parseInt(countList.get(1)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NGX)) {
			countList.set(2, String.valueOf(Integer.parseInt(countList.get(2)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NMZYXY)) {
			countList.set(3, String.valueOf(Integer.parseInt(countList.get(3)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NJHXX)) {
			countList.set(4, String.valueOf(Integer.parseInt(countList.get(4)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NJTGFWJG)) {
			countList.set(5, String.valueOf(Integer.parseInt(countList.get(5)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NYGX)) {
			countList.set(6, String.valueOf(Integer.parseInt(countList.get(6)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NKYS)) {
			countList.set(7, String.valueOf(Integer.parseInt(countList.get(7)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_NYXZZGBM)) {
			countList.set(8, String.valueOf(Integer.parseInt(countList.get(8)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_HZS)) {
			countList.set(9, String.valueOf(Integer.parseInt(countList.get(9)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_LYENLQY)) {
			countList.set(10, String.valueOf(Integer.parseInt(countList.get(10)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_QIGBJG)) {
			countList.set(11, String.valueOf(Integer.parseInt(countList.get(11)) + trainingOrganizationNumber));
		}
		if (trainingOrganizationInfoBean.getOrganizationCategory()
				.equals(TrainingOrganizationConfig.ORGANIZATION_TYPE_QIMBJG)) {
			countList.set(12, String.valueOf(Integer.parseInt(countList.get(12)) + trainingOrganizationNumber));
		}
		return countList;
	}
}
