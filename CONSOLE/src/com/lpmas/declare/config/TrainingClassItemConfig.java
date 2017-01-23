package com.lpmas.declare.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class TrainingClassItemConfig {
	// 课程类型
	public static final String COURSE_COMPREHENSIVE = "COURSE_COMPREHENSIVE";// 综合性课程
	public static final String COURSE_SPECIAL = "COURSE_SPECIAL";// 专题性课程
	public static final String COURSE_GRAIN = "COURSE_GRAIN";// 粮油产业课程
	public static final String COURSE_CASH = "COURSE_CASH";// 经作产业课程
	public static final String COURSE_GARDENING = "COURSE_GARDENING";// 园艺产业课程
	public static final String COURSE_CATTLE = "COURSE_CATTLE";// 家畜规模养殖课程
	public static final String COURSE_AQUATIC = "COURSE_AQUATIC";// 水产养殖课程
	public static final String COURSE_OTHER = "COURSE_OTHER";// 其他产业课程
	public static List<StatusBean<String, String>> COURSE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> COURSE_TYPE_MAP = new HashMap<String, String>();

	// 课程名称
	public static final String XDNYSCJY = "XDNYSCJY";
	public static final String NMSYYXDSH = "NMSYYXDSH";
	public static final String MLXCJS = "MLXCJS";
	public static final String NCPZLAQ = "NCPZLAQ";
	public static final String FLJCYNCFG = "FLJCYNCFG";
	public static final String NYZCBHZC = "NYZCBHZC";
	public static final String XXNYYXCLY = "XXNYYXCLY";
	public static final String ZNSJYY = "ZNSJYY";
	public static final String NCPDZSW = "NCPDZSW";
	public static final String NMHZSJSGL = "NMHZSJSGL";
	public static final String JTNCJYGL = "JTNCJYGL";
	public static final String XDNYCY = "XDNYCY";
	public static List<StatusBean<String, String>> CLASS_ITEM_NAME_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> CLASS_ITEM_NAME_MAP = new HashMap<String, String>();

	static {
		initClassItemNameList();
		initClassItemNameMap();

		initCourseTypeList();
		initCourseTypeMap();
	}

	private static void initClassItemNameList() {
		CLASS_ITEM_NAME_LIST = new ArrayList<StatusBean<String, String>>();
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(XDNYSCJY, "现代农业生产经营"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(NMSYYXDSH, "农民素养与现代生活"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(MLXCJS, "美丽乡村建设"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(NCPZLAQ, "农产品质量安全"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(FLJCYNCFG, "法律基础与农村法规"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(NYZCBHZC, "农业支持保护政策"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(XXNYYXCLY, "休闲农业与乡村旅游"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(ZNSJYY, "智能手机应用"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(NCPDZSW, "农产品电子商务"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(NMHZSJSGL, "农民合作社建设管理"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(JTNCJYGL, "家庭农场经营管理"));
		CLASS_ITEM_NAME_LIST.add(new StatusBean<String, String>(XDNYCY, "现代农业创业"));
	}

	private static void initClassItemNameMap() {
		CLASS_ITEM_NAME_MAP = StatusKit.toMap(CLASS_ITEM_NAME_LIST);

	}

	private static void initCourseTypeList() {
		COURSE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		COURSE_TYPE_LIST.add(new StatusBean<String, String>(COURSE_COMPREHENSIVE, "综合性课程"));
		COURSE_TYPE_LIST.add(new StatusBean<String, String>(COURSE_SPECIAL, "专题性课程"));
		COURSE_TYPE_LIST.add(new StatusBean<String, String>(COURSE_GRAIN, "粮油产业课程"));
		COURSE_TYPE_LIST.add(new StatusBean<String, String>(COURSE_CASH, "经作产业课程"));
		COURSE_TYPE_LIST.add(new StatusBean<String, String>(COURSE_GARDENING, "园艺产业课程"));
		COURSE_TYPE_LIST.add(new StatusBean<String, String>(COURSE_CATTLE, "家畜规模养殖课程"));
		COURSE_TYPE_LIST.add(new StatusBean<String, String>(COURSE_AQUATIC, "水产养殖课程"));
		COURSE_TYPE_LIST.add(new StatusBean<String, String>(COURSE_OTHER, "其他产业课程"));
	}

	private static void initCourseTypeMap() {
		COURSE_TYPE_MAP = StatusKit.toMap(COURSE_TYPE_LIST);

	}

}
