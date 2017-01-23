package com.lpmas.declare.admin.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.TargetStatisticsBean;
import com.lpmas.declare.admin.bean.TrainingClassTargetBean;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.dao.TargetStatisticsDao;
import com.lpmas.declare.bean.FarmerIndustryInfoBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.region.business.CityInfoBusiness;
import com.lpmas.declare.region.business.ProvinceInfoBusiness;
import com.lpmas.declare.region.business.RegionInfoBusiness;
import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.NumeralOperationKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.region.bean.CityInfoBean;
import com.lpmas.region.bean.ProvinceInfoBean;
import com.lpmas.region.bean.RegionInfoBean;

public class TargetStatisticsBusiness {
	int industryTypeId1 = -1;
	int industryTypeId2 = -1;
	int industryTypeId3 = -1;
	int industryTypeId4 = -1;
	int industryTypeId5 = -1;
	int industryTypeId6 = -1;
	int industryTypeId7 = -1;
	int industryTypeId8 = -1;
	int industryTypeId9 = -1;
	int industryTypeId10 = -1;

	int jobTypeId1 = -1;
	int jobTypeId2 = -1;
	int jobTypeId3 = -1;
	int jobTypeId4 = -1;
	int jobTypeId5 = -1;
	int jobTypeId6 = -1;
	int jobTypeId7 = -1;
	int jobTypeId8 = -1;
	int jobTypeId9 = -1;
	int jobTypeId10 = -1;
	int jobTypeId11 = -1;
	int jobTypeId12 = -1;
	int jobTypeId13 = -1;
	int jobTypeId14 = -1;
	int jobTypeId15 = -1;
	int jobTypeId16 = -1;
	int jobTypeId17 = -1;
	int jobTypeId18 = -1;

	public TargetStatisticsBusiness() {
		IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
		JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
		industryTypeId1 = industryTypeBusiness.getIndustryTypeByName("粮食作物").getTypeId();
		industryTypeId2 = industryTypeBusiness.getIndustryTypeByName("油料作物").getTypeId();
		industryTypeId3 = industryTypeBusiness.getIndustryTypeByName("经济作物").getTypeId();
		industryTypeId4 = industryTypeBusiness.getIndustryTypeByName("园艺作物").getTypeId();
		industryTypeId5 = industryTypeBusiness.getIndustryTypeByName("家畜").getTypeId();
		industryTypeId6 = industryTypeBusiness.getIndustryTypeByName("家禽").getTypeId();
		industryTypeId7 = industryTypeBusiness.getIndustryTypeByName("特种动物").getTypeId();
		industryTypeId8 = industryTypeBusiness.getIndustryTypeByName("海水").getTypeId();
		industryTypeId9 = industryTypeBusiness.getIndustryTypeByName("淡水").getTypeId();
		industryTypeId10 = industryTypeBusiness.getIndustryTypeByName("其他产业").getTypeId();

		jobTypeId1 = jobTypeBusiness.getJobTypeByName("农艺工").getTypeId();
		jobTypeId2 = jobTypeBusiness.getJobTypeByName("园艺工").getTypeId();
		jobTypeId3 = jobTypeBusiness.getJobTypeByName("牧草工").getTypeId();
		jobTypeId4 = jobTypeBusiness.getJobTypeByName("热带作物生产工").getTypeId();
		jobTypeId5 = jobTypeBusiness.getJobTypeByName("家畜繁殖员").getTypeId();
		jobTypeId6 = jobTypeBusiness.getJobTypeByName("家畜饲养员").getTypeId();
		jobTypeId7 = jobTypeBusiness.getJobTypeByName("家禽繁殖员").getTypeId();
		jobTypeId8 = jobTypeBusiness.getJobTypeByName("家禽饲养员").getTypeId();
		jobTypeId9 = jobTypeBusiness.getJobTypeByName("特种动物饲养员").getTypeId();
		jobTypeId10 = jobTypeBusiness.getJobTypeByName("实验动物养殖员").getTypeId();
		jobTypeId11 = jobTypeBusiness.getJobTypeByName("渔业生产船员").getTypeId();
		jobTypeId12 = jobTypeBusiness.getJobTypeByName("水生动物苗种繁育工").getTypeId();
		jobTypeId13 = jobTypeBusiness.getJobTypeByName("水生植物苗种繁育工").getTypeId();
		jobTypeId14 = jobTypeBusiness.getJobTypeByName("水生动物饲养工").getTypeId();
		jobTypeId15 = jobTypeBusiness.getJobTypeByName("水生植物栽培工").getTypeId();
		jobTypeId16 = jobTypeBusiness.getJobTypeByName("珍珠养殖工").getTypeId();
		jobTypeId17 = jobTypeBusiness.getJobTypeByName("水产捕捞工").getTypeId();
		jobTypeId18 = jobTypeBusiness.getJobTypeByName("其他").getTypeId();
	}

