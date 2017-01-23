package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.GovernmentOrganizationInfoBean;
import com.lpmas.declare.admin.business.GovernmentOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.migrate.bean.TabCityInfoBean;
import com.lpmas.declare.migrate.bean.TabProvinceInfoBean;
import com.lpmas.declare.migrate.bean.TabRegionBean;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.business.TabCityInfoBusiness;
import com.lpmas.declare.migrate.business.TabProvinceInfoBusiness;
import com.lpmas.declare.migrate.business.TabRegionBusiness;
import com.lpmas.declare.migrate.business.TabRegionInfoBusiness;
import com.lpmas.declare.migrate.util.ExcelStrHandle;
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
 * @see 将TAB_SunInfo的excel数据转移到government_organization_info表上
 */
@WebServlet("/declare/migrate/GovernmentOrganizationInfoImport.do")
public class GovernmentOrganizationInfoImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(GovernmentOrganizationInfoImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GovernmentOrganizationInfoImport() {
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
			ExcelReadResultBean excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0);
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();

				GovernmentOrganizationInfoBusiness organizationInfoBusiness = new GovernmentOrganizationInfoBusiness();
				GovernmentOrganizationInfoBean organizationInfoBean = null;

				TabRegionBusiness tabRegionBusiness = new TabRegionBusiness();
				TabRegionBean tabRegionBean = null;

				TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
				TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
				TabRegionInfoBusiness tabRegionInfoBusiness = new TabRegionInfoBusiness();

				TabProvinceInfoBean tabProvinceInfoBean = null;
				TabCityInfoBean tabCityInfoBean = null;
				TabRegionInfoBean tabRegionInfoBean = null;

				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						// 求地区级别及对应省市区
						organizationInfoBean = new GovernmentOrganizationInfoBean();

						tabRegionBean = tabRegionBusiness
								.getTabRegionByServerId(ExcelStrHandle.numStrFormat(content.get(0))); // serverID
						if (tabRegionBean != null) {
							String[] sArray = tabRegionBean.getServerId().split("\\.");
							int length = sArray.length;
							if (length == 1) {
								// 全国
								organizationInfoBean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
							} else if (length == 2) {
								// 省
								organizationInfoBean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
								tabProvinceInfoBean = tabProvinceInfoBusiness
										.getProvinceByCode(tabRegionBean.getCode());
								if (tabProvinceInfoBean != null) {
									organizationInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
								}
							} else if (length == 3) {
								// 市
								organizationInfoBean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
								tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
								if (tabCityInfoBean != null) {
									organizationInfoBean.setCity(tabCityInfoBean.getCityName());
									tabProvinceInfoBean = tabProvinceInfoBusiness
											.getProvinceByCode(tabRegionBean.getCode());
									if (tabProvinceInfoBean != null) {
										organizationInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								}
							} else if (length == 4) {
								// 区
								organizationInfoBean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
								tabRegionInfoBean = tabRegionInfoBusiness
										.getTabRegionInfoByCode(tabRegionBean.getCode());
								if (tabRegionInfoBean != null) {
									organizationInfoBean.setRegion(tabRegionInfoBean.getRegionName());
									tabCityInfoBean = tabCityInfoBusiness
											.getTabCityInfoByKey(tabRegionInfoBean.getCityId());
									if (tabCityInfoBean != null) {
										organizationInfoBean.setCity(tabCityInfoBean.getCityName());
									}
									tabProvinceInfoBean = tabProvinceInfoBusiness
											.getTabProvinceInfoByKey(tabRegionInfoBean.getProvinceId());
									if (tabProvinceInfoBean != null) {
										organizationInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								}
							}
						}

						organizationInfoBean.setTrainingYear(ExcelStrHandle.numStrFormat(content.get(1))); // 培育年份
						organizationInfoBean.setOrganizationName(content.get(2)); // 主管部门名称
						organizationInfoBean.setResponsiblePersonName(content.get(3)); // 负责人姓名
						organizationInfoBean.setOperatorName(content.get(4)); // 经办人姓名
						organizationInfoBean.setResponsiblePersonMobile(ExcelStrHandle.toPlainString(content.get(5))); // 负责人手机
						organizationInfoBean.setOperatorMobile(ExcelStrHandle.toPlainString(content.get(6))); // 经办人手机
						organizationInfoBean.setFax(ExcelStrHandle.toPlainString(content.get(7))); // 传真
						organizationInfoBean.setZipCode(ExcelStrHandle.numStrFormat(content.get(8))); // 邮政编码
						organizationInfoBean.setAddress(content.get(9)); // 地址
						organizationInfoBean.setIsDemonstrationArea(ExcelStrHandle.doubleStr2int(content.get(10)));
						organizationInfoBean.setStatus(Constants.STATUS_VALID); // 状态
						Timestamp createTime = new Timestamp(
								DateKit.str2Date(content.get(11).split("[.]")[0], "MM/dd/yyyy HH:mm:ss").getTime());
						organizationInfoBean.setCreateTime(createTime);
						organizationInfoBean.setDepartment(content.get(12)); // 主管科室
						if (result % 100 == 0)
							log.info("正在导入第 " + result + " 条数据。" + content.get(2));
						if (organizationInfoBusiness
								.addGovernmentOrganizationInfoWithCreateTime(organizationInfoBean) > 0) {
							++result;
						} else {
							message.add("政府组织信息 " + content.get(2) + " 导入失败;");
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
