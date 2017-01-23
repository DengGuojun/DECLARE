<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.lpmas.declare.admin.config.*"%>
<!--	培育对象库侧边栏	-->
<div class="detail_left">
	<div class="detail_left_top"></div>
	<div id="listItem" class="detail_left">
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a href="DeclareInfoRecommendList.do">对象推荐</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_VERIFY, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a
				href="DeclareInfoReviewList.do?modelType=<%=DeclareInfoRecommendConfig.TYPE_VERIFY%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER%>">对象审核</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_MANAGE, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a
				href="DeclareInfoReviewList.do?modelType=<%=DeclareInfoRecommendConfig.TYPE_MANAGE%>&declareType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER%>">对象管理</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_CLASSIFY, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a
				href="DeclareInfoReviewList.do?modelType=<%=DeclareInfoRecommendConfig.TYPE_CLASSIFY%>">对象类别</a>
		</div>
		<%
			}
		%>
	</div>
</div>