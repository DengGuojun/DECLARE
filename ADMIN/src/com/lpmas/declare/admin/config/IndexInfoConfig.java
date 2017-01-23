package com.lpmas.declare.admin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class IndexInfoConfig {

	public static final String GET_TEACHER_COUNT = "GET_TEACHER_COUNT";
	public static final String GET_TEACHER_COUNT_INCRESAE_THIS_YEAR = "GET_TEACHER_COUNT_INCRESAE_THIS_YEAR";
	public static final String GET_TRAINING_MATERIAL_COUNT = "GET_TRAINING_MATERIAL_COUNT";
	public static final String GET_TRAINING_MATERIAL_COUNT_INCRESAE_THIS_YEAR = "GET_TRAINING_MATERIAL_COUNT_INCRESAE_THIS_YEAR";
	public static final String GET_BASE_TRAINING_COUNT = "GET_BASE_TRAINING_COUNT";
	public static final String GET_BASE_TRAINING_COUNT_INCRESAE_THIS_YEAR = "GET_BASE_TRAINING_COUNT_INCRESAE_THIS_YEAR";
	public static final String GET_YOUNG_FARMER_DECLARE_COUNT = "GET_YOUNG_FARMER_DECLARE_COUNT";
	public static final String GET_PRODUCT_FARMER_DECLARE_COUNT = "GET_PRODUCT_FARMER_DECLARE_COUNT";
	public static final String GET_TECHNICAL_FARMER_DECLARE_COUNT = "GET_TECHNICAL_FARMER_DECLARE_COUNT";
	public static final String GET_SERVICE_FARMER_DECLARE_COUNT = "GET_SERVICE_FARMER_DECLARE_COUNT";
	public static final String GET_LEADER_FARMER_DECLARE_COUNT = "GET_LEADER_FARMER_DECLARE_COUNT";
	public static final String GET_AUTH_PRODUCT_FARMER_DECLARE_COUNT = "GET_AUTH_PRODUCT_FARMER_DECLARE_COUNT";
	public static final String GET_AUTH_TECHNICAL_AND_SERVICE_FARMER_DECLARE_COUNT = "GET_AUTH_TECHNICAL_AND_SERVICE_FARMER_DECLARE_COUNT";

	public static List<StatusBean<String, String>> INDEX_QUERY_METHOD_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> INDEX_QUERY_METHOD_MAP = new HashMap<String, String>();

	static {
		initIndexQueryMethodList();
		initIndexQueryMethodMap();
	}

	public static void initIndexQueryMethodList() {
		INDEX_QUERY_METHOD_LIST = new ArrayList<StatusBean<String, String>>();
		INDEX_QUERY_METHOD_LIST.add(new StatusBean<String, String>(GET_TEACHER_COUNT, "培育师资总人数"));
		INDEX_QUERY_METHOD_LIST.add(new StatusBean<String, String>(GET_TEACHER_COUNT_INCRESAE_THIS_YEAR, "当年新增师资总人数"));
		INDEX_QUERY_METHOD_LIST.add(new StatusBean<String, String>(GET_TRAINING_MATERIAL_COUNT, "培育教材总数"));
		INDEX_QUERY_METHOD_LIST
				.add(new StatusBean<String, String>(GET_TRAINING_MATERIAL_COUNT_INCRESAE_THIS_YEAR, "当年新增培育教材数"));
		INDEX_QUERY_METHOD_LIST.add(new StatusBean<String, String>(GET_BASE_TRAINING_COUNT, "实训基地总数"));
		INDEX_QUERY_METHOD_LIST
				.add(new StatusBean<String, String>(GET_BASE_TRAINING_COUNT_INCRESAE_THIS_YEAR, "当年新增实训基地数"));

		INDEX_QUERY_METHOD_LIST.add(new StatusBean<String, String>(GET_YOUNG_FARMER_DECLARE_COUNT, "当年入库青年农场主数"));
		INDEX_QUERY_METHOD_LIST.add(new StatusBean<String, String>(GET_PRODUCT_FARMER_DECLARE_COUNT, "当年入库生产经营农民数"));
		INDEX_QUERY_METHOD_LIST.add(new StatusBean<String, String>(GET_TECHNICAL_FARMER_DECLARE_COUNT, "当年入库技术型农民数"));
		INDEX_QUERY_METHOD_LIST.add(new StatusBean<String, String>(GET_SERVICE_FARMER_DECLARE_COUNT, "当年入库服务型农民数"));
		INDEX_QUERY_METHOD_LIST.add(new StatusBean<String, String>(GET_LEADER_FARMER_DECLARE_COUNT, "当年入库带头人数"));

		INDEX_QUERY_METHOD_LIST
				.add(new StatusBean<String, String>(GET_AUTH_PRODUCT_FARMER_DECLARE_COUNT, "当年认定生产经营农民数"));
		INDEX_QUERY_METHOD_LIST.add(
				new StatusBean<String, String>(GET_AUTH_TECHNICAL_AND_SERVICE_FARMER_DECLARE_COUNT, "当年认定技术型与服务型农民数"));
	}

	public static void initIndexQueryMethodMap() {
		INDEX_QUERY_METHOD_MAP = StatusKit.toMap(INDEX_QUERY_METHOD_LIST);
	}
}
