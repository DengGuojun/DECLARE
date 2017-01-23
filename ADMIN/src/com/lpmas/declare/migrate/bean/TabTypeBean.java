package com.lpmas.declare.migrate.bean;

import com.lpmas.framework.annotation.FieldTag;

public class TabTypeBean {
	@FieldTag(name = "")
	private int id = 0;
	@FieldTag(name = "")
	private int pid = 0;
	@FieldTag(name = "")
	private String name = "";
	@FieldTag(name = "")
	private int sort = 0;
	@FieldTag(name = "")
	private int state = 0;
	@FieldTag(name = "")
	private int type = 0;
	@FieldTag(name = "")
	private String remark = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}