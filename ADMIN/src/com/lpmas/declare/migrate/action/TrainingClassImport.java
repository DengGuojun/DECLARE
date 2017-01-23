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

import com.lpmas.declare.admin.bean.TrainingItemContentBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.MajorInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.bean.MajorInfoBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.migrate.config.TrainingClassInfoConfig;
import com.lpmas.declare.migrate.util.ExcelStrHandle;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.transfer.FileUploadKit;
import com.lpmas.framework.transfer.FileUploadResultBean;
import com.lpmas.framework.transfer.FileUploadResultBean.FileUploadItem;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.UuidKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * 
 * @author 潘宜杰
 * @see 将三个培训班表的excel数据转移到training_class_info表上。需要先导入培训机构、认定机构和专业的信息
 */
@WebServlet("/declare/migrate/TrainingClassImport.do")
public class TrainingClassImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TrainingClassImport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassImport() {
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

		TrainingOrganizationInfoBusiness organizationInfoBusiness = new TrainingOrganizationInfoBusiness();

		HashMap<String, String> condMap = new HashMap<>();

		List<TrainingOrganizationInfoBean> organizationInfoList = organizationInfoBusiness
				.getTrainingOrganizationInfoListByMap(condMap);
		Map<String, TrainingOrganizationInfoBean> organizationInfoMap = new HashMap<>();
		for (TrainingOrganizationInfoBean bean : organizationInfoList) {
			organizationInfoMap.put(bean.getMemo(), bean);
		}

		MajorInfoBusiness majorInfoBusiness = new MajorInfoBusiness();
		List<MajorInfoBean> majorInfoList = majorInfoBusiness.getMajorInfoListByMap(condMap);
		Map<String, MajorInfoBean> majorInfoMap = new HashMap<>();
		for (MajorInfoBean bean : majorInfoList) {
			majorInfoMap.put(bean.getMemo(), bean);
		}

