package com.lpmas.declare.portal.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.bean.DeclareImageBean;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.config.DeclareImageInfoConfig;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.portal.business.DeclareImageBusiness;
import com.lpmas.declare.portal.business.DeclareInfoBusiness;
import com.lpmas.declare.portal.business.DeclareReportBusiness;
import com.lpmas.declare.portal.business.FarmerInfoBusiness;
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

		// 获取申报信息
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId, declareType,
				DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));

		FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
		FarmerInfoBean farmerInfoBean = null;

		DeclareImageBusiness declareImageBusiness = new DeclareImageBusiness();
		DeclareImageBean imageBean = null;
		if (declareInfoBean == null) {
			HttpResponseKit.alertMessage(response, "申报信息错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		} else {
			farmerInfoBean = farmerInfoBusiness.getFarmerInfoByKey(declareInfoBean.getDeclareId());
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
		PortalKit.forwardPage(request, response, DeclarePortalConfig.PAGE_PATH + "FarmerInfoManage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
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
		
		// 获取用户ID
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByCondition(userId, declareType,
				DateKit.formatDate(new Date(), DateKit.REGEX_YEAR));

	
		// 查mongo根据身份证和年份判是否重复
		DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("identityNumber", farmerInfoBean.getIdentityNumber());
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		try {
			List<DeclareReportBean> declareReportList = declareReportBusiness.getDeclareReportListByMap(condMap);
			if (declareReportList != null
					&& !declareReportList.isEmpty()
					&& declareReportList.get(0).getDeclareId() != farmerInfoBean.getDeclareId()
					&& Math.abs(Integer.valueOf(declareInfoBean.getDeclareYear())
							- Integer.valueOf(declareReportList.get(0).getDeclareYear())) < 3) {
				messageBean.setMessage("同一身份证三年内不能重复申报");
				HttpResponseKit.printJson(request, response, messageBean, "");
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 查询申报信息
		int declareId = 0;
		int result = 0;
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

		// 判断申报图片是否新建
		DeclareImageBean dbExistImageBean = declareImageBusiness.getDeclareImageByKey(declareId,
				DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
		if (dbExistImageBean != null) {
			// 修改图片信息
			imageBean.setModifyUser(userId);
			result = declareImageBusiness.updateDeclareImage(imageBean);
		} else {
			// 新建图片信息
			imageBean.setImageType(DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
			imageBean.setStatus(Constants.STATUS_VALID);
			imageBean.setDeclareId(declareId);
			imageBean.setCreateUser(userId);
			result = declareImageBusiness.addDeclareImage(imageBean);
		}
		// 修改农民信息
		farmerInfoBean.setModifyUser(userId);
		result = farmerInfoBusiness.updateFarmerInfo(farmerInfoBean);

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
