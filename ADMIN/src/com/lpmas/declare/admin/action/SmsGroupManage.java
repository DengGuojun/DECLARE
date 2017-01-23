package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.SmsGroupBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.SmsGroupBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class SmsGroupManage
 */
@WebServlet("/declare/admin/SmsGroupManage.do")
public class SmsGroupManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SmsGroupManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int groupId = ParamKit.getIntParameter(request, "groupId", 0);

		SmsGroupBean bean = new SmsGroupBean();
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (groupId > 0) {
			SmsGroupBusiness business = new SmsGroupBusiness();
			bean = business.getSmsGroupByKey(groupId);
			if(bean.getOwnerId() != adminHelper.getAdminUserId()){
				HttpResponseKit.alertMessage(response, "你没有权限操作这个数据", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} else {
			bean.setOwnerId(adminHelper.getAdminUserId());
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("SmsGroupBean", bean);
		request.setAttribute("adminUserHelper", adminHelper);

		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "SmsGroupManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SmsGroupBean bean = new SmsGroupBean();
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		try {
			bean = BeanKit.request2Bean(request, SmsGroupBean.class);
			if(bean.getOwnerId() != adminHelper.getAdminUserId()){
				HttpResponseKit.alertMessage(response, "你没有权限操作这个数据", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			SmsGroupBusiness business = new SmsGroupBusiness();
			int result = 0;
			if (bean.getGroupId() > 0) {
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateSmsGroup(bean);
			} else {
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addSmsGroup(bean);
			}
			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/SmsGroupList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {

		}
	}
}
