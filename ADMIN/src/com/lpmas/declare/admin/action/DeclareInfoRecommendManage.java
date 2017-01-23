package com.lpmas.declare.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.bean.AdminUserInfoBean;
import com.lpmas.declare.admin.business.AdminUserHelper;
import com.lpmas.declare.admin.business.AdminUserInfoBusiness;
import com.lpmas.declare.admin.business.DeclareImageBusiness;
import com.lpmas.declare.admin.business.DeclareInfoBusiness;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.FarmerContactInfoBusiness;
import com.lpmas.declare.admin.business.FarmerIndustryInfoBusiness;
import com.lpmas.declare.admin.business.FarmerInfoBusiness;
import com.lpmas.declare.admin.business.FarmerJobInfoBusiness;
import com.lpmas.declare.admin.business.FarmerSkillInfoBusiness;
import com.lpmas.declare.admin.business.IndustryTypeBusiness;
import com.lpmas.declare.admin.business.JobTypeBusiness;
import com.lpmas.declare.admin.business.NationalCertificationBusiness;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.config.DeclareAdminResource;
import com.lpmas.declare.admin.config.OperationConfig;
import com.lpmas.declare.admin.handler.DeclareReportHandler;
import com.lpmas.declare.bean.DeclareImageBean;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.FarmerContactInfoBean;
import com.lpmas.declare.bean.FarmerIndustryInfoBean;
import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.bean.FarmerJobInfoBean;
import com.lpmas.declare.bean.FarmerSkillInfoBean;
import com.lpmas.declare.bean.IndustryTypeBean;
import com.lpmas.declare.bean.JobTypeBean;
import com.lpmas.declare.bean.NationalCertificationBean;
import com.lpmas.declare.config.DeclareImageInfoConfig;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.BeanKit;
import com.lpmas.framework.util.ListKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;
import com.lpmas.framework.web.ParamKit;
import com.lpmas.framework.web.ReturnMessageBean;

