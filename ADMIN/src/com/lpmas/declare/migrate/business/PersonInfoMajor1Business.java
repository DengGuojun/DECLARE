package com.lpmas.declare.migrate.business;

import java.util.HashMap;

import com.lpmas.declare.migrate.bean.PersonInfoBean;
import com.lpmas.declare.migrate.bean.PersonInfoMajor1Bean;
import com.lpmas.declare.migrate.dao.PersonInfoMajor1Dao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class PersonInfoMajor1Business {
	public int addPersonInfoMajor1(PersonInfoMajor1Bean bean) {
		PersonInfoMajor1Dao dao = new PersonInfoMajor1Dao();
		return dao.insertPersonInfoMajor1(bean);
	}

	public int updatePersonInfoMajor1(PersonInfoMajor1Bean bean) {
		PersonInfoMajor1Dao dao = new PersonInfoMajor1Dao();
		return dao.updatePersonInfoMajor1(bean);
	}

	public PersonInfoMajor1Bean getPersonInfoMajor1ByKey(String id) {
		PersonInfoMajor1Dao dao = new PersonInfoMajor1Dao();
		return dao.getPersonInfoMajor1ByKey(id);
	}

	public PageResultBean<PersonInfoMajor1Bean> getPersonInfoMajor1PageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PersonInfoMajor1Dao dao = new PersonInfoMajor1Dao();
		return dao.getPersonInfoMajor1PageListByMap(condMap, pageBean);
	}

	public PersonInfoMajor1Bean getPersonInfoMajor1ByInfoBean(PersonInfoBean personInfoBean) {
		PersonInfoMajor1Dao dao = new PersonInfoMajor1Dao();
		return dao.getPersonInfoMajor1ByInfoBean(personInfoBean);
	}

}