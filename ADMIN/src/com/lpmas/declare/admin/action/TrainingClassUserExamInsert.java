package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassUserBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

@WebServlet("/declare/admin/TrainingClassUserExamInsert.do")
public class TrainingClassUserExamInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassUserExamInsert() {
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
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.UPDATE)) {
			return;
		}
		ReturnMessageBean messageBean = new ReturnMessageBean();
		String checkStrexamResultApprove = ParamKit.getParameter(request, "checkStrexamResultApprove", "").trim();
		String checkStrexamResultNotApprove = ParamKit.getParameter(request, "checkStrexamResultNotApprove", "").trim();
		String hasCertification = ParamKit.getParameter(request, "hasCertification", "").trim();
		String hasNotCertification = ParamKit.getParameter(request, "hasNotCertification", "").trim();
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		// 根据班级ID查询班级信息
		TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean trainingClassInfoBean = trainingClassInfoBusiness.getTrainingClassInfoByKey(classId);
		if (trainingClassInfoBean == null) {
			messageBean.setMessage("班级信息不存在");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}
		// 新建和修改只能由同一个人操作
		if (adminHelper.getAdminUserId() != trainingClassInfoBean.getCreateUser()) {
			messageBean.setMessage("你没有该功能的操作权限！");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}
		TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
		HashMap<Integer, TrainingClassUserBean> trainingClassUserMap = new HashMap<Integer, TrainingClassUserBean>();
		if (StringKit.isValid(checkStrexamResultApprove)) {
			String[] sourceStrArray = checkStrexamResultApprove.split(",");
			for (int i = 0; i < sourceStrArray.length; ++i) {
				TrainingClassUserBean trainingClassUserBean = trainingClassUserBusiness
						.getTrainingClassUserByKey(classId, Integer.parseInt(sourceStrArray[i]));
				if (trainingClassUserBean == null) {
					messageBean.setMessage("学员为空，请刷新重试");
					HttpResponseKit.printJson(request, response, messageBean, "");
					return;
				} else {
					trainingClassUserBean.setExamResult(Constants.STATUS_VALID);
					trainingClassUserMap.put(Integer.parseInt(sourceStrArray[i]), trainingClassUserBean);
				}

			}

		}
		if (StringKit.isValid(checkStrexamResultNotApprove)) {
			String[] sourceStrArray = checkStrexamResultNotApprove.split(",");
			for (int i = 0; i < sourceStrArray.length; ++i) {
				if (trainingClassUserMap.containsKey(Integer.parseInt(sourceStrArray[i]))) {
					messageBean.setMessage("不允许同一学员同时多选考核结果");
					HttpResponseKit.printJson(request, response, messageBean, "");
					return;
				} else {
					TrainingClassUserBean trainingClassUserBean = trainingClassUserBusiness
							.getTrainingClassUserByKey(classId, Integer.parseInt(sourceStrArray[i]));
					if (trainingClassUserBean == null) {
						messageBean.setMessage("学员为空，请刷新重试");
						HttpResponseKit.printJson(request, response, messageBean, "");
						return;
					} else {
						trainingClassUserBean.setExamResult(Constants.STATUS_NOT_VALID);
						trainingClassUserMap.put(Integer.parseInt(sourceStrArray[i]), trainingClassUserBean);
					}
				}
			}
		}
		HashMap<Integer, TrainingClassUserBean> trainingClassUserCertificationMap = new HashMap<Integer, TrainingClassUserBean>();
		if (StringKit.isValid(hasCertification)) {
			String[] sourceStrArray = hasCertification.split(",");
			for (int i = 0; i < sourceStrArray.length; ++i) {
				if (trainingClassUserMap.containsKey(Integer.parseInt(sourceStrArray[i]))) {
					TrainingClassUserBean trainingClassUserBean = trainingClassUserMap
							.get(Integer.parseInt(sourceStrArray[i]));
					trainingClassUserBean.setHasCertification(Constants.STATUS_VALID);
					trainingClassUserMap.put(Integer.parseInt(sourceStrArray[i]), trainingClassUserBean);
					trainingClassUserCertificationMap.put(Integer.parseInt(sourceStrArray[i]), trainingClassUserBean);
				} else {
					TrainingClassUserBean trainingClassUserBean = trainingClassUserBusiness
							.getTrainingClassUserByKey(classId, Integer.parseInt(sourceStrArray[i]));
					if (trainingClassUserBean == null) {
						messageBean.setMessage("学员为空，请刷新重试");
						HttpResponseKit.printJson(request, response, messageBean, "");
						return;
					} else {
						trainingClassUserBean.setHasCertification(Constants.STATUS_VALID);
						trainingClassUserCertificationMap.put(Integer.parseInt(sourceStrArray[i]),
								trainingClassUserBean);
						trainingClassUserMap.put(Integer.parseInt(sourceStrArray[i]), trainingClassUserBean);
					}
				}
			}
		}
		if (StringKit.isValid(hasNotCertification)) {
			String[] sourceStrArray = hasNotCertification.split(",");
			for (int i = 0; i < sourceStrArray.length; ++i) {
				if (trainingClassUserCertificationMap.containsKey(Integer.parseInt(sourceStrArray[i]))) {
					messageBean.setMessage("不允许同一学员同时多选是否获得培训证书");
					HttpResponseKit.printJson(request, response, messageBean, "");
					return;
				} else {
					if (trainingClassUserMap.containsKey(Integer.parseInt(sourceStrArray[i]))) {
						TrainingClassUserBean trainingClassUserBean = trainingClassUserMap
								.get(Integer.parseInt(sourceStrArray[i]));
						trainingClassUserBean.setHasCertification(Constants.STATUS_NOT_VALID);
						trainingClassUserMap.put(Integer.parseInt(sourceStrArray[i]), trainingClassUserBean);
					} else {
						TrainingClassUserBean trainingClassUserBean = trainingClassUserBusiness
								.getTrainingClassUserByKey(classId, Integer.parseInt(sourceStrArray[i]));
						if (trainingClassUserBean == null) {
							messageBean.setMessage("学员为空，请刷新重试");
							HttpResponseKit.printJson(request, response, messageBean, "");
							return;
						} else {
							trainingClassUserBean.setHasCertification(Constants.STATUS_NOT_VALID);
							trainingClassUserMap.put(Integer.parseInt(sourceStrArray[i]), trainingClassUserBean);
						}
					}
				}
			}
		}
		int result = 0;
		for (Integer key : trainingClassUserMap.keySet()) {
			result = trainingClassUserBusiness.updateTrainingClassUser(trainingClassUserMap.get(key));
			if (result < 0) {
				messageBean.setMessage("处理失败");
				HttpResponseKit.printJson(request, response, messageBean, "");
				return;
			}
		}
		if (result >= 0) {
			messageBean.setCode(Constants.STATUS_VALID);
			messageBean.setMessage("处理成功");
		} else {
			messageBean.setMessage("处理失败");
		}
		HttpResponseKit.printJson(request, response, messageBean, "");
		return;

	}

}
