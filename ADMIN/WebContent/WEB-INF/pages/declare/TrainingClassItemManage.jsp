<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
    AdminUserHelper adminUserHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
    HashMap<String, List<TrainingClassItemBean>> trainingClassItemMap = (HashMap<String, List<TrainingClassItemBean>>)request.getAttribute("trainingClassItemMap");
    TrainingClassInfoBean trainingClassInfoBean = (TrainingClassInfoBean)request.getAttribute("trainingClassInfoBean");
    List<Integer> courseList = (List<Integer>)request.getAttribute("courseList");
    HashMap<Integer, TrainingClassItemBean> courseMap = (HashMap<Integer, TrainingClassItemBean>)request.getAttribute("courseMap");
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
    <title>新型农民职业培训系统 — 课程安排</title>
    <script type='text/javascript' src="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.js"></script>
    <link rel="stylesheet" href="<%=STATIC_URL %>/js/fancyBox/jquery.fancybox.css" type="text/css" media="screen" />
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
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

        .textGreenTop1
        {
            border-left: 0px solid #6893CF;
            border-right: 0px solid #6893CF;
            border-top: 0px solid #6893CF;
            border-bottom: 1px solid #ffd0d0;
        }
        .textGreen1
        {
            border-left: 1px solid #ffd0d0;
            border-right: 1px solid #ffd0d0;
            border-top: 1px solid #ffd0d0;
            border-bottom: 1px solid #ffd0d0;
        }
    
        .editView
        {
            text-align: center;
            vertical-align: middle;
        }
        .editView td
        {
            line-height: 30px;
        }
        .text
        {
            width: 90%;
            border: none;
            border-bottom: 1px solid gray;
            text-align:center;
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
                            <div class="detail_left">
                                <div class="detail_left_top"></div>
                                <div id="listItem" class="detail_left">
                                    <%
                                if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO, OperationConfig.SEARCH)) {
                        		%>
                        		     <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER %>">现代青年农场主培训</a>
                                    </div>
                                    <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER %>">新型农业经营主体带头人</a>
                                    </div>
                                    <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER %>">生产经营型职业农民培训</a>
                                    </div>
                                    <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER %>">专业技能型职业农民培训</a>
                                    </div>
                                    <div class="detail_left_box detail_box3">
                                        <a href="/declare/admin/TrainingClassInfoList.do?trainingType=<%=DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER %>">专业服务型职业农民培训</a>
                                    </div>
                        		<%	 
                        		}
                                %>
                                </div>
                            </div>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;"><%=trainingClassInfoBean.getClassName()%>课程安排</span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <form name="formSearch" method="post" action="TrainingClassItemManage.do">
                                    <input type="hidden" name="classId" id="classId" value="<%=trainingClassInfoBean.getClassId() %>"/>	
                                    <input type="hidden" name="addNumber" id="addNumber" value="20"/>	
                                    	<table class="editView" cellpadding="2" cellspacing="0" border="0" width="98%">
                                    	    <tbody>
                                    	        <tr>
                                    	            <td><span></span></td>
                                    	            <td><span></span></td>
                                    	            <td><span>必选</span></td>
                                    	            <td><span>课程</span></td>
                                    	            <td><span> 学时数<br>(选填)</span></td>
                                    	            <td><span> 培训教材<br>(必填)</span></td>
                                    	            <td><span>培训师资<br>(选填)</span>
                                    	            </td>
                                    	        </tr>
                                    	        <tr>
                                    	            <td rowspan="2"> 通<br> 用<br> 课<br>程</td>
                                    	            <td>综合性<br>课程</td>
                                    	            <td colspan="5">
                                    	                <table class="editView editView1" cellpadding="2" cellspacing="0" border="0" style="
                                    	                        width: 100%; height: 100%; margin: -1px 0;">
                                    	                    <tbody>
                                    	                       <%if(courseList.get(0)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_1" name="addIsRequiredCourse_1" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span >农民素养与现代生活</span></td>
                                    	                            <input type="hidden" id="addItemName_1" name="addItemName_1" value="农民素养与现代生活"/>	
                                    	                            <input type="hidden" id="addClassType_1" name="addClassType_1" value="<%=TrainingClassItemConfig.COURSE_COMPREHENSIVE %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_1" name="addclassHour_1"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_1" name="addTrainingMaterial_1"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_1" name="addTrainingTeacher_1"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(0) %>" name="isRequiredCourse_<%=courseList.get(0) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(0)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >农民素养与现代生活</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(0) %>" name="itemName_<%=courseList.get(0) %>" value="农民素养与现代生活"/>	
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(0) %>" name="classHour_<%=courseList.get(0) %>" value="<%=courseMap.get(courseList.get(0)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(0) %>" name="trainingMaterial_<%=courseList.get(0) %>" value="<%=courseMap.get(courseList.get(0)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(0) %>" name="trainingTeacher_<%=courseList.get(0) %>" value="<%=courseMap.get(courseList.get(0)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(1)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_2" name="addIsRequiredCourse_2" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">现代农业生产经营</span></td>
                                    	                            <input type="hidden" id="addItemName_2" name="addItemName_2" value="现代农业生产经营"/>	
                                    	                            <input type="hidden" id="addClassType_2" name="addClassType_2" value="<%=TrainingClassItemConfig.COURSE_COMPREHENSIVE %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_2" name="addclassHour_2"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_2" name="addTrainingMaterial_2"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_2" name="addTrainingTeacher_2"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(1) %>" name="isRequiredCourse_<%=courseList.get(1) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(1)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >现代农业生产经营</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(1) %>" name="itemName_<%=courseList.get(1) %>" value="现代农业生产经营"/>	
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(1) %>" name="classHour_<%=courseList.get(1) %>" value="<%=courseMap.get(courseList.get(1)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(1) %>" name="trainingMaterial_<%=courseList.get(1) %>" value="<%=courseMap.get(courseList.get(1)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(1) %>" name="trainingTeacher_<%=courseList.get(1) %>" value="<%=courseMap.get(courseList.get(1)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                       <%for(TrainingClassItemBean trainingClassItemBean :trainingClassItemMap.get(TrainingClassItemConfig.COURSE_COMPREHENSIVE)){
                                    	                           if(trainingClassItemBean.getItemId() != courseList.get(0) && trainingClassItemBean.getItemId() != courseList.get(1)){
                                    	                       %>                                  	                       
                                    	                       <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" name="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == trainingClassItemBean.getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><input type="text" class="text" id="itemName_<%=trainingClassItemBean.getItemId() %>" name="itemName_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getItemName()%>"></td>
                                    	                            <td><input type="text" class="text" id="classHour_<%=trainingClassItemBean.getItemId() %>" name="classHour_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" name="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" name="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                       <%   }
                                    	                         }%>                                   	                        
                                    	                        <tr>
                                    	                            <td><input type="button" value="+" class="AddRow" id="addRowComprehensive"></td>
                                    	                            <td><input type="text" class="text" id="addItemName_13" name="addItemName_13" ></td>
                                    	                            <input type="hidden" id="addClassType_13" name="addClassType_13" value="<%=TrainingClassItemConfig.COURSE_COMPREHENSIVE %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_13" name="addclassHour_13"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' id="addTrainingMaterial_13" name="addTrainingMaterial_13" readonly></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_13" name="addTrainingTeacher_13"></td>
                                    	                        </tr>
                                    	                    </tbody>
                                    	                </table>
                                    	            </td>
                                    	        </tr>
                                    	        <tr>
                                    	            <td> 专题性<br>课程</td>
                                    	            <td colspan="5">
                                    	                <table class="editView editView2" cellpadding="2" cellspacing="0" border="0" style="margin: -1px 0;
                                    	                        width: 100%; height: 100%;">
                                    	                    <tbody>
                                    	                        <%if(courseList.get(2)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_3" name="addIsRequiredCourse_3" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">现代农业创业</span></td>
                                    	                            <input type="hidden" id="addItemName_3" name="addItemName_3" value="现代农业创业"/>	
                                    	                            <input type="hidden" id="addClassType_3" name="addClassType_3" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_3" name="addclassHour_3"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_3" name="addTrainingMaterial_3"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_3" name="addTrainingTeacher_3"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(2) %>" name="isRequiredCourse_<%=courseList.get(2) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(2)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >现代农业创业</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(2) %>" name="itemName_<%=courseList.get(2) %>" value="现代农业创业"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(2) %>" name="classHour_<%=courseList.get(2) %>" value="<%=courseMap.get(courseList.get(2)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(2) %>" name="trainingMaterial_<%=courseList.get(2) %>" value="<%=courseMap.get(courseList.get(2)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(2) %>" name="trainingTeacher_<%=courseList.get(2) %>" value="<%=courseMap.get(courseList.get(2)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(3)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_4" name="addIsRequiredCourse_4" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">家庭农场经营管理</span></td>
                                    	                            <input type="hidden" id="addItemName_4" name="addItemName_4" value="家庭农场经营管理"/>	
                                    	                            <input type="hidden" id="addClassType_4" name="addClassType_4" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_4" name="addclassHour_4"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_4" name="addTrainingMaterial_4"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_4" name="addTrainingTeacher_4"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(3) %>" name="isRequiredCourse_<%=courseList.get(3) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(3)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >家庭农场经营管理</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(3) %>" name="itemName_<%=courseList.get(3) %>" value="家庭农场经营管理"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(3) %>" name="classHour_<%=courseList.get(3) %>" value="<%=courseMap.get(courseList.get(3)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(3) %>" name="trainingMaterial_<%=courseList.get(3) %>" value="<%=courseMap.get(courseList.get(3)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(3) %>" name="trainingTeacher_<%=courseList.get(3) %>" value="<%=courseMap.get(courseList.get(3)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(4)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_5" name="addIsRequiredCourse_5" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">农民合作社建设管理</span></td>
                                    	                            <input type="hidden" id="addItemName_5" name="addItemName_5" value="农民合作社建设管理"/>	
                                    	                            <input type="hidden" id="addClassType_5" name="addClassType_5" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_5" name="addclassHour_5"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_5" name="addTrainingMaterial_5"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_5" name="addTrainingTeacher_5"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(4) %>" name="isRequiredCourse_<%=courseList.get(4) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(4)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >农民合作社建设管理</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(4) %>" name="itemName_<%=courseList.get(4) %>" value="农民合作社建设管理"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(4) %>" name="classHour_<%=courseList.get(4) %>" value="<%=courseMap.get(courseList.get(4)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(4) %>" name="trainingMaterial_<%=courseList.get(4) %>" value="<%=courseMap.get(courseList.get(4)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(4) %>" name="trainingTeacher_<%=courseList.get(4) %>" value="<%=courseMap.get(courseList.get(4)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(5)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_6" name="addIsRequiredCourse_6" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">农产品电子商务</span></td>
                                    	                            <input type="hidden" id="addItemName_6" name="addItemName_6" value="农产品电子商务"/>	
                                    	                            <input type="hidden" id="addClassType_6" name="addClassType_6" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_6" name="addclassHour_6"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_6" name="addTrainingMaterial_6"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_6" name="addTrainingTeacher_6"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(5) %>" name="isRequiredCourse_<%=courseList.get(5) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(5)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >农产品电子商务</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(5) %>" name="itemName_<%=courseList.get(5) %>" value="农产品电子商务"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(5) %>" name="classHour_<%=courseList.get(5) %>" value="<%=courseMap.get(courseList.get(5)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(5) %>" name="trainingMaterial_<%=courseList.get(5) %>" value="<%=courseMap.get(courseList.get(5)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(5) %>" name="trainingTeacher_<%=courseList.get(5) %>" value="<%=courseMap.get(courseList.get(5)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(6)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_7" name="addIsRequiredCourse_7" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">智能手机应用</span></td>
                                    	                            <input type="hidden" id="addItemName_7" name="addItemName_7" value="智能手机应用"/>	
                                    	                            <input type="hidden" id="addClassType_7" name="addClassType_7" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_7" name="addclassHour_7"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_7" name="addTrainingMaterial_7"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_7" name="addTrainingTeacher_7"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(6) %>" name="isRequiredCourse_<%=courseList.get(6) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(6)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >智能手机应用</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(6) %>" name="itemName_<%=courseList.get(6) %>" value="智能手机应用"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(6) %>" name="classHour_<%=courseList.get(6) %>" value="<%=courseMap.get(courseList.get(6)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(6) %>" name="trainingMaterial_<%=courseList.get(6) %>" value="<%=courseMap.get(courseList.get(6)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(6) %>" name="trainingTeacher_<%=courseList.get(6) %>" value="<%=courseMap.get(courseList.get(6)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(7)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_8" name="addIsRequiredCourse_8" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">休闲农业与乡村旅游</span></td>
                                    	                            <input type="hidden" id="addItemName_8" name="addItemName_8" value="休闲农业与乡村旅游"/>	
                                    	                            <input type="hidden" id="addClassType_8" name="addClassType_8" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_8" name="addclassHour_8"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_8" name="addTrainingMaterial_8"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_8" name="addTrainingTeacher_8"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(7) %>" name="isRequiredCourse_<%=courseList.get(7) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(7)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >休闲农业与乡村旅游</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(7) %>" name="itemName_<%=courseList.get(7) %>" value="休闲农业与乡村旅游"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(7) %>" name="classHour_<%=courseList.get(7) %>" value="<%=courseMap.get(courseList.get(7)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(7) %>" name="trainingMaterial_<%=courseList.get(7) %>" value="<%=courseMap.get(courseList.get(7)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(7) %>" name="trainingTeacher_<%=courseList.get(7) %>" value="<%=courseMap.get(courseList.get(7)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(8)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_9" name="addIsRequiredCourse_9" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">农业支持保护政策</span></td>
                                    	                            <input type="hidden" id="addItemName_9" name="addItemName_9" value="农业支持保护政策"/>	
                                    	                            <input type="hidden" id="addClassType_9" name="addClassType_9" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_9" name="addclassHour_9"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_9" name="addTrainingMaterial_9"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_9" name="addTrainingTeacher_9"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(8) %>" name="isRequiredCourse_<%=courseList.get(8) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(8)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >农业支持保护政策</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(8) %>" name="itemName_<%=courseList.get(8) %>" value="农业支持保护政策"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(8) %>" name="classHour_<%=courseList.get(8) %>" value="<%=courseMap.get(courseList.get(8)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(8) %>" name="trainingMaterial_<%=courseList.get(8) %>" value="<%=courseMap.get(courseList.get(8)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(8) %>" name="trainingTeacher_<%=courseList.get(8) %>" value="<%=courseMap.get(courseList.get(8)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(9)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_10" name="addIsRequiredCourse_10" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">法律基础与农村法规</span></td>
                                    	                            <input type="hidden" id="addItemName_10" name="addItemName_10" value="法律基础与农村法规"/>	
                                    	                            <input type="hidden" id="addClassType_10" name="addClassType_10" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_10" name="addclassHour_10"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addItrainingMaterial_10" name="addItemName_10"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingMaterial_10" name="addItemName_10"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(9) %>" name="isRequiredCourse_<%=courseList.get(9) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(9)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >法律基础与农村法规</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(9) %>" name="itemName_<%=courseList.get(9) %>" value="法律基础与农村法规"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(9) %>" name="classHour_<%=courseList.get(9) %>" value="<%=courseMap.get(courseList.get(9)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(9) %>" name="trainingMaterial_<%=courseList.get(9) %>" value="<%=courseMap.get(courseList.get(9)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(9) %>" name="trainingTeacher_<%=courseList.get(9) %>" value="<%=courseMap.get(courseList.get(9)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(10)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_11" name="addIsRequiredCourse_11" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">农产品质量安全</span></td>
                                    	                            <input type="hidden" id="addItemName_11" name="addItemName_11" value="农产品质量安全"/>	
                                    	                            <input type="hidden" id="addClassType_11" name="addClassType_11" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_11" name="addclassHour_11"></td>
                                    	                           <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_11" name="addTrainingMaterial_11"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_11" name="addTrainingTeacher_11"></td>
                                    	                        </tr>
                                    	                        <%}else{ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(10) %>" name="isRequiredCourse_<%=courseList.get(10) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(10)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >农产品质量安全</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(10) %>" name="itemName_<%=courseList.get(10) %>" value="农产品质量安全"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(10) %>" name="classHour_<%=courseList.get(10) %>" value="<%=courseMap.get(courseList.get(10)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(10) %>" name="trainingMaterial_<%=courseList.get(10) %>" value="<%=courseMap.get(courseList.get(10)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(10) %>" name="trainingTeacher_<%=courseList.get(10) %>" value="<%=courseMap.get(courseList.get(10)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                        <%if(courseList.get(11)==0){ %>
                                    	                        <tr>
                                    	                            <td><input type="checkbox" id="addIsRequiredCourse_12" name="addIsRequiredCourse_12" value="<%=Constants.STATUS_VALID %>"></td>
                                    	                            <td><span id="ctl00_ContentPlaceHolder1_Label1_2SourceName">美丽乡村建设</span></td>
                                    	                            <input type="hidden" id="addItemName_12" name="addItemName_12" value="美丽乡村建设"/>	
                                    	                            <input type="hidden" id="addClassType_12" name="addClassType_12" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_12" name="addclassHour_12"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_12" name="addTrainingMaterial_12"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_12" name="addTrainingTeacher_12"></td>
                                    	                        </tr>
                                    	                       <%}else{ %>
                                    	                       <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=courseList.get(11) %>" name="isRequiredCourse_<%=courseList.get(11) %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == courseMap.get(courseList.get(11)).getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><span >美丽乡村建设</span></td>
                                    	                            <input type="hidden" id="itemName_<%=courseList.get(11) %>" name="itemName_<%=courseList.get(11) %>" value="美丽乡村建设"/>
                                    	                            <td><input type="text" class="text" id="classHour_<%=courseList.get(11) %>" name="classHour_<%=courseList.get(11) %>" value="<%=courseMap.get(courseList.get(11)).getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=courseList.get(11) %>" name="trainingMaterial_<%=courseList.get(11) %>" value="<%=courseMap.get(courseList.get(11)).getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=courseList.get(11) %>" name="trainingTeacher_<%=courseList.get(11) %>" value="<%=courseMap.get(courseList.get(11)).getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                        <%} %>
                                    	                       <%for(TrainingClassItemBean trainingClassItemBean :trainingClassItemMap.get(TrainingClassItemConfig.COURSE_SPECIAL)){
                                    	                    	   if(trainingClassItemBean.getItemId() != courseList.get(2) && trainingClassItemBean.getItemId() != courseList.get(3) && trainingClassItemBean.getItemId() != courseList.get(4)
                                    	                    			   && trainingClassItemBean.getItemId() != courseList.get(5) && trainingClassItemBean.getItemId() != courseList.get(6) && trainingClassItemBean.getItemId() != courseList.get(7)
                                    	                    			   && trainingClassItemBean.getItemId() != courseList.get(8) && trainingClassItemBean.getItemId() != courseList.get(9) && trainingClassItemBean.getItemId() != courseList.get(10)
                                    	                    			   && trainingClassItemBean.getItemId() != courseList.get(11)){
                                    	                       %>
                                    	                       <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" name="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == trainingClassItemBean.getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><input type="text" class="text" id="itemName_<%=trainingClassItemBean.getItemId() %>" name="itemName_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getItemName()%>"></td>
                                    	                            <td><input type="text" class="text" id="classHour_<%=trainingClassItemBean.getItemId() %>" name="classHour_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" name="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" name="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                       <% }
                                    	                       }%>                                   	                       
                                    	                        <tr>
                                    	                            <td><input type="button" value="+" class="AddRow"  id="addRowSpecial"></td>
                                    	                            <td><input type="text" class="text" id="addItemName_14" name="addItemName_14" ></td>
                                    	                            <input type="hidden" id="addClassType_14" name="addClassType_14" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_14" name="addclassHour_14"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_14" name="addTrainingMaterial_14"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_14" name="addTrainingTeacher_14"></td>
                                    	                        </tr>

                                    	                    </tbody>
                                    	                </table>
                                    	            </td>
                                    	        </tr>
                                    	        <tr>
                                    	            <td rowspan="7"> 专<br> 修<br>课<br>程</td>
                                    	            <td> 粮油<br>产业<br>课程</td>
                                    	            <td colspan="5">
                                    	                <table class="editView editView3" cellpadding="2" cellspacing="0" border="0" style="margin: -1px 0;
                                    	                        width: 100%; height: 100%;">
                                    	                    <tbody>
                                    	                    <%for(TrainingClassItemBean trainingClassItemBean :trainingClassItemMap.get(TrainingClassItemConfig.COURSE_GRAIN)){%>
                                    	                       <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" name="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == trainingClassItemBean.getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><input type="text" class="text" id="itemName_<%=trainingClassItemBean.getItemId() %>" name="itemName_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getItemName()%>"></td>
                                    	                            <td><input type="text" class="text" id="classHour_<%=trainingClassItemBean.getItemId() %>" name="classHour_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" name="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" name="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                       <%}%>
                                    	                        <tr>
                                    	                            <td><input type="button" value="+" class="AddRow" id="addRowGrain"></td>
                                    	                            <td><input type="text" class="text" id="addItemName_15" name="addItemName_15" ></td>
                                    	                            <input type="hidden" id="addClassType_15" name="addClassType_15" value="<%=TrainingClassItemConfig.COURSE_GRAIN %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_15" name="addclassHour_15"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_15" name="addTrainingMaterial_15"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_15" name="addTrainingTeacher_15"></td>
                                    	                        </tr>
                                    	                    </tbody>
                                    	                </table>
                                    	            </td>
                                    	        </tr>
                                    	        <tr>
                                    	            <td> 经作<br>产业<br>课程</td>
                                    	            <td colspan="5">
                                    	                <table class="editView editView4" cellpadding="2" cellspacing="0" border="0" style="margin: -1px 0;
                                    	                        width: 100%; height: 100%;">
                                    	                    <tbody>
                                    	                    <%for(TrainingClassItemBean trainingClassItemBean :trainingClassItemMap.get(TrainingClassItemConfig.COURSE_CASH)){%>
                                    	                       <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" name="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == trainingClassItemBean.getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><input type="text" class="text" id="itemName_<%=trainingClassItemBean.getItemId() %>" name="itemName_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getItemName()%>"></td>
                                    	                            <td><input type="text" class="text" id="classHour_<%=trainingClassItemBean.getItemId() %>" name="classHour_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" name="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" name="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                       <%}%>
                                    	                        <tr>
                                    	                            <td><input type="button" value="+" class="AddRow" id="addRowCash"></td>
                                    	                            <td><input type="text" class="text" id="addItemName_16" name="addItemName_16" ></td>
                                    	                            <input type="hidden" id="addClassType_16" name="addClassType_16" value="<%=TrainingClassItemConfig.COURSE_CASH %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_16" name="addclassHour_16"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_16" name="addTrainingMaterial_16"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_16" name="addTrainingTeacher_16"></td>
                                    	                        </tr>

                                    	                    </tbody>
                                    	                </table>
                                    	            </td>
                                    	        </tr>
                                    	        <tr>
                                    	            <td>园艺<br>产业<br>课程</td>
                                    	            <td colspan="5">
                                    	                <table class="editView editView5" cellpadding="2" cellspacing="0" border="0" style="margin: -1px 0;
                                    	                        width: 100%; height: 100%;">
                                    	                    <tbody>
                                    	                    <%for(TrainingClassItemBean trainingClassItemBean :trainingClassItemMap.get(TrainingClassItemConfig.COURSE_GARDENING)){%>
                                    	                       <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" name="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == trainingClassItemBean.getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><input type="text" class="text" id="itemName_<%=trainingClassItemBean.getItemId() %>" name="itemName_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getItemName()%>"></td>
                                    	                            <td><input type="text" class="text" id="classHour_<%=trainingClassItemBean.getItemId() %>" name="classHour_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" name="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" name="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                       <%}%>
                                    	                        <tr>
                                    	                            <td><input type="button" value="+" class="AddRow" id="addRowGardening"></td>
                                    	                            <td><input type="text" class="text" id="addItemName_17" name="addItemName_17" ></td>
                                    	                            <input type="hidden" id="addClassType_17" name="addClassType_17" value="<%=TrainingClassItemConfig.COURSE_GARDENING %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_17" name="addclassHour_17"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_17" name="addTrainingMaterial_17"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_17" name="addTrainingTeacher_17"></td>
                                    	                        </tr>

                                    	                    </tbody>
                                    	                </table>
                                    	            </td>
                                    	        </tr>
                                    	        <tr>
                                    	            <td>家畜<br>规模<br>养殖<br>课程</td>
                                    	            <td colspan="5">
                                    	                <table class="editView editView6" cellpadding="2" cellspacing="0" border="0" style="margin: -1px 0;
                                    	                        width: 100%; height: 100%;">
                                    	                    <tbody>
                                    	                      <%for(TrainingClassItemBean trainingClassItemBean :trainingClassItemMap.get(TrainingClassItemConfig.COURSE_CATTLE)){%>
                                    	                       <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" name="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == trainingClassItemBean.getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><input type="text" class="text" id="itemName_<%=trainingClassItemBean.getItemId() %>" name="itemName_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getItemName()%>"></td>
                                    	                            <td><input type="text" class="text" id="classHour_<%=trainingClassItemBean.getItemId() %>" name="classHour_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" name="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" name="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                       <%}%>
                                    	                        <tr>
                                    	                            <td><input type="button" value="+" class="AddRow" id="addRowCattle"></td>
                                    	                            <td><input type="text" class="text" id="addItemName_18" name="addItemName_18" ></td>
                                    	                            <input type="hidden" id="addClassType_18" name="addClassType_18" value="<%=TrainingClassItemConfig.COURSE_CATTLE %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_18" name="addclassHour_18"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_18" name="addTrainingMaterial_18"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_18" name="addTrainingTeacher_18"></td>
                                    	                        </tr>

                                    	                    </tbody>
                                    	                </table>
                                    	            </td>
                                    	        </tr>
                                    	        <tr>
                                    	            <td> 水产<br>养殖<br>课程</td>
                                    	            <td colspan="5">
                                    	                <table class="editView editView7" cellpadding="2" cellspacing="0" border="0" style="margin: -1px 0;
                                    	                        width: 100%; height: 100%;">
                                    	                    <tbody>
                                    	                    <%for(TrainingClassItemBean trainingClassItemBean :trainingClassItemMap.get(TrainingClassItemConfig.COURSE_AQUATIC)){%>
                                    	                       <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" name="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == trainingClassItemBean.getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><input type="text" class="text" id="itemName_<%=trainingClassItemBean.getItemId() %>" name="itemName_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getItemName()%>"></td>
                                    	                            <td><input type="text" class="text" id="classHour_<%=trainingClassItemBean.getItemId() %>" name="classHour_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" name="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" name="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                       <%}%>
                                    	                        <tr>
                                    	                            <td><input type="button" value="+" class="AddRow" id="addRowAquatic"></td>
                                    	                            <td><input type="text" class="text" id="addItemName_19" name="addItemName_19" ></td>
                                    	                            <input type="hidden" id="addClassType_19" name="addClassType_19" value="<%=TrainingClassItemConfig.COURSE_AQUATIC %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_19" name="addclassHour_19"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_19" name="addTrainingMaterial_19"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_19" name="addTrainingTeacher_19"></td>
                                    	                        </tr>

                                    	                    </tbody>
                                    	                </table>
                                    	            </td>
                                    	        </tr>
                                    	        <tr>
                                    	            <td>其它<br>产业<br>课程</td>
                                    	            <td colspan="5">
                                    	                <table class="editView editView8" cellpadding="2" cellspacing="0" border="0" style="margin: -1px 0;
                                    	                        width: 100%; height: 100%;">
                                    	                    <tbody>
                                    	                    <%for(TrainingClassItemBean trainingClassItemBean :trainingClassItemMap.get(TrainingClassItemConfig.COURSE_OTHER)){%>
                                    	                       <tr>
                                    	                            <td><input type="checkbox" id="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" name="isRequiredCourse_<%=trainingClassItemBean.getItemId() %>" value="<%=Constants.STATUS_VALID %>" <%=Constants.STATUS_VALID == trainingClassItemBean.getIsRequiredCourse() ? " checked" : ""%>></td>
                                    	                            <td><input type="text" class="text" id="itemName_<%=trainingClassItemBean.getItemId() %>" name="itemName_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getItemName()%>"></td>
                                    	                            <td><input type="text" class="text" id="classHour_<%=trainingClassItemBean.getItemId() %>" name="classHour_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getClassHour()%>"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" name="trainingMaterial_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingMaterial()%>"></td>
                                    	                            <td><input type="text" class="text" id="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" name="trainingTeacher_<%=trainingClassItemBean.getItemId() %>" value="<%=trainingClassItemBean.getTrainingTeacher() %>"></td>
                                    	                        </tr>
                                    	                       <%}%>
                                    	                        <tr>
                                    	                            <td><input type="button" value="+" class="AddRow" id="addRowOther"></td>
                                    	                            <td><input type="text" class="text" id="addItemName_20" name="addItemName_20" ></td>
                                    	                            <input type="hidden" id="addClassType_20" name="addClassType_20" value="<%=TrainingClassItemConfig.COURSE_OTHER %>"/>	
                                    	                            <td><input type="text" class="text" id="addclassHour_20" name="addclassHour_20"></td>
                                    	                            <td><input type="text" class="text" onclick='find(this)' readonly id="addTrainingMaterial_20" name="addTrainingMaterial_20"></td>
                                    	                            <td><input type="text" class="text" id="addTrainingTeacher_20" name="addTrainingTeacher_20"></td>
                                    	                        </tr>

                                    	                    </tbody>
                                    	                </table>
                                    	            </td>
                                    	        </tr>
                                    	    </tbody>
                                    	</table>
                                    	 <div style="text-align: center; margin: 20px 0 20px 0;">
                                    	 <%
						                   if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.UPDATE) && adminUserHelper.getAdminUserId() == trainingClassInfoBean.getCreateUser()) {
					                     %>
											<input type="submit" value="保 存"  name="submit" id="submit" id="save" class="btn72 js-save">
											<input type="button" value="导出" class="btn72" id="exportClassItem"/>
										<%} %>
										</div>
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
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
</body>   
<script>
function find(obj){
	$.fancybox.open({
		href : 'TrainingMaterialInfoSelect.do?classId=<%=trainingClassInfoBean.getClassId() %>&callbackFun=selectTrainingMaterialInfo&clickId='+obj.id,
		type : 'iframe',
		width : 560,
		minHeight : 500
});	
}
$('#exportClassItem').on('click',function(){
	var classId = $('#classId').val();
  window.location.href='TrainingClassItemExport.do'+'?classId='+classId;

});
function selectTrainingMaterialInfo(value,clickId) {
	$("#"+clickId).val(value);
};
//增加Row
$('#addRowComprehensive').click(function(){
	var addNumber = $('#addNumber').val();
	var add = parseFloat(addNumber)+1;
    $(this).parents('.editView1').append('<tr class="NewRow"><td><input type="button" value="-" class="DelRow"></td><td><input type="text" class="text" id="addItemName_'+add +'" name="addItemName_'+add +'" ></td><input type="hidden" id="addClassType_'+add +'" name="addClassType_'+add +'" value="<%=TrainingClassItemConfig.COURSE_COMPREHENSIVE %>"/><td><input type="text" class="text" id="addclassHour_'+add +'" name="addclassHour_'+add +'"></td><td><input type="text" class="text" onclick="find(this)" readonly id="addTrainingMaterial_'+add +'" name="addTrainingMaterial_'+add +'"></td><td><input type="text" class="text" id="addTrainingTeacher_'+add +'" name="addTrainingTeacher_'+add +'"></td></tr>');
    $('#addNumber').val(add);
    $('.DelRow').on('click',function(){
        $(this).parents('.NewRow').remove();
    }) 
})
$('#addRowSpecial').click(function(){
	var addNumber = $('#addNumber').val();
	var add = parseFloat(addNumber)+1;
    $(this).parents('.editView2').append('<tr class="NewRow"><td><input type="button" value="-" class="DelRow"></td><td><input type="text" class="text" id="addItemName_'+add +'" name="addItemName_'+add +'" ></td><input type="hidden" id="addClassType_'+add +'" name="addClassType_'+add +'" value="<%=TrainingClassItemConfig.COURSE_SPECIAL %>"/><td><input type="text" class="text" id="addclassHour_'+add +'" name="addclassHour_'+add +'"></td><td><input type="text" class="text" onclick="find(this)" readonly id="addTrainingMaterial_'+add +'" name="addTrainingMaterial_'+add +'"></td><td><input type="text" class="text" id="addTrainingTeacher_'+add +'" name="addTrainingTeacher_'+add +'"></td></tr>');
    $('#addNumber').val(add);
    $('.DelRow').on('click',function(){
        $(this).parents('.NewRow').remove();
    }) 
})
$('#addRowGrain').click(function(){
	var addNumber = $('#addNumber').val();
	var add = parseFloat(addNumber)+1;
    $(this).parents('.editView3').append('<tr class="NewRow"><td><input type="button" value="-" class="DelRow"></td><td><input type="text" class="text" id="addItemName_'+add +'" name="addItemName_'+add +'" ></td><input type="hidden" id="addClassType_'+add +'" name="addClassType_'+add +'" value="<%=TrainingClassItemConfig.COURSE_GRAIN %>"/><td><input type="text" class="text" id="addclassHour_'+add +'" name="addclassHour_'+add +'"></td><td><input type="text" class="text" onclick="find(this)" readonly id="addTrainingMaterial_'+add +'" name="addTrainingMaterial_'+add +'"></td><td><input type="text" class="text" id="addTrainingTeacher_'+add +'" name="addTrainingTeacher_'+add +'"></td></tr>');
    $('#addNumber').val(add);
    $('.DelRow').on('click',function(){
        $(this).parents('.NewRow').remove();
    }) 
})
$('#addRowCash').click(function(){
	var addNumber = $('#addNumber').val();
	var add = parseFloat(addNumber)+1;
    $(this).parents('.editView4').append('<tr class="NewRow"><td><input type="button" value="-" class="DelRow"></td><td><input type="text" class="text" id="addItemName_'+add +'" name="addItemName_'+add +'" ></td><input type="hidden" id="addClassType_'+add +'" name="addClassType_'+add +'" value="<%=TrainingClassItemConfig.COURSE_CASH %>"/><td><input type="text" class="text" id="addclassHour_'+add +'" name="addclassHour_'+add +'"></td><td><input type="text" class="text" onclick="find(this)" readonly id="addTrainingMaterial_'+add +'" name="addTrainingMaterial_'+add +'"></td><td><input type="text" class="text" id="addTrainingTeacher_'+add +'" name="addTrainingTeacher_'+add +'"></td></tr>');
    $('#addNumber').val(add);
    $('.DelRow').on('click',function(){
        $(this).parents('.NewRow').remove();
    }) 
})
$('#addRowGardening').click(function(){
	var addNumber = $('#addNumber').val();
	var add = parseFloat(addNumber)+1;
    $(this).parents('.editView5').append('<tr class="NewRow"><td><input type="button" value="-" class="DelRow"></td><td><input type="text" class="text" id="addItemName_'+add +'" name="addItemName_'+add +'" ></td><input type="hidden" id="addClassType_'+add +'" name="addClassType_'+add +'" value="<%=TrainingClassItemConfig.COURSE_GARDENING %>"/><td><input type="text" class="text" id="addclassHour_'+add +'" name="addclassHour_'+add +'"></td><td><input type="text" class="text" onclick="find(this)" readonly id="addTrainingMaterial_'+add +'" name="addTrainingMaterial_'+add +'"></td><td><input type="text" class="text" id="addTrainingTeacher_'+add +'" name="addTrainingTeacher_'+add +'"></td></tr>');
    $('#addNumber').val(add);
    $('.DelRow').on('click',function(){
        $(this).parents('.NewRow').remove();
    }) 
})
$('#addRowCattle').click(function(){
	var addNumber = $('#addNumber').val();
	var add = parseFloat(addNumber)+1;
    $(this).parents('.editView6').append('<tr class="NewRow"><td><input type="button" value="-" class="DelRow"></td><td><input type="text" class="text" id="addItemName_'+add +'" name="addItemName_'+add +'" ></td><input type="hidden" id="addClassType_'+add +'" name="addClassType_'+add +'" value="<%=TrainingClassItemConfig.COURSE_CATTLE %>"/><td><input type="text" class="text" id="addclassHour_'+add +'" name="addclassHour_'+add +'"></td><td><input type="text" class="text" onclick="find(this)" readonly id="addTrainingMaterial_'+add +'" name="addTrainingMaterial_'+add +'"></td><td><input type="text" class="text" id="addTrainingTeacher_'+add +'" name="addTrainingTeacher_'+add +'"></td></tr>');
    $('#addNumber').val(add);
    $('.DelRow').on('click',function(){
        $(this).parents('.NewRow').remove();
    }) 
})
$('#addRowAquatic').click(function(){
	var addNumber = $('#addNumber').val();
	var add = parseFloat(addNumber)+1;
    $(this).parents('.editView7').append('<tr class="NewRow"><td><input type="button" value="-" class="DelRow"></td><td><input type="text" class="text" id="addItemName_'+add +'" name="addItemName_'+add +'" ></td><input type="hidden" id="addClassType_'+add +'" name="addClassType_'+add +'" value="<%=TrainingClassItemConfig.COURSE_AQUATIC %>"/><td><input type="text" class="text" id="addclassHour_'+add +'" name="addclassHour_'+add +'"></td><td><input type="text" class="text" onclick="find(this)" readonly id="addTrainingMaterial_'+add +'" name="addTrainingMaterial_'+add +'"></td><td><input type="text" class="text" id="addTrainingTeacher_'+add +'" name="addTrainingTeacher_'+add +'"></td></tr>');
    $('#addNumber').val(add);
    $('.DelRow').on('click',function(){
        $(this).parents('.NewRow').remove();
    }) 
})
$('#addRowOther').click(function(){
	var addNumber = $('#addNumber').val();
	var add = parseFloat(addNumber)+1;
    $(this).parents('.editView8').append('<tr class="NewRow"><td><input type="button" value="-" class="DelRow"></td><td><input type="text" class="text" id="addItemName_'+add +'" name="addItemName_'+add +'" ></td><input type="hidden" id="addClassType_'+add +'" name="addClassType_'+add +'" value="<%=TrainingClassItemConfig.COURSE_OTHER %>"/><td><input type="text" class="text" id="addclassHour_'+add +'" name="addclassHour_'+add +'"></td><td><input type="text" class="text" onclick="find(this)" readonly id="addTrainingMaterial_'+add +'" name="addTrainingMaterial_'+add +'"></td><td><input type="text" class="text" id="addTrainingTeacher_'+add +'" name="addTrainingTeacher_'+add +'"></td></tr>');
    $('#addNumber').val(add);
    $('.DelRow').on('click',function(){
        $(this).parents('.NewRow').remove();
    }) 
})
</script>
</html>