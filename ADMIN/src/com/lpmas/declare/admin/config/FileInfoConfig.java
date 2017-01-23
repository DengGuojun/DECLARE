package com.lpmas.declare.admin.config;

import com.lpmas.framework.util.PropertiesKit;

public class FileInfoConfig {

	public static final String ALLOWED_FILE_TYPE = "jpg,jpeg,gif,png,doc,xls,docs,xlsx,zip,txt";// 设置允许上传的文件类型
	public static final int MAX_SIZE = 500 * 1024 * 1024; // 设置上传文件最大为500M
	
	public static final int INFO_TYPE_COMMON = 1;//普通资料
	public static final int INFO_TYPE_ANNOUNCEMENT_ATTACH = 2;//公告附件
	public static final int INFO_TYPE_MESSAGE_ATTACH = 3;//邮件附件
	public static final int INFO_TYPE_TEACHER_ATTACH = 4;//师资附件
	public static final int INFO_TYPE_TEACHER_DECLARE_ATTACH = 5;//师资申报附件

	// 设置文件保存路径
	public static final String ADMIN_FILE_PATH = PropertiesKit.getBundleProperties(DeclareAdminConfig.DECLARE_PROP_FILE_NAME,
			"ADMIN_FILE_PATH");
	// 设置文件备份路径
	public static final String ADMIN_BACKUP_FILE_PATH = PropertiesKit
			.getBundleProperties(DeclareAdminConfig.DECLARE_PROP_FILE_NAME, "ADMIN_BACKUP_FILE_PATH");

}
