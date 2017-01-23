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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.bean.AnnouncementInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AnnouncementInfoBusiness;
import com.lpmas.declare.admin.business.FileInfoBusiness;
import com.lpmas.declare.admin.config.AnnouncementInfoConfig;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.FileInfoConfig;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.bean.FileInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class AnnouncementInfoManage
 */
@WebServlet("/declare/admin/AnnouncementInfoManage.do")
public class AnnouncementInfoManage extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(AnnouncementInfoManage.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnnouncementInfoManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		int announcementId = ParamKit.getIntParameter(request, "announcementId", 0);
		AnnouncementInfoBean bean = new AnnouncementInfoBean();
		List<FileInfoBean> attachList = new ArrayList<FileInfoBean>();
		AnnouncementInfoBusiness business = new AnnouncementInfoBusiness();
		if (announcementId > 0) {
			if (!adminHelper.checkPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.UPDATE)) {
				return;
			}
			bean = business.getAnnouncementInfoByKey(announcementId);
			if (bean.getCreateUser() != adminHelper.getAdminUserId()) {
				HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			// 获取附件信息
			FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("infoType", String.valueOf(FileInfoConfig.INFO_TYPE_ANNOUNCEMENT_ATTACH));
			condMap.put("infoId", String.valueOf(bean.getAnnouncementId()));
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			attachList = fileInfoBusiness.getFileInfoListByMap(condMap);
		} else {
			if (!adminHelper.checkPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.CREATE)) {
				return;
			}
			bean.setAnnouncementStatus(AnnouncementInfoConfig.ANNOUNCEMENT_STATUS_EDIT);
			bean.setStatus(Constants.STATUS_VALID);
		}

		// 获取当前用户的行政级别
		AdminUserInfoBean adminUserInfoBean = adminHelper.getAdminUserInfo();
		String adminUserLevel = adminUserInfoBean.getAdminUserLevel();
		
		//获取接收者列表
		List<Integer> receiverList = new ArrayList<Integer>();
		if(StringKit.isValid(bean.getReceiver())){
			receiverList = JsonKit.toBean(bean.getReceiver(), ArrayList.class);
		}
		request.setAttribute("ReceiverList", receiverList);
		
		request.setAttribute("AnnouncementInfo", bean);
		request.setAttribute("AttachList", attachList);
		request.setAttribute("AdminUserLevel", adminUserLevel);
		request.setAttribute("AdminUserHelper", adminHelper);

		RequestDispatcher rd = request
				.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "AnnouncementInfoManage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		AnnouncementInfoBean bean = new AnnouncementInfoBean();
		try {
			bean = BeanKit.request2Bean(request, AnnouncementInfoBean.class);
			if (bean.getAnnouncementId() > 0) {
				if (!adminHelper.checkPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.UPDATE)) {
					return;
				}
			} else {
				if (!adminHelper.checkPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.CREATE)) {
					return;
				}
			}

			String receiver = getRecevierRoleList(request, adminHelper);
			bean.setReceiver(receiver);

			AnnouncementInfoBusiness business = new AnnouncementInfoBusiness();
			int result = 0;
			if (bean.getAnnouncementId() > 0) {
				if (business.getAnnouncementInfoByKey(bean.getAnnouncementId()).getCreateUser() != adminHelper
						.getAdminUserId()) {
					HttpResponseKit.alertMessage(response, "参数错误", HttpResponseKit.ACTION_HISTORY_BACK);
					return;
				}
				bean.setModifyUser(adminHelper.getAdminUserId());
				result = business.updateAnnouncementInfo(bean);
			} else {
				bean.setCreateUser(adminHelper.getAdminUserId());
				result = business.addAnnouncementInfo(bean);
				bean.setAnnouncementId(result);
			}

			if (result > 0) {
				// 处理附件
				FileInfoBusiness fileInfoBusiness = new FileInfoBusiness();
				String fileIds = ParamKit.getParameter(request, "fileId");
				String files[] = fileIds.split(",");
				for (String fileId : files) {
					if (StringKit.isValid(fileId.trim())) {
						FileInfoBean fileInfoBean = fileInfoBusiness.getFileInfoByKey(Integer.valueOf(fileId.trim()));
						fileInfoBean.setInfoId(bean.getAnnouncementId());
						fileInfoBusiness.updateFileInfo(fileInfoBean);
					}
				}
				HttpResponseKit.alertMessage(response, "处理成功", "/declare/admin/AnnouncementInfoList.do?owner=true");
			} else {
				HttpResponseKit.alertMessage(response, "处理失败", HttpResponseKit.ACTION_HISTORY_BACK);
			}
		} catch (Exception e) {
			log.error("{}", e.toString());
		}
	}

	// 设置接收者
	private String getRecevierRoleList(HttpServletRequest request, AdminUserHelper adminHelper) {
		List<Integer> receiverRoleList = new ArrayList<Integer>();
		String countryUser = ParamKit.getParameter(request, "countryUser");
		String provinceUser = ParamKit.getParameter(request, "provinceUser");
		String cityUser = ParamKit.getParameter(request, "cityUser");
		String regionUser = ParamKit.getParameter(request, "regionUser");
		String luruProvinceUser = ParamKit.getParameter(request, "luruProvinceUser");
		String luruCityUser = ParamKit.getParameter(request, "luruCityUser");
		String luruRegionUser = ParamKit.getParameter(request, "luruRegionUser");
		String rendingProvinceUser = ParamKit.getParameter(request, "rendingProvinceUser");
		String rendingCityUser = ParamKit.getParameter(request, "rendingCityUser");
		String rendingRegionUser = ParamKit.getParameter(request, "rendingRegionUser");

		if (StringKit.isValid(countryUser)) {
			receiverRoleList.add(1);
		}
		if (StringKit.isValid(provinceUser)) {
			receiverRoleList.add(2);
		}
		if (StringKit.isValid(cityUser)) {
			receiverRoleList.add(3);
		}
		if (StringKit.isValid(regionUser)) {
			receiverRoleList.add(4);
		}
		if (StringKit.isValid(luruProvinceUser)) {
			receiverRoleList.add(5);
		}
		if (StringKit.isValid(luruCityUser)) {
			receiverRoleList.add(6);
		}
		if (StringKit.isValid(luruRegionUser)) {
			receiverRoleList.add(7);
		}
		if (StringKit.isValid(rendingProvinceUser)) {
			receiverRoleList.add(8);
		}
		if (StringKit.isValid(rendingCityUser)) {
			receiverRoleList.add(9);
		}
		if (StringKit.isValid(rendingRegionUser)) {
			receiverRoleList.add(10);
		}
		return JsonKit.toJson(receiverRoleList);
	}

}
