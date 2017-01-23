package com.lpmas.declare.migrate.business;

import java.util.HashMap;

import com.lpmas.constant.user.GenderConfig;
import com.lpmas.declare.config.DeclareInfoConfig;
import com.lpmas.declare.migrate.bean.PersonInfoBean;
import com.lpmas.declare.migrate.dao.PersonInfoDao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class PersonInfoBusiness {
	public int addPersonInfo(PersonInfoBean bean) {
		PersonInfoDao dao = new PersonInfoDao();
		return dao.insertPersonInfo(bean);
	}

	public int updatePersonInfo(PersonInfoBean bean) {
		PersonInfoDao dao = new PersonInfoDao();
		return dao.updatePersonInfo(bean);
	}

	public PersonInfoBean getPersonInfoByKey(String id) {
		PersonInfoDao dao = new PersonInfoDao();
		return dao.getPersonInfoByKey(id);
	}

	public PageResultBean<PersonInfoBean> getPersonInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PersonInfoDao dao = new PersonInfoDao();
		return dao.getPersonInfoPageListByMap(condMap, pageBean);
	}

	public int getDeclareTypeByBaseInfoType(int baseInfoType) {
		if (baseInfoType == 1)
			return DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER;
		else if (baseInfoType == 2)
			return DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER;
		else if (baseInfoType == 3)
			return DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER;
		else if (baseInfoType == 4)
			return DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER;
		else if (baseInfoType == 5)
			return DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER;

		return 0;
	}

	public String getDeclareStatusByState(int state) {
		if (state == 0)
			return DeclareInfoConfig.DECLARE_STATUS_NOT_SUBMIT;
		else if (state == 1)
			return DeclareInfoConfig.DECLARE_STATUS_SUBMIT;
		else if (state == 2)
			return DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE;
		else if (state == 3)
			return DeclareInfoConfig.DECLARE_STATUS_APPROVE;
		else if (state == -1)
			return DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE;
		else if (state == -2)
			return DeclareInfoConfig.DECLARE_STATUS_REJECT;

		return "";
	}

	public int getGenderByOldGender(int gender) {
		if (gender == 0)
			return GenderConfig.GENDER_MALE;
		else
			return GenderConfig.GENDER_FEMALE;
	}

}