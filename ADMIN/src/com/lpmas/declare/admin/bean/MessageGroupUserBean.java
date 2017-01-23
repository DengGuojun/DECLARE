package com.lpmas.declare.admin.bean;

import com.lpmas.framework.annotation.FieldTag;

public class MessageGroupUserBean {
	@FieldTag(name = "通讯组ID")
	private int groupId = 0;
	@FieldTag(name = "用户ID")
	private int userId = 0;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}