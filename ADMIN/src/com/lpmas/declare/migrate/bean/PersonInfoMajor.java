package com.lpmas.declare.migrate.bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.FarmerIndustryInfoConfig;
import com.lpmas.declare.config.FarmerInfoConfig;
import com.lpmas.declare.config.FarmerJobInfoConfig;
import com.lpmas.framework.util.DateKit;

public class PersonInfoMajor {

	public static Map<Integer, String> WORK_KIND_MAP = new HashMap<Integer, String>();
	public static Map<Integer, Map<Integer, String>> WORK_NAME_MAP = new HashMap<Integer, Map<Integer, String>>();
	public static Map<Integer, String> COMPANY_TYPE_MAP = new HashMap<Integer, String>();

	static {
		initWorkKindMap();
		initWorkNameMap();
		initCompanyTypeMap();
	}

	private static void initCompanyTypeMap() {
		COMPANY_TYPE_MAP = new HashMap<Integer, String>();
		COMPANY_TYPE_MAP.put(1, FarmerJobInfoConfig.JOB_COMPANY_TYPE_LARGE_REEDING);
		COMPANY_TYPE_MAP.put(2, FarmerJobInfoConfig.JOB_COMPANY_TYPE_FAMILY_FARMER);
		COMPANY_TYPE_MAP.put(3, FarmerJobInfoConfig.JOB_COMPANY_TYPE_COOPERATIVES);
		COMPANY_TYPE_MAP.put(4, FarmerJobInfoConfig.JOB_COMPANY_TYPE_ENTERPRISE);
		COMPANY_TYPE_MAP.put(5, FarmerJobInfoConfig.JOB_COMPANY_TYPE_GARDEN);
		COMPANY_TYPE_MAP.put(6, FarmerJobInfoConfig.JOB_COMPANY_TYPE_OTHER);
	}

	private static void initWorkKindMap() {
		WORK_KIND_MAP = new HashMap<Integer, String>();
		WORK_KIND_MAP.put(1, "农艺工");
		WORK_KIND_MAP.put(2, "园艺工");
		WORK_KIND_MAP.put(3, "牧草工");
		WORK_KIND_MAP.put(4, "热带作物生产工");
		WORK_KIND_MAP.put(5, "家畜繁殖员");
		WORK_KIND_MAP.put(6, "家畜饲养员");

		WORK_KIND_MAP.put(7, "家禽繁殖员");
		WORK_KIND_MAP.put(8, "家畜繁殖员");
		WORK_KIND_MAP.put(9, "特种动物饲养员");
		WORK_KIND_MAP.put(10, "实验动物养殖员");
		WORK_KIND_MAP.put(11, "渔业生产船员");
		WORK_KIND_MAP.put(12, "水生动物苗种繁育工");

		WORK_KIND_MAP.put(13, "水生植物苗种繁育工");
		WORK_KIND_MAP.put(14, "水生动物饲养工");
		WORK_KIND_MAP.put(15, "水生植物栽培工");
		WORK_KIND_MAP.put(16, "珍珠养殖工");
		WORK_KIND_MAP.put(17, "水产捕捞工");
		WORK_KIND_MAP.put(18, "其他");
	}

