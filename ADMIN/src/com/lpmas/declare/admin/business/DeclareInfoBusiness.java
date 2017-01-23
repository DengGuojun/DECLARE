package com.lpmas.declare.admin.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.ccp.client.SmsMessageClient;
import com.lpmas.ccp.client.bean.SmsMessageRequestBean;
import com.lpmas.ccp.config.CcpConfig;
import com.lpmas.declare.admin.config.DeclareAdminConfig;
import com.lpmas.declare.admin.dao.DeclareInfoDao;
import com.lpmas.declare.bean.DeclareInfoBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.crypto.MD5;
import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.page.PageResultBean;
import com.lpmas.framework.web.ReturnMessageBean;
import com.lpmas.ow.passport.user.bean.UserRegBean;
import com.lpmas.ow.passport.user.service.bean.UserRegAddRequestBean;
import com.lpmas.ow.passport.user.service.bean.UserRegQueryRequestBean;
import com.lpmas.ow.passport.user.service.client.UserServiceClient;

public class DeclareInfoBusiness {

	public int addDeclareInfo(DeclareInfoBean bean) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.insertDeclareInfo(bean);
	}

	public int addDeclareInfoWithCreateTime(DeclareInfoBean bean) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.insertDeclareInfoWithCreateTime(bean);
	}

	public int updateDeclareInfo(DeclareInfoBean bean) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.updateDeclareInfo(bean);
	}

	public DeclareInfoBean getDeclareInfoByKey(int declareId) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoByKey(declareId);
	}

	public DeclareInfoBean getDeclareInfoByCondition(int userId, int declareType) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoByCondition(userId, declareType);
	}

	public DeclareInfoBean getDeclareInfoByMemo(String memo) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoByMemo(memo);
	}

	public List<DeclareInfoBean> getDeclareInfoListByUserId(int userId) {
		DeclareInfoDao dao = new DeclareInfoDao();
		HashMap<String, String> condMap = new HashMap<String, String>();
		condMap.put("userId", String.valueOf(userId));
		condMap.put("status", String.valueOf(Constants.STATUS_VALID));
		return dao.getDeclareInfoListByMap(condMap);
	}

	public List<DeclareInfoBean> getDeclareInfoListByMap(HashMap<String, String> condMap) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoListByMap(condMap);
	}

	public int getDeclareInfoRecordResultByMap(HashMap<String, String> condMap) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoRecordResultByMap(condMap);
	}

	public PageResultBean<DeclareInfoBean> getDeclareInfoPageListByMap(HashMap<String, String> condMap,
			PageBean pageBean) {
		DeclareInfoDao dao = new DeclareInfoDao();
		return dao.getDeclareInfoPageListByMap(condMap, pageBean);
	}

	public String getUserMobileByUserClient(int userId) {
		String result = "";
		UserServiceClient client = new UserServiceClient();
		UserRegQueryRequestBean queryRequestBean = new UserRegQueryRequestBean();
		queryRequestBean.setUserId(userId);
		ReturnMessageBean returnMessage = client.getUserReg(queryRequestBean);
		if (null != returnMessage && returnMessage.getCode() == Constants.STATUS_VALID) {
			UserRegBean userRegBean = (UserRegBean) returnMessage.getContent();
			if (userRegBean != null && userRegBean.getUserId() != 0) {
				result = userRegBean.getUserLoginPhone();
			}
		}
		return result;
	}

	public int getUserIdByUserClient(String mobile) {
		int result = 0;
		int storeId = 18;// Declare的StoreID
		// 根据手机号获取userId
		UserServiceClient client = new UserServiceClient();
		// 先检查是否存在uesrId
		UserRegQueryRequestBean queryRequestBean = new UserRegQueryRequestBean();
		queryRequestBean.setUserLoginPhone(mobile);
		ReturnMessageBean returnMessage = client.getUserReg(queryRequestBean);
		if (null != returnMessage && returnMessage.getCode() == Constants.STATUS_VALID) {
			UserRegBean userRegBean = (UserRegBean) returnMessage.getContent();
			if (userRegBean != null && userRegBean.getUserId() != 0) {
				result = userRegBean.getUserId();
			}
		}
		if (result == 0) {
			String userPassword = mobile.substring(mobile.length() - 6, mobile.length()); // defaultPassword
			UserRegAddRequestBean bean = new UserRegAddRequestBean();
			bean.setUserLoginPhone(mobile);
			bean.setUserPwd(userPassword);
			bean.setUserFrom(1);
			bean.setRegSource("Declare_web");
			bean.setUserName(mobile);
			bean.setStoreId(storeId);
			bean.setPhoneAuthCode(MD5.getMD5(mobile + "s3fs3jha"));
			returnMessage = client.addUserReg(bean);
			if (null != returnMessage && returnMessage.getCode() == Constants.STATUS_VALID) {
				result = Integer.valueOf((String) returnMessage.getContent());
				// 手机发送短信通知用户
				notifyUser(mobile);
			}
		}
		return result;
	}

	private static void notifyUser(String mobile) {
		SmsMessageRequestBean bean = new SmsMessageRequestBean();
		bean.setAppCode(DeclareAdminConfig.APP_ID);
		bean.setToMobileNumber(mobile);

		bean.setMode(CcpConfig.CM_TEMPLATE);
		bean.setTemplateCode("NgolUserPassword");
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("label1", mobile.substring(mobile.length() - 6, mobile.length()));
		bean.setData(data);

		SmsMessageClient client = new SmsMessageClient();
		try {
			client.send(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// int result = 0;
		// String mobile = "18819467391";
		// // 根据手机号获取userId
		// UserServiceClient client = new UserServiceClient();
		// // 先检查是否存在uesrId
		// UserRegQueryRequestBean queryRequestBean = new
		// UserRegQueryRequestBean();
		// queryRequestBean.setUserLoginPhone(mobile);
		// ReturnMessageBean returnMessage =
		// client.getUserReg(queryRequestBean);
		// if (null != returnMessage && returnMessage.getCode() ==
		// Constants.STATUS_VALID) {
		// UserRegBean userRegBean = (UserRegBean) returnMessage.getContent();
		// if (userRegBean != null && userRegBean.getUserId() != 0) {
		// System.out.println(userRegBean.getUserId());
		// result = 1;
		// }
		// }
		// if (result == 0) {
		// String userPassword = mobile.substring(mobile.length() - 6,
		// mobile.length()); // defaultPassword
		// UserRegAddRequestBean bean = new UserRegAddRequestBean();
		// bean.setUserLoginPhone(mobile);
		// bean.setUserPwd(userPassword);
		// bean.setUserFrom(1);
		// bean.setRegSource("Declare_web");
		// bean.setUserName(mobile);
		// bean.setStoreId(18);
		// bean.setPhoneAuthCode(MD5.getMD5(mobile + "s3fs3jha"));
		// returnMessage = client.addUserReg(bean);
		// if (null != returnMessage && returnMessage.getCode() ==
		// Constants.STATUS_VALID) {
		// System.out.println("创建成功");
		// result = Integer.valueOf((String) returnMessage.getContent());
		// System.out.println(result);
		// // 手机发送短信通知用户
		// // notifyUser("13602489201", 18);
		// }
		// }
		// queryRequestBean = new UserRegQueryRequestBean();
		// queryRequestBean.setUserId(4709);
		// returnMessage = client.getUserReg(queryRequestBean);
		// if (null != returnMessage && returnMessage.getCode() ==
		// Constants.STATUS_VALID) {
		// UserRegBean userRegBean = (UserRegBean) returnMessage.getContent();
		// if (userRegBean != null && userRegBean.getUserId() != 0) {
		// System.out.println(userRegBean.getUserLoginPhone());
		// result = 1;
		// }
		// }
		notifyUser("13602489201");

	}

}