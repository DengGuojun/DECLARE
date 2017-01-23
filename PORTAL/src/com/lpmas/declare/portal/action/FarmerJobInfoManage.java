package com.lpmas.declare.portal.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.FarmerJobInfoBean;
import com.lpmas.declare.bean.JobTypeBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.portal.business.DeclareInfoBusiness;
import com.lpmas.declare.portal.business.FarmerJobInfoBusiness;
import com.lpmas.declare.portal.business.JobTypeBusiness;
import com.lpmas.declare.portal.config.DeclarePortalConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.ow.passport.sso.business.SsoClientHelper;

/**
 * Servlet implementation class FarmerJobInfoManage
 */
@WebServlet("/declare/FarmerJobInfoManage.do")
public class FarmerJobInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(FarmerJobInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FarmerJobInfoManage() {
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
		if (!(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER)) {
			HttpResponseKit.alertMessage(response, "该申报类型不包含此内容", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		// 获取用户Id
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();

		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId, declareType,
				DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));
		FarmerJobInfoBusiness farmerJobInfoBusiness = new FarmerJobInfoBusiness();
		if (declareInfoBean == null) {
			HttpResponseKit.alertMessage(response, "申报信息错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		FarmerJobInfoBean jobInfoBean = farmerJobInfoBusiness.getFarmerJobInfoByKey(declareInfoBean.getDeclareId());
		request.setAttribute("FarmerJobInfoBean", jobInfoBean);

		// 获取从业工种
		JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
		List<JobTypeBean> jobTypeList = jobTypeBusiness.getJobTypeAllList();
		request.setAttribute("JobTypeList", jobTypeList);

		PortalKit.forwardPage(request, response, DeclarePortalConfig.PAGE_PATH + "FarmerJobInfoManage.jsp");
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
		if (!(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER)) {
			messageBean.setMessage("该申报类型不包含此内容");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();
		FarmerJobInfoBean bean = new FarmerJobInfoBean();
		try {
			bean = BeanKit.request2Bean(request, FarmerJobInfoBean.class);
			FarmerJobInfoBusiness business = new FarmerJobInfoBusiness();
			DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
			messageBean = business.verifyFarmerJobInfo(bean);
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
			result = business.updateFarmerJobInfo(bean);

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
