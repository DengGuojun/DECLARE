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
	List<TeacherDeclareBean> list = (List<TeacherDeclareBean>)request.getAttribute("TeacherDeclareList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper") ; 
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]>
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ />
    <![endif]–>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 师资审核</title>
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
        .table_fill td{ padding: 5px; }
        .table_fill .uploadImg{ padding: 5px 0; }
    </style>

</head>
<body class="body-index">
<%@include file="../nav/navigation.jsp" %>      	
        <div class="warpDefaultMgr">
            <table class="table_wrp">
                <tbody>
                   <tr>
                 <td style="width: 191px; background-color: #f2f2f2;" valign="top">
                            <%@include file="../include/nurturing_teachers_left.jsp" %>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">
                                        师资审核 
                                        </span>&nbsp;&nbsp;
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                     <form id="" name="" action="TeacherDeclareList.do" method="post">
                                        <div class="form-group form-horizontal">
                                        <label>姓名：</label>
                                        <input type="text" name="teacherName" class="form-control" value="<%=ParamKit.getParameter(request, "teacherName","") %>"/>
                                        </div>
                                        <div class="form-group form-horizontal">
                                        <label>身份证号码：</label>
                                        <input type="text" name="identityNumber" class="form-control" value="<%=ParamKit.getParameter(request, "identityNumber","") %>"/>
                                        </div>
                                        <div class="form-group form-horizontal">
                                        <label>手机号码：</label>
                                        <input type="text" name="phone" class="form-control" value="<%=ParamKit.getParameter(request, "phone","") %>"/>
                                        </div>
                                		<input type="submit" value="查 询" class="btn72" />
                                		</form>
                                        <div>
                                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse; width: 97%; ">
                                              <tbody>
                                                <tr class="gv_Head">
                                                  <th style="width:30px;">行号</th>
                                                  <th><a>姓名</a></th>
                                                  <th><a>性别</a></th>
                                                  <th><a>出生日期</a></th>
                                                  <th><a>职位名称</a></th>
                                                  <th><a>所属地区</a></th>
                                                  <th><a>工作单位</a></th>
                                                  <th><a>手机号码</a></th>
                                                  <th><a>审核状态</a></th>
                                                  <th></th>
                                                </tr>
                                                  <%
                                        			int rowCount = 1;
                                                    if(list.size() > 0){ 
									    			for(TeacherDeclareBean bean:list){ 
									    		 %>
                                                <tr class="gv_Item">
                                                  <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
                                                  <td><span><a href="TeacherDeclareManage.do?declareId=<%=bean.getDeclareId()%>"><%=bean.getTeacherName() %></a></span></td>
                                                  <td><span><%=MapKit.getValueFromMap(bean.getTeacherGender(),GenderConfig.GENDER_MAP) %></span></td>
                                                  <td><span><%=DateKit.formatDate(bean.getTeacherBirthday(), DateKit.DEFAULT_DATE_FORMAT)%></span></td>
                                                  <td><span><%=bean.getTechnicalTitle()%></span></td>
                                                  <td><span><%=bean.getProvince()+bean.getCity()+bean.getRegion() %></span></td>
                                                  <td><span><%=bean.getCompany() %></span></td>
                                                  <td><span><%=bean.getPhone() %></span></td>
                                                  <td><span><%=MapKit.getValueFromMap(bean.getDeclareStatus(),TeacherInfoConfig.DECLATE_STATUS_MAP) %></span></td>
                                                  <td><span>
                                                  <%
					                                if (adminUserHelper.hasPermission(DeclareAdminResource.TEACHER_INFO_SAME_LEVEL, OperationConfig.CREATE)) {
					                        	     %>
                                                  <%if(bean.getDeclareStatus().equals(TeacherInfoConfig.DECLATE_STATUS_COMMIT)){ %>
                                                  <a href="TeacherDeclareApprove.do?declareId=<%=bean.getDeclareId()%>&operation=<%=TeacherInfoConfig.APPROVE_OPERATION_PASS%>">通过</a>
                                                  <a href="TeacherDeclareApprove.do?declareId=<%=bean.getDeclareId()%>&operation=<%=TeacherInfoConfig.APPROVE_OPERATION_FAIL%>">不通过</a>
                                                  <%} %>
                                                  <%if(bean.getDeclareStatus().equals(TeacherInfoConfig.DECLATE_STATUS_APPROVED)){ %>
                                                  <a href="TeacherDeclareApprove.do?declareId=<%=bean.getDeclareId()%>&operation=<%=TeacherInfoConfig.APPROVE_OPERATION_REJECT%>">驳回</a>
                                                  <%} 
                                                  }%>
                                                  </span></td>
                                                </tr>
                                                <% rowCount++;}} %>
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
     <script src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</body>
</html>