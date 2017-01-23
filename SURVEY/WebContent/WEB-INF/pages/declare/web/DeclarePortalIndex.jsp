<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper" %>
<%
SsoClientHelper helper = new SsoClientHelper(request, response, false);	 
%>
<%@ include file="../../include/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta content="c0c6d687cf0f11fc3f67955a6bba6d69c211075e" name="csrf-token">
		<title>在线申报系统</title>
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
	<div class="container">
		<div class="declare-list">
			<div class="row">
				<div class="col-md-14">
					<div class="declare-item">
						<a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=2">生产经营型职业农民创业资助登记</a>
					</div>
					<div class="declare-item">
						<a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=3">专业技能型职业农民创业资助登记</a>
					</div>
					<div class="declare-item">
						<a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=4">专业服务型职业农民创业资助登记</a>
					</div>
					<div class="declare-item">
						<a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=1">现代青年农场主计划创业资助登记</a>
					</div>
					<div class="declare-item">
						<a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=5"> 新型农业经营主体带头人轮训计划创业资助登记</a>
					</div>
					<div class="declare-info">
						<a href="<%=PORTAL_URL%>web/about.html"><i class="icon-warning">i</i> 登记说明</a>
					</div>
				</div>	
			</div>
		</div>
	</div>
</body>
</html>