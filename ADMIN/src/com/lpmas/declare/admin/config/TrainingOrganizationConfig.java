package com.lpmas.declare.admin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class TrainingOrganizationConfig {

	// 培训单位
	public static final String ORGANIZATION_TRAINING = "ORGANIZATION_TRAINING";
	// 实训基地
	public static final String ORGANIZATION_BASE_TRAINING = "ORGANIZATION_BASE_TRAINING";
	// 认定机构 
	public static final String ORGANIZATION_AUTHORIZEDE = "ORGANIZATION_AUTHORIZEDE";
	 
	public static List<StatusBean<String, String>> ORGANIZATION_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> ORGANIZATION_MAP = new HashMap<String, String>();

	private static void initTechnicalList() {
		ORGANIZATION_LIST = new ArrayList<StatusBean<String, String>>();
		ORGANIZATION_LIST.add(new StatusBean<String, String>(ORGANIZATION_TRAINING, "培训单位库"));
		ORGANIZATION_LIST.add(new StatusBean<String, String>(ORGANIZATION_BASE_TRAINING, "实训基地库"));
		ORGANIZATION_LIST.add(new StatusBean<String, String>(ORGANIZATION_AUTHORIZEDE, "认定机构库"));
	}

	private static void initTechnicalMap() {
		ORGANIZATION_MAP = StatusKit.toMap(ORGANIZATION_LIST) ;
	}

	// 单位类型
	public static final String ORGANIZATION_TYPE_NGX = "ORGANIZATION_TYPE_NGX";
	public static final String ORGANIZATION_TYPE_NMZYXY = "ORGANIZATION_TYPE_NMZYXY";
	public static final String ORGANIZATION_TYPE_NYGX = "ORGANIZATION_TYPE_NYGX";
	public static final String ORGANIZATION_TYPE_HZS = "ORGANIZATION_TYPE_HZS";
	public static final String ORGANIZATION_TYPE_LYENLQY = "ORGANIZATION_TYPE_LYENLQY";
	public static final String ORGANIZATION_TYPE_NYXZZGBM = "ORGANIZATION_TYPE_NYXZZGBM";
	public static final String ORGANIZATION_TYPE_NKYS = "ORGANIZATION_TYPE_NKYS";
	public static final String ORGANIZATION_TYPE_NJHXX = "ORGANIZATION_TYPE_NJHXX";
	public static final String ORGANIZATION_TYPE_NJTGFWJG = "ORGANIZATION_TYPE_NJTGFWJG";
	public static final String ORGANIZATION_TYPE_QIMBJG = "ORGANIZATION_TYPE_QIMBJG";
	public static final String ORGANIZATION_TYPE_QIGBJG = "ORGANIZATION_TYPE_QIGBJG";
	
	public static List<StatusBean<String, String>> ORGANIZATION_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> ORGANIZATION_TYPE_MAP = new HashMap<String, String>();

	private static void initOrganizationTypelList() {
		ORGANIZATION_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_NGX, "农广校"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_NMZYXY, "农业职业院校"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_NYGX, "农业高校"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_HZS, "农民合作社"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_LYENLQY, "农业龙头企业"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_NYXZZGBM, "农业行政主管部门"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_NKYS, "农科院所（站）"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_NJHXX, "农机化学校"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_NJTGFWJG, "农技推广服务机构"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_QIMBJG, "其他民办机构"));
		ORGANIZATION_TYPE_LIST.add(new StatusBean<String, String>(ORGANIZATION_TYPE_QIGBJG, "其他公办机构"));
	}

	private static void initOrganizationTypeMap() {
		ORGANIZATION_TYPE_MAP = StatusKit.toMap(ORGANIZATION_TYPE_LIST);
	}

	static {
		initTechnicalList();
		initTechnicalMap();
		initOrganizationTypelList();
		initOrganizationTypeMap();
	}

}
