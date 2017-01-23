package com.lpmas.declare.admin.handler;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.declare.admin.business.DeclareImageBusiness;
import com.lpmas.declare.admin.business.DeclareReportBusiness;
import com.lpmas.declare.admin.business.FarmerContactInfoBusiness;
import com.lpmas.declare.admin.business.FarmerIndustryInfoBusiness;
import com.lpmas.declare.admin.business.FarmerInfoBusiness;
import com.lpmas.declare.admin.business.FarmerJobInfoBusiness;
import com.lpmas.declare.admin.business.FarmerSkillInfoBusiness;
import com.lpmas.declare.admin.dao.DeclareInfoDao;
import com.lpmas.declare.admin.factory.DeclareDBFactory;
import com.lpmas.declare.bean.DeclareImageBean;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.declare.bean.DeclareReportBean;
import com.lpmas.declare.bean.FarmerContactInfoBean;
import com.lpmas.declare.bean.FarmerIndustryInfoBean;
import com.lpmas.declare.bean.FarmerInfoBean;
import com.lpmas.declare.bean.FarmerJobInfoBean;
import com.lpmas.declare.bean.FarmerSkillInfoBean;
import com.lpmas.declare.config.DeclareImageInfoConfig;
import com.lpmas.framework.db.DBFactory;
import com.lpmas.framework.db.DBObject;
import com.lpmas.framework.util.DateKit;

public class DeclareReportHandler {

	private static Logger log = LoggerFactory.getLogger(DeclareReportHandler.class);

	private int updateDeclareInfo(DBObject db, DeclareInfoBean bean) throws Exception {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.updateDeclareInfo(db, bean);
	}

