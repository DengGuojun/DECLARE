package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.migrate.bean.TabCityInfoBean;
import com.lpmas.declare.migrate.bean.TabProvinceInfoBean;
import com.lpmas.declare.migrate.bean.TabRegionBean;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.business.TabCityInfoBusiness;
import com.lpmas.declare.migrate.business.TabProvinceInfoBusiness;
import com.lpmas.declare.migrate.business.TabRegionBusiness;
import com.lpmas.declare.migrate.business.TabRegionInfoBusiness;
import com.lpmas.framework.excel.ExcelReadKit;
import com.lpmas.framework.excel.ExcelReadResultBean;
import com.lpmas.framework.web.HttpResponseKit;

/**
 * Servlet implementation class RegionInfoMigrate
 */
@WebServlet("/RegionInfoMigrate.action")
public class RegionInfoMigrate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegionInfoMigrate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TabRegionBusiness tabRegionBusiness = new TabRegionBusiness();
		TabRegionInfoBusiness tabRegionInfoBusiness = new TabRegionInfoBusiness();
		TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
		TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
		TabRegionBean tabRegionBean = new TabRegionBean();
		TabRegionInfoBean tabRegionInfoBean = new TabRegionInfoBean();
		TabCityInfoBean tabCityInfoBean = new TabCityInfoBean();
		TabProvinceInfoBean tabProvinceInfoBean = new TabProvinceInfoBean();
		int count = 0;
		String serverId = "";
		List<String> failList = new ArrayList<String>();
		// 读取EXCEL
		ExcelReadKit readKit = new ExcelReadKit();
		ExcelReadResultBean readResultBean = readKit.readExcel("f:/region.xlsx", 0);
		if (readResultBean.getResult()) {
			List<List<String>> content = readResultBean.getContentList();
			for (int i = 1; i < content.size(); i++) {
				try {
					int result = 0;
					List<String> rowContent = content.get(i);
					serverId = rowContent.get(0).trim();
					String[] serverIdArr = serverId.split("[.]");
					// 是省
					tabRegionBean = new TabRegionBean();
					tabRegionBean.setCode(String.valueOf(Double.valueOf(rowContent.get(1).trim()).intValue()));
					tabRegionBean.setServerId(serverId);
					result = tabRegionBusiness.addTabRegion(tabRegionBean);
					if (result > 0) {
						if (serverIdArr.length == 2) {
							tabProvinceInfoBean = new TabProvinceInfoBean();
							tabProvinceInfoBean.setCountryId(1);
							tabProvinceInfoBean.setProvinceCode(
									String.valueOf(Double.valueOf(rowContent.get(1).trim()).intValue()));
							tabProvinceInfoBean.setProvinceName(rowContent.get(2).trim());
							result = tabProvinceInfoBusiness.addTabProvinceInfo(tabProvinceInfoBean);
							if (result > 0)
								count++;
							else {
								failList.add(serverId);
							}
						} else if (serverIdArr.length == 3) {
							// 是市
							// 获取省
							tabRegionBean = tabRegionBusiness
									.getTabRegionByServerId(serverIdArr[0] + "." + serverIdArr[1]);
							tabProvinceInfoBean = tabProvinceInfoBusiness.getProvinceByCode(tabRegionBean.getCode());
							tabCityInfoBean = new TabCityInfoBean();
							tabCityInfoBean
									.setCityCode(String.valueOf(Double.valueOf(rowContent.get(1).trim()).intValue()));
							tabCityInfoBean.setCityName(rowContent.get(2).trim());
							tabCityInfoBean.setCountryId(1);
							tabCityInfoBean.setProvinceId(tabProvinceInfoBean.getProvinceId());
							result = tabCityInfoBusiness.addTabCityInfo(tabCityInfoBean);
							if (result > 0)
								count++;
							else {
								failList.add(serverId);
							}
						} else if (serverIdArr.length == 4) {
							// 是区
							// 获取省市
							tabRegionBean = tabRegionBusiness
									.getTabRegionByServerId(serverIdArr[0] + "." + serverIdArr[1]);
							tabProvinceInfoBean = tabProvinceInfoBusiness.getProvinceByCode(tabRegionBean.getCode());
							tabRegionBean = tabRegionBusiness.getTabRegionByServerId(
									serverIdArr[0] + "." + serverIdArr[1] + "." + serverIdArr[2]);
							tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
							tabRegionInfoBean = new TabRegionInfoBean();
							tabRegionInfoBean.setCityId(tabCityInfoBean.getCityId());
							tabRegionInfoBean.setCountryId(1);
							tabRegionInfoBean.setProvinceId(tabProvinceInfoBean.getProvinceId());
							tabRegionInfoBean
									.setRegionCode(String.valueOf(Double.valueOf(rowContent.get(1).trim()).intValue()));
							tabRegionInfoBean.setRegionName(rowContent.get(2).trim());
							result = tabRegionInfoBusiness.addTabRegionInfo(tabRegionInfoBean);
							if (result > 0)
								count++;
							else {
								failList.add(serverId);
							}
						}
					} else {
						failList.add(serverId);
					}
				} catch (Exception e) {
					failList.add(serverId);
					continue;
				}
			}
			String failMessage = "";
			for (String fail : failList) {
				failMessage += "||" + fail;
			}
			HttpResponseKit.alertMessage(response, "成功插入：" + count + "条,失败：" + failMessage,
					HttpResponseKit.ACTION_NONE);
			return;
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
