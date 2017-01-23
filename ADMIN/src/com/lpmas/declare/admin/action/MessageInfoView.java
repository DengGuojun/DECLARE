package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.MessageInfoBean;
import com.lpmas.declare.admin.bean.MessageReceiverBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.business.MessageInfoBusiness;
import com.lpmas.declare.admin.business.MessageReceiverBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class MessageInfoView
 */
@WebServlet("/declare/admin/MessageInfoView.do")
public class MessageInfoView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageInfoView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int messageId = ParamKit.getIntParameter(request, "messageId", 0);
		MessageInfoBusiness business = new MessageInfoBusiness();
		MessageInfoBean bean = business.getMessageInfoByKey(messageId);
		if (bean == null) {
			HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		MessageReceiverBusiness receiverBusiness = new MessageReceiverBusiness();
		MessageReceiverBean receiverBean = receiverBusiness.getMessageReceiverByKey(messageId,
				adminHelper.getAdminUserId());
		if (receiverBean == null && bean.getSenderId() != adminHelper.getAdminUserId()) {
			HttpResponseKit.alertMessage(response, "你没有权限查看此邮件", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 获取发件人姓名
		AdminUserInfoBusiness userInfoBusiness = new AdminUserInfoBusiness();
		AdminUserInfoBean senderUserInfoBean = userInfoBusiness.getAdminUserInfoByKey(bean.getCreateUser());
		// 修改邮件状态为已读
		if (receiverBean != null && receiverBean.getIsRead() == 0) {
			receiverBean.setIsRead(1);
			receiverBean.setModifyUser(adminHelper.getAdminUserId());
			receiverBusiness.updateMessageReceiver(receiverBean);
		}

		// 获取附件信息
		List<FileInfoBean> attachList = new ArrayList<FileInfoBean>();
		FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("infoType", String.valueOf(FileInfoConfig.INFO_TYPE_MESSAGE_ATTACH));
		condMap.put("infoId", String.valueOf(bean.getMessageId()));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		attachList = fileInfoBusiness.getFileInfoListByMap(condMap);

		request.setAttribute("MessageInfo", bean);
		request.setAttribute("SenderName", senderUserInfoBean.getAdminUserName());
		request.setAttribute("AttachList", attachList);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "MessageInfoView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		this.doGet(request, response);
	}

}
