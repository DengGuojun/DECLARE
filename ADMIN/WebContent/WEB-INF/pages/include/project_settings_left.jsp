<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<!--	项目设置侧边栏	-->
<div class="detail_left">
	<div class="detail_left_top"></div>
	<div id="listItem" class="detail_left">
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.ADMIN_USER, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a onclick="javascript:location.href='AdminUserGroupList.do'">人员管理</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.MAJOR_TYPE, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a onclick="javascript:location.href='MajorTypeList.do'">专业类型维护</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.MAJOR_INFO, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a onclick="javascript:location.href='MajorInfoList.do'">专业维护</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.ADMIN_ROLE, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a onclick="javascript:location.href='AdminRoleInfoList.do'">角色管理</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG,
					OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a onclick="javascript:location.href='TrainingClassUserConfigList.do'">学员录入设置</a>
		</div>
		<%
			}
		%>
	</div>
</div>