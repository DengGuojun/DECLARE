<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	List<AdminRoleInfoBean> list = (List<AdminRoleInfoBean>)request.getAttribute("RoleList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 角色管理</title>
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
<body class="article_bg">
<%@include file="../nav/navigation.jsp" %>
    <div class="warpDefaultMgr">
        <table class="table_wrp">
            <tbody>
                <tr>
                    <td style="width: 191px; background-color: #f2f2f2;" valign="top">
                        <%@include file="../include/project_settings_left.jsp" %>
                    </td>
                    <td valign="top">
                        <div class="detail_right">
                            <div class="content_wrap">
                                <div class="detail_right_title h1 white">
                                  <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">角色管理</span></div>
                                 <div class="right_bg" style="padding-left: 5px;">
                                  <div>
                                    <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                      <tbody>
                                        <tr class="gv_Head">
                                          <th style="width:30px;">行号</th>
                                          <th>角色名称</th>
									      <th>角色描述</th>
									      <th>状态</th>
									      <th></th>
                                        </tr>
                                        <%
                                        int rowCount = 1;
									    for(AdminRoleInfoBean bean:list){ 
									    %>
                                        <tr class="gv_Item">
                                          <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                          <td><%=bean.getRoleName() %></td>
								   	   	 <td><%=bean.getMemo() %></td>
								   	   	 <td><%=MapKit.getValueFromMap(bean.getStatus(),Constants.STATUS_MAP) %></td>
								      	 <td><a href="AdminRoleInfoManage.do?roleId=<%=bean.getRoleId() %>">编辑</a></td>
                                        </tr>
                                        <% rowCount++;} %>
                                      </tbody>
                                    </table>
                                  </div>
								<%@ include file="../include/page.jsp" %>
                                </div>
                                <ul class="page_info">
								<li class="page_left_btn">
									<%if(adminUserHelper.hasPermission(DeclareAdminResource.ADMIN_ROLE, OperationConfig.CREATE)){ %>
								 	<input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='AdminRoleInfoManage.do'">
								 	<%} %>
								</li>
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
</html>