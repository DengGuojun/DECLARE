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
<%@ page import="com.lpmas.declare.business.DeclareInfoHelper"  %>
<%
	List<TrainingMaterialInfoBean> list = (List<TrainingMaterialInfoBean>)request.getAttribute("TrainingMaterialInfoList");

	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
	String province = ParamKit.getParameter(request, "province","");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper") ;
	String materialType = ParamKit.getParameter(request, "materialType", TrainingMaterialConfig.MATERIAL_GENERAL);
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]>
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ />
    <![endif]–>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 教材列表</title>
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
                                 <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
                        		%>
                        		   <div class="detail_left_box detail_box3">
                                    	<a onclick="javascript:location.href='TrainingMaterialInfoList.do?materialType=<%=TrainingMaterialConfig.MATERIAL_GENERAL%>'" >通用知识教材</a>
                                    </div>
                        		<%	 
                        		}
                                %>
                                 <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
                        		%>
                        		    <div class="detail_left_box detail_box3">
                                    	<a onclick="javascript:location.href='TrainingMaterialInfoList.do?materialType=<%=TrainingMaterialConfig.MATERIAL_PROFESSIONAL%>'" >专业技能教材</a>
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
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;"><%=MapKit.getValueFromMap(ParamKit.getParameter(request, "materialType"), TrainingMaterialConfig.MATERIAL_MAP) %></span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <form action="TrainingMaterialInfoList.do?materialType=<%=materialType %>" method='post'> 
                                    	<div class="form-group form-horizontal">
                                    		<label>年份：</label>
                                    		 <select name="trainingYear" id="trainingYear">
        						<option value="">请选择</option>
        						<%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
			                     <option value="<%=InfoYear %>" <%=ParamKit.getIntParameter(request, "trainingYear", 0) == InfoYear? "selected" : ""%>><%=InfoYear %></option>
	                             <%}%>
        					</select>
        					
                                    	</div>                                   	
                                    <%--查询的图书类型 --%>
                                    <input type="hidden" name="materialType" id="materialType" value="<%=ParamKit.getAttribute(request, "materialType") %>">
                                 
                                        <div class="form-group form-horizontal"> 
                                        	<input type="hidden" name="province" id="province" value="<%=ParamKit.getParameter(request, "province","")%>"/>
    	   									<input type="hidden" name="city" id="city" value="<%=ParamKit.getParameter(request, "city","")%>"/>
    	  								    <input type="hidden" name="region" id="region" value="<%=ParamKit.getParameter(request, "region","")%>"/>
                                        	
                                        	<label>省：</label>
                                            <select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()"></select>  	 
                                        	<label>市：</label>
                                        	<select class="form-control" name="selectProvince" id="selectCity" onchange="showRegion()" ></select>
                                        	<label>县：</label>
                                        	<select class="form-control" name="selectProvince" id="selectRegion" onchange="setRegion()" ></select> 
                                        </div>
                                         <br/>
                                        <div class="form-group form-horizontal">
                                            <label>产业：</label>
                                            <input id="industry" name="industry" type="text" class="form-control" value="<%=ParamKit.getParameter(request, "industry","") %>"/>
                                        </div>
                                        
                                        <div class="form-group form-horizontal">
                                            <label>教材名：</label>
                                            <input type="text" id="materialName" name="materialName" value="<%=ParamKit.getParameter(request, "materialName","") %>" class="form-control" />
                                        </div>
                                       
                                       <%
                                       if(materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)){
                       						if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
                       				   %>	 
                       						<input type="submit" value="查 询" class="btn72" />
                       				   <%
                       						}
                       				   }else if(materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)){
                       						if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
                       					%>
                       						<input type="submit" value="查 询" class="btn72" />
                       					<%	 
                       						}
                       				   } 
                                       %>
                                       
                                       <%
                                       if(materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)){
                       						if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.CREATE)) {
                       				   %>	 
                       						 <input type="button" value="录 入" class="btn72" onclick="javascript:location.href='/declare/admin/TrainingMaterialInfoManage.do?materialType=<%=ParamKit.getParameter(request, "materialType",TrainingMaterialConfig.MATERIAL_GENERAL) %>'" /> 
                       				   <%
                       						}
                       				   }else if(materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)){
                       						if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.CREATE)) {
                       					%>
                       						 <input type="button" value="录 入" class="btn72" onclick="javascript:location.href='/declare/admin/TrainingMaterialInfoManage.do?materialType=<%=ParamKit.getParameter(request, "materialType",TrainingMaterialConfig.MATERIAL_PROFESSIONAL) %>'" /> 
                       					<%	 
                       						}
                       				   } 
                                       %>
                                       </form>
                                       <%-- 
                                        <input type="submit" value="导 出" class="btn72" />
                                       --%> 
                                        <div>
                                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse; width: 97%; ">
                                              <tbody>
                                                <tr class="gv_Head">
                                                  <th style="width:30px;">行号</th>
                                                  <th><a>年份</a></th>
                                                  <th><a>省份</a></th>
                                                  <th><a>教材名称</a></th>
                                                  <th><a>组编单位</a></th>
                                                  <th><a>出版社</a></th>
                                                  <th><a>出版时间</a></th>
                                                  <th><a>字数（千字）</a></th>
                                                  <th><a>印张（张）</a></th>
                                                  <th><a>价格（元）</a></th>
                                                  <th><a>产业</a></th>
                                                  <th></th>
                                                  <th></th>
                                                  <th></th>
                                                  <%
                               					 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_MATERIAL_RECOMMEND, OperationConfig.CREATE)) {
                        							 %>
                                                  <th></th> 
                                                  <%} %>
                                                </tr>
                                                 <%
                                        			int rowCount = 1;
									    			for(TrainingMaterialInfoBean bean:list){ 
									    		 %>
                                                <tr class="gv_Item">
                                                  <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                                  <td><span><%=bean.getTrainingYear() %></span></td>
                                                  <td><span>
															<%=bean.getProvince()+bean.getCity()+bean.getRegion() %>
															
													  </span></td>
                                                  <td><span><%=bean.getMaterialName() %></span></td>
                                                  <td><span><%=bean.getCompileOrganization() %></span></td>
                                                  <td><span><%=bean.getPublishingCompany() %></span></td>
                                                  <td><span><%=bean.getPublishingYeah()+"-"+bean.getPublishingMonth() %></span></td>
                                                  <td><span><%=bean.getWordQuantity() %></span></td>
                                                  <td><span><%=bean.getPaperQuantity() %></span></td>
                                                  <td><span><%=bean.getPrice() %></span></td>
                                                  <td><span><%=bean.getIndustry() %></span></td>
                                                  <td><a href="<%=bean.getLink()%>" target="_blank">详细信息</a></td>
                                                  
			                                       <%
			                                       if(materialType.equals(TrainingMaterialConfig.MATERIAL_GENERAL)){
			                       						if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.UPDATE)) {
			                       				   %>	 <td>
			                       						 <a href="/declare/admin/TrainingMaterialInfoManage.do?materialId=<%=bean.getMaterialId() %>&materialType=<%=TrainingMaterialConfig.MATERIAL_GENERAL %>" >编辑</a></td>
			                       				   <%
			                       						}
			                       						if (adminUserHelper.checkPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.REMOVE)) {
			                                				   %>	 
			                                						 <td><a onclick="deleteTrainingMaterial('<%=bean.getMaterialName() %>', '<%=bean.getMaterialId() %>', '<%=TrainingMaterialConfig.MATERIAL_GENERAL %>')">删除</a></td>
			                                				   <%
			                                			}	
			                       				   }else if(materialType.equals(TrainingMaterialConfig.MATERIAL_PROFESSIONAL)){
			                       						if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.UPDATE)) {
			                       					%>
			                       						  <td><a href="/declare/admin/TrainingMaterialInfoManage.do?materialId=<%=bean.getMaterialId() %>&materialType=<%=TrainingMaterialConfig.MATERIAL_PROFESSIONAL %>" >编辑</a></td>
			                       					<%	 
			                       						}
			                       						
			                       						if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.REMOVE)) {
			                               					%>
			                               						 <td><a onclick="deleteTrainingMaterial('<%=bean.getMaterialName() %>', '<%=bean.getMaterialId() %>', '<%=TrainingMaterialConfig.MATERIAL_PROFESSIONAL %>')">删除</a></td>
			                               					<%	 
			                               				}
			                       						
			                       				   } 
			                                       %> 
			                                       <%
                               					 if ( adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_MATERIAL_RECOMMEND, OperationConfig.CREATE)) {
                        							 %>
                        							 <td><span><a href="RecommendInfoManage.do?recommendId=<%=bean.getMaterialId()%>&recommendType=<%=RecommendInfoConfig.RECOMMEND_TYPE_MATERIAL%>&province=<%=adminUserHelper.getAdminUserInfo().getProvince()%>&materialType=<%=materialType%>">推荐</a></span></td>
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
	var sel = $("#selectProvince"); 
	sel.empty();   

	var city = $("#selectCity") ;
	city.empty() ;  //清除城市下拉框
	
	var region = $("#selectRegion") ;
	region.empty() ;  //清除区域下拉框
	
	sel.append("<option value =''>全部</option>");
	
    var selected = <%=province.equals(DeclareAdminConfig.COUNTRY_STR)?"selected":"''" %>;
	sel.append("<option value ='国家'"+ selected +">国家</option>");
	
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixProvince = "<%=ParamKit.getParameter(request, "province","")%>";
	      	if(fixProvince == item.provinceName){
	      		sel.append("<option value = '"+item.provinceId+"' selected>"+item.provinceName+"</option>");
	      	}else{
	      		sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
	      	}
	    };
	    if(fixProvince != ""){
	    		showCity();
	    }
    } else{
   		sel.empty();  
    }
}

