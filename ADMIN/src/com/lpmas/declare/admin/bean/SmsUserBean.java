package com.lpmas.declare.admin.bean;

import java.sql.Timestamp;
import com.lpmas.framework.annotation.FieldTag;

public class SmsUserBean {
	@FieldTag(name = "短信用户ID")
	private int smsUserId = 0;
	@FieldTag(name = "短信用户名称")
	private String smsUserName = "";
	@FieldTag(name = "短信用户省份")
	private String smsUserProvince = "";
	@FieldTag(name = "短信用户手机")
	private String smsUserMobile = "";
	@FieldTag(name = "短信用户单位")
	private String smsUserCorporate = "";
	@FieldTag(name = "状态")
	private int status = 0;
	@FieldTag(name = "创建时间")
	private Timestamp createTime = null;
	@FieldTag(name = "创建用户")
	private int createUser = 0;
	@FieldTag(name = "修改时间")
	private Timestamp modifyTime = null;
	@FieldTag(name = "修改用户")
	private int modifyUser = 0;
	@FieldTag(name = "备注")
	private String memo = "";

	public int getSmsUserId() {
		return smsUserId;
	}

	public void setSmsUserId(int smsUserId) {
		this.smsUserId = smsUserId;
	}

	public String getSmsUserName() {
		return smsUserName;
	}

	public void setSmsUserName(String smsUserName) {
		this.smsUserName = smsUserName;
	}

	public String getSmsUserProvince() {
		return smsUserProvince;
	}

	public void setSmsUserProvince(String smsUserProvince) {
		this.smsUserProvince = smsUserProvince;
	}

	public String getSmsUserMobile() {
		return smsUserMobile;
	}

	public void setSmsUserMobile(String smsUserMobile) {
		this.smsUserMobile = smsUserMobile;
	}

	public String getSmsUserCorporate() {
		return smsUserCorporate;
	}

	public void setSmsUserCorporate(String smsUserCorporate) {
		this.smsUserCorporate = smsUserCorporate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getCreateUser() {
		return createUser;
	}

	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(int modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}