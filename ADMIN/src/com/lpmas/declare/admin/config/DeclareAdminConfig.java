package com.lpmas.declare.admin.config;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.PropertiesKit;

public class DeclareAdminConfig {

	public static final String APP_ID = "Declare";

	public static final String DECLARE_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/declare_config";
	public static final String DECLARE_MONGO_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/declare_mongo_config";

	// 默认域
	public final static String ADMIN_DOMAIN = PropertiesKit.getBundleProperties(DECLARE_PROP_FILE_NAME,
			"DECLARE_ADMIN_DOMAIN");
	
	// 默认域
		public final static String SMS_CODE = PropertiesKit.getBundleProperties(DECLARE_PROP_FILE_NAME,
				"SMS_CODE");

	public final static String ADMIN_USER_KEY = "DeclareAdminUserKey";
	public final static int ADMIN_TYPE_COMMON = 1;// 普通用户
	public final static int ADMIN_TYPE_ADMIN = 2;// 管理员用户

	public static final String COUNTRY_STR = "国家";
	public static final String ADMIN_LEVEL_COUNTRY = "COUNTRY";
	public static final String ADMIN_LEVEL_PROVINCE = "PROVINCE";
	public static final String ADMIN_LEVEL_CITY = "CITY";
	public static final String ADMIN_LEVEL_REGION = "REGION";

	public static final String ADMIN_LOGIN_SEPARATOR = "-";

	public static final Integer DEFAULT_PAGE_NUM = 1;
	public static final Integer DEFAULT_PAGE_SIZE = 20;

	public static final String PAGE_PATH = Constants.PAGE_PATH + "declare/";

}
