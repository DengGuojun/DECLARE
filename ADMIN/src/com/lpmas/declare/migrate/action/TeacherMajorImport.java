package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.business.TeacherMajorInfoBusiness;
import com.lpmas.declare.admin.business.TeacherMajorTypeBusiness;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.bean.TeacherMajorInfoBean;
import com.lpmas.declare.bean.TeacherMajorTypeBean;
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

@WebServlet("/declare/migrate/TeacherMajorImport.do")
public class TeacherMajorImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TeacherMajorImport.class);
	private static final long serialVersionUID = 1L;

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
			ExcelReadResultBean excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0);
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				TeacherMajorTypeBusiness majorTypeBusiness = new TeacherMajorTypeBusiness();
				TeacherMajorTypeBean TeacherMajorTypeBean = null;
				TeacherMajorInfoBusiness majorInfoBusiness = new TeacherMajorInfoBusiness();
				TeacherMajorInfoBean bean = null;
				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						if (content.get(1).equals("0")) {
							TeacherMajorTypeBean = new TeacherMajorTypeBean();
							TeacherMajorTypeBean.setMajorName(content.get(2));
							TeacherMajorTypeBean.setStatus(Integer.valueOf(content.get(4)));
							TeacherMajorTypeBean.setMemo(content.get(0));
							result = majorTypeBusiness.addMajorType(TeacherMajorTypeBean);
						} else {
							bean = new TeacherMajorInfoBean();
							bean.setMajorName(content.get(2));
							bean.setStatus(Integer.valueOf(content.get(4)));
							bean.setMemo(content.get(0));
							// 找父类型
							HashMap<String, String> condMap = new HashMap<String, String>();
							condMap.put("memo", content.get(1));
							List<TeacherMajorTypeBean> majorTypeList = majorTypeBusiness.getTeacherMajorTypeListByMap(condMap);
							if (!majorTypeList.isEmpty() && majorTypeList.size() > 0) {
								bean.setTypeId(majorTypeList.get(0).getMajorId());
							} else {
								List<TeacherMajorInfoBean> majorInfoList = majorInfoBusiness.getMajorInfoListByMap(condMap);
								if (!majorInfoList.isEmpty() && majorInfoList.size() > 0) {
									bean.setTypeId(majorInfoList.get(0).getTypeId());
								}
							}
							result = majorInfoBusiness.addMajorInfo(bean);
						}
						if (result < 0) {
							message.add(content.get(2) + "导入失败;");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
		}
		if (result < 0) {
			HttpResponseKit.alertMessage(response, ListKit.list2String(message, " "),
					"/declare/migrate/DataMigrationImport.do");
		} else {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/migrate/DataMigrationImport.do");
		}
	}

}
