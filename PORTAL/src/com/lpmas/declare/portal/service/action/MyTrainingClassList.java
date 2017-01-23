package com.lpmas.declare.portal.service.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.TrainingClassEvaluateBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.declare.portal.business.DeclareReportBusiness;
import com.lpmas.declare.portal.business.MajorInfoBusiness;
import com.lpmas.declare.portal.business.MajorTypeBusiness;
import com.lpmas.declare.portal.business.TrainingClassEvaluateBusiness;
import com.lpmas.declare.portal.business.TrainingClassInfoBusiness;
import com.lpmas.declare.portal.business.TrainingClassUserBusiness;
import com.lpmas.declare.portal.service.bean.TrainingClassInfoResponseBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.DateKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

@WebServlet("/declare/MyTrainingClassList.action")
public class MyTrainingClassList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyTrainingClassList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		ReturnMessageBean returnMessage = new ReturnMessageBean();

		// 获取用户Id
		int userId = ParamKit.getIntParameter(request, "userId", 0);
		if (userId <= 0) {
			returnMessage.setMessage("userId非法");
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}
		DeclareReportBean declareReportBean = new DeclareReportBean();
		DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("userId", String.valueOf(userId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		try {
			List<DeclareReportBean> list = declareReportBusiness.getDeclareReportListByMap(condMap);
			if (list.isEmpty()) {
				returnMessage.setMessage("没有该用户的申报信息");
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			}
			declareReportBean = list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			returnMessage.setMessage("没有该用户的申报信息");
		}

		// 获取用户所在的培训班
		List<TrainingClassInfoResponseBean> result = new ArrayList<TrainingClassInfoResponseBean>();
		TrainingClassInfoBusiness classInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassUserBusiness classUserBusiness = new TrainingClassUserBusiness();
		TrainingClassEvaluateBusiness classEvaluateBusiness = new TrainingClassEvaluateBusiness();
		condMap = new HashMap<String, String>();
		condMap.put("declareId", String.valueOf(declareReportBean.getDeclareId()));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		List<TrainingClassUserBean> classUserList = classUserBusiness.getTrainingClassUserListByMap(condMap);
		for (TrainingClassUserBean bean : classUserList) {
			TrainingClassInfoBean classInfoBean = classInfoBusiness.getTrainingClassInfoByKey(bean.getClassId());
			TrainingClassInfoResponseBean responseBean = new TrainingClassInfoResponseBean();
			BeanKit.copyBean(responseBean, classInfoBean);
			// 查询专业信息
			MajorTypeBusiness majorTypeBusiness = new MajorTypeBusiness();
			String majorTypeName = majorTypeBusiness.getMajorTypeByKey(classInfoBean.getMajorTypeId()).getMajorName();
			MajorInfoBusiness majorInfoBusiness = new MajorInfoBusiness();
			String majorName = majorInfoBusiness.getMajorInfoByKey(classInfoBean.getMajorId()).getMajorName();
			responseBean.setMajorTypeName(majorTypeName);
			responseBean.setMajorName(majorName);
			// 查询是否已评教
			condMap = new HashMap<String, String>();
			condMap.put("declareId", String.valueOf(declareReportBean.getDeclareId()));
			condMap.put("classId", String.valueOf(bean.getClassId()));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<TrainingClassEvaluateBean> evaluateList = classEvaluateBusiness
					.getTrainingClassEvaluateListByMap(condMap);
			if (!evaluateList.isEmpty()) {
				responseBean.setHasEvaluate(true);
			}
			if (DateKit.diffTime(DateKit.REGEX_DATE, classInfoBean.getTrainingBeginTime(), new Date()) > 60) {
				responseBean.setHasEvaluate(true);
			}
			result.add(responseBean);
		}
		// 返回结果
		returnMessage.setCode(Constants.STATUS_VALID);
		returnMessage.setContent(result);

		HttpResponseKit.printJson(request, response, returnMessage, "");

	}
}
