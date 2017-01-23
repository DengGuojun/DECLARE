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

import com.lpmas.declare.admin.bean.AdminUserGroupBean;
import com.lpmas.declare.admin.business.AdminUserGroupBusiness;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class AdminUserGroupExport
 */
@WebServlet("/declare/admin/AdminUserGroupExport.do")
public class AdminUserGroupExport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserGroupExport() {
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
		if (!adminHelper.checkPermission(DeclareAdminResource.ADMIN_USER, OperationConfig.SEARCH)) {
			return;
		}

		// 处理查询条件
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, "province,city,region,groupName");
		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("status", status);

		String onlyProvince = ParamKit.getParameter(request, "onlyProvince", "");
		String directUnder = ParamKit.getParameter(request, "directUnder", "");
		if (StringKit.isValid(onlyProvince)) {
			condMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "region"))) {
			condMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_REGION);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "city"))) {
			condMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_CITY);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "province"))) {
			condMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
		}

		AdminUserGroupBusiness business = new AdminUserGroupBusiness();
		List<AdminUserGroupBean> result = business.getAdminUserGroupListByMap(condMap);
		List<List<Object>> contentList = new ArrayList<List<Object>>();
		List<Object> tempList = null;
		List<String> headerList = new ArrayList<String>();
		headerList.add("所在地区");
		headerList.add("机构名称");
		headerList.add("地址");
		headerList.add("邮政编码");
		
		for (AdminUserGroupBean bean : result) {
			tempList = new ArrayList<Object>();
			tempList.add(bean.getProvince()+bean.getCity()+bean.getRegion());
			tempList.add(bean.getGroupName());
			tempList.add(bean.getAddress());
			tempList.add(bean.getZipCode());
			contentList.add(tempList);
		}
		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
		excelWriteBean.setFileName("主管机构");
		excelWriteBean.setFileType("xlsx");
		excelWriteBean.setHeaderList(headerList);
		excelWriteBean.setContentList(contentList);
		WebExcelWriteKit webExcelWriteKit = new WebExcelWriteKit();
		webExcelWriteKit.outputExcel(excelWriteBean, request, response);
		return;

	}

}
