package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.DeclareInfoBusiness;
import com.lpmas.declare.admin.business.FarmerContactInfoBusiness;
import com.lpmas.declare.admin.business.FarmerIndustryInfoBusiness;
import com.lpmas.declare.admin.business.FarmerInfoBusiness;
import com.lpmas.declare.admin.business.FarmerJobInfoBusiness;
import com.lpmas.declare.admin.business.FarmerSkillInfoBusiness;
import com.lpmas.declare.admin.business.IndustryInfoBusiness;
import com.lpmas.declare.admin.business.IndustryTypeBusiness;
import com.lpmas.declare.admin.business.JobInfoBusiness;
import com.lpmas.declare.admin.business.JobTypeBusiness;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.FarmerContactInfoBean;
import com.lpmas.declare.bean.FarmerIndustryInfoBean;
import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.bean.FarmerJobInfoBean;
import com.lpmas.declare.bean.FarmerSkillInfoBean;
import com.lpmas.declare.bean.IndustryInfoBean;
import com.lpmas.declare.bean.IndustryTypeBean;
import com.lpmas.declare.bean.JobInfoBean;
import com.lpmas.declare.bean.JobTypeBean;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.migrate.bean.PersonInfoBean;
import com.lpmas.declare.migrate.bean.PersonInfoMajor;
import com.lpmas.declare.migrate.bean.PersonInfoMajor1Bean;
import com.lpmas.declare.migrate.bean.PersonInfoMajor2Bean;
import com.lpmas.declare.migrate.bean.TabCityInfoBean;
import com.lpmas.declare.migrate.bean.TabProvinceInfoBean;
import com.lpmas.declare.migrate.bean.TabRegionBean;
import com.lpmas.declare.migrate.bean.TabRegionInfoBean;
import com.lpmas.declare.migrate.bean.TabTypeBean;
import com.lpmas.declare.migrate.business.PersonInfoBusiness;
import com.lpmas.declare.migrate.business.PersonInfoMajor1Business;
import com.lpmas.declare.migrate.business.PersonInfoMajor2Business;
import com.lpmas.declare.migrate.business.TabCityInfoBusiness;
import com.lpmas.declare.migrate.business.TabProvinceInfoBusiness;
import com.lpmas.declare.migrate.business.TabRegionBusiness;
import com.lpmas.declare.migrate.business.TabRegionInfoBusiness;
import com.lpmas.declare.migrate.business.TabTypeBusiness;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.util.StringKit;
import com.lpmas.framework.web.HttpResponseKit;

/**
 * Servlet implementation class FarmerInfoMigrate
 */
