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
<%@ page import="com.lpmas.declare.business.DeclareInfoHelper"  %>
<%
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper");
	TrainingMaterialInfoBean bean = (TrainingMaterialInfoBean)request.getAttribute("TrainingMaterialInfoBean");

	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
	List<Integer> trainingYearList = declareInfoHelper.getTrainingYearList();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]>
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ />
    <![endif]–>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 教材管理</title>
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
        .tit
        {
            text-align: right;
            padding-right: 3px;
            background-color: #EEEFF0;
            word-wrap: break-word; 
            word-break: break-all;
            border: 1px solid #cecece;
        }
        .con
        {
            text-align: left;
            padding-left: 3px;
            border: 1px solid #cecece;
        }
        select
        {
            border: 0;
            border-bottom: 1px solid #C2C2C2;
            max-width:99%;
        }
        input[type='checkbox']
        {
            vertical-align: middle;
        }
        label
        {
            vertical-align: middle;
        }
        .noborder td
        {
            border:1px solid #FFFFFF;
            border-bottom:1px solid #cecece;
        }
        .table_fill td{ padding: 5px; }
        .table_fill .uploadImg{ padding: 5px 0; }
    </style>
</head>
<body class="body-index">
<%@include file="../nav/navigation.jsp" %>
    <form id="formData"  name="formData" action="TrainingMaterialInfoManage.do" method='post' onsubmit="javascript:return checkThisForm('formData');">
        <div class="warpDefaultMgr">
            <table class="table_wrp">
                <tbody>
                    <tr>
                        <td style="width: 191px; background-color: #f2f2f2;" valign="top">
                            <div class="detail_left">
                                <div class="detail_left_top"></div>
                                <div id="listItem" class="detail_left">
                                     <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
                        		%>
                        		   <div class="detail_left_box detail_box3">
                                    	<a onclick="javascript:location.href='TrainingMaterialInfoList.do?materialType=<%=TrainingMaterialConfig.MATERIAL_GENERAL%>'" >通用知识教材</a>
                                    </div>
                        		<%	 
                        		}
                                %>
                                 <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
                        		%>
                        		    <div class="detail_left_box detail_box3">
                                    	<a onclick="javascript:location.href='TrainingMaterialInfoList.do?materialType=<%=TrainingMaterialConfig.MATERIAL_PROFESSIONAL%>'" >专业技能教材</a>
                                    </div>
                        		<%	 
                        		}
                                %>   
                                </div>
                            </div>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">
                                        <% if(bean.getMaterialId() > 0) {
                                       	%>
                                       	修改教材信息
                                        <% } else{
                                        %>
                                                                                                          增加教材信息
                                        <% 	
                                        }%>
                                        </span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    
                                    <%--查询的图书类型 --%>
                                    <input type="hidden" name="materialType" id="materialType" value="<%=ParamKit.getParameter(request, "materialType",TrainingMaterialConfig.MATERIAL_GENERAL) %>">
                                    <input type="hidden" name="materialId" value="<%=bean.getMaterialId()%>" />
                             		<input type='hidden' name="status" value="<%=Constants.STATUS_VALID %>" />
        		<table class="table_comment">
        			<tbody>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>年份</td>
        					<td>
        					<select name="trainingYear" id="trainingYear">
        						<option value="">请选择</option>
        						<% for(Integer year : trainingYearList ){ %>
        							<option value="<%=year %>" <%=(year+"").equals(bean.getTrainingYear())?"selected":""%> ><%=year %></option>
        						<% } %>
        					</select>
        					</td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>省份</td>
        					<td>
                                 <div class="form-group form-horizontal"> 
                
                                        	<input type="hidden" name="province" id="province" value="<%=(bean.getProvince().equals(""))?"国家":bean.getProvince()%>" />
    	   									<input type="hidden" name="city" id="city" value="<%=bean.getCity()%>"/>
    	  								    <input type="hidden" name="region" id="region" value="<%=bean.getRegion()%>"/>
                                        	
                                        	<label>省：</label>
                                            <select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()"></select>  	 
                                        	<label>市：</label>
                                        	<select class="form-control" name="selectProvince" id="selectCity" onchange="showRegion()" ></select>
                                        	<label>县：</label>
                                        	<select class="form-control" name="selectProvince" id="selectRegion" onchange="setRegion()" ></select> 
                                        </div>
                            </td>
        				</tr>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>教材名称</td>
        					<td>
        						 <input id="materialName" name="materialName" type="text" class="form-control" value="<%=bean.getMaterialName() %>" checkStr="教材名称;txt;true;;100"/>
        					</td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>组编单位</td>
        					<td> <input id="compileOrganization" name="compileOrganization" type="text" class="form-control" value="<%=bean.getCompileOrganization() %>" checkStr="组编单位;txt;true;;100"/></td>
        				</tr>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>出版社</td>
        					<td><input id="publishingCompany" name="publishingCompany" type="text" class="form-control" value="<%=bean.getPublishingCompany() %>" checkStr="出版社;txt;true;;100"/></td>
        				</tr>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>出版年</td>
        					<td>
        					<input type="text" name="publishingYeah" id="publishingYeah"
						   onclick="WdatePicker({dateFmt:'yyyy',minDate:'2008',maxDate:'2020'})"
						   size="4"  checkStr="出版年;txt;true;;100" width=6 ;
						   value="<%=bean.getPublishingYeah() %>"  />
                            </td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>出版月</td>
        					<td> <input type="hidden" id="publishingMonth" name="publishingMonth" value="<%=bean.getPublishingMonth()%>">
                                <select id='month'> 
                                <%
                                      for(int i = 1 ;i<13 ;i++){
                                %>
                                	<option  value="<%=i%>" <% if((""+i).equals(bean.getPublishingMonth())){%> selected <%} %>>
                                	<%=i %>
                                	</option>
                                <%
                                      }
                                %>
                                </select>   
                          </td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>字数（千字）</td>
        					<td>
        					<input id="wordQuantity" name="wordQuantity" type="text" class="form-control" value="<%=bean.getWordQuantity()==0?"":bean.getWordQuantity() %>" checkStr="字数;num;true;;100"/>
        					</td>
        				</tr>
                        <tr>
                            <td class="tit"><span style="color: #ff0000;">*</span>印张（张）</td>
                            <td><span>
							<input id="paperQuantity" name="paperQuantity" type="text" class="form-control" value="<%=bean.getPaperQuantity()==0?"":bean.getPaperQuantity() %>" checkStr="印张;digit;true;;100"/>
							</span></td>
                        </tr>
                        <tr>
                            <td class="tit"><span style="color: #ff0000;">*</span>价格（元）</td>
                            <td><span><input id="price" name="price" type="text" class="form-control" value="<%=bean.getPrice()==0?"":bean.getPrice() %>" checkStr="价格;num;true;;100"/></span></td>
                        </tr>
                        <tr>
                            <td class="tit"><span style="color: #ff0000;">*</span>产业</td>
                            <td><span><input id="industry" name="industry" type="text" class="form-control" value="<%=bean.getIndustry() %>" checkStr="产业;txt;true;;100"/></span></td>
                        </tr>
                        <tr>
                            <td class="tit"><span style="color: #ff0000;">*</span>链接</td>
                            <td><span><input id="link" name="link" type="text" class="form-control" value="<%=bean.getLink() %>" checkStr="链接;txt;true;;100"/></span></td>
                        </tr>
        			</tbody>
        		</table>
        	
        	<div class="dialog_ft text_center">
        		<input type="submit" class="btn72" value="保 存">&nbsp;&nbsp;	
        		<input class="btn72" type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">
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
    <script src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
   	<script>
   	function checkThisForm(name){
   		var trainingYear = $("#trainingYear").val() ;
  	  	if(trainingYear <= 0){
  		  alert("请选择正确的年份") ;
  			$('#trainingYear').focus() ;
  			return false ;
  	  }
   		$("#publishingMonth").val($("#month").val()) ;
   		return checkForm(name) ;
   	}
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

	var city = $("#selectCity");
	city.empty() ;  //清除城市下拉框
	
	var region = $("#selectRegion");
	region.empty() ;  //清除区域下拉框
	
	sel.append('<option <%=bean.getProvince().equals("")?"selected":"" %>>国家</option>');
	
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixProvince = "<%=bean.getProvince() %>";
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
	var provinceName = $("#selectProvince option:selected").text();
	$("#province").val("") ; //清空隐藏域的值
	$("#city").val("") ; //清空隐藏域的值
	$("#region").val("") ; //清空隐藏域的值
	
	$("#province").val(provinceName);//设置隐藏域的值 province
	
	if("国家" == provinceName ){
		
		var city = $("#selectCity") ;
		city.empty() ;  //清除城市下拉框
		
		var region = $("#selectRegion") ;
		region.empty() ;  //清除区域下拉框
		
		return ;
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
	
	var region = $("#selectRegion") ;
	region.empty() ;  //清除区域下拉框
	
	sel.append("<option value = ''></option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixCity = "<%=bean.getCity()%>";
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
	$("#region").val("") ; 
	
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
	      	var fixRegion = "<%=bean.getRegion()%>";
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
</body>
</html>