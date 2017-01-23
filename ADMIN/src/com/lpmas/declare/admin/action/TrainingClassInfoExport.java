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

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.MajorInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.TrainingClassInfoExportConfig;
import com.lpmas.declare.bean.MajorInfoBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.TrainingClassInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassInfoExport.do")
public class TrainingClassInfoExport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassInfoExport() {
		super();
		// TODO Auto-generated constructor stub
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
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int trainingType = ParamKit.getIntParameter(request, "trainingType", 0);
		if (!DeclareInfoConfig.DECLARE_TYPE_MAP.containsKey(trainingType)) {
			HttpResponseKit.alertMessage(response, "班级类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap = ParamKit.getParameterMap(request, "className,trainingYear,organizationId,majorTypeId,majorId", "");
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		// 根据用户级别，获取对应的地区信息
		if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
			// 国家级机构，可以选择省，市和区进行筛选
			String queryProvince = ParamKit.getParameter(request, "queryProvince", "").trim();
			if (StringKit.isValid(queryProvince)) {
				condMap.put("province", queryProvince);
			}
			String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
			if (StringKit.isValid(queryCity)) {
				condMap.put("city", queryCity);
			}
			String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
			// 省级机构，可以选择市和区进行筛选
			condMap.put("province", adminUserInfoBean.getProvince());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
			if (StringKit.isValid(queryCity)) {
				condMap.put("city", queryCity);
			}
			String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
			// 市级机构，可以选择区进行筛选
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			request.setAttribute("FixCity", adminUserInfoBean.getCity());
			String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)) {
			// 区级机构，不能客制化筛选
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			condMap.put("region", adminUserInfoBean.getRegion());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			request.setAttribute("FixCity", adminUserInfoBean.getCity());
			request.setAttribute("FixRegion", adminUserInfoBean.getRegion());
		}
		condMap.put("trainingType", String.valueOf(trainingType));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		TrainingClassInfoBusiness business = new TrainingClassInfoBusiness();
		List<TrainingClassInfoBean> result = business.getTrainingClassInfoListByMap(condMap);
		// 机构信息
		TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
		HashMap<String, String> trainingOrganizationInfoMap = new HashMap<String, String>();
		trainingOrganizationInfoMap.put("status", String.valueOf(Constants.STATUS_VALID));
		trainingOrganizationInfoMap.put("province", condMap.get("province"));
		trainingOrganizationInfoMap.put("city", condMap.get("city"));
		trainingOrganizationInfoMap.put("region", condMap.get("region"));
		List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = trainingOrganizationInfoBusiness
				.getTrainingOrganizationInfoListByMap(trainingOrganizationInfoMap);
		Map<Integer, String> organizationInfoMap = ListKit.list2Map(trainingOrganizationInfoList, "organizationId", "organizationName");
		// 产业类型和信息
		MajorInfoBusiness majorInfoBusiness = new MajorInfoBusiness();
		List<MajorInfoBean> majorInfoList = majorInfoBusiness.getMajorInfoAllList();
		Map<Integer, String> majorInfoMap = ListKit.list2Map(majorInfoList, "majorId", "majorName");
		List<List<Object>> contentList = new ArrayList<List<Object>>();
		List<Object> tempList = null;
		for (TrainingClassInfoBean bean : result) {
			tempList = new ArrayList<Object>();
			tempList.add(MapKit.getValueFromMap(bean.getOrganizationId(), organizationInfoMap));
			tempList.add(bean.getClassName());
			tempList.add(MapKit.getValueFromMap(bean.getTrainingType(), DeclareInfoConfig.DECLARE_TYPE_MAP));
			tempList.add(StringKit.isValid(MapKit.getValueFromMap(bean.getMajorId(), majorInfoMap))
					? MapKit.getValueFromMap(bean.getMajorId(), majorInfoMap) : bean.getTrainingPose());
			tempList.add(MapKit.getValueFromMap(bean.getClassStatus(), TrainingClassInfoConfig.ClASS_STATUS_MAP));
			tempList.add(bean.getClassPeopleQuantity());
			contentList.add(tempList);
		}

		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
		excelWriteBean.setFileName(MapKit.getValueFromMap(trainingType, DeclareInfoConfig.DECLARE_TYPE_MAP) + "班级管理");
		excelWriteBean.setFileType("xlsx");
		excelWriteBean.setHeaderList(TrainingClassInfoExportConfig.CLASS_INFO_HEADER_LIST);

		excelWriteBean.setContentList(contentList);
		WebExcelWriteKit webExcelWriteKit = new WebExcelWriteKit();
		webExcelWriteKit.outputExcel(excelWriteBean, request, response);
		return;

	}

}
