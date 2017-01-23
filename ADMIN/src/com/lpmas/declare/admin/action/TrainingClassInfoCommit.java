package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassItemBusiness;
import com.lpmas.declare.admin.business.TrainingClassUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassItemBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.declare.config.TrainingClassInfoConfig;
import com.lpmas.declare.invoker.bean.ClassRoomAddBean;
import com.lpmas.declare.invoker.bean.YunClassInvokeCommandBean;
import com.lpmas.declare.invoker.business.YunClassInvokCallBack;
import com.lpmas.declare.invoker.business.YunClassInvokeExecutor;
import com.lpmas.declare.invoker.business.YunClassInvoker;
import com.lpmas.declare.invoker.config.YunClassInvokeConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassInfoCommit.do")
public class TrainingClassInfoCommit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(TrainingClassInfoCommit.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassInfoCommit() {
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
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		if (classId <= 0) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 根据班级ID查询班级信息
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean trainingClassInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (trainingClassInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		int result = 0;
		String action = ParamKit.getParameter(request, "action", "");
		if (action.equals(TrainingClassInfoConfig.COMMIT_ACTION_DELETE)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.REMOVE)) {
				return;
			}
			// 检查当前状态是否已经被改变
			if (trainingClassInfoBean.getStatus() == Constants.STATUS_NOT_VALID) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作",
						"/declare/admin/TrainingClassInfoList.do?trainingType="
								+ trainingClassInfoBean.getTrainingType());
				return;
			}
			// 新建和删除只能由同一个人操作
			if (adminHelper.getAdminUserId() != trainingClassInfoBean.getCreateUser()) {
				HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			trainingClassInfoBean.setStatus(Constants.STATUS_NOT_VALID);
			trainingClassInfoBean.setModifyUser(adminHelper.getAdminUserId());
			result = trainingClassInfoBusiness.updateTrainingClassInfo(trainingClassInfoBean);
			// 删除学员
			TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
			DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("classId", String.valueOf(classId));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<TrainingClassUserBean> trainingClassUserList = trainingClassUserBusiness
					.getTrainingClassUserListByMap(condMap);
			for (TrainingClassUserBean trainingClassUserBean : trainingClassUserList) {
				trainingClassUserBean.setStatus(Constants.STATUS_NOT_VALID);
				trainingClassUserBean.setModifyUser(adminHelper.getAdminUserId());
				trainingClassUserBusiness.updateTrainingClassUser(trainingClassUserBean);
				// 插入mongo
				try {
					DeclareReportBean declareReportBean = declareReportBusiness
							.getDeclareReportByKey(String.valueOf(trainingClassUserBean.getDeclareId()));
					List<Integer> trainingClassInfoList = declareReportBean.getTrainingClassInfoList();
					if (trainingClassInfoList != null) {
						Iterator<Integer> it = trainingClassInfoList.iterator();
						while (it.hasNext()) {
							Integer value = it.next();
							if (classId == value) {
								it.remove();
							}
						}
						declareReportBean.setTrainingClassInfoList(trainingClassInfoList);
						declareReportBusiness.updateDeclareReport(declareReportBean);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.error("总表记录创建失败", e);
				}
			}

			if (result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingClassInfoList.do?trainingType="
						+ trainingClassInfoBean.getTrainingType());
			}
		} else if (action.equals(TrainingClassInfoConfig.COMMIT_ACTION_SUBMIT)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.APPLY)) {
				return;
			}
			// 检查当前状态是否已经被改变
			if (!trainingClassInfoBean.getClassStatus().equals(TrainingClassInfoConfig.ClASS_STATUS_EDIT)
					&& !trainingClassInfoBean.getClassStatus().equals(TrainingClassInfoConfig.ClASS_STATUS_REJECTED)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 检查是否有班级信息
			TrainingClassItemBusiness trainingClassItemBusiness = new TrainingClassItemBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("classId", String.valueOf(classId));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<TrainingClassItemBean> trainingClassItemList = trainingClassItemBusiness
					.getTrainingClassItemListByMap(condMap);
			if (trainingClassItemList == null || trainingClassItemList.isEmpty()) {
				HttpResponseKit.alertMessage(response, "班级信息不完整", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}

			trainingClassInfoBean.setClassStatus(TrainingClassInfoConfig.CLASS_STATUS_SUBMITTED);
			trainingClassInfoBean.setModifyUser(adminHelper.getAdminUserId());
			result = trainingClassInfoBusiness.updateTrainingClassInfo(trainingClassInfoBean);

			if (result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingClassInfoList.do?trainingType="
						+ trainingClassInfoBean.getTrainingType());
			}
		} else if (action.equals(TrainingClassInfoConfig.COMMIT_ACTION_REPORT)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.SUBMIT)) {
				return;
			}
			// 检查当前状态是否已经被改变
			if (!trainingClassInfoBean.getClassStatus().equals(TrainingClassInfoConfig.CLASS_STATUS_SUBMITTED)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 检查是否有班级信息
			TrainingClassItemBusiness trainingClassItemBusiness = new TrainingClassItemBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("classId", String.valueOf(classId));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<TrainingClassItemBean> trainingClassItemList = trainingClassItemBusiness
					.getTrainingClassItemListByMap(condMap);
			if (trainingClassItemList == null || trainingClassItemList.isEmpty()) {
				HttpResponseKit.alertMessage(response, "班级信息不完整", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			trainingClassInfoBean.setClassStatus(TrainingClassInfoConfig.CLASS_STATUS_APPROVED);
			trainingClassInfoBean.setModifyUser(adminHelper.getAdminUserId());
			result = trainingClassInfoBusiness.updateTrainingClassInfo(trainingClassInfoBean);

			if (result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				// 推送消息到云课堂
				YunClassInvokeCommandBean commandBean = new YunClassInvokeCommandBean();
				commandBean.setMethod(YunClassInvokeExecutor.HTTP_POST);
				commandBean.setService(YunClassInvokeConfig.YUN_SERVICE_ADD_CLASS);
				commandBean
						.setBody(trainingClassInfoBusiness.trainingClassInfo2ClassRoomAddBean(trainingClassInfoBean));

				YunClassInvoker invoker = new YunClassInvoker(commandBean, new YunClassInvokCallBack() {
					@Override
					public boolean process(Object data) {
						int result = 0;
						try {
							ClassRoomAddBean postResult = JsonKit.toBean(data.toString(), ClassRoomAddBean.class);
							// 更新到数据库
							TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
							TrainingClassInfoBean yunClass = trainingClassInfoBusiness
									.getTrainingClassInfoByKey(Integer.valueOf(postResult.getClassroomId()));
							yunClass.setSyncStatus(Constants.STATUS_VALID);
							result = trainingClassInfoBusiness.updateTrainingClassInfo(yunClass);
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
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingClassInfoList.do?trainingType="
						+ trainingClassInfoBean.getTrainingType());
			}
		} else if (action.equals(TrainingClassInfoConfig.COMMIT_ACTION_RESUBMIT)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.REJECT)) {
				return;
			}
			// 检查当前状态是否已经被改变
			if (!trainingClassInfoBean.getClassStatus().equals(TrainingClassInfoConfig.CLASS_STATUS_APPROVED)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}

			trainingClassInfoBean.setClassStatus(TrainingClassInfoConfig.ClASS_STATUS_REJECTED);
			trainingClassInfoBean.setModifyUser(adminHelper.getAdminUserId());
			result = trainingClassInfoBusiness.updateTrainingClassInfo(trainingClassInfoBean);

			if (result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingClassInfoList.do?trainingType="
						+ trainingClassInfoBean.getTrainingType());
			}
		} else {
			HttpResponseKit.alertMessage(response, "提交操作非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

	}

}
