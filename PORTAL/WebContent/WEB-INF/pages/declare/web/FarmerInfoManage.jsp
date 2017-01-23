<%@page import="com.lpmas.framework.util.MapKit"%>
<%@page import="com.lpmas.declare.config.*"%>
<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@page import="com.lpmas.declare.config.FarmerInfoConfig"%>
<%@page import="com.lpmas.framework.bean.StatusBean"%>
<%@page import="com.lpmas.framework.util.DateKit"%>
<%@page import="com.lpmas.declare.portal.config.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.lpmas.declare.bean.*"%>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper" %>
<!DOCTYPE html>
<%
    SsoClientHelper helper = new SsoClientHelper(request, response, false);	
	DeclareInfoBean declareInfoBean = (DeclareInfoBean)request.getAttribute("DeclareInfoBean");
	FarmerInfoBean farmerInfoBean = (FarmerInfoBean) request.getAttribute("FarmerInfoBean");
	DeclareImageBean imageBean = (DeclareImageBean) request.getAttribute("DeclareImageBean");
	int declareType = ParamKit.getIntParameter(request, "declareType", 0);
%>
<%@ include file="../../include/header.jsp" %>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta content="c0c6d687cf0f11fc3f67955a6bba6d69c211075e" name="csrf-token">
		<title>在线申报系统-基本信息</title>
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
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=<%=declareType%>">云上智农管理中心</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class=" navbar-nav navbar-right">
					<li class="dropdown" style="margin-top: 15px;">
						<a href="javascript:;" style="text-decoration:none;cursor:default" class="dropdown-toggle"> <%=helper.getUserLoginId()%></a>
						<span>|</span>
						<a href="http://passport.ngonline.cn/user/UserInfoManage.do" class="dropdown-toggle" data-toggle="dropdown">账号设置</a>
						<span>|</span>
						<a href="http://passport.ngonline.cn/user/Logout.do">退出</a>
					</li>
				</ul>
			</div>
		</div>
