package com.lpmas.declare.portal.action;

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

import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.declare.bean.TeacherMajorTypeBean;
import com.lpmas.declare.bean.TeacherDeclareBean;
import com.lpmas.declare.config.TeacherInfoConfig;
import com.lpmas.declare.portal.business.FileInfoBusiness;
import com.lpmas.declare.portal.business.TeacherMajorTypeBusiness;
import com.lpmas.declare.portal.business.TeacherDeclareBusiness;
import com.lpmas.declare.portal.config.DeclarePortalConfig;
import com.lpmas.declare.portal.config.FileInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.tools.portal.PortalKit;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.ow.passport.sso.business.SsoClientHelper;

/**
 * Servlet implementation class TeacherDeclareManage
 */
@WebServlet("/declare/TeacherDeclareManage.do")
public class TeacherDeclareManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(TeacherDeclareManage.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户Id
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();

		TeacherDeclareBusiness teacherDeclareBusiness = new TeacherDeclareBusiness();
		TeacherDeclareBean bean = teacherDeclareBusiness.getTeacherDeclareByUserId(userId);
		if (bean == null) {
			bean = new TeacherDeclareBean();
			bean.setStatus(Constants.STATUS_VALID);
		}
		// 获取附件信息
		List<FileInfoBean> attachList = new ArrayList<FileInfoBean>();
		if (bean != null) {
			FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("infoId", String.valueOf(bean.getDeclareId()));
			condMap.put("infoType", String.valueOf(FileInfoConfig.INFO_TYPE_TEACHER_DECLARE_ATTACH));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			attachList = fileInfoBusiness.getFileInfoListByMap(condMap);
		}

		TeacherMajorTypeBusiness teacherMajorTypeBusiness = new TeacherMajorTypeBusiness();
		// 获取MajorTypeList
		List<TeacherMajorTypeBean> teacherMajorTypeList = teacherMajorTypeBusiness.getTeacherMajorTypeAllList();
		request.setAttribute("TeacherMajorTypeList", teacherMajorTypeList);
		request.setAttribute("TeacherDeclareBean", bean);
		request.setAttribute("attachList", attachList);
		// 转发
		PortalKit.forwardPage(request, response, DeclarePortalConfig.PAGE_PATH + "TeacherDeclareManage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取用户ID
		SsoClientHelper helper = new SsoClientHelper(request, response, false);
		int userId = helper.getUserId();
		TeacherDeclareBean bean = new TeacherDeclareBean();
		try {
			bean = BeanKit.request2Bean(request, TeacherDeclareBean.class);
			if (bean.getDeclareStatus().equals(TeacherInfoConfig.DECLATE_STATUS_APPROVED)) {
				HttpResponseKit.alertMessage(response, "信息已经审核通过，不允许修改", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			bean.setDeclareStatus(TeacherInfoConfig.DECLATE_STATUS_COMMIT);
			bean.setUserId(userId);
			TeacherDeclareBusiness business = new TeacherDeclareBusiness();
			int result = 0;
			if (bean.getDeclareId() > 0) {
				bean.setModifyUser(userId);
				result = business.updateTeacherDeclare(bean);
			} else {
				bean.setCreateUser(userId);
				result = business.addTeacherDeclare(bean);
				if (result > 0) {
					bean.setDeclareId(result);
					// 处理相关材料
					FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
					String fileIds = ParamKit.getParameter(request, "fileId");
					String files[] = fileIds.split(",");
					for (String fileId : files) {
						if (StringKit.isValid(fileId.trim())) {
							FileInfoBean fileInfoBean = fileInfoBusiness
									.getFileInfoByKey(Integer.valueOf(fileId.trim()));
							fileInfoBean.setInfoId(bean.getDeclareId());
							fileInfoBusiness.updateFileInfo(fileInfoBean);
						}
					}
				} // 处理附件结束
			}

			if (result > 0) {
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/TeacherDeclareManage.do");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
