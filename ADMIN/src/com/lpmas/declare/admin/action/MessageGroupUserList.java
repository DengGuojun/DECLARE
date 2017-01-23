package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.MessageGroupUserBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.business.MessageGroupUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class MessageGroupUserList
 */
@WebServlet("/declare/admin/MessageGroupUserList.do")
public class MessageGroupUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageGroupUserList() {
		super();
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
		int groupId = ParamKit.getIntParameter(request, "groupId", 0);
		int pageNum = ParamKit.getIntParameter(request, "pageNum", 1);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", 20);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		// 处理查询条件
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, "adminUserDepartment,adminUserTelephone");
		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("status", status);
		condMap.put("adminUserType", String.valueOf(DeclareAdminConfig.ADMIN_TYPE_COMMON));
		String directUnder = ParamKit.getParameter(request, "directUnder", "");
		if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "region"))) {
			condMap.put("adminUserLevel", DeclareAdminConfig.ADMIN_LEVEL_REGION);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "city"))) {
			condMap.put("adminUserLevel", DeclareAdminConfig.ADMIN_LEVEL_CITY);
		} else if (StringKit.isValid(directUnder) && StringKit.isValid(ParamKit.getParameter(request, "province"))) {
			condMap.put("adminUserLevel", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
		}
		String adminUserName = ParamKit.getParameter(request, "adminUserName", "");
		if (StringKit.isValid(adminUserName)) {
			condMap.put("adminUserName", adminUserName);
		} else {
			String pronivce = ParamKit.getParameter(request, "province", "");
			String city = ParamKit.getParameter(request, "city", "");
			String region = ParamKit.getParameter(request, "region", "");
			condMap.put("adminUserName", pronivce + city + region);
		}

		//分页获取所有用户
		AdminUserInfoBusiness userInfoBusiness = new AdminUserInfoBusiness();
		PageResultBean<AdminUserInfoBean> result = userInfoBusiness.getAdminUserInfoPageListByMap(condMap, pageBean);
		
		//获取当前通讯录的用户
		MessageGroupUserBusiness business = new MessageGroupUserBusiness();
		List<MessageGroupUserBean> messageGroupUserList =  business.getMessageGroupUserByGroupId(groupId);
		Map<String,MessageGroupUserBean> messageGroupUserMap = ListKit.list2Map(messageGroupUserList, "userId");

		request.setAttribute("UserList", result);
		request.setAttribute("MessageGroupUserMap", messageGroupUserMap);
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("GroupId", groupId);
		request.setAttribute("AdminUserHelper", adminHelper);
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));

		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "MessageGroupUserList.jsp");
		rd.forward(request, response);
	}

}
