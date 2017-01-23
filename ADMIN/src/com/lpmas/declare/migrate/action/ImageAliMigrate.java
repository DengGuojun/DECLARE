package com.lpmas.declare.migrate.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.config.DeclareImageInfoConfig;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.ExcelWriteKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.invoker.picture.bean.UploadInfoBean;
import com.lpmas.invoker.picture.bean.UploadResultBean;
import com.lpmas.invoker.picture.client.PictureUploadClient;
import com.lpmas.invoker.picture.client.impl.AliYunUploadClient;

/**
 * @author 潘宜杰
 * 
 *         将下载好的图片上传至阿里云，并存下关于他们对应关系的excel文件。目前只用了doGet方法，doPost方法日后完善。
 *
 */
@WebServlet("/declare/migrate/ImageAliMigrate.do")
public class ImageAliMigrate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageAliMigrate() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uploadPath = ParamKit.getParameter(request, "uploadPath");
		String downloadName = ParamKit.getParameter(request, "downloadName");
		String downloadPath = ParamKit.getParameter(request, "downloadPath");
		if (!StringKit.isValid(uploadPath)) {
			HttpResponseKit.alertMessage(response, "uploadPath参数缺失,无法上传", "/declare/migrate/DataMigrationImport.do");
			return;
		} else if (!StringKit.isValid(downloadName)) {
			HttpResponseKit.alertMessage(response, "downloadName参数缺失,无法上传", "/declare/migrate/DataMigrationImport.do");
			return;
		} else if (!StringKit.isValid(downloadPath)) {
			HttpResponseKit.alertMessage(response, "downloadPath参数缺失,无法上传", "/declare/migrate/DataMigrationImport.do");
			return;
		}

		UploadThread unUploadThread = new UploadThread(request, response, uploadPath, downloadName, downloadPath);
		new Thread(unUploadThread).start();

		HttpResponseKit.alertMessage(response, "上传已在后台进行,请耐心等待", "/declare/migrate/DataMigrationImport.do");
	}

}

class UploadThread implements Runnable {
	private static Logger log = LoggerFactory.getLogger(UploadThread.class);
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String uploadPath;
	private String downloadName;
	private String downloadPath;

	UploadThread(HttpServletRequest request, HttpServletResponse response, String uploadPath, String downloadName,
			String downloadPath) {
		this.request = request;
		this.response = response;
		this.uploadPath = uploadPath;
		this.downloadName = downloadName;
		this.downloadPath = downloadPath;
	}

	@Override
	public void run() {
		log.info("上传线程执行开始");
		ReturnMessageBean returnMessage = new ReturnMessageBean();
		UploadResultBean uploadResultBean = null;
		File root = new File(uploadPath);

		List<List<Object>> contentList = new ArrayList<>();
		List<Object> secondContentList = null;

		File[] files = root.listFiles();

		int fileNum = files.length;
		int totalNum = 0;
		int tmpPersent = 0;
		int totalPercent = 0;

		for (File file : files) {
			secondContentList = new ArrayList<>();
			String localFileName = file.getName();
			String subFixx = localFileName.substring(localFileName.lastIndexOf('.'), localFileName.length());

			String remoteFileName = "IMG_ONE_HEAD" + "_" + System.currentTimeMillis() + subFixx;
			try {
				Path path = Files.copy(Paths.get(file.getAbsolutePath()),
						Paths.get(file.getParent() + File.separator + remoteFileName),
						StandardCopyOption.REPLACE_EXISTING);

				String localFilePath = path.toString();
				// 检验图片保存结果
				if (new File(localFilePath).exists()) {
					// 上传到阿里云
					PictureUploadClient client = new AliYunUploadClient();
					UploadInfoBean uploadInfoBean = new UploadInfoBean();
					uploadInfoBean.setLocalFile(localFilePath);
					uploadInfoBean.setRemoteFilePath(DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
					// 应用目录
					uploadResultBean = client.upload(uploadInfoBean);
				} else {
					returnMessage.setCode(HttpServletResponse.SC_BAD_REQUEST);
					returnMessage.setMessage("文件上传失败");
					HttpResponseKit.printJson(request, response, returnMessage, "");
					return;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 判断上传是否成功
			if (uploadResultBean.isResult()) {
				// 成功
				returnMessage.setCode(HttpServletResponse.SC_OK);
				returnMessage.setMessage(uploadResultBean.getResultUrl());

				secondContentList.add(localFileName);
				secondContentList.add(remoteFileName);
				contentList.add(secondContentList);
			} else {
				// 不成功
				returnMessage.setCode(HttpServletResponse.SC_BAD_REQUEST);
				returnMessage.setMessage(uploadResultBean.getMessage());
			}
			++totalNum;

			tmpPersent = totalPercent;
			totalPercent = totalNum * 100 / fileNum;
			if (tmpPersent != totalPercent && totalPercent % 4 == 0) {
				log.info("目前的百分比为" + totalPercent);
			}
		}
		ExcelWriteKit excelWriteKit = new ExcelWriteKit();
		ExcelWriteBean excelWriteBean = new ExcelWriteBean();

		excelWriteBean.setFileName(downloadName);
		excelWriteBean.setContentList(contentList);

		excelWriteKit.outputExcel(excelWriteBean, downloadPath);
		log.info("上传线程执行结束");
	}

}
