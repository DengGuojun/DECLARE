package com.lpmas.declare.survey.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.IndustryInfoBean;
import com.lpmas.declare.bean.IndustryTypeBean;
import com.lpmas.declare.bean.JobInfoBean;
import com.lpmas.declare.bean.JobTypeBean;
import com.lpmas.declare.survey.business.DeclareReportBusiness;
import com.lpmas.declare.survey.business.IndustryInfoBusiness;
import com.lpmas.declare.survey.business.IndustryTypeBusiness;
import com.lpmas.declare.survey.business.JobInfoBusiness;
import com.lpmas.declare.survey.business.JobTypeBusiness;
import com.lpmas.declare.survey.config.DeclareSurveyConfig;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.web.CookiesKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/DeclareReportManage.do")
public class DeclareReportManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareReportManage() {
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
		String loginKey = CookiesKit.getCookies(request, DeclareSurveyConfig.ADMIN_LOGIN_KEY);
		if (!DeclareSurveyConfig.ADMIN_LOGIN_ID.equals(loginKey)) {
			HttpResponseKit.alertMessage(response, "你没有该功能的操作权限！", "/declare/AdminLogon.do");
			return;
		}
		int declareId = ParamKit.getIntParameter(request, "declareId", 0);
		if (declareId == 0) {
			HttpResponseKit.alertMessage(response, "申报ID缺失!", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		request.setAttribute("declareId", declareId);
		// 查出对应的BEAN
		DeclareReportBusiness business = new DeclareReportBusiness();
		DeclareReportBean declareReportBean;
		try {
			declareReportBean = business.getDeclareReportByKey(String.valueOf(declareId));

			// 查工种类型和岗位
			if (declareReportBean.getJobType() != 0) {
				JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
				JobTypeBean jobTypeBean = jobTypeBusiness.getJobTypeByKey(declareReportBean.getJobType());
				if (jobTypeBean != null) {
					String jobType = jobTypeBean.getTypeName();
					request.setAttribute("JobType", jobType);
				}
			}
			if (declareReportBean.getJobName() != 0) {
				JobInfoBusiness jobInfoBusiness = new JobInfoBusiness();
				JobInfoBean jobInfoBean = jobInfoBusiness.getJobInfoByKey(declareReportBean.getJobName());
				if (jobInfoBean != null) {
					String jobName = jobInfoBean.getJobName();
					request.setAttribute("JobName", jobName);
				}
			}
			if (declareReportBean.getIndustryJobType() != 0) {
				JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
				JobTypeBean jobTypeBean = jobTypeBusiness.getJobTypeByKey(declareReportBean.getIndustryJobType());
				if (jobTypeBean != null) {
					String jobType = jobTypeBean.getTypeName();
					request.setAttribute("IndustryJobType", jobType);
				}
			}
			if (declareReportBean.getIndustryJobId() != 0) {
				JobInfoBusiness jobInfoBusiness = new JobInfoBusiness();
				JobInfoBean jobInfoBean = jobInfoBusiness.getJobInfoByKey(declareReportBean.getIndustryJobId());
				if (jobInfoBean != null) {
					String jobName = jobInfoBean.getJobName();
					request.setAttribute("IndustryJobName", jobName);
				}
			}
			// 查产业类型和产业
			if (declareReportBean.getIndustryTypeId1() != 0) {
				IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
				List<IndustryTypeBean> industryTypeList = industryTypeBusiness.getIndustryTypeAllList();
				Map<Integer, IndustryTypeBean> industryTypeMap = new HashMap<Integer, IndustryTypeBean>();
				if (!industryTypeList.isEmpty()) {
					industryTypeMap = ListKit.list2Map(industryTypeList, "typeId");
				}
				request.setAttribute("industryTypeMap", industryTypeMap);
			}
			if (declareReportBean.getIndustryId1() != 0) {
				IndustryInfoBusiness industryInfoBusiness = new IndustryInfoBusiness();
				List<IndustryInfoBean> industryInfoList = industryInfoBusiness.getIndustryInfoAllList();
				Map<Integer, IndustryInfoBean> industryInfoMap = new HashMap<Integer, IndustryInfoBean>();
				if (!industryInfoList.isEmpty()) {
					industryInfoMap = ListKit.list2Map(industryInfoList, "industryId");
				}
				request.setAttribute("industryInfoMap", industryInfoMap);
			}

			// 放到页面
			request.setAttribute("DeclareReportBean", declareReportBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 请求转发
		PortalKit.forwardPage(request, response, DeclareSurveyConfig.PAGE_PATH + "DeclareReportManage.jsp");
	}

}
