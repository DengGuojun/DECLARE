package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.TeacherMajorInfoBusiness;
import com.lpmas.declare.bean.TeacherMajorInfoBean;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.web.ParamKit;

/**
 * Servlet implementation class MajorInfoJsonList
 * 教师专业的相关信息查询
 */
@WebServlet("/declare/admin/TeacherMajorInfoJsonList.do")
public class TeacherMajorInfoJsonList extends HttpServlet {
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
		int typeId = ParamKit.getIntParameter(request, "typeId", 0);
		TeacherMajorInfoBusiness business = new TeacherMajorInfoBusiness();
		List<TeacherMajorInfoBean> list = business.getMajorInfoListByTypeId(typeId);
		if (list.size() > 0) {
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html;charset=utf-8");
			Map<String, List<TeacherMajorInfoBean>> map = new HashMap<String, List<TeacherMajorInfoBean>>();
			map.put("result", list);
			String result = JsonKit.toJson(map);
			writer.write(result);
			writer.flush();
			writer.close();
		}
	}

}
