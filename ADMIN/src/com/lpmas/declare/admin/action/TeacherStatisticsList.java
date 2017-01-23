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

import com.lpmas.declare.admin.bean.TeacherStatisticsBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TeacherStatisticsBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TeacherStatisticsList
 */
@WebServlet("/declare/admin/TeacherStatisticsList.do")
public class TeacherStatisticsList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherStatisticsList() {
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
		if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_STATISTICS, OperationConfig.SEARCH)) {
			return;
		}
		String province = ParamKit.getParameter(request, "province", "");
		String city = ParamKit.getParameter(request, "city", "");
		String level = ParamKit.getParameter(request, "level", "");

		TeacherStatisticsBusiness business = new TeacherStatisticsBusiness();
		List<TeacherStatisticsBean> result = new ArrayList<TeacherStatisticsBean>();
		HashMap<String, String> condMap = new HashMap<String, String>();
		if (!StringKit.isValid(level)) {
			// 默认获取总数、国家级的统计和省级的总数
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
			condMap.put("sum", String.valueOf(true));
			result.addAll(business.getTeacherStatisticsListByMap(condMap));
			condMap.put("sum", String.valueOf(false));
			result.addAll(business.getTeacherStatisticsListByMap(condMap));
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			condMap.put("sum", String.valueOf(true));
			result.addAll(business.getTeacherStatisticsListByMap(condMap));
		} else {
			
			if (level.equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
				//如果是从省级进入，则读取的是省级的统计和市级的总数
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
				condMap.put("province", province);
				condMap.put("sum", String.valueOf(false));
				result.addAll(business.getTeacherStatisticsListByMap(condMap));
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
				condMap.put("city", city);
				condMap.put("sum", String.valueOf(true));
				result.addAll(business.getTeacherStatisticsListByMap(condMap));
			}else if (level.equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)){
				//如果是从市级进入，则读取的是市级的统计和区级的统计
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
				condMap.put("province", province);
				condMap.put("city", city);
				condMap.put("sum", String.valueOf(false));
				result.addAll(business.getTeacherStatisticsListByMap(condMap));
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_REGION);
				result.addAll(business.getTeacherStatisticsListByMap(condMap));
			}
		}

		request.setAttribute("TeacherStatisticsList", result);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TeacherStatisticsList.jsp");
		rd.forward(request, response);

	}

}
