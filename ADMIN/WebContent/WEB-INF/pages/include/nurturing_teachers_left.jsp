<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<!--	培育师资库侧边栏	-->
<div class="detail_left">
	<div class="detail_left_top"></div>
	<div id="listItem" class="detail_left">
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a
				onclick="javascript:location.href='TeacherInfoList.do?teacherRange=2'">本级师资信息</a>
		</div>
		<div class="detail_left_box detail_box3">
			<a onclick="javascript:location.href='TeacherDeclareList.do'">师资审核</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_ALL, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a
				onclick="javascript:location.href='TeacherInfoList.do?teacherRange=0'">入库师资信息</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_POPEDOM, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a
				onclick="javascript:location.href='TeacherInfoList.do?teacherRange=1'">本辖区师资信息</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_STATISTICS, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a
				onclick="javascript:location.href='/declare/admin/TeacherStatisticsList.do'">入库师资统计</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_STATISTICS_BY_LEVEL,
					OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a
				onclick="javascript:location.href='/declare/admin/TeacherStatisticsByLevelList.do'">分级师资统计</a>
		</div>
		<%
			}
		%>
	</div>
</div>