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
import com.lpmas.declare.admin.business.AdminUserGroupBusiness;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class TrainingOrganizationInfoManage
 */
@WebServlet("/declare/admin/TrainingOrganizationInfoManage.do")
public class TrainingOrganizationInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingOrganizationInfoManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int organizationId = ParamKit.getIntParameter(request, "organizationId", 0);
		String organizationType = ParamKit.getParameter(request, "organizationType",
				TrainingOrganizationConfig.ORGANIZATION_TRAINING); // 获取类型

		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);

		TrainingOrganizationInfoBean bean = new TrainingOrganizationInfoBean();

		bean.setOrganizationCategory(organizationType);
		if (organizationId > 0) {
			if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)) {
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO,
						OperationConfig.UPDATE)) {
					return;
				}
			} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)) {
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO,
						OperationConfig.UPDATE)) {
					return;
				}
			} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)) {
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO,
						OperationConfig.UPDATE)) {
					return;
				}
			}
			TrainingOrganizationInfoBusiness business = new TrainingOrganizationInfoBusiness();
			bean = business.getTrainingOrganizationInfoByKey(organizationId);

		} else {
			if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)) {
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO,
						OperationConfig.CREATE)) {
					return;
				}
			} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)) {
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO,
						OperationConfig.CREATE)) {
					return;
				}
			} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)) {
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO,
						OperationConfig.CREATE)) {
					return;
				}
			}
			
			AdminUserGroupBusiness userGroupBusiness = new AdminUserGroupBusiness();
			String organizationLevel = userGroupBusiness.getAdminUserGroupByKey(adminUserHelper.getAdminUserInfo().getGroupId()).getAdminGroupLevel();
			bean.setProvince(adminUserHelper.getAdminUserInfo().getProvince());
			bean.setCity(adminUserHelper.getAdminUserInfo().getCity());
			bean.setRegion(adminUserHelper.getAdminUserInfo().getRegion());
			bean.setStatus(Constants.STATUS_VALID);
			bean.setOrganizationLevel(organizationLevel);
			bean.setOrganizationType(organizationType);
		}
		request.setAttribute("TrainingOrganizationInfoBean", bean);
		request.setAttribute("adminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH
				+ "TrainingOrganizationInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);

		TrainingOrganizationInfoBean bean = new TrainingOrganizationInfoBean();
		String organizationType = ParamKit.getParameter(request, "organizationType",
				TrainingOrganizationConfig.ORGANIZATION_TRAINING); // 获取类型

		try {
			bean = BeanKit.request2Bean(request, TrainingOrganizationInfoBean.class);
			TrainingOrganizationInfoBusiness business = new TrainingOrganizationInfoBusiness();
			// 录入时用户的等级不为空
			if (StringKit.isValid(adminUserHelper.getAdminUserInfo().getRegion())) {
				bean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
			} else if (StringKit.isValid(adminUserHelper.getAdminUserInfo().getCity())) {
				bean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
			} else if (StringKit.isValid(adminUserHelper.getAdminUserInfo().getProvince())) {
				bean.setOrganizationLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			}
			// 每个培训年度每一级的培训机构少于5个（河南省级放开，安徽全省放开）
			if (bean.getOrganizationType() == TrainingOrganizationConfig.ORGANIZATION_TRAINING) {
				HashMap<String, String> condMap = new HashMap<String, String>();
				condMap.put("trainingYear", bean.getTrainingYear());
				condMap.put("province", bean.getProvince());
				condMap.put("city", bean.getCity());
				condMap.put("region", bean.getRegion());
				condMap.put("organizationLevel", bean.getOrganizationLevel());
				condMap.put("organizationType", TrainingOrganizationConfig.ORGANIZATION_TRAINING);
				condMap.put("status", String.valueOf(Constants.STATUS_VALID));
				List<TrainingOrganizationInfoBean> list = business.getTrainingOrganizationInfoListByMap(condMap);
				boolean isHeNanProvince = bean.getProvince().equals("河南省") && !StringKit.isValid(bean.getCity())
						&& !StringKit.isValid(bean.getRegion());
				boolean isAnHuiProvince = bean.getProvince().equals("安徽省");
				if (list.size() >= 4 && !isHeNanProvince && !isAnHuiProvince) {
					HttpResponseKit.alertMessage(response, "同一培训年度的培训机构不能多于4个", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			}

			ReturnMessageBean returnMessageBean = business.verifyTrainingOrganizationInfo(bean);
			if (StringKit.isValid(returnMessageBean.getMessage())) {
				HttpResponseKit.alertMessage(response, returnMessageBean.getMessage(),
						HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			int result = 0;
			if (bean.getOrganizationId() > 0) {
				if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)) {
					if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO,
							OperationConfig.UPDATE)) {
						return;
					}
				} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)) {
					if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO,
							OperationConfig.UPDATE)) {
						return;
					}
				} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)) {
					if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO,
							OperationConfig.UPDATE)) {
						return;
					}
				}
				bean.setModifyUser(adminUserHelper.getAdminUserId());
				result = business.updateTrainingOrganizationInfo(bean);
			} else {
				// 增加数据
				if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)) {
					if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO,
							OperationConfig.CREATE)) {
						return;
					}
				} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)) {
					if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO,
							OperationConfig.CREATE)) {
						return;
					}
				} else if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)) {
					if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO,
							OperationConfig.CREATE)) {
						return;
					}
				}
				bean.setProvince(adminUserHelper.getAdminUserInfo().getProvince());
				bean.setCity(adminUserHelper.getAdminUserInfo().getCity());
				bean.setRegion(adminUserHelper.getAdminUserInfo().getRegion());
				bean.setCreateUser(adminUserHelper.getAdminUserId());
				result = business.addTrainingOrganizationInfo(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功",
						"/declare/admin/TrainingOrganizationInfoList.do?organizationType=" + organizationType);
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {

		}
	}

}
