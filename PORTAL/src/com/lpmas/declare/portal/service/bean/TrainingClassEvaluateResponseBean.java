package com.lpmas.declare.portal.service.bean;

public class TrainingClassEvaluateResponseBean {

	private int classId;
	private int itemId;
	private String itemName;
	private double evaluateScore;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public double getEvaluateScore() {
		return evaluateScore;
	}

	public void setEvaluateScore(double evaluateScore) {
		this.evaluateScore = evaluateScore;
	}

}
