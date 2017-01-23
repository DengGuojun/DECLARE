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

import com.lpmas.declare.admin.bean.TrainingMaterialInfoBean;
import com.lpmas.declare.admin.business.TrainingMaterialInfoBusiness;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.admin.config.TrainingMaterialConfig;
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
 * Servlet implementation class TrainingMaterialImport
 * 
 * @author 潘宜杰
 * @see 将TrainingBook的excel数据转移到training_material_info表上
 */
@WebServlet("/declare/migrate/TrainingMaterialImport.do")
public class TrainingMaterialImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TrainingMaterialImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingMaterialImport() {
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
				TrainingMaterialInfoBusiness materialInfoBusiness = new TrainingMaterialInfoBusiness();
				TrainingMaterialInfoBean materialInfoBean = null;

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
						materialInfoBean = new TrainingMaterialInfoBean();
						materialInfoBean.setMemo(content.get(0)); // 备注
						materialInfoBean.setTrainingYear(ExcelStrHandle.numStrFormat(content.get(1))); // 培育年份
						materialInfoBean.setProvince("国家"); // 因为tabProvinceInfoBean.getProvinceName().equals("")一直不为真
						// 求地区级别及对应省市区
						tabRegionBean = tabRegionBusiness.getTabRegionByServerId(content.get(2)); // 根据原表的省份字段来查，而不是ServerId字段
						if (tabRegionBean != null) {
							String[] sArray = tabRegionBean.getServerId().split("\\.");
							int length = sArray.length;
							if (length == 1) {
								// 全国

							} else if (length == 2) {
								// 省
								tabProvinceInfoBean = tabProvinceInfoBusiness
										.getProvinceByCode(tabRegionBean.getCode());
								if (tabProvinceInfoBean != null) {
									materialInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
								}
							} else if (length == 3) {
								// 市
								tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
								if (tabCityInfoBean != null) {
									materialInfoBean.setCity(tabCityInfoBean.getCityName());
									tabProvinceInfoBean = tabProvinceInfoBusiness
											.getTabProvinceInfoByKey(tabCityInfoBean.getProvinceId());
									if (tabProvinceInfoBean != null) {
										materialInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								}
								materialInfoBean.setCity(tabCityInfoBean.getCityName());
							} else if (length == 4) {
								// 区
								tabRegionInfoBean = tabRegionInfoBusiness
										.getTabRegionInfoByCode(tabRegionBean.getCode());
								if (tabRegionInfoBean != null) {
									materialInfoBean.setRegion(tabRegionInfoBean.getRegionName());
									tabCityInfoBean = tabCityInfoBusiness
											.getTabCityInfoByKey(tabRegionInfoBean.getCityId());
									if (tabCityInfoBean != null) {
										materialInfoBean.setCity(tabCityInfoBean.getCityName());
									}
									tabProvinceInfoBean = tabProvinceInfoBusiness
											.getTabProvinceInfoByKey(tabRegionInfoBean.getProvinceId());
									if (tabProvinceInfoBean != null) {
										materialInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								}
							}
						}

						materialInfoBean.setMaterialName(content.get(3)); // 教材名称
						materialInfoBean.setCompileOrganization(content.get(4)); // 组编单位
						materialInfoBean.setPublishingCompany(content.get(5)); // 出版社
						materialInfoBean.setPublishingYeah(ExcelStrHandle.numStrFormat(content.get(6))); // 出版年份
						materialInfoBean.setPublishingMonth(ExcelStrHandle.numStrFormat(content.get(7))); // 出版月份
						materialInfoBean.setWordQuantity(ExcelStrHandle.doubleStr2int(content.get(8))); // 字数（千字）
						materialInfoBean.setPaperQuantity(ExcelStrHandle.doubleStr2int(content.get(9))); // 印张（张）
						materialInfoBean.setPrice(Double.valueOf(content.get(10))); // 价格（元）
						materialInfoBean.setIndustry(content.get(11)); // 产业
						materialInfoBean.setLink(content.get(12)); // 链接
						if (ExcelStrHandle.doubleStr2int(content.get(13)) == 1) {
							materialInfoBean.setMaterialType(TrainingMaterialConfig.MATERIAL_GENERAL); // 教材类型
						} else if (ExcelStrHandle.doubleStr2int(content.get(13)) == 2) {
							materialInfoBean.setMaterialType(TrainingMaterialConfig.MATERIAL_PROFESSIONAL); // 教材类型
						}
						materialInfoBean.setStatus(Constants.STATUS_VALID); // 状态
						Timestamp createTime = new Timestamp(
								DateKit.str2Date(content.get(16).split("[.]")[0], "MM/dd/yyyy HH:mm:ss").getTime());
						materialInfoBean.setCreateTime(createTime);
						if (materialInfoBusiness.addTrainingMaterialInfoWithCreateTime(materialInfoBean) > 0) {
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
		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/migrate/DataMigrationImport.do");
		} else {
			HttpResponseKit.alertMessage(response, ListKit.list2String(message, ""),
					"/declare/migrate/DataMigrationImport.do");
		}
	}
}
