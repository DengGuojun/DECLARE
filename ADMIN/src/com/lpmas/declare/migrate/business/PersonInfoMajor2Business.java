package com.lpmas.declare.migrate.business;

import java.util.HashMap;

import com.lpmas.declare.migrate.bean.PersonInfoBean;
import com.lpmas.declare.migrate.bean.PersonInfoMajor2Bean;
import com.lpmas.declare.migrate.dao.PersonInfoMajor2Dao;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;

public class PersonInfoMajor2Business {
	public int addPersonInfoMajor2(PersonInfoMajor2Bean bean) {
		PersonInfoMajor2Dao dao = new PersonInfoMajor2Dao();
		return dao.insertPersonInfoMajor2(bean);
	}

	public int updatePersonInfoMajor2(PersonInfoMajor2Bean bean) {
		PersonInfoMajor2Dao dao = new PersonInfoMajor2Dao();
		return dao.updatePersonInfoMajor2(bean);
	}

	public PersonInfoMajor2Bean getPersonInfoMajor2ByKey(String id) {
		PersonInfoMajor2Dao dao = new PersonInfoMajor2Dao();
		return dao.getPersonInfoMajor2ByKey(id);
	}

	public PageResultBean<PersonInfoMajor2Bean> getPersonInfoMajor2PageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		PersonInfoMajor2Dao dao = new PersonInfoMajor2Dao();
		return dao.getPersonInfoMajor2PageListByMap(condMap, pageBean);
	}

	public PersonInfoMajor2Bean getPersonInfoMajor2BeanByInfoBean(PersonInfoBean personInfoBean) {
		PersonInfoMajor2Dao dao = new PersonInfoMajor2Dao();
		return dao.getPersonInfoMajor2BeanByInfoBean(personInfoBean);
	}

}