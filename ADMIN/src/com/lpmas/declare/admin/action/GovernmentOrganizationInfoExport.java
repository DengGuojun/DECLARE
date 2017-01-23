package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.GovernmentOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.GovernmentOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class GovernmentOrganizationInfoExport
 */
@WebServlet("/declare/admin/GovernmentOrganizationInfoExport.do")
public class GovernmentOrganizationInfoExport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GovernmentOrganizationInfoExport() {
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
		if (!adminHelper.checkPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO, OperationConfig.SEARCH)) {
			return;
		}

		// 处理查询条件
		HashMap<String, String> condMap = new HashMap<String, String>();

		String organizationName = ParamKit.getParameter(request, "organizationName", "");
		if (StringKit.isValid(organizationName)) {
			condMap.put("organizationName", organizationName);
		}
		
		String onlyProvince = ParamKit.getParameter(request, "onlyProvince", "");
		String directUnder = ParamKit.getParameter(request, "directUnder", "");
		if (StringKit.isValid(onlyProvince)) {
			condMap.put("organizationLevel", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "region"))) {
			condMap.put("organizationLevel", DeclareAdminConfig.ADMIN_LEVEL_REGION);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "city"))) {
			condMap.put("organizationLevel", DeclareAdminConfig.ADMIN_LEVEL_CITY);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "province"))) {
			condMap.put("organizationLevel", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
		}

		int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
		condMap.put("status", "" + status);
		String trainingYear = ParamKit.getParameter(request, "trainingYear", "");
		if (StringKit.isValid(trainingYear)) {
			condMap.put("trainingYear", trainingYear);
		}

		// 根据用户级别，获取对应的地区信息
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
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

		GovernmentOrganizationInfoBusiness business = new GovernmentOrganizationInfoBusiness();
		List<GovernmentOrganizationInfoBean> result = business
				.getGovernmentOrganizationInfoListByMap(condMap);
		
		List<List<Object>> contentList = new ArrayList<List<Object>>();
		List<Object> tempList = null;
		List<String> headerList = new ArrayList<String>();
		headerList.add("培训年度");
		headerList.add("所属地区");
		headerList.add("主管部门");
		headerList.add("主管处（科）室");
		headerList.add("处（科）室负责人姓名");
		headerList.add("处（科）室负责人电话");
		headerList.add("处（科）室经办人姓名");
		headerList.add("处（科）室经办人电话");
		headerList.add("传真");
		headerList.add("通讯地址");
		headerList.add("邮政编码");
		
		for (GovernmentOrganizationInfoBean bean : result) {
			tempList = new ArrayList<Object>();
			tempList.add(bean.getTrainingYear());
			tempList.add(bean.getProvince()+bean.getCity()+bean.getRegion());
			tempList.add(bean.getOrganizationName());
			tempList.add(bean.getDepartment());
			tempList.add(bean.getResponsiblePersonName());
			tempList.add(bean.getResponsiblePersonMobile());
			tempList.add(bean.getOperatorName());
			tempList.add(bean.getOperatorMobile());
			tempList.add(bean.getFax());
			tempList.add(bean.getAddress());
			tempList.add(bean.getZipCode());
			contentList.add(tempList);
		}
		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
		excelWriteBean.setFileName("主管部门");
		excelWriteBean.setFileType("xlsx");
		excelWriteBean.setHeaderList(headerList);
		excelWriteBean.setContentList(contentList);
		WebExcelWriteKit webExcelWriteKit = new WebExcelWriteKit();
		webExcelWriteKit.outputExcel(excelWriteBean, request, response);
		return;

	}

}
