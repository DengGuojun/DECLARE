package com.lpmas.declare.admin.bean;

import org.bson.types.ObjectId;

import com.lpmas.framework.nosql.mongodb.MongoDBDocumentBean;

public class DeclareStatisticsBean extends MongoDBDocumentBean<ObjectId> {

	private String province = ""; // 省
	private String city = ""; // 市
	private String region = ""; // 区
	private String level = ""; // 区域等级
	private boolean isSum = false;
	private String declareYear = ""; // 培育年份

	private int count = 0; // 总数
	private int approveCount = 0; // 审核通过人数
	private int rejectCount = 0; // 审核不通过人数
	private int waitApproveCount = 0; // 待审核人数
	private int count1 = 0;
	private int approveCount1 = 0;
	private int rejectCount1 = 0;
	private int waitApproveCount1 = 0;
	private int count2 = 0;
	private int approveCount2 = 0;
	private int rejectCount2 = 0;
	private int waitApproveCount2 = 0;
	private int count3 = 0;
	private int approveCount3 = 0;
	private int rejectCount3 = 0;
	private int waitApproveCount3 = 0;
	private int count4 = 0;
	private int approveCount4 = 0;
	private int rejectCount4 = 0;
	private int waitApproveCount4 = 0;
	private int count5 = 0;
	private int approveCount5 = 0;
	private int rejectCount5 = 0;
	private int waitApproveCount5 = 0;

	private int peopleCount1 = 0; // 总人数
	private int peopleCount2 = 0; // 总人数
	private int peopleCount3 = 0; // 总人数
	private int peopleCount4 = 0; // 总人数
	private int peopleCount5 = 0; // 总人数
	// 性别
	private int male1 = 0; // 男性
	private int male2 = 0; // 男性
	private int male3 = 0; // 男性
	private int male4 = 0; // 男性
	private int male5 = 0; // 男性
	private int female1 = 0; // 女性
	private int female2 = 0; // 女性
	private int female3 = 0; // 女性
	private int female4 = 0; // 女性
	private int female5 = 0; // 女性
	// 年龄
	private int ageLevel1Num1 = 0; // 18-25
	private int ageLevel1Num2 = 0; // 18-25
	private int ageLevel1Num3 = 0; // 18-25
	private int ageLevel1Num4 = 0; // 18-25
	private int ageLevel1Num5 = 0; // 18-25
	private int ageLevel2Num1 = 0; // 26-35
	private int ageLevel2Num2 = 0; // 26-35
	private int ageLevel2Num3 = 0; // 26-35
	private int ageLevel2Num4 = 0; // 26-35
	private int ageLevel2Num5 = 0; // 26-35
	private int ageLevel3Num1 = 0; // 36-45
	private int ageLevel3Num2 = 0; // 36-45
	private int ageLevel3Num3 = 0; // 36-45
	private int ageLevel3Num4 = 0; // 36-45
	private int ageLevel3Num5 = 0; // 36-45
	private int ageLevel4Num1 = 0; // 46-60
	private int ageLevel4Num2 = 0; // 46-60
	private int ageLevel4Num3 = 0; // 46-60
	private int ageLevel4Num4 = 0; // 46-60
	private int ageLevel4Num5 = 0; // 46-60
	private int ageLevel5Num1 = 0; // 60岁以上
	private int ageLevel5Num2 = 0; // 60岁以上
	private int ageLevel5Num3 = 0; // 60岁以上
	private int ageLevel5Num4 = 0; // 60岁以上
	private int ageLevel5Num5 = 0; // 60岁以上
	// 文化程度
	private int cultureLevel1Num1 = 0; // 小学及以下
	private int cultureLevel1Num2 = 0; // 小学及以下
	private int cultureLevel1Num3 = 0; // 小学及以下
	private int cultureLevel1Num4 = 0; // 小学及以下
	private int cultureLevel1Num5 = 0; // 小学及以下
	private int cultureLevel2Num1 = 0; // 初中
	private int cultureLevel2Num2 = 0; // 初中
	private int cultureLevel2Num3 = 0; // 初中
	private int cultureLevel2Num4 = 0; // 初中
	private int cultureLevel2Num5 = 0; // 初中
	private int cultureLevel3Num1 = 0; // 高中/中专
	private int cultureLevel3Num2 = 0; // 高中/中专
	private int cultureLevel3Num3 = 0; // 高中/中专
	private int cultureLevel3Num4 = 0; // 高中/中专
	private int cultureLevel3Num5 = 0; // 高中/中专
	private int cultureLevel4Num1 = 0; // 大专及以上
	private int cultureLevel4Num2 = 0; // 大专及以上
	private int cultureLevel4Num3 = 0; // 大专及以上
	private int cultureLevel4Num4 = 0; // 大专及以上
	private int cultureLevel4Num5 = 0; // 大专及以上
	// 政治面貌
	private int politicalLevel1Num1 = 0; // 中共党员
	private int politicalLevel1Num2 = 0; // 中共党员
	private int politicalLevel1Num3 = 0; // 中共党员
	private int politicalLevel1Num4 = 0; // 中共党员
	private int politicalLevel1Num5 = 0; // 中共党员
	private int politicalLevel2Num1 = 0; // 共青团员
	private int politicalLevel2Num2 = 0; // 共青团员
	private int politicalLevel2Num3 = 0; // 共青团员
	private int politicalLevel2Num4 = 0; // 共青团员
	private int politicalLevel2Num5 = 0; // 共青团员
	private int politicalLevel3Num1 = 0; // 群众
	private int politicalLevel3Num2 = 0; // 群众
	private int politicalLevel3Num3 = 0; // 群众
	private int politicalLevel3Num4 = 0; // 群众
	private int politicalLevel3Num5 = 0; // 群众
	private int politicalLevel4Num1 = 0; // 其他
	private int politicalLevel4Num2 = 0; // 其他
	private int politicalLevel4Num3 = 0; // 其他
	private int politicalLevel4Num4 = 0; // 其他
	private int politicalLevel4Num5 = 0; // 其他
	// 申报人数
	private int personal_declaration1 = 0; // 个人申报
	private int personal_declaration2 = 0; // 个人申报
	private int personal_declaration3 = 0; // 个人申报
	private int personal_declaration4 = 0; // 个人申报
	private int personal_declaration5 = 0; // 个人申报
	private int organization_recommended1 = 0; // 组织推荐
	private int organization_recommended2 = 0; // 组织推荐
	private int organization_recommended3 = 0; // 组织推荐
	private int organization_recommended4 = 0; // 组织推荐
	private int organization_recommended5 = 0; // 组织推荐

