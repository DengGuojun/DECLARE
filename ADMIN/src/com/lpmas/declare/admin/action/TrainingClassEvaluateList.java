package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingClassEvaluateBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassItemBusiness;
import com.lpmas.declare.admin.business.TrainingClassItemEvaluateBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.TrainingClassEvaluateBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassItemBean;
import com.lpmas.declare.bean.TrainingClassItemEvaluateBean;
import com.lpmas.declare.config.TrainingClassInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.NumeralOperationKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassEvaluateList.do")
public class TrainingClassEvaluateList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassEvaluateList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_EVALUATE, OperationConfig.SEARCH)) {
			return;
		}
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean classInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (classInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		TrainingClassEvaluateBusiness business = new TrainingClassEvaluateBusiness();
		// 计算培训效果评分
		double effectTotalScore = 0;
		double effectScore = 0;
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("classId", String.valueOf(classId));
		condMap.put("evaluateType", TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_EFFECT);
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		List<TrainingClassEvaluateBean> list = business.getTrainingClassEvaluateListByMap(condMap);
		for (TrainingClassEvaluateBean bean : list) {
			effectTotalScore = NumeralOperationKit.add(effectTotalScore, bean.getEvaluateScore());
		}
		if (!list.isEmpty()) {
			effectScore = NumeralOperationKit.divide(effectTotalScore, list.size(), 2);
		}

		int effectScoreCount = list.size();

		// 计算组织管理评分
		double organizeTotalScore = 0;
		double organizeScore = 0;
		condMap.put("evaluateType", TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_ORGANIZE);
		list = business.getTrainingClassEvaluateListByMap(condMap);
		for (TrainingClassEvaluateBean bean : list) {
			organizeTotalScore = NumeralOperationKit.add(organizeTotalScore, bean.getEvaluateScore());
		}
		if (!list.isEmpty()) {
			organizeScore = NumeralOperationKit.divide(organizeTotalScore, list.size(), 2);
		}
		int organizeScoreCount = list.size();
		
		//查询所有课程
		TrainingClassItemBusiness trainingClassItemBusiness = new TrainingClassItemBusiness();
		condMap = new HashMap<String, String>();
		condMap.put("classId", String.valueOf(classId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		List<TrainingClassItemBean> classItemList = trainingClassItemBusiness.getTrainingClassItemListByMap(condMap);
		Map<Object,Object> itemNameMap =ListKit.list2Map(classItemList, "itemId","itemName");

		// 计算课程评分
		HashMap<Integer, Double> itemTotalScoreMap = new HashMap<Integer, Double>();//课程总评分
		HashMap<Integer, Integer> itemTotalCountMap = new HashMap<Integer, Integer>();//课程评分数量
		TrainingClassItemEvaluateBusiness itemBusiness = new TrainingClassItemEvaluateBusiness();
		List<TrainingClassItemEvaluateBean> itemList = itemBusiness.getTrainingClassItemEvaluateListByMap(condMap);
		for (TrainingClassItemEvaluateBean bean : itemList) {
			int key = bean.getItemId();
			if (itemTotalScoreMap.containsKey(key)) {
				//如果已存在，则相加数值
				itemTotalScoreMap
						.put(key, NumeralOperationKit.add(itemTotalScoreMap.get(key), bean.getEvaluateScore()));
				itemTotalCountMap.put(key, itemTotalCountMap.get(key) + 1);
			} else {
				//如果不存在，则初始化数值
				itemTotalScoreMap.put(key, bean.getEvaluateScore());
				itemTotalCountMap.put(key, 1);
			}
		}
		
		

		request.setAttribute("EffectScore", effectScore);
		request.setAttribute("EffectScoreCount", effectScoreCount);
		request.setAttribute("OrganizeScore", organizeScore);
		request.setAttribute("OrganizeScoreCount", organizeScoreCount);
		request.setAttribute("EvaluateItemNameMap", itemNameMap);
		request.setAttribute("EvaluateItemTotalScoreMap", itemTotalScoreMap);
		request.setAttribute("EvaluateItemTotalCountMap", itemTotalCountMap);
		request.setAttribute("TrainingClassInfoBean", classInfoBean);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH
				+ "TrainingClassEvaluateList.jsp");
		rd.forward(request, response);

	}

}
