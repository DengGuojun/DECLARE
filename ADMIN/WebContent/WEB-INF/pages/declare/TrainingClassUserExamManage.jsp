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
    List<TrainingClassUserBean> list = (List<TrainingClassUserBean>)request.getAttribute("trainingClassUserList");
    Map<Integer, DeclareReportBean> declareReportMap= (Map<Integer, DeclareReportBean>)request.getAttribute("declareReportMap");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
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
    <title>新型农民职业培训系统 — 考核记录</title>
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
                                        <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;"><%=trainingClassInfoBean.getClassName()%>考核记录</span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    <input type="hidden" name="classId" id="classId" value="<%=trainingClassInfoBean.getClassId() %>"/>	
                                    	<table class="gv js-gvHead">
                                    	    <tbody>
                                    	        <tr class="gv_Head">
                                    	            <th rowspan="2">行号</th>
                                    	            <th rowspan="2">学生姓名</th>
                                    	            <th rowspan="2">性别</th>
                                    	            <th rowspan="2">文化程度</th>
                                    	            <th rowspan="2">身份证号</th>
                                    	            <th rowspan="2">联系电话</th>
                                    	            <th colspan="2">考核结果</th>
                                    	            <th colspan="2">是否获得培训证书</th>
                                    	        </tr>
                                    	        <tr class="gv_Head">
                                    	            <th>
                                    	                合格
                                    	                <br>
                                    	                <input type="checkbox" id="checkAllExamResultApprove">
                                    	                <label for="ctl00_ContentPlaceHolder1_CheckBoxResultYesAll">全选</label>
                                    	            </th>
                                    	            <th>
                                    	                不合格
                                    	                <br>
                                    	                <input type="checkbox" id="checkAllExamResultNotApprove">
                                    	                <label for="ctl00_ContentPlaceHolder1_CheckBoxResultNoAll">全选</label>
                                    	            </th>
                                    	            <th>
                                    	                是
                                    	                <br>
                                    	                <input type="checkbox" id="checkAllHasCertification">
                                    	                <label for="ctl00_ContentPlaceHolder1_CheckBoxYesAll">全选</label>
                                    	            </th>
                                    	            <th>
                                    	                否
                                    	                <br>
                                    	                <input type="checkbox" id="checkAllNotHasCertification">
                                    	                <label for="ctl00_ContentPlaceHolder1_CheckBoxNoAll">全选</label>
                                    	            </th>
                                    	        </tr>
                                    	         <%int rowCount = 1;
									    for(TrainingClassUserBean bean:list){%> 
									    <%DeclareReportBean declareReportBean = declareReportMap.get(bean.getDeclareId()); %>
									     <tr class="gv_Item">
									      <td style="width:30px;"><span style="display:inline-block;width:30px;"><%=rowCount %></span></td>
									      <td><span><%=declareReportBean.getUserName() %></span></td>									      
									      <td><span><%=MapKit.getValueFromMap(declareReportBean.getUserGender(), GenderConfig.GENDER_MAP) %></span></td>
									      <td><span><%=MapKit.getValueFromMap(declareReportBean.getEducation(), FarmerInfoConfig.EDUCATION_LEVEL_MAP) %></span></td>
									      <td><span><%=declareReportBean.getIdentityNumber() %></span></td>
									      <td><span><%=declareReportBean.getUserMobile() %></span></td>
									      <td>
									      <input type="checkbox" name="subBox" id="examResultApprove_<%=bean.getDeclareId() %>" <%=Constants.STATUS_VALID == bean.getExamResult() ? "checked" : ""%> value="<%=bean.getDeclareId() %>"/>
									      </td>
									      <td>
									      <input type="checkbox" name="subBox2"id="examResultNotApprove_<%=bean.getDeclareId() %>" <%=Constants.STATUS_NOT_VALID == bean.getExamResult() ? "checked" : ""%> value="<%=bean.getDeclareId() %>">
									      </td>
									      <td><input type="checkbox" name="subBox3" id="hasCertification_<%=bean.getDeclareId() %>" <%=Constants.STATUS_VALID == bean.getHasCertification() ? "checked" : ""%> value="<%=bean.getDeclareId() %>"></td>
									      <td><input type="checkbox" name="subBox4" id="hasNotCertification_<%=bean.getDeclareId() %>" <%=Constants.STATUS_NOT_VALID == bean.getHasCertification() ? "checked" : ""%> value="<%=bean.getDeclareId() %>"></td>
									    </tr>	
									    <%rowCount++;}%>
                                    	    </tbody>
                                    	</table>
                                    <div style="text-align: center; margin: 20px 0 20px 0;">
                                    <%
					                 if (adminUserHelper.hasPermission(DeclareAdminResource.TRAINING_CLASS_INFO,OperationConfig.UPDATE)
					                		 && adminUserHelper.getAdminUserId() == trainingClassInfoBean.getCreateUser()) {
				                   %>
										<input type="button" value="保 存"  class="btn72 js-save" id="save">
									<%}%>
									</div>
                                    </div>
                                    <!-- 页码 -->
                                    <%@ include file="../include/page.jsp" %>
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
$(function() {
    $("#checkAllExamResultApprove").click(function() {
         $('input[name="subBox"]').attr("checked",this.checked); 
         if(this.checked == true){
        	 $('input[name="subBox2"]').attr("checked",false);
        	 $("#checkAllExamResultNotApprove").attr("checked",false);
         }
     });
     var $subBox = $("input[name='subBox']");
     $subBox.click(function(){
         $("#checkAllExamResultApprove").attr("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false);
     });
     $("#checkAllExamResultNotApprove").click(function() {
         $('input[name="subBox2"]').attr("checked",this.checked); 
         if(this.checked == true){
        	 $('input[name="subBox"]').attr("checked",false);
        	 $("#checkAllExamResultApprove").attr("checked",false);
         }
     });
     var $subBox2 = $("input[name='subBox2']");
     $subBox2.click(function(){
         $("#checkAllExamResultNotApprove").attr("checked",$subBox2.length == $("input[name='subBox2']:checked").length ? true : false);
     });
     $("#checkAllHasCertification").click(function() {
         $('input[name="subBox3"]').attr("checked",this.checked); 
         if(this.checked == true){
        	 $('input[name="subBox4"]').attr("checked",false);
        	 $("#checkAllNotHasCertification").attr("checked",false);
         }
     });
     var $subBox3 = $("input[name='subBox3']");
     $subBox3.click(function(){
         $("#checkAllHasCertification").attr("checked",$subBox3.length == $("input[name='subBox3']:checked").length ? true : false);
     });
     $("#checkAllNotHasCertification").click(function() {
         $('input[name="subBox4"]').attr("checked",this.checked); 
         if(this.checked == true){
        	 $('input[name="subBox3"]').attr("checked",false);
        	 $("#checkAllHasCertification").attr("checked",false);
         }
     });
     var $subBox4 = $("input[name='subBox4']");
     $subBox4.click(function(){
         $("#checkAllNotHasCertification").attr("checked",$subBox4.length == $("input[name='subBox4']:checked").length ? true : false);
     });
     $("#save").click(function() {
    	  var checkStrexamResultApprove="";
    	  $("input[name='subBox']:checked").each(function () {
              if(checkStrexamResultApprove!="")    
              {    
            	  checkStrexamResultApprove+=",";    
               }    
              checkStrexamResultApprove += $(this).val(); 
          });	
    	  var checkStrexamResultNotApprove="";
    	  $("input[name='subBox2']:checked").each(function () {
              if(checkStrexamResultNotApprove!="")    
              {    
            	  checkStrexamResultNotApprove+=",";    
               }    
              checkStrexamResultNotApprove += $(this).val(); 
          });
    	  var hasCertification="";
    	  $("input[name='subBox3']:checked").each(function () {
              if(hasCertification!="")    
              {    
            	  hasCertification+=",";    
               }    
              hasCertification += $(this).val(); 
          });
    	  var hasNotCertification="";
    	  $("input[name='subBox4']:checked").each(function () {
              if(hasNotCertification!="")    
              {    
            	  hasNotCertification+=",";    
               }    
              hasNotCertification += $(this).val(); 
          });
    	  var classId = $('#classId').val();
    	  $.ajax({
              url: 'TrainingClassUserExamInsert.do',
              data: {
            	  checkStrexamResultApprove: checkStrexamResultApprove,
            	  checkStrexamResultNotApprove: checkStrexamResultNotApprove,
            	  hasCertification: hasCertification,
            	  hasNotCertification: hasNotCertification,
            	  classId:classId,
              },
              type: 'POST',
              success: function (json) {
                  console.log(json);
                  if (json.code == '1') {
                	  alert(json.message);
                      window.location = 'TrainingClassInfoList.do?trainingType='+<%=trainingClassInfoBean.getTrainingType() %>
                  }
                  else {
                      alert(json.message);
                  }
              }
          })
 	});
 });
</script>
</html>