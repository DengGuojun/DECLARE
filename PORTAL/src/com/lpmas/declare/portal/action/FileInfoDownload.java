package com.lpmas.declare.portal.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/FileInfoDownload.do")
public class FileInfoDownload extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(FileInfoDownload.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileInfoDownload() {
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
		
		// 设置文件头：最后一个参数是设置下载文件名
		response.setHeader(
				"Content-Disposition",
				"attachment;fileName=" + new String(bean.getFileName().getBytes("gb2312"), "ISO8859-1") + "."
						+ bean.getFileFormat());
		ServletOutputStream out;
		// 通过文件路径获得File对象
		File file = new File(FileInfoConfig.ADMIN_FILE_PATH + bean.getFilePath());
		// 设置文件ContentType类型
		response.setContentType(new MimetypesFileTypeMap().getContentType(file));
		try {
			FileInputStream inputStream = new FileInputStream(file);
			out = response.getOutputStream();
			int b = 0;
			byte[] buffer = new byte[512];
			while (b != -1) {
				b = inputStream.read(buffer);
				out.write(buffer, 0, b);
			}
			inputStream.close();
			out.close();
			out.flush();
		} catch (IOException e) {
			log.error(e.getMessage());
		}

	}
}