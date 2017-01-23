package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AnnouncementInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AnnouncementInfoBusiness;
import com.lpmas.declare.admin.config.AnnouncementInfoConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class AnnouncementInfoPublish
 */
@WebServlet("/declare/admin/AnnouncementInfoPublish.do")
public class AnnouncementInfoPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnnouncementInfoPublish() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.PUBLISH)) {
			return;
		}
		int announcementId = ParamKit.getIntParameter(request, "announcementId", 0);
		AnnouncementInfoBusiness business = new AnnouncementInfoBusiness();
		AnnouncementInfoBean bean = business.getAnnouncementInfoByKey(announcementId);
		if (bean == null) {
			HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} 
		if(bean.getCreateUser() != adminHelper.getAdminUserId()){
			HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		bean.setAnnouncementStatus(AnnouncementInfoConfig.ANNOUNCEMENT_STATUS_PUBLISHED);
		int result = business.updateAnnouncementInfo(bean);
		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/AnnouncementInfoList.do?owner=true");
		} else {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
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
