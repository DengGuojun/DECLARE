<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
    AdminUserHelper adminUserHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
    double effectScore = (Double)request.getAttribute("EffectScore");
    int effectScoreCount = (Integer)request.getAttribute("EffectScoreCount");
    double organizeScore = (Double)request.getAttribute("OrganizeScore");
    int organizeScoreCount = (Integer)request.getAttribute("OrganizeScoreCount");
    Map<Object,Object> itemNameMap =  (Map<Object,Object>)request.getAttribute("EvaluateItemNameMap");
	HashMap<Integer, Double> itemTotalScoreMap =  (HashMap<Integer, Double>)request.getAttribute("EvaluateItemTotalScoreMap");
	HashMap<Integer, Integer> itemTotalCountMap =  (HashMap<Integer, Integer>)request.getAttribute("EvaluateItemTotalCountMap");
    TrainingClassInfoBean trainingClassInfoBean = (TrainingClassInfoBean)request.getAttribute("TrainingClassInfoBean");
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
    <title>新型农民职业培训系统 — 培训班评教信息</title>
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
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;"><%=trainingClassInfoBean.getClassName()%>考核评教</span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                        <div>
                                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                                <tbody>
                                                    <tr class="gv_Head">
												      <th>课程</th>
												      <th>综合得分</th>
												      <th>评教人数</th>
												      <th></th>
                                                    </tr>
                                                    <tr class="gv_Item">
                                                       <td><%=MapKit.getValueFromMap(TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_EFFECT, TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_MAP)%></td>
                                                       <td><%=effectScore%></td>
                                                       <td><%=effectScoreCount%></td>
                                                       <td><a style="cursor:pointer;" href="TrainingClassEvaluateManage.do?classId=<%=trainingClassInfoBean.getClassId()%>&evaluateType=<%=TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_EFFECT %>" >详情</a></td>
												    </tr>
												     <tr class="gv_Item">
												      <td><%=MapKit.getValueFromMap(TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_ORGANIZE, TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_MAP)%></td>
                                                       <td><%=organizeScore%></td>
                                                       <td><%=organizeScoreCount%></td>
                                                       <td><a style="cursor:pointer;" href="TrainingClassEvaluateManage.do?classId=<%=trainingClassInfoBean.getClassId()%>&evaluateType=<%=TrainingClassInfoConfig.CLASS_EVALUATE_TYPE_ORGANIZE %>" >详情</a></td>
												    </tr>
												    <%for(Object itemId : itemNameMap.keySet()) {%>
												    <tr>
												    	  <td><%=itemNameMap.get(itemId)%></td>
                                                       <td><%=itemTotalScoreMap.get(itemId) != null ? NumeralOperationKit.divide(itemTotalScoreMap.get(itemId), (Integer)itemTotalCountMap.get(itemId), 2): 0%></td>
                                                       <td><%=itemTotalCountMap.get(itemId) != null ? (Integer)itemTotalCountMap.get(itemId) : 0%></td>
                                                       <td><a style="cursor:pointer;" href="TrainingClassItemEvaluateList.do?classId=<%=trainingClassInfoBean.getClassId()%>&itemId=<%=itemId %>" >详情</a></td>
												    </tr>
												    <%} %>	
                                                </tbody>
                                            </table>
                                        </div>
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
</html>