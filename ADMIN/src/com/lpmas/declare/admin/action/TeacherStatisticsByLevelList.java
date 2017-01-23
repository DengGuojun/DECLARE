package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.bean.TeacherStatisticsBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TeacherInfoBusiness;
import com.lpmas.declare.admin.business.TeacherStatisticsBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TeacherStatisticsByLevelList
 */
@WebServlet("/declare/admin/TeacherStatisticsByLevelList.do")
public class TeacherStatisticsByLevelList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherStatisticsByLevelList() {
		super();
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
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		AdminUserInfoBean adminUserInfoBean = adminUserHelper.getAdminUserInfo();
		if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_STATISTICS_BY_LEVEL, OperationConfig.SEARCH)) {
			return;
		}
		String queryProvince = ParamKit.getParameter(request, "queryProvince", "");
		String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
		String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
		HashMap<String, String> condMap = new HashMap<String, String>();
		if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
			// 国家级机构，可以选择省，市和区进行筛选
			if (StringKit.isValid(queryProvince)) {
				condMap.put("province", queryProvince);
			} else {
				condMap.put("province", DeclareAdminConfig.COUNTRY_STR);
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
			}
			if (StringKit.isValid(queryCity)) {
				condMap.put("city", queryCity);
			}
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
			// 省级机构，可以选择市和区进行筛选
			condMap.put("province", adminUserInfoBean.getProvince());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			if (StringKit.isValid(queryCity)) {
				condMap.put("city", queryCity);
			}
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
			// 市级机构，可以选择区进行筛选
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			request.setAttribute("FixCity", adminUserInfoBean.getCity());
			if (StringKit.isValid(queryRegion)) {
				condMap.put("region", queryRegion);
			}
		} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)) {
			// 区级机构，不能客制化筛选
			condMap.put("province", adminUserInfoBean.getProvince());
			condMap.put("city", adminUserInfoBean.getCity());
			condMap.put("region", adminUserInfoBean.getRegion());
			request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
			request.setAttribute("FixCity", adminUserInfoBean.getCity());
			request.setAttribute("FixRegion", adminUserInfoBean.getRegion());
		}

		TeacherStatisticsBusiness business = new TeacherStatisticsBusiness();
		TeacherInfoBusiness teacherBusiness = new TeacherInfoBusiness();
		List<TeacherInfoBean> result = teacherBusiness.getTeacherRegionInfoListByMap(condMap);
		Map<String, TeacherStatisticsBean> resultMap = new HashMap<String, TeacherStatisticsBean>();
		for (TeacherInfoBean teacherInfoBean : result) {
			TeacherStatisticsBean bean = new TeacherStatisticsBean();
			String key = "";
			if (!StringKit.isValid(queryProvince) && !StringKit.isValid(queryCity) && !StringKit.isValid(queryRegion)) {
				// 默认是当前本级的统计
				key = adminUserInfoBean.getProvince()+adminUserInfoBean.getCity()+adminUserInfoBean.getRegion();
				bean.setProvince(teacherInfoBean.getProvince());
			} else if (!StringKit.isValid(queryCity) && !StringKit.isValid(queryRegion)) {
				// 省级及下属市的统计
				key = teacherInfoBean.getCity();
				bean.setProvince(teacherInfoBean.getProvince());
				bean.setCity(teacherInfoBean.getCity());
			} else if (!StringKit.isValid(queryRegion)) {
				// 市级及下属县的统计
				key = teacherInfoBean.getRegion();
				bean.setProvince(teacherInfoBean.getProvince());
				bean.setCity(teacherInfoBean.getCity());
				bean.setRegion(teacherInfoBean.getRegion());
			} else {
				// 具体某个县的统计
				key = teacherInfoBean.getRegion();
				bean.setProvince(teacherInfoBean.getProvince());
				bean.setCity(teacherInfoBean.getCity());
				bean.setRegion(teacherInfoBean.getRegion());
			}
			if(resultMap.containsKey(key)){
				bean = resultMap.get(key);
			}
			bean = business.countTeacherStatisticsBean(bean, teacherInfoBean);
			resultMap.put(key, bean);
		}
		request.setAttribute("TeacherStaticsticsResultMap", resultMap);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH
				+ "TeacherStatisticsByLevelList.jsp");
		rd.forward(request, response);
	}
}
