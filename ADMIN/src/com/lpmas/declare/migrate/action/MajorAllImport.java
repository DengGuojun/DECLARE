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

import com.lpmas.declare.bean.MajorInfoBean;
import com.lpmas.declare.bean.MajorTypeBean;
import com.lpmas.declare.admin.business.MajorInfoBusiness;
import com.lpmas.declare.admin.business.MajorTypeBusiness;
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
 * @see 将TAB_MAJOR的excel数据转移到两张新表上
 */
@WebServlet("/declare/migrate/MajorAllImport.do")
public class MajorAllImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(MajorAllImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MajorAllImport() {
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

				MajorTypeBusiness typeBusiness = new MajorTypeBusiness();
				MajorTypeBean typeBean = null;

				MajorInfoBusiness infoBusiness = new MajorInfoBusiness();
				MajorInfoBean infoBean = null;

				TabRegionBusiness tabRegionBusiness = new TabRegionBusiness();
				TabRegionBean tabRegionBean = null;

				TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
				TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
				TabRegionInfoBusiness tabRegionInfoBusiness = new TabRegionInfoBusiness();

				TabProvinceInfoBean tabProvinceInfoBean = null;
				TabCityInfoBean tabCityInfoBean = null;
				TabRegionInfoBean tabRegionInfoBean = null;

				// major_type表
				for (List<String> content : contentList) {
					typeBean = new MajorTypeBean();

					if (content.get(2).isEmpty()) {
						typeBean.setMemo(content.get(0)); // 备注
						typeBean.setMajorName(content.get(1)); // 专业类型名称
						// 求地区级别及对应省市区
						tabRegionBean = tabRegionBusiness
								.getTabRegionByServerId(ExcelStrHandle.numStrFormat(content.get(3))); // 地区ID
						if (tabRegionBean != null) {
							String[] sArray = tabRegionBean.getServerId().split("\\.");
							int length = sArray.length;
							if (length == 1) {
								// 全国
								typeBean.setMajorLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
							} else if (length == 2) {
								// 省
								typeBean.setMajorLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
								tabProvinceInfoBean = tabProvinceInfoBusiness
										.getProvinceByCode(tabRegionBean.getCode());
								if (tabProvinceInfoBean != null) {
									typeBean.setProvince(tabProvinceInfoBean.getProvinceName());
								}
							} else if (length == 3) {
								// 市
								typeBean.setMajorLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
								tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
								if (tabCityInfoBean != null) {
									typeBean.setCity(tabCityInfoBean.getCityName());
									tabProvinceInfoBean = tabProvinceInfoBusiness
											.getTabProvinceInfoByKey(tabCityInfoBean.getProvinceId());
									if (tabProvinceInfoBean != null) {
										typeBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								}
							} else if (length == 4) {
								// 区
								typeBean.setMajorLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
								tabRegionInfoBean = tabRegionInfoBusiness
										.getTabRegionInfoByCode(tabRegionBean.getCode());
								if (tabRegionInfoBean != null) {
									typeBean.setRegion(tabRegionInfoBean.getRegionName());
									tabCityInfoBean = tabCityInfoBusiness
											.getTabCityInfoByKey(tabRegionInfoBean.getCityId());
									if (tabCityInfoBean != null) {
										typeBean.setCity(tabCityInfoBean.getCityName());
									}
									tabProvinceInfoBean = tabProvinceInfoBusiness
											.getTabProvinceInfoByKey(tabRegionInfoBean.getProvinceId());
									if (tabProvinceInfoBean != null) {
										typeBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								}
							}
						}

						typeBean.setStatus(ExcelStrHandle.doubleStr2int(content.get(4))); // 状态
						typeBean.setMajorYear(ExcelStrHandle.numStrFormat(content.get(5))); // 专业年份
						if (typeBusiness.addMajorType(typeBean) > 0) {
							++result;
						} else {
							message.add("专业类型" + content.get(3) + " 导入失败;");
						}
					}
				}

				HashMap<String, String> condMap = new HashMap<>();
				List<MajorTypeBean> majorTypeList = typeBusiness.getMajorTypeListByMap(condMap);
				Map<String, Integer> majorTypeMap = new HashMap<>();
				for (MajorTypeBean bean : majorTypeList) {
					majorTypeMap.put(bean.getMemo(), bean.getMajorId());
				}
				// major_info表
				int j = 0;
				for (List<String> content : contentList) {
					if (j == 0) {
						++j;
						continue;
					} else {
						++j;
					}
					if (!content.get(2).isEmpty()) {
						infoBean = new MajorInfoBean();
						infoBean.setMajorId(j);
						infoBean.setMemo(content.get(0)); // 备注
						infoBean.setMajorName(content.get(1)); // 专业类型名称
						infoBean.setStatus(ExcelStrHandle.doubleStr2int(content.get(4))); // 状态

						if (majorTypeMap.containsKey(content.get(2))) {
							infoBean.setTypeId(majorTypeMap.get(content.get(2)));
						} else {
							infoBean.setTypeId(0);
							infoBean.setStatus(Constants.STATUS_NOT_VALID);
						}
						if (infoBusiness.addMajorInfo(infoBean) > 0) {
							++result;
						} else {
							message.add("专业信息" + content.get(3) + " 导入失败;");
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
