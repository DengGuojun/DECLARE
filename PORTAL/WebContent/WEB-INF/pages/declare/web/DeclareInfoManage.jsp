<%@page import="com.lpmas.declare.portal.config.FormInstructionConfig"%>
<%@page import="com.lpmas.declare.portal.config.PortalEntryConfig"%>
<%@page import="com.lpmas.declare.config.DeclareInfoConfig"%>
<%@page import="java.util.Map.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.bean.StatusBean"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.constant.info.*"%>
<%@page import="com.fasterxml.jackson.core.type.*"%>
<%@page import="com.lpmas.declare.bean.DeclareInfoBean"%>
<%@page import="com.lpmas.ow.passport.sso.business.SsoClientHelper" %>

<%
    SsoClientHelper helper = new SsoClientHelper(request, response, false);	
	DeclareInfoBean declareInfoBean = (DeclareInfoBean)request.getAttribute("DeclareInfoBean");
    Map<String, Boolean> moduleFinishedMap = (Map<String, Boolean>)request.getAttribute("ModuleFinishedMap");  
    int declareType = ParamKit.getIntParameter(request, "declareType", 0);
%>
<%@ include file="../../include/header.jsp" %>
<!DOCTYPE html>
<html>
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
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="DeclarePortalIndex.do">云上智农管理中心</a>
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
		<div class="declare-list">
			<div class="row">
				<div class="col-md-14">
				<%for(Entry<String, Boolean> entry : moduleFinishedMap.entrySet()){ %>
				<div class="declare-item">
	            <a href="<%=PORTAL_URL %><%=PortalEntryConfig.DECLARE_MODULE_URL_MAP.get(entry.getKey()) %>?declareType=<%=declareType%>">
	            <%=DeclareInfoConfig.DECLARE_MODULE_MAP.get(entry.getKey()) %></a>
	              <%if(declareInfoBean.getDeclareId()>0){ %>
				     <%if(entry.getValue()){ %>
				         <span class="text-success text-fr">已填写</span>
				     <%}else{ %>
				         <span class="text-danger text-fr">未填写</span>
				     <%} %>		
			      <%}%>
	            </div>
	            <%}%>
	           <%if(declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_SUBMIT)){%>
	           <button class="btn btn-primary btn-block" name="commit" id="commit">提交</button>
	           <%}else if(declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_SUBMIT) 
	        		   || declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE)){ %>
	           <button class="btn btn-primary btn-block" name="commit" id="commit" disabled>已提交审核，请等待审核结果</button>
	           <%}else if(declareInfoBean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)){ %>
	           <button class="btn btn-primary btn-block" name="commit" id="commit" disabled>审核通过</button>
	           <%} else {%>
	           <button class="btn btn-primary btn-block" name="commit" id="commit" >审核驳回，请修改后重新提交</button>
	           <%}%>
			  </div>	
			</div>
		</div>
	</div>
 <section class="popup">
	    <div class="popup-content">
	        <img class="imgAttr" src="<%=STATIC_URL %>images/nodone.png">
	        <span id="disc">请信息填写完整</span>
	        <input type="hidden" id="back_flag" value="">
	    </div>
</section>
</body>
<script>
$(document).ready(function() {
	
	$("#commit").click(
			function() {
				var declareType = '<%=declareType%>';
				var declareId = '<%=declareInfoBean.getDeclareId()%>';
				var url='<%=PORTAL_URL%>/declare/DeclareInfoManage.do?declareType='+declareType+'&declareId='+declareId;
				$.post(url,'',function(data){
					if(data.code=='1'||data.code=='200'){
						alert(data.message);
						window.location = window.location;
					}else{
						showTip("false",data.message);
					}
				});
		});
});
function  showTip(status,text){
    if(status == 'true')
    {
        $('.imgAttr').attr('src','<%=STATIC_URL %>images/done.png');
        $('.popup-content span').css('color','#5DAB2F');
        $('#back_flag').val('true');
    }
    else{
        $('.imgAttr').attr('src','<%=STATIC_URL %>images/nodone.png');
        $('.popup-content span').css('color','#F32E00');
        $('#back_flag').val('false');
    }
    $('.popup-content span').text(text);
    $('.popup').show();
};
$('.popup').on('click', function () {
    $(this).hide();
    if($('#back_flag').val()=='true'){
    	window.location.href="DeclarePortalIndex.do";
    }
});
$('.popup-content').on('click',function(){
    event.stopPropagation();
});
</script>
</html>