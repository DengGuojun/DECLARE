package com.lpmas.declare.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class TrainingClassInfoConfig {
	// 项目实施第一年
	public static final String EDUCATION1 = "education1";
	public static final String PRACTICE1 = "practice1";
	public static final String ENTREPRENEURSHIP1 = "entrepreneurship1";
	public static final String IDENTIFY1 = "identify1";
	public static final String TRACK1 = "track1";
	// 项目实施第二年
	public static final String EDUCATION2 = "education2";
	public static final String PRACTICE2 = "practice2";
	public static final String ENTREPRENEURSHIP2 = "entrepreneurship2";
	public static final String IDENTIFY2 = "identify2";
	public static final String TRACK2 = "track2";

	// 培训班状态
	public static final String ClASS_STATUS_EDIT = "EDIT";
	public static final String CLASS_STATUS_APPROVED = "APPROVED";
	public static final String CLASS_STATUS_SUBMITTED = "SUBMITTED";
	public static final String ClASS_STATUS_REJECTED = "REJECTED";
	public static List<StatusBean<String, String>> ClASS_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> ClASS_STATUS_MAP = new HashMap<String, String>();

	// 认定状态
	public static final String AUTH_STATUS_APPROVE = "APPROVE";
	public static final String AUTH_STATUS_WAIT_APPROVE = "WAIT_APPROVE";
	public static List<StatusBean<String, String>> AUTH_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> AUTH_STATUS_MAP = new HashMap<String, String>();
	
	public static final String CLASS_EVALUATE_TYPE_EFFECT = "EFFECT"; //培训效果
	public static final String CLASS_EVALUATE_TYPE_ORGANIZE = "ORGANIZE"; //组织管理
	
	public static List<StatusBean<String, String>> CLASS_EVALUATE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> CLASS_EVALUATE_TYPE_MAP = new HashMap<String, String>();

	// 提交动作
	public static final String COMMIT_ACTION_SUBMIT = "SUBMIT"; // 上传
	public static final String COMMIT_ACTION_REPORT = "REPORT"; // 上报
	public static final String COMMIT_ACTION_RESUBMIT = "RESUBMIT"; // 重报
	public static final String COMMIT_ACTION_DELETE = "DELETE"; // 删除
	
	static {
		initClassStatusList();
		initClassStatusMap();
		initAuthStatusList();
		initAuthStatusMap();
		initClassEvaluateTypeList();
		initClassEvaluateTypeMap();
	}

	private static void initClassStatusList() {
		ClASS_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		ClASS_STATUS_LIST.add(new StatusBean<String, String>(ClASS_STATUS_EDIT, "已填写"));
		ClASS_STATUS_LIST.add(new StatusBean<String, String>(CLASS_STATUS_APPROVED, "在省"));
		ClASS_STATUS_LIST.add(new StatusBean<String, String>(CLASS_STATUS_SUBMITTED, "已上传"));
		ClASS_STATUS_LIST.add(new StatusBean<String, String>(ClASS_STATUS_REJECTED, "需重报"));
	}

	private static void initClassStatusMap() {
		ClASS_STATUS_MAP = StatusKit.toMap(ClASS_STATUS_LIST);
	}
	
	private static void initAuthStatusList() {
		AUTH_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		AUTH_STATUS_LIST.add(new StatusBean<String, String>(AUTH_STATUS_APPROVE, "已认定"));
		AUTH_STATUS_LIST.add(new StatusBean<String, String>(AUTH_STATUS_WAIT_APPROVE, "未认定"));
	}

	private static void initAuthStatusMap() {
		AUTH_STATUS_MAP = StatusKit.toMap(AUTH_STATUS_LIST);
	}
	
	private static void initClassEvaluateTypeList() {
		CLASS_EVALUATE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		CLASS_EVALUATE_TYPE_LIST.add(new StatusBean<String, String>(CLASS_EVALUATE_TYPE_EFFECT, "培训效果"));
		CLASS_EVALUATE_TYPE_LIST.add(new StatusBean<String, String>(CLASS_EVALUATE_TYPE_ORGANIZE, "组织管理"));
	}

	private static void initClassEvaluateTypeMap() {
		CLASS_EVALUATE_TYPE_MAP = StatusKit.toMap(CLASS_EVALUATE_TYPE_LIST);
	}
}
