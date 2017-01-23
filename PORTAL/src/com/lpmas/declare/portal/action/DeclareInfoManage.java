package com.lpmas.declare.portal.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.FarmerContactInfoBean;
import com.lpmas.declare.bean.FarmerIndustryInfoBean;
import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.bean.FarmerJobInfoBean;
import com.lpmas.declare.bean.FarmerSkillInfoBean;
import com.lpmas.declare.business.DeclareInfoHelper;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.portal.business.DeclareInfoBusiness;
import com.lpmas.declare.portal.business.DeclareReportBusiness;
import com.lpmas.declare.portal.business.FarmerContactInfoBusiness;
import com.lpmas.declare.portal.business.FarmerIndustryInfoBusiness;
import com.lpmas.declare.portal.business.FarmerInfoBusiness;
import com.lpmas.declare.portal.business.FarmerJobInfoBusiness;
import com.lpmas.declare.portal.business.FarmerSkillInfoBusiness;
import com.lpmas.declare.portal.config.DeclarePortalConfig;
import com.lpmas.declare.portal.handler.DeclareReportHandler;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.ow.passport.sso.business.SsoClientHelper;

/**
 * Servlet implementation class DeclareInfoManage
 */
@WebServlet("/declare/DeclareInfoManage.do")
public class DeclareInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DeclareInfoManage.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int declareType = ParamKit.getIntParameter(request, "declareType", 0);
		if (declareType <= 0) {
			HttpResponseKit.alertMessage(response, "申报类型必须填写", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		if (!DeclareInfoConfig.DECLARE_TYPE_MAP.containsKey(declareType)) {
			HttpResponseKit.alertMessage(response, "申报类型非法", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 获取用户Id
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();

		HashMap<String,String> condMap = new HashMap<String,String>();
		condMap.put("userId", String.valueOf(userId));
		condMap.put("declareYear", DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));
		DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
		try {
			List<DeclareReportBean> list = declareReportBusiness.getDeclareReportListByMap(condMap);
			if(!list.isEmpty()){
				DeclareReportBean declareReportBean = list.get(0);
				if(declareReportBean.getDeclareType() != declareType){
					HttpResponseKit.alertMessage(response, "你已申报其他类型，不能重复申报", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			}
		} catch (Exception exception) {
			log.error("查询错误", exception );
			HttpResponseKit.alertMessage(response, "数据错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		Map<String, Boolean> moduleFinishedMap = new LinkedHashMap<String, Boolean>();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId, declareType, DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));
		int declareId = 0;
		if (declareInfoBean != null) {
			// 获取那些表示已经填写的
			declareId = declareInfoBean.getDeclareId();
			moduleFinishedMap = declareInfoBusiness.getModuleFinishedMapByCondition(declareId, declareType);
		} else {
			DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
			declareInfoBean = new DeclareInfoBean();
			declareInfoBean.setDeclareType(declareType);
			declareInfoBean.setRegistryType(DeclareInfoConfig.REGISTRY_TYPE_FARMER);
			declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_NOT_SUBMIT);
			declareInfoBean.setUserId(userId);
			declareInfoBean.setStatus(Constants.STATUS_VALID);
			declareInfoBean.setCreateUser(userId);
			declareInfoBean.setDeclareYear(String.valueOf(declareInfoHelper.getDeclareYear()));
			declareId = declareInfoBusiness.addDeclareInfo(declareInfoBean);
			// 新建农民信息
			FarmerInfoBean farmerInfoBean = new FarmerInfoBean();
			FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
			farmerInfoBean.setCreateUser(userId);
			farmerInfoBean.setStatus(Constants.STATUS_VALID);
			farmerInfoBean.setDeclareId(declareId);
			farmerInfoBusiness.addFarmerInfo(farmerInfoBean);
			// 新建联系信息
			FarmerContactInfoBean farmerContactInfoBean = new FarmerContactInfoBean();
			FarmerContactInfoBusiness farmerContactInfoBusiness = new FarmerContactInfoBusiness();
			farmerContactInfoBean.setUserMobile(declareInfoBusiness.getUserMobileByUserClient(userId));
			farmerContactInfoBean.setCreateUser(userId);
			farmerContactInfoBean.setStatus(Constants.STATUS_VALID);
			farmerContactInfoBean.setDeclareId(declareId);
			farmerContactInfoBusiness.addFarmerContactInfo(farmerContactInfoBean);
			// 新建产业信息
			FarmerIndustryInfoBean farmerIndustryInfoBean = new FarmerIndustryInfoBean();
			FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();
			farmerIndustryInfoBean.setCreateUser(userId);
			farmerIndustryInfoBean.setStatus(Constants.STATUS_VALID);
			farmerIndustryInfoBean.setDeclareId(declareId);
			farmerIndustryInfoBusiness.addFarmerIndustryInfo(farmerIndustryInfoBean);
			// 新建工作表
			FarmerJobInfoBean farmerJobInfoBean = new FarmerJobInfoBean();
			FarmerJobInfoBusiness farmerJobInfoBusiness = new FarmerJobInfoBusiness();
			farmerJobInfoBean.setCreateUser(userId);
			farmerJobInfoBean.setStatus(Constants.STATUS_VALID);
			farmerJobInfoBean.setDeclareId(declareId);
			farmerJobInfoBusiness.addFarmerJobInfo(farmerJobInfoBean);
			// 新建技能表
			FarmerSkillInfoBean farmerSkillInfoBean = new FarmerSkillInfoBean();
			farmerSkillInfoBean.setCreateUser(userId);
			farmerSkillInfoBean.setStatus(Constants.STATUS_VALID);
			farmerSkillInfoBean.setDeclareId(declareId);
			FarmerSkillInfoBusiness farmerSkillInfoBusiness = new FarmerSkillInfoBusiness();
			farmerSkillInfoBusiness.addFarmerSkillInfo(farmerSkillInfoBean);
			// 插入mongo
			DeclareReportHandler handler = new DeclareReportHandler();
			try {
				declareInfoBean.setDeclareId(declareId);
				handler.createDeclareReport(declareInfoBean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("总表记录创建失败", e);
			}
			moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_INFO, false);
			moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_CONTACT_INFO, false);
			moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_SKILL_INFO, false);
			// 是专业的就要农务工作信息，其他要填农务经营情况
			if (declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER
					|| declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {
				// 要填农务工作信息
				moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_JOB_INFO, false);
			} else {
				// 要填农务经营情况
				moduleFinishedMap.put(DeclareInfoConfig.MODULE_FARMER_INDUSTRY_INFO, false);
			}
		}
		// 设值
		request.setAttribute("DeclareInfoBean", declareInfoBean);
		request.setAttribute("ModuleFinishedMap", moduleFinishedMap);
		// 转发
		PortalKit.forwardPage(request, response, DeclarePortalConfig.PAGE_PATH + "DeclareInfoManage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnMessageBean messageBean = new ReturnMessageBean();

		// 获取申报类型
		int declareType = ParamKit.getIntParameter(request, "declareType", 0);
		if (declareType <= 0) {
			messageBean.setMessage("申报类型非法");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}
		if (!DeclareInfoConfig.DECLARE_TYPE_MAP.containsKey(declareType)) {
			messageBean.setMessage("申报类型非法");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

		int declareId = ParamKit.getIntParameter(request, "declareId", 0);
		if (declareId <= 0) {
			messageBean.setMessage("请先填写资料再提交审核!");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

		// 获取用户ID
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();

		// 获得申报信息
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId, declareType, DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));
		if (declareInfoBean == null || declareInfoBean.getDeclareId() != declareId) {
			messageBean.setMessage("申报ID非法");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

		// 检验模块填写完成情况
		Map<String, Boolean> moduleFinishedMap = declareInfoBusiness.getModuleFinishedMapByCondition(declareId,
				declareType);
		String verifyResult = declareInfoBusiness.verifyModule(moduleFinishedMap);
		if (StringKit.isValid(verifyResult)) {
			messageBean.setMessage(verifyResult);
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

		// 完成检查，UPDATE状态
		int result = 0;
		declareInfoBean.setModifyUser(userId);
		declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_SUBMIT);
		declareInfoBean.setAuthStatus(DeclareInfoConfig.AUTH_STATUS_WAIT_APPROVE);
		declareInfoBean.setDeclareCategory(DeclareInfoConfig.DECLARE_CATEGORY_SPARE);
		declareInfoBean.setRegistryType(DeclareInfoConfig.REGISTRY_TYPE_FARMER);
		DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
		declareInfoBean.setDeclareYear(String.valueOf(declareInfoHelper.getDeclareYear()));

		DeclareReportHandler handler = new DeclareReportHandler();
		try {
			handler.createDeclareReport(declareInfoBean);
			result = Constants.STATUS_VALID;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("审核操作失败，总表记录创建失败", e);
		}
		if (result > 0) {
			messageBean.setCode(Constants.STATUS_VALID);
			messageBean.setMessage("操作成功，请等待管理员审核");
		} else {
			messageBean.setMessage("操作失败");
		}
		HttpResponseKit.printJson(request, response, messageBean, "");
		return;
	}

}
