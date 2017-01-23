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

import com.lpmas.declare.admin.bean.TrainingClassTargetBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.TrainingClassTargetBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.migrate.bean.TabCityInfoBean;
import com.lpmas.declare.migrate.bean.TabProvinceInfoBean;
import com.lpmas.declare.migrate.bean.TabRegionBean;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.business.TabCityInfoBusiness;
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
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ReturnMessageBean;

@WebServlet("/declare/migrate/TrainingOrgTaskImport.do")
public class TrainingOrgTaskImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TrainingOrgTaskImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingOrgTaskImport() {
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
				TrainingClassTargetBusiness trainingClassTargetBusiness = new TrainingClassTargetBusiness();
				TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
				TrainingClassTargetBean trainingClassTargetBean = null;
				HashMap<String, String> condMap = null;
				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						trainingClassTargetBean = new TrainingClassTargetBean();
						trainingClassTargetBean.setMemo(content.get(0));
						if (content.size() > 7 && StringKit.isValid(content.get(7))) {
							trainingClassTargetBean.setTrainingYear(content.get(7).substring(4, 8));
						}
						if (content.size() > 2 && StringKit.isValid(content.get(2))) {
							trainingClassTargetBean.setTargetQuantity1(Integer.valueOf(content.get(2).split("\\.")[0]));
						}
						if (content.size() > 3 && StringKit.isValid(content.get(3))) {
							trainingClassTargetBean.setTargetQuantity5(Integer.valueOf(content.get(3).split("\\.")[0]));
						}
						if (content.size() > 4 && StringKit.isValid(content.get(4))) {
							trainingClassTargetBean.setTargetQuantity2(Integer.valueOf(content.get(4).split("\\.")[0]));
						}
						if (content.size() > 5 && StringKit.isValid(content.get(5))) {
							trainingClassTargetBean.setTargetQuantity3(Integer.valueOf(content.get(5).split("\\.")[0]));
						}
						if (content.size() > 6 && StringKit.isValid(content.get(6))) {
							trainingClassTargetBean.setTargetQuantity4(Integer.valueOf(content.get(6).split("\\.")[0]));
						}
						if (trainingClassTargetBean.getTargetQuantity1() == Constants.STATUS_NOT_VALID
								&& trainingClassTargetBean.getTargetQuantity2() == Constants.STATUS_NOT_VALID
								&& trainingClassTargetBean.getTargetQuantity3() == Constants.STATUS_NOT_VALID
								&& trainingClassTargetBean.getTargetQuantity4() == Constants.STATUS_NOT_VALID
								&& trainingClassTargetBean.getTargetQuantity5() == Constants.STATUS_NOT_VALID) {
							continue;
						}
						trainingClassTargetBean.setStatus(Constants.STATUS_VALID);
						condMap = new HashMap<String, String>();
						if (content.size() > 1 && StringKit.isValid(content.get(1))) {
							condMap.put("memo", content.get(1));
							List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = trainingOrganizationInfoBusiness
									.getTrainingOrganizationInfoListByMap(condMap);
							if (!trainingOrganizationInfoList.isEmpty() && trainingOrganizationInfoList.size() > 0) {
								trainingClassTargetBean
										.setOrganizationId(trainingOrganizationInfoList.get(0).getOrganizationId());
							} else {
								continue;
							}
						}
						if (content.size() > 9 && StringKit.isValid(content.get(9))) {
							// 求地区级别及对应省市区
							TabRegionBusiness tabRegionBusiness = new TabRegionBusiness();
							TabRegionBean tabRegionBean = tabRegionBusiness.getTabRegionByServerId(content.get(9));
							if (tabRegionBean != null) {
								String[] sArray = tabRegionBean.getServerId().split("\\.");
								if (sArray.length == 1) {
									// 全国
								} else if (sArray.length == 2) {
									// 省
									TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
									TabProvinceInfoBean tabProvinceInfoBean = tabProvinceInfoBusiness
											.getProvinceByCode(tabRegionBean.getCode());
									if (tabProvinceInfoBean != null) {
										trainingClassTargetBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								} else if (sArray.length == 3) {
									// 市
									TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
									TabCityInfoBean tabCityInfoBean = tabCityInfoBusiness
											.getCityByCode(tabRegionBean.getCode());
									if (tabCityInfoBean != null) {
										trainingClassTargetBean.setCity(tabCityInfoBean.getCityName());
										TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
										TabProvinceInfoBean tabProvinceInfoBean = tabProvinceInfoBusiness
												.getTabProvinceInfoByKey(tabCityInfoBean.getProvinceId());
										if (tabProvinceInfoBean != null) {
											trainingClassTargetBean.setProvince(tabProvinceInfoBean.getProvinceName());
										}
									}
								} else if (sArray.length == 4) {
									// 区
									TabRegionInfoBusiness tabRegionInfoBusiness = new TabRegionInfoBusiness();
									TabRegionInfoBean tabRegionInfoBean = tabRegionInfoBusiness
											.getTabRegionInfoByCode(tabRegionBean.getCode());
									if (tabRegionInfoBean != null) {
										trainingClassTargetBean.setRegion(tabRegionInfoBean.getRegionName());
										TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
										TabCityInfoBean tabCityInfoBean = tabCityInfoBusiness
												.getTabCityInfoByKey(tabRegionInfoBean.getCityId());
										if (tabCityInfoBean != null) {
											trainingClassTargetBean.setCity(tabCityInfoBean.getCityName());
										}
										TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
										TabProvinceInfoBean tabProvinceInfoBean = tabProvinceInfoBusiness
												.getTabProvinceInfoByKey(tabRegionInfoBean.getProvinceId());
										if (tabProvinceInfoBean != null) {
											trainingClassTargetBean.setProvince(tabProvinceInfoBean.getProvinceName());
										}
									}
								}
							}
							result = trainingClassTargetBusiness.addTrainingClassTarget(trainingClassTargetBean);
							if (result < 0) {
								message.add(content.get(1) + "导入失败;");
								flag = true;
							}
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
