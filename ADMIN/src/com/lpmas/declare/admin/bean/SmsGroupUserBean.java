package com.lpmas.declare.admin.bean;

import com.lpmas.framework.annotation.FieldTag;

public class SmsGroupUserBean {
	@FieldTag(name = "通讯组ID")
	private int groupId = 0;
	@FieldTag(name = "短信用户ID")
	private int smsUserId = 0;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getSmsUserId() {
		return smsUserId;
	}

	public void setSmsUserId(int smsUserId) {
		this.smsUserId = smsUserId;
	}
}