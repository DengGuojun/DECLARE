<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
List<TrainingClassUserConfigBean> list = (List<TrainingClassUserConfigBean>)request.getAttribute("TrainingClassUserConfigList");
AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper") ;
PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
int type = ParamKit.getIntParameter(request, "type", 0) ;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 评价列表</title>
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
                                      <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">地区录入列表</span></div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    
                                    <form name="formSearch" method="post" action="TrainingClassUserConfigList.do">
                                      <div class="form-group form-horizontal">
                                      	<input type="hidden" value="direct" name="direct"> 
                                      	<%-- 判断是什么查询 --%>
                                      	<% if(type==0){
                                      		//查询的地区信息 
                                      	%>
				                        <label>省份：</label>
				                        <input type="hidden" name="type" value="0">
										<input type="text" name="province" id="province" value="<%=ParamKit.getParameter(request, "province", "") %>" size="20" class="form-control">        	
                                      	<%	
                                      		} else if(type ==1){
                                      	%>
                                      		<label>城市：</label>
                                      	    <input type="hidden" name="type" value="1">
                                      		<input type="hidden" value="<%=ParamKit.getParameter(request, "province","") %>" name="province">
                                      		<input type="text" name="city" id="city" value="<%=ParamKit.getParameter(request, "city", "") %>" size="20" class="form-control">        	
                                      	<%		
                                      		}else if(type==2){
                                      	%>
                                      			 <label>地区：</label>
                                      			 <input type="hidden" name="type" value="2">
                                      	    <input type="hidden" value="<%=ParamKit.getParameter(request, "province","") %>" name="province">
                                      		<input type="hidden" value="<%=ParamKit.getParameter(request, "city","") %>" name="city">
                                      		<input type="text" name="region" id="region" value="<%=ParamKit.getParameter(request, "region", "") %>" size="20" class="form-control">        	
                                      	<%		
                                      		}
                                      	%>
                                       
                                      </div>
                                      	<% 
                                      		if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG, OperationConfig.SEARCH)) {
                                		%>
                                		<input type="submit" value="查 询" class="btn72" />
                                		<% 	 
                                			}
                                      	%>
										<% 
                                      		if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG, OperationConfig.CREATE)||adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_USER_CONFIG, OperationConfig.CREATE)) {
                                		%>
                                		<input type="button" value="设置" class="btn72" onclick="javascript:location.href='TrainingClassUserConfigManage.do'"/>
                                		<% 	 
                                			}
                                      	%>	
                                      	<%
                                      		if(type > 0){
                                      	%>	
                                      		<input type="button" value="返回" class="btn72" onclick="javascript:history.back()"/>
                                      	<%
                                      		}
                                      	%>
									</form>
                                      <div>
                                        <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                          <tbody>
                                            <tr class="gv_Head">
                                              <th style="width:30px;">行号</th>
                                          	  <th>地区</th>
                                              <th>录入频率</th>
                                            </tr>
                                             <%
                                        	int rowCount = 1;
									    	for(TrainingClassUserConfigBean bean:list){ 
									    	 %>
									    	 
									    	<%
									    	if(type==0){
									    	%> 
									    	 <tr class="gv_Item">
                                              <td style="width:30px;"> <span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                              <td>
                                               <a href="TrainingClassUserConfigList.do?province=<%=bean.getProvince()%>&type=1"><%=bean.getProvince() %></a>
                                              </td>
                                              <td>
                                            	<%=MapKit.getValueFromMap(bean.getConfigFrequency(), TrainingClassUserConfig.CONFIG_FREQUENCY_MAP) %>
                                             </td>
                                            </tr> 
									    	<%	
									    	rowCount++;
									    	%>
									    	
									    	<% 
									    	  }else if(type==1){
									    		if(StringKit.isValid(bean.getCity())){
									    	%>
									    	  <tr class="gv_Item">
                                              <td style="width:30px;"> <span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                              <td><a href="TrainingClassUserConfigList.do?type=2&province=<%=bean.getProvince()%>&city=<%=bean.getCity()%>"><%=bean.getCity()%></a></td>
                                              <td>
                                            	<%=MapKit.getValueFromMap(bean.getConfigFrequency(), TrainingClassUserConfig.CONFIG_FREQUENCY_MAP) %>
                                             </td>
                                            </tr>
									    	<% 	rowCount++;}
									    	
									    	%>
									    	
									    	<%
									    	}else if(type==2){
									    		if(StringKit.isValid(bean.getRegion())){
									    	%>
									    	 <tr class="gv_Item">
                                              <td style="width:30px;"> <span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                              <td><%=bean.getRegion() %></td>
                                              <td>
                                            	<%=MapKit.getValueFromMap(bean.getConfigFrequency(), TrainingClassUserConfig.CONFIG_FREQUENCY_MAP) %>
                                             </td>
                                            </tr> 
									    	<%	
									    		rowCount++;
									    	}
									    		%>
									    		
									       <%
									    	 }
									    	%> 
									    	<% }
                                      %>
                                          </tbody>
                                        </table>
                                      </div>
                                    <%@ include file="../include/page.jsp" %>
                                    </div>
                                   
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    	<div class="contents-footer"></div>
     <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>

</body>
</html>