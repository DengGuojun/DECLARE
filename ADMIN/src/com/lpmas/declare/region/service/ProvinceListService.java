package com.lpmas.declare.region.service;

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

import com.lpmas.declare.region.cache.CountryInfoCache;
import com.lpmas.declare.region.cache.ProvinceInfoCache;
import com.lpmas.declare.region.config.RegionConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.region.bean.CountryInfoBean;
import com.lpmas.region.bean.ProvinceInfoBean;

/**
 * Servlet implementation class ProvinceListService
 */
@WebServlet("/m/ProvinceList.action")
public class ProvinceListService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ProvinceListService() {
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

		int countryId = ParamKit.getIntParameter(request, "countryId", 0);
		String countryName = ParamKit.getParameter(request, "countryName", "");

		ReturnMessageBean resultBean = new ReturnMessageBean();
		// 省份查询
		ProvinceInfoCache cache = new ProvinceInfoCache();
		List<ProvinceInfoBean> provinceList = new ArrayList<ProvinceInfoBean>();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (countryId > 0) {
			provinceList = cache.getProvinceInfoListByCountryId(countryId);
		} else if (StringKit.isValid(countryName)) {
			provinceList = cache.getProvinceInfoListByCountryName(countryName);
		} else {
			CountryInfoCache countryInfoCache = new CountryInfoCache();
			List<CountryInfoBean> countryList = countryInfoCache.getCountryInfoAllList();
			if(countryList!=null && countryList.size()>0 ){
				ProvinceInfoCache provinceInfoCache = new ProvinceInfoCache();
				List<ProvinceInfoBean> provincePartList = provinceInfoCache
						.getProvinceInfoListByCountryId(countryList.get(0).getCountryId());
				provinceList.addAll(provincePartList);
			}
		}

		resultMap.put("provinceList", provinceList);

		resultBean.setCommand(ParamKit.getParameter(request, "action", ""));
		resultBean.setCode(Constants.STATUS_VALID);
		resultBean.setContent(resultMap);
		HttpResponseKit.printJson(request, response, resultBean, RegionConfig.JSONP_CALLBACK);
		return;
	}

}
