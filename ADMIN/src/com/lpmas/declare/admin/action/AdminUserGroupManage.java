package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserGroupBean;
import com.lpmas.declare.admin.business.AdminUserGroupBusiness;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class AdminUserGroupManage
 */
@WebServlet("/declare/admin/AdminUserGroupManage.do")
public class AdminUserGroupManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(AdminUserGroupManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserGroupManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int groupId = ParamKit.getIntParameter(request, "groupId", 0);
		AdminUserGroupBean bean = new AdminUserGroupBean();
		if (groupId > 0) {
			if (!adminHelper.checkPermission(DeclareAdminResource.ADMIN_USER, OperationConfig.UPDATE)) {
				return;
			}
			AdminUserGroupBusiness business = new AdminUserGroupBusiness();
			bean = business.getAdminUserGroupByKey(groupId);
		} else {
			if (!adminHelper.checkPermission(DeclareAdminResource.ADMIN_USER, OperationConfig.CREATE)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
		}

		request.setAttribute("UserGroup", bean);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "AdminUserGroupManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		AdminUserGroupBean bean = new AdminUserGroupBean();
		try {
			bean = BeanKit.request2Bean(request, AdminUserGroupBean.class);
			if (bean.getGroupId() > 0) {
				if (!adminHelper.checkPermission(DeclareAdminResource.ADMIN_USER, OperationConfig.UPDATE)) {
					return;
				}
			} else {
				if (!adminHelper.checkPermission(DeclareAdminResource.ADMIN_USER, OperationConfig.CREATE)) {
					return;
				}
			}

			AdminUserGroupBusiness business = new AdminUserGroupBusiness();
			if (business.isDuplicateUserGroup(bean.getGroupId(), bean.getProvince(), bean.getCity(), bean.getRegion())) {
				HttpResponseKit.alertMessage(response, "已存在相同的地区机构！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			if (StringKit.isValid(bean.getRegion())) {
				bean.setAdminGroupLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
			} else if (StringKit.isValid(bean.getCity())) {
				bean.setAdminGroupLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
			} else if (StringKit.isValid(bean.getProvince())){
				bean.setAdminGroupLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			}else{
				bean.setAdminGroupLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
			}
			int result = 0;
			if (bean.getGroupId() > 0) {
				result = business.updateAdminUserGroup(bean);
			} else {
				result = business.addAdminUserGroup(bean);
			}
			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/AdminUserGroupList.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("{}", e.toString());
		}
	}
}
