package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.DeclareStatisticsBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareStatisticsBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.business.DeclareInfoHelper;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class DeclareStatisticsList
 */
@WebServlet("/declare/admin/DeclareStatisticsList.do")
public class DeclareStatisticsList extends HttpServlet {
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
		if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_KINDS_COUNT, OperationConfig.SEARCH)) {
			return;
		}
		DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
		String province = ParamKit.getParameter(request, "queryProvince", "");
		String city = ParamKit.getParameter(request, "queryCity", "");
		String region = ParamKit.getParameter(request, "queryRegion", "");
		String declareYear = ParamKit.getParameter(request, "declareYear",
				String.valueOf(declareInfoHelper.getDeclareYear()));
		int declareType = ParamKit.getIntParameter(request, "declareType", 0);

		LinkedHashMap<String, List<Object>> declareReportMap = new LinkedHashMap<>();
		LinkedHashMap<String, List<Object>> declareCollectMap = new LinkedHashMap<>();
		DeclareStatisticsBean totalCountBean = new DeclareStatisticsBean();
		DeclareStatisticsBusiness business = new DeclareStatisticsBusiness();
		List<DeclareStatisticsBean> result = new ArrayList<DeclareStatisticsBean>();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("declareYear", declareYear);
		if (!StringKit.isValid(province) && !StringKit.isValid(city) && !StringKit.isValid(region)) {
			// 默认获取总数、国家级的统计和省级的总数
			condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			condMap.put("sum", String.valueOf(true));
			result.addAll(business.getDeclareStatisticsListByMap(condMap));
		} else {
			if (StringKit.isValid(province) && !StringKit.isValid(city) && !StringKit.isValid(region)) {
				// 如果是从省级进入，则读取的是省级的统计和市级的总数
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
				condMap.put("province", province);
				condMap.put("sum", String.valueOf(false));
				result.addAll(business.getDeclareStatisticsListByMap(condMap));
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
				condMap.put("city", city);
				condMap.put("sum", String.valueOf(true));
				result.addAll(business.getDeclareStatisticsListByMap(condMap));
			} else if (StringKit.isValid(province) && StringKit.isValid(city) && !StringKit.isValid(region)) {
				// 如果是从市级进入，则读取的是市级的统计和区级的统计
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
				condMap.put("province", province);
				condMap.put("city", city);
				condMap.put("sum", String.valueOf(false));
				result.addAll(business.getDeclareStatisticsListByMap(condMap));
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_REGION);
				result.addAll(business.getDeclareStatisticsListByMap(condMap));
			} else if (StringKit.isValid(province) && StringKit.isValid(city) && StringKit.isValid(region)) {
				// 如果是从区级进入，则读取的是区级的统计
				condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_REGION);
				condMap.put("province", province);
				condMap.put("city", city);
				condMap.put("region", region);
				condMap.put("sum", String.valueOf(false));
				result.addAll(business.getDeclareStatisticsListByMap(condMap));
			}
		}
		for (DeclareStatisticsBean bean : result) {
			List<Object> list = new ArrayList<>();

			switch (declareType) {
			case 0:
				list.add(bean.getLevel());
				list.add(bean.getProvince());
				list.add(bean.getCity());
				list.add(bean.getRegion());
				list.add(bean.getCount());
				list.add(bean.getCount1());
				list.add(bean.getApproveCount1());
				list.add(bean.getRejectCount1());
				list.add(bean.getWaitApproveCount1());
				list.add(bean.getCount2());
				list.add(bean.getApproveCount2());
				list.add(bean.getRejectCount2());
				list.add(bean.getWaitApproveCount2());
				list.add(bean.getCount3());
				list.add(bean.getApproveCount3());
				list.add(bean.getRejectCount3());
				list.add(bean.getWaitApproveCount3());
				list.add(bean.getCount4());
				list.add(bean.getApproveCount4());
				list.add(bean.getRejectCount4());
				list.add(bean.getWaitApproveCount4());
				list.add(bean.getCount5());
				list.add(bean.getApproveCount5());
				list.add(bean.getRejectCount5());
				list.add(bean.getWaitApproveCount5());
				break;

			case DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getProvince());
				list.add(bean.getCity());
				list.add(bean.getRegion());
				list.add(bean.getPeopleCount1());

				list.add(bean.getMale1());
				list.add(bean.getFemale1());
				list.add(bean.getAgeLevel1Num1());
				list.add(bean.getAgeLevel2Num1());
				list.add(bean.getAgeLevel3Num1());
				list.add(bean.getAgeLevel4Num1());
				list.add(bean.getAgeLevel5Num1());
				list.add(bean.getCultureLevel1Num1());
				list.add(bean.getCultureLevel2Num1());
				list.add(bean.getCultureLevel3Num1());
				list.add(bean.getCultureLevel4Num1());
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

				list.add(bean.getPlanting_large1());
				list.add(bean.getScale_farm1());
				list.add(bean.getFamily_farm1());
				list.add(bean.getFarmer_cooperatives1());
				list.add(bean.getCollege_students1());
				list.add(bean.getVocational_graduates1());
				list.add(bean.getMigrant_workers1());
				list.add(bean.getVeteran1());

				list.add(bean.getPersonal_declaration1());
				list.add(bean.getOrganization_recommended1());
				break;
			case DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getLevel());
				list.add(bean.getProvince());
				list.add(bean.getCity());
				list.add(bean.getRegion());
				list.add(bean.getPeopleCount2());

				list.add(bean.getMale2());
				list.add(bean.getFemale2());
				list.add(bean.getAgeLevel1Num2());
				list.add(bean.getAgeLevel2Num2());
				list.add(bean.getAgeLevel3Num2());
				list.add(bean.getAgeLevel4Num2());
				list.add(bean.getAgeLevel5Num2());
				list.add(bean.getCultureLevel1Num2());
				list.add(bean.getCultureLevel2Num2());
				list.add(bean.getCultureLevel3Num2());
				list.add(bean.getCultureLevel4Num2());
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

				list.add(bean.getPlanting_large2());
				list.add(bean.getScale_farm2());
				list.add(bean.getFamily_farm2());
				list.add(bean.getAgricultural_enterprises2());
				list.add(bean.getFarmer_cooperatives2());
				list.add(bean.getCollege_students2());
				list.add(bean.getVocational_graduates2());
				list.add(bean.getMigrant_workers2());
				list.add(bean.getVeteran2());

				list.add(bean.getPersonal_declaration2());
				list.add(bean.getOrganization_recommended2());
				break;
			case DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getProvince());
				list.add(bean.getCity());
				list.add(bean.getRegion());
				list.add(bean.getPeopleCount5());

				list.add(bean.getMale5());
				list.add(bean.getFemale5());
				list.add(bean.getAgeLevel1Num5());
				list.add(bean.getAgeLevel2Num5());
				list.add(bean.getAgeLevel3Num5());
				list.add(bean.getAgeLevel4Num5());
				list.add(bean.getAgeLevel5Num5());
				list.add(bean.getCultureLevel1Num5());
				list.add(bean.getCultureLevel2Num5());
				list.add(bean.getCultureLevel3Num5());
				list.add(bean.getCultureLevel4Num5());
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

				list.add(bean.getPlanting_large5());
				list.add(bean.getScale_farm5());
				list.add(bean.getFamily_farm5());
				list.add(bean.getFarmer_cooperatives5());
				list.add(bean.getAgricultural_enterprises5());
				list.add(bean.getService_experts5());

				list.add(bean.getPersonal_declaration5());
				list.add(bean.getOrganization_recommended5());
				break;
			case DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getProvince());
				list.add(bean.getCity());
				list.add(bean.getRegion());
				list.add(bean.getPeopleCount3());

				list.add(bean.getMale3());
				list.add(bean.getFemale3());
				list.add(bean.getAgeLevel1Num3());
				list.add(bean.getAgeLevel2Num3());
				list.add(bean.getAgeLevel3Num3());
				list.add(bean.getAgeLevel4Num3());
				list.add(bean.getAgeLevel5Num3());
				list.add(bean.getCultureLevel1Num3());
				list.add(bean.getCultureLevel2Num3());
				list.add(bean.getCultureLevel3Num3());
				list.add(bean.getCultureLevel4Num3());

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

				list.add(bean.getPersonal_declaration3());
				list.add(bean.getOrganization_recommended3());
				break;
			case DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER:
				list.add(bean.getLevel());
				list.add(bean.getProvince());
				list.add(bean.getCity());
				list.add(bean.getRegion());
				list.add(bean.getPeopleCount4());

				list.add(bean.getMale4());
				list.add(bean.getFemale4());
				list.add(bean.getAgeLevel1Num4());
				list.add(bean.getAgeLevel2Num4());
				list.add(bean.getAgeLevel3Num4());
				list.add(bean.getAgeLevel4Num4());
				list.add(bean.getAgeLevel5Num4());
				list.add(bean.getCultureLevel1Num4());
				list.add(bean.getCultureLevel2Num4());
				list.add(bean.getCultureLevel3Num4());
				list.add(bean.getCultureLevel4Num4());

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

				list.add(bean.getPersonal_declaration4());
				list.add(bean.getOrganization_recommended4());
				break;
			}

			switch (bean.getLevel()) {
			case DeclareAdminConfig.ADMIN_LEVEL_PROVINCE:
				declareReportMap.put(bean.getProvince(), list);
				break;
			case DeclareAdminConfig.ADMIN_LEVEL_CITY:
				declareReportMap.put(bean.getCity(), list);
				break;
			case DeclareAdminConfig.ADMIN_LEVEL_REGION:
				declareReportMap.put(bean.getRegion(), list);
				break;
			}

			totalCountBean = business.countDeclareStatisticsBean(totalCountBean, bean);
		}

		List<Object> list = new ArrayList<>();
		switch (declareType) {
		case 0:
			list.add(totalCountBean.getLevel());
			list.add(totalCountBean.getProvince());
			list.add(totalCountBean.getCity());
			list.add(totalCountBean.getRegion());
			list.add(totalCountBean.getCount());
			list.add(totalCountBean.getCount1());
			list.add(totalCountBean.getApproveCount1());
			list.add(totalCountBean.getRejectCount1());
			list.add(totalCountBean.getWaitApproveCount1());
			list.add(totalCountBean.getCount2());
			list.add(totalCountBean.getApproveCount2());
			list.add(totalCountBean.getRejectCount2());
			list.add(totalCountBean.getWaitApproveCount2());
			list.add(totalCountBean.getCount3());
			list.add(totalCountBean.getApproveCount3());
			list.add(totalCountBean.getRejectCount3());
			list.add(totalCountBean.getWaitApproveCount3());
			list.add(totalCountBean.getCount4());
			list.add(totalCountBean.getApproveCount4());
			list.add(totalCountBean.getRejectCount4());
			list.add(totalCountBean.getWaitApproveCount4());
			list.add(totalCountBean.getCount5());
			list.add(totalCountBean.getApproveCount5());
			list.add(totalCountBean.getRejectCount5());
			list.add(totalCountBean.getWaitApproveCount5());
			break;

		case DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER:
			list.add(totalCountBean.getLevel());
			list.add(totalCountBean.getProvince());
			list.add(totalCountBean.getCity());
			list.add(totalCountBean.getRegion());
			list.add(totalCountBean.getPeopleCount1());

			list.add(totalCountBean.getMale1());
			list.add(totalCountBean.getFemale1());
			list.add(totalCountBean.getAgeLevel1Num1());
			list.add(totalCountBean.getAgeLevel2Num1());
			list.add(totalCountBean.getAgeLevel3Num1());
			list.add(totalCountBean.getAgeLevel4Num1());
			list.add(totalCountBean.getAgeLevel5Num1());
			list.add(totalCountBean.getCultureLevel1Num1());
			list.add(totalCountBean.getCultureLevel2Num1());
			list.add(totalCountBean.getCultureLevel3Num1());
			list.add(totalCountBean.getCultureLevel4Num1());
			list.add(totalCountBean.getType1industry1());
			list.add(totalCountBean.getType1industry2());
			list.add(totalCountBean.getType1industry3());
			list.add(totalCountBean.getType1industry4());
			list.add(totalCountBean.getType1industry5());
			list.add(totalCountBean.getType1industry6());
			list.add(totalCountBean.getType1industry7());
			list.add(totalCountBean.getType1industry8());
			list.add(totalCountBean.getType1industry9());
			list.add(totalCountBean.getType1industry10());

			list.add(totalCountBean.getPlanting_large1());
			list.add(totalCountBean.getScale_farm1());
			list.add(totalCountBean.getFamily_farm1());
			list.add(totalCountBean.getFarmer_cooperatives1());
			list.add(totalCountBean.getCollege_students1());
			list.add(totalCountBean.getVocational_graduates1());
			list.add(totalCountBean.getMigrant_workers1());
			list.add(totalCountBean.getVeteran1());

			list.add(totalCountBean.getPersonal_declaration1());
			list.add(totalCountBean.getOrganization_recommended1());
			break;
		case DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER:
			list.add(totalCountBean.getLevel());
			list.add(totalCountBean.getLevel());
			list.add(totalCountBean.getProvince());
			list.add(totalCountBean.getCity());
			list.add(totalCountBean.getRegion());
			list.add(totalCountBean.getPeopleCount2());

			list.add(totalCountBean.getMale2());
			list.add(totalCountBean.getFemale2());
			list.add(totalCountBean.getAgeLevel1Num2());
			list.add(totalCountBean.getAgeLevel2Num2());
			list.add(totalCountBean.getAgeLevel3Num2());
			list.add(totalCountBean.getAgeLevel4Num2());
			list.add(totalCountBean.getAgeLevel5Num2());
			list.add(totalCountBean.getCultureLevel1Num2());
			list.add(totalCountBean.getCultureLevel2Num2());
			list.add(totalCountBean.getCultureLevel3Num2());
			list.add(totalCountBean.getCultureLevel4Num2());
			list.add(totalCountBean.getType2industry1());
			list.add(totalCountBean.getType2industry2());
			list.add(totalCountBean.getType2industry3());
			list.add(totalCountBean.getType2industry4());
			list.add(totalCountBean.getType2industry5());
			list.add(totalCountBean.getType2industry6());
			list.add(totalCountBean.getType2industry7());
			list.add(totalCountBean.getType2industry8());
			list.add(totalCountBean.getType2industry9());
			list.add(totalCountBean.getType2industry10());

			list.add(totalCountBean.getPlanting_large2());
			list.add(totalCountBean.getScale_farm2());
			list.add(totalCountBean.getFamily_farm2());
			list.add(totalCountBean.getAgricultural_enterprises2());
			list.add(totalCountBean.getFarmer_cooperatives2());
			list.add(totalCountBean.getCollege_students2());
			list.add(totalCountBean.getVocational_graduates2());
			list.add(totalCountBean.getMigrant_workers2());
			list.add(totalCountBean.getVeteran2());

			list.add(totalCountBean.getPersonal_declaration2());
			list.add(totalCountBean.getOrganization_recommended2());
			break;
		case DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER:
			list.add(totalCountBean.getLevel());
			list.add(totalCountBean.getProvince());
			list.add(totalCountBean.getCity());
			list.add(totalCountBean.getRegion());
			list.add(totalCountBean.getPeopleCount5());

			list.add(totalCountBean.getMale5());
			list.add(totalCountBean.getFemale5());
			list.add(totalCountBean.getAgeLevel1Num5());
			list.add(totalCountBean.getAgeLevel2Num5());
			list.add(totalCountBean.getAgeLevel3Num5());
			list.add(totalCountBean.getAgeLevel4Num5());
			list.add(totalCountBean.getAgeLevel5Num5());
			list.add(totalCountBean.getCultureLevel1Num5());
			list.add(totalCountBean.getCultureLevel2Num5());
			list.add(totalCountBean.getCultureLevel3Num5());
			list.add(totalCountBean.getCultureLevel4Num5());
			list.add(totalCountBean.getType5industry1());
			list.add(totalCountBean.getType5industry2());
			list.add(totalCountBean.getType5industry3());
			list.add(totalCountBean.getType5industry4());
			list.add(totalCountBean.getType5industry5());
			list.add(totalCountBean.getType5industry6());
			list.add(totalCountBean.getType5industry7());
			list.add(totalCountBean.getType5industry8());
			list.add(totalCountBean.getType5industry9());
			list.add(totalCountBean.getType5industry10());

			list.add(totalCountBean.getPlanting_large5());
			list.add(totalCountBean.getScale_farm5());
			list.add(totalCountBean.getFamily_farm5());
			list.add(totalCountBean.getFarmer_cooperatives5());
			list.add(totalCountBean.getAgricultural_enterprises5());
			list.add(totalCountBean.getService_experts5());

			list.add(totalCountBean.getPersonal_declaration5());
			list.add(totalCountBean.getOrganization_recommended5());
			break;
		case DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER:
			list.add(totalCountBean.getLevel());
			list.add(totalCountBean.getProvince());
			list.add(totalCountBean.getCity());
			list.add(totalCountBean.getRegion());
			list.add(totalCountBean.getPeopleCount3());

			list.add(totalCountBean.getMale3());
			list.add(totalCountBean.getFemale3());
			list.add(totalCountBean.getAgeLevel1Num3());
			list.add(totalCountBean.getAgeLevel2Num3());
			list.add(totalCountBean.getAgeLevel3Num3());
			list.add(totalCountBean.getAgeLevel4Num3());
			list.add(totalCountBean.getAgeLevel5Num3());
			list.add(totalCountBean.getCultureLevel1Num3());
			list.add(totalCountBean.getCultureLevel2Num3());
			list.add(totalCountBean.getCultureLevel3Num3());
			list.add(totalCountBean.getCultureLevel4Num3());

			list.add(totalCountBean.getType3job1());
			list.add(totalCountBean.getType3job2());
			list.add(totalCountBean.getType3job3());
			list.add(totalCountBean.getType3job4());
			list.add(totalCountBean.getType3job5());
			list.add(totalCountBean.getType3job6());
			list.add(totalCountBean.getType3job7());
			list.add(totalCountBean.getType3job8());
			list.add(totalCountBean.getType3job9());
			list.add(totalCountBean.getType3job10());
			list.add(totalCountBean.getType3job11());
			list.add(totalCountBean.getType3job12());
			list.add(totalCountBean.getType3job13());
			list.add(totalCountBean.getType3job14());
			list.add(totalCountBean.getType3job15());
			list.add(totalCountBean.getType3job16());
			list.add(totalCountBean.getType3job17());
			list.add(totalCountBean.getType3job18());

			list.add(totalCountBean.getPersonal_declaration3());
			list.add(totalCountBean.getOrganization_recommended3());
			break;
		case DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER:
			list.add(totalCountBean.getLevel());
			list.add(totalCountBean.getProvince());
			list.add(totalCountBean.getCity());
			list.add(totalCountBean.getRegion());
			list.add(totalCountBean.getPeopleCount4());

			list.add(totalCountBean.getMale4());
			list.add(totalCountBean.getFemale4());
			list.add(totalCountBean.getAgeLevel1Num4());
			list.add(totalCountBean.getAgeLevel2Num4());
			list.add(totalCountBean.getAgeLevel3Num4());
			list.add(totalCountBean.getAgeLevel4Num4());
			list.add(totalCountBean.getAgeLevel5Num4());
			list.add(totalCountBean.getCultureLevel1Num4());
			list.add(totalCountBean.getCultureLevel2Num4());
			list.add(totalCountBean.getCultureLevel3Num4());
			list.add(totalCountBean.getCultureLevel4Num4());

			list.add(totalCountBean.getType4job1());
			list.add(totalCountBean.getType4job2());
			list.add(totalCountBean.getType4job3());
			list.add(totalCountBean.getType4job4());
			list.add(totalCountBean.getType4job5());
			list.add(totalCountBean.getType4job6());
			list.add(totalCountBean.getType4job7());
			list.add(totalCountBean.getType4job8());
			list.add(totalCountBean.getType4job9());
			list.add(totalCountBean.getType4job10());
			list.add(totalCountBean.getType4job11());
			list.add(totalCountBean.getType4job12());
			list.add(totalCountBean.getType4job13());
			list.add(totalCountBean.getType4job14());
			list.add(totalCountBean.getType4job15());
			list.add(totalCountBean.getType4job16());
			list.add(totalCountBean.getType4job17());
			list.add(totalCountBean.getType4job18());

			list.add(totalCountBean.getPersonal_declaration4());
			list.add(totalCountBean.getOrganization_recommended4());
			break;
		}
		declareCollectMap.put("合计", list);
		declareCollectMap.putAll(declareReportMap);

		request.setAttribute("declareCollectMap", declareCollectMap);
		request.setAttribute("AdminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "DeclareStatisticsList.jsp");
		rd.forward(request, response);

	}

}
