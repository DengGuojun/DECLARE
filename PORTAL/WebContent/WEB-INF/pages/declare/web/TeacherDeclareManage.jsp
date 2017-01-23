<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@page import="com.lpmas.framework.util.MapKit"%>
<%@page import="com.lpmas.declare.config.*"%>
<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@page import="com.lpmas.declare.config.FarmerInfoConfig"%>
<%@page import="com.lpmas.framework.bean.StatusBean"%>
<%@page import="com.lpmas.framework.util.DateKit"%>
<%@page import="com.lpmas.declare.portal.config.*" %>
<%@page import="com.lpmas.declare.bean.*"%>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper" %>
<!DOCTYPE html>
<%
    SsoClientHelper helper = new SsoClientHelper(request, response, false);	
	TeacherDeclareBean bean = (TeacherDeclareBean)request.getAttribute("TeacherDeclareBean");
	List<TeacherMajorTypeBean> teacherMajorTypeList =  (List<TeacherMajorTypeBean>)request.getAttribute("TeacherMajorTypeList") ;
	List<FileInfoBean> attachList =(List<FileInfoBean>)request.getAttribute("attachList") ;
%>
<%@ include file="../../include/header.jsp" %>
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta content="c0c6d687cf0f11fc3f67955a6bba6d69c211075e" name="csrf-token">
		<title>师资申报系统</title>
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
		<script src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
				<a class="navbar-brand" href="<%=PORTAL_URL %>declare/TeacherDeclareManage.do">师资申报系统</a>
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
		<div class="detail-info">
			<h1>师资申报</h1>
		</div>
		<form name="formData" id="formData" method="post" action="TeacherDeclareManage.do" onsubmit="javascript:return checkThisForm('formData');">
		<input type="hidden" name="declareStatus" id="declareStatus" value="<%=bean.getDeclareStatus() %>">
	    <input type="hidden" name="status" id="status" value="<%=bean.getStatus()%>">
	    <input type="hidden" name="declareId" id="declareId" value="<%=bean.getDeclareId()%>">
		<div class="row">
			<div class="col-md-5">
					<div class="declareInfo-cont form-horizontal">
					<div class="tab-cont">
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 姓名：</label>
							<div class="col-md-9"><input type="text" name="teacherName" id="teacherName" value="<%=bean.getTeacherName() %>" class="form-control" checkStr="姓名;txt;true;;100"  /></div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 身份证号：</label>
							<div class="col-md-9"><input type="text" name="identityNumber" id="identityNumber" value="<%=bean.getIdentityNumber() %>"  class="form-control" checkStr="身份证;txt;true;;100" /></div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 所属地区：</label>
							<input type="hidden" name="province" id="province" value="<%=bean.getProvince() %>"/>
					         <input type="hidden" name="city" id="city" value="<%=bean.getCity() %>"/>
					         <input type="hidden" name="region" id="region" value="<%=bean.getRegion() %>"/>
							<div class="col-md-9">
			                    <select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()" style="width:120px" checkStr="地区;txt;true;;100"></select>
			                    <select class="form-control" name="selectCity" id="selectCity" onchange="showRegion()" style="width:120px" ></select>
			                    <select class="form-control" name="selectRegion" id="selectRegion" onchange="setRegion()" style="width:120px" ></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 工作单位：</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="company"  name="company"  value="<%=bean.getCompany() %>" checkStr="工作单位;txt;true;;100"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 职称名称：</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="technicalTitle" name="technicalTitle" value="<%=bean.getTechnicalTitle() %>" checkStr="职称名称;txt;true;;100" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 类型：</label>
							<div class="col-md-9">
							<select  name="teacherType" id="teacherType" checkStr="教师类型;txt;true;;100" >
                                 <option value=""></option>
					   			<%for(StatusBean<Integer, String> statusBean : TeacherInfoConfig.TEACHER_TYPE_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=((statusBean.getStatus()+"").equals(bean.getTeacherType()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
					   		</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 主授课程：</label>
							<div class="col-md-9">
								<input type="text" class="form-control" name="coursesOffer" id="coursesOffer" value="<%=bean.getCoursesOffer()%>" checkStr="主授课程;txt;true;;100"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 电子邮箱：</label>
							<div class="col-md-9">
								<input type="text" class="form-control"  name="email" value="<%=bean.getEmail() %>" id="email" checkStr="电子邮箱;mail;true;;100"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""> 相关材料：</label>
							<div class="col-md-9">
							 <%
							 	for(FileInfoBean fileBean : attachList){
							 	%>
							 	<a onclick="javascript:location.href='FileInfoDownload.do?fileId=<%=fileBean.getFileId()%>'"><%=fileBean.getFileName() %></a>&nbsp;&nbsp;&nbsp;
							 	<% 		
							 		}
							 	%>
							 <br/>
							 <%if(bean.getDeclareStatus().equals("")){%>
							<input type="file" id="fileUpload" name="fileUpload" multiple="multiple"  style="opacity: 100; position: relative;" onchange="fileUp();">
                    		    <input type="hidden" id="fileId" name="fileId" value=""/>
                    		    <%} %> 
							</div>
						</div>
					</div>
				</div>
				</div>
				<div class="col-md-5">
				<div class="declareInfo-cont form-horizontal">
					<!-- 1 -->
					<div class="tab-cont">
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 性别：</label>
							<div class="col-md-9">
								<select name="teacherGender" id="teacherGender">
								<%for(StatusBean<Integer, String> gender:GenderConfig.GENDER_LIST){ %>
								<option value="<%=gender.getStatus() %>" <%=(gender.getStatus()==bean.getTeacherGender())?"selected":"" %>><%=gender.getValue() %></option><%} %>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 出生日期：</label>
							<div class="col-md-9"><input type="text" class="form-control" name="teacherBirthday" id="teacherBirthday" value="<%=bean.getTeacherBirthday() == null ? "" : bean.getTeacherBirthday()%>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1940-01-01',maxDate:'2030-01-01'})" checkStr="出生日期;txt;true;;100"/></div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 编号：</label>
							<div class="col-md-9"><input type="text" class="form-control" name="teacherNumber" value="<%=bean.getTeacherNumber() %>" id="teacherNumber" checkStr="教师编号;txt;true;;100"/></div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 手机：</label>
							<div class="col-md-9">
								<input type="text" class="form-control"  name="phone" value="<%=bean.getPhone() %>" id="phone" checkStr="手机;digit;true;;"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""><i class="int-piont">*</i> 专业：</label>
							<div class="col-md-9">
								 <select name="majorTypeId" id="majorTypeId"  checkStr="专业;txt;true;;">
                                 <option value=""></option>
								<%for(TeacherMajorTypeBean majorTypeBean : teacherMajorTypeList){ %><option value="<%=majorTypeBean.getMajorId()%>" <%=majorTypeBean.getMajorId() == bean.getMajorTypeId()?"selected":"" %>><%=majorTypeBean.getMajorName() %></option><%} %>
						       </select>
						       <input type="hidden" id="originalMajorId" name="originalMajorId" value="<%=bean.getMajorId() %>">
                                <select name="majorId" id="majorId"  checkStr="专业;txt;true;;"></select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 required" for=""> 职称级别：</label>
							<div class="col-md-9">
								<select  name="technicalGrade" id="technicalGrade"  checkStr="职称级别;txt;false;;100">
                                  <option value=""></option>
							   <%for(StatusBean<String, String> statusBean : TeacherInfoConfig.TECHNICAL_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(bean.getTechnicalGrade()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
							   </select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<div class="form-group">
					<div class="col-md-11">
						<!-- 图片上传 -->
						<div>上传照片：</div>
						<div class="upload-img">
							 <%
                               if(bean!=null&&!bean.getTeacherImage().equals("")){
                            %>
                            	<img id="img_preview" src="<%=bean.getTeacherImage()%>">
                            <%	   
                               }else{
                            %>      
                            <span class="add-icon">+</span>                                                      
                            <img id="img_preview" src="" >
                            <%	   
                               }
                            %>
                             <input type="file" id="file" name="file" accept="image/*" onchange="up(this);"/>
                             <input type="hidden" id="imagePath" name="teacherImage" value="<%=bean.getTeacherImage()%>"/>
						</div>
						<!-- end -->
					</div>
				</div>
			</div>
			 <div id="loading" class="popup" style="display: block; ">
		    	<div class="loadingbox"><span class="loadingbtn"></span></div>
			</div>
			</div>
			<div class="text-center">
			    <%if(bean.getDeclareStatus().equals("")){%>
			    <button class="btn btn-primary btn-block" >提交审核</button>
				<%}else if(bean.getDeclareStatus().equals(TeacherInfoConfig.DECLATE_STATUS_COMMIT)){%>
				<button class="btn btn-primary btn-block" disabled >已提交，请等待审核</button>
				<%}else if (bean.getDeclareStatus().equals(TeacherInfoConfig.DECLATE_STATUS_FAIL) || 
						bean.getDeclareStatus().equals(TeacherInfoConfig.DECLATE_STATUS_REJECTED) ) {%>
				<button class="btn btn-primary btn-block" >审核驳回，请修改后重新提交</button>
				<%}else{%>
				<button class="btn btn-primary btn-block" disabled >审核通过，信息已入库</button>	
				<%} %>
			</div>
			</form>
</body>
<script type="text/javascript">
function checkThisForm(name){
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
 	  var cardNo = $("#identityNumber").val()  ;
    if(""==cardNo){
  	  alert("身份证号码不能为空");  
        $("#identityNumber").focus() ;
        return  false;  
    }

    if(reg.test(cardNo) === false)  
    {  
        alert("身份证号码格式不合法");  
        $("#identityNumber").focus() ;
        return  false;  
    }   
    var re4 = /^\d{10,11}$/;
    var userMobile = $("#phone").val() ;
    if(re4.test(userMobile) === false)
		{
		    alert("手机号输入不合法");
		    $("phone").focus() ;
		    return false;
		}
    
 	return checkForm(name) ;
}
function up() {
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
                url: '/declare/admin/DeclareImageManage.do',
                data: {
                	imgData:imgData,
                	imgType:imgType
                },
                type: 'POST',
                success: function (data) {
                    console.log(data);
                    if(data.code==200){
                        var url=data.message;
                        var img_preview = $('#img_preview');
                        $('#imagePath').val(url);
                        img_preview.attr("src",url);
                        img_preview.attr("width","86");
                        img_preview.attr("height","129");
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
}
}
$(document).ready(function() {
	showProvince();
	 $("#majorTypeId").on("change",function(){
			var majorTypeId = $("#majorTypeId").val() ;	
			if(majorTypeId == ""){
				$("#majorId").empty() ;
				return ;
			}
			var major = $("#majorId") ;
			major.empty() ; 
		    major.append("<option value =''></option>"); 
			$.ajax({
		        type: 'POST',
		        dataType:'json',
		        url: "TeacherMajorInfoJsonList.do?typeId="+majorTypeId,
		        success: function(data){
		        	//拼接字符串 
		        	if(data != null){
		    			 for (var i = 0; i < data.result.length; i++) {
							var item = data.result[i] ;
							var originalId = $('#originalMajorId').val();
							if(originalId == item.majorId){
								major.append("<option value = '"+item.majorId+"' selected>"+item.majorName+"</option>");
					      	}else{
					      		major.append("<option value = '"+item.majorId+"'>"+item.majorName+"</option>");
					      	}
						}  		
		        	}
		        },
		        error: function(){
		            return;
		        }
		    });
		}) ;
	$('#majorTypeId').trigger('change') ;
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
function showProvince(){
	$.ajax({
        type: 'POST',
        dataType:'jsonp',
        url: "http://ims.ngonline.cn/m/ProvinceList.action?jsoncallback=provinceData",
        success: function(data){
        },
        error: function(){
            return;
        }
    });
} 

function provinceData(data){
	var sel = $("#selectProvince");  
	sel.empty();  
	sel.append("<option value = ''></option>");
	var fixProvince = $("#province").val();
	if(fixProvince == '国家'){
		sel.append("<option value = '0' selected>国家</option>");
	}else{
		sel.append("<option value = '0'>国家</option>");
	}
	
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	if(fixProvince == item.provinceName){
	      		sel.append("<option value = '"+item.provinceId+"' selected>"+item.provinceName+"</option>");
	      	}else{
	      		sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
	      	}
	    };
	    if(fixProvince != ""){
	    		showCity();
	    }
	    
    } else{
   		sel.empty();  
    }
}

function showCity(){
	var provinceId = $("#selectProvince").val();
	var provinceName = $("#selectProvince  option:selected").text();
	$("#province").val(provinceName);
	if(provinceId == 0){
		return;
	}
	
	$.ajax({
        type: 'POST',
        dataType:'jsonp',
        url: "http://ims.ngonline.cn/m/CityList.action?provinceId="+provinceId+"&jsoncallback=cityData",
        success: function(data){
        },
        error: function(){
            return;
        }
    });
} 

function cityData(data){
	var sel = $("#selectCity");  
	sel.empty();  
	sel.append("<option value = ''></option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixCity = $("#city").val();
	      	if(fixCity == item.cityName){
	      		sel.append("<option value = '"+item.cityId+"' selected>"+item.cityName+"</option>");
	      	}else{
	      		sel.append("<option value = '"+item.cityId+"'>"+item.cityName+"</option>");
	      	}
	    };
	    if(fixCity != ""){
	    		showRegion();
	    }
    } else{
   		sel.empty();  
    }
}

function showRegion(){
	var cityId = $("#selectCity").val();
	var cityName = $("#selectCity  option:selected").text();
	$("#city").val(cityName);
	$.ajax({
        type: 'POST',
        dataType:'jsonp',
        url: "http://ims.ngonline.cn/m/RegionList.action?cityId="+cityId+"&jsoncallback=regionData",
        success: function(data){
        },
        error: function(){
            return;
        }
    });
} 

function regionData(data){
	var sel = $("#selectRegion");  
	sel.empty();  
	sel.append("<option value = ''></option>");
	var items=data.content.regionList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixRegion = $("#region").val();
	      	if(fixRegion == item.regionName){
	      		sel.append("<option value = '"+item.regionId+"' selected>"+item.regionName+"</option>");
	      		isFix = true;
	      	}else{
	      		sel.append("<option value = '"+item.regionId+"'>"+item.regionName+"</option>");
	      	}
	    };
    } else{
   		sel.empty();  
    }
}

function setRegion(){
	var regionName = $("#selectRegion  option:selected").text();
	$("#region").val(regionName);
}
function fileUp() {
    var file_data = $('#fileUpload')[0].files; // for multiple files
    for (var i = 0; i < file_data.length; i++) {
        var fd = new FormData();
        fd.append('file', file_data[i]);
        $.ajax({
        		url: 'FileInfoUpload.do?infoType=<%=FileInfoConfig.INFO_TYPE_TEACHER_DECLARE_ATTACH%>&infoId=<%=bean.getDeclareId() %>',
            data: fd,
            contentType: false,
            processData: false,
            type: 'POST',
            success: function (data) {
                console.log(data);
                if(data.code == '1'){
                		var dataObj=eval("("+data.message+")");
                		var orgId= $("#fileId").val();
                		$("#fileId").val(orgId+","+dataObj);
                		var fiId = $("#fileId").val();
                }else{
                		alert(data.message);
                		$("#fileId").val("");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    }
}

</script>
</html>