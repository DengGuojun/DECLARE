package com.lpmas.declare.invoker.bean;

public class ClassRoomMemberAddBean {

	private String classroomId = "";
	private String userId = "";
	private String activationCode = "";

	public ClassRoomMemberAddBean() {
	}

	public String getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
}
