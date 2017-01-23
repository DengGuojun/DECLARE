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
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TeacherDeclareBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.TeacherDeclareBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TeacherDeclareList
 */
@WebServlet("/declare/admin/TeacherDeclareList.do")
public class TeacherDeclareList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherDeclareList() {
		super();
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
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		AdminUserInfoBean adminUserInfoBean = adminUserHelper.getAdminUserInfo();
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		HashMap<String, String> condMap = ParamKit.getParameterMap(request,
				"identityNumber,teacherName,phone,declareStatus");
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.SEARCH)) {
			return;
		}
		TeacherDeclareBusiness business = new TeacherDeclareBusiness();
		if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
			condMap.put("province", DeclareAdminConfig.COUNTRY_STR);
		} else {
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			condMap.put("region", adminUserInfoBean.getRegion());
		}

		PageResultBean<TeacherDeclareBean> result = business.getTeacherDeclarePageListByMap(condMap, pageBean);
		request.setAttribute("TeacherDeclareList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("adminUserHelper", adminUserHelper);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TeacherDeclareList.jsp");
		rd.forward(request, response);
	}
}
