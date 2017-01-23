package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TrainingOrganizationRemove
 */
@WebServlet("/declare/admin/TrainingOrganizationRemove.do")
public class TrainingOrganizationRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingOrganizationRemove() {
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
		AdminUserHelper adminUserHelper = new AdminUserHelper(request);
		int organizationId = ParamKit.getIntParameter(request, "organizationId", 0);

		String organizationType = ParamKit.getParameter(request, "organizationType", "");

		if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO,
					OperationConfig.REMOVE)) {
				return;
			}
		} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO,
					OperationConfig.REMOVE)) {
				return;
			}
		} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO,
					OperationConfig.REMOVE)) {
				return;
			}
		}

		TrainingOrganizationInfoBean bean = new TrainingOrganizationInfoBean();
		if (StringKit.isValid(organizationType) && organizationId > 0) {
			TrainingOrganizationInfoBusiness business = new TrainingOrganizationInfoBusiness();

			bean = business.getTrainingOrganizationInfoByKey(organizationId);

			if (bean != null && bean.getStatus() == Constants.STATUS_VALID) {
				bean.setStatus(Constants.STATUS_NOT_VALID);
				int result = business.updateTrainingOrganizationInfo(bean);
				
				if (result > 0) {
					HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingOrganizationInfoList.do?organizationType="+organizationType);
				} else {
					HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
				}
				
			}else{
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		}
	}
}
