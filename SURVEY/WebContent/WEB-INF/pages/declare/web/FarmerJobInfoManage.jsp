<%@page import="com.lpmas.framework.util.MapKit"%>
<%@page import="com.lpmas.declare.config.FarmerJobInfoConfig"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.StatusBean" %>
<%@ page import="com.lpmas.declare.bean.FarmerJobInfoBean" %>
<%@ page import="com.lpmas.declare.bean.JobTypeBean" %>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper" %>
<% 
    SsoClientHelper helper = new SsoClientHelper(request, response, false);	
	FarmerJobInfoBean bean = (FarmerJobInfoBean)request.getAttribute("FarmerJobInfoBean");
	int declareType = ParamKit.getIntParameter(request, "declareType", 0);
	List<JobTypeBean> jobTypeList = (List<JobTypeBean>)request.getAttribute("JobTypeList");
%>
<%@ include file="../../include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta content="c0c6d687cf0f11fc3f67955a6bba6d69c211075e" name="csrf-token">
		<title>在线申报系统-农务工作信息</title>
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
					<li><a href="<%=PORTAL_URL %>declare/FarmerSkillInfoManage.do?declareType=<%=declareType%>">专业技能</a></li>
					<li class="selected"><a href="<%=PORTAL_URL %>declare/FarmerJobInfoManage.do?declareType=<%=declareType%>">农务工作信息</a></li>
				</ul>
			</div>
			<div class="col-md-8">
				<p class="text-right"><a href="FormInstructionDisplay.do?declareType=<%=declareType%>">填表说明</a></p>
				<form class="declareInfo-cont form-horizontal">
				    	<input type="hidden" name="declareId" id="declareId" value="<%=bean.getDeclareId()%>"/>
	                    <input type="hidden" name="status" id="status" value="<%=bean.getStatus()%>"/>
	                    <input type="hidden" name="declareType" id="declareType" value="<%=declareType%>"/>
					<div class="tab-cont">
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 从事工种：</label>
							<div class="col-md-9">
								<select class="jobType form-control" id="jobType" name="jobType">
									 <%for(JobTypeBean jobTypeBean: jobTypeList){ %>
					                 <option value="<%=jobTypeBean.getTypeId()%>" <%=jobTypeBean.getTypeId()== bean.getJobType() ? "selected" : ""%>><%=jobTypeBean.getTypeName() %></option>
				                     <%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 从事岗位：</label>
							<div class="col-md-9">
							<input type="hidden" name="originalJobId" id= "originalJobId" value="<%=bean.getJobId()%>"/>
                            <select class="job form-control" id="job" name="job" ></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 从业年限：</label>
							<div class="col-md-9">
								<input type="number" name="experience" id="experience" value="<%=bean.getExperience() >0 ? bean.getExperience() : ""%>" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 个人从事该工种／岗位年收入（万）：</label>
							<div class="col-md-9">
								<input type="number" name="income" id="income" value="<%=bean.getIncome() > 0 ? bean.getIncome() : "" %>"  class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 从业单位类别：</label>
							<div class="col-md-9">
								<select class="form-control" name="companyType" id="companyType">
								<option value="">请选择</option>
								<% String companytype = bean.getCompanyType(); %>
								 <%for(StatusBean<String,String> statusBean : FarmerJobInfoConfig.JOB_COMPANY_TYPE_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(companytype)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 工作地点：</label>
							<input type="hidden" name="selProvince" id="selProvince" value="<%=bean.getJobProvince()%>"/>
							<input type="hidden" name="selCity" id="selCity" value="<%=bean.getJobCity()%>"/>
							<input type="hidden" name="selRegion" id="selRegion" value="<%=bean.getJobRegion()%>"/>
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
						<button class="btn btn-primary btn-block" id="base-save-btn" type="button">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=STATIC_URL %>js/declare.js"></script>
<script>
$(document).ready(function() {

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

$("#base-save-btn").click(
		function() {
		//获取表单数据
		var declareId = $('#declareId').val();
		var declareType = $('#declareType').val();
		var status = $('#status').val();
		var country = $('#country').val();
		var jobType = $('#jobType').val();
		var jobName = $('#job').val();
		var experience = $('#experience').val();
		var income = $('#income').val();
		var companyType = $('#companyType').val();
		var jobProvince = $('#selectProvince option:selected').text();
		var jobCity = $('#selectCity option:selected').text();
		var jobRegion = $('#selectRegion option:selected').text();
		if (jobType == '' || jobName == ''){
			alert("从业工种/岗位必须填写");
			return;
		}
		if (companyType==''){
			alert("从业单位类别必须填写");
			return;
		}
		if (jobProvince == '' || jobRegion == '' || jobCity == ''||jobProvince == '全部' || jobRegion == '全部' || jobCity == '全部'||jobProvince == '请选择' || jobRegion == '请选择' || jobCity == '请选择'){
			alert("工作地点必须填写");
			return;
		}
		if (typeof(experience)=="undefined"||experience==''){
			alert("从业年限必须填写");
			return;
		}
		if (typeof(income)=="undefined"||income==''){
			alert("从事岗位年收入必须填写");
			return;
		}
		//保留表单验证
		
		//表单提交
		 $.ajax({
	            url: 'FarmerJobInfoManage.do',
	            data: {
	                declareId: declareId,
	                declareType: declareType,
	                status: status,
	                country: country,
	                jobType: jobType,
	                jobId: jobName,
	                experience: experience,
	                income: income,
	                companyType: companyType,
	                jobProvince: jobProvince,
	                jobCity: jobCity,
	                jobRegion: jobRegion,
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