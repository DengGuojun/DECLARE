package com.lpmas.declare.admin.action;

import java.io.IOException;

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
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class SmsUserManage
 */
@WebServlet("/declare/admin/SmsUserManage.do")
public class SmsUserManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SmsUserManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int smsUserId = ParamKit.getIntParameter(request, "smsUserId", 0);

		SmsUserBean bean = new SmsUserBean();
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (smsUserId > 0) {
			SmsUserBusiness business = new SmsUserBusiness();
			bean = business.getSmsUserByKey(smsUserId);
		} else {
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("SmsUserBean", bean);
		request.setAttribute("adminUserHelper", adminHelper);

		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "SmsUserManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		SmsUserBean bean = new SmsUserBean();
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		try {
			bean = BeanKit.request2Bean(request, SmsUserBean.class);
			SmsUserBusiness business = new SmsUserBusiness();
			int result = 0;
			if (bean.getSmsUserId() > 0) {
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateSmsUser(bean);
			} else {
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addSmsUser(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/SmsUserList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {

		}
	}
}
