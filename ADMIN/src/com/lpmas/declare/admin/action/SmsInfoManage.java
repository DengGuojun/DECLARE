package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.ccp.client.SmsMessageClient;
import com.lpmas.ccp.client.bean.SmsMessageRequestBean;
import com.lpmas.declare.admin.bean.SmsInfoBean;
import com.lpmas.declare.admin.bean.SmsUserBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.SmsInfoBusiness;
import com.lpmas.declare.admin.business.SmsUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class SmsInfoManage
 */
@WebServlet("/declare/admin/SmsInfoManage.do")
public class SmsInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(SmsInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SmsInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		SmsInfoBean bean = new SmsInfoBean();
		bean.setSenderId(adminHelper.getAdminUserId());
		bean.setStatus(Constants.STATUS_VALID);
		request.setAttribute("SmsInfo", bean);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "SmsInfoManage.jsp");
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
		SmsInfoBean bean = new SmsInfoBean();
		SmsInfoBusiness business = new SmsInfoBusiness();
		SmsUserBusiness smsUserBusiness = new SmsUserBusiness();
		try {
			String code = ParamKit.getParameter(request, "code", "");
			if(!code.equals(DeclareAdminConfig.SMS_CODE)){
				HttpResponseKit.alertMessage(response, "授权码错误", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			bean = BeanKit.request2Bean(request, SmsInfoBean.class);
			String[] receiverName = bean.getReceiverList().split(",");
			bean.setReceiverList("");// 重新初始化接收者名单，把不存在的过滤掉
			for (String name : receiverName) {
				SmsUserBean smsUserBean = smsUserBusiness.getSmsUserByName(name);
				if (smsUserBean != null) {
					bean.setReceiverList(bean.getReceiverList() + "," + smsUserBean.getSmsUserMobile());
				}
			}
			String mobileStr = ParamKit.getParameter(request, "mobile", "");
			String[] mobile = mobileStr.split(",");
			for (String str : mobile) {
				if (StringKit.isValid(str)) {
					bean.setReceiverList(bean.getReceiverList() + "," + str);
				}
			}
			if (!StringKit.isValid(bean.getReceiverList())) {
				HttpResponseKit.alertMessage(response, "接收者不能为空", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			bean.setReceiverList(bean.getReceiverList().substring(1));// 把开头的","去掉
			bean.setCreateUser(userId);
			int result = business.addSmsInfo(bean);
			if (result > 0) {
				// 发送短信
//				String[] toSendMobile = bean.getReceiverList().split(",");
//				for (String str : toSendMobile) {
					SmsMessageRequestBean smsRequestBean = new SmsMessageRequestBean();
					smsRequestBean.setAppCode(DeclareAdminConfig.APP_ID);
					smsRequestBean.setToMobileNumber(bean.getReceiverList());
					smsRequestBean.setContent("【新型职业农民培育】"+bean.getContent()+". 回复TD退订");

					SmsMessageClient client = new SmsMessageClient();
					try {
						client.send(smsRequestBean);
					} catch (Exception e) {
						e.printStackTrace();
					}
//				}
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/SmsInfoManage.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("{}", e.toString());
		}
	}
}
