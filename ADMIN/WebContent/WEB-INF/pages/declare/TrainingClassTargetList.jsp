<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
    List<TrainingClassTargetBean> list = (List<TrainingClassTargetBean>)request.getAttribute("trainingClassTargetList");
    List<TrainingClassTargetBean> trainingClassOrgTargetList = (List<TrainingClassTargetBean>)request.getAttribute("trainingClassOrgTargetList");
    Map<Integer, String> trainingOrganizationInfoMap= (Map<Integer, String>)request.getAttribute("trainingOrganizationInfoMap");
    DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
    int level = ParamKit.getIntParameter(request, "level", 0);
	String fixProvince = (String)request.getAttribute("FixProvince");
	String fixCity = (String)request.getAttribute("FixCity");
	String fixRegion = (String)request.getAttribute("FixRegion");
	String queryProvince = ParamKit.getParameter(request, "queryProvince", "");
	String queryCity = ParamKit.getParameter(request, "queryCity", "");
	String queryRegion = ParamKit.getParameter(request, "queryRegion", "");
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 培训任务</title>
    <%@ include file="../include/header.jsp" %>
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/SlideMenuStyle.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/default.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/StyleBlue.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/index_main.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/tab_select.css" type="text/css" rel="Stylesheet">

</head>
<body class="body-index">
	<%@include file="../nav/navigation.jsp" %>
<div class="warpDefaultMgr">
            <table class="table_wrp">
                <tbody>
                    <tr>
                        <td style="width: 191px; background-color: #f2f2f2;" valign="top">
                            <%@include file="../include/project_management_left.jsp" %>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                            	<div class="content_wrap fixed_height">
                            		<div class="detail_right_title h1 white">
                            			<span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">培训任务数</span>
                            		</div>
                            		<%if(level == 0 || level == 3){ %>
                            		<div class="con fl pt_10">
                            			<ul id="tags" class="tags">
                            			    <li class = <%=level == 0 ? "selectTag" : ""%>>	
                            					<a class="text_15" href="TrainingClassTargetList.do">培训任务数</a>
                            				</li>
                            				<li  class = <%=level == 3 ? "selectTag" : ""%>>
                            					<a class="text_15" href="TrainingClassTargetList.do?level=3">计划任务数</a>
                            				</li>
                            			</ul>
                            			<div id="tagContent">
                            				<div class="tagContent selectTag scroller-box">
                            					<div class="right_bg pl_5">
                            	    <%}else{%>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <%}%>
                            					<form name="formSearch" method="post" action="TrainingClassTargetList.do">
                            					<input type="hidden" id="level"  name="level" value="<%=level %>"/>
                            						<div class="form-group form-horizontal">
                            							<label>年份：</label>
                            						 <select class="form-control" name="trainingYear" id="trainingYear" >
								                         <%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
									                     <option value="<%=InfoYear %>" <%=ParamKit.getIntParameter(request, "trainingYear", 0) == InfoYear? "selected" : ""%>><%=InfoYear %></option>
							                             <%}%>	
			                                          </select>
                            						</div>
                            				   <%if(level == 3){ %>
                            				   <input type="submit" value="查 询"  class="btn72"> 
                            				   <%}else{%>
                            						<input type="hidden" id="queryProvince"  name="queryProvince" value="<%=queryProvince !=null ? queryProvince : "" %>"/>
				                                    <input type="hidden" id="queryCity"  name="queryCity" value="<%=queryCity !=null ? queryCity : "" %>"/>
				           		                    <input type="hidden" id="queryRegion" name="queryRegion" value="<%=queryRegion !=null ? queryRegion : "" %>"/>	
                            						<div class="form-group form-horizontal">
                            							<label>省：</label>
                            							<select name="selectProvince" id="selectProvince" onchange="showCity()">
			                                               <option value="">请选择</option>
			                                           </select>
                            							<label>市：</label>
                            							<select name="selectCity" id="selectCity" onchange="showRegion()">
			                                               <option value="">请选择</option>
			                                            </select>
                            							<label>县：</label>
                            							<select name="selectRegion" id="selectRegion" onchange="setRegion()">
			                                               <option value="">请选择</option>
			                                            </select>
                            						</div>
                            						<input type="submit" value="查 询"  class="btn72">   
                            						<%if(level == 0){ %>
                            						<input type="button" value="本级任务管理" onclick="javascript:location.href='TrainingClassTargetList.do?level=1'"  class="btn72">   
                            						<input type="button" value="下级任务管理" onclick="javascript:location.href='TrainingClassTargetList.do?level=2'"  class="btn72"> 
                            						<%}else{%>
                            						<%
									                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_TARGET,OperationConfig.CREATE)) {
								                    %>
                            						<input type="button" class="btn72" value="添加任务" onclick="javascript:location.href='TrainingClassTargetManage.do?level=<%=level%>'">  
                            						<%}%>
                            						<%}%>
                            					<%}%>
                            						</form>
                            						<%if(level == 0 || level == 3 || level == 2){ %>
                            						<div>
                            						 <%if(level == 0){ %>
                            							<p class="mt_5">下达区县任务数</p>
                            						 <%}%>
                            							<table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                            								<tbody>
                            									<tr class="gv_Head">
                            										<th>序号</th>
                            										<th style="min-width:100px">地区</th>
                            										<%if(level != 2){ %>
                            										<th>总任务数（人）</th>
                            										 <%}%>
                            										<th>现代青年农场主培训任务数（人）</th>
                            										<th>生产经营型职业农民培训任务数（人）</th>
                            										<th>专业技能型职业农民培训任务数（人）</th>
                            										<th>专业服务型职业农民培训任务数（人）</th>
                            										<th>新型农业经营带头人培训任务数（人）</th>
                            									</tr>
                            									<% int i=0;
												                for(TrainingClassTargetBean bean:list){
												                  if(level !=2 || !bean.getCountry().equals("合计")){%> 
                            									<tr>
                            									<td><%= ++i %></td>
                           										<%if(StringKit.isValid(bean.getRegion())){ %>
                           										<td><%=bean.getRegion()%></td>
                           										<%} else if(StringKit.isValid(bean.getCity())){ %>
                           										<td><%=bean.getCity()%></td>
                           										<%} else if(StringKit.isValid(bean.getProvince())){ %>
                           										<td><%=bean.getProvince()%></td>
                           										<%} else{%>   
                           										<td><%=bean.getCountry()%></td>
                           										<%}%>  
                           										<%if(level != 2){ %>
                           										<td><%=bean.getTargetQuantity1()+bean.getTargetQuantity2()+bean.getTargetQuantity3()+bean.getTargetQuantity4()+bean.getTargetQuantity5()%></td>
                           										<%}%> 
                           										<td><%=bean.getTargetQuantity1() %></td>
                           										<td><%=bean.getTargetQuantity2() %></td>
                           										<td><%=bean.getTargetQuantity3() %></td>
                           										<td><%=bean.getTargetQuantity4() %></td>
                           										<td><%=bean.getTargetQuantity5() %></td>	
                            									</tr>
                            									<%} }%>
                            								</tbody>
                            							</table>
                            						</div>
                            						<%}%>
                            						<div>
                            						    <%if(level == 0){ %>
                            							<p class="mt_5">下达培训机构任务数</p>
                            							<%}%>
                            							<%if(level != 3 && level != 2){ %>
                            							<table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                            								<tbody>
                            									<tr class="gv_Head">
                            										<th>序号</th>
                            										<th style="min-width:100px">地区</th>
                            										<th>培训机构</th>
                            										<th>总任务数（人）</th>
                            										<th>现代青年农场主培训任务数（人）</th>
                            										<th>生产经营型职业农民培训任务数（人）</th>
                            										<th>专业技能型职业农民培训任务数（人）</th>
                            										<th>专业服务型职业农民培训任务数（人）</th>
                            										<th>新型农业经营带头人培训任务数（人）</th>
                            									</tr>
                            									<% int i=0;
												                for(TrainingClassTargetBean bean:trainingClassOrgTargetList){%> 
												                <%if(bean.getOrganizationId() != 0){ %>
                            									<tr>
                            										<td><%= ++i %></td>
                            										<%if(StringKit.isValid(bean.getRegion())){ %>
                            										<td><%=bean.getRegion()%></td>
                            										<%} else if(StringKit.isValid(bean.getCity())){ %>
                            										<td><%=bean.getCity()%></td>
                            										<%} else if(StringKit.isValid(bean.getProvince())){ %>
                            										<td><%=bean.getProvince()%></td>
                            										<%} else{%>   
                            										<td></td>
                            										<%}%>  
                            										<td><%=MapKit.getValueFromMap(bean.getOrganizationId(), trainingOrganizationInfoMap) %></td>                  										
                            										<td><%=bean.getTargetQuantity1()+bean.getTargetQuantity2()+bean.getTargetQuantity3()+bean.getTargetQuantity4()+bean.getTargetQuantity5()%></td>
                            										<td><%=bean.getTargetQuantity1() %></td>
                            										<td><%=bean.getTargetQuantity2() %></td>
                            										<td><%=bean.getTargetQuantity3() %></td>
                            										<td><%=bean.getTargetQuantity4() %></td>
                            										<td><%=bean.getTargetQuantity5() %></td>
                            									</tr>
                            									<%} %>
                            									<%} %>
                            								</tbody>
                            							</table>
                            							<%}%>
                            						</div>
                            						<!-- 页码 -->    
                            	  <%if(level == 0 || level == 3){ %>       
                            					</div>
                            				</div>
                            			</div>
                            		</div>
                                 <%}else{ %>
                                  </div>
                                 <%} %>  
                            	</div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    <div class="contents-footer"></div>
    <script src="<%=STATIC_URL%>/js/Public.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/InsertFlash.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
