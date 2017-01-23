package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassItemBusiness;
import com.lpmas.declare.admin.business.TrainingClassItemEvaluateBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassItemBean;
import com.lpmas.declare.bean.TrainingClassItemEvaluateBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassItemEvaluateList.do")
public class TrainingClassItemEvaluateList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(TrainingClassItemEvaluateList.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassItemEvaluateList() {
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

		int itemId = ParamKit.getIntParameter(request, "itemId", 0);
		TrainingClassItemBusiness trainingClassItemBusiness = new TrainingClassItemBusiness();
		TrainingClassItemBean classItemBean = trainingClassItemBusiness.getTrainingClassItemByKey(itemId);
		if (classItemBean == null) {
			HttpResponseKit.alertMessage(response, "课程ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		TrainingClassItemEvaluateBusiness business = new TrainingClassItemEvaluateBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("classId", String.valueOf(classId));
		condMap.put("itemId", String.valueOf(itemId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		List<TrainingClassItemEvaluateBean> list = business.getTrainingClassItemEvaluateListByMap(condMap);
		// 获取评教人的姓名(国家级和省级能看到评教人姓名，市级和县级看到的是“匿名”)
		HashMap<Integer, String> evaluateUserMap = new HashMap<Integer, String>();
		if (adminHelper.getAdminUserInfo().getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)
				|| adminHelper.getAdminUserInfo().getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
			DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
			for (TrainingClassItemEvaluateBean bean : list) {
				try {
					DeclareReportBean declareReportBean = declareReportBusiness.getDeclareReportByKey(String
							.valueOf(bean.getDeclareId()));
					evaluateUserMap.put(bean.getEvaluateId(), declareReportBean.getUserName());
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}

		request.setAttribute("TrainingClassItemEvaluateList", list);
		request.setAttribute("EvaluateUserMap", evaluateUserMap);
		request.setAttribute("TrainingClassInfoBean", classInfoBean);
		request.setAttribute("TrainingClassItemBean", classItemBean);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH
				+ "TrainingClassItemEvaluateList.jsp");
		rd.forward(request, response);

	}

}
