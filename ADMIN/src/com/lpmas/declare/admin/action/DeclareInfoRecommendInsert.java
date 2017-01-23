package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareInfoBusiness;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.FarmerContactInfoBusiness;
import com.lpmas.declare.admin.business.FarmerIndustryInfoBusiness;
import com.lpmas.declare.admin.business.FarmerInfoBusiness;
import com.lpmas.declare.admin.business.FarmerJobInfoBusiness;
import com.lpmas.declare.admin.business.FarmerSkillInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.handler.DeclareReportHandler;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.FarmerContactInfoBean;
import com.lpmas.declare.bean.FarmerIndustryInfoBean;
import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.bean.FarmerJobInfoBean;
import com.lpmas.declare.bean.FarmerSkillInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;

@WebServlet("/declare/admin/DeclareInfoRecommendInsert.do")
public class DeclareInfoRecommendInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DeclareInfoRecommendInsert.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoRecommendInsert() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND, OperationConfig.CREATE)) {
			return;
		}
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "DeclareInfoRecommendInsert.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND, OperationConfig.CREATE)) {
			return;
		}
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		int declareId = 0;
		int resultFarmerInfo = 0;
		int resultFarmerContactInfo = 0;
		int resultMongo = 0;
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
		FarmerContactInfoBusiness farmerContactInfoBusiness = new FarmerContactInfoBusiness();
		// 映射Bean
		DeclareInfoBean declareInfoBean = BeanKit.request2Bean(request, DeclareInfoBean.class);
		FarmerInfoBean farmerInfoBean = BeanKit.request2Bean(request, FarmerInfoBean.class);
		FarmerContactInfoBean farmerContactInfoBean = BeanKit.request2Bean(request, FarmerContactInfoBean.class);
		// 查mongo根据身份证和年份判是否重复
		DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("identityNumber", farmerInfoBean.getIdentityNumber());
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		try {
			List<DeclareReportBean> declareReportList = declareReportBusiness.getDeclareReportListByMap(condMap, new HashMap<String, List<String>>());
			if (declareReportList != null && !declareReportList.isEmpty()
					&& Math.abs(Integer.valueOf(declareInfoBean.getDeclareYear()) - Integer.valueOf(declareReportList.get(0).getDeclareYear())) < 3) {
				HttpResponseKit.alertMessage(response, "同一身份证三年内不能重复申报", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 获取用户的userId
		int userId = declareInfoBusiness.getUserIdByUserClient(farmerContactInfoBean.getUserMobile());

		// 新建申报信息
		declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_NOT_SUBMIT);
		declareInfoBean.setDeclareCategory(DeclareInfoConfig.DECLARE_CATEGORY_SPARE);
		declareInfoBean.setAuthStatus(DeclareInfoConfig.AUTH_STATUS_WAIT_APPROVE);
		declareInfoBean.setRegistryType(DeclareInfoConfig.REGISTRY_TYPE_RECOMMEND);
		declareInfoBean.setUserId(userId);
		declareInfoBean.setStatus(Constants.STATUS_VALID);
		declareInfoBean.setCreateUser(adminHelper.getAdminUserId());
		declareId = declareInfoBusiness.addDeclareInfo(declareInfoBean);
		if (declareId <= 0) {
			HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 新建农民信息
		farmerInfoBean.setCreateUser(adminHelper.getAdminUserId());
		farmerInfoBean.setStatus(Constants.STATUS_VALID);
		farmerInfoBean.setDeclareId(declareId);
		resultFarmerInfo = farmerInfoBusiness.addFarmerInfo(farmerInfoBean);
		// 新建联系信息
		if (StringKit.isValid(adminUserInfoBean.getProvince())) {
			farmerContactInfoBean.setProvince(adminUserInfoBean.getProvince());
		}
		if (StringKit.isValid(adminUserInfoBean.getCity())) {
			farmerContactInfoBean.setCity(adminUserInfoBean.getCity());
		}
		if (StringKit.isValid(adminUserInfoBean.getRegion())) {
			farmerContactInfoBean.setRegion(adminUserInfoBean.getRegion());
		}
		farmerContactInfoBean.setCreateUser(adminHelper.getAdminUserId());
		farmerContactInfoBean.setStatus(Constants.STATUS_VALID);
		farmerContactInfoBean.setDeclareId(declareId);
		resultFarmerContactInfo = farmerContactInfoBusiness.addFarmerContactInfo(farmerContactInfoBean);
		// 新建工作表
		FarmerJobInfoBean farmerJobInfoBean = new FarmerJobInfoBean();
		farmerJobInfoBean.setStatus(Constants.STATUS_VALID);
		farmerJobInfoBean.setDeclareId(declareId);
		farmerJobInfoBean.setCreateUser(adminHelper.getAdminUserId());
		FarmerJobInfoBusiness farmerJobInfoBusiness = new FarmerJobInfoBusiness();
		farmerJobInfoBusiness.addFarmerJobInfo(farmerJobInfoBean);
		// 新建技能表
		FarmerSkillInfoBean farmerSkillInfoBean = new FarmerSkillInfoBean();
		farmerSkillInfoBean.setStatus(Constants.STATUS_VALID);
		farmerSkillInfoBean.setDeclareId(declareId);
		farmerSkillInfoBean.setCreateUser(adminHelper.getAdminUserId());
		FarmerSkillInfoBusiness farmerSkillInfoBusiness = new FarmerSkillInfoBusiness();
		farmerSkillInfoBusiness.addFarmerSkillInfo(farmerSkillInfoBean);
		// 新建产业表
		FarmerIndustryInfoBean farmerIndustryInfoBean = new FarmerIndustryInfoBean();
		if (StringKit.isValid(adminUserInfoBean.getProvince())) {
			farmerIndustryInfoBean.setIndustryProvince(adminUserInfoBean.getProvince());
		}
		if (StringKit.isValid(adminUserInfoBean.getCity())) {
			farmerIndustryInfoBean.setIndustryProvince(adminUserInfoBean.getCity());
		}
		if (StringKit.isValid(adminUserInfoBean.getRegion())) {
			farmerIndustryInfoBean.setIndustryProvince(adminUserInfoBean.getRegion());
		}
		farmerIndustryInfoBean.setStatus(Constants.STATUS_VALID);
		farmerIndustryInfoBean.setDeclareId(declareId);
		farmerIndustryInfoBean.setCreateUser(adminHelper.getAdminUserId());
		FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();
		farmerIndustryInfoBusiness.addFarmerIndustryInfo(farmerIndustryInfoBean);

		// 插入mongo
		DeclareReportHandler handler = new DeclareReportHandler();
		try {
			declareInfoBean.setDeclareId(declareId);
			handler.createDeclareReport(declareInfoBean);
			resultMongo = Constants.STATUS_VALID;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("总表记录创建失败", e);
		}
		if (resultMongo <= 0 || resultFarmerInfo < 0 || resultFarmerContactInfo < 0) {
			HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} else {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/DeclareInfoRecommendList.do");
			return;
		}
	}
}
