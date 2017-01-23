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
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
    AdminUserHelper adminUserHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	List<DeclareReportBean> list = (List<DeclareReportBean>)request.getAttribute("DeclareReportList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
	Integer classId = (Integer)request.getAttribute("classId");
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
    <script type='text/javascript' src="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.js"></script>
    <link rel="stylesheet" href="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.css" type="text/css" media="screen" />
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
            font-family: "宋体";
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
        ol li
        {
            margin: 8px;
        }
        #con
        {
            font-size: 12px;
            margin: 0px auto;
            width: 100%;
            height: 100%;
        }
        #tags
        {
            padding-right: 0px;
            padding-left: 0px;
            padding-bottom: 0px;
            margin: 0px 0px 0px 10px;
            width: 400px;
            padding-top: 0px;
            height: 23px;
            width: 1000px;
        }
        #tags li
        {
            background: url(../../../images/tagleft.gif) no-repeat left bottom;
            float: left;
            margin-right: 1px;
            list-style-type: none;
            height: 23px;
        }
        #tags li a
        {
            padding-right: 10px;
            padding-left: 10px;
            background: url(../../../images/tagright.gif) no-repeat right bottom;
            float: left;
            padding-bottom: 0px;
            color: #999;
            line-height: 23px;
            padding-top: 0px;
            height: 23px;
            text-decoration: none;
        }
        #tags li.emptyTag
        {
            background: none transparent scroll repeat 0% 0%;
            width: 4px;
        }
        #tags li.selectTag
        {
            background-position: left top;
            background-repeat: repeat-x;
            margin-bottom: -2px;
            position: relative;
            height: 25px;
        }
        #tags li.selectTag a
        {
            background-position: right top;
            color: #FFF;
            line-height: 25px;
            height: 25px;
        }
        #tagContent
        {
            border-right: #aecbd4 1px solid;
            padding-right: 1px;
            border-top: #aecbd4 1px solid;
            padding-left: 1px;
            padding-bottom: 1px;
            border-left: #aecbd4 1px solid;
            padding-top: 1px;
            border-bottom: #aecbd4 1px solid;
            background-color: #fff;
        }
        .tagContent
        {
            padding-right: 10px;
            display: none;
            padding-left: 5px;
            padding-bottom: 10px;
            width: 98%;
            color: #474747;
            padding-top: 5px;
            height: 498px;
        }
        #tagContent div.selectTag
        {
            display: block;
        }
    </style>
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
                                    <%if(classId == 0){ %>
                                  	<div class="con fl pt_10">
                                  		<ul id="tags" class="tags">
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER%>">现代青年农场主</a></li>
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER%>">新型农业经营主体带头人</a></li>                                  		
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER%>">生产经营型</a></li>                                  			
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER%>">专业技能型</a></li>                                 		
                                  			<li><a class="text_15" href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER%>">专业服务型</a></li>                                 		
                                  			<li class="selectTag"><a class="text_15" href="DeclareInfoAuthManage.do?classId=<%=Constants.STATUS_NOT_VALID %>">非学员认定</a></li>                                  			
                                  		</ul>
                                  		<div id="tagContent">
	                                  		<div class="tagContent selectTag scroller-box">
	                                  			<div class="right_bg pl_5">
		                                 <%}else{ %>
		                                 <div class="right_bg" style="padding-left: 5px;">
		                                 <%} %>
	                                  			<form name="formSearch" method="post" action="DeclareInfoAuthManage.do">
	                                  			<input type="hidden" name="classId" id="classId" value="<%=classId%>">
	                                  			<%if(classId == 0){ %>
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
			                                           <select class="form-control" name="declareYear" id="declareYear" >
			                                            <option value="">请选择</option>
								                         <%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
									                     <option value="<%=InfoYear %>" <%=ParamKit.getIntParameter(request, "declareYear", 0) == InfoYear? "selected" : ""%>><%=InfoYear %></option>
							                             <%}%>	
			                                           </select>
			                                        </div>
			                                        <div class="form-group form-horizontal">
			                                            <label>认定状态：</label>
			                                            <select class="form-control" name="authStatus" id="authStatus">
			                                                <option value="" >全部</option>
														    	<%
														    	String status = ParamKit.getParameter(request, "authStatus", "");
														    	for(StatusBean<String, String> statusBean:DeclareInfoConfig.AUTH_STATUS_LIST){ %>
														        <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(status))?"selected":"" %>><%=statusBean.getValue() %></option>
														    <%} %>
			                                            </select>
			                                        </div>
			                                        <%}else{ %>
		                                            <div class="form-group form-horizontal">
			                                            <label>姓名：</label>
			                                            <input type="text" class="form-control" name="userName" id="userName" value="<%=ParamKit.getParameter(request, "userName", "") %>" size="20"/>
			                                        </div>
			                                        <div class="form-group form-horizontal">
			                                            <label>身份证号：</label>
			                                            <input type="text" class="form-control" name="identityNumber" id="identityNumber" value="<%=ParamKit.getParameter(request, "identityNumber", "") %>" size="20"/>
			                                        </div>
		                                             <%} %>
	                                  				<input type="submit" value="查 询"  class="btn72">
	                                  				</form>
	                                  				<div>
	      					                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
	      					                              <tbody>
	      					                                <tr class="gv_Head">
																<th><input type="checkbox" id="checkAllApprove"></th>
																<th>姓名</th>
																<th>性别</th>
																<th>身份证号</th>
																<th>文化程度</th>
																<th>状态</th>
																<th>通过认定</th>																
	      					                                </tr>
	      					                                <%for(DeclareReportBean bean:list){%>
	      					                                <tr>
	      					                                	<td><input type="checkbox" name="checkDeclare" id="check_<%=bean.getDeclareId() %>" value="<%=bean.getDeclareId()%>"></td>
	      					                                	<td><%=bean.getUserName() %></td>
	      					                                	<td><%=MapKit.getValueFromMap(bean.getUserGender(), GenderConfig.GENDER_MAP) %></td>
	      					                                	<td><%=bean.getIdentityNumber() %></td>
	      					                                	<td><%=MapKit.getValueFromMap(bean.getEducation(), FarmerInfoConfig.EDUCATION_LEVEL_MAP) %></td>
	      					                                	<td><%=MapKit.getValueFromMap(bean.getAuthStatus(), DeclareInfoConfig.AUTH_STATUS_MAP) %></td>
	      					                                	<%if(bean.getAuthStatus().equals(DeclareInfoConfig.AUTH_STATUS_APPROVE)){ %>												    
															      <td>    
															      <%
													                 if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_AUTH,OperationConfig.APPROVE)) {
												                  %>  	
															      	<a href="/declare/admin/DeclareInfoAuthAcceptCommit.do?declareId=<%=bean.getDeclareId()%>&action=<%=TrainingClassUserConfig.COMMIT_ACTION_AUTH_WAIT_APPROVE%>&classId=<%=classId%>">取消认定</a> 
															      <%} %>
															      </td>
															      <%}else{ %>															 
															       <td>     
															        <%
													                 if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_AUTH,OperationConfig.APPROVE)) {
												                     %>  	
															      	<a onclick="approve(<%=bean.getDeclareId()%>);">通过</a>
															        <%} %>
															      </td>
															      <%} %>	      					                                		      					                                	
	      					                                </tr>
	      					                               <%} %>
	      					                              </tbody>
	      					                            </table>
	      					                    	</div>
	      					                    	<div style="text-align: center; margin: 20px 0 20px 0;">
	      					                    	<%
									                 if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_AUTH,OperationConfig.APPROVE)) {
								                     %>  
														<input type="button" id="batch" value="批量认定" class="btn72">
													<%} %>
													</div>
	      					                        <!-- 页码 -->
	      					                        <%@ include file="../include/page.jsp" %>
	      					                        <%if(classId == 0){ %>
	                                  				</div>
	                                  			</div>
	                                  		</div>
	                                  	</div>
	                                  	<%}else{ %>
                                    </div>
                                         <%} %>
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
<script type='text/javascript'>
$(function() {
    $("#checkAllApprove").click(function() {
         $('input[name="checkDeclare"]').attr("checked",this.checked); 
     });
     var $subBox = $("input[name='checkDeclare']");
     $subBox.click(function(){
         $("#checkAllApprove").attr("checked",$subBox.length == $("input[name='checkDeclare']:checked").length ? true : false);
     });   
 });
