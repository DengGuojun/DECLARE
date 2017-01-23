package com.lpmas.declare.admin.action;

import java.io.IOException;

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
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class SmsGroupUserManage
 */
@WebServlet("/declare/admin/SmsGroupUserManage.do")
public class SmsGroupUserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SmsGroupUserManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int groupId = ParamKit.getIntParameter(request, "groupId", 0);
		int smsUserId = ParamKit.getIntParameter(request, "smsUserId", 0);
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);

		SmsGroupBusiness groupBusiness = new SmsGroupBusiness();
		SmsGroupBean groupBean = groupBusiness.getSmsGroupByKey(groupId);
		// 只能管理属于自己的通讯录
		if (groupBean.getOwnerId() != adminHelper.getAdminUserId()) {
			HttpResponseKit.alertMessage(response, "通讯录参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		SmsUserBusiness smsUserBusiness = new SmsUserBusiness();
		SmsUserBean smsUserBean = smsUserBusiness.getSmsUserByKey(smsUserId);
		if (smsUserBean == null) {
			HttpResponseKit.alertMessage(response, "联系人参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		SmsGroupUserBusiness business = new SmsGroupUserBusiness();
		SmsGroupUserBean bean = new SmsGroupUserBean();
		bean.setGroupId(groupId);
		bean.setSmsUserId(smsUserId);

		int result = 0;
		String redirectUrl = "";
		int isDelete = ParamKit.getIntParameter(request, "isDelete", 0);
		if (isDelete > 0) {
			// 从通讯录中移除
			result = business.removeSmsGroupUser(bean);
			redirectUrl = "/declare/admin/SmsGroupList.do?groupId=" + groupId;
		} else {
			// 新增联系人到通讯录
			result = business.addSmsGroupUser(bean);
			redirectUrl = "/declare/admin/SmsGroupUserList.do?groupId=" + groupId;
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
