package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AnnouncementInfoBean;
import com.lpmas.declare.admin.business.AnnouncementInfoBusiness;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.transfer.FileUploadResultBean.FileUploadItem;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * 
 * @author 潘宜杰
 * @see 将WebFile的excel数据转移到file_info表上
 */
@WebServlet("/declare/migrate/FileInfoImport.do")
public class FileInfoImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(FileInfoImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileInfoImport() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/declare/migrate/DataMigrationImport.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int result = 0;
		List<String> message = new ArrayList<String>();
		ReturnMessageBean returnMessage = new ReturnMessageBean();

		try {
			FileUploadKit fileUploadKit = new FileUploadKit();
			String fileName = UuidKit.getUuid();
			String filePath = DateKit.getCurrentDateTime("yyyy" + "_" + "MM" + "_" + "dd");
			String uploadPath = FileInfoConfig.ADMIN_FILE_PATH + filePath;
			fileUploadKit.setAllowedFileType(FileInfoConfig.ALLOWED_FILE_TYPE);// 设置允许上传的文件类型
			fileUploadKit.setMaxSize(FileInfoConfig.MAX_SIZE);
			FileUploadResultBean resultBean = fileUploadKit.fileUpload(request, "file", uploadPath, fileName);
			String extensionFileName = null;
			// 获取上传结果
			if (resultBean.getResult()) {
				List<FileUploadItem> list = resultBean.getFileItemList();
				for (FileUploadItem item : list) {
					if (item.getResult()) {
						extensionFileName = item.getExtensionFileName();
					} else {
						returnMessage.setMessage(item.getResultContent());
						message.add(returnMessage.getMessage());
					}
				}
			} else {
				returnMessage.setMessage(resultBean.getResultContent());
				message.add(returnMessage.getMessage());
			}
			// 读取excel表
			ExcelReadKit excelReadKit = new ExcelReadKit();
			ExcelReadResultBean excelReadResultBean = null;

			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0); // WebFile
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();

				FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
				FileInfoBean fileInfoBean = null;

				AnnouncementInfoBusiness announcementInfoBusiness = new AnnouncementInfoBusiness();
				HashMap<String, String> condMap = new HashMap<>();
				List<AnnouncementInfoBean> announcementInfoList = announcementInfoBusiness
						.getAnnouncementInfoListByMap(condMap);
				Map<String, Integer> announcementInfoMapSite = new HashMap<>();
				for (AnnouncementInfoBean bean : announcementInfoList) {
					announcementInfoMapSite.put(bean.getMemo(), bean.getAnnouncementId());
				}

				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						fileInfoBean = new FileInfoBean();
						if (content.get(1).equals("WebInfo")) { // 公告文件的转移
							fileInfoBean.setInfoType(2); // 信息类型

							if (announcementInfoMapSite.containsKey(content.get(2))) {
								fileInfoBean.setInfoId(announcementInfoMapSite.get(content.get(2)));
							}

							String oldFileName = content.get(3); // 文件名称（原名）

							if (oldFileName.contains(".")) {
								fileInfoBean.setFileName(oldFileName.substring(0, oldFileName.lastIndexOf(".")));
								fileInfoBean.setFileFormat(
										oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length())); // 文件格式
							} else {
								fileInfoBean.setFileName(oldFileName.substring(0, oldFileName.length()));
							}
							fileInfoBean.setFilePath(content.get(4)); // 文件路径（带有AttachMail文件夹）
							fileInfoBean.setStatus(Constants.STATUS_VALID); // 状态
							fileInfoBean.setCreateUser(2); // 创建用户
						} else {
							// 非公告文件
							fileInfoBean.setInfoType(1);
						}
						fileInfoBean.setMemo(content.get(0));

						if (fileInfoBean.getInfoType() != 1 && fileInfoBusiness.addFileInfo(fileInfoBean) > 0) {
							++result;
						} else {
							message.add(content.get(3) + " 导入失败;");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("一共导入 " + result + " 条数据。");
		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/migrate/DataMigrationImport.do");
		} else {
			HttpResponseKit.alertMessage(response, ListKit.list2String(message, ""),
					"/declare/migrate/DataMigrationImport.do");
		}
	}
}
