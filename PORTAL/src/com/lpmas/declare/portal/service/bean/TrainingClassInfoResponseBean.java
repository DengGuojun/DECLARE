package com.lpmas.declare.portal.service.bean;


public class TrainingClassInfoResponseBean {

	private int classId = 0;
	private int eduClassId = 0;
	private String className = "";
	private String majorTypeName = "";
	private String majorName = "";
	private boolean hasEvaluate = false;
	private boolean isEvaluateFinished = false;
	

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}
	
	public int getEduClassId() {
		return eduClassId;
	}

	public void setEduClassId(int eduClassId) {
		this.eduClassId = eduClassId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMajorTypeName() {
		return majorTypeName;
	}

	public void setMajorTypeName(String majorTypeName) {
		this.majorTypeName = majorTypeName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public boolean isHasEvaluate() {
		return hasEvaluate;
	}

	public void setHasEvaluate(boolean hasEvaluate) {
		this.hasEvaluate = hasEvaluate;
	}

	public boolean isEvaluateFinished() {
		return isEvaluateFinished;
	}

	public void setEvaluateFinished(boolean isEvaluateFinished) {
		this.isEvaluateFinished = isEvaluateFinished;
	}

}
