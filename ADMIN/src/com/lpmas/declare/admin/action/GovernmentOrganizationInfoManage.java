package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.GovernmentOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserGroupBusiness;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.GovernmentOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class GovernmentOrganizationInfoManage
 */
@WebServlet("/declare/admin/GovernmentOrganizationInfoManage.do")
public class GovernmentOrganizationInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GovernmentOrganizationInfoManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int organizationId = ParamKit.getIntParameter(request, "organizationId", 0);

		GovernmentOrganizationInfoBean bean = new GovernmentOrganizationInfoBean();
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (organizationId > 0) {
			if (!adminHelper.checkPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO,
					OperationConfig.SEARCH)) {
				return;
			}
			GovernmentOrganizationInfoBusiness business = new GovernmentOrganizationInfoBusiness();
			bean = business.getGovernmentOrganizationInfoByKey(organizationId);
		} else {
			if (!adminHelper.checkPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO,
					OperationConfig.CREATE)) {
				return;
			}
			//只能增加本级的主管部门
			AdminUserGroupBusiness userGroupBusiness = new AdminUserGroupBusiness();
			String organizationLevel = userGroupBusiness.getAdminUserGroupByKey(adminHelper.getAdminUserInfo().getGroupId()).getAdminGroupLevel();
			bean.setOrganizationLevel(organizationLevel);
			bean.setProvince(adminHelper.getAdminUserInfo().getProvince());
			bean.setCity(adminHelper.getAdminUserInfo().getCity());
			bean.setRegion(adminHelper.getAdminUserInfo().getRegion());
			bean.setStatus(Constants.STATUS_VALID);
		}
		request.setAttribute("GovernmentOrganization", bean);
		request.setAttribute("adminUserHelper", adminHelper);

		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "GovernmentOrganizationInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GovernmentOrganizationInfoBean bean = new GovernmentOrganizationInfoBean();
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		try {
			bean = BeanKit.request2Bean(request, GovernmentOrganizationInfoBean.class);
			GovernmentOrganizationInfoBusiness business = new GovernmentOrganizationInfoBusiness();
			ReturnMessageBean returnMessageBean = business.verifyGovernmentOrganizationInfo(bean);
			if (StringKit.isValid(returnMessageBean.getMessage())) {
				HttpResponseKit.alertMessage(response, returnMessageBean.getMessage(),
						HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			//确认主管部门等级等级
			if (StringKit.isValid(adminHelper.getAdminUserInfo().getRegion())) {
				bean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
			} else if (StringKit.isValid(adminHelper.getAdminUserInfo().getCity())) {
				bean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
			} else if (StringKit.isValid(adminHelper.getAdminUserInfo().getProvince())) {
				bean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			}
			
			int result = 0;
			if (bean.getOrganizationId() > 0) {
				if (!adminHelper.checkPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO,
						OperationConfig.UPDATE)) {
					return;
				}
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateGovernmentOrganizationInfo(bean);
			} else {
				if (!adminHelper.checkPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO,
						OperationConfig.CREATE)) {
					return;
				}
			
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addGovernmentOrganizationInfo(bean);
			}
			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/GovernmentOrganizationInfoList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {

		}
	}
}
