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
import com.lpmas.declare.admin.bean.TeacherRegionInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TeacherMajorInfoBusiness;
import com.lpmas.declare.admin.business.TeacherMajorTypeBusiness;
import com.lpmas.declare.admin.business.TeacherEvaluateBusiness;
import com.lpmas.declare.admin.business.TeacherInfoBusiness;
import com.lpmas.declare.admin.business.TeacherRegionInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.TeacherMajorInfoBean;
import com.lpmas.declare.bean.TeacherMajorTypeBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TeacherInfoList
 */
@WebServlet("/declare/admin/TeacherInfoList.do")
public class TeacherInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		AdminUserInfoBean adminUserInfoBean = adminUserHelper.getAdminUserInfo();
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		TeacherMajorTypeBusiness teacherMajorTypeBusiness = new TeacherMajorTypeBusiness();
		List<TeacherMajorTypeBean> teacherMajorTypeList = teacherMajorTypeBusiness.getTeacherMajorTypeAllList();
		request.setAttribute("TeacherMajorTypeList", teacherMajorTypeList);
		// 获取专业分类
		Map<Integer, String> teacherMajorTypeMap = new HashMap<Integer, String>();
		for (TeacherMajorTypeBean bean : teacherMajorTypeList) {
			teacherMajorTypeMap.put(bean.getMajorId(), bean.getMajorName());
		}
		request.setAttribute("TeacherMajorTypeMap", teacherMajorTypeMap);
		TeacherMajorInfoBusiness teacherMajorInfoBusiness = new TeacherMajorInfoBusiness();
		List<TeacherMajorInfoBean> teacherMajorInfoList = teacherMajorInfoBusiness.getMajorInfoAllList();
		Map<Integer, String> teacherMajorInfoMap = new HashMap<Integer, String>();
		for (TeacherMajorInfoBean bean : teacherMajorInfoList) {
			teacherMajorInfoMap.put(bean.getMajorId(), bean.getMajorName());
		}
		request.setAttribute("TeacherMajorInfoMap", teacherMajorInfoMap);
		TeacherEvaluateBusiness evaluateBusiness = new TeacherEvaluateBusiness();
		Map<Integer, Float> teacherEvaluateMap = evaluateBusiness.getTeacherEvaluateMap();
		request.setAttribute("teacherEvaluateMap", teacherEvaluateMap);
		HashMap<String, String> condMap = new HashMap<String, String>(); // 获取condMap
		String status = ParamKit.getParameter(request, "status", "" + Constants.STATUS_VALID);
		if (StringKit.isValid(status)) {
			condMap.put("status", status);
		}
		String phone = ParamKit.getParameter(request, "phone", ""); // 获取手机号码
		String identityNumber = ParamKit.getParameter(request, "identityNumber", ""); // 身份证号码
		String teacherType = ParamKit.getParameter(request, "teacherType", ""); // 教师类型
		String majorTypeId = ParamKit.getParameter(request, "majorTypeId", ""); // 获取专业的ID
		String majorId = ParamKit.getParameter(request, "majorId", ""); // 获取专业的ID
		String teacherName = ParamKit.getParameter(request, "teacherName", ""); // 获取教师的名字
		String coursesOffer = ParamKit.getParameter(request, "coursesOffer", ""); // 主授课程
		if (StringKit.isValid(identityNumber)) {
			condMap.put("identityNumber", identityNumber);
		}
		if (StringKit.isValid(phone)) {
			condMap.put("phone", phone);
		}
		if (StringKit.isValid(teacherType)) {
			condMap.put("teacherType", teacherType);
		}
		if (StringKit.isValid(majorTypeId)) {
			condMap.put("majorTypeId", majorTypeId);
		}
		if (StringKit.isValid(majorId)) {
			condMap.put("majorId", majorId);
		}
		if (StringKit.isValid(teacherName)) {
			condMap.put("teacherName", teacherName);
		}
		if (StringKit.isValid(coursesOffer)) {
			condMap.put("coursesOffer", coursesOffer);
		}
		int teacherRange = ParamKit.getIntParameter(request, "teacherRange", 0);
		condMap.put("teacherRange", String.valueOf(teacherRange));
		// 0 入库信息
		// 1.本辖区
		// 2.本级别
		if (teacherRange == 0) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_ALL, OperationConfig.SEARCH)) {
				return;
			}
			TeacherInfoBusiness business = new TeacherInfoBusiness();
			String province = ParamKit.getParameter(request, "province");
			if (StringKit.isValid(province)) {
				condMap.put("province", province);
			}
			String city = ParamKit.getParameter(request, "city");
			if (StringKit.isValid(city)) {
				condMap.put("city", city);
			}
			String region = ParamKit.getParameter(request, "region");
			if (StringKit.isValid(region)) {
				condMap.put("region", region);
			}

			PageResultBean<TeacherInfoBean> result = business.getTeacherInfoPageListByMap(condMap, pageBean);
			// 判断是否已经存入本级别
			province = adminUserInfoBean.getProvince();
			city = adminUserInfoBean.getCity();
			region = adminUserInfoBean.getRegion();
			if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
				province = DeclareAdminConfig.COUNTRY_STR;
			}
			TeacherRegionInfoBusiness teacherRegionBusiness = new TeacherRegionInfoBusiness();
			Map<Integer, TeacherRegionInfoBean> teacherRegionMap = new HashMap<Integer, TeacherRegionInfoBean>();
			for (TeacherInfoBean bean : result.getRecordList()) {

				TeacherRegionInfoBean teacherRegionInfoBean = teacherRegionBusiness.getTeacherRegionInfoByKey(
						bean.getTeacherId(), adminUserInfoBean.getAdminUserLevel(), province, city, region);
				teacherRegionMap.put(bean.getTeacherId(), teacherRegionInfoBean);
			}
			request.setAttribute("TeacherInfoList", result.getRecordList());
			request.setAttribute("TeacherRegionMap", teacherRegionMap);
			pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
			request.setAttribute("PageResult", pageBean);
			request.setAttribute("adminUserHelper", adminUserHelper);
			request.setAttribute("CondList", MapKit.map2List(condMap));
			RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TeacherInfoList.jsp");
			rd.forward(request, response);
		} else if (teacherRange == 1) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_POPEDOM, OperationConfig.SEARCH)) {
				return;
			}
			if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
				// 国家级机构，可以选择省，市和区进行筛选
				String queryProvince = ParamKit.getParameter(request, "queryProvince", "").trim();
				if (StringKit.isValid(queryProvince)) {
					condMap.put("province", queryProvince);
				}
				String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
				if (StringKit.isValid(queryCity)) {
					condMap.put("city", queryCity);
				}
				String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
				if (StringKit.isValid(queryRegion)) {
					condMap.put("region", queryRegion);
				}
			} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
				// 省级机构，可以选择市和区进行筛选
				condMap.put("province", adminUserInfoBean.getProvince());
				request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
				String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
				if (StringKit.isValid(queryCity)) {
					condMap.put("city", queryCity);
				}
				String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
				if (StringKit.isValid(queryRegion)) {
					condMap.put("region", queryRegion);
				}
			} else if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)) {
				// 市级机构，可以选择区进行筛选
				condMap.put("province", adminUserInfoBean.getProvince());
				condMap.put("city", adminUserInfoBean.getCity());
				request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
				request.setAttribute("FixCity", adminUserInfoBean.getCity());
				String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();
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
			TeacherInfoBusiness business = new TeacherInfoBusiness();
			PageResultBean<TeacherInfoBean> result = business.getTeacherRegionInfoPageListByMap(condMap, pageBean);
			request.setAttribute("TeacherInfoList", result.getRecordList());
			pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());

			request.setAttribute("PageResult", pageBean);
			request.setAttribute("adminUserHelper", adminUserHelper);
			request.setAttribute("PageResult", pageBean);
			request.setAttribute("CondList", MapKit.map2List(condMap));

			RequestDispatcher rd = request
					.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TeacherInfoPopedomList.jsp");
			rd.forward(request, response);
		} else if (teacherRange == 2) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
					OperationConfig.SEARCH)) {
				return;
			}
			// 本级别
			if (StringKit.isValid(adminUserInfoBean.getRegion())) {
				condMap.put("province", adminUserInfoBean.getProvince());
				condMap.put("city", adminUserInfoBean.getCity());
				condMap.put("region", adminUserInfoBean.getRegion());
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_REGION);
			} else if (StringKit.isValid(adminUserInfoBean.getCity())) {
				condMap.put("province", adminUserInfoBean.getProvince());
				condMap.put("city", adminUserInfoBean.getCity());
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
			} else if (StringKit.isValid(adminUserInfoBean.getProvince())) {
				condMap.put("province", adminUserInfoBean.getProvince());
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			} else {
				condMap.put("province", DeclareAdminConfig.COUNTRY_STR);
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
			}
			TeacherInfoBusiness business = new TeacherInfoBusiness();
			PageResultBean<TeacherInfoBean> result = business.getTeacherRegionInfoPageListByMap(condMap, pageBean);
			request.setAttribute("TeacherInfoList", result.getRecordList());
			pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
			request.setAttribute("PageResult", pageBean);
			request.setAttribute("adminUserHelper", adminUserHelper);
			request.setAttribute("CondList", MapKit.map2List(condMap));
			RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TeacherInfoList.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("adminUserHelper", adminUserHelper);
			// 默认的页面 为了权限方便
			RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TeacherInfoList.jsp");
			rd.forward(request, response);
		}
	}
}
