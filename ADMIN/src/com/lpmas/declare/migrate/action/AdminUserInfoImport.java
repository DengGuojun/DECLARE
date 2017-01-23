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

import com.lpmas.declare.admin.bean.AdminUserGroupBean;
import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminUserGroupBusiness;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelConfig;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.transfer.FileUploadResultBean.FileUploadItem;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ReturnMessageBean;

@WebServlet("/declare/migrate/AdminUserInfoImport.do")
public class AdminUserInfoImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(AdminUserInfoImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserInfoImport() {
		super();
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
		// 输出没有导入的
		WebExcelWriteKit excelWriteKit = new WebExcelWriteKit();
		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
		// 开始写EXCEL
		excelWriteBean.setFileType(ExcelConfig.FT_XLSX);
		excelWriteBean.setFileName("用户名密码-没导入");
		excelWriteBean.setSheetName("用户名密码-没导入");
		// 表头信息
		List<String> headerList = new ArrayList<String>();
		headerList.add("省/市/县");
		headerList.add("地域");
		headerList.add("用户");
		headerList.add("用户名");
		headerList.add("密码");
		excelWriteBean.setHeaderList(headerList);
		List<List<Object>> contentWriteList = new ArrayList<List<Object>>();
		List<Object> rowContentList = null;
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
				AdminUserGroupBusiness adminUserGroupBusiness = new AdminUserGroupBusiness();
				HashMap<String, String> adminUserGroupMap = null;
				AdminUserInfoBusiness adminUserInfoBusiness = new AdminUserInfoBusiness();
				HashMap<String, String> condMap = null;
				for (List<String> content : contentList) {
					if (StringKit.isValid(content.get(1)) && !"省/市/县".equals(content.get(1))) {
						AdminUserInfoBean adminUserInfoBean = null;
						boolean isModifyName = false;
						// 求地区级别及对应省市区
						adminUserGroupMap = new HashMap<String, String>();
						String[] sArray = content.get(1).split("/");
						if (sArray.length == 1) {
							// 国家或省
							if ("国家".equals(sArray[0])) {
								adminUserGroupMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
							} else {
								adminUserGroupMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
								adminUserGroupMap.put("province", sArray[0]);
							}
						} else if (sArray.length == 2) {
							// 市
							adminUserGroupMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_CITY);
							adminUserGroupMap.put("province", sArray[0]);
							adminUserGroupMap.put("city", content.get(2));
						} else if (sArray.length == 3) {
							// 区
							adminUserGroupMap.put("adminGroupLevel", DeclareAdminConfig.ADMIN_LEVEL_REGION);
							adminUserGroupMap.put("province", sArray[0]);
							adminUserGroupMap.put("city", sArray[1]);
							adminUserGroupMap.put("region", content.get(2));
						}
						List<AdminUserGroupBean> adminUserGroupList = adminUserGroupBusiness.getAdminUserGroupListByMap(adminUserGroupMap);
						if (adminUserGroupList.size() > 0) {
							condMap = new HashMap<String, String>();
							String groupId = String.valueOf(adminUserGroupList.get(0).getGroupId());
							condMap.put("groupId", groupId);
							if (StringKit.isValid(content.get(3))) {
								if (content.get(3).contains("认定") || content.get(3).contains("录入")) {
									condMap.put("adminUserName", content.get(3));
								} else if (content.get(3).contains("用户") && !content.get(3).contains("管理用户")) {
									condMap.put("adminUserName", content.get(3).replaceAll("用户", "管理用户"));
								} else if ("admin".equals(content.get(4)) || "luru".equals(content.get(4)) || "rending".equals(content.get(4))) {
									condMap.put("loginId", groupId + DeclareAdminConfig.ADMIN_LOGIN_SEPARATOR + content.get(4));
									isModifyName = true;
								} else {
									condMap.put("adminUserName", content.get(3));
								}
							}
							List<AdminUserInfoBean> adminUserInfoList = adminUserInfoBusiness.getAdminUserInfoListByMap(condMap);
							if (adminUserInfoList.size() > 0) {
								adminUserInfoBean = adminUserInfoList.get(0);
								if (StringKit.isValid(content.get(4))) {
									adminUserInfoBean.setLoginId(groupId + DeclareAdminConfig.ADMIN_LOGIN_SEPARATOR + content.get(4));
								}
								if (StringKit.isValid(content.get(5))) {
									adminUserInfoBean.setLoginPassword(adminUserInfoBusiness.getCryptoPassword(content.get(5)));
								}
								if (isModifyName) {
									adminUserInfoBean.setAdminUserName(content.get(3));
								}
								result = adminUserInfoBusiness.updateAdminUserInfo(adminUserInfoBean);
							}
						}
						if (adminUserInfoBean == null || result < 0) {
							message.add(content.get(3) + ";");
							flag = true;
							// 开始组装一行数据
							rowContentList = new ArrayList<Object>();
							rowContentList.add(content.get(1));
							rowContentList.add(content.get(2));
							rowContentList.add(content.get(3));
							rowContentList.add(content.get(4));
							rowContentList.add(content.get(5));
							// 插一行数据
							contentWriteList.add(rowContentList);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
		}
		if (flag) {
			excelWriteBean.setContentList(contentWriteList);
			excelWriteKit.outputExcel(excelWriteBean, request, response);
			return;
		} else {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/migrate/DataMigrationImport.do");
		}
	}
}
