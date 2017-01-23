package com.lpmas.declare.admin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class RecommendInfoConfig {

	public static final String RECOMMEND_TYPE_TEACHER = "TEACHER";
	public static final String RECOMMEND_TYPE_MATERIAL = "MATERIAL";
	public static final String RECOMMEND_TYPE_BASE = "BASE";
	
	public static List<StatusBean<String, String>> RECOMMEND_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> RECOMMEND_TYPE_MAP = new HashMap<String, String>();

	static {
		initRecommendInfoList();
		initRecommendInfoMap();

	}

	private static void initRecommendInfoList() {
		RECOMMEND_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		RECOMMEND_TYPE_LIST.add(new StatusBean<String, String>(RECOMMEND_TYPE_TEACHER, "教师"));
		RECOMMEND_TYPE_LIST.add(new StatusBean<String, String>(RECOMMEND_TYPE_MATERIAL, "教材"));
		RECOMMEND_TYPE_LIST.add(new StatusBean<String, String>(RECOMMEND_TYPE_BASE, "实训基地"));
	}

	private static void initRecommendInfoMap() {
		RECOMMEND_TYPE_MAP = StatusKit.toMap(RECOMMEND_TYPE_LIST);
	}
}
