package com.lpmas.declare.admin.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.TrainingMaterialInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingMaterialInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingMaterialConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TrainingMaterialRemove
 */
@WebServlet("/declare/admin/TrainingMaterialRemove.do")
public class TrainingMaterialRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrainingMaterialRemove() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  AdminUserHelper adminHelper = new AdminUserHelper(request) ;
	  int trainingMaterialId = ParamKit.getIntParameter(request,"materialId",0) ; 
	  TrainingMaterialInfoBusiness business = new TrainingMaterialInfoBusiness() ;
	  String materialType = ParamKit.getParameter(request, "materialType","") ;
	  
	  if(materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)){
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.REMOVE)) {
				return;
			}
		}else if(materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)){
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.REMOVE)) {
				return;
			}
		}
	 
	  if(trainingMaterialId > 0){		  
		TrainingMaterialInfoBean bean =  business.getTrainingMaterialInfoByKey(trainingMaterialId) ;
		if(bean != null){
			if(bean.getStatus() == Constants.STATUS_VALID){
				bean.setStatus(Constants.STATUS_NOT_VALID);
				int result = business.updateTrainingMaterialInfo(bean) ;
				if (result > 0) {
					HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingMaterialInfoList.do?materialType="+materialType);
				} else {
					HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
				}
			}else{
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		}
	  }else{
		  HttpResponseKit.alertMessage(response, "无效的图书ID", HttpResponseKit.ACTION_HISTORY_BACK);
	  }
	  
	}

}
