<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.util.*" %>
<%@ page import="com.lpmas.framework.bean.StatusBean" %>
<%@ page import="com.lpmas.declare.bean.*" %>
<%@ page import="com.lpmas.declare.config.*" %>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper" %>
<% 
    SsoClientHelper helper = new SsoClientHelper(request, response, false);	
	FarmerIndustryInfoBean bean = (FarmerIndustryInfoBean)request.getAttribute("FarmerIndustryInfoBean");
	int declareType = ParamKit.getIntParameter(request, "declareType", 0);
	List<IndustryTypeBean> industryTypeList = (List<IndustryTypeBean>)request.getAttribute("IndustryTypeList");
	String originalIndustryName = (String)request.getAttribute("OriginalIndustryName");
	List<JobTypeBean> jobTypeList = (List<JobTypeBean>)request.getAttribute("JobTypeList");
%>
<%@ include file="../../include/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
 <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta content="c0c6d687cf0f11fc3f67955a6bba6d69c211075e" name="csrf-token">
		<title>在线申报系统-生产经营情况</title>
		<link href="<%=STATIC_URL %>css/bootstrap.css" rel="stylesheet">
		<link href="<%=STATIC_URL %>css/common.css" rel="stylesheet">
		<link href="<%=STATIC_URL %>css/other.css" rel="stylesheet">
		<link href="<%=STATIC_URL %>css/admin.css" rel="stylesheet">
		<link href="<%=STATIC_URL %>css/admin_v2.css" rel="stylesheet">
		<link rel="stylesheet" media="screen" href="<%=STATIC_URL %>css/es-icon.css">

		<!-- 新增样式 -->
		<link rel="stylesheet" href="../css/declare.css">
		<!-- end -->

		<script type="text/javascript" src="<%=STATIC_URL %>js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=STATIC_URL %>js/admin_common.js"></script>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<ul class="u-tab">
					<li><a href="<%=PORTAL_URL %>declare/FarmerInfoManage.do?declareType=<%=declareType%>">基本信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerContactInfoManage.do?declareType=<%=declareType%>">联系信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerSkillInfoManage.do?declareType=<%=declareType%>">申请培训信息</a></li>
					<li class="selected"><a href="<%=PORTAL_URL %>declare/FarmerIndustryInfoManage.do?declareType=<%=declareType%>">生产经营状况</a></li>
				</ul>
			</div>
			<div class="col-md-8">
				<p class="text-right"><a href="FormInstructionDisplay.do?declareType=<%=declareType%>">填表说明</a></p>
				<form class="declareInfo-cont form-horizontal">
				<input type="hidden" name="declareId" id="declareId" value="<%=bean.getDeclareId()%>"/>
	            <input type="hidden" name="status" id="status" value="<%=bean.getStatus()%>"/>
	            <input type="hidden" name="declareType" id="declareType" value="<%=declareType%>"/>
					<!-- 1 -->
					<div class="tab-cont">
						<div class="form-group">
						<%
						//获取人员类别列表
						List<StatusBean<String, String>> PERSONNEL_CATEGORY_LIST = new ArrayList<StatusBean<String, String>>();
						if(declareType == 2){
							PERSONNEL_CATEGORY_LIST = FarmerInfoConfig.PRODUCT_FARMER_PERSONNEL_CATEGORY_LIST;
						}else if(declareType == 1){
							PERSONNEL_CATEGORY_LIST = FarmerInfoConfig.YOUNG_FARMER_PERSONNEL_CATEGORY_LIST;
							
						}else if(declareType == 5){
							PERSONNEL_CATEGORY_LIST = FarmerInfoConfig.LEADER_FARMER_PERSONNEL_CATEGORY_LIST;
						}
						%>
						
						
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 人员类别</label>	
							<div class="col-md-9">
								<select class="form-control" name="farmerType" id="farmerType" onchange="showBox()">
									<option value="">请选择</option>
									
									<% String farmerType = bean.getFarmerType(); %>
									<%for(StatusBean<String,String> statusBean : PERSONNEL_CATEGORY_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(farmerType)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
									<%} %>
								</select>
							</div>
						</div>

						<div id="divAddress" class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 产业所在地：</label>
							<input type="hidden" name="selProvince" id="selProvince" value="<%=bean.getIndustryProvince()%>"/>
							<input type="hidden" name="selCity" id="selCity" value="<%=bean.getIndustryCity()%>"/>
							<input type="hidden" name="selRegion" id="selRegion" value="<%=bean.getIndustryRegion()%>"/>
							<div class="col-md-9">
								<div class="row">
									<div class="col-md-4">
										<select class="form-control" id="selectProvince">
											<option>请选择</option>
										</select>
									</div>
									<div class="col-md-4">
										<select class="form-control" id="selectCity">
											<option>请选择</option>
										</select>
									</div>
									<div class="col-md-4">
										<select class="form-control" id="selectRegion">
											<option>请选择</option>
										</select>
									</div>
									<input type="hidden" id="queryCity" />
									<input type="hidden" id="queryRegion" />
								</div>
							</div>
						</div>
						
						<!--  <div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 带动农民数量：</label>
							<div class="col-md-9"><input class="form-control" name="driveFarmerNumber" id="driveFarmerNumber" value="<%=bean.getDriveFarmerNumber() >0 ? bean.getDriveFarmerNumber() : "" %>"  type="number"/></div>
						</div>-->
						<%if(declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {%>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 地区类型：</label>
							<div class="col-md-9">
								<select class="form-control" name="areaType" id="areaType">
								<option>请选择</option>
								<%for(StatusBean<Integer,String> statusBean : FarmerIndustryInfoConfig.AREA_TYPE_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus()== bean.getAreaType()) ? "selected" : ""%>><%=statusBean.getValue() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 经济区域类型：</label>
							<div class="col-md-9">
								<select class="form-control" name="economicAreaType" id="economicAreaType">
								<option>请选择</option>
								<%for(StatusBean<Integer,String> statusBean : FarmerIndustryInfoConfig.ECONOMIC_AREA_TYPE_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus()== bean.getEconomicAreaType()) ? "selected" : ""%>><%=statusBean.getValue() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<%} %>
                        <div id="divIndustry" <%=FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_SERVICE_ORGANIZER.equals(bean.getFarmerType()) ? "style='display:none'" : "style='display:block'"%>>
						<br/>
						<p class="bg-info pd15">主体产业</p>
                    	<div class="form-group">
							<label class="col-md-3 required" for="int-photo"><i class="int-piont">*</i> 主体产业1：</label>
							<div class="col-md-9">
								<select class="industryType form-control" id="industryTypeId1" name="industryTypeId1" >
								<option>请选择</option>
								<%for(IndustryTypeBean industryTypeBean: industryTypeList){ %>
									<option value="<%=industryTypeBean.getTypeId()%>" <%=industryTypeBean.getTypeId() == bean.getIndustryTypeId1() ? "selected" : ""%>><%=industryTypeBean.getTypeName() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 产业名称1：</label>
							<div class="col-md-9">
							   <input type="hidden" class="originalIndustryId" id="originalIndustryId1" name="originalIndustryId1" value="<%=bean.getIndustryId1() %>">
                               <select class="industry form-control" id="industryId1" name="industryId1" ></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 产业规模：</label>
							<div class="col-md-9"><input type="number" class="form-control" name="industryScale1" id="industryScale1" value="<%=bean.getIndustryScale1() > 0 ? bean.getIndustryScale1() : "" %>" /></div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 单位：</label>
							<div class="col-md-9">
							      <select class="form-control" id="industryUnit1" name="industryTypeUnit1" >
				                    <option value="亩" <%=bean.getIndustryUnit1().equals("亩") ? "selected" : ""%>>亩</option>
				                    <option value="只/羽/头" <%=bean.getIndustryUnit1().equals("只/羽/头") ? "selected" : ""%>>只/羽/头</option>
				                    <option value="公顷" <%=bean.getIndustryUnit1().equals("公顷") ? "selected" : ""%>>公顷</option>
                                  </select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 从事年限：</label>
							<div class="col-md-8">
								<input class="form-control" type="number" name="experience1" id="experience1" value="<%=bean.getExperience1() > 0 ? bean.getExperience1() : "" %>">
							</div>
							<p class="form-control-static">年</p>
						</div>
						
						<div class="form-group">
							<label class="col-md-3" for="int-photo"><i class="int-piont">*</i>  主体产业2：</label>
							<div class="col-md-9">
								<select class="form-control industryType" id="industryTypeId2" name="industryTypeId2" >
								<option>请选择</option>
								<%for(IndustryTypeBean industryTypeBean: industryTypeList){ %>
									<option value="<%=industryTypeBean.getTypeId()%>" <%=industryTypeBean.getTypeId() == bean.getIndustryTypeId2() ? "selected" : ""%>><%=industryTypeBean.getTypeName() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 产业名称2：</label>
							<div class="col-md-9">
								<input type="hidden" class="originalIndustryId" id="originalIndustryId2" name="originalIndustryId2" value="<%=bean.getIndustryId2() %>">
                               <select class="form-control industry" id="industryId2" name="industryId2" ></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 产业规模：</label>
							<div class="col-md-9"><input class="form-control"  type="number" name="industryScale2" id="industryScale2" value="<%=bean.getIndustryScale2() > 0 ? bean.getIndustryScale2() : ""%>" /></div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i>  单位：</label>
							<div class="col-md-9">
                            <select class="form-control" id="industryUnit2" name="industryTypeUnit2" >
				            <option value="亩" <%=bean.getIndustryUnit2().equals("亩") ? "selected" : ""%>>亩</option>
				            <option value="只/羽/头" <%=bean.getIndustryUnit2().equals("只/羽/头") ? "selected" : ""%>>只/羽/头</option>
				            <option value="公顷" <%=bean.getIndustryUnit2().equals("公顷") ? "selected" : ""%>>公顷</option>
                            </select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i>  从事年限：</label>
							<div class="col-md-8">
								<input class="form-control" type="number" name="experience2" id="experience2" value="<%=bean.getExperience2() >0 ? bean.getExperience2() : ""%>">
							</div>
							<p class="form-control-static">年</p>
						</div>
						<div class="form-group">
							<label class="col-md-3" for="int-photo"><i class="int-piont">*</i>  主体产业3：</label>
							<div class="col-md-9">
								<select class="form-control industryType" id="industryTypeId3" name="industryTypeId3" >
								<option>请选择</option>
								<%for(IndustryTypeBean industryTypeBean: industryTypeList){ %>
									<option value="<%=industryTypeBean.getTypeId()%>" <%=industryTypeBean.getTypeId() == bean.getIndustryTypeId3() ? "selected" : ""%>><%=industryTypeBean.getTypeName() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i>  产业名称3：</label>
							<div class="col-md-9">
							   <input type="hidden" class="originalIndustryId" id="originalIndustryId3" name="originalIndustryId3" value="<%=bean.getIndustryId3() %>">
                               <select class="form-control industry" id="industryId3" name="industryId3" ></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i>  产业规模：</label>
							<div class="col-md-9"><input class="form-control"  type="number" name="industryScale3" id="industryScale3" value="<%=bean.getIndustryScale3() > 0 ? bean.getIndustryScale3() : ""%>" /></div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 单位：</label>
							<div class="col-md-9">
							<select class="form-control" id="industryUnit3" name="industryTypeUnit3" >
				            <option value="亩" <%=bean.getIndustryUnit3().equals("亩") ? "selected" : ""%>>亩</option>
				            <option value="只/羽/头" <%=bean.getIndustryUnit3().equals("只/羽/头") ? "selected" : ""%>>只/羽/头</option>
				            <option value="公顷" <%=bean.getIndustryUnit3().equals("公顷") ? "selected" : ""%>>公顷</option>
                            </select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i>  从事年限：</label>
							<div class="col-md-8">
								<input class="form-control" type="number" name="experience3" id="experience3" value="<%=bean.getExperience3() >0 ? bean.getExperience3() : ""%>">
							</div>
							<p class="form-control-static">年</p>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 上年度产业收入（万元）：</label>
							<div class="col-md-9">
								<input type="number" class="form-control" name="lastYearIncome" id="lastYearIncome" value="<%=bean.getLastYearIncome() > 0 ? bean.getLastYearIncome() : ""%>" />
							</div>
						</div>
					    </div>
						<br/>
						<div id="divFamily" <%=FarmerInfoConfig.PERSONNEL_CATEGORY_FAMILY_FARMER.equals(bean.getFarmerType()) ? "style='display:block'" : "style='display:none'"%>>
						<p class="bg-info pd15">家庭农场情况</p>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 是否登记注册？：</label>
							<div class="col-md-9">
								<select class="form-control" name="hasRegisted" id="hasRegisted">
									<option value="">请选择</option>
									<option value="<%=Constants.STATUS_VALID %>" <%=(bean.getHasRegisted()==Constants.STATUS_VALID)?"selected":"" %>>是</option>
									<option value="<%=Constants.STATUS_NOT_VALID %>" <%=(bean.getHasRegisted()==Constants.STATUS_NOT_VALID && bean.getDeclareId()>0)?"selected":"" %>>否</option>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i>  是否是示范性家庭农场？：</label>
							<div class="col-md-9">
								<select class="form-control" name="isExampleFamilyFarm" id="isExampleFamilyFarm" onclick="isHidden()">
									<option value="">请选择</option>
									<option value="<%=Constants.STATUS_VALID %>" <%=(bean.getIsExampleFamilyFarm()==Constants.STATUS_VALID)?"selected":"" %>>是</option>
									<option value="<%=Constants.STATUS_NOT_VALID %>" <%=(bean.getIsExampleFamilyFarm()==Constants.STATUS_NOT_VALID && bean.getDeclareId()>0)?"selected":"" %>>否</option>
								</select>
							</div>
						</div>
						<!-- 隐藏 -->
						<div id="divFarmLevel" class="form-group" <%=Constants.STATUS_VALID == bean.getIsExampleFamilyFarm()? "style='display:block'" : "style='display:none'"%>>
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 示范性农场级别</label>	
							<div class="col-md-9">
								<select class="form-control" name="exampleFarmLevel" id="exampleFarmLevel">
									<option value="">请选择</option>
									<% String exampleFarmLevel = bean.getExampleFarmLevel(); %>
									<%for(StatusBean<String,String> statusBean : FarmerIndustryInfoConfig.FARM_LEVEL_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(exampleFarmLevel)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
									<%} %>
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 家庭农场类型</label>	
							<div class="col-md-9">
								<select class="form-control" name="familyFarmType" id="familyFarmType">
									<option value="">请选择</option>
									<% String familyFarmType = bean.getFamilyFarmType(); %>
									<%for(StatusBean<String,String> statusBean : FarmerIndustryInfoConfig.FAMILY_FARM_TYPE_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(familyFarmType)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
									<%} %>									
								</select>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 土地经营规模(亩)：</label>
							<div class="col-md-9"><input class="form-control" name="farmLandScale" id="farmLandScale"  value="<%=bean.getFarmLandScale() >0 ? bean.getFarmLandScale() : "" %>"  type="number"/></div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 家庭总人口：</label>
							<div class="col-md-9"><input class="form-control" name="familyPerson" id="familyPerson"  value="<%=bean.getFamilyWorkingPerson() > 0 ? bean.getFamilyPerson() : "" %>"  type="number"/></div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 家庭从事产业人数：</label>
							<div class="col-md-9"><input class="form-control" name="familyWorkingPerson" id="familyWorkingPerson"  value="<%=bean.getFamilyWorkingPerson() >0 ? bean.getFamilyWorkingPerson() : "" %>"  type="number"/></div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 上年家庭收入（万元）：</label>
							<div class="col-md-9">
								<input type="number" class="form-control" name="lastYearFamilyIncome" id="lastYearFamilyIncome" value="<%=bean.getLastYearFamilyIncome() > 0 ? bean.getLastYearFamilyIncome() : ""%>" />
							</div>
						</div>
						</div>
						<!-- 农业社会化服务组织服务能手 -->
						<div id="divJob" <%=FarmerInfoConfig.PERSONNEL_CATEGORY_AGRICULTURAL_SERVICE_ORGANIZER.equals(bean.getFarmerType()) ? "style='display:block'" : "style='display:none'"%>>
						<br/>
						<div class="form-group">
							<label class="col-md-3 required" for="int-photo"><i class="int-piont">*</i> 工种类别</label>
							<div class="col-md-9">
								<select class="jobType form-control" id="jobType" name="jobType" >
								<option>请选择</option>
								<%for(JobTypeBean jobTypeBean: jobTypeList){ %>
									<option value="<%=jobTypeBean.getTypeId()%>" <%=jobTypeBean.getTypeId() == bean.getJobType() ? "selected" : ""%>><%=jobTypeBean.getTypeName() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 从事工种/岗位</label>
							<div class="col-md-9">
							   <input type="hidden" class="originalJobId" id= "originalJobId" value="<%=bean.getJobId() %>">
                               <select class="job form-control" id="job" name="job" ></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 服务规模：</label>
							<div class="col-md-9"><input type="text" class="form-control" id="serviceScale" name="serviceScale" value="<%=bean.getServiceScale() %>"/></div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 从业年限：</label>
							<div class="col-md-9">
								<input type="number" name="experience" id="experience" value="<%=bean.getExperience() >0 ? bean.getExperience() : ""%>" class="form-control">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i>年收入（万元）：</label>
							<div class="col-md-9">
								<input type="number" class="form-control" name="income" id="income" value="<%=bean.getIncome() > 0 ? bean.getIncome() : ""%>" />
							</div>
						</div>
					</div>
						<button class="btn btn-primary btn-block" id="produce-save-btn" type="button">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>  

</body>
<script type="text/javascript" src="<%=STATIC_URL %>js/declare.js"></script>
<script>
function showBox(){
    var divJob = $('#divJob');
    var divFamily = $('#divFamily');
    var divAddress = $('#divAddress');
    var divIndustry = $('#divIndustry');
    var personInput = $("#farmerType").val();
	if(personInput == "FAMILY_FARMER"){
    	divFamily.show();
	}
    else{
    	divFamily.hide();
    }
	if(personInput == 'AGRICULTURAL_SERVICE_ORGANIZER'){
		$("#divAddress").find("label").html(" <i class='int-piont'>*</i>工作地点:");
		divIndustry.hide();
    	divJob.show();
	}
    else{
    	$("#divAddress").find("label").html(" <i class='int-piont'>*</i>产业所在地:");
		divIndustry.show();
    	divJob.hide();
    }
	divAddress.show();
  }
function isHidden(){
    var divFarmLevel = $('#divFarmLevel');
    var isExampleFamilyFarm = $("#isExampleFamilyFarm").val();
    if (isExampleFamilyFarm == '1') {
    	divFarmLevel.show();
	}
    else{
    	divFarmLevel.hide();
    }
  }
$(document).ready(function() {
	var declareType = $('#declareType').val();
	if(declareType == '5'){
	    var divAddress = $('#divAddress');
	    var divIndustry = $('#divIndustry');
	    divAddress.hide();
	    divIndustry.hide();
	}
	$("#farmerType").trigger('change');
	$("#industryTypeId1").on("change",function(){
         var typeId = $(this).find("option:selected").val();
         seletCate1(typeId,$(this));
     });
	$("#industryTypeId1").trigger('change');
     function seletCate1(typeId,element){
        	$.ajax({
    	        type: 'get',
    	        url: "/declare/IndustryInfoJsonList.do?typeId="+typeId,
    	        dataType: 'json',
    	        success: function(data){
    	        		var industry=data.result;
    	        		child ="";
                 for(var j=0;j< industry.length;j++){
                	 	var originalId = $('#originalIndustryId1').val();
                	 	if(industry[j].industryId == originalId){
                	 		child += "<option value='" + industry[j].industryId  +"' selected>"+ industry[j].industryName + "</option>"
                	 	}else{
                	 		child += "<option value='" + industry[j].industryId  +"'>"+ industry[j].industryName + "</option>"
                	 	}
                 }
                 $("#industryId1").html(child);
    	        },
    	        error: function(){
    	            return;
    	        }
    	    });
     }
     $("#industryTypeId2").on("change",function(){
         var typeId = $(this).find("option:selected").val();
         seletCate2(typeId,$(this));
     });
	$("#industryTypeId2").trigger('change');
     function seletCate2(typeId,element){
        	$.ajax({
    	        type: 'get',
    	        url: "/declare/IndustryInfoJsonList.do?typeId="+typeId,
    	        dataType: 'json',
    	        success: function(data){
    	        		var industry=data.result;
    	        		child ="";
                 for(var j=0;j< industry.length;j++){
                	 	var originalId = $('#originalIndustryId2').val();
                	 	if(industry[j].industryId == originalId){
                	 		child += "<option value='" + industry[j].industryId  +"' selected>"+ industry[j].industryName + "</option>"
                	 	}else{
                	 		child += "<option value='" + industry[j].industryId  +"'>"+ industry[j].industryName + "</option>"
                	 	}
                 }
                 $("#industryId2").html(child);
    	        },
    	        error: function(){
    	            return;
    	        }
    	    });
     }
     $("#industryTypeId3").on("change",function(){
         var typeId = $(this).find("option:selected").val();
         seletCate3(typeId,$(this));
     });
	$("#industryTypeId3").trigger('change');
     function seletCate3(typeId,element){
        	$.ajax({
    	        type: 'get',
    	        url: "/declare/IndustryInfoJsonList.do?typeId="+typeId,
    	        dataType: 'json',
    	        success: function(data){
    	        		var industry=data.result;
    	        		child ="";
                 for(var j=0;j< industry.length;j++){
                	 	var originalId = $('#originalIndustryId3').val();
                	 	if(industry[j].industryId == originalId){
                	 		child += "<option value='" + industry[j].industryId  +"' selected>"+ industry[j].industryName + "</option>"
                	 	}else{
                	 		child += "<option value='" + industry[j].industryId  +"'>"+ industry[j].industryName + "</option>"
                	 	}
                 }
                 $("#industryId3").html(child);
    	        },
    	        error: function(){
    	            return;
    	        }
    	    });
     }
     $("#jobType").on("change",function(){
	      var typeId = $(this).find("option:selected").val();
	      seletCate(typeId,$(this));
	  });
	  $("#jobType").trigger('change');

	  function seletCate(typeId,element){
		  $.ajax({
 	        type: 'get',
 	        url: "/declare/JobInfoJsonList.do?typeId="+typeId,
 	        dataType: 'json',
 	        success: function(data){
 	        		var jobs=data.result;
 	        		child ="";
               for(var j=0;j< jobs.length;j++){
             	 	var originalId = element.parent().parent().find(".originalJobId").val();
             	 	if(jobs[j].jobId == originalId){
             	 		child += "<option value='" + jobs[j].jobId  +"' selected>"+ jobs[j].jobName + "</option>"
             	 	}else{
             	 		child += "<option value='" + jobs[j].jobId  +"'>"+ jobs[j].jobName + "</option>"
             	 	}
              }
              $("#job").html(child);
 	        },
 	        error: function(){
 	            return;
 	        }
		});
	  }		     
     
 });


$('#produce-save-btn').on('click',function(){

    	
    	var declareType = $('#declareType').val();
        var status = $('#status').val();
        var declareId = $('#declareId').val();
        
        var farmerType = $('#farmerType').val();
        var familyWorkingPerson = $('#familyWorkingPerson').val();
        var areaType = $('#areaType').val();
        var economicAreaType = $('#economicAreaType').val();
        
        var industryTypeId1 = $('#industryTypeId1').val();
        var industryId1 = $('#industryId1').val();
        var industryScale1 = $('#industryScale1').val();
        var experience1 = $('#experience1').val();
        var industryUnit1 = $('#industryUnit1').val();
        var industryTypeId2 = $('#industryTypeId2').val();
        var industryId2 = $('#industryId2').val();
        var industryScale2 = $('#industryScale2').val();
        var experience2 = $('#experience2').val();
        var industryUnit2 = $('#industryUnit2').val();
        var industryTypeId3 = $('#industryTypeId3').val();
        var industryId3 = $('#industryId3').val();
        var industryScale3 = $('#industryScale3').val();
        var experience3 = $('#experience3').val();
        var industryUnit3 = $('#industryUnit3').val();
        
        var exampleFarmLevel = $('#exampleFarmLevel').val()
        var familyFarmType = $('#familyFarmType').val()
        var farmLandScale = $('#farmLandScale').val()
        var lastYearIncome = $('#lastYearIncome').val();
        var familyPerson = $('#familyPerson').val();
        var experience = $('#experience').val();
        var income = $('#income').val();
        var serviceScale = $('#serviceScale').val();
        var lastYearFamilyIncome = $('#lastYearFamilyIncome').val();
        
        var industryProvince = $('#selectProvince option:selected').text();
		var industryCity = $('#selectCity option:selected').text();
		var industryRegion = $('#selectRegion option:selected').text();
        var hasRegisted  = $('#hasRegisted').val(); 
        var isExampleFamilyFarm  = $('#isExampleFamilyFarm').val(); 
        var jobType =  $('#jobType').val();
        var jobId =  $('#job').val();
        if(declareType == '1' || declareType == '2'){
		 if (farmerType == ''){
				alert("人员类别必须填写");
				return;
			}
        if (industryTypeId1 == '' || industryId1 == '' || industryScale1 == '' || experience1 == '' || industryUnit1 == '' ){
			alert("主体产业1必须填写");
			return;
		}
        if (lastYearIncome == ''){
			alert("上年度产业收入必须填写");
			return;
		}
        if (industryProvince == '' || industryRegion == '' || industryCity == ''||industryProvince == '全部' || industryRegion == '全部' || industryCity == '全部'||industryProvince == '请选择' || industryRegion == '请选择' || industryCity == '请选择'){
			alert("地点必须填写");
			return;
		}
        if(declareType == '1'){
        if (areaType == ''){
			alert("地区类型必须填写");
			return;
		}
    	if (economicAreaType == ''){
			alert("经济区域类型必须填写");
			return;
		}
        }
        if(farmerType == 'FAMILY_FARMER'){
        if(isExampleFamilyFarm == 1){
        	if(exampleFarmLevel == ''){
        		alert("示范性农场级别必须填写");
    			return;
        	}
        }
        if (familyFarmType == ''){
			alert("家庭农场类型必须填写");
			return;
		}
        if (familyPerson == ''){
			alert("家庭总人口数必须填写");
			return;
		}
        if (familyFarmType == ''){
			alert("家庭农场类型必须填写");
			return;
		}
        var re = /^[0-9]+[0-9]*]*$/;
        var number1 = $('#familyWorkingPerson').val();
        if (!re.test(number1)){
            alert("[家庭从事产业人数]请输入数字");
            return false;
        }
        if (lastYearFamilyIncome == ''){
			alert("请输上年家庭收入");
			return;
		}
        if(hasRegisted == ''){
			alert("请对是否注册做出选择");
			return;        	
        }
        if(isExampleFamilyFarm == ''){
			alert("请对是否示范性家庭做出选择");
			return;        	
        }
        }	
        }
        if(declareType == '5'){
        	if (farmerType == ''){
				alert("人员类别必须填写");
				return;
			}
        	if(farmerType == 'FAMILY_FARMER'){
                if(isExampleFamilyFarm == 1){
                	if(exampleFarmLevel == ''){
                		alert("示范性农场级别必须填写");
            			return;
                	}
                }
                if (familyFarmType == ''){
        			alert("家庭农场类型必须填写");
        			return;
        		}
                if (familyPerson == ''){
        			alert("家庭总人口数必须填写");
        			return;
        		}
                if (familyFarmType == ''){
        			alert("家庭农场类型必须填写");
        			return;
        		}
                var re = /^[0-9]+[0-9]*]*$/;
                var number1 = $('#familyWorkingPerson').val();
                if (!re.test(number1)){
                    alert("[家庭从事产业人数]请输入数字");
                    return false;
                }
                if (lastYearFamilyIncome == ''){
        			alert("请输上年家庭收入");
        			return;
        		}
                if(hasRegisted == ''){
        			alert("请对是否注册做出选择");
        			return;        	
                }
                if(isExampleFamilyFarm == ''){
        			alert("请对是否示范性家庭做出选择");
        			return;        	
                }
                }
        	else if(farmerType == 'AGRICULTURAL_SERVICE_ORGANIZER'){
        	if(jobType == ''){
        		alert("从事工种/岗位须填写");
    			return;
        	}
        	if(serviceScale == ''){
        		alert("服务规模须填写");
    			return;
        	}
        	if(experience == ''){
        		alert("从业年限必须填写");
    			return;
        	}
        	if(income == ''){
        		alert("年收入必须填写");
    			return;
        	}
        }else{
        	if (industryTypeId1 == '' || industryId1 == '' || industryScale1 == '' || experience1 == '' || industryUnit1 == '' ){
    			alert("主体产业1必须填写");
    			return;
    		}
            if (lastYearIncome == ''){
    			alert("上年度产业收入必须填写");
    			return;
    		}
            if (industryProvince == '' || industryRegion == '' || industryCity == ''||industryProvince == '全部' || industryRegion == '全部' || industryCity == '全部'||industryProvince == '请选择' || industryRegion == '请选择' || industryCity == '请选择'){
    			alert("地点必须填写");
    			
        }
        }
        }
        $.ajax({
            url: 'FarmerIndustryInfoManage.do',
            data: {
                declareType: declareType,
                status: status,
                declareId: declareId,
                farmerType:farmerType,
                industryProvince: industryProvince,
                industryCity: industryCity,
                industryRegion: industryRegion,
                areaType:areaType,
                economicAreaType:economicAreaType,
                lastYearIncome:lastYearIncome,
                                industryTypeId1: industryTypeId1,
                                industryId1: industryId1,
                                industryScale1: industryScale1,
                                experience1: experience1,
                                industryUnit1: industryUnit1,
                                industryTypeId2: industryTypeId2,
                                industryId2: industryId2,
                                industryScale2: industryScale2,
                                experience2: experience2,
                                industryUnit2: industryUnit2,
                                industryTypeId3: industryTypeId3,
                                industryId3: industryId3,
                                industryScale3: industryScale3,
                                experience3: experience3,
                                industryUnit3: industryUnit3,
                hasRegisted:hasRegisted,
                isExampleFamilyFarm:isExampleFamilyFarm,
                exampleFarmLevel:exampleFarmLevel,
                familyFarmType:familyFarmType,
                farmLandScale:farmLandScale,
                familyPerson:familyPerson,
                familyWorkingPerson:familyWorkingPerson,
                lastYearFamilyIncome:lastYearFamilyIncome,
                serviceScale:serviceScale,

                experience:experience,
                income:income,
                jobType:jobType,
                jobId:jobId,
            },
            type: 'POST',
            success: function (json) {
                console.log(json);
                json = ajaxAuthCheck(json);
                if (json.code == '1') {
                    window.location = 'DeclareInfoManage.do?declareType='+declareType
                }
                else if (json.code == '0') {
                    alert(json.message);
                }
            }
        })
    
});

</script>
</html>