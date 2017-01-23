package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassItemBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassItemBean;
import com.lpmas.declare.config.TrainingClassItemConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassItemManage.do")
public class TrainingClassItemManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassItemManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.SEARCH)) {
			return;
		}
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		if (classId <= 0) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 根据班级ID查询班级信息
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean trainingClassInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (trainingClassInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		TrainingClassItemBusiness trainingClassItemBusiness = new TrainingClassItemBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("classId", String.valueOf(classId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		List<TrainingClassItemBean> trainingClassItemList = trainingClassItemBusiness.getTrainingClassItemListByMap(condMap);
		HashMap<String, List<TrainingClassItemBean>> trainingClassItemMap = new HashMap<String, List<TrainingClassItemBean>>();
		List<TrainingClassItemBean> trainingClassItemComprehensiveList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemSpecialList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemGrainList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemCashList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemGardeningList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemCattleList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemAquaticList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemOtherList = new ArrayList<TrainingClassItemBean>();
		List<Integer> courseList = new ArrayList<Integer>();
		HashMap<Integer, TrainingClassItemBean> courseMap = new HashMap<Integer, TrainingClassItemBean>();
		Integer isHasCourse = 0;
		for (int i = 0; i < 12; ++i) {
			courseList.add(isHasCourse);
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemList) {
			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_COMPREHENSIVE)) {
				trainingClassItemComprehensiveList.add(trainingClassItemBean);
				if (trainingClassItemBean.getItemName().equals("农民素养与现代生活")) {
					courseList.set(0, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("现代农业生产经营")) {
					courseList.set(1, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
			}
			if (trainingClassItemBean.getClassType().equals(TrainingClassItemConfig.COURSE_SPECIAL)) {
				trainingClassItemSpecialList.add(trainingClassItemBean);
				if (trainingClassItemBean.getItemName().equals("现代农业创业")) {
					courseList.set(2, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("家庭农场经营管理")) {
					courseList.set(3, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("农民合作社建设管理")) {
					courseList.set(4, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("农产品电子商务")) {
					courseList.set(5, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("智能手机应用")) {
					courseList.set(6, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("休闲农业与乡村旅游")) {
					courseList.set(7, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("农业支持保护政策")) {
					courseList.set(8, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("法律基础与农村法规")) {
					courseList.set(9, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("农产品质量安全")) {
					courseList.set(10, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
				}
				if (trainingClassItemBean.getItemName().equals("美丽乡村建设")) {
					courseList.set(11, trainingClassItemBean.getItemId());
					courseMap.put(trainingClassItemBean.getItemId(), trainingClassItemBean);
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
		trainingClassItemMap.put(TrainingClassItemConfig.COURSE_COMPREHENSIVE, trainingClassItemComprehensiveList);
		trainingClassItemMap.put(TrainingClassItemConfig.COURSE_SPECIAL, trainingClassItemSpecialList);
		trainingClassItemMap.put(TrainingClassItemConfig.COURSE_GRAIN, trainingClassItemGrainList);
		trainingClassItemMap.put(TrainingClassItemConfig.COURSE_CASH, trainingClassItemCashList);
		trainingClassItemMap.put(TrainingClassItemConfig.COURSE_GARDENING, trainingClassItemGardeningList);
		trainingClassItemMap.put(TrainingClassItemConfig.COURSE_CATTLE, trainingClassItemCattleList);
		trainingClassItemMap.put(TrainingClassItemConfig.COURSE_AQUATIC, trainingClassItemAquaticList);
		trainingClassItemMap.put(TrainingClassItemConfig.COURSE_OTHER, trainingClassItemOtherList);
		request.setAttribute("courseList", courseList);
		request.setAttribute("courseMap", courseMap);
		request.setAttribute("trainingClassItemMap", trainingClassItemMap);
		request.setAttribute("trainingClassInfoBean", trainingClassInfoBean);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingClassItemManage.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.UPDATE)) {
			return;
		}
		// 处理修改部分
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		if (classId <= 0) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 根据班级ID查询班级信息
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean trainingClassInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (trainingClassInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 新建和修改只能由同一个人操作
		if (adminHelper.getAdminUserId() != trainingClassInfoBean.getCreateUser()) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		int result = 0;
		TrainingClassItemBusiness trainingClassItemBusiness = new TrainingClassItemBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("classId", String.valueOf(classId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		List<TrainingClassItemBean> trainingClassItemList = trainingClassItemBusiness.getTrainingClassItemListByMap(condMap);
		List<TrainingClassItemBean> trainingClassItemUpadteList = new ArrayList<TrainingClassItemBean>();
		List<TrainingClassItemBean> trainingClassItemAddList = new ArrayList<TrainingClassItemBean>();
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemList) {
			int isRequiredCourse = ParamKit.getIntParameter(request, "isRequiredCourse_" + trainingClassItemBean.getItemId(), 0);
			String itemName = ParamKit.getParameter(request, "itemName_" + trainingClassItemBean.getItemId(), "");
			String classHour = ParamKit.getParameter(request, "classHour_" + trainingClassItemBean.getItemId(), "");
			String trainingMaterial = ParamKit.getParameter(request, "trainingMaterial_" + trainingClassItemBean.getItemId(), "");
			String trainingTeacher = ParamKit.getParameter(request, "trainingTeacher_" + trainingClassItemBean.getItemId(), "");
			trainingClassItemBean.setIsRequiredCourse(isRequiredCourse);
			trainingClassItemBean.setItemName(itemName);
			trainingClassItemBean.setClassHour(classHour);
			trainingClassItemBean.setTrainingMaterial(trainingMaterial);
			trainingClassItemBean.setTrainingTeacher(trainingTeacher);
			trainingClassItemBean.setModifyUser(adminHelper.getAdminUserId());
			if ((isRequiredCourse != Constants.STATUS_NOT_VALID
					|| (StringKit.isValid(itemName) && !TrainingClassItemConfig.CLASS_ITEM_NAME_MAP.containsValue(itemName)))
					&& !StringKit.isValid(trainingMaterial)) {
				HttpResponseKit.alertMessage(response, "请补全必填项", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			if (StringKit.isValid(trainingMaterial) || StringKit.isValid(classHour) || StringKit.isValid(trainingTeacher)) {
				if (!StringKit.isValid(itemName)) {
					HttpResponseKit.alertMessage(response, "请填写课程名称", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			}
			trainingClassItemUpadteList.add(trainingClassItemBean);
		}
		// 处理新增部分
		int addNumber = ParamKit.getIntParameter(request, "addNumber", 0);
		if (addNumber <= 0) {
			HttpResponseKit.alertMessage(response, "参数非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		TrainingClassItemBean addTrainingClassItemBean = null;
		for (int i = 1; i <= addNumber; ++i) {
			int addIsRequiredCourse = ParamKit.getIntParameter(request, "addIsRequiredCourse_" + i, 0);
			String addItemName = ParamKit.getParameter(request, "addItemName_" + i, "");
			String addClassHour = ParamKit.getParameter(request, "addclassHour_" + i, "");
			String addTrainingMaterial = ParamKit.getParameter(request, "addTrainingMaterial_" + i, "");
			String addTrainingTeacher = ParamKit.getParameter(request, "addTrainingTeacher_" + i, "");
			String addClassType = ParamKit.getParameter(request, "addClassType_" + i, "");
			if ((addIsRequiredCourse != Constants.STATUS_NOT_VALID
					|| (StringKit.isValid(addItemName)) && !TrainingClassItemConfig.CLASS_ITEM_NAME_MAP.containsValue(addItemName))
					&& !StringKit.isValid(addTrainingMaterial)) {
				HttpResponseKit.alertMessage(response, "请补全必填项", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			if (StringKit.isValid(addTrainingMaterial) || StringKit.isValid(addClassHour) || StringKit.isValid(addTrainingTeacher)) {
				if (!StringKit.isValid(addItemName)) {
					HttpResponseKit.alertMessage(response, "请填写课程名称", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			}
			if (!StringKit.isValid(addClassHour) && !StringKit.isValid(addTrainingMaterial) && !StringKit.isValid(addTrainingTeacher)) {
				if (TrainingClassItemConfig.CLASS_ITEM_NAME_MAP.containsValue(addItemName)) {
					continue;
				} else if (!StringKit.isValid(addItemName)) {
					continue;
				}
			}
			addTrainingClassItemBean = new TrainingClassItemBean();
			addTrainingClassItemBean.setCreateUser(adminHelper.getAdminUserId());
			addTrainingClassItemBean.setClassId(classId);
			addTrainingClassItemBean.setClassType(addClassType);
			addTrainingClassItemBean.setIsRequiredCourse(addIsRequiredCourse);
			addTrainingClassItemBean.setItemName(addItemName);
			addTrainingClassItemBean.setTrainingMaterial(addTrainingMaterial);
			addTrainingClassItemBean.setTrainingTeacher(addTrainingTeacher);
			addTrainingClassItemBean.setClassHour(addClassHour);
			addTrainingClassItemBean.setStatus(Constants.STATUS_VALID);
			trainingClassItemAddList.add(addTrainingClassItemBean);
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemUpadteList) {
			result = trainingClassItemBusiness.updateTrainingClassItem(trainingClassItemBean);
		}
		for (TrainingClassItemBean trainingClassItemBean : trainingClassItemAddList) {
			result = trainingClassItemBusiness.addTrainingClassItem(trainingClassItemBean);
		}
		if (result >= 0) {
			HttpResponseKit.alertMessage(response, "处理成功",
					"/declare/admin/TrainingClassInfoList.do?trainingType=" + trainingClassInfoBean.getTrainingType());
		} else {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}

}
