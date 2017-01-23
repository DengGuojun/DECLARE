<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
	TrainingOrganizationInfoBean bean = (TrainingOrganizationInfoBean)request.getAttribute("TrainingOrganizationInfoBean");
	AdminUserHelper adminUserHelper =(AdminUserHelper) request.getAttribute("adminUserHelper") ;
	String organizationType = ParamKit.getParameter(request, "organizationType",TrainingOrganizationConfig.ORGANIZATION_TRAINING) ;
	//获取组织的类型
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
  <title>新型农民职业培训系统 — 部门管理</title>
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
  <form name="formData" id="formData" method="post" action="TrainingOrganizationInfoManage.do" onsubmit="javascript:return checkThisForm('formData');" >
	<div class="warpDefaultMgr">
	  <table class="table_wrp">
		<tbody>
		  <tr>
			<td style="width: 191px; background-color: #f2f2f2;" valign="top">
			  <div class="detail_left">
				<div class="detail_left_top"></div>
				<div id="listItem" class="detail_left">
				 <% if(TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE.equals(organizationType)){
                                	%>
                                	<%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_COGNIZANCE_INFO , OperationConfig.SEARCH)) {
                        		%>
                        		  <div class="detail_left_box detail_box3">
                                    	<a onclick="javascript:location.href='TrainingOrganizationInfoList.do?organizationType=<%=TrainingOrganizationConfig.ORGANIZATION_AUTHORIZEDE%>'">认定机构管理</a>
                                    </div>
                        		<%	 
                        		}
                                %> 
                                 <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_AUTH, OperationConfig.SEARCH)) {
                        		%>
                        		    <div class="detail_left_box detail_box3">
                                        <a href="DeclareInfoAuthList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER%>">认定学员管理</a>
                                    </div>
                        		<%	 
                        		}
                                %> 
                                	<%
                                		}else{
                                		 if(TrainingOrganizationConfig.ORGANIZATION_BASE_TRAINING.equals(organizationType)){
                                				//此时 实训单位
                                				 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO , OperationConfig.CREATE)) {
                                    %>     
                                    		<div class="detail_left_box detail_box3">
                                    			<a href="TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>">实训单位录入</a>
                                    		</div> 
                                    <%	
                                    		} 
                                				 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_BASE_INFO , OperationConfig.SEARCH)) {
                                    %>
                                    		<div class="detail_left_box detail_box3">
                                    			<a href="TrainingOrganizationInfoList.do?organizationType=<%=organizationType %>">实训单位管理</a>
                                    		</div> 
                                    <%       		   
                                 				}  
                                		} else if(TrainingOrganizationConfig.ORGANIZATION_TRAINING.equals(organizationType)){
                                			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO , OperationConfig.CREATE)) {
                                     %>     
                                                		<div class="detail_left_box detail_box3">
                                                			<a href="TrainingOrganizationInfoManage.do?organizationType=<%=organizationType %>">培训单位录入</a>
                                                		</div> 
                                     <%	
                                            } 
                                			if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_ORGANIZATION_INFO , OperationConfig.SEARCH)) {
                                                %>     
                                                           		<div class="detail_left_box detail_box3">
                                                           			<a href="TrainingOrganizationInfoList.do?organizationType=<%=organizationType %>">培训单位管理</a>
                                                           		</div> 
                                                <%	
                                                       } 
                                		}
                                	}
                            %> 
				</div>
			  </div>
			</td>
			<td valign="top">
			  <div class="detail_right">
				<div class="content_wrap">
				  <div class="detail_right_title h1 white">
				   <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">
                                        <% if(bean.getOrganizationId() > 0) {
                                       	%>
                                       	修改<%=TrainingOrganizationConfig.ORGANIZATION_MAP.get(organizationType) %>信息
                                        <% } else{
                                        %>
                                                                                                          增加<%=TrainingOrganizationConfig.ORGANIZATION_MAP.get(organizationType) %>信息
                                        <% 	
                                        }%>
                                        </span>
				  </div>
				    <div class="right_bg" style="padding-left: 5px;">
				   
				    <input type="hidden" name="organizationId" value="<%=bean.getOrganizationId()%> ">
				    <input type="hidden" name="status" value="<%=Constants.STATUS_VALID %>" /> 
				     <input type="hidden" name="organizationType" value="<%=organizationType %>" /> 
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
                                 <div class="form-group form-horizontal"> 
                                 	<input type="hidden" name="organizationLevel" id="organizationLevel" value="<%=bean.getOrganizationLevel()%>" />
                                   	<input type="hidden" name="province" id="province" value="<%=bean.getProvince()%>" />
  	   								<input type="hidden" name="city" id="city" value="<%=bean.getCity()%>"/>
  	  							    <input type="hidden" name="region" id="region" value="<%=bean.getRegion()%>"/>
                                    	<input readonly  type="text" class="form-control" value="<%=bean.getProvince()+bean.getCity()+bean.getRegion() %>" />
                                </div>
                            </td>
        				</tr>
        				
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>名称</td>
        					<td>
        						 <input id="organizationName" name="organizationName" type="text" class="form-control" value="<%=bean.getOrganizationName() %>" checkStr="部门名称;txt;true;;100"/>
        					</td>
        				</tr>
       
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>类型</td>
        					<td> 
        						<select name="organizationCategory" id="organizationCategory">
			<%for(StatusBean<String, String> gender:TrainingOrganizationConfig.ORGANIZATION_TYPE_LIST){ %>
			<option value="<%=gender.getStatus() %>" <%=(gender.getStatus().equals(bean.getOrganizationCategory()))?"selected":"" %>><%=gender.getValue() %></option><%} %>
								</select>  
        					</td>
        				</tr>
        				 
        				<tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>法人代表</td>
        					<td>
        						 <input id="representativeName" name="representativeName" type="text" class="form-control" value="<%=bean.getRepresentativeName() %>" checkStr="法人代表;txt;true;;100"/>
        					</td>
        				</tr>
        			 
        				 <tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>联系电话(固定电话带区号)</td>
        					<td>
        						 <input id="telephone" name="telephone" type="text" class="form-control" value="<%=bean.getTelephone() %>" checkStr="联系电话;txt;true;;100"/>
        					</td>
        				</tr>
        				
        				 <tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>通讯地址</td>
        					<td>
        						 <input id="address" name="address" type="text" class="form-control" value="<%=bean.getAddress() %>" checkStr="通讯地址;txt;true;;100"/>
        					</td>
        				</tr>
        				
        				 <tr>
        					<td class="tit"><span style="color: #ff0000;">*</span>邮政编码</td>
        					<td>
        						 <input id="zipCode" name="zipCode" type="text" class="form-control" value="<%=bean.getZipCode() %>" maxlength="6" checkStr="邮政编码;digit;true;3;6"/>
        					</td>
        				</tr>   
        			</tbody>
        		</table>
        		<div class="dialog_ft text_center">
        		<%
					if(TrainingOrganizationConfig.ORGANIZATION_TRAINING.equals(organizationType)){
						
			    %>
			    	<span style="font-size:14px;color: #ff0000;">注：培训机构名称中如果含有“局、委、办”字样，请确认是否为行政机构，行政机构不得承担培训任务！</span>
			    <%
					}
				%>
				<br/>
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
	  var mobile = /^1[3|4|5|7|8]\d{9}$/ ; //手机号码
	  var tel =   /^0\d{2,3}-\d{7,8}(-\d{1,6})?$/ ;  //电话号码 
	  var trainingYear = $("#trainingYear").val() ;
	  if(trainingYear <= 0){
		  alert("请选择正确的年份") ;
			$('#trainingYear').focus() ;
			return false ;
	  }
	  var telephone = $("#telephone").val() ;
	  if(!tel.test(telephone) && !mobile.test(telephone)){
			alert("请输入正确的联系方式,固定电话需带区号") ;
			$('#telephone').focus() ;
			return false ;
		}
	  return checkForm(name) ;
  }
  </script>
</body>
</html>