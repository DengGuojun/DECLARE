package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminRoleUserBean;
import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.AnnouncementInfoBean;
import com.lpmas.declare.admin.bean.MessageReceiverBean;
import com.lpmas.declare.admin.bean.RecommendInfoBean;
import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.bean.TrainingClassTargetBean;
import com.lpmas.declare.admin.bean.TrainingMaterialInfoBean;
import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminRoleUserBusiness;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.business.AnnouncementInfoBusiness;
import com.lpmas.declare.admin.business.MajorInfoBusiness;
import com.lpmas.declare.admin.business.MessageReceiverBusiness;
import com.lpmas.declare.admin.business.RecommendInfoBusiness;
import com.lpmas.declare.admin.business.TeacherInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassTargetBusiness;
import com.lpmas.declare.admin.business.TrainingClassUserBusiness;
import com.lpmas.declare.admin.business.TrainingMaterialInfoBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.AnnouncementInfoConfig;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.RecommendInfoConfig;
import com.lpmas.declare.bean.MajorInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.declare.invoker.bean.ArticleInfoBean;
import com.lpmas.declare.invoker.business.ArticleInfoBusiness;
import com.lpmas.declare.invoker.config.ArticleInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.NumeralOperationKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;

/**
 * Servlet implementation class Index
 */
