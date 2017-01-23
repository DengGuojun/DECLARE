package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassUserExamManage.do")
public class TrainingClassUserExamManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassUserExamManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		// 初始化页面分页参数
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("classId", String.valueOf(classId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
		PageResultBean<TrainingClassUserBean> trainingClassUserList = trainingClassUserBusiness
				.getTrainingClassUserPageListByMap(condMap, pageBean);
		// 从Mongo中获取相应的数据
		DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
		HashMap<Integer, DeclareReportBean> declareReportMap = new HashMap<Integer, DeclareReportBean>();
		for (TrainingClassUserBean trainingClassUserBean : trainingClassUserList.getRecordList()) {
			try {
				DeclareReportBean declareReportBean = declareReportBusiness
						.getDeclareReportByKey(String.valueOf(trainingClassUserBean.getDeclareId()));
				declareReportMap.put(trainingClassUserBean.getDeclareId(), declareReportBean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("trainingClassInfoBean", trainingClassInfoBean);
		request.setAttribute("declareReportMap", declareReportMap);
		request.setAttribute("trainingClassUserList", trainingClassUserList.getRecordList());
		// 初始化分页数据
		pageBean.init(pageNum, pageSize, trainingClassUserList.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingClassUserExamManage.jsp");
		rd.forward(request, response);
	}

}