	private static void initWorkNameMap() {
		WORK_NAME_MAP = new HashMap<Integer, Map<Integer, String>>();

		Map<Integer, String> MAP_1 = new HashMap<Integer, String>();
		MAP_1.put(1, "粮食作物栽培工");
		MAP_1.put(2, "棉花作物栽培工");
		MAP_1.put(3, "油料作物栽培工");
		MAP_1.put(4, "糖料作物栽培工");
		MAP_1.put(5, "麻");
		MAP_1.put(6, "养料作物栽培工");
		MAP_1.put(7, "啤酒花栽培工");
		MAP_1.put(8, "牧草栽培工");
		MAP_1.put(9, "其他农艺工");
		WORK_NAME_MAP.put(1, MAP_1);

		Map<Integer, String> MAP_2 = new HashMap<Integer, String>();
		MAP_2.put(1, "蔬菜园艺工");
		MAP_2.put(2, "菌类园艺工");
		MAP_2.put(3, "果树园艺工");
		MAP_2.put(4, "花卉园艺工");
		MAP_2.put(5, "茶园园艺工");
		MAP_2.put(6, "蚕桑园艺工");
		MAP_2.put(7, "其他园艺工");
		WORK_NAME_MAP.put(2, MAP_2);

		Map<Integer, String> MAP_3 = new HashMap<Integer, String>();
		MAP_3.put(1, "牧草种子繁育工");
		MAP_3.put(2, "牧草种子检验工");
		MAP_3.put(3, "牧草栽培工");
		MAP_3.put(4, "牧草产品加工工");
		MAP_3.put(5, "其他牧草工");
		WORK_NAME_MAP.put(3, MAP_3);

		Map<Integer, String> MAP_4 = new HashMap<Integer, String>();
		MAP_4.put(1, "橡胶育苗工");
		MAP_4.put(2, "橡胶栽培工");
		MAP_4.put(3, "橡胶割胶工");
		MAP_4.put(4, "橡胶制胶工");
		MAP_4.put(5, "其他天然橡胶生产工");
		MAP_4.put(6, "剑麻栽培工");
		MAP_4.put(7, "剑麻制品工");
		MAP_4.put(8, "剑麻纤维生产工");
		MAP_4.put(9, "热带作物初制工");
		WORK_NAME_MAP.put(4, MAP_4);

		Map<Integer, String> MAP_5 = new HashMap<Integer, String>();
		MAP_5.put(1, "家畜繁殖员");
		WORK_NAME_MAP.put(5, MAP_5);

		Map<Integer, String> MAP_6 = new HashMap<Integer, String>();
		MAP_6.put(1, "牛羊饲养员");
		MAP_6.put(2, "生猪饲养员");
		MAP_6.put(3, "其他家畜饲养员");
		WORK_NAME_MAP.put(6, MAP_6);

		Map<Integer, String> MAP_7 = new HashMap<Integer, String>();
		MAP_7.put(1, "家禽繁殖员");
		WORK_NAME_MAP.put(7, MAP_7);

		Map<Integer, String> MAP_8 = new HashMap<Integer, String>();
		MAP_8.put(1, "鸡的饲养员");
		MAP_8.put(2, "水禽饲养员");
		MAP_8.put(3, "其他家畜饲养员");
		WORK_NAME_MAP.put(8, MAP_8);

		Map<Integer, String> MAP_9 = new HashMap<Integer, String>();
		MAP_9.put(1, "特种禽类饲养员");
		MAP_9.put(2, "特种经济动物繁育员");
		MAP_9.put(3, "药用动物养殖员");
		MAP_9.put(4, "蜜蜂养殖员");
		MAP_9.put(5, "其他特种动物饲养员");
		WORK_NAME_MAP.put(9, MAP_9);

		Map<Integer, String> MAP_10 = new HashMap<Integer, String>();
		MAP_10.put(1, "实验动物养殖员");
		WORK_NAME_MAP.put(10, MAP_10);

		Map<Integer, String> MAP_11 = new HashMap<Integer, String>();
		MAP_11.put(1, "海洋普通渔业船员");
		MAP_11.put(2, "内陆渔业船员");
		MAP_11.put(3, "渔船驾驶人员");
		MAP_11.put(4, "渔船电机员");
		MAP_11.put(5, "渔船无线电操作员");
		MAP_11.put(6, "渔船机驾长");
		MAP_11.put(7, "渔船轮机人员");
		WORK_NAME_MAP.put(11, MAP_11);

		Map<Integer, String> MAP_12 = new HashMap<Integer, String>();
		MAP_12.put(1, "淡水鱼苗种繁育工");
		MAP_12.put(2, "淡水虾、蟹、贝类苗种繁育工");
		MAP_12.put(3, "海水鱼苗种繁育工");
		MAP_12.put(4, "海水虾、蟹、贝类苗种繁育工");
		MAP_12.put(5, "珍稀水生动物苗种繁育工");
		MAP_12.put(6, "其他水生动物苗种繁育工");
		WORK_NAME_MAP.put(12, MAP_12);

		Map<Integer, String> MAP_13 = new HashMap<Integer, String>();
		MAP_13.put(1, "海藻育苗工");
		MAP_13.put(2, "淡水水生植物苗种培育工");
		MAP_13.put(3, "其他水生植物苗种培育工");
		WORK_NAME_MAP.put(13, MAP_13);

		Map<Integer, String> MAP_14 = new HashMap<Integer, String>();
		MAP_14.put(1, "淡水成鱼饲养工");
		MAP_14.put(2, "淡水虾、蟹、贝类饲养工");
		MAP_14.put(3, "海水成鱼饲养工");
		MAP_14.put(4, "海水虾、蟹、贝类饲养工");
		MAP_14.put(5, "珍稀水生动物饲养工");
		MAP_14.put(6, "其他水生动物饲养工");
		WORK_NAME_MAP.put(14, MAP_14);

		Map<Integer, String> MAP_15 = new HashMap<Integer, String>();
		MAP_15.put(1, "水生植物栽培工");
		WORK_NAME_MAP.put(15, MAP_15);

		Map<Integer, String> MAP_16 = new HashMap<Integer, String>();
		MAP_16.put(1, "淡水育珠工");
		MAP_16.put(2, "海水育珠工");
		WORK_NAME_MAP.put(16, MAP_16);

		Map<Integer, String> MAP_17 = new HashMap<Integer, String>();
		MAP_17.put(1, "淡水捕捞工");
		MAP_17.put(2, "海水捕捞工");
		MAP_17.put(3, "水生动植物采集工");
		WORK_NAME_MAP.put(17, MAP_17);

		Map<Integer, String> MAP_18 = new HashMap<Integer, String>();
		MAP_18.put(1, "农产品贮藏加工人员");
		MAP_18.put(2, "其他");
		WORK_NAME_MAP.put(18, MAP_18);
	}

