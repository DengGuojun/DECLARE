package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.MessageReceiverBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.business.MessageReceiverBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class MessageInfoReceiveList
 */
@WebServlet("/declare/admin/MessageInfoReceiveList.do")
public class MessageInfoReceiveList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageInfoReceiveList() {
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
		MessageReceiverBusiness receiverBusiness = new MessageReceiverBusiness();
		int pageNum = ParamKit.getIntParameter(request, "pageNum", 1);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", 20);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		HashMap<String, String> condMap = ParamKit.getParameterMap(request, "createTimeFrom,createTimeTo,messageTitle", "");
		condMap.put("receiverId", String.valueOf(adminHelper.getAdminUserId()));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		// 查找筛选条件“发送人”
		AdminUserInfoBusiness userInfoBusiness = new AdminUserInfoBusiness();
		String sender = ParamKit.getParameter(request, "sender", "");
		if(StringKit.isValid(sender)){
			AdminUserInfoBean userInfoBean = userInfoBusiness.getAdminUserInfoByName(sender);
			if (userInfoBean != null) {
				condMap.put("senderId", String.valueOf(userInfoBean.getUserId()));
			}else{
				condMap.put("senderId", "0");
			}
		}
		

		PageResultBean<MessageReceiverBean> result = receiverBusiness
				.getMessageReceiverPageListByMap(condMap, pageBean);

		// 获取邮件发件人姓名
		Map<Integer, String> messageCreateUserMap = new HashMap<Integer, String>();
		for (MessageReceiverBean bean : result.getRecordList()) {
			AdminUserInfoBean senderUserInfoBean = userInfoBusiness.getAdminUserInfoByKey(bean.getCreateUser());
			messageCreateUserMap.put(bean.getCreateUser(), senderUserInfoBean.getAdminUserName());
		}

		request.setAttribute("MessageCreateUserMap", messageCreateUserMap);
		request.setAttribute("MessageReceiverList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("AdminUserHelper", adminHelper);
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));

		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "MessageInfoReceiveList.jsp");
		rd.forward(request, response);
	}

}
