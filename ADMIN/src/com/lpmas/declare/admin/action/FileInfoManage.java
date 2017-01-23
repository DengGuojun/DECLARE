package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.transfer.FileUploadResultBean.FileUploadItem;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/FileInfoManage.do")
public class FileInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(FileInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileInfoManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(DeclareAdminResource.FILE_INFO, OperationConfig.CREATE)) {
			return;
		}

		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "FileInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		if (!adminUserHelper.checkPermission(DeclareAdminResource.FILE_INFO, OperationConfig.CREATE)) {
			return;
		}
		String fileTitle = ParamKit.getParameter(request, "fileTitle", "");
		try {
			FileUploadKit fileUploadKit = new FileUploadKit();
			String fileName = UuidKit.getUuid();
			String uploadPath = FileInfoConfig.ADMIN_FILE_PATH;
			fileUploadKit.setAllowedFileType(FileInfoConfig.ALLOWED_FILE_TYPE);// 设置允许上传的文件类型
			fileUploadKit.setMaxSize(FileInfoConfig.MAX_SIZE);
			FileUploadResultBean resultBean = fileUploadKit.fileUpload(request, "file", uploadPath, fileName);
			// 获取上传结果
			if (resultBean.getResult()) {
				List<FileUploadItem> list = resultBean.getFileItemList();
				for (FileUploadItem item : list) {
					if (item.getResult()) {
						FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
						FileInfoBean bean = new FileInfoBean();
						bean.setInfoType(FileInfoConfig.INFO_TYPE_COMMON);
						bean.setFileName(item.getFileName().substring(0, item.getFileName().lastIndexOf(".")));
						if(!StringKit.isValid(fileTitle)){
							fileTitle = bean.getFileName();
						}
						bean.setFileTitle(fileTitle);
						bean.setFileFormat(item.getExtensionFileName());
						bean.setFilePath(fileName + "." + item.getExtensionFileName());
						bean.setCreateUser(adminUserHelper.getAdminUserId());
						bean.setStatus(Constants.STATUS_VALID);
						fileInfoBusiness.addFileInfo(bean);
					} else {
						HttpResponseKit.alertMessage(response, item.getResultContent(), HttpResponseKit.ACTION_HISTORY_BACK);
						return;
					}
				}
			} else {
				HttpResponseKit.alertMessage(response, resultBean.getResultContent(), HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} catch (Exception e) {
			log.error("", e);
		}
		HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/FileInfoList.do");

	}
}