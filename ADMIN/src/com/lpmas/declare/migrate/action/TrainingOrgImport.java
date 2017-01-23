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

import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.declare.migrate.bean.TabCityInfoBean;
import com.lpmas.declare.migrate.bean.TabIndexBean;
import com.lpmas.declare.migrate.bean.TabProvinceInfoBean;
import com.lpmas.declare.migrate.bean.TabRegionBean;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.business.TabCityInfoBusiness;
import com.lpmas.declare.migrate.business.TabIndexBusiness;
import com.lpmas.declare.migrate.business.TabProvinceInfoBusiness;
import com.lpmas.declare.migrate.business.TabRegionBusiness;
import com.lpmas.declare.migrate.business.TabRegionInfoBusiness;
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

@WebServlet("/declare/migrate/TrainingOrgImport.do")
public class TrainingOrgImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TrainingOrgImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingOrgImport() {
		super();
		// TODO Auto-generated constructor stub
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
			ExcelReadResultBean excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0);
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
				TrainingOrganizationInfoBean trainingOrganizationInfoBean = null;
				TabIndexBusiness tabIndexBusiness = new TabIndexBusiness();
				HashMap<String, String> condMap = null;
				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						trainingOrganizationInfoBean = new TrainingOrganizationInfoBean();
						trainingOrganizationInfoBean.setMemo(content.get(0));
						trainingOrganizationInfoBean
								.setOrganizationType(TrainingOrganizationConfig.ORGANIZATION_TRAINING);
						trainingOrganizationInfoBean.setOrganizationName(content.get(3));
						trainingOrganizationInfoBean.setTrainingYear(content.get(2));
						trainingOrganizationInfoBean.setAddress(content.get(4));
						trainingOrganizationInfoBean.setRepresentativeName(content.get(5));
						trainingOrganizationInfoBean.setTelephone(content.get(6));
						trainingOrganizationInfoBean.setZipCode(content.get(16));
						trainingOrganizationInfoBean.setStatus(Constants.STATUS_VALID);
						// 插入机构类型
						condMap = new HashMap<String, String>();
						condMap.put("id", content.get(8));
						List<TabIndexBean> tabIndexList = tabIndexBusiness.getTabIndexListByMap(condMap);
						if (!tabIndexList.isEmpty() && tabIndexList.size() > 0) {
							for (String key : TrainingOrganizationConfig.ORGANIZATION_TYPE_MAP.keySet()) {
								if (TrainingOrganizationConfig.ORGANIZATION_TYPE_MAP.get(key)
										.equals(tabIndexList.get(0).getName())) {
									trainingOrganizationInfoBean.setOrganizationCategory(key);
									continue;
								}
							}
						}
						// 求地区级别及对应省市区
						TabRegionBusiness tabRegionBusiness = new TabRegionBusiness();
						TabRegionBean tabRegionBean = tabRegionBusiness.getTabRegionByServerId(content.get(1));
						if (tabRegionBean != null) {
							String[] sArray = tabRegionBean.getServerId().split("\\.");
							if (sArray.length == 1) {
								// 全国
								trainingOrganizationInfoBean
										.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
							} else if (sArray.length == 2) {
								// 省
								trainingOrganizationInfoBean
										.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
								TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
								TabProvinceInfoBean tabProvinceInfoBean = tabProvinceInfoBusiness
										.getProvinceByCode(tabRegionBean.getCode());
								if (tabProvinceInfoBean != null) {
									trainingOrganizationInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
								}
							} else if (sArray.length == 3) {
								// 市
								trainingOrganizationInfoBean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
								TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
								TabCityInfoBean tabCityInfoBean = tabCityInfoBusiness
										.getCityByCode(tabRegionBean.getCode());
								if (tabCityInfoBean != null) {
									trainingOrganizationInfoBean.setCity(tabCityInfoBean.getCityName());
									TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
									TabProvinceInfoBean tabProvinceInfoBean = tabProvinceInfoBusiness
											.getTabProvinceInfoByKey(tabCityInfoBean.getProvinceId());
									if (tabProvinceInfoBean != null) {
										trainingOrganizationInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								}
							} else if (sArray.length == 4) {
								// 区
								trainingOrganizationInfoBean
										.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
								TabRegionInfoBusiness tabRegionInfoBusiness = new TabRegionInfoBusiness();
								TabRegionInfoBean tabRegionInfoBean = tabRegionInfoBusiness
										.getTabRegionInfoByCode(tabRegionBean.getCode());
								if (tabRegionInfoBean != null) {
									trainingOrganizationInfoBean.setRegion(tabRegionInfoBean.getRegionName());
									TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
									TabCityInfoBean tabCityInfoBean = tabCityInfoBusiness
											.getTabCityInfoByKey(tabRegionInfoBean.getCityId());
									if (tabCityInfoBean != null) {
										trainingOrganizationInfoBean.setCity(tabCityInfoBean.getCityName());
									}
									TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
									TabProvinceInfoBean tabProvinceInfoBean = tabProvinceInfoBusiness
											.getTabProvinceInfoByKey(tabRegionInfoBean.getProvinceId());
									if (tabProvinceInfoBean != null) {
										trainingOrganizationInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								}
							}
						}

						result = trainingOrganizationInfoBusiness
								.addTrainingOrganizationInfo(trainingOrganizationInfoBean);
						if (result < 0) {
							message.add(content.get(3) + "导入失败;");
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
			HttpResponseKit.alertMessage(response, ListKit.list2String(message, " "),
					"/declare/migrate/DataMigrationImport.do");
		} else {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/migrate/DataMigrationImport.do");
		}
	}

}
