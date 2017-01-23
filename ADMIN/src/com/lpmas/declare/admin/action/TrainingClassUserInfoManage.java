package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.IndustryInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.IndustryInfoBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassUserInfoManage.do")
public class TrainingClassUserInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassUserInfoManage() {
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
		request.setAttribute("trainingClassInfoBean", trainingClassInfoBean);
		// 初始化页面分页参数
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		HashMap<String, String> condMap = new HashMap<String, String>();
		HashMap<String, List<String>> scopeMap = new HashMap<String, List<String>>();
		// 从Mongo中获取相应的数据
		DeclareReportBusiness business = new DeclareReportBusiness();
		try {
			condMap.put("declareYear", trainingClassInfoBean.getTrainingYear());
			condMap.put("declareStatus", DeclareInfoConfig.DECLARE_STATUS_APPROVE);
			condMap.put("declareType", String.valueOf(trainingClassInfoBean.getTrainingType()));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			condMap.put("province", trainingClassInfoBean.getProvince());
			condMap.put("city", trainingClassInfoBean.getCity());
			condMap.put("region", trainingClassInfoBean.getRegion());
			String userStatus = ParamKit.getParameter(request, "userStatus", "").trim();
			if (StringKit.isValid(userStatus)) {
				List<String> classInfoList = new ArrayList<String>();
				classInfoList.add(String.valueOf(trainingClassInfoBean.getClassId()));
				if (userStatus.equals(TrainingClassUserConfig.USER_STATUS_NOT_ADD)) {
					scopeMap.put(TrainingClassUserConfig.USER_STATUS_NOT_ADD, classInfoList);
				} else if (userStatus.equals(TrainingClassUserConfig.USER_STATUS_ADD)) {
					scopeMap.put(TrainingClassUserConfig.USER_STATUS_ADD, classInfoList);
				}
			}
			PageResultBean<DeclareReportBean> result = business.getDeclareReportPageListByMap(condMap, scopeMap,
					pageBean);

			request.setAttribute("DeclareReportList", result.getRecordList());
			// 初始化分页数据
			pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
			request.setAttribute("PageResult", pageBean);
			request.setAttribute("CondList", MapKit.map2List(condMap));
			// 查产业
			IndustryInfoBusiness industryInfoBusiness = new IndustryInfoBusiness();
			List<IndustryInfoBean> industryInfoList = industryInfoBusiness.getIndustryInfoAllList();
			Map<Integer, String> industryInfoMap = new HashMap<Integer, String>();
			if (!industryInfoList.isEmpty()) {
				industryInfoMap = ListKit.list2Map(industryInfoList, "industryId", "industryName");
			}
			request.setAttribute("industryInfoMap", industryInfoMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingClassUserInfoManage.jsp");
		rd.forward(request, response);
	}

}
