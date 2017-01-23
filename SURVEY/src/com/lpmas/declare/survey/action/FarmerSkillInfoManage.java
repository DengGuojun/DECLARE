package com.lpmas.declare.survey.action;

import java.io.IOException;
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
import com.lpmas.declare.survey.business.DeclareInfoBusiness;
import com.lpmas.declare.survey.business.FarmerSkillInfoBusiness;
import com.lpmas.declare.survey.business.NationalCertificationBusiness;
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
		// 获取declareId
		String declareId = CookiesKit.getCookies(request, DeclareSurveyConfig.DECALRE_ID);

		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = null;
		if (StringKit.isVerified(declareId)) {
			declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(Integer.valueOf(declareId));
		}
		FarmerSkillInfoBusiness farmerSkillInfoBusiness = new FarmerSkillInfoBusiness();

		if (declareInfoBean != null) {
			// 检查是否完成了填写
			if ((declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT))
					&& declareInfoBean.getDeclareType() != declareType) {
				// 仅有的一个已经是等候审批状态
				HttpResponseKit.alertMessage(response, "申报已经提交,请勿重复填写", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			FarmerSkillInfoBean skillInfoBean = farmerSkillInfoBusiness.getFarmerSkillInfoByKey(declareInfoBean
					.getDeclareId());
			if (skillInfoBean == null) {
				// 新建
				skillInfoBean = new FarmerSkillInfoBean();
				skillInfoBean.setStatus(Constants.STATUS_VALID);
			}
			request.setAttribute("FarmerSkillInfoBean", skillInfoBean);
		} else {
			// 新建
			FarmerSkillInfoBean skillInfoBean = new FarmerSkillInfoBean();
			skillInfoBean.setStatus(Constants.STATUS_VALID);
			request.setAttribute("FarmerSkillInfoBean", skillInfoBean);
		}

		// 获取国家资格证书数据
		NationalCertificationBusiness nationalCertificationBusiness = new NationalCertificationBusiness();
		List<NationalCertificationBean> ncList = nationalCertificationBusiness.getNationalCertificationAllList();
		request.setAttribute("NationalCertificationList", ncList);
		Map<Integer, String> nationalCertificationMap = ListKit.list2Map(ncList, "certificateId", "certificateName");
		request.setAttribute("NationalCertificationMap", nationalCertificationMap);

		PortalKit.forwardPage(request, response, DeclareSurveyConfig.PAGE_PATH + "FarmerSkillInfoManage.jsp");
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
				result = business.updateFarmerSkillInfo(bean);
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
					if (declareId > 0) {
						// 记录Cookies
						CookiesKit.setCookie(response, DeclareSurveyConfig.DECALRE_ID, String.valueOf(declareId),
								DeclareSurveyConfig.ADMIN_DOMAIN);
					}
				} else {
					declareId = originalBean.getDeclareId();

				}
				if (declareId > 0) {
					// 新建技能信息
					bean.setDeclareId(declareId);
					bean.setCreateUser(Constants.STATUS_NOT_VALID);
					result = business.addFarmerSkillInfo(bean);
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
