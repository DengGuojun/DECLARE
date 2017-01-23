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
import com.lpmas.declare.admin.business.DeclareInfoBusiness;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.IndustryTypeBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.DeclareInfoRecommendConfig;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.IndustryTypeBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/DeclareInfoReviewList.do")
public class DeclareInfoReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoReviewList() {
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
		int modelType = ParamKit.getIntParameter(request, "modelType", 0);
		// 初始化页面分页参数
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		HashMap<String, String> condMap = new HashMap<String, String>();
		String userName = ParamKit.getParameter(request, "userName", "").trim();
		if (StringKit.isValid(userName)) {
			condMap.put("userName", userName);
		}
		String userMobile = ParamKit.getParameter(request, "userMobile", "").trim();
		if (StringKit.isValid(userMobile)) {
			condMap.put("userMobile", userMobile);
		}
		String identityNumber = ParamKit.getParameter(request, "identityNumber", "").trim();
		if (StringKit.isValid(identityNumber)) {
			condMap.put("identityNumber", identityNumber);
		}
		condMap.put("modelType", String.valueOf(modelType));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		HashMap<String, List<String>> scopeMap = new HashMap<String, List<String>>();
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
				condMap.put("queryProvince", queryProvince);
			}
			String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
			if (StringKit.isValid(queryCity)) {
				condMap.put("city", queryCity);
				condMap.put("queryCity", queryCity);
			}
			String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
				condMap.put("queryRegion", queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
			// 省级机构，可以选择市和区进行筛选
			condMap.put("province", adminUserInfoBean.getProvince());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
			if (StringKit.isValid(queryCity)) {
				condMap.put("city", queryCity);
				condMap.put("queryCity", queryCity);
			}
			String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
				condMap.put("queryRegion", queryRegion);
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
				condMap.put("queryRegion", queryRegion);
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
		if (modelType == DeclareInfoRecommendConfig.TYPE_VERIFY) {
			if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_VERIFY, OperationConfig.SEARCH)) {
				return;
			}
			int declareType = ParamKit.getIntParameter(request, "declareType", 0);
			if (!DeclareInfoConfig.DECLARE_TYPE_MAP.containsKey(declareType)) {
				HttpResponseKit.alertMessage(response, "对象类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			condMap.put("declareType", String.valueOf(declareType));
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			condMap.put("region", adminUserInfoBean.getRegion());
			// 从Mongo中获取相应的数据
			DeclareReportBusiness business = new DeclareReportBusiness();
			try {

				List<String> reviewStatusList = new ArrayList<String>();
				reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_SUBMIT);
				reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE);
				scopeMap.put("declareStatus", reviewStatusList);
				PageResultBean<DeclareReportBean> result = business.getDeclareReportPageListByMap(condMap, scopeMap,
						pageBean);

				request.setAttribute("DeclareReportList", result.getRecordList());
				// 初始化分页数据
				pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
				request.setAttribute("PageResult", pageBean);
				request.setAttribute("CondList", MapKit.map2List(condMap));
				request.setAttribute("modelType", modelType);
				request.setAttribute("declareType", declareType);
				// 获取产业类型列表
				IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
				List<IndustryTypeBean> industryTypeList = industryTypeBusiness.getIndustryTypeAllList();
				request.setAttribute("IndustryTypeMap", ListKit.list2Map(industryTypeList, "typeId", "typeName"));

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (modelType == DeclareInfoRecommendConfig.TYPE_MANAGE) {
			if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_MANAGE, OperationConfig.SEARCH)) {
				return;
			}
			int declareType = ParamKit.getIntParameter(request, "declareType", 0);
			if (!DeclareInfoConfig.DECLARE_TYPE_MAP.containsKey(declareType)) {
				HttpResponseKit.alertMessage(response, "对象类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			condMap.put("declareType", String.valueOf(declareType));
			// 从Mongo中获取相应的数据
			DeclareReportBusiness business = new DeclareReportBusiness();
			try {
				String declareStatus = ParamKit.getParameter(request, "declareStatus", "").trim();
				if (StringKit.isValid(declareStatus)) {
					condMap.put("declareStatus", declareStatus);
				}
				List<String> reviewStatusList = new ArrayList<String>();
				reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_SUBMIT);
				reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE);
				reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_APPROVE);
				scopeMap.put("declareStatus", reviewStatusList);
				PageResultBean<DeclareReportBean> result = business.getDeclareReportPageListByMap(condMap, scopeMap,
						pageBean);

				request.setAttribute("DeclareReportList", result.getRecordList());
				// 初始化分页数据
				pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
				request.setAttribute("PageResult", pageBean);
				request.setAttribute("CondList", MapKit.map2List(condMap));
				request.setAttribute("declareType", declareType);
				// 获取产业类型列表
				IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
				List<IndustryTypeBean> industryTypeList = industryTypeBusiness.getIndustryTypeAllList();
				request.setAttribute("IndustryTypeMap", ListKit.list2Map(industryTypeList, "typeId", "typeName"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (modelType == DeclareInfoRecommendConfig.TYPE_CLASSIFY) {
			if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_CLASSIFY, OperationConfig.SEARCH)) {
				return;
			}
			// 从Mongo中获取相应的数据
			DeclareInfoBusiness business = new DeclareInfoBusiness();
			DeclareReportBusiness reportBusiness = new DeclareReportBusiness();
			List<DeclareReportBean> resultList = new ArrayList<DeclareReportBean>();
			try {
				condMap.put("declareStatus", DeclareInfoConfig.DECLARE_STATUS_APPROVE);
				PageResultBean<DeclareInfoBean> result = business.getDeclareInfoPageListByMap(condMap, pageBean);
				for(DeclareInfoBean bean : result.getRecordList()){
					DeclareReportBean reportBean  = reportBusiness.getDeclareReportByKey(String.valueOf(bean.getDeclareId()));
					resultList.add(reportBean);
				}
				request.setAttribute("DeclareReportList", resultList);
				// 初始化分页数据
				pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
				request.setAttribute("PageResult", pageBean);
				request.setAttribute("CondList", MapKit.map2List(condMap));
				request.setAttribute("modelType", modelType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			HttpResponseKit.alertMessage(response, "页面操作非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "DeclareInfoReviewList.jsp");
		rd.forward(request, response);

	}

}
