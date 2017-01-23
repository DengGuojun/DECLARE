package com.lpmas.declare.migrate.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.migrate.util.ExcelHandler;
import com.lpmas.framework.excel.ExcelConfig;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;

/**
 * Servlet implementation class MajorInfoExcelCreate
 * @see 该方法已停止，请使用MajorAllImport.do请求
 */
@WebServlet("/MajorInfoExcelCreate.do")
public class MajorInfoExcelCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MajorInfoExcelCreate() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("/declare/migrate/DataMigrationImport.do");
//		ExcelReadKit excelReadKit = new ExcelReadKit();
//		File file = new File(com.lpmas.declare.migrate.config.ExcelConfig.tabMajorFile) ;
//		if(!file.exists()){
//			
//		}
//		ExcelReadResultBean bean = excelReadKit.readExcel(com.lpmas.declare.migrate.config.ExcelConfig.tabMajorFile , 0);
//
//		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
//
//		List<List<Object>> majorInfoContentLineList = new ArrayList<List<Object>>();
//		List<Object> majorInfoContentRowList = new ArrayList<Object>();
//		List<String> majorInfoHeaderList = new ArrayList<String>();
//		majorInfoHeaderList.add("major_id");
//		majorInfoHeaderList.add("major_name");
//		majorInfoHeaderList.add("type_id");
//		majorInfoHeaderList.add("status");
//		majorInfoHeaderList.add("create_time");
//		majorInfoHeaderList.add("create_user");
//		majorInfoHeaderList.add("modify_time");
//		majorInfoHeaderList.add("modify_user");
//		majorInfoHeaderList.add("memo");
//
//		List<List<String>> lists = bean.getContentList();
//		int i = 0;
//
//		Map<String, String> majorTypeIdMap = ExcelHandler.getMajorTypeIdMap();
//		for (List<String> list : lists) {
//			if (StringKit.isValid(list.get(2))) { // 第三个位置是专业的父专业 如果为空 表示专业类型
//				majorInfoContentRowList = new ArrayList<Object>();
//				majorInfoContentRowList.add(++i);
//				majorInfoContentRowList.add(list.get(1));
//				majorInfoContentRowList.add(MapKit.getIntValueFromMap(list.get(2), majorTypeIdMap));
//				majorInfoContentRowList.add(list.get(4));
//				majorInfoContentRowList.add("");
//				majorInfoContentRowList.add("");
//				majorInfoContentRowList.add("");
//				majorInfoContentRowList.add("");
//				majorInfoContentRowList.add(list.get(0));
//				majorInfoContentLineList.add(majorInfoContentRowList);
//			}
//		}
//		String fileType = ExcelConfig.FT_XLSX; // 设置文件后缀
//		excelWriteBean.setFileName("MajorInfo");
//		excelWriteBean.setFileType(fileType);
//		excelWriteBean.setSheetName("专业");
//		excelWriteBean.setHeaderList(majorInfoHeaderList);
//		excelWriteBean.setContentList(majorInfoContentLineList);
//		WebExcelWriteKit webExcelWriteKit = new WebExcelWriteKit();
//		webExcelWriteKit.outputExcel(excelWriteBean, request, response);
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
