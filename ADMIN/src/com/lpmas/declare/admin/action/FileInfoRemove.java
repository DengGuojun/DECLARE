package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class FileInfoRemove
 */
@WebServlet("/declare/admin/FileInfoRemove.do")
public class FileInfoRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileInfoRemove() {
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

		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(DeclareAdminResource.FILE_INFO, OperationConfig.REMOVE)) {
			return;
		}
		FileInfoBean bean = null;
		int fileId = ParamKit.getIntParameter(request, "fileId", 0);
		FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
		if (fileId > 0) {
			bean = fileInfoBusiness.getFileInfoByKey(fileId);
		}
		if (bean == null || bean.getStatus() == Constants.STATUS_NOT_VALID) {
			HttpResponseKit.alertMessage(response, "没有对应的文件", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		bean.setStatus(Constants.STATUS_NOT_VALID);
		int result = fileInfoBusiness.updateFileInfo(bean);
		// 删除原文件
		if (result > 0) {
			fileInfoBusiness.deleteFile(bean);
		}
		HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/FileInfoList.do");
	}
}
