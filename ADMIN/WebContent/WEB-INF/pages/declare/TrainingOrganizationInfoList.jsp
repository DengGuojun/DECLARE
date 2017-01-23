<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%
	List<TrainingOrganizationInfoBean> list = (List<TrainingOrganizationInfoBean>)request.getAttribute("TrainingOrganizationInfoList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper") ;
	String organizationType = ParamKit.getParameter(request, "organizationType",TrainingOrganizationConfig.ORGANIZATION_TRAINING ) ; 
	//从url获取organizationType 区分是实训基地库 还是培训基地库
	//String organizationType = ParamKit.getParameter(request, "organizationType") ;
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
    <title>新型农民职业培训系统 — 部门列表页面</title>
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
                            <div class="detail_left">
                                <div class="detail_left_top"></div>
                                <div id="listItem" class="detail_left">
                                	 <% if(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE.equals(organizationType)){
                                	%>
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
                                	<%
                                		}else{
                                		 if(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING.equals(organizationType)){
                                				//此时是实训单位
                                				 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO , OperationConfig.CREATE)) {
                                    %>     
                                    		<div class="detail_left_box detail_box3">
                                    			<a href="TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>">实训单位录入</a>
                                    		</div> 
                                    <%	
                                    		} 
                                				 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO , OperationConfig.SEARCH)) {
                                    %>
                                    		<div class="detail_left_box detail_box3">
                                    			<a href="TrainingOrganizationInfoList.do?organizationType=<%=organizationType %>">实训单位管理</a>
                                    		</div> 
                                    <%       		   
                                 				}  
                                		} else if(TrainingOrganizationConfig.ORGANIZATION_TRAINING.equals(organizationType)){
                                			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO , OperationConfig.CREATE)) {
                                     %>     
                                                		<div class="detail_left_box detail_box3">
                                                			<a href="TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>">培训单位录入</a>
                                                		</div> 
                                     <%	
                                            } 
                                			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO , OperationConfig.SEARCH)) {
                                                %>     
                                                           		<div class="detail_left_box detail_box3">
                                                           			<a href="TrainingOrganizationInfoList.do?organizationType=<%=organizationType %>">培训单位管理</a>
                                                           		</div> 
                                                <%	
                                                       } 
                                		}
                                	}
                            %> 
                                </div>
                            </div>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;"><%=TrainingOrganizationConfig.ORGANIZATION_MAP.get(organizationType) %>
                                        </span>&nbsp;&nbsp;
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <form name="formSearch" method="post" action="">
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
                                        <label>机构名称：</label>
                                        <input type="text" name="organizationName" class="form-control" value="<%=ParamKit.getParameter(request, "organizationName","") %>"/>
                                        </div>
                                       <div class="form-group form-horizontal">
                                      	<input type="checkbox" name="directUnder" <%=StringKit.isValid(ParamKit.getParameter(request, "directUnder", ""))? "checked" : "" %>/>
                                      	<span>只查直属</span>
                                      </div>
                                        
                                        <%
                                        if(organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)){
                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO, OperationConfig.SEARCH)){
                                		%>
                                				 <input type="submit" value="查 询" class="btn72" />
                                		<%	
                                			}
                                		}else if(organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)){
                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO, OperationConfig.SEARCH)){
                                		%>
                                				 <input type="submit" value="查 询" class="btn72" />
                                		<% 	 
                                			}
                                		}else if(organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)){
                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO, OperationConfig.SEARCH)){
                                		%>
                               				 <input type="submit" value="查 询" class="btn72" />
                               			<% 			
                                			}
                                		} 
                                        %>
                                        
                                         <%
                                        if(organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)){
                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO, OperationConfig.CREATE)){
                                		%>
                                				<input type="button" class="btn72 font_small" value="录入" 
                                		 onclick="javascript:location.href='/declare/admin/TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>'"
                                		 />
                                		<%	
                                			}
                                		}else if(organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)){
                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO, OperationConfig.CREATE)){
                                		%>
                                				<input type="button" class="btn72 font_small" value="录入" 
                                		 onclick="javascript:location.href='/declare/admin/TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>'"
                                		 />
                                		<% 	 
                                			}
                                		}else if(organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)){
                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO, OperationConfig.CREATE)){
                                		%>
                                				<input type="button" class="btn72 font_small" value="录入" 
                                		 onclick="javascript:location.href='/declare/admin/TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>'"
                                		 />
                                		<% 			
                                			}
                                		}
                                        %>
                                      <%--
                                        <input type="submit" value="导 出" class="btn72" />
                                      --%>  
                                      </form>
                                        <div>
                                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse; width: 97%; ">
                                              <tbody>
                                                <tr class="gv_Head">
                                                  <th style="width:30px;">行号</th>
                                                  <th><a>年份</a></th>
                                                  <th><a>地区</a></th>
                                                  <th><a>机构名称</a></th>
                                                  <th><a>机构类型</a></th>
                                                  <th><a>法人代表</a></th>
                                                  <th><a>联系电话（区号必填）</a></th>
                                                  <th><a>通讯地址</a></th>
                                                  <th><a>邮政编号</a></th>
                                                  <th></th>
                                                  <th></th>
                                                  <%
                               					 if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)  && adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_RECOMMEND, OperationConfig.CREATE)) {
                        							 %>
                                                  <th></th> 
                                                  <%} %>
                                                </tr>
                                                  <%
                                        			int rowCount = 1;
									    			for(TrainingOrganizationInfoBean bean:list){ 
									    		 %>
                                                <tr class="gv_Item">
                                                  <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                                  <td><span><%=bean.getTrainingYear() %></span></td>
                                                  <td><span><%=bean.getOrganizationLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY) ? DeclareAdminConfig.COUNTRY_STR : bean.getProvince()+bean.getCity()+bean.getRegion() %></span></td>
                                                  <td><span><%=bean.getOrganizationName() %></span></td>
                                                  <td><span><%=MapKit.getValueFromMap(bean.getOrganizationCategory(), TrainingOrganizationConfig.ORGANIZATION_TYPE_MAP) %></span></td>
                                                  <td><span><%=bean.getRepresentativeName() %></span></td>
                                                  <td><span><%=bean.getTelephone() %></span></td>
                                                  <td><span><%=bean.getAddress() %></span></td>
                                                  <td><span><%=bean.getZipCode() %></span></td>
                                                   <%
			                                        if(organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_TRAINING)){
			                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO, OperationConfig.UPDATE)){
			                                		%>
			                                			<td><span><a href="/declare/admin/TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>&organizationId=<%=bean.getOrganizationId()%>">编辑</a></span></td>
			                                		<%	
			                                			}
			                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO, OperationConfig.REMOVE)){
			                                        		%>
			                                           <td><span><a onclick="deleteOrganization('<%=bean.getOrganizationId() %>', '<%=bean.getOrganizationName() %>', '<%=bean.getOrganizationType() %>')">删除</a></span></td>
			                                        		<%	
			                                        			}
				                                		}else if(organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING)){
				                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO, OperationConfig.UPDATE)){
				                                		%>
				                                			<td><span><a href="/declare/admin/TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>&organizationId=<%=bean.getOrganizationId()%>">编辑</a></span></td>
				                                		<% 	 
			                                			}
			                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO, OperationConfig.REMOVE)){
			                                        		%>
			                                            <td><span><a onclick="deleteOrganization('<%=bean.getOrganizationId() %>', '<%=bean.getOrganizationName() %>', '<%=bean.getOrganizationType() %>')">删除</a></span></td>
			                                        		<% 	 
			                                        			}
					                                		}else if(organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE)){
					                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO, OperationConfig.UPDATE)){
					                                		%>
			                              			    <td><span><a href="/declare/admin/TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>&organizationId=<%=bean.getOrganizationId()%>">编辑</a></span></td>
			                              			<% 		
			                                			}
			                                			if(adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO, OperationConfig.REMOVE)){
			                                        		%>
			                                      	    <td><span> <a onclick="deleteOrganization('<%=bean.getOrganizationId() %>', '<%=bean.getOrganizationName() %>', '<%=bean.getOrganizationType() %>')">删除</a></span></td>
			                                      			<% 		
			                                        			}
			                                	  		}
                                       			 %> 	
		                                         <%
		                       					 if (organizationType.equals(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING) && adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_RECOMMEND, OperationConfig.CREATE)) {
		                							 %>
		                                          <td><span><a href="RecommendInfoManage.do?recommendId=<%=bean.getOrganizationId()%>&recommendType=<%=RecommendInfoConfig.RECOMMEND_TYPE_BASE%>&province=<%=adminUserHelper.getAdminUserInfo().getProvince()%>">推荐</a></span></td>
		                                          <%} %>  
                                                </tr>
                                                <% rowCount++;} %>
                                              </tbody>
                                            </table>
                                        </div>
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
<script src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
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
 
function deleteOrganization(id,name,type){
	if(confirm("确认删除【"+ name+"】的信息吗?")){ 
		var url = "TrainingOrganizationRemove.do?organizationId="+id+"&organizationType="+type ;
		window.location.href = url  ;
	}
}
</script>
</body>
</html>