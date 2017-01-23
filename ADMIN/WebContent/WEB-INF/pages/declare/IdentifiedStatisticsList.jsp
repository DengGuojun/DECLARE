<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
    DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
    LinkedHashMap<String, List<Object>> declareCollectMap = (LinkedHashMap<String, List<Object>>)request.getAttribute("declareCollectMap");
	String queryProvince = ParamKit.getParameter(request, "queryProvince","") ;
	String queryCity = ParamKit.getParameter(request, "queryCity","") ;
	String queryRegion = ParamKit.getParameter(request, "queryRegion","") ;
	int declareType = ParamKit.getIntParameter(request, "declareType", 0);
	String declareYear = ParamKit.getParameter(request, "declareYear","") ;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 培育对象统计</title>
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
							<%@include file="../include/project_management_left.jsp" %>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                            	<div class="content_wrap fixed_height">
                            		<div class="detail_right_title h1 white">
                            		<span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">认定情况统计</span>
                            		</div>
                            		<div class="con fl pt_10">
                            			<ul id="tags" class="tags" style="width: 1100px;">
                            				<%if(declareType == 0){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %> 
                            					<a class="text_15" href="IdentifiedStatisticsList.do">各地认定情况统计</a>
                            				</li>
                            				<%if(declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                            					<a class="text_15" href="IdentifiedStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER %>">现代青年农场主</a>
                            				</li>
                                            <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                                                <a class="text_15" href="IdentifiedStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER %>">新型农业经营主体带头人培训</a>
                                            </li>
                                            <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                                                <a class="text_15" href="IdentifiedStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER %>">生产经营型职业农民培训</a>
                                            </li>
                                           <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                                                <a class="text_15" href="IdentifiedStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER %>">专业技能型职业农民培训</a>
                                            </li>
                                           <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                                                <a class="text_15" href="IdentifiedStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER %>">专业服务型职业农民培训</a>
                                            </li>
                            			</ul>
                            			<div id="tagContent">
                            				<div class="tagContent selectTag scroller-box">
                            					<div class="right_bg pl_5">
                            					<form name="formSearch" method="post" action="IdentifiedStatisticsList.do">
                            						<input type="hidden" name="declareType" id="declareType" value="<%=declareType %>"/> 
                            						<div class="form-group form-horizontal">
                            							<label>年份：</label>
														<select class="form-control" name="declareYear" id="declareYear" >
								                         <%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
									                     <option value="<%=InfoYear %>" <%=ParamKit.getIntParameter(request, "declareYear", 0) == InfoYear? "selected" : ""%>><%=InfoYear %></option>
							                             <%}%>	
			                                           </select>
                            						</div>
                            					    <input type="hidden" id="queryProvince"  name="queryProvince" value="<%=queryProvince !=null ? queryProvince : "" %>"/>
				                                    <input type="hidden" id="queryCity"  name="queryCity" value="<%=queryCity !=null ? queryCity : "" %>"/>
				           		                    <input type="hidden" id="queryRegion" name="queryRegion" value="<%=queryRegion !=null ? queryRegion : "" %>"/>
                            						<div class="form-group form-horizontal">
                            							<label>地区：</label>
                            							<select name="selectProvince" id="selectProvince" onchange="showCity()">
			                                               <option value="">请选择</option>
			                                           </select>
                            							<select name="selectCity" id="selectCity" onchange="showRegion()">
			                                               <option value="">请选择</option>
			                                            </select>
                            							<select name="selectRegion" id="selectRegion" onchange="setRegion()">
			                                               <option value="">请选择</option>
			                                            </select>                           							
                            						</div>
                            						<input type="submit" value="查 询"  class="btn72"> 
                            						</form>
                            						<div>
                            							<table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                            								<tbody>
                            									<tr class="gv_Head">
                            										<% if(declareType == 0){ %>
	                            										<th style="width:30px;">序号</th>
	                            										<th>地区</th>
	                            										<th>新型职业农民认定总人数</th>
	                            										<th>现代青年农场主认定总人数</th>
	                            										<th>新型农业经营主体带头人认定总人数</th>
	                            										<th>生产经营型职业农民认定总人数</th>
	                            										<th>专业技能型职业农民认定总人数</th>
	                            										<th>专业服务型职业农民认定总人数</th>
                            										<% }else if(declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER){ %>
                            											<th rowspan="2">序号</th>
														                <th rowspan="2">地区</th>
														                <th rowspan="2">总人数</th>
														                <th colspan="2">性别</th>
														                <th colspan="5">年龄</th>
														                <th colspan="4">文化程度</th>
														                <th colspan="10">主体产业</th>
														                <th colspan="8">人员类别</th>
													                </tr>
													                <tr>
													                	<th>男性</th>
														                <th>女性</th>
														                <th>18-25</th>
														                <th>26-35</th>
														                <th>36-45</th>
														                <th>45-60</th>
														                <th>60岁以上</th>
														                <th>小学及以下</th>
														                <th>初中</th>
														                <th>高中/中专</th>
														                <th>大专及以上</th>
														                <th>粮食作物</th>
														                <th>油料作物</th>
														                <th>经济作物</th>
														                <th>园艺作物</th>
														                <th>家畜</th>
														                <th>家禽</th>
														                <th>特种动物</th>
														                <th>海水</th>
														                <th>淡水</th>
														                <th>其他产业</th>
														                <th>种植大户</th>
														                <th>规模养殖场经营者</th>
														                <th>家庭农场经营者</th>
														                <th>农民合作社骨干</th>
														                <th>创业大学生</th>
														                <th>中高职毕业生</th>
														                <th>返乡农民工</th>
														                <th>退伍军人</th>
                            										<% }else if(declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER ){ %>
                            											<th rowspan="2">序号</th>
														                <th rowspan="2">地区</th>
														                <th rowspan="2">总人数</th>
														                <th colspan="2">性别</th>
														                <th colspan="5">年龄</th>
														                <th colspan="4">文化程度</th>
														                <th colspan="10">主体产业</th>
														                <th colspan="9">人员类别</th>
													                </tr>
													                <tr>
													                	<th>男性</th>
														                <th>女性</th>
														                <th>18-25</th>
														                <th>26-35</th>
														                <th>36-45</th>
														                <th>45-60</th>
														                <th>60岁以上</th>
														                <th>小学及以下</th>
														                <th>初中</th>
														                <th>高中/中专</th>
														                <th>大专及以上</th>
														                <th>粮食作物</th>
														                <th>油料作物</th>
														                <th>经济作物</th>
														                <th>园艺作物</th>
														                <th>家畜</th>
														                <th>家禽</th>
														                <th>特种动物</th>
														                <th>海水</th>
														                <th>淡水</th>
														                <th>其他产业</th>
														                <th>种植大户</th>
														                <th>规模养殖场经营者</th>
														                <th>家庭农场经营者</th>
														                <th>农业企业负责人</th>
														                <th>农民合作社骨干</th>
														                <th>创业大学生</th>
														                <th>中高职毕业生</th>
														                <th>返乡农民工</th>
														                <th>退伍军人</th>
                            										<% }else if(declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
	                            										<th rowspan="2">序号</th>
														                <th rowspan="2">地区</th>
														                <th rowspan="2">总人数</th>
														                <th colspan="2">性别</th>
														                <th colspan="5">年龄</th>
														                <th colspan="4">文化程度</th>
														                <th colspan="10">主体产业</th>
														                <th colspan="6">人员类别</th>
													                </tr>
													                <tr>
													                	<th>男性</th>
														                <th>女性</th>
														                <th>18-25</th>
														                <th>26-35</th>
														                <th>36-45</th>
														                <th>45-60</th>
														                <th>60岁以上</th>
														                <th>小学及以下</th>
														                <th>初中</th>
														                <th>高中/中专</th>
														                <th>大专及以上</th>
														                <th>粮食作物</th>
														                <th>油料作物</th>
														                <th>经济作物</th>
														                <th>园艺作物</th>
														                <th>家畜</th>
														                <th>家禽</th>
														                <th>特种动物</th>
														                <th>海水</th>
														                <th>淡水</th>
														                <th>其他产业</th>
														                <th>种植大户</th>
														                <th>规模养殖场经营者</th>
														                <th>家庭农场经营者</th>
														                <th>农民合作社骨干</th>
														                <th>农业企业负责人</th>
														                <th>农业社会化服务组织服务能手</th>
                            										<% }else if(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER||declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER){ %>
                            											<th rowspan="2">序号</th>
														                <th rowspan="2">地区</th>
														                <th rowspan="2">总人数</th>
														                <th colspan="2">性别</th>
														                <th colspan="5">年龄</th>
														                <th colspan="4">文化程度</th>
														                <th rowspan="2">农艺工</th>
														                <th rowspan="2">园艺工</th>
														                <th rowspan="2">牧草工</th>
														                <th rowspan="2">热带作物生产工</th>
														                <th rowspan="2">家畜繁殖员</th>
														                <th rowspan="2">家畜饲养员</th>
														                <th rowspan="2">家禽繁殖员</th>
														                <th rowspan="2">家禽饲养员</th>
														                <th rowspan="2">特种动物饲养员</th>
														                <th rowspan="2">特种动物养殖员</th>
														                <th rowspan="2">渔业生产船员</th>
														                <th rowspan="2">水生动物苗种繁育工</th>
														                <th rowspan="2">水生植物苗种繁育工</th>
														                <th rowspan="2">水生动物饲养工</th>
														                <th rowspan="2">水生植物栽培工</th>
														                <th rowspan="2">珍珠养殖工</th>
														                <th rowspan="2">水产捕捞工</th>
														                <th rowspan="2">其他</th>
													                </tr>
													                <tr>
													                	<th>男性</th>
														                <th>女性</th>
														                <th>18-25</th>
														                <th>26-35</th>
														                <th>36-45</th>
														                <th>45-60</th>
														                <th>60岁以上</th>
														                <th>小学及以下</th>
														                <th>初中</th>
														                <th>高中/中专</th>
														                <th>大专及以上</th>
                            										<% } %>
                            									</tr>
                            									
                            									<% int i = 0;
                                                                for (Map.Entry<String,List<Object>> entry : declareCollectMap.entrySet()) {%>
                                                                <tr>
                                                                    <td><%=++i %></td>
                            										<%if(entry.getValue().get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)){ %>
                            										<td><a href="IdentifiedStatisticsList.do?queryProvince=<%=entry.getKey() %>&declareType=<%=declareType%>&declareYear=<%=declareYear%>"><%=entry.getKey() %></a></td>
                            										<%}else if(entry.getValue().get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)){ %>
                            										<td><a href="IdentifiedStatisticsList.do?queryCity=<%=entry.getKey() %>&queryProvince=<%=entry.getValue().get(0) %>&declareType=<%=declareType%>&declareYear=<%=declareYear%>"><%=entry.getKey() %></a></td>
                            										<%} else if(entry.getValue().get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)){ %>
                            										<td><a href="IdentifiedStatisticsList.do?queryRegion=<%=entry.getKey() %>&queryProvince=<%=entry.getValue().get(0) %>&queryCity=<%=entry.getValue().get(1) %>&declareType=<%=declareType%>&declareYear=<%=declareYear%>"><%=entry.getKey() %></a></td>
                            										<%} else {%>
                            										<td><%=entry.getKey() %></td>
                            										<%}%>
                            										<% List<Object> list = entry.getValue().subList(4, entry.getValue().size()); %>
                            											<% for(Object field : list){ %>
                            												<td><%=field %></td>
                            											<% } %>
                                                                </tr>
                                                                <%} %>
                            								</tbody>
                            							</table>
                            						</div>
                            					</div>
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
	var isQuery; 
	var sel = $("#selectProvince");  
	sel.empty();  
  	var queryProvince = "<%=queryProvince%>";	
  	sel.append("<option value = '' >请选择</option>");
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	if(queryProvince == item.provinceName){
	      		sel.append("<option value = '"+item.provinceId+"' selected>"+item.provinceName+"</option>");
	      		isQuery = true;
	      	}else{
	      		sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
	      	}
	    };
	    if(isQuery){
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
	var isQuery
	var sel = $("#selectCity");  
	sel.empty();  
	sel.append("<option value ='' selected>全部</option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var queryCity = "<%=queryCity%>";
	      	if(queryCity == item.cityName){
	      		sel.append("<option value = '"+item.cityId+"' selected>"+item.cityName+"</option>");
	      		isQuery = true;
	      	}else{
	      		sel.append("<option value = '"+item.cityId+"'>"+item.cityName+"</option>");
	      	}
	    };
	    if(isQuery){
	    		showRegion();
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
	var sel = $("#selectRegion");  
	sel.empty();  
	sel.append("<option value = ''>全部</option>");
	var items=data.content.regionList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var queryRegion = "<%=queryRegion%>";
	      	if(queryRegion == item.regionName){
	      		sel.append("<option value = '"+item.regionId+"' selected>"+item.regionName+"</option>");
	      	}else{
	      		sel.append("<option value = '"+item.regionId+"'>"+item.regionName+"</option>");
	      	}
	    };
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