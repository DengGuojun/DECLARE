package com.lpmas.declare.migrate.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lpmas.declare.admin.business.MajorTypeBusiness;
import com.lpmas.declare.admin.business.TrainingClassInfoBusiness;
import com.lpmas.declare.bean.MajorTypeBean;
import com.lpmas.declare.bean.TrainingClassInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.web.HttpResponseKit;

/**
 * 专业去重:专业重复名字的，会通过这个程序去重.并更正专业类型下拉列表的实现
 *
 */
@WebServlet("/declare/migrate/MajorTypeChange.do")
public class MajorTypeChange extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MajorTypeBusiness typeBusiness = new MajorTypeBusiness();
		List<MajorTypeBean> majorTypeList = typeBusiness.getMajorTypeAllList();

		List<Integer> removeTypeIdList = new ArrayList<>();

		int count = 0;
		TrainingClassInfoBusiness classInfoBusiness = new TrainingClassInfoBusiness();
		HashMap<String, String> condMap = new HashMap<>();
		for (MajorTypeBean bean : majorTypeList) {
			condMap.put("majorTypeId", String.valueOf(bean.getMajorId()));
			List<TrainingClassInfoBean> trainingClassList = classInfoBusiness.getTrainingClassInfoListByMap(condMap);
			if (trainingClassList.isEmpty()) {
				removeTypeIdList.add(bean.getMajorId());
			}
		}

		for (Integer typeId : removeTypeIdList) {
			MajorTypeBean bean = typeBusiness.getMajorTypeByKey(typeId);
			bean.setStatus(Constants.STATUS_NOT_VALID);
			count += typeBusiness.updateMajorType(bean);
		}

		MajorTypeBean bean = new MajorTypeBean();
		bean = typeBusiness.getMajorTypeByKey(1);	//农艺工
		bean.setStatus(Constants.STATUS_NOT_VALID);
		typeBusiness.updateMajorType(bean);

		bean = typeBusiness.getMajorTypeByKey(2);	//种植服务
		bean.setStatus(Constants.STATUS_NOT_VALID);
		typeBusiness.updateMajorType(bean);

		bean = typeBusiness.getMajorTypeByKey(20);	//海水
		bean.setStatus(Constants.STATUS_VALID);
		typeBusiness.updateMajorType(bean);

		if (count > 0) {// 成功
			HttpResponseKit.alertMessage(res, "去重成功", "/declare/migrate/DataMigrationImport.do");
		} else {
			HttpResponseKit.alertMessage(res, "没有去重", "/declare/migrate/DataMigrationImport.do");
		}
	}

}
