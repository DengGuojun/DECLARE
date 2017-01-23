<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
List<MajorInfoBean> list = (List<MajorInfoBean>)request.getAttribute("MajorInfoList");
Map<String,String> majorTypeMap = (Map<String,String>)request.getAttribute("majorTypeMap") ;
AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminHelper") ;
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
    <title>新型农民职业培训系统 — 详细页面</title>
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
                                      <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">专业列表</span></div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <form name="formSearch" method="post" action="MajorInfoList.do">
                                      <div class="form-group form-horizontal">
                                      	<label>专业名称：</label>
										<input type="text" name="majorName" id="majorName" value="<%=ParamKit.getParameter(request, "majorName", "") %>" size="20" class="form-control">
                                      </div>
                                      	<% 
                                      		if (adminUserHelper.hasPermission(DeclareAdminResource.MAJOR_INFO, OperationConfig.SEARCH)) {
                                		%>
                                		<input type="submit" value="查 询" class="btn72" />
                                		<% 	 
                                			}
                                      	%>
										<% 
                                      		if (adminUserHelper.hasPermission(DeclareAdminResource.MAJOR_INFO, OperationConfig.CREATE)) {
                                		%>
                                		<input type="button" value="新增" class="btn72" onclick="javascript:location.href='MajorInfoManage.do'"/>
                                		<% 	 
                                			}
                                      	%>
										
									</form>
                                      <div>
                                        <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                          <tbody>
                                            <tr class="gv_Head">
                                              <th style="width:30px;">行号</th>
                                              <th>专业类型</th>
                                              <th>专业名称</th>
                                              <th>是否可用</th>
                                              <th></th>
                                            </tr>
                                             <%
                                        	int rowCount = 1;
									    	for(MajorInfoBean bean:list){ 
									    	 %>
                                            <tr class="gv_Item">
                                              <td style="width:30px;"> <span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                              <td><%=majorTypeMap.get(bean.getTypeId()) %></td>
                                              <td><%=bean.getMajorName() %></td> 
                                              <td><%=Constants.STATUS_MAP.get(bean.getStatus())%></td>
                                              <td>
                                            <% 
                                      		if (adminUserHelper.hasPermission(DeclareAdminResource.MAJOR_INFO, OperationConfig.CREATE)) {
                                			%>
                                			 <a href="MajorInfoManage.do?majorId=<%=bean.getMajorId() %>" style="cursor:pointer;">编辑</a>
                                			<% 	 
                                			}
                                      		%>
                                             </td>
                                            </tr>
                                               <% rowCount++;} %>
                                          </tbody>
                                        </table>
                                      </div>
                                    <%@ include file="../include/page.jsp" %>
                                    </div>
                                    <ul class="page_info">
									<li class="page_left_btn">
									</li>
									</ul>
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