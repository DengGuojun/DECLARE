package com.lpmas.declare.portal.action;

import java.io.IOException;
import java.util.Date;
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
import com.lpmas.declare.bean.FarmerSkillInfoBean;
import com.lpmas.declare.bean.NationalCertificationBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.portal.business.DeclareInfoBusiness;
import com.lpmas.declare.portal.business.FarmerSkillInfoBusiness;
import com.lpmas.declare.portal.business.NationalCertificationBusiness;
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
 * Servlet implementation class FarmerSkillInfoManage
 */
@WebServlet("/declare/FarmerSkillInfoManage.do")
public class FarmerSkillInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(FarmerSkillInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FarmerSkillInfoManage() {
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
		// 获取用户Id
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();

		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId, declareType,
				DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));
		FarmerSkillInfoBusiness farmerSkillInfoBusiness = new FarmerSkillInfoBusiness();
		if (declareInfoBean == null) {
			HttpResponseKit.alertMessage(response, "申报信息错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		FarmerSkillInfoBean skillInfoBean = farmerSkillInfoBusiness.getFarmerSkillInfoByKey(declareInfoBean
				.getDeclareId());
		request.setAttribute("FarmerSkillInfoBean", skillInfoBean);

		// 获取国家资格证书数据
		NationalCertificationBusiness nationalCertificationBusiness = new NationalCertificationBusiness();
		List<NationalCertificationBean> ncList = nationalCertificationBusiness.getNationalCertificationAllList();
		request.setAttribute("NationalCertificationList", ncList);
		Map<Integer, String> nationalCertificationMap = ListKit.list2Map(ncList, "certificateId", "certificateName");
		request.setAttribute("NationalCertificationMap", nationalCertificationMap);

		PortalKit.forwardPage(request, response, DeclarePortalConfig.PAGE_PATH + "FarmerSkillInfoManage.jsp");
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
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();
		FarmerSkillInfoBean bean = new FarmerSkillInfoBean();
		try {

			bean = BeanKit.request2Bean(request, FarmerSkillInfoBean.class);
			// 如果不是新型职业农民资格证，就去掉对应字段的值
			if (Constants.STATUS_VALID != bean.getHasNewTypeCertification()) {
				bean.setCertificationDate(null);
				bean.setCertificationDepartment("");
				bean.setCertificationGrade("");
			}
			// 如果没有认证，把国家认证和培训置0
			if (Constants.STATUS_VALID == bean.getHasNoCertification()) {
				bean.setHasNationalCertification(Constants.STATUS_NOT_VALID);
				bean.setHasNewTypeTrainingCertification(Constants.STATUS_NOT_VALID);
			}
			// 如果没有其他培训，其他培训要变为0
			if (Constants.STATUS_VALID != bean.getIsTrained()) {
				bean.setOtherTrainingTime(Constants.STATUS_NOT_VALID);
			}

			FarmerSkillInfoBusiness business = new FarmerSkillInfoBusiness();
			DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
			messageBean = business.verifyFarmerSkillInfo(bean);
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
			result = business.updateFarmerSkillInfo(bean);

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
