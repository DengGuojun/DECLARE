package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.TeacherRegionInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TeacherRegionInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TeacherRemove
 */
@WebServlet("/declare/admin/TeacherRegionInfoManage.do")
public class TeacherRegionInfoManage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherRegionInfoManage() {
		super();
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
		try {

			int teacherId = ParamKit.getIntParameter(request, "teacherId", 0);
			if (teacherId == 0) {
				HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			int action = ParamKit.getIntParameter(request, "action", 0);// 0 退出本级别 1加入本级别
			int teacherRange = ParamKit.getIntParameter(request, "teacherRange", 0);
			
			AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
			AdminUserInfoBean adminUserInfoBean = adminUserHelper.getAdminUserInfo();
			TeacherRegionInfoBusiness business = new TeacherRegionInfoBusiness();
			int result = 0;
			HashMap<String, String> condMap = new HashMap<String, String>();

			if (action == 0) {
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL,
						OperationConfig.REMOVE)) {
					return;
				}
				// 本级师资才可以退出
				if (adminUserInfoBean != null) {
					if (StringKit.isValid(adminUserInfoBean.getRegion())) {
						condMap.put("region", adminUserInfoBean.getRegion());
						condMap.put("city", adminUserInfoBean.getCity());
						condMap.put("province", adminUserInfoBean.getProvince());
						condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_REGION);
					} else if (StringKit.isValid(adminUserInfoBean.getCity())) {
						condMap.put("city", adminUserInfoBean.getCity());
						condMap.put("province", adminUserInfoBean.getProvince());
						condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
					} else if (StringKit.isValid(adminUserInfoBean.getProvince())) {
						condMap.put("province", adminUserInfoBean.getProvince());
						condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
					} else {
						condMap.put("country", DeclareAdminConfig.COUNTRY_STR);
						condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
					}
				}
				condMap.put("teacherId", String.valueOf(teacherId));
				List<TeacherRegionInfoBean> list = business.getTeacherRegionInfoPageListByMap(condMap);
				if (list.size() > 0) { 
					// 教师地区信息存在
					int infoId = list.get(0).getInfoId();
					result = business.removeTeacherRegionInfo(infoId);
				}
			} else if (action == 1) {
				// 加入本级别
				if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.CREATE)) {
					return; 
				}
				TeacherRegionInfoBean bean = new TeacherRegionInfoBean();

				if (adminUserInfoBean != null) {
					if (StringKit.isValid(adminUserInfoBean.getRegion())) {
						condMap.put("region", adminUserInfoBean.getRegion());
						condMap.put("city", adminUserInfoBean.getCity());
						condMap.put("province", adminUserInfoBean.getProvince());
						condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_REGION);
						bean.setRegion(adminUserInfoBean.getRegion());
						bean.setCity(adminUserInfoBean.getCity());
						bean.setProvince(adminUserInfoBean.getProvince());
						bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
					} else if (StringKit.isValid(adminUserInfoBean.getCity())) {
						condMap.put("city", adminUserInfoBean.getCity());
						condMap.put("province", adminUserInfoBean.getProvince());
						condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_CITY);
						bean.setCity(adminUserInfoBean.getCity());
						bean.setProvince(adminUserInfoBean.getProvince());
						bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
					} else if (StringKit.isValid(adminUserInfoBean.getProvince())) {
						condMap.put("province", adminUserInfoBean.getProvince());
						condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
						bean.setProvince(adminUserInfoBean.getProvince());
						bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
					} else {
						condMap.put("country", DeclareAdminConfig.COUNTRY_STR);
						condMap.put("level", DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
						bean.setProvince(DeclareAdminConfig.COUNTRY_STR);
						bean.setLevel(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY);
					}
				}
				condMap.put("teacherId",  String.valueOf(teacherId));
				List<TeacherRegionInfoBean> list = business.getTeacherRegionInfoPageListByMap(condMap);

				if (list.size() > 0) { // 存在
					HttpResponseKit.alertMessage(response, "该教师已经在当前级别,处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				} else {
					if (teacherId > 0) {
						bean.setTeacherId(teacherId);
						result = business.addTeacherRegionInfo(bean);
					}
				}
			}
			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TeacherInfoList.do?teacherRange="
						+ teacherRange);
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", "/declare/admin/TeacherInfoList.do?teacherRange="
						+ teacherRange);
			}
		} catch (Exception e) {
			HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
		}
	}
}
