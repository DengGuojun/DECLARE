<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper" %>
<%@ include file="../../include/header.jsp" %>
<%
    SsoClientHelper helper = new SsoClientHelper(request, response, false);	
	String instruction = (String)request.getAttribute("Instruction");
%>
<!DOCTYPE html>
<html lang="en">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta content="c0c6d687cf0f11fc3f67955a6bba6d69c211075e" name="csrf-token">
		<title>在线申报系统 - 申报说明</title>
		<link href="<%=STATIC_URL %>css/bootstrap.css" rel="stylesheet">
		<link href="<%=STATIC_URL %>css/common.css" rel="stylesheet">
		<link href="<%=STATIC_URL %>css/other.css" rel="stylesheet">
		<link href="<%=STATIC_URL %>css/admin.css" rel="stylesheet">
		<link href="<%=STATIC_URL %>css/admin_v2.css" rel="stylesheet">
		<link rel="stylesheet" media="screen" href="<%=STATIC_URL %>css/es-icon.css">

		<!-- 新增样式 -->
		<link rel="stylesheet" href="../css/declare.css">
		<!-- end -->

		<script type="text/javascript" src="<%=STATIC_URL %>js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=STATIC_URL %>js/admin_common.js"></script>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="javascript:history.go(-1);">云上智农管理中心</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class=" navbar-nav navbar-right">
					<li class="dropdown" style="margin-top: 15px;">
						<a href="javascript:;" style="text-decoration:none;cursor:default" class="dropdown-toggle"> <%=helper.getUserLoginId()%></a>
						<span>|</span>
						<a href="http://passport.ngonline.cn/user/UserInfoManage.do" class="dropdown-toggle" data-toggle="dropdown">账号设置</a>
						<span>|</span>
						<a href="http://passport.ngonline.cn/user/Logout.do">退出</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="col-md-14">
			<div class="detail-info">
				<h1>填表说明</h1>
				<p>
					<%=instruction %>
				</p>
			</div>
		</div>
	</div>
</body>
</html>