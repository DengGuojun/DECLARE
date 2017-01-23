package com.lpmas.declare.admin.bean;

import org.bson.types.ObjectId;

import com.lpmas.framework.nosql.mongodb.MongoDBDocumentBean;

public class TargetStatisticsBean extends MongoDBDocumentBean<ObjectId> {

	private String province = ""; // 省
	private String city = ""; // 市
	private String region = ""; // 区
	private String level = ""; // 区域等级
	private boolean isSum = false;
	private String declareYear = ""; // 培育年份

	private int countiesNumber = 0; // 项目县数

	private int count = 0; // 任务总数
	private int finishedCount = 0; // 已完成任务数
	private String precent = ""; // 已完成比例

	private int count1 = 0;
	private int finishedCount1 = 0;
	private String precent1 = "";
	private int count2 = 0;
	private int finishedCount2 = 0;
	private String precent2 = "";
	private int count3 = 0;
	private int finishedCount3 = 0;
	private String precent3 = "";
	private int count4 = 0;
	private int finishedCount4 = 0;
	private String precent4 = "";
	private int count5 = 0;
	private int finishedCount5 = 0;
	private String precent5 = "";

	private int type1count = 0; // 总数
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

	private int type2count = 0; // 总数
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

	private int type5count = 0; // 总数
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

	private int type3count = 0; // 总数
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

	private int type4count = 0; // 总数
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

	public int getCountiesNumber() {
		return countiesNumber;
	}

	public void setCountiesNumber(int countiesNumber) {
		this.countiesNumber = countiesNumber;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFinishedCount() {
		return finishedCount;
	}

	public void setFinishedCount(int finishedCount) {
		this.finishedCount = finishedCount;
	}

	public String getPrecent() {
		return precent;
	}

	public void setPrecent(String precent) {
		this.precent = precent;
	}

	public int getCount1() {
		return count1;
	}

	public void setCount1(int count1) {
		this.count1 = count1;
	}

	public int getFinishedCount1() {
		return finishedCount1;
	}

	public void setFinishedCount1(int finishedCount1) {
		this.finishedCount1 = finishedCount1;
	}

	public String getPrecent1() {
		return precent1;
	}

	public void setPrecent1(String precent1) {
		this.precent1 = precent1;
	}

	public int getCount2() {
		return count2;
	}

	public void setCount2(int count2) {
		this.count2 = count2;
	}

	public int getFinishedCount2() {
		return finishedCount2;
	}

	public void setFinishedCount2(int finishedCount2) {
		this.finishedCount2 = finishedCount2;
	}

	public String getPrecent2() {
		return precent2;
	}

	public void setPrecent2(String precent2) {
		this.precent2 = precent2;
	}

	public int getCount3() {
		return count3;
	}

	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public int getFinishedCount3() {
		return finishedCount3;
	}

	public void setFinishedCount3(int finishedCount3) {
		this.finishedCount3 = finishedCount3;
	}

	public String getPrecent3() {
		return precent3;
	}

	public void setPrecent3(String precent3) {
		this.precent3 = precent3;
	}

	public int getCount4() {
		return count4;
	}

	public void setCount4(int count4) {
		this.count4 = count4;
	}

	public int getFinishedCount4() {
		return finishedCount4;
	}

	public void setFinishedCount4(int finishedCount4) {
		this.finishedCount4 = finishedCount4;
	}

	public String getPrecent4() {
		return precent4;
	}

	public void setPrecent4(String precent4) {
		this.precent4 = precent4;
	}

	public int getCount5() {
		return count5;
	}

	public void setCount5(int count5) {
		this.count5 = count5;
	}

	public int getFinishedCount5() {
		return finishedCount5;
	}

	public void setFinishedCount5(int finishedCount5) {
		this.finishedCount5 = finishedCount5;
	}

	public String getPrecent5() {
		return precent5;
	}

	public void setPrecent5(String precent5) {
		this.precent5 = precent5;
	}

	public int getType1count() {
		return type1count;
	}

	public void setType1count(int type1count) {
		this.type1count = type1count;
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

	public int getType2count() {
		return type2count;
	}

	public void setType2count(int type2count) {
		this.type2count = type2count;
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

	public int getType5count() {
		return type5count;
	}

	public void setType5count(int type5count) {
		this.type5count = type5count;
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

	public int getType3count() {
		return type3count;
	}

	public void setType3count(int type3count) {
		this.type3count = type3count;
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

	public int getType4count() {
		return type4count;
	}

	public void setType4count(int type4count) {
		this.type4count = type4count;
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

}
