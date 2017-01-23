package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.lpmas.declare.admin.business.DeclareInfoBusiness;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.declare.config.TrainingClassInfoConfig;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.declare.invoker.bean.ClassRoomMemberAddBean;
import com.lpmas.declare.invoker.bean.YunClassInvokeCommandBean;
import com.lpmas.declare.invoker.business.YunClassInvokCallBack;
import com.lpmas.declare.invoker.business.YunClassInvokeExecutor;
import com.lpmas.declare.invoker.business.YunClassInvoker;
import com.lpmas.declare.invoker.config.YunClassInvokeConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/TrainingClassUserCommit.do")
public class TrainingClassUserCommit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(TrainingClassUserCommit.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassUserCommit() {
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
		int declareId = ParamKit.getIntParameter(request, "declareId", 0);
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		if (declareId <= 0 || classId <= 0) {
			HttpResponseKit.alertMessage(response, "参数ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 根据申报ID查询申报信息
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(declareId);
		if (declareInfoBean == null) {
			HttpResponseKit.alertMessage(response, "申报信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 根据班级ID查询班级信息
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean trainingClassInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (trainingClassInfoBean == null) {
			HttpResponseKit.alertMessage(response, "班级信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 检查班级是否通过
		if (!trainingClassInfoBean.getClassStatus().equals(TrainingClassInfoConfig.CLASS_STATUS_APPROVED)) {
			HttpResponseKit.alertMessage(response, "班级还没通过审核，不能添加学员", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 新建和修改只能由同一个人操作
		if (adminHelper.getAdminUserId() != trainingClassInfoBean.getCreateUser()) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		String action = ParamKit.getParameter(request, "action", "");
		int result = 0;
		long resultMongo = 0;
		if (action.equals(TrainingClassUserConfig.COMMIT_ACTION_ADD)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.UPDATE)) {
				return;
			}
			TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
			TrainingClassUserBean trainingClassUserBean = trainingClassUserBusiness.getTrainingClassUserByKey(classId,
					declareId);
			// 检查当前状态是否已经被改变
			if (trainingClassUserBean != null && trainingClassUserBean.getStatus() == Constants.STATUS_VALID) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作",
						"/declare/admin/TrainingClassUserInfoManage.do?classId=" + classId);
				return;
			}
			// 新建
			if (trainingClassUserBean == null) {
				trainingClassUserBean = new TrainingClassUserBean();
				trainingClassUserBean.setClassId(classId);
				trainingClassUserBean.setCreateUser(adminHelper.getAdminUserId());
				trainingClassUserBean.setDeclareId(declareId);
				trainingClassUserBean.setUserStatus(TrainingClassUserConfig.USER_STATUS_APPROVE);
				trainingClassUserBean.setStatus(Constants.STATUS_VALID);
				result = trainingClassUserBusiness.addTrainingClassUser(trainingClassUserBean);
			} else {
				// 修改
				trainingClassUserBean.setStatus(Constants.STATUS_VALID);
				trainingClassUserBean.setModifyUser(adminHelper.getAdminUserId());
				result = trainingClassUserBusiness.updateTrainingClassUser(trainingClassUserBean);
			}
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
				resultMongo = declareReportBusiness.updateDeclareReport(declareReportBean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("总表记录创建失败", e);
			}
			if (resultMongo <= 0 || result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				// 推送学员到云课堂
				try {
					YunClassInvokeCommandBean commandBean = new YunClassInvokeCommandBean();
					commandBean.setMethod(YunClassInvokeExecutor.HTTP_POST);
					commandBean.setService(YunClassInvokeConfig.YUN_SERVICE_ADD_USER_TO_CLASS);
					commandBean.setBody(trainingClassUserBusiness
							.trainingClassUser2MemberAddBean(trainingClassUserBean));

					YunClassInvoker invoker = new YunClassInvoker(commandBean, new YunClassInvokCallBack() {
						@Override
						public boolean process(Object data) {
							int result = 0;
							try {
								ClassRoomMemberAddBean postResult = JsonKit.toBean(data.toString(),
										ClassRoomMemberAddBean.class);
								// 更新到数据库
								TrainingClassUserBean userBean = trainingClassUserBusiness.getTrainingClassUserByKey(
										Integer.parseInt(postResult.getClassroomId()), declareId);
								userBean.setModifyUser(adminHelper.getAdminUserId());
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
					log.error("推送到云课堂失败", e);
				}
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingClassUserInfoManage.do?classId="
						+ classId);
			}
		} else if (action.equals(TrainingClassUserConfig.COMMIT_ACTION_DELETE)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.UPDATE)) {
				return;
			}
			TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
			TrainingClassUserBean trainingClassUserBean = trainingClassUserBusiness.getTrainingClassUserByKey(classId,
					declareId);
			// 检查当前状态是否已经被改变
			if (trainingClassUserBean != null && trainingClassUserBean.getStatus() == Constants.STATUS_NOT_VALID) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作",
						"/declare/admin/TrainingClassUserInfoManage.do?classId=" + classId);
				return;
			}
			if (trainingClassUserBean == null) {
				HttpResponseKit.alertMessage(response, "学员不存在", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}

			// 修改
			trainingClassUserBean.setStatus(Constants.STATUS_NOT_VALID);
			trainingClassUserBean.setModifyUser(adminHelper.getAdminUserId());
			result = trainingClassUserBusiness.updateTrainingClassUser(trainingClassUserBean);

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
				if (trainingClassInfoList != null) {
					Iterator<Integer> it = trainingClassInfoList.iterator();
					while (it.hasNext()) {
						Integer value = it.next();
						if (classId == value) {
							it.remove();
						}
					}
					declareReportBean.setTrainingClassInfoList(trainingClassInfoList);
					resultMongo = declareReportBusiness.updateDeclareReport(declareReportBean);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("总表记录创建失败", e);
			}
			if (resultMongo <= 0 || result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				// 推送学员到云课堂
				YunClassInvokeCommandBean commandBean = new YunClassInvokeCommandBean();
				commandBean.setMethod(YunClassInvokeExecutor.HTTP_POST);
				commandBean.setService(YunClassInvokeConfig.YUN_SERVICE_DELETE_USER_TO_CLASS);
				commandBean
						.setBody(trainingClassUserBusiness.trainingClassUser2MemberDeleteBean(trainingClassUserBean));

				YunClassInvoker invoker = new YunClassInvoker(commandBean, new YunClassInvokCallBack() {
					@Override
					public boolean process(Object data) {
						return true;
					}
				});
				YunClassInvokeExecutor.attachAsync(invoker);
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingClassUserInfoManage.do?classId="
						+ classId);
			}
		} else {
			HttpResponseKit.alertMessage(response, "提交操作非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
	}

}
