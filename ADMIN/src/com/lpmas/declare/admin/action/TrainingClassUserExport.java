package com.lpmas.declare.admin.action;

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

import com.lpmas.constant.user.GenderConfig;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.IndustryInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.config.TrainingClassInfoExportConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.IndustryInfoBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.FarmerInfoConfig;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassUserExport.do")
public class TrainingClassUserExport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TrainingClassUserExport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassUserExport() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		if (classId <= 0) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 根据班级ID查询班级信息
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean trainingClassInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (trainingClassInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		HashMap<String, String> condMap = new HashMap<String, String>();
		HashMap<String, List<String>> scopeMap = new HashMap<String, List<String>>();
		// 查产业
		IndustryInfoBusiness industryInfoBusiness = new IndustryInfoBusiness();
		List<IndustryInfoBean> industryInfoList = industryInfoBusiness.getIndustryInfoAllList();
		Map<Integer, String> industryInfoMap = new HashMap<Integer, String>();
		if (!industryInfoList.isEmpty()) {
			industryInfoMap = ListKit.list2Map(industryInfoList, "industryId", "industryName");
		}
		// 从Mongo中获取相应的数据
		DeclareReportBusiness business = new DeclareReportBusiness();
		try {
			condMap.put("declareYear", trainingClassInfoBean.getTrainingYear());
			condMap.put("declareStatus", DeclareInfoConfig.DECLARE_STATUS_APPROVE);
			condMap.put("declareType", String.valueOf(trainingClassInfoBean.getTrainingType()));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			condMap.put("province", trainingClassInfoBean.getProvince());
			condMap.put("city", trainingClassInfoBean.getCity());
			condMap.put("region", trainingClassInfoBean.getRegion());
			String userStatus = ParamKit.getParameter(request, "userStatus", "").trim();
			if (StringKit.isValid(userStatus)) {
				List<String> classInfoList = new ArrayList<String>();
				classInfoList.add(String.valueOf(trainingClassInfoBean.getClassId()));
				if (userStatus.equals(TrainingClassUserConfig.USER_STATUS_NOT_ADD)) {
					scopeMap.put(TrainingClassUserConfig.USER_STATUS_NOT_ADD, classInfoList);
				} else if (userStatus.equals(TrainingClassUserConfig.USER_STATUS_ADD)) {
					scopeMap.put(TrainingClassUserConfig.USER_STATUS_ADD, classInfoList);
				}
			}
			List<DeclareReportBean> result = business.getDeclareReportListByMap(condMap, scopeMap);
			List<List<Object>> contentList = new ArrayList<List<Object>>();
			List<Object> tempList = null;
			for (DeclareReportBean bean : result) {
				tempList = new ArrayList<Object>();
				tempList.add(bean.getRegion());
				tempList.add(bean.getUserName());
				tempList.add(MapKit.getValueFromMap(bean.getUserGender(), GenderConfig.GENDER_MAP));
				tempList.add(FarmerInfoConfig.EDUCATION_LEVEL_MAP.get(bean.getEducation()));
				tempList.add(bean.getIdentityNumber());
				if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {
					tempList.add(MapKit.getValueFromMap(bean.getFarmerType(), FarmerInfoConfig.YOUNG_FARMER_PERSONNEL_CATEGORY_MAP));
				} else if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
					tempList.add(MapKit.getValueFromMap(bean.getFarmerType(), FarmerInfoConfig.PRODUCT_FARMER_PERSONNEL_CATEGORY_MAP));
				} else if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
					tempList.add(MapKit.getValueFromMap(bean.getFarmerType(), FarmerInfoConfig.LEADER_FARMER_PERSONNEL_CATEGORY_MAP));
				} else {
					tempList.add("");
				}
				tempList.add(bean.getUserMobile());
				if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER
						|| bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER
						|| bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
					tempList.add(MapKit.getValueFromMap(bean.getIndustryId1(), industryInfoMap));
					tempList.add(bean.getIndustryScale1() + bean.getIndustryUnit1());
				}
				boolean flag = false;
				if (bean.getTrainingClassInfoList() != null && !bean.getTrainingClassInfoList().isEmpty()) {
					for (Integer calssInfo : bean.getTrainingClassInfoList()) {
						if (calssInfo == trainingClassInfoBean.getClassId()) {
							flag = true;
						}
					}
				}
				if (flag) {
					tempList.add("已添加");
				} else {
					tempList.add("未添加");
				}
				contentList.add(tempList);
			}
			ExcelWriteBean excelWriteBean = new ExcelWriteBean();
			excelWriteBean.setFileName(trainingClassInfoBean.getClassName() + "学员管理");
			excelWriteBean.setFileType("xlsx");
			if (trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER
					|| trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER
					|| trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
				excelWriteBean.setHeaderList(TrainingClassInfoExportConfig.CLASS_USER_HEADER_LIST);
			} else {
				excelWriteBean.setHeaderList(TrainingClassInfoExportConfig.CLASS_USER_SECOND_HEADER_LIST);
			}
			excelWriteBean.setContentList(contentList);
			WebExcelWriteKit webExcelWriteKit = new WebExcelWriteKit();
			webExcelWriteKit.outputExcel(excelWriteBean, request, response);
			return;
		} catch (Exception e) {
			log.error("", e);
		}

	}

}
