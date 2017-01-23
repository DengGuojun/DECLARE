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

import com.lpmas.declare.admin.bean.AnnouncementInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AnnouncementInfoBusiness;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class AnnouncementInfoView
 */
@WebServlet("/declare/admin/AnnouncementInfoView.do")
public class AnnouncementInfoView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnnouncementInfoView() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.SEARCH)) {
			return;
		}
		int announcementId = ParamKit.getIntParameter(request, "announcementId", 0);
		AnnouncementInfoBusiness business = new AnnouncementInfoBusiness();
		AnnouncementInfoBean bean = business.getAnnouncementInfoByKey(announcementId);
		if (bean == null) {
			HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} 
		
		//获取附件信息
		List<FileInfoBean> attachList = new ArrayList<FileInfoBean>();
		FileInfoBusiness  fileInfoBusiness = new FileInfoBusiness();
		HashMap<String,String> condMap = new HashMap<String,String>();
		condMap.put("infoType", String.valueOf(FileInfoConfig.INFO_TYPE_ANNOUNCEMENT_ATTACH));
		condMap.put("infoId", String.valueOf(bean.getAnnouncementId()));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		attachList = fileInfoBusiness.getFileInfoListByMap(condMap);
		
		request.setAttribute("AnnouncementInfo", bean);
		request.setAttribute("AttachList", attachList);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "AnnouncementInfoView.jsp");
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