		TrainingOrganizationInfoBean bean = null;
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
			TrainingClassInfoBusiness classInfoBusiness = new TrainingClassInfoBusiness();
			TrainingClassInfoBean classInfoBean = null;

			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0); // TrainClass_NCZ
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();

				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						classInfoBean = new TrainingClassInfoBean();
						classInfoBean.setMemo(content.get(0));
						if (organizationInfoMap.containsKey(content.get(2))) {
							bean = organizationInfoMap.get(content.get(2));
							classInfoBean.setClassName(bean.getOrganizationName() + "培训班"); // 培训班名称
							classInfoBean.setOrganizationId(bean.getOrganizationId()); // 培训机构ID
							classInfoBean.setTrainingYear(ExcelStrHandle.numStrFormat(content.get(1))); // 培训年份
							classInfoBean.setTrainingType(
									MapKit.getIntValueFromMap(ExcelStrHandle.numStrFormat(content.get(7)),
											TrainingClassInfoConfig.ClASS_TYPE_MAP)); // 培训类型（五个类型）
							classInfoBean.setClassPeopleQuantity(ExcelStrHandle.doubleStr2int(content.get(4))); // 培训班人数
							if (majorInfoMap.containsKey(content.get(3))) {
								classInfoBean.setMajorTypeId(majorInfoMap.get(content.get(3)).getTypeId()); // 专业类型ID
								classInfoBean.setMajorId(majorInfoMap.get(content.get(3)).getMajorId()); // 专业ID
							}
							classInfoBean.setProvince(bean.getProvince());
							classInfoBean.setCity(bean.getCity());
							classInfoBean.setRegion(bean.getRegion());
							classInfoBean.setTrainingItem1(trainingItemContentBeanString(content.get(8), 1)); // 实施项目1
							classInfoBean.setTrainingItem2(trainingItemContentBeanString(content.get(9), 2)); // 实施项目2
							classInfoBean
									.setClassStatus(MapKit.getValueFromMap(ExcelStrHandle.numStrFormat(content.get(10)),
											TrainingClassInfoConfig.ClASS_STATUS_MAP)); // 培训班状态
							if (!content.get(15).equals("0")) {
								if (organizationInfoMap.containsKey(content.get(14))) {
									bean = organizationInfoMap.get(content.get(14));
									classInfoBean.setAuthOrganizationId(bean.getOrganizationId()); // 认定机构ID
									classInfoBean.setAuthStatus(
											com.lpmas.declare.config.TrainingClassInfoConfig.AUTH_STATUS_APPROVE); // 认定状态
								} else {
									classInfoBean.setAuthStatus(
											com.lpmas.declare.config.TrainingClassInfoConfig.AUTH_STATUS_WAIT_APPROVE); // 认定状态
									log.info("认定机构不存在或未认定");
								}
							}
							classInfoBean.setStatus(Constants.STATUS_VALID); // 状态
							Timestamp createTime = new Timestamp(
									DateKit.str2Date(content.get(11).split("[.]")[0], "MM/dd/yyyy HH:mm:ss").getTime());
							classInfoBean.setCreateTime(createTime);
						} else {
							log.error("导入数据错误：培训机构不存在 " + content.get(2));
							continue;
						}
						if (classInfoBusiness.addTrainingClassInfoWithCreateTime(classInfoBean) > 0) {
							++result;
						} else {
							message.add("农场主 " + content.get(0) + " 导入失败;");
						}
					}
				}
			}

			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 1); // TrainClass_Operate
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();

				int i = 0;
				for (List<String> content : contentList) {
					if (i == 0) {
						++i;
						continue;
					} else {
						++i;
					}
					if (!content.get(0).isEmpty()) {
						classInfoBean = new TrainingClassInfoBean();
						classInfoBean.setMemo(content.get(0));
						if (organizationInfoMap.containsKey(content.get(2))) {
							bean = organizationInfoMap.get(content.get(2));
							classInfoBean.setClassName(bean.getOrganizationName() + "培训班"); // 培训班名称
							classInfoBean.setOrganizationId(bean.getOrganizationId()); // 培训机构ID
							classInfoBean.setTrainingYear(ExcelStrHandle.numStrFormat(content.get(1))); // 培训年份
							classInfoBean.setTrainingType(
									MapKit.getIntValueFromMap(ExcelStrHandle.numStrFormat(content.get(6)),
											TrainingClassInfoConfig.ClASS_TYPE_MAP)); // 培训类型（五个类型）
							classInfoBean.setClassPeopleQuantity(ExcelStrHandle.doubleStr2int(content.get(4))); // 培训班人数
							if (majorInfoMap.containsKey(content.get(3))) {
								classInfoBean.setMajorTypeId(majorInfoMap.get(content.get(3)).getTypeId()); // 专业类型ID
								classInfoBean.setMajorId(majorInfoMap.get(content.get(3)).getMajorId()); // 专业ID
							}
							classInfoBean.setProvince(bean.getProvince());
							classInfoBean.setCity(bean.getCity());
							classInfoBean.setRegion(bean.getRegion());

							classInfoBean.setTrainingItem1(trainingItemContentBeanString("", 1)); // 实施项目1
							classInfoBean.setTrainingItem2(trainingItemContentBeanString("", 2)); // 实施项目2

							classInfoBean
									.setClassStatus(MapKit.getValueFromMap(ExcelStrHandle.numStrFormat(content.get(8)),
											TrainingClassInfoConfig.ClASS_STATUS_MAP)); // 培训班状态
							if (!content.get(13).equals("0")) {
								if (organizationInfoMap.containsKey(content.get(12))) {
									bean = organizationInfoMap.get(content.get(12));
									classInfoBean.setAuthOrganizationId(bean.getOrganizationId()); // 认定机构ID
									classInfoBean.setAuthStatus(
											com.lpmas.declare.config.TrainingClassInfoConfig.AUTH_STATUS_APPROVE); // 认定状态
								} else {
									classInfoBean.setAuthStatus(
											com.lpmas.declare.config.TrainingClassInfoConfig.AUTH_STATUS_WAIT_APPROVE); // 认定状态
								}
							}
							classInfoBean.setStatus(Constants.STATUS_VALID); // 状态
							Timestamp createTime = new Timestamp(
									DateKit.str2Date(content.get(9).split("[.]")[0], "MM/dd/yyyy HH:mm:ss").getTime());
							classInfoBean.setCreateTime(createTime);
						} else {
							log.error("导入数据错误：培训机构不存在 " + content.get(2));
							continue;
						}

						if (classInfoBusiness.addTrainingClassInfoWithCreateTime(classInfoBean) > 0) {
							++result;
						} else {
							message.add("Operate " + content.get(0) + " 导入失败;");
						}
					}
				}
			}

			excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 2); // TrainClass_Special
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				int i = 0;
				for (List<String> content : contentList) {
					if (i == 0) {
						++i;
						continue;
					} else {
						++i;
					}
					if (!content.get(0).isEmpty()) {
						classInfoBean = new TrainingClassInfoBean();
						classInfoBean.setMemo(content.get(0));
						if (organizationInfoMap.containsKey(content.get(2))) {
							bean = organizationInfoMap.get(content.get(2));
							classInfoBean.setClassName(bean.getOrganizationName() + "培训班"); // 培训班名称
							classInfoBean.setOrganizationId(bean.getOrganizationId()); // 培训机构ID
							classInfoBean.setTrainingYear(ExcelStrHandle.numStrFormat(content.get(1))); // 培训年份
							classInfoBean.setTrainingType(
									MapKit.getIntValueFromMap(ExcelStrHandle.numStrFormat(content.get(8)),
											TrainingClassInfoConfig.ClASS_TYPE_MAP)); // 培训类型（五个类型）
							classInfoBean.setClassPeopleQuantity(ExcelStrHandle.doubleStr2int(content.get(7))); // 培训班人数
							classInfoBean.setTrainingPose(content.get(4));
							classInfoBean.setProvince(bean.getProvince());
							classInfoBean.setCity(bean.getCity());
							classInfoBean.setRegion(bean.getRegion());

							classInfoBean.setTrainingItem1(trainingItemContentBeanString("", 1)); // 实施项目1
							classInfoBean.setTrainingItem2(trainingItemContentBeanString("", 2)); // 实施项目2

							classInfoBean
									.setClassStatus(MapKit.getValueFromMap(ExcelStrHandle.numStrFormat(content.get(9)),
											TrainingClassInfoConfig.ClASS_STATUS_MAP)); // 培训班状态
							if (!content.get(14).equals("0")) {
								if (organizationInfoMap.containsKey(content.get(13))) {
									bean = organizationInfoMap.get(content.get(13));
									classInfoBean.setAuthOrganizationId(bean.getOrganizationId()); // 认定机构ID
									classInfoBean.setAuthStatus(
											com.lpmas.declare.config.TrainingClassInfoConfig.AUTH_STATUS_APPROVE); // 认定状态
								} else {
									classInfoBean.setAuthStatus(
											com.lpmas.declare.config.TrainingClassInfoConfig.AUTH_STATUS_WAIT_APPROVE); // 认定状态
								}
							}
							classInfoBean.setStatus(Constants.STATUS_VALID); // 状态
							Timestamp createTime = new Timestamp(
									DateKit.str2Date(content.get(10).split("[.]")[0], "MM/dd/yyyy HH:mm:ss").getTime());
							classInfoBean.setCreateTime(createTime);
						} else {
							log.error("导入数据错误：培训机构不存在 " + content.get(2));
							continue;
						}
						if (classInfoBusiness.addTrainingClassInfoWithCreateTime(classInfoBean) > 0) {
							++result;
						} else {
							message.add("专业 " + content.get(0) + " 导入失败;");
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

	public String trainingItemContentBeanString(String dbStr, int num) {
		List<TrainingItemContentBean> contentList = null;
		contentList = new ArrayList<TrainingItemContentBean>();
		TrainingItemContentBean contentBean = null;
		contentBean = new TrainingItemContentBean();
		if (num == 1) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.EDUCATION1);
		} else if (num == 2) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.EDUCATION2);
		}
		contentBean.setValue(dbStr.contains("1") ? "1" : "0");
		contentBean.setName("教育培训");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		if (num == 1) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.PRACTICE1);
		} else if (num == 2) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.PRACTICE2);
		}
		contentBean.setValue(dbStr.contains("2") ? "1" : "0");
		contentBean.setName("实践培训");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		if (num == 1) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.ENTREPRENEURSHIP1);
		} else if (num == 2) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.ENTREPRENEURSHIP2);
		}
		contentBean.setValue(dbStr.contains("3") ? "1" : "0");
		contentBean.setName("创业指导和孵化");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		if (num == 1) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.IDENTIFY1);
		} else if (num == 2) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.IDENTIFY2);
		}
		contentBean.setValue(dbStr.contains("4") ? "1" : "0");
		contentBean.setName("认定管理");
		contentList.add(contentBean);
		contentBean = new TrainingItemContentBean();
		if (num == 1) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.TRACK1);
		} else if (num == 2) {
			contentBean.setKey(com.lpmas.declare.config.TrainingClassInfoConfig.TRACK2);
		}
		contentBean.setValue(dbStr.contains("5") ? "1" : "0");
		contentBean.setName("跟踪服务");
		contentList.add(contentBean);
		return JsonKit.toJson(contentList);
	}
}
