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
import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.IndustryTypeBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareInfoRecommendConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.IndustryTypeBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.FarmerInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/DeclareInfoExport.do")
public class DeclareInfoExport extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(DeclareInfoExport.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoExport() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		HashMap<String, String> condMap = new HashMap<String, String>();
		String userName = ParamKit.getParameter(request, "userName", "").trim();
		if (StringKit.isValid(userName)) {
			condMap.put("userName", userName);
		}
		String userMobile = ParamKit.getParameter(request, "userMobile", "").trim();
		if (StringKit.isValid(userMobile)) {
			condMap.put("userMobile", userMobile);
		}
		String identityNumber = ParamKit.getParameter(request, "identityNumber", "").trim();
		if (StringKit.isValid(identityNumber)) {
			condMap.put("identityNumber", identityNumber);
		}
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		HashMap<String, List<String>> scopeMap = new HashMap<String, List<String>>();
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
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
		int declareType = ParamKit.getIntParameter(request, "declareType", 0);
		if (!DeclareInfoConfig.DECLARE_TYPE_MAP.containsKey(declareType)) {
			HttpResponseKit.alertMessage(response, "对象类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		condMap.put("declareType", String.valueOf(declareType));
		// 查产业类型和产业

		IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
		List<IndustryTypeBean> industryTypeList = industryTypeBusiness.getIndustryTypeAllList();
		Map<Integer, IndustryTypeBean> industryTypeMap = new HashMap<Integer, IndustryTypeBean>();
		if (!industryTypeList.isEmpty()) {
			industryTypeMap = ListKit.list2Map(industryTypeList, "typeId");
		}
		// 从Mongo中获取相应的数据
		DeclareReportBusiness business = new DeclareReportBusiness();
		try {
			String declareStatus = ParamKit.getParameter(request, "declareStatus", "").trim();
			if (StringKit.isValid(declareStatus)) {
				condMap.put("declareStatus", declareStatus);
			}
			List<String> reviewStatusList = new ArrayList<String>();
			reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_SUBMIT);
			reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE);
			reviewStatusList.add(DeclareInfoConfig.DECLARE_STATUS_APPROVE);
			scopeMap.put("declareStatus", reviewStatusList);
			List<DeclareReportBean> result = business.getDeclareReportListByMap(condMap,scopeMap);
			List<List<Object>> contentList = new ArrayList<List<Object>>();
			List<Object> tempList = null;
			for (DeclareReportBean bean : result) {
				tempList = new ArrayList<Object>();
				tempList.add(bean.getUserName());
				tempList.add(MapKit.getValueFromMap(bean.getUserGender(), GenderConfig.GENDER_MAP));
				tempList.add(FarmerInfoConfig.EDUCATION_LEVEL_MAP.get(bean.getEducation()));
				tempList.add(bean.getIdentityNumber());
				tempList.add(bean.getUserMobile());
				if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {
					tempList.add(MapKit.getValueFromMap(bean.getFarmerType(),
							FarmerInfoConfig.YOUNG_FARMER_PERSONNEL_CATEGORY_MAP));
				} else if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {
					tempList.add(MapKit.getValueFromMap(bean.getFarmerType(),
							FarmerInfoConfig.PRODUCT_FARMER_PERSONNEL_CATEGORY_MAP));
				} else if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
					tempList.add(MapKit.getValueFromMap(bean.getFarmerType(),
							FarmerInfoConfig.LEADER_FARMER_PERSONNEL_CATEGORY_MAP));
				} else {
					tempList.add("");
				}
				if (declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER
						|| declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER
						|| declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
					if (bean.getIndustryTypeId1() != 0) {
						if (industryTypeMap.get(bean.getIndustryTypeId1()) != null) {
							tempList.add(industryTypeMap.get(bean.getIndustryTypeId1()).getTypeName());
						}
					} else {
						tempList.add("");
					}
				}
				tempList.add(MapKit.getValueFromMap(bean.getRegistryType(), DeclareInfoConfig.REGISTRY_TYPE_MAP));
				tempList.add(bean.getProvince() + bean.getCity() + bean.getRegion());
				tempList.add(DeclareInfoRecommendConfig.REVIEW_STATUS_MAP.get(bean.getDeclareStatus()));
				contentList.add(tempList);
			}
			ExcelWriteBean excelWriteBean = new ExcelWriteBean();
			excelWriteBean.setFileName("对象管理");
			excelWriteBean.setFileType("xlsx");
			if (declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER
					|| declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER
					|| declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {
				excelWriteBean.setHeaderList(DeclareInfoRecommendConfig.DECLARE_REPORT_HEADER_LIST);
			} else {
				excelWriteBean.setHeaderList(DeclareInfoRecommendConfig.DECLARE_REPORT_SECOND_HEADER_LIST);
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
