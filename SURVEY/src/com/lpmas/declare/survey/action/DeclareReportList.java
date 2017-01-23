package com.lpmas.declare.survey.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.survey.business.DeclareReportBusiness;
import com.lpmas.declare.survey.config.DeclareSurveyConfig;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.CookiesKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/DeclareReportList.do")
public class DeclareReportList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareReportList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginKey = CookiesKit.getCookies(request, DeclareSurveyConfig.ADMIN_LOGIN_KEY);
		if (!DeclareSurveyConfig.ADMIN_LOGIN_ID.equals(loginKey)) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", "/declare/AdminLogon.do");
			return;
		}
		// 初始化页面分页参数
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareSurveyConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareSurveyConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		HashMap<String, String> condMap = new HashMap<String, String>();
		String userName = ParamKit.getParameter(request, "userName", "").trim();
		if (StringKit.isValid(userName)) {
			condMap.put("userName", userName);
		}
		String declareStatus = ParamKit.getParameter(request, "declareStatus", "").trim();
		if (StringKit.isValid(declareStatus)) {
			condMap.put("declareStatus", declareStatus);
		}
		// 从Mongo中获取相应的数据
		DeclareReportBusiness business = new DeclareReportBusiness();
		HashMap<String, List<String>> scopeMap = new HashMap<String, List<String>>();
		try {
			List<String> reviewStatusList = new ArrayList<String>();
			reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_SUBMIT);
			reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_APPROVE);
			reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE);
			scopeMap.put("declareStatusList", reviewStatusList);

			PageResultBean<DeclareReportBean> result = business.getDeclareReportPageListByMap(condMap, scopeMap,
					pageBean);

			request.setAttribute("DeclareReportList", result.getRecordList());
			// 初始化分页数据
			pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
			request.setAttribute("PageResult", pageBean);
			request.setAttribute("CondList", MapKit.map2List(condMap));
		} catch (Exception e) {
			e.printStackTrace();
		}

		PortalKit.forwardPage(request, response, DeclareSurveyConfig.PAGE_PATH + "DeclareReportList.jsp");

	}

}
