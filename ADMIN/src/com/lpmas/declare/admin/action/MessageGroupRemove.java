package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.MessageGroupBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.MessageGroupBusiness;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class MessageGroupRemove
 */
@WebServlet("/declare/admin/MessageGroupRemove.do")
public class MessageGroupRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageGroupRemove() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int groupId = ParamKit.getIntParameter(request, "groupId", 0);
		MessageGroupBean bean = new MessageGroupBean();
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int userId = adminHelper.getAdminUserId();
		MessageGroupBusiness business = new MessageGroupBusiness();
		bean = business.getMessageGroupByKey(groupId);
		if (bean == null || bean.getOwnerId() != userId) {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} else {
			int result = 0;
			bean.setStatus(Constants.STATUS_NOT_VALID);
			bean.setModifyUser(userId);
			result = business.updateMessageGroup(bean);
			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/MessageGroupList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}
}