</body>
<script>
$(document).ready(function() {
	showProvince();
});
function showProvince(){
	$("#queryProvince").val("") ; //清空隐藏域的值
	$("#queryCity").val("") ; //清空隐藏域的值
	var city = $("#selectCity") ;
	city.empty() ;  //清除城市下拉框	
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
	var city = $("#selectCity") ;
	city.empty() ;  //清除城市下拉框	
	var region = $("#selectRegion") ;
	region.empty() ;  //清除区域下拉框
	sel.append("<option value = ''>请选择</option>");
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixProvince = "<%=fixProvince%>";
	      	var queryProvince = "<%=queryProvince%>";	      	
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
	$("#queryProvince").val("") ; //清空隐藏域的值
	$("#queryCity").val("") ; //清空隐藏域的值
	$("#queryRegion").val("") ; //清空隐藏域的值
	if(text == ""  || text == "请选择" ){
		$("#queryProvince").val("");
		var city = $("#selectCity") ;
		city.empty() ;  //清除城市下拉框		
		var region = $("#selectRegion") ;
		region.empty() ;  //清除区域下拉框
	}else{
		$("#queryProvince").val(text);
	} 
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
	var region = $("#selectRegion") ;
	region.empty() ;  //清除区域下拉框
	sel.append("<option value = ''>全部</option>");
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
	}else{
		$("#queryCity").val(text);
	} 
	$("#queryRegion").val("") ; //清空隐藏域的值
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

//选项
$('.select-item select').change(function(){
	var val = $(this).children('option:selected').text();
	$(this).parents('.select-item').find('.select-txt').text(val);
});
</script>
</html>