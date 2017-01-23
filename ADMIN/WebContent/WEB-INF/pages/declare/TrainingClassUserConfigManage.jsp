<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>

<%
    AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper");
	TrainingClassUserConfigBean bean = (TrainingClassUserConfigBean)request.getAttribute("TrainingClassUserConfigBean") ;
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
    <title>新型农民职业培训系统 — 学院录入设置</title>
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
     <form id="formData" name="formData" method="post" action="TrainingClassUserConfigManage.do" onsubmit="javascript:return checkThisForm('formData');">
          <input type="hidden" name="status" id="status" value=<%=Constants.STATUS_VALID %> />
          <input type="hidden" name="configId" id="configId" value=<%=bean.getConfigId() %> />
          
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
                                        <%if(bean.getConfigId() > 0) {%>
                                      	编辑录入设置
                                      <%} else{%>
                                      	新增录入设置
                                      <%} %></span>
                                     </div>
                                    <div class="right_bg" style="padding-left: 5px;" >
                                      <div>
                                        <table class="editView table_wrp gv" style="width: 98%">
                                            <tbody>
                                               <tr>
                                                 <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>地区</span>
                                                    </td>
                                        
                                                   <td align="left" class="td_data">
                                                   
                                                    <input type="hidden" name="province" id="province" value="<%=bean.getProvince()%>"/>
    	   											<input type="hidden" name="city" id="city" value="<%=bean.getCity()%>"/>
    	  											<input type="hidden" name="region" id="region" value="<%=bean.getRegion()%>"/>
    
      												省<select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()" style="width:100px">
   	  												</select>
     											 	市<select class="form-control" name="selectProvince" id="selectCity" onchange="showRegion()" style="width:100px">
       												</select>
       												区<select class="form-control" name="selectProvince" id="selectRegion" onchange="setRegion()" style="width:100px">
      											    </select>
                                                        </td>
                                               
                                                </tr>
                                                 <tr>
                                                 <td align="right" class="td_head">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>设置模式</span>
                                                    </td>
                                        
                                                   <td align="left" class="td_data">
                                                          <select name="configMode" id="configMode" >
													      <%for(StatusBean<String, String> statusBean : TrainingClassUserConfig.CONFIG_MODE_LIST ){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(bean.getConfigMode()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
													      </select>
                                                        </td>
                                               
                                                </tr>
                              
                                                 <tr>
                                                 <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>录入频率</span>
                                                    </td>
                                        
                                                   <td align="left" class="td_data">
                                                            <select  name="configFrequency" id="configFrequency" >
													      	<%for(StatusBean<String, String> statusBean : TrainingClassUserConfig.CONFIG_FREQUENCY_LIST ){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(bean.getConfigFrequency()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
													       </select>
                                                        </td>
                                               
                                                </tr> 
                                            </tbody>
                                        </table>
                                        <div class="text_center mt_5" style="width: 98%">
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
    <script>
    function checkThisForm(){
    	 var province = $("#province").val() ;
    	 if(""==province){
    		 alert("请选择省份") ;
    		 $("#selectProvince").focus() ;
    		 return false ;
    	 }
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

    	sel.append("<option value =''></option>");
    	
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
    	var provinceName = $("#selectProvince  option:selected").text();
    	$("#province").val("") ; //清空隐藏域的值
    	$("#city").val("") ; //清空隐藏域的值
    	$("#region").val("") ; //清空隐藏域的值
    	
    	if("国家" != provinceName ){
    		$("#province").val(provinceName);//设置隐藏域的值 province
    	}
    	
    	if("" == provinceName ){
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