	PersonInfoMajor1Bean major1Bean = null;
	PersonInfoMajor2Bean major2Bean = null;

	public PersonInfoMajor1Bean getMajor1Bean() {
		return major1Bean;
	}

	public void setMajor1Bean(PersonInfoMajor1Bean major1Bean) {
		this.major1Bean = major1Bean;
	}

	public PersonInfoMajor2Bean getMajor2Bean() {
		return major2Bean;
	}

	public void setMajor2Bean(PersonInfoMajor2Bean major2Bean) {
		this.major2Bean = major2Bean;
	}

	public String getName() {
		if (major1Bean != null)
			return major1Bean.getName();
		else
			return major2Bean.getName();
	}

	public int getGender() {
		if (major1Bean != null)
			return major1Bean.getGender();
		else
			return major2Bean.getGender();
	}

	public int getBirthYear() {
		if (major1Bean != null)
			return major1Bean.getBirthYear();
		else
			return major2Bean.getBirthYear();
	}

	public int getBirthMonth() {
		if (major1Bean != null)
			return major1Bean.getBirthMonth();
		else
			return major2Bean.getBirthMonth();
	}

	public String getNation() {
		int nation = 0;
		if (major1Bean != null)
			nation = major1Bean.getNation();
		else
			nation = major2Bean.getNation();

		if (nation > 9)
			return "0" + nation;
		else {
			return String.valueOf(nation);
		}
	}

	public String getEducation() {
		int education = 0;
		if (major1Bean != null)
			education = major1Bean.getEducationDegree();
		else
			education = major2Bean.getEducationDegree();

		if (education == 1)
			return FarmerInfoConfig.EDUCATION_LEVEL_NO_EDUCATED;
		if (education == 2)
			return FarmerInfoConfig.EDUCATION_LEVEL_PRIMARY_SCHOOL;
		if (education == 3)
			return FarmerInfoConfig.EDUCATION_LEVEL_MIDDLE_SCHOOL;
		if (education == 4)
			return FarmerInfoConfig.EDUCATION_LEVEL_HEIGHT_SCHOOL;
		if (education == 5)
			return FarmerInfoConfig.EDUCATION_LEVEL_COLLEGE;
		if (education == 6)
			return FarmerInfoConfig.EDUCATION_LEVEL_UNIVERSITY_AND_ABOVE;

		return "";
	}

