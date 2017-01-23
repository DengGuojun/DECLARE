package com.lpmas.declare.console.config;

import com.lpmas.framework.util.PropertiesKit;

public class DeclareDBConfig {

	public static String DB_LINK_DECLARE_W = PropertiesKit.getBundleProperties(DeclareConsoleConfig.DECLARE_PROP_FILE_NAME,
			"DB_LINK_DECLARE_W");

	public static String DB_LINK_DECLARE_R = PropertiesKit.getBundleProperties(DeclareConsoleConfig.DECLARE_PROP_FILE_NAME,
			"DB_LINK_DECLARE_R");

}
