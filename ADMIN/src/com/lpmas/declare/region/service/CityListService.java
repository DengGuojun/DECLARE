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

import com.lpmas.declare.region.cache.CityInfoCache;
import com.lpmas.declare.region.cache.CountryInfoCache;
import com.lpmas.declare.region.cache.ProvinceInfoCache;
import com.lpmas.declare.region.config.RegionConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.region.bean.CityInfoBean;
import com.lpmas.region.bean.CountryInfoBean;
import com.lpmas.region.bean.ProvinceInfoBean;

/**
 * Servlet implementation class CityListService
 */
@WebServlet("/m/CityList.action")
public class CityListService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CityListService() {
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

		int provinceId = ParamKit.getIntParameter(request, "provinceId", 0);
		String provinceName = ParamKit.getParameter(request, "provinceName", "");

		ReturnMessageBean resultBean = new ReturnMessageBean();
		// 城市查询
		CityInfoCache cache = new CityInfoCache();
		List<CityInfoBean> cityList = new ArrayList<CityInfoBean>();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (provinceId > 0) {
			cityList = cache.getCityInfoListByProvinceId(provinceId);
		} else if (StringKit.isValid(provinceName)) {
			cityList = cache.getCityInfoListByProvinceName(provinceName);
		} else {
			CountryInfoCache countryInfoCache = new CountryInfoCache();
			List<CountryInfoBean> countryList = countryInfoCache.getCountryInfoAllList();
			if(countryList!=null && countryList.size()>0 ){
				ProvinceInfoCache provinceInfoCache = new ProvinceInfoCache();
				List<ProvinceInfoBean> provinceList = provinceInfoCache
						.getProvinceInfoListByCountryId(countryList.get(0).getCountryId());
				for (ProvinceInfoBean provinceInfoBean : provinceList) {
					List<CityInfoBean> cityPartList = cache.getCityInfoListByProvinceId(provinceInfoBean.getProvinceId());
					cityList.addAll(cityPartList);
				}
			}
		}

		resultMap.put("cityList", cityList);

		resultBean.setCommand(ParamKit.getParameter(request, "action", ""));
		resultBean.setCode(Constants.STATUS_VALID);
		resultBean.setContent(resultMap);
		HttpResponseKit.printJson(request, response, resultBean, RegionConfig.JSONP_CALLBACK);
		return;
	}

}
