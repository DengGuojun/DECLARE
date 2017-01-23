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

import com.lpmas.declare.region.cache.RegionInfoCache;
import com.lpmas.declare.region.config.RegionConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.region.bean.RegionInfoBean;

/**
 * Servlet implementation class RegionListService
 */
@WebServlet("/m/RegionList.action")
public class RegionListService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RegionListService() {
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

		int cityId = ParamKit.getIntParameter(request, "cityId", 0);
		String cityName = ParamKit.getParameter(request, "cityName", "");

		ReturnMessageBean resultBean = new ReturnMessageBean();
		// 地区查询
		RegionInfoCache cache = new RegionInfoCache();
		List<RegionInfoBean> regionList = new ArrayList<RegionInfoBean>();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (cityId > 0) {
			regionList = cache.getRegionInfoListByCityId(cityId);
		} else if (StringKit.isValid(cityName)) {
			regionList = cache.getRegionInfoListByCityName(cityName);
		} else {
			resultBean.setCode(Constants.STATUS_NOT_VALID);
			resultBean.setMessage("InvalidArgument");
			HttpResponseKit.printJson(request, response, resultBean, RegionConfig.JSONP_CALLBACK);
			return;
		}

		resultMap.put("regionList", regionList);

		resultBean.setCommand(ParamKit.getParameter(request, "action", ""));
		resultBean.setCode(Constants.STATUS_VALID);
		resultBean.setContent(resultMap);
		HttpResponseKit.printJson(request, response, resultBean, RegionConfig.JSONP_CALLBACK);
		return;
	}

}