</div>
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<ul class="u-tab">
				<%if(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) {%>
				    <li class="selected"><a href="<%=PORTAL_URL %>declare/FarmerInfoManage.do?declareType=<%=declareType%>">基本信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerContactInfoManage.do?declareType=<%=declareType%>">联系信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerSkillInfoManage.do?declareType=<%=declareType%>">申请培训信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerJobInfoManage.do?declareType=<%=declareType%>">农务工作信息</a></li>
				<%}else{ %>
					<li class="selected"><a href="<%=PORTAL_URL %>declare/FarmerInfoManage.do?declareType=<%=declareType%>">基本信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerContactInfoManage.do?declareType=<%=declareType%>">联系信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerSkillInfoManage.do?declareType=<%=declareType%>">申请培训信息</a></li>
					<li><a href="<%=PORTAL_URL %>declare/FarmerIndustryInfoManage.do?declareType=<%=declareType%>">生产经营状况</a></li>
				<%} %>
				</ul>
			</div>
			<div class="col-md-8">
				<p class="text-right"><a href="FormInstructionDisplay.do?declareType=<%=declareType%>">填表说明</a></p>
				<form class="declareInfo-cont form-horizontal">
				<input type="hidden" name="declareId" id="declareId" value="<%=declareInfoBean.getDeclareId() %>"><!--value-->
	            <input type="hidden" name="status" id="status" value="<%=declareInfoBean.getStatus()%>"><!--value-->
	            <input type="hidden" name="declareType" id="declareType" value="<%=ParamKit.getIntParameter(request, "declareType", 0) %>"><!--value-->
					<!-- 1 -->
					<div class="tab-cont">
						<div class="form-group">
							<label class="col-md-3 required" for="int-photo"><i class="int-piont">*</i> 1寸照片：</label>
							<div class="col-md-9">
								<!-- 图片上传 -->
								<div class="upload-img">
									<%if(imageBean!=null&&!imageBean.getImagePath().equals("")){ %>
	                                <img id="img_preview" src="<%=imageBean.getImagePath() %>"  />
	                                <%}else{ %>
	                                <span class="add-icon">+</span>
	                                <img id="img_preview" src="" class="plus-img" style="top:44px !important" />
	                                 <%} %>
									<input type="file" id="file" name="file" accept="image/*" onchange="up();" />
								</div>
								<!-- end -->
							</div>
							<input type="hidden" id="imagePath" name="imagePath" value="<%=imageBean.getImagePath()%>"/>
				            <input type="hidden" id="imageType" name="imageType" value="<%=DeclareImageInfoConfig.IMG_TYPE_ONE_INCH%>"/>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 姓名：</label>
							<div class="col-md-9"><input type="text" class="form-control" id="userName" name="userName" value="<%=farmerInfoBean.getUserName() %>"/></div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 性别：</label>
							<div class="col-md-9">
								<select class="form-control" name="userGender" id="userGender">
								<option value="">请选择</option>
								<% int userGender = farmerInfoBean.getUserGender(); %>
								 <%for(StatusBean<Integer,String> statusBean : GenderConfig.GENDER_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus()==userGender) ? "selected" : ""%>><%=statusBean.getValue() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 出生年月：</label>
							<div class="col-md-9"><input type="date" class="form-control" id="userBirthday" name="userBirthday" value="<%=farmerInfoBean.getUserBirthday() !=null ? DateKit.formatDate(farmerInfoBean.getUserBirthday(), DateKit.DEFAULT_DATE_FORMAT):"" %>"  autocomplete="off"/></div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 民族：</label>
							<div class="col-md-9">
								<select class="form-control" name="nationality" id="nationality">
								<option value="">请选择</option>
								<% String nationality = farmerInfoBean.getNationality(); %>
								 <%for(StatusBean<String,String> statusBean : NationalityConfig.NATIONALITY_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(nationality)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 文化程度：</label>
							<div class="col-md-9">
								<select class="form-control" name="education" id="education">
								<option value="">请选择</option>
								<% String education = farmerInfoBean.getEducation(); %>
								 <%for(StatusBean<String,String> statusBean : FarmerInfoConfig.EDUCATION_LEVEL_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(education)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 政治面貌：</label>
							<div class="col-md-9">
								<select class="form-control" name="politicalStatus" id="politicalStatus">
								<option value="">请选择</option>
								<% String politicalStatus = farmerInfoBean.getPoliticalStatus(); %>
								 <%for(StatusBean<String,String> statusBean : FarmerInfoConfig.POLITICAL_STATUS_LIST){ %>
									<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(politicalStatus)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
								<%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 身份证号：</label>
							<div class="col-md-9"><input type="text" class="form-control" id="identityNumber" name="identityNumber" value="<%=farmerInfoBean.getIdentityNumber() %>" /></div>
						</div>
						<div class="text-center">
							<button class="btn btn-primary btn-block" id="base-save-btn" type="button">保存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<section class="popup loading" id="loading" style="display: block;">
	    <div class="popup-content">
	        <img src="../sources/loading.gif" width="40" height="40">
	    </div>
	</section>
</body>
<script type="text/javascript" src="<%=STATIC_URL %>js/declare.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#base-save-btn").click(
			function() {
			//获取表单数据
			var declareId = $('#declareId').val();
			var declareType = $('#declareType').val();
			var status = $('#status').val();
			var imagePath = $('#imagePath').val();
			var imageType = $('#imageType').val();
			var userName = $('#userName').val();
			var userGender = $('#userGender').val();
			var userBirthday = $('#userBirthday').val();
			var nationality = $('#nationality').val();
			var education = $('#education').val();
			var major = $('#major').val();
			var politicalStatus = $('#politicalStatus').val();
			var identityNumber = $('#identityNumber').val();
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if (imagePath == ''){
				alert("图片必须上传");
				return;
			}
			if (userName == ''){
				alert("姓名必须填写");
				return;
			}
			if (userGender== ''){
				alert("性别必须填写");
				return;
			}
			if (userBirthday == ''){
				alert("出生日期必须填写");
				return;
			}
			if (nationality ==''){
				alert("民族必须填写");
				return;
			}
			if (education==''){
				alert("文化程度必须填写");
				return;
			}
			if (politicalStatus==''){
				alert("政治面貌必须填写");
				return;
			}
			if (identityNumber == ''){
				alert("身份证号必须填写");
				return;
			}
			if(reg.test(identityNumber) === false)
			{
			    alert("身份证输入不合法");
			    return ;
			}
			//保留表单验证
			
			//表单提交
			 $.ajax({
		            url: 'FarmerInfoManage.do',
		            data: {
		                declareId: declareId,
		                declareType: declareType,
		                status: status,
		                imagePath: imagePath,
		                imageType: imageType,
		                userName: userName,
		                userGender: userGender,
		                userBirthday: userBirthday,
		                nationality: nationality,
		                education: education,
		                major: major,
		                politicalStatus: politicalStatus,
		                identityNumber: identityNumber
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
	
	$('#loading').hide();
});

function up() {
	$('#loading').show();
    var file_data = $('#file')[0].files; // for multiple files
    //验证文件大小，文件类型
    var file = file_data[0];
    var size = file.size;
    var maxSize = <%=DeclareImagePortalConfig.MAX_SIZE%>;
    if(size>maxSize){
    	alert("文件大小超过限制");
    	return;
    }
    var name = (file.name).toLowerCase();
    if(!(name.lastIndexOf('jpg')>0||name.lastIndexOf('jpeg')>0||name.lastIndexOf('png')>0)){
    	alert("图片类型错误，请上传JPG或者PNG图片");
    	return;
    }
    //文件压缩
    var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');
    var scale = 0;
    var path = window.URL.createObjectURL(file);
    var imgData = "";
    var imgType = file.type;
    //判定压缩比
    if(size<=1024*1024){
    	scale = 1;
    }else{
    	scale = 0.5;
    }
    
    var image = new Image();
    image.onload = function() {
        if (image.width != canvas.width) {
            canvas.width = image.width;
        }
        if (image.height != canvas.height) {
            canvas.height = image.height;
        }
        ctx.clearRect(image,0, 0, canvas.width, canvas.height);
        ctx.drawImage(image, 0, 0);
        imgData = canvas.toDataURL(imgType,scale);
        window.URL.revokeObjectURL(path);
        
      //提交
        for (var i = 0; i < file_data.length; i++) {
            $.ajax({
                url: '/declare/DeclareImageManage.do?declareType=<%=ParamKit.getIntParameter(request, "declareType", 0) %>',
                data: {
                	imgData:imgData,
                	imgType:imgType
                },
                type: 'POST',
                success: function (data) {
                    console.log(data);
                    if(data.code==200){
                        var url=data.message;
                        var img_main = $('#img_main');
                        var img_preview = $('#img_preview');
                        $('#imagePath').val(url);
                        
                        img_preview.attr("src",url);
                        img_preview.attr("width","86");
                        img_preview.attr("height","129");
                        
                        img_main.attr("src",url);
                        img_main.removeClass("plus-img");
                        img_main.addClass("upload-img");
                        img_main.removeAttr('style');
                        img_main.attr('style',"top:0px");
                    }else{
                    	alert(data.message);
                    	$('#loading').hide();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(errorThrown);
                    alert(errorThrown);
                    $('#loading').hide();
                }
            });
        }
    }
    image.src=path;
    //文件压缩结束
    
    $('#img_preview')[0].onload = function() {
    	$('#loading').hide();
    }
}


</script>
</html>