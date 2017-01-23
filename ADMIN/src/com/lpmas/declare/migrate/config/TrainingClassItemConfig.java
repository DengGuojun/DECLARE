package com.lpmas.declare.migrate.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class TrainingClassItemConfig {
	public static final String COURSE_COMPREHENSIVE = "COURSE_COMPREHENSIVE";
	public static final String COURSE_SPECIAL = "COURSE_SPECIAL";
	public static final String COURSE_GRAIN = "COURSE_GRAIN";
	public static final String COURSE_CASH = "COURSE_CASH";
	public static final String COURSE_GARDENING = "COURSE_GARDENING";
	public static final String COURSE_CATTLE = "COURSE_CATTLE";
	public static final String COURSE_AQUATIC = "COURSE_AQUATIC";
	public static final String COURSE_OTHER = "COURSE_OTHER";

	public static List<StatusBean<Integer, String>> COURSE_TYPE_LIST = null;
	public static HashMap<Integer, String> COURSE_TYPE_MAP = null;

	static {
		initClassTypeList();
		initClassTypeMap();
	}

	private static void initClassTypeList() {
		COURSE_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		COURSE_TYPE_LIST.add(new StatusBean<Integer, String>(1, COURSE_COMPREHENSIVE)); // 综合性课程
		COURSE_TYPE_LIST.add(new StatusBean<Integer, String>(2, COURSE_SPECIAL));// 专题性课程
		COURSE_TYPE_LIST.add(new StatusBean<Integer, String>(3, COURSE_GRAIN));// 粮油产业课程
		COURSE_TYPE_LIST.add(new StatusBean<Integer, String>(4, COURSE_CASH));// 经作产业课程
		COURSE_TYPE_LIST.add(new StatusBean<Integer, String>(5, COURSE_GARDENING));// 园艺产业课程
		COURSE_TYPE_LIST.add(new StatusBean<Integer, String>(6, COURSE_CATTLE));// 家畜规模养殖课程
		COURSE_TYPE_LIST.add(new StatusBean<Integer, String>(7, COURSE_AQUATIC));// 水产养殖课程
		COURSE_TYPE_LIST.add(new StatusBean<Integer, String>(8, COURSE_OTHER));// 其他产业课程
	}

	private static void initClassTypeMap() {
		COURSE_TYPE_MAP = StatusKit.toMap(COURSE_TYPE_LIST);
	}
}
