package com.lpmas.declare.admin.business;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.constant.user.GenderConfig;
import com.lpmas.declare.admin.bean.DeclareStatisticsBean;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.dao.DeclareStatisticsDao;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.FarmerInfoConfig;
import com.lpmas.declare.region.business.CityInfoBusiness;
import com.lpmas.declare.region.business.ProvinceInfoBusiness;
import com.lpmas.declare.region.business.RegionInfoBusiness;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.lpmas.region.bean.CityInfoBean;
import com.lpmas.region.bean.ProvinceInfoBean;
import com.lpmas.region.bean.RegionInfoBean;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class DeclareStatisticsBusiness {

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

	public DeclareStatisticsBusiness() {
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

	public void initDeclareStatistics(List<Integer> yearList) throws Exception {
		ProvinceInfoBusiness provinceBusiness = new ProvinceInfoBusiness();
		List<ProvinceInfoBean> provinceList = provinceBusiness.getProvinceInfoListByCountryId(1);
		for (int year : yearList) {
			// 统计省级的申报数据
			for (ProvinceInfoBean provinceBean : provinceList) {
				DeclareStatisticsBean totalProvinceCountBean = new DeclareStatisticsBean();
				totalProvinceCountBean.setDeclareYear(String.valueOf(year));
				totalProvinceCountBean.setProvince(provinceBean.getProvinceName());
				totalProvinceCountBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
				totalProvinceCountBean.setSum(true);

				DeclareStatisticsBean bean = new DeclareStatisticsBean();
				bean.setDeclareYear(String.valueOf(year));
				bean.setProvince(provinceBean.getProvinceName());
				bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
				bean.setSum(false);
				bean = countDeclareStatisticsBean(bean);
				totalProvinceCountBean = countDeclareStatisticsBean(totalProvinceCountBean, bean);
				// 如果存在则更新，否则新增
				DeclareStatisticsBean orignialBean = getDeclareStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE,
						false, bean.getProvince(), "", "", String.valueOf(year));
				if (orignialBean != null) {
					// 更新
					bean.set_id(orignialBean.get_id());
					updateDeclareStatistics(bean);
				} else {
					// 新增
					insertDeclareStaticsBean(bean);
				}

				// 统计市级的申报数据
				CityInfoBusiness cityBusiness = new CityInfoBusiness();
				List<CityInfoBean> cityList = cityBusiness.getCityInfoListByProvinceId(provinceBean.getProvinceId());
				for (CityInfoBean cityBean : cityList) {

					DeclareStatisticsBean totalCityCountBean = new DeclareStatisticsBean();
					totalCityCountBean.setDeclareYear(String.valueOf(year));
					totalCityCountBean.setProvince(provinceBean.getProvinceName());
					totalCityCountBean.setCity(cityBean.getCityName());
					totalCityCountBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
					totalCityCountBean.setSum(true);

					bean = new DeclareStatisticsBean();
					bean.setDeclareYear(String.valueOf(year));
					bean.setProvince(provinceBean.getProvinceName());
					bean.setCity(cityBean.getCityName());
					bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
					bean.setSum(false);
					bean = countDeclareStatisticsBean(bean);
					totalCityCountBean = countDeclareStatisticsBean(totalCityCountBean, bean);
					totalProvinceCountBean = countDeclareStatisticsBean(totalProvinceCountBean, bean);
					// 如果存在则更新，否则新增
					orignialBean = getDeclareStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_CITY, false,
							bean.getProvince(), bean.getCity(), bean.getRegion(), String.valueOf(year));
					if (orignialBean != null) {
						// 更新
						bean.set_id(orignialBean.get_id());
						updateDeclareStatistics(bean);
					} else {
						// 新增
						insertDeclareStaticsBean(bean);
					}

					// 统计区级的申报数据
					RegionInfoBusiness regionBusiness = new RegionInfoBusiness();
					List<RegionInfoBean> regionList = regionBusiness.getRegionInfoListByCityId(cityBean.getCityId());
					for (RegionInfoBean regionBean : regionList) {
						bean = new DeclareStatisticsBean();
						bean.setDeclareYear(String.valueOf(year));
						bean.setProvince(provinceBean.getProvinceName());
						bean.setCity(cityBean.getCityName());
						bean.setRegion(regionBean.getRegionName());
						bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
						bean.setSum(false);
						bean = countDeclareStatisticsBean(bean);
						totalCityCountBean = countDeclareStatisticsBean(totalCityCountBean, bean);
						totalProvinceCountBean = countDeclareStatisticsBean(totalProvinceCountBean, bean);
						// 如果存在则更新，否则新增
						orignialBean = getDeclareStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_REGION, false,
								bean.getProvince(), bean.getCity(), bean.getRegion(), String.valueOf(year));
						if (orignialBean != null) {
							// 更新
							bean.set_id(orignialBean.get_id());
							updateDeclareStatistics(bean);
						} else {
							// 新增
							insertDeclareStaticsBean(bean);
						}
					}

					// 保存市级总数统计，如果存在则更新，否则新增
					orignialBean = getDeclareStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_CITY, true,
							bean.getProvince(), bean.getCity(), "", String.valueOf(year));
					if (orignialBean != null) {
						// 更新
						totalCityCountBean.set_id(orignialBean.get_id());
						updateDeclareStatistics(totalCityCountBean);
					} else {
						// 新增
						insertDeclareStaticsBean(totalCityCountBean);
					}
				}
				// 保存省级总数统计，如果存在则更新，否则新增
				orignialBean = getDeclareStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE, true,
						bean.getProvince(), "", "", String.valueOf(year));
				if (orignialBean != null) {
					// 更新
					totalProvinceCountBean.set_id(orignialBean.get_id());
					updateDeclareStatistics(totalProvinceCountBean);
				} else {
					// 新增
					insertDeclareStaticsBean(totalProvinceCountBean);
				}
			}
		}
	}

	public int insertDeclareStaticsBean(DeclareStatisticsBean bean) throws Exception {
		DeclareStatisticsDao dao = new DeclareStatisticsDao();
		return dao.insertDeclareStatistics(bean);
	}

	public long updateDeclareStatistics(DeclareStatisticsBean bean) {
		DeclareStatisticsDao dao = new DeclareStatisticsDao();
		return dao.updateDeclareStatistics(bean);
	}

	public DeclareStatisticsBean getDeclareStatisticsByKey(String id) throws Exception {
		DeclareStatisticsDao dao = new DeclareStatisticsDao();
		return dao.getDeclareStatisticsByKey(id);
	}

	public DeclareStatisticsBean getDeclareStatisticsByKey(String level, boolean sum, String province, String city,
			String region, String declareYear) throws Exception {
		DeclareStatisticsDao dao = new DeclareStatisticsDao();
		return dao.getDeclareStatisticsByKey(level, sum, province, city, region, declareYear);
	}

	public List<DeclareStatisticsBean> getDeclareStatisticsListByMap(HashMap<String, String> condMap) {
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		DeclareStatisticsDao declareStatisticsDao = new DeclareStatisticsDao();
		String country = condMap.get("country");
		if (StringKit.isValid(country)) {
			queryMap.put("country", country);
		}
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
		String sum = condMap.get("sum");
		if (StringKit.isValid(sum)) {
			queryMap.put("sum", Boolean.valueOf(sum));
		}
		String level = condMap.get("level");
		if (StringKit.isValid(level)) {
			queryMap.put("level", level);
		}
		String declareYear = condMap.get("declareYear");
		if (!StringKit.isNull(declareYear)) {
			queryMap.put("declareYear", declareYear);
		}
		List<DeclareStatisticsBean> list = null;
		try {
			list = declareStatisticsDao.getDeclareStatisticsListByMap(queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public PageResultBean<DeclareStatisticsBean> getDeclareStatisticsPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) throws Exception {
		DeclareStatisticsDao dao = new DeclareStatisticsDao();
		// 条件处理
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		String province = condMap.get("province");
		if (!StringKit.isNull(province)) {
			queryMap.put("province", province);
		}
		String city = condMap.get("city");
		if (!StringKit.isNull(city)) {
			queryMap.put("city", city);
		}
		String region = condMap.get("region");
		if (!StringKit.isNull(region)) {
			queryMap.put("region", region);
		}
		String country = condMap.get("country");
		if (!StringKit.isNull(country)) {
			queryMap.put("country", country);
		}
		String level = condMap.get("level");
		if (!StringKit.isNull(level)) {
			queryMap.put("level", level);
		}
		String declareYear = condMap.get("declareYear");
		if (!StringKit.isNull(declareYear)) {
			queryMap.put("declareYear", declareYear);
		}
		return dao.getDeclareStatisticsPageListByMap(queryMap, pageBean);
	}

	public List<DeclareStatisticsBean> getDeclareStatisticsListByMap(HashMap<String, String> condMap,
			HashMap<String, List<String>> scopeMap) throws Exception {
		DeclareStatisticsDao dao = new DeclareStatisticsDao();
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		String province = condMap.get("province");
		if (!StringKit.isNull(province)) {
			queryMap.put("province", province);
		}
		String city = condMap.get("city");
		if (!StringKit.isNull(city)) {
			queryMap.put("city", city);
		}
		String region = condMap.get("region");
		if (!StringKit.isNull(region)) {
			queryMap.put("region", region);
		}
		String country = condMap.get("country");
		if (!StringKit.isNull(country)) {
			queryMap.put("country", country);
		}
		String level = condMap.get("level");
		if (!StringKit.isNull(level)) {
			queryMap.put("level", level);
		}
		String declareYear = condMap.get("declareYear");
		if (!StringKit.isNull(declareYear)) {
			queryMap.put("declareYear", declareYear);
		}
		return dao.getDeclareStatisticsListByMap(queryMap);
	}

	/**
	 * 查询数据库的值，放到统计的对象中
	 * 
	 * @param bean
	 * @return
	 */
	private DeclareStatisticsBean countDeclareStatisticsBean(DeclareStatisticsBean bean) {
		HashMap<String, Object> condMap = new HashMap<String, Object>();
		DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
		condMap.put("declareYear", bean.getDeclareYear());
		condMap.put("province", bean.getProvince());
		condMap.put("city", bean.getCity());
		condMap.put("region", bean.getRegion());
		condMap.put("status", Constants.STATUS_VALID);
		// 所有数量
		bean.setCount(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		bean.setCount1(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		bean.setCount2(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		bean.setCount3(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		bean.setCount4(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		bean.setCount5(declareReportBusiness.getDeclareReportCountByMap(condMap));
		// 审核通过数量
		condMap.put("declareStatus", DeclareInfoConfig.DECLARE_STATUS_APPROVE);
		condMap.remove("declareType");
		bean.setApproveCount(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		bean.setApproveCount1(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		bean.setApproveCount2(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		bean.setApproveCount3(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		bean.setApproveCount4(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		bean.setApproveCount5(declareReportBusiness.getDeclareReportCountByMap(condMap));
		// 审核不通过数量
		List<String> listStatus = new ArrayList<String>();
		BasicDBList searchQueryCondition = new BasicDBList();
		listStatus.add(DeclareInfoConfig.DECLARE_STATUS_REJECT);
		listStatus.add(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE);
		for (String queryStatus : listStatus) {
			BasicDBObject cond = new BasicDBObject();
			cond.append("declareStatus", queryStatus);
			searchQueryCondition.add(cond);
		}
		condMap.put("$or", searchQueryCondition);
		condMap.remove("declareType");
		bean.setRejectCount(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		bean.setRejectCount1(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		bean.setRejectCount2(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		bean.setRejectCount3(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		bean.setRejectCount4(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		bean.setRejectCount5(declareReportBusiness.getDeclareReportCountByMap(condMap));
		// 待审核数量
		listStatus = new ArrayList<String>();
		searchQueryCondition = new BasicDBList();
		listStatus.add(DeclareInfoConfig.DECLARE_STATUS_SUBMIT);
		listStatus.add(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE);
		for (String queryStatus : listStatus) {
			BasicDBObject cond = new BasicDBObject();
			cond.append("declareStatus", queryStatus);
			searchQueryCondition.add(cond);
		}
		condMap.put("$or", searchQueryCondition);
		condMap.remove("declareType");
		bean.setWaitApproveCount(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		bean.setWaitApproveCount1(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		bean.setWaitApproveCount2(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		bean.setWaitApproveCount3(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		bean.setWaitApproveCount4(declareReportBusiness.getDeclareReportCountByMap(condMap));
		condMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		bean.setWaitApproveCount5(declareReportBusiness.getDeclareReportCountByMap(condMap));

		HashMap<String, Object> queryMap = new HashMap<>(condMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int peopleCount1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int peopleCount2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int peopleCount3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int peopleCount4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int peopleCount5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		bean.setPeopleCount1(peopleCount1);// 总人数
		bean.setPeopleCount2(peopleCount2);// 总人数
		bean.setPeopleCount3(peopleCount3);// 总人数
		bean.setPeopleCount4(peopleCount4);// 总人数
		bean.setPeopleCount5(peopleCount5);// 总人数

		if (peopleCount1 + peopleCount2 + peopleCount3 + peopleCount4 + peopleCount5 == 0) {
			return bean;
		}

		queryMap = new HashMap<>(condMap);
		queryMap.put("userGender", GenderConfig.GENDER_MALE); // 男性
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int male1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int male2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int male3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int male4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int male5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("userGender", GenderConfig.GENDER_FEMALE); // 女性
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int female1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int female2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int female3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int female4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int female5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		bean.setMale1(male1);
		bean.setMale2(male2);
		bean.setMale3(male3);
		bean.setMale4(male4);
		bean.setMale5(male5);
		bean.setFemale1(female1);
		bean.setFemale2(female2);
		bean.setFemale3(female3);
		bean.setFemale4(female4);
		bean.setFemale5(female5);

		queryMap = new HashMap<>(condMap);
		LocalDate today = LocalDate.now();
		LocalDate birthday = today.minus(60, ChronoUnit.YEARS);
		queryMap.put("userBirthday", "{\"$gt\":\"" + birthday.getYear() + "-01-01\"}");
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int ageLevel5Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap); // 60岁以上
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int ageLevel5Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap); // 60岁以上
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int ageLevel5Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap); // 60岁以上
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int ageLevel5Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap); // 60岁以上
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int ageLevel5Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap); // 60岁以上

		birthday = today.minus(45, ChronoUnit.YEARS);
		queryMap.put("userBirthday", "{\"$gt\":\"" + birthday.getYear() + "-01-01\"}");
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int ageLevel4Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel5Num1; // 46-60
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int ageLevel4Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel5Num2; // 46-60
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int ageLevel4Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel5Num3; // 46-60
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int ageLevel4Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel5Num4; // 46-60
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int ageLevel4Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel5Num5; // 46-60

		birthday = today.minus(35, ChronoUnit.YEARS);
		queryMap.put("userBirthday", "{\"$gt\":\"" + birthday.getYear() + "-01-01\"}");
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int ageLevel3Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel4Num1; // 36-45
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int ageLevel3Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel4Num2; // 36-45
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int ageLevel3Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel4Num3; // 36-45
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int ageLevel3Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel4Num4; // 36-45
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int ageLevel3Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel4Num5; // 36-45

		birthday = today.minus(25, ChronoUnit.YEARS);
		queryMap.put("userBirthday", "{\"$gt\":\"" + birthday.getYear() + "-01-01\"}");
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int ageLevel2Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel3Num1; // 26-35
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int ageLevel2Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel3Num2; // 26-35
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int ageLevel2Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel3Num3; // 26-35
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int ageLevel2Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel3Num4; // 26-35
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int ageLevel2Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel3Num5; // 26-35

		birthday = today.minus(18, ChronoUnit.YEARS);
		queryMap.put("userBirthday", "{\"$gt\":\"" + birthday.getYear() + "-01-01\"}");
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int ageLevel1Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel2Num1; // 18-25
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int ageLevel1Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel2Num2; // 18-25
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int ageLevel1Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel2Num3; // 18-25
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int ageLevel1Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel2Num4; // 18-25
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int ageLevel1Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap) - ageLevel2Num5; // 18-25

		bean.setAgeLevel1Num1(ageLevel1Num1);
		bean.setAgeLevel1Num2(ageLevel1Num2);
		bean.setAgeLevel1Num3(ageLevel1Num3);
		bean.setAgeLevel1Num4(ageLevel1Num4);
		bean.setAgeLevel1Num5(ageLevel1Num5);

		bean.setAgeLevel2Num1(ageLevel2Num1);
		bean.setAgeLevel2Num2(ageLevel2Num2);
		bean.setAgeLevel2Num3(ageLevel2Num3);
		bean.setAgeLevel2Num4(ageLevel2Num4);
		bean.setAgeLevel2Num5(ageLevel2Num5);

		bean.setAgeLevel3Num1(ageLevel3Num1);
		bean.setAgeLevel3Num2(ageLevel3Num2);
		bean.setAgeLevel3Num3(ageLevel3Num3);
		bean.setAgeLevel3Num4(ageLevel3Num4);
		bean.setAgeLevel3Num5(ageLevel3Num5);

		bean.setAgeLevel4Num1(ageLevel4Num1);
		bean.setAgeLevel4Num2(ageLevel4Num2);
		bean.setAgeLevel4Num3(ageLevel4Num3);
		bean.setAgeLevel4Num4(ageLevel4Num4);
		bean.setAgeLevel4Num5(ageLevel4Num5);

		bean.setAgeLevel5Num1(ageLevel5Num1);
		bean.setAgeLevel5Num2(ageLevel5Num2);
		bean.setAgeLevel5Num3(ageLevel5Num3);
		bean.setAgeLevel5Num4(ageLevel5Num4);
		bean.setAgeLevel5Num5(ageLevel5Num5);

		queryMap = new HashMap<>(condMap);
		queryMap.put("education", FarmerInfoConfig.EDUCATION_LEVEL_NO_EDUCATED);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int cultureLevel0Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int cultureLevel0Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int cultureLevel0Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int cultureLevel0Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int cultureLevel0Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("education", FarmerInfoConfig.EDUCATION_LEVEL_PRIMARY_SCHOOL);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int cultureLevel1Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int cultureLevel1Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int cultureLevel1Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int cultureLevel1Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int cultureLevel1Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("education", FarmerInfoConfig.EDUCATION_LEVEL_MIDDLE_SCHOOL);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int cultureLevel2Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int cultureLevel2Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int cultureLevel2Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int cultureLevel2Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int cultureLevel2Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("education", FarmerInfoConfig.EDUCATION_LEVEL_HEIGHT_SCHOOL);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int cultureLevel3Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int cultureLevel3Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int cultureLevel3Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int cultureLevel3Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int cultureLevel3Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("education", FarmerInfoConfig.EDUCATION_LEVEL_COLLEGE);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int cultureLevel4Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int cultureLevel4Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int cultureLevel4Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int cultureLevel4Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int cultureLevel4Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		bean.setCultureLevel1Num1(cultureLevel0Num1 + cultureLevel1Num1);
		bean.setCultureLevel1Num2(cultureLevel0Num2 + cultureLevel1Num2);
		bean.setCultureLevel1Num3(cultureLevel0Num3 + cultureLevel1Num3);
		bean.setCultureLevel1Num4(cultureLevel0Num4 + cultureLevel1Num4);
		bean.setCultureLevel1Num5(cultureLevel0Num5 + cultureLevel1Num5);

		bean.setCultureLevel2Num1(cultureLevel2Num1);
		bean.setCultureLevel2Num2(cultureLevel2Num2);
		bean.setCultureLevel2Num3(cultureLevel2Num3);
		bean.setCultureLevel2Num4(cultureLevel2Num4);
		bean.setCultureLevel2Num5(cultureLevel2Num5);

		bean.setCultureLevel3Num1(cultureLevel3Num1);
		bean.setCultureLevel3Num2(cultureLevel3Num2);
		bean.setCultureLevel3Num3(cultureLevel3Num3);
		bean.setCultureLevel3Num4(cultureLevel3Num4);
		bean.setCultureLevel3Num5(cultureLevel3Num5);

		bean.setCultureLevel4Num1(cultureLevel4Num1);
		bean.setCultureLevel4Num2(cultureLevel4Num2);
		bean.setCultureLevel4Num3(cultureLevel4Num3);
		bean.setCultureLevel4Num4(cultureLevel4Num4);
		bean.setCultureLevel4Num5(cultureLevel4Num5);

		queryMap = new HashMap<>(condMap);
		queryMap.put("politicalStatus", FarmerInfoConfig.POLITICAL_STATUS_CPC); // 中共党员
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int politicalLevel1Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int politicalLevel1Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int politicalLevel1Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int politicalLevel1Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int politicalLevel1Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("politicalStatus", FarmerInfoConfig.POLITICAL_STATUS_CYL); // 共青团员
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int politicalLevel2Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int politicalLevel2Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int politicalLevel2Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int politicalLevel2Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int politicalLevel2Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("politicalStatus", FarmerInfoConfig.POLITICAL_STATUS_PEOPLE); // 群众
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int politicalLevel3Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int politicalLevel3Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int politicalLevel3Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int politicalLevel3Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int politicalLevel3Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("politicalStatus", FarmerInfoConfig.POLITICAL_STATUS_OTHER); // 其他
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int politicalLevel4Num1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int politicalLevel4Num2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int politicalLevel4Num3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int politicalLevel4Num4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int politicalLevel4Num5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		bean.setPoliticalLevel1Num1(politicalLevel1Num1);
		bean.setPoliticalLevel1Num2(politicalLevel1Num2);
		bean.setPoliticalLevel1Num3(politicalLevel1Num3);
		bean.setPoliticalLevel1Num4(politicalLevel1Num4);
		bean.setPoliticalLevel1Num5(politicalLevel1Num5);

		bean.setPoliticalLevel2Num1(politicalLevel2Num1);
		bean.setPoliticalLevel2Num2(politicalLevel2Num2);
		bean.setPoliticalLevel2Num3(politicalLevel2Num3);
		bean.setPoliticalLevel2Num4(politicalLevel2Num4);
		bean.setPoliticalLevel2Num5(politicalLevel2Num5);

		bean.setPoliticalLevel3Num1(politicalLevel3Num1);
		bean.setPoliticalLevel3Num2(politicalLevel3Num2);
		bean.setPoliticalLevel3Num3(politicalLevel3Num3);
		bean.setPoliticalLevel3Num4(politicalLevel3Num4);
		bean.setPoliticalLevel3Num5(politicalLevel3Num5);

		bean.setPoliticalLevel4Num1(politicalLevel4Num1);
		bean.setPoliticalLevel4Num2(politicalLevel4Num2);
		bean.setPoliticalLevel4Num3(politicalLevel4Num3);
		bean.setPoliticalLevel4Num4(politicalLevel4Num4);
		bean.setPoliticalLevel4Num5(politicalLevel4Num5);

		queryMap = new HashMap<>(condMap);
		queryMap.put("registryType", DeclareInfoConfig.REGISTRY_TYPE_FARMER); // 个人申报
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int personal_declaration1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int personal_declaration2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int personal_declaration3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int personal_declaration4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int personal_declaration5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("registryType", DeclareInfoConfig.REGISTRY_TYPE_RECOMMEND); // 组织推荐
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int organization_recommended1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int organization_recommended2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int organization_recommended3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int organization_recommended4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int organization_recommended5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		bean.setPersonal_declaration1(personal_declaration1);
		bean.setPersonal_declaration2(personal_declaration2);
		bean.setPersonal_declaration3(personal_declaration3);
		bean.setPersonal_declaration4(personal_declaration4);
		bean.setPersonal_declaration5(personal_declaration5);

		bean.setOrganization_recommended1(organization_recommended1);
		bean.setOrganization_recommended2(organization_recommended2);
		bean.setOrganization_recommended3(organization_recommended3);
		bean.setOrganization_recommended4(organization_recommended4);
		bean.setOrganization_recommended5(organization_recommended5);

		queryMap = new HashMap<>(condMap);
		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_LARGE_REEDING);// 种养大户
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int planting_large1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int planting_large2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int planting_large5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER);// 家庭农场经营者
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int family_farm1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int family_farm2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int family_farm5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_COOPERATIVES_BACKBONE);// 农民合作社骨干
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int farmer_cooperatives1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int farmer_cooperatives2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int farmer_cooperatives5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_ENTERPRISE_BOSS);// 农业企业负责人
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int agricultural_enterprises2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int agricultural_enterprises5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_STUDENTS_ENTREPRENEURSHIP);// 创业大学生
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int college_students1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int college_students2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_MIGRANT_WORKERS);// 返乡农民工
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int migrant_workers1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int migrant_workers2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_VETERAN);// 退伍军人
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int veteran1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int veteran2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_SCALE_AQUACULTURE_OPERATORS);// 规模养殖经营者
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int scale_farm1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int scale_farm2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int scale_farm5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_COLLEGE_GRADUATES);// 中高职毕业生
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int vocational_graduates1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int vocational_graduates2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("farmerType", FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_SERVICE_ORGANIZER);// 农业社会化服务组织服务能手
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int service_experts5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		bean.setPlanting_large1(planting_large1);// 种植大户
		bean.setPlanting_large2(planting_large2);// 种植大户
		bean.setPlanting_large5(planting_large5);// 种植大户

		bean.setFamily_farm1(family_farm1);// 家庭农场经营者
		bean.setFamily_farm2(family_farm2);// 家庭农场经营者
		bean.setFamily_farm5(family_farm5);// 家庭农场经营者

		bean.setFarmer_cooperatives1(farmer_cooperatives1);// 农民合作社骨干
		bean.setFarmer_cooperatives2(farmer_cooperatives2);// 农民合作社骨干
		bean.setFarmer_cooperatives5(farmer_cooperatives5);// 农民合作社骨干

		bean.setAgricultural_enterprises2(agricultural_enterprises2); // 农业企业负责人
		bean.setAgricultural_enterprises5(agricultural_enterprises5); // 农业企业负责人

		bean.setCollege_students1(college_students1);// 创业大学生
		bean.setCollege_students2(college_students2);// 创业大学生

		bean.setMigrant_workers1(migrant_workers1);// 返乡农民工
		bean.setMigrant_workers2(migrant_workers2);// 返乡农民工

		bean.setVeteran1(veteran1);// 退伍军人
		bean.setVeteran2(veteran2);// 退伍军人

		bean.setScale_farm1(scale_farm1);// 规模养殖场经营者
		bean.setScale_farm2(scale_farm2);// 规模养殖场经营者
		bean.setScale_farm5(scale_farm5);// 规模养殖场经营者

		bean.setVocational_graduates1(vocational_graduates1);// 中高职毕业生
		bean.setVocational_graduates2(vocational_graduates2);// 中高职毕业生

		bean.setService_experts5(service_experts5);// 农业社会化服务组织服务能手

		queryMap = new HashMap<>(condMap);
		queryMap.put("industryTypeId1", industryTypeId1);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("industryTypeId1", industryTypeId2);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("industryTypeId1", industryTypeId3);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("industryTypeId1", industryTypeId4);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("industryTypeId1", industryTypeId5);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("industryTypeId1", industryTypeId6);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry6 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry6 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry6 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("industryTypeId1", industryTypeId7);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry7 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry7 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry7 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("industryTypeId1", industryTypeId8);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry8 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry8 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry8 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("industryTypeId1", industryTypeId9);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry9 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry9 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry9 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("industryTypeId1", industryTypeId10);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int type1industry10 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int type2industry10 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int type5industry10 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		bean.setType1industry1(type1industry1);
		bean.setType1industry2(type1industry2);
		bean.setType1industry3(type1industry3);
		bean.setType1industry4(type1industry4);
		bean.setType1industry5(type1industry5);
		bean.setType1industry6(type1industry6);
		bean.setType1industry7(type1industry7);
		bean.setType1industry8(type1industry8);
		bean.setType1industry9(type1industry9);
		bean.setType1industry10(type1industry10);

		bean.setType2industry1(type2industry1);
		bean.setType2industry2(type2industry2);
		bean.setType2industry3(type2industry3);
		bean.setType2industry4(type2industry4);
		bean.setType2industry5(type2industry5);
		bean.setType2industry6(type2industry6);
		bean.setType2industry7(type2industry7);
		bean.setType2industry8(type2industry8);
		bean.setType2industry9(type2industry9);
		bean.setType2industry10(type2industry10);

		bean.setType5industry1(type5industry1);
		bean.setType5industry2(type5industry2);
		bean.setType5industry3(type5industry3);
		bean.setType5industry4(type5industry4);
		bean.setType5industry5(type5industry5);
		bean.setType5industry6(type5industry6);
		bean.setType5industry7(type5industry7);
		bean.setType5industry8(type5industry8);
		bean.setType5industry9(type5industry9);
		bean.setType5industry10(type5industry10);

		queryMap = new HashMap<>(condMap);
		queryMap.put("jobType", jobTypeId1);// 农艺工
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job1 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId2);// 园艺工
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job2 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId3);// 牧草工
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job3 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId4);// 热带作物生产工
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job4 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId5);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job5 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId6);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job6 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job6 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId7);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job7 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job7 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId8);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job8 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job8 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId9);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job9 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job9 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId10);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job10 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job10 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId11);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job11 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job11 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId12);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job12 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job12 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId13);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job13 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job13 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId14);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job14 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job14 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId15);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job15 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job15 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId16);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job16 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job16 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId17);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job17 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job17 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		queryMap.put("jobType", jobTypeId18);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int type3job18 = declareReportBusiness.getDeclareReportCountByMap(queryMap);
		queryMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
		int type4job18 = declareReportBusiness.getDeclareReportCountByMap(queryMap);

		bean.setType3job1(type3job1);
		bean.setType3job2(type3job2);
		bean.setType3job3(type3job3);
		bean.setType3job4(type3job4);
		bean.setType3job5(type3job5);
		bean.setType3job6(type3job6);
		bean.setType3job7(type3job7);
		bean.setType3job8(type3job8);
		bean.setType3job9(type3job9);
		bean.setType3job10(type3job10);
		bean.setType3job11(type3job11);
		bean.setType3job12(type3job12);
		bean.setType3job13(type3job13);
		bean.setType3job14(type3job14);
		bean.setType3job15(type3job15);
		bean.setType3job16(type3job16);
		bean.setType3job17(type3job17);
		bean.setType3job18(type3job18);

		bean.setType4job1(type4job1);
		bean.setType4job2(type4job2);
		bean.setType4job3(type4job3);
		bean.setType4job4(type4job4);
		bean.setType4job5(type4job5);
		bean.setType4job6(type4job6);
		bean.setType4job7(type4job7);
		bean.setType4job8(type4job8);
		bean.setType4job9(type4job9);
		bean.setType4job10(type4job10);
		bean.setType4job11(type4job11);
		bean.setType4job12(type4job12);
		bean.setType4job13(type4job13);
		bean.setType4job14(type4job14);
		bean.setType4job15(type4job15);
		bean.setType4job16(type4job16);
		bean.setType4job17(type4job17);
		bean.setType4job18(type4job18);

		return bean;
	}

	/**
	 * 把后者的统计值加到前者
	 * 
	 * @param bean
	 * @return
	 */
	public DeclareStatisticsBean countDeclareStatisticsBean(DeclareStatisticsBean originalBean,
			DeclareStatisticsBean bean) {
		originalBean.setCount(originalBean.getCount() + bean.getCount());
		originalBean.setCount1(originalBean.getCount1() + bean.getCount1());
		originalBean.setCount2(originalBean.getCount2() + bean.getCount2());
		originalBean.setCount3(originalBean.getCount3() + bean.getCount3());
		originalBean.setCount4(originalBean.getCount4() + bean.getCount4());
		originalBean.setCount5(originalBean.getCount5() + bean.getCount5());

		originalBean.setApproveCount(originalBean.getApproveCount() + bean.getApproveCount());
		originalBean.setApproveCount1(originalBean.getApproveCount1() + bean.getApproveCount1());
		originalBean.setApproveCount2(originalBean.getApproveCount2() + bean.getApproveCount2());
		originalBean.setApproveCount3(originalBean.getApproveCount3() + bean.getApproveCount3());
		originalBean.setApproveCount4(originalBean.getApproveCount4() + bean.getApproveCount4());
		originalBean.setApproveCount5(originalBean.getApproveCount5() + bean.getApproveCount5());

		originalBean.setRejectCount(originalBean.getRejectCount() + bean.getApproveCount());
		originalBean.setRejectCount1(originalBean.getRejectCount1() + bean.getRejectCount1());
		originalBean.setRejectCount2(originalBean.getRejectCount2() + bean.getRejectCount2());
		originalBean.setRejectCount3(originalBean.getRejectCount3() + bean.getRejectCount3());
		originalBean.setRejectCount4(originalBean.getRejectCount4() + bean.getRejectCount4());
		originalBean.setRejectCount5(originalBean.getRejectCount5() + bean.getRejectCount5());

		originalBean.setWaitApproveCount(originalBean.getWaitApproveCount() + bean.getWaitApproveCount());
		originalBean.setWaitApproveCount1(originalBean.getWaitApproveCount1() + bean.getWaitApproveCount1());
		originalBean.setWaitApproveCount2(originalBean.getWaitApproveCount2() + bean.getWaitApproveCount2());
		originalBean.setWaitApproveCount3(originalBean.getWaitApproveCount3() + bean.getWaitApproveCount3());
		originalBean.setWaitApproveCount4(originalBean.getWaitApproveCount4() + bean.getWaitApproveCount4());
		originalBean.setWaitApproveCount5(originalBean.getWaitApproveCount5() + bean.getWaitApproveCount5());

		originalBean.setPeopleCount1(bean.getPeopleCount1() + originalBean.getPeopleCount1());
		originalBean.setPeopleCount2(bean.getPeopleCount2() + originalBean.getPeopleCount2());
		originalBean.setPeopleCount3(bean.getPeopleCount3() + originalBean.getPeopleCount3());
		originalBean.setPeopleCount4(bean.getPeopleCount4() + originalBean.getPeopleCount4());
		originalBean.setPeopleCount5(bean.getPeopleCount5() + originalBean.getPeopleCount5());

		originalBean.setMale1(bean.getMale1() + originalBean.getMale1());
		originalBean.setMale2(bean.getMale2() + originalBean.getMale2());
		originalBean.setMale3(bean.getMale3() + originalBean.getMale3());
		originalBean.setMale4(bean.getMale4() + originalBean.getMale4());
		originalBean.setMale5(bean.getMale5() + originalBean.getMale5());
		originalBean.setFemale1(bean.getFemale1() + originalBean.getFemale1());
		originalBean.setFemale2(bean.getFemale2() + originalBean.getFemale2());
		originalBean.setFemale3(bean.getFemale3() + originalBean.getFemale3());
		originalBean.setFemale4(bean.getFemale4() + originalBean.getFemale4());
		originalBean.setFemale5(bean.getFemale5() + originalBean.getFemale5());

		originalBean.setAgeLevel1Num1(bean.getAgeLevel1Num1() + originalBean.getAgeLevel1Num1());
		originalBean.setAgeLevel1Num2(bean.getAgeLevel1Num2() + originalBean.getAgeLevel1Num2());
		originalBean.setAgeLevel1Num3(bean.getAgeLevel1Num3() + originalBean.getAgeLevel1Num3());
		originalBean.setAgeLevel1Num4(bean.getAgeLevel1Num4() + originalBean.getAgeLevel1Num4());
		originalBean.setAgeLevel1Num5(bean.getAgeLevel1Num5() + originalBean.getAgeLevel1Num5());

		originalBean.setAgeLevel2Num1(bean.getAgeLevel2Num1() + originalBean.getAgeLevel2Num1());
		originalBean.setAgeLevel2Num2(bean.getAgeLevel2Num2() + originalBean.getAgeLevel2Num2());
		originalBean.setAgeLevel2Num3(bean.getAgeLevel2Num3() + originalBean.getAgeLevel2Num3());
		originalBean.setAgeLevel2Num4(bean.getAgeLevel2Num4() + originalBean.getAgeLevel2Num4());
		originalBean.setAgeLevel2Num5(bean.getAgeLevel2Num5() + originalBean.getAgeLevel2Num5());

		originalBean.setAgeLevel3Num1(bean.getAgeLevel3Num1() + originalBean.getAgeLevel3Num1());
		originalBean.setAgeLevel3Num2(bean.getAgeLevel3Num2() + originalBean.getAgeLevel3Num2());
		originalBean.setAgeLevel3Num3(bean.getAgeLevel3Num3() + originalBean.getAgeLevel3Num3());
		originalBean.setAgeLevel3Num4(bean.getAgeLevel3Num4() + originalBean.getAgeLevel3Num4());
		originalBean.setAgeLevel3Num5(bean.getAgeLevel3Num5() + originalBean.getAgeLevel3Num5());

		originalBean.setAgeLevel4Num1(bean.getAgeLevel4Num1() + originalBean.getAgeLevel4Num1());
		originalBean.setAgeLevel4Num2(bean.getAgeLevel4Num2() + originalBean.getAgeLevel4Num2());
		originalBean.setAgeLevel4Num3(bean.getAgeLevel4Num3() + originalBean.getAgeLevel4Num3());
		originalBean.setAgeLevel4Num4(bean.getAgeLevel4Num4() + originalBean.getAgeLevel4Num4());
		originalBean.setAgeLevel4Num5(bean.getAgeLevel4Num5() + originalBean.getAgeLevel4Num5());

		originalBean.setAgeLevel5Num1(bean.getAgeLevel5Num1() + originalBean.getAgeLevel5Num1());
		originalBean.setAgeLevel5Num2(bean.getAgeLevel5Num2() + originalBean.getAgeLevel5Num2());
		originalBean.setAgeLevel5Num3(bean.getAgeLevel5Num3() + originalBean.getAgeLevel5Num3());
		originalBean.setAgeLevel5Num4(bean.getAgeLevel5Num4() + originalBean.getAgeLevel5Num4());
		originalBean.setAgeLevel5Num5(bean.getAgeLevel5Num5() + originalBean.getAgeLevel5Num5());

		originalBean.setCultureLevel1Num1(bean.getCultureLevel1Num1() + originalBean.getCultureLevel1Num1());
		originalBean.setCultureLevel1Num2(bean.getCultureLevel1Num2() + originalBean.getCultureLevel1Num2());
		originalBean.setCultureLevel1Num3(bean.getCultureLevel1Num3() + originalBean.getCultureLevel1Num3());
		originalBean.setCultureLevel1Num4(bean.getCultureLevel1Num4() + originalBean.getCultureLevel1Num4());
		originalBean.setCultureLevel1Num5(bean.getCultureLevel1Num5() + originalBean.getCultureLevel1Num5());

		originalBean.setCultureLevel2Num1(bean.getCultureLevel2Num1() + originalBean.getCultureLevel2Num1());
		originalBean.setCultureLevel2Num2(bean.getCultureLevel2Num2() + originalBean.getCultureLevel2Num2());
		originalBean.setCultureLevel2Num3(bean.getCultureLevel2Num3() + originalBean.getCultureLevel2Num3());
		originalBean.setCultureLevel2Num4(bean.getCultureLevel2Num4() + originalBean.getCultureLevel2Num4());
		originalBean.setCultureLevel2Num5(bean.getCultureLevel2Num5() + originalBean.getCultureLevel2Num5());

		originalBean.setCultureLevel3Num1(bean.getCultureLevel3Num1() + originalBean.getCultureLevel3Num1());
		originalBean.setCultureLevel3Num2(bean.getCultureLevel3Num2() + originalBean.getCultureLevel3Num2());
		originalBean.setCultureLevel3Num3(bean.getCultureLevel3Num3() + originalBean.getCultureLevel3Num3());
		originalBean.setCultureLevel3Num4(bean.getCultureLevel3Num4() + originalBean.getCultureLevel3Num4());
		originalBean.setCultureLevel3Num5(bean.getCultureLevel3Num5() + originalBean.getCultureLevel3Num5());

		originalBean.setCultureLevel4Num1(bean.getCultureLevel4Num1() + originalBean.getCultureLevel4Num1());
		originalBean.setCultureLevel4Num2(bean.getCultureLevel4Num2() + originalBean.getCultureLevel4Num2());
		originalBean.setCultureLevel4Num3(bean.getCultureLevel4Num3() + originalBean.getCultureLevel4Num3());
		originalBean.setCultureLevel4Num4(bean.getCultureLevel4Num4() + originalBean.getCultureLevel4Num4());
		originalBean.setCultureLevel4Num5(bean.getCultureLevel4Num5() + originalBean.getCultureLevel4Num5());

		originalBean
				.setPersonal_declaration1(bean.getPersonal_declaration1() + originalBean.getPersonal_declaration1());
		originalBean
				.setPersonal_declaration2(bean.getPersonal_declaration2() + originalBean.getPersonal_declaration2());
		originalBean
				.setPersonal_declaration3(bean.getPersonal_declaration3() + originalBean.getPersonal_declaration3());
		originalBean
				.setPersonal_declaration4(bean.getPersonal_declaration4() + originalBean.getPersonal_declaration4());
		originalBean
				.setPersonal_declaration5(bean.getPersonal_declaration5() + originalBean.getPersonal_declaration5());

		originalBean.setOrganization_recommended1(
				bean.getOrganization_recommended1() + originalBean.getOrganization_recommended1());
		originalBean.setOrganization_recommended2(
				bean.getOrganization_recommended2() + originalBean.getOrganization_recommended2());
		originalBean.setOrganization_recommended3(
				bean.getOrganization_recommended3() + originalBean.getOrganization_recommended3());
		originalBean.setOrganization_recommended4(
				bean.getOrganization_recommended4() + originalBean.getOrganization_recommended4());
		originalBean.setOrganization_recommended5(
				bean.getOrganization_recommended5() + originalBean.getOrganization_recommended5());

		originalBean.setPlanting_large1(bean.getPlanting_large1() + originalBean.getPlanting_large1());
		originalBean.setPlanting_large2(bean.getPlanting_large2() + originalBean.getPlanting_large2());
		originalBean.setPlanting_large5(bean.getPlanting_large5() + originalBean.getPlanting_large5());

		originalBean.setScale_farm1(bean.getScale_farm1() + originalBean.getScale_farm1());
		originalBean.setScale_farm2(bean.getScale_farm2() + originalBean.getScale_farm2());
		originalBean.setScale_farm5(bean.getScale_farm5() + originalBean.getScale_farm5());

		originalBean.setFamily_farm1(bean.getFamily_farm1() + originalBean.getFamily_farm1());
		originalBean.setFamily_farm2(bean.getFamily_farm2() + originalBean.getFamily_farm2());
		originalBean.setFamily_farm5(bean.getFamily_farm5() + originalBean.getFamily_farm5());

		originalBean.setFarmer_cooperatives1(bean.getFarmer_cooperatives1() + originalBean.getFarmer_cooperatives1());
		originalBean.setFarmer_cooperatives2(bean.getFarmer_cooperatives2() + originalBean.getFarmer_cooperatives2());
		originalBean.setFarmer_cooperatives5(bean.getFarmer_cooperatives5() + originalBean.getFarmer_cooperatives5());

		originalBean.setCollege_students1(bean.getCollege_students1() + originalBean.getCollege_students1());
		originalBean.setCollege_students2(bean.getCollege_students2() + originalBean.getCollege_students2());

		originalBean
				.setVocational_graduates1(bean.getVocational_graduates1() + originalBean.getVocational_graduates1());
		originalBean
				.setVocational_graduates2(bean.getVocational_graduates2() + originalBean.getVocational_graduates2());

		originalBean.setMigrant_workers1(bean.getMigrant_workers1() + originalBean.getMigrant_workers1());
		originalBean.setMigrant_workers2(bean.getMigrant_workers2() + originalBean.getMigrant_workers2());

		originalBean.setVeteran1(bean.getVeteran1() + originalBean.getVeteran1());
		originalBean.setVeteran2(bean.getVeteran2() + originalBean.getVeteran2());

		originalBean.setAgricultural_enterprises2(
				bean.getAgricultural_enterprises2() + originalBean.getAgricultural_enterprises2());
		originalBean.setAgricultural_enterprises5(
				bean.getAgricultural_enterprises5() + originalBean.getAgricultural_enterprises5());

		originalBean.setService_experts5(bean.getService_experts5() + originalBean.getService_experts5());

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
}
