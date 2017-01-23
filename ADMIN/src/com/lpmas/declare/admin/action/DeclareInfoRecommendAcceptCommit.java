package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareInfoBusiness;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.FarmerContactInfoBusiness;
import com.lpmas.declare.admin.business.FarmerIndustryInfoBusiness;
import com.lpmas.declare.admin.business.FarmerInfoBusiness;
import com.lpmas.declare.admin.business.FarmerJobInfoBusiness;
import com.lpmas.declare.admin.business.FarmerSkillInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.DeclareInfoRecommendConfig;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.handler.DeclareReportHandler;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.FarmerContactInfoBean;
import com.lpmas.declare.bean.FarmerIndustryInfoBean;
import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.bean.FarmerJobInfoBean;
import com.lpmas.declare.bean.FarmerSkillInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/admin/DeclareInfoRecommendAcceptCommit.do")
public class DeclareInfoRecommendAcceptCommit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DeclareInfoRecommendAcceptCommit.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoRecommendAcceptCommit() {
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
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		if (adminUserInfoBean == null) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		int declareId = ParamKit.getIntParameter(request, "declareId", 0);
		if (declareId <= 0) {
			HttpResponseKit.alertMessage(response, "申报ID非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 根据申报ID查询申报信息
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(declareId);
		if (declareInfoBean == null) {
			HttpResponseKit.alertMessage(response, "申报信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		int result = 0;
		int resultMongo = 0;
		String action = ParamKit.getParameter(request, "action", "");
		String modelType = ParamKit.getParameter(request, "modelType", "");
		if (action.equals(DeclareInfoRecommendConfig.COMMIT_ACTION_SUBMIT)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND, OperationConfig.APPLY)) {
				return;
			}
			// 检查当前状态是否已经被改变
			if (!declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_SUBMIT)
					&& !declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)
					&& !declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REJECT)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", "/declare/admin/DeclareInfoRecommendList.do");
				return;
			}
			if (adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)) {
				declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_APPROVE);
			} else {
				declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_SUBMIT);
			}
			declareInfoBean.setModifyUser(adminHelper.getAdminUserId());
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
			if (resultMongo <= 0 || result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/DeclareInfoRecommendList.do");
			}
		} else if (action.equals(DeclareInfoRecommendConfig.COMMIT_ACTION_REJECT)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_MANAGE, OperationConfig.REJECT)) {
				return;
			}
			// 检查当前状态是否已经被改变
			if (!declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)
					&& !declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					&& !declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 查询是否加入培训班
			DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
			try {
				DeclareReportBean declareReportBean = declareReportBusiness
						.getDeclareReportByKey(String.valueOf(declareInfoBean.getDeclareId()));
				if (declareReportBean == null) {
					HttpResponseKit.alertMessage(response, "申报信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				List<Integer> trainingClassInfoList = declareReportBean.getTrainingClassInfoList();
				if (trainingClassInfoList != null && !trainingClassInfoList.isEmpty()) {
					HttpResponseKit.alertMessage(response, "该学员已加入培训班，不允许驳回", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 查询是否已被认定
				if (declareInfoBean.getAuthStatus().equals(DeclareInfoConfig.AUTH_STATUS_APPROVE)) {
					HttpResponseKit.alertMessage(response, "该学员学员已被认定，不允许驳回", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_REJECT);
			declareInfoBean.setModifyUser(adminHelper.getAdminUserId());
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
			if (resultMongo <= 0 || result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/DeclareInfoReviewList.do?modelType="
						+ modelType + "&declareType=" + declareInfoBean.getDeclareType());
			}
		} else if (action.equals(DeclareInfoRecommendConfig.COMMIT_ACTION_DELETE)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_MANAGE, OperationConfig.REMOVE)) {
				return;
			}
			// 检查当前状态是否已经被改变
			if (!declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)
					&& !declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)
					&& !declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}

			DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
			try {
				DeclareReportBean declareReportBean = declareReportBusiness
						.getDeclareReportByKey(String.valueOf(declareInfoBean.getDeclareId()));
				if (declareReportBean == null) {
					HttpResponseKit.alertMessage(response, "申报信息不存在", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 查询是否加入培训班
				List<Integer> trainingClassInfoList = declareReportBean.getTrainingClassInfoList();
				if (trainingClassInfoList != null && !trainingClassInfoList.isEmpty()) {
					HttpResponseKit.alertMessage(response, "该学员已加入培训班，不允许删除", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 查询是否已被认定
				if (declareInfoBean.getAuthStatus().equals(DeclareInfoConfig.AUTH_STATUS_APPROVE)) {
					HttpResponseKit.alertMessage(response, "该学员学员已被认定，不允许删除", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			declareInfoBean.setStatus(Constants.STATUS_NOT_VALID);
			declareInfoBean.setModifyUser(adminHelper.getAdminUserId());
			result = declareInfoBusiness.updateDeclareInfo(declareInfoBean);
			FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
			FarmerContactInfoBusiness farmerContactInfoBusiness = new FarmerContactInfoBusiness();
			FarmerJobInfoBusiness farmerJobInfoBusiness = new FarmerJobInfoBusiness();
			FarmerSkillInfoBusiness farmerSkillInfoBusiness = new FarmerSkillInfoBusiness();
			FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();
			FarmerInfoBean farmerInfoBean = farmerInfoBusiness.getFarmerInfoByKey(declareId);
			FarmerContactInfoBean farmerContactInfoBean = farmerContactInfoBusiness
					.getFarmerContactInfoByKey(declareId);
			FarmerJobInfoBean farmerJobInfoBean = farmerJobInfoBusiness.getFarmerJobInfoByKey(declareId);
			FarmerSkillInfoBean farmerSkillInfoBean = farmerSkillInfoBusiness.getFarmerSkillInfoByKey(declareId);
			FarmerIndustryInfoBean farmerIndustryInfoBean = farmerIndustryInfoBusiness
					.getFarmerIndustryInfoByKey(declareId);
			farmerInfoBean.setStatus(Constants.STATUS_NOT_VALID);
			farmerContactInfoBean.setStatus(Constants.STATUS_NOT_VALID);
			if (farmerJobInfoBean != null) {
				farmerJobInfoBean.setStatus(Constants.STATUS_NOT_VALID);
				farmerJobInfoBusiness.updateFarmerJobInfo(farmerJobInfoBean);
			}
			if (farmerSkillInfoBean != null) {
				farmerSkillInfoBean.setStatus(Constants.STATUS_NOT_VALID);
				farmerSkillInfoBusiness.updateFarmerSkillInfo(farmerSkillInfoBean);
			}
			if (farmerIndustryInfoBean != null) {
				farmerIndustryInfoBean.setStatus(Constants.STATUS_NOT_VALID);
				farmerIndustryInfoBusiness.updateFarmerIndustryInfo(farmerIndustryInfoBean);
			}
			int resultFarmerInfo = farmerInfoBusiness.updateFarmerInfo(farmerInfoBean);
			int resultFarmerContactInfo = farmerContactInfoBusiness.updateFarmerContactInfo(farmerContactInfoBean);
			// 插入mongo
			DeclareReportHandler handler = new DeclareReportHandler();
			try {
				handler.createDeclareReport(declareInfoBean);
				resultMongo = Constants.STATUS_VALID;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("总表记录创建失败", e);
			}
			if (resultMongo <= 0 || result < 0 || resultFarmerInfo < 0 || resultFarmerContactInfo < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/DeclareInfoReviewList.do?modelType="
						+ modelType + "&declareType=" + declareInfoBean.getDeclareType());
			}
		} else if (action.equals(DeclareInfoRecommendConfig.COMMIT_ACTION_APPROVE)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_VERIFY, OperationConfig.REVIEW)) {
				return;
			}
			// 检查当前状态是否已经被改变
			if (!declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_APPROVE);
			declareInfoBean.setModifyUser(adminHelper.getAdminUserId());
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
			if (resultMongo <= 0 || result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/DeclareInfoReviewList.do?modelType="
						+ modelType + "&declareType=" + declareInfoBean.getDeclareType());
			}
		} else if (action.equals(DeclareInfoRecommendConfig.COMMIT_ACTION_REGION_APPROVE)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_VERIFY, OperationConfig.APPROVE)) {
				return;
			}

			// 检查当前状态是否已经被改变
			if (!declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			//现代青年农场主需要提交给省级审批，其他类型在县级审批后直接变成审批通过状态
			if(declareInfoBean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER){
				declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE);
			}else{
				declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_APPROVE);
			}
			
			declareInfoBean.setModifyUser(adminHelper.getAdminUserId());
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
			if (resultMongo <= 0 || result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/DeclareInfoReviewList.do?modelType="
						+ modelType + "&declareType=" + declareInfoBean.getDeclareType());
			}
		} else if (action.equals(DeclareInfoRecommendConfig.COMMIT_ACTION_NOT_APPROVE)) {
			if (!adminHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_VERIFY, OperationConfig.REVIEW)
					&& !adminHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_VERIFY, OperationConfig.APPROVE)) {
				HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 检查当前状态是否已经被改变
			if (!declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)
					&& !declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE);
			declareInfoBean.setModifyUser(adminHelper.getAdminUserId());
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
			if (resultMongo <= 0 || result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/DeclareInfoReviewList.do?modelType="
						+ modelType + "&declareType=" + declareInfoBean.getDeclareType());
			}
		} else if (action.equals(DeclareInfoRecommendConfig.COMMIT_ACTION_CHANGE)) {
			if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_CLASSIFY, OperationConfig.UPDATE)) {
				return;
			}
			// 检查当前状态是否已经被改变
			if (!declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)) {
				HttpResponseKit.alertMessage(response, "当前状态不允许进行此项操作", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			if (declareInfoBean.getDeclareCategory().equals(DeclareInfoConfig.DECLARE_CATEGORY_PRESENT)) {
				declareInfoBean.setDeclareCategory(DeclareInfoConfig.DECLARE_CATEGORY_SPARE);
			} else {
				declareInfoBean.setDeclareCategory(DeclareInfoConfig.DECLARE_CATEGORY_PRESENT);
			}
			declareInfoBean.setModifyUser(adminHelper.getAdminUserId());
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
			if (resultMongo <= 0 || result < 0) {
				HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				HttpResponseKit.alertMessage(response, "处理成功",
						"/declare/admin/DeclareInfoReviewList.do?modelType=" + modelType);
			}
		} else {
			HttpResponseKit.alertMessage(response, "提交操作非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

	}

}
