<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper"%>
<%
	SsoClientHelper helper = new SsoClientHelper(request, response, false);
%>
<%@ include file="../../include/header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="c0c6d687cf0f11fc3f67955a6bba6d69c211075e"
	name="csrf-token">
<title>在线申报系统</title>
<link href="<%=STATIC_URL%>css/bootstrap.css" rel="stylesheet">
<link href="<%=STATIC_URL%>css/common.css" rel="stylesheet">
<link href="<%=STATIC_URL%>css/other.css" rel="stylesheet">
<link href="<%=STATIC_URL%>css/admin.css" rel="stylesheet">
<link href="<%=STATIC_URL%>css/admin_v2.css" rel="stylesheet">
<link rel="stylesheet" media="screen"
	href="<%=STATIC_URL%>css/es-icon.css">

<!-- 新增样式 -->
<link rel="stylesheet" href="../css/declare.css">
<!-- end -->

<script type="text/javascript"
	src="<%=STATIC_URL%>js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="<%=STATIC_URL%>js/admin_common.js"></script>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="DeclarePortalIndex.do">云上智农管理中心</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class=" navbar-nav navbar-right">
					<li class="dropdown" style="margin-top: 15px;"><a
						href="javascript:;" style="text-decoration: none; cursor: default"
						class="dropdown-toggle"> <%=helper.getUserLoginId()%></a> <span>|</span>
						<a href="http://passport.ngonline.cn/user/UserInfoManage.do"
						class="dropdown-toggle" data-toggle="dropdown">账号设置</a> <span>|</span>
						<a href="http://passport.ngonline.cn/user/Logout.do">退出</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="declare-list">
			<div class="row">
				<div class="col-md-14">
					<div class="declare-item">
						<a
							href="<%=PORTAL_URL%>declare/DeclareInfoManage.do?declareType=2">生产经营型职业农民培育对象申请</a>
					</div>
					<div class="declare-item">
						<a
							href="<%=PORTAL_URL%>declare/DeclareInfoManage.do?declareType=3">专业技能型职业农民培育对象申请</a>
					</div>
					<div class="declare-item">
						<a
							href="<%=PORTAL_URL%>declare/DeclareInfoManage.do?declareType=4">专业服务型职业农民培育对象申请</a>
					</div>
					<div class="declare-item">
						<a
							href="<%=PORTAL_URL%>declare/DeclareInfoManage.do?declareType=1">现代青年农场主计划培育对象申请</a>
					</div>
					<div class="declare-item">
						<a
							href="<%=PORTAL_URL%>declare/DeclareInfoManage.do?declareType=5">
							新型农业经营主体带头人轮训计划培育对象申报</a>
					</div>
					<div class="declare-info">
						<a href="<%=PORTAL_URL%>web/about.html"><i
							class="icon-warning">i</i> 申报说明</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>