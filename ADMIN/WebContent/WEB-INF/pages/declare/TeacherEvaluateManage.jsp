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
<%
    TeacherEvaluateBean bean = (TeacherEvaluateBean)request.getAttribute("TeacherEvaluateBean"); 	
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper") ; 
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	Integer teacherRange = ParamKit.getIntParameter(request, "teacherRange", 0) ;
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]>
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ />
    <![endif]–>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 教师评价管理</title>
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
    <form id="formData"  name="formData" action="TeacherEvaluateManage.do" method='post' onsubmit="javascript:return checkThisForm('formData');">
       <input type='hidden' id="teacherRange" name="teacherRange" value="<%=teacherRange%>">
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
                                        <% if(bean.getEvaluateId() > 0) {
                                       	%>
                                       	修改教师评价
                                        <% } else{
                                        %>
                                                                                                          增加教师评价
                                        <% 	
                                        }%>
                                        </span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    
                                  	<input type="hidden" name="evaluateId" value="<%=bean.getEvaluateId()%>">
                                  	<input type="hidden"  value="<%=Constants.STATUS_VALID%>" name="status" />
        		<table class="table_comment">
        			<tbody>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>课程</td>
        					<td>
        						 <input id="className" name="className" type="text" class="form-control" value="<%=bean.getClassName() %>" checkStr="课程名称;txt;true;;100"/>
        					</td>
        				</tr>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>培训机构</td>
        					<td>
        						 <input id="trainingOrganization" name="trainingOrganization" type="text" class="form-control" value="<%=bean.getTrainingOrganization() %>" checkStr="培训机构;txt;true;;100"/>
        					</td>
        				</tr>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>培训时间</td>
        					<td>
        				  <input type="text" name="trainingTime" id="trainingTime"
			 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'2008-01-01',maxDate:'2020-01-01'})"
			 size="20"  checkStr="培训时间;txt;true;;100"
			 value="<%=bean.getTrainingTime() == null ? "" : bean.getTrainingTime()%>"  />
        					</td>
        				</tr>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>教师ID</td>
        					<td>
        						 <input id="teacherId" name="teacherId" type="text" class="form-control" readonly="readonly" value="<%=bean.getTeacherId()==0?"":bean.getTeacherId() %>" checkStr="教师Id;digit;true;;100"/>
        					</td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>学员评价</td>
        					<td>
        						 <input id="evaluateScore" name="evaluateScore" type="text" class="form-control" value="<%=bean.getEvaluateScore()==0?"":bean.getEvaluateScore() %>" checkStr="学员评价;num;true;;100"/>
        					</td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>培训地点</td>
        					<td> <input id="trainingAddress" name="trainingAddress" type="text" class="form-control" value="<%=bean.getTrainingAddress() %>" checkStr="培训地点;txt;true;;100"/></td>
        				</tr>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>教师评语</td>
        					<td><input id="teacherComment" name="teacherComment" type="text" class="form-control" value="<%=bean.getTeacherComment() %>" checkStr="教师评语;txt;true;;100"/></td>
        				</tr>  
        			</tbody>
        		</table> 
        		<p class="tips">学员评价分数满分为5分,填写分数请在0-5分之间,可保留一位小数</p>
        	<div class="dialog_ft text_center">
        	  <%if (adminUserHelper.checkPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.CREATE)) {
              %>                            			
                 <input type="submit" class="btn72" value="保 存">
             <%
              }
 			 %> 
        		&nbsp;&nbsp;	
        		<input class="btn72" type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">
        	</div>
        </div>
     </div>
   </div>
 </td>
</tr>
</tbody>
</table>
</div>
</form>
  
    <div class="contents-footer"></div>
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
   <script>
	function checkThisForm(name){
		var evaluateScore = $("#evaluateScore").val() ;
		var re = /^\d+(\.\d)?$/ ;
		if(!isDouble(evaluateScore)){
			alert("学员评价不能为空且必须是数字") ;
			$('#identityNumber').focus() ;
			return false ; 
		}else if(evaluateScore<0 || evaluateScore>5 ){
			alert("学员评价的范围是0-5") ;
			$('#evaluateScore').focus() ;
			return false ;
		}else if(!re.test(evaluateScore)){
			alert("学员评价的范围只允许小数点后一位") ;
			$('#evaluateScore').focus() ;
			return false ;
		}
		return checkForm(name) ;
	}
	</script>
</body>
</html>