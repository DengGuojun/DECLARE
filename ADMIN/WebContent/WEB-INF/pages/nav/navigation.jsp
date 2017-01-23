<%@page import="com.lpmas.declare.admin.config.NavigationConfig"%>
<%@page import="com.lpmas.declare.admin.config.TrainingOrganizationConfig"%>
<%@page import="com.lpmas.declare.admin.config.TrainingMaterialConfig"%>
<%@page import="com.lpmas.declare.admin.config.DeclareAdminConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<div class="header">
	<div class="contents_width header_top">
		<div id="logo" class="align-left logo"
			style="background:url(<%=STATIC_URL %>/images/logo.png) no-repeat;"></div>
		<div class="align-right">
			<a class="input-button-4" href="/Logout.do">注销系统</a> <span id="UserName"
				class="span-row white">用户：<%=adminUserHelper.getAdminUserInfo().getAdminUserName() %></span>
		</div>
	</div>
</div>
<div class="contents">
	<table class="table_wrp">
		<tbody>
			<tr id="maindhead">
				<td style="width: 76px;"></td>
				<td align="center"
					style="background-image: url(images/body_middle.jpg) repeat-x; text-align: center">
					<div style="width: 1105px;" class="nav">
						<div class="nav_item align-left" style="padding-left: 0px;">
							<a onclick="javascript:location.href='Index.do'" style="color: #fff; font-size: 12px; text-decoration: none;">
								<img src="<%=STATIC_URL %>/images/nav_01.png" name="1"> 
								<span class="white">系统首页</span>
							</a>
						</div>   
						 <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND, OperationConfig.SEARCH) || adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_VERIFY, OperationConfig.SEARCH) ||
                                		adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_MANAGE, OperationConfig.SEARCH) || adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_CLASSIFY, OperationConfig.SEARCH)){
                       	%>
						<div id="duixiang" class="nav_item align-left">
							<a name="ProfessionalInfo" onclick="javascript:location.href='NavigationList.do?navigationType=<%=NavigationConfig.TRAINING_OBJECT%>'"> 
								<img id="ProfessionalInfo"src="<%=STATIC_URL %>/images/nav_02.png" name="2"> 
								<span class="white">培育对象库</span>
							</a>
						</div>
						<%
						}
						%>
						<%
						   if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO , OperationConfig.SEARCH)||
								   adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO , OperationConfig.CREATE)) {
						%>
						<div id="tj6" class="nav_item align-left">
							<a name="ProfessionalInfo" onclick="javascript:location.href='NavigationList.do?navigationType=<%=NavigationConfig.TRAINING_ORGANIZATION%>'"> 
								<img id="tj6" src="<%=STATIC_URL %>/images/nav_03.png" name="3"> 
								<span class="white">培训单位库</span>
							</a>
						</div>
						<%	
						}
						%>
					
						<%
						if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.SEARCH) || adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_ALL, OperationConfig.SEARCH) ||
								adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_POPEDOM, OperationConfig.SEARCH) || adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_STATISTICS, OperationConfig.SEARCH) ||
								adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_STATISTICS_BY_LEVEL, OperationConfig.SEARCH)) {
						%>
							<div id="szk" class="nav_item align-left">
							<a name="ProfessionalInfo" onclick="javascript:location.href='NavigationList.do?navigationType=<%=NavigationConfig.TEACHER_INFO%>'"> 
								<img id="szk" src="<%=STATIC_URL %>/images/nav_011.png" name="11"> 
								<span class="white">培育师资库</span>
							</a>
							</div>
						<%	
						}
						%>
					
						<%
						   if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO , OperationConfig.SEARCH)||
								   adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO , OperationConfig.CREATE)) {
						%>
						<div id="Reports" class="nav_item align-left">
							<a name="ProfessionalInfo" onclick="javascript:location.href='NavigationList.do?navigationType=<%=NavigationConfig.PRACTICE_ORGANIZATION%>'"> 
								<img id="Reports" src="<%=STATIC_URL %>/images/nav_04.png" name="4"> 
								<span class="white">实训基地库</span>
							</a>
						</div>
						<%	   
						   }
						%>
						
						<%
						 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_GENERAL_MATERIAL_INFO, OperationConfig.SEARCH) ||
								 adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_PROFESSIONAL_MATERIAL_INFO, OperationConfig.SEARCH)) {
						%>
						<div class="nav_item align-left">
							<a name="ProfessionalInfo" onclick="javascript:location.href='NavigationList.do?navigationType=<%=NavigationConfig.TRAINING_MATERIAL%>'"> 
								<img id="WorkMessage" src="<%=STATIC_URL %>/images/nav_05.png" name="5"> 
								<span class="white">培育教材库</span>
							</a>
						</div>
						<%	 
						 }
						%>
						
						<%
						 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.SEARCH)) {
						%>
						<div id="teach" class="nav_item align-left">
							<a name="ProfessionalInfo" onclick="javascript:location.href='NavigationList.do?navigationType=<%=NavigationConfig.EDUCATION%>'"> 
								<img id="Message" src="<%=STATIC_URL %>/images/nav_07.png" name="7"> 
								<span class="white">教育培训班</span>
							</a>
						</div>
						<%	 
						 }
						%>
						
						   <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO , OperationConfig.SEARCH) || 
                                		adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_AUTH, OperationConfig.SEARCH)) {
                            %>
                            <div id="MessageBase" class="nav_item align-left">
							<a name="ProfessionalInfo" onclick="javascript:location.href='NavigationList.do?navigationType=<%=NavigationConfig.INFORMATION_BANK%>'">  
							<img id="MessageBase" src="<%=STATIC_URL %>/images/nav_09.png" name="9"> 
							<span class="white">认定管理</span>
							</a>
						   </div>
                            <%    	
                                }
                        	%>
						
						<%
						    if (adminUserHelper.hasPermission(DeclareAdminResource.GOVERNMENT_ORGANIZATION_INFO , OperationConfig.SEARCH) || adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_TARGET , OperationConfig.SEARCH)	||
								adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_KINDS_COUNT , OperationConfig.SEARCH) ||
								adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CUSTOM_COUNT , OperationConfig.SEARCH) ||
								adminUserHelper.hasPermission(DeclareAdminResource.ANNOUNCEMENT_INFO , OperationConfig.SEARCH) ||
								adminUserHelper.hasPermission(DeclareAdminResource.FILE_INFO , OperationConfig.SEARCH)) {
						%>
						<div id="zlk" class="nav_item align-left">
							<a name="ProfessionalInfo" onclick="javascript:location.href='NavigationList.do?navigationType=<%=NavigationConfig.PROJECT_MANAGEMENT%>'"> 
							<img id="zlk" src="<%=STATIC_URL %>/images/nav_010.png"  name="10"> 
							<span class="white">项目管理</span>
							</a>
						</div> 
						<%	
						}
						%>
						
						<% 
						if (adminUserHelper.hasPermission(DeclareAdminResource.ADMIN_USER, OperationConfig.SEARCH) || adminUserHelper.hasPermission(DeclareAdminResource.MAJOR_TYPE, OperationConfig.SEARCH) ||
								adminUserHelper.hasPermission(DeclareAdminResource.MAJOR_INFO, OperationConfig.SEARCH) ||
								adminUserHelper.hasPermission(DeclareAdminResource.ADMIN_ROLE, OperationConfig.SEARCH) ||
								adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG, OperationConfig.SEARCH)
								){
						%>
						<div id="SysManager" class="nav_item align-left">
							<a name="ProfessionalInfo" onclick="javascript:location.href='NavigationList.do?navigationType=<%=NavigationConfig.SYSTEM_SETTING%>'"> 
							<img id="SysManager" src="<%=STATIC_URL %>/images/nav_08.png" name="8"> 
							<span class="white">系统设置</span>
							</a>
						</div>
						<% 
						}
						%>
					</div>
				</td>
				<td style="width: 76px;"></td>
			</tr>
		</tbody>
	</table>
</div>