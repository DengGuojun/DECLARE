package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.bean.TeacherStatisticsBean;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.dao.TeacherStatisticsDao;
import com.lpmas.declare.config.TeacherInfoConfig;
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

public class TeacherStatisticsBusiness {

	public int insertTeacherStaticsBean(TeacherStatisticsBean bean) throws Exception {
		TeacherStatisticsDao dao = new TeacherStatisticsDao();
		return dao.insertTeacherStatistics(bean);
	}

	public long updateTeacherStatistics(TeacherStatisticsBean bean) {
		TeacherStatisticsDao dao = new TeacherStatisticsDao();
		return dao.updateTeacherStatistics(bean);
	}

	public TeacherStatisticsBean getDeclareReportByKey(String id) throws Exception {
		TeacherStatisticsDao dao = new TeacherStatisticsDao();
		return dao.getTeacherStatisticsByKey(id);
	}

	public TeacherStatisticsBean getTeacherStatisticsByKey(String level, boolean sum, String province, String city,
			String region) throws Exception {
		TeacherStatisticsDao dao = new TeacherStatisticsDao();
		return dao.getTeacherStatisticsByKey(level, sum, province, city, region);
	}

	public List<TeacherStatisticsBean> getTeacherStatisticsListByMap(HashMap<String, String> condMap) {
		HashMap<String, Object> queryMap = new HashMap<String, Object>();
		TeacherStatisticsDao teacherStatisticsDao = new TeacherStatisticsDao();

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

		List<TeacherStatisticsBean> list = null;
		try {
			list = teacherStatisticsDao.getTeacherStatisticsListByMap(queryMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public PageResultBean<TeacherStatisticsBean> getTeacherStatisticsPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) throws Exception {
		TeacherStatisticsDao dao = new TeacherStatisticsDao();
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

		return dao.getTeacherStatisticsPageListByMap(queryMap, pageBean);
	}

	// 获取数据 前端显示
	public List<TeacherStatisticsBean> getTeacherStatisticsListByMap(HashMap<String, String> condMap,
			HashMap<String, List<String>> scopeMap) throws Exception {
		TeacherStatisticsDao dao = new TeacherStatisticsDao();

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

		return dao.getTeacherStatisticsListByMap(queryMap);
	}

	public void initTeacherStatistics() throws Exception {
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		TeacherInfoBusiness teacherInfoBusiness = new TeacherInfoBusiness();
		// 统计所有地区的老师数据
		TeacherStatisticsBean totalCountBean = new TeacherStatisticsBean();
		ProvinceInfoBusiness provinceBusiness = new ProvinceInfoBusiness();
		List<ProvinceInfoBean> provinceList = provinceBusiness.getProvinceInfoListByCountryId(1);

		// 统计国家级的数据
		TeacherStatisticsBean bean = new TeacherStatisticsBean();
		condMap.put("province", DeclareAdminConfig.COUNTRY_STR);
		List<TeacherInfoBean> teacherList = teacherInfoBusiness.getTeacherInfoListByMap(condMap);
		for (TeacherInfoBean provinceTeacherBean : teacherList) {
			bean = countTeacherStatisticsBean(bean, provinceTeacherBean);
			totalCountBean = countTeacherStatisticsBean(totalCountBean, provinceTeacherBean);
		}
		bean.setProvince(DeclareAdminConfig.COUNTRY_STR);
		bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
		bean.setSum(false);
		// 如果存在则更新，否则新增
		TeacherStatisticsBean orignialBean = getTeacherStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY, false,
				DeclareAdminConfig.COUNTRY_STR, "", "");
		if (orignialBean != null) {
			// 更新
			bean.set_id(orignialBean.get_id());
			updateTeacherStatistics(bean);
		} else {
			// 新增
			insertTeacherStaticsBean(bean);
		}

		// 统计省级的师资数据
		for (ProvinceInfoBean provinceBean : provinceList) {
			TeacherStatisticsBean totalProvinceCountBean = new TeacherStatisticsBean();
			String province = provinceBean.getProvinceName();
			bean = new TeacherStatisticsBean();
			// 查找师资信息数据，进行统计
			condMap.put("province", province);
			condMap.put("city", "");
			condMap.put("region", "");
			teacherList = teacherInfoBusiness.getTeacherInfoListByMap(condMap);
			for (TeacherInfoBean provinceTeacherBean : teacherList) {
				bean = countTeacherStatisticsBean(bean, provinceTeacherBean);
				totalProvinceCountBean = countTeacherStatisticsBean(totalProvinceCountBean, provinceTeacherBean);
				totalCountBean = countTeacherStatisticsBean(totalCountBean, provinceTeacherBean);
			}

			bean.setProvince(province);
			bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			bean.setSum(false);
			// 如果存在则更新，否则新增
			orignialBean = getTeacherStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE, false, province, "", "");
			if (orignialBean != null) {
				// 更新
				bean.set_id(orignialBean.get_id());
				updateTeacherStatistics(bean);
			} else {
				// 新增
				insertTeacherStaticsBean(bean);
			}

			// 统计市级的师资数据
			CityInfoBusiness cityBusiness = new CityInfoBusiness();
			List<CityInfoBean> cityList = cityBusiness.getCityInfoListByProvinceId(provinceBean.getProvinceId());
			for (CityInfoBean cityBean : cityList) {
				TeacherStatisticsBean totalCityCountBean = new TeacherStatisticsBean();
				String city = cityBean.getCityName();
				bean = new TeacherStatisticsBean();
				// 查找师资信息数据，进行统计
				condMap.put("city", city);
				condMap.put("region", "");
				teacherList = teacherInfoBusiness.getTeacherInfoListByMap(condMap);
				for (TeacherInfoBean cityTeacherBean : teacherList) {
					bean = countTeacherStatisticsBean(bean, cityTeacherBean);
					totalCityCountBean = countTeacherStatisticsBean(totalCityCountBean, cityTeacherBean);
					totalProvinceCountBean = countTeacherStatisticsBean(totalProvinceCountBean, cityTeacherBean);
					totalCountBean = countTeacherStatisticsBean(totalCountBean, cityTeacherBean);
				}

				bean.setProvince(province);
				bean.setCity(city);
				bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
				bean.setSum(false);
				// 如果存在则更新，否则新增
				orignialBean = getTeacherStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_CITY, false, province, city, "");
				if (orignialBean != null) {
					// 更新
					bean.set_id(orignialBean.get_id());
					updateTeacherStatistics(bean);
				} else {
					// 新增
					insertTeacherStaticsBean(bean);
				}

				// 统计区级的师资数据
				RegionInfoBusiness regionBusiness = new RegionInfoBusiness();
				List<RegionInfoBean> regionList = regionBusiness.getRegionInfoListByCityId(cityBean.getCityId());
				for (RegionInfoBean regionBean : regionList) {
					String region = regionBean.getRegionName();
					bean = new TeacherStatisticsBean();
					// 查找师资信息数据，进行统计
					condMap.put("region", region);
					teacherList = teacherInfoBusiness.getTeacherInfoListByMap(condMap);
					for (TeacherInfoBean regionTeacherBean : teacherList) {
						bean = countTeacherStatisticsBean(bean, regionTeacherBean);
						totalCityCountBean = countTeacherStatisticsBean(totalCityCountBean, regionTeacherBean);
						totalProvinceCountBean = countTeacherStatisticsBean(totalProvinceCountBean, regionTeacherBean);
						totalCountBean = countTeacherStatisticsBean(totalCountBean, regionTeacherBean);
					}

					// 如果存在则更新，否则新增
					bean.setProvince(province);
					bean.setCity(city);
					bean.setRegion(region);
					bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
					bean.setSum(false);
					orignialBean = getTeacherStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_REGION, false, province,
							city, region);
					if (orignialBean != null) {
						// 更新
						bean.set_id(orignialBean.get_id());
						updateTeacherStatistics(bean);
					} else {
						// 新增
						insertTeacherStaticsBean(bean);
					}
				}
				// 保存市级总数统计，如果存在则更新，否则新增
				totalCityCountBean.setProvince(province);
				totalCityCountBean.setCity(city);
				totalCityCountBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
				totalCityCountBean.setSum(true);
				orignialBean = getTeacherStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_CITY, true, province, city, "");
				if (orignialBean != null) {
					// 更新
					totalCityCountBean.set_id(orignialBean.get_id());
					updateTeacherStatistics(totalCityCountBean);
				} else {
					// 新增
					insertTeacherStaticsBean(totalCityCountBean);
				}
			}
			// 保存省级总数统计，如果存在则更新，否则新增
			totalProvinceCountBean.setProvince(province);
			totalProvinceCountBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			totalProvinceCountBean.setSum(true);
			orignialBean = getTeacherStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE, true, province, "", "");
			if (orignialBean != null) {
				// 更新
				totalProvinceCountBean.set_id(orignialBean.get_id());
				updateTeacherStatistics(totalProvinceCountBean);
			} else {
				// 新增
				insertTeacherStaticsBean(totalProvinceCountBean);
			}
		}

		// 保存总数统计，如果存在则更新，否则新增
		totalCountBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
		totalCountBean.setProvince("总计");
		totalCountBean.setSum(true);
		orignialBean = getTeacherStatisticsByKey(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY, true, "总计", "", "");
		if (orignialBean != null) {
			// 更新
			totalCountBean.set_id(orignialBean.get_id());
			updateTeacherStatistics(totalCountBean);
		} else {
			// 新增
			insertTeacherStaticsBean(totalCountBean);
		}

	}

	public TeacherStatisticsBean countTeacherStatisticsBean(TeacherStatisticsBean statisticsBean,
			TeacherInfoBean teacherBean) {
		String technicalGrade = teacherBean.getTechnicalGrade();
		String teacherType = teacherBean.getTeacherType();
		statisticsBean.setCount(statisticsBean.getCount() + 1);// 总数
		switch (technicalGrade) {
		case TeacherInfoConfig.TECHNICAL_GRADE_MIDDLE: // 中级别教师
			statisticsBean.setMiddleCount(statisticsBean.getMiddleCount() + 1);
			break;
		case TeacherInfoConfig.TECHNICAL_GRADE_PRIMARY: // 出级别教师
			statisticsBean.setPrimaryCount(statisticsBean.getPrimaryCount() + 1);
			break;
		case TeacherInfoConfig.TECHNICAL_GRADE_SENIOR: // 高级别教师
			statisticsBean.setSeniorCount(statisticsBean.getSeniorCount() + 1);
			break;
		case TeacherInfoConfig.TECHNICAL_GRADE_SUB_SENIOR: // 副高级别
			statisticsBean.setSubSeniorCount(statisticsBean.getSubSeniorCount() + 1);
			break;
		default: // 无等级
			statisticsBean.setOtherLevelCount(statisticsBean.getOtherLevelCount() + 1);
		}

		switch (Integer.parseInt(teacherType)) {
		case TeacherInfoConfig.TEACHER_TYPE_PLANT:
			statisticsBean.setTypePlantCount(statisticsBean.getTypePlantCount() + 1);
			break;
		case TeacherInfoConfig.TEACHER_TYPE_BREED:
			statisticsBean.setTypeBreedCount(statisticsBean.getTypeBreedCount() + 1);
			break;
		case TeacherInfoConfig.TEACHER_TYPE_PUBLIC_MANAGE:
			statisticsBean.setTypePublicManageCount(statisticsBean.getTypePublicManageCount() + 1);
			break;
		case TeacherInfoConfig.TEACHER_TYPE_ENGINEERING_AND_SERVER:
			statisticsBean.setTypeEngineeringAndServerCount(statisticsBean.getTypeEngineeringAndServerCount() + 1);
			break;
		case TeacherInfoConfig.TEACHER_TYPE_MODERN_AGRICULTURE:
			statisticsBean.setTypeModernAgricultureCount(statisticsBean.getTypeModernAgricultureCount() + 1);
			break;
		case TeacherInfoConfig.TEACHER_TYPE_OTHER:
			statisticsBean.setTypeOtherCount(statisticsBean.getTypeOtherCount() + 1);
			break;
		case TeacherInfoConfig.TEACHER_TYPE_PUBLIC_FOUNDATION:
			statisticsBean.setTypePublicFoundationCount(statisticsBean.getTypePublicFoundationCount() + 1);
			break;
		}

		return statisticsBean;
	}

}
