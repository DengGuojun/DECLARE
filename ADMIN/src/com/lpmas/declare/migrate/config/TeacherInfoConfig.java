package com.lpmas.declare.migrate.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class TeacherInfoConfig {
	// 教师等级
	public static final String TECHNICAL_GRADE_PRIMARY = "TECHNICAL_GRADE_PRIMARY";
	public static final String TECHNICAL_GRADE_MIDDLE = "TECHNICAL_GRADE_MIDDLE";
	public static final String TECHNICAL_GRADE_SUB_SENIOR = "TECHNICAL_GRADE_SUB_SENIOR";
	public static final String TECHNICAL_GRADE_SENIOR = "TECHNICAL_GRADE_SENIOR";

	public static final int TEACHER_TYPE_PLANT = 1; // 种植业
	public static final int TEACHER_TYPE_BREED = 2; // 养殖业
	public static final int TEACHER_TYPE_MODERN_AGRICULTURE = 3; // 现代农业
	public static final int TEACHER_TYPE_PUBLIC_FOUNDATION = 4; // 公共基础
	public static final int TEACHER_TYPE_ENGINEERING_AND_SERVER = 5; // 农业工程与服务
	public static final int TEACHER_TYPE_PUBLIC_MANAGE = 6; // 农村经营与农村管理
	public static final int TEACHER_TYPE_OTHER = 7; // 其他
	// 教师类型
	public static List<StatusBean<String, Integer>> TEACHER_TYPE_LIST = new ArrayList<StatusBean<String, Integer>>();
	public static HashMap<String, Integer> TEACHER_TYPE_MAP = new HashMap<String, Integer>();

	public static List<StatusBean<String, String>> TEACHER_GRADE_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> TEACHER_GRADE_MAP = new HashMap<String, String>();

	static {
		initTeacherGradeList();
		initTeacherGradeMap();

		initTeachnicalList();
		initTeachnicalMap();
	}

	private static void initTeacherGradeList() {
		TEACHER_GRADE_LIST = new ArrayList<StatusBean<String, String>>();
		TEACHER_GRADE_LIST.add(new StatusBean<String, String>("1", TECHNICAL_GRADE_PRIMARY));
		TEACHER_GRADE_LIST.add(new StatusBean<String, String>("2", TECHNICAL_GRADE_MIDDLE));
		TEACHER_GRADE_LIST.add(new StatusBean<String, String>("3", TECHNICAL_GRADE_SUB_SENIOR));
		TEACHER_GRADE_LIST.add(new StatusBean<String, String>("4", TECHNICAL_GRADE_SENIOR));

	}

	private static void initTeacherGradeMap() {
		TEACHER_GRADE_MAP = StatusKit.toMap(TEACHER_GRADE_LIST);

	}

	private static void initTeachnicalList() {
		TEACHER_TYPE_LIST = new ArrayList<StatusBean<String, Integer>>();
		TEACHER_TYPE_LIST.add(new StatusBean<String, Integer>("种植业", TEACHER_TYPE_PLANT));
		TEACHER_TYPE_LIST.add(new StatusBean<String, Integer>("养殖业", TEACHER_TYPE_BREED));
		TEACHER_TYPE_LIST.add(new StatusBean<String, Integer>("现代农业", TEACHER_TYPE_MODERN_AGRICULTURE));
		TEACHER_TYPE_LIST.add(new StatusBean<String, Integer>("公共基础", TEACHER_TYPE_PUBLIC_FOUNDATION));
		TEACHER_TYPE_LIST.add(new StatusBean<String, Integer>("农村工程与服务", TEACHER_TYPE_ENGINEERING_AND_SERVER));
		TEACHER_TYPE_LIST.add(new StatusBean<String, Integer>("农村经营与农村管理", TEACHER_TYPE_PUBLIC_MANAGE));
		TEACHER_TYPE_LIST.add(new StatusBean<String, Integer>("其他", TEACHER_TYPE_OTHER));
	}

	private static void initTeachnicalMap() {
		TEACHER_TYPE_MAP = StatusKit.toMap(TEACHER_TYPE_LIST);
	}

}
