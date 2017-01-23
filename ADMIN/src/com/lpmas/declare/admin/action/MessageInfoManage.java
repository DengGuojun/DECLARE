package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.MessageInfoBean;
import com.lpmas.declare.admin.bean.MessageReceiverBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.business.MessageInfoBusiness;
import com.lpmas.declare.admin.business.MessageReceiverBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class MessageInfoManage
 */
@WebServlet("/declare/admin/MessageInfoManage.do")
public class MessageInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(MessageInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		MessageInfoBean bean = new MessageInfoBean();
		bean.setSenderId(adminHelper.getAdminUserId());
		bean.setStatus(Constants.STATUS_VALID);
		request.setAttribute("MessageInfo", bean);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "MessageInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int userId = adminHelper.getAdminUserId();
		MessageInfoBean bean = new MessageInfoBean();
		MessageInfoBusiness business = new MessageInfoBusiness();
		MessageReceiverBusiness receiverBusiness = new MessageReceiverBusiness();
		AdminUserInfoBusiness userInfoBusiness = new AdminUserInfoBusiness();
		try {
			bean = BeanKit.request2Bean(request, MessageInfoBean.class);

			List<MessageReceiverBean> receiverList = new ArrayList<MessageReceiverBean>();
			String[] receiverName = bean.getReceiverList().split(",");
			bean.setReceiverList("");// 重新初始化接收者名单，把不存在的过滤掉
			for (String name : receiverName) {
				AdminUserInfoBean userInfoBean = userInfoBusiness.getAdminUserInfoByName(name);
				if (userInfoBean != null) {
					MessageReceiverBean receiverBean = new MessageReceiverBean();
					receiverBean.setMessageTitle(bean.getTitle());
					receiverBean.setReceiverId(userInfoBean.getUserId());
					receiverBean.setIsRead(Constants.STATUS_NOT_VALID);
					receiverBean.setStatus(Constants.STATUS_VALID);
					receiverBean.setCreateUser(userId);
					receiverList.add(receiverBean);
					bean.setReceiverList(bean.getReceiverList() + "," + name);
				}
			}
			if(!StringKit.isValid(bean.getReceiverList())){
				HttpResponseKit.alertMessage(response, "接收者不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			bean.setReceiverList(bean.getReceiverList().substring(1));// 把开头的","去掉
			bean.setCreateUser(userId);
			int result = business.addMessageInfo(bean);
			if (result > 0) {
				bean.setMessageId(result);
				//保存接收者数据
				for (MessageReceiverBean receiverBean : receiverList) {
					receiverBean.setMessageId(bean.getMessageId());
					receiverBusiness.addMessageReceiver(receiverBean);
				}
				// 处理附件
				FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
				String fileIds = ParamKit.getParameter(request, "fileId");
				String files[] = fileIds.split(",");
				for (String fileId : files) {
					if (StringKit.isValid(fileId.trim())) {
						FileInfoBean fileInfoBean = fileInfoBusiness.getFileInfoByKey(Integer.valueOf(fileId.trim()));
						fileInfoBean.setInfoId(bean.getMessageId());
						fileInfoBusiness.updateFileInfo(fileInfoBean);
					}
				}
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/MessageInfoSendList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("{}", e.toString());
		}
	}
}
