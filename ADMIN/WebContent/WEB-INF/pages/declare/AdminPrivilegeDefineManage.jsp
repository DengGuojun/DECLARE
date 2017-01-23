<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%
AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
List<AdminPrivilegeDefineBean> list = (List<AdminPrivilegeDefineBean>)request.getAttribute("PrivilegeList");
List<AdminOperationInfoBean> operationList = (List<AdminOperationInfoBean>)request.getAttribute("OperationList");
AdminResourceInfoBean resourceInfoBean = (AdminResourceInfoBean)request.getAttribute("ResourceInfo");
HashMap<Integer, AdminPrivilegeDefineBean> privilegeMap = (HashMap<Integer, AdminPrivilegeDefineBean>)request.getAttribute("PrivilegeMap");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!–[if lte IE 8]> 
 <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
 <![endif]–> 
 <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
 <%@ include file="../include/header.jsp" %>
    <title>新型农民职业培训系统 — 权限定义管理</title>
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/SlideMenuStyle.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/default.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/StyleBlue.css" type="text/css" rel="Stylesheet">
	<script language="JavaScript" src="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.js"></script>
	<link rel="stylesheet" href="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.css" type="text/css" media="screen" />
	<link href="<%=STATIC_URL %>/css/index_main.css" type="text/css" rel="Stylesheet">
	<script type='text/javascript'>
	$(document).ready(function() {
		$("#fancybox-manual-a").click(
			function() {
				$.fancybox.open({
					href : '/declare/admin/AdminResourceInfoList.do?fromTag=modal&callbackFun=callbackFun',
					type : 'iframe',
					width : 560,
					minHeight : 500,
					autoScale : false
			});
		});
	});
	function callbackFun(resourceId, resourceName) {
		jQuery("#resourceId").val(resourceId);
		jQuery("#resourceName").val(resourceName);
	}
	</script>
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
                                      <span style="float: left;">
                                      权限定义
                                      </span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                      <div>
                                       <form name="formSearch" method="get" action="AdminPrivilegeDefineManage.do">
						  <div class="search_form">
						    <em class="em1">资源：</em>
						    <input type="text" name="resourceName" id="resourceName" value="<%=resourceInfoBean.getResourceName() %>" size="50"/>
						    <input type="hidden" name="resourceId" id="resourceId" value="<%=resourceInfoBean.getResourceId() %>"/>
						    <input id="fancybox-manual-a" type="button" class="search_btn_sub" value="浏览..." />
						    <input name="" type="submit" class="search_btn_sub" value="查询"/>
						  </div>
						</form>
						<form name="formData" method="post" action="AdminPrivilegeDefineManage.do">
						  <input type="hidden" name="resourceId" id="resourceId" value="<%=ParamKit.getIntParameter(request, "resourceId", 0) %>"/>
						  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table_style">
						    <tr>
						      <th>选择</th>
						      <th>操作</th>
						    </tr>
						    <%
						    for(AdminOperationInfoBean operationBean:operationList){
						    	AdminPrivilegeDefineBean bean = new AdminPrivilegeDefineBean();
						    	boolean check = false;
						    	if(privilegeMap.containsKey(operationBean.getOperationId())){
						    		bean = privilegeMap.get(operationBean.getOperationId());
						    		check = true;
						    	}
						    %>
						    <tr>
						      <td><input type="checkbox" name="operationId" id="operationId" value="<%=operationBean.getOperationId() %>" <%=check?"checked":"" %>/></td>
						      <td><%=operationBean.getOperationName() %></td>
						    </tr>
						    <%} %>
						  </table>
						  <div class="text_right mt_5" style="width:98%">
                                       		<input type="submit" value="保存" class="btn72" />
                                       </div>
                                      </div>
                                    </div>
                                </div>
                            </div>
                        </td>
						</form>
                                       
                </tr>
            </tbody>
        </table>
    </div>
    <div class="contents-footer"></div>
</body>
</html>