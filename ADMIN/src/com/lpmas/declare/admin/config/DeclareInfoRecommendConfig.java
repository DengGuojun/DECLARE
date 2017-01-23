package com.lpmas.declare.admin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class DeclareInfoRecommendConfig {
	// 提交动作
	public static final String COMMIT_ACTION_SUBMIT = "SUBMIT"; // 提交
	public static final String COMMIT_ACTION_REGION_APPROVE = "REGION_APPROVE"; // 县级通过
	public static final String COMMIT_ACTION_APPROVE = "APPROVE"; // 通过
	public static final String COMMIT_ACTION_NOT_APPROVE = "NOT_APPROVE"; // 不通过
	public static final String COMMIT_ACTION_REJECT = "REJECT"; // 驳回
	public static final String COMMIT_ACTION_DELETE = "DELETE"; // 删除
	public static final String COMMIT_ACTION_CHANGE = "CHANGE"; // 转换类型
	// 页面类型
	public static final Integer TYPE_VERIFY = 1; // 对象审核
	public static final Integer TYPE_MANAGE = 2; // 对象管理
	public static final Integer TYPE_CLASSIFY = 3; // 对象类别
	public static List<StatusBean<Integer, String>> MODEL_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> MODEL_TYPE_MAP = new HashMap<Integer, String>();
	// 审核状态
	public static List<StatusBean<String, String>> REVIEW_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> REVIEW_STATUS_MAP = new HashMap<String, String>();
	// 统计时人员状态
	public static final String COUNT_STATUS_ALL = "ALL";
	public static final String COUNT_STATUS_CITY_APPROVE = "CITY_APPROVE";
	public static List<StatusBean<String, String>> COUNT_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> COUNT_STATUS_MAP = new HashMap<String, String>();
	// 申请方式
	public static List<StatusBean<String, String>> APPLY_LEVEL_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> APPLY_LEVEL_MAP = new HashMap<String, String>();
	// 导出表头
	public static List<String> DECLARE_REPORT_HEADER_LIST = new ArrayList<String>();
	public static List<String> DECLARE_REPORT_SECOND_HEADER_LIST = new ArrayList<String>();

	static {
		initModelTypeList();
		initModelTypeMap();

		initReviewStatusList();
		initReviewStatusMap();

		initCountStatusList();
		initCountStatusMap();

		initApplyLevelList();
		initApplyLevelMap();

		initDeclareReportHeaderList();
		initDeclareReportSecondHeaderList();
	}

	private static void initModelTypeList() {
		MODEL_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		MODEL_TYPE_LIST.add(new StatusBean<Integer, String>(TYPE_VERIFY, "对象审核"));
		MODEL_TYPE_LIST.add(new StatusBean<Integer, String>(TYPE_MANAGE, "对象管理"));
		MODEL_TYPE_LIST.add(new StatusBean<Integer, String>(TYPE_CLASSIFY, "对象类别"));

	}

	private static void initModelTypeMap() {
		MODEL_TYPE_MAP = StatusKit.toMap(MODEL_TYPE_LIST);

	}

	private static void initReviewStatusList() {
		REVIEW_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		REVIEW_STATUS_LIST.add(new StatusBean<String, String>(DeclareInfoConfig.DECLARE_STATUS_SUBMIT, "待审核"));
		REVIEW_STATUS_LIST.add(new StatusBean<String, String>(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE, "县级通过"));
		REVIEW_STATUS_LIST.add(new StatusBean<String, String>(DeclareInfoConfig.DECLARE_STATUS_APPROVE, "通过"));

	}

	private static void initReviewStatusMap() {
		REVIEW_STATUS_MAP = StatusKit.toMap(REVIEW_STATUS_LIST);

	}

	private static void initCountStatusList() {
		COUNT_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		COUNT_STATUS_LIST.add(new StatusBean<String, String>(COUNT_STATUS_ALL, "全部"));
		COUNT_STATUS_LIST.add(new StatusBean<String, String>(COUNT_STATUS_CITY_APPROVE, "在省"));

	}

	private static void initCountStatusMap() {
		COUNT_STATUS_MAP = StatusKit.toMap(COUNT_STATUS_LIST);
	}

	private static void initApplyLevelMap() {
		APPLY_LEVEL_LIST = new ArrayList<StatusBean<String, String>>();
		APPLY_LEVEL_LIST.add(new StatusBean<String, String>(DeclareAdminConfig.ADMIN_LEVEL_REGION, "县级推荐"));
		APPLY_LEVEL_LIST.add(new StatusBean<String, String>(DeclareAdminConfig.ADMIN_LEVEL_CITY, "市级推荐"));
		APPLY_LEVEL_LIST.add(new StatusBean<String, String>(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE, "省级推荐"));

	}

	private static void initApplyLevelList() {
		APPLY_LEVEL_MAP = StatusKit.toMap(APPLY_LEVEL_LIST);
	}

	public static void initDeclareReportHeaderList() {
		DECLARE_REPORT_HEADER_LIST = new ArrayList<String>();
		DECLARE_REPORT_HEADER_LIST.add("姓名");
		DECLARE_REPORT_HEADER_LIST.add("性别");
		DECLARE_REPORT_HEADER_LIST.add("文化程度");
		DECLARE_REPORT_HEADER_LIST.add("身份证号");
		DECLARE_REPORT_HEADER_LIST.add("手机号");
		DECLARE_REPORT_HEADER_LIST.add("人员类别");
		DECLARE_REPORT_HEADER_LIST.add("主体产业");
		DECLARE_REPORT_HEADER_LIST.add("申请方式");
		DECLARE_REPORT_HEADER_LIST.add("地区");
		DECLARE_REPORT_HEADER_LIST.add("审核状态");
	}

	public static void initDeclareReportSecondHeaderList() {
		DECLARE_REPORT_SECOND_HEADER_LIST = new ArrayList<String>();
		DECLARE_REPORT_SECOND_HEADER_LIST.add("姓名");
		DECLARE_REPORT_SECOND_HEADER_LIST.add("性别");
		DECLARE_REPORT_SECOND_HEADER_LIST.add("文化程度");
		DECLARE_REPORT_SECOND_HEADER_LIST.add("身份证号");
		DECLARE_REPORT_SECOND_HEADER_LIST.add("手机号");
		DECLARE_REPORT_SECOND_HEADER_LIST.add("人员类别");
		DECLARE_REPORT_SECOND_HEADER_LIST.add("申请方式");
		DECLARE_REPORT_SECOND_HEADER_LIST.add("地区");
		DECLARE_REPORT_SECOND_HEADER_LIST.add("审核状态");
	}
}
