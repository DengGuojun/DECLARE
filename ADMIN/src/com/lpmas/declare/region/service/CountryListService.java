package com.lpmas.declare.region.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.region.cache.CountryInfoCache;
import com.lpmas.declare.region.config.RegionConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.region.bean.CountryInfoBean;

/**
 * Servlet implementation class CountryListService
 */
@WebServlet("/m/CountryList.action")
public class CountryListService extends HttpServlet {

	private static final long serialVersionUID = 1L;
	/**
	 * Default constructor.
	 */
	public CountryListService() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		ReturnMessageBean resultBean = new ReturnMessageBean();
		// 国家查询
		CountryInfoCache cache = new CountryInfoCache();
		List<CountryInfoBean> countryList = cache.getCountryInfoAllList();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("countryList", countryList);

		resultBean.setCommand(ParamKit.getParameter(request, "action", ""));
		resultBean.setCode(Constants.STATUS_VALID);
		resultBean.setContent(resultMap);
		HttpResponseKit.printJson(request, response, resultBean, RegionConfig.JSONP_CALLBACK);
		return;
	}

}
