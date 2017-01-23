<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lpmas.framework.web.*"  %>
<!DOCTYPE html>
<html style="margin-bottom:0px;">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
	<meta http-equiv=”x-ua-compatible” content=”IE=7″ /> 
	<![endif]–> 
	<meta http-equiv="x-ua-compatible" content="IE=edge,chrom=1" />
    <title>新型职业农民培育工程信息管理系统</title>
    <%@ include file="../include/header.jsp" %>
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <style type="text/css">
        body
        {
            background-color: #e9f1f4;
        }
        .div
        {
            width:522px;
            height:426px;
            position:absolute;
            top:50%;
            left:50%;
            background:url(../images/login_box_bg.png) no-repeat;
            margin:-213px 0 0 -261px;
            }
        .div1
        {
            margin-top:140px;
            padding-left:30px;
            }
        .div2
        {
            margin-top:55px;
            padding-left:40px;
            }
        .div3
        {
            margin-top:25px;
            padding-left:40px;
            }
        .div4
        {
            margin-top:10px;
            text-align:center;
            }
        .div5
        {
            margin-top:18px;
            text-align:center;
            }
    </style>
</head>
<body>
    <div class="div">
        <form id="formData" name="formData"  method="post"  onsubmit="javascript:return checkForm('formData');">
         <input type="hidden" name="province" id="province" value="<%=ParamKit.getParameter(request, "province", "") %>"/>
         <input type="hidden" name="city" id="city" value="<%=ParamKit.getParameter(request, "city", "") %>"/>
         <input type="hidden" name="region" id="region" value="<%=ParamKit.getParameter(request, "region", "") %>"/>
            <div class="div1">
                <span class="span-row">
                    省：
                    <select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()" style="width:120px"></select>
                </span>
                <span class="span-row">
                    市：
                    <select class="form-control" name="selectCity" id="selectCity" onchange="showRegion()" style="width:120px"></select>
                </span>
                <span class="span-row">
                    县：
                    <select class="form-control" name="selectRegion" id="selectRegion" onchange="setRegion()" style="width:120px"></select>
                </span>
            </div>
            <div class="div2">
                <span class="span-row">
                    用户名：
                    <input name="loginId" type="text" maxlength="20" id="TextBoxLoginName" class="input-text input-con font14" checkStr="用户名;txt;true;;16" ></span>
                <span class="span-row">
                    密码：
                    <input name="loginPassword" type="password" maxlength="20" id="TextBoxPwd" class="input-text input-con font14" checkStr="密码;txt;true;;16"></span>
            </div>
            <div class="div3">
                <span style="padding-right: 32px">
                    <span class="span-row">
                        验证码：
                        <input type="text" class="input-text input-con font14" name="kaptcha" value="" /></span>
                    		<span class="span-row validation">
                        <img src="kaptcha.jpg" id="kaptchaImage" /></span>
                    		<span class="span-row input-note"> <strong style="cursor: pointer;" id="kaptchaChange">看不清，换一张？</strong>
                    </span>
                </span>
            </div>
            <div class="div4">
                <input type="image" name="ButtonLogin" id="ButtonLogin" class="login-bt" src="<%=STATIC_URL %>/images/login_btn.png" style="border-width:0px;" ></div>
            <div class="div5">
                <span style=" font-size: larger; font-weight: bolder; color: #fff;">农业部科技教育司  农业部农民科技教育培训中心</span>
                <br>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">  
    $(function() {
        $('#kaptchaChange').click(function() {
        		$('#kaptchaImage').attr('src','kaptcha.jpg?' + Math.floor(Math.random() * 100));
     	});  
    });  
</script>
<script>
$(document).ready(function() {
	showProvince();
	$('#kaptchaImage').attr('src','kaptcha.jpg?' + Math.floor(Math.random() * 100));
});

function showProvince(){
	$.ajax({
        type: 'POST',
        dataType:'jsonp',
        url: "<%=STATIC_URL%>/m/ProvinceList.action?jsoncallback=provinceData",
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
        url: "<%=STATIC_URL%>/m/CityList.action?provinceId="+provinceId+"&jsoncallback=cityData",
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
        url: "<%=STATIC_URL%>/m/RegionList.action?cityId="+cityId+"&jsoncallback=regionData",
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
</script>
</html>
