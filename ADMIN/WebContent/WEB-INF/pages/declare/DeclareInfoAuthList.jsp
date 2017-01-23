<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
    List<TrainingClassInfoBean> list = (List<TrainingClassInfoBean>)request.getAttribute("TrainingClassInfoList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	int trainingType = ParamKit.getIntParameter(request, "trainingType", 0);
	DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
	Map<Integer, String> trainingOrganizationInfoMap= (Map<Integer, String>)request.getAttribute("TrainingOrganizationInfoMap");
	Map<Integer, TrainingOrganizationInfoBean> authOrganizationInfoMap= (Map<Integer, TrainingOrganizationInfoBean>)request.getAttribute("AuthOrganizationInfoMap");
	Map<Integer, String> majorInfoMap= (Map<Integer, String>)request.getAttribute("MajorInfoMap");
	String fixProvince = (String)request.getAttribute("FixProvince");
	String fixCity = (String)request.getAttribute("FixCity");
	String fixRegion = (String)request.getAttribute("FixRegion");
	String queryProvince = ParamKit.getParameter(request, "queryProvince", "");
	String queryCity = ParamKit.getParameter(request, "queryCity", "");
	String queryRegion = ParamKit.getParameter(request, "queryRegion", "");
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
    <title>新型农民职业培训系统 — 认定管理</title>
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
<body>
<body class="body-index">
  <%@include file="../nav/navigation.jsp" %>
   <div class="warpDefaultMgr">
            <table class="table_wrp">
                <tbody>
                    <tr>
                        <td style="width: 191px; background-color: #f2f2f2;" valign="top">
                            <div class="detail_left">
                                <div class="detail_left_top"></div>
                                <div id="listItem" class="detail_left">
                                <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO , OperationConfig.SEARCH)) {
                        		%>
                        		  <div class="detail_left_box detail_box3">
                                    	<a onclick="javascript:location.href='TrainingOrganizationInfoList.do?organizationType=<%=TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE%>'">认定机构管理</a>
                                    </div>
                        		<%	 
                        		}
                                %> 
                                 <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_AUTH, OperationConfig.SEARCH)) {
                        		%>
                        		    <div class="detail_left_box detail_box3">
                                        <a href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER%>">认定学员管理</a>
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
                                      	<span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">认定学员管理</span>
                                    </div>
                                  	<div class="con fl pt_10">
                                  		<ul id="tags" class="tags">
                                  			<%if(trainingType == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER%>">现代青年农场主</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER%>">现代青年农场主</a></li>
                                  			<%} %>
                                  			 <%if(trainingType == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER%>">新型农业经营主体带头人</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER%>">新型农业经营主体带头人</a></li>
                                  			<%} %>
                                  			 <%if(trainingType == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER%>">生产经营型</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER%>">生产经营型</a></li>
                                  			<%} %>
                                  			 <%if(trainingType == DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER%>">专业技能型</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER%>">专业技能型</a></li>
                                  			<%} %>
                                  			 <%if(trainingType == DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER){ %>
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER%>">专业服务型</a></li>
                                  			<%} else{%>
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER%>">专业服务型</a></li>
                                  			<%} %>
                                  			<li><a class="text_15" href="DeclareInfoAuthManage.do?classId=<%=Constants.STATUS_NOT_VALID %>">非学员认定</a></li>                                  			
                                  		</ul>
                                  		<div id="tagContent">
	                                  		<div class="tagContent selectTag scroller-box">
	                                  			<div class="right_bg pl_5">
	                                  			<form name="formSearch" method="post" action="DeclareInfoAuthList.do">
                                                  <input type="hidden" name="trainingType" id="trainingType" value="<%=trainingType%>">
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
			                                            <label>年份：</label>
			                                           <select class="form-control" name="trainingYear" id="trainingYear" >
			                                            <option value="">请选择</option>
								                         <%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
									                     <option value="<%=InfoYear %>" <%=ParamKit.getIntParameter(request, "trainingYear", 0) == InfoYear? "selected" : ""%>><%=InfoYear %></option>
							                             <%}%>	
			                                           </select>
			                                        </div>
			                                        <div class="form-group form-horizontal">
			                                         	<label>认定状态：</label>
				                                      	<select class="form-control" name="authStatus" id="authStatus" >
			                                            <option value="">请选择</option>
								                         <%for(StatusBean<String, String> statusBean : TrainingClassInfoConfig.AUTH_STATUS_LIST){ %>
									                     <option value="<%=statusBean.getStatus() %>" <%=ParamKit.getParameter(request, "authStatus", "").equals(statusBean.getStatus())? "selected" : ""%>><%=statusBean.getValue() %></option>
							                             <%}%>	
			                                           </select>
				                                      </div>
	                                  				<input type="submit" value="查 询"  class="btn72">
	                                  				</form>
	                                  				<div>
	      					                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
	      					                              <tbody>
	      					                                <tr class="gv_Head">
																<th style="width:30px;">行号</th>
																<th>培训机构</th>
																<th>培训班</th>
																<th>培训产业</th>
																<th>培训人数</th>
																<th>认定状态</th>
																<th>认定时间</th>
																<th>认定级别</th>
																<th>认定管理</th>
	      					                                </tr>
	      					                                <% int i=0;
												            for(TrainingClassInfoBean bean:list){%> 
	      					                                <tr>	      					                           
	      					                                	<td><%=(PAGE_BEAN.getCurrentPageNumber()-1)*20 + ++i %></td>
	      					                                	<td><span><%=MapKit.getValueFromMap(bean.getOrganizationId(), trainingOrganizationInfoMap) %></span></td>
	      					                                	<td><%=bean.getClassName() %></td>
	      					                                	<td><%=MapKit.getValueFromMap(bean.getMajorId(), majorInfoMap) %></td>
	      					                                	<td><%=bean.getClassPeopleQuantity() %></td>
	      					                                	<td><%=MapKit.getValueFromMap(bean.getAuthStatus(), TrainingClassInfoConfig.AUTH_STATUS_MAP) %></td>
	      					                                	<td><%=bean.getAuthTime()!=null ? DateKit.formatTimestamp(bean.getAuthTime(), DateKit.DEFAULT_DATE_FORMAT) : ""%></td>
	      					                                	<td><%=authOrganizationInfoMap.get(bean.getAuthOrganizationId()) != null ? authOrganizationInfoMap.get(bean.getAuthOrganizationId()).getOrganizationName() : ""%></td>
	      					                                	<td><a href="/declare/admin/DeclareInfoAuthManage.do?classId=<%=bean.getClassId() %>">认定管理</a></td>
	      					                                </tr>
	      					                                <%} %>
	      					                              </tbody>
	      					                            </table>
	      					                    	</div>
	      					                        <!-- 页码 -->
	      					                        <%@ include file="../include/page.jsp" %>
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