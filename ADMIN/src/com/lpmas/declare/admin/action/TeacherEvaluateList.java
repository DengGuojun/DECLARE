package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.TeacherEvaluateBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TeacherEvaluateBusiness;
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
 * Servlet implementation class TeacherEvaluate
 */
@WebServlet("/declare/admin/TeacherEvaluateList.do")
public class TeacherEvaluateList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherEvaluateList() {
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
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		
		 int teacherRange =  ParamKit.getIntParameter(request, "teacherRange", -1) ;
		 
		 if(teacherRange == 0 ){   //入库师资 
			 if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_ALL, OperationConfig.SEARCH)){
				 return;
			 }
		 }else if(teacherRange == 1){   //本辖区师资
			 if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_POPEDOM, OperationConfig.SEARCH)){
				 return;
			 }
		 }else if(teacherRange==2){		//本级师资
			 if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.SEARCH)){
				 return;
			 }
		 }
		 
		int pageNum = ParamKit.getIntParameter(request, "pageNum", DeclareAdminConfig.DEFAULT_PAGE_NUM);
		int pageSize = ParamKit.getIntParameter(request, "pageSize", DeclareAdminConfig.DEFAULT_PAGE_SIZE);
		PageBean pageBean = new PageBean(pageNum, pageSize);

		String condStr = "";
		HashMap<String, String> condMap = ParamKit.getParameterMap(request, condStr);

		int status = ParamKit.getIntParameter(request, "status", Constants.STATUS_VALID);
		condMap.put("status", String.valueOf(status));

		String className = ParamKit.getParameter(request, "className");
		if (StringKit.isValid(className)) {
			condMap.put("className", className);
		}

		String teacherId = ParamKit.getParameter(request, "teacherId");
		if (StringKit.isValid(teacherId)) {
			condMap.put("teacherId", teacherId);
			request.setAttribute("teacherId", teacherId);
		}

		String trainingOrganization = ParamKit.getParameter(request, "trainingOrganization");
		if (StringKit.isValid(trainingOrganization)) {
			condMap.put("trainingOrganization", trainingOrganization);
		}
		 
		TeacherEvaluateBusiness business = new TeacherEvaluateBusiness();
		PageResultBean<TeacherEvaluateBean> result = business.getTeacherEvaluatePageListByMap(condMap, pageBean);

		request.setAttribute("TeacherEvaluateList", result.getRecordList());
		pageBean.init(pageNum, pageSize, result.getTotalRecordNumber());
		request.setAttribute("PageResult", pageBean);
		request.setAttribute("CondList", MapKit.map2List(condMap));
		request.setAttribute("adminUserHelper", adminUserHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TeacherEvaluateList.jsp");
		rd.forward(request, response);
	}

}