	/**
	 * 根据主页信息，新建总表记录
	 */
	public void createDeclareReport(DeclareInfoBean declareInfoBean) throws Exception {
		DBFactory dbFactory = new DeclareDBFactory();
		DBObject db = null;
		try {
			db = dbFactory.getDBObjectW();
			db.beginTransition();// 事务开始
			updateDeclareInfo(db, declareInfoBean);
			DeclareReportBusiness declareReportBusiness = new DeclareReportBusiness();
			DeclareImageBusiness declareImageBusiness = new DeclareImageBusiness();
			DeclareImageBean imageBean = declareImageBusiness.getDeclareImageByKey(declareInfoBean.getDeclareId(),
					DeclareImageInfoConfig.IMG_TYPE_ONE_INCH);
			FarmerContactInfoBusiness farmerContactInfoBusiness = new FarmerContactInfoBusiness();
			FarmerContactInfoBean farmerContactInfoBean = farmerContactInfoBusiness
					.getFarmerContactInfoByKey(declareInfoBean.getDeclareId());
			FarmerIndustryInfoBusiness farmerIndustryInfoBusiness = new FarmerIndustryInfoBusiness();
			FarmerIndustryInfoBean farmerIndustryInfoBean = farmerIndustryInfoBusiness
					.getFarmerIndustryInfoByKey(declareInfoBean.getDeclareId());
			FarmerInfoBusiness farmerInfoBusiness = new FarmerInfoBusiness();
			FarmerInfoBean farmerInfoBean = farmerInfoBusiness.getFarmerInfoByKey(declareInfoBean.getDeclareId());
			FarmerJobInfoBusiness FarmerJobInfoBusiness = new FarmerJobInfoBusiness();
			FarmerJobInfoBean farmerJobInfoBean = FarmerJobInfoBusiness
					.getFarmerJobInfoByKey(declareInfoBean.getDeclareId());
			FarmerSkillInfoBusiness farmerSkillInfoBusiness = new FarmerSkillInfoBusiness();
			FarmerSkillInfoBean farmerSkillInfoBean = farmerSkillInfoBusiness
					.getFarmerSkillInfoByKey(declareInfoBean.getDeclareId());
			DeclareReportBean declareReportBean = new DeclareReportBean();
			if (imageBean != null) {
				declareReportBean.setImagePath(imageBean.getImagePath());
				declareReportBean.setImageType(imageBean.getImageType());
			}
			if (farmerContactInfoBean != null) {
				declareReportBean.setAddress(farmerContactInfoBean.getAddress());
				declareReportBean.setCity(farmerContactInfoBean.getCity());
				declareReportBean.setProvince(farmerContactInfoBean.getProvince());
				declareReportBean.setRegion(farmerContactInfoBean.getRegion());
				declareReportBean.setUserQq(farmerContactInfoBean.getUserQq());
				declareReportBean.setUserWechat(farmerContactInfoBean.getUserWechat());
				declareReportBean.setUserEmail(farmerContactInfoBean.getUserEmail());
				declareReportBean.setUserMobile(farmerContactInfoBean.getUserMobile());
			}
			if (farmerIndustryInfoBean != null) {
				declareReportBean.setAreaType(farmerIndustryInfoBean.getAreaType());
				declareReportBean.setDriveFarmerNumber(farmerIndustryInfoBean.getDriveFarmerNumber());
				declareReportBean.setEconomicAreaType(farmerIndustryInfoBean.getEconomicAreaType());
				declareReportBean.setExperience1(farmerIndustryInfoBean.getExperience1());
				declareReportBean.setExperience2(farmerIndustryInfoBean.getExperience2());
				declareReportBean.setExperience3(farmerIndustryInfoBean.getExperience3());
				declareReportBean.setFamilyWorkingPerson(farmerIndustryInfoBean.getFamilyWorkingPerson());
				declareReportBean.setIndustryCity(farmerIndustryInfoBean.getIndustryCity());
				declareReportBean.setIndustryId1(farmerIndustryInfoBean.getIndustryId1());
				declareReportBean.setIndustryId2(farmerIndustryInfoBean.getIndustryId2());
				declareReportBean.setIndustryId3(farmerIndustryInfoBean.getIndustryId3());
				declareReportBean.setIndustryProvince(farmerIndustryInfoBean.getIndustryProvince());
				declareReportBean.setIndustryRegion(farmerIndustryInfoBean.getIndustryRegion());
				declareReportBean.setIndustryScale1(farmerIndustryInfoBean.getIndustryScale1());
				declareReportBean.setIndustryScale2(farmerIndustryInfoBean.getIndustryScale2());
				declareReportBean.setIndustryScale3(farmerIndustryInfoBean.getIndustryScale3());
				declareReportBean.setIndustryTypeId1(farmerIndustryInfoBean.getIndustryTypeId1());
				declareReportBean.setIndustryTypeId2(farmerIndustryInfoBean.getIndustryTypeId2());
				declareReportBean.setIndustryTypeId3(farmerIndustryInfoBean.getIndustryTypeId3());
				declareReportBean.setIndustryUnit1(farmerIndustryInfoBean.getIndustryUnit1());
				declareReportBean.setIndustryUnit2(farmerIndustryInfoBean.getIndustryUnit2());
				declareReportBean.setIndustryUnit3(farmerIndustryInfoBean.getIndustryUnit3());
				declareReportBean.setLastYearFamilyIncome(farmerIndustryInfoBean.getLastYearFamilyIncome());
				declareReportBean.setLastYearIncome(farmerIndustryInfoBean.getLastYearIncome());
				declareReportBean.setFarmerType(farmerIndustryInfoBean.getFarmerType());
				declareReportBean.setHasRegisted(farmerIndustryInfoBean.getHasRegisted());
				declareReportBean.setIsExampleFamilyFarm(farmerIndustryInfoBean.getIsExampleFamilyFarm());
				declareReportBean.setExampleFarmLevel(farmerIndustryInfoBean.getExampleFarmLevel());
				declareReportBean.setFamilyFarmType(farmerIndustryInfoBean.getFamilyFarmType());
				declareReportBean.setFarmLandScale(farmerIndustryInfoBean.getFarmLandScale());
				declareReportBean.setFamilyPerson(farmerIndustryInfoBean.getFamilyPerson());
				declareReportBean.setIndustryExperience(farmerIndustryInfoBean.getExperience());
				declareReportBean.setIndustryIncome(farmerIndustryInfoBean.getIncome());
				declareReportBean.setIndustryJobId(farmerIndustryInfoBean.getJobId());
				declareReportBean.setIndustryJobType(farmerIndustryInfoBean.getJobType());
				declareReportBean.setServiceScale(farmerIndustryInfoBean.getServiceScale());
			}
			if (farmerInfoBean != null) {
				declareReportBean.setEducation(farmerInfoBean.getEducation());
				declareReportBean.setIdentityNumber(farmerInfoBean.getIdentityNumber());
				declareReportBean.setMajor(farmerInfoBean.getMajor());
				declareReportBean.setPoliticalStatus(farmerInfoBean.getPoliticalStatus());
				declareReportBean.setUserBirthday(farmerInfoBean.getUserBirthday());
				declareReportBean.setUserGender(farmerInfoBean.getUserGender());
				declareReportBean.setNationality(farmerInfoBean.getNationality());
				declareReportBean.setUserName(farmerInfoBean.getUserName());
			}
			if (farmerJobInfoBean != null) {
				declareReportBean.setCompanyType(farmerJobInfoBean.getCompanyType());
				declareReportBean.setExperience(farmerJobInfoBean.getExperience());
				declareReportBean.setIncome(farmerJobInfoBean.getIncome());
				declareReportBean.setJobCity(farmerJobInfoBean.getJobCity());
				declareReportBean.setJobName(farmerJobInfoBean.getJobId());
				declareReportBean.setJobProvince(farmerJobInfoBean.getJobProvince());
				declareReportBean.setJobRegion(farmerJobInfoBean.getJobRegion());
				declareReportBean.setJobType(farmerJobInfoBean.getJobType());
			}
			if (farmerSkillInfoBean != null) {
				declareReportBean.setHasNationalCertification(farmerSkillInfoBean.getHasNationalCertification());
				declareReportBean.setHasNewTypeCertification(farmerSkillInfoBean.getHasNewTypeCertification());
				declareReportBean
						.setHasNewTypeTrainingCertification(farmerSkillInfoBean.getHasNewTypeTrainingCertification());
				declareReportBean.setHasNoCertification(farmerSkillInfoBean.getHasNoCertification());
				declareReportBean.setIsTrained(farmerSkillInfoBean.getIsTrained());
				declareReportBean.setOtherTrainingTime(farmerSkillInfoBean.getOtherTrainingTime());
				declareReportBean.setApplyType(farmerSkillInfoBean.getApplyType());
				declareReportBean.setCertificationGrade(farmerSkillInfoBean.getCertificationGrade());
				declareReportBean.setCertificationDate(farmerSkillInfoBean.getCertificationDate());
				declareReportBean.setCertificationDepartment(farmerSkillInfoBean.getCertificationDepartment());
				declareReportBean.setCertificationTitle(farmerSkillInfoBean.getCertificationTitle());
				declareReportBean.setTrainingExperience(farmerSkillInfoBean.getTrainingExperience());
			}
			declareReportBean.setDeclareId(declareInfoBean.getDeclareId());
			declareReportBean.setDeclareType(declareInfoBean.getDeclareType());
			declareReportBean.setDeclareStatus(declareInfoBean.getDeclareStatus());
			declareReportBean.setUserId(declareInfoBean.getUserId());
			declareReportBean.setStatus(declareInfoBean.getStatus());
			declareReportBean.setAuthStatus(declareInfoBean.getAuthStatus());
			declareReportBean.setAuthOrganizationId(declareInfoBean.getAuthOrganizationId());
			declareReportBean.setDeclareCategory(declareInfoBean.getDeclareCategory());
			declareReportBean.setDeclareYear(declareInfoBean.getDeclareYear());
			declareReportBean.setRegistryType(declareInfoBean.getRegistryType());
			// 保存库存对象数据到MongoDB
			DeclareReportBean originalBean = declareReportBusiness
					.getDeclareReportByKey(String.valueOf(declareInfoBean.getDeclareId()));
			if (originalBean != null) {
				// 更新总表记录
				declareReportBean.set_id(originalBean.get_id());
				declareReportBean.setCreateTime(originalBean.getCreateTime());
				declareReportBean.setCreateUser(originalBean.getCreateUser());
				declareReportBean.setTrainingClassInfoList(originalBean.getTrainingClassInfoList());
				declareReportBean.setModifyTime(DateKit.getCurrentTimestamp());
				declareReportBean.setModifyUser(declareInfoBean.getModifyUser());
				declareReportBusiness.updateDeclareReport(declareReportBean);
			} else {
				// 新建总表记录
				declareReportBean.set_id(String.valueOf(declareInfoBean.getDeclareId()));
				declareReportBean.setCreateTime(DateKit.getCurrentTimestamp());
				declareReportBean.setCreateUser(declareInfoBean.getCreateUser());
				declareReportBusiness.insertDeclareReport(declareReportBean);
			}
			db.commit();// 事务提交
		} catch (Exception e) {
			db.rollback();// 事务回滚
			log.error("", e);
			throw e;
		} finally {
			try {
				db.close();
			} catch (SQLException sqle) {
				log.error("", sqle);
			}
		}

	}
}
