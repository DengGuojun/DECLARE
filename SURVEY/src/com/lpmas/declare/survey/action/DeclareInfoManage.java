package com.lpmas.declare.survey.action;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.business.DeclareInfoHelper;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.survey.business.DeclareInfoBusiness;
import com.lpmas.declare.survey.config.DeclareSurveyConfig;
import com.lpmas.declare.survey.handler.DeclareReportHandler;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.CookiesKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class DeclareProtalIndex
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
		// 获取declareId
		String declareId = CookiesKit.getCookies(request, DeclareSurveyConfig.DECALRE_ID);

		Map<String, Boolean> moduleFinishedMap = new LinkedHashMap<String, Boolean>();
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = null;
		if (StringKit.isVerified(declareId)) {
			declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(Integer.valueOf(declareId));
		}

		if (declareInfoBean != null) {
			// 检查是否完成了填写
			if ((declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT))
					&& declareInfoBean.getDeclareType() != declareType) {
				// 仅有的一个已经是等候审批状态
				HttpResponseKit.alertMessage(response, "您已提交其他审核，请勿重复提交", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 获取那些表示已经填写的
			moduleFinishedMap = declareInfoBusiness.getModuleFinishedMapByCondition(Integer.valueOf(declareId),
					declareType);
		} else {
			declareInfoBean = new DeclareInfoBean();
			declareInfoBean.setDeclareType(declareType);
			declareInfoBean.setUserId(Constants.STATUS_NOT_VALID);

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
		PortalKit.forwardPage(request, response, DeclareSurveyConfig.PAGE_PATH + "DeclareInfoManage.jsp");
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

		// 获取declareId
		String DeclareId = CookiesKit.getCookies(request, DeclareSurveyConfig.DECALRE_ID);

		// 获得申报信息
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = null;
		if (StringKit.isVerified(DeclareId)) {
			declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(Integer.valueOf(DeclareId));
		}
		if (declareInfoBean == null) {
			messageBean.setMessage("申报ID非法");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		} else {
			// 检查是否完成了填写
			if (declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT)) {
				// 仅有的一个已经是等候审批状态
				messageBean.setMessage("申报已经提交,请勿重复提交");
				HttpResponseKit.printJson(request, response, messageBean, "");
				return;
			}
		}

		// 检验模块填写完成情况
		Map<String, Boolean> moduleFinishedMap = declareInfoBusiness
				.getModuleFinishedMapByCondition(Integer.valueOf(DeclareId), declareType);
		String verifyResult = declareInfoBusiness.verifyModule(moduleFinishedMap);
		if (StringKit.isValid(verifyResult)) {
			messageBean.setMessage(verifyResult);
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

		// 完成检查，UPDATE状态
		int result = 0;
		declareInfoBean.setModifyUser(Constants.STATUS_NOT_VALID);
		declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_SUBMIT);
		declareInfoBean.setAuthStatus(DeclareInfoConfig.AUTH_STATUS_WAIT_APPROVE);
		declareInfoBean.setDeclareCategory(DeclareInfoConfig.DECLARE_CATEGORY_SPARE);
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
			messageBean.setMessage("操作成功");
		} else {
			messageBean.setMessage("操作失败");
		}
		HttpResponseKit.printJson(request, response, messageBean, "");
		return;
	}

}
