package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.TrainingClassTargetBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingClassTargetBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

@WebServlet("/declare/admin/TrainingClassTargetManage.do")
public class TrainingClassTargetManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassTargetManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_TARGET, OperationConfig.SEARCH)) {
			return;
		}
		int level = ParamKit.getIntParameter(request, "level", 0);
		request.setAttribute("level", level);
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		HashMap<String, String> condMap = new HashMap<String, String>();
		// 根据用户级别，获取对应的地区信息
		if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
			// 国家级机构
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
			// 省级机构
			condMap.put("province", adminUserInfoBean.getProvince());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());

		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
			// 市级机构
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			request.setAttribute("FixCity", adminUserInfoBean.getCity());
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)) {
			// 区级机构
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			condMap.put("region", adminUserInfoBean.getRegion());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			request.setAttribute("FixCity", adminUserInfoBean.getCity());
			request.setAttribute("FixRegion", adminUserInfoBean.getRegion());
		}
		// 机构信息
		TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("organizationType", TrainingOrganizationConfig.ORGANIZATION_TRAINING);
		List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = new ArrayList<TrainingOrganizationInfoBean>();
		if (level == 1) {
			for (TrainingOrganizationInfoBean trainingOrganizationInfoBean : trainingOrganizationInfoBusiness
					.getTrainingOrganizationInfoListByMap(condMap)) {
				if (trainingOrganizationInfoBean.getProvince().equals(adminUserInfoBean.getProvince())
						&& trainingOrganizationInfoBean.getCity().equals(adminUserInfoBean.getCity())
						&& trainingOrganizationInfoBean.getRegion().equals(adminUserInfoBean.getRegion())) {
					trainingOrganizationInfoList.add(trainingOrganizationInfoBean);
				}
			}
		} else if (level == 2) {
			if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
				// 省级机构
				for (TrainingOrganizationInfoBean trainingOrganizationInfoBean : trainingOrganizationInfoBusiness
						.getTrainingOrganizationInfoListByMap(condMap)) {
					if (trainingOrganizationInfoBean.getProvince().equals(adminUserInfoBean.getProvince())
							&& (StringKit.isValid(trainingOrganizationInfoBean.getCity())
									|| StringKit.isValid(trainingOrganizationInfoBean.getRegion()))) {
						trainingOrganizationInfoList.add(trainingOrganizationInfoBean);
					}
				}
			} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
				// 市级机构
				for (TrainingOrganizationInfoBean trainingOrganizationInfoBean : trainingOrganizationInfoBusiness
						.getTrainingOrganizationInfoListByMap(condMap)) {
					if (trainingOrganizationInfoBean.getProvince().equals(adminUserInfoBean.getProvince())
							&& trainingOrganizationInfoBean.getCity().equals(adminUserInfoBean.getCity())
							&& StringKit.isValid(trainingOrganizationInfoBean.getRegion())) {
						trainingOrganizationInfoList.add(trainingOrganizationInfoBean);
					}
				}
			} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)) {
				// 区级机构
				for (TrainingOrganizationInfoBean trainingOrganizationInfoBean : trainingOrganizationInfoBusiness
						.getTrainingOrganizationInfoListByMap(condMap)) {
					if (trainingOrganizationInfoBean.getProvince().equals(adminUserInfoBean.getProvince())
							&& trainingOrganizationInfoBean.getCity().equals(adminUserInfoBean.getCity())
							&& trainingOrganizationInfoBean.getRegion().equals(adminUserInfoBean.getRegion())) {
						trainingOrganizationInfoList.add(trainingOrganizationInfoBean);
					}
				}
			}
		} else {
			HttpResponseKit.alertMessage(response, "参数非法！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		request.setAttribute("trainingOrganizationInfoList", trainingOrganizationInfoList);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingClassTargetManage.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_TARGET, OperationConfig.CREATE)) {
			return;
		}
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		int result = 0;
		TrainingClassTargetBusiness trainingClassTargetBusiness = new TrainingClassTargetBusiness();
		// 映射Bean
		TrainingClassTargetBean trainingClassTargetBean = BeanKit.request2Bean(request, TrainingClassTargetBean.class);
		// 根据用户级别，获取对应的地区信息
		if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
			// 国家级机构
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
			// 省级机构
			trainingClassTargetBean.setProvince(adminUserInfoBean.getProvince());
			String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
			if (StringKit.isValid(queryCity)) {
				trainingClassTargetBean.setCity(queryCity);
			}
			String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
			if (StringKit.isValid(queryRegion)) {
				trainingClassTargetBean.setRegion(queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
			// 市级机构
			trainingClassTargetBean.setProvince(adminUserInfoBean.getProvince());
			trainingClassTargetBean.setCity(adminUserInfoBean.getCity());
			String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
			if (StringKit.isValid(queryRegion)) {
				trainingClassTargetBean.setRegion(queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)) {
			// 区级机构
			trainingClassTargetBean.setProvince(adminUserInfoBean.getProvince());
			trainingClassTargetBean.setCity(adminUserInfoBean.getCity());
			trainingClassTargetBean.setRegion(adminUserInfoBean.getRegion());
		}
		if (trainingClassTargetBean.getOrganizationId() != 0) {
			TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
			TrainingOrganizationInfoBean trainingOrganizationInfoBean = trainingOrganizationInfoBusiness
					.getTrainingOrganizationInfoByKey(trainingClassTargetBean.getOrganizationId());
			trainingClassTargetBean.setProvince(trainingOrganizationInfoBean.getProvince());
			trainingClassTargetBean.setCity(trainingOrganizationInfoBean.getCity());
			trainingClassTargetBean.setRegion(trainingOrganizationInfoBean.getRegion());
		}
		ReturnMessageBean messageBean = trainingClassTargetBusiness.verifyTrainingClassTarget(trainingClassTargetBean);
		if (StringKit.isValid(messageBean.getMessage())) {
			HttpResponseKit.alertMessage(response, messageBean.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		trainingClassTargetBean.setStatus(Constants.STATUS_VALID);
		trainingClassTargetBean.setCreateUser(adminHelper.getAdminUserId());
		result = trainingClassTargetBusiness.addTrainingClassTarget(trainingClassTargetBean);
		if (result <= 0) {
			HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} else {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingClassTargetList.do");
		}

	}

}
