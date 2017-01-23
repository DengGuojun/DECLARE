package com.lpmas.declare.survey.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.survey.business.DeclareInfoBusiness;
import com.lpmas.declare.survey.config.DeclareSurveyConfig;
import com.lpmas.declare.survey.handler.DeclareReportHandler;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.CookiesKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

@WebServlet("/declare/DeclareInfoApprove.do")
public class DeclareInfoApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DeclareInfoApprove.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoApprove() {
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
		String checkDeclareList = ParamKit.getParameter(request, "checkDeclareList", "").trim();
		int approveAction = ParamKit.getIntParameter(request, "approveAction", 0);
		int result = 0;
		long resultMongo = 0;
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		if (approveAction == Constants.STATUS_VALID) {
			int declareId = ParamKit.getIntParameter(request, "declareId", 0);
			DeclareInfoBean declareInfoBean = declareInfoBusiness.getDeclareInfoByKey(declareId);
			if (declareInfoBean == null) {
				HttpResponseKit.alertMessage(response, "对象为空，请刷新重试", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			} else {
				declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE);
				result = declareInfoBusiness.updateDeclareInfo(declareInfoBean);
				if (result < 0) {
					HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				// 插入mongo
				DeclareReportHandler handler = new DeclareReportHandler();
				try {
					handler.createDeclareReport(declareInfoBean);
					resultMongo = Constants.STATUS_VALID;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.error("总表记录创建失败", e);
				}
				if (resultMongo <= 0) {
					HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			}
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/DeclareReportList.do");
		} else if (StringKit.isValid(checkDeclareList)) {
			String[] sourceStrArray = checkDeclareList.split(",");
			for (int i = 0; i < sourceStrArray.length; ++i) {
				DeclareInfoBean declareInfoBean = declareInfoBusiness
						.getDeclareInfoByKey(Integer.parseInt(sourceStrArray[i]));
				if (declareInfoBean == null) {
					HttpResponseKit.alertMessage(response, "对象为空，请刷新重试", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				} else {
					declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_APPROVE);
					result = declareInfoBusiness.updateDeclareInfo(declareInfoBean);
					if (result < 0) {
						HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
						return;
					}
					// 插入mongo
					DeclareReportHandler handler = new DeclareReportHandler();
					try {
						handler.createDeclareReport(declareInfoBean);
						resultMongo = Constants.STATUS_VALID;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.error("总表记录创建失败", e);
					}
					if (resultMongo <= 0) {
						HttpResponseKit.alertMessage(response, "操作失败", HttpResponseKit.ACTION_HISTORY_BACK);
						return;
					}
				}
			}
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/DeclareReportList.do");
		} else {
			HttpResponseKit.alertMessage(response, "操作不当！", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
	}

}
