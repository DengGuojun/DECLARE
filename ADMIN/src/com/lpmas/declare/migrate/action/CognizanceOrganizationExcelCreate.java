package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.TrainingOrganizationConfig;
import com.lpmas.declare.migrate.util.ExcelHandler;
import com.lpmas.framework.excel.ExcelConfig;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.excel.ExcelWriteBean;
import com.lpmas.framework.excel.WebExcelWriteKit;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.util.StringKit;

/**
 * Servlet implementation class CognizanceOrganizationExcelCreate
 */
@WebServlet("/CognizanceOrganizationExcelCreate.do")
public class CognizanceOrganizationExcelCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CognizanceOrganizationExcelCreate() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 认证机构 excel生成
//		ExcelReadKit excelReadKit = new ExcelReadKit();
//		ExcelReadResultBean bean = excelReadKit.readExcel(com.lpmas.declare.migrate.config. , 0);
//
//		ExcelWriteBean excelWriteBean = new ExcelWriteBean();
//
//		List<List<Object>> majorInfoContentLineList = new ArrayList<List<Object>>();
//		List<Object> majorInfoContentRowList = new ArrayList<Object>();
//		List<String> majorInfoHeaderList = new ArrayList<String>();
//
//		majorInfoHeaderList.add("organization_id");
//		majorInfoHeaderList.add("organization_name");
//		majorInfoHeaderList.add("organization_type");
//		majorInfoHeaderList.add("training_year");
//		majorInfoHeaderList.add("organization_category");
//		majorInfoHeaderList.add("organization_level");
//		majorInfoHeaderList.add("province");
//		majorInfoHeaderList.add("city");
//		majorInfoHeaderList.add("region");
//		majorInfoHeaderList.add("representative_name");
//		majorInfoHeaderList.add("telephone");
//		majorInfoHeaderList.add("mobile");
//		majorInfoHeaderList.add("address");
//		majorInfoHeaderList.add("zip_code");
//		majorInfoHeaderList.add("status");
//		majorInfoHeaderList.add("create_time");
//		majorInfoHeaderList.add("create_user");
//		majorInfoHeaderList.add("modify_time");
//		majorInfoHeaderList.add("modify_user");
//		majorInfoHeaderList.add("memo");
//		List<List<String>> lists = bean.getContentList();
//		int i = 0;
//		Map<String, String> regionMap = new HashMap<String, String>();
//		Map<String, String> regionInfoMap = new HashMap<String, String>();
//		Map<String, String> indexMap = new HashMap<String, String>();
//
//		regionMap = ExcelHandler.getRegionMap();
//		regionInfoMap = ExcelHandler.getFullCaptionMap();
//		indexMap = getIndexType();
//
//		for (List<String> list : lists) {
//			String regionId = list.get(1);
//			String serverId = regionMap.get(regionId);
//			String fullCaption = regionInfoMap.get(serverId);
//
//			List<String> regionList = regionInfomation(fullCaption);
//
//			majorInfoContentRowList = new ArrayList<Object>();
//
//			majorInfoContentRowList.add(++i);
//			majorInfoContentRowList.add(list.get(2));
//			majorInfoContentRowList.add(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE);
//			majorInfoContentRowList.add(list.get(9));
//			String type = MapKit.getValueFromMap(list.get(3), indexMap);
//			majorInfoContentRowList.add(ExcelHandler.changeType(type));
//			if (regionList.size() == 4) {
//				if (StringKit.isValid(regionList.get(3))) {
//					majorInfoContentRowList.add(DeclareAdminConfig.ADMIN_LEVEL_REGION);
//				} else if (StringKit.isValid(regionList.get(2))) {
//					majorInfoContentRowList.add(DeclareAdminConfig.ADMIN_LEVEL_CITY);
//				} else if (StringKit.isValid(regionList.get(1))) {
//					majorInfoContentRowList.add(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
//				} else {
//					majorInfoContentRowList.add("");
//				}
//				majorInfoContentRowList.add(regionList.get(1));
//				majorInfoContentRowList.add(regionList.get(2));
//				majorInfoContentRowList.add(regionList.get(3));
//			} else {
//				majorInfoContentRowList.add("");
//				majorInfoContentRowList.add("");
//				majorInfoContentRowList.add("");
//				majorInfoContentRowList.add("");
//			}
//			majorInfoContentRowList.add(list.get(4));
//			majorInfoContentRowList.add(list.get(5)); // 手机
//			majorInfoContentRowList.add("");
//			majorInfoContentRowList.add(list.get(6)); // 地址
//			majorInfoContentRowList.add(list.get(7)); // 邮政编码
//			majorInfoContentRowList.add(list.get(8)); // 状态
//			majorInfoContentRowList.add("");
//			majorInfoContentRowList.add("");
//			majorInfoContentRowList.add("");
//			majorInfoContentRowList.add("");
//			majorInfoContentRowList.add(list.get(0));
//			majorInfoContentLineList.add(majorInfoContentRowList);
		}

//		String fileType = ExcelConfig.FT_XLSX; // 设置文件后缀
//		excelWriteBean.setFileName("认证机构");
//		excelWriteBean.setFileType(fileType);
//		excelWriteBean.setSheetName("AuthizedOrganization");
//		excelWriteBean.setHeaderList(majorInfoHeaderList);
//		excelWriteBean.setContentList(majorInfoContentLineList);
//		WebExcelWriteKit webExcelWriteKit = new WebExcelWriteKit();
//		webExcelWriteKit.outputExcel(excelWriteBean, request, response);
 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * tab_index索引
	 * 
	 * @return
	 */
//	public Map<String, String> getIndexType() {
//		ExcelReadKit excelReadKit = new ExcelReadKit();
//		ExcelReadResultBean bean = excelReadKit.readExcel(com.lpmas.declare.migrate.config.ExcelConfig.tabIndexFile, 0);
//
//		Map<String, String> map = new HashMap<String, String>();
//		List<List<String>> lists = bean.getContentList();
//
//		for (List<String> list : lists) {
//			map.put(list.get(0), list.get(1));
//		}
//		return map;
//	}
}
	 


