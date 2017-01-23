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
import com.lpmas.declare.admin.bean.TrainingClassTargetBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingClassTargetBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.business.DeclareInfoHelper;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassTargetList.do")
public class TrainingClassTargetList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassTargetList() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_TARGET, OperationConfig.SEARCH)) {
			return;
		}
		int level = ParamKit.getIntParameter(request, "level", 0);
		HashMap<String, String> condMap = new HashMap<String, String>();
		DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
		String trainingYear = ParamKit.getParameter(request, "trainingYear", String.valueOf(declareInfoHelper.getDeclareYear())).trim();
		if (StringKit.isValid(trainingYear)) {
			condMap.put("trainingYear", trainingYear);
		}
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_MANAGE, OperationConfig.SEARCH)) {
			return;
		}
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
		TrainingClassTargetBusiness business = new TrainingClassTargetBusiness();
		condMap.put("orderBy", "province,city,region");
		List<TrainingClassTargetBean> trainingClassTargetList = business.getTrainingClassTargetListByMap(condMap);
		List<TrainingClassTargetBean> trainingClassOrgTargetList = null;
		if (level == 0 || level == 1 || level == 2) {
			trainingClassOrgTargetList = new ArrayList<TrainingClassTargetBean>();
			for (TrainingClassTargetBean trainingClassTargetBean : trainingClassTargetList) {
				if (trainingClassTargetBean.getProvince().equals(adminUserInfoBean.getProvince())
						&& trainingClassTargetBean.getCity().equals(adminUserInfoBean.getCity())
						&& trainingClassTargetBean.getRegion().equals(adminUserInfoBean.getRegion())) {
					trainingClassOrgTargetList.add(trainingClassTargetBean);
				}
			}
			request.setAttribute("trainingClassTargetList", business.processTrainingClassTargetListByMap(condMap, trainingClassTargetList));
		}  else if (level == 3) {
			request.setAttribute("trainingClassTargetList", business.processTrainingClassTargetProvinceListByMap(condMap, trainingClassTargetList));
		} else {
			HttpResponseKit.alertMessage(response, "参数非法！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		request.setAttribute("trainingClassOrgTargetList", trainingClassOrgTargetList);
		// 机构信息
		TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
		HashMap<String, String> trainingOrganizationInfoMap = new HashMap<String, String>();
		trainingOrganizationInfoMap.put("status", String.valueOf(Constants.STATUS_VALID));
		trainingOrganizationInfoMap.put("province", condMap.get("province"));
		trainingOrganizationInfoMap.put("city", condMap.get("city"));
		trainingOrganizationInfoMap.put("region", condMap.get("region"));
		List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = trainingOrganizationInfoBusiness
				.getTrainingOrganizationInfoListByMap(trainingOrganizationInfoMap);
		request.setAttribute("trainingOrganizationInfoMap", ListKit.list2Map(trainingOrganizationInfoList, "organizationId", "organizationName"));
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingClassTargetList.jsp");
		rd.forward(request, response);

	}

}
