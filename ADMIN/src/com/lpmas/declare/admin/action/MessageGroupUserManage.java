package com.lpmas.declare.admin.action;

import java.io.IOException;

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
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class MessageGroupUserManage
 */
@WebServlet("/declare/admin/MessageGroupUserManage.do")
public class MessageGroupUserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageGroupUserManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int groupId = ParamKit.getIntParameter(request, "groupId", 0);
		int userId = ParamKit.getIntParameter(request, "userId", 0);
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);

		MessageGroupBusiness groupBusiness = new MessageGroupBusiness();
		MessageGroupBean groupBean = groupBusiness.getMessageGroupByKey(groupId);
		// 只能管理属于自己的通讯录
		if (groupBean.getOwnerId() != adminHelper.getAdminUserId()) {
			HttpResponseKit.alertMessage(response, "通讯录参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		AdminUserInfoBusiness userInfoBusiness = new AdminUserInfoBusiness();
		AdminUserInfoBean userInfoBean = userInfoBusiness.getAdminUserInfoByKey(userId);
		if (userInfoBean == null) {
			HttpResponseKit.alertMessage(response, "联系人参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		MessageGroupUserBusiness business = new MessageGroupUserBusiness();
		MessageGroupUserBean bean = new MessageGroupUserBean();
		bean.setGroupId(groupId);
		bean.setUserId(userId);

		int result = 0;
		String redirectUrl = "";
		int isDelete = ParamKit.getIntParameter(request, "isDelete", 0);
		if (isDelete > 0) {
			// 从通讯录中移除
			result = business.removeMessageGroupUser(bean);
			redirectUrl = "/declare/admin/MessageGroupList.do?groupId=" + groupId;
		} else {
			// 新增联系人到通讯录
			result = business.addMessageGroupUser(bean);
			redirectUrl = "/declare/admin/MessageGroupUserList.do?groupId=" + groupId;
		}

		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", redirectUrl);
		} else {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}
}
