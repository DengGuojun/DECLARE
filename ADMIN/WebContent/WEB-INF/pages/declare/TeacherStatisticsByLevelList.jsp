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
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	Map<String,TeacherStatisticsBean> result = (Map<String,TeacherStatisticsBean>)request.getAttribute("TeacherStaticsticsResultMap")	;	
	int type = ParamKit.getIntParameter(request, "type", 1) ;  //默认获取按照等级来划分
	String province = ParamKit.getParameter(request, "province","") ;
 	String city = ParamKit.getParameter(request, "city","") ;
 	String fixProvince = (String)request.getAttribute("FixProvince");
	String fixCity = (String)request.getAttribute("FixCity");
	String fixRegion = (String)request.getAttribute("FixRegion");
	String queryProvince = ParamKit.getParameter(request, "queryProvince", "");
	String queryCity = ParamKit.getParameter(request, "queryCity", "");
	String queryRegion = ParamKit.getParameter(request, "queryRegion", "");
	java.text.NumberFormat percentFormat = java.text.NumberFormat.getPercentInstance();
	percentFormat.setMaximumFractionDigits(2); // 最大  小数位  数
	percentFormat.setMaximumIntegerDigits(3);// 最大 整数位 数
	percentFormat.setMinimumFractionDigits(1); // 最小小数位数
	percentFormat.setMinimumIntegerDigits(1);// 最小整数位数
	
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 分级师资统计</title>
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
    <form name="ctl01" method="post"  id="ctl01" action="TeacherStatisticsByLevelList.do" name="formData">
        <div class="warpDefaultMgr">
            <table class="table_wrp">
                <tbody>
                    <tr>
                        <td style="width: 191px; background-color: #f2f2f2;" valign="top">
                            <%@include file="../include/nurturing_teachers_left.jsp" %>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                      <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">分级师资统计 </span></div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    	<div class="form-group form-horizontal">
                                    		<select class="form-control" name="type" id="type">
                                                <option value="1" <%=1==ParamKit.getIntParameter(request, "type", 1)?"selected":"" %>>按职称统计</option>
                                                <option value="2" <%=2==ParamKit.getIntParameter(request, "type", 1)?"selected":"" %>>按类型统计</option>
                                            </select>
                                     		<input type="hidden" name="queryProvince" id="queryProvince" value="<%=queryProvince%>"/>
    	   									<input type="hidden" name="queryCity" id="queryCity" value="<%=queryCity%>"/>
    	  								    <input type="hidden" name="queryRegion" id="queryRegion" value="<%=queryRegion%>"/>
                                        	
                                        	<label>省：</label>
                                            <select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()"></select>  	 
                                        	<label>市：</label>
                                        	<select class="form-control" name="selectProvince" id="selectCity" onchange="showRegion()" ></select>
                                        	<label>县：</label>
                                        	<select class="form-control" name="selectProvince" id="selectRegion" onchange="setRegion()" ></select>
                                        	<%
                                        		if(adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_STATISTICS_BY_LEVEL,OperationConfig.SEARCH)){
                                        	%>
                                        		<input type="submit" value="查 询" class="btn72" />
                                        	<%		
                                        		}
                                        	%>
                                        	
                                        	 <%
                                        	 if(StringKit.isValid(province)||StringKit.isValid(city)){
                                        	%>
                                        	<input type="button" value="返回" class="btn72" onclick="javascript:history.back()"/>
                                       		 <%	 
                                        	 }
                                        	 %>
                                         </div> 
                                         <div>
                                            <!-- 等级  -->
                                            <table id="teacherStatistics" class="gv" cellspacing="0" rules="all" border="1" style="border-collapse:collapse; width: 97%; ">
                                             <tbody>
                                               <% if(type == 1){ %>
                                                <tr class="gv_Head">
                                                  <th rowspan="2" style="width:30px;">序号</th>
                                                  <th rowspan="2" ><a>所属地区</a></th>
                                                  <th rowspan="2" ><a>总数</a></th>
                                                  <th colspan="2"><a>正高</a></th>
                                                  <th colspan="2"><a>副高</a></th>
                                                  <th colspan="2"><a>中级</a></th>
                                                  <th colspan="2"><a>初级</a></th>
                                                  <th colspan="2"><a>无</a></th>
                                                </tr>
                                                <tr class="gv_Head">
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                </tr>
                                                <%int rowCount = 1 ;
                                                	 for(Map.Entry<String, TeacherStatisticsBean> entry : result.entrySet()){
                                                		   TeacherStatisticsBean bean = entry.getValue();
                                                 %>
                                                <tr class="gv_Item">
                                                  <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                                  <%if((!StringKit.isValid(bean.getCity()) && StringKit.isValid(queryProvince))
                                                		  ||(!StringKit.isValid(bean.getRegion()) && StringKit.isValid(queryCity))
                                                		  || (StringKit.isValid(queryRegion))){ %>
                                                  <td><span><%=bean.getProvince()+bean.getCity()+bean.getRegion()%></span></td>
                                                  <%}else{ %>
                                                  <td><span><a onclick="javascript:location.href='TeacherStatisticsByLevelList.do?queryProvince=<%=bean.getProvince()%>&queryCity=<%=bean.getCity()%>&queryRegion=<%=bean.getRegion()%>&type=<%=type %>&province=<%=bean.getProvince()%>&city=<%=bean.getCity() %>'"><%=bean.getProvince()+bean.getCity()+bean.getRegion()%></a></span></td>
                                                  <%} %>
                                                  <td><span><%=bean.getCount() %></span></td>
                                                  <td><span><%=bean.getSeniorCount() %></span></td>
                                                  <td><span><%=percentFormat.format((1.0*bean.getSeniorCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getSubSeniorCount() %></span></td>
                                                  <td><span><%=percentFormat.format((1.0*bean.getSubSeniorCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getMiddleCount() %></span></td>
                                                  <td><span><%=percentFormat.format((1.0*bean.getMiddleCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getPrimaryCount() %></span></td>
                                                  <td><span><%=percentFormat.format((1.0*bean.getPrimaryCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getOtherLevelCount() %></span></td>
                                                  <td><span><%=percentFormat.format((1.0*bean.getOtherLevelCount()/bean.getCount())) %></span></td>
                                                </tr>
                                                <%  
                                                		rowCount++ ;
                                                	 } 
                                             	}else if(type == 2){
                                             	%>
                                             	 <tr class="gv_Head">
                                                  <th rowspan="2" style="width:30px;">序号</th>
                                                  <th rowspan="2" ><a>地区</a></th>
                                                  <th rowspan="2"><a>总数</a></th>
                                                  <th colspan="2"><a>种植业</a></th>
                                                  <th colspan="2"><a>养殖业</a></th>
                                                  <th colspan="2"><a>农村工程与服务</a></th>
                                                  <th colspan="2"><a>农村经营与农村管理</a></th>
                                                  <th colspan="2"><a>现代农业</a></th>
                                                  <th colspan="2"><a>公共基础</a></th>
                                                  <th colspan="2"><a>其他</a></th>
                                                </tr>
                                                   <tr class="gv_Head">
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>
                                                	<th>人数</th>
                                                	<th>百分比</th>	 
                                                </tr>
                                             	<%int rowCount = 1 ;
                                                	 for(Map.Entry<String, TeacherStatisticsBean> entry : result.entrySet()){
                                                		   TeacherStatisticsBean bean = entry.getValue();
                                                 %>
                                                <tr class="gv_Item">
                                                  <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                                  <%if((!StringKit.isValid(bean.getCity()) && StringKit.isValid(queryProvince))
                                                		  ||(!StringKit.isValid(bean.getRegion()) && StringKit.isValid(queryCity))
                                                		  || (StringKit.isValid(queryRegion))){ %>
                                                  <td><span><%=bean.getProvince()+bean.getCity()+bean.getRegion()%></span></td>
                                                  <%}else{ %>
                                                  <td><span><a onclick="javascript:location.href='TeacherStatisticsByLevelList.do?queryProvince=<%=bean.getProvince()%>&queryCity=<%=bean.getCity()%>&queryRegion=<%=bean.getRegion()%>&type=<%=type %>&province=<%=bean.getProvince()%>&city=<%=bean.getCity() %>'"><%=bean.getProvince()+bean.getCity()+bean.getRegion()%></a></span></td>
                                                  <%} %>
                                                  <td><span><%=bean.getCount() %></span></td>
                                                  <td><span><%=bean.getTypePlantCount() %></span></td>
                                                   <td><span><%=percentFormat.format((1.0*bean.getTypePlantCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getTypeBreedCount() %></span></td>
                                                  <td><span><%=percentFormat.format((1.0*bean.getTypeBreedCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getTypeEngineeringAndServerCount() %></span></td>
                                                  <td><span><%=percentFormat.format((1.0*bean.getTypeEngineeringAndServerCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getTypePublicManageCount() %></span></td>
                                                 <td><span><%=percentFormat.format((1.0*bean.getTypePublicManageCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getTypeModernAgricultureCount() %></span></td>
                                                 <td><span><%=percentFormat.format((1.0*bean.getTypeModernAgricultureCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getTypePublicFoundationCount() %></span></td>
                                                  <td><span><%=percentFormat.format((1.0*bean.getTypePublicFoundationCount()/bean.getCount())) %></span></td>
                                                  <td><span><%=bean.getTypeOtherCount() %></span></td>
                                                   <td><span><%=percentFormat.format((1.0*bean.getTypeOtherCount()/bean.getCount())) %></span></td>
                                                </tr>
                                                <%  
                                                		rowCount++ ;
                                                	 } 
                                             	}
                                             	%>
                                              </tbody>
                                            </table>
                                           
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
$(document).ready(function() { 
	showProvince() ;
	}) ;

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
	var isFix;
	var isQuery; 
	var sel = $("#selectProvince");  
	sel.empty();  
	var fixProvince = "<%=fixProvince%>";
  	var queryProvince = "<%=queryProvince%>";	
  	if(fixProvince == '国家' || queryProvince == '国家'){
  		sel.append("<option value = '国家' selected>国家</option>");
  	}else{
  		sel.append("<option value = '国家'>国家</option>");
  	}
	
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	if(fixProvince == item.provinceName){
	      		sel.append("<option value = '"+item.provinceId+"' selected>"+item.provinceName+"</option>");
	      		isFix = true;
	      	}else if(queryProvince == item.provinceName){
	      		sel.append("<option value = '"+item.provinceId+"' selected>"+item.provinceName+"</option>");
	      		isQuery = true;
	      	}else{
	      		sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
	      	}
	    };
	    if(isFix){
    		$("#selectProvince").attr("disabled","disabled");
    		 showCity();
	    }else if(isQuery){
	    	 showCity();
			}
    } else{
   		sel.empty();  
    }
}

function showCity(){
	var text = $("#selectProvince  option:selected").text();
	$("#queryProvince").val(text);
	$("#queryCity").val("");
	$("#queryRegion").val("");
	$("#selectCity").empty();
	$("#selectRegion").empty();
	var provinceId = $("#selectProvince").val();
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
	var isFix;
	var isQuery
	var sel = $("#selectCity");  
	sel.empty();  
	sel.append("<option value ='' selected>全部</option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixCity = "<%=fixCity%>";
	      	var queryCity = "<%=queryCity%>";
	      	if(fixCity == item.cityName){
	      		sel.append("<option value = '"+item.cityId+"' selected>"+item.cityName+"</option>");
	      		isFix = true;
	      	}else if(queryCity == item.cityName){
	      		sel.append("<option value = '"+item.cityId+"' selected>"+item.cityName+"</option>");
	      		isQuery = true;
	      	}else{
	      		sel.append("<option value = '"+item.cityId+"'>"+item.cityName+"</option>");
	      	}
	    };
	    if(isFix){
	    		$("#selectCity").attr("disabled","disabled");
	    		showRegion();
	    }else if(isQuery){
	    		showRegion();
   		}else {
   		//初始化筛选框
		$('.select-item select').each(function(){
			var val = $(this).find('option:selected').text();
			$(this).parents('.select-item').find('.select-txt').text(val);
		})
   		}
    } else{
   		sel.empty();  
    }
}

function showRegion(){
	var text = $("#selectCity  option:selected").text();
	if(text == "全部" || text == "请选择" ){
		$("#queryCity").val("");
		$("#queryRegion").val("");
	}else{
		$("#queryCity").val(text);
	} 
	var cityId = $("#selectCity").val();
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
	var isFix;
	var sel = $("#selectRegion");  
	sel.empty();  
	sel.append("<option value = ''>全部</option>");
	var items=data.content.regionList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixRegion = "<%=fixRegion%>";
	      	var queryRegion = "<%=queryRegion%>";
	      	if(fixRegion == item.regionName){
	      		sel.append("<option value = '"+item.regionId+"' selected>"+item.regionName+"</option>");
	      		isFix = true;
	      	}else if(queryRegion == item.regionName){
	      		sel.append("<option value = '"+item.regionId+"' selected>"+item.regionName+"</option>");
	      	}else{
	      		sel.append("<option value = '"+item.regionId+"'>"+item.regionName+"</option>");
	      	}
	    };
	    if(isFix){
	    		$("#selectRegion").attr("disabled","disabled");
	    }
	    setRegion();
    } else{
   		sel.empty();  
    }
    	//初始化筛选框
    	$('.select-item select').each(function(){
    		var val = $(this).find('option:selected').text();
    		$(this).parents('.select-item').find('.select-txt').text(val);
    	})
}

function setRegion(){
	var text = $("#selectRegion  option:selected").text();
	if(text == "全部" || text == "请选择"){
		$("#queryRegion").val("");
	}else{
		$("#queryRegion").val(text);
	} 
}
</script>
</body>
</html>