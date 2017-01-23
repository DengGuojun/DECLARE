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
import com.lpmas.declare.admin.bean.TeacherInfoBean;
import com.lpmas.declare.admin.bean.TeacherRegionInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.business.TeacherDeclareBusiness;
import com.lpmas.declare.admin.business.TeacherInfoBusiness;
import com.lpmas.declare.admin.business.TeacherRegionInfoBusiness;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.declare.bean.TeacherDeclareBean;
import com.lpmas.declare.config.TeacherInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class TeacherDeclareApprove
 */
@WebServlet("/declare/admin/TeacherDeclareApprove.do")
public class TeacherDeclareApprove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherDeclareApprove() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminUserHelper = new AdminUserHelper(request, response);
		AdminUserInfoBean adminUserInfoBean = adminUserHelper.getAdminUserInfo();
		Integer declareId = ParamKit.getIntParameter(request, "declareId", 0);
		String operation = ParamKit.getParameter(request, "operation", "");
		if (!adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.CREATE)) {
			return;
		}

		TeacherDeclareBusiness declareBusiness = new TeacherDeclareBusiness();
		TeacherDeclareBean declareBean = declareBusiness.getTeacherDeclareByKey(declareId);
		if (declareBean == null || !TeacherInfoConfig.APPROVE_OPERATION_MAP.containsKey(operation)) {
			HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		TeacherInfoBusiness teacherInfoBusiness = new TeacherInfoBusiness();
		TeacherRegionInfoBusiness trBusiness = new TeacherRegionInfoBusiness();
		if (operation.equals(TeacherInfoConfig.APPROVE_OPERATION_PASS)
				&& declareBean.getDeclareStatus().equals(TeacherInfoConfig.DECLATE_STATUS_COMMIT)) {
			// 通过审核
			// 存入师资库
			TeacherInfoBean teacherInfoBean = new TeacherInfoBean();
			BeanKit.copyBean(teacherInfoBean, declareBean);
			teacherInfoBean.setCreateUser(adminUserHelper.getAdminUserId());
			int result = teacherInfoBusiness.addTeacherInfo(teacherInfoBean);
			if (result > 0) {
				// 处理附件
				FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
				HashMap<String, String> condMap = new HashMap<String, String>();
				condMap.put("infoId", String.valueOf(declareBean.getDeclareId()));
				condMap.put("infoType", String.valueOf(FileInfoConfig.INFO_TYPE_TEACHER_DECLARE_ATTACH));
				condMap.put("status", String.valueOf(Constants.STATUS_VALID));
				List<FileInfoBean> attachList = fileInfoBusiness.getFileInfoListByMap(condMap);
				for (FileInfoBean fileInfoBean : attachList) {
					FileInfoBean teacherFileInfoBean = new FileInfoBean();
					BeanKit.copyBean(teacherFileInfoBean, fileInfoBean);
					teacherFileInfoBean.setInfoType(FileInfoConfig.INFO_TYPE_TEACHER_ATTACH);
					teacherFileInfoBean.setInfoId(result);
					fileInfoBusiness.addFileInfo(teacherFileInfoBean);
				}

				// 存入师资地区表
				TeacherRegionInfoBean trBean = new TeacherRegionInfoBean();
				trBean.setTeacherId(result);
				trBean.setLevel(adminUserInfoBean.getAdminUserLevel());
				trBean.setProvince(teacherInfoBean.getProvince());
				trBean.setCity(teacherInfoBean.getCity());
				trBean.setRegion(teacherInfoBean.getRegion());
				trBusiness.addTeacherRegionInfo(trBean);
				// 修改审核状态
				declareBean.setTeacherId(result);
				declareBean.setDeclareStatus(TeacherInfoConfig.DECLATE_STATUS_APPROVED);
				declareBusiness.updateTeacherDeclare(declareBean);
			}
		} else if (operation.equals(TeacherInfoConfig.APPROVE_OPERATION_FAIL)
				&& declareBean.getDeclareStatus().equals(TeacherInfoConfig.DECLATE_STATUS_COMMIT)) {
			// 不通过审核
			declareBean.setDeclareStatus(TeacherInfoConfig.DECLATE_STATUS_FAIL);
			declareBusiness.updateTeacherDeclare(declareBean);
		} else if (operation.equals(TeacherInfoConfig.APPROVE_OPERATION_REJECT)
				&& declareBean.getDeclareStatus().equals(TeacherInfoConfig.DECLATE_STATUS_APPROVED)) {
			// 审核驳回
			// 删除师资和师资地区信息
			TeacherInfoBean teacherInfoBean = teacherInfoBusiness.getTeacherInfoByKey(declareBean.getTeacherId());
			teacherInfoBean.setStatus(Constants.STATUS_NOT_VALID);
			teacherInfoBusiness.updateTeacherInfo(teacherInfoBean);
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("teacherId", String.valueOf(teacherInfoBean.getTeacherId()));
			List<TeacherRegionInfoBean> teacherRegionList = trBusiness.getTeacherRegionInfoPageListByMap(condMap);
			for (TeacherRegionInfoBean trBean : teacherRegionList) {
				trBusiness.removeTeacherRegionInfo(trBean.getInfoId());
			}
			declareBean.setDeclareStatus(TeacherInfoConfig.DECLATE_STATUS_REJECTED);
			declareBusiness.updateTeacherDeclare(declareBean);
		} else {
			HttpResponseKit.alertMessage(response, "审批动作与申报状态不一致", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}

		HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/TeacherDeclareList.do");

	}
}