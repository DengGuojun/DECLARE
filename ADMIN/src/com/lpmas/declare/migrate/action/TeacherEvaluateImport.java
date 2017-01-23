package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.sql.Timestamp;
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

import com.lpmas.declare.admin.bean.TeacherEvaluateBean;
import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.business.TeacherEvaluateBusiness;
import com.lpmas.declare.admin.business.TeacherInfoBusiness;
import com.lpmas.declare.admin.config.FileInfoConfig;
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

@WebServlet("/declare/migrate/TeacherEvaluateImport.do")
public class TeacherEvaluateImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TeacherEvaluateImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherEvaluateImport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/declare/migrate/DataMigrationImport.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		boolean flag = false;
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
			ExcelReadResultBean excelReadResultBean = excelReadKit
					.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath + Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0);
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				TeacherEvaluateBusiness teacherEvaluateBusiness = new TeacherEvaluateBusiness();
				TeacherEvaluateBean teacherEvaluateBean = null;
				TeacherInfoBusiness teacherInfoBusiness = new TeacherInfoBusiness();
				HashMap<String, String> condMap = null;
				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						teacherEvaluateBean = new TeacherEvaluateBean();
						teacherEvaluateBean.setClassName(content.get(3));
						teacherEvaluateBean.setTrainingOrganization(content.get(4));
						condMap = new HashMap<String, String>();
						condMap.put("memo", content.get(2));
						List<TeacherInfoBean> teacherInfoList = teacherInfoBusiness.getTeacherInfoListByMap(condMap);
						if (!teacherInfoList.isEmpty() && teacherInfoList.size() > 0) {
							teacherEvaluateBean.setTeacherId(teacherInfoList.get(0).getTeacherId());
						} else {
							continue;
						}
						teacherEvaluateBean.setEvaluateScore(Double.valueOf(content.get(5)));
						teacherEvaluateBean.setTrainingAddress(content.get(6));
						teacherEvaluateBean.setTeacherComment(content.get(7));
						Timestamp createTime = new Timestamp(DateKit.str2Date(content.get(8).split("[.]")[0], "MM/dd/yyyy HH:mm:ss").getTime());
						teacherEvaluateBean.setCreateTime(createTime);
						teacherEvaluateBean.setTrainingTime(content.get(8).split(" ")[0]);
						teacherEvaluateBean.setStatus(Constants.STATUS_VALID);
						result = teacherEvaluateBusiness.addTeacherEvaluateWithCreateTime(teacherEvaluateBean);
						if (result < 0) {
							message.add(content.get(1) + "导入失败;");
							flag = true;
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
		}
		if (flag) {
			HttpResponseKit.alertMessage(response, ListKit.list2String(message, " "), "/declare/migrate/DataMigrationImport.do");
		} else {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/migrate/DataMigrationImport.do");
		}
	}

}
