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

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.AnnouncementInfoBean;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.business.AnnouncementInfoBusiness;
import com.lpmas.declare.admin.config.AnnouncementInfoConfig;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.migrate.bean.TabCityInfoBean;
import com.lpmas.declare.migrate.bean.TabProvinceInfoBean;
import com.lpmas.declare.migrate.bean.TabRegionBean;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.business.TabCityInfoBusiness;
import com.lpmas.declare.migrate.business.TabProvinceInfoBusiness;
import com.lpmas.declare.migrate.business.TabRegionBusiness;
import com.lpmas.declare.migrate.business.TabRegionInfoBusiness;
import com.lpmas.declare.migrate.config.RoleTypeConfig;
import com.lpmas.declare.migrate.config.WebInfoConfig;
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
 * @see 将WebInfo的excel数据转移到announcement_info表上
 */
@WebServlet("/declare/migrate/AnnouncementInfoImport.do")
public class AnnouncementInfoImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(AnnouncementInfoImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnnouncementInfoImport() {
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
			Map<String, String> personInfoMap = new HashMap<>();
			Map<String, String> unitInfoMap = new HashMap<>();
			Map<String, String> loginMap = new HashMap<>();

			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 1); // PersonInfo
																							// 拿ｐｅｒｓｏｎ对应的unitId
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();

				for (List<String> content : contentList) {
					personInfoMap.put(content.get(0), content.get(1));
				}
			}

			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 2); // UnitInfo
																							// 拿ｕｎｉｔ对应的regionId
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				for (List<String> content : contentList) {
					unitInfoMap.put(content.get(0), ExcelStrHandle.numStrFormat(content.get(2)));
				}
			}

			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 3); // Login
																							// 拿ｐｅｒｓｏｎ对应的loginName
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				for (List<String> content : contentList) {
					loginMap.put(content.get(1), content.get(0));
				}
			}

			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0); // WebInfo
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();

				AnnouncementInfoBusiness announcementInfoBusiness = new AnnouncementInfoBusiness();
				AdminUserInfoBusiness userInfoBusiness = new AdminUserInfoBusiness();

				AnnouncementInfoBean announcementInfoBean = null;
				List<AdminUserInfoBean> adminUserInfoList = null;

				TabRegionBusiness tabRegionBusiness = new TabRegionBusiness();
				TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
				TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
				TabRegionInfoBusiness tabRegionInfoBusiness = new TabRegionInfoBusiness();

				TabRegionBean tabRegionBean = null;
				TabProvinceInfoBean tabProvinceInfoBean = null;
				TabCityInfoBean tabCityInfoBean = null;
				TabRegionInfoBean tabRegionInfoBean = null;

				HashMap<String, String> condMap = null;

				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						announcementInfoBean = new AnnouncementInfoBean();
						announcementInfoBean.setAnnouncementTitle(content.get(2)); // 公告标题
						announcementInfoBean.setAnnouncementContent(content.get(3)); // 公告内容

						String roleType = MapKit.getValueFromMap(content.get(6), loginMap); // 从loginMap查找对应的角色类型

						String unitId = MapKit.getValueFromMap(content.get(6), personInfoMap); // 从personInfoMap查找对应的unitId

						String regionId = MapKit.getValueFromMap(unitId, unitInfoMap); // 从unitInfoMap查找对应的regionId

						tabRegionBean = tabRegionBusiness.getTabRegionByServerId(regionId);

						condMap = new HashMap<>();
						if (tabRegionBean != null) {
							tabProvinceInfoBean = tabProvinceInfoBusiness.getProvinceByCode(tabRegionBean.getCode());
							tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
							tabRegionInfoBean = tabRegionInfoBusiness.getTabRegionInfoByCode(tabRegionBean.getCode());

							if (tabProvinceInfoBean != null) {
								condMap.put("country", tabProvinceInfoBean.getProvinceName());
							}
							if (tabCityInfoBean != null) {
								condMap.put("city", tabCityInfoBean.getCityName());
							}
							if (tabRegionInfoBean != null) {
								condMap.put("region", tabRegionInfoBean.getRegionName());
							}

							adminUserInfoList = userInfoBusiness.getAdminUserInfoListByMap(condMap);

							if (!adminUserInfoList.isEmpty()) {
								int index = -1;
								if (roleType.equals(RoleTypeConfig.ROLE_TYPE_ADMIN)) {
									index = 0;
								} else if (roleType.equals(RoleTypeConfig.ROLE_TYPE_LURU)) {
									index = 1;
								} else if (roleType.equals(RoleTypeConfig.ROLE_TYPE_REDING)) {
									index = 2;
								}
								if (index != -1) {
									announcementInfoBean.setCreateUser(adminUserInfoList.get(index).getUserId()); // 创建用户
								} else {
									announcementInfoBean.setCreateUser(2); // 国家管理员
									log.info(content.get(6) + "设为默认的国家管理员");
								}
							} else {
								log.error(content.get(6) + "没有对应的角色类型");
							}
						}
						int announcementStatus = ExcelStrHandle.doubleStr2int(content.get(7));
						if (announcementStatus == 1) {
							announcementInfoBean
									.setAnnouncementStatus(AnnouncementInfoConfig.ANNOUNCEMENT_STATUS_PUBLISHED); // 公告状态
						} else if (announcementStatus == 0) {
							announcementInfoBean.setAnnouncementStatus(AnnouncementInfoConfig.ANNOUNCEMENT_STATUS_EDIT);
						}

						String receiverStr = "[";
						String receiver = content.get(11);
						if (receiver.contains("国家用户")) {
							for (int roleId : WebInfoConfig.ROLE_TYPE_DETAIL_COUNTRY) {
								receiverStr += roleId + ",";
							}
						}
						if (receiver.contains("省级用户")) {
							for (int roleId : WebInfoConfig.ROLE_TYPE_DETAIL_PROVINCE) {
								receiverStr += roleId + ",";
							}
						}
						if (receiver.contains("市级用户")) {
							for (int roleId : WebInfoConfig.ROLE_TYPE_DETAIL_CITY) {
								receiverStr += roleId + ",";
							}
						}
						if (receiver.contains("县级用户")) {
							for (int roleId : WebInfoConfig.ROLE_TYPE_DETAIL_REGION) {
								receiverStr += roleId + ",";
							}
						}
						if (receiver.contains("录入用户")) {
							for (int roleId : WebInfoConfig.ROLE_TYPE_DETAIL_LURU) {
								receiverStr += roleId + ",";
							}
						}
						if (receiver.contains("认定用户")) {
							for (int roleId : WebInfoConfig.ROLE_TYPE_DETAIL_RENDING) {
								receiverStr += roleId + ",";
							}
						}
						receiverStr = receiverStr.substring(0, receiverStr.length() - 1) + "]";
						announcementInfoBean.setReceiver(receiverStr); // 接收者角色
						announcementInfoBean.setStatus(Constants.STATUS_VALID); // 状态
						announcementInfoBean.setMemo(content.get(0));

						if (announcementInfoBusiness.addAnnouncementInfo(announcementInfoBean) > 0) {
							++result;
						} else {
							message.add(content.get(2) + " 导入失败;");
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
