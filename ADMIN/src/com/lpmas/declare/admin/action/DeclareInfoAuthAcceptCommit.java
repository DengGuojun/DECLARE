package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.TrainingOrganizationInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareInfoBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.admin.business.TrainingOrganizationInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.handler.DeclareReportHandler;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/DeclareInfoAuthAcceptCommit.do")
public class DeclareInfoAuthAcceptCommit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DeclareInfoAuthAcceptCommit.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoAuthAcceptCommit() {
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
		if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_AUTH, OperationConfig.APPROVE)) {
			return;
		}
		int classId = ParamKit.getIntParameter(request, "classId", -1);
		if (classId <= -1) {
			HttpResponseKit.alertMessage(response, "班级ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		String action = ParamKit.getParameter(request, "action", "");
		int result = 0;
		long resultMongo = 0;
		if (action.equals(TrainingClassUserConfig.COMMIT_ACTION_AUTH_APPROVE)) {
			int organizationId = ParamKit.getIntParameter(request, "organizationId", 0);
			if (organizationId <= 0) {
				HttpResponseKit.alertMessage(response, "认定机构ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 根据机构ID查询机构信息
			TrainingOrganizationInfoBusiness trainingOrganizationInfoBusiness = new TrainingOrganizationInfoBusiness();
			TrainingOrganizationInfoBean trainingOrganizationInfoBean = trainingOrganizationInfoBusiness
					.getTrainingOrganizationInfoByKey(organizationId);
			if (trainingOrganizationInfoBean == null) {
				HttpResponseKit.alertMessage(response, "机构信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			String checkDeclareList = ParamKit.getParameter(request, "checkDeclareList", "").trim();
			DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
			if (StringKit.isValid(checkDeclareList)) {
				String[] sourceStrArray = checkDeclareList.split(",");
				for (int i = 0; i < sourceStrArray.length; ++i) {
					DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(Integer
							.parseInt(sourceStrArray[i]));
					if (declareInfoBean == null) {
						HttpResponseKit.alertMessage(response, "学员为空，请刷新重试", HttpResponseKit.ACTION_HISTORY_BACK);
						return;
					} else {
						if (declareInfoBean.getAuthStatus().equals(DeclareInfoConfig.AUTH_STATUS_APPROVE)) {
							HttpResponseKit.alertMessage(response, "存在学员已被认定，请刷新重试",
									HttpResponseKit.ACTION_HISTORY_BACK);
							return;
						}
						declareInfoBean.setAuthOrganizationId(organizationId);
						declareInfoBean.setAuthStatus(DeclareInfoConfig.AUTH_STATUS_APPROVE);
						result = declareInfoBusiness.updateDeclareInfo(declareInfoBean);
						if (result < 0) {
							HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
							return;
						}
						// 如果是培训班内的学员认定，则更新培训班的相关字段
						if (classId > 0) {
							TrainingClassInfoBusiness trainingClassInfoBusiness = new TrainingClassInfoBusiness();
							TrainingClassInfoBean classInfoBean = trainingClassInfoBusiness
									.getTrainingClassInfoByKey(classId);
							if (classInfoBean.getAuthTime() == null) {
								classInfoBean.setAuthOrganizationId(organizationId);
								classInfoBean.setAuthStatus(DeclareInfoConfig.AUTH_STATUS_APPROVE);
								classInfoBean.setAuthTime(DateKit.getCurrentTimestamp());
								trainingClassInfoBusiness.updateTrainingClassInfo(classInfoBean);
							}
						}

						// 插入mongo
						DeclareReportHandler handler = new DeclareReportHandler();
						try {
							handler.createDeclareReport(declareInfoBean);
							resultMongo = Constants.STATUS_VALID;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							log.error("总表记录创建失败", e);
						}
						if (resultMongo <= 0) {
							HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
							return;
						}
					}
				}
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/DeclareInfoAuthManage.do?classId="
						+ classId);
			}

		} else if (action.equals(TrainingClassUserConfig.COMMIT_ACTION_AUTH_WAIT_APPROVE)) {
			int declareId = ParamKit.getIntParameter(request, "declareId", 0);
			if (declareId <= 0) {
				HttpResponseKit.alertMessage(response, "学员ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
			DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(declareId);
			if (declareInfoBean == null) {
				HttpResponseKit.alertMessage(response, "学员为空，请刷新重试", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			declareInfoBean.setAuthOrganizationId(Constants.STATUS_NOT_VALID);
			declareInfoBean.setAuthStatus(DeclareInfoConfig.AUTH_STATUS_WAIT_APPROVE);
			result = declareInfoBusiness.updateDeclareInfo(declareInfoBean);
			// 插入mongo
			DeclareReportHandler handler = new DeclareReportHandler();
			try {
				handler.createDeclareReport(declareInfoBean);
				resultMongo = Constants.STATUS_VALID;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("总表记录创建失败", e);
			}
			if (result < 0 || resultMongo <= 0) {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/DeclareInfoAuthManage.do?classId="
						+ classId);
			}

		} else {
			HttpResponseKit.alertMessage(response, "提交操作非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

	}

}
