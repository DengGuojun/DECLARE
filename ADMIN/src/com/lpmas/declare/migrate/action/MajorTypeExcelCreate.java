package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.lpmas.framework.util.StringKit;

/**
 * Servlet implementation class MajorTypeExcelCreat
 * @see 该方法已停止，请使用MajorAllImport.do请求
 */
@WebServlet("/MajorTypeExcelCreate.do")
public class MajorTypeExcelCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MajorTypeExcelCreate() {
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
//		// 专业类型的excel表格
//		ExcelReadResultBean bean = excelReadKit.readExcel(com.lpmas.declare.migrate.config.ExcelConfig.tabMajorFile, 0);
//
//		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
//
//		List<List<Object>> contentLineList = new ArrayList<List<Object>>();
//		List<Object> contentRowList = new ArrayList<Object>();
//		List<String> headerList = new ArrayList<String>();
//		headerList.add("major_id");
//		headerList.add("major_name");
//		headerList.add("major_level");
//		headerList.add("major_year");
//		headerList.add("province");
//		headerList.add("city");
//		headerList.add("region");
//		headerList.add("status");
//		headerList.add("create_time");
//		headerList.add("create_user");
//		headerList.add("modify_time");
//		headerList.add("modify_user");
//		headerList.add("memo");
//
//		List<List<String>> lists = bean.getContentList();
//		int i = 0;
//		for (List<String> list : lists) {
//			if (!StringKit.isValid(list.get(2))) { // 第三个位置是专业的父专业 如果为空 表示专业类型
//				contentRowList = new ArrayList<Object>();
//
//				String serverId = list.get(3);
//				if ("22.0".equals(serverId)) {
//					serverId = serverId.substring(0, serverId.length() - 2);
//				}
//
//				// 获取地区的id 获取对应的省市区
//				List<String> regionList = ExcelHandler.getRegionList(serverId);
//				contentRowList.add(++i);
//				contentRowList.add(list.get(1)); // 专业类型名字
//				
//				contentRowList.add(regionList.get(0));
//				contentRowList.add(list.get(5));
//				contentRowList.add(regionList.get(1));
//				contentRowList.add(regionList.get(2));
//				contentRowList.add(regionList.get(3));
//				contentRowList.add(list.get(4));
//				contentRowList.add("");
//				contentRowList.add("");
//				contentRowList.add("");
//				contentRowList.add("");
//				contentRowList.add(list.get(0));
//				contentLineList.add(contentRowList);
//			}
//		}
//		String fileType = ExcelConfig.FT_XLSX; // 设置文件后缀
//		excelWriteBean.setFileName("MajorType");
//		excelWriteBean.setFileType(fileType);
//		excelWriteBean.setSheetName("专业类型");
//		excelWriteBean.setHeaderList(headerList);
//		excelWriteBean.setContentList(contentLineList);
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
