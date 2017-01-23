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
	List<SmsGroupBean> groupList = (List<SmsGroupBean>)request.getAttribute("SmsGroupList");
	List<SmsUserBean> smsUserList = (List<SmsUserBean>)request.getAttribute("SmsUserList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper") ;
	int currentGroupId = (Integer)request.getAttribute("CurrentGroupId") ;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!–[if lte IE 8]> 
  <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
  <![endif]–> 
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
  <title>新型农民职业培训系统 — 短信通讯录</title>
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
        .tit
        {
            text-align: right;
            padding-right: 3px;
            background-color: #EEEFF0;
            word-wrap: break-word; 
            word-break: break-all;
            border: 1px solid #cecece;
        }
        .con
        {
            text-align: left;
            padding-left: 3px;
            border: 1px solid #cecece;
        }
        select
        {
            border: 0;
            border-bottom: 1px solid #C2C2C2;
            max-width:99%;
        }
        input[type='checkbox']
        {
            vertical-align: middle;
        }
        label
        {
            vertical-align: middle;
        }
        .noborder td
        {
            border:1px solid #FFFFFF;
            border-bottom:1px solid #cecece;
        }
        .table_fill td{ padding: 5px; }
        .table_fill .uploadImg{ padding: 5px 0; }
    </style>

</head>
<body class="body-index">
<%@include file="../nav/navigation.jsp" %>
  <form name="" method="post"  id="" onsubmit="javascript:return checkThisForm();" >
	<div class="warpDefaultMgr">
	  <table class="table_wrp">
		<tbody>
		  <tr>
			<td style="width: 191px; background-color: #f2f2f2;" valign="top">
				<%@include file="../include/project_management_left.jsp" %>
			</td>
			 <td valign="top">
                  <div class="detail_right"  >
                      <div class="content_wrap fixed_height" >
                          <div class="detail_right_title h1 white">
                              <span style="float: left;">邮件通讯录</span>
                          </div>
                          <table class="mail-group-wrp">
                              <tr>
                                  <td class="wrp-left">
                                  <div class="group-manage">
                                      <table>
                                          <tbody>
                                              <tr>
                                                  <th><span>通讯组名称</span></th>
                                                  <th></th>
                                                  <th></th>
                                              </tr>
                                              <%for(SmsGroupBean groupBean :groupList) {%>
                                              <tr class=<%=groupBean.getGroupId() == currentGroupId ? "selected" : "" %>>
                                                  <td><a onclick="window.location.href='SmsGroupList.do?groupId=<%=groupBean.getGroupId()%>'"><%=groupBean.getGroupName()%> </a></td>
                                                  <td><a onclick="window.location.href='SmsGroupManage.do?groupId=<%=groupBean.getGroupId()%>'">重命名</a></td>
                                                  <td><a onclick="window.location.href='SmsGroupRemove.do?groupId=<%=groupBean.getGroupId()%>'">删除</a></td>
                                              </tr>
                                              <%} %>
                                              <tr>
                                              <td><input type="button" onclick="window.location.href='SmsGroupManage.do'" class="btn72" value="添加组"/></td>
                                              <td><input type="button" onclick="window.location.href='SmsUserList.do'"class="btn72" value="添加人"/></td>
                                              <td><input type="button" onclick="window.location.href='SmsGroupUserList.do?groupId=<%=currentGroupId%>'"class="btn72" value="添加组员"/></td>
                                              </tr>
                                          </tbody>
                                      </table>
                                  </div>
                                  </td>
                                  <td class="wrp-right">
                                  <div class="manage-content">
                                      <table>
                                          <tbody>
                                              <tr>
                                                  <th>省份</th>
                                                  <th>姓名</th>
                                                  <th>单位</th>
                                                  <th>联系电话</th>
                                                  <th></th>
                                                  <th></th>
                                              </tr>
                                              <%for(SmsUserBean smsUserBean :smsUserList) {%>
                                              <tr>
                                                  <td><%=smsUserBean.getSmsUserProvince()%></td>
                                                  <td><%=smsUserBean.getSmsUserName() %></td>
                                                  <td><%=smsUserBean.getSmsUserCorporate() %></td>
                                                  <td><%=smsUserBean.getSmsUserMobile() %></td>
                                                  <td><a onclick="window.location.href='SmsUserManage.do?smsUserId=<%=smsUserBean.getSmsUserId()%>'">编辑</a></td>
                                                  <td><a onclick="window.location.href='SmsGroupUserManage.do?groupId=<%=currentGroupId%>&smsUserId=<%=smsUserBean.getSmsUserId()%>&isDelete=1'">删除</a></td>
                                              </tr>
                                              <%} %>
                                          </tbody>
                                      </table>
                                  </div>
                                  </td>
                              </tr>
                          </table>
                      </div>
                  </div>
              </td>
		  </tr>
		</tbody>
	  </table>
	</div>
  </form>
  <div class="contents-footer"></div>
  <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
  <script type="text/javascript" src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>