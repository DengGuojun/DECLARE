package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.sql.Date;
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

import com.lpmas.constant.user.GenderConfig;
import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.bean.TeacherRegionInfoBean;
import com.lpmas.declare.admin.business.TeacherMajorInfoBusiness;
import com.lpmas.declare.admin.business.TeacherMajorTypeBusiness;
import com.lpmas.declare.admin.business.TeacherInfoBusiness;
import com.lpmas.declare.admin.business.TeacherRegionInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.bean.TeacherMajorInfoBean;
import com.lpmas.declare.bean.TeacherMajorTypeBean;
import com.lpmas.declare.migrate.bean.TabCityInfoBean;
import com.lpmas.declare.migrate.bean.TabProvinceInfoBean;
import com.lpmas.declare.migrate.bean.TabRegionBean;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.business.TabCityInfoBusiness;
import com.lpmas.declare.migrate.business.TabProvinceInfoBusiness;
import com.lpmas.declare.migrate.business.TabRegionBusiness;
import com.lpmas.declare.migrate.business.TabRegionInfoBusiness;
import com.lpmas.declare.migrate.config.TeacherInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
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

@WebServlet("/declare/migrate/TeacherInfoImport.do")
public class TeacherInfoImport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TeacherInfoImport.class);
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
			ExcelReadResultBean excelReadResultBean = excelReadKit.readExcel(FileInfoConfig.ADMIN_FILE_PATH + filePath
					+ Constants.FILE_SEPARATOR + fileName + "." + extensionFileName, 0);
			if (excelReadResultBean != null && excelReadResultBean.getResult()) {
				List<List<String>> contentList = excelReadResultBean.getContentList();
				TeacherInfoBusiness teacherInfoBusiness = new TeacherInfoBusiness();
				TeacherInfoBean teacherInfoBean = null;
				TeacherRegionInfoBusiness teacherRegionInfoBusiness = new TeacherRegionInfoBusiness();
				TeacherRegionInfoBean teacherRegionInfoBean = null;
				HashMap<String, String> condMap = new HashMap<>();
				TeacherMajorTypeBusiness majorTypeBusiness = new TeacherMajorTypeBusiness();
				TeacherMajorInfoBusiness majorInfoBusiness = new TeacherMajorInfoBusiness();

				List<TeacherMajorTypeBean> majorTypeList = majorTypeBusiness.getTeacherMajorTypeListByMap(condMap);
				Map<String, TeacherMajorTypeBean> majorTypeMap = new HashMap<>();
				for (TeacherMajorTypeBean bean : majorTypeList) {
					majorTypeMap.put(bean.getMemo(), bean);
				}
				List<TeacherMajorInfoBean> majorInfoList = majorInfoBusiness.getMajorInfoListByMap(condMap);
				Map<String, TeacherMajorInfoBean> majorInfoMap = new HashMap<>();
				for (TeacherMajorInfoBean bean : majorInfoList) {
					majorInfoMap.put(bean.getMemo(), bean);
				}

				for (List<String> content : contentList) {
					if (!content.get(0).isEmpty()) {
						teacherInfoBean = new TeacherInfoBean();
						teacherInfoBean.setMemo(content.get(0));
						if (content.size() > 1 && StringKit.isValid(content.get(1))) {
							teacherInfoBean.setTeacherName(content.get(1));
						}
						if (content.size() > 2 && StringKit.isValid(content.get(2))) {
							teacherInfoBean.setIdentityNumber(content.get(2));
						}
						if (content.size() > 3 && StringKit.isValid(content.get(3))) {
							if ("0".equals(content.get(3).split("\\.")[0])) {
								teacherInfoBean.setTeacherGender(GenderConfig.GENDER_MALE);
							} else if ("1".equals(content.get(3).split("\\.")[0])) {
								teacherInfoBean.setTeacherGender(GenderConfig.GENDER_FEMALE);
							}
						}
						if (content.size() > 4 && StringKit.isValid(content.get(4))) {
							String teacherImage = content.get(4);
							teacherInfoBean.setTeacherImage(
									teacherImage.substring(teacherImage.lastIndexOf("/") + 1, teacherImage.length())); // 头像
						}
						if (content.size() > 5 && StringKit.isValid(content.get(5))) {
							teacherInfoBean.setTeacherBirthday(Date.valueOf(content.get(5).split(" ")[0]));
						}
						if (content.size() > 9 && StringKit.isValid(content.get(9))) {
							teacherInfoBean.setCompany(content.get(9));
						}
						if (content.size() > 10 && StringKit.isValid(content.get(10))) {
							teacherInfoBean.setTeacherNumber(content.get(10));
						}
						if (content.size() > 19 && StringKit.isValid(content.get(19))) {
							teacherInfoBean.setEmail(content.get(19));
						}
						if (content.size() > 20 && StringKit.isValid(content.get(20))) {
							teacherInfoBean.setPhone(content.get(20));
						}
						if (content.size() > 13 && StringKit.isValid(content.get(13))) {
							teacherInfoBean.setTechnicalTitle(content.get(13));
						}
						if (content.size() > 14 && StringKit.isValid(content.get(14))) {
							teacherInfoBean.setTechnicalGrade(MapKit.getValueFromMap(content.get(14).split("\\.")[0],
									TeacherInfoConfig.TEACHER_GRADE_MAP));
						}
						if (content.size() > 18 && StringKit.isValid(content.get(18))) {
							teacherInfoBean.setCoursesOffer(content.get(18));
						}
						condMap = new HashMap<String, String>();
						if (content.size() > 17 && StringKit.isValid(content.get(17))) {
							String typeMemo = content.get(16).split("\\.")[0];
							if (majorTypeMap.containsKey(typeMemo)) {
								TeacherMajorTypeBean bean = majorTypeMap.get(typeMemo);
								teacherInfoBean.setMajorTypeId(bean.getMajorId());
								teacherInfoBean.setTeacherType(MapKit.getValueFromMap(bean.getMajorName(),
										TeacherInfoConfig.TEACHER_TYPE_MAP));
							}

							String infoMemo = content.get(17).split("\\.")[0];
							if (majorInfoMap.containsKey(infoMemo)) {
								TeacherMajorInfoBean bean = majorInfoMap.get(infoMemo);
								teacherInfoBean.setMajorId(bean.getMajorId());
							}
						}
						if (!StringKit.isValid(teacherInfoBean.getTeacherType())) {
							teacherInfoBean.setTeacherType(String.valueOf(Constants.STATUS_NOT_VALID));
						}
						if (content.size() > 21 && StringKit.isValid(content.get(21))) {
							String relativeMaterial = content.get(21);
							teacherInfoBean.setRelativeMaterial(relativeMaterial
									.substring(relativeMaterial.lastIndexOf("/") + 1, relativeMaterial.length())); // 材料
						}
						if (content.size() > 23 && StringKit.isValid(content.get(23))) {
							Timestamp createTime = new Timestamp(
									DateKit.str2Date(content.get(23).split("[.]")[0], "yyyy-dd-MM HH:mm:ss").getTime());
							teacherInfoBean.setCreateTime(createTime);
						}
						if (content.size() > 25 && StringKit.isValid(content.get(25))) {
							if (!content.get(25).equals("0")) {
								teacherInfoBean.setStatus(Constants.STATUS_VALID);
							} else {
								continue;
							}
						}
						if (teacherInfoBean.getStatus() == Constants.STATUS_NOT_VALID) {
							continue;
						}
						int teacherId = teacherInfoBusiness.addTeacherInfoWithCreateTime(teacherInfoBean);
						teacherInfoBean.setTeacherId(teacherId);
						if (content.size() > 8 && StringKit.isValid(content.get(8))) {
							teacherRegionInfoBean = new TeacherRegionInfoBean();
							teacherRegionInfoBean.setTeacherId(teacherId);
							// 求地区级别及对应省市区
							TabRegionBusiness tabRegionBusiness = new TabRegionBusiness();
							TabRegionBean tabRegionBean = tabRegionBusiness.getTabRegionByServerId(content.get(8));
							if (tabRegionBean != null) {
								String[] sArray = tabRegionBean.getServerId().split("\\.");
								if (sArray.length == 1) {
									// 全国
									teacherRegionInfoBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
									teacherRegionInfoBean.setProvince(DeclareAdminConfig.COUNTRY_STR);
									teacherInfoBean.setProvince(DeclareAdminConfig.COUNTRY_STR);
								} else if (sArray.length == 2) {
									// 省
									teacherRegionInfoBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
									TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
									TabProvinceInfoBean tabProvinceInfoBean = tabProvinceInfoBusiness
											.getProvinceByCode(tabRegionBean.getCode());
									if (tabProvinceInfoBean != null) {
										teacherRegionInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
										teacherInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
									}
								} else if (sArray.length == 3) {
									// 市
									teacherRegionInfoBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
									TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
									TabCityInfoBean tabCityInfoBean = tabCityInfoBusiness
											.getCityByCode(tabRegionBean.getCode());
									if (tabCityInfoBean != null) {
										teacherRegionInfoBean.setCity(tabCityInfoBean.getCityName());
										teacherInfoBean.setCity(tabCityInfoBean.getCityName());
										TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
										TabProvinceInfoBean tabProvinceInfoBean = tabProvinceInfoBusiness
												.getTabProvinceInfoByKey(tabCityInfoBean.getProvinceId());
										if (tabProvinceInfoBean != null) {
											teacherRegionInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
											teacherInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
										}
									}
								} else if (sArray.length == 4) {
									// 区
									teacherRegionInfoBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
									TabRegionInfoBusiness tabRegionInfoBusiness = new TabRegionInfoBusiness();
									TabRegionInfoBean tabRegionInfoBean = tabRegionInfoBusiness
											.getTabRegionInfoByCode(tabRegionBean.getCode());
									if (tabRegionInfoBean != null) {
										teacherRegionInfoBean.setRegion(tabRegionInfoBean.getRegionName());
										teacherInfoBean.setRegion(tabRegionInfoBean.getRegionName());
										TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
										TabCityInfoBean tabCityInfoBean = tabCityInfoBusiness
												.getTabCityInfoByKey(tabRegionInfoBean.getCityId());
										if (tabCityInfoBean != null) {
											teacherRegionInfoBean.setCity(tabCityInfoBean.getCityName());
											teacherInfoBean.setCity(tabCityInfoBean.getCityName());
										}
										TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
										TabProvinceInfoBean tabProvinceInfoBean = tabProvinceInfoBusiness
												.getTabProvinceInfoByKey(tabRegionInfoBean.getProvinceId());
										if (tabProvinceInfoBean != null) {
											teacherRegionInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
											teacherInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
										}
									}
								}
							}
							result = teacherInfoBusiness.updateTeacherInfo(teacherInfoBean);
							if (result > 0) {
								result = teacherRegionInfoBusiness.addTeacherRegionInfo(teacherRegionInfoBean);
							}

							if (result < 0) {
								message.add(content.get(1) + "导入失败;");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		} finally {
		}
		if (result < 0) {
			HttpResponseKit.alertMessage(response, ListKit.list2String(message, " "),
					"/declare/migrate/DataMigrationImport.do");
		} else {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/migrate/DataMigrationImport.do");
		}
	}

}
