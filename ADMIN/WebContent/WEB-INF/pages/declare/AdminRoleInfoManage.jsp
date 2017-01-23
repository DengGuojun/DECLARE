<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<% 
AdminRoleInfoBean bean = (AdminRoleInfoBean)request.getAttribute("RoleInfo");
AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
List<AdminResourceTypeBean> typeList = (List<AdminResourceTypeBean>)request.getAttribute("TypeList");
HashMap<Integer,String> operationMap = (HashMap<Integer,String>)request.getAttribute("OperationMap");
HashSet<String> privilegeSet = (HashSet<String>)request.getAttribute("PrivilegeSet");
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
    <title>新型农民职业培训系统 — 角色管理</title>
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
	<form id="formData" name="formData" method="post" action="AdminRoleInfoManage.do" onsubmit="javascript:return checkForm('formData');">
	<input type="hidden" name="roleId" id="roleId" value="<%=bean.getRoleId() %>"/>
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
                                      <%if(bean.getRoleId()>0) {%>
                                      编辑角色
                                      <%} else{%>
                                      新增角色
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
                                                            <span>角色名称</span>
                                                        </td>
                                                        <td align="left" class="td_data">
                                                            <input type="text" name="roleName" id="roleName" value="<%=bean.getRoleName() %>" checkStr="角色名称;txt;true;;100" maxlength="60" class="textEdit" style="width:100%;"></td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right" class="td_head" style="width:30%;">
                                                            <span>角色描述</span>
                                                        </td>
                                                        <td align="left" class="td_data">
                                                            <input type="text" name="memo" id="memo"  value="<%=bean.getMemo() %>" maxlength="60" class="textEdit" style="width:100%;"></td>
                                                    </tr>
                                                     <tr>
                                                        <td align="right" class="td_head" style="width:30%;">
                                                            <span>权限</span>
                                                        </td>
                                                        <td align="left" class="td_data">
                                                            <div class="group_wrp">
                                                            		<%for(AdminResourceTypeBean typeBean : typeList){ %>
                                                            		<div class="group_tit"><%=typeBean.getTypeName() %></div>       
															       <%
															       List<AdminResourceInfoBean> resourceList = (List<AdminResourceInfoBean>)request.getAttribute("ResourceList_" + typeBean.getTypeId());
															       for(AdminResourceInfoBean resourceBean:resourceList){
															       %>
															       <div class="form-group">
                                                                    <label><%=resourceBean.getResourceName() %>：</label>
                                                                   <% List<AdminPrivilegeDefineBean> defineList = (List<AdminPrivilegeDefineBean>)request.getAttribute("DefineList_" + typeBean.getTypeId() + "_" + resourceBean.getResourceId());
															       for(AdminPrivilegeDefineBean defineBean:defineList){
															    	   String privilegeKey = resourceBean.getResourceId() + "_" + defineBean.getOperationId();
															       %>
	                                                                    <label><input type="checkbox" id="privilegeKey" name="privilegeKey" value="<%=privilegeKey %>" <%=(privilegeSet.contains(privilegeKey))?"checked":"" %>/><%=MapKit.getValueFromMap(defineBean.getOperationId(), operationMap) %><%} %></label>
                                                                		</div>	
															       <%} %>
															       <%} %>
                                                            </div>    
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align="right" class="td_head" style="width:30%;">
                                                            <span style="color:#FE7200;">*</span>
                                                            <span>状态</span>
                                                        </td>
                                                        <td align="left" class="td_data" style="width:160px;">
                                                             <select  name="status" id="status" >
													      	<%for(StatusBean<Integer, String> statusBean : Constants.STATUS_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(bean.getStatus()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
													       </select>
                                                        </td>
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