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
import com.lpmas.declare.admin.bean.OrganizationStatisticsBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.OrganizationStatisticsBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.declare.business.DeclareInfoHelper;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/OrganizationStatisticsList.do")
public class OrganizationStatisticsList extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_KINDS_COUNT, OperationConfig.SEARCH)) {
			return;
		}
		int trainingType = ParamKit.getIntParameter(request, "trainingType", -1);
		HashMap<String, String> condMap = new HashMap<String, String>();

		condMap.put("trainingType", String.valueOf(trainingType));

		// 获取组织类型
		String organizationType = ParamKit.getParameter(request, "organizationType", "ORGANIZATION_TRAINING").trim();

		if (!TrainingOrganizationConfig.ORGANIZATION_MAP.containsKey(organizationType)) {
			HttpResponseKit.alertMessage(response, "参数类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		condMap.put("organizationType", organizationType);

		// 获取培训年份
		DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
		String trainingYear = ParamKit
				.getParameter(request, "trainingYear", String.valueOf(declareInfoHelper.getDeclareYear())).trim();
		if (StringKit.isValid(trainingYear)) {
			condMap.put("declareYear", trainingYear);
		}

		condMap.put("status", String.valueOf(Constants.STATUS_VALID));

		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		String queryProvince = ParamKit.getParameter(request, "queryProvince", "").trim();
		String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
		String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();

		if (StringKit.isValid(queryRegion)) {

		} else if (StringKit.isValid(queryCity)) {
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_REGION);
		} else if (StringKit.isValid(queryProvince)) {
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
		} else {
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
		}

		// 根据用户级别，获取对应的地区信息
		if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
			// 国家级机构，可以选择省，市和区进行筛选
			if (StringKit.isValid(queryProvince)) {
				condMap.put("province", queryProvince);
			}
			if (StringKit.isValid(queryCity)) {
				condMap.put("city", queryCity);
			}
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
			// 省级机构，可以选择市和区进行筛选
			condMap.put("province", adminUserInfoBean.getProvince());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			if (StringKit.isValid(queryCity)) {
				condMap.put("city", queryCity);
			}
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
			// 市级机构，可以选择区进行筛选
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			request.setAttribute("FixCity", adminUserInfoBean.getCity());
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

		HashMap<String, List<Object>> declareReportMap = new HashMap<>();
		HashMap<String, List<Object>> declareCollectMap = new HashMap<>();

		OrganizationStatisticsBusiness business = new OrganizationStatisticsBusiness();

		List<OrganizationStatisticsBean> organizationStatisticsList = business
				.getOrganizationStatisticsListByMap(condMap);
		for (OrganizationStatisticsBean bean : organizationStatisticsList) {
			List<Object> list = new ArrayList<>();
			list.add(bean.getLevel());
			list.add(bean.getTotalTraining());
			list.add(bean.getAgriculturalSchool());
			list.add(bean.getAgricultural_vocational_college());
			list.add(bean.getSchool_of_Agricultural_Mechanization());
			list.add(bean.getAgricultural_extension_services());
			list.add(bean.getAgricultural_efficiency());
			list.add(bean.getAcademy_of_Agricultural_Sciences());
			list.add(bean.getAgricultural_administrative_departments());
			list.add(bean.getPeasant_cooperatives());
			list.add(bean.getAgricultural_leading_enterprises());
			list.add(bean.getOther_public_institutions());
			list.add(bean.getOther_private_organizations());

			String name = "";
			switch (bean.getLevel()) {
			case DeclareAdminConfig.ADMIN_LEVEL_PROVINCE:
				name = new String(bean.getProvince());
				break;
			case DeclareAdminConfig.ADMIN_LEVEL_CITY:
				name = new String(bean.getCity());
				list.add(bean.getProvince());
				break;
			case DeclareAdminConfig.ADMIN_LEVEL_REGION:
				name = new String(bean.getRegion());
				list.add(bean.getProvince());
				list.add(bean.getCity());
				break;
			}
			declareReportMap.put(name, list);
		}

		int totalTraining = 0; // 培训总数
		int agriculturalSchool = 0; // 农广校
		int agricultural_vocational_college = 0; // 农业职业院校
		int school_of_Agricultural_Mechanization = 0; // 农机化学院
		int agricultural_extension_services = 0; // 农技推广服务机构
		int agricultural_efficiency = 0; // 农业高效
		int academy_of_Agricultural_Sciences = 0; // 农科院所(站)
		int agricultural_administrative_departments = 0; // 农业行政主管部门
		int peasant_cooperatives = 0; // 农民合作社
		int agricultural_leading_enterprises = 0; // 农业龙头企业
		int other_public_institutions = 0; // 其他公办机构
		int other_private_organizations = 0; // 其他民办机构
		for (OrganizationStatisticsBean bean : organizationStatisticsList) {
			totalTraining += bean.getTotalTraining();
			agriculturalSchool += bean.getAgriculturalSchool();
			agricultural_vocational_college += bean.getAgricultural_vocational_college();
			school_of_Agricultural_Mechanization += bean.getSchool_of_Agricultural_Mechanization();
			agricultural_extension_services += bean.getAgricultural_extension_services();
			agricultural_efficiency += bean.getAgricultural_efficiency();
			academy_of_Agricultural_Sciences += bean.getAcademy_of_Agricultural_Sciences();
			agricultural_administrative_departments += bean.getAgricultural_administrative_departments();
			peasant_cooperatives += bean.getPeasant_cooperatives();
			agricultural_leading_enterprises += bean.getAgricultural_leading_enterprises();
			other_public_institutions += bean.getOther_public_institutions();
			other_private_organizations += bean.getOther_private_organizations();
		}
		List<Object> list = new ArrayList<>();
		list.add("NONE");
		list.add(totalTraining);
		list.add(agriculturalSchool);
		list.add(agricultural_vocational_college);
		list.add(school_of_Agricultural_Mechanization);
		list.add(agricultural_extension_services);
		list.add(agricultural_efficiency);
		list.add(academy_of_Agricultural_Sciences);
		list.add(agricultural_administrative_departments);
		list.add(peasant_cooperatives);
		list.add(agricultural_leading_enterprises);
		list.add(other_public_institutions);
		list.add(other_private_organizations);
		declareCollectMap.put("合计", list);

		request.setAttribute("declareReportMap", declareReportMap);
		request.setAttribute("declareCollectMap", declareCollectMap);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "OrganizationStatisticsList.jsp");
		rd.forward(request, response);
	}

}
