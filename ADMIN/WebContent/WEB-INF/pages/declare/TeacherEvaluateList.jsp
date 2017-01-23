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
	List<TeacherEvaluateBean> list = (List<TeacherEvaluateBean>)request.getAttribute("TeacherEvaluateList"); 
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList") ;
	Integer teacherId = ParamKit.getIntParameter(request, "teacherId", 0) ;
	Integer teacherRange = ParamKit.getIntParameter(request, "teacherRange", 0) ;
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper")  ; 
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]>
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ />
    <![endif]–>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 教师评价列表</title>
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
    <form action="TeacherEvaluateList.do" method='post'>
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
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">师资评价列表</span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                     	<input type="hidden"  name="teacherId" 
                                     	<%
                                     		if(ParamKit.getIntAttribute(request, "teacherId",0) > 0){
                                     	%>
                                     		value="<%=ParamKit.getIntAttribute(request, "teacherId",0) %>"
                                     	<%		
                                     		}else{
                                     	%>
                                     	value="<%=teacherId %>"
                                     	<% 		
                                     		}
                                     	%>
                                     	value="">
                                         <br/>
                                        <div class="form-group form-horizontal">
                                            <label>培训机构：</label>
                                            <input id="trainingOrganization" name="trainingOrganization" type="text" class="form-control" value="<%=ParamKit.getParameter(request, "trainingOrganization","") %>"/>
                                        </div>
                                        
                                        <div class="form-group form-horizontal">
                                            <label>课程名：</label>
                                            <input type="text" id="className" name="className" value="<%=ParamKit.getParameter(request, "className","") %>" class="form-control" />
                                        </div>
                                        <%
                                        if(teacherRange == 0 ){   //入库师资 
                               			 if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_ALL, OperationConfig.SEARCH)){
                               			%>
                               			 <input type="submit" value="查 询" class="btn72" />
                               			<%
                               			 }
                               			 }else if(teacherRange == 1){   //本辖区师资
                               			 if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_POPEDOM, OperationConfig.SEARCH)){
                               			%>
                               			 <input type="submit" value="查 询" class="btn72" />
                               			<%
                               			 }
                               		 }else if(teacherRange==2){		//本级师资
                               			 if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.SEARCH)){
                               			%>
                               			 <input type="submit" value="查 询" class="btn72" />
                               			<%
                               			 }
                               			}
                                		%> 
                                       <%
                                       if(teacherRange == 2){
                                       	if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.CREATE)) {
                            			%>                            			
                                       <input type="button" value="录 入" class="btn72" onclick="javascript:location.href='/declare/admin/TeacherEvaluateManage.do?teacherId=<%=teacherId %>&teacherRange=<%=teacherRange%>'" /> 
                            			<%
                            			}
                                       }
 										%>
                                       <input type="hidden" name="teacherId" value="">
                                        <div>
                                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse; width: 97%; ">
                                              <tbody>
                                                <tr class="gv_Head">
                                                  <th style="width:30px;">行号</th>
                                                  <th><a>地区</a></th>
                                                  <th><a>培训机构</a></th>
                                                  <th><a>课程</a></th>
                                                  <th><a>学员评价</a></th>
                                                  <th><a>培训时间</a></th>
                                                  <th><a></a></th>
                                                </tr>
                                                 <%
                                        			int rowCount = 1;
									    			for(TeacherEvaluateBean bean:list){ 
									    		 %>
                                                <tr class="gv_Item">
                                                  <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                                  <td><span><%=bean.getTrainingAddress() %></span></td>
                                                  <td><span><%=bean.getTrainingOrganization() %></span></td>
                                                  <td><span><%=bean.getClassName() %></span></td>
                                                  <td><span><%=bean.getEvaluateScore() %></span></td>
                                                  <td><span><%=bean.getTrainingTime() %></span></td>
                                                  <td><span>
                                                     <%
                                                     if(teacherRange == 2){
                                                     		if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.UPDATE)) {
                            						 %>                            			
                                                 <a href="/declare/admin/TeacherEvaluateManage.do?teacherId=<%=teacherId %>&evaluateId=<%=bean.getEvaluateId() %>&teacherRange=<%=teacherRange%>">编辑</a></span></td>            
                            			            <%
                            			            		}
                                                     }
 										            %> 
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
      </form>
    <div class="contents-footer"></div>
	<script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script> 
</body>
</html>