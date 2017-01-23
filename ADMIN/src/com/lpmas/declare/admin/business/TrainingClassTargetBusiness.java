package com.lpmas.declare.admin.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.declare.admin.bean.TrainingClassTargetBean;
import com.lpmas.declare.admin.dao.TrainingClassTargetDao;
import com.lpmas.declare.region.business.CityInfoBusiness;
import com.lpmas.declare.region.business.ProvinceInfoBusiness;
import com.lpmas.declare.region.business.RegionInfoBusiness;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.region.bean.CityInfoBean;
import com.lpmas.region.bean.ProvinceInfoBean;
import com.lpmas.region.bean.RegionInfoBean;

public class TrainingClassTargetBusiness {
	public int addTrainingClassTarget(TrainingClassTargetBean bean) {
		TrainingClassTargetDao dao = new TrainingClassTargetDao();
		return dao.insertTrainingClassTarget(bean);
	}

	public int updateTrainingClassTarget(TrainingClassTargetBean bean) {
		TrainingClassTargetDao dao = new TrainingClassTargetDao();
		return dao.updateTrainingClassTarget(bean);
	}

	public TrainingClassTargetBean getTrainingClassTargetByKey(int targetId) {
		TrainingClassTargetDao dao = new TrainingClassTargetDao();
		return dao.getTrainingClassTargetByKey(targetId);
	}

