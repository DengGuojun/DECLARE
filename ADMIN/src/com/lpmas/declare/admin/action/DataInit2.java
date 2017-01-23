package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminRoleUserBean;
import com.lpmas.declare.admin.bean.AdminUserGroupBean;
import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminRoleUserBusiness;
import com.lpmas.declare.admin.business.AdminUserGroupBusiness;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.config.Constants;

/**
 * Servlet implementation class AdminLogon
 */
@WebServlet("/init2.do")
public class DataInit2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataInit2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserGroupBusiness ugBusiness = new AdminUserGroupBusiness();
		AdminRoleUserBusiness roleUserBusiness = new AdminRoleUserBusiness();
		AdminUserInfoBusiness business = new AdminUserInfoBusiness();
		List<AdminUserGroupBean> list = ugBusiness.getAdminUserGroupAllList();
		int result = 0;
		int roleId = 0;
		for (AdminUserGroupBean groupBean : list) {
			// 管理用户
			AdminUserInfoBean userBean = new AdminUserInfoBean();
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
				userBean.setAdminUserName(groupBean.getProvince() + "管理用户");
				userBean.setProvince(groupBean.getProvince());
				roleId = 2;
			}
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
				userBean.setAdminUserName(groupBean.getProvince() + groupBean.getCity() + "管理用户");
				userBean.setProvince(groupBean.getProvince());
				userBean.setCity(groupBean.getCity());
				roleId = 3;
			}
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)) {
				userBean.setAdminUserName(groupBean.getProvince() + groupBean.getCity() + groupBean.getRegion()
						+ "管理用户");
				userBean.setProvince(groupBean.getProvince());
				userBean.setCity(groupBean.getCity());
				userBean.setRegion(groupBean.getRegion());
				roleId = 4;
			}
			userBean.setLoginId(groupBean.getGroupId() + "-admin");
			userBean.setLoginPassword(business.getCryptoPassword("NGXNGX123"));
			userBean.setAdminUserLevel(groupBean.getAdminGroupLevel());
			userBean.setAdminUserType(DeclareAdminConfig.ADMIN_TYPE_COMMON);
			userBean.setGroupId(groupBean.getGroupId());
			userBean.setStatus(Constants.STATUS_VALID);
			result = business.addAdminUserInfo(userBean);

			AdminRoleUserBean roleUserBean = new AdminRoleUserBean();
			roleUserBean.setUserId(result);
			roleUserBean.setRoleId(roleId);
			roleUserBean.setStatus(Constants.STATUS_VALID);
			roleUserBusiness.addAdminRoleUser(roleUserBean);

			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
				continue;
			}
			// 录入用户
			userBean = new AdminUserInfoBean();
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
				userBean.setAdminUserName(groupBean.getProvince() + "录入用户");
				userBean.setProvince(groupBean.getProvince());
				roleId = 5;
			}
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
				userBean.setAdminUserName(groupBean.getProvince() + groupBean.getCity() + "录入用户");
				userBean.setProvince(groupBean.getProvince());
				userBean.setCity(groupBean.getCity());
				roleId = 6;
			}
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)) {
				userBean.setAdminUserName(groupBean.getProvince() + groupBean.getCity() + groupBean.getRegion()
						+ "录入用户");
				userBean.setProvince(groupBean.getProvince());
				userBean.setCity(groupBean.getCity());
				userBean.setRegion(groupBean.getRegion());
				roleId = 7;
			}
			userBean.setLoginId(groupBean.getGroupId() + "-luru");
			userBean.setLoginPassword(business.getCryptoPassword("NGXNGX123"));
			userBean.setAdminUserLevel(groupBean.getAdminGroupLevel());
			userBean.setAdminUserType(DeclareAdminConfig.ADMIN_TYPE_COMMON);
			userBean.setGroupId(groupBean.getGroupId());
			userBean.setStatus(Constants.STATUS_VALID);
			result = business.addAdminUserInfo(userBean);

			roleUserBean = new AdminRoleUserBean();
			roleUserBean.setUserId(result);
			roleUserBean.setRoleId(roleId);
			roleUserBean.setStatus(Constants.STATUS_VALID);
			roleUserBusiness.addAdminRoleUser(roleUserBean);

			// 认定用户
			userBean = new AdminUserInfoBean();
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
				userBean.setAdminUserName(groupBean.getProvince() + "认定用户");
				userBean.setProvince(groupBean.getProvince());
				roleId = 8;
			}
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
				userBean.setAdminUserName(groupBean.getProvince() + groupBean.getCity() + "认定用户");
				userBean.setProvince(groupBean.getProvince());
				userBean.setCity(groupBean.getCity());
				roleId = 9;
			}
			if (groupBean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)) {
				userBean.setAdminUserName(groupBean.getProvince() + groupBean.getCity() + groupBean.getRegion()
						+ "认定用户");
				userBean.setProvince(groupBean.getProvince());
				userBean.setCity(groupBean.getCity());
				userBean.setRegion(groupBean.getRegion());
				roleId = 10;
			}
			userBean.setLoginId(groupBean.getGroupId() + "-rending");
			userBean.setLoginPassword(business.getCryptoPassword("NGXNGX123"));
			userBean.setAdminUserLevel(groupBean.getAdminGroupLevel());
			userBean.setAdminUserType(DeclareAdminConfig.ADMIN_TYPE_COMMON);
			userBean.setGroupId(groupBean.getGroupId());
			userBean.setStatus(Constants.STATUS_VALID);
			result = business.addAdminUserInfo(userBean);

			roleUserBean = new AdminRoleUserBean();
			roleUserBean.setUserId(result);
			roleUserBean.setRoleId(roleId);
			roleUserBean.setStatus(Constants.STATUS_VALID);
			roleUserBusiness.addAdminRoleUser(roleUserBean);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
