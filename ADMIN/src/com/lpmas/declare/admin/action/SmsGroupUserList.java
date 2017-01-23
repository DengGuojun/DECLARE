package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.SmsGroupUserBean;
import com.lpmas.declare.admin.bean.SmsUserBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.SmsGroupUserBusiness;
import com.lpmas.declare.admin.business.SmsUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class SmsGroupUserList
 */
@WebServlet("/declare/admin/SmsGroupUserList.do")
public class SmsGroupUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SmsGroupUserList() {
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
		int groupId = ParamKit.getIntParameter(request, "groupId", 0);

		// 处理查询条件
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, "smsUserName,smsUserCorporate");
		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("status", status);

		//获取所有用户
		SmsUserBusiness smsUserBusiness = new SmsUserBusiness();
		List<SmsUserBean> result = smsUserBusiness.getSmsUserListByMap(condMap);
		
		//获取当前通讯录的用户
		SmsGroupUserBusiness business = new SmsGroupUserBusiness();
		List<SmsGroupUserBean> smsGroupUserList =  business.getSmsGroupUserByGroupId(groupId);
		Map<String,SmsGroupUserBean> smsGroupUserMap = ListKit.list2Map(smsGroupUserList, "smsUserId");

		request.setAttribute("SmsUserList", result);
		request.setAttribute("SmsGroupUserMap", smsGroupUserMap);
		request.setAttribute("GroupId", groupId);
		request.setAttribute("AdminUserHelper", adminHelper);

		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "SmsGroupUserList.jsp");
		rd.forward(request, response);
	}

}
