package com.lpmas.declare.survey.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.lpmas.declare.bean.FarmerIndustryInfoBean;
import com.lpmas.declare.bean.IndustryInfoBean;
import com.lpmas.declare.bean.IndustryTypeBean;
import com.lpmas.declare.bean.JobInfoBean;
import com.lpmas.declare.bean.JobTypeBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.survey.business.DeclareInfoBusiness;
import com.lpmas.declare.survey.business.FarmerIndustryInfoBusiness;
import com.lpmas.declare.survey.business.IndustryInfoBusiness;
import com.lpmas.declare.survey.business.IndustryTypeBusiness;
import com.lpmas.declare.survey.business.JobInfoBusiness;
import com.lpmas.declare.survey.business.JobTypeBusiness;
import com.lpmas.declare.survey.config.DeclareSurveyConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.CookiesKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class FarmerIndustryInfoManage
 */
@WebServlet("/declare/FarmerIndustryInfoManage.do")
public class FarmerIndustryInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(FarmerIndustryInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FarmerIndustryInfoManage() {
		super();
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
		// 判断是否对应的申报类型
		if (declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER
				|| declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {
			HttpResponseKit.alertMessage(response, "该申报类型不包含此内容", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 获取declareId
		String declareId = CookiesKit.getCookies(request, DeclareSurveyConfig.DECALRE_ID);

		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = null;
		if (StringKit.isVerified(declareId)) {
			declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(Integer.valueOf(declareId));
		}
		FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();
		JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();

		if (declareInfoBean != null) {
			// 检查是否完成了填写
			if ((declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT))
					&& declareInfoBean.getDeclareType() != declareType) {
				// 仅有的一个已经是等候审批状态
				HttpResponseKit.alertMessage(response, "申报已经提交,请勿重复填写", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			FarmerIndustryInfoBean farmerIndustryInfoBean = farmerIndustryInfoBusiness
					.getFarmerIndustryInfoByKey(declareInfoBean.getDeclareId());
			if (farmerIndustryInfoBean == null) {
				// 新建
				farmerIndustryInfoBean = new FarmerIndustryInfoBean();
				farmerIndustryInfoBean.setStatus(Constants.STATUS_VALID);
			} else {
				// 如果存在，则获取产业名称1
				IndustryInfoBusiness industryInfoBusiness = new IndustryInfoBusiness();
				if (farmerIndustryInfoBean.getIndustryId1() != 0) {
					IndustryInfoBean industryInfoBean = industryInfoBusiness
							.getIndustryInfoByKey(farmerIndustryInfoBean.getIndustryId1());
					request.setAttribute("OriginalIndustryName", industryInfoBean.getIndustryName());
				}
			}
			request.setAttribute("FarmerIndustryInfoBean", farmerIndustryInfoBean);
		} else {
			// 新建
			FarmerIndustryInfoBean farmerIndustryInfoBean = new FarmerIndustryInfoBean();
			farmerIndustryInfoBean.setStatus(Constants.STATUS_VALID);
			request.setAttribute("FarmerIndustryInfoBean", farmerIndustryInfoBean);
		}

		// 获取产业类型列表
		IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
		List<IndustryTypeBean> industryTypeList = industryTypeBusiness.getIndustryTypeAllList();
		request.setAttribute("IndustryTypeList", industryTypeList);

		// 获取从业工种
		List<JobTypeBean> jobTypeList = jobTypeBusiness.getJobTypeAllList();
		request.setAttribute("JobTypeList", jobTypeList);
		// 获取所有岗位
		Map<Integer, List<JobInfoBean>> jobInfoMap = new HashMap<Integer, List<JobInfoBean>>();
		List<JobInfoBean> jobInfoList = new ArrayList<JobInfoBean>();
		JobInfoBusiness business = new JobInfoBusiness();
		for (JobTypeBean jobTypeBean : jobTypeList) {
			List<JobInfoBean> list = business.getJobInfoListByTypeId(jobTypeBean.getTypeId());
			jobInfoMap.put(jobTypeBean.getTypeId(), list);
			jobInfoList = ListKit.combineList(jobInfoList, list);
		}
		request.setAttribute("JobInfoMap", jobInfoMap);
		request.setAttribute("JobIdMap", ListKit.list2Map(jobInfoList, "jobId", "jobName"));

		String pagePath = DeclareSurveyConfig.PAGE_PATH + "FarmerIndustryInfoManage.jsp";
		PortalKit.forwardPage(request, response, pagePath);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReturnMessageBean messageBean = new ReturnMessageBean();
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
		// 判断是否对应的申报类型
		if (declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER
				|| declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {
			messageBean.setMessage("该申报类型不包含此内容");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}
		FarmerIndustryInfoBean bean = new FarmerIndustryInfoBean();
		try {
			bean = BeanKit.request2Bean(request, FarmerIndustryInfoBean.class);
			FarmerIndustryInfoBusiness business = new FarmerIndustryInfoBusiness();
			DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
			messageBean = business.verifyFarmerIndustryInfo(bean, declareType);
			if (StringKit.isValid(messageBean.getMessage())) {
				HttpResponseKit.printJson(request, response, messageBean, "");
				return;
			}

			int result = 0;
			// 获取declareId
			String declareIdKey = CookiesKit.getCookies(request, DeclareSurveyConfig.DECALRE_ID);
			DeclareInfoBean originalBean = null;
			if (StringKit.isVerified(declareIdKey)) {
				originalBean = declareInfoBusiness.getDeclareInfoByKey(Integer.valueOf(declareIdKey));
			}
			if (bean.getDeclareId() > 0 && originalBean != null) {
				// 判断修改的表单数据是否属于用户自己拥有的申报表单数据
				if (originalBean.getDeclareId() != bean.getDeclareId()) {
					messageBean.setMessage("申报ID不合法");
					HttpResponseKit.printJson(request, response, messageBean, "");
					return;
				}
				// 检验是否已经是待审批状态
				if (DeclareInfoConfig.DECLARE_STATUS_SUBMIT.equals(originalBean.getDeclareStatus())) {
					messageBean.setMessage("已经是审核状态，不能修改");
					HttpResponseKit.printJson(request, response, messageBean, "");
					return;
				}
				bean.setModifyUser(Constants.STATUS_NOT_VALID);
				result = business.updateFarmerIndustryInfo(bean);
			} else {
				int declareId = 0;
				if (originalBean == null) {
					// 新建申报对象
					DeclareInfoBean declareInfoBean = new DeclareInfoBean();
					declareInfoBean.setUserId(Constants.STATUS_NOT_VALID);
					declareInfoBean.setDeclareType(declareType);
					declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_NOT_SUBMIT);
					declareInfoBean.setStatus(Constants.STATUS_VALID);
					declareInfoBean.setCreateUser(Constants.STATUS_NOT_VALID);
					declareId = declareInfoBusiness.addDeclareInfo(declareInfoBean);
					if(declareId >0){
						// 记录Cookies
						CookiesKit.setCookie(response, DeclareSurveyConfig.DECALRE_ID, String.valueOf(declareId),
								DeclareSurveyConfig.ADMIN_DOMAIN);
					}
				} else {
					declareId = originalBean.getDeclareId();
				}
				// 新建产业信息
				if (declareId > 0) {
					bean.setDeclareId(declareId);
					bean.setCreateUser(Constants.STATUS_NOT_VALID);
					result = business.addFarmerIndustryInfo(bean);
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
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
