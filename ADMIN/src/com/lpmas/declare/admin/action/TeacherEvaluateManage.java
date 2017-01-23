package com.lpmas.declare.admin.action;

import java.io.IOException;

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
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class TeacherEvaluateManage
 */
@WebServlet("/declare/admin/TeacherEvaluateManage.do")
public class TeacherEvaluateManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherEvaluateManage() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		int teacherEvaluateId = ParamKit.getIntParameter(request, "evaluateId", 0);
		int teacherId = ParamKit.getIntParameter(request, "teacherId", 0);
		TeacherEvaluateBean bean = new TeacherEvaluateBean();
		if (teacherEvaluateId > 0) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
					OperationConfig.UPDATE)) {
				return;
			}
			TeacherEvaluateBusiness business = new TeacherEvaluateBusiness();
			bean = business.getTeacherEvaluateByKey(teacherEvaluateId);
		} else {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
					OperationConfig.CREATE)) {
				return;
			}
			bean.setStatus(Constants.STATUS_VALID);
			bean.setTeacherId(teacherId);
		}
		request.setAttribute("adminUserHelper", adminUserHelper);
		request.setAttribute("TeacherEvaluateBean", bean);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "TeacherEvaluateManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		TeacherEvaluateBean bean = new TeacherEvaluateBean();
		bean = BeanKit.request2Bean(request, TeacherEvaluateBean.class);
		TeacherEvaluateBusiness business = new TeacherEvaluateBusiness();
		ReturnMessageBean returnMessageBean = business.verifyTeacherEvaluate(bean);
		int teacherRange = ParamKit.getIntParameter(request, "teacherRange", 0);
		if (StringKit.isValid(returnMessageBean.getMessage())) {
			HttpResponseKit.alertMessage(response, returnMessageBean.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		int result = 0;
		if (bean.getEvaluateId() > 0) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
					OperationConfig.UPDATE)) {
				return;
			}
			bean.setModifyUser(adminUserHelper.getAdminUserId());
			result = business.updateTeacherEvaluate(bean);
		} else {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
					OperationConfig.CREATE)) {
				return;
			}
			bean.setCreateUser(adminUserHelper.getAdminUserId());
			result = business.addTeacherEvaluate(bean);
		}
		if (result > 0) {
			HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TeacherEvaluateList.do?teacherRange="
					+ teacherRange + "&teacherId=" + bean.getTeacherId());
		} else {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}

}