$(document).ready(function() {
	showProvince();	 
	$("#batch").click(
		function() {
			var selectProvince = $("#selectProvince  option:selected").text(); 
			var selectCity = $("#selectCity  option:selected").text();  
			var selectRegion = $("#selectRegion  option:selected").text(); 
			$.fancybox.open({
				href : 'TrainingOrganizationInfoSelect.do?classId=<%=classId%>&queryProvince='+selectProvince+'&queryCity='+selectCity+'&queryRegion='+selectRegion+'&callbackFun=selectTrainingOrganizationInfo',
				type : 'iframe',
				width : 560,
				minHeight : 500
		});
	});
});
function selectTrainingOrganizationInfo(value) {
	 var checkDeclareList="";
	  $("input[name='checkDeclare']:checked").each(function () {
         if(checkDeclareList!="")    
         {    
        	 checkDeclareList+=",";    
          }    
         checkDeclareList += $(this).val(); 
     });
	 if (checkDeclareList == ''){
			alert("请先选择认定的学员");
			return;
	}
	var url = "DeclareInfoAuthAcceptCommit.do?organizationId="+ value+"&action=<%=TrainingClassUserConfig.COMMIT_ACTION_AUTH_APPROVE%>&checkDeclareList="+ checkDeclareList +"&classId=<%=classId%>";
	window.location.href= url
}
function approve(declareId) {
	 $("#check_"+ declareId).attr("checked",true);
	 document.getElementById("batch").click(); 
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