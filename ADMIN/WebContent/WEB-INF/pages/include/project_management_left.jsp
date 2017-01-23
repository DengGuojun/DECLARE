<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--	项目管理侧边栏	-->
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<div class="detail_left">
	<div class="detail_left_top"></div>
	<div id="listItem" class="detail_left">
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO,
					OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a href="GovernmentOrganizationInfoList.do">主管部门</a>
		</div>
		<%
			}
		%>

		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_TARGET, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a href="TrainingClassTargetList.do">培训任务数</a>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_KINDS_COUNT, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a href="DeclareStatisticsList.do">统计分析</a>
		</div>
		<div class="sub_menu">
			<ul>
				<li><a href="DeclareStatisticsList.do">培育对象情况统计</a></li>
				<li><a href="TargetStatisticsList.do">各地培训情况统计</a></li>
				<li><a href="DeclareStatisticsList.do?model=auth">各地认定情况统计</a>
				</li>
				<li><a href="TrainingOrganizationCountList.do?organizationType=<%=TrainingOrganizationConfig.ORGANIZATION_TRAINING%>">各地机构类型统计</a>
				</li>
				<li><a href="OrganizationStatisticsList.do?organizationType=<%=TrainingOrganizationConfig.ORGANIZATION_TRAINING%>">各地机构培训人数统计</a>
				</li>
				<li><a href="TrainingProcessList.do">培训进展</a>
				</li>

				<%
					if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CUSTOM_COUNT, OperationConfig.SEARCH)) {
				%>
				<li><a href="TrainingClassUserSearch.do">自定义统计</a></li>
				<%
					}
				%>
			</ul>
		</div>
		<%
			}
		%>
		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a onclick="javascript:location.href='AnnouncementInfoList.do'">工作通知</a>
		</div>
		<div class="sub_menu">
			<ul>
				<li><a onclick="javascript:location.href='AnnouncementInfoList.do'">通知浏览</a>
				</li>
				<%
					if (adminUserHelper.hasPermission(DeclareAdminResource.ANNOUNCEMENT_INFO, OperationConfig.CREATE)) {
				%>
				<li><a onclick="javascript:location.href='AnnouncementInfoList.do?owner=true'">通知发布</a>
				</li>
				<%
					}
				%>
				<li><a onclick="javascript:location.href='SmsInfoManage.do'">短信发送</a>
				</li>
				<li><a onclick="javascript:location.href='SmsGroupList.do?owner=true'">短信通信录</a>
				</li>
			</ul>
		</div>
		<%
			}
		%>
		<div class="detail_left_box detail_box3">
			<a onclick="javascript:location.href='MessageInfoManage.do'">邮件管理</a>
		</div>
		<div class="sub_menu">
			<ul>
				<li><a onclick="javascript:location.href='MessageInfoManage.do'">发送新邮件</a>
				</li>
			</ul>
			<ul>
				<li><a onclick="javascript:location.href='MessageInfoReceiveList.do'">收到邮件</a>
				</li>
			</ul>
			<ul>
				<li><a onclick="javascript:location.href='MessageInfoSendList.do'">已发邮件</a>
				</li>
			</ul>
			<ul>
				<li><a onclick="javascript:location.href='MessageGroupList.do'">邮件通讯录</a>
				</li>
			</ul>
		</div>

		<%
			if (adminUserHelper.hasPermission(DeclareAdminResource.FILE_INFO, OperationConfig.SEARCH)) {
		%>
		<div class="detail_left_box detail_box3">
			<a onclick="javascript:location.href='FileInfoList.do'">资料下载</a>
		</div>
		<%
			}
		%>
	</div>
</div>