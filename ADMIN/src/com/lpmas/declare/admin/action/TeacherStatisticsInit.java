package com.lpmas.declare.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.TeacherStatisticsBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.web.HttpResponseKit;
	
/**
 * Servlet implementation class TeacherRegionInfoRestore
 */
@WebServlet("/declare/admin/TeacherStatisticsInit.do")
public class TeacherStatisticsInit extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(TeacherStatisticsInit.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherStatisticsInit() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			AdminUserHelper adminUserHelper = new AdminUserHelper(request);

			if (adminUserHelper.getAdminUserInfo().getAdminUserType() != DeclareAdminConfig.ADMIN_TYPE_ADMIN) {
				HttpResponseKit.alertMessage(response, "您没有权限,请重新操作!", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			TeacherStatisticsBusiness business = new TeacherStatisticsBusiness();
			log.info("TeacherStatistics start");
			business.initTeacherStatistics();
			log.info("TeacherStatistics finish");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
