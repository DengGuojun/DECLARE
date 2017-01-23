package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
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
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class AdminUserGroupList
 */
@WebServlet("/declare/admin/AdminUserGroupList.do")
public class AdminUserGroupList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserGroupList() {
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
		int pageNum = ParamKit.getIntParameter(request, "pageNum", 1);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", 20);
		PageBean pageBean = new PageBean(pageNum, pageSize);

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
		PageResultBean<AdminUserGroupBean> result = business.getAdminUserGroupPageListByMap(condMap, pageBean);

		request.setAttribute("UserGroupList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("AdminUserHelper", adminHelper);

		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "AdminUserGroupList.jsp");
		rd.forward(request, response);
	}

}
