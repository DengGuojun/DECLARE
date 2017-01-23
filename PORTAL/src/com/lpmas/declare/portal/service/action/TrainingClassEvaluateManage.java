package com.lpmas.declare.portal.service.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.TrainingClassEvaluateBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.declare.bean.TrainingClassItemBean;
import com.lpmas.declare.bean.TrainingClassItemEvaluateBean;
import com.lpmas.declare.bean.TrainingClassUserBean;
import com.lpmas.declare.config.TrainingClassInfoConfig;
import com.lpmas.declare.portal.business.DeclareReportBusiness;
import com.lpmas.declare.portal.business.TrainingClassEvaluateBusiness;
import com.lpmas.declare.portal.business.TrainingClassInfoBusiness;
import com.lpmas.declare.portal.business.TrainingClassItemBusiness;
import com.lpmas.declare.portal.business.TrainingClassItemEvaluateBusiness;
import com.lpmas.declare.portal.business.TrainingClassUserBusiness;
import com.lpmas.declare.portal.service.bean.TrainingClassEvaluateResponseBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

@WebServlet("/declare/TrainingClassEvaluateManage.action")
public class TrainingClassEvaluateManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(TrainingClassEvaluateManage.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassEvaluateManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReturnMessageBean returnMessage = new ReturnMessageBean();
		List<TrainingClassEvaluateResponseBean> result = new ArrayList<TrainingClassEvaluateResponseBean>();

		// 获取用户Id
		int userId = ParamKit.getIntParameter(request, "userId", 0);
		if (userId <= 0) {
			returnMessage.setCode(-1);
			returnMessage.setMessage("userId非法");
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}

		// 获取培训班Id
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		TrainingClassInfoBusiness classInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean classInfoBean = classInfoBusiness.getTrainingClassInfoByKey(classId);
		if (classInfoBean == null) {
			returnMessage.setMessage("班级Id非法");
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}

		try {
			DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("userId", String.valueOf(userId));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<DeclareReportBean> list = declareReportBusiness.getDeclareReportListByMap(condMap);
			if (list.isEmpty()) {
				returnMessage.setMessage("没有该用户的申报信息");
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			}
			DeclareReportBean declareReportBean = list.get(0);

			// 检查是否用户属于培训班
			TrainingClassUserBusiness classUserBusiness = new TrainingClassUserBusiness();
			condMap = new HashMap<String, String>();
			condMap.put("classId", String.valueOf(classId));
			condMap.put("declareId", String.valueOf(declareReportBean.getDeclareId()));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<TrainingClassUserBean> classUserList = classUserBusiness.getTrainingClassUserListByMap(condMap);
			if (classUserList.isEmpty()) {
				returnMessage.setMessage("没有该用户的班级信息");
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			}

			TrainingClassEvaluateBusiness classEvaluateBusiness = new TrainingClassEvaluateBusiness();
			TrainingClassItemEvaluateBusiness classItemEvaluateBusiness = new TrainingClassItemEvaluateBusiness();

			// 查询用户的课程评教信息
			TrainingClassItemBusiness classItemBusiness = new TrainingClassItemBusiness();
			condMap = new HashMap<String, String>();
			condMap.put("classId", String.valueOf(classId));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<TrainingClassItemBean> classItemList = classItemBusiness.getTrainingClassItemListByMap(condMap);
			for (TrainingClassItemBean bean : classItemList) {
				TrainingClassEvaluateResponseBean responseBean = new TrainingClassEvaluateResponseBean();
				responseBean.setClassId(classId);
				responseBean.setItemId(bean.getItemId());
				String itemName = "《" + bean.getItemName() + "》 " + bean.getTrainingTeacher();
				responseBean.setItemName(itemName);

				condMap = new HashMap<String, String>();
				condMap.put("declareId", String.valueOf(declareReportBean.getDeclareId()));
				condMap.put("classId", String.valueOf(classId));
				condMap.put("status", String.valueOf(Constants.STATUS_VALID));
				List<TrainingClassItemEvaluateBean> classItemEvaluateList = classItemEvaluateBusiness
						.getTrainingClassItemEvaluateListByMap(condMap);
				if (!classItemEvaluateList.isEmpty()) {
					responseBean.setEvaluateScore(classItemEvaluateList.get(0).getEvaluateScore());
				}
				result.add(responseBean);
			}

			// 查询用户的班级评教信息
			TrainingClassEvaluateResponseBean effectBean = new TrainingClassEvaluateResponseBean();
			effectBean.setClassId(classId);
			effectBean.setItemName(MapKit.getValueFromMap(TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_EFFECT,
					TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_MAP));
			TrainingClassEvaluateResponseBean organizeBean = new TrainingClassEvaluateResponseBean();
			organizeBean.setClassId(classId);
			organizeBean.setItemName(MapKit.getValueFromMap(TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_ORGANIZE,
					TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_MAP));
			condMap = new HashMap<String, String>();
			condMap.put("declareId", String.valueOf(declareReportBean.getDeclareId()));
			condMap.put("classId", String.valueOf(classId));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<TrainingClassEvaluateBean> classEvaluateList = classEvaluateBusiness
					.getTrainingClassEvaluateListByMap(condMap);
			for (TrainingClassEvaluateBean bean : classEvaluateList) {
				if (bean.getEvaluateType().equals(TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_EFFECT)) {
					effectBean.setEvaluateScore(bean.getEvaluateScore());
				} else {
					organizeBean.setEvaluateScore(bean.getEvaluateScore());
				}
			}
			result.add(effectBean);
			result.add(organizeBean);

			// 返回结果
			returnMessage.setCode(Constants.STATUS_VALID);
			returnMessage.setContent(result);
		} catch (Exception e) {
			returnMessage.setMessage("没有该用户的申报信息");
		}

		// 返回结果
		HttpResponseKit.printJson(request, response, returnMessage, "");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		ReturnMessageBean returnMessage = new ReturnMessageBean();
		List<TrainingClassEvaluateResponseBean> result = new ArrayList<TrainingClassEvaluateResponseBean>();

		// 获取用户Id
		int userId = ParamKit.getIntParameter(request, "userId", 0);
		if (userId <= 0) {
			returnMessage.setMessage("userId非法");
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}

		// 获取培训班Id
		int classId = ParamKit.getIntParameter(request, "classId", 0);
		TrainingClassInfoBusiness classInfoBusiness = new TrainingClassInfoBusiness();
		TrainingClassInfoBean classInfoBean = classInfoBusiness.getTrainingClassInfoByKey(classId);
		if (classInfoBean == null) {
			returnMessage.setMessage("班级Id非法");
			HttpResponseKit.printJson(request, response, returnMessage, "");
			return;
		}

		try {
			DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("userId", String.valueOf(userId));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<DeclareReportBean> list = declareReportBusiness.getDeclareReportListByMap(condMap);
			if (list.isEmpty()) {
				returnMessage.setMessage("没有该用户的申报信息");
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			}
			DeclareReportBean declareReportBean = list.get(0);

			// 检查用户属于培训班
			TrainingClassUserBusiness classUserBusiness = new TrainingClassUserBusiness();
			condMap = new HashMap<String, String>();
			condMap.put("classId", String.valueOf(classId));
			condMap.put("declareId", String.valueOf(declareReportBean.getDeclareId()));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			List<TrainingClassUserBean> classUserList = classUserBusiness.getTrainingClassUserListByMap(condMap);
			if (classUserList.isEmpty()) {
				returnMessage.setMessage("没有该用户的班级信息");
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			}

			String itemIds = ParamKit.getParameter(request, "itemIds", "");
			String itemNames = ParamKit.getParameter(request, "itemNames", "");
			String scores = ParamKit.getParameter(request, "scores", "");

			String itemId[] = itemIds.split(",");
			String itemName[] = itemNames.split(",");
			String score[] = scores.split(",");

			if (itemId.length != itemName.length || itemId.length != score.length) {
				returnMessage.setMessage("参数长度错误");
				HttpResponseKit.printJson(request, response, returnMessage, "");
				return;
			}
			// 检查itemId的正确性
			TrainingClassItemBusiness classItemBusiness = new TrainingClassItemBusiness();
			for (String str : itemId) {
				if (!str.equals("0") && classItemBusiness.getTrainingClassItemByKey(Integer.valueOf(str)) == null) {
					returnMessage.setMessage("没有对应的课程信息");
					HttpResponseKit.printJson(request, response, returnMessage, "");
					return;
				}
			}

			for (int i = 0; i < itemId.length; i++) {
				log.debug("评教 itemId[i]:" + itemId[i]);
				log.debug("评教 itemName[i]:" + itemName[i]);
				if (itemId[i].equals("0")) {
					// 如果itemId为0，则此评价为培训班评价
					TrainingClassEvaluateBusiness classEvaluateBusiness = new TrainingClassEvaluateBusiness();
					condMap = new HashMap<String, String>();
					condMap.put("declareId", String.valueOf(declareReportBean.getDeclareId()));
					condMap.put("classId", String.valueOf(classId));
					String evaluateType = TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_EFFECT;
					if (itemName[i].equals("培训效果")) {
						evaluateType = TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_EFFECT;
					} else if (itemName[i].equals("组织管理")) {
						evaluateType = TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_ORGANIZE;
					}
					condMap.put("evaluateType", evaluateType);
					condMap.put("status", String.valueOf(Constants.STATUS_VALID));
					List<TrainingClassEvaluateBean> classEvaluateList = classEvaluateBusiness
							.getTrainingClassEvaluateListByMap(condMap);
					if (classEvaluateList.isEmpty()) {
						// 新增评价
						TrainingClassEvaluateBean bean = new TrainingClassEvaluateBean();
						bean.setClassId(classId);
						bean.setDeclareId(declareReportBean.getDeclareId());
						bean.setEvaluateType(evaluateType);
						bean.setEvaluateScore(Double.valueOf(score[i]));
						bean.setStatus(Constants.STATUS_VALID);
						bean.setCreateUser(userId);
						classEvaluateBusiness.addTrainingClassEvaluate(bean);
					} else {
						// 修改评价
						TrainingClassEvaluateBean bean = classEvaluateList.get(0);
						bean.setEvaluateScore(Double.valueOf(score[i]));
						classEvaluateBusiness.updateTrainingClassEvaluate(bean);
					}
				} else {
					// 如果itemId不为0，则此评价为课程评价
					TrainingClassItemEvaluateBusiness classItemEvaluateBusiness = new TrainingClassItemEvaluateBusiness();
					condMap = new HashMap<String, String>();
					condMap.put("declareId", String.valueOf(declareReportBean.getDeclareId()));
					condMap.put("classId", String.valueOf(classId));
					condMap.put("itemId", itemId[i]);
					condMap.put("status", String.valueOf(Constants.STATUS_VALID));
					List<TrainingClassItemEvaluateBean> classItemEvaluateList = classItemEvaluateBusiness
							.getTrainingClassItemEvaluateListByMap(condMap);
					if (classItemEvaluateList.isEmpty()) {
						// 新增评价
						TrainingClassItemEvaluateBean bean = new TrainingClassItemEvaluateBean();
						bean.setClassId(classId);
						bean.setItemId(Integer.valueOf(itemId[i]));
						bean.setDeclareId(declareReportBean.getDeclareId());
						bean.setEvaluateScore(Double.valueOf(score[i]));
						bean.setStatus(Constants.STATUS_VALID);
						bean.setCreateUser(userId);
						classItemEvaluateBusiness.addTrainingClassItemEvaluate(bean);
					} else {
						// 修改评价
						TrainingClassItemEvaluateBean bean = classItemEvaluateList.get(0);
						bean.setEvaluateScore(Double.valueOf(score[i]));
						classItemEvaluateBusiness.updateTrainingClassItemEvaluate(bean);
					}
				}
			}

			returnMessage.setCode(Constants.STATUS_VALID);
			returnMessage.setContent(result);
		} catch (Exception e) {
			returnMessage.setMessage("没有该用户的申报信息");
		}

		// 返回结果

		HttpResponseKit.printJson(request, response, returnMessage, "");

	}
}
