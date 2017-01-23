<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>

<%
AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminHelper");
	MajorInfoBean bean = (MajorInfoBean)request.getAttribute("MajorInfoBean") ; 
	List<MajorTypeBean> majorTypeList = (List<MajorTypeBean>)request.getAttribute("MajorTypeList") ;
	
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
    <title>新型农民职业培训系统 — 详细页面</title>
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
     <form id="formData" name="formData" method="post" action="MajorInfoManage.do" onsubmit="javascript:return checkForm('formData');">
          <%-- 判断是否修改或者新增信息 --%>
          <input type="hidden" name="majorId" id="majorId" value=<%=bean.getMajorId() %> />
           
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
                                      <span style="float: left;">             
                                        <%if(bean.getMajorId()>0) {%>
                                      	编辑专业
                                      <%} else{%>
                                      	新增专业
                                      <%} %></span>
                                     </div>
                                    <div class="right_bg" style="padding-left: 5px;" >
                                      <div>
                                        <table class="editView table_wrp gv" style="width: 98%">
                                            <tbody>
                                                <tr>
                                         
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>专业类型</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                       <select name="typeId" id="typeId" >
												      	<%for(MajorTypeBean MajorTypeBean : majorTypeList){ %><option value="<%=MajorTypeBean.getMajorId()%>" <%=(MajorTypeBean.getMajorId() == bean.getTypeId())?"selected":"" %>><%=MajorTypeBean.getMajorName() %></option><%} %>
												     </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>专业名称</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                        <input id="majorName"  name="majorName" type="text" value="<%=bean.getMajorName() %>" maxlength="60" checkStr="专业名称;txt;true;;100" class="textEdit" style="width:100%;"></td>
                                                </tr>
                                                <tr>
                                                 <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>有效状态</span>
                                                    </td>
                                        
                                                   <td align="left" class="td_data" style="width:160px;">
                                                             <select  name="status" id="status" >
													      	<%for(StatusBean<Integer, String> statusBean : Constants.STATUS_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(bean.getStatus()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
													       </select>
                                                        </td>
                                               
                                                </tr>
                                               
                                            </tbody>
                                        </table>
                                        <div class="text_center mt_5" style="width: 98%">
                                            <input type="submit" value="保 存" class="btn72" />
                                            <input type="button" class="btn72" value="取消" onclick="javascript:history.back()">
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