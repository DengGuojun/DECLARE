package com.lpmas.declare.console.business;

import com.lpmas.declare.bean.DeclareImageBean;
import com.lpmas.declare.console.dao.DeclareImageDao;

public class DeclareImageBusiness {

	public DeclareImageBean getDeclareImageByKey(int declareId, String imageType) {
		DeclareImageDao dao = new DeclareImageDao();
		return dao.getDeclareImageByKey(declareId, imageType);
	}

}