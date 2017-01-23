package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.bean.TeacherRegionInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.business.TeacherMajorTypeBusiness;
import com.lpmas.declare.admin.business.TeacherDeclareBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.declare.bean.TeacherMajorTypeBean;
import com.lpmas.declare.bean.TeacherDeclareBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TeacherDeclareManage
 */
@WebServlet("/declare/admin/TeacherDeclareManage.do")
public class TeacherDeclareManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取teacherId
		Integer declareId = ParamKit.getIntParameter(request, "declareId", 0);
		// 权限
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		List<FileInfoBean> attachList = new ArrayList<FileInfoBean>();
		// 教师信息
		TeacherDeclareBean bean = new TeacherDeclareBean();
		TeacherInfoBean teacherInfoBean = new TeacherInfoBean();
		TeacherDeclareBusiness business = new TeacherDeclareBusiness();
		// 教师地区信息
		TeacherRegionInfoBean teacherRegionInfoBean = new TeacherRegionInfoBean();
		if (declareId > 0) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.UPDATE)) {
				return;
			}
			bean = business.getTeacherDeclareByKey(declareId);
			BeanKit.copyBean(teacherInfoBean, bean);
			// 获取附件信息
			FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("infoId", String.valueOf(bean.getDeclareId()));
			condMap.put("infoType", String.valueOf(FileInfoConfig.INFO_TYPE_TEACHER_DECLARE_ATTACH));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			attachList = fileInfoBusiness.getFileInfoListByMap(condMap);
		} 
		TeacherMajorTypeBusiness teacherMajorTypeBusiness = new TeacherMajorTypeBusiness();
		// 获取TeacherMajorTypeList
		List<TeacherMajorTypeBean> teacherMajorTypeList = teacherMajorTypeBusiness.getTeacherMajorTypeAllList();
		request.setAttribute("TeacherMajorTypeList", teacherMajorTypeList);
		request.setAttribute("TeacherInfoBean", teacherInfoBean);
		request.setAttribute("TeacherRegionInfoBean", teacherRegionInfoBean);
		request.setAttribute("adminUserHelper", adminUserHelper);
		request.setAttribute("attachList", attachList);
		request.setAttribute("IsDeclare", true);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher(
				DeclareAdminConfig.PAGE_PATH + "/TeacherInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		this.doGet(request, response);

	}
}