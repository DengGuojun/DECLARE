package com.lpmas.declare.console.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.admin.business.AdminUserHelper;
import com.lpmas.admin.config.OperationConfig;
import com.lpmas.declare.bean.NationalCertificationBean;
import com.lpmas.declare.config.DeclareInfoResource;
import com.lpmas.declare.console.business.NationalCertificationBusiness;
import com.lpmas.declare.console.config.DeclareConsoleConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/NationalCertificationList.do")
public class NationalCertificationList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NationalCertificationList() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(DeclareInfoResource.DECLARE_INFO, OperationConfig.SEARCH)) {
			return;
		}
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareConsoleConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareConsoleConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		HashMap<String, String> condMap = new HashMap<String, String>();
		String certificateName = ParamKit.getParameter(request, "certificateName", "").trim();
		if (StringKit.isValid(certificateName)) {
			condMap.put("certificateName", certificateName);
		}
		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID)).trim();
		if (StringKit.isValid(status)) {
			condMap.put("status", status);
		}
		NationalCertificationBusiness nationalCertificationBusiness = new NationalCertificationBusiness();
		PageResultBean<NationalCertificationBean> result = nationalCertificationBusiness
				.getNationalCertificationPageListByMap(condMap, pageBean);
		request.setAttribute("NationalCertificationList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareConsoleConfig.PAGE_PATH + "NationalCertificationList.jsp");
		rd.forward(request, response);
	}

}
