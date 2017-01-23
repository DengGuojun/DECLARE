<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.constant.*"  %>
<% 
	AdminUserInfoBean bean = (AdminUserInfoBean)request.getAttribute("UserInfo");
AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	AdminRoleUserBean roleUserBean = (AdminRoleUserBean)request.getAttribute("RoleUser");
	List<AdminRoleInfoBean> roleList = (List<AdminRoleInfoBean>)request.getAttribute("RoleList");
	int userId = bean.getUserId();
	boolean updateFlag = bean.getUserId() > 0;
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
 <script type="text/javascript" src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
    <title>新型农民职业培训系统 — 用户管理</title>
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
<script type="text/javascript">
function isCheckLoginId(str){
  var re=/^[a-zA-Z0-9_\.@]+?$/;
  return re.test(str);
}

function checkThisForm(form){
	var loginId = $("#loginId").val();
	if(!isCheckLoginId(loginId)){
		alert('登陆ID只能录入字母、数字、下划线、小数点、"@"！');
		return false;
	}
	
	var updateFlag=$('#userId').val() > 0;
	var loginPassword=$.trim($('#loginPassword').val());
	if(!loginPassword&&!updateFlag){
		alert('密码不能为空');
		$('#loginPassword').focus();
		return false;
	}
	var modifyPwd = $("input[type='checkbox']").is(':checked');
	var updateFlag = <%=updateFlag%>;
	if(!updateFlag){
		modifyPwd = true;
	}
	if(!loginPassword&&modifyPwd){
		alert('密码不能为空');
		$('#loginPassword').focus();
		return false;
	}
	if(!modifyPwd){
		$('#loginPassword').val("");
	}else if(loginPassword){
		var flag=false;
		if(/([a-z]|[A-Z]|[0-9]){6,12}/.test(loginPassword)){
			var len=loginPassword.length;
			if(loginPassword.replace(/[a-z]|[A-Z]/,"").length<len && loginPassword.replace(/[0-9]/,"").length<len){
				flag=true;
			}
		}
		if(!flag){
			alert('新密码的格式不符，请输入包含数字和字母6到12位的密码');
			return false;
		}
	}
	var loginPassword2=$.trim($('#loginPassword2').val());
	if(loginPassword != loginPassword2){
		alert('两次输入的密码不一致');
		return false;
	}
	return checkForm(form);
}
</script>

