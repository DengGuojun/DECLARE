<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	List<TeacherInfoBean> list = (List<TeacherInfoBean>)request.getAttribute("TeacherInfoList");
	Map<Integer, TeacherRegionInfoBean> teacherRegionMap = (Map<Integer, TeacherRegionInfoBean>)request.getAttribute("TeacherRegionMap");;
    List<TeacherMajorTypeBean> teacherMajorTypeList =  (List<TeacherMajorTypeBean>)request.getAttribute("TeacherMajorTypeList") ;
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper") ; 
 	int teacherRange = ParamKit.getIntParameter(request, "teacherRange",0) ; 
 	String country = ParamKit.getParameter(request, "country") ;
 	Map<Integer,String> teacherMajorInfoMap = (Map<Integer,String>)request.getAttribute("TeacherMajorInfoMap") ;
	Map<Integer,String> teacherMajorTypeMap = (Map<Integer,String>)request.getAttribute("TeacherMajorTypeMap") ;
	Map<Integer,Float> teacherEvaluateMap = (Map<Integer,Float>)request.getAttribute("teacherEvaluateMap") ;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]>
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ />
    <![endif]–>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 师资列表</title>
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
                            <%@include file="../include/nurturing_teachers_left.jsp" %>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">
                                        	<% if(teacherRange >=0){ %>
                                        	<% if(teacherRange == 0){
                                        	%>
                                        		入库师资列表
                                        	<% }else if(teacherRange == 2){
                                        	%>
                                        		本级师资列表  
                                        	<%	} 
                                        	%>  
                                        </span>&nbsp;&nbsp;
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                     <form id="" name="" action="TeacherInfoList.do" method="post">
                                     <input type="hidden" name="teacherRange" value="<%=teacherRange %>"	>
                                     <div class="form-group form-horizontal"> 
                                   		<% if(teacherRange == 0){ 
                                   		%>
                                   		<input type="hidden" name="province" id="province" value="<%=ParamKit.getParameter(request, "province","")%>"/>
  	   									<input type="hidden" name="city" id="city" value="<%=ParamKit.getParameter(request, "city","")%>"/>
  	  								    <input type="hidden" name="region" id="region" value="<%=ParamKit.getParameter(request, "region","")%>"/>
                                      	<label>省：</label>
                                          <select class="form-control" name="selectProvince" id="selectProvince" onchange="showCity()"></select>  	 
                                      	<label>市：</label>
                                      	<select class="form-control" name="selectProvince" id="selectCity" onchange="showRegion()" ></select>
                                      	<label>县：</label>
                                      	<select class="form-control" name="selectProvince" id="selectRegion" onchange="setRegion()" ></select>
                                   		<% 	
                                   		}%>
                                      	教师类型:      <select name="teacherType" id="teacherType">
                                      					<option value=""></option>
													<%for(StatusBean<Integer, String> statusBean:TeacherInfoConfig.TEACHER_TYPE_LIST){ %>
													<option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==ParamKit.getIntParameter(request, "teacherType", 0))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
													</select>	  
                                      </div>
                                      <br/>
                                      专业: <select name="majorTypeId" id="majorTypeId" >
                        						<option value=""></option>
					  					<% for(TeacherMajorTypeBean TeacherMajorTypeBean : teacherMajorTypeList){ 
					  					%>
					  					<option value="<%=TeacherMajorTypeBean.getMajorId()%>" 
					  					<%=TeacherMajorTypeBean.getMajorId() == ParamKit.getIntParameter(request,"majorTypeId",0)?"selected":"" %>>
					  					<%=TeacherMajorTypeBean.getMajorName() %>
					  					</option>
					  					<%
					  					} 
					  					%>
									 </select>     
                                        <select name="majorId" id="majorId"></select> 
                                       <div class="form-group form-horizontal">
                                       <label>姓名：</label>
                                       <input type="text" name="teacherName" class="form-control" value="<%=ParamKit.getParameter(request, "teacherName","") %>"/>
                                       </div>
                                       <div class="form-group form-horizontal">
                                       <label>身份证号码：</label>
                                       <input type="text" name="identityNumber" class="form-control" value="<%=ParamKit.getParameter(request, "identityNumber","") %>"/>
                                       </div>
                                       <div class="form-group form-horizontal">
                                       <label>手机号码：</label>
                                       <input type="text" name="phone" class="form-control" value="<%=ParamKit.getParameter(request, "phone","") %>"/>
                                       </div>
                                        <div class="form-group form-horizontal">
                                       <label>主授课程：</label>
                                       <input type="text" name="coursesOffer" class="form-control" value="<%=ParamKit.getParameter(request, "coursesOffer","") %>"/>
                                       </div>
                                		<%
                                			if(teacherRange==0 && adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_ALL, OperationConfig.SEARCH)) {
                                		%>
                                		<input type="submit" value="查 询" class="btn72" />
                                		<%			
                                			}
                                		%>
                                		 
                                		<%
                               			if(teacherRange==2){
                               				if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.SEARCH)) {
                                		%>
                                		<input type="submit" value="查 询" class="btn72" />
                                		<%			
                               				}  
                                       	if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.CREATE)) {
                                       %>
                                      <input type="button" value="新增师资" width="6" onclick="javascript:location.href='TeacherInfoManage.do'" class="btn72" />
                                        <%			
                                          	}	 
                                		}
                                		%>
                                		</form>
                                        <div>
                                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse; width: 97%; ">
                                              <tbody>
                                                <tr class="gv_Head">
                                                  <th style="width:30px;">行号</th>
                                                  <th><a>姓名</a></th>
                                                  <th><a>性别</a></th>
                                                  <th><a>类型</a></th>
                                                  <th><a>专业类型</a></th>
                                                  <th><a>专业</a></th>
                                                  <th><a>所属地区</a></th>
                                                  <th><a>工作单位</a></th>
                                                  <th><a>手机号码</a></th>
                                                  <th><a>综合评分</a></th>
                                                  <th></th>
                                                  <th></th>
                                                  <%
                               					 if (teacherRange == 0  && adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_RECOMMEND, OperationConfig.CREATE)) {
                        							 %>
                                                  <th></th> 
                                                  <%} %>
                                                  <%
                                                   if(teacherRange ==2){
                                                	 %> 
                                                	 <th></th>
                                                	 <%} %>
                                                </tr>
                                                  <%
                                        			int rowCount = 1;
                                                    if(list.size() > 0){ 
									    			for(TeacherInfoBean bean:list){ 
									    		 %>
                                                <tr class="gv_Item">
                                                  <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                                  <td><span><a href="TeacherInfoManage.do?teacherId=<%=bean.getTeacherId()%>"><%=bean.getTeacherName() %></a></span></td>
                                                  <td><span><%=MapKit.getValueFromMap(bean.getTeacherGender(),GenderConfig.GENDER_MAP) %></span></td>
                                                  <td><span><%=MapKit.getValueFromMap(bean.getTeacherType(),TeacherInfoConfig.TEACHER_TYPE_MAP) %></span></td>
                                                  <td><span><%=MapKit.getValueFromMap(bean.getMajorTypeId(),teacherMajorTypeMap) %></span></td>
                                                  <td><span><%=MapKit.getValueFromMap(bean.getMajorId(),teacherMajorInfoMap) %></span></td>
                                                  <td><span><%=bean.getProvince()%><%=bean.getCity()%><%=bean.getRegion()%></span></td>
                                                  <td><span><%=bean.getCompany() %></span></td>
                                                  <td><span><%=bean.getPhone() %></span></td>
                                                  <td><span><%=MapKit.getValueFromMap(bean.getTeacherId(),teacherEvaluateMap) %></span></td>
                                                  <%
                                                   if(teacherRange ==2){
                                                	%>                                                	
                                                  <td><span><a href="/declare/admin/TeacherInfoManage.do?teacherId=<%=bean.getTeacherId()%>&teacherRange=<%=teacherRange%>">编辑</a></span></td>
                                               	  <%   
                                                   }
                                                  %>
                                                  <td><span>
                                                  <a href="/declare/admin/TeacherEvaluateList.do?teacherId=<%=bean.getTeacherId()%>&teacherRange=<%=teacherRange%>">评价详情</a>
                                                  </span></td>
                                                  <td><span>
                                  					<%  
                                  					if(teacherRange == 0  && adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.CREATE)){   
                                  					%>
                                  					 	<a
                                  					 	<%=teacherRegionMap.get(bean.getTeacherId()) !=null 
                                  					 	?"style='display:none;'":"" %>	
                                  					 	onclick="modifyTeacherRegion('<%=bean.getTeacherName() %>', '<%=bean.getTeacherId() %>', 1 ,'<%=teacherRange %>')">存入本级</a>  
                                  					<%} else if(teacherRange == 2 && adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.REMOVE)){%> 
                                  						<a onclick="modifyTeacherRegion('<%=bean.getTeacherName() %>', '<%=bean.getTeacherId() %>', 0 ,'<%=teacherRange %>')">退出本级</a>
                                  					<%}%>  
                                                  </span></td>
                                                  <%
                               					 if (teacherRange == 0  && adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_RECOMMEND, OperationConfig.CREATE)) {
                        							 %>
                        							 <td><span><a href="RecommendInfoManage.do?recommendId=<%=bean.getTeacherId()%>&recommendType=<%=RecommendInfoConfig.RECOMMEND_TYPE_TEACHER%>&province=<%=adminUserHelper.getAdminUserInfo().getProvince()%>">推荐</a></span></td>
                        							 <%} %>
                                                </tr>
                                                <% rowCount++;}} %>
                                              </tbody>
                                            </table>
                                        </div>
                                       <%@ include file="../include/page.jsp" %>
                                    </div>
                                    <% } %>
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
	
	$("#majorTypeId").on("change",function(){
		var majorTypeId = $("#majorTypeId").val() ;	
		if(majorTypeId == ""){
			$("#majorId").empty() ;
			return ;
		}
		var major = $("#majorId") ;
		major.empty() ; 
	    major.append("<option value =''></option>"); 
		$.ajax({
	        type: 'POST',
	        dataType:'json',
	        url: "/declare/admin/TeacherMajorInfoJsonList.do?typeId="+majorTypeId,
	        success: function(data){
	        	//拼接字符串 
	        	if(data != null){
	    			 for (var i = 0; i < data.result.length; i++) {
						var item = data.result[i] ;
						var fixMajorId = "<%= ParamKit.getIntParameter(request, "majorId",0)  %>";
						if(fixMajorId == item.majorId){
							major.append("<option value = '"+item.majorId+"' selected>"+item.majorName+"</option>");
				      	}else{
				      		major.append("<option value = '"+item.majorId+"'>"+item.majorName+"</option>");
				      	}
					}  		
	        	}
	        },
	        error: function(){
	            return;
	        }
	    });
	}) ;
	 $('#majorTypeId').trigger('change') ; 
}) 
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
	sel.append("<option value = ''></option>");
	var fixProvince = $("#province").val();
	if(fixProvince=='国家'){
		sel.append("<option value = '国家' selected>国家</option>");
	}else{
		sel.append("<option value = '国家'>国家</option>");
	}
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	if(fixProvince == item.provinceName){
	      		sel.append("<option value = '"+item.provinceId+"' selected>"+item.provinceName+"</option>");
	      	}else{
	      		sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
	      	}
	    };
	    if(fixProvince != "" && fixProvince !='国家'){
	    		showCity();
	    }
	    
    } else{
   		sel.empty();  
    }
}

function showCity(){
	var provinceId = $("#selectProvince").val();
	var provinceName = $("#selectProvince  option:selected").text();
	$("#province").val(provinceName);
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
	sel.append("<option value = ''></option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixCity = $("#city").val();
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
	if(cityName == ""){
		$("#selectRegion").empty();
		$("#region").val("");
	}else{
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
	
} 

function regionData(data){
	var sel = $("#selectRegion");  
	sel.empty();  
	sel.append("<option value = ''></option>");
	var items=data.content.regionList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixRegion = $("#region").val();
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

/**
 * 处理教师的等级 
 */
function modifyTeacherRegion(name,id,action,teacherRange){
	if(action==1){
	 	if(confirm("确认要将【"+name+"】加入本级吗?")){ 
	 		var url = "TeacherRegionInfoManage.do?action=1&teacherRange="+teacherRange+"&teacherId="+id ;
	 		window.location.href = url ;
	 	}
	}else if(action==0){
		if(confirm("确认要将【"+ name+"】退出本级吗?")){ 
			var url = "TeacherRegionInfoManage.do?action=0&teacherRange="+teacherRange+"&teacherId="+id ;
	 		window.location.href=url ;
	 	}
	}
} 
</script>
</body>
</html>