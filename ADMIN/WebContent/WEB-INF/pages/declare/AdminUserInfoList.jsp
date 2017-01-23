<%@page import="com.lpmas.constant.user.GenderConfig"%>
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
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	PageResultBean<AdminUserInfoBean> result = (PageResultBean<AdminUserInfoBean>)request.getAttribute("UserList");
	List<AdminUserInfoBean> list = result.getRecordList();
	Map<Integer, String> userRoleNameMap = (Map<Integer, String>)request.getAttribute("UserRoleNameMap");

	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
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
    <title>新型农民职业培训系统 — 用户管理</title>
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
                                  <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">查看人员</span></div>
                                 <div class="right_bg" style="padding-left: 5px;">
                                	 <form name="formSearch" method="post" action="AdminUserInfoList.do">
                                  	<div class="form-group form-horizontal">
                                  	<label>联系人：</label>
									<input type="text" name="adminUserName" id="adminUserName" value="<%=ParamKit.getParameter(request, "adminUserName", "") %>" size="20"/>
					  			   </div>
					  			<input type="submit" value="查 询" class="btn72" />
					  			</form>
                                  <div>
                                    <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                      <tbody>
                                        <tr class="gv_Head">
                                          <th style="width:30px;">行号</th>
                                          <th>联系人</th>
									      <th>性别</th>
									      <th>出生日期</th>
									      <th>部门/职务</th>
									      <th>联系电话</th>
									      <th>手机</th>
									      <th>传真</th>
									      <th>电子邮箱</th>
									      <th>登录名</th>
									      <th>登录名状态</th>
									      <th>角色</th>
									      <th></th>
                                        </tr>
                                        <%
                                        int rowCount = 1;
									    for(AdminUserInfoBean bean:list){ 
									    %>
                                        <tr class="gv_Item">
                                          <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                          <td><%=bean.getAdminUserName() %></td>
								   	   	 <td><%=GenderConfig.GENDER_MAP.get(bean.getAdminUserGender()) != null ? GenderConfig.GENDER_MAP.get(bean.getAdminUserGender()):"" %></td>
								   	   	 <td><%=bean.getAdminUserBirthday() != null ? bean.getAdminUserBirthday() : "" %></td>
								   	   	 <td><%=bean.getAdminUserPose() %></td>
								   	   	 <td><%=bean.getAdminUserPhone() %></td>
								   	   	 <td><%=bean.getAdminUserTelephone() %></td>
								   	   	 <td><%=bean.getAdminUserFax() %></td>
								   	   	 <td><%=bean.getAdminUserEmail()%></td>
								      	 <td><%=bean.getLoginId().split(DeclareAdminConfig.ADMIN_LOGIN_SEPARATOR)[1] %></td>
								      	 <td><%=MapKit.getValueFromMap(bean.getStatus(),Constants.STATUS_MAP) %></td>
								      	 <td><%=MapKit.getValueFromMap(bean.getUserId(), userRoleNameMap) %></td>
								      	 <td><a href="AdminUserInfoManage.do?userId=<%=bean.getUserId() %>">编辑</a></td>
                                        </tr>
                                        <% rowCount++;} %>
                                      </tbody>
                                    </table>
                                  </div>
								<%@ include file="../include/page.jsp" %>
                                </div>
                                <ul class="page_info">
								<li class="page_left_btn">
								<%if(adminUserHelper.hasPermission(DeclareAdminResource.ADMIN_USER, OperationConfig.CREATE)){ %>
								 <input type="button" name="button" id="button" value="新建" onclick="javascript:location.href='AdminUserInfoManage.do?groupId=<%=ParamKit.getIntParameter(request, "groupId", 0) %>'">
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
