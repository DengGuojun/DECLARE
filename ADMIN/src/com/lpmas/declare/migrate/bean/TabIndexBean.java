package com.lpmas.declare.migrate.bean;

import com.lpmas.framework.annotation.FieldTag;

public class TabIndexBean {
	@FieldTag(name = "index_id")
	private int indexId = 0;
	@FieldTag(name = "id")
	private int id = 0;
	@FieldTag(name = "name")
	private String name = "";
	@FieldTag(name = "parent_id")
	private int parentId = 0;

	public int getIndexId() {
		return indexId;
	}

	public void setIndexId(int indexId) {
		this.indexId = indexId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}