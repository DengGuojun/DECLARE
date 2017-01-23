package com.lpmas.declare.console.config;

import com.lpmas.framework.config.Constants;

public class DeclareConsoleConfig {
	
	public static final String APP_ID = "Declare";

	public static final String DECLARE_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/declare_config";
	public static final String DECLARE_MONGO_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/declare_mongo_config";

	public static final Integer DEFAULT_PAGE_NUM = 1;
	public static final Integer DEFAULT_PAGE_SIZE = 20;

	public static final String ERROR_PAGE = Constants.PAGE_PATH + "common/error_page.jsp";
	public static final String PAGE_PATH = Constants.PAGE_PATH + "declare/";

}
