package com.lpmas.declare.console.action;

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

import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.console.bean.TrainingClassUserBean;
import com.lpmas.declare.console.business.DeclareInfoBusiness;
import com.lpmas.declare.console.business.DeclareReportBusiness;
import com.lpmas.declare.console.business.TrainingClassUserBusiness;
import com.lpmas.declare.console.handler.DeclareReportHandler;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.web.HttpResponseKit;

@WebServlet("/declare/DeclareReportSync.do")
public class DeclareReportSync extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(DeclareReportSync.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareReportSync() {
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
		int pageSize = 500;
		int pageNum = 1;
		// 循环读取数据
		PageBean pageBean = new PageBean(pageNum, pageSize);

		DeclareReportBusiness business = new DeclareReportBusiness();
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		TrainingClassUserBusiness trainingClassUserBusiness = new TrainingClassUserBusiness();
		PageResultBean<DeclareInfoBean> resultBean = declareInfoBusiness
				.getDeclareInfoPageListByMap(new HashMap<String, String>(), pageBean);
		List<Integer> classInfoList = null;
		int result = 0;

		while (!resultBean.getRecordList().isEmpty()) {
			try {
				for (DeclareInfoBean declareInfoBean : resultBean.getRecordList()) {
					// 从Mongo中获取相应的数据
					DeclareReportBean declareReportBean = business
							.getDeclareReportByKey(String.valueOf(declareInfoBean.getDeclareId()));
					if (declareReportBean == null) {
						DeclareReportHandler handler = new DeclareReportHandler();
						handler.createDeclareReport(declareInfoBean);
						result = Constants.STATUS_VALID;
						declareReportBean = business
								.getDeclareReportByKey(String.valueOf(declareInfoBean.getDeclareId()));
					}
					List<TrainingClassUserBean> trainingClassUserList = trainingClassUserBusiness
							.getTrainingClassUserListByDeclare(declareInfoBean.getDeclareId());
					if (!trainingClassUserList.isEmpty()) {
						classInfoList = new ArrayList<Integer>(); 
						for (TrainingClassUserBean trainingClassUserBean : trainingClassUserList) {
							classInfoList.add(trainingClassUserBean.getClassId());
						}
						declareReportBean.setTrainingClassInfoList(classInfoList);
						business.updateDeclareReport(declareReportBean);
						result = Constants.STATUS_VALID;
					}
				}
			} catch (Exception e) {
				log.error("总表记录创建失败", e);
				continue;
			} finally {
			}

			pageNum++;
			pageBean = new PageBean(pageNum, pageSize);
			resultBean = declareInfoBusiness.getDeclareInfoPageListByMap(new HashMap<String, String>(), pageBean);
		}

		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/DeclareReportList.do");
		} else {
			HttpResponseKit.alertMessage(response, "已全部更新", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}
}
