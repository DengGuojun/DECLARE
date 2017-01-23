package com.lpmas.declare.admin.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.GovernmentOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.GovernmentOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class GovernmentOrganizationInfoRemove
 */
@WebServlet("/declare/admin/GovernmentOrganizationInfoRemove.do")
public class GovernmentOrganizationInfoRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GovernmentOrganizationInfoRemove() {
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
	 
		if (!adminUserHelper.checkPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO, OperationConfig.REMOVE)) {
			return;
		}
		GovernmentOrganizationInfoBean bean = new GovernmentOrganizationInfoBean() ;
		if(organizationId > 0){
			GovernmentOrganizationInfoBusiness business = new GovernmentOrganizationInfoBusiness() ;
			bean = business.getGovernmentOrganizationInfoByKey(organizationId) ;
			
			if(bean!= null && bean.getStatus() == Constants.STATUS_VALID){
				bean.setStatus(Constants.STATUS_NOT_VALID);
				int result = business.updateGovernmentOrganizationInfo(bean) ;
				
				if(result > 0){
					HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/GovernmentOrganizationInfoList.do");
				} else {
					HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
				}
			}else{
				HttpResponseKit.alertMessage(response, "请重新操作", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		}else{
			HttpResponseKit.alertMessage(response, "错误的ID", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}

}
