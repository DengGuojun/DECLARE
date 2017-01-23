<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
    AdminUserHelper adminUserHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	List<DeclareReportBean> list = (List<DeclareReportBean>)request.getAttribute("DeclareReportList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	int modelType = ParamKit.getIntParameter(request, "modelType", 0);
	int declareType = ParamKit.getIntParameter(request, "declareType", 0);
	String fixProvince = (String)request.getAttribute("FixProvince");
	String fixCity = (String)request.getAttribute("FixCity");
	String fixRegion = (String)request.getAttribute("FixRegion");
	String queryProvince = ParamKit.getParameter(request, "queryProvince", "");
	String queryCity = ParamKit.getParameter(request, "queryCity", "");
	String queryRegion = ParamKit.getParameter(request, "queryRegion", "");
	Map<Integer, String> industryTypeMap = (Map<Integer, String>) request.getAttribute("IndustryTypeMap");
%>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — <%=MapKit.getValueFromMap(modelType, DeclareInfoRecommendConfig.MODEL_TYPE_MAP) %></title>
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
                             <%@include file="../include/cultivate_object_library_left.jsp" %>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;"><%=MapKit.getValueFromMap(modelType, DeclareInfoRecommendConfig.MODEL_TYPE_MAP) %></span>
                                    </div>
                                    <%if(modelType != DeclareInfoRecommendConfig.TYPE_CLASSIFY){ %>
                                    <div class="con fl pt_10">
                                  		<ul id="tags" class="tags">
                                  		    <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER%>">现代青年农场主</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER%>">现代青年农场主</a></li>
                                  			<%} %>
                                  			 <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER%>">新型农业经营主体带头人</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER%>">新型农业经营主体带头人</a></li>
                                  			<%} %>
                                  			 <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER%>">生产经营型</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER%>">生产经营型</a></li>
                                  			<%} %>
                                  			 <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER%>">专业技能型</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER%>">专业技能型</a></li>
                                  			<%} %>
                                  			 <%if(declareType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER%>">专业服务型</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoReviewList.do?modelType=<%=modelType%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER%>">专业服务型</a></li>
                                  			<%} %>
                                  		</ul>
                                  		<div id="tagContent">
	                                  		<div class="tagContent selectTag scroller-box">
	                                  			<div class="right_bg pl_5">
                                    <%}else{%>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <%}%>
                                    <form name="formSearch" method="post" action="DeclareInfoReviewList.do">
                                    <input type="hidden" name="modelType" id="modelType" value="<%=modelType%>">
                                     <%if(modelType != DeclareInfoRecommendConfig.TYPE_CLASSIFY){ %>
                                    <input type="hidden" name="declareType" id="declareType" value="<%=declareType%>">
                                    <%}%>
                                    <input type="hidden" id="queryProvince"  name="queryProvince" value="<%=queryProvince !=null ? queryProvince : "" %>"/>
                                    <input type="hidden" id="queryCity"  name="queryCity" value="<%=queryCity !=null ? queryCity : "" %>"/>
           		                    <input type="hidden" id="queryRegion" name="queryRegion" value="<%=queryRegion !=null ? queryRegion : "" %>"/>							  
								       <div class="form-group form-horizontal">
                                                                                                                            省：
                                           <select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()">
                                               <option value="">请选择</option>
                                           </select>
                                                                                                                            市：
                                           <select class="form-control" name="selectCity" id="selectCity" onchange="showRegion()">
                                               <option value="">请选择</option>
                                           </select>
                                                                                                                        区：
                                           <select class="form-control" name="selectRegion" id="selectRegion" onchange="setRegion()">
                                               <option value="">请选择</option>
                                           </select>
                                        </div>
                                        <div class="form-group form-horizontal">
                                            <label>姓名：</label>
                                            <input type="text" class="form-control" name="userName" id="userName" value="<%=ParamKit.getParameter(request, "userName", "") %>" size="20"/>
                                        </div>
                                        <div class="form-group form-horizontal">
                                            <label>身份证号：</label>
                                            <input type="text" class="form-control" name="identityNumber" id="identityNumber" value="<%=ParamKit.getParameter(request, "identityNumber", "") %>" size="20"/>
                                        </div>
                                        <div class="form-group form-horizontal">
                                            <label>手机号码：</label>
                                            <input type="text" class="form-control" name="userMobile" id="userMobile" value="<%=ParamKit.getParameter(request, "userMobile", "") %>" size="20"/>
                                        </div>
                                         <%if(modelType == DeclareInfoRecommendConfig.TYPE_MANAGE){ %>
                                         <div class="form-group form-horizontal">
                                            <label>审核状态：</label>
                                            <select class="form-control" name="declareStatus" id="declareStatus">
                                                <option value="" >全部</option>
											    	<%
											    	String status = ParamKit.getParameter(request, "declareStatus", "");
											    	for(StatusBean<String, String> statusBean:DeclareInfoRecommendConfig.REVIEW_STATUS_LIST){ %>
											        <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(status))?"selected":"" %>><%=statusBean.getValue() %></option>
											    <%} %>
                                            </select>
                                        </div>
                                        <%} %>                                       
                                       <input type="submit" value="查 询" class="btn72" />                                     
                                       	<%
						                if (modelType == DeclareInfoRecommendConfig.TYPE_MANAGE) {
					                   %>
									  <input type="button" value="导出" class="btn72" id="exportDeclare"/>
									   <%
											}
									    %>
									    </form>
                                        <div>
                                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                                <tbody>
                                                    <tr class="gv_Head">
                                                      <th style="width:30px;">行号</th>
                                                      <th>姓名</th>
												      <th>性别</th>
												      <th>文化程度</th>
												      <th>身份证号</th>      
												      <th>手机号</th>
												      <th>人员类别</th>
												      <%if((modelType == DeclareInfoRecommendConfig.TYPE_VERIFY || modelType == DeclareInfoRecommendConfig.TYPE_MANAGE) && (declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER)){  %>
												      <th>主体产业</th>
												      <%}%>
												      <th>申请方式</th>
												      <th>地区</th>
												      <%if(modelType == DeclareInfoRecommendConfig.TYPE_CLASSIFY){ %>
												      <th>培育类型</th>
												      <%} else{%>
												      <th>审核状态</th>
												      <%} %>
												      <%if(modelType == DeclareInfoRecommendConfig.TYPE_VERIFY || modelType == DeclareInfoRecommendConfig.TYPE_MANAGE){ %>
												      <th></th>
												      <%}else if(modelType == DeclareInfoRecommendConfig.TYPE_CLASSIFY){ %>
												      <th>对象类别</th>
												      <%} %>
												      <th>操作</th>
                                                    </tr>                                                
                                                    <% int i=0;
												    for(DeclareReportBean bean:list){%> 
												    <tr class="gv_Item">
												      <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=(PAGE_BEAN.getCurrentPageNumber()-1)*DeclareAdminConfig.DEFAULT_PAGE_SIZE + ++i %></span></td>
												      <td><span id="declare_<%=bean.getDeclareId()%>"><%=bean.getUserName() %></span></td>
												      <td><span><%=MapKit.getValueFromMap(bean.getUserGender(), GenderConfig.GENDER_MAP) %></span></td>
												      <td><span><%=MapKit.getValueFromMap(bean.getEducation(), FarmerInfoConfig.EDUCATION_LEVEL_MAP) %></span></td>
												      <td><span><%=bean.getIdentityNumber() %></span></td>
												      <td><span><%=bean.getUserMobile() %></span></td>
												      <%if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) {%>
												      <td><span><%=MapKit.getValueFromMap(bean.getFarmerType(), FarmerInfoConfig.YOUNG_FARMER_PERSONNEL_CATEGORY_MAP)%></span></td>
													  <%} else if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) {%>
													  <td><span><%=MapKit.getValueFromMap(bean.getFarmerType(), FarmerInfoConfig.PRODUCT_FARMER_PERSONNEL_CATEGORY_MAP)%></span></td>
													  <%}else if (bean.getDeclareType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) {%>
													  <td><span><%=MapKit.getValueFromMap(bean.getFarmerType(), FarmerInfoConfig.LEADER_FARMER_PERSONNEL_CATEGORY_MAP)%></span></td>
													  <%} else {%>
													  <td></td>
													  <%} %>
													  <%if((modelType == DeclareInfoRecommendConfig.TYPE_VERIFY || modelType == DeclareInfoRecommendConfig.TYPE_MANAGE) && (declareType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER || declareType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER)){ %>
												      <td><span><%=MapKit.getValueFromMap(bean.getIndustryTypeId1(), industryTypeMap)%></span></td>
												      <%}%>
												      <td><span><%=MapKit.getValueFromMap(bean.getRegistryType(), DeclareInfoConfig.REGISTRY_TYPE_MAP)%></span></td>
												      <td><span><%=bean.getProvince()%>/<%=bean.getCity()%>/<%=bean.getRegion()%></span></td>
												      <%if(modelType == DeclareInfoRecommendConfig.TYPE_CLASSIFY){ %>
												      <td><span><%=MapKit.getValueFromMap(bean.getDeclareType(), DeclareInfoConfig.DECLARE_TYPE_MAP) %></span></td>
												      <%} else{%>
												      <td><span><%=MapKit.getValueFromMap(bean.getDeclareStatus(), DeclareInfoRecommendConfig.REVIEW_STATUS_MAP) %></span></td>
												      <%} %>
												       <%if(modelType == DeclareInfoRecommendConfig.TYPE_VERIFY || modelType == DeclareInfoRecommendConfig.TYPE_MANAGE){ %>												       
												       <td>
												       <%
												       if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND,OperationConfig.SEARCH)) {
									                   %>
												      	<a href="/declare/admin/DeclareInfoRecommendManage.do?declareId=<%=bean.getDeclareId()%>">申报表</a> 
												      <%} %>
												      </td>
												      <td>
												       <%
										                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_VERIFY,OperationConfig.APPROVE)) {
									                   %>
												      	<a href="/declare/admin/DeclareInfoRecommendAcceptCommit.do?declareId=<%=bean.getDeclareId()%>&action=<%=DeclareInfoRecommendConfig.COMMIT_ACTION_REGION_APPROVE%>&modelType=<%=modelType%>">通过</a> 
												      	<a href="/declare/admin/DeclareInfoRecommendAcceptCommit.do?declareId=<%=bean.getDeclareId()%>&action=<%=DeclareInfoRecommendConfig.COMMIT_ACTION_NOT_APPROVE%>&modelType=<%=modelType%>">不通过</a> 
												      	<%}if(adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_VERIFY,OperationConfig.REVIEW) && bean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)){ %>
												      	<a href="/declare/admin/DeclareInfoRecommendAcceptCommit.do?declareId=<%=bean.getDeclareId()%>&action=<%=DeclareInfoRecommendConfig.COMMIT_ACTION_APPROVE%>&modelType=<%=modelType%>">通过</a> 
												      	<a href="/declare/admin/DeclareInfoRecommendAcceptCommit.do?declareId=<%=bean.getDeclareId()%>&action=<%=DeclareInfoRecommendConfig.COMMIT_ACTION_NOT_APPROVE%>&modelType=<%=modelType%>">不通过</a>
												      	<%} %>
												      </td>
												      <%}else if(modelType == DeclareInfoRecommendConfig.TYPE_MANAGE){ %>
												      <td>
												      <%
										                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_MANAGE,OperationConfig.REJECT)) {
									                   %>
												      	<a href="/declare/admin/DeclareInfoRecommendAcceptCommit.do?declareId=<%=bean.getDeclareId()%>&action=<%=DeclareInfoRecommendConfig.COMMIT_ACTION_REJECT%>&modelType=<%=modelType%>">驳回</a> 
												      	<%} %>
												      	<%
										                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_MANAGE,OperationConfig.REMOVE)) {
									                   %>
												      	<a onclick="deleteDeclare('<%=bean.getDeclareId()%>')">删除</a> 
												       <%} %>
												      </td>
												      <%} else if(modelType == DeclareInfoRecommendConfig.TYPE_CLASSIFY){ %>
												      <td><%=MapKit.getValueFromMap(bean.getDeclareCategory(), DeclareInfoConfig.DECLARE_CATEGORY_MAP) %></td>
												      <td>
												      <%
										                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_CLASSIFY,OperationConfig.UPDATE)) {
									                   %>
												      	<a href="/declare/admin/DeclareInfoRecommendAcceptCommit.do?declareId=<%=bean.getDeclareId()%>&action=<%=DeclareInfoRecommendConfig.COMMIT_ACTION_CHANGE%>&modelType=<%=modelType%>">转换类型</a> 
												      <%} %>
												      </td>
												      <%} %>
												    </tr>	
												    <%} %>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- 页码 -->
                                         <%@ include file="../include/page.jsp" %>
                              <%if(modelType != DeclareInfoRecommendConfig.TYPE_CLASSIFY){ %>
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
$('#exportDeclare').on('click',function(){
        var userName = $('#userName').val();
        var userMobile = $('#userMobile').val();
        var identityNumber = $('#identityNumber').val();
        var queryProvince = $('#queryProvince').val();
        var queryCity = $('#queryCity').val();
        var queryRegion = $('#queryRegion').val();
        var declareType = $('#declareType').val();
        var declareStatus = $('#declareStatus').val();
        var pageNum = $('#pageNum').val();
        var pageSize = $('#pageSize').val();
        window.location.href='DeclareInfoExport.do?userName='+userName+'&userMobile='+userMobile+'&identityNumber='+identityNumber+'&queryProvince='+queryProvince+'&queryCity='+queryCity+'&queryRegion='+queryRegion+'&declareType='+declareType+'&declareStatus='+declareStatus+'&pageNum='+pageNum+'&pageSize='+pageSize;
    
});
function deleteDeclare(id) {
	var declareInfo = $('#declare_'+id).html();
	if(confirm("确定要删除对象【"+declareInfo+"】吗?")){
		var url = "/declare/admin/DeclareInfoRecommendAcceptCommit.do?action=<%=DeclareInfoRecommendConfig.COMMIT_ACTION_DELETE%>&modelType=<%=modelType%>&declareId="+id;
		window.location.href= url
	 }
}
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