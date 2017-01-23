package com.lpmas.declare.admin.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.OrganizationStatisticsBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.declare.admin.dao.OrganizationStatisticsDao;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.region.business.CityInfoBusiness;
import com.lpmas.declare.region.business.ProvinceInfoBusiness;
import com.lpmas.declare.region.business.RegionInfoBusiness;
import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StringKit;
import com.lpmas.region.bean.CityInfoBean;
import com.lpmas.region.bean.ProvinceInfoBean;
import com.lpmas.region.bean.RegionInfoBean;

public class OrganizationStatisticsBusiness {

	public int insertOrganizationStaticsBean(OrganizationStatisticsBean bean) throws Exception {
		OrganizationStatisticsDao dao = new OrganizationStatisticsDao();
		return dao.insertOrganizationStatistics(bean);
	}

	public long updateOrganizationStatistics(OrganizationStatisticsBean bean) {
		OrganizationStatisticsDao dao = new OrganizationStatisticsDao();
		return dao.updateOrganizationStatistics(bean);
	}

	public List<OrganizationStatisticsBean> getOrganizationStatisticsListByMap(HashMap<String, String> condMap) {
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		OrganizationStatisticsDao organizationStatisticsDao = new OrganizationStatisticsDao();

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
		String trainingType = condMap.get("trainingType");
		if (!StringKit.isNull(trainingType)) {
			queryMap.put("trainingType", trainingType);
		}
		String organizationType = condMap.get("organizationType");
		if (!StringKit.isNull(organizationType)) {
			queryMap.put("organizationType", organizationType);
		}

		List<OrganizationStatisticsBean> list = null;
		try {
			list = organizationStatisticsDao.getOrganizationStatisticsListByMap(queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public OrganizationStatisticsBean getOrganizationStatisticsByKey(String level, String province, String city,
			String region, String declareYear, boolean isSum, String trainingType, String organizationType)
			throws Exception {
		OrganizationStatisticsDao dao = new OrganizationStatisticsDao();
		return dao.getOrganizationStatisticsByKey(level, province, city, region, declareYear, isSum, trainingType,
				organizationType);
	}

	/**
	 * @throws Exception
	 *             初始化培训单位的统计,并将结果保存到mongo里
	 */
	public void initOrganizationStatistics(List<Integer> yearList) throws Exception {
		ProvinceInfoBusiness provinceBusiness = new ProvinceInfoBusiness();
		CityInfoBusiness cityBusiness = new CityInfoBusiness();
		RegionInfoBusiness regionBusiness = new RegionInfoBusiness();

		// trainingType的六种情况
		List<StatusBean<Integer, String>> classTypeList = new ArrayList<>(DeclareInfoConfig.DECLARE_TYPE_LIST);
		classTypeList.add(new StatusBean<Integer, String>(-1, "-1"));

		List<ProvinceInfoBean> provinceList = provinceBusiness.getProvinceInfoListByCountryId(1);
		for (int year : yearList) {
			for (StatusBean<Integer, String> typeStatus : classTypeList) {
				// 统计省级的组织机构人数数据
				for (ProvinceInfoBean provinceBean : provinceList) {
					OrganizationStatisticsBean totalProvinceCountBean = new OrganizationStatisticsBean();
					totalProvinceCountBean.setDeclareYear(String.valueOf(year));
					totalProvinceCountBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
					totalProvinceCountBean.setProvince(provinceBean.getProvinceName());
					totalProvinceCountBean.setTrainingType(String.valueOf(typeStatus.getStatus()));
					totalProvinceCountBean.setSum(true);

					OrganizationStatisticsBean targetStatisticsBean = new OrganizationStatisticsBean();
					targetStatisticsBean.setDeclareYear(String.valueOf(year));
					targetStatisticsBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
					targetStatisticsBean.setProvince(provinceBean.getProvinceName());
					targetStatisticsBean.setTrainingType(String.valueOf(typeStatus.getStatus()));

					targetStatisticsBean = countOrganizationStatisticsBean(targetStatisticsBean);
					totalProvinceCountBean = countOrganizationStatisticsBean(totalProvinceCountBean,
							targetStatisticsBean);

					if (targetStatisticsBean != null) {
						// 新增或更新数据
						OrganizationStatisticsBean statisticsBean = getOrganizationStatisticsByKey(
								DeclareAdminConfig.ADMIN_LEVEL_PROVINCE, targetStatisticsBean.getProvince(), "", "",
								targetStatisticsBean.getDeclareYear(), false, targetStatisticsBean.getTrainingType(),
								targetStatisticsBean.getOrganizationType());

						if (statisticsBean != null) {
							targetStatisticsBean.set_id(statisticsBean.get_id());
							updateOrganizationStatistics(targetStatisticsBean);
						} else {
							insertOrganizationStaticsBean(targetStatisticsBean);
						}
					}

					// 统计市级的组织机构人数数据
					List<CityInfoBean> cityList = cityBusiness
							.getCityInfoListByProvinceId(provinceBean.getProvinceId());
					for (CityInfoBean cityBean : cityList) {
						OrganizationStatisticsBean totalCityCountBean = new OrganizationStatisticsBean();
						totalCityCountBean.setDeclareYear(String.valueOf(year));
						totalCityCountBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
						totalCityCountBean.setProvince(provinceBean.getProvinceName());
						totalCityCountBean.setCity(cityBean.getCityName());
						totalCityCountBean.setTrainingType(String.valueOf(typeStatus.getStatus()));
						totalCityCountBean.setSum(true);

						targetStatisticsBean = new OrganizationStatisticsBean();
						targetStatisticsBean.setDeclareYear(String.valueOf(year));
						targetStatisticsBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
						targetStatisticsBean.setProvince(provinceBean.getProvinceName());
						targetStatisticsBean.setCity(cityBean.getCityName());
						targetStatisticsBean.setTrainingType(String.valueOf(typeStatus.getStatus()));

						targetStatisticsBean = countOrganizationStatisticsBean(targetStatisticsBean);
						totalCityCountBean = countOrganizationStatisticsBean(totalCityCountBean, targetStatisticsBean);
						totalProvinceCountBean = countOrganizationStatisticsBean(totalProvinceCountBean,
								targetStatisticsBean);

						if (targetStatisticsBean != null) {
							// 新增或更新数据
							OrganizationStatisticsBean statisticsBean = getOrganizationStatisticsByKey(
									DeclareAdminConfig.ADMIN_LEVEL_CITY, targetStatisticsBean.getProvince(),
									targetStatisticsBean.getCity(), "", targetStatisticsBean.getDeclareYear(), false,
									targetStatisticsBean.getTrainingType(), targetStatisticsBean.getOrganizationType());

							if (statisticsBean != null) {
								targetStatisticsBean.set_id(statisticsBean.get_id());
								updateOrganizationStatistics(targetStatisticsBean);
							} else {
								insertOrganizationStaticsBean(targetStatisticsBean);
							}
						}

						// 统计区级的组织机构人数数据
						List<RegionInfoBean> regionList = regionBusiness
								.getRegionInfoListByCityId(cityBean.getCityId());
						for (RegionInfoBean regionBean : regionList) {
							targetStatisticsBean = new OrganizationStatisticsBean();
							targetStatisticsBean.setDeclareYear(String.valueOf(year));
							targetStatisticsBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
							targetStatisticsBean.setProvince(provinceBean.getProvinceName());
							targetStatisticsBean.setCity(cityBean.getCityName());
							targetStatisticsBean.setRegion(regionBean.getRegionName());
							targetStatisticsBean.setTrainingType(String.valueOf(typeStatus.getStatus()));

							targetStatisticsBean = countOrganizationStatisticsBean(targetStatisticsBean);
							totalCityCountBean = countOrganizationStatisticsBean(totalCityCountBean,
									targetStatisticsBean);
							totalProvinceCountBean = countOrganizationStatisticsBean(totalProvinceCountBean,
									targetStatisticsBean);

							if (targetStatisticsBean != null) {
								// 新增或更新数据
								OrganizationStatisticsBean statisticsBean = getOrganizationStatisticsByKey(
										DeclareAdminConfig.ADMIN_LEVEL_REGION, targetStatisticsBean.getProvince(),
										targetStatisticsBean.getCity(), targetStatisticsBean.getRegion(),
										targetStatisticsBean.getDeclareYear(), false,
										targetStatisticsBean.getTrainingType(),
										targetStatisticsBean.getOrganizationType());

								if (statisticsBean != null) {
									targetStatisticsBean.set_id(statisticsBean.get_id());
									updateOrganizationStatistics(targetStatisticsBean);
								} else {
									insertOrganizationStaticsBean(targetStatisticsBean);
								}
							}
						}
						if (totalCityCountBean.getTotalTraining() != 0) {
							// 保存市级总数统计，如果存在则更新，否则新增
							OrganizationStatisticsBean statisticsBean = getOrganizationStatisticsByKey(
									DeclareAdminConfig.ADMIN_LEVEL_CITY, totalCityCountBean.getProvince(),
									totalCityCountBean.getCity(), "", totalCityCountBean.getDeclareYear(), true,
									totalCityCountBean.getTrainingType(), totalCityCountBean.getOrganizationType());

							if (statisticsBean != null) {
								totalCityCountBean.set_id(statisticsBean.get_id());
								updateOrganizationStatistics(totalCityCountBean);
							} else {
								insertOrganizationStaticsBean(totalCityCountBean);
							}
						}
					}
					if (totalProvinceCountBean.getTotalTraining() != 0) {
						// 保存省级总数统计，如果存在则更新，否则新增
						OrganizationStatisticsBean statisticsBean = getOrganizationStatisticsByKey(
								DeclareAdminConfig.ADMIN_LEVEL_PROVINCE, totalProvinceCountBean.getProvince(), "", "",
								totalProvinceCountBean.getDeclareYear(), true, totalProvinceCountBean.getTrainingType(),
								totalProvinceCountBean.getOrganizationType());

						if (statisticsBean != null) {
							totalProvinceCountBean.set_id(statisticsBean.get_id());
							updateOrganizationStatistics(totalProvinceCountBean);
						} else {
							insertOrganizationStaticsBean(totalProvinceCountBean);
						}
					}
				}
			}
		}
	}

	private OrganizationStatisticsBean countOrganizationStatisticsBean(OrganizationStatisticsBean originalBean,
			OrganizationStatisticsBean bean) {
		if (bean == null) {
			return originalBean;
		}
		originalBean.setAgriculturalSchool(bean.getAgriculturalSchool() + originalBean.getAgriculturalSchool());
		originalBean.setAgricultural_vocational_college(
				bean.getAgricultural_vocational_college() + originalBean.getAgricultural_vocational_college());
		originalBean.setSchool_of_Agricultural_Mechanization(bean.getSchool_of_Agricultural_Mechanization()
				+ originalBean.getSchool_of_Agricultural_Mechanization());
		originalBean.setAgricultural_extension_services(
				bean.getAgricultural_extension_services() + originalBean.getAgricultural_extension_services());
		originalBean.setAgricultural_efficiency(
				bean.getAgricultural_efficiency() + originalBean.getAgricultural_efficiency());
		originalBean.setAcademy_of_Agricultural_Sciences(
				bean.getAcademy_of_Agricultural_Sciences() + originalBean.getAcademy_of_Agricultural_Sciences());
		originalBean.setAgricultural_administrative_departments(bean.getAgricultural_administrative_departments()
				+ originalBean.getAgricultural_administrative_departments());
		originalBean.setPeasant_cooperatives(bean.getPeasant_cooperatives() + originalBean.getPeasant_cooperatives());
		originalBean.setAgricultural_leading_enterprises(
				bean.getAgricultural_leading_enterprises() + originalBean.getAgricultural_leading_enterprises());
		originalBean.setOther_public_institutions(
				bean.getOther_public_institutions() + originalBean.getOther_public_institutions());
		originalBean.setOther_private_organizations(
				bean.getOther_private_organizations() + originalBean.getOther_private_organizations());
		originalBean.setTotalTraining(bean.getTotalTraining() + originalBean.getTotalTraining());
		originalBean.setOrganizationType(bean.getOrganizationType());
		return originalBean;
	}

	private OrganizationStatisticsBean countOrganizationStatisticsBean(OrganizationStatisticsBean originalBean)
			throws Exception {
		TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();

		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("organizationLevel", originalBean.getLevel());
		condMap.put("province", originalBean.getProvince());
		condMap.put("city", originalBean.getCity());
		condMap.put("region", originalBean.getRegion());

		List<TrainingOrganizationInfoBean> trainingOrganizationList = trainingOrganizationInfoBusiness
				.getTrainingOrganizationInfoListByMap(condMap);

		if (!trainingOrganizationList.isEmpty()) {
			for (TrainingOrganizationInfoBean organizationInfoBean : trainingOrganizationList) {
				int result = 0;
				condMap.clear();
				String organizationType = organizationInfoBean.getOrganizationType();
				String trainingType = originalBean.getTrainingType();
				if (!trainingType.equals("-1")) {
					condMap.put("trainingType", trainingType);
				}
				if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)) {
					originalBean.setOrganizationType(TrainingOrganizationConfig.ORGANIZATION_TRAINING);
					// 培训机构
					condMap.put("organizationId", String.valueOf(organizationInfoBean.getOrganizationId()));
					List<TrainingClassInfoBean> trainingClassList = trainingClassInfoBusiness
							.getTrainingClassInfoListByMap(condMap);
					for (TrainingClassInfoBean trainingClassInfoBean : trainingClassList) {
						condMap.clear();
						condMap.put("classId", String.valueOf(trainingClassInfoBean.getClassId()));
						result += trainingClassUserBusiness.getTrainingClassUserRecordResultByMap(condMap);
					}

				} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)) {
					originalBean.setOrganizationType(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE);
					// 认定机构
					condMap.put("authOrganizationId", String.valueOf(organizationInfoBean.getOrganizationId()));
					condMap.put("authStatus", DeclareInfoConfig.AUTH_STATUS_APPROVE);
					result = declareInfoBusiness.getDeclareInfoRecordResultByMap(condMap);
				} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)) {
					continue;
				}

