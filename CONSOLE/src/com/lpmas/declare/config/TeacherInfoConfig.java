package com.lpmas.declare.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class TeacherInfoConfig {
	// 教师等级
	public static final String TECHNICAL_GRADE_PRIMARY = "TECHNICAL_GRADE_PRIMARY";
	public static final String TECHNICAL_GRADE_MIDDLE = "TECHNICAL_GRADE_MIDDLE";
	public static final String TECHNICAL_GRADE_SUB_SENIOR = "TECHNICAL_GRADE_SUB_SENIOR";
	public static final String TECHNICAL_GRADE_SENIOR = "TECHNICAL_GRADE_SENIOR";

	public static List<StatusBean<String, String>> TECHNICAL_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> TECHNICAL_MAP = new HashMap<String, String>();

	// 教师类型
	public static final int TEACHER_TYPE_PLANT = 1; // 种植业
	public static final int TEACHER_TYPE_BREED = 2; // 养殖业
	public static final int TEACHER_TYPE_MODERN_AGRICULTURE = 3; // 现代农业
	public static final int TEACHER_TYPE_PUBLIC_FOUNDATION = 4; // 公共基础
	public static final int TEACHER_TYPE_ENGINEERING_AND_SERVER = 5; // 农业工程与服务
	public static final int TEACHER_TYPE_PUBLIC_MANAGE = 6; // 农村经营与农村管理
	public static final int TEACHER_TYPE_OTHER = 7; // 其他

	public static List<StatusBean<Integer, String>> TEACHER_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static Map<Integer, String> TEACHER_TYPE_MAP = new HashMap<Integer, String>();

	// 审批动作
	public static final String APPROVE_OPERATION_PASS = "PASS";
	public static final String APPROVE_OPERATION_FAIL = "FAIL";
	public static final String APPROVE_OPERATION_REJECT = "REJECT";
	public static List<StatusBean<String, String>> APPROVE_OPERATION_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> APPROVE_OPERATION_MAP = new HashMap<String, String>();

	// 申报状态
	public static final String DECLATE_STATUS_COMMIT = "COMMIT";
	public static final String DECLATE_STATUS_APPROVED = "APPROVED";
	public static final String DECLATE_STATUS_FAIL = "FAIL";
	public static final String DECLATE_STATUS_REJECTED = "REJECTED";
	public static List<StatusBean<String, String>> DECLATE_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> DECLATE_STATUS_MAP = new HashMap<String, String>();

	private static void initDeclareStatusList() {
		DECLATE_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		DECLATE_STATUS_LIST.add(new StatusBean<String, String>(DECLATE_STATUS_COMMIT, "待审核"));
		DECLATE_STATUS_LIST.add(new StatusBean<String, String>(DECLATE_STATUS_APPROVED, "审核通过"));
		DECLATE_STATUS_LIST.add(new StatusBean<String, String>(DECLATE_STATUS_FAIL, "审核不通过"));
		DECLATE_STATUS_LIST.add(new StatusBean<String, String>(DECLATE_STATUS_REJECTED, "审核驳回"));
	}

	private static void initDeclareTypeMap() {
		DECLATE_STATUS_MAP = StatusKit.toMap(DECLATE_STATUS_LIST);
	}

	private static void initApproveOperationList() {
		APPROVE_OPERATION_LIST = new ArrayList<StatusBean<String, String>>();
		APPROVE_OPERATION_LIST.add(new StatusBean<String, String>(APPROVE_OPERATION_PASS, "通过"));
		APPROVE_OPERATION_LIST.add(new StatusBean<String, String>(APPROVE_OPERATION_FAIL, "不通过"));
		APPROVE_OPERATION_LIST.add(new StatusBean<String, String>(APPROVE_OPERATION_REJECT, "驳回"));
	}

	private static void initApproveOperationMap() {
		APPROVE_OPERATION_MAP = StatusKit.toMap(APPROVE_OPERATION_LIST);
	}

	private static void initTechnicalList() {
		TECHNICAL_LIST = new ArrayList<StatusBean<String, String>>();
		TECHNICAL_LIST.add(new StatusBean<String, String>(TECHNICAL_GRADE_PRIMARY, "初级"));
		TECHNICAL_LIST.add(new StatusBean<String, String>(TECHNICAL_GRADE_MIDDLE, "中级"));
		TECHNICAL_LIST.add(new StatusBean<String, String>(TECHNICAL_GRADE_SUB_SENIOR, "副高级"));
		TECHNICAL_LIST.add(new StatusBean<String, String>(TECHNICAL_GRADE_SENIOR, "正高级"));
	}

	private static void initTechnicalMap() {
		TECHNICAL_MAP = StatusKit.toMap(TECHNICAL_LIST);
	}

	private static void initTeachnicalList() {
		TEACHER_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		TEACHER_TYPE_LIST.add(new StatusBean<Integer, String>(TEACHER_TYPE_PLANT, "种植业"));
		TEACHER_TYPE_LIST.add(new StatusBean<Integer, String>(TEACHER_TYPE_BREED, "养殖业"));
		TEACHER_TYPE_LIST.add(new StatusBean<Integer, String>(TEACHER_TYPE_MODERN_AGRICULTURE, "现代农业"));
		TEACHER_TYPE_LIST.add(new StatusBean<Integer, String>(TEACHER_TYPE_PUBLIC_FOUNDATION, "公共基础"));
		TEACHER_TYPE_LIST.add(new StatusBean<Integer, String>(TEACHER_TYPE_ENGINEERING_AND_SERVER, "农村工程与服务"));
		TEACHER_TYPE_LIST.add(new StatusBean<Integer, String>(TEACHER_TYPE_PUBLIC_MANAGE, "农村经营与农村管理"));
		TEACHER_TYPE_LIST.add(new StatusBean<Integer, String>(TEACHER_TYPE_OTHER, "其他"));
	}

	private static void initTeachnicalMap() {
		TEACHER_TYPE_MAP = StatusKit.toMap(TEACHER_TYPE_LIST);
	}

	static {
		initTechnicalList();
		initTechnicalMap();

		initTeachnicalList();
		initTeachnicalMap();

		initDeclareStatusList();
		initDeclareTypeMap();

		initApproveOperationList();
		initApproveOperationMap();
	}
}
