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
	GovernmentOrganizationInfoBean bean = (GovernmentOrganizationInfoBean)request.getAttribute("GovernmentOrganization");
	AdminUserHelper adminUserHelper =(AdminUserHelper) request.getAttribute("adminUserHelper") ;
	
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!–[if lte IE 8]> 
  <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
  <![endif]–> 
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
  <title>新型农民职业培训系统 — 主管部门管理</title>
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
<body class="body-index">
<%@include file="../nav/navigation.jsp" %>
  <form name="formData" id="formData" method="post" action="GovernmentOrganizationInfoManage.do" onsubmit="javascript:return checkThisForm('formData');" >
	<div class="warpDefaultMgr">
	  <table class="table_wrp">
		<tbody>
		  <tr>
			<td style="width: 191px; background-color: #f2f2f2;" valign="top">
				<%@include file="../include/project_management_left.jsp" %>
			</td>
			<td valign="top">
			  <div class="detail_right">
				<div class="content_wrap fixed_height">
				  <div class="detail_right_title h1 white">
				   <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">
                                        <% if(bean.getOrganizationId() > 0) {
                                       	%>
                                       	修改主管部门信息
                                        <% } else{
                                        %>
                                        增加主管部门信息
                                        <% 	
                                        }%>
                                        </span>
				  </div>
				    <div class="right_bg" style="padding-left: 5px;">
				   <input type="hidden" name="organizationLevel" value="<%=bean.getOrganizationLevel()%>">
				    <input type="hidden" name="organizationId" value="<%=bean.getOrganizationId()%>">
				    <input type="hidden" name="status" value="<%=Constants.STATUS_VALID %>" /> 
				   	<table class="table_comment">
        			<tbody>
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>年份</td>
        					<td>
        					
        					<select name="trainingYear" id="trainingYear">
        						<option value="">请选择</option>
        						<option value="2013" <%="2013".equals(bean.getTrainingYear())?"selected":""%> >2013</option>
        						<option value="2014" <%="2014".equals(bean.getTrainingYear())?"selected":""%> >2014</option>
        						<option value="2015" <%="2015".equals(bean.getTrainingYear())?"selected":""%> >2015</option>
        						<option value="2016" <%="2016".equals(bean.getTrainingYear())?"selected":""%> >2016</option>
        						<option value="2017" <%="2017".equals(bean.getTrainingYear())?"selected":""%> >2017</option>
        					</select>
        					 
        					</td>
        				</tr>
        					<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>省份</td>
        					<td>
        					<input type="hidden" name="city" value="<%=bean.getCity()%>" />
        					<input type="hidden" name="province" value="<%=bean.getProvince()%>" />
        					<input type="hidden" name="region" value="<%=bean.getRegion()%>" />
        					
                                 <div class="form-group form-horizontal">   
                                     <input readonly="true"  type="text" class="form-control" value="<%=bean.getProvince()+bean.getCity()+bean.getRegion() %>" />
                                </div>
                            </td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>主管部门名称</td>
        					<td>
        						 <input id="organizationName" name="organizationName" type="text" class="form-control" value="<%=bean.getOrganizationName() %>" checkStr="主管部门名称;txt;true;;100"/>
        					</td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>主管处(科)室</td>
        					<td>
        						 <input id="department" name="department" type="text" class="form-control" value="<%=bean.getDepartment() %>" checkStr="主管处(科)室;txt;true;;100"/>
        					</td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>处(科)室负责人姓名</td>
        					<td>
        						 <input id="responsiblePersonName" name="responsiblePersonName" type="text" class="form-control" value="<%=bean.getResponsiblePersonName() %>" checkStr="处(科)室负责人姓名;txt;true;;100"/>
        					</td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>处(科)室负责人电话(固定电话带区号)</td>
        					<td>
        						 <input id="responsiblePersonMobile" name="responsiblePersonMobile" type="text" class="form-control" value="<%=bean.getResponsiblePersonMobile() %>" checkStr="处(科)室负责人电话;txt;true;;100"/>
        					</td>
        				</tr>
        				
        				 <tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>处(科)室经办人姓名</td>
        					<td>
        						 <input id="operatorName" name="operatorName" type="text" class="form-control" value="<%=bean.getOperatorName() %>" checkStr="处(科)室经办人姓名;txt;true;;100"/>
        					</td>
        				</tr>
        				 <tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>处(科)室经办人电话(固定电话带区号)</td>
        					<td>
        						 <input id="operatorMobile" name="operatorMobile" type="text" class="form-control" value="<%=bean.getOperatorMobile() %>" checkStr="处(科)室经办人电话;txt;true;;100"/>
        					</td>
        				</tr>
        				
        				 <tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>通讯地址</td>
        					<td>
        						 <input id="address" name="address" type="text" class="form-control" value="<%=bean.getAddress() %>" checkStr="通讯地址;txt;true;;100"/>
        					</td>
        				</tr>
        				
        				 <tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>传真</td>
        					<td>
        						 <input id="fax" name="fax" type="text" class="form-control" value="<%=bean.getFax() %>" checkStr="传真;txt;true;;15"/>
        					</td>
        				</tr>
        				
        				 <tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>邮政编码:</td>
        					<td>
        						 <input id="zipCode" name="zipCode" type="text" class="form-control" value="<%=bean.getZipCode() %>" maxlength="6" checkStr="邮政编码;digit;true;;6"/>
        					</td>
        				</tr>
        				
        				 <tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>是否属于国家新型职业农民培育重点对象：</td>
        					<td>
        						<select name="isDemonstrationArea" id="isDemonstrationArea">
    					<% 
    					for(StatusBean<Integer, String> statusBean:Constants.SELECT_LIST){ %>
        				<option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus()==bean.getIsDemonstrationArea())?"selected":"" %>><%=statusBean.getValue() %></option>
       					<%} %>
       							</select>
        					</td>
        				</tr>
        			</tbody>
        		</table>
        		<div class="dialog_ft text_center">
        		<input type="submit" class="btn72" value="保 存">&nbsp;&nbsp;	
        		<input class="btn72" type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">
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
  <script src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
  <script>
  function isCheckFax(str){
		var re =  /^(\d{3,4}-)?\d{7,8}$/ ;
		return re.test(str) ;
	}
  function checkThisForm(name){
	  var trainingYear = $("#trainingYear").val() ;
	  if(trainingYear <= 0){
		  alert("请选择正确的年份") ;
			$('#trainingYear').focus() ;
			return false ;
	  }
	  
	  var mobile = /^1[3|4|5|7|8]\d{9}$/ ; //手机号码
	  var tel =   /^0\d{2,3}-\d{7,8}(-\d{1,6})?$/ ;  //电话号码
	  var responsiblePersonMobile = $("#responsiblePersonMobile").val() ;
	  if(!tel.test(responsiblePersonMobile) && !mobile.test(responsiblePersonMobile)){
			alert("请输入正确的负责人联系方式,固定电话需要带区号") ;
			$('#responsiblePersonMobile').focus() ;
			return false ;
		}
	  var operatorMobile = $("#operatorMobile").val() ;
	  if(!tel.test(operatorMobile) && !mobile.test(operatorMobile)){
	        alert("请填写正确的经办人联系方式,固定电话需要带区号") ;
	   	    $('#operatorMobile').focus() ;
	   	    return false ;
	   }
		var faxId = $("#fax").val() ;
		if(!isCheckFax(faxId)){
		alert("传真格式为:XXX-12345678或XXXX-1234567或XXXX-12345678") ;
		$("#fax").focus() ;
		return false ;
	    }
	  return checkForm(name) ;
  }
  </script>
</body>
</html>