</head>
<body class="article_bg">
	<%@include file="../nav/navigation.jsp" %>
	<form id="formData" name="formData" method="post" action="AdminUserInfoManage.do" onsubmit="javascript:return checkThisForm('formData');">
	<input type="hidden" name="userId" id="userId" value="<%=bean.getUserId() %>"/>
	<input type="hidden" name="groupId" id="groupId" value="<%=bean.getGroupId() %>"/>
	<input type="hidden" name="adminUserLevel" id="adminUserLevel" value="<%=bean.getAdminUserLevel()%>"/>
	<input type="hidden" name="province" id="province" value="<%=bean.getProvince()%>"/>
	<input type="hidden" name="city" id="city" value="<%=bean.getCity() %>"/>
	<input type="hidden" name="region" id="region" value="<%=bean.getRegion() %>"/>
	<input type="hidden" name="adminUserType" id="adminUserType" value="<%=bean.getAdminUserType() %>"/>
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
                                      <span style="float: left;">编辑人员信息</span></div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                      <div>
                                       <table class="editView table_wrp gv" width="98%">
                                       	<tbody>
                                       		<tr>
                                       			<td align="left" class="td_head" colspan="4"> <b>·用户基本信息</b></td>
                                       		</tr>
                                       		<tr>
                                       			<td align="right" class="td_head">
                                       				<span style="color: #FE7200;">*</span>
                                       				<span>人员姓名</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				 <input type="text" name="adminUserName" id="adminUserName" size="30" maxlength="50" value="<%=bean.getAdminUserName() %>" readonly checkStr="人员姓名;txt;true;;100"/><em></em></td>
                                       			<td align="right" class="td_head">
                                       				<span>性 别</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<select name="adminUserGender" id="adminUserGender">
											      	<%for(StatusBean<Integer, String> gender:GenderConfig.GENDER_LIST){ %>
											      	  <option value="<%=gender.getStatus() %>" <%=(gender.getStatus()==bean.getAdminUserGender())?"selected":"" %>><%=gender.getValue() %></option><%} %>
											      	</select>
                                       			</td>
                                       		</tr>
                                       		<tr>
                                       			<td align="right" class="td_head">
                                       				<span>出生日期</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<input type="text" name="adminUserBirthday" id="adminUserBirthday" size="30" maxlength="50" value="<%=bean.getAdminUserBirthday() != null ? bean.getAdminUserBirthday() : "" %>" checkStr="出生日期;txt;false;;50" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
                                       			<td align="right" class="td_head">
                                       				<span>部门职务</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<input type="text" name="adminUserPose" id="adminUserPose" size="30" maxlength="100" value="<%=bean.getAdminUserPose() %>" checkStr="用户职位;txt;false;;100"/>
                                       			</td>
                                       		</tr>
                                       		<tr>
                                       			<td align="right" class="td_head">
                                       				<span>联系电话</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<input type="text" name="adminUserPhone" id="adminUserPhone" size="30" maxlength="50" value="<%=bean.getAdminUserPhone() %>" checkStr="联系电话;txt;false;;50"/>
                                       			</td>
                                       			<td align="right" class="td_head">
                                       				<span>手 机</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<input type="text" name="adminUserTelephone" id="adminUserTelephone" size="30" maxlength="50" value="<%=bean.getAdminUserTelephone() %>" checkStr="手机;txt;false;;50"/>
                                       			</td>
                                       		</tr>
                                       		<tr>
                                       			<td align="right" class="td_head">
                                       				<span>传 真</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<input type="text" name="adminUserFax" id="adminUserFax" size="30" maxlength="50" value="<%=bean.getAdminUserFax() %>" checkStr="传真;txt;false;;50"/>
                                       			</td>
                                       			<td align="right" class="td_head">
                                       				<span>电子邮件</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<input type="text" name="adminUserEmail" id="adminUserEmail" size="30" maxlength="50" value="<%=bean.getAdminUserEmail() %>" checkStr="传真;txt;false;;50"/>
                                       			</td>
                                       		</tr>
                                       		<tr>
                                       			<td align="right" class="td_head">
                                       				<span>备 注</span>
                                       			</td>
                                       			<td align="left" class="td_data" colspan="3">
                                       				<textarea rows="3" cols="20" class="textEdit" name="memo" id="memo" style="width: 100%;"><%=bean.getMemo() %></textarea>
                                       			</td>
                                       		</tr>
                                       		<tr>
                                       			<td align="left" class="td_head" colspan="4"> <b>·登录帐号信息</b></td>
                                       		</tr>
                                       		<tr>
                                       			<td align="right" class="td_head">
                                       				<span style="color: #FE7200;">*</span>
                                       				<span>登录名</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<input type="text" name="loginId" id="loginId" size="30" maxlength="100" value="<%=StringKit.isValid(bean.getLoginId()) ? bean.getLoginId().split(DeclareAdminConfig.ADMIN_LOGIN_SEPARATOR)[1] : "" %>"/>
                                       			</td>
                                       			<td align="center" class="td_head" colspan="2">（将作为登录系统的唯一帐号）</td>
                                       		</tr>
                                       		<tr>
                                       			<td align="right" class="td_head">
                                       				<%if(updateFlag){ %>
                                       				<span id="ctl00_ContentPlaceHolder1_TrModifyPassword">
                                       					<label>我要修改密码：</label>
                                       					<input type="checkbox" id="modifyPwd" value="1">
                                       				</span>
                                       				 <%} %>
                                       				<span style="color: #FE7200;">*</span>
                                       				<span>输入密码</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<input type="password"   name="loginPassword" id="loginPassword" maxlength="20" class="textEdit" style="width: 100%;background-color: #EEEEEE;">
                                       			</td>
                                       			<td align="right" class="td_head">
                                       				<span style="color: #FE7200;">*</span>
                                       				<span>再输一次密码</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<input type="password"  name="loginPassword2" id="loginPassword2"  maxlength="20" class="textEdit" style="width: 100%; background-color: #EEEEEE;">
                                       			</td>
                                       		</tr>
                                       		<tr>
                                       			<td align="right" class="td_head">
                                       				<span style="color: #FE7200;">*</span>
                                       				<span>登录帐号状态</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<select  name="status" id="status" >
												      	<%for(StatusBean<Integer, String> statusBean : Constants.STATUS_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(bean.getStatus()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
												    </select>
                                       			</td>
                                       			<td align="right" class="td_head">
                                       				<span style="color: #FE7200;">*</span>
                                       				<span>角色权限</span>
                                       			</td>
                                       			<td align="left" class="td_data">
                                       				<select name="roleId" id="roleId" >
												      	<%for(AdminRoleInfoBean roleBean : roleList){ %><option value="<%=roleBean.getRoleId()%>" <%=(roleBean.getRoleId() == roleUserBean.getRoleId())?"selected":"" %>><%=roleBean.getRoleName() %></option><%} %>
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