	// 主体产业
	private int type1industry1 = 0; // 粮食作物
	private int type1industry2 = 0; // 油粮作物
	private int type1industry3 = 0; // 经济作物
	private int type1industry4 = 0; // 园艺作物
	private int type1industry5 = 0; // 家畜
	private int type1industry6 = 0; // 家禽
	private int type1industry7 = 0; // 特种动物
	private int type1industry8 = 0; // 海水
	private int type1industry9 = 0; // 淡水
	private int type1industry10 = 0; // 其他产业

	private int type2industry1 = 0; // 粮食作物
	private int type2industry2 = 0; // 油粮作物
	private int type2industry3 = 0; // 经济作物
	private int type2industry4 = 0; // 园艺作物
	private int type2industry5 = 0; // 家畜
	private int type2industry6 = 0; // 家禽
	private int type2industry7 = 0; // 特种动物
	private int type2industry8 = 0; // 海水
	private int type2industry9 = 0; // 淡水
	private int type2industry10 = 0; // 其他产业

	private int type5industry1 = 0; // 粮食作物
	private int type5industry2 = 0; // 油粮作物
	private int type5industry3 = 0; // 经济作物
	private int type5industry4 = 0; // 园艺作物
	private int type5industry5 = 0; // 家畜
	private int type5industry6 = 0; // 家禽
	private int type5industry7 = 0; // 特种动物
	private int type5industry8 = 0; // 海水
	private int type5industry9 = 0; // 淡水
	private int type5industry10 = 0; // 其他产业

	private int type3job1 = 0; // 农艺工
	private int type3job2 = 0; // 园艺工
	private int type3job3 = 0; // 牧草工
	private int type3job4 = 0; // 热带作物生产工
	private int type3job5 = 0; // 家畜繁殖员
	private int type3job6 = 0; // 家畜饲养员
	private int type3job7 = 0; // 家禽繁殖员
	private int type3job8 = 0; // 家禽饲养员
	private int type3job9 = 0; // 特种动物饲养员
	private int type3job10 = 0; // 实验动物养殖员
	private int type3job11 = 0; // 渔业生产船员
	private int type3job12 = 0; // 水生动物苗种繁育工
	private int type3job13 = 0; // 水生植物苗种繁育工
	private int type3job14 = 0; // 水生动物饲养工
	private int type3job15 = 0; // 水生植物栽培工
	private int type3job16 = 0; // 珍珠养殖工
	private int type3job17 = 0; // 水产捕捞员
	private int type3job18 = 0; // 其他

	private int type4job1 = 0; // 农艺工
	private int type4job2 = 0; // 园艺工
	private int type4job3 = 0; // 牧草工
	private int type4job4 = 0; // 热带作物生产工
	private int type4job5 = 0; // 家畜繁殖员
	private int type4job6 = 0; // 家畜饲养员
	private int type4job7 = 0; // 家禽繁殖员
	private int type4job8 = 0; // 家禽饲养员
	private int type4job9 = 0; // 特种动物饲养员
	private int type4job10 = 0; // 实验动物养殖员
	private int type4job11 = 0; // 渔业生产船员
	private int type4job12 = 0; // 水生动物苗种繁育工
	private int type4job13 = 0; // 水生植物苗种繁育工
	private int type4job14 = 0; // 水生动物饲养工
	private int type4job15 = 0; // 水生植物栽培工
	private int type4job16 = 0; // 珍珠养殖工
	private int type4job17 = 0; // 水产捕捞员
	private int type4job18 = 0; // 其他

	// 人员类别
	private int planting_large1 = 0; // 种植大户
	private int scale_farm1 = 0; // 规模养殖场经营者
	private int family_farm1 = 0; // 家庭农场经营者
	private int farmer_cooperatives1 = 0; // 农民合作社骨干
	private int college_students1 = 0; // 创业大学生
	private int vocational_graduates1 = 0; // 中高职毕业生
	private int migrant_workers1 = 0; // 返乡农民工
	private int veteran1 = 0; // 退伍军人

	private int planting_large2 = 0; // 种植大户
	private int scale_farm2 = 0; // 规模养殖场经营者
	private int family_farm2 = 0; // 家庭农场经营者
	private int agricultural_enterprises2 = 0; // 农业企业负责人
	private int farmer_cooperatives2 = 0; // 农民合作社骨干
	private int college_students2 = 0; // 创业大学生
	private int vocational_graduates2 = 0; // 中高职毕业生
	private int migrant_workers2 = 0; // 返乡农民工
	private int veteran2 = 0; // 退伍军人

	private int planting_large5 = 0; // 种植大户
	private int scale_farm5 = 0; // 规模养殖场经营者
	private int family_farm5 = 0; // 家庭农场经营者
	private int farmer_cooperatives5 = 0; // 农民合作社骨干
	private int agricultural_enterprises5 = 0; // 农业企业负责人
	private int service_experts5 = 0; // 农业社会化服务组织服务能手

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public boolean isSum() {
		return isSum;
	}

	public void setSum(boolean isSum) {
		this.isSum = isSum;
	}

	public String getDeclareYear() {
		return declareYear;
	}

