package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.MajorTypeBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.MajorTypeBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;



/**
 * Servlet implementation class MajorTypeList
 */
@WebServlet("/declare/admin/MajorTypeList.do")
public class MajorTypeList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MajorTypeList() {
		super();
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
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.MAJOR_TYPE, OperationConfig.SEARCH)) {
			return;
		}
		// 分页信息
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		// 查询条件参数
		HashMap<String, String> condMap = new HashMap<String, String>();

		String typeName = ParamKit.getParameter(request, "majorName", "").trim();
		if (StringKit.isValid(typeName)) {
			condMap.put("majorName", typeName);
		}

		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID)).trim();
		if (StringKit.isValid(status)) {
			condMap.put("status", status);
		}
		// 初始化业务类
		MajorTypeBusiness business = new MajorTypeBusiness();
		PageResultBean<MajorTypeBean> result = business.getMajorTypePageListByMap(condMap, pageBean);

		// 页面数据绑定
		request.setAttribute("MajorTypeList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("adminUserHelper", adminHelper);
		// 页面转发
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "MajorTypeList.jsp");
		rd.forward(request, response);
	}

}
