package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.DeclareStatisticsBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.web.HttpResponseKit;

@WebServlet("/declare/admin/DeclareStatisticsInit.do")
public class DeclareStatisticsInit extends HttpServlet {
	private static Logger log = LoggerFactory.getLogger(DeclareStatisticsInit.class);
	private static final long serialVersionUID = 1L;

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
		try {
			long startTime = System.currentTimeMillis(); // 获取开始时间
			AdminUserHelper adminUserHelper = new AdminUserHelper(request);

			if (adminUserHelper.getAdminUserInfo().getAdminUserType() != DeclareAdminConfig.ADMIN_TYPE_ADMIN) {
				HttpResponseKit.alertMessage(response, "您没有权限,请重新操作!", HttpResponseKit.ACTION_HISTORY_BACK);
				return;
			}
			DeclareStatisticsBusiness business = new DeclareStatisticsBusiness();
			log.info("DeclareStatistics start");
			List<Integer> yearList = new ArrayList<>();
			yearList.add(2016);
			business.initDeclareStatistics(yearList);
			log.info("DeclareStatistics finish");
			long endTime = System.currentTimeMillis(); // 获取结束时间
			System.out.println(yearList + " 的申报情况统计完毕");
			System.out.println("程序运行时间： " + (endTime - startTime) / 1000 + "s");
			System.out.println("程序运行时间： " + (endTime - startTime) / 1000 / 60 + "m");
			System.out.println("程序运行时间： " + (endTime - startTime) / 1000 / 60 / 60 + "h");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
