package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.SmsUserBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.SmsUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.config.Constants;

/**
 * Servlet implementation class SmsUserList
 */
@WebServlet("/declare/admin/SmsUserList.do")
public class SmsUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SmsUserList() {
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

		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		// 处理查询条件
		HashMap<String,String> condMap = new HashMap<String,String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		SmsUserBusiness business = new SmsUserBusiness();
		List<SmsUserBean> result = business.getSmsUserListByMap(condMap);
		request.setAttribute("SmsUserList", result);
		request.setAttribute("AdminUserHelper", adminHelper);

		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "SmsUserList.jsp");
		rd.forward(request, response);
	}

}
