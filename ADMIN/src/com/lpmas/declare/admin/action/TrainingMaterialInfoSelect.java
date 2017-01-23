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

import com.lpmas.declare.admin.bean.TrainingMaterialInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingMaterialInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingMaterialConfig;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingMaterialInfoSelect.do")
public class TrainingMaterialInfoSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingMaterialInfoSelect() {
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

		String materialType = ParamKit.getParameter(request, "materialType", TrainingMaterialConfig.MATERIAL_GENERAL); // 获取教材类型
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);

		if (materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO,
					OperationConfig.SEARCH)) {
				return;
			}
		} else if (materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO,
					OperationConfig.SEARCH)) {
				return;
			}
		}

		int classId = ParamKit.getIntParameter(request, "classId", 0);
		if (classId <= 0) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 根据班级ID查询班级信息
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean trainingClassInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (trainingClassInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		TrainingMaterialInfoBusiness business = new TrainingMaterialInfoBusiness();

		// 处理查询条件
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, "materialName");

		String province = trainingClassInfoBean.getProvince();
		String city = trainingClassInfoBean.getCity();
		String region = trainingClassInfoBean.getRegion();
		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("status", status); // 默认查询的是存在数据库的资料

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

		String clickId = ParamKit.getParameter(request, "clickId", "");
		request.setAttribute("clickId", clickId);

		List<TrainingMaterialInfoBean> trainingMaterialInfoList = business.getAllTrainingMaterial(condMap);

		request.setAttribute("TrainingMaterialInfoList", trainingMaterialInfoList);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingMaterialInfoSelect.jsp");
		rd.forward(request, response);
	}

}
