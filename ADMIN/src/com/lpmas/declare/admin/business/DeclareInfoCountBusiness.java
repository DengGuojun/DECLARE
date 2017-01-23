package com.lpmas.declare.admin.business;

import java.util.ArrayList;
import java.util.List;

import com.lpmas.constant.user.GenderConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.IndustryTypeBean;
import com.lpmas.declare.bean.JobTypeBean;
import com.lpmas.declare.business.DeclareInfoHelper;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.FarmerInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.DateKit;

public class DeclareInfoCountBusiness {
	// 组装统计申报人数的List
	public List<String> getDeclareInfoCountList(DeclareReportBean declareReportBean, String level) {
		List<String> countList = new ArrayList<String>();
		countList.add(level);
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
			}
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
			}
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
			}
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
			}
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
			}
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		return countList;
	}

	public List<String> getDeclareInfoCountList(DeclareReportBean declareReportBean, List<String> countList) {
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {
			countList.set(1, String.valueOf(Integer.parseInt(countList.get(1)) + 1));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.set(2, String.valueOf(Integer.parseInt(countList.get(2)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.set(3, String.valueOf(Integer.parseInt(countList.get(3)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.set(4, String.valueOf(Integer.parseInt(countList.get(4)) + 1));
			}
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
			countList.set(5, String.valueOf(Integer.parseInt(countList.get(5)) + 1));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.set(6, String.valueOf(Integer.parseInt(countList.get(6)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.set(7, String.valueOf(Integer.parseInt(countList.get(7)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.set(8, String.valueOf(Integer.parseInt(countList.get(8)) + 1));
			}
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
			countList.set(9, String.valueOf(Integer.parseInt(countList.get(9)) + 1));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.set(10, String.valueOf(Integer.parseInt(countList.get(10)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.set(11, String.valueOf(Integer.parseInt(countList.get(11)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.set(12, String.valueOf(Integer.parseInt(countList.get(12)) + 1));
			}
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER) {
			countList.set(13, String.valueOf(Integer.parseInt(countList.get(13)) + 1));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.set(14, String.valueOf(Integer.parseInt(countList.get(14)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.set(15, String.valueOf(Integer.parseInt(countList.get(15)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.set(16, String.valueOf(Integer.parseInt(countList.get(16)) + 1));
			}
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {
			countList.set(17, String.valueOf(Integer.parseInt(countList.get(17)) + 1));
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				countList.set(18, String.valueOf(Integer.parseInt(countList.get(18)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					|| declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				countList.set(19, String.valueOf(Integer.parseInt(countList.get(19)) + 1));
			}
			if (declareReportBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				countList.set(20, String.valueOf(Integer.parseInt(countList.get(20)) + 1));
			}
		}
		return countList;
	}

	public List<String> getDeclareDetailInfoCountList(DeclareReportBean declareReportBean, String level) {
		List<String> countList = new ArrayList<String>();
		countList.add(level);
		countList.add(String.valueOf(Constants.STATUS_VALID));
		// 性别
		if (declareReportBean.getUserGender() == GenderConfig.GENDER_MALE) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_VALID));
		}
		// 年龄
		if (declareReportBean.getUserBirthday() != null) {
			DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
			Integer nowYear = declareInfoHelper.getDeclareYear();
			String userBirthYear = DateKit.formatDate(declareReportBean.getUserBirthday(), DateKit.REGEX_YEAR);
			Integer age = nowYear - Integer.valueOf(userBirthYear);
			if (age >= 18 && age <= 25) {
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			} else if (age >= 26 && age <= 35) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			} else if (age >= 36 && age <= 45) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			} else if (age >= 46 && age <= 60) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			} else if (age > 60) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
			} else {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		// 文化程度
		if (declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_NO_EDUCATED)
				|| declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_PRIMARY_SCHOOL)) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

		} else if (declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_MIDDLE_SCHOOL)) {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

		} else if (declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_HEIGHT_SCHOOL)) {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

		} else if (declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_COLLEGE)
				|| declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_UNIVERSITY_AND_ABOVE)) {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_VALID));

		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER
				|| declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER
				|| declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
			// 政治面貌
			if (declareReportBean.getPoliticalStatus().equals(FarmerInfoConfig.POLITICAL_STATUS_CPC)) {
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			} else if (declareReportBean.getPoliticalStatus().equals(FarmerInfoConfig.POLITICAL_STATUS_CYL)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			} else if (declareReportBean.getPoliticalStatus().equals(FarmerInfoConfig.POLITICAL_STATUS_PEOPLE)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			} else if (declareReportBean.getPoliticalStatus().equals(FarmerInfoConfig.POLITICAL_STATUS_OTHER)) {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_VALID));
			} else {
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
			}
			// 主体产业
			IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
			List<IndustryTypeBean> industryTypeList = industryTypeBusiness.getIndustryTypeAllList();
			for (IndustryTypeBean industryTypeBean : industryTypeList) {
				if (declareReportBean.getIndustryTypeId1() == industryTypeBean.getTypeId()) {
					countList.add(String.valueOf(Constants.STATUS_VALID));
				} else {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				}
			}
			// 人员类别
			if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {
				if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING)) {
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_STUDENTS_ENTREPRENEURSHIP)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COLLEGE_GRADUATES)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_MIGRANT_WORKERS)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

				} else if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_VETERAN)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));

				} else {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				}

			}
			if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
				if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING)) {
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_ENTERPRISE_BOSS)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_STUDENTS_ENTREPRENEURSHIP)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COLLEGE_GRADUATES)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_MIGRANT_WORKERS)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_VETERAN)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
				} else {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				}

			}
			if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
				if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING)) {
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_ENTERPRISE_BOSS)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_SERVICE_ORGANIZER)) {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_VALID));
				} else {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				}
			}
		} else {
			// 工种
			JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
			List<JobTypeBean> jobTypeList = jobTypeBusiness.getJobTypeAllList();
			for (JobTypeBean jobTypeBean : jobTypeList) {
				if (declareReportBean.getJobType() == jobTypeBean.getTypeId()) {
					countList.add(String.valueOf(Constants.STATUS_VALID));
				} else {
					countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
				}
			}
		}
		return countList;
	}

	public List<String> getDeclareDetailInfoCountList(DeclareReportBean declareReportBean, List<String> countList) {
		countList.set(1, String.valueOf(Integer.parseInt(countList.get(1)) + 1));
		// 性别
		if (declareReportBean.getUserGender() == GenderConfig.GENDER_MALE) {
			countList.set(2, String.valueOf(Integer.parseInt(countList.get(2)) + 1));
		} else {
			countList.set(3, String.valueOf(Integer.parseInt(countList.get(3)) + 1));
		}
		// 年龄
		if (declareReportBean.getUserBirthday() != null) {
			DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
			Integer nowYear = declareInfoHelper.getDeclareYear();
			String userBirthYear = DateKit.formatDate(declareReportBean.getUserBirthday(), DateKit.REGEX_YEAR);
			Integer age = nowYear - Integer.valueOf(userBirthYear);
			if (age >= 18 && age <= 25) {
				countList.set(4, String.valueOf(Integer.parseInt(countList.get(4)) + 1));
			} else if (age >= 26 && age <= 35) {
				countList.set(5, String.valueOf(Integer.parseInt(countList.get(5)) + 1));
			} else if (age >= 36 && age <= 45) {
				countList.set(6, String.valueOf(Integer.parseInt(countList.get(6)) + 1));
			} else if (age >= 46 && age <= 60) {
				countList.set(7, String.valueOf(Integer.parseInt(countList.get(7)) + 1));
			} else if (age > 60) {
				countList.set(8, String.valueOf(Integer.parseInt(countList.get(8)) + 1));
			}
		}
		// 文化程度
		if (declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_NO_EDUCATED)
				|| declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_PRIMARY_SCHOOL)) {
			countList.set(9, String.valueOf(Integer.parseInt(countList.get(9)) + 1));

		} else if (declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_MIDDLE_SCHOOL)) {
			countList.set(10, String.valueOf(Integer.parseInt(countList.get(10)) + 1));

		} else if (declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_HEIGHT_SCHOOL)) {
			countList.set(11, String.valueOf(Integer.parseInt(countList.get(11)) + 1));

		} else if (declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_COLLEGE)
				|| declareReportBean.getEducation().equals(FarmerInfoConfig.EDUCATION_LEVEL_UNIVERSITY_AND_ABOVE)) {
			countList.set(12, String.valueOf(Integer.parseInt(countList.get(12)) + 1));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER
				|| declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER
				|| declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
			// 政治面貌
			if (declareReportBean.getPoliticalStatus().equals(FarmerInfoConfig.POLITICAL_STATUS_CPC)) {
				countList.set(13, String.valueOf(Integer.parseInt(countList.get(13)) + 1));
			} else if (declareReportBean.getPoliticalStatus().equals(FarmerInfoConfig.POLITICAL_STATUS_CYL)) {
				countList.set(14, String.valueOf(Integer.parseInt(countList.get(14)) + 1));
			} else if (declareReportBean.getPoliticalStatus().equals(FarmerInfoConfig.POLITICAL_STATUS_PEOPLE)) {
				countList.set(15, String.valueOf(Integer.parseInt(countList.get(15)) + 1));
			} else if (declareReportBean.getPoliticalStatus().equals(FarmerInfoConfig.POLITICAL_STATUS_OTHER)) {
				countList.set(16, String.valueOf(Integer.parseInt(countList.get(16)) + 1));
			}
			// 主体产业
			IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
			List<IndustryTypeBean> industryTypeList = industryTypeBusiness.getIndustryTypeAllList();
			int tempFlag = 16;
			for (IndustryTypeBean industryTypeBean : industryTypeList) {
				++tempFlag;
				if (declareReportBean.getIndustryTypeId1() == industryTypeBean.getTypeId()) {
					countList.set(tempFlag, String.valueOf(Integer.parseInt(countList.get(tempFlag)) + 1));
				}
			}
			tempFlag = 17 + industryTypeList.size();
			if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {
				if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING)) {
					countList.set(tempFlag + 1, String.valueOf(Integer.parseInt(countList.get(tempFlag + 1)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS)) {
					countList.set(tempFlag + 2, String.valueOf(Integer.parseInt(countList.get(tempFlag + 2)) + 1));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER)) {
					countList.set(tempFlag + 3, String.valueOf(Integer.parseInt(countList.get(tempFlag + 3)) + 1));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE)) {
					countList.set(tempFlag + 4, String.valueOf(Integer.parseInt(countList.get(tempFlag + 4)) + 1));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_STUDENTS_ENTREPRENEURSHIP)) {
					countList.set(tempFlag + 5, String.valueOf(Integer.parseInt(countList.get(tempFlag + 5)) + 1));
					;

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COLLEGE_GRADUATES)) {
					countList.set(tempFlag + 6, String.valueOf(Integer.parseInt(countList.get(tempFlag + 6)) + 1));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_MIGRANT_WORKERS)) {
					countList.set(tempFlag + 7, String.valueOf(Integer.parseInt(countList.get(tempFlag + 7)) + 1));

				} else if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_VETERAN)) {
					countList.set(tempFlag + 8, String.valueOf(Integer.parseInt(countList.get(tempFlag + 8)) + 1));

				}

			}
			if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
				if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING)) {
					countList.set(tempFlag + 1, String.valueOf(Integer.parseInt(countList.get(tempFlag + 1)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS)) {
					countList.set(tempFlag + 2, String.valueOf(Integer.parseInt(countList.get(tempFlag + 2)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER)) {
					countList.set(tempFlag + 3, String.valueOf(Integer.parseInt(countList.get(tempFlag + 3)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE)) {
					countList.set(tempFlag + 4, String.valueOf(Integer.parseInt(countList.get(tempFlag + 4)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_ENTERPRISE_BOSS)) {
					countList.set(tempFlag + 5, String.valueOf(Integer.parseInt(countList.get(tempFlag + 5)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_SERVICE_ORGANIZER)) {
					countList.set(tempFlag + 6, String.valueOf(Integer.parseInt(countList.get(tempFlag + 6)) + 1));
				}
			}
			if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
				if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING)) {
					countList.set(tempFlag + 1, String.valueOf(Integer.parseInt(countList.get(tempFlag + 1)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS)) {
					countList.set(tempFlag + 2, String.valueOf(Integer.parseInt(countList.get(tempFlag + 2)) + 1));

				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER)) {
					countList.set(tempFlag + 3, String.valueOf(Integer.parseInt(countList.get(tempFlag + 3)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_ENTERPRISE_BOSS)) {
					countList.set(tempFlag + 4, String.valueOf(Integer.parseInt(countList.get(tempFlag + 4)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE)) {
					countList.set(tempFlag + 5, String.valueOf(Integer.parseInt(countList.get(tempFlag + 5)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_STUDENTS_ENTREPRENEURSHIP)) {
					countList.set(tempFlag + 6, String.valueOf(Integer.parseInt(countList.get(tempFlag + 6)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_COLLEGE_GRADUATES)) {
					countList.set(tempFlag + 7, String.valueOf(Integer.parseInt(countList.get(tempFlag + 7)) + 1));
				} else if (declareReportBean.getFarmerType()
						.equals(FarmerInfoConfig.PERSONNEL_CATEGORY_MIGRANT_WORKERS)) {
					countList.set(tempFlag + 8, String.valueOf(Integer.parseInt(countList.get(tempFlag + 8)) + 1));
				} else if (declareReportBean.getFarmerType().equals(FarmerInfoConfig.PERSONNEL_CATEGORY_VETERAN)) {
					countList.set(tempFlag + 9, String.valueOf(Integer.parseInt(countList.get(tempFlag + 9)) + 1));
				}
			}
		}

		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER
				|| declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {
			// 工种
			JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
			List<JobTypeBean> jobTypeList = jobTypeBusiness.getJobTypeAllList();
			int tempFlag = 12;
			for (JobTypeBean jobTypeBean : jobTypeList) {
				++tempFlag;
				if (declareReportBean.getJobType() == jobTypeBean.getTypeId()) {
					countList.set(tempFlag, String.valueOf(Integer.parseInt(countList.get(tempFlag)) + 1));
				}
			}
		}
		return countList;
	}

	public List<String> getDeclareAuthInfoCountList(DeclareReportBean declareReportBean, String level) {
		List<String> countList = new ArrayList<String>();
		countList.add(level);
		countList.add(String.valueOf(Constants.STATUS_VALID));
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {
			countList.add(String.valueOf(Constants.STATUS_VALID));
		} else {
			countList.add(String.valueOf(Constants.STATUS_NOT_VALID));
		}
		return countList;
	}

	public List<String> getDeclareAuthInfoCountList(DeclareReportBean declareReportBean, List<String> countList) {
		countList.set(1, String.valueOf(Integer.parseInt(countList.get(1)) + 1));
		countList.add(String.valueOf(Constants.STATUS_VALID));
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {
			countList.set(2, String.valueOf(Integer.parseInt(countList.get(2)) + 1));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
			countList.set(3, String.valueOf(Integer.parseInt(countList.get(3)) + 1));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
			countList.set(4, String.valueOf(Integer.parseInt(countList.get(4)) + 1));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER) {
			countList.set(5, String.valueOf(Integer.parseInt(countList.get(5)) + 1));
		}
		if (declareReportBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {
			countList.set(6, String.valueOf(Integer.parseInt(countList.get(6)) + 1));
		}
		return countList;
	}

}
