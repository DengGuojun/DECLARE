package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.RecommendInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.RecommendInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.config.RecommendInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class RecommendInfoManage
 */
@WebServlet("/declare/admin/RecommendInfoManage.do")
public class RecommendInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecommendInfoManage() {
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
		// 权限
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		AdminUserInfoBean adminUserInfoBean = adminUserHelper.getAdminUserInfo();
		boolean hasPermission = true;
		if (adminUserInfoBean == null) {
			hasPermission = false;
		}

		RecommendInfoBean bean = new RecommendInfoBean();
		bean = BeanKit.request2Bean(request, RecommendInfoBean.class);
		RecommendInfoBusiness business = new RecommendInfoBusiness();
		int result = 0;

		if (bean.getRecommendType().equals(RecommendInfoConfig.RECOMMEND_TYPE_TEACHER)
				&& !adminUserHelper
						.checkPermission(DeclareAdminResource.TEACHER_INFO_RECOMMEND, OperationConfig.CREATE)) {
			hasPermission = false;
		} else if (bean.getRecommendType().equals(RecommendInfoConfig.RECOMMEND_TYPE_MATERIAL)
				&& !adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_MATERIAL_RECOMMEND,
						OperationConfig.CREATE)) {
			hasPermission = false;
		} else if (bean.getRecommendType().equals(RecommendInfoConfig.RECOMMEND_TYPE_BASE)
				&& !adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_RECOMMEND,
						OperationConfig.CREATE)) {
			hasPermission = false;
		}
		if (!hasPermission) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		if (bean.getRecommendId() <= 0) {
			HttpResponseKit.alertMessage(response, "推荐ID错误！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		if (!RecommendInfoConfig.RECOMMEND_TYPE_MAP.containsKey(bean.getRecommendType())) {
			HttpResponseKit.alertMessage(response, "推荐类型错误！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		if (business.getRecommendInfoByKey(bean.getRecommendId(), bean.getRecommendType(), bean.getProvince()) != null) {
			HttpResponseKit.alertMessage(response, "该记录已被推荐，不能重复推荐！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} else {
			bean.setCreateUser(adminUserHelper.getAdminUserId());
			bean.setStatus(Constants.STATUS_VALID);
			result = business.addRecommendInfo(bean);
		}

		if (result > 0) {
			if (bean.getRecommendType().equals(RecommendInfoConfig.RECOMMEND_TYPE_TEACHER)) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TeacherInfoList.do?teacherRange=0");
			} else if (bean.getRecommendType().equals(RecommendInfoConfig.RECOMMEND_TYPE_MATERIAL)) {
				String materialType = ParamKit.getParameter(request, "materialType");
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingMaterialInfoList.do?materialType="+materialType);
			} else if (bean.getRecommendType().equals(RecommendInfoConfig.RECOMMEND_TYPE_BASE)) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingOrganizationInfoList.do?organizationType=ORGANIZATION_BASE_TRAINING");
			}
		} else {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}
}