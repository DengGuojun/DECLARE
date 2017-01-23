package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.lpmas.declare.admin.business.TrainingOrganizationCountBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.declare.business.DeclareInfoHelper;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingOrganizationCountList.do")
public class TrainingOrganizationCountList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingOrganizationCountList() {
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
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_KINDS_COUNT, OperationConfig.SEARCH)) {
			return;
		}
		String model = ParamKit.getParameter(request, "model", "").trim();
		int trainingType = ParamKit.getIntParameter(request, "trainingType", 0);
		HashMap<String, String> condMap = new HashMap<String, String>();

		String organizationType = ParamKit.getParameter(request, "organizationType", "").trim();
		if (!StringKit.isValid(organizationType)) {
			HttpResponseKit.alertMessage(response, "参数类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} else {
			if (!TrainingOrganizationConfig.ORGANIZATION_MAP.containsKey(organizationType)) {
				HttpResponseKit.alertMessage(response, "参数类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			condMap.put("organizationType", organizationType);
		}
		DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
		String trainingYear = ParamKit
				.getParameter(request, "trainingYear", String.valueOf(declareInfoHelper.getDeclareYear())).trim();
		if (StringKit.isValid(trainingYear)) {
			condMap.put("trainingYear", trainingYear);
		}
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
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
		TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
		List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = trainingOrganizationInfoBusiness
				.getTrainingOrganizationInfoListByMap(condMap);
		TrainingOrganizationCountBusiness trainingOrganizationCountBusiness = new TrainingOrganizationCountBusiness();
		HashMap<String, List<String>> declareReportMap = new HashMap<String, List<String>>();
		HashMap<String, List<String>> declareCollectMap = new HashMap<String, List<String>>();
		List<String> countList = null;
		for (TrainingOrganizationInfoBean trainingOrganizationInfoBean : trainingOrganizationInfoList) {
			if (model.equals("Number")) {
				if (StringKit.isValid(condMap.get("region"))) {
					if (StringKit.isValid(trainingOrganizationInfoBean.getRegion())) {
						countList = new ArrayList<String>();
						// 新插入县
						if (!declareCollectMap.containsKey(trainingOrganizationInfoBean.getRegion())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_REGION, trainingType);
							// 增加省,市名称，以作筛选
							countList.add(trainingOrganizationInfoBean.getProvince());
							countList.add(trainingOrganizationInfoBean.getCity());
							if (!countList.get(1).equals("0")) {
								declareCollectMap.put(trainingOrganizationInfoBean.getRegion(), countList);
							}
						} else {
							countList = declareCollectMap.get(trainingOrganizationInfoBean.getRegion());
							declareCollectMap.put(trainingOrganizationInfoBean.getRegion(),
									trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
											trainingOrganizationInfoBean, countList, trainingType));
						}
					}
				} else if (StringKit.isValid(condMap.get("city"))) {
					if (StringKit.isValid(trainingOrganizationInfoBean.getCity())) {
						countList = new ArrayList<String>();
						// 新插入市
						if (!declareCollectMap.containsKey(trainingOrganizationInfoBean.getCity())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_CITY, trainingType);
							// 增加省名称，以作筛选
							countList.add(trainingOrganizationInfoBean.getProvince());
							if (!countList.get(1).equals("0")) {
								declareCollectMap.put(trainingOrganizationInfoBean.getCity(), countList);
							}
						} else {
							countList = declareCollectMap.get(trainingOrganizationInfoBean.getCity());
							declareCollectMap.put(trainingOrganizationInfoBean.getCity(),
									trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
											trainingOrganizationInfoBean, countList, trainingType));
						}
					}
					if (StringKit.isValid(trainingOrganizationInfoBean.getRegion())) {
						countList = new ArrayList<String>();
						// 新插入县
						if (!declareReportMap.containsKey(trainingOrganizationInfoBean.getRegion())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_REGION, trainingType);
							// 增加省,市名称，以作筛选
							countList.add(trainingOrganizationInfoBean.getProvince());
							countList.add(trainingOrganizationInfoBean.getCity());
							if (!countList.get(1).equals("0")) {
								declareReportMap.put(trainingOrganizationInfoBean.getRegion(), countList);
							}
						} else {
							countList = declareReportMap.get(trainingOrganizationInfoBean.getRegion());
							declareReportMap.put(trainingOrganizationInfoBean.getRegion(),
									trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
											trainingOrganizationInfoBean, countList, trainingType));
						}
					}
				} else if (StringKit.isValid(condMap.get("province"))) {
					if (StringKit.isValid(trainingOrganizationInfoBean.getProvince())) {
						countList = new ArrayList<String>();
						// 新插入省
						if (!declareCollectMap.containsKey(trainingOrganizationInfoBean.getProvince())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_PROVINCE,
									trainingType);
							if (!countList.get(1).equals("0")) {
								declareCollectMap.put(trainingOrganizationInfoBean.getProvince(), countList);
							}
						} else {
							countList = declareCollectMap.get(trainingOrganizationInfoBean.getProvince());
							declareCollectMap.put(trainingOrganizationInfoBean.getProvince(),
									trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
											trainingOrganizationInfoBean, countList, trainingType));
						}
					}
					if (StringKit.isValid(trainingOrganizationInfoBean.getCity())) {
						countList = new ArrayList<String>();
						if (declareReportMap.containsKey(trainingOrganizationInfoBean.getCity())
								&& declareReportMap.get(trainingOrganizationInfoBean.getCity())
										.contains(trainingOrganizationInfoBean.getProvince())) {
							countList = declareReportMap.get(trainingOrganizationInfoBean.getCity());
							declareReportMap.put(trainingOrganizationInfoBean.getCity(),
									trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
											trainingOrganizationInfoBean, countList, trainingType));
						} else {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_CITY, trainingType);
							// 增加省名称，以作筛选
							countList.add(trainingOrganizationInfoBean.getProvince());
							if (!countList.get(1).equals("0")) {
								declareReportMap.put(trainingOrganizationInfoBean.getCity(), countList);
							}
						}
					}
				} else {
					if (StringKit.isValid(trainingOrganizationInfoBean.getProvince())) {
						countList = new ArrayList<String>();
						// 新插入全国
						if (!declareCollectMap.containsKey("全国")) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_COUNTRY, trainingType);
							if (!countList.get(1).equals("0")) {
								declareCollectMap.put("全国", countList);
							}
						} else {
							countList = declareCollectMap.get("全国");
							declareCollectMap.put("全国",
									trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
											trainingOrganizationInfoBean, countList, trainingType));
						}
					}
					if (StringKit.isValid(trainingOrganizationInfoBean.getProvince())) {
						countList = new ArrayList<String>();
						// 新插入省
						if (!declareReportMap.containsKey(trainingOrganizationInfoBean.getProvince())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_PROVINCE,
									trainingType);
							if (!countList.get(1).equals("0")) {
								declareReportMap.put(trainingOrganizationInfoBean.getProvince(), countList);
							}
						} else {
							countList = declareReportMap.get(trainingOrganizationInfoBean.getProvince());
							declareReportMap.put(trainingOrganizationInfoBean.getProvince(),
									trainingOrganizationCountBusiness.getTrainingOrganizationNumberCountList(
											trainingOrganizationInfoBean, countList, trainingType));
						}
					}
				}
			} else {
				if (StringKit.isValid(condMap.get("region"))) {
					if (StringKit.isValid(trainingOrganizationInfoBean.getRegion())) {
						countList = new ArrayList<String>();
						// 新插入县
						if (!declareCollectMap.containsKey(trainingOrganizationInfoBean.getRegion())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_REGION);
							// 增加省,市名称，以作筛选
							countList.add(trainingOrganizationInfoBean.getProvince());
							countList.add(trainingOrganizationInfoBean.getCity());
							declareCollectMap.put(trainingOrganizationInfoBean.getRegion(), countList);
						} else {
							countList = declareCollectMap.get(trainingOrganizationInfoBean.getRegion());
							declareCollectMap.put(trainingOrganizationInfoBean.getRegion(),
									trainingOrganizationCountBusiness
											.getTrainingOrganizationCountList(trainingOrganizationInfoBean, countList));
						}
					}
				} else if (StringKit.isValid(condMap.get("city"))) {
					if (StringKit.isValid(trainingOrganizationInfoBean.getCity())) {
						countList = new ArrayList<String>();
						// 新插入市
						if (!declareCollectMap.containsKey(trainingOrganizationInfoBean.getCity())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_CITY);
							// 增加省名称，以作筛选
							countList.add(trainingOrganizationInfoBean.getProvince());
							declareCollectMap.put(trainingOrganizationInfoBean.getCity(), countList);
						} else {
							countList = declareCollectMap.get(trainingOrganizationInfoBean.getCity());
							declareCollectMap.put(trainingOrganizationInfoBean.getCity(),
									trainingOrganizationCountBusiness
											.getTrainingOrganizationCountList(trainingOrganizationInfoBean, countList));
						}
					}
					if (StringKit.isValid(trainingOrganizationInfoBean.getRegion())) {
						countList = new ArrayList<String>();
						// 新插入县
						if (!declareReportMap.containsKey(trainingOrganizationInfoBean.getRegion())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_REGION);
							// 增加省,市名称，以作筛选
							countList.add(trainingOrganizationInfoBean.getProvince());
							countList.add(trainingOrganizationInfoBean.getCity());
							declareReportMap.put(trainingOrganizationInfoBean.getRegion(), countList);
						} else {
							countList = declareReportMap.get(trainingOrganizationInfoBean.getRegion());
							declareReportMap.put(trainingOrganizationInfoBean.getRegion(),
									trainingOrganizationCountBusiness
											.getTrainingOrganizationCountList(trainingOrganizationInfoBean, countList));
						}
					}
				} else if (StringKit.isValid(condMap.get("province"))) {
					if (StringKit.isValid(trainingOrganizationInfoBean.getProvince())) {
						countList = new ArrayList<String>();
						// 新插入省
						if (!declareCollectMap.containsKey(trainingOrganizationInfoBean.getProvince())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
							declareCollectMap.put(trainingOrganizationInfoBean.getProvince(), countList);
						} else {
							countList = declareCollectMap.get(trainingOrganizationInfoBean.getProvince());
							declareCollectMap.put(trainingOrganizationInfoBean.getProvince(),
									trainingOrganizationCountBusiness
											.getTrainingOrganizationCountList(trainingOrganizationInfoBean, countList));
						}
					}
					if (StringKit.isValid(trainingOrganizationInfoBean.getCity())) {
						countList = new ArrayList<String>();
						if (declareReportMap.containsKey(trainingOrganizationInfoBean.getCity())
								&& declareReportMap.get(trainingOrganizationInfoBean.getCity())
										.contains(trainingOrganizationInfoBean.getProvince())) {
							countList = declareReportMap.get(trainingOrganizationInfoBean.getCity());
							declareReportMap.put(trainingOrganizationInfoBean.getCity(),
									trainingOrganizationCountBusiness
											.getTrainingOrganizationCountList(trainingOrganizationInfoBean, countList));
						} else {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_CITY);
							// 增加省名称，以作筛选
							countList.add(trainingOrganizationInfoBean.getProvince());
							declareReportMap.put(trainingOrganizationInfoBean.getCity(), countList);
						}
					}
				} else {
					if (StringKit.isValid(trainingOrganizationInfoBean.getProvince())) {
						countList = new ArrayList<String>();
						// 新插入省
						if (!declareCollectMap.containsKey("全国")) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
							declareCollectMap.put("全国", countList);
						} else {
							countList = declareCollectMap.get("全国");
							declareCollectMap.put("全国", trainingOrganizationCountBusiness
									.getTrainingOrganizationCountList(trainingOrganizationInfoBean, countList));
						}
					}
					if (StringKit.isValid(trainingOrganizationInfoBean.getProvince())) {
						countList = new ArrayList<String>();
						// 新插入省
						if (!declareReportMap.containsKey(trainingOrganizationInfoBean.getProvince())) {
							countList = trainingOrganizationCountBusiness.getTrainingOrganizationCountList(
									trainingOrganizationInfoBean, DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
							declareReportMap.put(trainingOrganizationInfoBean.getProvince(), countList);
						} else {
							countList = declareReportMap.get(trainingOrganizationInfoBean.getProvince());
							declareReportMap.put(trainingOrganizationInfoBean.getProvince(),
									trainingOrganizationCountBusiness
											.getTrainingOrganizationCountList(trainingOrganizationInfoBean, countList));
						}
					}
				}
			}
		}
		request.setAttribute("declareReportMap", declareReportMap);
		request.setAttribute("declareCollectMap", declareCollectMap);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingOrganizationCountList.jsp");
		rd.forward(request, response);
	}

}
