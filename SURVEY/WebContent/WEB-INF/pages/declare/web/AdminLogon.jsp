<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lpmas.framework.web.*"  %>
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
		<link type="text/css" rel="stylesheet" href="http://passport.ngonline.cn/lpmas/passport/web/v0/css/global.css">
    		<link type="text/css" rel="stylesheet" href="http://passport.ngonline.cn/lpmas/passport/web/v0/css/index.css">
    		<link type="text/css" rel="stylesheet" href="http://passport.ngonline.cn/lpmas/passport/web/v0/css/other.css">
		<link rel="stylesheet" media="screen" href="<%=STATIC_URL %>css/es-icon.css">
		<style>
    		.container{padding-left: 30px;padding-right: 30px;margin: auto}
				#content{background: url(/lpmas/passport/web/v0/images/index-bg.png) no-repeat 100% 68%/cover;}
				.member-box{padding-top: 2rem;width:390px;padding-bottom: 1rem}
				.member-btn{margin: 2.5rem 0}
				.welcome-text{text-align:left;color:#5DAB2F;font-size:18px;margin-bottom:20px}
				.index{margin: 0;padding: 0}
				#header{background-color: #222;border-color: #080808;left: 0;position: fixed;right: 0;z-index: 1030;min-height: 50px;top:0}
				.navbar-brand{text-decoration: none;color: #999;    float: left;padding: 15px;font-size: 18px;line-height: 20px;}
    		</style>


		<script type="text/javascript" src="<%=STATIC_URL %>js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="<%=STATIC_URL %>js/admin_common.js"></script>
	</head>
<body class="index">
<div class="navbar navbar-inverse navbar-fixed-top" id="header">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="">创业资助登记系统管理中心</a>
        </div>
    </div>
</div>
<div id="content" style="margin-top:50px;">
    <div class="index-contain">
        <section class="container member-box">
           <form class="declareInfo-cont form-horizontal" id="formData" name="formData"  method="post"  onsubmit="javascript:return checkForm('formData');">
				<p class="welcome-text"><span>管理后台</span></p>
                <div class="form-row">
                    <div class="form-th">登录名</div>
                    <input type="tel"  placeholder="请输入登录名" value="" name="loginId" id="loginId"  data-verify="loginName" checkStr="用户名;txt;true;;16" >
                    <span class="form-set" style="display: none;"><img src="http://passport.ngonline.cn/lpmas/passport/web/v0/images/cancel_gray.png" alt="" class="imgbox" id="cancel"></span>
                </div>
                <div class="error-info" style="display: none;">手机号码错误,请重新输入</div>
                <div class="form-row">
                    <div class="form-th">密码</div>
                    <input type="password" placeholder="请输入密码" value="" name="loginPassword" id="loginPassword"  data-verify="password" checkStr="密码;txt;true;;16">
                    <span class="form-set"></span>
                </div>
    <div class="member-btn">
        <button  class="m-btn" id="loadBtn" type="submit">登录</button>
    </div>
        
    <input type="hidden" value="http://declare.ngonline.cn/declare/DeclarePortalIndex.do?owpf=1" name="target" id="target">
    <input type="hidden" id="sessionId" name="sessionId" value="aaaEYYhUrqxbg_5CMGpuv">
    </form>
    </section>

</div>
</div>
<footer>
    <p>指导单位：中央农业广播电视学校</p>
    <p>运营单位：农业部科技教育司 农业部农民科技教育培训中心Copyright (C) 2015-2016 www.lpmas.com All Right Reserved. 京ICP备05072014</p>
</footer>
<script>
    var win_h= window.innerHeight;
    var header_h=document.getElementById('header').clientHeight;
    console.log(win_h,header_h);
    document.getElementById('content').style.height = win_h-header_h+'px';
</script>
</body>

</html>
