package com.lpmas.declare.admin.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.declare.admin.config.TrainingClassInfoExportConfig;
import com.lpmas.declare.admin.dao.TrainingClassItemDao;
import com.lpmas.declare.bean.TrainingClassItemBean;
import com.lpmas.declare.config.TrainingClassItemConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;

public class TrainingClassItemBusiness {
	public int addTrainingClassItem(TrainingClassItemBean bean) {
		TrainingClassItemDao dao = new TrainingClassItemDao();
		return dao.insertTrainingClassItem(bean);
	}

	public int addTrainingClassItemWithCreateTime(TrainingClassItemBean bean) {
		TrainingClassItemDao dao = new TrainingClassItemDao();
		return dao.insertTrainingClassItemWithCreateTime(bean);
	}

	public int updateTrainingClassItem(TrainingClassItemBean bean) {
		TrainingClassItemDao dao = new TrainingClassItemDao();
		return dao.updateTrainingClassItem(bean);
	}

	public TrainingClassItemBean getTrainingClassItemByKey(int itemId) {
		TrainingClassItemDao dao = new TrainingClassItemDao();
		return dao.getTrainingClassItemByKey(itemId);
	}

	public PageResultBean<TrainingClassItemBean> getTrainingClassItemPageListByMap(HashMap<String, String> condMap, PageBean pageBean) {
		TrainingClassItemDao dao = new TrainingClassItemDao();
		return dao.getTrainingClassItemPageListByMap(condMap, pageBean);
	}

	public List<TrainingClassItemBean> getTrainingClassItemListByMap(HashMap<String, String> condMap) {
		TrainingClassItemDao dao = new TrainingClassItemDao();
		return dao.getTrainingClassItemListByMap(condMap);
	}

	public ExcelWriteBean createTrainingClassItem(String className, List<TrainingClassItemBean> trainingClassItemList) {
		List<TrainingClassItemBean> trainingClassItemComprehensiveList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemSpecialList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemGrainList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemCashList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemGardeningList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemCattleList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemAquaticList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemOtherList = new ArrayList<TrainingClassItemBean>();

		Map<String, TrainingClassItemBean> trainingClassItemComprehensiveMap = new HashMap<String, TrainingClassItemBean>();
		Map<String, TrainingClassItemBean> trainingClassItemSpecialMap = new HashMap<String, TrainingClassItemBean>();

		List<List<Object>> contentList = new ArrayList<List<Object>>();
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemList) {

			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_COMPREHENSIVE)) {
				if (trainingClassItemBean.getItemName().equals("农民素养与现代生活")) {
					trainingClassItemComprehensiveMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("现代农业生产经营")) {
					trainingClassItemComprehensiveMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else {
					trainingClassItemComprehensiveList.add(trainingClassItemBean);
				}
			}
			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_SPECIAL)) {
				if (trainingClassItemBean.getItemName().equals("现代农业创业")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("家庭农场经营管理")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("农民合作社建设管理")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("农产品电子商务")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("智能手机应用")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("休闲农业与乡村旅游")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("农业支持保护政策")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("法律基础与农村法规")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("农产品质量安全")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else if (trainingClassItemBean.getItemName().equals("美丽乡村建设")) {
					trainingClassItemSpecialMap.put(trainingClassItemBean.getItemName(), trainingClassItemBean);
				} else {
					trainingClassItemSpecialList.add(trainingClassItemBean);
				}
			}
			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_GRAIN)) {
				trainingClassItemGrainList.add(trainingClassItemBean);
			}
			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_CASH)) {
				trainingClassItemCashList.add(trainingClassItemBean);
			}
			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_GARDENING)) {
				trainingClassItemGardeningList.add(trainingClassItemBean);
			}
			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_CATTLE)) {
				trainingClassItemCattleList.add(trainingClassItemBean);
			}
			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_AQUATIC)) {
				trainingClassItemAquaticList.add(trainingClassItemBean);
			}
			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_OTHER)) {
				trainingClassItemOtherList.add(trainingClassItemBean);
			}
		}
		if (trainingClassItemComprehensiveMap.containsKey("农民素养与现代生活")) {
			contentList.add(processContent(trainingClassItemComprehensiveMap.get("农民素养与现代生活")));
		}
		if (trainingClassItemComprehensiveMap.containsKey("现代农业生产经营")) {
			contentList.add(processContent(trainingClassItemComprehensiveMap.get("现代农业生产经营")));
		}

		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemComprehensiveList) {
			contentList.add(processContent(trainingClassItemBean));
		}
		if (trainingClassItemSpecialMap.containsKey("现代农业创业")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("现代农业创业")));
		}
		if (trainingClassItemSpecialMap.containsKey("家庭农场经营管理")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("家庭农场经营管理")));
		}
		if (trainingClassItemSpecialMap.containsKey("农民合作社建设管理")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("农民合作社建设管理")));
		}
		if (trainingClassItemSpecialMap.containsKey("农产品电子商务")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("农产品电子商务")));
		}
		if (trainingClassItemSpecialMap.containsKey("智能手机应用")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("智能手机应用")));
		}
		if (trainingClassItemSpecialMap.containsKey("休闲农业与乡村旅游")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("休闲农业与乡村旅游")));
		}
		if (trainingClassItemSpecialMap.containsKey("农业支持保护政策")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("农业支持保护政策")));
		}
		if (trainingClassItemSpecialMap.containsKey("法律基础与农村法规")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("法律基础与农村法规")));
		}
		if (trainingClassItemSpecialMap.containsKey("农产品质量安全")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("农产品质量安全")));
		}
		if (trainingClassItemSpecialMap.containsKey("美丽乡村建设")) {
			contentList.add(processContent(trainingClassItemSpecialMap.get("美丽乡村建设")));
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemSpecialList) {
			contentList.add(processContent(trainingClassItemBean));
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemGrainList) {
			contentList.add(processContent(trainingClassItemBean));
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemCashList) {
			contentList.add(processContent(trainingClassItemBean));
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemGardeningList) {
			contentList.add(processContent(trainingClassItemBean));
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemCattleList) {
			contentList.add(processContent(trainingClassItemBean));
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemAquaticList) {
			contentList.add(processContent(trainingClassItemBean));
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemOtherList) {
			contentList.add(processContent(trainingClassItemBean));
		}
		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
		excelWriteBean.setFileName(className + "课程安排");
		excelWriteBean.setFileType("xlsx");
		excelWriteBean.setHeaderList(TrainingClassInfoExportConfig.CLASS_ITEM_HEADER_LIST);
		excelWriteBean.setContentList(contentList);
		return excelWriteBean;
	}

	public List<Object> processContent(TrainingClassItemBean trainingClassItemBean) {
		List<Object> tempList = new ArrayList<Object>();
		tempList.add(MapKit.getValueFromMap(trainingClassItemBean.getClassType(), TrainingClassItemConfig.COURSE_TYPE_MAP));
		tempList.add(Constants.STATUS_VALID == trainingClassItemBean.getIsRequiredCourse() ? "是" : "否");
		tempList.add(trainingClassItemBean.getItemName());
		tempList.add(trainingClassItemBean.getClassHour());
		tempList.add(trainingClassItemBean.getTrainingMaterial());
		tempList.add(trainingClassItemBean.getTrainingTeacher());
		return tempList;
	}

}