@WebServlet("/declare/admin/DeclareInfoRecommendManage.do")
public class DeclareInfoRecommendManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DeclareInfoRecommendManage.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclareInfoRecommendManage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND, OperationConfig.UPDATE)) {
			return;
		}
		int declareId = ParamKit.getIntParameter(request, "declareId", 0);
		if (declareId == 0) {
			HttpResponseKit.alertMessage(response, "申报ID缺失!", HttpResponseKit.ACTION_HISTORY_BACK);
			return;
		}
		// 查出对应的BEAN
		DeclareReportBusiness business = new DeclareReportBusiness();
		DeclareReportBean declareReportBean;
		try {
			declareReportBean = business.getDeclareReportByKey(String.valueOf(declareId));
			AdminUserInfoBusiness adminUserInfoBusiness = new AdminUserInfoBusiness();
			AdminUserInfoBean adminUserInfoBean = adminUserInfoBusiness.getAdminUserInfoByKey(declareReportBean.getCreateUser());
			if (adminUserInfoBean != null) {
				if (StringKit.isValid(adminUserInfoBean.getProvince())) {
					request.setAttribute("FixProvince", adminUserInfoBean.getProvince());
				}
				if (StringKit.isValid(adminUserInfoBean.getCity())) {
					request.setAttribute("FixCity", adminUserInfoBean.getCity());
				}
				if (StringKit.isValid(adminUserInfoBean.getRegion())) {
					request.setAttribute("FixRegion", adminUserInfoBean.getRegion());
				}
			}
			// 放到页面
			request.setAttribute("DeclareReportBean", declareReportBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 图片
		DeclareImageBusiness declareImageBusiness = new DeclareImageBusiness();
		DeclareImageBean imageBean = declareImageBusiness.getDeclareImageByKey(declareId, DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
		if (imageBean == null) {
			imageBean = new DeclareImageBean();
		}
		request.setAttribute("DeclareImageBean", imageBean);
		// 获取产业类型列表
		IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
		List<IndustryTypeBean> industryTypeList = industryTypeBusiness.getIndustryTypeAllList();
		request.setAttribute("IndustryTypeList", industryTypeList);

		// 获取从业工种
		JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
		List<JobTypeBean> jobTypeList = jobTypeBusiness.getJobTypeAllList();
		request.setAttribute("JobTypeList", jobTypeList);

		// 获取国家资格证书数据
		NationalCertificationBusiness nationalCertificationBusiness = new NationalCertificationBusiness();
		List<NationalCertificationBean> ncList = nationalCertificationBusiness.getNationalCertificationAllList();
		request.setAttribute("NationalCertificationList", ncList);
		Map<Integer, String> nationalCertificationMap = ListKit.list2Map(ncList, "certificateId", "certificateName");
		request.setAttribute("NationalCertificationMap", nationalCertificationMap);

		// 请求转发
		request.setAttribute("AdminUserHelper", adminHelper);
		RequestDispatcher rd = request.getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "DeclareInfoRecommendManage.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserHelper adminHelper = new AdminUserHelper(request, response);
		if (!adminHelper.checkPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND, OperationConfig.UPDATE)) {
			return;
		}
		ReturnMessageBean messageBean = new ReturnMessageBean();
		int resultDeclareInfo = 0;
		int resultFarmerInfo = 0;
		int resultFarmerContactInfo = 0;
		int resultFarmerJobInfo = 0;
		int resultFarmerSkillInfo = 0;
		int resultFarmerIndustryInfo = 0;
		int resultMongo = 0;
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
		FarmerContactInfoBusiness farmerContactInfoBusiness = new FarmerContactInfoBusiness();
		FarmerJobInfoBusiness farmerJobInfoBusiness = new FarmerJobInfoBusiness();
		FarmerSkillInfoBusiness farmerSkillInfoBusiness = new FarmerSkillInfoBusiness();
		FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();
		DeclareImageBusiness declareImageBusiness = new DeclareImageBusiness();
		DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
		// 映射Bean
		DeclareInfoBean declareInfoBean = BeanKit.request2Bean(request, DeclareInfoBean.class);
		FarmerInfoBean farmerInfoBean = BeanKit.request2Bean(request, FarmerInfoBean.class);
		DeclareImageBean imageBean = BeanKit.request2Bean(request, DeclareImageBean.class);
		FarmerContactInfoBean farmerContactInfoBean = BeanKit.request2Bean(request, FarmerContactInfoBean.class);
		FarmerJobInfoBean farmerJobInfoBean = BeanKit.request2Bean(request, FarmerJobInfoBean.class);
		FarmerSkillInfoBean farmerSkillInfoBean = BeanKit.request2Bean(request, FarmerSkillInfoBean.class);
		FarmerIndustryInfoBean farmerIndustryInfoBean = BeanKit.request2Bean(request, FarmerIndustryInfoBean.class);
		if (declareInfoBean.getDeclareId() <= 0) {
			messageBean.setMessage("申报ID不合法");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}
		// 根据申报ID查询申报信息
		DeclareInfoBean originalDeclareInfoBean = declareInfoBusiness.getDeclareInfoByKey(declareInfoBean.getDeclareId());

		// 检验是否已经是提交状态
		if (DeclareInfoConfig.DECLARE_STATUS_SUBMIT.equals(originalDeclareInfoBean.getDeclareStatus())
				|| DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE.equals(originalDeclareInfoBean.getDeclareStatus())
				|| DeclareInfoConfig.DECLARE_STATUS_APPROVE.equals(originalDeclareInfoBean.getDeclareStatus())) {
			messageBean.setMessage("已经是提交状态，不能修改!");
			HttpResponseKit.printJson(request, response, messageBean, "");
			return;
		}
		// 比较身份证号是否改变
		FarmerInfoBean originalFarmerInfoBean = farmerInfoBusiness.getFarmerInfoByKey(declareInfoBean.getDeclareId());
		if (!originalFarmerInfoBean.getIdentityNumber().equals(farmerInfoBean.getIdentityNumber())) {
			HashMap<String, String> condMap = new HashMap<String, String>();
			condMap.put("identityNumber", farmerInfoBean.getIdentityNumber());
			condMap.put("status", String.valueOf(Constants.STATUS_VALID));
			try {
				List<DeclareReportBean> declareReportList = declareReportBusiness.getDeclareReportListByMap(condMap,
						new HashMap<String, List<String>>());
				if (declareReportList != null && !declareReportList.isEmpty() && Math
						.abs(Integer.valueOf(declareInfoBean.getDeclareYear()) - Integer.valueOf(declareReportList.get(0).getDeclareYear())) < 3) {
					messageBean.setMessage("同一身份证三年内不能重复申报");
					HttpResponseKit.printJson(request, response, messageBean, "");
					return;
				}
			} catch (Exception e) {
				messageBean.setMessage("处理失败");
				HttpResponseKit.printJson(request, response, messageBean, "");
				return;
			}
		}

		// 获取用户的userId
		int userId = declareInfoBusiness.getUserIdByUserClient(farmerContactInfoBean.getUserMobile());

		// 判断申报图片是否新建
		if (StringKit.isValid(imageBean.getImagePath())) {
			DeclareImageBean dbExistImageBean = declareImageBusiness.getDeclareImageByKey(declareInfoBean.getDeclareId(),
					DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
			if (dbExistImageBean != null) {
				// 修改图片信息
				imageBean.setModifyUser(adminHelper.getAdminUserId());
				declareImageBusiness.updateDeclareImage(imageBean);
			} else {
				// 新建图片信息
				imageBean.setImageType(DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
				imageBean.setStatus(Constants.STATUS_VALID);
				imageBean.setCreateUser(adminHelper.getAdminUserId());
				declareImageBusiness.addDeclareImage(imageBean);
			}
		}
		declareInfoBean.setModifyUser(adminHelper.getAdminUserId());
		declareInfoBean.setDeclareStatus(DeclareInfoConfig.DECLARE_STATUS_NOT_SUBMIT);
		declareInfoBean.setDeclareCategory(DeclareInfoConfig.DECLARE_CATEGORY_SPARE);
		declareInfoBean.setAuthStatus(DeclareInfoConfig.AUTH_STATUS_WAIT_APPROVE);
		declareInfoBean.setRegistryType(DeclareInfoConfig.REGISTRY_TYPE_RECOMMEND);
		declareInfoBean.setUserId(userId);
		declareInfoBean.setDeclareYear(originalDeclareInfoBean.getDeclareYear());
		declareInfoBean.setCreateUser(originalDeclareInfoBean.getCreateUser());
		resultDeclareInfo = declareInfoBusiness.updateDeclareInfo(declareInfoBean);
		resultFarmerInfo = farmerInfoBusiness.updateFarmerInfo(farmerInfoBean);
		resultFarmerContactInfo = farmerContactInfoBusiness.updateFarmerContactInfo(farmerContactInfoBean);
		resultFarmerJobInfo = farmerJobInfoBusiness.updateFarmerJobInfo(farmerJobInfoBean);
		resultFarmerSkillInfo = farmerSkillInfoBusiness.updateFarmerSkillInfo(farmerSkillInfoBean);
		resultFarmerIndustryInfo = farmerIndustryInfoBusiness.updateFarmerIndustryInfo(farmerIndustryInfoBean);
		// 插入mongo
		DeclareReportHandler handler = new DeclareReportHandler();
		try {
			handler.createDeclareReport(declareInfoBean);
			resultMongo = Constants.STATUS_VALID;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("总表记录创建失败", e);
		}
		if (resultMongo <= 0 || resultFarmerInfo < 0 || resultFarmerContactInfo < 0 || resultDeclareInfo < 0 || resultFarmerJobInfo < 0
				|| resultFarmerSkillInfo < 0 || resultFarmerIndustryInfo < 0) {
			messageBean.setMessage("操作失败");
		} else {
			messageBean.setCode(Constants.STATUS_VALID);
			messageBean.setMessage("操作成功");
		}
		HttpResponseKit.printJson(request, response, messageBean, "");
		return;
	}

}
