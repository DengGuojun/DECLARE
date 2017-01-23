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
	List<AnnouncementInfoBean> list = (List<AnnouncementInfoBean>)request.getAttribute("AnnouncementList");

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
    <title>新型农民职业培训系统 — 公告通知</title>
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
        
        body
        {
            font-size: 14px;
            font-family: "宋体";
        }
        ol li
        {
            margin: 8px;
        }
        #con
        {
            font-size: 12px;
            margin: 0px auto;
            width: 100%;
            height: 100%;
        }
        #tags
        {
            padding-right: 0px;
            padding-left: 0px;
            padding-bottom: 0px;
            margin: 0px 0px 0px 10px;
            width: 400px;
            padding-top: 0px;
            height: 23px;
            width: 1000px;
        }
        #tags li
        {
            background: url(../images/tagleft.gif) no-repeat left bottom;
            float: left;
            margin-right: 1px;
            list-style-type: none;
            height: 23px;
        }
        #tags li a
        {
            padding-right: 10px;
            padding-left: 10px;
            background: url(../images/tagright.gif) no-repeat right bottom;
            float: left;
            padding-bottom: 0px;
            color: #999;
            line-height: 23px;
            padding-top: 0px;
            height: 23px;
            text-decoration: none;
        }
        #tags li.emptyTag
        {
            background: none transparent scroll repeat 0% 0%;
            width: 4px;
        }
        #tags li.selectTag
        {
            background-position: left top;
            background-repeat: repeat-x;
            margin-bottom: -2px;
            position: relative;
            height: 25px;
        }
        #tags li.selectTag a
        {
            background-position: right top;
            color: #FFF;
            line-height: 25px;
            height: 25px;
        }
        #tagContent
        {
            border-right: #aecbd4 1px solid;
            padding-right: 1px;
            border-top: #aecbd4 1px solid;
            padding-left: 1px;
            padding-bottom: 1px;
            border-left: #aecbd4 1px solid;
            padding-top: 1px;
            border-bottom: #aecbd4 1px solid;
            background-color: #fff;
        }
        .tagContent
        {
            padding-right: 10px;
            display: none;
            padding-left: 5px;
            padding-bottom: 10px;
            width: 98%;
            color: #474747;
            padding-top: 5px;
            height: 498px;
        }
        #tagContent div.selectTag
        {
            display: block;
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
							<%@include file="../include/project_management_left.jsp" %>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap fixed_height">
                                    <div class="detail_right_title h1 white">
                                        <span style="float: left;">公告通知</span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    	   <%if(adminUserHelper.hasPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.CREATE)
                                    			   &&  StringKit.isValid(ParamKit.getParameter(request, "owner",""))){ %>
                                        <input type="submit" value="新增公告" class="btn72 mt_5" onclick="javascript:location.href='AnnouncementInfoManage.do'"/>
                                        <%} %>
                                        <div>
                                        	<table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                      <tbody>
                                        <tr class="gv_Head">
                                          <th style="width:30px;">行号</th>
                                          <th>标题</th>
									      <th>时间</th>
									      <th>发布状态</th>
									      <th></th>
									      <%if(StringKit.isValid(ParamKit.getParameter(request, "owner",""))) {%>
									      <th></th>
									      <th></th>
									      <%} %>
                                        </tr>
                                        <%
                                        int rowCount = 1;
									    for(AnnouncementInfoBean bean:list){ 
									    %>
                                        <tr class="gv_Item">
                                          <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                          <td id="title_<%=bean.getAnnouncementId()%>"><%=bean.getAnnouncementTitle() %></td>
								   	   	 <td><%=DateKit.formatTimestamp(bean.getCreateTime(), DateKit.DEFAULT_DATE_FORMAT)%></td>
								   	   	 <td><%=MapKit.getValueFromMap(bean.getAnnouncementStatus(), AnnouncementInfoConfig.ANNOUNCEMENT_STATUS_MAP)%></td>
								   	   	 <%if(StringKit.isValid(ParamKit.getParameter(request, "owner",""))) {%>
								      	 <td><a href="AnnouncementInfoManage.do?announcementId=<%=bean.getAnnouncementId() %>">编辑</a></td>
									     <td><a onclick="deleteAnnouncement('<%=bean.getAnnouncementId()%>')">删除</a></td>
									     <td><a href="AnnouncementInfoPublish.do?announcementId=<%=bean.getAnnouncementId() %>">发布</a></td>
									     <%}else{ %>
									     <td><a href="AnnouncementInfoView.do?announcementId=<%=bean.getAnnouncementId() %>">浏览</a></td>
									     <%} %>
                                        </tr>
                                        <% rowCount++;} %>
                                      </tbody>
                                    </table>
                                        </div>
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
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
</body>
<script>
function deleteAnnouncement(id) {
	var title = $('#title_'+id).html();
	if(confirm("确定要删除公告【"+title+"】吗?")){
		var url = "AnnouncementInfoRemove.do?announcementId="+id;
		window.location.href= url
	 }
}
</script>
</html>