function showCity(){
	var provinceId = $("#selectProvince").val();
	var provinceName = $("#selectProvince  option:selected").text();
	$("#province").val("") ; //清空隐藏域的值
	$("#city").val("") ; //清空隐藏域的值
	$("#region").val("") ; //清空隐藏域的值
	
	if("全部" != provinceName){
		$("#province").val(provinceName);//设置隐藏域的值 province
	}
	
	if("国家" == provinceName ){
		
		var city = $("#selectCity") ;
		city.empty() ;  //清除城市下拉框
		
		var region = $("#selectRegion") ;
		region.empty() ;  //清除区域下拉框
		
		return ;
	}
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
	var sel = $("#selectCity");  
	sel.empty();  
	
	var region = $("#selectRegion") ;
	region.empty() ;  //清除区域下拉框
	
	sel.append("<option value = ''></option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixCity = "<%=ParamKit.getParameter(request, "city","")%>";
	      	if(fixCity == item.cityName){
	      		sel.append("<option value = '"+item.cityId+"' selected>"+item.cityName+"</option>");
	      	}else{
	      		sel.append("<option value = '"+item.cityId+"'>"+item.cityName+"</option>");
	      	}
	    };
	    if(fixCity != ""){
	    		showRegion();
	    }
    } else{
   		sel.empty();  
    }
}

function showRegion(){
	var cityId = $("#selectCity").val();
	var cityName = $("#selectCity  option:selected").text();
	$("#city").val(cityName);
	$("#region").val("") ; 
	
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
	sel.append("<option value = ''></option>");
	var items=data.content.regionList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixRegion = "<%=ParamKit.getParameter(request, "region","")%>";
	      	if(fixRegion == item.regionName){
	      		sel.append("<option value = '"+item.regionId+"' selected>"+item.regionName+"</option>");
	      		isFix = true;
	      	}else{
	      		sel.append("<option value = '"+item.regionId+"'>"+item.regionName+"</option>");
	      	}
	    };
    } else{
   		sel.empty();  
    }
}
function setRegion(){
	var regionName = $("#selectRegion  option:selected").text();
	$("#region").val(regionName);
}

function deleteTrainingMaterial(name,id,kind){
	if(confirm("确认删除教材【"+ name+"】吗?")){ 
		var url = "TrainingMaterialRemove.do?materialId="+id+"&materialType="+kind ;
		window.location.href = url  ;
	}
}
</script>
</body>
</html>