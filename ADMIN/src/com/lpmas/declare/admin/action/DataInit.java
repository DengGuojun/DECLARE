package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.bean.AdminUserGroupBean;
import com.lpmas.declare.admin.business.AdminUserGroupBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.region.bean.CityInfoBean;
import com.lpmas.region.bean.ProvinceInfoBean;
import com.lpmas.region.bean.RegionInfoBean;
import com.lpmas.region.client.RegionServiceClient;

/**
 * Servlet implementation class AdminLogon
 */
@WebServlet("/init.do")
public class DataInit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataInit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserGroupBusiness ugBusiness = new AdminUserGroupBusiness();
		RegionServiceClient client = new RegionServiceClient();
		List<ProvinceInfoBean> list = client.getProvinceInfoListByCountryCode("086");
		for (ProvinceInfoBean provinceBean : list) {
			String province = provinceBean.getProvinceName();
			AdminUserGroupBean group = new AdminUserGroupBean();
			group.setAdminGroupLevel(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE);
			group.setGroupName(province);
			group.setProvince(province);
			group.setStatus(Constants.STATUS_VALID);
			ugBusiness.addAdminUserGroup(group);
			List<CityInfoBean> cityList = client.getCityInfoListByProvinceId(provinceBean.getProvinceId());
			for (CityInfoBean cityBean : cityList) {
				String city = cityBean.getCityName();
				group = new AdminUserGroupBean();
				group.setAdminGroupLevel(DeclareAdminConfig.ADMIN_LEVEL_CITY);
				group.setGroupName(province + city);
				group.setProvince(province);
				group.setCity(city);
				group.setStatus(Constants.STATUS_VALID);
				ugBusiness.addAdminUserGroup(group);
				List<RegionInfoBean> regionList = client.getRegionInfoListByCityId(cityBean.getCityId());
				for (RegionInfoBean regionBean : regionList) {
					String region = regionBean.getRegionName();
					group = new AdminUserGroupBean();
					group.setAdminGroupLevel(DeclareAdminConfig.ADMIN_LEVEL_REGION);
					group.setGroupName(province + city + region);
					group.setProvince(province);
					group.setCity(city);
					group.setRegion(region);
					group.setStatus(Constants.STATUS_VALID);
					ugBusiness.addAdminUserGroup(group);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

}
