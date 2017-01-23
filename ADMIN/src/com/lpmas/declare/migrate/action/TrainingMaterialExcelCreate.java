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
import com.lpmas.declare.admin.config.TrainingMaterialConfig;
import com.lpmas.declare.migrate.util.ExcelHandler;
import com.lpmas.framework.excel.ExcelConfig;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;

/**
 * Servlet implementation class TrainingMaterialExcelCreate
 * @see 该方法已停止，请使用TrainingMaterialImport.do请求
 */
@WebServlet("/TrainingMaterialExcelCreate.do")
public class TrainingMaterialExcelCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TrainingMaterialExcelCreate() {
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
//
//		ExcelReadResultBean bean = excelReadKit.readExcel(com.lpmas.declare.migrate.config.ExcelConfig.trainingBookFile,
//				0);
//
//		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
//
//		List<List<Object>> majorInfoContentLineList = new ArrayList<List<Object>>();
//		List<Object> majorInfoContentRowList = new ArrayList<Object>();
//		List<String> majorInfoHeaderList = new ArrayList<String>();
//
//		majorInfoHeaderList.add("material_id");
//		majorInfoHeaderList.add("material_name");
//		majorInfoHeaderList.add("material_type");
//		majorInfoHeaderList.add("training_year");
//		majorInfoHeaderList.add("country");
//		majorInfoHeaderList.add("province");
//		majorInfoHeaderList.add("city");
//		majorInfoHeaderList.add("region");
//		majorInfoHeaderList.add("compile_organization");
//		majorInfoHeaderList.add("publishing_company");
//		majorInfoHeaderList.add("publishing_yeah");
//		majorInfoHeaderList.add("publishing_month");
//		majorInfoHeaderList.add("word_quantity");
//		majorInfoHeaderList.add("paper_quantity");
//		majorInfoHeaderList.add("price");
//		majorInfoHeaderList.add("industry");
//		majorInfoHeaderList.add("link");
//		majorInfoHeaderList.add("status");
//		majorInfoHeaderList.add("create_time");
//		majorInfoHeaderList.add("create_user");
//		majorInfoHeaderList.add("modify_time");
//		majorInfoHeaderList.add("modify_user");
//		majorInfoHeaderList.add("memo");
//		List<List<String>> lists = bean.getContentList();
//		int i = 0;
//
//		for (List<String> list : lists) {
//			String serverId = list.get(2);
//			if ("22.0".equals(serverId)) {
//				serverId = serverId.substring(0, serverId.length() - 2);
//			}
//
//			List<String> regionList = ExcelHandler.getRegionList(serverId);
//			majorInfoContentRowList = new ArrayList<Object>();
//			majorInfoContentRowList.add(++i);
//			majorInfoContentRowList.add(list.get(3)); // 教材名字
//			// 此处未确定 等待确认 是专业教材 还是普通教材
//			String type = "";
//			if (list.get(13).equals("1.0")) {
//				type = TrainingMaterialConfig.MATERIAL_PROFESSIONAL;
//			} else if (list.get(13).equals("2.0")) {
//				type = TrainingMaterialConfig.MATERIAL_GENERAL;
//			}
//			majorInfoContentRowList.add(type);
//			majorInfoContentRowList.add(list.get(1)); // 培训年份
//			majorInfoContentRowList.add(regionList.get(0));
//			majorInfoContentRowList.add(regionList.get(1));
//			majorInfoContentRowList.add(regionList.get(2));
//			majorInfoContentRowList.add(regionList.get(3));
//			majorInfoContentRowList.add(list.get(4)); // 编辑单位
//			majorInfoContentRowList.add(list.get(5)); // 出版社
//			majorInfoContentRowList.add(list.get(6)); // 出版年份
//			majorInfoContentRowList.add(list.get(7)); // 出版月
//			majorInfoContentRowList.add(list.get(8)); // 字数
//			majorInfoContentRowList.add(list.get(9)); // 张数
//			majorInfoContentRowList.add(list.get(10)); // 价格
//			majorInfoContentRowList.add(list.get(11)); // 产业
//			majorInfoContentRowList.add(list.get(12)); // 链接
//			majorInfoContentRowList.add("1"); // 状态
//			majorInfoContentRowList.add(list.get(list.size() - 2)); // 创建时间
//			majorInfoContentRowList.add(""); // 创建者
//			majorInfoContentRowList.add(list.get(list.size() - 1)); // 修改时间
//			majorInfoContentRowList.add(""); // 修改者
//			majorInfoContentRowList.add(list.get(0)); // 保存主键
//			majorInfoContentLineList.add(majorInfoContentRowList);
//		}
//
//		String fileType = ExcelConfig.FT_XLSX; // 设置文件后缀
//		excelWriteBean.setFileName("MaterialInfo");
//		excelWriteBean.setFileType(fileType);
//		excelWriteBean.setSheetName("教材");
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
