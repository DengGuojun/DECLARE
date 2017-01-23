<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
	List<AdminUserGroupBean> list = (List<AdminUserGroupBean>)request.getAttribute("UserGroupList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 人员管理</title>
    <%@ include file="../include/header.jsp" %>
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
<body class="body-index">
	<%@include file="../nav/navigation.jsp" %>
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
                                  <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">人员管理</span></div>
                                 <div class="right_bg" style="padding-left: 5px;">
                                	 <form name="formSearch" method="post" action="AdminUserGroupList.do">
                                  	<div class="form-group form-horizontal">
                                  	<label>省：</label>
                                  	<select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()">
                                     </select>
                                     <label>市：</label>
                                  	<select class="form-control" name="selectCity" id="selectCity" onchange="showRegion()">
                                     </select>
                                     <label>区：</label>
                                  	<select class="form-control" name="selectRegion" id="selectRegion" onchange="setRegion()">
                                     </select>
                                     <div class="form-group form-horizontal">
                                      	<label>机构名称：</label>
										<input type="text" name="groupName" id="groupName" value="<%=ParamKit.getParameter(request, "groupName", "") %>" size="20"/>
                                      </div>
                                      <div class="form-group form-horizontal">
                                      	<input type="checkbox" name="onlyProvince" <%=StringKit.isValid(ParamKit.getParameter(request, "onlyProvince", ""))? "checked" : "" %>/>
                                      	<span>只查省级</span>
                                      </div>
                                      <div class="form-group form-horizontal">
                                      	<input type="checkbox" name="directUnder" <%=StringKit.isValid(ParamKit.getParameter(request, "directUnder", ""))? "checked" : "" %>/>
                                      	<span>只查直属</span>
                                      </div>
					  			   </div>
					  			    <input type="hidden" name="province" id="province" value="<%=ParamKit.getParameter(request, "province", "") %>"/>
                                     <input type="hidden" name="city" id="city" value="<%=ParamKit.getParameter(request, "city", "") %>"/>
                                     <input type="hidden" name="region" id="region" value="<%=ParamKit.getParameter(request, "region", "") %>"/>
					  				<input type="submit" value="查 询" class="btn72" />
					  				<input type="button" value="录入部门" class="btn72" onclick="javascript:location.href='AdminUserGroupManage.do'"/>
					  			</form>
                                  <div>
                                    <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                      <tbody>
                                        <tr class="gv_Head">
                                           <th style="width:30px;">行号</th>
                                           <th>所在地域</th>
									      <th>机构名称</th>
									      <th>通讯地址</th>
									      <th>邮政编码</th>
									      <th></th>
									      <th></th>
                                        </tr>
                                        <%
                                        int rowCount = 1;
									    for(AdminUserGroupBean bean:list){ 
									    %>
                                        <tr class="gv_Item">
                                          <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                          <%if(bean.getAdminGroupLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {%>
                                           <td>国家</td>
                                          <%}else{ %>
                                          <td><%=bean.getProvince()%><%=bean.getCity()%><%=bean.getRegion()%></td>
                                          <%} %>
								   	   	 <td><%=bean.getGroupName() %></td>
								   	   	 <td><%=bean.getAddress() %></td>
								   	   	 <td><%=bean.getZipCode() %></td>
								   	   	 <td><a href="AdminUserGroupManage.do?groupId=<%=bean.getGroupId() %>">编辑</a></td>
								      	 <td><a href="AdminUserInfoList.do?groupId=<%=bean.getGroupId() %>">查看人员</a></td>
                                        </tr>
                                        <% rowCount++;} %>
                                      </tbody>
                                    </table>
                                  </div>
								<%@ include file="../include/page.jsp" %>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
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
	if(cityName == ""){
		$("#selectRegion").empty();
		$("#region").val("");
	}else{
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
