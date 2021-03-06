package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TrainingOrganizationInfoList
 */
@WebServlet("/declare/admin/TrainingOrganizationInfoList.do")
public class TrainingOrganizationInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingOrganizationInfoList() {
		super();
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
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		// 设置用户查询的权限
		AdminUserInfoBean adminUserInfoBean = adminUserHelper.getAdminUserInfo();

		HashMap<String, String> condMap = new HashMap<String, String>();

		String organizationType = ParamKit.getParameter(request, "organizationType",
				TrainingOrganizationConfig.ORGANIZATION_TRAINING);
		if (StringKit.isValid(organizationType)) {
			condMap.put("organizationType", organizationType);
		}
		int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
		condMap.put("status", "" + status);

		if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO,
					OperationConfig.SEARCH)) {
				return;
			}
		} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO,
					OperationConfig.SEARCH)) {
				return;
			}
		} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)) {			 
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO,
					OperationConfig.SEARCH)) {
				return;
			}
		}

		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		String organizationCategory = ParamKit.getParameter(request, "organizationCategory");
		if (StringKit.isValid(organizationCategory)) {
			condMap.put("organizationCategory", organizationCategory);
		}

		String organizationName = ParamKit.getParameter(request, "organizationName");
		if (StringKit.isValid(organizationName)) {
			condMap.put("organizationName", organizationName);
		}

		String trainingYear = ParamKit.getParameter(request, "trainingYear");
		if (StringKit.isValid(trainingYear)) {
			condMap.put("trainingYear", trainingYear);
		}

		String directUnder = ParamKit.getParameter(request, "directUnder", "");

		if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "region"))) {
			condMap.put("organizationLevel", DeclareAdminConfig.ADMIN_LEVEL_REGION);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "city"))) {
			condMap.put("organizationLevel", DeclareAdminConfig.ADMIN_LEVEL_CITY);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "province"))) {
			condMap.put("organizationLevel", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
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

		TrainingOrganizationInfoBusiness business = new TrainingOrganizationInfoBusiness();
		PageResultBean<TrainingOrganizationInfoBean> result = business.getTrainingOrganizationInfoPageListByMap(condMap,
				pageBean);
		request.setAttribute("TrainingOrganizationInfoList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("adminUserHelper", adminUserHelper);

		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingOrganizationInfoList.jsp");
		rd.forward(request, response);
	}

}
