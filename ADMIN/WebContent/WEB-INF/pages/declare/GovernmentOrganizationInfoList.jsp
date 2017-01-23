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
<%@ page import="com.lpmas.declare.business.*"  %>
<%
	List<GovernmentOrganizationInfoBean> list = (List<GovernmentOrganizationInfoBean>)request.getAttribute("GovernmentOrganizationInfoList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper") ;
	
    PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	
	String fixProvince = (String)request.getAttribute("FixProvince");
	String fixCity = (String)request.getAttribute("FixCity");
	String fixRegion = (String)request.getAttribute("FixRegion");
	String queryProvince = ParamKit.getParameter(request, "queryProvince", "");
	String queryCity = ParamKit.getParameter(request, "queryCity", "");
	String queryRegion = ParamKit.getParameter(request, "queryRegion", "");
	DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!–[if lte IE 8]> 
  <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
  <![endif]–> 
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
  <title>新型农民职业培训系统 — 主管部门列表</title>
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
					<span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">主管部门</span></div>
				  <div class="right_bg" style="padding-left: 5px;">
				  <form name="" method="post"  id="" onsubmit="javascript:return checkThisForm();" >
					 <div class="form-group form-horizontal"> 
                          <input type="hidden" id="queryProvince"  name="queryProvince" value="<%=queryProvince !=null ? queryProvince : "" %>"/>
                          <input type="hidden" id="queryCity"  name="queryCity" value="<%=queryCity !=null ? queryCity : "" %>"/>
 		                    <input type="hidden" id="queryRegion" name="queryRegion" value="<%=queryRegion !=null ? queryRegion : "" %>"/>							  
 		                    
                              	<label>省：</label>
                                  <select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()"></select>  	 
                              	<label>市：</label>
                              	<select class="form-control" name="selectProvince" id="selectCity" onchange="showRegion()" ></select>
                              	<label>县：</label>
                              	<select class="form-control" name="selectProvince" id="selectRegion" onchange="setRegion()" ></select> 
                    </div>
					<br/>
					<div class="form-group form-horizontal">
                                    		<label>年份：</label>	
                                <select name="trainingYear" id="trainingYear">
        						<option value="">请选择</option>
		                         <%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
			                     <option value="<%=InfoYear %>" <%=ParamKit.getIntParameter(request, "trainingYear", 0) == InfoYear? "selected" : ""%>><%=InfoYear %></option>
	                             <%}%>	
        					</select>
                    </div>
                                    	
					<div class="form-group form-horizontal">
					<label>主管部门：</label>  
					<input type="text" name="organizationName" class="form-control" value="<%=ParamKit.getParameter(request, "organizationName","") %>" />
					</div>
					 <div class="form-group form-horizontal">
                          <input type="checkbox" name="directUnder" <%=StringKit.isValid(ParamKit.getParameter(request, "directUnder", ""))? "checked" : "" %>/>
                           <span>只查直属</span>
                 	 </div>
                 	 <div class="form-group form-horizontal">
                      	<input type="checkbox" name="onlyProvince" <%=StringKit.isValid(ParamKit.getParameter(request, "onlyProvince", ""))? "checked" : "" %>/>
                      	<span>只查省级</span>
                      </div>
					<input type="submit" value="查 询" class="btn72" /> 	
				    <%
					if (adminUserHelper.hasPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO,
						OperationConfig.CREATE )) {
					%>
					<input type="button" value="录入部门" class="btn72" onclick="javascript:location.href='/declare/admin/GovernmentOrganizationInfoManage.do'"/>				
					<%
					}
					%>
					 <input type="button" value="导出" class="btn72" onclick="javascript:location.href='GovernmentOrganizationInfoExport.do?queryProvince=<%=queryProvince%>&queryCity=<%=queryCity%>&queryRegion=<%=queryRegion%>&trainingYear=<%=ParamKit.getParameter(request, "trainingYear", "")%>&organizationName=<%=ParamKit.getParameter(request, "organizationName","")%>&directUnder=<%=ParamKit.getParameter(request, "directUnder", "")%>&onlyProvince=<%=ParamKit.getParameter(request, "onlyProvince", "")%>'"/>
					 </form>
					<div>
					<table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse; width: 97%; ">
					  <tbody>
					  <tr class="gv_Head">
						<th style="width:30px;">行号</th>
						<th><a>培训年度</a></th>
						<th><a>所属地区</a></th>
						<th><a>主管部门</a></th>
						<th><a>主管处（科）室</a></th>
						<th><a>处（科）室负责人姓名</a></th>
						<th><a>处（科）室负责人电话</a></th>
						<th><a>处（科）室经办人姓名</a></th>
						<th><a>处（科）室经办人电话</a></th>
						<th><a>传真</a></th>
						<th><a>通讯地址</a></th>
						<th><a>邮政编码</a></th>
						<th></th>
						<th></th>
					  </tr>
					    <%
                          int rowCount = 1;
					    	  if(list.size() > 0){
							  for(GovernmentOrganizationInfoBean bean:list){ 
						%>
					  <tr class="gv_Item">
						<td style="width:30px;">
							<span style="display:inline-block;width:30px;"><%=rowCount %></span>
						</td>
						<td>
							<span><%=bean.getTrainingYear() %></span></td>
						<td>
							<span><%=bean.getOrganizationLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY) ? DeclareAdminConfig.COUNTRY_STR : bean.getProvince()+bean.getCity()+bean.getRegion() %></span>
						</td>
						<td>
							<span><%=bean.getOrganizationName() %></span></td>
						<td>
							<span><%=bean.getDepartment() %></span></td>
						<td>
							<span><%=bean.getResponsiblePersonName() %></span></td>
						 
						<td>
							<span><%=bean.getResponsiblePersonMobile() %></span></td>
						<td>
							<span><%=bean.getOperatorName() %></span></td>
						<td>
							<span><%=bean.getOperatorMobile() %></span></td>
						<td>
							<span><%=bean.getFax() %></span></td>
						<td>
							<span><%=bean.getAddress() %></span></td>
						<td>
							<span><%=bean.getZipCode() %></span></td>
						<td>
							<span>
					<%
					if (adminUserHelper.hasPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO,
						OperationConfig.UPDATE )) {
					%>
						<a href="/declare/admin/GovernmentOrganizationInfoManage.do?organizationId=<%=bean.getOrganizationId() %> ">编辑</a>
					<%
					}
					%>
						   </span>
					  </td>
					  <td>
							<span>
							<%
					if (adminUserHelper.hasPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO,
						OperationConfig.REMOVE )) {
					%>
						<a onclick="deleteGovernmentOrganization('<%=bean.getOrganizationId() %>', '<%=bean.getOrganizationName()%>')">删除</a>
					<%
					}
					%>
					  </span>
					 </td>
					  </tr>
					   <% rowCount++;}  }%>
					  </tbody>
					</table>
					</div>
					<!-- 页码 -->
				  <%@ include file="../include/page.jsp" %>
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
  <script type="text/javascript" src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
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
	sel.append("<option value = ''></option>");
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
	if(text == ""){
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
//选项
$('.select-item select').change(function(){
	var val = $(this).children('option:selected').text();
	$(this).parents('.select-item').find('.select-txt').text(val);
});

function deleteGovernmentOrganization(id,name){
	if(confirm("确认删除主管部门【"+ name+"】吗?")){ 
		var url = "GovernmentOrganizationInfoRemove.do?organizationId="+id ;
		window.location.href = url  ;
	}
}
</script>
</body>
</html>