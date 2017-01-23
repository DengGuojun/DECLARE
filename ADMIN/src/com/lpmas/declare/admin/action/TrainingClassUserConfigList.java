package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;

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
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TrainingClassUserConfigList
 */
@WebServlet("/declare/admin/TrainingClassUserConfigList.do")
public class TrainingClassUserConfigList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingClassUserConfigList() {
		super();
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
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG, OperationConfig.SEARCH)) {
			return;
		}
		// 分页信息
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);
		// 查询条件参数
		HashMap<String, String> condMap = new HashMap<String, String>();

		int type = ParamKit.getIntParameter(request, "type", 0);
		// 获取查询的类型 默认查询的是省级的信息
		String province = ParamKit.getParameter(request, "province", "");
		String city = ParamKit.getParameter(request, "city", "");
		String region = ParamKit.getParameter(request, "region", "");
		 
		String direct = ParamKit.getParameter(request, "direct", "");
		 
		if (StringKit.isValid(direct)) {
			if(type==0){
				if(StringKit.isValid(province)){
					condMap.put("province", province) ;
				} ;
				condMap.put("city", "") ;
			}else if(type==1){
				if(StringKit.isValid(city)){
					condMap.put("city", city) ;
					if(StringKit.isValid(province)){
						condMap.put("province", province) ;
					} ;
				} ;
				condMap.put("region", "") ;
			}else if(type==2){
				if(StringKit.isValid(region)){
					condMap.put("province", province) ;
				} ;
				if(StringKit.isValid(city)){
					condMap.put("city", city) ;
				} ;
				if(StringKit.isValid(region)){
					condMap.put("region", region) ;
				} ;
			}
		} else {
			if (type == 0) {
				// 全部的省份信息
				condMap.put("region", "");
				condMap.put("city", "");
			} else if (type == 1) {
				// 市的级别
				condMap.put("province", province);
				condMap.put("region", "");
			} else if (type == 2) {
				// 区的级别
				condMap.put("province", province);
				condMap.put("city", city);
			}
		}
		String status = ParamKit.getParameter(request, "status", String.valueOf(Constants.STATUS_VALID)).trim();
		if (StringKit.isValid(status)) {
			condMap.put("status", status);
		}
		// 初始化业务类
		TrainingClassUserConfigBusiness business = new TrainingClassUserConfigBusiness();
		PageResultBean<TrainingClassUserConfigBean> result = business.getTrainingClassUserConfigPageListByMap(condMap,
				pageBean);

		// 页面数据绑定
		request.setAttribute("TrainingClassUserConfigList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("adminUserHelper", adminHelper);
		// 页面转发
		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TrainingClassUserConfigList.jsp");
		rd.forward(request, response);
	}

}
