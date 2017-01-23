package com.lpmas.declare.portal.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.declare.portal.business.FileInfoBusiness;
import com.lpmas.declare.portal.config.FileInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.transfer.FileUploadResultBean.FileUploadItem;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

@WebServlet("/declare/FileInfoUpload.do")
public class FileInfoUpload extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(FileInfoUpload.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileInfoUpload() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		List<Integer> resultList = new ArrayList<Integer>();
		ReturnMessageBean returnMessage = new ReturnMessageBean();
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
						int infoType = ParamKit.getIntParameter(request, "infoType", 0);
						int infoId = ParamKit.getIntParameter(request, "infoId", 0);
						bean.setInfoType(infoType);
						bean.setInfoId(infoId);
						bean.setFileName(item.getFileName().substring(0, item.getFileName().lastIndexOf(".")));
						bean.setFileFormat(item.getExtensionFileName());
						bean.setFilePath(fileName + "." + item.getExtensionFileName());
						bean.setStatus(Constants.STATUS_VALID);
						int fileId = fileInfoBusiness.addFileInfo(bean);
						resultList.add(fileId);
					} else {
						returnMessage.setCode(Constants.STATUS_NOT_VALID);
						returnMessage.setMessage(item.getResultContent());
						HttpResponseKit.printJson(request, response, returnMessage, "");
						return;
					}
				}
				returnMessage.setCode(Constants.STATUS_VALID);
				returnMessage.setMessage(JsonKit.toJson(resultList));
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			} else {
				returnMessage.setCode(Constants.STATUS_NOT_VALID);
				returnMessage.setMessage(resultBean.getResultContent());
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			}
		} catch (Exception e) {
			log.error("", e);
		}

	}
}