<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.util.*" %>
<%@ page import="com.lpmas.framework.bean.StatusBean" %>
<%@ page import="com.lpmas.declare.config.DeclareInfoConfig" %>
<%@ page import="com.lpmas.declare.bean.FarmerSkillInfoBean" %>
<%@ page import="com.lpmas.declare.bean.NationalCertificationBean" %>
<%@ page import="com.lpmas.declare.config.FarmerSkillInfoConfig" %>
<%@ page import="com.lpmas.declare.config.DeclareInfoConfig" %>
<%@ page import="com.lpmas.declare.config.FarmerInfoConfig" %>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper" %>
<% 
    SsoClientHelper helper = new SsoClientHelper(request, response, false);	
	FarmerSkillInfoBean bean = (FarmerSkillInfoBean)request.getAttribute("FarmerSkillInfoBean");
	int declareType = ParamKit.getIntParameter(request, "declareType", 0);
	List<NationalCertificationBean> ncList = (List<NationalCertificationBean>)request.getAttribute("NationalCertificationList");
	Map<Integer, String> ncMap = (Map<Integer, String>)request.getAttribute("NationalCertificationMap");
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
		<title>在线申报系统-专业技能</title>
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
				<%if(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {%>
				    <li><a href="<%=PORTAL_URL %>declare/FarmerInfoManage.do?declareType=<%=declareType%>">基本信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerContactInfoManage.do?declareType=<%=declareType%>">联系信息</a></li>
					<li class="selected"><a href="<%=PORTAL_URL %>declare/FarmerSkillInfoManage.do?declareType=<%=declareType%>">申请培训信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerJobInfoManage.do?declareType=<%=declareType%>">农务工作信息</a></li>
				<%}else{ %>
					<li><a href="<%=PORTAL_URL %>declare/FarmerInfoManage.do?declareType=<%=declareType%>">基本信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerContactInfoManage.do?declareType=<%=declareType%>">联系信息</a></li>
					<li class="selected"><a href="<%=PORTAL_URL %>declare/FarmerSkillInfoManage.do?declareType=<%=declareType%>">申请培训信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerIndustryInfoManage.do?declareType=<%=declareType%>">生产经营状况</a></li>
				<%} %>
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
						<p class="bg-info pd15">申请方式</p>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 申请方式：</label>	
							<div class="col-md-9">
								<select class="form-control" name="applyType" id="applyType">
									<option value="">请选择</option>
									<% String applyType = bean.getApplyType(); %>
									<%for(StatusBean<String,String> statusBean : DeclareInfoConfig.APPLY_TYPE_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(applyType)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
									<%} %>
								</select>
							</div>
						</div>
						
						<p class="bg-info pd15">学习培训经历</p>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 是否参加过新型职业农民培训：</label>
							<div class="col-md-9">
								<select class="form-control" name="isTrained" id="isTrained" onchange="isTrainedChange()">
									<option value="">请选择</option>
									<option value="<%=Constants.STATUS_VALID %>" <%=(bean.getIsTrained()==Constants.STATUS_VALID)?"selected":"" %>>是</option>
									<option value="<%=Constants.STATUS_NOT_VALID %>" <%=(bean.getIsTrained()==Constants.STATUS_NOT_VALID && bean.getDeclareId()>0)?"selected":"" %>>否</option>
								</select>
							</div>
						</div>
						<div class="form-group" id="otherTrain">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 参加其它农业培训：</label>
							<div class="col-md-8"><input type="number" class="form-control" name="otherTrainingTime" id="otherTrainingTime" value="<%=bean.getDeclareId() >0 ? bean.getOtherTrainingTime() : ""%>"/></div>
							<p class="form-control-static"> 次／年</p>
						</div> 
						<p class="bg-info pd15">获得证书情况</p>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 是否获得新型职业农民资格证</label>
							<div class="col-md-9" >
								<div class="u-checkbox cm-checkbox cc-checkbox <%=Constants.STATUS_VALID == bean.getHasNewTypeCertification()? "selected" : ""%>"  onclick="isHidden()">
									<input type="checkbox" name="hasNewTypeCertification" id="hasNewTypeCertification" <%=Constants.STATUS_VALID == bean.getHasNewTypeCertification()? "checked" : ""%> value="<%=Constants.STATUS_VALID%>"/>
								</div>
							</div>
						</div>
						<div id="certificateContent" <%=Constants.STATUS_VALID == bean.getHasNewTypeCertification()? "style='display:block'" : "style='display:none'"%>>
							<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 认定等级：</label>
								<div class="col-md-9">
									<select class="form-control" name="certificationGrade" id="certificationGrade">
										<option value="">请选择</option>
										<% String certificationGrade = bean.getCertificationGrade(); %>
										<%for(StatusBean<String,String> statusBean : FarmerInfoConfig.CERTIFICATION_LEVEL_LIST){ %>
										<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(certificationGrade)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
										<%} %>
									</select>
								</div>
								</div>
							<div class="form-group">
								<label class="col-md-3 required" for=""><i class="int-piont">*</i> 认定时间：</label>
								<div class="col-md-9"><input type="date" class="form-control" id="certificationDate" name="certificationDate" value="<%=bean.getCertificationDate() !=null ? DateKit.formatDate(bean.getCertificationDate(), DateKit.DEFAULT_DATE_FORMAT):"" %>"  autocomplete="off"/></div>
							</div>
							<div class="form-group">
								<label class="col-md-3 required" for=""><i class="int-piont">*</i> 认定部门：</label>
								<div class="col-md-9"><input type="text" class="form-control" id="certificationDepartment" name="certificationDepartment" value="<%=bean.getCertificationDepartment() %>"/></div>
							</div>
						</div>
               
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 未获得证书</label>
							<div class="col-md-9">
								<div class="u-checkbox checkAllNone <%=Constants.STATUS_VALID == bean.getHasNoCertification() ? "selected" : ""%>">
									<input type="checkbox" name="hasNoCertification" id="hasNoCertification" <%=Constants.STATUS_VALID == bean.getHasNoCertification() ? "checked" : ""%> value="<%=Constants.STATUS_VALID%>" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 新型职业农民培训证书</label>
							<div class="col-md-9">
								<div class="u-checkbox cm-checkbox cd-checkbox <%=Constants.STATUS_VALID == bean.getHasNewTypeTrainingCertification() ? "selected" : ""%>" >
									<input type="checkbox" name="hasNewTypeTrainingCertification" id="hasNewTypeTrainingCertification" <%=Constants.STATUS_VALID == bean.getHasNewTypeTrainingCertification() ? "checked" : ""%> value="<%=Constants.STATUS_VALID%>"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 国家职业资格证书</label>
							<div class="col-md-9">
								<div class="u-checkbox cm-checkbox cd-checkbox <%=Constants.STATUS_VALID == bean.getHasNationalCertification() ? "selected" : ""%>" >
									<input type="checkbox" name="hasNationalCertification" id="hasNationalCertification" <%=Constants.STATUS_VALID == bean.getHasNationalCertification() ? "checked" : ""%> value="<%=Constants.STATUS_VALID%>"/>
								</div>
							</div>
						</div>
						
						 <div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i>农民技术等级或职称证书：</label>
							<div class="col-md-9">
								<select class="form-control" name="certificationTitle" id="certificationTitle">
									<option value="">请选择</option>
									<% String certificationTitle = bean.getCertificationTitle(); %>
									<%for(StatusBean<String,String> statusBean : FarmerInfoConfig.FARMER_TITLE_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(certificationTitle)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
									<%} %>
								</select>
							</div>
						</div>

						<button class="btn btn-primary btn-block" id="tech-save-btn" type="button">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript" src="<%=STATIC_URL %>js/declare.js"></script>
<script>
$(document).ready(function(){
	isTrainedChange();
});
function isTrainedChange(){
	var train = $('#isTrained').val();
	if(train=="<%=Constants.STATUS_VALID%>"){
		$('#otherTrain').show();
	}else{
		$('#otherTrain').hide();
	}
}
$('#tech-save-btn').on('click',function(){
	var isTrained = $('#isTrained').val();
    if(isTrained==''){
    		alert("请填写[是否参加过新型职业农民培训]")
    		return false;
    } 
    var re = /^[0-9]+[0-9]*]*$/;
    var number = $('#otherTrainingTime').val();
    if(isTrained=='1'){
    if (!re.test(number)){
        alert("[参加其他培训次数]请输入数字");
        return false;
    }
    }
        var declareType = $('#declareType').val();
        var status = $('#status').val();
        var declareId = $('#declareId').val();
        var applyType = $('#applyType').val();
        var isTrained = $('#isTrained').val();
        var otherTrainingTime = $('#otherTrainingTime').val();
        var certificationGrade = $('#certificationGrade').val();
        var certificationDate = $('#certificationDate').val()+' 00:00:00';
        var certificationDepartment = $('#certificationDepartment').val();
        
        
        var hasNewTypeCertification = 0;
        if (typeof($("#hasNewTypeCertification").attr("checked"))!="undefined") {
        	hasNewTypeCertification = 1;
        }
        var hasNoCertification = 0;
        if (typeof($("#hasNoCertification").attr("checked"))!="undefined") {
        	hasNoCertification = 1;
        }
        var hasNewTypeTrainingCertification = 0 ;
        if (typeof($("#hasNewTypeTrainingCertification").attr("checked"))!="undefined") {
        	hasNewTypeTrainingCertification = 1 ;
        }
        var hasNationalCertification = 0;
        if (typeof($("#hasNationalCertification").attr("checked"))!="undefined") {
        	hasNationalCertification = 1;
        }

        var certificationTitle = $('#certificationTitle').val();
        
        if(typeof(applyType)=="undefined"||applyType.trim()==""){
        	alert("申请类型必须填写");
        	return false;
        }
        
        if(hasNewTypeCertification=="<%=Constants.STATUS_VALID%>"){
        	if(typeof(certificationGrade)=="undefined"||certificationGrade.trim()==""){
            	alert("认证等级必须填写");
            	return false;
            }else if(typeof(certificationDate)=="undefined"||certificationDate.trim()==""){
            	alert("认证时间必须填写");
            	return false;
            }else if(typeof(certificationDepartment)=="undefined"||certificationDepartment.trim()==""){
            	alert("认证部门必须填写");
            	return false;
            }
        }
        
        if(isTrained=="<%=Constants.STATUS_VALID%>"){
        	if(typeof(otherTrainingTime)=="undefined"||otherTrainingTime.trim()==""||otherTrainingTime.trim()=="0"){
            	alert("其他培训时间必须填写");
            	return false;
            }
        }
        
        $.ajax({
            url: 'FarmerSkillInfoManage.do',
            data: {
                declareType: declareType,
                status: status,
                declareId: declareId,
                applyType:applyType,
                isTrained: isTrained,
                otherTrainingTime: otherTrainingTime,
                hasNewTypeCertification:hasNewTypeCertification,
                certificationGrade:certificationGrade,
                certificationDate:certificationDate,
                certificationDepartment:certificationDepartment,
                hasNoCertification:hasNoCertification,
                hasNewTypeTrainingCertification:hasNewTypeTrainingCertification,
                hasNationalCertification:hasNationalCertification,
                certificationTitle:certificationTitle,
                
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
function isHidden(){
    var content = $('#certificateContent');
    if (typeof($("#hasNewTypeCertification").attr("checked"))=="undefined") {
    	content.show();
	}
    else{
    	content.hide();
    }
  }
</script>
</html>