@WebServlet("/FarmerInfoMigrate.action")
public class FarmerInfoMigrate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FarmerInfoMigrate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * RequestDispatcher rd = request
		 * .getRequestDispatcher(DeclareAdminConfig.PAGE_PATH + "migrate/" +
		 * "FarmerInfoMigrate.jsp"); rd.forward(request, response);
		 */
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int endPage = 300;
		int pageSize = 500;
		int pageNum = 1;
		// 循环读取数据
		PageBean pageBean = new PageBean(pageNum, pageSize);

		PersonInfoBusiness personInfoBusiness = new PersonInfoBusiness();
		PersonInfoMajor1Business personInfoMajor1Business = new PersonInfoMajor1Business();
		PersonInfoMajor2Business personInfoMajor2Business = new PersonInfoMajor2Business();
		PersonInfoMajor1Bean personInfoMajor1Bean = null;
		PersonInfoMajor2Bean personInfoMajor2Bean = null;
		PersonInfoMajor major = null;
		PageResultBean<PersonInfoBean> pageResultBean = personInfoBusiness
				.getPersonInfoPageListByMap(new HashMap<String, String>(), pageBean);
		Map<String, String> failMap = new HashMap<String, String>();
		DeclareInfoBusiness declareInfoBusiness = new DeclareInfoBusiness();
		DeclareInfoBean declareInfoBean = null;
		int result = -1;

		FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
		FarmerContactInfoBusiness farmerContactInfoBusiness = new FarmerContactInfoBusiness();
		FarmerSkillInfoBusiness farmerSkillInfoBusiness = new FarmerSkillInfoBusiness();
		FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();
		FarmerJobInfoBusiness farmerJobInfoBusiness = new FarmerJobInfoBusiness();
		IndustryTypeBusiness industryTypeBusiness = new IndustryTypeBusiness();
		IndustryInfoBusiness industryInfoBusiness = new IndustryInfoBusiness();
		JobTypeBusiness jobTypeBusiness = new JobTypeBusiness();
		JobInfoBusiness jobInfoBusiness = new JobInfoBusiness();
		TabRegionBusiness tabRegionBusiness = new TabRegionBusiness();
		TabProvinceInfoBusiness tabProvinceInfoBusiness = new TabProvinceInfoBusiness();
		TabCityInfoBusiness tabCityInfoBusiness = new TabCityInfoBusiness();
		TabRegionInfoBusiness tabRegionInfoBusiness = new TabRegionInfoBusiness();
		TabTypeBusiness tabTypeBusiness = new TabTypeBusiness();
		TabTypeBean tabTypeBean = null;
		TabRegionBean tabRegionBean = null;
		TabProvinceInfoBean tabProvinceInfoBean = null;
		TabCityInfoBean tabCityInfoBean = null;
		TabRegionInfoBean tabRegionInfoBean = null;
		FarmerInfoBean farmerInfoBean = null;
		FarmerContactInfoBean farmerContactInfoBean = null;
		FarmerSkillInfoBean farmerSkillInfoBean = null;
		FarmerIndustryInfoBean farmerIndustryInfoBean = null;
		FarmerJobInfoBean farmerJobInfoBean = null;
		IndustryTypeBean industryTypeBean = null;
		IndustryInfoBean industryInfoBean = null;
		JobTypeBean jobTypeBean = null;
		JobInfoBean jobInfoBean = null;
		Calendar calendar = Calendar.getInstance();
		boolean isMajor1 = true;
		String[] addressArr = new String[]{};

		while (!pageResultBean.getRecordList().isEmpty()) {
			for (PersonInfoBean personInfoBean : pageResultBean.getRecordList()) {
				try {
					if(personInfoBean.getStatus()!=Constants.STATUS_VALID){
						continue;
					}
					personInfoMajor1Bean = null;
					personInfoMajor2Bean = null;
					result = -1;
					//检查这个用户同步了没有
					declareInfoBean = declareInfoBusiness.getDeclareInfoByMemo(personInfoBean.getId().trim());
					personInfoMajor1Bean = personInfoMajor1Business.getPersonInfoMajor1ByInfoBean(personInfoBean);
					personInfoMajor2Bean = personInfoMajor2Business
							.getPersonInfoMajor2BeanByInfoBean(personInfoBean);
					
					if(declareInfoBean==null){
						declareInfoBean = new DeclareInfoBean();
						// 处理DECLARE TYPE
						declareInfoBean.setDeclareType(
								personInfoBusiness.getDeclareTypeByBaseInfoType(personInfoBean.getBaseInfoType()));
						// 处理DECLARE YEAR
						if (personInfoBean.getYear() > 0) {
							declareInfoBean.setDeclareYear(String.valueOf(personInfoBean.getYear()));
						} else {
							Calendar createCalender = Calendar.getInstance();
							createCalender.setTime(personInfoBean.getCreateTime());
							declareInfoBean.setDeclareYear(String.valueOf(createCalender.get(Calendar.YEAR)));
						}
						declareInfoBean.setDeclareCategory(DeclareInfoConfig.DECLARE_CATEGORY_SPARE);
						declareInfoBean.setRegistryType(personInfoBean.getRegistType());
						declareInfoBean.setAuthStatus(DeclareInfoConfig.AUTH_STATUS_WAIT_APPROVE);
						declareInfoBean.setStatus(Constants.STATUS_VALID);
						declareInfoBean.setCreateTime(personInfoBean.getCreateTime());
						//在MEMO处记录USERID
						declareInfoBean.setMemo(personInfoBean.getId());
						// 处理申报状态
						if (personInfoBean.getBaseInfoType() == 1 || personInfoBean.getBaseInfoType() == 2
								|| personInfoBean.getBaseInfoType() == 3) {
							isMajor1 = true;
							if (personInfoMajor1Bean != null)
								declareInfoBean.setDeclareStatus(
										personInfoBusiness.getDeclareStatusByState(personInfoMajor1Bean.getState()));
							else
								continue;
						} else if (personInfoBean.getBaseInfoType() == 4 || personInfoBean.getBaseInfoType() == 5) {
							isMajor1 = false;
							if (personInfoMajor2Bean != null)
								declareInfoBean.setDeclareStatus(
										personInfoBusiness.getDeclareStatusByState(personInfoMajor2Bean.getState()));
							else
								continue;
						} else {
							continue;
						}
						result = declareInfoBusiness.addDeclareInfoWithCreateTime(declareInfoBean);
					}else{
						result = declareInfoBean.getDeclareId();
					}
					
					major = new PersonInfoMajor();
					major.setMajor1Bean(personInfoMajor1Bean);
					major.setMajor2Bean(personInfoMajor2Bean);
					if(personInfoMajor1Bean!=null) isMajor1 = true;
					else isMajor1 = false;
					
					if (result > 0) {
						// 成功就分别处理其他数据

						// 处理farmerinfo
						farmerInfoBean = new FarmerInfoBean();
						farmerInfoBean.setDeclareId(result);
						farmerInfoBean.setUserName(major.getName());
						farmerInfoBean.setUserGender(personInfoBusiness.getGenderByOldGender(major.getGender()));
						calendar.set(major.getBirthYear(), major.getBirthMonth(), 1);
						farmerInfoBean.setUserBirthday(new Date(calendar.getTimeInMillis()));
						farmerInfoBean.setNationality(major.getNation());
						farmerInfoBean.setEducation(major.getEducation());
						farmerInfoBean.setMajor(major.getMajor());
						farmerInfoBean.setIdentityNumber(major.getIdCardNumber());
						farmerInfoBean.setPoliticalStatus(major.getPoliticStatus());
						farmerInfoBean
								.setFarmerType(major.getFarmerTypeByDeclareType(declareInfoBean.getDeclareType()));
						farmerInfoBean.setStatus(Constants.STATUS_VALID);
						farmerInfoBean.setCreateTime(major.getCreateTime());
						farmerInfoBean.setMemo(major.getMemo());
						if(farmerInfoBusiness.getFarmerInfoByKey(result)==null){
							farmerInfoBusiness.addFarmerInfoWithCreateTime(farmerInfoBean);
						}else{
							farmerInfoBusiness.updateFarmerInfo(farmerInfoBean);
						}

						// 处理FARMERCONTACT
						farmerContactInfoBean = new FarmerContactInfoBean();
						farmerContactInfoBean.setDeclareId(result);
						farmerContactInfoBean.setUserMobile(major.getMobil());
						farmerContactInfoBean.setUserEmail(major.getEmail());
						farmerContactInfoBean.setUserWechat(major.getWechat());
						farmerContactInfoBean.setUserQq(major.getQQ());
						farmerContactInfoBean.setFamilyPerson(major.getFamilyPerson());
						if(StringKit.isValid(major.getAddress().trim())){
							try {
								addressArr = major.getAddress().split("[.]");
								if (addressArr.length == 2) {
									tabRegionBean = tabRegionBusiness
											.getTabRegionByServerId(addressArr[0] + "." + addressArr[1]);
									tabProvinceInfoBean = tabProvinceInfoBusiness.getProvinceByCode(tabRegionBean.getCode());
									farmerContactInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());
								} else if (addressArr.length == 3) {
									tabRegionBean = tabRegionBusiness
											.getTabRegionByServerId(addressArr[0] + "." + addressArr[1]);
									tabProvinceInfoBean = tabProvinceInfoBusiness.getProvinceByCode(tabRegionBean.getCode());
									farmerContactInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());

									tabRegionBean = tabRegionBusiness
											.getTabRegionByServerId(addressArr[0] + "." + addressArr[1] + "." + addressArr[2]);
									tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
									farmerContactInfoBean.setCity(tabCityInfoBean.getCityName());
								} else if (addressArr.length == 4) {
									tabRegionBean = tabRegionBusiness
											.getTabRegionByServerId(addressArr[0] + "." + addressArr[1]);
									tabProvinceInfoBean = tabProvinceInfoBusiness.getProvinceByCode(tabRegionBean.getCode());
									farmerContactInfoBean.setProvince(tabProvinceInfoBean.getProvinceName());

									tabRegionBean = tabRegionBusiness
											.getTabRegionByServerId(addressArr[0] + "." + addressArr[1] + "." + addressArr[2]);
									tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
									farmerContactInfoBean.setCity(tabCityInfoBean.getCityName());

									tabRegionBean = tabRegionBusiness.getTabRegionByServerId(
											addressArr[0] + "." + addressArr[1] + "." + addressArr[2] + "." + addressArr[3]);
									tabRegionInfoBean = tabRegionInfoBusiness.getTabRegionInfoByCode(tabRegionBean.getCode());
									farmerContactInfoBean.setRegion(tabRegionInfoBean.getRegionName());
								}
							} catch (Exception e) {
							}
						}
						farmerContactInfoBean.setAddress(major.getContactAddress());
						farmerContactInfoBean.setStatus(Constants.STATUS_VALID);
						farmerContactInfoBean.setCreateTime(major.getCreateTime());
						if(farmerContactInfoBusiness.getFarmerContactInfoByKey(result)==null){
							farmerContactInfoBusiness.addFarmerContactInfoWithCreateTime(farmerContactInfoBean);
						}else{
							farmerContactInfoBusiness.updateFarmerContactInfo(farmerContactInfoBean);
						}

						// 处理SKILL
						farmerSkillInfoBean = new FarmerSkillInfoBean();
						farmerSkillInfoBean.setDeclareId(result);
						farmerSkillInfoBean.setIsTrained(major.getIsTrained());
						farmerSkillInfoBean.setApplyType(major.getApplyType());
						farmerSkillInfoBean.setCertificationGrade(major.getCertificateGrade());
						farmerSkillInfoBean.setCertificationGrade(major.getIdentityGrade());
						farmerSkillInfoBean.setCertificationDate(major.getIndentityDate());
						farmerSkillInfoBean.setCertificationDepartment(major.getIdentityDepartment());
						farmerSkillInfoBean.setOtherTrainingTime(major.getOtherTrainTimes());
						farmerSkillInfoBean.setTrainingExperience(major.getTrainExp());
						farmerSkillInfoBean.setHasNationalCertification(major.getHasNationalCertification());
						farmerSkillInfoBean.setHasNewTypeTrainingCertification(major.getHasNationalCertification());
						farmerSkillInfoBean.setHasNewTypeCertification(major.getHasNewTypeCertification());
						farmerSkillInfoBean.setHasNoCertification(major.getHasNoCertification());
						farmerSkillInfoBean.setStatus(Constants.STATUS_VALID);
						farmerSkillInfoBean.setCertificationDate(major.getCreateTime());
						if(farmerSkillInfoBusiness.getFarmerSkillInfoByKey(result)==null){
							farmerSkillInfoBusiness.addFarmerSkillInfoWithCreateTime(farmerSkillInfoBean);
						}else{
							farmerSkillInfoBusiness.updateFarmerSkillInfo(farmerSkillInfoBean);
						}

						if (isMajor1) {
							// farmerIndustryInfoBean
							farmerIndustryInfoBean = new FarmerIndustryInfoBean();
							farmerIndustryInfoBean.setDeclareId(result);
							if(StringKit.isValid(personInfoMajor1Bean.getRegionId().trim())){
								try {
									addressArr = personInfoMajor1Bean.getRegionId().split("[.]");
									if (addressArr.length == 2) {
										tabRegionBean = tabRegionBusiness
												.getTabRegionByServerId(addressArr[0] + "." + addressArr[1]);
										tabProvinceInfoBean = tabProvinceInfoBusiness
												.getProvinceByCode(tabRegionBean.getCode());
										farmerIndustryInfoBean.setIndustryProvince(tabProvinceInfoBean.getProvinceName());
									} else if (addressArr.length == 3) {
										tabRegionBean = tabRegionBusiness
												.getTabRegionByServerId(addressArr[0] + "." + addressArr[1]);
										tabProvinceInfoBean = tabProvinceInfoBusiness
												.getProvinceByCode(tabRegionBean.getCode());
										farmerIndustryInfoBean.setIndustryProvince(tabProvinceInfoBean.getProvinceName());

										tabRegionBean = tabRegionBusiness.getTabRegionByServerId(
												addressArr[0] + "." + addressArr[1] + "." + addressArr[2]);
										tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
										farmerIndustryInfoBean.setIndustryCity(tabCityInfoBean.getCityName());
									} else if (addressArr.length == 4) {
										tabRegionBean = tabRegionBusiness
												.getTabRegionByServerId(addressArr[0] + "." + addressArr[1]);
										tabProvinceInfoBean = tabProvinceInfoBusiness
												.getProvinceByCode(tabRegionBean.getCode());
										farmerIndustryInfoBean.setIndustryProvince(tabProvinceInfoBean.getProvinceName());

										tabRegionBean = tabRegionBusiness.getTabRegionByServerId(
												addressArr[0] + "." + addressArr[1] + "." + addressArr[2]);
										tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
										farmerIndustryInfoBean.setIndustryCity(tabCityInfoBean.getCityName());

										tabRegionBean = tabRegionBusiness.getTabRegionByServerId(addressArr[0] + "."
												+ addressArr[1] + "." + addressArr[2] + "." + addressArr[3]);
										tabRegionInfoBean = tabRegionInfoBusiness
												.getTabRegionInfoByCode(tabRegionBean.getCode());
										farmerIndustryInfoBean.setIndustryRegion(tabRegionInfoBean.getRegionName());
									}
								} catch (Exception e) {
								}
							}
							farmerIndustryInfoBean.setHasRegisted(personInfoMajor1Bean.getRegister());
							farmerIndustryInfoBean.setIsExampleFamilyFarm(personInfoMajor1Bean.getModelFarm());
							farmerIndustryInfoBean.setFarmerType(major.getFarmerTypeByDeclareType(declareInfoBean.getDeclareType()));
							farmerIndustryInfoBean
									.setFarmLandScale(Double.valueOf(personInfoMajor1Bean.getLandScale()));
							farmerIndustryInfoBean.setFamilyPerson(personInfoMajor1Bean.getFamilyNum());
							farmerIndustryInfoBean.setFamilyWorkingPerson(personInfoMajor1Bean.getHomeWorkNum());
							farmerIndustryInfoBean.setDriveFarmerNumber(personInfoMajor1Bean.getEffectFramNum());
							// 地区类型，经济地区类型，工种类型，工种，从业年限，收入，服务规模

							try {
								// 1
								tabTypeBean = tabTypeBusiness
										.getTabTypeByKey(Integer.valueOf(personInfoMajor1Bean.getMainIndustry1()));
								if(tabTypeBean!=null){
									industryInfoBean = industryInfoBusiness.getIndustryInfoByName(tabTypeBean.getName());
									if (industryInfoBean != null) {
										farmerIndustryInfoBean.setIndustryId1(industryInfoBean.getIndustryId());
										farmerIndustryInfoBean.setIndustryUnit1(industryInfoBean.getUnit());
									}
								}
								tabTypeBean = tabTypeBusiness.getTabTypeByKey(tabTypeBean.getPid());
								if(tabTypeBean!=null){
									industryTypeBean = industryTypeBusiness.getIndustryTypeByName(tabTypeBean.getName());
									if (industryTypeBean != null) {
										farmerIndustryInfoBean.setIndustryTypeId1(industryTypeBean.getTypeId());
									}
								}
								farmerIndustryInfoBean
										.setIndustryScale1(Double.valueOf(personInfoMajor1Bean.getMainScale1()));
								farmerIndustryInfoBean
										.setExperience1(Double.valueOf(personInfoMajor1Bean.getWorkYear1()));
								// 2
								tabTypeBean = tabTypeBusiness
										.getTabTypeByKey(Integer.valueOf(personInfoMajor1Bean.getMainIndustry2()));
								if(tabTypeBean!=null){
									industryInfoBean = industryInfoBusiness.getIndustryInfoByName(tabTypeBean.getName());
									if (industryInfoBean != null) {
										farmerIndustryInfoBean.setIndustryId2(industryInfoBean.getIndustryId());
										farmerIndustryInfoBean.setIndustryUnit2(industryInfoBean.getUnit());
									}
								}
								tabTypeBean = tabTypeBusiness.getTabTypeByKey(tabTypeBean.getPid());
								if(tabTypeBean!=null){
									industryTypeBean = industryTypeBusiness.getIndustryTypeByName(tabTypeBean.getName());
									if (industryTypeBean != null) {
										farmerIndustryInfoBean.setIndustryTypeId2(industryTypeBean.getTypeId());
									}
								}
								farmerIndustryInfoBean
										.setIndustryScale2(Double.valueOf(personInfoMajor1Bean.getMainScale2()));
								farmerIndustryInfoBean
										.setExperience2(Double.valueOf(personInfoMajor1Bean.getWorkYear2()));
								// 3
								tabTypeBean = tabTypeBusiness
										.getTabTypeByKey(Integer.valueOf(personInfoMajor1Bean.getMainIndustry3()));
								if(tabTypeBean!=null){
									industryInfoBean = industryInfoBusiness.getIndustryInfoByName(tabTypeBean.getName());
									if (industryInfoBean != null) {
										farmerIndustryInfoBean.setIndustryId3(industryInfoBean.getIndustryId());
										farmerIndustryInfoBean.setIndustryUnit3(industryInfoBean.getUnit());
									}
								}
								tabTypeBean = tabTypeBusiness.getTabTypeByKey(tabTypeBean.getPid());
								if(tabTypeBean!=null){
									industryTypeBean = industryTypeBusiness.getIndustryTypeByName(tabTypeBean.getName());
									if (industryTypeBean != null) {
										farmerIndustryInfoBean.setIndustryTypeId3(industryTypeBean.getTypeId());
									}
								}
								farmerIndustryInfoBean
										.setIndustryScale3(Double.valueOf(personInfoMajor1Bean.getMainScale3()));
								farmerIndustryInfoBean
										.setExperience3(Double.valueOf(personInfoMajor1Bean.getWorkYear3()));
							} catch (Exception e) {
							}

							farmerIndustryInfoBean
									.setLastYearIncome(Double.valueOf(personInfoMajor1Bean.getIncomeLastYear()));
							farmerIndustryInfoBean
									.setLastYearFamilyIncome(Double.valueOf(personInfoMajor1Bean.getTotalIncome()));
							farmerIndustryInfoBean.setStatus(Constants.STATUS_VALID);
							farmerIndustryInfoBean.setCreateTime(personInfoMajor1Bean.getCreateTime());
							
							if(farmerIndustryInfoBusiness.getFarmerIndustryInfoByKey(result)==null){
								farmerIndustryInfoBusiness.addFarmerIndustryInfoWithCreateTime(farmerIndustryInfoBean);
							}else{
								farmerIndustryInfoBusiness.updateFarmerIndustryInfo(farmerIndustryInfoBean);
							}
							
							if(farmerJobInfoBusiness.getFarmerJobInfoByKey(result)==null){
								farmerJobInfoBean = new FarmerJobInfoBean();
								farmerJobInfoBean.setDeclareId(result);
								farmerJobInfoBusiness.addFarmerJobInfo(farmerJobInfoBean);
							}
							
						} else {
							// MAJOR2

							// jobinfo
							farmerJobInfoBean = new FarmerJobInfoBean();
							farmerJobInfoBean.setDeclareId(result);
							if (personInfoBean.getBaseInfoType() == 4 && personInfoMajor2Bean.getWorkKind() > 0
									&& personInfoMajor2Bean.getWorkName() > 0) {
								String typeName = PersonInfoMajor.WORK_KIND_MAP.get(personInfoMajor2Bean.getWorkKind());
								String jobName = PersonInfoMajor.WORK_NAME_MAP.get(personInfoMajor2Bean.getWorkKind())
										.get(personInfoMajor2Bean.getWorkName());
								jobTypeBean = jobTypeBusiness.getJobTypeByName(typeName);
								if (jobTypeBean != null) {
									farmerJobInfoBean.setJobType(jobTypeBean.getTypeId());
									jobInfoBean = jobInfoBusiness.getJobInfoByName(jobName, jobTypeBean.getTypeId());
									if (jobInfoBean != null)
										farmerJobInfoBean.setJobId(jobInfoBean.getJobId());
								}
							} else {
								tabTypeBean = tabTypeBusiness.getTabTypeByCondition(0,
										personInfoMajor2Bean.getWorkKind(), 2);
								if (tabTypeBean != null) {
									jobTypeBean = jobTypeBusiness.getJobTypeByName(tabTypeBean.getName());
									if (jobTypeBean != null) {
										farmerJobInfoBean.setJobType(jobTypeBean.getTypeId());
										tabTypeBean = tabTypeBusiness.getTabTypeByCondition(tabTypeBean.getId(),
												personInfoMajor2Bean.getWorkName(), 2);
										if (tabTypeBean != null) {
											jobInfoBean = jobInfoBusiness.getJobInfoByName(tabTypeBean.getName(),
													jobTypeBean.getTypeId());
											if (jobInfoBean != null)
												farmerJobInfoBean.setJobId(jobInfoBean.getJobId());
										}
									}
								}
							}
							farmerJobInfoBean.setIncome(Double.valueOf(personInfoMajor2Bean.getYearIncome()));
							farmerJobInfoBean.setExperience(Double.valueOf(personInfoMajor2Bean.getWorkYear()));
							farmerJobInfoBean.setCompanyType(
									PersonInfoMajor.COMPANY_TYPE_MAP.get(personInfoMajor2Bean.getWorkType()));
							addressArr = personInfoMajor2Bean.getRegionId().split("[.]");
							if (addressArr.length == 2) {
								tabRegionBean = tabRegionBusiness
										.getTabRegionByServerId(addressArr[0] + "." + addressArr[1]);
								tabProvinceInfoBean = tabProvinceInfoBusiness
										.getProvinceByCode(tabRegionBean.getCode());
								farmerJobInfoBean.setJobProvince(tabProvinceInfoBean.getProvinceName());
							} else if (addressArr.length == 3) {
								tabRegionBean = tabRegionBusiness
										.getTabRegionByServerId(addressArr[0] + "." + addressArr[1]);
								tabProvinceInfoBean = tabProvinceInfoBusiness
										.getProvinceByCode(tabRegionBean.getCode());
								farmerJobInfoBean.setJobProvince(tabProvinceInfoBean.getProvinceName());

								tabRegionBean = tabRegionBusiness.getTabRegionByServerId(
										addressArr[0] + "." + addressArr[1] + "." + addressArr[2]);
								tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
								farmerJobInfoBean.setJobCity(tabCityInfoBean.getCityName());
							} else if (addressArr.length == 4) {
								tabRegionBean = tabRegionBusiness
										.getTabRegionByServerId(addressArr[0] + "." + addressArr[1]);
								tabProvinceInfoBean = tabProvinceInfoBusiness
										.getProvinceByCode(tabRegionBean.getCode());
								farmerJobInfoBean.setJobProvince(tabProvinceInfoBean.getProvinceName());

								tabRegionBean = tabRegionBusiness.getTabRegionByServerId(
										addressArr[0] + "." + addressArr[1] + "." + addressArr[2]);
								tabCityInfoBean = tabCityInfoBusiness.getCityByCode(tabRegionBean.getCode());
								farmerJobInfoBean.setJobCity(tabCityInfoBean.getCityName());

								tabRegionBean = tabRegionBusiness.getTabRegionByServerId(addressArr[0] + "."
										+ addressArr[1] + "." + addressArr[2] + "." + addressArr[3]);
								tabRegionInfoBean = tabRegionInfoBusiness
										.getTabRegionInfoByCode(tabRegionBean.getCode());
								farmerJobInfoBean.setJobRegion(tabRegionInfoBean.getRegionName());
							}
							farmerJobInfoBean.setStatus(Constants.STATUS_VALID);
							farmerJobInfoBean.setCreateTime(personInfoMajor2Bean.getCreateTime());
							
							if(farmerJobInfoBusiness.getFarmerJobInfoByKey(result)==null){
								farmerJobInfoBusiness.addFarmerJobInfoWithCreateTime(farmerJobInfoBean);
							}else{
								farmerJobInfoBusiness.updateFarmerJobInfo(farmerJobInfoBean);
							}
							
							if(farmerIndustryInfoBusiness.getFarmerIndustryInfoByKey(result)==null){
								farmerIndustryInfoBean = new FarmerIndustryInfoBean();
								farmerIndustryInfoBean.setDeclareId(result);
								farmerIndustryInfoBusiness.addFarmerIndustryInfo(farmerIndustryInfoBean);
							}
							
						}
					}
				} catch (Exception e) {
					String erro = "";
					for (StackTraceElement s : e.getStackTrace()) {
						erro += s.getMethodName() + "|" + s.getClassName() + "|" + s.getLineNumber() + "|||";
					}
					failMap.put(personInfoBean.getId(), erro);
					continue;
				}
			}

			pageNum++;
			/*if(pageNum>endPage){
				break;
			}*/
			pageBean = new PageBean(pageNum, pageSize);
			pageResultBean = personInfoBusiness.getPersonInfoPageListByMap(new HashMap<String, String>(), pageBean);
		}

		HttpResponseKit.printJson(request, response, failMap, "");
		return;
	}
}
