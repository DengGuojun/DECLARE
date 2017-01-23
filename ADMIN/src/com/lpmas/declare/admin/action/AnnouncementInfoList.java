package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminRoleUserBean;
import com.lpmas.declare.admin.bean.AnnouncementInfoBean;
import com.lpmas.declare.admin.business.AdminRoleUserBusiness;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AnnouncementInfoBusiness;
import com.lpmas.declare.admin.config.AnnouncementInfoConfig;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class AnnouncementInfoList
 */
@WebServlet("/declare/admin/AnnouncementInfoList.do")
public class AnnouncementInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnnouncementInfoList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.SEARCH)) {
			return;
		}
		String owner = ParamKit.getParameter(request, "owner", "");
		int pageNum = ParamKit.getIntParameter(request, "pageNum", 1);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", 20);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		
		// 处理查询条件
		HashMap<String, String> condMap = new HashMap<String, String>();
		if (StringKit.isValid(owner)) {
			condMap.put("createUser", String.valueOf(adminHelper.getAdminUserId()));
		} else {
			AdminRoleUserBusiness roleUserBusiness = new AdminRoleUserBusiness();
			List<AdminRoleUserBean> roleUserBean = roleUserBusiness.getAdminRoleUserListByUserId(
					adminHelper.getAdminUserId(), Constants.STATUS_VALID);
			condMap.put("receiver", String.valueOf(roleUserBean.get(0).getRoleId()));
			condMap.put("announcementStatus", AnnouncementInfoConfig.ANNOUNCEMENT_STATUS_PUBLISHED);
		}
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));

		AnnouncementInfoBusiness business = new AnnouncementInfoBusiness();
		PageResultBean<AnnouncementInfoBean> result = business.getAnnouncementInfoPageListByMap(condMap, pageBean);
		request.setAttribute("AnnouncementList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("AdminUserHelper", adminHelper);
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));

		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "AnnouncementInfoList.jsp");
		rd.forward(request, response);
	}

}
