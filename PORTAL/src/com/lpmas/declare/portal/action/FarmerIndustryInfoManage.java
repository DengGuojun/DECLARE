package com.lpmas.declare.portal.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.lpmas.declare.portal.business.DeclareInfoBusiness;
import com.lpmas.declare.portal.business.FarmerIndustryInfoBusiness;
import com.lpmas.declare.portal.business.IndustryInfoBusiness;
import com.lpmas.declare.portal.business.IndustryTypeBusiness;
import com.lpmas.declare.portal.business.JobInfoBusiness;
import com.lpmas.declare.portal.business.JobTypeBusiness;
import com.lpmas.declare.portal.config.DeclarePortalConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.ow.passport.sso.business.SsoClientHelper;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		// 获取用户Id
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();

		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId, declareType,
				DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));
		FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();
		JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
		if (declareInfoBean == null) {
			HttpResponseKit.alertMessage(response, "申报信息错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		FarmerIndustryInfoBean farmerIndustryInfoBean = farmerIndustryInfoBusiness
				.getFarmerIndustryInfoByKey(declareInfoBean.getDeclareId());
		IndustryInfoBusiness industryInfoBusiness = new IndustryInfoBusiness();
		if (farmerIndustryInfoBean.getIndustryId1() != 0) {
			IndustryInfoBean industryInfoBean = industryInfoBusiness.getIndustryInfoByKey(farmerIndustryInfoBean
					.getIndustryId1());
			request.setAttribute("OriginalIndustryName", industryInfoBean.getIndustryName());
		}
		request.setAttribute("FarmerIndustryInfoBean", farmerIndustryInfoBean);

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

		String pagePath = DeclarePortalConfig.PAGE_PATH + "FarmerIndustryInfoManage.jsp";
		PortalKit.forwardPage(request, response, pagePath);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
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
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();
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
			DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId, declareType,
					DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));
			// 检验是否已经是待审批状态
			if (DeclareInfoConfig.DECLARE_STATUS_SUBMIT.equals(declareInfoBean.getDeclareStatus())) {
				messageBean.setMessage("已经是审核状态，不能修改");
				HttpResponseKit.printJson(request, response, messageBean, "");
				return;
			}
			bean.setModifyUser(userId);
			result = business.updateFarmerIndustryInfo(bean);

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
