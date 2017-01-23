<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<% 
AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
DeclareInfoHelper declareInfoHelper = new DeclareInfoHelper();
%>
<%@ include file="../include/header.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 对象推荐</title>
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/SlideMenuStyle.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/default.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/StyleBlue.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/index_main.css" type="text/css" rel="Stylesheet">
    
    <style type="text/css">
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
        input[type='text']
        {
            border: 0;
            width: 98%;
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
    </style>
</head>
<body class="body-index">
	<%@include file="../nav/navigation.jsp" %>
<div class="warpDefaultMgr">
            <table class="table_wrp">
                <tbody>
                    <tr>
                        <td style="width: 191px; background-color: #f2f2f2;" valign="top">
                             <%@include file="../include/cultivate_object_library_left.jsp" %>
                        </td>	
	                    <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                      <span style="float: left;">对象推荐</span>
                                </div>
								<div align="center" style="margin-top: 15px;">
								<form id="formData" name="formData" method="post" action="DeclareInfoRecommendInsert.do" onsubmit="javascript:return formOnSubmit();">
									<span style="font-size: 16px; font-weight: bolder; font-family: 微软雅黑;">推荐信息</span>
									<br>
									<span>
										<div>									
	                                    <table style="width: 805px; border: 1px solid #cecece; border-top: 1px solid #FFFFFF; border-collapse: collapse;" class="gv" border="1" cellspacing="0">
												<tbody>
													<tr class="noborder">
														<td style="width: 65px;"></td>
														<td style="width: 67px;"></td>
														<td style="width: 47px;"></td>
														<td style="width: 60px;"></td>
														<td style="width: 68px;"></td>
													</tr>
													<tr class="">
														<td class="tit" style="">
															<span style="color:Red; display:inline;">*</span>
															姓 名
														</td>
														<td class="con" colspan="4">
															<input type="text" id="userName" name="userName" size="20" maxlength="50" style="width:100%;" checkStr="姓名;txt;true;;50"/>
														</td>
												   </tr>
												   <tr class="">
														<td class="tit" style="">
															<span style="color:Red; display:inline;">*</span>
															手机号
														</td>
														<td class="con" colspan="4">
															<input type="number" name="userMobile" id="userMobile" size="20" maxlength="50" style="width:100%;" checkStr="手机号;txt;true;;50"/>
														</td>
												   </tr>
												   <tr class="">
														<td class="tit" style="">
															<span style="color:Red; display:inline;">*</span>
															身份证号
														</td>
														<td class="con" colspan="4">
															<input type="text" id="identityNumber" name="identityNumber" size="20" maxlength="50" style="width:100%;" checkStr="身份证号;txt;true;;50"/>
														</td>
												   </tr>
												   <tr class="">
														<td class="tit" style="">
															<span style="color:Red; display:inline;">*</span>
															年份
														</td>
														<td class="con" colspan="4">
															<select name="declareYear" id="declareYear" >
										                         <%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
											                     <option value="<%=InfoYear %>"><%=InfoYear %></option>
									                             <%}%>	
					                                        </select>
														</td>
												   </tr>
												   <tr class="">
														<td class="tit" style="">
															<span style="color:Red; display:inline;">*</span>
															申报类型
														</td>
														<td class="con" colspan="12" style="text-align: left;">
															<select name="declareType" id="declareType">
																<%
																	for (StatusBean<Integer,String> statusBean : DeclareInfoConfig.DECLARE_TYPE_LIST) {
																%>
																<option value="<%=statusBean.getStatus()%>"><%=statusBean.getValue()%></option>
																<%
																	}
																%>
															</select>
														</td>
												   </tr>
												</tbody>
											</table>											
										</div>
								  </span>
								  <input type="submit" value="保 存" class="btn72">
								  <input type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()" class="btn72">
								  </form>
	                             </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    <div class="contents-footer"></div>
    <script src="<%=STATIC_URL%>/js/Public.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/InsertFlash.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
</body>
<script type="text/javascript">
function formOnSubmit(){
	if(checkForm('formData')){
		var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		var identityNumber = $('#identityNumber').val();
		var re4 = /^\d{10,11}$/;
		var userMobile = $('#userMobile').val();
		if(reg.test(identityNumber) === false)
		{
		    alert("身份证输入不合法");
		    return false;
		}
		if(re4.test(userMobile) === false)
		{
		    alert("手机号输入不合法");
		    return false;
		}
		return true;
	}else{
		return false;
	}
}   
</script>
</html>