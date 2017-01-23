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

import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingOrganizationInfoSelect.do")
public class TrainingOrganizationInfoSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingOrganizationInfoSelect() {
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
		TrainingOrganizationInfoBusiness business = new TrainingOrganizationInfoBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		String queryProvince = ParamKit.getParameter(request, "queryProvince", "").trim();
		if (StringKit.isValid(queryProvince) && !queryProvince.equals("请选择") && !queryProvince.equals("全部")) {
			condMap.put("province", queryProvince);
		}
		String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
		if (StringKit.isValid(queryCity) && !queryCity.equals("请选择") && !queryCity.equals("全部")) {
			condMap.put("city", queryCity);
		}
		String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
		if (StringKit.isValid(queryRegion) && !queryRegion.equals("请选择") && !queryRegion.equals("全部")) {
			condMap.put("region", queryRegion);
		}
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		if (classId != 0) {
			// 根据班级ID查询班级信息
			TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
			TrainingClassInfoBean trainingClassInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
			condMap.put("province", trainingClassInfoBean.getProvince());
			condMap.put("city", trainingClassInfoBean.getCity());
			condMap.put("region", trainingClassInfoBean.getRegion());
		}
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("organizationType", TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE);
		List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = business
				.getTrainingOrganizationInfoListByMap(condMap);

		request.setAttribute("TrainingOrganizationInfoList", trainingOrganizationInfoList);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingOrganizationInfoSelect.jsp");
		rd.forward(request, response);
	}

}
