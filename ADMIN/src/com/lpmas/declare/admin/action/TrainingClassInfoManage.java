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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.MajorTypeBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.declare.bean.MajorTypeBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.TrainingClassInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

@WebServlet("/declare/admin/TrainingClassInfoManage.do")
public class TrainingClassInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TrainingClassInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.SEARCH)) {
			return;
		}
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		TrainingClassInfoBean bean = new TrainingClassInfoBean();
		TrainingClassInfoBusiness business = new TrainingClassInfoBusiness();
		if (classId > 0) {
			bean = business.getTrainingClassInfoByKey(classId);
		} else {
			bean.setAuthStatus(TrainingClassInfoConfig.AUTH_STATUS_WAIT_APPROVE);
			bean.setSyncStatus(Constants.STATUS_NOT_VALID);
			bean.setStatus(Constants.STATUS_VALID);
			int trainingType = ParamKit.getIntParameter(request, "trainingType", 0);
			bean.setTrainingType(trainingType);
		}
		// 产业信息
		MajorTypeBusiness majorTypeBusiness = new MajorTypeBusiness();
		List<MajorTypeBean> majorTypeList = majorTypeBusiness.getMajorTypeAllList();
		request.setAttribute("majorTypeList", majorTypeList);
		// 机构信息
		TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("province", adminUserInfoBean.getProvince());
		condMap.put("city", adminUserInfoBean.getCity());
		condMap.put("region", adminUserInfoBean.getRegion());
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		condMap.put("organizationType", TrainingOrganizationConfig.ORGANIZATION_TRAINING);
		List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = trainingOrganizationInfoBusiness
				.getTrainingOrganizationInfoListByMap(condMap);
		request.setAttribute("trainingOrganizationInfoList", trainingOrganizationInfoList);

		request.setAttribute("TrainingClassInfoBean", bean);
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = this.getServletContext()
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingClassInfoManage.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		TrainingClassInfoBean bean = new TrainingClassInfoBean();
		try {
			bean = BeanKit.request2Bean(request, TrainingClassInfoBean.class);
			TrainingClassInfoBusiness business = new TrainingClassInfoBusiness();
			// 组装项目实施内容
			ReturnMessageBean messageBean = business.assemblyTrainingItemContentBean(request, bean);
			if (StringKit.isValid(messageBean.getMessage())) {
				HttpResponseKit.alertMessage(response, messageBean.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			messageBean = business.verifyTrainingClassInfo(bean);
			if (StringKit.isValid(messageBean.getMessage())) {
				HttpResponseKit.alertMessage(response, messageBean.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			bean.setProvince(adminUserInfoBean.getProvince());
			bean.setCity(adminUserInfoBean.getCity());
			bean.setRegion(adminUserInfoBean.getRegion());
			int result = 0;
			bean.setClassStatus(TrainingClassInfoConfig.ClASS_STATUS_EDIT);
			if (bean.getClassId() > 0) {
				if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.UPDATE)) {
					return;
				}
				TrainingClassInfoBean originalClassInfoBean = business.getTrainingClassInfoByKey(bean.getClassId());
				if (originalClassInfoBean == null) {
					HttpResponseKit.alertMessage(response, "班级非法！", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 新建和修改只能由同一个人操作
				if (adminHelper.getAdminUserId() != originalClassInfoBean.getCreateUser()) {
					HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateTrainingClassInfo(bean);
			} else {
				if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.CREATE)) {
					return;
				}
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addTrainingClassInfo(bean);
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功",
						"/declare/admin/TrainingClassInfoList.do?trainingType=" + bean.getTrainingType());
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