	public int insertTargetStaticsBean(TargetStatisticsBean bean) throws Exception {
		TargetStatisticsDao dao = new TargetStatisticsDao();
		return dao.insertTargetStatistics(bean);
	}

	public long updateTargetStatistics(TargetStatisticsBean bean) {
		TargetStatisticsDao dao = new TargetStatisticsDao();
		return dao.updateTargetStatistics(bean);
	}

	private TargetStatisticsBean getTargetStatisticsByKey(String level, String province, String city, String region,
			String declareYear, boolean isSum) throws Exception {
		TargetStatisticsDao dao = new TargetStatisticsDao();
		return dao.getTargetStatisticsByKey(level, province, city, region, declareYear, isSum);
	}

	public List<TargetStatisticsBean> getTargetStatisticsListByMap(HashMap<String, String> condMap) {
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		TargetStatisticsDao targetStatisticsDao = new TargetStatisticsDao();

		String province = condMap.get("province");
		if (StringKit.isValid(province)) {
			queryMap.put("province", province);
		}
		String city = condMap.get("city");
		if (StringKit.isValid(city)) {
			queryMap.put("city", city);
		}
		String region = condMap.get("region");
		if (StringKit.isValid(region)) {
			queryMap.put("region", region);
		}
		String level = condMap.get("level");
		if (StringKit.isValid(level)) {
			queryMap.put("level", level);
		}
		String declareYear = condMap.get("declareYear");
		if (!StringKit.isNull(declareYear)) {
			queryMap.put("declareYear", declareYear);
		}

		List<TargetStatisticsBean> list = null;
		try {
			list = targetStatisticsDao.getTargetStatisticsListByMap(queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void initTargetAllStatistics(List<Integer> yearList) throws Exception {
		ProvinceInfoBusiness provinceBusiness = new ProvinceInfoBusiness();
		CityInfoBusiness cityBusiness = new CityInfoBusiness();
		RegionInfoBusiness regionBusiness = new RegionInfoBusiness();

		List<ProvinceInfoBean> provinceList = provinceBusiness.getProvinceInfoListByCountryId(1);

		for (int year : yearList) {
			// 统计省级的培训班目标数据
			for (ProvinceInfoBean provinceBean : provinceList) {
				TargetStatisticsBean totalProvinceCountBean = new TargetStatisticsBean();
				TargetStatisticsBean targetStatisticsBean = new TargetStatisticsBean();
				targetStatisticsBean.setDeclareYear(String.valueOf(year));
				targetStatisticsBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
				targetStatisticsBean.setProvince(provinceBean.getProvinceName());

				// 各地完成情况(总)
				targetStatisticsBean = countTargetStatisticsBean(targetStatisticsBean);
				totalProvinceCountBean = countTargetStatisticsBean(totalProvinceCountBean, targetStatisticsBean);

				TargetStatisticsBean orignialBean = getTargetStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE,
						targetStatisticsBean.getProvince(), "", "", String.valueOf(year), false);

				if (orignialBean != null) {
					// 更新
					targetStatisticsBean.set_id(orignialBean.get_id());
					updateTargetStatistics(targetStatisticsBean);
				} else {
					// 新增
					insertTargetStaticsBean(targetStatisticsBean);
				}

				// 统计市级的培训班目标数据
				List<CityInfoBean> cityList = cityBusiness.getCityInfoListByProvinceId(provinceBean.getProvinceId());
				for (CityInfoBean cityBean : cityList) {
					TargetStatisticsBean totalCityCountBean = new TargetStatisticsBean();
					targetStatisticsBean = new TargetStatisticsBean();
					targetStatisticsBean.setDeclareYear(String.valueOf(year));
					targetStatisticsBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
					targetStatisticsBean.setProvince(provinceBean.getProvinceName());
					targetStatisticsBean.setCity(cityBean.getCityName());

					targetStatisticsBean = countTargetStatisticsBean(targetStatisticsBean);
					totalCityCountBean = countTargetStatisticsBean(totalCityCountBean, targetStatisticsBean);
					totalProvinceCountBean = countTargetStatisticsBean(totalProvinceCountBean, targetStatisticsBean);

					orignialBean = getTargetStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_CITY,
							targetStatisticsBean.getProvince(), targetStatisticsBean.getCity(), "",
							String.valueOf(year), false);

					if (orignialBean != null) {
						// 更新
						targetStatisticsBean.set_id(orignialBean.get_id());
						updateTargetStatistics(targetStatisticsBean);
					} else {
						// 新增
						insertTargetStaticsBean(targetStatisticsBean);
					}

					// 统计区级的培训班目标数据
					List<RegionInfoBean> regionList = regionBusiness.getRegionInfoListByCityId(cityBean.getCityId());
					for (RegionInfoBean regionBean : regionList) {
						targetStatisticsBean = new TargetStatisticsBean();
						targetStatisticsBean.setDeclareYear(String.valueOf(year));
						targetStatisticsBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
						targetStatisticsBean.setProvince(provinceBean.getProvinceName());
						targetStatisticsBean.setCity(cityBean.getCityName());
						targetStatisticsBean.setRegion(regionBean.getRegionName());

						targetStatisticsBean = countTargetStatisticsBean(targetStatisticsBean);
						totalCityCountBean = countTargetStatisticsBean(totalCityCountBean, targetStatisticsBean);
						totalProvinceCountBean = countTargetStatisticsBean(totalProvinceCountBean,
								targetStatisticsBean);

						orignialBean = getTargetStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_REGION,
								targetStatisticsBean.getProvince(), targetStatisticsBean.getCity(),
								targetStatisticsBean.getRegion(), String.valueOf(year), false);

						if (orignialBean != null) {
							// 更新
							targetStatisticsBean.set_id(orignialBean.get_id());
							updateTargetStatistics(targetStatisticsBean);
						} else {
							// 新增
							insertTargetStaticsBean(targetStatisticsBean);
						}

					}

					// 保存市级总数统计，如果存在则更新，否则新增
					totalCityCountBean.setDeclareYear(String.valueOf(year));
					totalCityCountBean.setProvince(provinceBean.getProvinceName());
					totalCityCountBean.setCity(cityBean.getCityName());
					totalCityCountBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
					totalCityCountBean.setSum(true);

					TrainingClassTargetBusiness trainingClassTargetBusiness = new TrainingClassTargetBusiness();
					// 拿到目标
					TrainingClassTargetBean trainingClassTargetBean = trainingClassTargetBusiness
							.getTrainingClassTargetByCondition(totalCityCountBean.getProvince(),
									totalCityCountBean.getCity(), "", totalCityCountBean.getDeclareYear(), 0);
					if (trainingClassTargetBean != null) {
						totalCityCountBean.setCount1(trainingClassTargetBean.getTargetQuantity1());
						totalCityCountBean.setCount2(trainingClassTargetBean.getTargetQuantity2());
						totalCityCountBean.setCount3(trainingClassTargetBean.getTargetQuantity3());
						totalCityCountBean.setCount4(trainingClassTargetBean.getTargetQuantity4());
						totalCityCountBean.setCount5(trainingClassTargetBean.getTargetQuantity5());
						totalCityCountBean.setCount(trainingClassTargetBean.getTargetQuantity1()
								+ trainingClassTargetBean.getTargetQuantity2()
								+ trainingClassTargetBean.getTargetQuantity3()
								+ trainingClassTargetBean.getTargetQuantity4()
								+ trainingClassTargetBean.getTargetQuantity5());
					}
					totalCityCountBean = calculatePercentage(totalCityCountBean);

					orignialBean = getTargetStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_CITY,
							totalCityCountBean.getProvince(), totalCityCountBean.getCity(), "",
							totalCityCountBean.getDeclareYear(), true);
					if (orignialBean != null) {
						// 更新
						totalCityCountBean.set_id(orignialBean.get_id());
						updateTargetStatistics(totalCityCountBean);
					} else {
						// 新增
						insertTargetStaticsBean(totalCityCountBean);
					}
				}

				// 保存省级总数统计，如果存在则更新，否则新增
				totalProvinceCountBean.setDeclareYear(String.valueOf(year));
				totalProvinceCountBean.setProvince(provinceBean.getProvinceName());
				totalProvinceCountBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
				totalProvinceCountBean.setSum(true);

				TrainingClassTargetBusiness trainingClassTargetBusiness = new TrainingClassTargetBusiness();
				// 拿到目标
				TrainingClassTargetBean trainingClassTargetBean = trainingClassTargetBusiness
						.getTrainingClassTargetByCondition(totalProvinceCountBean.getProvince(), "", "",
								totalProvinceCountBean.getDeclareYear(), 0);
				if (trainingClassTargetBean != null) {
					totalProvinceCountBean.setCount1(trainingClassTargetBean.getTargetQuantity1());
					totalProvinceCountBean.setCount2(trainingClassTargetBean.getTargetQuantity2());
					totalProvinceCountBean.setCount3(trainingClassTargetBean.getTargetQuantity3());
					totalProvinceCountBean.setCount4(trainingClassTargetBean.getTargetQuantity4());
					totalProvinceCountBean.setCount5(trainingClassTargetBean.getTargetQuantity5());
					totalProvinceCountBean.setCount(
							trainingClassTargetBean.getTargetQuantity1() + trainingClassTargetBean.getTargetQuantity2()
									+ trainingClassTargetBean.getTargetQuantity3()
									+ trainingClassTargetBean.getTargetQuantity4()
									+ trainingClassTargetBean.getTargetQuantity5());
				}
				totalProvinceCountBean = calculatePercentage(totalProvinceCountBean);

				orignialBean = getTargetStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE,
						totalProvinceCountBean.getProvince(), "", "", totalProvinceCountBean.getDeclareYear(), true);
				if (orignialBean != null) {
					// 更新
					totalProvinceCountBean.set_id(orignialBean.get_id());
					updateTargetStatistics(totalProvinceCountBean);
				} else {
					// 新增
					insertTargetStaticsBean(totalProvinceCountBean);
				}
			}
		}

	}

	/**
	 * 把后者的统计值加到前者
	 * 
	 * @param bean
	 * @return
	 */
	private TargetStatisticsBean countTargetStatisticsBean(TargetStatisticsBean originalBean,
			TargetStatisticsBean bean) {
		originalBean.setFinishedCount(bean.getFinishedCount() + originalBean.getFinishedCount());
		originalBean.setFinishedCount1(bean.getFinishedCount1() + originalBean.getFinishedCount1());
		originalBean.setFinishedCount2(bean.getFinishedCount2() + originalBean.getFinishedCount2());
		originalBean.setFinishedCount3(bean.getFinishedCount3() + originalBean.getFinishedCount3());
		originalBean.setFinishedCount4(bean.getFinishedCount4() + originalBean.getFinishedCount4());
		originalBean.setFinishedCount5(bean.getFinishedCount5() + originalBean.getFinishedCount5());

		originalBean.setType1count(bean.getType1count() + originalBean.getType1count());
		originalBean.setType1industry1(bean.getType1industry1() + originalBean.getType1industry1());
		originalBean.setType1industry2(bean.getType1industry2() + originalBean.getType1industry2());
		originalBean.setType1industry3(bean.getType1industry3() + originalBean.getType1industry3());
		originalBean.setType1industry4(bean.getType1industry4() + originalBean.getType1industry4());
		originalBean.setType1industry5(bean.getType1industry5() + originalBean.getType1industry5());
		originalBean.setType1industry6(bean.getType1industry6() + originalBean.getType1industry6());
		originalBean.setType1industry7(bean.getType1industry7() + originalBean.getType1industry7());
		originalBean.setType1industry8(bean.getType1industry8() + originalBean.getType1industry8());
		originalBean.setType1industry9(bean.getType1industry9() + originalBean.getType1industry9());
		originalBean.setType1industry10(bean.getType1industry10() + originalBean.getType1industry10());

		originalBean.setType2count(bean.getType2count() + originalBean.getType2count());
		originalBean.setType2industry1(bean.getType2industry1() + originalBean.getType2industry1());
		originalBean.setType2industry2(bean.getType2industry2() + originalBean.getType2industry2());
		originalBean.setType2industry3(bean.getType2industry3() + originalBean.getType2industry3());
		originalBean.setType2industry4(bean.getType2industry4() + originalBean.getType2industry4());
		originalBean.setType2industry5(bean.getType2industry5() + originalBean.getType2industry5());
		originalBean.setType2industry6(bean.getType2industry6() + originalBean.getType2industry6());
		originalBean.setType2industry7(bean.getType2industry7() + originalBean.getType2industry7());
		originalBean.setType2industry8(bean.getType2industry8() + originalBean.getType2industry8());
		originalBean.setType2industry9(bean.getType2industry9() + originalBean.getType2industry9());
		originalBean.setType2industry10(bean.getType2industry10() + originalBean.getType2industry10());

		originalBean.setType5count(bean.getType5count() + originalBean.getType5count());
		originalBean.setType5industry1(bean.getType5industry1() + originalBean.getType5industry1());
		originalBean.setType5industry2(bean.getType5industry2() + originalBean.getType5industry2());
		originalBean.setType5industry3(bean.getType5industry3() + originalBean.getType5industry3());
		originalBean.setType5industry4(bean.getType5industry4() + originalBean.getType5industry4());
		originalBean.setType5industry5(bean.getType5industry5() + originalBean.getType5industry5());
		originalBean.setType5industry6(bean.getType5industry6() + originalBean.getType5industry6());
		originalBean.setType5industry7(bean.getType5industry7() + originalBean.getType5industry7());
		originalBean.setType5industry8(bean.getType5industry8() + originalBean.getType5industry8());
		originalBean.setType5industry9(bean.getType5industry9() + originalBean.getType5industry9());
		originalBean.setType5industry10(bean.getType5industry10() + originalBean.getType5industry10());

		originalBean.setType3count(bean.getType3count() + originalBean.getType3count());
		originalBean.setType3job1(bean.getType3job1() + originalBean.getType3job1());
		originalBean.setType3job2(bean.getType3job2() + originalBean.getType3job2());
		originalBean.setType3job3(bean.getType3job3() + originalBean.getType3job3());
		originalBean.setType3job4(bean.getType3job4() + originalBean.getType3job4());
		originalBean.setType3job5(bean.getType3job5() + originalBean.getType3job5());
		originalBean.setType3job6(bean.getType3job6() + originalBean.getType3job6());
		originalBean.setType3job7(bean.getType3job7() + originalBean.getType3job7());
		originalBean.setType3job8(bean.getType3job8() + originalBean.getType3job8());
		originalBean.setType3job9(bean.getType3job9() + originalBean.getType3job9());
		originalBean.setType3job10(bean.getType3job10() + originalBean.getType3job10());
		originalBean.setType3job11(bean.getType3job11() + originalBean.getType3job11());
		originalBean.setType3job12(bean.getType3job12() + originalBean.getType3job12());
		originalBean.setType3job13(bean.getType3job13() + originalBean.getType3job13());
		originalBean.setType3job14(bean.getType3job14() + originalBean.getType3job14());
		originalBean.setType3job15(bean.getType3job15() + originalBean.getType3job15());
		originalBean.setType3job16(bean.getType3job16() + originalBean.getType3job16());
		originalBean.setType3job17(bean.getType3job17() + originalBean.getType3job17());
		originalBean.setType3job18(bean.getType3job18() + originalBean.getType3job18());

		originalBean.setType4count(bean.getType4count() + originalBean.getType4count());
		originalBean.setType4job1(bean.getType4job1() + originalBean.getType4job1());
		originalBean.setType4job2(bean.getType4job2() + originalBean.getType4job2());
		originalBean.setType4job3(bean.getType4job3() + originalBean.getType4job3());
		originalBean.setType4job4(bean.getType4job4() + originalBean.getType4job4());
		originalBean.setType4job5(bean.getType4job5() + originalBean.getType4job5());
		originalBean.setType4job6(bean.getType4job6() + originalBean.getType4job6());
		originalBean.setType4job7(bean.getType4job7() + originalBean.getType4job7());
		originalBean.setType4job8(bean.getType4job8() + originalBean.getType4job8());
		originalBean.setType4job9(bean.getType4job9() + originalBean.getType4job9());
		originalBean.setType4job10(bean.getType4job10() + originalBean.getType4job10());
		originalBean.setType4job11(bean.getType4job11() + originalBean.getType4job11());
		originalBean.setType4job12(bean.getType4job12() + originalBean.getType4job12());
		originalBean.setType4job13(bean.getType4job13() + originalBean.getType4job13());
		originalBean.setType4job14(bean.getType4job14() + originalBean.getType4job14());
		originalBean.setType4job15(bean.getType4job15() + originalBean.getType4job15());
		originalBean.setType4job16(bean.getType4job16() + originalBean.getType4job16());
		originalBean.setType4job17(bean.getType4job17() + originalBean.getType4job17());
		originalBean.setType4job18(bean.getType4job18() + originalBean.getType4job18());

		return originalBean;
	}

	/**
	 * 查询数据库的值，放到统计的对象中
	 * 
	 * @param originalBean
	 * @return
	 */
	private TargetStatisticsBean countTargetStatisticsBean(TargetStatisticsBean originalBean) {
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
		TrainingClassTargetBusiness trainingClassTargetBusiness = new TrainingClassTargetBusiness();

		FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();

		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("province", originalBean.getProvince());
		condMap.put("city", originalBean.getCity());
		condMap.put("region", originalBean.getRegion());
		condMap.put("trainingYear", originalBean.getDeclareYear());

		List<StatusBean<Integer, String>> trainingTypeList = new ArrayList<>(DeclareInfoConfig.DECLARE_TYPE_LIST);
		trainingTypeList.add(new StatusBean<Integer, String>(-1, "-1"));

		for (StatusBean<Integer, String> statusBean : trainingTypeList) {
			int trainingType = statusBean.getStatus();
			if (trainingType != -1) {
				condMap.put("trainingType", String.valueOf(trainingType));
			}
			List<TrainingClassInfoBean> trainingClassList = trainingClassInfoBusiness
					.getTrainingClassInfoListByMap(condMap);

			if (trainingType == -1) {
				for (TrainingClassInfoBean bean : trainingClassList) {
					HashMap<String, String> classIdMap = new HashMap<>();
					int classId = bean.getClassId();
					classIdMap.put("classId", String.valueOf(classId));
					int result = trainingClassUserBusiness.getTrainingClassUserRecordResultByMap(classIdMap);
					originalBean.setFinishedCount(originalBean.getFinishedCount() + result);
					switch (trainingType) {
					case DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER: // 现代青年农场主
						originalBean.setFinishedCount1(originalBean.getFinishedCount1() + result);
						break;
					case DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER: // 生产经营职业农民
						originalBean.setFinishedCount2(originalBean.getFinishedCount2() + result);
						break;
					case DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER: // 专业技能型农民
						originalBean.setFinishedCount3(originalBean.getFinishedCount3() + result);
						break;
					case DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER: // 专业服务型农民
						originalBean.setFinishedCount4(originalBean.getFinishedCount4() + result);
						break;
					case DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER: // 新型农业经营带头人
						originalBean.setFinishedCount5(originalBean.getFinishedCount5() + result);
						break;
					}
				}
			} else {
				for (TrainingClassInfoBean bean : trainingClassList) {
					int classId = bean.getClassId();
					List<TrainingClassUserBean> trainingClassUserList = trainingClassUserBusiness
							.getTrainingClassUserListByClass(classId);

					for (TrainingClassUserBean trainingClassUserBean : trainingClassUserList) {
						int declareId = trainingClassUserBean.getDeclareId();
						FarmerIndustryInfoBean farmerIndustryInfoBean = farmerIndustryInfoBusiness
								.getFarmerIndustryInfoByKey(declareId);
						int industryType = farmerIndustryInfoBean.getIndustryTypeId1();
						int jobType = farmerIndustryInfoBean.getJobType();
						if (industryType != 0) {
							switch (trainingType) {
							case DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER:
								originalBean.setType1count(originalBean.getType1count() + 1);
								if (industryType == industryTypeId1) {
									originalBean.setType1industry1(originalBean.getType1industry1() + 1);
								} else if (industryType == industryTypeId2) {
									originalBean.setType1industry2(originalBean.getType1industry2() + 1);
								} else if (industryType == industryTypeId3) {
									originalBean.setType1industry3(originalBean.getType1industry3() + 1);
								} else if (industryType == industryTypeId4) {
									originalBean.setType1industry4(originalBean.getType1industry4() + 1);
								} else if (industryType == industryTypeId5) {
									originalBean.setType1industry5(originalBean.getType1industry5() + 1);
								} else if (industryType == industryTypeId6) {
									originalBean.setType1industry6(originalBean.getType1industry6() + 1);
								} else if (industryType == industryTypeId7) {
									originalBean.setType1industry7(originalBean.getType1industry7() + 1);
								} else if (industryType == industryTypeId8) {
									originalBean.setType1industry8(originalBean.getType1industry8() + 1);
								} else if (industryType == industryTypeId9) {
									originalBean.setType1industry9(originalBean.getType1industry9() + 1);
								} else if (industryType == industryTypeId10) {
									originalBean.setType1industry10(originalBean.getType1industry10() + 1);
								}
								break;
							case DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER:
								originalBean.setType2count(originalBean.getType2count() + 1);
								if (industryType == industryTypeId1) {
									originalBean.setType2industry1(originalBean.getType2industry1() + 1);
								} else if (industryType == industryTypeId2) {
									originalBean.setType2industry2(originalBean.getType2industry2() + 1);
								} else if (industryType == industryTypeId3) {
									originalBean.setType2industry3(originalBean.getType2industry3() + 1);
								} else if (industryType == industryTypeId4) {
									originalBean.setType2industry4(originalBean.getType2industry4() + 1);
								} else if (industryType == industryTypeId5) {
									originalBean.setType2industry5(originalBean.getType2industry5() + 1);
								} else if (industryType == industryTypeId6) {
									originalBean.setType2industry6(originalBean.getType2industry6() + 1);
								} else if (industryType == industryTypeId7) {
									originalBean.setType2industry7(originalBean.getType2industry7() + 1);
								} else if (industryType == industryTypeId8) {
									originalBean.setType2industry8(originalBean.getType2industry8() + 1);
								} else if (industryType == industryTypeId9) {
									originalBean.setType2industry9(originalBean.getType2industry9() + 1);
								} else if (industryType == industryTypeId10) {
									originalBean.setType2industry10(originalBean.getType2industry10() + 1);
								}
								break;
							case DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER:
								originalBean.setType5count(originalBean.getType5count() + 1);
								if (industryType == industryTypeId1) {
									originalBean.setType5industry1(originalBean.getType5industry1() + 1);
								} else if (industryType == industryTypeId2) {
									originalBean.setType5industry2(originalBean.getType5industry2() + 1);
								} else if (industryType == industryTypeId3) {
									originalBean.setType5industry3(originalBean.getType5industry3() + 1);
								} else if (industryType == industryTypeId4) {
									originalBean.setType5industry4(originalBean.getType5industry4() + 1);
								} else if (industryType == industryTypeId5) {
									originalBean.setType5industry5(originalBean.getType5industry5() + 1);
								} else if (industryType == industryTypeId6) {
									originalBean.setType5industry6(originalBean.getType5industry6() + 1);
								} else if (industryType == industryTypeId7) {
									originalBean.setType5industry7(originalBean.getType5industry7() + 1);
								} else if (industryType == industryTypeId8) {
									originalBean.setType5industry8(originalBean.getType5industry8() + 1);
								} else if (industryType == industryTypeId9) {
									originalBean.setType5industry9(originalBean.getType5industry9() + 1);
								} else if (industryType == industryTypeId10) {
									originalBean.setType5industry10(originalBean.getType5industry10() + 1);
								}
								break;
							}
						}
						if (jobType != 0) {
							switch (trainingType) {
							case DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER:
								originalBean.setType3count(originalBean.getType3count() + 1);
								if (jobType == jobTypeId1) {
									originalBean.setType3job1(originalBean.getType3job1() + 1);
								} else if (jobType == jobTypeId2) {
									originalBean.setType3job2(originalBean.getType3job2() + 1);
								} else if (jobType == jobTypeId3) {
									originalBean.setType3job3(originalBean.getType3job3() + 1);
								} else if (jobType == jobTypeId4) {
									originalBean.setType3job4(originalBean.getType3job4() + 1);
								} else if (jobType == jobTypeId5) {
									originalBean.setType3job5(originalBean.getType3job5() + 1);
								} else if (jobType == jobTypeId6) {
									originalBean.setType3job6(originalBean.getType3job6() + 1);
								} else if (jobType == jobTypeId7) {
									originalBean.setType3job7(originalBean.getType3job7() + 1);
								} else if (jobType == jobTypeId8) {
									originalBean.setType3job8(originalBean.getType3job8() + 1);
								} else if (jobType == jobTypeId9) {
									originalBean.setType3job9(originalBean.getType3job9() + 1);
								} else if (jobType == jobTypeId10) {
									originalBean.setType3job10(originalBean.getType3job10() + 1);
								} else if (jobType == jobTypeId11) {
									originalBean.setType3job11(originalBean.getType3job11() + 1);
								} else if (jobType == jobTypeId12) {
									originalBean.setType3job12(originalBean.getType3job12() + 1);
								} else if (jobType == jobTypeId13) {
									originalBean.setType3job13(originalBean.getType3job13() + 1);
								} else if (jobType == jobTypeId14) {
									originalBean.setType3job14(originalBean.getType3job14() + 1);
								} else if (jobType == jobTypeId15) {
									originalBean.setType3job15(originalBean.getType3job15() + 1);
								} else if (jobType == jobTypeId16) {
									originalBean.setType3job16(originalBean.getType3job16() + 1);
								} else if (jobType == jobTypeId17) {
									originalBean.setType3job17(originalBean.getType3job17() + 1);
								} else if (jobType == jobTypeId18) {
									originalBean.setType3job18(originalBean.getType3job18() + 1);
								}
								break;
							case DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER:
								originalBean.setType4count(originalBean.getType4count() + 1);
								if (jobType == jobTypeId1) {
									originalBean.setType4job1(originalBean.getType4job1() + 1);
								} else if (jobType == jobTypeId2) {
									originalBean.setType4job2(originalBean.getType4job2() + 1);
								} else if (jobType == jobTypeId3) {
									originalBean.setType4job3(originalBean.getType4job3() + 1);
								} else if (jobType == jobTypeId4) {
									originalBean.setType4job4(originalBean.getType4job4() + 1);
								} else if (jobType == jobTypeId5) {
									originalBean.setType4job5(originalBean.getType4job5() + 1);
								} else if (jobType == jobTypeId6) {
									originalBean.setType4job6(originalBean.getType4job6() + 1);
								} else if (jobType == jobTypeId7) {
									originalBean.setType4job7(originalBean.getType4job7() + 1);
								} else if (jobType == jobTypeId8) {
									originalBean.setType4job8(originalBean.getType4job8() + 1);
								} else if (jobType == jobTypeId9) {
									originalBean.setType4job9(originalBean.getType4job9() + 1);
								} else if (jobType == jobTypeId10) {
									originalBean.setType4job10(originalBean.getType4job10() + 1);
								} else if (jobType == jobTypeId11) {
									originalBean.setType4job11(originalBean.getType4job11() + 1);
								} else if (jobType == jobTypeId12) {
									originalBean.setType4job12(originalBean.getType4job12() + 1);
								} else if (jobType == jobTypeId13) {
									originalBean.setType4job13(originalBean.getType4job13() + 1);
								} else if (jobType == jobTypeId14) {
									originalBean.setType4job14(originalBean.getType4job14() + 1);
								} else if (jobType == jobTypeId15) {
									originalBean.setType4job15(originalBean.getType4job15() + 1);
								} else if (jobType == jobTypeId16) {
									originalBean.setType4job16(originalBean.getType4job16() + 1);
								} else if (jobType == jobTypeId17) {
									originalBean.setType4job17(originalBean.getType4job17() + 1);
								} else if (jobType == jobTypeId18) {
									originalBean.setType4job18(originalBean.getType4job18() + 1);
								}
								break;
							}
						}
					}
				}
			}
		}

		// 拿到目标
		TrainingClassTargetBean trainingClassTargetBean = trainingClassTargetBusiness.getTrainingClassTargetByCondition(
				originalBean.getProvince(), originalBean.getCity(), originalBean.getRegion(),
				originalBean.getDeclareYear(), 0);
		if (trainingClassTargetBean != null) {
			originalBean.setCount1(trainingClassTargetBean.getTargetQuantity1());
			originalBean.setCount2(trainingClassTargetBean.getTargetQuantity2());
			originalBean.setCount3(trainingClassTargetBean.getTargetQuantity3());
			originalBean.setCount4(trainingClassTargetBean.getTargetQuantity4());
			originalBean.setCount5(trainingClassTargetBean.getTargetQuantity5());
			originalBean.setCount(trainingClassTargetBean.getTargetQuantity1()
					+ trainingClassTargetBean.getTargetQuantity2() + trainingClassTargetBean.getTargetQuantity3()
					+ trainingClassTargetBean.getTargetQuantity4() + trainingClassTargetBean.getTargetQuantity5());
		}

		originalBean = calculatePercentage(originalBean);
		return originalBean;
	}

	/**
	 * 更新百分比的显示
	 * 
	 * @param originalBean
	 * @return
	 */
	private TargetStatisticsBean calculatePercentage(TargetStatisticsBean originalBean) {
		String precent = NumeralOperationKit.calculatePercent(originalBean.getFinishedCount(), originalBean.getCount(),
				1);
		String precent1 = NumeralOperationKit.calculatePercent(originalBean.getFinishedCount1(),
				originalBean.getCount1(), 1);
		String precent2 = NumeralOperationKit.calculatePercent(originalBean.getFinishedCount2(),
				originalBean.getCount2(), 1);
		String precent3 = NumeralOperationKit.calculatePercent(originalBean.getFinishedCount3(),
				originalBean.getCount3(), 1);
		String precent4 = NumeralOperationKit.calculatePercent(originalBean.getFinishedCount4(),
				originalBean.getCount4(), 1);
		String precent5 = NumeralOperationKit.calculatePercent(originalBean.getFinishedCount5(),
				originalBean.getCount5(), 1);
		originalBean.setPrecent(precent + "%");
		originalBean.setPrecent1(precent1 + "%");
		originalBean.setPrecent2(precent2 + "%");
		originalBean.setPrecent3(precent3 + "%");
		originalBean.setPrecent4(precent4 + "%");
		originalBean.setPrecent5(precent5 + "%");
		return originalBean;
	}
}
