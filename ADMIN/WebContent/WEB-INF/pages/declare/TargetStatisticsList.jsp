<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
    AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
    HashMap<String, List<Object>> declareReportMap = (HashMap<String, List<Object>>)request.getAttribute("declareReportMap");
    HashMap<String, List<Object>> declareCollectMap = (HashMap<String, List<Object>>)request.getAttribute("declareCollectMap");
    List<JobTypeBean> jobTypeList = (List<JobTypeBean>)request.getAttribute("jobTypeList");
    int jobTypeAmount = jobTypeList.size();
    List<IndustryTypeBean> industryTypeList = (List<IndustryTypeBean>)request.getAttribute("industryTypeList");
    int industryTypeAmount = industryTypeList.size();
    DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
	String fixProvince = (String)request.getAttribute("FixProvince");
	String fixCity = (String)request.getAttribute("FixCity");
	String fixRegion = (String)request.getAttribute("FixRegion");
	String queryProvince = ParamKit.getParameter(request, "queryProvince");
	String queryCity = ParamKit.getParameter(request, "queryCity");
	String queryRegion = ParamKit.getParameter(request, "queryRegion");
	int declareType = ParamKit.getIntParameter(request, "declareType", 0);
	String dataStatus = ParamKit.getParameter(request, "dataStatus");
	String declareYear = ParamKit.getParameter(request, "declareYear");
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 对象统计</title>
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
                            			<span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">各地培训情况</span>
                            		</div>
                            		<div class="con fl pt_10">
                            			<ul id="tags" class="tags" style="width: 1100px;">
                            				<%if(declareType == 0){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %> 
                            					<a class="text_15" href="TargetStatisticsList.do">各地完成情况</a>
                            				</li>
                            				<%if(declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                            					<a class="text_15" href="TargetStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER %>">现代青年农场主</a>
                            				</li>
                                            <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                                                <a class="text_15" href="TargetStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER %>">新型农业经营主体带头人培训</a>
                                            </li>
                                            <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                                                <a class="text_15" href="TargetStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER %>">生产经营型职业农民培训</a>
                                            </li>
                                           <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                                                <a class="text_15" href="TargetStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER %>">专业技能型职业农民培训</a>
                                            </li>
                                           <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER){ %>
                            				<li class="selectTag">
                            				<%}else{ %>
                            				<li>
                            				<%} %>
                                                <a class="text_15" href="TargetStatisticsList.do?declareType=<%=DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER %>">专业服务型职业农民培训</a>
                                            </li>
                            			</ul>
                            			<div id="tagContent">
                            				<div class="tagContent selectTag scroller-box">
                            					<div class="right_bg pl_5">
                            					<form name="formSearch" method="post" action="TargetStatisticsList.do">
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
                            						<%if(declareType == 0){ %>
                            						<div>
                            							<table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                            								<tbody>
                            									<tr class="gv_Head">
                            										<th rowspan="2" style="width:30px;">序号</th>
                            										<th rowspan="2">
                            											地区
                            										</th>
                            										<th rowspan="2">
                            											项目县数（个）
                            										</th>
                            										<th rowspan="2">
                            											任务总数（个）
                            										</th>
                            										<th rowspan="2">
                            											已完成任务数（个）
                            										</th>
                            										<th rowspan="2">
                            											已完成比例
                            										</th>
                            										<th colspan="3">
                            											现代青年农场主
                            										</th>
                                                                    <th colspan="3">
                                                                                                                                                                                                新型农业经营主体带头人
                                                                    </th>
                                                                    <th colspan="3">
                                                                                                                                                                                                 生产经营型职业农民
                                                                    </th>
                                                                    <th colspan="3">
                                                                       	 专业技能型职业农民
                                                                    </th>
                                                                    <th colspan="3">
                                                                       	 专业服务型职业农民
                                                                    </th>
                            									</tr>
                                                                <tr class="gv_Head">
                                                                    <th>任务数</th>
                                                                    <th>完成数</th>
                                                                    <th>完成比例</th>
                                                                    <th>任务数</th>
                                                                    <th>完成数</th>
                                                                    <th>完成比例</th>
                                                                    <th>任务数</th>
                                                                    <th>完成数</th>
                                                                    <th>完成比例</th>
                                                                    <th>任务数</th>
                                                                    <th>完成数</th>
                                                                    <th>完成比例</th>
                                                                    <th>任务数</th>
                                                                    <th>完成数</th>
                                                                    <th>完成比例</th>
                                                                </tr>
                                                                <%int i=0; 
                                                                for (String key : declareCollectMap.keySet()) {%>
                            									<tr>
                            										<td><%=++i %></td>
                            										<td><%=key %></td>
                            										<td><%=declareCollectMap.get(key).get(1) %></td>
                            										<td><%=declareCollectMap.get(key).get(2) %></td>
                            										<td><%=declareCollectMap.get(key).get(3) %></td>
                            										<td><%=declareCollectMap.get(key).get(4) %></td>
                            										<td><%=declareCollectMap.get(key).get(5) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(6) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(7) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(8) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(9) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(10) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(11) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(12) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(13) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(14) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(15) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(16) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(17) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(18) %></td>
                                                                    <td><%=declareCollectMap.get(key).get(19) %></td>
                            									</tr>
															   <%} %>
                                                                <%
                                                                for (String key : declareReportMap.keySet()) {%>
                            									<tr>
                            										<td><%=++i %></td>
                            										<%if(declareReportMap.get(key).get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)){ %>
                            										<td><a href="TargetStatisticsList.do?queryProvince=<%=key %>&dataStatus=<%=dataStatus%>&declareYear=<%=declareYear%>"><%=key %></a></td>
                            										<%}else if(declareReportMap.get(key).get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)){ %>
                            										<td><a href="TargetStatisticsList.do?queryCity=<%=key %>&queryProvince=<%=declareReportMap.get(key).get(20) %>&dataStatus=<%=dataStatus%>&declareYear=<%=declareYear%>"><%=key %></a></td>
                            										<%} else if(declareReportMap.get(key).get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)){ %>
                            										<td><a href="TargetStatisticsList.do?queryRegion=<%=key %>&queryProvince=<%=declareReportMap.get(key).get(20) %>&queryCity=<%=declareReportMap.get(key).get(21) %>&dataStatus=<%=dataStatus%>&declareYear=<%=declareYear%>"><%=key %></a></td>
                            										<%} else {%>
                            										<td><%=key %></td>
                            										<%}%>
                            										<td><%=declareReportMap.get(key).get(1) %></td>
                            										<td><%=declareReportMap.get(key).get(2) %></td>
                            										<td><%=declareReportMap.get(key).get(3) %></td>
                            										<td><%=declareReportMap.get(key).get(4) %></td>
                            										<td><%=declareReportMap.get(key).get(5) %></td>
                                                                    <td><%=declareReportMap.get(key).get(6) %></td>
                                                                    <td><%=declareReportMap.get(key).get(7) %></td>
                                                                    <td><%=declareReportMap.get(key).get(8) %></td>
                                                                    <td><%=declareReportMap.get(key).get(9) %></td>
                                                                    <td><%=declareReportMap.get(key).get(10) %></td>
                                                                    <td><%=declareReportMap.get(key).get(11) %></td>
                                                                    <td><%=declareReportMap.get(key).get(12) %></td>
                                                                    <td><%=declareReportMap.get(key).get(13) %></td>
                                                                    <td><%=declareReportMap.get(key).get(14) %></td>
                                                                    <td><%=declareReportMap.get(key).get(15) %></td>
                                                                    <td><%=declareReportMap.get(key).get(16) %></td>
                                                                    <td><%=declareReportMap.get(key).get(17) %></td>
                                                                    <td><%=declareReportMap.get(key).get(18) %></td>
                                                                    <td><%=declareReportMap.get(key).get(19) %></td>
                            									</tr>
															   <%} %>
                            								</tbody>
                            							</table>
                            						</div>
                            						<%} %>
                            						<%if(declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
                            						<div>
                            							<table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                            								<tbody>
                            									<tr class="gv_Head">
                            										<th style="width:30px;">序号</th>
                            										<th>
                            											地域
                            										</th>
                            										<th>
                            											总人数
                            										</th>
                            										<%for(IndustryTypeBean industryTypeBean : industryTypeList){ %>
                                                                    <th><%=industryTypeBean.getTypeName() %></th>
                                                                    <%} %>
                            									</tr>
                            									<%int i=0; 
                                                                for (String key : declareCollectMap.keySet()) {%>
                            									<tr>
                            										<td><%=++i %></td>
                            										<td><%=key %></td>
                            										<td><%=declareCollectMap.get(key).get(1) %></td>
                            										<%int tempFlag = 2; 
                                                                      for(int k=0;k<industryTypeAmount;++k){
                                                                    %>
                                                                    <td><%=declareCollectMap.get(key).get(tempFlag + k)%></td>
                                                                    <%}%>
                            									</tr>
                            									<%} %>
                            									<%
                                                                for (String key : declareReportMap.keySet()) {%>
                            									<tr>
                            										<td><%=++i %></td>
                            										<%if(declareReportMap.get(key).get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)){ %>
                            										<td><a href="TargetStatisticsList.do?queryProvince=<%=key %>&declareType=<%=declareType%>&declareYear=<%=declareYear%>"><%=key %></a></td>
                            										<%}else if(declareReportMap.get(key).get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)){ %>
                            										<td><a href="TargetStatisticsList.do?queryCity=<%=key %>&queryProvince=<%=declareReportMap.get(key).get(2+industryTypeAmount) %>&declareType=<%=declareType%>&declareYear=<%=declareYear%>"><%=key %></a></td>
                            										<%} else if(declareReportMap.get(key).get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)){ %>
                            										<td><a href="TargetStatisticsList.do?queryRegion=<%=key %>&queryProvince=<%=declareReportMap.get(key).get(2+industryTypeAmount) %>&queryCity=<%=declareReportMap.get(key).get(3+industryTypeAmount) %>&declareType=<%=declareType%>&declareYear=<%=declareYear%>"><%=key %></a></td>
                            										<%} else {%>
                            										<td><%=key %></td>
                            										<%}%>
                            										<td><%=declareReportMap.get(key).get(1) %></td>
                            										<%int tempFlag = 2; 
                                                                      for(int k=0;k<industryTypeAmount;++k){
                                                                    %>
                                                                    <td><%=declareReportMap.get(key).get(tempFlag+k) %></td>
                                                                    <%}%>
                            									</tr>
                            									<%} %>
                            								</tbody>
                            							</table>
                            						</div>
                            						<%} %>
                            						<%if(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER){ %>
                            						<div>
                            							<table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                            								<tbody>
                            									<tr class="gv_Head">
                            										<th style="width:30px;">序号</th>
                            										<th>
                            											<a>地域</a>
                            										</th>
                            										<th>
                            											<a>总人数</a>
                            										</th>
                                                                   <%for(JobTypeBean jobTypeBean : jobTypeList){ %>
                            										<th>
                            											<%=jobTypeBean.getTypeName() %>
                            										</th>
                            										<%} %>
                            									</tr>
                            									<%int i=0; 
                                                                for (String key : declareCollectMap.keySet()) {%>
                            									<tr>
                            										<td><%=++i %></td>
                            										<td><%=key %></td>
                            										<td><%=declareCollectMap.get(key).get(1) %></td>
                            										<%int tempFlag = 2; 
                                                                      for(int k=0;k<jobTypeAmount;++k){
                                                                    %>
                                                                    <td><%=declareCollectMap.get(key).get(tempFlag+k)%></td>
                                                                    <%}%>
                            									</tr>
                            									<%} %>
                            									<%
                                                                for (String key : declareReportMap.keySet()) {%>
                            									<tr>
                            										<td><%=++i %></td>
                            										<%if(declareReportMap.get(key).get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)){ %>
                            										<td><a href="TargetStatisticsList.do?queryProvince=<%=key %>&declareType=<%=declareType%>&declareYear=<%=declareYear%>"><%=key %></a></td>
                            										<%}else if(declareReportMap.get(key).get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)){ %>
                            										<td><a href="TargetStatisticsList.do?queryCity=<%=key %>&queryProvince=<%=declareReportMap.get(key).get(2+jobTypeAmount) %>&declareType=<%=declareType%>&declareYear=<%=declareYear%>"><%=key %></a></td>
                            										<%} else if(declareReportMap.get(key).get(0).equals(DeclareAdminConfig.ADMIN_LEVEL_REGION)){ %>
                            										<td><a href="TargetStatisticsList.do?queryRegion=<%=key %>&queryProvince=<%=declareReportMap.get(key).get(2+jobTypeAmount) %>&queryCity=<%=declareReportMap.get(key).get(3+jobTypeAmount) %>&declareType=<%=declareType%>&declareYear=<%=declareYear%>"><%=key %></a></td>
                            										<%} else {%>
                            										<td><%=key %></td>
                            										<%}%>
                            										<td><%=declareReportMap.get(key).get(1) %></td>
                            										<%int tempFlag = 2; 
                                                                      for(int k=0;k<jobTypeAmount;++k){
                                                                    %>
                                                                    <td><%=declareReportMap.get(key).get(tempFlag+k)%></td>
                                                                    <%}%>
                            									</tr>
                            									<%} %>
                            								</tbody>
                            							</table>
                            						</div>
                            						<%} %>
                            						<!-- 页码 -->    
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