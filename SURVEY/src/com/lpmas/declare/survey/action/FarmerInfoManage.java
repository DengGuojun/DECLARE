package com.lpmas.declare.survey.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.bean.DeclareImageBean;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.config.DeclareImageInfoConfig;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.survey.business.DeclareImageBusiness;
import com.lpmas.declare.survey.business.DeclareInfoBusiness;
import com.lpmas.declare.survey.business.FarmerInfoBusiness;
import com.lpmas.declare.survey.config.DeclareSurveyConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.CookiesKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class FarmerInfoManage
 */
@WebServlet("/declare/FarmerInfoManage.do")
public class FarmerInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FarmerInfoManage() {
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

		// 获取申报信息
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = null;
		if (StringKit.isVerified(declareId)) {
			declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(Integer.valueOf(declareId));
		}

		FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
		FarmerInfoBean farmerInfoBean = null;

		DeclareImageBusiness declareImageBusiness = new DeclareImageBusiness();
		DeclareImageBean imageBean = null;

		if (declareInfoBean == null) {
			declareInfoBean = new DeclareInfoBean();
			farmerInfoBean = new FarmerInfoBean();
			imageBean = new DeclareImageBean();
		} else {
			// 检查是否完成了填写
			if ((declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT))
					&& declareInfoBean.getDeclareType() != declareType) {
				// 仅有的一个已经是等候审批状态
				HttpResponseKit.alertMessage(response, "申报已经提交,请勿重复填写", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			farmerInfoBean = farmerInfoBusiness.getFarmerInfoByKey(declareInfoBean.getDeclareId());
			if (farmerInfoBean == null) {
				farmerInfoBean = new FarmerInfoBean();
			}
			imageBean = declareImageBusiness.getDeclareImageByKey(declareInfoBean.getDeclareId(),
					DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
			if (imageBean == null) {
				imageBean = new DeclareImageBean();
			}
		}

		// 设值
		request.setAttribute("DeclareInfoBean", declareInfoBean);
		request.setAttribute("FarmerInfoBean", farmerInfoBean);
		request.setAttribute("DeclareImageBean", imageBean);
		// 转发
		PortalKit.forwardPage(request, response, DeclareSurveyConfig.PAGE_PATH + "FarmerInfoManage.jsp");
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

		// 映射Bean并进行数据验证
		ReturnMessageBean verifyMessage = null;
		FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
		FarmerInfoBean farmerInfoBean = BeanKit.request2Bean(request, FarmerInfoBean.class);
		verifyMessage = farmerInfoBusiness.verifyFarmerInfoBean(farmerInfoBean);
		if (StringKit.isValid(verifyMessage.getMessage())) {
			messageBean.setMessage(verifyMessage.getMessage());
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

		DeclareImageBusiness declareImageBusiness = new DeclareImageBusiness();
		DeclareImageBean imageBean = BeanKit.request2Bean(request, DeclareImageBean.class);
		verifyMessage = declareImageBusiness.verifyDeclareImageBean(imageBean);
		if (StringKit.isValid(verifyMessage.getMessage())) {
			messageBean.setMessage(verifyMessage.getMessage());
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}

		// 查询申报信息
		int declareId = 0;
		int result = 0;
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		// 获取declareId
		String declareIdKey = CookiesKit.getCookies(request, DeclareSurveyConfig.DECALRE_ID);
		DeclareInfoBean declareInfoBean = null;
		if (StringKit.isVerified(declareIdKey)) {
			declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(Integer.valueOf(declareIdKey));
		}
		if (declareInfoBean != null) {
			// 已存在申报信息
			// 检验申报ID
			if ((declareInfoBean.getDeclareId() != farmerInfoBean.getDeclareId())) {
				messageBean.setMessage("申报ID不合法");
				HttpResponseKit.printJson(request, response, messageBean, "");
				return;
			}
			// 检验是否已经是待审批状态
			if (DeclareInfoConfig.DECLARE_STATUS_SUBMIT.equals(declareInfoBean.getDeclareStatus())) {
				messageBean.setMessage("已经是审核状态，不能修改");
				HttpResponseKit.printJson(request, response, messageBean, "");
				return;
			}
			declareId = declareInfoBean.getDeclareId();
		} else {
			// 先新建申报信息
			declareInfoBean = new DeclareInfoBean();
			declareInfoBean.setDeclareType(declareType);
			declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_NOT_SUBMIT);
			declareInfoBean.setUserId(Constants.STATUS_NOT_VALID);
			declareInfoBean.setStatus(Constants.STATUS_VALID);
			declareInfoBean.setCreateUser(Constants.STATUS_NOT_VALID);
			declareId = declareInfoBusiness.addDeclareInfo(declareInfoBean);
			if(declareId >0){
				// 记录Cookies
				CookiesKit.setCookie(response, DeclareSurveyConfig.DECALRE_ID, String.valueOf(declareId),
						DeclareSurveyConfig.ADMIN_DOMAIN);
			}
			if (declareId <= 0) {
				messageBean.setMessage("操作失败");
				HttpResponseKit.printJson(request, response, messageBean, "");
				return;
			}
		}

		// 判断申报图片是否新建
		DeclareImageBean dbExistImageBean = declareImageBusiness.getDeclareImageByKey(declareId,
				DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
		if (dbExistImageBean != null) {
			// 修改图片信息
			imageBean.setModifyUser(Constants.STATUS_NOT_VALID);
			result = declareImageBusiness.updateDeclareImage(imageBean);
		} else {
			// 新建图片信息
			imageBean.setImageType(DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
			imageBean.setStatus(Constants.STATUS_VALID);
			imageBean.setDeclareId(declareId);
			imageBean.setCreateUser(Constants.STATUS_NOT_VALID);
			result = declareImageBusiness.addDeclareImage(imageBean);
		}
		// 判断农民信息是否新建
		FarmerInfoBean dbExistFarmerInfoBean = farmerInfoBusiness.getFarmerInfoByKey(declareId);
		if (dbExistFarmerInfoBean != null) {
			// 修改农民信息
			farmerInfoBean.setModifyUser(Constants.STATUS_NOT_VALID);
			result = farmerInfoBusiness.updateFarmerInfo(farmerInfoBean);
		} else {
			// 新建农民信息
			farmerInfoBean.setCreateUser(Constants.STATUS_NOT_VALID);
			farmerInfoBean.setStatus(Constants.STATUS_VALID);
			farmerInfoBean.setDeclareId(declareId);
			result = farmerInfoBusiness.addFarmerInfo(farmerInfoBean);
		}

		if (result >= 0) {
			messageBean.setCode(Constants.STATUS_VALID);
			messageBean.setMessage("操作成功");
		} else {
			messageBean.setMessage("操作失败");
		}
		HttpResponseKit.printJson(request, response, messageBean, "");
		return;
	}

}
