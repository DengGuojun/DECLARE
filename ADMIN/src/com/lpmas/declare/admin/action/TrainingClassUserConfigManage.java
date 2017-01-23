package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.TrainingClassUserConfigBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TrainingClassUserConfigBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.config.TrainingClassUserConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class TrainingClassUserConfigManage
 */
@WebServlet("/declare/admin/TrainingClassUserConfigManage.do")
public class TrainingClassUserConfigManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassUserConfigManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		int configId = ParamKit.getIntParameter(request, "configId", 0);

		TrainingClassUserConfigBean bean = new TrainingClassUserConfigBean();
		TrainingClassUserConfigBusiness business = new TrainingClassUserConfigBusiness();

		if (configId > 0) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG,
					OperationConfig.UPDATE)) {
				return;
			}
			bean = business.getTrainingClassUserConfigByKey(configId);
		} else {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG,
					OperationConfig.SEARCH)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
		}

		request.setAttribute("adminUserHelper", adminUserHelper);
		request.setAttribute("TrainingClassUserConfigBean", bean);
		RequestDispatcher rd = this.getServletContext()
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "/TrainingClassUserConfigManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		TrainingClassUserConfigBean bean = new TrainingClassUserConfigBean();
		bean = BeanKit.request2Bean(request, TrainingClassUserConfigBean.class);
		
		TrainingClassUserConfigBusiness trainingClassUserConfigBusiness = new TrainingClassUserConfigBusiness();
		int result = 0;

		ReturnMessageBean messageBean = trainingClassUserConfigBusiness.verifyTrainingClassUserConfig(bean) ;
		
		if (StringKit.isValid(messageBean.getMessage())) {
			HttpResponseKit.alertMessage(response, messageBean.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
  
		if (bean.getConfigId() > 0) {
			//修改功能 
		} else {
			if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG, OperationConfig.CREATE)) {
				return;
			}
			//临时保存省市区的信息 
			String region = bean.getRegion();  
			String province = bean.getProvince();
			String city = bean.getCity();
			
			if (StringKit.isValid(bean.getRegion())) {
				//如果用户的等级 是 省市区
				bean.setRegion("");
				//设置地区 为 null 
				TrainingClassUserConfigBean trainingClassUserConfigBean = trainingClassUserConfigBusiness
						.getTrainingClassUserConfigBeanByAreaAndSatus(bean);
				if (trainingClassUserConfigBean == null) {
					//如果上一级还没有设置信息  
					HttpResponseKit.alertMessage(response,
							"处理失败,请设置【" + bean.getProvince() + bean.getCity() + "】的学员录入设置",
							HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				} else {
					//上一级已经设置学员录入频率
					bean.setCity("");
					bean.setRegion("");
					//设置省份 
					trainingClassUserConfigBean = new TrainingClassUserConfigBean();
					trainingClassUserConfigBean = trainingClassUserConfigBusiness
							.getTrainingClassUserConfigBeanByAreaAndSatus(bean);
					if (trainingClassUserConfigBean == null) {
						HttpResponseKit.alertMessage(response, "处理失败,请设置【" + bean.getProvince() + "】的学员录入设置",
								HttpResponseKit.ACTION_HISTORY_BACK);
						return;
					}
				}
			} else if (StringKit.isValid(bean.getCity())) {
				//该地区 为 省市 
				bean.setCity("");
				bean.setRegion("");
				TrainingClassUserConfigBean trainingClassUserConfigBean = trainingClassUserConfigBusiness
						.getTrainingClassUserConfigBeanByAreaAndSatus(bean);
				if (trainingClassUserConfigBean == null) {
					HttpResponseKit.alertMessage(response, "处理失败,请设置【" + bean.getProvince() + "】的学员录入设置",
							HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
			}
			//重新设置前台的数据 
			bean.setRegion(region);
			bean.setCity(city);
			bean.setProvince(province);

			TrainingClassUserConfigBean newBean = trainingClassUserConfigBusiness.getTrainingClassUserConfigBeanByAreaAndSatus(bean);
			
			if (newBean != null) {
				if (newBean.getConfigId() > 0) {
					//newBean.setStatus(Constants.STATUS_NOT_VALID);
					newBean.setConfigFrequency(bean.getConfigFrequency());
					newBean.setConfigMode(bean.getConfigMode());
					newBean.setCreateUser(adminHelper.getAdminUserId());
					result = trainingClassUserConfigBusiness.updateTrainingClassUserConfig(newBean);
				}
			}else{
				result = trainingClassUserConfigBusiness.addTrainingClassUserConfig(bean);
			}
			
			if (TrainingClassUserConfig.NOT_DIRECT_UNDER.equals(bean.getConfigMode())) {
				// 如果是设置直属以及下属
				HashMap<String, String> condMap = new HashMap<String, String>();
				// 获取所有的信息
				if (StringKit.isValid(bean.getCity()) && !StringKit.isValid(bean.getRegion())) { // 省份不为空  省市
					condMap.put("city", bean.getCity());
					List<TrainingClassUserConfigBean> trainingClassUserConfigBeans = trainingClassUserConfigBusiness
							.getTrainingClassUserConfigPageListByMap(condMap);
					for (TrainingClassUserConfigBean tcuBean : trainingClassUserConfigBeans) {
						if (StringKit.isValid(tcuBean.getRegion())) {
							tcuBean.setConfigFrequency(bean.getConfigFrequency());
							trainingClassUserConfigBusiness.updateTrainingClassUserConfig(tcuBean);
						}
					}
				} else if (StringKit.isValid(bean.getProvince()) && !StringKit.isValid(bean.getCity())) {
					condMap.put("province", bean.getProvince());
					List<TrainingClassUserConfigBean> trainingClassUserConfigBeans = trainingClassUserConfigBusiness
							.getTrainingClassUserConfigPageListByMap(condMap);
					for (TrainingClassUserConfigBean tcuBean : trainingClassUserConfigBeans) {
						if (StringKit.isValid(tcuBean.getCity())) {
							tcuBean.setConfigFrequency(bean.getConfigFrequency());
							trainingClassUserConfigBusiness.updateTrainingClassUserConfig(tcuBean);
						}
					}
				}
			}
		}
		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TrainingClassUserConfigList.do");
		} else {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}

}