				switch (organizationInfoBean.getOrganizationCategory()) {
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_NGX: // 农广校
					originalBean.setAgriculturalSchool(originalBean.getAgriculturalSchool() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_NMZYXY: // 农业职业院校
					originalBean.setAgricultural_vocational_college(
							originalBean.getAgricultural_vocational_college() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_NJHXX: // 农机化学院
					originalBean.setSchool_of_Agricultural_Mechanization(
							originalBean.getSchool_of_Agricultural_Mechanization() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_NJTGFWJG: // 农技推广服务机构
					originalBean.setAgricultural_extension_services(
							originalBean.getAgricultural_extension_services() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_NYGX: // 农业高效
					originalBean.setAgricultural_efficiency(originalBean.getAgricultural_efficiency() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_NKYS: // 农科院(站)
					originalBean.setAcademy_of_Agricultural_Sciences(
							originalBean.getAcademy_of_Agricultural_Sciences() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_NYXZZGBM: // 农业行政主管部门
					originalBean.setAgricultural_administrative_departments(
							originalBean.getAgricultural_administrative_departments() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_HZS: // 农民合作社
					originalBean.setPeasant_cooperatives(originalBean.getPeasant_cooperatives() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_LYENLQY: // 农业龙头企业
					originalBean.setAgricultural_leading_enterprises(
							originalBean.getAgricultural_leading_enterprises() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_QIGBJG: // 其他公办机构
					originalBean.setOther_public_institutions(originalBean.getOther_public_institutions() + result);
					break;
				case TrainingOrganizationConfig.ORGANIZATION_TYPE_QIMBJG: // 其他民办机构
					originalBean.setOther_private_organizations(originalBean.getOther_private_organizations() + result);
					break;
				}
			}
			// 计算总数
			originalBean.setTotalTraining(originalBean.getAgriculturalSchool()
					+ originalBean.getAgricultural_vocational_college()
					+ originalBean.getSchool_of_Agricultural_Mechanization()
					+ originalBean.getAgricultural_extension_services() + originalBean.getAgricultural_efficiency()
					+ originalBean.getAcademy_of_Agricultural_Sciences()
					+ originalBean.getAgricultural_administrative_departments() + originalBean.getPeasant_cooperatives()
					+ originalBean.getOther_public_institutions() + originalBean.getOther_private_organizations());

			return originalBean;
		}
		return null;
	}
}
