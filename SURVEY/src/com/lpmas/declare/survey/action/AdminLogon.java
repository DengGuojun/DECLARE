package com.lpmas.declare.survey.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.survey.config.DeclareSurveyConfig;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.CookiesKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class AdminLogon
 */
@WebServlet("/declare/AdminLogon.do")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PortalKit.forwardPage(request, response, DeclareSurveyConfig.PAGE_PATH + "AdminLogon.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		if (loginId.equals(DeclareSurveyConfig.ADMIN_LOGIN_ID)
				&& loginPassword.equals(DeclareSurveyConfig.ADMIN_LOGIN_PASSWORD)) {
			// 记录Cookies
			CookiesKit.setCookie(response, DeclareSurveyConfig.ADMIN_LOGIN_KEY, loginId,
					DeclareSurveyConfig.ADMIN_DOMAIN);
			CookiesKit.setCookie(response, DeclareSurveyConfig.ADMIN_LOGIN_KEY, loginId, DeclareSurveyConfig.ADMIN_DOMAIN, "/", 30000);
			// 跳转
			response.sendRedirect("/declare/DeclareReportList.do");
		} else {
			HttpResponseKit.alertMessage(response, "用户ID或密码错误！", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}

}
