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
import com.lpmas.declare.admin.business.IndustryTypeBusiness;
import com.lpmas.declare.admin.business.JobTypeBusiness;
import com.lpmas.declare.admin.business.TargetStatisticsBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.IndustryTypeBean;
import com.lpmas.declare.bean.JobTypeBean;
import com.lpmas.declare.business.DeclareInfoHelper;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.util.NumeralOperationKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TargetStatisticsList.do")
public class TargetStatisticsList extends HttpServlet {
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
		int declareType = ParamKit.getIntParameter(request, "declareType", 0);
		DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
		String declareYear = ParamKit
				.getParameter(request, "declareYear", String.valueOf(declareInfoHelper.getDeclareYear())).trim();
		if (StringKit.isValid(declareYear)) {
			condMap.put("declareYear", declareYear);
			condMap.put("trainingYear", declareYear);
		}
		if (declareType != 0) {
			if (!DeclareInfoConfig.DECLARE_TYPE_MAP.containsKey(declareType)) {
				HttpResponseKit.alertMessage(response, "对象类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			condMap.put("declareType", String.valueOf(declareType));
			condMap.put("trainingType", String.valueOf(declareType));
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
		List<TargetStatisticsBean> targetStatisticsList = targetStatisticsBusiness
				.getTargetStatisticsListByMap(condMap);
		Map<String, List<Object>> declareReportMap = new HashMap<>();
		Map<String, List<Object>> declareCollectMap = new HashMap<>();
		TargetStatisticsBean countTargetBean = new TargetStatisticsBean();
		countTargetBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
		countTargetBean.setDeclareYear(declareYear);

		for (TargetStatisticsBean bean : targetStatisticsList) {
			countTargetBean.setCountiesNumber(bean.getCountiesNumber() + countTargetBean.getCountiesNumber());
			countTargetBean.setCount(bean.getCount() + countTargetBean.getCount());
			countTargetBean.setFinishedCount(bean.getFinishedCount() + countTargetBean.getFinishedCount());
			countTargetBean.setCount1(bean.getCount1() + countTargetBean.getCount1());
			countTargetBean.setFinishedCount1(bean.getFinishedCount1() + countTargetBean.getFinishedCount1());
			countTargetBean.setCount2(bean.getCount2() + countTargetBean.getCount2());
			countTargetBean.setFinishedCount2(bean.getFinishedCount2() + countTargetBean.getFinishedCount2());
			countTargetBean.setCount3(bean.getCount3() + countTargetBean.getCount3());
			countTargetBean.setFinishedCount3(bean.getFinishedCount3() + countTargetBean.getFinishedCount3());
			countTargetBean.setCount4(bean.getCount4() + countTargetBean.getCount4());
			countTargetBean.setFinishedCount4(bean.getFinishedCount4() + countTargetBean.getFinishedCount4());
			countTargetBean.setCount5(bean.getCount5() + countTargetBean.getCount5());
			countTargetBean.setFinishedCount5(bean.getFinishedCount5() + countTargetBean.getFinishedCount5());

			countTargetBean.setType1count(bean.getType1count() + countTargetBean.getType1count());
			countTargetBean.setType1industry1(bean.getType1industry1() + countTargetBean.getType1industry1());
			countTargetBean.setType1industry2(bean.getType1industry2() + countTargetBean.getType1industry2());
			countTargetBean.setType1industry3(bean.getType1industry3() + countTargetBean.getType1industry3());
			countTargetBean.setType1industry4(bean.getType1industry4() + countTargetBean.getType1industry4());
			countTargetBean.setType1industry5(bean.getType1industry5() + countTargetBean.getType1industry5());
			countTargetBean.setType1industry6(bean.getType1industry6() + countTargetBean.getType1industry6());
			countTargetBean.setType1industry7(bean.getType1industry7() + countTargetBean.getType1industry7());
			countTargetBean.setType1industry8(bean.getType1industry8() + countTargetBean.getType1industry8());
			countTargetBean.setType1industry9(bean.getType1industry9() + countTargetBean.getType1industry9());
			countTargetBean.setType1industry10(bean.getType1industry10() + countTargetBean.getType1industry10());

			countTargetBean.setType2count(bean.getType2count() + countTargetBean.getType2count());
			countTargetBean.setType2industry1(bean.getType2industry1() + countTargetBean.getType2industry1());
			countTargetBean.setType2industry2(bean.getType2industry2() + countTargetBean.getType2industry2());
			countTargetBean.setType2industry3(bean.getType2industry3() + countTargetBean.getType2industry3());
			countTargetBean.setType2industry4(bean.getType2industry4() + countTargetBean.getType2industry4());
			countTargetBean.setType2industry5(bean.getType2industry5() + countTargetBean.getType2industry5());
			countTargetBean.setType2industry6(bean.getType2industry6() + countTargetBean.getType2industry6());
			countTargetBean.setType2industry7(bean.getType2industry7() + countTargetBean.getType2industry7());
			countTargetBean.setType2industry8(bean.getType2industry8() + countTargetBean.getType2industry8());
			countTargetBean.setType2industry9(bean.getType2industry9() + countTargetBean.getType2industry9());
			countTargetBean.setType2industry10(bean.getType2industry10() + countTargetBean.getType2industry10());

			countTargetBean.setType5count(bean.getType5count() + countTargetBean.getType5count());
			countTargetBean.setType5industry1(bean.getType5industry1() + countTargetBean.getType5industry1());
			countTargetBean.setType5industry2(bean.getType5industry2() + countTargetBean.getType5industry2());
			countTargetBean.setType5industry3(bean.getType5industry3() + countTargetBean.getType5industry3());
			countTargetBean.setType5industry4(bean.getType5industry4() + countTargetBean.getType5industry4());
			countTargetBean.setType5industry5(bean.getType5industry5() + countTargetBean.getType5industry5());
			countTargetBean.setType5industry6(bean.getType5industry6() + countTargetBean.getType5industry6());
			countTargetBean.setType5industry7(bean.getType5industry7() + countTargetBean.getType5industry7());
			countTargetBean.setType5industry8(bean.getType5industry8() + countTargetBean.getType5industry8());
			countTargetBean.setType5industry9(bean.getType5industry9() + countTargetBean.getType5industry9());
			countTargetBean.setType5industry10(bean.getType5industry10() + countTargetBean.getType5industry10());

			countTargetBean.setType3count(bean.getType3count() + countTargetBean.getType3count());
			countTargetBean.setType3job1(bean.getType3job1() + countTargetBean.getType3job1());
			countTargetBean.setType3job2(bean.getType3job2() + countTargetBean.getType3job2());
			countTargetBean.setType3job3(bean.getType3job3() + countTargetBean.getType3job3());
			countTargetBean.setType3job4(bean.getType3job4() + countTargetBean.getType3job4());
			countTargetBean.setType3job5(bean.getType3job5() + countTargetBean.getType3job5());
			countTargetBean.setType3job6(bean.getType3job6() + countTargetBean.getType3job6());
			countTargetBean.setType3job7(bean.getType3job7() + countTargetBean.getType3job7());
			countTargetBean.setType3job8(bean.getType3job8() + countTargetBean.getType3job8());
			countTargetBean.setType3job9(bean.getType3job9() + countTargetBean.getType3job9());
			countTargetBean.setType3job10(bean.getType3job10() + countTargetBean.getType3job10());
			countTargetBean.setType3job11(bean.getType3job11() + countTargetBean.getType3job11());
			countTargetBean.setType3job12(bean.getType3job12() + countTargetBean.getType3job12());
			countTargetBean.setType3job13(bean.getType3job13() + countTargetBean.getType3job13());
			countTargetBean.setType3job14(bean.getType3job14() + countTargetBean.getType3job14());
			countTargetBean.setType3job15(bean.getType3job15() + countTargetBean.getType3job15());
			countTargetBean.setType3job16(bean.getType3job16() + countTargetBean.getType3job16());
			countTargetBean.setType3job17(bean.getType3job17() + countTargetBean.getType3job17());
			countTargetBean.setType3job18(bean.getType3job18() + countTargetBean.getType3job18());

			countTargetBean.setType4count(bean.getType4count() + countTargetBean.getType4count());
			countTargetBean.setType4job1(bean.getType4job1() + countTargetBean.getType4job1());
			countTargetBean.setType4job2(bean.getType4job2() + countTargetBean.getType4job2());
			countTargetBean.setType4job3(bean.getType4job3() + countTargetBean.getType4job3());
			countTargetBean.setType4job4(bean.getType4job4() + countTargetBean.getType4job4());
			countTargetBean.setType4job5(bean.getType4job5() + countTargetBean.getType4job5());
			countTargetBean.setType4job6(bean.getType4job6() + countTargetBean.getType4job6());
			countTargetBean.setType4job7(bean.getType4job7() + countTargetBean.getType4job7());
			countTargetBean.setType4job8(bean.getType4job8() + countTargetBean.getType4job8());
			countTargetBean.setType4job9(bean.getType4job9() + countTargetBean.getType4job9());
			countTargetBean.setType4job10(bean.getType4job10() + countTargetBean.getType4job10());
			countTargetBean.setType4job11(bean.getType4job11() + countTargetBean.getType4job11());
			countTargetBean.setType4job12(bean.getType4job12() + countTargetBean.getType4job12());
			countTargetBean.setType4job13(bean.getType4job13() + countTargetBean.getType4job13());
			countTargetBean.setType4job14(bean.getType4job14() + countTargetBean.getType4job14());
			countTargetBean.setType4job15(bean.getType4job15() + countTargetBean.getType4job15());
			countTargetBean.setType4job16(bean.getType4job16() + countTargetBean.getType4job16());
			countTargetBean.setType4job17(bean.getType4job17() + countTargetBean.getType4job17());
			countTargetBean.setType4job18(bean.getType4job18() + countTargetBean.getType4job18());

			List<Object> list = new ArrayList<>();
			String name = "";
			switch (declareType) {
			case 0:
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
				break;
			case DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getType1count());
				list.add(bean.getType1industry1());
				list.add(bean.getType1industry2());
				list.add(bean.getType1industry3());
				list.add(bean.getType1industry4());
				list.add(bean.getType1industry5());
				list.add(bean.getType1industry6());
				list.add(bean.getType1industry7());
				list.add(bean.getType1industry8());
				list.add(bean.getType1industry9());
				list.add(bean.getType1industry10());
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
				break;
			case DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getType2count());
				list.add(bean.getType2industry1());
				list.add(bean.getType2industry2());
				list.add(bean.getType2industry3());
				list.add(bean.getType2industry4());
				list.add(bean.getType2industry5());
				list.add(bean.getType2industry6());
				list.add(bean.getType2industry7());
				list.add(bean.getType2industry8());
				list.add(bean.getType2industry9());
				list.add(bean.getType2industry10());
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
				break;
			case DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getType3count());
				list.add(bean.getType3job1());
				list.add(bean.getType3job2());
				list.add(bean.getType3job3());
				list.add(bean.getType3job4());
				list.add(bean.getType3job5());
				list.add(bean.getType3job6());
				list.add(bean.getType3job7());
				list.add(bean.getType3job8());
				list.add(bean.getType3job9());
				list.add(bean.getType3job10());
				list.add(bean.getType3job11());
				list.add(bean.getType3job12());
				list.add(bean.getType3job13());
				list.add(bean.getType3job14());
				list.add(bean.getType3job15());
				list.add(bean.getType3job16());
				list.add(bean.getType3job17());
				list.add(bean.getType3job18());
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
				break;
			case DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getType4count());
				list.add(bean.getType4job1());
				list.add(bean.getType4job2());
				list.add(bean.getType4job3());
				list.add(bean.getType4job4());
				list.add(bean.getType4job5());
				list.add(bean.getType4job6());
				list.add(bean.getType4job7());
				list.add(bean.getType4job8());
				list.add(bean.getType4job9());
				list.add(bean.getType4job10());
				list.add(bean.getType4job11());
				list.add(bean.getType4job12());
				list.add(bean.getType4job13());
				list.add(bean.getType4job14());
				list.add(bean.getType4job15());
				list.add(bean.getType4job16());
				list.add(bean.getType4job17());
				list.add(bean.getType4job18());
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
				break;
			case DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getType5count());
				list.add(bean.getType5industry1());
				list.add(bean.getType5industry2());
				list.add(bean.getType5industry3());
				list.add(bean.getType5industry4());
				list.add(bean.getType5industry5());
				list.add(bean.getType5industry6());
				list.add(bean.getType5industry7());
				list.add(bean.getType5industry8());
				list.add(bean.getType5industry9());
				list.add(bean.getType5industry10());
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
				break;
			}
		}

		String precent = NumeralOperationKit.calculatePercent(countTargetBean.getFinishedCount(),
				countTargetBean.getCount(), 1) + "%"; // 已完成比例
		String precent1 = NumeralOperationKit.calculatePercent(countTargetBean.getFinishedCount1(),
				countTargetBean.getCount1(), 1) + "%";
		String precent2 = NumeralOperationKit.calculatePercent(countTargetBean.getFinishedCount2(),
				countTargetBean.getCount2(), 1) + "%";
		String precent3 = NumeralOperationKit.calculatePercent(countTargetBean.getFinishedCount3(),
				countTargetBean.getCount3(), 1) + "%";
		String precent4 = NumeralOperationKit.calculatePercent(countTargetBean.getFinishedCount4(),
				countTargetBean.getCount4(), 1) + "%";
		String precent5 = NumeralOperationKit.calculatePercent(countTargetBean.getFinishedCount5(),
				countTargetBean.getCount5(), 1) + "%";
		countTargetBean.setPrecent(precent);
		countTargetBean.setPrecent1(precent1);
		countTargetBean.setPrecent2(precent2);
		countTargetBean.setPrecent3(precent3);
		countTargetBean.setPrecent4(precent4);
		countTargetBean.setPrecent5(precent5);

		List<Object> list = new ArrayList<>();
		switch (declareType) {
		case 0:
			list.add("NONE");
			list.add(countTargetBean.getCountiesNumber());
			list.add(countTargetBean.getFinishedCount());
			list.add(countTargetBean.getCount());
			list.add(countTargetBean.getPrecent());
			list.add(countTargetBean.getFinishedCount1());
			list.add(countTargetBean.getCount1());
			list.add(countTargetBean.getPrecent1());
			list.add(countTargetBean.getFinishedCount2());
			list.add(countTargetBean.getCount2());
			list.add(countTargetBean.getPrecent2());
			list.add(countTargetBean.getFinishedCount3());
			list.add(countTargetBean.getCount3());
			list.add(countTargetBean.getPrecent3());
			list.add(countTargetBean.getFinishedCount4());
			list.add(countTargetBean.getCount4());
			list.add(countTargetBean.getPrecent4());
			list.add(countTargetBean.getFinishedCount5());
			list.add(countTargetBean.getCount5());
			list.add(countTargetBean.getPrecent5());
			declareCollectMap.put("合计", list);
			break;
		case DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER:
			list.add("NONE");
			list.add(countTargetBean.getType1count());
			list.add(countTargetBean.getType1industry1());
			list.add(countTargetBean.getType1industry2());
			list.add(countTargetBean.getType1industry3());
			list.add(countTargetBean.getType1industry4());
			list.add(countTargetBean.getType1industry5());
			list.add(countTargetBean.getType1industry6());
			list.add(countTargetBean.getType1industry7());
			list.add(countTargetBean.getType1industry8());
			list.add(countTargetBean.getType1industry9());
			list.add(countTargetBean.getType1industry10());
			declareCollectMap.put("合计", list);
			break;
		case DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER:
			list.add("NONE");
			list.add(countTargetBean.getType2count());
			list.add(countTargetBean.getType2industry1());
			list.add(countTargetBean.getType2industry2());
			list.add(countTargetBean.getType2industry3());
			list.add(countTargetBean.getType2industry4());
			list.add(countTargetBean.getType2industry5());
			list.add(countTargetBean.getType2industry6());
			list.add(countTargetBean.getType2industry7());
			list.add(countTargetBean.getType2industry8());
			list.add(countTargetBean.getType2industry9());
			list.add(countTargetBean.getType2industry10());
			declareCollectMap.put("合计", list);
			break;
		case DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER:
			list.add("NONE");
			list.add(countTargetBean.getType3count());
			list.add(countTargetBean.getType3job1());
			list.add(countTargetBean.getType3job2());
			list.add(countTargetBean.getType3job3());
			list.add(countTargetBean.getType3job4());
			list.add(countTargetBean.getType3job5());
			list.add(countTargetBean.getType3job6());
			list.add(countTargetBean.getType3job7());
			list.add(countTargetBean.getType3job8());
			list.add(countTargetBean.getType3job9());
			list.add(countTargetBean.getType3job10());
			list.add(countTargetBean.getType3job11());
			list.add(countTargetBean.getType3job12());
			list.add(countTargetBean.getType3job13());
			list.add(countTargetBean.getType3job14());
			list.add(countTargetBean.getType3job15());
			list.add(countTargetBean.getType3job16());
			list.add(countTargetBean.getType3job17());
			list.add(countTargetBean.getType3job18());
			declareCollectMap.put("合计", list);
			break;
		case DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER:
			list.add("NONE");
			list.add(countTargetBean.getType4count());
			list.add(countTargetBean.getType4job1());
			list.add(countTargetBean.getType4job2());
			list.add(countTargetBean.getType4job3());
			list.add(countTargetBean.getType4job4());
			list.add(countTargetBean.getType4job5());
			list.add(countTargetBean.getType4job6());
			list.add(countTargetBean.getType4job7());
			list.add(countTargetBean.getType4job8());
			list.add(countTargetBean.getType4job9());
			list.add(countTargetBean.getType4job10());
			list.add(countTargetBean.getType4job11());
			list.add(countTargetBean.getType4job12());
			list.add(countTargetBean.getType4job13());
			list.add(countTargetBean.getType4job14());
			list.add(countTargetBean.getType4job15());
			list.add(countTargetBean.getType4job16());
			list.add(countTargetBean.getType4job17());
			list.add(countTargetBean.getType4job18());
			declareCollectMap.put("合计", list);
			break;
		case DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER:
			list.add("NONE");
			list.add(countTargetBean.getType5count());
			list.add(countTargetBean.getType5industry1());
			list.add(countTargetBean.getType5industry2());
			list.add(countTargetBean.getType5industry3());
			list.add(countTargetBean.getType5industry4());
			list.add(countTargetBean.getType5industry5());
			list.add(countTargetBean.getType5industry6());
			list.add(countTargetBean.getType5industry7());
			list.add(countTargetBean.getType5industry8());
			list.add(countTargetBean.getType5industry9());
			list.add(countTargetBean.getType5industry10());
			declareCollectMap.put("合计", list);
			break;
		}

		// 工种
		JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
		List<JobTypeBean> jobTypeList = jobTypeBusiness.getJobTypeAllList();
		// 主体产业
		IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
		List<IndustryTypeBean> industryTypeList = industryTypeBusiness.getIndustryTypeAllList();
		request.setAttribute("jobTypeList", jobTypeList);
		request.setAttribute("industryTypeList", industryTypeList);
		request.setAttribute("declareReportMap", declareReportMap);
		request.setAttribute("declareCollectMap", declareCollectMap);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TargetStatisticsList.jsp");
		rd.forward(request, response);
	}

}
