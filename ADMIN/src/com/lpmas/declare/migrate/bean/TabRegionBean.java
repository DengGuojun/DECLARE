package com.lpmas.declare.migrate.bean;

import com.lpmas.framework.annotation.FieldTag;

public class TabRegionBean {
	@FieldTag(name = "id")
	private int id = 0;
	@FieldTag(name = "server_id")
	private String serverId = "";
	@FieldTag(name = "code")
	private String code = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}