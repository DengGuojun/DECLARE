package com.lpmas.declare.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class TrainingClassUserConfig {
	// 添加状态
	public static final String USER_STATUS_ADD = "ADD";
	public static final String USER_STATUS_NOT_ADD = "NOT_ADD";
	public static List<StatusBean<String, String>> USER_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> USER_STATUS_MAP = new HashMap<String, String>();

	// 学员状态
	public static final String USER_STATUS_APPROVE = "APPROVE";
	// 提交动作
	public static final String COMMIT_ACTION_ADD = "ADD"; // 添加
	public static final String COMMIT_ACTION_DELETE = "DELETE"; // 删除

	public static final String COMMIT_ACTION_AUTH_APPROVE = "AUTH_APPROVE"; // 认定
	public static final String COMMIT_ACTION_AUTH_WAIT_APPROVE = "AUTH_WAIT_APPROVE"; // 取消认定

	public static final String EVERY_THREE_YEAR = "EVERY_THREE_YEAR"; // 三年一次
	public static final String EVERY_TWO_YEAR = "EVERY_TWO_YEAR"; // 两年一次
	public static final String EVERY_YEAR = "EVERY_YEAR"; // 每年一次

	public static List<StatusBean<String, String>> CONFIG_FREQUENCY_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> CONFIG_FREQUENCY_MAP = new HashMap<String, String>();

	public static final String DIRECT_UNDER = "DIRECT_UNDER"; // 直属
	public static final String NOT_DIRECT_UNDER = "NOT_DIRECT_UNDER"; // 直属以及下级

	public static List<StatusBean<String, String>> CONFIG_MODE_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> CONFIG_MODE_MAP = new HashMap<String, String>();

	static {
		initUserStatusList();
		initUserStatusMap();
		initConfigFrequencyList();
		initConfigFrequencyMap();
		initConfigModeList();
		initConfigModeMap();
	}

	private static void initUserStatusList() {
		USER_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		USER_STATUS_LIST.add(new StatusBean<String, String>(USER_STATUS_ADD, "已添加"));
		USER_STATUS_LIST.add(new StatusBean<String, String>(USER_STATUS_NOT_ADD, "未添加"));
	}

	private static void initUserStatusMap() {
		USER_STATUS_MAP = StatusKit.toMap(USER_STATUS_LIST);
	}

	private static void initConfigFrequencyList() {
		CONFIG_FREQUENCY_LIST = new ArrayList<StatusBean<String, String>>();
		CONFIG_FREQUENCY_LIST.add(new StatusBean<String, String>(EVERY_THREE_YEAR, "三年一次"));
		CONFIG_FREQUENCY_LIST.add(new StatusBean<String, String>(EVERY_TWO_YEAR, "两年一次"));
		CONFIG_FREQUENCY_LIST.add(new StatusBean<String, String>(EVERY_YEAR, "一年一次"));
	}

	private static void initConfigFrequencyMap() {
		CONFIG_FREQUENCY_MAP = StatusKit.toMap(CONFIG_FREQUENCY_LIST);
	}

	private static void initConfigModeList() {
		CONFIG_MODE_LIST = new ArrayList<StatusBean<String, String>>();
		CONFIG_MODE_LIST.add(new StatusBean<String, String>(DIRECT_UNDER, "仅设置直属"));
		CONFIG_MODE_LIST.add(new StatusBean<String, String>(NOT_DIRECT_UNDER, "设置直属及下属地区"));
	}

	private static void initConfigModeMap() {
		CONFIG_MODE_MAP = StatusKit.toMap(CONFIG_MODE_LIST);
	}

}