	public PageResultBean<TrainingClassTargetBean> getTrainingClassTargetPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		TrainingClassTargetDao dao = new TrainingClassTargetDao();
		return dao.getTrainingClassTargetPageListByMap(condMap, pageBean);
	}

	public List<TrainingClassTargetBean> getTrainingClassTargetListByMap(HashMap<String, String> condMap) {
		TrainingClassTargetDao dao = new TrainingClassTargetDao();
		return dao.getTrainingClassTargetListByMap(condMap);
	}

	public int getTrainingClassTargetRecordResultByMap(HashMap<String, String> condMap) {
		TrainingClassTargetDao dao = new TrainingClassTargetDao();
		return dao.getTrainingClassTargetRecordResultByMap(condMap);
	}

	public ReturnMessageBean verifyTrainingClassTarget(TrainingClassTargetBean bean) {
		ReturnMessageBean result = new ReturnMessageBean();
		if (bean.getTargetQuantity1() == 0 || bean.getTargetQuantity2() == 0 || bean.getTargetQuantity3() == 0 || bean.getTargetQuantity4() == 0
				|| bean.getTargetQuantity1() == 0) {
			result.setMessage("任务数必须填写且不能为0");
		}
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("trainingYear", bean.getTrainingYear());
		condMap.put("province", bean.getProvince());
		condMap.put("city", bean.getCity());
		condMap.put("region", bean.getRegion());
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("organizationId", String.valueOf(bean.getOrganizationId()));
		List<TrainingClassTargetBean> trainingClassTargetList = getTrainingClassTargetListByMap(condMap);
		for (TrainingClassTargetBean trainingClassTargetBean : trainingClassTargetList) {
			if (trainingClassTargetBean.getProvince().equals(bean.getProvince()) && trainingClassTargetBean.getCity().equals(bean.getCity())
					&& trainingClassTargetBean.getRegion().equals(bean.getRegion())) {
				result.setMessage("该任务已经添加，不能重复添加");
			}
		}
		return result;
	}

	public TrainingClassTargetBean getTrainingClassTargetByCondition(String province, String city, String region, String trainingYear,
			int organizationId) {
		TrainingClassTargetDao dao = new TrainingClassTargetDao();
		if (!StringKit.isValid(province) && !StringKit.isValid(city) && !StringKit.isValid(region)) {
			return dao.getCountryTrainingClassTargetOfYear(trainingYear);
		}
		return dao.getTrainingClassTargetByCondition(province, city, region, trainingYear, organizationId);
	}

	private String getTargetKey(TrainingClassTargetBean trainingClassTargetBean) {
		String result = "";
		if (StringKit.isValid(trainingClassTargetBean.getCountry())) {
			result = result + trainingClassTargetBean.getCountry();
		}
		if (StringKit.isValid(trainingClassTargetBean.getProvince())) {
			result = result + trainingClassTargetBean.getProvince();
		}
		if (StringKit.isValid(trainingClassTargetBean.getCity())) {
			result = result + trainingClassTargetBean.getCity();
		}
		if (StringKit.isValid(trainingClassTargetBean.getRegion())) {
			result = result + trainingClassTargetBean.getRegion();
		}
		return result;
	}

	public List<TrainingClassTargetBean> processTrainingClassTargetListByMap(HashMap<String, String> condMap,
			List<TrainingClassTargetBean> trainingClassTargetList) {
		List<TrainingClassTargetBean> result = new ArrayList<TrainingClassTargetBean>();
		Map<String, TrainingClassTargetBean> targetMap = new HashMap<String, TrainingClassTargetBean>();
		// 地区的培训目标（不包括培训机构）
		for (TrainingClassTargetBean trainingClassTargetBean : trainingClassTargetList) {
			if (trainingClassTargetBean.getOrganizationId() == 0) {
				targetMap.put(getTargetKey(trainingClassTargetBean), trainingClassTargetBean);
			}
		}
		// 合计数
		int targetQuantity1 = 0;
		int targetQuantity2 = 0;
		int targetQuantity3 = 0;
		int targetQuantity4 = 0;
		int targetQuantity5 = 0;
		if (StringKit.isValid(condMap.get("region"))) {
			String key = condMap.get("province") + condMap.get("city") + condMap.get("region");
			if (targetMap.containsKey(key)) {
				result.add(targetMap.get(key));
				targetQuantity1 = targetQuantity1 + targetMap.get(key).getTargetQuantity1();
				targetQuantity2 = targetQuantity2 + targetMap.get(key).getTargetQuantity2();
				targetQuantity3 = targetQuantity3 + targetMap.get(key).getTargetQuantity3();
				targetQuantity4 = targetQuantity4 + targetMap.get(key).getTargetQuantity4();
				targetQuantity5 = targetQuantity5 + targetMap.get(key).getTargetQuantity5();
			} else {
				TrainingClassTargetBean trainingClassTargetBean = new TrainingClassTargetBean();
				trainingClassTargetBean.setProvince(condMap.get("province"));
				trainingClassTargetBean.setCity(condMap.get("city"));
				trainingClassTargetBean.setRegion(condMap.get("region"));
				result.add(trainingClassTargetBean);
			}
		} else if (StringKit.isValid(condMap.get("city"))) {
			RegionInfoBusiness regionInfoBusiness = new RegionInfoBusiness();
			List<RegionInfoBean> regionInfoList = regionInfoBusiness.getRegionInfoListByCityName(condMap.get("city"));
			String key = condMap.get("province") + condMap.get("city");
			TrainingClassTargetBean trainingClassTargetBean = new TrainingClassTargetBean();
			trainingClassTargetBean.setProvince(condMap.get("province"));
			trainingClassTargetBean.setCity(condMap.get("city"));
			if (targetMap.containsKey(key)) {
				result.add(targetMap.get(key));
			} else {
				result.add(trainingClassTargetBean);
			}
			if (regionInfoList == null || regionInfoList.size() == 0) {
				for (String target : targetMap.keySet()) {
					if (StringKit.isValid(targetMap.get(target).getRegion())) {
						result.add(targetMap.get(target));
						targetQuantity1 = targetQuantity1 + targetMap.get(target).getTargetQuantity1();
						targetQuantity2 = targetQuantity2 + targetMap.get(target).getTargetQuantity2();
						targetQuantity3 = targetQuantity3 + targetMap.get(target).getTargetQuantity3();
						targetQuantity4 = targetQuantity4 + targetMap.get(target).getTargetQuantity4();
						targetQuantity5 = targetQuantity5 + targetMap.get(target).getTargetQuantity5();
					}
				}
			} else {
				for (RegionInfoBean regionInfoBean : regionInfoList) {
					if (targetMap.containsKey(key + regionInfoBean.getRegionName())) {
						result.add(targetMap.get(key + regionInfoBean.getRegionName()));
						targetQuantity1 = targetQuantity1 + targetMap.get(key + regionInfoBean.getRegionName()).getTargetQuantity1();
						targetQuantity2 = targetQuantity2 + targetMap.get(key + regionInfoBean.getRegionName()).getTargetQuantity2();
						targetQuantity3 = targetQuantity3 + targetMap.get(key + regionInfoBean.getRegionName()).getTargetQuantity3();
						targetQuantity4 = targetQuantity4 + targetMap.get(key + regionInfoBean.getRegionName()).getTargetQuantity4();
						targetQuantity5 = targetQuantity5 + targetMap.get(key + regionInfoBean.getRegionName()).getTargetQuantity5();
					} else {
						trainingClassTargetBean = new TrainingClassTargetBean();
						trainingClassTargetBean.setProvince(condMap.get("province"));
						trainingClassTargetBean.setCity(condMap.get("city"));
						trainingClassTargetBean.setRegion(regionInfoBean.getRegionName());
						result.add(trainingClassTargetBean);
					}
				}
			}
		} else if (StringKit.isValid(condMap.get("province"))) {
			CityInfoBusiness cityInfoBusiness = new CityInfoBusiness();
			RegionInfoBusiness regionInfoBusiness = new RegionInfoBusiness();
			List<CityInfoBean> cityInfoList = cityInfoBusiness.getCityInfoListByProvinceName(condMap.get("province"));
			String key = condMap.get("province");
			for (CityInfoBean cityInfoBean : cityInfoList) {
				TrainingClassTargetBean trainingClassTargetBean = new TrainingClassTargetBean();
				trainingClassTargetBean.setProvince(condMap.get("province"));
				trainingClassTargetBean.setCity(cityInfoBean.getCityName());
				List<RegionInfoBean> regionInfoList = regionInfoBusiness.getRegionInfoListByCityId(cityInfoBean.getCityId());
				int target1 = 0;
				int target2 = 0;
				int target3 = 0;
				int target4 = 0;
				int target5 = 0;
				if (targetMap.containsKey(key + cityInfoBean.getCityName())) {
					target1 = target1 + targetMap.get(key + cityInfoBean.getCityName()).getTargetQuantity1();
					target2 = target2 + targetMap.get(key + cityInfoBean.getCityName()).getTargetQuantity2();
					target3 = target3 + targetMap.get(key + cityInfoBean.getCityName()).getTargetQuantity3();
					target4 = target4 + targetMap.get(key + cityInfoBean.getCityName()).getTargetQuantity4();
					target5 = target5 + targetMap.get(key + cityInfoBean.getCityName()).getTargetQuantity5();
				}
				for (RegionInfoBean regionInfoBean : regionInfoList) {
					if (targetMap.containsKey(key + cityInfoBean.getCityName() + regionInfoBean.getRegionName())) {
						target1 = target1 + targetMap.get(key + cityInfoBean.getCityName() + regionInfoBean.getRegionName()).getTargetQuantity1();
						target2 = target2 + targetMap.get(key + cityInfoBean.getCityName() + regionInfoBean.getRegionName()).getTargetQuantity2();
						target3 = target3 + targetMap.get(key + cityInfoBean.getCityName() + regionInfoBean.getRegionName()).getTargetQuantity3();
						target4 = target4 + targetMap.get(key + cityInfoBean.getCityName() + regionInfoBean.getRegionName()).getTargetQuantity4();
						target5 = target5 + targetMap.get(key + cityInfoBean.getCityName() + regionInfoBean.getRegionName()).getTargetQuantity5();
					}
				}
				targetQuantity1 = targetQuantity1 + target1;
				targetQuantity2 = targetQuantity2 + target2;
				targetQuantity3 = targetQuantity3 + target3;
				targetQuantity4 = targetQuantity4 + target4;
				targetQuantity5 = targetQuantity5 + target5;
				trainingClassTargetBean.setTargetQuantity1(target1);
				trainingClassTargetBean.setTargetQuantity2(target2);
				trainingClassTargetBean.setTargetQuantity3(target3);
				trainingClassTargetBean.setTargetQuantity4(target4);
				trainingClassTargetBean.setTargetQuantity5(target5);
				result.add(trainingClassTargetBean);

			}
		} else {
			ProvinceInfoBusiness provinceInfoBusiness = new ProvinceInfoBusiness();
			CityInfoBusiness cityInfoBusiness = new CityInfoBusiness();
			RegionInfoBusiness regionInfoBusiness = new RegionInfoBusiness();
			List<ProvinceInfoBean> provinceInfoList = provinceInfoBusiness.getProvinceInfoListByCountryId(Constants.STATUS_VALID);
			for (ProvinceInfoBean provinceInfoBean : provinceInfoList) {
				TrainingClassTargetBean trainingClassTargetBean = new TrainingClassTargetBean();
				trainingClassTargetBean.setProvince(provinceInfoBean.getProvinceName());
				List<CityInfoBean> cityInfoList = cityInfoBusiness.getCityInfoListByProvinceId(provinceInfoBean.getProvinceId());
				int target1 = 0;
				int target2 = 0;
				int target3 = 0;
				int target4 = 0;
				int target5 = 0;
				for (CityInfoBean cityInfoBean : cityInfoList) {
					if (targetMap.containsKey(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName())) {
						target1 = target1 + targetMap.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName()).getTargetQuantity1();
						target2 = target2 + targetMap.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName()).getTargetQuantity2();
						target3 = target3 + targetMap.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName()).getTargetQuantity3();
						target4 = target4 + targetMap.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName()).getTargetQuantity4();
						target5 = target5 + targetMap.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName()).getTargetQuantity5();
						List<RegionInfoBean> regionInfoList = regionInfoBusiness.getRegionInfoListByCityId(cityInfoBean.getCityId());
						for (RegionInfoBean regionInfoBean : regionInfoList) {
							if (targetMap
									.containsKey(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName() + regionInfoBean.getRegionName())) {
								target1 = target1 + targetMap
										.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName() + regionInfoBean.getRegionName())
										.getTargetQuantity1();
								target2 = target2 + targetMap
										.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName() + regionInfoBean.getRegionName())
										.getTargetQuantity2();
								target3 = target3 + targetMap
										.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName() + regionInfoBean.getRegionName())
										.getTargetQuantity3();
								target4 = target4 + targetMap
										.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName() + regionInfoBean.getRegionName())
										.getTargetQuantity4();
								target5 = target5 + targetMap
										.get(provinceInfoBean.getProvinceName() + cityInfoBean.getCityName() + regionInfoBean.getRegionName())
										.getTargetQuantity5();
							}
						}
					}
				}
				targetQuantity1 = targetQuantity1 + target1;
				targetQuantity2 = targetQuantity2 + target2;
				targetQuantity3 = targetQuantity3 + target3;
				targetQuantity4 = targetQuantity4 + target4;
				targetQuantity5 = targetQuantity5 + target5;
				trainingClassTargetBean.setTargetQuantity1(target1);
				trainingClassTargetBean.setTargetQuantity2(target2);
				trainingClassTargetBean.setTargetQuantity3(target3);
				trainingClassTargetBean.setTargetQuantity4(target4);
				trainingClassTargetBean.setTargetQuantity5(target5);
				result.add(trainingClassTargetBean);
			}
		}
		TrainingClassTargetBean trainingClassTargetBean = new TrainingClassTargetBean();
		trainingClassTargetBean.setCountry("合计");
		trainingClassTargetBean.setTargetQuantity1(targetQuantity1);
		trainingClassTargetBean.setTargetQuantity2(targetQuantity2);
		trainingClassTargetBean.setTargetQuantity3(targetQuantity3);
		trainingClassTargetBean.setTargetQuantity4(targetQuantity4);
		trainingClassTargetBean.setTargetQuantity5(targetQuantity5);
		result.add(0, trainingClassTargetBean);
		return result;
	}

	public List<TrainingClassTargetBean> processTrainingClassTargetProvinceListByMap(HashMap<String, String> condMap,
			List<TrainingClassTargetBean> trainingClassTargetList) {
		List<TrainingClassTargetBean> result = new ArrayList<TrainingClassTargetBean>();
		Map<String, TrainingClassTargetBean> targetMap = new HashMap<String, TrainingClassTargetBean>();
		// 地区的培训目标（不包括培训机构）
		for (TrainingClassTargetBean trainingClassTargetBean : trainingClassTargetList) {
			if (trainingClassTargetBean.getOrganizationId() == 0) {
				targetMap.put(getTargetKey(trainingClassTargetBean), trainingClassTargetBean);
			}
		}
		if (StringKit.isValid(condMap.get("region"))) {
		} else if (StringKit.isValid(condMap.get("city"))) {
		} else if (StringKit.isValid(condMap.get("province"))) {
			String key = condMap.get("province");
			TrainingClassTargetBean trainingClassTargetBean = new TrainingClassTargetBean();
			trainingClassTargetBean.setProvince(condMap.get("province"));
			if (targetMap.containsKey(key)) {
				result.add(targetMap.get(key));
			} else {
				result.add(trainingClassTargetBean);
			}
		} else {
			ProvinceInfoBusiness provinceInfoBusiness = new ProvinceInfoBusiness();
			List<ProvinceInfoBean> provinceInfoList = provinceInfoBusiness.getProvinceInfoListByCountryId(Constants.STATUS_VALID);
			int target1 = 0;
			int target2 = 0;
			int target3 = 0;
			int target4 = 0;
			int target5 = 0;
			for (ProvinceInfoBean provinceInfoBean : provinceInfoList) {
				TrainingClassTargetBean trainingClassTargetBean = new TrainingClassTargetBean();
				trainingClassTargetBean.setProvince(provinceInfoBean.getProvinceName());
				if (targetMap.containsKey(provinceInfoBean.getProvinceName())) {
					result.add(targetMap.get(provinceInfoBean.getProvinceName()));
					target1 = target1 + targetMap.get(provinceInfoBean.getProvinceName()).getTargetQuantity1();
					target2 = target2 + targetMap.get(provinceInfoBean.getProvinceName()).getTargetQuantity2();
					target3 = target3 + targetMap.get(provinceInfoBean.getProvinceName()).getTargetQuantity3();
					target4 = target4 + targetMap.get(provinceInfoBean.getProvinceName()).getTargetQuantity4();
					target5 = target5 + targetMap.get(provinceInfoBean.getProvinceName()).getTargetQuantity5();
				} else {
					result.add(trainingClassTargetBean);
				}
			}
			TrainingClassTargetBean trainingClassTargetBean = new TrainingClassTargetBean();
			trainingClassTargetBean.setCountry("合计");
			trainingClassTargetBean.setTargetQuantity1(target1);
			trainingClassTargetBean.setTargetQuantity2(target2);
			trainingClassTargetBean.setTargetQuantity3(target3);
			trainingClassTargetBean.setTargetQuantity4(target4);
			trainingClassTargetBean.setTargetQuantity5(target5);
			result.add(0, trainingClassTargetBean);
		}
		return result;
	}

}
