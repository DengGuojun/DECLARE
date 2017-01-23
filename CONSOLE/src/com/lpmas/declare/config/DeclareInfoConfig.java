package com.lpmas.declare.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class DeclareInfoConfig {

	// 填写模块
	public static final String MODULE_FARMER_INFO = "FarmerInfo";
	public static final String MODULE_FARMER_CONTACT_INFO = "FarmerContactInfo";
	public static final String MODULE_FARMER_SKILL_INFO = "FarmerSkillInfo";
	public static final String MODULE_FARMER_INDUSTRY_INFO = "FarmerIndustryInfo";
	public static final String MODULE_FARMER_JOB_INFO = "FarmerJobInfo";
	public static List<StatusBean<String, String>> DECLARE_MODULE_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> DECLARE_MODULE_MAP = new HashMap<String, String>();

	// 申报类型
	public static final int DECLARE_TYPE_YOUNG_FARMER = 1;
	public static final int DECLARE_TYPE_PRODUCT_FARMER = 2;
	public static final int DECLARE_TYPE_TECHNICAL_FARMER = 3;
	public static final int DECLARE_TYPE_SERVICE_FARMER = 4;
	public static final int DECLARE_TYPE_LEADER_FARMER = 5;
	public static List<StatusBean<Integer, String>> DECLARE_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static Map<Integer, String> DECLARE_TYPE_MAP = new HashMap<Integer, String>();

	//申报方式
	public static final int REGISTRY_TYPE_FARMER = 0;
	public static final int REGISTRY_TYPE_RECOMMEND = 1;
	public static List<StatusBean<Integer, String>> REGISTRY_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static Map<Integer, String> REGISTRY_TYPE_MAP = new HashMap<Integer, String>();
	
	// 进度状态
	public static final String DECLARE_STATUS_SUBMIT = "SUBMIT";
	public static final String DECLARE_STATUS_NOT_SUBMIT = "NOT_SUBMIT";
	public static final String DECLARE_STATUS_APPROVE = "APPROVE";
	public static final String DECLARE_STATUS_NOT_APPROVE = "NOT_APPROVE";
	public static final String DECLARE_STATUS_REGION_APPROVE = "REGION_APPROVE";
	public static final String DECLARE_STATUS_REJECT = "REJECT";
	public static List<StatusBean<String, String>> DECLARE_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> DECLARE_STATUS_MAP = new HashMap<String, String>();

	// 申请方式
	public static final String APPLY_TYPE_PERSONAL = "PERSONAL";
	public static final String APPLY_TYPE_ENTERPRISES_TRAIN_RECOMMEND = "ENTERPRISES_TRAIN_RECOMMEND";
	public static final String APPLY_TYPE_DEPARTMENT_RECONMEND = "DEPARTMENT_RECONMEND";
	public static List<StatusBean<String, String>> APPLY_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> APPLY_TYPE_MAP = new HashMap<String, String>();

	// 认定状态
	public static final String AUTH_STATUS_APPROVE = "APPROVE";
	public static final String AUTH_STATUS_WAIT_APPROVE = "WAIT_APPROVE";
	public static List<StatusBean<String, String>> AUTH_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> AUTH_STATUS_MAP = new HashMap<String, String>();

	// 对象类别
	public static final String DECLARE_CATEGORY_SPARE = "MDDYBX";
	public static final String DECLARE_CATEGORY_PRESENT = "SBJNXM";
	public static List<StatusBean<String, String>> DECLARE_CATEGORY_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> DECLARE_CATEGORY_MAP = new HashMap<String, String>();

	static {
		initDeclareTypeList();
		initDeclareTypeMap();

		initDeclareStatusList();
		initDeclareStatusMap();
		
		initRegistryTypeList();
		initRegistryTypeMap();

		initModuleList();
		initModuleMap();

		initApplyTypeList();
		initApplyTypeMap();

		initAuthStatusList();
		initAuthStatusMap();

		initDeclareCategoryList();
		initDeclareCategoryMap();
	}

	private static void initDeclareTypeList() {
		DECLARE_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		DECLARE_TYPE_LIST.add(new StatusBean<Integer, String>(DECLARE_TYPE_YOUNG_FARMER, "现代青年农场主"));
		DECLARE_TYPE_LIST.add(new StatusBean<Integer, String>(DECLARE_TYPE_PRODUCT_FARMER, "生产经营职业农民"));
		DECLARE_TYPE_LIST.add(new StatusBean<Integer, String>(DECLARE_TYPE_TECHNICAL_FARMER, "专业技能型农民"));
		DECLARE_TYPE_LIST.add(new StatusBean<Integer, String>(DECLARE_TYPE_SERVICE_FARMER, "专业服务型农民"));
		DECLARE_TYPE_LIST.add(new StatusBean<Integer, String>(DECLARE_TYPE_LEADER_FARMER, "新型农业经营带头人"));
	}

	private static void initDeclareTypeMap() {
		DECLARE_TYPE_MAP = StatusKit.toMap(DECLARE_TYPE_LIST);
	}

	private static void initModuleList() {
		DECLARE_MODULE_LIST = new ArrayList<StatusBean<String, String>>();
		DECLARE_MODULE_LIST.add(new StatusBean<String, String>(MODULE_FARMER_INFO, "基本信息"));
		DECLARE_MODULE_LIST.add(new StatusBean<String, String>(MODULE_FARMER_CONTACT_INFO, "联系信息"));
		DECLARE_MODULE_LIST.add(new StatusBean<String, String>(MODULE_FARMER_SKILL_INFO, "申请培训信息"));
		DECLARE_MODULE_LIST.add(new StatusBean<String, String>(MODULE_FARMER_INDUSTRY_INFO, "生产经营状况"));
		DECLARE_MODULE_LIST.add(new StatusBean<String, String>(MODULE_FARMER_JOB_INFO, "农务工作信息"));
	}

	private static void initModuleMap() {
		DECLARE_MODULE_MAP = StatusKit.toMap(DECLARE_MODULE_LIST);
	}

	private static void initDeclareStatusList() {
		DECLARE_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		DECLARE_STATUS_LIST.add(new StatusBean<String, String>(DECLARE_STATUS_NOT_SUBMIT, "未提交"));
		DECLARE_STATUS_LIST.add(new StatusBean<String, String>(DECLARE_STATUS_SUBMIT, "已提交"));
		DECLARE_STATUS_LIST.add(new StatusBean<String, String>(DECLARE_STATUS_REGION_APPROVE, "县级通过"));
		DECLARE_STATUS_LIST.add(new StatusBean<String, String>(DECLARE_STATUS_APPROVE, "通过"));
		DECLARE_STATUS_LIST.add(new StatusBean<String, String>(DECLARE_STATUS_NOT_APPROVE, "不通过"));
		DECLARE_STATUS_LIST.add(new StatusBean<String, String>(DECLARE_STATUS_REJECT, "驳回"));
	}

	private static void initDeclareStatusMap() {
		DECLARE_STATUS_MAP = StatusKit.toMap(DECLARE_STATUS_LIST);
	}

	private static void initApplyTypeList() {
		APPLY_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		APPLY_TYPE_LIST.add(new StatusBean<String, String>(APPLY_TYPE_PERSONAL, "个人申请"));
		APPLY_TYPE_LIST.add(new StatusBean<String, String>(APPLY_TYPE_ENTERPRISES_TRAIN_RECOMMEND, "农业企业及培训单位联合推荐"));
		APPLY_TYPE_LIST.add(new StatusBean<String, String>(APPLY_TYPE_DEPARTMENT_RECONMEND, "主管部门推荐"));

	}

	private static void initApplyTypeMap() {
		APPLY_TYPE_MAP = StatusKit.toMap(APPLY_TYPE_LIST);
	}

	private static void initAuthStatusList() {
		AUTH_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		AUTH_STATUS_LIST.add(new StatusBean<String, String>(AUTH_STATUS_APPROVE, "已通过"));
		AUTH_STATUS_LIST.add(new StatusBean<String, String>(AUTH_STATUS_WAIT_APPROVE, "未通过"));
	}

	private static void initAuthStatusMap() {
		AUTH_STATUS_MAP = StatusKit.toMap(AUTH_STATUS_LIST);
	}
	
	private static void initRegistryTypeList() {
		REGISTRY_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		REGISTRY_TYPE_LIST.add(new StatusBean<Integer, String>(REGISTRY_TYPE_FARMER, "个人申报"));
		REGISTRY_TYPE_LIST.add(new StatusBean<Integer, String>(REGISTRY_TYPE_RECOMMEND, "本级推荐"));

	}

	private static void initRegistryTypeMap() {
		REGISTRY_TYPE_MAP = StatusKit.toMap(REGISTRY_TYPE_LIST);

	}

	private static void initDeclareCategoryList() {
		DECLARE_CATEGORY_LIST = new ArrayList<StatusBean<String, String>>();
		DECLARE_CATEGORY_LIST.add(new StatusBean<String, String>(DECLARE_CATEGORY_SPARE, "摸底调研备选"));
		DECLARE_CATEGORY_LIST.add(new StatusBean<String, String>(DECLARE_CATEGORY_PRESENT, "申报今年项目"));
	}

	private static void initDeclareCategoryMap() {
		DECLARE_CATEGORY_MAP = StatusKit.toMap(DECLARE_CATEGORY_LIST);
	}
}
