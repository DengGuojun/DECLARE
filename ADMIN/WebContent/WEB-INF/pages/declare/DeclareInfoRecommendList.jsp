<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
    AdminUserHelper adminUserHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
	List<DeclareReportBean> list = (List<DeclareReportBean>)request.getAttribute("DeclareReportList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 对象推荐</title>
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
                            <%@include file="../include/cultivate_object_library_left.jsp" %>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                      <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">对象推荐</span></div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <form name="formSearch" method="post" action="DeclareInfoRecommendList.do">
                                      <div class="form-group form-horizontal">
                                      	<label>姓名：</label>
										<input type="text" class="form-control" name="userName" id="userName" value="<%=ParamKit.getParameter(request, "userName", "") %>" size="20"/>
									  </div>
                                      <div class="form-group form-horizontal">
                                        <label>身份证号：</label>
                                        <input type="text" class="form-control" name="identityNumber" id="identityNumber" value="<%=ParamKit.getParameter(request, "identityNumber", "") %>" size="20"/>
                                      </div>
                                      <div class="form-group form-horizontal">
                                        <label>手机号：</label>
                                        <input type="text" class="form-control" name="userMobile" id="userMobile" value="<%=ParamKit.getParameter(request, "userMobile", "") %>" size="20"/>
                                      </div>
									  <input type="submit" value="查 询" class="btn72" />
									  </form>
									  	<%
						                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND,OperationConfig.CREATE)) {
					                   %>
									  <input type="button" value="新增本级推荐" class="btn72" onclick="javascript:location.href='DeclareInfoRecommendInsert.do'" />
									  <%
											}
									 %>
                                      <div>
                                        <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                          <tbody>
                                            <tr class="gv_Head">
                                              <th style="width:30px;">行号</th>
                                              <th>姓名</th>
                                              <th>身份证号</th>
                                              <th>性别</th>
                                              <th>手机号</th>
                                              <th>申报方式</th>                                            
                                              <th>申报类型</th>
                                              <th>状态</th>
                                              <th>操作</th>
                                            </tr>
									     <%int i=0;
									    for(DeclareReportBean bean:list){%> 
									     <tr class="gv_Item">
									      <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=(PAGE_BEAN.getCurrentPageNumber()-1)*DeclareAdminConfig.DEFAULT_PAGE_SIZE + ++i %></span></td>
									      <td><span><%=bean.getUserName() %></span></td>
									      <td><span><%=bean.getIdentityNumber() %></span></td>
									      <td><span><%=MapKit.getValueFromMap(bean.getUserGender(), GenderConfig.GENDER_MAP) %></span></td>
									      <td><span><%=bean.getUserMobile() %></span></td>
									      <td><span>本级推荐</span></td>
									      <td><span><%=DeclareInfoConfig.DECLARE_TYPE_MAP.get(bean.getDeclareType())%></span></td>
									      <td><span><%=DeclareInfoConfig.DECLARE_STATUS_MAP.get(bean.getDeclareStatus())%></span></td>
									      <td style="cursor:pointer;">
									      <%
						                    if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND,OperationConfig.UPDATE)
						                    		&&(!DeclareInfoConfig.DECLARE_STATUS_SUBMIT.equals(bean.getDeclareStatus())
						                    				&& !DeclareInfoConfig.DECLARE_STATUS_REGION_APPROVE.equals(bean.getDeclareStatus())
						                    				&& !DeclareInfoConfig.DECLARE_STATUS_APPROVE.equals(bean.getDeclareStatus()))) {
					                      %>
									      	<a href="/declare/admin/DeclareInfoRecommendManage.do?declareId=<%=bean.getDeclareId()%>">填写申报表</a> 
									      <%} %>
									      </td>
									    </tr>	
									    <%} %>
                                          </tbody>
                                        </table>
                                      </div>
                                      <!-- 页码 -->
                                      <%@ include file="../include/page.jsp" %>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    <div class="contents-footer"></div>
    <script src="<%=STATIC_URL%>/js/Public.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/InsertFlash.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
</body>
</html>