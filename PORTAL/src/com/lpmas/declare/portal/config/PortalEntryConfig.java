package com.lpmas.declare.portal.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class PortalEntryConfig {

	// 模块入口配置
	public static final String MODULE_FARMER_INFO_URL = "declare/FarmerInfoManage.do";
	public static final String MODULE_FARMER_CONTACT_INFO_URL = "declare/FarmerContactInfoManage.do";
	public static final String MODULE_FARMER_SKILL_INFO_URL = "declare/FarmerSkillInfoManage.do";
	public static final String MODULE_FARMER_INDUSTRY_INFO_URL = "declare/FarmerIndustryInfoManage.do";
	public static final String MODULE_FARMER_JOB_INFO_URL = "declare/FarmerJobInfoManage.do";
	public static List<StatusBean<String, String>> DECLARE_MODULE_URL_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> DECLARE_MODULE_URL_MAP = new HashMap<String, String>();

	static {
		initDeclareModuleUrlList();
		initDeclareModuleUrlMap();
	}

	private static void initDeclareModuleUrlList() {
		DECLARE_MODULE_URL_LIST = new ArrayList<StatusBean<String, String>>();
		DECLARE_MODULE_URL_LIST
				.add(new StatusBean<String, String>(DeclareInfoConfig.MODULE_FARMER_INFO, MODULE_FARMER_INFO_URL));
		DECLARE_MODULE_URL_LIST.add(new StatusBean<String, String>(DeclareInfoConfig.MODULE_FARMER_CONTACT_INFO,
				MODULE_FARMER_CONTACT_INFO_URL));
		DECLARE_MODULE_URL_LIST.add(new StatusBean<String, String>(DeclareInfoConfig.MODULE_FARMER_SKILL_INFO,
				MODULE_FARMER_SKILL_INFO_URL));
		DECLARE_MODULE_URL_LIST.add(new StatusBean<String, String>(DeclareInfoConfig.MODULE_FARMER_INDUSTRY_INFO,
				MODULE_FARMER_INDUSTRY_INFO_URL));
		DECLARE_MODULE_URL_LIST.add(
				new StatusBean<String, String>(DeclareInfoConfig.MODULE_FARMER_JOB_INFO, MODULE_FARMER_JOB_INFO_URL));
	}

	private static void initDeclareModuleUrlMap() {
		DECLARE_MODULE_URL_MAP = StatusKit.toMap(DECLARE_MODULE_URL_LIST);
	}

}
