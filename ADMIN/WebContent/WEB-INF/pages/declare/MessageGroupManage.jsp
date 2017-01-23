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
	MessageGroupBean bean = (MessageGroupBean)request.getAttribute("MessageGroupBean");
	AdminUserHelper adminUserHelper =(AdminUserHelper) request.getAttribute("adminUserHelper") ;
	
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!–[if lte IE 8]> 
  <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
  <![endif]–> 
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
  <title>新型农民职业培训系统 — 通讯录管理</title>
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
  <form name="formData" id="formData" method="post" action="MessageGroupManage.do" onsubmit="javascript:return checkForm('formData');" >
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
				   <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">
                                        <% if(bean.getGroupId() > 0) {
                                       	%>
                                       	重命名通讯录
                                        <% } else{
                                        %>
                                        新增通讯录
                                        <% 	
                                        }%>
                                        </span>
				  </div>
				    <div class="right_bg" style="padding-left: 5px;">
				    <input type="hidden" name="groupId" value="<%=bean.getGroupId()%> ">
				    <input type="hidden" name="ownerId" value="<%=bean.getOwnerId()%> ">
				    <input type="hidden" name="status" value="<%=Constants.STATUS_VALID %>" /> 
				   	<table class="table_comment">
        			<tbody>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>通讯录名称</td>
        					<td>
        					<input id="groupName" name="groupName" type="text" class="form-control" value="<%=bean.getGroupName() %>" checkStr="通讯录名称;txt;true;;100"/>
        					 
        					</td>
        				</tr>
        				
        			</tbody>
        		</table>
        		<div class="dialog_ft text_center">
        		<input type="submit" class="btn72" value="保 存">&nbsp;&nbsp;	
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
</body>
</html>