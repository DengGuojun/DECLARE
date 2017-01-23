package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.lpmas.declare.admin.bean.TargetStatisticsBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TargetStatisticsBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.business.DeclareInfoHelper;
import com.lpmas.framework.util.NumeralOperationKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingProcessList.do")
public class TrainingProcessList extends HttpServlet {
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
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_KINDS_COUNT, OperationConfig.SEARCH)) {
			return;
		}
		HashMap<String, String> condMap = new HashMap<String, String>();
		DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
		String declareYear = ParamKit
				.getParameter(request, "declareYear", String.valueOf(declareInfoHelper.getDeclareYear())).trim();
		if (StringKit.isValid(declareYear)) {
			condMap.put("declareYear", declareYear);
			condMap.put("trainingYear", declareYear);
		}

		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		String queryProvince = ParamKit.getParameter(request, "queryProvince", "").trim();
		String queryCity = ParamKit.getParameter(request, "queryCity", "").trim();
		String queryRegion = ParamKit.getParameter(request, "queryRegion", "").trim();

		if (StringKit.isValid(queryRegion)) {

		} else if (StringKit.isValid(queryCity)) {
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_REGION);
		} else if (StringKit.isValid(queryProvince)) {
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
		} else {
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
		}

		// 根据用户级别，获取对应的地区信息
		if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {
			// 国家级机构，可以选择省，市和区进行筛选
			if (StringKit.isValid(queryProvince)) {
				condMap.put("province", queryProvince);
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

		TargetStatisticsBusiness targetStatisticsBusiness = new TargetStatisticsBusiness();
		Map<String, List<Object>> declareReportMap = new HashMap<>();
		Map<String, List<Object>> declareCollectMap = new HashMap<>();
		List<TargetStatisticsBean> declareReportList = new ArrayList<>();
		declareReportList = targetStatisticsBusiness.getTargetStatisticsListByMap(condMap);
		for (TargetStatisticsBean bean : declareReportList) {
			List<Object> list = new ArrayList<>();
			list.add(bean.getLevel());
			list.add(bean.getCountiesNumber());
			list.add(bean.getFinishedCount());
			list.add(bean.getCount());
			list.add(bean.getPrecent());
			list.add(bean.getFinishedCount1());
			list.add(bean.getCount1());
			list.add(bean.getPrecent1());
			list.add(bean.getFinishedCount2());
			list.add(bean.getCount2());
			list.add(bean.getPrecent2());
			list.add(bean.getFinishedCount3());
			list.add(bean.getCount3());
			list.add(bean.getPrecent3());
			list.add(bean.getFinishedCount4());
			list.add(bean.getCount4());
			list.add(bean.getPrecent4());
			list.add(bean.getFinishedCount5());
			list.add(bean.getCount5());
			list.add(bean.getPrecent5());

			String name = "";
			switch (bean.getLevel()) {
			case DeclareAdminConfig.ADMIN_LEVEL_PROVINCE:
				name = new String(bean.getProvince());
				break;
			case DeclareAdminConfig.ADMIN_LEVEL_CITY:
				name = new String(bean.getCity());
				list.add(bean.getProvince());
				break;
			case DeclareAdminConfig.ADMIN_LEVEL_REGION:
				name = new String(bean.getRegion());
				list.add(bean.getProvince());
				list.add(bean.getCity());
				break;
			}
			declareReportMap.put(name, list);
		}

		int countiesNumber = 0; // 项目县数

		int count = 0; // 任务总数
		int finishedCount = 0; // 已完成任务数

		int count1 = 0;
		int finishedCount1 = 0;
		int count2 = 0;
		int finishedCount2 = 0;
		int count3 = 0;
		int finishedCount3 = 0;
		int count4 = 0;
		int finishedCount4 = 0;
		int count5 = 0;
		int finishedCount5 = 0;

		for (TargetStatisticsBean bean : declareReportList) {
			countiesNumber += bean.getCountiesNumber();
			count += bean.getCount();
			count1 += bean.getCount1();
			count2 += bean.getCount2();
			count3 += bean.getCount3();
			count4 += bean.getCount4();
			count5 += bean.getCount5();
			finishedCount += bean.getFinishedCount();
			finishedCount1 += bean.getFinishedCount1();
			finishedCount2 += bean.getFinishedCount2();
			finishedCount3 += bean.getFinishedCount3();
			finishedCount4 += bean.getFinishedCount4();
			finishedCount5 += bean.getFinishedCount5();

		}
		String precent = NumeralOperationKit.calculatePercent(finishedCount, count, 1) + "%"; // 已完成比例
		String precent1 = NumeralOperationKit.calculatePercent(finishedCount1, count1, 1) + "%";
		String precent2 = NumeralOperationKit.calculatePercent(finishedCount2, count2, 1) + "%";
		String precent3 = NumeralOperationKit.calculatePercent(finishedCount3, count3, 1) + "%";
		String precent4 = NumeralOperationKit.calculatePercent(finishedCount4, count4, 1) + "%";
		String precent5 = NumeralOperationKit.calculatePercent(finishedCount5, count5, 1) + "%";

		List<Object> list = new ArrayList<>();
		list.add("NONE");
		list.add(countiesNumber);
		list.add(finishedCount);
		list.add(count);
		list.add(precent);
		list.add(finishedCount1);
		list.add(count1);
		list.add(precent1);
		list.add(finishedCount2);
		list.add(count2);
		list.add(precent2);
		list.add(finishedCount3);
		list.add(count3);
		list.add(precent3);
		list.add(finishedCount4);
		list.add(count4);
		list.add(precent4);
		list.add(finishedCount5);
		list.add(count5);
		list.add(precent5);
		declareCollectMap.put("合计", list);

		request.setAttribute("declareReportMap", declareReportMap);
		request.setAttribute("declareCollectMap", declareCollectMap);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingProcessList.jsp");
		rd.forward(request, response);
	}

}
