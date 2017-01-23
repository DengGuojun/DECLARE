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

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.TrainingClassEvaluateBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.TrainingClassEvaluateBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.TrainingClassInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassEvaluateManage.do")
public class TrainingClassEvaluateManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(TrainingClassEvaluateManage.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassEvaluateManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_EVALUATE, OperationConfig.SEARCH)) {
			return;
		}
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean classInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (classInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		String evaluateType = ParamKit.getParameter(request, "evaluateType", "");
		if(!TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_MAP.containsKey(evaluateType)){
			HttpResponseKit.alertMessage(response, "评价类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		TrainingClassEvaluateBusiness business = new TrainingClassEvaluateBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("classId", String.valueOf(classId));
		condMap.put("evaluateType", evaluateType);
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		List<TrainingClassEvaluateBean> list = business.getTrainingClassEvaluateListByMap(condMap);
		//获取评教人的姓名(国家级和省级能看到评教人姓名，市级和县级看到的是“匿名”)
		HashMap<Integer,String> evaluateUserMap = new HashMap<Integer,String>();
		if(adminHelper.getAdminUserInfo().getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)||
				adminHelper.getAdminUserInfo().getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)){
			DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
			for(TrainingClassEvaluateBean bean : list){
				try {
					DeclareReportBean declareReportBean = declareReportBusiness.getDeclareReportByKey(String.valueOf(bean.getDeclareId()));
					evaluateUserMap.put(bean.getEvaluateId(), declareReportBean.getUserName());
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
		
		
		request.setAttribute("TrainingClassEvaluateList", list);
		request.setAttribute("EvaluateUserMap", evaluateUserMap);
		request.setAttribute("TrainingClassInfoBean", classInfoBean);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH
				+ "TrainingClassEvaluateManage.jsp");
		rd.forward(request, response);

	}

}
