package com.lpmas.declare.survey.config;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.PropertiesKit;

public class DeclareSurveyConfig {

	// appId
	public static final String APP_ID = "Declare";

	public static final String DECLARE_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/declare_config";
	public static final String DECLARE_MONGO_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/declare_mongo_config";

	// 默认域
	public final static String ADMIN_DOMAIN = PropertiesKit.getBundleProperties(DECLARE_PROP_FILE_NAME,
			"DECLARE_ADMIN_DOMAIN");

	public final static String ADMIN_LOGIN_KEY = "AdminSurveyLoginKey";
	public final static String DECALRE_ID = "declareId";
	public static final String ADMIN_LOGIN_ID = PropertiesKit.getBundleProperties(DECLARE_PROP_FILE_NAME,
			"ADMIN_LOGIN_ID");
	public static final String ADMIN_LOGIN_PASSWORD = PropertiesKit.getBundleProperties(DECLARE_PROP_FILE_NAME,
			"ADMIN_LOGIN_PASSWORD");

	public static final Integer DEFAULT_PAGE_NUM = 1;
	public static final Integer DEFAULT_PAGE_SIZE = 20;

	public static final String ERROR_PAGE = Constants.PAGE_PATH + "common/error_page.jsp";
	public static final String PAGE_PATH = Constants.PAGE_PATH + "declare/{0}/";

}
