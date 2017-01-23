package com.lpmas.declare.migrate.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

/**
 * 配置信息
 * 
 * @author 潘宜杰
 *
 */
public class TrainingClassInfoConfig {
	public static final int TRAIN_CLASS_TYPE_NCZ = 1;
	public static final int TRAIN_CLASS_TYPE_JYX = 2;
	public static final int TRAIN_CLASS_TYPE_JNX = 3;
	public static final int TRAIN_CLASS_TYPE_FWX = 4;
	public static final int TRAIN_CLASS_TYPE_DTR = 5;

	public static final String ClASS_STATUS_EDIT = "EDIT";
	public static final String CLASS_STATUS_APPROVED = "APPROVED";
	public static final String CLASS_STATUS_SUBMITTED = "SUBMITTED";
	public static final String ClASS_STATUS_REJECTED = "REJECTED";

	public static List<StatusBean<Integer, Integer>> ClASS_TYPE_LIST = null;
	public static HashMap<Integer, Integer> ClASS_TYPE_MAP = null;

	public static List<StatusBean<Integer, String>> ClASS_STATUS_LIST = null;
	public static HashMap<Integer, String> ClASS_STATUS_MAP = null;

	static {
		initClassTypeList();
		initClassTypeMap();

		initClassStatusList();
		initClassStatusMap();
	}

	private static void initClassTypeList() {
		ClASS_TYPE_LIST = new ArrayList<StatusBean<Integer, Integer>>();
		ClASS_TYPE_LIST.add(new StatusBean<Integer, Integer>(1, TRAIN_CLASS_TYPE_NCZ)); // 现代青年农场主
		ClASS_TYPE_LIST.add(new StatusBean<Integer, Integer>(2, TRAIN_CLASS_TYPE_DTR));// 新型农业经营主体带头人
		ClASS_TYPE_LIST.add(new StatusBean<Integer, Integer>(3, TRAIN_CLASS_TYPE_JYX));// 生产经营型职业农业
		ClASS_TYPE_LIST.add(new StatusBean<Integer, Integer>(4, TRAIN_CLASS_TYPE_JNX));// 专业技能型职业农民
		ClASS_TYPE_LIST.add(new StatusBean<Integer, Integer>(5, TRAIN_CLASS_TYPE_FWX));// 专业服务型职业农民
	}

	private static void initClassTypeMap() {
		ClASS_TYPE_MAP = StatusKit.toMap(ClASS_TYPE_LIST);
	}

	private static void initClassStatusList() {
		ClASS_STATUS_LIST = new ArrayList<StatusBean<Integer, String>>();
		ClASS_STATUS_LIST.add(new StatusBean<Integer, String>(0, CLASS_STATUS_SUBMITTED)); // 0->"在县"
		ClASS_STATUS_LIST.add(new StatusBean<Integer, String>(1, CLASS_STATUS_SUBMITTED)); // 1->"在市"
		ClASS_STATUS_LIST.add(new StatusBean<Integer, String>(2, CLASS_STATUS_APPROVED));// 2->"在省"
		ClASS_STATUS_LIST.add(new StatusBean<Integer, String>(3, ClASS_STATUS_EDIT)); // 3->"已填写"
		ClASS_STATUS_LIST.add(new StatusBean<Integer, String>(4, ClASS_STATUS_REJECTED)); // 4->"需重报"
	}

	private static void initClassStatusMap() {
		ClASS_STATUS_MAP = StatusKit.toMap(ClASS_STATUS_LIST);
	}
}
