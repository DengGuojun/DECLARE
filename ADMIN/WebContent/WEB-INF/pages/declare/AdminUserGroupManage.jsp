<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.framework.util.JsonKit"  %>
<%@ page import="com.lpmas.constant.*"  %>
<% 
	AdminUserGroupBean bean = (AdminUserGroupBean)request.getAttribute("UserGroup");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!–[if lte IE 8]> 
 <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
 <![endif]–> 
 <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
 <%@ include file="../include/header.jsp" %>
 <script type="text/javascript" src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
    <title>新型农民职业培训系统 — 主管机构管理</title>
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/SlideMenuStyle.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/default.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/StyleBlue.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/index_main.css" type="text/css" rel="Stylesheet">
    
    <style type="text/css">
        html
        {
            width: 100%;
        }
        body
        {
            width: 100%;
            margin-top: 0px;
            margin-left: 0px;
            margin-bottom: 10px;
            margin-right: 0px;
            font-size: 12px;
            font-weight: normal;
        }
        .text_t
        {
            font-size: 12px;
            color: #D6E8FF; /*F9C22D*/
            font-weight: bold;
        }
        .a1
        {
            font-size: 12px;
            color: #D6E8FF; /*EF6D05*/
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body class="article_bg">
	<%@include file="../nav/navigation.jsp" %>
	<form id="formData" name="formData" method="post" action="AdminUserGroupManage.do" onsubmit="javascript:return checkForm('formData');">
	<input type="hidden" name="groupId" id="groupId" value="<%=bean.getGroupId() %>"/>
    <div class="warpDefaultMgr">
        <table class="table_wrp">
            <tbody>
                <tr>
                    <td style="width: 191px; background-color: #f2f2f2;" valign="top">
                        <%@include file="../include/project_settings_left.jsp" %>
                    </td>
                    <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                      <span style="float: left;">编辑主管机构信息</span></div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                      <div>
                                       <table class="editView table_wrp gv" width="98%">
                                       	<tbody>
                                       		<tr>
                                                <td align="right" class="td_head" style="width:30%;">
                                                    <span style="color:#FE7200;">*</span>
                                                    <span>主管机构名称：</span>
                                                </td>
                                                <td align="left" class="td_data">
                                                    <input type="text" name="groupName" id="groupName" value="<%=bean.getGroupName() %>" checkStr="主管机构名称;txt;true;;100" maxlength="60" class="textEdit" style="width:100%;"></td>
                                            </tr>
                                            <tr>
                                                <td align="right" class="td_head" style="width:30%;">
                                                    <span style="color:#FE7200;">*</span>
                                                    <span>通讯地址：</span>
                                                </td>
                                                <td align="left" class="td_data">
                                                    <input type="text" name="address" id="address" value="<%=bean.getAddress() %>" checkStr="地址;txt;false;;100" maxlength="60" class="textEdit" style="width:100%;"></td>
                                            </tr>
                                            <tr>
                                                <td align="right" class="td_head" style="width:30%;">
                                                    <span style="color:#FE7200;">*</span>
                                                    <span>邮政编码：</span>
                                                </td>
                                                <td align="left" class="td_data">
                                                    <input type="text" name="zipCode" id="zipCode" value="<%=bean.getZipCode() %>" checkStr="邮政编码;txt;false;;100" maxlength="60" class="textEdit" style="width:100%;"></td>
                                            </tr>
                                            <tr>
                                                <td align="right" class="td_head" style="width:30%;">
                                                    <span>省：</span>
                                                </td>
                                                <td align="left" class="td_data">
                                                <select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()" >
                                     			</select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="right" class="td_head" style="width:30%;">
                                                    <span>市：</span>
                                                </td>
                                                <td align="left" class="td_data">
                                                <select class="form-control" name="selectCity" id="selectCity" onchange="showRegion()">
                                     			</select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="right" class="td_head" style="width:30%;">
                                                    <span>区：</span>
                                                </td>
                                                <td align="left" class="td_data">
                                                <select class="form-control" name="selectRegion" id="selectRegion" onchange="setRegion()">
                                     			</select>
                                                </td>
                                            </tr>
                                       	</tbody>
                                       </table>
                                       <div class="text_right mt_5" style="width:98%">
                                       		<input type="hidden" name="province" id="province" value="<%=bean.getProvince()%>"/>
                                             <input type="hidden" name="city" id="city" value="<%=bean.getCity()%>"/>
                                             <input type="hidden" name="region" id="region" value="<%=bean.getRegion()%>"/>
                                             <input type="hidden" name="status" id="status" value="<%=bean.getStatus()%>"/>
                                       		<input type="submit" value="保存" class="btn72" />
                                       </div>
                                      </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                </tr>
            </tbody>
        </table>
    </div>
    </form>
    <div class="contents-footer"></div>
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
</body>
<script>
$(document).ready(function() {
	showProvince();
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
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixProvince = $("#province").val();
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