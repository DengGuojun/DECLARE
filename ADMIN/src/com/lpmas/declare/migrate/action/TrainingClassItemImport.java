package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassItemBusiness;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassItemBean;
import com.lpmas.declare.migrate.util.ExcelStrHandle;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.transfer.FileUploadResultBean.FileUploadItem;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * 
 * @author 潘宜杰
 * @see 将CourseArrange的excel数据转移到training_class_item表上
 */
@WebServlet("/declare/migrate/TrainingClassItemImport.do")
public class TrainingClassItemImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TrainingClassItemImport.class);
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
		long startTime1 = System.currentTimeMillis(); // 获取开始时间
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
			System.out.println("文件上传:" + resultBean.getResult());
			// 读取excel表
			ExcelReadKit excelReadKit = new ExcelReadKit();
			ExcelReadResultBean excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0);
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				TrainingClassItemBusiness classItemBusiness = new TrainingClassItemBusiness();
				TrainingClassItemBean classItemBean = null;

				TrainingClassInfoBusiness classInfoBusiness = new TrainingClassInfoBusiness();
				HashMap<String, String> condMap = new HashMap<>();
				List<TrainingClassInfoBean> classInfoList = classInfoBusiness.getTrainingClassInfoListByMap(condMap);

				Map<String, Integer> classInfoMap = new HashMap<>();
				for (TrainingClassInfoBean bean : classInfoList) {
					classInfoMap.put(bean.getMemo(), bean.getClassId());
				}

				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						classItemBean = new TrainingClassItemBean();
						classItemBean.setItemName(content.get(2)); // 培训班名称
						if (!classInfoMap.containsKey(content.get(1))) {
							log.error("培训班不存在" + content.get(1) + content.get(2));
							continue;
						}
						classItemBean.setClassId(classInfoMap.get(content.get(1))); // 培训班ID
						if (content.get(3).length() < 10) {
							classItemBean.setClassHour(ExcelStrHandle.numStrFormat(content.get(3))); // 学时
						}
						classItemBean.setTrainingMaterial(content.get(4)); // 培训教材
						classItemBean.setTrainingTeacher(content.get(5)); // 培训师资
						classItemBean.setClassType(MapKit.getValueFromMap(ExcelStrHandle.numStrFormat(content.get(6)),
								com.lpmas.declare.migrate.config.TrainingClassItemConfig.COURSE_TYPE_MAP)); // 课程类型
						classItemBean.setIsRequiredCourse(
								(ExcelStrHandle.doubleStr2int(content.get(6)) > 2) ? Constants.STATUS_VALID
										: Constants.STATUS_NOT_VALID); // 是否必修课
						classItemBean.setStatus(Constants.STATUS_VALID); // 状态
						Timestamp createTime = new Timestamp(new Date().getTime());
						try {
							createTime = new Timestamp(
									DateKit.str2Date(content.get(9).split("[.]")[0], "MM/dd/yyyy HH:mm:ss").getTime());
						} catch (Exception e) {
							createTime = new Timestamp(
									DateKit.str2Date(content.get(9).split("[.]")[0], "yyyy-MM-dd HH:mm:ss").getTime());
						}
						classItemBean.setCreateTime(createTime);
						if (classItemBusiness.addTrainingClassItemWithCreateTime(classItemBean) > 0) {
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
		long endTime1 = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间： " + (endTime1 - startTime1) / 1000 + "s");
		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/migrate/DataMigrationImport.do");
		} else {
			HttpResponseKit.alertMessage(response, ListKit.list2String(message, ""),
					"/declare/migrate/DataMigrationImport.do");
		}
	}
}
