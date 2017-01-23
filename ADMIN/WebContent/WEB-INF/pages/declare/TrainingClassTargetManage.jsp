<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%
    AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
    DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
    List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = (List<TrainingOrganizationInfoBean>)request.getAttribute("trainingOrganizationInfoList");
    Integer level = (Integer)request.getAttribute("level");
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
    
    <style type="text/css">
        
        body
        {
            font-size: 14px;
            font-family: "宋体";
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
                            			<span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">添加任务</span>
                            		</div>
                            		<div class="right_bg" style="padding-left: 5px;">
                            		<form id="formData" name="formData" method="post" action="TrainingClassTargetManage.do" onsubmit="javascript:return checkForm('formData');">
                                        <div>
                                        	<table class="editView" style="width: 98%">
                                                <tbody>
                                                    <tr>
                                                        <td class="td_head text_right" style="width:30%;">
                                                            <span>年度</span>
                                                        </td>
                                                        <td class="td_data text_left">
														<select name="trainingYear" id="trainingYear" >
								                         <%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
									                     <option value="<%=InfoYear %>" <%=ParamKit.getIntParameter(request, "trainingYear", 0) == InfoYear? "selected" : ""%>><%=InfoYear %></option>
							                             <%}%>	
			                                           </select>
                                                        </td>
                                                    </tr>
                                                    <%if(level!=1){ %>
                                                    <tr>
                                                        <td class="td_head text_right" style="width:30%;">
                                                            <span>地区</span>
                                                        </td>
                                                        <input type="hidden" id="queryProvince"  name="queryProvince" value="<%=queryProvince !=null ? queryProvince : "" %>"/>
				                                        <input type="hidden" id="queryCity"  name="queryCity" value="<%=queryCity !=null ? queryCity : "" %>"/>
				           		                        <input type="hidden" id="queryRegion" name="queryRegion" value="<%=queryRegion !=null ? queryRegion : "" %>"/>	
                                                        <td class="td_data text_left">
                                                            <select name="selectProvince" id="selectProvince" onchange="showCity()">
				                                               <option value="">请选择</option>
				                                           </select>
				                                           <select name="selectCity" id="selectCity" onchange="showRegion()">
				                                               <option value="">请选择</option>
				                                            </select>
				                                            <select name="selectRegion" id="selectRegion" onchange="setRegion()">
				                                               <option value="">请选择</option>
				                                            </select>
                                                        </td>
                                                    </tr>
                                                    <%} %>
                                                    <%if(level!=2){ %>
                                                    <tr>
                                                        <td class="td_head text_right" style="width:30%;">
                                                            <span>培训机构</span>
                                                        </td>
                                                        <td class="td_data text_left">
                                                             <select name="organizationId" id="organizationId">
					                                            <option value="" ></option>
															    <%for (TrainingOrganizationInfoBean trainingOrganizationInfoBean : trainingOrganizationInfoList) {%>
															    <option value="<%=trainingOrganizationInfoBean.getOrganizationId() %>" <%=trainingOrganizationInfoBean.getOrganizationId() == ParamKit.getIntParameter(request, "organizationId", 0) ? "selected" : ""%>><%=trainingOrganizationInfoBean.getOrganizationName() %></option>
																<%}%>
					                                         </select>
                                                        </td>
                                                    </tr>
                                                    <%} %>
                                                    <tr>
                                                        <td class="td_head text_right" style="width:30%;">
                                                            <span style="color:#FE7200;">*</span>
                                                            <span>现代青年农场主培训任务数（人）</span>
                                                        </td>
                                                        <td class="td_data text_left" style="width:160px;">
                                                            <input type="text" id="targetQuantity1" name="targetQuantity1" checkStr="现代青年农场主培训任务数;num;true;;50">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td_head text_right" style="width:30%;">
                                                            <span style="color:#FE7200;">*</span>
                                                            <span>生产经营型职业农民培训任务数（人）</span>
                                                        </td>
                                                        <td class="td_data text_left" style="width:160px;">
                                                            <input type="text" id="targetQuantity2" name="targetQuantity2" checkStr="现代青年农场主培训任务数;num;true;;50">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td_head text_right" style="width:30%;">
                                                            <span style="color:#FE7200;">*</span>
                                                            <span>专业技能型职业农民培训任务数（人）</span>
                                                        </td>
                                                        <td class="td_data text_left" style="width:160px;">
                                                            <input type="text" id="targetQuantity3" name="targetQuantity3" checkStr="专业技能型职业农民培训任务数;num;true;;50">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td_head text_right" style="width:30%;">
                                                            <span style="color:#FE7200;">*</span>
                                                            <span>社会服务型农民职业培训任务数（人）</span>
                                                        </td>
                                                        <td class="td_data text_left" style="width:160px;">
                                                            <input type="text" id="targetQuantity4" name="targetQuantity4" checkStr="社会服务型农民职业培训任务数;num;true;;50">
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="td_head text_right" style="width:30%;">
                                                            <span style="color:#FE7200;">*</span>
                                                            <span>新型农业经营带头人培训任务数（人）</span>
                                                        </td>
                                                        <td class="td_data text_left" style="width:160px;">
                                                            <input type="text" id="targetQuantity5" name="targetQuantity5" checkStr="新型农业经营带头人培训任务数;num;true;;50">
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <div class="text_center">
                                                <input type="submit" class="btn72 mt_5" value="保 存"/>
                                            </div>
                                           
                                        </div>
                                       </form>
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
	if(text == ""  || text == "请选择" ){
		$("#queryProvince").val("");
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