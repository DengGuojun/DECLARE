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
    List<DeclareReportBean> list = (List<DeclareReportBean>)request.getAttribute("DeclareReportList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	Map<Integer, String> industryInfoMap = (Map<Integer, String>) request.getAttribute("industryInfoMap");
	TrainingClassInfoBean trainingClassInfoBean = (TrainingClassInfoBean)request.getAttribute("trainingClassInfoBean");
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
    <title>新型农民职业培训系统 — 培训班信息</title>
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
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;"><%=trainingClassInfoBean.getClassName()%>学员管理</span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <form name="formSearch" method="post" action="TrainingClassUserInfoManage.do">
                                    <input type="hidden" name="classId" id="classId" value="<%=trainingClassInfoBean.getClassId() %>"/>	
                                        <div class="form-group form-horizontal">
                                                                                                                            省：
                                           <select class="form-control" name="province" id="province">
                                               <option value="<%=trainingClassInfoBean.getProvince() %>"><%=trainingClassInfoBean.getProvince() %></option>
                                           </select>
                                                                                                                            市：
                                           <select class="form-control" name="city" id="city">
                                               <option value="<%=trainingClassInfoBean.getCity() %>"><%=trainingClassInfoBean.getCity() %></option>
                                           </select>
                                                                                                                            区：
                                           <select class="form-control" name="region" id="region">
                                               <option value="<%=trainingClassInfoBean.getRegion() %>"><%=trainingClassInfoBean.getRegion() %></option>
                                           </select>
                                        </div>
                                        <div class="form-group form-horizontal">
                                        	<label>状态：</label>
                                        	<select class="form-control" name="userStatus" id="userStatus">
										    <option value="" ></option>
										    	<%
										    	String status = ParamKit.getParameter(request, "userStatus", "");
										    	for(StatusBean<String, String> statusBean:TrainingClassUserConfig.USER_STATUS_LIST){ %>
										        <option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(status))?"selected":"" %>><%=statusBean.getValue() %></option>
										    <%} %>
										    </select>
                                        </div>
                                        <br/>
                                        <div class="form-group form-horizontal">
                                            <label>姓名：</label>
                                            <input type="text" class="form-control" name="userName" id="userName" value="<%=ParamKit.getParameter(request, "userName", "") %>" size="20"/>
                                        </div>
                                        <div class="form-group form-horizontal">
                                            <label>身份证号：</label>
                                            <input type="text" class="form-control" name="identityNumber" id="identityNumber" value="<%=ParamKit.getParameter(request, "identityNumber", "") %>" size="20"/>
                                        </div>
                                        <input type="submit" value="查 询" class="btn72" />
                                        <input type="button" value="导出" class="btn72" id="exportClassUser"/>
                                       </form>
                                        <div>
                                            <table class="gv js-gvHead" cellspacing="0" rules="all" border="1" style="border-collapse:collapse;">
                                                <tbody>
                                                    <tr class="gv_Head">
                                                        <th style="width:30px;">行号</th>
														      <th>地区</th>
														      <th>姓名</th>
														      <th>性别</th>
														      <th>文化程度</th>
														      <th>身份证号</th>
														      <%if(trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER || trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER || trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
														      <th>人员类别</th>
														      <%} %>
														      <th>手机号码</th>
														       <%if(trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER || trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER || trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
														      <th>主体产业</th>
														      <th>产业规模</th>
														       <%} %>
														      <th>状态</th>
														      <th>操作</th>
                                                    </tr>
                                                          <%
															//获取人员类别列表
															Map<String, String> PERSONNEL_CATEGORY_MAP = new LinkedHashMap<String, String>();
															if(trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER){
																PERSONNEL_CATEGORY_MAP = FarmerInfoConfig.PRODUCT_FARMER_PERSONNEL_CATEGORY_MAP;
															}else if(trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER){
																PERSONNEL_CATEGORY_MAP = FarmerInfoConfig.YOUNG_FARMER_PERSONNEL_CATEGORY_MAP;						
															}else if(trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){
																PERSONNEL_CATEGORY_MAP = FarmerInfoConfig.LEADER_FARMER_PERSONNEL_CATEGORY_MAP;
															}
															%>
														    <%int i=0;
														    for(DeclareReportBean bean:list){%> 
                                                    <tr class="gv_Item">
                                                        <td style="width:30px;">
                                                            <span style="display:inline-block;width:30px;"><%=(PAGE_BEAN.getCurrentPageNumber()-1)*20 + ++i %></span>
                                                        </td>
                                                        <td>
                                                            <span><%=bean.getRegion() %></span>
                                                        </td>
                                                      <td><span><%=bean.getUserName() %></span></td>
                                                      <td><span><%=MapKit.getValueFromMap(bean.getUserGender(), GenderConfig.GENDER_MAP) %></span></td>
												      <td><span><%=MapKit.getValueFromMap(bean.getEducation(), FarmerInfoConfig.EDUCATION_LEVEL_MAP) %></span></td>
												      <td><span><%=bean.getIdentityNumber() %></span></td>
												      <%if(trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER || trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER || trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
												      <td><span><%=MapKit.getValueFromMap(bean.getFarmerType(), PERSONNEL_CATEGORY_MAP) %></span></td>
												      <%} %>      
												      <td><span><%=bean.getUserMobile() %></span></td>
												      <%if(trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER || trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER || trainingClassInfoBean.getTrainingType() == DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER){ %>
												      <td><span><%=MapKit.getValueFromMap(bean.getIndustryId1(), industryInfoMap) %></span></td>
												      <td><span><%=bean.getIndustryScale1()%><%=bean.getIndustryUnit1() %></span></td>
												      <%} %> 
												      <%
												           boolean flag =false;
												           if(bean.getTrainingClassInfoList() != null && !bean.getTrainingClassInfoList().isEmpty()){ 
												             for(Integer calssInfo : bean.getTrainingClassInfoList()){
												            	 if(calssInfo == trainingClassInfoBean.getClassId()){
												            		 flag =true;
												            	 }
												             }
												           }
												           %>
												      <%if(flag){ %>
												      <td><span>已添加</span></td>
												      <td align="center">      
												       <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.UPDATE) && adminUserHelper.getAdminUserId() == trainingClassInfoBean.getCreateUser()) {
									                   %>	
												      	<a href="/declare/admin/TrainingClassUserCommit.do?declareId=<%=bean.getDeclareId()%>&classId=<%=trainingClassInfoBean.getClassId() %>&action=<%=TrainingClassUserConfig.COMMIT_ACTION_DELETE%>">删除</a> 
												       <%} %>
												      </td>
												      <%}else{ %>
												      <td><span>未添加</span></td>
												       <td align="center">   
												        <%
										                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.UPDATE) && adminUserHelper.getAdminUserId() == trainingClassInfoBean.getCreateUser()) {
									                   %>   	
												      	<a href="/declare/admin/TrainingClassUserCommit.do?declareId=<%=bean.getDeclareId()%>&classId=<%=trainingClassInfoBean.getClassId() %>&action=<%=TrainingClassUserConfig.COMMIT_ACTION_ADD%>">添加</a>
												       <%} %>
												      </td>
												      <%} %>
												    </tr>	
												    <%} %>
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- 页码 -->
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
    <script src="<%=STATIC_URL%>/js/Public.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/InsertFlash.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
</body>
<script>
$('#exportClassUser').on('click',function(){
	var classId = $('#classId').val();
    var userName = $('#userName').val();
    var identityNumber = $('#identityNumber').val();
    var userStatus = $('#userStatus').val();
    var pageNum = $('#pageNum').val();
    var pageSize = $('#pageSize').val();
    window.location.href='TrainingClassUserExport.do?userName='+userName+'&classId='+classId+'&identityNumber='+identityNumber+'&userStatus='+userStatus+'&pageNum='+pageNum+'&pageSize='+pageSize;

});
</script>
</html>