package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.TrainingMaterialInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingMaterialInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingMaterialConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class TrainingMaterialInfoManage
 */
@WebServlet("/declare/admin/TrainingMaterialInfoManage.do")
public class TrainingMaterialInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingMaterialInfoManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int materialId = ParamKit.getIntParameter(request, "materialId", 0);
		String materialType = ParamKit.getParameter(request, "materialType", TrainingMaterialConfig.MATERIAL_GENERAL);  //获取教材类型
		
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		TrainingMaterialInfoBean bean = new TrainingMaterialInfoBean();
		if (materialId > 0) {
			if(materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)){
				if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.UPDATE)) {
					return;
				}
			}else if(materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)){
				if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.UPDATE)) {
					return;
				}
			}
			TrainingMaterialInfoBusiness business = new TrainingMaterialInfoBusiness();
			bean = business.getTrainingMaterialInfoByKey(materialId);
		} else {
			if(materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)){
				if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.CREATE)) {
					return;
				}
			}else if(materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)){
				if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.CREATE)) {
					return;
				}
			}
			bean.setStatus(Constants.STATUS_VALID);
		}

		request.setAttribute("TrainingMaterialInfoBean", bean);
		request.setAttribute("adminUserHelper", adminHelper);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingMaterialInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		String materialType = ParamKit.getParameter(request, "materialType", TrainingMaterialConfig.MATERIAL_GENERAL);
		//默认获取通用教材 
		TrainingMaterialInfoBean bean = new TrainingMaterialInfoBean();
		try {
			bean = BeanKit.request2Bean(request, TrainingMaterialInfoBean.class); // 前台参数保存到bean
			TrainingMaterialInfoBusiness business = new TrainingMaterialInfoBusiness();
			ReturnMessageBean messageBean = business.verifyTrainingMaterial(bean);
			if (StringKit.isValid(messageBean.getMessage())) {
				HttpResponseKit.alertMessage(response, messageBean.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			int result = 0;

			if (bean.getMaterialId() > 0) {
				if(materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)){
					if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.UPDATE)) {
						return;
					}
				}else if(materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)){
					if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.UPDATE)) {
						return;
					}
				}
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateTrainingMaterialInfo(bean);
			} else {
				if(materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)){
					if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.CREATE)) {
						return;
					}
				}else if(materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)){
					if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.CREATE)) {
						return;
					}
				}
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addTrainingMaterialInfo(bean);
			}
			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功",
						"/declare/admin/TrainingMaterialInfoList.do?materialType=" + materialType);
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {

		}
	}

}
