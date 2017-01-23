package com.lpmas.declare.migrate.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.declare.migrate.bean.TabCityInfoBean;
import com.lpmas.declare.migrate.bean.TabProvinceInfoBean;
import com.lpmas.declare.migrate.bean.TabRegionBean;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.business.TabCityInfoBusiness;
import com.lpmas.declare.migrate.business.TabProvinceInfoBusiness;
import com.lpmas.declare.migrate.business.TabRegionBusiness;
import com.lpmas.declare.migrate.config.ExcelConfig;
import com.lpmas.declare.migrate.config.OrganizationConfig;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.util.StringKit;

public class ExcelHandler {
	/**
	 * 返回 serverId + regionId 的map
	 * 
	 * @return
	 */
	public static Map<String, String> getRegionMap() {
		Map<String, String> map = new HashMap<String, String>();
		HashMap<String, String> condMap = new HashMap<String, String>();
		TabRegionBusiness business = new TabRegionBusiness();
		List<TabRegionBean> tabRegionList = business.getTabRegionListByMap(condMap);
		for (TabRegionBean tabRegion : tabRegionList) {
			map.put(tabRegion.getServerId(), tabRegion.getCode());
		}

		return map;
	}

	/**
	 * 返回省市区的信息
	 * 
	 * @param serverId
	 *            tab_major的serverId
	 * @return List<String> 0 等级  1省  2市 3区 
	 */
	public static List<String> getRegionList(String serverId) {
		List<String> regionList = new ArrayList<String>();

		String code = "";
		int length = 0;

		if (serverId != null) {
			Map<String, String> regionMap = getRegionMap();
			code = regionMap.get(serverId);
			// 地区等级 3省市区 2省市 1 省
			length = serverId.split("\\.").length;
		}

		if (length == 1) {
			// 国家
			regionList.add(DeclareAdminConfig.COUNTRY_STR);
			regionList.add("");
			regionList.add("");
			regionList.add("");
		} else if (Pattern.compile("^67.*").matcher(code).matches()) {
			regionList.add("");
			regionList.add("辽宁省");
			regionList.add("大连市");
			regionList.add("");
		} else if (Pattern.compile("^68.*").matcher(code).matches()) {
			regionList.add("");
			regionList.add("山东省");
			regionList.add("青岛市");
			regionList.add("");
		} else if (Pattern.compile("^69.*").matcher(code).matches()) {
			regionList.add("");
			regionList.add("黑龙江省");
			regionList.add("");
			regionList.add("");
		} else if (Pattern.compile("^70.*").matcher(code).matches()) {
			regionList.add("");
			regionList.add("浙江省");
			regionList.add("");
			regionList.add("");
		} else if (Pattern.compile("^71.*").matcher(code).matches()) {
			regionList.add("");
			regionList.add("新疆维吾尔自治区");
			regionList.add("");
			regionList.add("");
		} else if (Pattern.compile("^73.*").matcher(code).matches()) {
			regionList.add("");
			regionList.add("广东省");
			regionList.add("");
			regionList.add("");
		} else if (length == 2) {
			regionList.add("");
			// 省
			TabProvinceInfoBusiness provinceBusiness = new TabProvinceInfoBusiness();
			TabProvinceInfoBean tabProvinceInfoBean = provinceBusiness.getProvinceByCode(code);
			regionList.add(tabProvinceInfoBean.getProvinceName());
			regionList.add("");
			regionList.add("");
		} else if (length == 3) {
			regionList.add("");
			// 市
			TabCityInfoBusiness cityBusiness = new TabCityInfoBusiness();
			TabCityInfoBean tabCityInfoBean = cityBusiness.getCityByCode(code);
			code = StringKit.substring(code, 4, "00");
			TabProvinceInfoBusiness provinceBusiness = new TabProvinceInfoBusiness();
			TabProvinceInfoBean tabProvinceInfoBean = provinceBusiness.getProvinceByCode(code);
			regionList.add(tabProvinceInfoBean.getProvinceName());
			regionList.add(tabCityInfoBean.getCityName());
			regionList.add("");
		} else if (length == 4) {
			regionList.add("");
			// 区
			TabRegionBusiness regionBusiness = new TabRegionBusiness();
			TabRegionInfoBean tabRegionBean = regionBusiness.getRregionByCode(code);
			code = StringKit.substring(code, 4, "00");
			TabCityInfoBusiness cityBusiness = new TabCityInfoBusiness();
			TabCityInfoBean tabCityInfoBean = cityBusiness.getCityByCode(code);
			code = StringKit.substring(code, 2, "0000");
			TabProvinceInfoBusiness provinceBusiness = new TabProvinceInfoBusiness();
			TabProvinceInfoBean tabProvinceInfoBean = provinceBusiness.getProvinceByCode(code);
			regionList.add(tabProvinceInfoBean.getProvinceName());
			regionList.add(tabCityInfoBean.getCityName());
			regionList.add(tabRegionBean.getRegionName());
		}
		return regionList;
	}

	/**
	 * 获取地区对应的信息
	 * 
	 * @return
	 */
	public static Map<String, String> getMajorTypeIdMap() {
		ExcelReadKit excelReadKit = new ExcelReadKit();
		ExcelReadResultBean bean = excelReadKit.readExcel(ExcelConfig.majorTypeFile, 0);

		Map<String, String> map = new HashMap<String, String>();
		List<List<String>> lists = bean.getContentList();
		for (List<String> list : lists) {
			map.put(list.get(list.size() - 1), list.get(0));
		}
		return map;
	}

	/**
	 * 传回类型 返回对应的代码
	 * 
	 * @param type
	 * @return
	 */
	public static String changeType(String type) {
		if (OrganizationConfig.ORGANIZATION_TYPE_NGX.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_NGX;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_NMZYXY.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_NMZYXY;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_NYGX.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_NYGX;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_NMZYXY.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_NMZYXY;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_LYENLQY.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_LYENLQY;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_NYXZZGBM.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_NYXZZGBM;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_NKYS.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_NKYS;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_NJHXX.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_NJHXX;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_NJTGFWJG.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_NJTGFWJG;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_QIMBJG.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_QIMBJG;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_QIGBJG.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_QIGBJG;
		} else if (OrganizationConfig.ORGANIZATION_TYPE_HZS.equals(type)) {
			type = TrainingOrganizationConfig.ORGANIZATION_TYPE_HZS;
		}
		return type;
	}

}