@WebServlet("/declare/admin/Index.do")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Index() {
		super();
		// TODO Auto-generated constructor stub
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
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		PageBean pageBean = new PageBean(1, 5);

		// 查询本省师资推荐
		TeacherInfoBusiness teacherInfoBusiness = new TeacherInfoBusiness();
		MajorInfoBusiness majorInfoBusiness = new MajorInfoBusiness();
		List<TeacherInfoBean> recommendTeacherList = new ArrayList<TeacherInfoBean>();
		Map<Integer, String> majorNameMap = new HashMap<Integer, String>();
		HashMap<String, String> condMap = new HashMap<String, String>();
		if (StringKit.isValid(adminUserInfoBean.getProvince())) {
			condMap.put("province", adminUserInfoBean.getProvince());
		}
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		RecommendInfoBusiness business = new RecommendInfoBusiness();
		condMap.put("recommendType", RecommendInfoConfig.RECOMMEND_TYPE_TEACHER);
		PageResultBean<RecommendInfoBean> recommendTeacherResult = business.getRecommendInfoPageListByMap(condMap,
				pageBean);
		for (RecommendInfoBean recommendInfoBean : recommendTeacherResult.getRecordList()) {
			TeacherInfoBean bean = teacherInfoBusiness.getTeacherInfoByKey(recommendInfoBean.getRecommendId());
			recommendTeacherList.add(bean);
			MajorInfoBean majorInfoBean = majorInfoBusiness.getMajorInfoByKey(bean.getMajorId());
			majorNameMap.put(bean.getTeacherId(), majorInfoBean.getMajorName());
		}

		request.setAttribute("RecommendTeacherList", recommendTeacherList);
		request.setAttribute("MajorNameMap", majorNameMap);

		// 查询本省基地推荐
		TrainingOrganizationInfoBusiness trainingOrgBusiness = new TrainingOrganizationInfoBusiness();
		List<TrainingOrganizationInfoBean> recommendBaseList = new ArrayList<TrainingOrganizationInfoBean>();
		condMap.put("recommendType", RecommendInfoConfig.RECOMMEND_TYPE_BASE);
		PageResultBean<RecommendInfoBean> recommendBaseResult = business.getRecommendInfoPageListByMap(condMap,
				pageBean);
		for (RecommendInfoBean recommendInfoBean : recommendBaseResult.getRecordList()) {
			TrainingOrganizationInfoBean bean = trainingOrgBusiness.getTrainingOrganizationInfoByKey(recommendInfoBean
					.getRecommendId());
			recommendBaseList.add(bean);
		}
		request.setAttribute("RecommendBaseList", recommendBaseList);

		// 查询本省教材推荐
		TrainingMaterialInfoBusiness trainingMaterialBusiness = new TrainingMaterialInfoBusiness();
		List<TrainingMaterialInfoBean> recommendMaterialList = new ArrayList<TrainingMaterialInfoBean>();
		condMap.put("recommendType", RecommendInfoConfig.RECOMMEND_TYPE_MATERIAL);
		PageResultBean<RecommendInfoBean> recommendMaterialResult = business.getRecommendInfoPageListByMap(condMap,
				pageBean);
		for (RecommendInfoBean recommendInfoBean : recommendMaterialResult.getRecordList()) {
			TrainingMaterialInfoBean bean = trainingMaterialBusiness.getTrainingMaterialInfoByKey(recommendInfoBean
					.getRecommendId());
			recommendMaterialList.add(bean);
		}
		request.setAttribute("RecommendMaterialList", recommendMaterialList);
		
		//查询轮播图和资讯
		ArticleInfoBusiness articleInfoBusiness = new ArticleInfoBusiness();
		List<ArticleInfoBean> imageList = articleInfoBusiness.getArticleInfoListByType(ArticleInfoConfig.ARTICLE_TYPE_IMAGES, 5);
		List<ArticleInfoBean> articleList = articleInfoBusiness.getArticleInfoListByType(ArticleInfoConfig.ARTICLE_TYPE_RECOMMEND, 8);
		request.setAttribute("ImageList", imageList);
		request.setAttribute("ArticleList", articleList);
		
		// 通知公告
		condMap = new HashMap<String, String>();
		AdminRoleUserBusiness roleUserBusiness = new AdminRoleUserBusiness();
		List<AdminRoleUserBean> roleUserBean = roleUserBusiness.getAdminRoleUserListByUserId(
				adminHelper.getAdminUserId(), Constants.STATUS_VALID);
		condMap.put("receiver", String.valueOf(roleUserBean.get(0).getRoleId()));
		condMap.put("announcementStatus", AnnouncementInfoConfig.ANNOUNCEMENT_STATUS_PUBLISHED);
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));

		AnnouncementInfoBusiness announcementInfoBusiness = new AnnouncementInfoBusiness();
		PageResultBean<AnnouncementInfoBean> announcementResult = announcementInfoBusiness
				.getAnnouncementInfoPageListByMap(condMap, pageBean);
		request.setAttribute("AnnouncementList", announcementResult.getRecordList());

		// 已收邮件
		MessageReceiverBusiness receiverBusiness = new MessageReceiverBusiness();
		condMap = new HashMap<String, String>();
		condMap.put("receiverId", String.valueOf(adminHelper.getAdminUserId()));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		PageResultBean<MessageReceiverBean> messageResult = receiverBusiness.getMessageReceiverPageListByMap(condMap,
				pageBean);
		request.setAttribute("MessageList", messageResult.getRecordList());
		// 获取邮件发件人姓名
		AdminUserInfoBusiness userInfoBusiness = new AdminUserInfoBusiness();
		Map<Integer, String> messageCreateUserMap = new HashMap<Integer, String>();
		for (MessageReceiverBean bean : messageResult.getRecordList()) {
			AdminUserInfoBean senderUserInfoBean = userInfoBusiness.getAdminUserInfoByKey(bean.getCreateUser());
			messageCreateUserMap.put(bean.getCreateUser(), senderUserInfoBean.getAdminUserName());
		}

		// 目标完成情况
		TrainingClassTargetBusiness trainingClassTargetBusiness = new TrainingClassTargetBusiness();
		TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
		Map<Integer, String> targetPercentMap = new HashMap<Integer, String>();
		Map<Integer, Integer> targetFinishedMap = new HashMap<Integer, Integer>();
		targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER, "0.00");
		targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER, "0.00");
		targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER, "0.00");
		targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER, "0.00");
		targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER, "0.00");

		int youngCount = trainingClassUserBusiness.getTrainingClassUserCountByCondition(
				adminUserInfoBean.getProvince(), adminUserInfoBean.getCity(), adminUserInfoBean.getRegion(),
				String.valueOf(year), TrainingClassUserConfig.USER_STATUS_APPROVE,
				DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER);
		int leaderCount = trainingClassUserBusiness.getTrainingClassUserCountByCondition(
				adminUserInfoBean.getProvince(), adminUserInfoBean.getCity(), adminUserInfoBean.getRegion(),
				String.valueOf(year), TrainingClassUserConfig.USER_STATUS_APPROVE,
				DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER);
		int productCount = trainingClassUserBusiness.getTrainingClassUserCountByCondition(
				adminUserInfoBean.getProvince(), adminUserInfoBean.getCity(), adminUserInfoBean.getRegion(),
				String.valueOf(year), TrainingClassUserConfig.USER_STATUS_APPROVE,
				DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER);
		int technicalCount = trainingClassUserBusiness.getTrainingClassUserCountByCondition(
				adminUserInfoBean.getProvince(), adminUserInfoBean.getCity(), adminUserInfoBean.getRegion(),
				String.valueOf(year), TrainingClassUserConfig.USER_STATUS_APPROVE,
				DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER);
		int serviceCount = trainingClassUserBusiness.getTrainingClassUserCountByCondition(
				adminUserInfoBean.getProvince(), adminUserInfoBean.getCity(), adminUserInfoBean.getRegion(),
				String.valueOf(year), TrainingClassUserConfig.USER_STATUS_APPROVE,
				DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER);

		targetFinishedMap.put(DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER, youngCount);
		targetFinishedMap.put(DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER, leaderCount);
		targetFinishedMap.put(DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER, productCount);
		targetFinishedMap.put(DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER, technicalCount);
		targetFinishedMap.put(DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER, serviceCount);
		TrainingClassTargetBean target = trainingClassTargetBusiness.getTrainingClassTargetByCondition(
				adminUserInfoBean.getProvince(), adminUserInfoBean.getCity(), adminUserInfoBean.getRegion(),
				String.valueOf(year), 0);
		if (target != null) {
			if (target.getTargetQuantity1() > 0) {
				targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER,
						NumeralOperationKit.calculatePercent(youngCount, target.getTargetQuantity1(), 2));
			}
			if (target.getTargetQuantity5() > 0) {
				targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER,
						NumeralOperationKit.calculatePercent(leaderCount, target.getTargetQuantity5(), 2));
			}
			if (target.getTargetQuantity2() > 0) {
				targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER,
						NumeralOperationKit.calculatePercent(productCount, target.getTargetQuantity2(), 2));
			}
			if (target.getTargetQuantity3() > 0) {
				targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER,
						NumeralOperationKit.calculatePercent(technicalCount, target.getTargetQuantity3(), 2));
			}
			if (target.getTargetQuantity4() > 0) {
				targetPercentMap.put(DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER,
						NumeralOperationKit.calculatePercent(serviceCount, target.getTargetQuantity4(), 2));
			}
		}

		request.setAttribute("MessageCreateUserMap", messageCreateUserMap);
		request.setAttribute("AdminUserHelper", adminHelper);
		request.setAttribute("targetFinishedMap", targetFinishedMap);
		request.setAttribute("targetPercentMap", targetPercentMap);
		request.setAttribute("year", year);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "Index.jsp");
		rd.forward(request, response);
	}

	public static void main(String[] args) {

	}

}