	public String getMajor() {
		if (major1Bean != null)
			return major1Bean.getMajor();
		else
			return major2Bean.getMajor();
	}

	public String getIdCardNumber() {
		String id = "";
		if (major1Bean != null)
			id = major1Bean.getIdCardNo();
		else
			id = major2Bean.getIdCardNo();
		id = id.trim();
		if (id.length() > 20) {
			return id.substring(0, 18);
		}
		return id;
	}

	public String getPoliticStatus() {
		int politic = 0;
		if (major1Bean != null)
			politic = major1Bean.getPoliticStatus();
		else
			politic = major2Bean.getPoliticsStatus();

		if (politic == 1)
			return FarmerInfoConfig.POLITICAL_STATUS_CPC;
		if (politic == 2)
			return FarmerInfoConfig.POLITICAL_STATUS_CYL;
		if (politic == 3)
			return FarmerInfoConfig.POLITICAL_STATUS_PEOPLE;
		if (politic == 4)
			return FarmerInfoConfig.POLITICAL_STATUS_OTHER;

		return "";
	}

	public String getFarmerTypeByDeclareType(int declareType) {
		int farmerType = 0;
		if (major1Bean != null)
			farmerType = major1Bean.getPersonType();
		else
			return "";

		if (declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
			if (farmerType == 1)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING;
			if (farmerType == 2)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS;
			if (farmerType == 3)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER;
			if (farmerType == 4)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_ENTERPRISE_BOSS;
			if (farmerType == 5)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE;
			if (farmerType == 6)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_STUDENTS_ENTREPRENEURSHIP;
			if (farmerType == 7)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_COLLEGE_GRADUATES;
			if (farmerType == 8)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_MIGRANT_WORKERS;
			if (farmerType == 9)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_VETERAN;
		} else if (declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {
			if (farmerType == 1)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING;
			if (farmerType == 2)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS;
			if (farmerType == 3)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER;
			if (farmerType == 4)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE;
			if (farmerType == 5)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_STUDENTS_ENTREPRENEURSHIP;
			if (farmerType == 6)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_COLLEGE_GRADUATES;
			if (farmerType == 7)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_MIGRANT_WORKERS;
			if (farmerType == 8)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_VETERAN;
		} else if (declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
			if (farmerType == 1)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING;
			if (farmerType == 2)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS;
			if (farmerType == 3)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER;
			if (farmerType == 4)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE;
			if (farmerType == 5)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_ENTERPRISE_BOSS;
			if (farmerType == 6)
				return FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_SERVICE_ORGANIZER;
		}

		return "";
	}

	public Timestamp getCreateTime() {
		if (major1Bean != null)
			return major1Bean.getCreateTime();
		else
			return major2Bean.getCreateTime();
	}

	public String getMemo() {
		if (major1Bean != null)
			return major1Bean.getRemark();
		else
			return major2Bean.getRemarks();
	}

	public String getMobil() {
		String mobil = "";
		if (major1Bean != null)
			mobil = major1Bean.getPhoneNo();
		else
			mobil = major2Bean.getPhoneNo();
		mobil = mobil.trim();
		if (mobil.length() > 20) {
			return mobil.substring(0, 11);
		}
		return mobil;
	}

	public String getEmail() {
		if (major1Bean != null)
			return major1Bean.getEmail();
		else
			return major2Bean.getEmail();
	}

	public String getWechat() {
		if (major1Bean != null)
			return major1Bean.getWechat();
		else
			return major2Bean.getWechat();
	}

	public String getQQ() {
		if (major1Bean != null)
			return major1Bean.getQqNo();
		else
			return major2Bean.getQqNo();
	}

	public int getFamilyPerson() {
		if (major1Bean != null)
			return major1Bean.getFamilyNum();
		else
			return major2Bean.getFamileAddress();
	}

	public String getAddress() {
		if (major1Bean != null)
			return major1Bean.getHomeAddress();
		else
			return major2Bean.getHomeAddress();
	}

	public String getContactAddress() {
		if (major1Bean != null)
			return major1Bean.getContactAddress();
		else
			return major2Bean.getContactAddress();
	}

	public int getIsTrained() {
		if (major1Bean != null)
			return major1Bean.getIsJoinTrain();
		else
			return major2Bean.getIsJoinTrain();
	}

	public String getApplyType() {
		int applyType = 0;
		if (major1Bean != null)
			applyType = major1Bean.getApplyType();
		else
			return "";

		if (applyType == 1)
			return DeclareInfoConfig.APPLY_TYPE_PERSONAL;
		if (applyType == 2)
			return DeclareInfoConfig.APPLY_TYPE_ENTERPRISES_TRAIN_RECOMMEND;
		if (applyType == 3)
			return DeclareInfoConfig.APPLY_TYPE_DEPARTMENT_RECONMEND;

		return "";
	}

	public String getCertificateGrade() {
		if (major1Bean != null)
			return major1Bean.getCertificateGrade();
		else
			return "";
	}

	public int getOtherTrainTimes() {
		if (major1Bean != null)
			return major1Bean.getOtherTrain();
		else
			return major2Bean.getOtherTrain();
	}

	public String getTrainExp() {
		if (major1Bean != null)
			return major1Bean.getTrainExp();
		else
			return major2Bean.getTrainExp();
	}

	public int getHasNationalCertification() {
		if (major1Bean != null)
			return major1Bean.getHasNationalCertificate();
		else
			return major2Bean.getHasNationalCertificate();
	}

	public int hasNewTypeTrainingCertification() {
		if (major1Bean != null)
			return major1Bean.getHasNewTypeTrainCetificate();
		else
			return major2Bean.getHasNewTypeTrainCertificate();
	}

	public int getHasNewTypeCertification() {
		if (major1Bean != null)
			return major1Bean.getHasNewTypeCetificate();
		else
			return major2Bean.getHasNewTypeCertificate();
	}

	public int getHasNoCertification() {
		if (major1Bean != null)
			return major1Bean.getHasNoCetificate();
		else
			return major2Bean.getHasNoCertificate();
	}

	public String getFarmType() {
		int farmType = major1Bean.getFarmType();
		if (farmType == 1)
			return FarmerIndustryInfoConfig.FAMILY_FARM_TYPE_INDIVIDUAL_BUSINESS;
		if (farmType == 2)
			return FarmerIndustryInfoConfig.FAMILY_FARM_TYPE_INDIVIDUAL_PROPRIETORSHIP;
		if (farmType == 4)
			return FarmerIndustryInfoConfig.FAMILY_FARM_TYPE_LIMITED_LIABILITY_COMPANY;
		return "";
	}

	public String getIdentityGrade() {
		try {
			int identityGrade = 0;
			if (major1Bean != null)
				identityGrade = Integer.valueOf(major1Bean.getIndentityGrade());

			if (identityGrade == 1)
				return FarmerInfoConfig.CERTIFICATION_LEVEL_LOW;
			if (identityGrade == 2)
				return FarmerInfoConfig.CERTIFICATION_LEVEL_INTERMEDIATE;
			if (identityGrade == 3)
				return FarmerInfoConfig.CERTIFICATION_LEVEL_HIGH;

			return "";
		} catch (Exception e) {
			return "";
		}
	}

	public String getIdentityDepartment() {
		if (major1Bean != null)
			return major1Bean.getIdentityDepartment();
		else
			return "";
	}

	public Timestamp getIndentityDate() {
		String date = "";

		try {
			date = major1Bean.getIndentityTime();
			return DateKit.str2Timestamp(date, DateKit.DEFAULT_DATE_FORMAT);
		} catch (Exception e) {
			return null;
		}
	}

}
