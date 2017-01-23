package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.sql.Timestamp;
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

import com.lpmas.declare.admin.business.DeclareImageBusiness;
import com.lpmas.declare.admin.business.DeclareInfoBusiness;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.bean.DeclareImageBean;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.config.DeclareImageInfoConfig;
import com.lpmas.declare.migrate.bean.PersonInfoBean;
import com.lpmas.declare.migrate.bean.PersonInfoMajor1Bean;
import com.lpmas.declare.migrate.bean.PersonInfoMajor2Bean;
import com.lpmas.declare.migrate.business.PersonInfoBusiness;
import com.lpmas.declare.migrate.business.PersonInfoMajor1Business;
import com.lpmas.declare.migrate.business.PersonInfoMajor2Business;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.transfer.FileUploadResultBean.FileUploadItem;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * 
 * @author 潘宜杰
 * @see 将PersonInfo_major1和PersonInfo_major2的excel数据转移到declare_image表上
 */
@WebServlet("/declare/migrate/DeclareImageInfoImport.do")
public class DeclareImageInfoImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(DeclareImageInfoImport.class);
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
			ExcelReadResultBean excelReadResultBean = null;

			HashMap<String, String> condMap = new HashMap<>();
			Map<String, String> imgPathMap = new HashMap<>();

			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0); // headImg.xlsx
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						imgPathMap.put(content.get(0), content.get(1)); // imgFileName:newImgFileName
					}
				}
			}
			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 1); // headImg.xlsx
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						imgPathMap.put(content.get(0), content.get(1)); // imgFileName:newImgFileName
					}
				}
			}
			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 2); // headImg.xlsx
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						imgPathMap.put(content.get(0), content.get(1)); // imgFileName:newImgFileName
					}
				}
			}
			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 3); // headImg.xlsx
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						imgPathMap.put(content.get(0), content.get(1)); // imgFileName:newImgFileName
					}
				}
			}

			DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
			PersonInfoBusiness personInfoBusiness = new PersonInfoBusiness();
			PersonInfoMajor1Business major1Business = new PersonInfoMajor1Business();
			PersonInfoMajor2Business major2Business = new PersonInfoMajor2Business();
			DeclareImageBusiness declareImageBusiness = new DeclareImageBusiness();
			DeclareImageBean declareImageBean = null;
			int currentPageNumber = 1;
			int pageSize = 1000;
			PageBean pageBean = new PageBean(currentPageNumber, pageSize);
			PageResultBean<DeclareInfoBean> declare_info_list = declareInfoBusiness.getDeclareInfoPageListByMap(condMap,
					pageBean);

			while (!declare_info_list.getRecordList().isEmpty()) {
				for (DeclareInfoBean bean : declare_info_list.getRecordList()) {
					PersonInfoBean person_info_bean = personInfoBusiness.getPersonInfoByKey(bean.getMemo());
					String imgPath = null;
					String new_file_name = null;
					Timestamp createTime = null;
					if (person_info_bean == null) {
						log.info("查不到这个人" + bean.getMemo());
						message.add(" " + bean.getMemo() + " ");
						continue;
					}
					switch (person_info_bean.getBaseInfoType()) {
					case 1:
					case 2:
					case 3:
						PersonInfoMajor1Bean personInfoMajor1Bean = major1Business
								.getPersonInfoMajor1ByInfoBean(person_info_bean);
						imgPath = personInfoMajor1Bean.getImgpath();
						new_file_name = imgPath.substring(imgPath.lastIndexOf('/') + 1, imgPath.length());
						createTime = personInfoMajor1Bean.getCreateTime();
						break;
					case 4:
					case 5:
						PersonInfoMajor2Bean personInfoMajor2Bean = major2Business
								.getPersonInfoMajor2BeanByInfoBean(person_info_bean);
						imgPath = personInfoMajor2Bean.getImgpath();
						new_file_name = imgPath.substring(imgPath.lastIndexOf('/') + 1, imgPath.length());
						createTime = personInfoMajor2Bean.getCreateTime();
						break;
					}
					declareImageBean = new DeclareImageBean();
					declareImageBean.setCreateTime(createTime);
					declareImageBean.setDeclareId(bean.getDeclareId());
					if (StringKit.isValid(new_file_name)) {
						String imagePath = MapKit.getValueFromMap(new_file_name, imgPathMap);
						if (StringKit.isValid(imagePath)) {
							declareImageBean.setImagePath(
									"http://img-declare.ngonline.cn/ngonline-declare/test/IMG_ONE_INCH/" + imagePath);
						}
					}
					declareImageBean.setImageType(DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
					declareImageBean.setStatus(Constants.STATUS_VALID);

					if (result % 1000 == 0) {
						log.info("正在导入数据 " + bean.getDeclareId());
					}

					if (declareImageBusiness.addDeclareImageWithCreateTime(declareImageBean) > 0) {
						++result;
					} else {
						message.add(bean.getDeclareId() + " 导入失败;");
					}
				}
				pageBean = new PageBean(++currentPageNumber, pageSize);
				declare_info_list = declareInfoBusiness.getDeclareInfoPageListByMap(condMap, pageBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("一共导入 " + result + " 条数据。");
		log.info("以下数据导入失败" + ListKit.list2String(message, ""));
		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/migrate/DataMigrationImport.do");
		} else {
			HttpResponseKit.alertMessage(response, ListKit.list2String(message, ""),
					"/declare/migrate/DataMigrationImport.do");
		}
	}
}