	public void setDeclareYear(String declareYear) {
		this.declareYear = declareYear;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getApproveCount() {
		return approveCount;
	}

	public void setApproveCount(int approveCount) {
		this.approveCount = approveCount;
	}

	public int getRejectCount() {
		return rejectCount;
	}

	public void setRejectCount(int rejectCount) {
		this.rejectCount = rejectCount;
	}

	public int getWaitApproveCount() {
		return waitApproveCount;
	}

	public void setWaitApproveCount(int waitApproveCount) {
		this.waitApproveCount = waitApproveCount;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getApproveCount1() {
		return approveCount1;
	}

	public void setApproveCount1(int approveCount1) {
		this.approveCount1 = approveCount1;
	}

	public int getRejectCount1() {
		return rejectCount1;
	}

	public void setRejectCount1(int rejectCount1) {
		this.rejectCount1 = rejectCount1;
	}

	public int getWaitApproveCount1() {
		return waitApproveCount1;
	}

	public void setWaitApproveCount1(int waitApproveCount1) {
		this.waitApproveCount1 = waitApproveCount1;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getApproveCount2() {
		return approveCount2;
	}

	public void setApproveCount2(int approveCount2) {
		this.approveCount2 = approveCount2;
	}

	public int getRejectCount2() {
		return rejectCount2;
	}

	public void setRejectCount2(int rejectCount2) {
		this.rejectCount2 = rejectCount2;
	}

	public int getWaitApproveCount2() {
		return waitApproveCount2;
	}

	public void setWaitApproveCount2(int waitApproveCount2) {
		this.waitApproveCount2 = waitApproveCount2;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public int getApproveCount3() {
		return approveCount3;
	}

	public void setApproveCount3(int approveCount3) {
		this.approveCount3 = approveCount3;
	}

	public int getRejectCount3() {
		return rejectCount3;
	}

	public void setRejectCount3(int rejectCount3) {
		this.rejectCount3 = rejectCount3;
	}

	public int getWaitApproveCount3() {
		return waitApproveCount3;
	}

	public void setWaitApproveCount3(int waitApproveCount3) {
		this.waitApproveCount3 = waitApproveCount3;
	}

	public int getCount4() {
		return count4;
	}

	public void setCount4(int count4) {
		this.count4 = count4;
	}

	public int getApproveCount4() {
		return approveCount4;
	}

	public void setApproveCount4(int approveCount4) {
		this.approveCount4 = approveCount4;
	}

	public int getRejectCount4() {
		return rejectCount4;
	}

	public void setRejectCount4(int rejectCount4) {
		this.rejectCount4 = rejectCount4;
	}

	public int getWaitApproveCount4() {
		return waitApproveCount4;
	}

	public void setWaitApproveCount4(int waitApproveCount4) {
		this.waitApproveCount4 = waitApproveCount4;
	}

	public int getCount5() {
		return count5;
	}

	public void setCount5(int count5) {
		this.count5 = count5;
	}

	public int getApproveCount5() {
		return approveCount5;
	}

	public void setApproveCount5(int approveCount5) {
		this.approveCount5 = approveCount5;
	}

	public int getRejectCount5() {
		return rejectCount5;
	}

	public void setRejectCount5(int rejectCount5) {
		this.rejectCount5 = rejectCount5;
	}

	public int getWaitApproveCount5() {
		return waitApproveCount5;
	}

	public void setWaitApproveCount5(int waitApproveCount5) {
		this.waitApproveCount5 = waitApproveCount5;
	}

	public int getPeopleCount1() {
		return peopleCount1;
	}

	public void setPeopleCount1(int peopleCount1) {
		this.peopleCount1 = peopleCount1;
	}

	public int getPeopleCount2() {
		return peopleCount2;
	}

	public void setPeopleCount2(int peopleCount2) {
		this.peopleCount2 = peopleCount2;
	}

	public int getPeopleCount3() {
		return peopleCount3;
	}

	public void setPeopleCount3(int peopleCount3) {
		this.peopleCount3 = peopleCount3;
	}

	public int getPeopleCount4() {
		return peopleCount4;
	}

	public void setPeopleCount4(int peopleCount4) {
		this.peopleCount4 = peopleCount4;
	}

	public int getPeopleCount5() {
		return peopleCount5;
	}

	public void setPeopleCount5(int peopleCount5) {
		this.peopleCount5 = peopleCount5;
	}

	public int getMale1() {
		return male1;
	}

	public void setMale1(int male1) {
		this.male1 = male1;
	}

	public int getMale2() {
		return male2;
	}

	public void setMale2(int male2) {
		this.male2 = male2;
	}

	public int getMale3() {
		return male3;
	}

	public void setMale3(int male3) {
		this.male3 = male3;
	}

	public int getMale4() {
		return male4;
	}

	public void setMale4(int male4) {
		this.male4 = male4;
	}

	public int getMale5() {
		return male5;
	}

	public void setMale5(int male5) {
		this.male5 = male5;
	}

	public int getFemale1() {
		return female1;
	}

	public void setFemale1(int female1) {
		this.female1 = female1;
	}

	public int getFemale2() {
		return female2;
	}

	public void setFemale2(int female2) {
		this.female2 = female2;
	}

	public int getFemale3() {
		return female3;
	}

	public void setFemale3(int female3) {
		this.female3 = female3;
	}

	public int getFemale4() {
		return female4;
	}

	public void setFemale4(int female4) {
		this.female4 = female4;
	}

	public int getFemale5() {
		return female5;
	}

	public void setFemale5(int female5) {
		this.female5 = female5;
	}

	public int getAgeLevel1Num1() {
		return ageLevel1Num1;
	}

	public void setAgeLevel1Num1(int ageLevel1Num1) {
		this.ageLevel1Num1 = ageLevel1Num1;
	}

	public int getAgeLevel1Num2() {
		return ageLevel1Num2;
	}

	public void setAgeLevel1Num2(int ageLevel1Num2) {
		this.ageLevel1Num2 = ageLevel1Num2;
	}

	public int getAgeLevel1Num3() {
		return ageLevel1Num3;
	}

	public void setAgeLevel1Num3(int ageLevel1Num3) {
		this.ageLevel1Num3 = ageLevel1Num3;
	}

	public int getAgeLevel1Num4() {
		return ageLevel1Num4;
	}

	public void setAgeLevel1Num4(int ageLevel1Num4) {
		this.ageLevel1Num4 = ageLevel1Num4;
	}

	public int getAgeLevel1Num5() {
		return ageLevel1Num5;
	}

	public void setAgeLevel1Num5(int ageLevel1Num5) {
		this.ageLevel1Num5 = ageLevel1Num5;
	}

	public int getAgeLevel2Num1() {
		return ageLevel2Num1;
	}

	public void setAgeLevel2Num1(int ageLevel2Num1) {
		this.ageLevel2Num1 = ageLevel2Num1;
	}

	public int getAgeLevel2Num2() {
		return ageLevel2Num2;
	}

	public void setAgeLevel2Num2(int ageLevel2Num2) {
		this.ageLevel2Num2 = ageLevel2Num2;
	}

	public int getAgeLevel2Num3() {
		return ageLevel2Num3;
	}

	public void setAgeLevel2Num3(int ageLevel2Num3) {
		this.ageLevel2Num3 = ageLevel2Num3;
	}

	public int getAgeLevel2Num4() {
		return ageLevel2Num4;
	}

	public void setAgeLevel2Num4(int ageLevel2Num4) {
		this.ageLevel2Num4 = ageLevel2Num4;
	}

	public int getAgeLevel2Num5() {
		return ageLevel2Num5;
	}

	public void setAgeLevel2Num5(int ageLevel2Num5) {
		this.ageLevel2Num5 = ageLevel2Num5;
	}

	public int getAgeLevel3Num1() {
		return ageLevel3Num1;
	}

	public void setAgeLevel3Num1(int ageLevel3Num1) {
		this.ageLevel3Num1 = ageLevel3Num1;
	}

	public int getAgeLevel3Num2() {
		return ageLevel3Num2;
	}

	public void setAgeLevel3Num2(int ageLevel3Num2) {
		this.ageLevel3Num2 = ageLevel3Num2;
	}

	public int getAgeLevel3Num3() {
		return ageLevel3Num3;
	}

	public void setAgeLevel3Num3(int ageLevel3Num3) {
		this.ageLevel3Num3 = ageLevel3Num3;
	}

	public int getAgeLevel3Num4() {
		return ageLevel3Num4;
	}

	public void setAgeLevel3Num4(int ageLevel3Num4) {
		this.ageLevel3Num4 = ageLevel3Num4;
	}

	public int getAgeLevel3Num5() {
		return ageLevel3Num5;
	}

	public void setAgeLevel3Num5(int ageLevel3Num5) {
		this.ageLevel3Num5 = ageLevel3Num5;
	}

	public int getAgeLevel4Num1() {
		return ageLevel4Num1;
	}

	public void setAgeLevel4Num1(int ageLevel4Num1) {
		this.ageLevel4Num1 = ageLevel4Num1;
	}

	public int getAgeLevel4Num2() {
		return ageLevel4Num2;
	}

	public void setAgeLevel4Num2(int ageLevel4Num2) {
		this.ageLevel4Num2 = ageLevel4Num2;
	}

	public int getAgeLevel4Num3() {
		return ageLevel4Num3;
	}

	public void setAgeLevel4Num3(int ageLevel4Num3) {
		this.ageLevel4Num3 = ageLevel4Num3;
	}

	public int getAgeLevel4Num4() {
		return ageLevel4Num4;
	}

	public void setAgeLevel4Num4(int ageLevel4Num4) {
		this.ageLevel4Num4 = ageLevel4Num4;
	}

	public int getAgeLevel4Num5() {
		return ageLevel4Num5;
	}

	public void setAgeLevel4Num5(int ageLevel4Num5) {
		this.ageLevel4Num5 = ageLevel4Num5;
	}

	public int getAgeLevel5Num1() {
		return ageLevel5Num1;
	}

	public void setAgeLevel5Num1(int ageLevel5Num1) {
		this.ageLevel5Num1 = ageLevel5Num1;
	}

	public int getAgeLevel5Num2() {
		return ageLevel5Num2;
	}

	public void setAgeLevel5Num2(int ageLevel5Num2) {
		this.ageLevel5Num2 = ageLevel5Num2;
	}

	public int getAgeLevel5Num3() {
		return ageLevel5Num3;
	}

	public void setAgeLevel5Num3(int ageLevel5Num3) {
		this.ageLevel5Num3 = ageLevel5Num3;
	}

	public int getAgeLevel5Num4() {
		return ageLevel5Num4;
	}

	public void setAgeLevel5Num4(int ageLevel5Num4) {
		this.ageLevel5Num4 = ageLevel5Num4;
	}

	public int getAgeLevel5Num5() {
		return ageLevel5Num5;
	}

	public void setAgeLevel5Num5(int ageLevel5Num5) {
		this.ageLevel5Num5 = ageLevel5Num5;
	}

	public int getCultureLevel1Num1() {
		return cultureLevel1Num1;
	}

	public void setCultureLevel1Num1(int cultureLevel1Num1) {
		this.cultureLevel1Num1 = cultureLevel1Num1;
	}

	public int getCultureLevel1Num2() {
		return cultureLevel1Num2;
	}

	public void setCultureLevel1Num2(int cultureLevel1Num2) {
		this.cultureLevel1Num2 = cultureLevel1Num2;
	}

	public int getCultureLevel1Num3() {
		return cultureLevel1Num3;
	}

	public void setCultureLevel1Num3(int cultureLevel1Num3) {
		this.cultureLevel1Num3 = cultureLevel1Num3;
	}

	public int getCultureLevel1Num4() {
		return cultureLevel1Num4;
	}

	public void setCultureLevel1Num4(int cultureLevel1Num4) {
		this.cultureLevel1Num4 = cultureLevel1Num4;
	}

	public int getCultureLevel1Num5() {
		return cultureLevel1Num5;
	}

	public void setCultureLevel1Num5(int cultureLevel1Num5) {
		this.cultureLevel1Num5 = cultureLevel1Num5;
	}

	public int getCultureLevel2Num1() {
		return cultureLevel2Num1;
	}

	public void setCultureLevel2Num1(int cultureLevel2Num1) {
		this.cultureLevel2Num1 = cultureLevel2Num1;
	}

	public int getCultureLevel2Num2() {
		return cultureLevel2Num2;
	}

	public void setCultureLevel2Num2(int cultureLevel2Num2) {
		this.cultureLevel2Num2 = cultureLevel2Num2;
	}

	public int getCultureLevel2Num3() {
		return cultureLevel2Num3;
	}

	public void setCultureLevel2Num3(int cultureLevel2Num3) {
		this.cultureLevel2Num3 = cultureLevel2Num3;
	}

	public int getCultureLevel2Num4() {
		return cultureLevel2Num4;
	}

	public void setCultureLevel2Num4(int cultureLevel2Num4) {
		this.cultureLevel2Num4 = cultureLevel2Num4;
	}

	public int getCultureLevel2Num5() {
		return cultureLevel2Num5;
	}

	public void setCultureLevel2Num5(int cultureLevel2Num5) {
		this.cultureLevel2Num5 = cultureLevel2Num5;
	}

	public int getCultureLevel3Num1() {
		return cultureLevel3Num1;
	}

	public void setCultureLevel3Num1(int cultureLevel3Num1) {
		this.cultureLevel3Num1 = cultureLevel3Num1;
	}

	public int getCultureLevel3Num2() {
		return cultureLevel3Num2;
	}

	public void setCultureLevel3Num2(int cultureLevel3Num2) {
		this.cultureLevel3Num2 = cultureLevel3Num2;
	}

	public int getCultureLevel3Num3() {
		return cultureLevel3Num3;
	}

	public void setCultureLevel3Num3(int cultureLevel3Num3) {
		this.cultureLevel3Num3 = cultureLevel3Num3;
	}

	public int getCultureLevel3Num4() {
		return cultureLevel3Num4;
	}

	public void setCultureLevel3Num4(int cultureLevel3Num4) {
		this.cultureLevel3Num4 = cultureLevel3Num4;
	}

	public int getCultureLevel3Num5() {
		return cultureLevel3Num5;
	}

	public void setCultureLevel3Num5(int cultureLevel3Num5) {
		this.cultureLevel3Num5 = cultureLevel3Num5;
	}

	public int getCultureLevel4Num1() {
		return cultureLevel4Num1;
	}

	public void setCultureLevel4Num1(int cultureLevel4Num1) {
		this.cultureLevel4Num1 = cultureLevel4Num1;
	}

	public int getCultureLevel4Num2() {
		return cultureLevel4Num2;
	}

	public void setCultureLevel4Num2(int cultureLevel4Num2) {
		this.cultureLevel4Num2 = cultureLevel4Num2;
	}

	public int getCultureLevel4Num3() {
		return cultureLevel4Num3;
	}

	public void setCultureLevel4Num3(int cultureLevel4Num3) {
		this.cultureLevel4Num3 = cultureLevel4Num3;
	}

	public int getCultureLevel4Num4() {
		return cultureLevel4Num4;
	}

	public void setCultureLevel4Num4(int cultureLevel4Num4) {
		this.cultureLevel4Num4 = cultureLevel4Num4;
	}

	public int getCultureLevel4Num5() {
		return cultureLevel4Num5;
	}

	public void setCultureLevel4Num5(int cultureLevel4Num5) {
		this.cultureLevel4Num5 = cultureLevel4Num5;
	}

	public int getPoliticalLevel1Num1() {
		return politicalLevel1Num1;
	}

	public void setPoliticalLevel1Num1(int politicalLevel1Num1) {
		this.politicalLevel1Num1 = politicalLevel1Num1;
	}

	public int getPoliticalLevel1Num2() {
		return politicalLevel1Num2;
	}

	public void setPoliticalLevel1Num2(int politicalLevel1Num2) {
		this.politicalLevel1Num2 = politicalLevel1Num2;
	}

	public int getPoliticalLevel1Num3() {
		return politicalLevel1Num3;
	}

	public void setPoliticalLevel1Num3(int politicalLevel1Num3) {
		this.politicalLevel1Num3 = politicalLevel1Num3;
	}

	public int getPoliticalLevel1Num4() {
		return politicalLevel1Num4;
	}

	public void setPoliticalLevel1Num4(int politicalLevel1Num4) {
		this.politicalLevel1Num4 = politicalLevel1Num4;
	}

	public int getPoliticalLevel1Num5() {
		return politicalLevel1Num5;
	}

	public void setPoliticalLevel1Num5(int politicalLevel1Num5) {
		this.politicalLevel1Num5 = politicalLevel1Num5;
	}

	public int getPoliticalLevel2Num1() {
		return politicalLevel2Num1;
	}

	public void setPoliticalLevel2Num1(int politicalLevel2Num1) {
		this.politicalLevel2Num1 = politicalLevel2Num1;
	}

	public int getPoliticalLevel2Num2() {
		return politicalLevel2Num2;
	}

	public void setPoliticalLevel2Num2(int politicalLevel2Num2) {
		this.politicalLevel2Num2 = politicalLevel2Num2;
	}

	public int getPoliticalLevel2Num3() {
		return politicalLevel2Num3;
	}

	public void setPoliticalLevel2Num3(int politicalLevel2Num3) {
		this.politicalLevel2Num3 = politicalLevel2Num3;
	}

	public int getPoliticalLevel2Num4() {
		return politicalLevel2Num4;
	}

	public void setPoliticalLevel2Num4(int politicalLevel2Num4) {
		this.politicalLevel2Num4 = politicalLevel2Num4;
	}

	public int getPoliticalLevel2Num5() {
		return politicalLevel2Num5;
	}

	public void setPoliticalLevel2Num5(int politicalLevel2Num5) {
		this.politicalLevel2Num5 = politicalLevel2Num5;
	}

	public int getPoliticalLevel3Num1() {
		return politicalLevel3Num1;
	}

	public void setPoliticalLevel3Num1(int politicalLevel3Num1) {
		this.politicalLevel3Num1 = politicalLevel3Num1;
	}

	public int getPoliticalLevel3Num2() {
		return politicalLevel3Num2;
	}

	public void setPoliticalLevel3Num2(int politicalLevel3Num2) {
		this.politicalLevel3Num2 = politicalLevel3Num2;
	}

	public int getPoliticalLevel3Num3() {
		return politicalLevel3Num3;
	}

	public void setPoliticalLevel3Num3(int politicalLevel3Num3) {
		this.politicalLevel3Num3 = politicalLevel3Num3;
	}

	public int getPoliticalLevel3Num4() {
		return politicalLevel3Num4;
	}

	public void setPoliticalLevel3Num4(int politicalLevel3Num4) {
		this.politicalLevel3Num4 = politicalLevel3Num4;
	}

	public int getPoliticalLevel3Num5() {
		return politicalLevel3Num5;
	}

	public void setPoliticalLevel3Num5(int politicalLevel3Num5) {
		this.politicalLevel3Num5 = politicalLevel3Num5;
	}

	public int getPoliticalLevel4Num1() {
		return politicalLevel4Num1;
	}

	public void setPoliticalLevel4Num1(int politicalLevel4Num1) {
		this.politicalLevel4Num1 = politicalLevel4Num1;
	}

	public int getPoliticalLevel4Num2() {
		return politicalLevel4Num2;
	}

	public void setPoliticalLevel4Num2(int politicalLevel4Num2) {
		this.politicalLevel4Num2 = politicalLevel4Num2;
	}

	public int getPoliticalLevel4Num3() {
		return politicalLevel4Num3;
	}

	public void setPoliticalLevel4Num3(int politicalLevel4Num3) {
		this.politicalLevel4Num3 = politicalLevel4Num3;
	}

	public int getPoliticalLevel4Num4() {
		return politicalLevel4Num4;
	}

	public void setPoliticalLevel4Num4(int politicalLevel4Num4) {
		this.politicalLevel4Num4 = politicalLevel4Num4;
	}

	public int getPoliticalLevel4Num5() {
		return politicalLevel4Num5;
	}

	public void setPoliticalLevel4Num5(int politicalLevel4Num5) {
		this.politicalLevel4Num5 = politicalLevel4Num5;
	}

	public int getPersonal_declaration1() {
		return personal_declaration1;
	}

	public void setPersonal_declaration1(int personal_declaration1) {
		this.personal_declaration1 = personal_declaration1;
	}

	public int getPersonal_declaration2() {
		return personal_declaration2;
	}

	public void setPersonal_declaration2(int personal_declaration2) {
		this.personal_declaration2 = personal_declaration2;
	}

	public int getPersonal_declaration3() {
		return personal_declaration3;
	}

	public void setPersonal_declaration3(int personal_declaration3) {
		this.personal_declaration3 = personal_declaration3;
	}

	public int getPersonal_declaration4() {
		return personal_declaration4;
	}

	public void setPersonal_declaration4(int personal_declaration4) {
		this.personal_declaration4 = personal_declaration4;
	}

	public int getPersonal_declaration5() {
		return personal_declaration5;
	}

	public void setPersonal_declaration5(int personal_declaration5) {
		this.personal_declaration5 = personal_declaration5;
	}

	public int getOrganization_recommended1() {
		return organization_recommended1;
	}

	public void setOrganization_recommended1(int organization_recommended1) {
		this.organization_recommended1 = organization_recommended1;
	}

	public int getOrganization_recommended2() {
		return organization_recommended2;
	}

	public void setOrganization_recommended2(int organization_recommended2) {
		this.organization_recommended2 = organization_recommended2;
	}

	public int getOrganization_recommended3() {
		return organization_recommended3;
	}

	public void setOrganization_recommended3(int organization_recommended3) {
		this.organization_recommended3 = organization_recommended3;
	}

	public int getOrganization_recommended4() {
		return organization_recommended4;
	}

	public void setOrganization_recommended4(int organization_recommended4) {
		this.organization_recommended4 = organization_recommended4;
	}

	public int getOrganization_recommended5() {
		return organization_recommended5;
	}

	public void setOrganization_recommended5(int organization_recommended5) {
		this.organization_recommended5 = organization_recommended5;
	}

	public int getType1industry1() {
		return type1industry1;
	}

	public void setType1industry1(int type1industry1) {
		this.type1industry1 = type1industry1;
	}

	public int getType1industry2() {
		return type1industry2;
	}

	public void setType1industry2(int type1industry2) {
		this.type1industry2 = type1industry2;
	}

	public int getType1industry3() {
		return type1industry3;
	}

	public void setType1industry3(int type1industry3) {
		this.type1industry3 = type1industry3;
	}

	public int getType1industry4() {
		return type1industry4;
	}

	public void setType1industry4(int type1industry4) {
		this.type1industry4 = type1industry4;
	}

	public int getType1industry5() {
		return type1industry5;
	}

	public void setType1industry5(int type1industry5) {
		this.type1industry5 = type1industry5;
	}

	public int getType1industry6() {
		return type1industry6;
	}

	public void setType1industry6(int type1industry6) {
		this.type1industry6 = type1industry6;
	}

	public int getType1industry7() {
		return type1industry7;
	}

	public void setType1industry7(int type1industry7) {
		this.type1industry7 = type1industry7;
	}

	public int getType1industry8() {
		return type1industry8;
	}

	public void setType1industry8(int type1industry8) {
		this.type1industry8 = type1industry8;
	}

	public int getType1industry9() {
		return type1industry9;
	}

	public void setType1industry9(int type1industry9) {
		this.type1industry9 = type1industry9;
	}

	public int getType1industry10() {
		return type1industry10;
	}

	public void setType1industry10(int type1industry10) {
		this.type1industry10 = type1industry10;
	}

	public int getType2industry1() {
		return type2industry1;
	}

	public void setType2industry1(int type2industry1) {
		this.type2industry1 = type2industry1;
	}

	public int getType2industry2() {
		return type2industry2;
	}

	public void setType2industry2(int type2industry2) {
		this.type2industry2 = type2industry2;
	}

	public int getType2industry3() {
		return type2industry3;
	}

	public void setType2industry3(int type2industry3) {
		this.type2industry3 = type2industry3;
	}

	public int getType2industry4() {
		return type2industry4;
	}

	public void setType2industry4(int type2industry4) {
		this.type2industry4 = type2industry4;
	}

	public int getType2industry5() {
		return type2industry5;
	}

	public void setType2industry5(int type2industry5) {
		this.type2industry5 = type2industry5;
	}

	public int getType2industry6() {
		return type2industry6;
	}

	public void setType2industry6(int type2industry6) {
		this.type2industry6 = type2industry6;
	}

	public int getType2industry7() {
		return type2industry7;
	}

	public void setType2industry7(int type2industry7) {
		this.type2industry7 = type2industry7;
	}

	public int getType2industry8() {
		return type2industry8;
	}

	public void setType2industry8(int type2industry8) {
		this.type2industry8 = type2industry8;
	}

	public int getType2industry9() {
		return type2industry9;
	}

	public void setType2industry9(int type2industry9) {
		this.type2industry9 = type2industry9;
	}

	public int getType2industry10() {
		return type2industry10;
	}

	public void setType2industry10(int type2industry10) {
		this.type2industry10 = type2industry10;
	}

	public int getType5industry1() {
		return type5industry1;
	}

	public void setType5industry1(int type5industry1) {
		this.type5industry1 = type5industry1;
	}

	public int getType5industry2() {
		return type5industry2;
	}

	public void setType5industry2(int type5industry2) {
		this.type5industry2 = type5industry2;
	}

	public int getType5industry3() {
		return type5industry3;
	}

	public void setType5industry3(int type5industry3) {
		this.type5industry3 = type5industry3;
	}

	public int getType5industry4() {
		return type5industry4;
	}

	public void setType5industry4(int type5industry4) {
		this.type5industry4 = type5industry4;
	}

	public int getType5industry5() {
		return type5industry5;
	}

	public void setType5industry5(int type5industry5) {
		this.type5industry5 = type5industry5;
	}

	public int getType5industry6() {
		return type5industry6;
	}

	public void setType5industry6(int type5industry6) {
		this.type5industry6 = type5industry6;
	}

	public int getType5industry7() {
		return type5industry7;
	}

	public void setType5industry7(int type5industry7) {
		this.type5industry7 = type5industry7;
	}

	public int getType5industry8() {
		return type5industry8;
	}

	public void setType5industry8(int type5industry8) {
		this.type5industry8 = type5industry8;
	}

	public int getType5industry9() {
		return type5industry9;
	}

	public void setType5industry9(int type5industry9) {
		this.type5industry9 = type5industry9;
	}

	public int getType5industry10() {
		return type5industry10;
	}

	public void setType5industry10(int type5industry10) {
		this.type5industry10 = type5industry10;
	}

	public int getType3job1() {
		return type3job1;
	}

	public void setType3job1(int type3job1) {
		this.type3job1 = type3job1;
	}

	public int getType3job2() {
		return type3job2;
	}

	public void setType3job2(int type3job2) {
		this.type3job2 = type3job2;
	}

	public int getType3job3() {
		return type3job3;
	}

	public void setType3job3(int type3job3) {
		this.type3job3 = type3job3;
	}

	public int getType3job4() {
		return type3job4;
	}

	public void setType3job4(int type3job4) {
		this.type3job4 = type3job4;
	}

	public int getType3job5() {
		return type3job5;
	}

	public void setType3job5(int type3job5) {
		this.type3job5 = type3job5;
	}

	public int getType3job6() {
		return type3job6;
	}

	public void setType3job6(int type3job6) {
		this.type3job6 = type3job6;
	}

	public int getType3job7() {
		return type3job7;
	}

	public void setType3job7(int type3job7) {
		this.type3job7 = type3job7;
	}

	public int getType3job8() {
		return type3job8;
	}

	public void setType3job8(int type3job8) {
		this.type3job8 = type3job8;
	}

	public int getType3job9() {
		return type3job9;
	}

	public void setType3job9(int type3job9) {
		this.type3job9 = type3job9;
	}

	public int getType3job10() {
		return type3job10;
	}

	public void setType3job10(int type3job10) {
		this.type3job10 = type3job10;
	}

	public int getType3job11() {
		return type3job11;
	}

	public void setType3job11(int type3job11) {
		this.type3job11 = type3job11;
	}

	public int getType3job12() {
		return type3job12;
	}

	public void setType3job12(int type3job12) {
		this.type3job12 = type3job12;
	}

	public int getType3job13() {
		return type3job13;
	}

	public void setType3job13(int type3job13) {
		this.type3job13 = type3job13;
	}

	public int getType3job14() {
		return type3job14;
	}

	public void setType3job14(int type3job14) {
		this.type3job14 = type3job14;
	}

	public int getType3job15() {
		return type3job15;
	}

	public void setType3job15(int type3job15) {
		this.type3job15 = type3job15;
	}

	public int getType3job16() {
		return type3job16;
	}

	public void setType3job16(int type3job16) {
		this.type3job16 = type3job16;
	}

	public int getType3job17() {
		return type3job17;
	}

	public void setType3job17(int type3job17) {
		this.type3job17 = type3job17;
	}

	public int getType3job18() {
		return type3job18;
	}

	public void setType3job18(int type3job18) {
		this.type3job18 = type3job18;
	}

	public int getType4job1() {
		return type4job1;
	}

	public void setType4job1(int type4job1) {
		this.type4job1 = type4job1;
	}

	public int getType4job2() {
		return type4job2;
	}

	public void setType4job2(int type4job2) {
		this.type4job2 = type4job2;
	}

	public int getType4job3() {
		return type4job3;
	}

	public void setType4job3(int type4job3) {
		this.type4job3 = type4job3;
	}

	public int getType4job4() {
		return type4job4;
	}

	public void setType4job4(int type4job4) {
		this.type4job4 = type4job4;
	}

	public int getType4job5() {
		return type4job5;
	}

	public void setType4job5(int type4job5) {
		this.type4job5 = type4job5;
	}

	public int getType4job6() {
		return type4job6;
	}

	public void setType4job6(int type4job6) {
		this.type4job6 = type4job6;
	}

	public int getType4job7() {
		return type4job7;
	}

	public void setType4job7(int type4job7) {
		this.type4job7 = type4job7;
	}

	public int getType4job8() {
		return type4job8;
	}

	public void setType4job8(int type4job8) {
		this.type4job8 = type4job8;
	}

	public int getType4job9() {
		return type4job9;
	}

	public void setType4job9(int type4job9) {
		this.type4job9 = type4job9;
	}

	public int getType4job10() {
		return type4job10;
	}

	public void setType4job10(int type4job10) {
		this.type4job10 = type4job10;
	}

	public int getType4job11() {
		return type4job11;
	}

	public void setType4job11(int type4job11) {
		this.type4job11 = type4job11;
	}

	public int getType4job12() {
		return type4job12;
	}

	public void setType4job12(int type4job12) {
		this.type4job12 = type4job12;
	}

	public int getType4job13() {
		return type4job13;
	}

	public void setType4job13(int type4job13) {
		this.type4job13 = type4job13;
	}

	public int getType4job14() {
		return type4job14;
	}

	public void setType4job14(int type4job14) {
		this.type4job14 = type4job14;
	}

	public int getType4job15() {
		return type4job15;
	}

	public void setType4job15(int type4job15) {
		this.type4job15 = type4job15;
	}

	public int getType4job16() {
		return type4job16;
	}

	public void setType4job16(int type4job16) {
		this.type4job16 = type4job16;
	}

	public int getType4job17() {
		return type4job17;
	}

	public void setType4job17(int type4job17) {
		this.type4job17 = type4job17;
	}

	public int getType4job18() {
		return type4job18;
	}

	public void setType4job18(int type4job18) {
		this.type4job18 = type4job18;
	}

	public int getPlanting_large1() {
		return planting_large1;
	}

	public void setPlanting_large1(int planting_large1) {
		this.planting_large1 = planting_large1;
	}

	public int getScale_farm1() {
		return scale_farm1;
	}

	public void setScale_farm1(int scale_farm1) {
		this.scale_farm1 = scale_farm1;
	}

	public int getFamily_farm1() {
		return family_farm1;
	}

	public void setFamily_farm1(int family_farm1) {
		this.family_farm1 = family_farm1;
	}

	public int getFarmer_cooperatives1() {
		return farmer_cooperatives1;
	}

	public void setFarmer_cooperatives1(int farmer_cooperatives1) {
		this.farmer_cooperatives1 = farmer_cooperatives1;
	}

	public int getCollege_students1() {
		return college_students1;
	}

	public void setCollege_students1(int college_students1) {
		this.college_students1 = college_students1;
	}

	public int getVocational_graduates1() {
		return vocational_graduates1;
	}

	public void setVocational_graduates1(int vocational_graduates1) {
		this.vocational_graduates1 = vocational_graduates1;
	}

	public int getMigrant_workers1() {
		return migrant_workers1;
	}

	public void setMigrant_workers1(int migrant_workers1) {
		this.migrant_workers1 = migrant_workers1;
	}

	public int getVeteran1() {
		return veteran1;
	}

	public void setVeteran1(int veteran1) {
		this.veteran1 = veteran1;
	}

	public int getPlanting_large2() {
		return planting_large2;
	}

	public void setPlanting_large2(int planting_large2) {
		this.planting_large2 = planting_large2;
	}

	public int getScale_farm2() {
		return scale_farm2;
	}

	public void setScale_farm2(int scale_farm2) {
		this.scale_farm2 = scale_farm2;
	}

	public int getFamily_farm2() {
		return family_farm2;
	}

	public void setFamily_farm2(int family_farm2) {
		this.family_farm2 = family_farm2;
	}

	public int getAgricultural_enterprises2() {
		return agricultural_enterprises2;
	}

	public void setAgricultural_enterprises2(int agricultural_enterprises2) {
		this.agricultural_enterprises2 = agricultural_enterprises2;
	}

	public int getFarmer_cooperatives2() {
		return farmer_cooperatives2;
	}

	public void setFarmer_cooperatives2(int farmer_cooperatives2) {
		this.farmer_cooperatives2 = farmer_cooperatives2;
	}

	public int getCollege_students2() {
		return college_students2;
	}

	public void setCollege_students2(int college_students2) {
		this.college_students2 = college_students2;
	}

	public int getVocational_graduates2() {
		return vocational_graduates2;
	}

	public void setVocational_graduates2(int vocational_graduates2) {
		this.vocational_graduates2 = vocational_graduates2;
	}

	public int getMigrant_workers2() {
		return migrant_workers2;
	}

	public void setMigrant_workers2(int migrant_workers2) {
		this.migrant_workers2 = migrant_workers2;
	}

	public int getVeteran2() {
		return veteran2;
	}

	public void setVeteran2(int veteran2) {
		this.veteran2 = veteran2;
	}

	public int getPlanting_large5() {
		return planting_large5;
	}

	public void setPlanting_large5(int planting_large5) {
		this.planting_large5 = planting_large5;
	}

	public int getScale_farm5() {
		return scale_farm5;
	}

	public void setScale_farm5(int scale_farm5) {
		this.scale_farm5 = scale_farm5;
	}

	public int getFamily_farm5() {
		return family_farm5;
	}

	public void setFamily_farm5(int family_farm5) {
		this.family_farm5 = family_farm5;
	}

	public int getFarmer_cooperatives5() {
		return farmer_cooperatives5;
	}

	public void setFarmer_cooperatives5(int farmer_cooperatives5) {
		this.farmer_cooperatives5 = farmer_cooperatives5;
	}

	public int getAgricultural_enterprises5() {
		return agricultural_enterprises5;
	}

	public void setAgricultural_enterprises5(int agricultural_enterprises5) {
		this.agricultural_enterprises5 = agricultural_enterprises5;
	}

	public int getService_experts5() {
		return service_experts5;
	}

	public void setService_experts5(int service_experts5) {
		this.service_experts5 = service_experts5;
	}
}
