package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserGroupBean;
import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminLogonUtil;
import com.lpmas.declare.admin.business.AdminUserGroupBusiness;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.CookiesKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class AdminLogon
 */
@WebServlet("/Logon.do")
public class AdminLogon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminLogon() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = DeclareAdminConfig.PAGE_PATH + "AdminLogon.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String loginId = ParamKit.getParameter(request, "loginId", "");
		String loginPassword = ParamKit.getParameter(request, "loginPassword", "");

		if (!StringKit.isVerified(loginId)) {
			HttpResponseKit.alertMessage(response, "用户ID为空或者含有非法字符！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		if (!StringKit.isVerified(loginPassword)) {
			HttpResponseKit.alertMessage(response, "用户密码为空或者含有非法字符！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 从session中取出servlet生成的验证码text值
		String kaptchaExpected = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 获取用户页面输入的验证码
		String kaptchaReceived = request.getParameter("kaptcha");
		// 校验验证码是否正确
		if (kaptchaReceived == null || !kaptchaReceived.equalsIgnoreCase(kaptchaExpected)) {
			HttpResponseKit.alertMessage(response, "验证码错误！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 查出真正的loginId
		AdminUserGroupBusiness ugBusiness = new AdminUserGroupBusiness();
		String province = ParamKit.getParameter(request, "province", "");
		String city = ParamKit.getParameter(request, "city", "");
		String region = ParamKit.getParameter(request, "region", "");
		if (!StringKit.isValid(province) && !StringKit.isValid(city) && !StringKit.isValid(region)) {
			HttpResponseKit.alertMessage(response, "请选择所属地区！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		AdminUserGroupBean userGroupBean = null;
		if (province.equals(DeclareAdminConfig.COUNTRY_STR)) {
			// 国家级为系统初始化数据
			userGroupBean = ugBusiness.getAdminUserGroupByKey(1);
		} else {
			userGroupBean = ugBusiness.getAdminUserGroupByKey(province, city, region);
		}
		if (userGroupBean == null) {
			HttpResponseKit.alertMessage(response, "所属地区行政机构不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		loginId = userGroupBean.getGroupId() + DeclareAdminConfig.ADMIN_LOGIN_SEPARATOR + loginId;
		AdminUserInfoBusiness business = new AdminUserInfoBusiness();
		AdminUserInfoBean bean = business.isValidAdminUser(loginId, loginPassword);
		if (bean != null) {
			// 记录Cookies
			String cookieStr = AdminLogonUtil.encryptLogonSign(bean.getUserId());
			CookiesKit.setCookie(response, DeclareAdminConfig.ADMIN_USER_KEY, cookieStr,
					DeclareAdminConfig.ADMIN_DOMAIN);
			// 跳转
			String returnUrl = ParamKit.getParameter(request, "returnUrl");
			if (StringKit.isValid(returnUrl)) {
				response.sendRedirect(returnUrl);
			} else {
				response.sendRedirect("/declare/admin/Index.do");
			}
		} else {
			HttpResponseKit.alertMessage(response, "用户ID或密码错误！", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}
	
}
