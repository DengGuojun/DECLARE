<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*" %>
<% 
AdminResourceInfoBean bean = (AdminResourceInfoBean)request.getAttribute("ResourceInfo");
List<AdminResourceTypeBean> typeList = (List<AdminResourceTypeBean>)request.getAttribute("TypeList");
AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
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
    <title>新型农民职业培训系统 — 资源管理</title>
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
<body class="article_bg">
<%@include file="../nav/navigation.jsp" %>
	<form id="formData" name="formData" method="post" action="AdminResourceInfoManage.do" onsubmit="javascript:return checkForm('formData');">
	<input type="hidden" name="resourceId" id="resourceId" value="<%=bean.getResourceId() %>"/>
	<input type="hidden" name="status" id="status" value="<%=bean.getStatus() %>"/>
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
                                      <%if(bean.getResourceId()>0) {%>
                                      编辑资源
                                      <%} else{%>
                                      新增资源
                                      <%} %>
                                      </span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                      <div>
                                       <table class="editView" style="width: 98%">
                                                <tbody>
                                                    <tr>
                                                        <td align="right" class="td_head" style="width:30%;">
                                                            <span style="color:#FE7200;">*</span>
                                                            <span>资源代码</span>
                                                        </td>
                                                        <td align="left" class="td_data">
                                                            <input type="text" name="resourceCode" id="resourceCode" value="<%=bean.getResourceCode() %>" checkStr="资源代码;txt;true;;100" maxlength="60" class="textEdit" style="width:100%;"></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right" class="td_head" style="width:30%;">
                                                            <span>资源名称</span>
                                                        </td>
                                                        <td align="left" class="td_data">
                                                            <input type="text" name="resourceName" id="resourceName"  value="<%=bean.getResourceName() %>"  maxlength="60" class="textEdit" style="width:100%;"></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right" class="td_head" style="width:30%;">
                                                            <span>资源类型</span>
                                                        </td>
                                                        <td align="left" class="td_data">
                                                         <select name="typeId" id="typeId">
												        <%for(AdminResourceTypeBean typeBean:typeList){ %>
												          <option value="<%=typeBean.getTypeId() %>" <%=(typeBean.getTypeId()==bean.getTypeId())?"selected":"" %>><%=typeBean.getTypeName() %></option>
												        <%} %>
												        </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right" class="td_head" style="width:30%;">
                                                            <span>备注</span>
                                                        </td>
                                                        <td align="left" class="td_data">
                                                            <input type="text" name="memo" id="memo"  value="<%=bean.getMemo() %>"    maxlength="60" class="textEdit" style="width:100%;"></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                       <div class="text_right mt_5" style="width:98%">
                                       		<input type="submit" value="保存" class="btn72" />
                                       </div>
                                      </div>
                                    </div>
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
</body>
</html>