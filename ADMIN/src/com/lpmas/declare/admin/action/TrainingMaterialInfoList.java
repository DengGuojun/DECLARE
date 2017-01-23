package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;

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
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TrainingMaterialInfoList
 */
@WebServlet("/declare/admin/TrainingMaterialInfoList.do")
public class TrainingMaterialInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingMaterialInfoList() {
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
		
		String materialType = ParamKit.getParameter(request, "materialType", TrainingMaterialConfig.MATERIAL_GENERAL);  //获取教材类型
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		
		if(materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)){
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
				return ;
			}
		}else if(materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)){
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
				return;
			}
		}
		
		TrainingMaterialInfoBusiness business = new TrainingMaterialInfoBusiness();

		int pageNum = ParamKit.getIntParameter(request, "pageNum", 1);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", 20);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		// 处理查询条件
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, "materialName");

		String trainingYear = ParamKit.getParameter(request, "trainingYear", "");
		String province = ParamKit.getParameter(request, "province", "");
		String city = ParamKit.getParameter(request, "city", "");
		String region = ParamKit.getParameter(request, "region", "");
		String industry = ParamKit.getParameter(request, "industry", "");
		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("status", status); // 默认查询的是存在数据库的资料
		
		if (StringKit.isValid(trainingYear)) {
			condMap.put("trainingYear", trainingYear);
		}
		if (StringKit.isValid(industry)) {
			condMap.put("industry", industry);
		}
		if (StringKit.isValid(province)) {
			condMap.put("province", province);
		}
		if (StringKit.isValid(city)) {
			condMap.put("city", city);
		}
		if (StringKit.isValid(region)) {
			condMap.put("region", region);
		}
		if (StringKit.isValid(materialType)) {
			condMap.put("materialType", materialType);
		}

		PageResultBean<TrainingMaterialInfoBean> result = business.getTrainingMaterialInfoPageListByMap(condMap,
				pageBean);
		
		request.setAttribute("TrainingMaterialInfoList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("materialType", materialType); 
		request.setAttribute("adminUserHelper", adminHelper);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingMaterialInfoList.jsp");
		rd.forward(request, response);
	}
}
