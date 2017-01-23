package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.TeacherInfoBusiness;
import com.lpmas.declare.admin.business.TrainingMaterialInfoBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.IndexInfoConfig;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * Servlet implementation class IndexInfoAjaxCount
 */
@WebServlet("/decalre/IndexInfoAjaxCount.do")
public class IndexInfoAjaxCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexInfoAjaxCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnMessageBean messageBean = new ReturnMessageBean();
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			messageBean.setMessage("你没有该功能的操作权限！");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}
		String queryMethod = ParamKit.getParameter(request, "queryMethod", "");
		if (!IndexInfoConfig.INDEX_QUERY_METHOD_MAP.containsKey(queryMethod)) {
			messageBean.setMessage("查询方法错误");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

		int result = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		HashMap<String, Object> mongoCondMap = new HashMap<String, Object>();
		mongoCondMap.put("status", Constants.STATUS_VALID);
		mongoCondMap.put("declareYear", String.valueOf(year));

		if (StringKit.isValid(adminUserInfoBean.getRegion())) {
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			condMap.put("region", adminUserInfoBean.getRegion());
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_REGION);
			mongoCondMap.put("province", adminUserInfoBean.getProvince());
			mongoCondMap.put("city", adminUserInfoBean.getCity());
			mongoCondMap.put("region", adminUserInfoBean.getRegion());
		} else if (StringKit.isValid(adminUserInfoBean.getCity())) {
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
			mongoCondMap.put("province", adminUserInfoBean.getProvince());
			mongoCondMap.put("city", adminUserInfoBean.getCity());
		} else if (StringKit.isValid(adminUserInfoBean.getProvince())) {
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			mongoCondMap.put("province", adminUserInfoBean.getProvince());
		} else {
			condMap.put("country", DeclareAdminConfig.COUNTRY_STR);
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
		}
		TeacherInfoBusiness teacherInfoBusiness = new TeacherInfoBusiness();
		TrainingMaterialInfoBusiness materialInfoBusiness = new TrainingMaterialInfoBusiness();
		TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
		DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();

		if (queryMethod.equals(IndexInfoConfig.GET_TEACHER_COUNT)) {
			result = teacherInfoBusiness.getTeacherRegionCountByMap(condMap);
		} else if (queryMethod.equals(IndexInfoConfig.GET_TEACHER_COUNT_INCRESAE_THIS_YEAR)) {
			condMap.put("beginTime", year + "-01-01 00:00:00");
			condMap.put("endTime", year + "-12-31 23:59:59");
			result = teacherInfoBusiness.getTeacherRegionCountByMap(condMap);
		} else if (queryMethod.equals(IndexInfoConfig.GET_TRAINING_MATERIAL_COUNT)) {
			result = materialInfoBusiness.getTrainingMaterialCountByMap(condMap);
		} else if (queryMethod.equals(IndexInfoConfig.GET_TRAINING_MATERIAL_COUNT_INCRESAE_THIS_YEAR)) {
			condMap.put("beginTime", year + "-01-01 00:00:00");
			condMap.put("endTime", year + "-12-31 23:59:59");
			result = materialInfoBusiness.getTrainingMaterialCountByMap(condMap);
		} else if (queryMethod.equals(IndexInfoConfig.GET_BASE_TRAINING_COUNT)) {
			condMap.put("organizationType", "ORGANIZATION_BASE_TRAINING");
			result = trainingOrganizationInfoBusiness.getTrainingOrganizationCountByMap(condMap);
		} else if (queryMethod.equals(IndexInfoConfig.GET_BASE_TRAINING_COUNT_INCRESAE_THIS_YEAR)) {
			condMap.put("organizationType", "ORGANIZATION_BASE_TRAINING");
			condMap.put("beginTime", year + "-01-01 00:00:00");
			condMap.put("endTime", year + "-12-31 23:59:59");
			result = trainingOrganizationInfoBusiness.getTrainingOrganizationCountByMap(condMap);
		} else if (queryMethod.equals(IndexInfoConfig.GET_YOUNG_FARMER_DECLARE_COUNT)
				|| queryMethod.equals(IndexInfoConfig.GET_PRODUCT_FARMER_DECLARE_COUNT)
				|| queryMethod.equals(IndexInfoConfig.GET_TECHNICAL_FARMER_DECLARE_COUNT)
				|| queryMethod.equals(IndexInfoConfig.GET_SERVICE_FARMER_DECLARE_COUNT)
				|| queryMethod.equals(IndexInfoConfig.GET_LEADER_FARMER_DECLARE_COUNT)) {
			// 范围条件处理
			List<String> listStatus = new ArrayList<String>();
			BasicDBList searchQueryCondition = new BasicDBList();
			listStatus.add(DeclareInfoConfig.DECLARE_STATUS_SUBMIT);
			listStatus.add(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE);
			listStatus.add(DeclareInfoConfig.DECLARE_STATUS_APPROVE);
			for (String queryStatus : listStatus) {
				BasicDBObject cond = new BasicDBObject();
				cond.append("declareStatus", queryStatus);
				searchQueryCondition.add(cond);
			}
			mongoCondMap.put("$or", searchQueryCondition);
			if (queryMethod.equals(IndexInfoConfig.GET_YOUNG_FARMER_DECLARE_COUNT)) {
				mongoCondMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
				result = declareReportBusiness.getDeclareReportCountByMap(mongoCondMap);
			} else if (queryMethod.equals(IndexInfoConfig.GET_PRODUCT_FARMER_DECLARE_COUNT)) {
				mongoCondMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
				result = declareReportBusiness.getDeclareReportCountByMap(mongoCondMap);
			} else if (queryMethod.equals(IndexInfoConfig.GET_TECHNICAL_FARMER_DECLARE_COUNT)) {
				mongoCondMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
				result = declareReportBusiness.getDeclareReportCountByMap(mongoCondMap);
			} else if (queryMethod.equals(IndexInfoConfig.GET_SERVICE_FARMER_DECLARE_COUNT)) {
				mongoCondMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
				result = declareReportBusiness.getDeclareReportCountByMap(mongoCondMap);
			} else if (queryMethod.equals(IndexInfoConfig.GET_LEADER_FARMER_DECLARE_COUNT)) {
				mongoCondMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
				result = declareReportBusiness.getDeclareReportCountByMap(mongoCondMap);
			}
		} else if (queryMethod.equals(IndexInfoConfig.GET_AUTH_PRODUCT_FARMER_DECLARE_COUNT)) {
			mongoCondMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
			mongoCondMap.put("authStatus", DeclareInfoConfig.AUTH_STATUS_APPROVE);
			result = declareReportBusiness.getDeclareReportCountByMap(mongoCondMap);
		} else if (queryMethod.equals(IndexInfoConfig.GET_AUTH_TECHNICAL_AND_SERVICE_FARMER_DECLARE_COUNT)) {
			mongoCondMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
			mongoCondMap.put("authStatus", DeclareInfoConfig.AUTH_STATUS_APPROVE);
			result = declareReportBusiness.getDeclareReportCountByMap(mongoCondMap);
			mongoCondMap.put("declareType", DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);
			result = result + declareReportBusiness.getDeclareReportCountByMap(mongoCondMap);
		}

		messageBean.setCode(Constants.STATUS_VALID);
		messageBean.setMessage(String.valueOf(result));
		HttpResponseKit.printJson(request, response, messageBean, "");
		return;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
