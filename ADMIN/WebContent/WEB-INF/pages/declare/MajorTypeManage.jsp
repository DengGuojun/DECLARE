<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.business.DeclareInfoHelper"  %>

<%
    AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper");
    List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	MajorTypeBean bean =(MajorTypeBean)request.getAttribute("MajorTypeBean") ; 
	List<MajorTypeBean> majorTypeList = (List<MajorTypeBean>)request.getAttribute("MajorTypeList") ;
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
    <title>新型农民职业培训系统 — 专业类型编辑页面</title>
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
     <form id="formData" name="formData" method="post" action="MajorTypeManage.do" onsubmit="javascript:return checkForm('formData');">
          <%-- 判断是否修改或者新增信息 --%>
          <input type="hidden" name="majorId" id="majorId" value=<%=bean.getMajorId() %> />
           
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
                                      <span style="float: left;">             
                                        <%if(bean.getMajorId()>0) {%>
                                      	编辑专业类型
                                      <%} else{%>
                                      	新增专业类型
                                      <%} %></span>
                                     </div>
                                    <div class="right_bg" style="padding-left: 5px;" >
                                      <div>
                                        <table class="editView table_wrp gv" style="width: 98%">
                                            <tbody>
                                                
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>专业类型名称</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                        <input id="majorName"  name="majorName" type="text" value="<%=bean.getMajorName() %>" maxlength="60" checkStr="专业名称;txt;true;;100" class="textEdit" style="width:100%;"></td>
                                                </tr>
                                                <tr>
                                                 <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>有效状态</span>
                                                    </td>
                                        
                                                   <td align="left" class="td_data" style="width:160px;">
                                                             <select  name="status" id="status" >
													      	<%for(StatusBean<Integer, String> statusBean : Constants.STATUS_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(bean.getStatus()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
													       </select>
                                                        </td>
                                               
                                                </tr>
                                                
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>地区</span>
                                                    </td>
                                                    <td align="left" class="td_data" style="width:160px;">
                           							<input type="hidden" name="province" id="province" value="<%=bean.getProvince()%>"/>
    	   											<input type="hidden" name="city" id="city" value="<%=bean.getCity()%>"/>
    	  											<input type="hidden" name="region" id="region" value="<%=bean.getRegion()%>"/>
    
      												<select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()" style="width:100px">
   	  												</select>
     											 	<select class="form-control" name="selectProvince" id="selectCity" onchange="showRegion()" style="width:100px">
       												</select>
       												<select class="form-control" name="selectProvince" id="selectRegion" onchange="setRegion()" style="width:100px">
      											    </select>
                                                    </td>
                                                </tr>
                                                
                                     
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>专业年份</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                    
                                                    <select name="majorYear" id="majorYear">
        						<option value="">请选择</option>
        						<% for(Integer year : trainingYearList ){ %>
        							<option value="<%=year %>" <%=(year+"").equals(bean.getMajorYear())?"selected":""%> ><%=year %></option>
        						<% } %>
        						
        					</select>
                                                          
                                                    </td>
                                                </tr>
                                              
                                            </tbody>
                                        </table>
                                        <div class="text_center mt_5" style="width: 98%">
                                            <input type="submit" value="保 存" class="btn72" />
                                            <input type="button" class="btn72" value="取消" onclick="javascript:history.back()">
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
     <script type="text/javascript" src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
    <script>
function checkThisForm(formName){
	var majorYear = $("#majorYear").val() ;
	alert("majorYear " + majorYear) ;
	if("" == majorYear){
		alert("请选择有效的专业年份") ;
		$('#majorYear').focus() ;
		return false ;
	}
	return checkForm(formName) ;
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

	var city = $("#selectCity") ;
	city.empty() ;  //清除城市下拉框
	
	var region = $("#selectRegion") ;
	region.empty() ;  //清除区域下拉框

	sel.append("<option value =''>国家</option>");
	
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixProvince = "<%=bean.getProvince()%>";
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
	//alert("provinceId " + provinceId) ;
	var provinceName = $("#selectProvince  option:selected").text();
	$("#province").val("") ; //清空隐藏域的值
	$("#city").val("") ; //清空隐藏域的值
	$("#region").val("") ; //清空隐藏域的值
	
	if("国家" != provinceName ){
		$("#province").val(provinceName);//设置隐藏域的值 province
	}
	
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