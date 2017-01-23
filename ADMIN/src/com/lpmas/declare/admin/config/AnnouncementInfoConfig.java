package com.lpmas.declare.admin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class AnnouncementInfoConfig {

	public static final String ANNOUNCEMENT_STATUS_EDIT = "EDIT";
	public static final String ANNOUNCEMENT_STATUS_PUBLISHED = "PUBLISHED";
	
	public static List<StatusBean<String, String>> ANNOUNCEMENT_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> ANNOUNCEMENT_STATUS_MAP = new HashMap<String, String>();

	static {
		initAnnouncementStatusList();
		initAnnouncementStatusMap();

	}

	private static void initAnnouncementStatusList() {
		ANNOUNCEMENT_STATUS_LIST = new ArrayList<StatusBean<String, String>>();
		ANNOUNCEMENT_STATUS_LIST.add(new StatusBean<String, String>(ANNOUNCEMENT_STATUS_EDIT, "编辑中"));
		ANNOUNCEMENT_STATUS_LIST.add(new StatusBean<String, String>(ANNOUNCEMENT_STATUS_PUBLISHED, "已发布"));
	}

	private static void initAnnouncementStatusMap() {
		ANNOUNCEMENT_STATUS_MAP = StatusKit.toMap(ANNOUNCEMENT_STATUS_LIST);
	}
}
