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

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/DeclareInfoAuthManage.do")
public class DeclareInfoAuthManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoAuthManage() {
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
		if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_AUTH, OperationConfig.SEARCH)) {
			return;
		}
		int classId = ParamKit.getIntParameter(request, "classId", -1);
		if (classId <= -1) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		request.setAttribute("classId", classId);
		// 初始化页面分页参数
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		HashMap<String, String> condMap = new HashMap<String, String>();
		HashMap<String, List<String>> scopeMap = new HashMap<String, List<String>>();
		// 从Mongo中获取相应的数据
		DeclareReportBusiness business = new DeclareReportBusiness();
		try {
			condMap.put("declareStatus", DeclareInfoConfig.DECLARE_STATUS_APPROVE);
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			if (classId != 0) {
				// 根据班级ID查询班级信息
				TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
				TrainingClassInfoBean trainingClassInfoBean = trainingClassInfoBusiness
						.getTrainingClassInfoByKey(classId);
				if (trainingClassInfoBean == null) {
					HttpResponseKit.alertMessage(response, "班级信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				String userName = ParamKit.getParameter(request, "userName", "").trim();
				if (StringKit.isValid(userName)) {
					condMap.put("userName", userName);
				}
				String identityNumber = ParamKit.getParameter(request, "identityNumber", "").trim();
				if (StringKit.isValid(identityNumber)) {
					condMap.put("identityNumber", identityNumber);
				}
				List<String> classInfoList = new ArrayList<String>();
				classInfoList.add(String.valueOf(trainingClassInfoBean.getClassId()));
				scopeMap.put(TrainingClassUserConfig.USER_STATUS_ADD, classInfoList);

			} else {
				AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
				if (adminUserInfoBean == null) {
					HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 根据用户级别，获取对应的地区信息
				if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
					// 国家级机构，可以选择省，市和区进行筛选
					String queryProvince = ParamKit.getParameter(request, "queryProvince", "").trim();
					if (StringKit.isValid(queryProvince)) {
						condMap.put("province", queryProvince);
					}
					String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
					if (StringKit.isValid(queryCity)) {
						condMap.put("city", queryCity);
					}
					String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
					if (StringKit.isValid(queryRegion)) {
						condMap.put("region", queryRegion);
					}
				} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
					// 省级机构，可以选择市和区进行筛选
					condMap.put("province", adminUserInfoBean.getProvince());
					request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
					String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
					if (StringKit.isValid(queryCity)) {
						condMap.put("city", queryCity);
					}
					String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
					if (StringKit.isValid(queryRegion)) {
						condMap.put("region", queryRegion);
					}
				} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
					// 市级机构，可以选择区进行筛选
					condMap.put("province", adminUserInfoBean.getProvince());
					condMap.put("city", adminUserInfoBean.getCity());
					request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
					request.setAttribute("FixCity", adminUserInfoBean.getCity());
					String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
					if (StringKit.isValid(queryRegion)) {
						condMap.put("region", queryRegion);
					}
				} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)) {
					// 区级机构，不能客制化筛选
					condMap.put("province", adminUserInfoBean.getProvince());
					condMap.put("city", adminUserInfoBean.getCity());
					condMap.put("region", adminUserInfoBean.getRegion());
					request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
					request.setAttribute("FixCity", adminUserInfoBean.getCity());
					request.setAttribute("FixRegion", adminUserInfoBean.getRegion());
				}
				String authStatus = ParamKit.getParameter(request, "authStatus", "").trim();
				if (StringKit.isValid(authStatus)) {
					condMap.put("authStatus", authStatus);
				}
				String declareYear = ParamKit.getParameter(request, "declareYear", "").trim();
				if (StringKit.isValid(declareYear)) {
					condMap.put("declareYear", declareYear);
				}
				List<String> classInfoList = new ArrayList<String>();
				scopeMap.put("existNoClass", classInfoList);
			}

			PageResultBean<DeclareReportBean> result = business.getDeclareReportPageListByMap(condMap, scopeMap,
					pageBean);

			request.setAttribute("DeclareReportList", result.getRecordList());
			// 初始化分页数据
			pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
			request.setAttribute("PageResult", pageBean);
			request.setAttribute("CondList", MapKit.map2List(condMap));

		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "DeclareInfoAuthManage.jsp");
		rd.forward(request, response);

	}

}
