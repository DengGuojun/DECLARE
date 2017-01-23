<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.StatusBean" %>
<%@ page import="com.lpmas.declare.bean.FarmerContactInfoBean" %>
<%@page import="com.lpmas.declare.config.*"%>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper" %>
<% 
    SsoClientHelper helper = new SsoClientHelper(request, response, false);	
    FarmerContactInfoBean bean = (FarmerContactInfoBean)request.getAttribute("FarmerContactInfoBean");
	int declareType = ParamKit.getIntParameter(request, "declareType", 0);
%>
<%@ include file="../../include/header.jsp" %>
<!DOCTYPE html >
<html lang="en">
 <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta content="c0c6d687cf0f11fc3f67955a6bba6d69c211075e" name="csrf-token">
		<title>在线申报系统-联系信息</title>
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
<body >
	 <input type="hidden" name="declareId" id="declareId" value="<%=bean.getDeclareId()%>"/>
	 <input type="hidden" name="status" id="status" value="<%=bean.getStatus()%>"/>
	 <input type="hidden" name="country" id="country" value="中国">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<ul class="u-tab">
					<%if(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {%>
				    <li><a href="<%=PORTAL_URL %>declare/FarmerInfoManage.do?declareType=<%=declareType%>">基本信息</a></li>
					<li class="selected"><a href="<%=PORTAL_URL %>declare/FarmerContactInfoManage.do?declareType=<%=declareType%>">联系信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerSkillInfoManage.do?declareType=<%=declareType%>">申请培训信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerJobInfoManage.do?declareType=<%=declareType%>">农务工作信息</a></li>
				<%}else{ %>
					<li><a href="<%=PORTAL_URL %>declare/FarmerInfoManage.do?declareType=<%=declareType%>">基本信息</a></li>
					<li class="selected"><a href="<%=PORTAL_URL %>declare/FarmerContactInfoManage.do?declareType=<%=declareType%>">联系信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerSkillInfoManage.do?declareType=<%=declareType%>">申请培训信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerIndustryInfoManage.do?declareType=<%=declareType%>">生产经营状况</a></li>
				<%} %>
				</ul>
			</div>
			<div class="col-md-8">
				<p class="text-right"><a href="FormInstructionDisplay.do?declareType=<%=declareType%>">填表说明</a></p>
				<form class="declareInfo-cont form-horizontal">
				 <input type="hidden" name="declareType" id="declareType" value="<%=declareType%>"/>
					<!-- 1 -->
					<div class="tab-cont">
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 手机号码：</label>
							<div class="col-md-9"><input class="form-control" type="number" name="userMobile" id="userMobile" value="<%=bean.getUserMobile() %>"  /></div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 电子邮箱：</label>
							<div class="col-md-9"><input type="text" class="form-control" name="userEmail" id="userEmail" value="<%=bean.getUserEmail() %>"/></div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> QQ号：</label>
							<div class="col-md-9"><input class="form-control" type="number" name="userQq" id="userQq" value="<%=bean.getUserQq() %>" /></div>
						</div>
						<div class="form-group">
							<label class="col-md-3" for=""><i class="int-piont">*</i> 微信号：</label>
							<div class="col-md-9">
								<input class="form-control" type="text" name="userWechat" id="userWechat" value="<%=bean.getUserWechat() %>" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 户籍所在地：</label>
							<input type="hidden" name="perProvince" id="perProvince" value="<%=bean.getProvince()%>"/>
							<input type="hidden" name="perCity" id="perCity" value="<%=bean.getCity()%>"/>
							<input type="hidden" name="perRegion" id="perRegion" value="<%=bean.getRegion()%>"/>
							<div class="col-md-9">
								<div class="row">
									<div class="col-md-4">
										<select class="form-control" id="personProvince" >
											<option>请选择</option>
										</select>
									</div>
									<div class="col-md-4">
										<select class="form-control" id="personCity" >
											<option>请选择</option>
										</select>
									</div>
									<div class="col-md-4">
										<select class="form-control" id="personRegion" >
											<option>请选择</option>
										</select>
									</div>
									<input type="hidden" id="queryCity1" />
									<input type="hidden" id="queryRegion1" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont"></i> 通讯地址：</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="address" id="address" value="<%=bean.getAddress() %>" />
							</div>
						</div>
						<button class="btn btn-primary btn-block" id="contact-save-btn" type="button">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=STATIC_URL %>js/declare.js"></script>
<script>
$('#contact-save-btn').on('click',function(){
    var re = /^[1-9]+[0-9]*]*$/;

        var declareType = $('#declareType').val();
        var status = $('#status').val();
        var declareId = $('#declareId').val();
        var userMobile = $('#userMobile').val();
        var userEmail = $('#userEmail').val();
        var userQq = $('#userQq').val();
        var userWechat = $('#userWechat').val();
        var province = $('#personProvince option:selected').text();
        var city = $('#personCity option:selected').text();
        var region = $('#personRegion option:selected').text();
        var address = $('#address').val();
        if (userMobile == ''){
			alert("手机必须填写");
			return;
		}
        if (province == '' || city == '' || region == ''|| province == '全部' || city == '全部' || region == '全部'|| province == '请选择' || city == '请选择' || region == '请选择'){
			alert("户籍所在地必须填写");
			return;
		}
        $.ajax({
            url: 'FarmerContactInfoManage.do',
            data: {
                declareType: declareType,
                status: status,
                declareId: declareId,
                userMobile: userMobile,
                userEmail: userEmail,
                userQq: userQq,
                userWechat: userWechat,
                province: province,
                city: city,
                region: region,
                address: address
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