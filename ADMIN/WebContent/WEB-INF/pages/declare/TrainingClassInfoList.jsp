<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
    AdminUserHelper adminUserHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	List<TrainingClassInfoBean> list = (List<TrainingClassInfoBean>)request.getAttribute("TrainingClassInfoList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
	List<MajorTypeBean> majorTypeList = (List<MajorTypeBean>)request.getAttribute("majorTypeList");
	Integer trainingType = (Integer)request.getAttribute("trainingType");
	Map<Integer, String> trainingOrganizationInfoMap= (Map<Integer, String>)request.getAttribute("trainingOrganizationInfoMap");
	List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = (List<TrainingOrganizationInfoBean>)request.getAttribute("trainingOrganizationInfoList");
	Map<Integer, String> majorInfoMap= (Map<Integer, String>)request.getAttribute("majorInfoMap");
	String fixProvince = (String)request.getAttribute("FixProvince");
	String fixCity = (String)request.getAttribute("FixCity");
	String fixRegion = (String)request.getAttribute("FixRegion");
	String queryProvince = ParamKit.getParameter(request, "queryProvince", "");
	String queryCity = ParamKit.getParameter(request, "queryCity", "");
	String queryRegion = ParamKit.getParameter(request, "queryRegion", "");
	String organizationId = ParamKit.getParameter(request, "organizationId", "");
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
    <title>新型农民职业培训系统 — 培训班信息</title>
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
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
                            <div class="detail_left">
                                <div class="detail_left_top"></div>
                                <div id="listItem" class="detail_left">
                                <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.SEARCH)) {
                        		%>
                        		     <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER %>">现代青年农场主培训</a>
                                    </div>
                                    <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER %>">新型农业经营主体带头人</a>
                                    </div>
                                    <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER %>">生产经营型职业农民培训</a>
                                    </div>
                                    <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER %>">专业技能型职业农民培训</a>
                                    </div>
                                    <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER %>">专业服务型职业农民培训</a>
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
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;"><%=MapKit.getValueFromMap(trainingType, DeclareInfoConfig.DECLARE_TYPE_MAP) %>培训班级信息</span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <form name="formSearch" method="post" action="TrainingClassInfoList.do">
                                    <input type="hidden" name="trainingType" id="trainingType" value="<%=trainingType %>"/>	
                                        <div class="form-group form-horizontal">
                                            <label>年份：</label>
                                           <select class="form-control" name="trainingYear" id="trainingYear" >
                                            <option value="">请选择</option>
					                         <%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
						                     <option value="<%=InfoYear %>" <%=ParamKit.getIntParameter(request, "trainingYear", 0) == InfoYear? "selected" : ""%>><%=InfoYear %></option>
				                             <%}%>	
                                           </select>
                                        </div>
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
                                        <br/>
                                        <div class="form-group form-horizontal">
                                            <label>单位名称：</label>
                                            <select name="organizationId" id="organizationId" class="form-control">
                                            <option value="" ></option>
										    <%for (TrainingOrganizationInfoBean trainingOrganizationInfoBean : trainingOrganizationInfoList) {%>
										    <option value="<%=trainingOrganizationInfoBean.getOrganizationId() %>" <%=trainingOrganizationInfoBean.getOrganizationId() == ParamKit.getIntParameter(request, "organizationId", 0) ? "selected" : ""%>><%=trainingOrganizationInfoBean.getOrganizationName() %></option>
											<%}%>
                                            </select>
                                        </div>
                                        <div class="form-group form-horizontal">
                                            <label>培训班名称：</label>
                                            <input type="text" class="form-control" name="className" id="className" value="<%=ParamKit.getParameter(request, "className", "") %>"/>
                                        </div>
                                        <div class="form-group form-horizontal">
                                            <label>培训产业：</label>
                                            <select name="majorTypeId" id="majorTypeId" class="form-control">
										    <option value="" ></option>
										    <%for (MajorTypeBean majorTypeBean : majorTypeList) {%>
										    <option value="<%=majorTypeBean.getMajorId() %>" <%=majorTypeBean.getMajorId() == ParamKit.getIntParameter(request, "majorTypeId", 0) ? "selected" : ""%>><%=majorTypeBean.getMajorName() %></option>
											<%}%>
											</select>
                                            -
                                            <input type="hidden" class="major" id= "major" value="<%=ParamKit.getParameter(request, "majorId", "") %>">
	                                        <select class="major form-control" id="majorId" name="majorId" ></select>
                                        </div>
                                        <input type="submit" value="查 询" class="btn72" />
                                         <%
						                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.CREATE)) {
					                     %>
                                        <input type="button" value="录入班级" onclick="javascript:location.href='TrainingClassInfoManage.do?trainingType=<%=trainingType %>'" class="btn72" />
                                       <%}%>
                                        <input type="button" value="导出" class="btn72" id="exportClassInfo"/>
                                       </form>
                                        <div>
                                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                                <tbody>
                                                    <tr class="gv_Head">
                                                        <th style="width:30px;">行号</th>
                                                        <th>培训机构</th>
												        <th>培训班</th>
												        <th>培训对象</th>
												        <th>培训产业</th>
												        <th>数据状态</th>
												        <th>培训人数</th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                    </tr>
                                                    <% int i=0;
												    for(TrainingClassInfoBean bean:list){%> 
												    <tr class="gv_Item">
												      <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=(PAGE_BEAN.getCurrentPageNumber()-1)*DeclareAdminConfig.DEFAULT_PAGE_SIZE + ++i %></span></td>
												      <td><span><%=MapKit.getValueFromMap(bean.getOrganizationId(), trainingOrganizationInfoMap) %></span></td>
												      <td><span id="class_<%=bean.getClassId()%>"><%=bean.getClassName() %></span></td>
												      <td><span><%=MapKit.getValueFromMap(bean.getTrainingType(), DeclareInfoConfig.DECLARE_TYPE_MAP) %></span></td>
												      <td><span><%=StringKit.isValid(MapKit.getValueFromMap(bean.getMajorId(), majorInfoMap))?MapKit.getValueFromMap(bean.getMajorId(), majorInfoMap):bean.getTrainingPose()%></span></td>
												      <td><span><%=MapKit.getValueFromMap(bean.getClassStatus(), TrainingClassInfoConfig.ClASS_STATUS_MAP) %></span></td>
												      <td><span><%=bean.getClassPeopleQuantity() %></span></td>
												      <td>
												       <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.UPDATE)) {
									                   %>
												      	<a style="cursor:pointer;" href="/declare/admin/TrainingClassInfoManage.do?classId=<%=bean.getClassId() %>">编辑</a> 
												       <%}%>
												      </td>
												      <td>
												      <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.SEARCH)) {
									                   %>
												      	<a style="cursor:pointer;" href="/declare/admin/TrainingClassUserInfoManage.do?classId=<%=bean.getClassId() %>">学员管理</a> 
												       <%}%>
												      </td>
												      <td>
												      <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.SEARCH)) {
									                   %>
												      	<a style="cursor:pointer;" href="/declare/admin/TrainingClassItemManage.do?classId=<%=bean.getClassId() %>">课程安排</a> 
												       <%}%>
												      </td>
												      <td>
												      <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.SEARCH)) {
									                   %>
												      	<a style="cursor:pointer;" href="/declare/admin/TrainingClassUserExamManage.do?classId=<%=bean.getClassId() %>">考核记录</a> 
												       <%}%>
												      </td>
												      <td>
												      <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.REMOVE) && adminUserHelper.getAdminUserId() == bean.getCreateUser()) {
									                   %>
												      	<a style="cursor:pointer;" onclick="deleteClass('<%=bean.getClassId()%>')" >删除</a> 
												       <%}%>
												      </td>
												      <td>
												      	<a style="cursor:pointer;" href="/declare/admin/TrainingClassEvaluateList.do?classId=<%=bean.getClassId() %>" >考核评教</a> 
												      </td>
												      <td>
												      <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.APPLY)
										                		 &&!bean.getClassStatus().equals(TrainingClassInfoConfig.CLASS_STATUS_APPROVED)
										     					   && !bean.getClassStatus().equals(TrainingClassInfoConfig.CLASS_STATUS_SUBMITTED)) {
									                   %>
												      	<a style="cursor:pointer;" href="/declare/admin/TrainingClassInfoCommit.do?classId=<%=bean.getClassId() %>&action=<%=TrainingClassInfoConfig.COMMIT_ACTION_SUBMIT%>">上传</a> 
												      <%}%>
												      <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.SUBMIT)&& bean.getClassStatus().equals(TrainingClassInfoConfig.CLASS_STATUS_SUBMITTED)) {
									                   %>
												      	<a style="cursor:pointer;" href="/declare/admin/TrainingClassInfoCommit.do?classId=<%=bean.getClassId() %>&action=<%=TrainingClassInfoConfig.COMMIT_ACTION_REPORT%>">上报</a>
												      <%}%>
												      <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.REJECT)&& bean.getClassStatus().equals(TrainingClassInfoConfig.CLASS_STATUS_APPROVED)) {
									                   %>
												      	<a style="cursor:pointer;" href="/declare/admin/TrainingClassInfoCommit.do?classId=<%=bean.getClassId() %>&action=<%=TrainingClassInfoConfig.COMMIT_ACTION_RESUBMIT%>">重报</a>
												      <%}%>
												      </td>
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
	
	   $("#majorTypeId").on("change",function(){
		      var typeId = $(this).find("option:selected").val();
		      seletCate(typeId,$(this));
		  });
		  $("#majorTypeId").trigger('change');

		  function seletCate(typeId,element){
			  $.ajax({
	 	        type: 'get',
	 	        url: "/declare/admin/MajorInfoJsonList.do?typeId="+typeId,
	 	        dataType: 'json',
	 	        success: function(data){
	 	        	$("#majorId").empty();
	 	        	child ="";
	 	        	if(data==null) {
	 	        		child.append("<option value = '' >"+""+"</option>");
		          	}else {
	 	        		var majors=data.result;	 	        		
	               for(var j=0;j< majors.length;j++){
	             	 	var originalId = element.parent().parent().find(".major").val();
	             	 	if(majors[j].majorId == originalId){
	             	 		child += "<option value='" + majors[j].majorId  +"' selected>"+ majors[j].majorName + "</option>"
	             	 	}else{
	             	 		child += "<option value='" + majors[j].majorId  +"'>"+ majors[j].majorName + "</option>"
	             	 	}
	              }
		          	}
	              $("#majorId").html(child);
	 	        },
	 	        error: function(){
	 	            return;
	 	        }
			});
		  }		     
	     
});
$('#exportClassInfo').on('click',function(){
	  var trainingType = $('#trainingType').val();
	  var trainingYear = $('#trainingYear').val();
      var organizationId = $('#organizationId').val();
      var className = $('#className').val();
      var queryProvince = $('#queryProvince').val();
      var queryCity = $('#queryCity').val();
      var queryRegion = $('#queryRegion').val();
      var majorTypeId = $('#majorTypeId').val();
      var majorId = '';
      if($('#majorId').val() != null){
    	  var majorId = $('#majorId').val();
      }
      var pageNum = $('#pageNum').val();
      var pageSize = $('#pageSize').val();
    window.location.href='TrainingClassInfoExport.do?trainingType='+trainingType+'&trainingYear='+trainingYear+'&organizationId='+organizationId+'&className='+className+'&queryProvince='+queryProvince+'&queryCity='+queryCity+'&queryRegion='+queryRegion+'&majorTypeId='+majorTypeId+'&majorId='+majorId+'&pageNum='+pageNum+'&pageSize='+pageSize;

});
function deleteClass(id) {
	var classInfo = $('#class_'+id).html();
	if(confirm("确定要删除班级【"+classInfo+"】吗?")){
		var url = "/declare/admin/TrainingClassInfoCommit.do?action=<%=TrainingClassInfoConfig.COMMIT_ACTION_DELETE%>&classId="+id;
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