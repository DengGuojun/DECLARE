package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.SmsGroupBean;
import com.lpmas.declare.admin.bean.SmsGroupUserBean;
import com.lpmas.declare.admin.bean.SmsUserBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.SmsGroupBusiness;
import com.lpmas.declare.admin.business.SmsGroupUserBusiness;
import com.lpmas.declare.admin.business.SmsUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class SmsGroupList
 */
@WebServlet("/declare/admin/SmsGroupList.do")
public class SmsGroupList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SmsGroupList() {
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
		SmsGroupBusiness groupBusiness = new SmsGroupBusiness();
		SmsGroupUserBusiness groupUserBusiness = new SmsGroupUserBusiness();
		SmsUserBusiness smsUserBusiness = new SmsUserBusiness();

		List<SmsGroupBean> groupList = groupBusiness.getSmsGroupByOwnerId(adminHelper.getAdminUserId());

		int groupId = ParamKit.getIntParameter(request, "groupId", 0);
		if (groupId == 0 && !groupList.isEmpty()) {
			groupId = groupList.get(0).getGroupId();
		}
		List<SmsUserBean> smsUserList = new ArrayList<SmsUserBean>();
		List<SmsGroupUserBean> groupUserList = groupUserBusiness.getSmsGroupUserByGroupId(groupId);
		for(SmsGroupUserBean groupUserBean : groupUserList){
			SmsUserBean userBean  = smsUserBusiness.getSmsUserByKey(groupUserBean.getSmsUserId());
			smsUserList.add(userBean);
		}	

		request.setAttribute("SmsGroupList", groupList);
		request.setAttribute("SmsUserList", smsUserList);
		request.setAttribute("CurrentGroupId", groupId);

		request.setAttribute("adminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH
				+ "SmsGroupList.jsp");

		rd.forward(request, response);
	}

}
