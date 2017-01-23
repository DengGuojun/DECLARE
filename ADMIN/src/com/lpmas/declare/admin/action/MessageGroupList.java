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

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.MessageGroupBean;
import com.lpmas.declare.admin.bean.MessageGroupUserBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.business.MessageGroupBusiness;
import com.lpmas.declare.admin.business.MessageGroupUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class MessageGroupList
 */
@WebServlet("/declare/admin/MessageGroupList.do")
public class MessageGroupList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageGroupList() {
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
		MessageGroupBusiness groupBusiness = new MessageGroupBusiness();
		MessageGroupUserBusiness groupUserBusiness = new MessageGroupUserBusiness();
		AdminUserInfoBusiness userBusiness = new AdminUserInfoBusiness();

		int userId = adminHelper.getAdminUserId();
		List<MessageGroupBean> groupList = groupBusiness.getMessageGroupByOwnerId(userId);

		int groupId = ParamKit.getIntParameter(request, "groupId", 0);
		if (groupId == 0 && !groupList.isEmpty()) {
			groupId = groupList.get(0).getGroupId();
		}
		List<AdminUserInfoBean> userInfoList = new ArrayList<AdminUserInfoBean>();
		List<MessageGroupUserBean> groupUserList = groupUserBusiness.getMessageGroupUserByGroupId(groupId);
		for(MessageGroupUserBean groupUserBean : groupUserList){
			AdminUserInfoBean userBean  = userBusiness.getAdminUserInfoByKey(groupUserBean.getUserId());
			userInfoList.add(userBean);
		}

		request.setAttribute("MessageGroupList", groupList);
		request.setAttribute("UserInfoList", userInfoList);
		request.setAttribute("CurrentGroupId", groupId);

		request.setAttribute("adminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH
				+ "MessageGroupList.jsp");

		rd.forward(request, response);
	}

}
