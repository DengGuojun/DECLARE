package com.lpmas.declare.admin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class TrainingMaterialConfig {

	public static final String MATERIAL_GENERAL = "MATERIAL_GENERAL";
	public static final String MATERIAL_PROFESSIONAL = "MATERIAL_PROFESSIONAL";

	public static List<StatusBean<String, String>> MATERIAL_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> MATERIAL_MAP = new HashMap<String, String>();

	private static void initMaterialList() {
		MATERIAL_LIST = new ArrayList<StatusBean<String, String>>();
		MATERIAL_LIST.add(new StatusBean<String, String>(MATERIAL_GENERAL, "通用知识教材"));
		MATERIAL_LIST.add(new StatusBean<String, String>(MATERIAL_PROFESSIONAL, "专业知识教材"));
	}

	private static void initMaterialMap() {
		MATERIAL_MAP = StatusKit.toMap(MATERIAL_LIST);
	}

	static {
		initMaterialList();
		initMaterialMap();
	}
}
