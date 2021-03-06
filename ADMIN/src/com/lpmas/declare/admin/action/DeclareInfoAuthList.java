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

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.MajorInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.MajorInfoBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/DeclareInfoAuthList.do")
public class DeclareInfoAuthList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoAuthList() {
		super();
		// TODO Auto-generated constructor stub
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
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_AUTH, OperationConfig.SEARCH)) {
			return;
		}
		int trainingType = ParamKit.getIntParameter(request, "trainingType", 0);
		if (!DeclareInfoConfig.DECLARE_TYPE_MAP.containsKey(trainingType)) {
			HttpResponseKit.alertMessage(response, "学员类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		request.setAttribute("trainingType", trainingType);
		// 初始化页面分页参数
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, "trainingYear,authStatus", "");
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 根据用户级别，获取对应的地区信息
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
		condMap.put("trainingType", String.valueOf(trainingType));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		TrainingClassInfoBusiness business = new TrainingClassInfoBusiness();
		PageResultBean<TrainingClassInfoBean> result = business.getTrainingClassInfoPageListByMap(condMap, pageBean);
		request.setAttribute("TrainingClassInfoList", result.getRecordList());
		// 产业类型和信息
		MajorInfoBusiness majorInfoBusiness = new MajorInfoBusiness();
		List<MajorInfoBean> majorInfoList = majorInfoBusiness.getMajorInfoAllList();
		request.setAttribute("MajorInfoMap", ListKit.list2Map(majorInfoList, "majorId", "majorName"));
		// 机构信息
		TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
		HashMap<String, String> trainingOrganizationInfoMap = new HashMap<String, String>();
		trainingOrganizationInfoMap.put("status", String.valueOf(Constants.STATUS_VALID));
		List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = trainingOrganizationInfoBusiness
				.getTrainingOrganizationInfoListByMap(trainingOrganizationInfoMap);
		request.setAttribute("TrainingOrganizationInfoMap",
				ListKit.list2Map(trainingOrganizationInfoList, "organizationId", "organizationName"));
		//认定机构信息
		HashMap<Integer,TrainingOrganizationInfoBean> authOrgInfoMap = new HashMap<Integer,TrainingOrganizationInfoBean>();
		for(TrainingClassInfoBean bean : result.getRecordList()){
			if(bean.getAuthOrganizationId() > 0){
				TrainingOrganizationInfoBean authOrgInfoBean = trainingOrganizationInfoBusiness.getTrainingOrganizationInfoByKey(bean.getAuthOrganizationId());
				authOrgInfoMap.put(authOrgInfoBean.getOrganizationId(), authOrgInfoBean);
			}
		}
		request.setAttribute("AuthOrganizationInfoMap", authOrgInfoMap);

		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "DeclareInfoAuthList.jsp");
		rd.forward(request, response);
	}

}
