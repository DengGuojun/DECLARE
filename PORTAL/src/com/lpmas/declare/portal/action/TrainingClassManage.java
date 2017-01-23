package com.lpmas.declare.portal.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.declare.invoker.bean.ClassRoomMemberAddBean;
import com.lpmas.declare.invoker.bean.YunClassInvokeCommandBean;
import com.lpmas.declare.invoker.business.YunClassInvokCallBack;
import com.lpmas.declare.invoker.business.YunClassInvokeExecutor;
import com.lpmas.declare.invoker.business.YunClassInvoker;
import com.lpmas.declare.invoker.config.YunClassInvokeConfig;
import com.lpmas.declare.portal.business.DeclareInfoBusiness;
import com.lpmas.declare.portal.business.DeclareReportBusiness;
import com.lpmas.declare.portal.business.TrainingClassInfoBusiness;
import com.lpmas.declare.portal.business.TrainingClassUserBusiness;
import com.lpmas.declare.portal.config.DeclarePortalConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.ow.passport.sso.business.SsoClientHelper;

/**
 * Servlet implementation class TrainingClassManage
 */
@WebServlet("/declare/TrainingClassManage.do")
public class TrainingClassManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(TrainingClassManage.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		if (classId <= 0) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 根据班级ID查询班级
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean classInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (classInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 获取用户ID
		SsoClientHelper helper = new SsoClientHelper(request, response);
		int userId = helper.getUserId();

		// 检查这个用户是否填完表并提交了
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId,
				classInfoBean.getTrainingType(), DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));
		if (declareInfoBean == null) {
			HttpResponseKit.alertMessage(response, "类型非法，不能选择此班", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		if (declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_SUBMIT)) {
			HttpResponseKit.alertMessage(response, "未申报或申报不完整 不能报名这个课程", "DeclareInfoManage.do?declareType="
					+ declareInfoBean.getDeclareType());
			return;
		}

		// 查看这个用户是不是报名了
		TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
		TrainingClassUserBean trainingClassUserBean = trainingClassUserBusiness.getTrainingClassUserByKey(classId,
				declareInfoBean.getDeclareId());

		// request.setAttribute("TrainingOrgInfoBean", trainingOrgInfoBean);
		request.setAttribute("TrainingClassInfoBean", classInfoBean);
		request.setAttribute("TrainingClassUserBean", trainingClassUserBean);

		// 转发
		PortalKit.forwardPage(request, response, DeclarePortalConfig.PAGE_PATH + "TrainingClassManage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		if (classId <= 0) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 根据班级ID查询班级
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean classInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (classInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 获取用户ID
		SsoClientHelper helper = new SsoClientHelper(request, response);
		int userId = helper.getUserId();

		// 检查这个用户的申报是否通过了
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId,
				classInfoBean.getTrainingType(), DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));
		if (declareInfoBean == null
				|| !declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)) {
			HttpResponseKit.alertMessage(response, "未申报或申报未通过不能报名这个课程", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		
		//查看是否超过报名截止时间
		if(DateKit.diffTime(DateKit.REGEX_DATE, classInfoBean.getRegistrationEndTime(), new Date()) >0){
			HttpResponseKit.alertMessage(response, "报名时间已截止", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 查看这个用户是不是报名了
		TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("declareId", String.valueOf(declareInfoBean.getDeclareId()));
		List<TrainingClassUserBean> list = trainingClassUserBusiness.getTrainingClassUserListByMap(condMap);
		for (TrainingClassUserBean bean : list) {
			TrainingClassInfoBean existBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(bean.getClassId());
			if (existBean.getTrainingYear().equals(DateKit.formatDate(new Date(), DateKit.REGEX_YEAR))) {
				HttpResponseKit.alertMessage(response, "你已报名本年度培训班，不能重复报班", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
		}

		int result = 0;
		TrainingClassUserBean userBean = new TrainingClassUserBean();
		userBean.setClassId(classId);
		userBean.setCreateUser(userId);
		userBean.setDeclareId(declareInfoBean.getDeclareId());
		userBean.setSignUpTime(DateKit.getCurrentTimestamp());
		userBean.setUserStatus(TrainingClassUserConfig.USER_STATUS_APPROVE);
		userBean.setStatus(Constants.STATUS_VALID);
		result = trainingClassUserBusiness.addTrainingClassUser(userBean);
		if (result > 0) {
			// 插入mongo
			DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
			try {
				DeclareReportBean declareReportBean = declareReportBusiness.getDeclareReportByKey(String
						.valueOf(declareInfoBean.getDeclareId()));
				if (declareReportBean == null) {
					HttpResponseKit.alertMessage(response, "申报信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				List<Integer> trainingClassInfoList = declareReportBean.getTrainingClassInfoList();
				if (trainingClassInfoList == null) {
					trainingClassInfoList = new ArrayList<Integer>();
				}
				trainingClassInfoList.add(classId);
				declareReportBean.setTrainingClassInfoList(trainingClassInfoList);
				declareReportBusiness.updateDeclareReport(declareReportBean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("总表记录创建失败", e);
			}
			try {
				// 推送学员到云课堂
				YunClassInvokeCommandBean commandBean = new YunClassInvokeCommandBean();
				commandBean.setMethod(YunClassInvokeExecutor.HTTP_POST);
				commandBean.setService(YunClassInvokeConfig.YUN_SERVICE_ADD_USER_TO_CLASS);
				commandBean.setBody(trainingClassUserBusiness.trainingClassUser2MemberAddBean(userBean));

				YunClassInvoker invoker = new YunClassInvoker(commandBean, new YunClassInvokCallBack() {
					@Override
					public boolean process(Object data) {
						int result = 0;
						try {
							ClassRoomMemberAddBean postResult = JsonKit.toBean(data.toString(),
									ClassRoomMemberAddBean.class);
							// 更新到数据库
							TrainingClassUserBean userBean = trainingClassUserBusiness.getTrainingClassUserByKey(
									Integer.parseInt(postResult.getClassroomId()), declareInfoBean.getDeclareId());
							userBean.setModifyUser(userId);
							userBean.setSyncStatus(Constants.STATUS_VALID);
							result = trainingClassUserBusiness.updateTrainingClassUser(userBean);
							if (result > 0) {
								return true;
							} else {
								return false;
							}
						} catch (Exception e) {
							return false;
						}
					}
				});
				YunClassInvokeExecutor.attachAsync(invoker);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("总表记录创建失败", e);
			}
		}

		if (result > 0) {
			HttpResponseKit.alertMessage(response, "恭喜!您已成功报名此课程", "TrainingClassInfoList.do?declareType="
					+ classInfoBean.getTrainingType());
			return;
		} else {
			HttpResponseKit.alertMessage(response, "报名失败", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
	}
	
}
