package com.lpmas.declare.admin.bean;
import java.sql.Timestamp;
import com.lpmas.framework.annotation.FieldTag;
public class TeacherEvaluateBean{
@FieldTag(name="评价ID")
private int evaluateId = 0;
@FieldTag(name="师资ID")
private int teacherId = 0;
@FieldTag(name="课程名称")
private String className = "";
@FieldTag(name="培训机构")
private String trainingOrganization = "";
@FieldTag(name="培训时间")
private String trainingTime = "";
@FieldTag(name="学员评价")
private double evaluateScore = 0;
@FieldTag(name="培训地点")
private String trainingAddress = "";
@FieldTag(name="教师评语")
private String teacherComment = "";
@FieldTag(name="状态")
private int status = 0;
@FieldTag(name="创建时间")
private Timestamp createTime = null;
@FieldTag(name="创建用户")
private int createUser = 0;
@FieldTag(name="修改时间")
private Timestamp modifyTime = null;
@FieldTag(name="修改用户")
private int modifyUser = 0;
@FieldTag(name="备注")
private String memo = "";

public int getEvaluateId() {return evaluateId;}
public void setEvaluateId(int evaluateId) {this.evaluateId = evaluateId;}
public int getTeacherId() {return teacherId;}
public void setTeacherId(int teacherId) {this.teacherId = teacherId;}
public String getClassName() {return className;}
public void setClassName(String className) {this.className = className;}
public String getTrainingOrganization() {return trainingOrganization;}
public void setTrainingOrganization(String trainingOrganization) {this.trainingOrganization = trainingOrganization;}
public String getTrainingTime() {return trainingTime;}
public void setTrainingTime(String trainingTime) {this.trainingTime = trainingTime;}
public double getEvaluateScore() {return evaluateScore;}
public void setEvaluateScore(double evaluateScore) {this.evaluateScore = evaluateScore;}
public String getTrainingAddress() {return trainingAddress;}
public void setTrainingAddress(String trainingAddress) {this.trainingAddress = trainingAddress;}
public String getTeacherComment() {return teacherComment;}
public void setTeacherComment(String teacherComment) {this.teacherComment = teacherComment;}
public int getStatus() {return status;}
public void setStatus(int status) {this.status = status;}
public Timestamp getCreateTime() {return createTime;}
public void setCreateTime(Timestamp createTime) {this.createTime = createTime;}
public int getCreateUser() {return createUser;}
public void setCreateUser(int createUser) {this.createUser = createUser;}
public Timestamp getModifyTime() {return modifyTime;}
public void setModifyTime(Timestamp modifyTime) {this.modifyTime = modifyTime;}
public int getModifyUser() {return modifyUser;}
public void setModifyUser(int modifyUser) {this.modifyUser = modifyUser;}
public String getMemo() {return memo;}
public void setMemo(String memo) {this.memo = memo;}
}