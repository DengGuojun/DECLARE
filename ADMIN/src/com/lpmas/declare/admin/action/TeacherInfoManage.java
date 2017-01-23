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

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.bean.TeacherRegionInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.business.TeacherMajorTypeBusiness;
import com.lpmas.declare.admin.business.TeacherInfoBusiness;
import com.lpmas.declare.admin.business.TeacherRegionInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.declare.bean.TeacherMajorTypeBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

/**
 * Servlet implementation class TeacherInfoManage
 */
@WebServlet("/declare/admin/TeacherInfoManage.do")
public class TeacherInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取teacherId
		Integer teacherId = ParamKit.getIntParameter(request, "teacherId", 0);
		// 权限
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		List<FileInfoBean> attachList = new ArrayList<FileInfoBean>();
		// 教师信息
		TeacherInfoBean bean = new TeacherInfoBean();
		TeacherInfoBusiness business = new TeacherInfoBusiness();
		// 教师地区信息
		TeacherRegionInfoBean teacherRegionInfoBean = new TeacherRegionInfoBean();
		if (teacherId > 0) {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
					OperationConfig.UPDATE)) {
				return;
			}
			bean = business.getTeacherInfoByKey(teacherId);
			// 获取附件信息
			FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("infoId", String.valueOf(bean.getTeacherId()));
			condMap.put("infoType", String.valueOf(FileInfoConfig.INFO_TYPE_TEACHER_ATTACH));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			attachList = fileInfoBusiness.getFileInfoListByMap(condMap);
		} else {
			if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
					OperationConfig.CREATE)) {
				return;
			}
			// 设置教师信息的status为true(有效)
			bean.setStatus(Constants.STATUS_VALID);
		}
		TeacherMajorTypeBusiness teacherMajorTypeBusiness = new TeacherMajorTypeBusiness();
		// 获取MajorTypeList
		List<TeacherMajorTypeBean> teacherMajorTypeList = teacherMajorTypeBusiness.getTeacherMajorTypeAllList();
		request.setAttribute("TeacherMajorTypeList", teacherMajorTypeList);
		request.setAttribute("TeacherInfoBean", bean);
		request.setAttribute("TeacherRegionInfoBean", teacherRegionInfoBean);
		request.setAttribute("adminUserHelper", adminUserHelper);
		request.setAttribute("attachList", attachList); 
		RequestDispatcher rd = this.getServletContext()
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "/TeacherInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {
		try {

			// 权限
			AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
			AdminUserInfoBean adminUserInfoBean = adminUserHelper.getAdminUserInfo();
			// 教师信息
			TeacherInfoBean bean = new TeacherInfoBean();
			// 获取前台传回的参数
			bean = BeanKit.request2Bean(request, TeacherInfoBean.class);
			TeacherInfoBusiness business = new TeacherInfoBusiness();
			// 教师地区信息
			TeacherRegionInfoBean trBean = new TeacherRegionInfoBean();
			// 获取教地区信息
			trBean = BeanKit.request2Bean(request, TeacherRegionInfoBean.class);

			TeacherRegionInfoBusiness trBusiness = new TeacherRegionInfoBusiness();
			// 验证数据的正确性
			ReturnMessageBean messageBean = business.verifyTeacherInfo(bean);
			if (StringKit.isValid(messageBean.getMessage())) {
				HttpResponseKit.alertMessage(response, messageBean.getMessage(), HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 保存更新的结果
			int result = 0;
			if (bean.getTeacherId() > 0) {
				// 更新教师信息
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
						OperationConfig.UPDATE)) {
					return;
				}
				result = business.updateTeacherInfo(bean);
				if (result > 0) {
					// 更新成功
					FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
					String fileIds = ParamKit.getParameter(request, "fileId");
					String files[] = fileIds.split(",");
					for (String fileId : files) {
						if (StringKit.isValid(fileId.trim())) {
							FileInfoBean fileInfoBean = fileInfoBusiness.getFileInfoByKey(Integer.valueOf(fileId.trim()));
							fileInfoBean.setInfoId(bean.getTeacherId());
							fileInfoBusiness.updateFileInfo(fileInfoBean);
						}
					}
				}
			} else {
				// 增加教师信息
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
						OperationConfig.CREATE)) {
					return;
				}
				// 增加教师信息
				result = business.addTeacherInfo(bean);
				if (result > 0) {
					bean.setTeacherId(result);
					if (bean.getTeacherId() > 0) {
						// 处理相关材料
						FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
						String fileIds = ParamKit.getParameter(request, "fileId");
						String files[] = fileIds.split(",");
						for (String fileId : files) {
							if (StringKit.isValid(fileId.trim())) {
								FileInfoBean fileInfoBean = fileInfoBusiness
										.getFileInfoByKey(Integer.valueOf(fileId.trim()));
								fileInfoBean.setInfoId(bean.getTeacherId());
								fileInfoBusiness.updateFileInfo(fileInfoBean);
							}
						}
					} // 处理附件结束

					trBean.setTeacherId(result);
					if (StringKit.isValid(adminUserInfoBean.getRegion())) {
						trBean.setProvince(adminUserInfoBean.getProvince());
						trBean.setCity(adminUserInfoBean.getCity());
						trBean.setRegion(adminUserInfoBean.getRegion());
						trBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
					} else if (StringKit.isValid(adminUserInfoBean.getCity())) {
						trBean.setCity(adminUserInfoBean.getCity());
						trBean.setProvince(adminUserInfoBean.getProvince());
						trBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
					} else if (StringKit.isValid(adminUserInfoBean.getProvince())) {
						trBean.setProvince(adminUserInfoBean.getProvince());
						trBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
					} else {
						trBean.setProvince(DeclareAdminConfig.COUNTRY_STR);
						trBean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
					}
					// 增加教师地区信息
					result = trBusiness.addTeacherRegionInfo(trBean);
				}
			}
			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TeacherInfoList.do?teacherRange=2");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}
}