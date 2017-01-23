package com.lpmas.declare.admin.config;

import java.util.ArrayList;
import java.util.List;

public class TrainingClassInfoExportConfig {
	// 导出表头
	public static List<String> CLASS_INFO_HEADER_LIST = new ArrayList<String>();
	public static List<String> CLASS_USER_HEADER_LIST = new ArrayList<String>();
	public static List<String> CLASS_USER_SECOND_HEADER_LIST = new ArrayList<String>();
	public static List<String> CLASS_ITEM_HEADER_LIST = new ArrayList<String>();
	static {
		initTrainingClassInfoHeaderList();
		initTrainingClassUserHeaderList();
		initTrainingClassUserSecondHeaderList();
		initTrainingClassItemHeaderList();
	}

	public static void initTrainingClassInfoHeaderList() {
		CLASS_INFO_HEADER_LIST = new ArrayList<String>();
		CLASS_INFO_HEADER_LIST.add("培训机构");
		CLASS_INFO_HEADER_LIST.add("培训班");
		CLASS_INFO_HEADER_LIST.add("培训对象");
		CLASS_INFO_HEADER_LIST.add("培训产业");
		CLASS_INFO_HEADER_LIST.add("数据状态");
		CLASS_INFO_HEADER_LIST.add("培训人数");
	}

	public static void initTrainingClassUserHeaderList() {
		CLASS_USER_HEADER_LIST = new ArrayList<String>();
		CLASS_USER_HEADER_LIST.add("地区");
		CLASS_USER_HEADER_LIST.add("姓名");
		CLASS_USER_HEADER_LIST.add("性别");
		CLASS_USER_HEADER_LIST.add("文化程度");
		CLASS_USER_HEADER_LIST.add("身份证号");
		CLASS_USER_HEADER_LIST.add("人员类别");
		CLASS_USER_HEADER_LIST.add("手机号码");
		CLASS_USER_HEADER_LIST.add("主体产业");
		CLASS_USER_HEADER_LIST.add("产业规模");
		CLASS_USER_HEADER_LIST.add("状态");
	}

	public static void initTrainingClassUserSecondHeaderList() {
		CLASS_USER_SECOND_HEADER_LIST = new ArrayList<String>();
		CLASS_USER_SECOND_HEADER_LIST.add("地区");
		CLASS_USER_SECOND_HEADER_LIST.add("姓名");
		CLASS_USER_SECOND_HEADER_LIST.add("性别");
		CLASS_USER_SECOND_HEADER_LIST.add("文化程度");
		CLASS_USER_SECOND_HEADER_LIST.add("身份证号");
		CLASS_USER_SECOND_HEADER_LIST.add("手机号码");
		CLASS_USER_SECOND_HEADER_LIST.add("状态");
	}

	public static void initTrainingClassItemHeaderList() {
		CLASS_ITEM_HEADER_LIST = new ArrayList<String>();
		CLASS_ITEM_HEADER_LIST.add("课程类型");
		CLASS_ITEM_HEADER_LIST.add("是否必选");
		CLASS_ITEM_HEADER_LIST.add("课程名称");
		CLASS_ITEM_HEADER_LIST.add("学时数");
		CLASS_ITEM_HEADER_LIST.add("培训教材");
		CLASS_ITEM_HEADER_LIST.add("培训师资");
	}
}
