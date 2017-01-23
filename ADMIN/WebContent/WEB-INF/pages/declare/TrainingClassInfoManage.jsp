<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.business.*"  %>
<% 
AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
TrainingClassInfoBean bean = (TrainingClassInfoBean)request.getAttribute("TrainingClassInfoBean");
List<MajorTypeBean> majorTypeList = (List<MajorTypeBean>)request.getAttribute("majorTypeList");
List<TrainingOrganizationInfoBean> trainingOrganizationInfoList = (List<TrainingOrganizationInfoBean>)request.getAttribute("trainingOrganizationInfoList");
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
                                    <% if(bean.getClassId() > 0) {%>
									<span style="float: left;">修改培训班信息</span>
									<%}else{ %>
									<span style="float: left;">新建培训班信息</span>
									<%}%>                                    
                                    </div>
                                    <div class="right_bg">
                                      <div>
                                       <form id="formData" name="formData" method="post" action="TrainingClassInfoManage.do" onsubmit="javascript:return formOnSubmit();">
										  <input type="hidden" name="classId" id="classId" value="<%=bean.getClassId() %>"/>
										   <input type="hidden" name="trainingType" id="trainingType" value="<%=bean.getTrainingType() %>"/>
										   <input type="hidden" name="authOrganizationId" id="authOrganizationId" value="<%=bean.getAuthOrganizationId() %>"/>
										   <input type="hidden" name="authStatus" id="authStatus" value="<%=bean.getAuthStatus() %>"/>
										   <input type="hidden" name="authTime" id="authTime" value="<%=bean.getAuthTime() %>"/>
										   <input type="hidden" name="syncStatus" id="syncStatus" value="<%=bean.getSyncStatus() %>"/>
										   <input type="hidden" name="status" id="status" value="<%=bean.getStatus() %>"/>
                                        <table class="editView" style="width: 98%">
                                            <tbody>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>项目年度</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                    <select style="background-color:White;" name="trainingYear" id="trainingYear">
								                         <%for(Integer InfoYear : declareInfoHelper.getTrainingYearList()){ %>
									                     <option value="<%=InfoYear %>" <%=bean.getTrainingYear().equals(String.valueOf(InfoYear)) ? "selected" : ""%>><%=InfoYear %></option>
							                             <%}%>	
			                                         </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>培训单位</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                        <select style="background-color:White;" name="organizationId" id="organizationId" checkStr="培训单位;txt;true;;50">
														<option value="" ></option>
														<%
																for (TrainingOrganizationInfoBean trainingOrganizationInfoBean : trainingOrganizationInfoList) {
															%><option value="<%=trainingOrganizationInfoBean.getOrganizationId() %>"
																<%=trainingOrganizationInfoBean.getOrganizationId() == bean.getOrganizationId() ? "selected" : ""%>><%=trainingOrganizationInfoBean.getOrganizationName() %></option>
															<%
																}
															%>
														</select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>培训班名称</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                        <input type="text" name="className" id="className" maxlength="60" class="textEdit" style="width:100%;" value="<%=bean.getClassName() %>" checkStr="培训班名称;txt;true;;50"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>培训产业</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                        <select style="background-color:White;" name="majorTypeId" id="majorTypeId" checkStr="培训产业;txt;true;;50">
                                                            <option value="" ></option>
															<%
																for (MajorTypeBean majorTypeBean : majorTypeList) {
															%><option value="<%=majorTypeBean.getMajorId() %>"
																<%=majorTypeBean.getMajorId() == bean.getMajorTypeId() ? "selected" : ""%>><%=majorTypeBean.getMajorName() %></option>
															<%
																}
															%>
                                                        </select>
                                                        <input type="hidden" class="major" id= "major" value="<%=bean.getMajorId() %>">
                                                        <select style="background-color:White;" id="majorId" name="majorId" checkStr="培训产业;txt;true;;50"></select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>培训人数</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                        <input type="text" name="classPeopleQuantity" id="classPeopleQuantity" maxlength="60" class="textEdit" style="width:100%;"value="<%=bean.getClassPeopleQuantity() >0 ? bean.getClassPeopleQuantity() : ""%>" checkStr="培训人数;digit;true;;50"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>培训开始时间</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                        <input type="text" name="trainingBeginTime" id="trainingBeginTime" maxlength="60"   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="textEdit" style="width:100%;"value="<%=bean.getTrainingBeginTime() !=null ? DateKit.formatTimestamp(bean.getTrainingBeginTime(), DateKit.DEFAULT_DATE_TIME_FORMAT): ""%>"   checkStr="培训开始时间;txt;true;;50"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>培训结束时间</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                        <input type="text" name="trainingEndTime" id="trainingEndTime" maxlength="60"   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="textEdit" style="width:100%;"value="<%=bean.getTrainingEndTime() !=null ? DateKit.formatTimestamp(bean.getTrainingEndTime(), DateKit.DEFAULT_DATE_TIME_FORMAT): ""%>"   checkStr="培训结束时间;txt;true;;50"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>培训班报名截止时间</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                        <input type="text" name="registrationEndTime" id="registrationEndTime" maxlength="60"   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  class="textEdit" style="width:100%;"value="<%=bean.getRegistrationEndTime() !=null ? DateKit.formatTimestamp(bean.getRegistrationEndTime(), DateKit.DEFAULT_DATE_TIME_FORMAT): ""%>"  DateKit checkStr="报名截止时间;txt;true;;50"/>
                                                    </td>
                                                </tr>
                                                <% if(bean.getClassId() > 0) {%>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>项目实施第一年</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                         <%List<TrainingItemContentBean> contentList1 = JsonKit.toList(bean.getTrainingItem1(), TrainingItemContentBean.class);
													      	Map<String,String> contentMap1 = ListKit.list2Map(contentList1, "key","value");%>
													      <input type="checkbox" name="education1" id="education1" value="<%=Constants.STATUS_VALID %>" <%=contentMap1.get(TrainingClassInfoConfig.EDUCATION1).equals(String.valueOf(Constants.STATUS_VALID)) ?"checked":"" %>/>教育培训
													      <input type="checkbox" name="practice1" id="practice1" value="<%=Constants.STATUS_VALID %>" <%=contentMap1.get(TrainingClassInfoConfig.PRACTICE1).equals(String.valueOf(Constants.STATUS_VALID))?"checked":"" %>/>实践培训
													      <input type="checkbox" name="entrepreneurship1" id="entrepreneurship1" value="<%=Constants.STATUS_VALID %>" <%=contentMap1.get(TrainingClassInfoConfig.ENTREPRENEURSHIP1).equals(String.valueOf(Constants.STATUS_VALID))?"checked":"" %>/>创业指导和孵化
													      <input type="checkbox" name="identify1" id="identify1" value="<%=Constants.STATUS_VALID %>" <%=contentMap1.get(TrainingClassInfoConfig.IDENTIFY1).equals(String.valueOf(Constants.STATUS_VALID))?"checked":"" %>/>认定管理
													      <input type="checkbox" name="track1" id="track1" value="<%=Constants.STATUS_VALID %>" <%=contentMap1.get(TrainingClassInfoConfig.TRACK1).equals(String.valueOf(Constants.STATUS_VALID))?"checked":"" %>/>跟踪服务
                                                    </td>
                                                </tr>
                                                 <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>项目实施第二年</span>
                                                    </td>
                                                    <td align="left" class="td_data">
													      <%List<TrainingItemContentBean> contentList2 = JsonKit.toList(bean.getTrainingItem2(), TrainingItemContentBean.class);
													      	Map<String,String> contentMap2 = ListKit.list2Map(contentList2, "key","value");%>
													      <input type="checkbox" name="education2" id="education2" value="<%=Constants.STATUS_VALID %>" <%=contentMap2.get(TrainingClassInfoConfig.EDUCATION2).equals(String.valueOf(Constants.STATUS_VALID)) ?"checked":"" %>/>教育培训
													      <input type="checkbox" name="practice2" id="practice2" value="<%=Constants.STATUS_VALID %>" <%=contentMap2.get(TrainingClassInfoConfig.PRACTICE2).equals(String.valueOf(Constants.STATUS_VALID))?"checked":"" %>/>实践培训
													      <input type="checkbox" name="entrepreneurship2" id="entrepreneurship2" value="<%=Constants.STATUS_VALID %>" <%=contentMap2.get(TrainingClassInfoConfig.ENTREPRENEURSHIP2).equals(String.valueOf(Constants.STATUS_VALID))?"checked":"" %>/>创业指导和孵化
													      <input type="checkbox" name="identify2" id="identify2" value="<%=Constants.STATUS_VALID %>" <%=contentMap2.get(TrainingClassInfoConfig.IDENTIFY2).equals(String.valueOf(Constants.STATUS_VALID))?"checked":"" %>/>认定管理
													      <input type="checkbox" name="track2" id="track2" value="<%=Constants.STATUS_VALID %>" <%=contentMap2.get(TrainingClassInfoConfig.TRACK2).equals(String.valueOf(Constants.STATUS_VALID))?"checked":"" %>/>跟踪服务
                                                    </td>
                                                </tr>
                                                <%}else{ %>
                                                 <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>项目实施第一年</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                         <input type="checkbox" name="education1" id="education1" value="<%=Constants.STATUS_VALID %>"/>教育培训
													     <input type="checkbox" name="practice1" id="practice1" value="<%=Constants.STATUS_VALID %>"/>实践培训
													     <input type="checkbox" name="entrepreneurship1" id="entrepreneurship1" value="<%=Constants.STATUS_VALID %>"/>创业指导和孵化
													     <input type="checkbox" name="identify1" id="identify1" value="<%=Constants.STATUS_VALID %>" />认定管理
													     <input type="checkbox" name="track1" id="track1" value="<%=Constants.STATUS_VALID %>" />跟踪服务
                                                    </td>
                                                </tr>
                                                 <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>项目实施第二年</span>
                                                    </td>
                                                    <td align="left" class="td_data">
													    <input type="checkbox" name="education2" id="education2" value="<%=Constants.STATUS_VALID %>" />教育培训
													    <input type="checkbox" name="practice2" id="practice2" value="<%=Constants.STATUS_VALID %>" />实践培训
													    <input type="checkbox" name="entrepreneurship2" id="entrepreneurship2" value="<%=Constants.STATUS_VALID %>" />创业指导和孵化
													    <input type="checkbox" name="identify2" id="identify2" value="<%=Constants.STATUS_VALID %>" />认定管理
													    <input type="checkbox" name="track2" id="track2" value="<%=Constants.STATUS_VALID %>"/>跟踪服务
                                                    </td>
                                                </tr>
                                                 <%}%>
                                            </tbody>
                                        </table>
                                        <div class="text_center mt_5" style="width: 98%">
                                            <input type="submit" value="保 存" class="btn72" />
                                            <input type="button" name="cancel" id="cancel" class="btn72" value="取消" onclick="javascript:history.back()">
                                        </div>
                                        </form>
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
    <div class="contents-footer"></div>
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
</body>
<script>
$(document).ready(function() {
	   $("#majorTypeId").on("change",function(){
		      var typeId = $(this).find("option:selected").val();
		      seletCate(typeId,$(this));
		  });
		  $("#majorTypeId").trigger('change');

		  function seletCate(typeId,element){
			  $.ajax({
	 	        type: 'get',
	 	        url: "/declare/admin/MajorInfoJsonList.do?typeId="+typeId,
	 	        dataType: 'json',
	 	        success: function(data){
	 	        	if(data!=null) {
	 	        	 $("#majorId").empty();
	 	        		var majors=data.result;
	 	        		child ="";
	               for(var j=0;j< majors.length;j++){
	             	 	var originalId = element.parent().parent().find(".major").val();
	             	 	if(majors[j].majorId == originalId){
	             	 		child += "<option value='" + majors[j].majorId  +"' selected>"+ majors[j].majorName + "</option>"
	             	 	}else{
	             	 		child += "<option value='" + majors[j].majorId  +"'>"+ majors[j].majorName + "</option>"
	             	 	}
	              }
	              $("#majorId").html(child);
	 	         }
	 	        },
	 	        error: function(){
	 	            return;
	 	        }
			});
		  }		     
});

function formOnSubmit(){
	if(checkForm('formData')){
		return true;
	}else{
		return false;
	}
} 
</script>
</html>