package com.lpmas.declare.invoker.config;

import java.util.HashSet;
import java.util.Set;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.PropertiesKit;
import com.lpmas.framework.util.StringKit;

public class YunClassInvokeConfig {

	public static final String YUN_CLASS_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/yun_class_invoke_config";

	// 权鉴信息
	public static String YUN_FLAG = "AMSTV";
	public static String YUN_VERSION = PropertiesKit
			.getBundleProperties(YUN_CLASS_PROP_FILE_NAME, "YUN_SERVER_VERSION")
			+ "."
			+ PropertiesKit.getBundleProperties(YUN_CLASS_PROP_FILE_NAME, "YUN_APPID_VERSION");
	public static String YUN_APPID = PropertiesKit.getBundleProperties(YUN_CLASS_PROP_FILE_NAME, "YUN_APPID");
	public static String YUN_HOST = PropertiesKit.getBundleProperties(YUN_CLASS_PROP_FILE_NAME, "YUN_HOST");
	public static String YUN_SECRETKEY = PropertiesKit.getBundleProperties(YUN_CLASS_PROP_FILE_NAME, "YUN_SECRETKEY");
	public static String DEBUG_YUN_APPID = PropertiesKit.getBundleProperties(YUN_CLASS_PROP_FILE_NAME,
			"DEBUG_YUN_APPID");
	public static String DEBUG_YUN_HOST = PropertiesKit.getBundleProperties(YUN_CLASS_PROP_FILE_NAME, "DEBUG_YUN_HOST");
	public static String DEBUG_YUN_SECRETKEY = PropertiesKit.getBundleProperties(YUN_CLASS_PROP_FILE_NAME,
			"DEBUG_YUN_SECRETKEY");

	// 要调用的应用服务名
	public static final String YUN_SERVICE_ADD_CLASS = "course.classroom.add";
	public static final String YUN_SERVICE_ADD_USER_TO_CLASS = "course.classroom.member.add";
	public static final String YUN_SERVICE_DELETE_USER_TO_CLASS = "course.classroom.member.delete";
	public static Set<String> YUN_SERVICE_SET = new HashSet<String>();

	// 调用时的配置信息
	public static int MAX_ATTEMPT_COUNT = 1;

	// 是否DEBUG模式
	public static boolean IS_DEBUG_MODE = true;

	// 默认是
	static {
		initIsDebugMode();
		initMaxAttemptCount();
		initYunServiceSet();
	}

	private static void initMaxAttemptCount() {
		try {
			MAX_ATTEMPT_COUNT = Integer.valueOf(PropertiesKit.getBundleProperties(
					YUN_CLASS_PROP_FILE_NAME, "MAX_ATTEMPT_COUNT"));
		} catch (Exception e) {
			MAX_ATTEMPT_COUNT = 1;
		}
	}

	private static void initIsDebugMode() {
		String idDebugModeStr = PropertiesKit.getBundleProperties(YUN_CLASS_PROP_FILE_NAME,
				"IS_DEBUG_MODE");
		if (StringKit.isValid(idDebugModeStr)) {
			idDebugModeStr.trim().toLowerCase();
			if (idDebugModeStr.equals("true")) {
				IS_DEBUG_MODE = true;
			} else if (idDebugModeStr.equals("false")) {
				IS_DEBUG_MODE = false;
			}
		}
	}

	private static void initYunServiceSet() {
		YUN_SERVICE_SET = new HashSet<String>();
		YUN_SERVICE_SET.add(YUN_SERVICE_ADD_CLASS);
		YUN_SERVICE_SET.add(YUN_SERVICE_ADD_USER_TO_CLASS);
	}

}
