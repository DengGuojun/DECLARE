<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.lpmas.framework.web.ParamKit"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.bean.StatusBean" %>
<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@page import="com.lpmas.declare.admin.config.*" %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<% 
    AdminUserHelper adminUserHelper = (AdminUserHelper) request.getAttribute("AdminUserHelper");
    DeclareReportBean bean = (DeclareReportBean)request.getAttribute("DeclareReportBean");
	int declareId = ParamKit.getIntParameter(request, "declareId", 0);
	List<IndustryTypeBean> industryTypeList = (List<IndustryTypeBean>)request.getAttribute("IndustryTypeList");
	List<JobTypeBean> jobTypeList = (List<JobTypeBean>)request.getAttribute("JobTypeList");
	DeclareImageBean imageBean = (DeclareImageBean) request.getAttribute("DeclareImageBean");
	int declareType = ParamKit.getIntParameter(request, "declareType", 0);
	int isSave = ParamKit.getIntParameter(request, "isSave", 0);
	String fixProvince = (String)request.getAttribute("FixProvince");
	String fixCity = (String)request.getAttribute("FixCity");
	String fixRegion = (String)request.getAttribute("FixRegion");
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
    <title>新型农民职业培训系统 — 对象推荐</title>
    <link media="print,all" href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link media="print,all" href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link media="print,all" href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link media="print,all" href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <link media="print,all" href="<%=STATIC_URL %>/css/SlideMenuStyle.css" type="text/css" rel="Stylesheet">
    <link media="print,all" href="<%=STATIC_URL %>/css/default.css" type="text/css" rel="stylesheet">
    <link media="print,all" href="<%=STATIC_URL %>/css/StyleBlue.css" type="text/css" rel="Stylesheet">
    <link media="print,all" href="<%=STATIC_URL %>/css/index_main.css" type="text/css" rel="Stylesheet">
    
    <style type="text/css">
    		body{ min-width:100%; }
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
        input[type='text']
        {
            border: 0;
            width: 98%;
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
    </style>
</head>
<body class="body-index">
	<%@include file="../nav/navigation.jsp" %>
<div class="warpDefaultMgr">
            <table class="table_wrp">
                <tbody>
                    <tr>
                        <td style="width: 191px; background-color: #f2f2f2;" valign="top">
                             <%@include file="../include/cultivate_object_library_left.jsp" %>
                        </td>		
	                    <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                      <span style="float: left;">对象推荐</span>
                                </div>
								<div align="center" class="text_center js-title" style="margin-top: 15px;" >
								<div class="printDom" id="printHtml">
								   <input type="hidden" name="isSave" id="isSave" value="<%=isSave%>">
								   <input type="hidden" name="declareId" id="declareId" value="<%=bean.getDeclareId() %>">
	                               <input type="hidden" name="status" id="status" value="<%=bean.getStatus()%>">
									<h2 class="table_title" align="center"><%=DeclareInfoConfig.DECLARE_TYPE_MAP.get(bean.getDeclareType())%>培育对象申报表</h2>
									<br>
									<span>
										<div>
											<table style="margin: 0 auto; width: 725px; border-collapse: collapse; border: 0px; text-align: left;
								            padding-left: 5px;" class="gv" border="0" cellspacing="0">
												<tbody>
													<tr class="" style="border: 0px;">
														<td style="border: 0px; text-align: left; padding-left: 5px;">
															填表日期
															<input type="text" value="<%=DateKit.formatDate(new Date(), DateKit.REGEX_YEAR) %>" style="width:50px;border-bottom: 1px solid #cecece; text-align:center;">
															年
															<input type="text" value="<%=DateKit.formatDate(new Date(), DateKit.REGEX_MONTH) %>" style="width:50px;border-bottom: 1px solid #cecece; text-align:center;">
															月
															<input type="text" value="<%=DateKit.formatDate(new Date(), DateKit.REGEX_DATE) %>" style="width:50px;border-bottom: 1px solid #cecece; text-align:center;">日</td>
														<td style="border: 0px; text-align: right; padding-right: 5px; width: 100px;">
															注：
															<span style="color:Red; display:inline;">*</span>
															为必填项
														</td>
													</tr>
												</tbody>
											</table>
											<table style="margin: 0 auto; width: 805px; border: 1px solid #cecece; border-top: 1px solid #FFFFFF; border-collapse: collapse;" class="gv" border="1" cellspacing="0">
												<tbody>
													<tr class="">
														<td class="tit" style="">
															<span style="color:Red; display:inline;">*</span>
															姓 名
														</td>
														<td class="con" colspan="4">
															<input maxlength="50" style="width:100%;" id="userName" name="userName" type="text" value="<%=bean.getUserName() %>" />
														</td>
														<td class="tit" colspan="3" style="">
															<span style="color:Red; display:inline;">*</span>
															性 别
														</td>
														<td class="con" colspan="3">
														  <select  name="userGender" id="userGender">
															<option value="">请选择</option>
															<% int userGender = bean.getUserGender(); %>
															 <%for(StatusBean<Integer,String> statusBean : GenderConfig.GENDER_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus()==userGender) ? "selected" : ""%>><%=statusBean.getValue() %></option>
															<%} %>
									                       </select>
														</td>
														<td class="con head_upload" rowspan="6" colspan="2" style="text-align: center">
																<%if(bean!=null&&!bean.getImagePath().equals("")){ %>
									                                <img id="img_preview" src="<%=imageBean.getImagePath() %>" style="height:160px;width:132px;border-width:0px;">
									                                <%}else{ %>
									                                <img id="img_preview" src="<%=STATIC_URL %>/images/headimg.jpg" style="height:160px;width:132px;border-width:0px;">
									                                 <%} %>
																	<label><input type="file" id="file" name="file" accept="image/*" onchange="up();" /></label>
															<input type="hidden" id="imagePath" name="imagePath" value="<%=imageBean.getImagePath()%>"/>
												            <input type="hidden" id="imageType" name="imageType" value="<%=DeclareImageInfoConfig.IMG_TYPE_ONE_INCH%>"/>
														</td>
													</tr>
													<tr class="">
														<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															出生日期
														</td>
														<td class="con" colspan="4">
															<input type="text" onClick="WdatePicker()" id="userBirthday" name="userBirthday" value="<%=bean.getUserBirthday() !=null ? DateKit.formatDate(bean.getUserBirthday(), DateKit.DEFAULT_DATE_FORMAT):"" %>"  autocomplete="off"/>
														</td>
														<td class="tit" colspan="3">
															<span style="color:Red; display:inline;">*</span>
															民 族
														</td>
														<td class="con" colspan="3">
															<select name="nationality" id="nationality">
															<option value="">请选择</option>
															<% String nationality = bean.getNationality(); %>
															 <%for(StatusBean<String,String> statusBean : NationalityConfig.NATIONALITY_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(nationality)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
															<%} %>
											             </select>
														</td>
													</tr>
													<tr class="">
														<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															文化程度
														</td>
														<td class="con" colspan="4">
															<select name="education" id="education">
															<option value="">请选择</option>
															<% String education = bean.getEducation(); %>
															 <%for(StatusBean<String,String> statusBean : FarmerInfoConfig.EDUCATION_LEVEL_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(education)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
															<%} %>
															</select>
														</td>
														<td class="tit" colspan="3">专 业</td>
														<td class="con" colspan="3">
															<input maxlength="32" style="width:100%;" id="major" name="major" type="text" value="<%=bean.getMajor() %>" />
														</td>
													</tr>
													<tr class="">
														<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															身份证号
														</td>
														<td class="con" colspan="4">
															<input maxlength="32" style="width:100%;" id="identityNumber" name="identityNumber" type="text" value="<%=bean.getIdentityNumber() %>" />
														</td>
														<td class="tit" colspan="3">
															<span style="color:Red; display:inline;">*</span>
															政治面貌
														</td>
														<td class="con" colspan="3">
															<select name="politicalStatus" id="politicalStatus">
															<option value="">请选择</option>
															<% String politicalStatus = bean.getPoliticalStatus(); %>
															 <%for(StatusBean<String,String> statusBean : FarmerInfoConfig.POLITICAL_STATUS_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(politicalStatus)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
															<%} %>
															</select>
														</td>
													</tr>
													<tr class="">
														<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															手机号码
														</td>
														<td class="con" colspan="4">
															<input maxlength="50" style="width:100%;" type="number" name="userMobile" id="userMobile" value="<%=bean.getUserMobile() %>" />
														</td>
														<td class="tit" colspan="3">电子邮箱</td>
														<td class="con" colspan="3">
															<input maxlength="128"  style="width:100%;" name="userEmail" id="userEmail" type="text" value="<%=bean.getUserEmail()%>" />
														</td>
													</tr>
													<tr class="">
														<td class="tit">QQ号</td>
														<td class="con" colspan="4">
															<input maxlength="32" style="width:100%;" type="number" name="userQq" id="userQq" value="<%=bean.getUserQq()%>" />
														</td>
														<td class="tit" colspan="3">微 信 号</td>
														<td class="con" colspan="3">
															<input maxlength="64" style="width:100%;" type="text" name="userWechat" id="userWechat" value="<%=bean.getUserWechat()%>" />
														</td>
													</tr>
													<tr class="">
														<td class="tit" colspan="1">
															<span style="color:Red; display:inline;">*</span>
															户籍所在地
														</td>
														<td class="con" colspan="12">

															<div>
															<input type="hidden" name="perProvince" id="perProvince" value="<%=bean.getProvince()%>"/>
															<input type="hidden" name="perCity" id="perCity" value="<%=bean.getCity()%>"/>
															<input type="hidden" name="perRegion" id="perRegion" value="<%=bean.getRegion()%>"/>
																<div>
																	省:
																	<select style="width:80px;" id="personProvince">
																		<option selected="selected" value="">请选择</option>
																	</select>
																	市:
																	<select style="width:80px;" id="personCity">
																		<option selected="selected" value="">请选择</option>
																	</select>
																	县:
																	<select style="width:80px;" id="personRegion">
																		<option value="">请选择</option>
																	</select>
																<input type="hidden" id="queryCity1" />
									                            <input type="hidden" id="queryRegion1" />
																</div>
															</div>															

														</td>
													</tr>
													<tr class="">
														<td class="tit">通讯地址</td>
														<td class="con" colspan="12">
															<input maxlength="500" style="width:100%;" name="address" id="address" type="text" value="<%=bean.getAddress()%>" />
														</td>
													</tr>
													<input type="hidden" name="farmerType" id="farmerType" value="<%=bean.getFarmerType()%>"/>
													<tr class="js-tr-1-1">
													<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															人员类别
														</td>
														<td class="con" colspan="12" style="text-align: left;">
																<select  name="farmerSelectType" id="farmerSelectType1">
																	<option value="">请选择</option>									
																	<%for(StatusBean<String,String> statusBean : FarmerInfoConfig.YOUNG_FARMER_PERSONNEL_CATEGORY_LIST){ %>
																	<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(bean.getFarmerType())) ? "selected" : ""%>><%=statusBean.getValue() %></option>
																	<%} %>
																</select>
														</td>
													</tr>
													<tr class="js-tr-1-2">
													<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															人员类别
														</td>
														<td class="con" colspan="12" style="text-align: left;">
																<select  name="farmerSelectType" id="farmerSelectType2">
																	<option value="">请选择</option>									
																	<%for(StatusBean<String,String> statusBean : FarmerInfoConfig.PRODUCT_FARMER_PERSONNEL_CATEGORY_LIST){ %>
																	<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(bean.getFarmerType())) ? "selected" : ""%>><%=statusBean.getValue() %></option>
																	<%} %>
																</select>
														</td>
													</tr>
													<tr class="js-tr-1-5">
													<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															人员类别
														</td>
														<td class="con" colspan="12" style="text-align: left;">
																<select  name="farmerSelectType" id="farmerSelectType5">
																	<option value="">请选择</option>									
																	<%for(StatusBean<String,String> statusBean : FarmerInfoConfig.LEADER_FARMER_PERSONNEL_CATEGORY_LIST){ %>
																	<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(bean.getFarmerType())) ? "selected" : ""%>><%=statusBean.getValue() %></option>
																	<%} %>
																</select>
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															申请方式
														</td>
														<td class="con" colspan="12" style="text-align: left;">
															<select name="applyType" id="applyType">
																<option value="">请选择</option>
																<% String applyType = bean.getApplyType(); %>
																<%for(StatusBean<String,String> statusBean : DeclareInfoConfig.APPLY_TYPE_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(applyType)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
																<%} %>
															</select>
														</td>
													</tr>
													<tr class="">
														<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															学习
															<br>培训经历</td>
														<td colspan="12" style="text-align: left;">
															是否参加过农业培训
															<select name="isTrained" id="isTrained">
																<option value="">请选择</option>
																<option value="<%=Constants.STATUS_VALID %>" <%=(bean.getIsTrained()==Constants.STATUS_VALID)?"selected":"" %>>参加过</option>
																<option value="<%=Constants.STATUS_NOT_VALID %>" <%=(bean.getIsTrained()==Constants.STATUS_NOT_VALID && bean.getDeclareId()>0)?"selected":"" %>>未参加过</option>
															</select>
															，平均每年参加培训
															<input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="10" title="提示：只能输入整数" style="width:40px;border-bottom: 1px solid #cecece;" name="otherTrainingTime" id="otherTrainingTime" value="<%=bean.getDeclareId() >0 ? bean.getOtherTrainingTime() : ""%>"/>
															次。
															<br>
															<textarea rows="5" cols="20" style="width:100%;" id="trainingExperience" name="trainingExperience"><%=bean.getTrainingExperience() %></textarea>
															<div style="text-align: right; padding-right: 5px;">
																还能输入
																<span id="wordNum" style="display: inline; color: #7F0064"><%=300-bean.getTrainingExperience().length()%></span>
																字
															</div>
														</td>
													</tr>
													<tr class="">
														<td class="tit" rowspan="2">
															<span style="color:Red; display:inline;">*</span>
															获 取
															<br>证书情况</td>
														<td class="con" colspan="12" style="text-align: left;">
															<input type="checkbox" name="hasNoCertification" id="hasNoCertification" <%=Constants.STATUS_VALID == bean.getHasNoCertification() ? "checked" : ""%> value="<%=Constants.STATUS_VALID%>" />
															<label>未获得证书</label>
															<br>
															<input type="checkbox" name="hasNewTypeTrainingCertification" id="hasNewTypeTrainingCertification" <%=Constants.STATUS_VALID == bean.getHasNewTypeTrainingCertification() ? "checked" : ""%> value="<%=Constants.STATUS_VALID%>"/>
															<label>新型职业农民培训证书</label>
															<br>
															<input type="checkbox" name="hasNationalCertification" id="hasNationalCertification" <%=Constants.STATUS_VALID == bean.getHasNationalCertification() ? "checked" : ""%> value="<%=Constants.STATUS_VALID%>"/>
															<label>国家职业资格证书</label>
															<br>
															<input type="checkbox" <%=!bean.getCertificationTitle().isEmpty() ? "checked" : ""%>>
															<label>农民技术等级或技术职称</label>
															<select name="certificationTitle" id="certificationTitle">
																<option value="">请选择</option>
																<% String certificationTitle = bean.getCertificationTitle(); %>
																<%for(StatusBean<String,String> statusBean : FarmerInfoConfig.FARMER_TITLE_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(certificationTitle)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
																<%} %>
															</select>
														</td>
													</tr>
													<tr>
														<td class="con" colspan="12" style="text-align: left;">
															<input type="checkbox" name="hasNewTypeCertification" id="hasNewTypeCertification" <%=Constants.STATUS_VALID == bean.getHasNewTypeCertification()? "checked" : ""%> value="<%=Constants.STATUS_VALID%>"/>
															<label>新型职业农民证书</label>
															<br>
															认定等级：
															<select name="certificationGrade" id="certificationGrade">
																<option value="">请选择</option>
																<% String certificationGrade = bean.getCertificationGrade(); %>
																<%for(StatusBean<String,String> statusBean : FarmerInfoConfig.CERTIFICATION_LEVEL_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(certificationGrade)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
																<%} %>
												           </select>
															&nbsp;&nbsp;
								                    		认定时间：
															<input class="int_default" type="text" onClick="WdatePicker()" id="certificationDate" name="certificationDate" value="<%=bean.getCertificationDate() !=null ? DateKit.formatDate(bean.getCertificationDate(), DateKit.DEFAULT_DATE_FORMAT):"" %>" />
															&nbsp;&nbsp;
								                    		认定部门：
															<input class="int_default" type="text" id="certificationDepartment" name="certificationDepartment" value="<%=bean.getCertificationDepartment() %>"/>
															<br>
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit" rowspan="10">
															产 业
															<br>
															生产经营
															<br>
															和服务
															<br>基本情况</td>
														<td class="tit" style="">
															<span style="color:Red; display:inline;">*</span>
															产业所在地
														</td>
														<td class="con" style="text-align: left; padding-left:20px;" colspan="11">
															<div>
															<input type="hidden" name="selProvince" id="selProvince" value="<%=bean.getIndustryProvince()%>"/>
															<input type="hidden" name="selCity" id="selCity" value="<%=bean.getIndustryCity()%>"/>
															<input type="hidden" name="selRegion" id="selRegion" value="<%=bean.getIndustryRegion()%>"/>
																<div>
																	省:
																	<select style="width:100px;" id="selectProvince">
																		<option selected="selected" value="">请选择</option>
																	</select>
																	市:
																	<select style="width:100px;" id="selectCity">
																		<option selected="selected" value="">请选择</option>
																	</select>
																	县:
																	<select style="width:100px;" id="selectRegion">
																		<option value="">请选择</option>
																	</select>
																	<input type="hidden" id="queryCity" />
									                                <input type="hidden" id="queryRegion" />
																</div>
															</div>
														</td>
													</tr>

													<tr class="" style="display:none;">
														<td class="tit" colspan="2">地区类型</td>
														<td class="con" colspan="4">
															<select name="areaType" id="areaType">
															<option>请选择</option>
															<%for(StatusBean<Integer,String> statusBean : FarmerIndustryInfoConfig.AREA_TYPE_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus()== bean.getAreaType()) ? "selected" : ""%>><%=statusBean.getValue() %></option>
															<%} %>
															</select>
														</td>
														<td class="tit" colspan="3">经济区域类型</td>
														<td class="con" colspan="3">
															<select name="economicAreaType" id="economicAreaType">
															<option>请选择</option>
															<%for(StatusBean<Integer,String> statusBean : FarmerIndustryInfoConfig.ECONOMIC_AREA_TYPE_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus()== bean.getEconomicAreaType()) ? "selected" : ""%>><%=statusBean.getValue() %></option>
															<%} %>
															</select>
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit" colspan="12" style="text-align:center;">主 体 产 业</td>
													</tr>
													<tr class="js-tr-1" style="">
														<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															主体产业1
														</td>
														<td class="con" colspan="2">
															<select class="industryType" id="industryTypeId1" name="industryTypeId1" >
															<option value="">请选择</option>
															<%for(IndustryTypeBean industryTypeBean: industryTypeList){ %>
																<option value="<%=industryTypeBean.getTypeId()%>" <%=industryTypeBean.getTypeId() == bean.getIndustryTypeId1() ? "selected" : ""%>><%=industryTypeBean.getTypeName() %></option>
															<%} %>
															</select>
														</td>
														<td class="tit" colspan="2">
															<span style="color:Red; display:inline;">*</span>
															产业名称
														</td>
														<td colspan="2" class="con">
															<input type="hidden" class="originalIndustryId" value="<%=bean.getIndustryId1() %>">
                                                            <select style="width:77px;" class="industry " id="industryId1" name="industryId1" ></select>
														</td>
														<td class="tit" colspan="1" style="">
															<span style="color:Red; display:inline;">*</span>
															产业规模
														</td>
														<td class="ajaxDiv" colspan="2">
															<div style="width: 125px;">
																<input type="text" maxlength="100" style="width:50px;border-bottom: 1px solid #cecece; margin-right: 2px;" name="industryScale1" id="industryScale1" value="<%=bean.getIndustryScale1() > 0 ? bean.getIndustryScale1() : "" %>" />
																<select style="width:65px;" id="industryUnit1" name="industryUnit1" >
											                    <option value="亩" <%=bean.getIndustryUnit1().equals("亩") ? "selected" : ""%>>亩</option>
											                    <option value="只/羽/头" <%=bean.getIndustryUnit1().equals("只/羽/头") ? "selected" : ""%>>只/羽/头</option>
											                    <option value="公顷" <%=bean.getIndustryUnit1().equals("公顷") ? "selected" : ""%>>公顷</option>
							                                  </select>
															</div>
														</td>
														<td class="tit" colspan="1">
															<span style="color:Red; display:inline;">*</span>
															从事年限
														</td>
														<td>
															<input maxlength="18" title="提示：只能输入数字" style="width:50px;border-bottom: 1px solid #cecece;" type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="experience1" id="experience1" value="<%=bean.getExperience1() > 0 ? bean.getExperience1() : "" %>">年
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit">主体产业2</td>
														<td class="con" colspan="2">															
															<select style="width:77px;" class="industryType" id="industryTypeId2" name="industryTypeId2" >
															<option value="">请选择</option>
															<%for(IndustryTypeBean industryTypeBean: industryTypeList){ %>
																<option value="<%=industryTypeBean.getTypeId()%>" <%=industryTypeBean.getTypeId() == bean.getIndustryTypeId2() ? "selected" : ""%>><%=industryTypeBean.getTypeName() %></option>
															<%} %>
															</select>
														</td>
														<td colspan="2" class="tit">产业名称</td>
														<td colspan="2" class="con">
															<input type="hidden" class="originalIndustryId" value="<%=bean.getIndustryId2() %>">
                                                            <select class="industry" style="width:77px;" id="industryId2" name="industryId2" ></select>
														</td>
														<td class="tit" colspan="1">产业规模</td>
														<td class="ajaxDiv" colspan="2">
															<div style="width: 125px;">
																<div id="ctl00_ContentPlaceHolder1_UpdatePanel5">

																	<input maxlength="100" style="width:50px;border-bottom: 1px solid #cecece; margin-right: 2px;"  type="text" name="industryScale2" id="industryScale2" value="<%=bean.getIndustryScale2() > 0 ? bean.getIndustryScale2() : ""%>" />													
																	<select style="width:65px;" id="industryUnit2" name="industryUnit2" >
														            <option value="亩" <%=bean.getIndustryUnit2().equals("亩") ? "selected" : ""%>>亩</option>
														            <option value="只/羽/头" <%=bean.getIndustryUnit2().equals("只/羽/头") ? "selected" : ""%>>只/羽/头</option>
														            <option value="公顷" <%=bean.getIndustryUnit2().equals("公顷") ? "selected" : ""%>>公顷</option>
										                            </select>

																</div>
															</div>
														</td>
														<td class="tit" colspan="1">从事年限</td>
														<td>
															<input maxlength="18" title="提示：只能输入数字" style="width:50px;border-bottom: 1px solid #cecece;" type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="experience2" id="experience2" value="<%=bean.getExperience2() >0 ? bean.getExperience2() : ""%>">年
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit">主体产业3</td>
														<td class="con" colspan="2">
															<select class="industryType" style="width:77px;" id="industryTypeId3" name="industryTypeId3" >
															<option value="">请选择</option>
															<%for(IndustryTypeBean industryTypeBean: industryTypeList){ %>
																<option value="<%=industryTypeBean.getTypeId()%>" <%=industryTypeBean.getTypeId() == bean.getIndustryTypeId3() ? "selected" : ""%>><%=industryTypeBean.getTypeName() %></option>
															<%} %>
															</select>
														</td>
														<td class="tit" colspan="2">产业名称</td>
														<td colspan="2">
															<input type="hidden" class="originalIndustryId" value="<%=bean.getIndustryId3() %>">
                                                            <select style="width:77px;"class="industry" id="industryId3" name="industryId3" ></select>
														</td>
														<td class="tit" colspan="1">产业规模</td>
														<td class="ajaxDiv" colspan="2">
															<div style="width: 125px;">
																<div>
																    <input maxlength="100" style="width:50px;border-bottom: 1px solid #cecece; margin-right: 2px;"  type="text" name="industryScale3" id="industryScale3" value="<%=bean.getIndustryScale3() > 0 ? bean.getIndustryScale3() : ""%>" />
																	<select style="width:65px;" id="industryUnit3" name="industryUnit3" >
														            <option value="亩" <%=bean.getIndustryUnit3().equals("亩") ? "selected" : ""%>>亩</option>
														            <option value="只/羽/头" <%=bean.getIndustryUnit3().equals("只/羽/头") ? "selected" : ""%>>只/羽/头</option>
														            <option value="公顷" <%=bean.getIndustryUnit3().equals("公顷") ? "selected" : ""%>>公顷</option>
										                            </select>
																</div>
															</div>
														</td>
														<td class="tit" colspan="1">从事年限</td>
														<td>
															<input maxlength="18" title="提示：只能输入数字" style="width:50px;border-bottom: 1px solid #cecece;" type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="experience3" id="experience3" value="<%=bean.getExperience3() >0 ? bean.getExperience3() : ""%>">年
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit" colspan="7">
															<span style="color:Red; display:inline;">*</span>
															上年度产业收入(万元)
														</td>
														<td class="con" colspan="5">
															<input type="number" maxlength="18" title="提示：只能输入数字" style="width:100%;" name="lastYearIncome" id="lastYearIncome" value="<%=bean.getLastYearIncome() > 0 ? bean.getLastYearIncome() : ""%>" />
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit" rowspan="4">
															家庭
															<br>
															农场
															<br>情况</td>
														<td class="tit" colspan="2">是否登记注册</td>
														<td class="con" colspan="4">
															<select name="hasRegisted" id="hasRegisted">
																<option value="">请选择</option>
																<option value="<%=Constants.STATUS_VALID %>" <%=(bean.getHasRegisted()==Constants.STATUS_VALID)?"selected":"" %>>是</option>
																<option value="<%=Constants.STATUS_NOT_VALID %>" <%=(bean.getHasRegisted()==Constants.STATUS_NOT_VALID && bean.getDeclareId()>0)?"selected":"" %>>否</option>
															</select>
														</td>
														<td class="tit" colspan="2">示范性家庭农场</td>
														<td class="con" colspan="3">
															<select name="isExampleFamilyFarm" id="isExampleFamilyFarm">
																<option value="">请选择</option>
																<option value="<%=Constants.STATUS_VALID %>" <%=(bean.getIsExampleFamilyFarm()==Constants.STATUS_VALID)?"selected":"" %>>是</option>
																<option value="<%=Constants.STATUS_NOT_VALID %>" <%=(bean.getIsExampleFamilyFarm()==Constants.STATUS_NOT_VALID && bean.getDeclareId()>0)?"selected":"" %>>否</option>
															</select>
															<select name="exampleFarmLevel" id="exampleFarmLevel">
																<option value="">请选择</option>
																<% String exampleFarmLevel = bean.getExampleFarmLevel(); %>
																<%for(StatusBean<String,String> statusBean : FarmerIndustryInfoConfig.FARM_LEVEL_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(exampleFarmLevel)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
																<%} %>
															</select>
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit" colspan="2">家庭农场类型</td>
														<td class="con" colspan="9">
															<select  name="familyFarmType" id="familyFarmType">
																	<option value="">请选择</option>
																	<% String familyFarmType = bean.getFamilyFarmType(); %>
																	<%for(StatusBean<String,String> statusBean : FarmerIndustryInfoConfig.FAMILY_FARM_TYPE_LIST){ %>
																	<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(familyFarmType)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
																	<%} %>									
														   </select>
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit" colspan="2">土地经营规模(亩)</td>
														<td class="con" colspan="4">
															<input name="farmLandScale" id="farmLandScale"  value="<%=bean.getFarmLandScale() >0 ? bean.getFarmLandScale() : "" %>"  type="text"/>
														</td>
														<td class="tit" colspan="3">家庭总人口</td>
														<td class="con" colspan="2">
															<input maxlength="10" title="提示：只能输入整数" style="width:100%;" name="familyPerson" id="familyPerson"  value="<%=bean.getFamilyWorkingPerson() > 0 ? bean.getFamilyPerson() : "" %>"  type="text" onkeyup="value=value.replace(/[^\d]/g,'')"/>
														</td>
													</tr>
													<tr class="js-tr-1">
														<td class="tit" colspan="2">家庭从事产业人数</td>
														<td class="con" colspan="4">
															<input maxlength="10" title="提示：只能输入整数" style="width:100%;border-bottom: 1px solid #cecece;" name="familyWorkingPerson" id="familyWorkingPerson"  value="<%=bean.getFamilyWorkingPerson() >0 ? bean.getFamilyWorkingPerson() : "" %>"  type="text" onkeyup="value=value.replace(/[^\d]/g,'')"/>
														</td>
														<td class="tit" colspan="3">上年度家庭收入(万元)</td>
														<td class="con" colspan="2">
															<input type="text" maxlength="18" title="提示：只能输入数字" style="width:100%;" name="lastYearFamilyIncome" id="lastYearFamilyIncome" value="<%=bean.getLastYearFamilyIncome() > 0 ? bean.getLastYearFamilyIncome() : ""%>" />
														</td>
													</tr>
													<tr class="js-tr-3">
														<td class="tit">
															<span style="color:Red; display:inline;">*</span>
															从业单位类别
														</td>
														<td class="con" colspan="12" style="text-align: left;">
															<select name="companyType" id="companyType">
																<option value="">请选择</option>
																<% String companyType = bean.getCompanyType(); %>
																<%for(StatusBean<String,String> statusBean : FarmerJobInfoConfig.JOB_COMPANY_TYPE_LIST){ %>
																<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus().equals(companyType)) ? "selected" : ""%>><%=statusBean.getValue() %></option>
																<%} %>
															</select>
														</td>
													</tr>
													<tr class="js-tr-2">
														<td class="tit" rowspan="3">
															<span class="js-tr-3" style="color:Red; display:inline;">*</span>
															从业/服务
															<br>情况</td>
														<td class="tit" colspan="1"><span class="js-tr-3" style="color:Red; display:inline;">*</span>工作地点</td>
														<td class="con" colspan="11">
															<div>
															<input type="hidden" name="jobProvince" id="jobProvince" value="<%=bean.getJobProvince()%>"/>
															<input type="hidden" name="jobCity" id="jobCity" value="<%=bean.getJobCity()%>"/>
															<input type="hidden" name="jobRegion" id="jobRegion" value="<%=bean.getJobRegion()%>"/>
																<div id="ctl00_ContentPlaceHolder1_WorkAddress_UpdatePanel1">
																	省:
																	<select style="width:90px;" id="selectJobProvince">
																		<option selected="selected" value="">请选择</option>
																	</select>
																	市:
																	<select style="width:90px;" id="selectJobCity">
																		<option selected="selected" value="">请选择</option>
																	</select>
																	县:
																	<select style="width:90px;" id="selectJobRegion">
																		<option value="">请选择</option>
																	</select>
																	<input type="hidden" id="queryCity2" />
									                                <input type="hidden" id="queryRegion2" />
																</div>
															</div>
														</td>
													</tr>
													<tr class="js-tr-2">
														<td class="tit" colspan="1"><span class="js-tr-3" style="color:Red; display:inline;">*</span>从事工种/岗位</td>
														<td class="con" colspan="6">
															<select style="width:120px;" class="jobType" id="jobType" name="jobType" >
															<option>请选择</option>
															<%for(JobTypeBean jobTypeBean: jobTypeList){ %>
																<option value="<%=jobTypeBean.getTypeId()%>" <%=jobTypeBean.getTypeId() == bean.getJobType() ? "selected" : ""%>><%=jobTypeBean.getTypeName() %></option>
															<%} %>
															</select>
															 <input type="hidden" class="originalJobId" id= "originalJobId" value="<%=bean.getJobName() %>">
                                                             <select style="width:120px;" class="job" id="job" name="job" ></select>
														</td>
														<td class="tit" colspan="3"><span class="js-tr-3" style="color:Red; display:inline;">*</span>从业年限</td>
														<td class="con" colspan="2">
															<input type="text" name="experience" id="experience" value="<%=bean.getExperience() >0 ? bean.getExperience() : ""%>">
														</td>
													</tr>

													<tr  class="js-tr-2">
														<td class="tit" colspan="1">服务规模</td>
														<td class="con" colspan="6">
															<input type="text" id="serviceScale" name="serviceScale" value="<%=bean.getServiceScale() %>"/></td>
														<td class="tit" colspan="3">
														<span class="js-tr-3" style="color:Red; display:inline;">*</span>
															从事该工种/岗位
															<br>年收入(万元)</td>
														<td class="con" colspan="2">
															<input type="text" name="income" id="income" value="<%=bean.getIncome() > 0 ? bean.getIncome() : ""%>" />
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<table cellpadding="0" cellspacing="0" width="98%" border="0" style="padding-left;:1%;;">
											<tbody>
												<tr>
													<td>
														<table class="editViewHeadFoot" cellpadding="2" cellspacing="0" width="99%" border="0"></table>
													</td>
												</tr>
												<tr>
													<td>
														<table class="editView" cellpadding="2" cellspacing="0" width="99%" border="0"></table>
													</td>
												</tr>
												<tr>
													<td>
														<table class="editViewHeadFoot" cellpadding="2" cellspacing="0" width="99%" border="0"></table>
													</td>
												</tr>
											</tbody>
										</table>
									</span>
									</div>
									</div>
									<div style="text-align: center; margin: 20px 0 20px 0;">
										类型变更：
										<select style="width:110px;" name="declareType" id="declareType"  class="js-fillForm">									
											<%for(StatusBean<Integer,String> statusBean : DeclareInfoConfig.DECLARE_TYPE_LIST){ %>
											<option value="<%=statusBean.getStatus()%>" <%=(statusBean.getStatus() == bean.getDeclareType()) ? "selected" : ""%>><%=statusBean.getValue() %></option>
											<%} %>
										</select>
										<%
						                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND,OperationConfig.UPDATE)) {
					                    %>
										<input type="button" value="保 存"  name="submit" id="submit" class="btn72 js-save">
										<%} %>
										<%
						                if (adminUserHelper.hasPermission(DeclareAdminResource.DECLARE_INFO_RECOMMEND,OperationConfig.APPLY)) {
					                    %>
										<input type="button" value="提 交" class="btn72 js-submit">
										<%} %>
										<input type="submit" onclick="printme();" value="打 印" class="btn72">
									</div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="popup loading" id="loading" style="display: block;">	    
	        <img class="dialogLoading" src="<%=STATIC_URL%>/images/loading.gif" width="40" height="40">	   
	    <div class="mask"></div>
	</div>
 <div class="contents-footer"></div>
 <script type="text/javascript" src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
 <script src="<%=STATIC_URL%>/js/Public.js" type="text/javascript"></script>
 <script src="<%=STATIC_URL%>/js/InsertFlash.js" type="text/javascript"></script>
 <script src="<%=STATIC_URL%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
 <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>	
</body>
<script>
$(document).ready(function() {
	$('#loading').hide();
	$("#declareType").trigger('change');
	$("#farmerSelectType1").on("change",function(){
        var farmerSelectType = $(this).find("option:selected").val();
        $('#farmerType').val(farmerSelectType); 
    });
	$("#farmerSelectType2").on("change",function(){
        var farmerSelectType = $(this).find("option:selected").val();
        $('#farmerType').val(farmerSelectType); 
    });
	$("#farmerSelectType5").on("change",function(){
        var farmerSelectType = $(this).find("option:selected").val();
        $('#farmerType').val(farmerSelectType); 
    });
	$("#industryTypeId1").on("change",function(){
         var typeId = $(this).find("option:selected").val();
         seletCate1(typeId,$(this));
     });
	$("#industryTypeId1").trigger('change');
     function seletCate1(typeId,element){
        	$.ajax({
    	        type: 'get',
    	        url: "/declare/admin/IndustryInfoJsonList.do?typeId="+typeId,
    	        dataType: 'json',
    	        success: function(data){
    	        		if(data != null){
    	        			var industry=data.result;
        	        		child ="";
                     for(var j=0;j< industry.length;j++){
                    	 	var originalId = element.parent().parent().find(".originalIndustryId").val();
                    	 	if(industry[j].industryId == originalId){
                    	 		child += "<option value='" + industry[j].industryId  +"' selected>"+ industry[j].industryName + "</option>"
                    	 	}else{
                    	 		child += "<option value='" + industry[j].industryId  +"'>"+ industry[j].industryName + "</option>"
                    	 	}
                     }
                     $("#industryId1").html(child);
    	        		}
    	        },
    	        error: function(){
    	            return;
    	        }
    	    });
     }
     $("#industryTypeId2").on("change",function(){
         var typeId = $(this).find("option:selected").val();
         seletCate2(typeId,$(this));
     });
	$("#industryTypeId2").trigger('change');
     function seletCate2(typeId,element){
        	$.ajax({
    	        type: 'get',
    	        url: "/declare/admin/IndustryInfoJsonList.do?typeId="+typeId,
    	        dataType: 'json',
    	        success: function(data){
    	        		if(data != null){
    	        			var industry=data.result;
        	        		child ="";
                     for(var j=0;j< industry.length;j++){
                    	 	var originalId = element.parent().parent().find(".originalIndustryId").val();
                    	 	if(industry[j].industryId == originalId){
                    	 		child += "<option value='" + industry[j].industryId  +"' selected>"+ industry[j].industryName + "</option>"
                    	 	}else{
                    	 		child += "<option value='" + industry[j].industryId  +"'>"+ industry[j].industryName + "</option>"
                    	 	}
                     }
                     $("#industryId2").html(child);
    	        		}
    	        },
    	        error: function(){
    	            return;
    	        }
    	    });
     }
     $("#industryTypeId3").on("change",function(){
         var typeId = $(this).find("option:selected").val();
         seletCate3(typeId,$(this));
     });
	$("#industryTypeId3").trigger('change');
     function seletCate3(typeId,element){
        	$.ajax({
    	        type: 'get',
    	        url: "/declare/admin/IndustryInfoJsonList.do?typeId="+typeId,
    	        dataType: 'json',
    	        success: function(data){
    	        		if(data !=null){
    	        			var industry=data.result;
        	        		child ="";
                     for(var j=0;j< industry.length;j++){
                    	 	var originalId = element.parent().parent().find(".originalIndustryId").val();
                    	 	if(industry[j].industryId == originalId){
                    	 		child += "<option value='" + industry[j].industryId  +"' selected>"+ industry[j].industryName + "</option>"
                    	 	}else{
                    	 		child += "<option value='" + industry[j].industryId  +"'>"+ industry[j].industryName + "</option>"
                    	 	}
                     }
                     $("#industryId3").html(child);
    	        		}
    	        },
    	        error: function(){
    	            return;
    	        }
    	    });
     }
     $("#jobType").on("change",function(){
	      var typeId = $(this).find("option:selected").val();
	      seletCate(typeId,$(this));
	  });
	  $("#jobType").trigger('change');

	  function seletCate(typeId,element){
		  $.ajax({
 	        type: 'get',
 	        url: "/declare/admin/JobInfoJsonList.do?typeId="+typeId,
 	        dataType: 'json',
 	        success: function(data){
 	        		if(data != null){
 	        			var jobs=data.result;
 	 	        		child ="";
 	               for(var j=0;j< jobs.length;j++){
 	             	 	var originalId = element.parent().parent().find(".originalJobId").val();
 	             	 	if(jobs[j].jobId == originalId){
 	             	 		child += "<option value='" + jobs[j].jobId  +"' selected>"+ jobs[j].jobName + "</option>"
 	             	 	}else{
 	             	 		child += "<option value='" + jobs[j].jobId  +"'>"+ jobs[j].jobName + "</option>"
 	             	 	}
 	              }
 	              $("#job").html(child);
 	        		}
 	        },
 	        error: function(){
 	            return;
 	        }
		});
	  }		     
		$('.js-save').click(function(){
			//获取表单数据
			var declareId = $('#declareId').val();
			var declareType = $('#declareType').val();
			var status = $('#status').val();
			var imagePath = $('#imagePath').val();
			var imageType = $('#imageType').val();
			var userName = $('#userName').val();
			var userGender = $('#userGender').val();
			var userBirthday = $('#userBirthday').val();
			var nationality = $('#nationality').val();
			var education = $('#education').val();
			var major = $('#major').val();
			var politicalStatus = $('#politicalStatus').val();
			var identityNumber = $('#identityNumber').val();
			
			var userMobile = $('#userMobile').val();
	        var userEmail = $('#userEmail').val();
	        var userQq = $('#userQq').val();
	        var userWechat = $('#userWechat').val();
	        var province = $('#personProvince option:selected').text();
	        var city = $('#personCity option:selected').text();
	        var region = $('#personRegion option:selected').text();
	        var address = $('#address').val();
	        
	        var farmerType = $('#farmerType').val();
	        var familyWorkingPerson = $('#familyWorkingPerson').val();
	        var areaType = $('#areaType').val();
	        var economicAreaType = $('#economicAreaType').val();
	        
	        var industryTypeId1 = $('#industryTypeId1').val();
	        var industryId1 = $('#industryId1').val();
	        var industryScale1 = $('#industryScale1').val();
	        var experience1 = $('#experience1').val();
	        var industryUnit1 = $('#industryUnit1').val();
	        var industryTypeId2 = $('#industryTypeId2').val();
	        var industryId2 = $('#industryId2').val();
	        var industryScale2 = $('#industryScale2').val();
	        var experience2 = $('#experience2').val();
	        var industryUnit2 = $('#industryUnit2').val();
	        var industryTypeId3 = $('#industryTypeId3').val();
	        var industryId3 = $('#industryId3').val();
	        var industryScale3 = $('#industryScale3').val();
	        var experience3 = $('#experience3').val();
	        var industryUnit3 = $('#industryUnit3').val();
	        
	        var exampleFarmLevel = $('#exampleFarmLevel').val()
	        var familyFarmType = $('#familyFarmType').val()
	        var farmLandScale = $('#farmLandScale').val()
	        var lastYearIncome = $('#lastYearIncome').val();
	        var familyPerson = $('#familyPerson').val();
	        var experience = $('#experience').val();
	        var income = $('#income').val();
	        var serviceScale = $('#serviceScale').val();
	        var lastYearFamilyIncome = $('#lastYearFamilyIncome').val();
	        
	        var industryProvince = $('#selectProvince option:selected').text();
			var industryCity = $('#selectCity option:selected').text();
			var industryRegion = $('#selectRegion option:selected').text();
	        var hasRegisted  = $('#hasRegisted').val(); 
	        var isExampleFamilyFarm  = $('#isExampleFamilyFarm').val(); 
	        
	        var country = $('#country').val();
			var jobType = $('#jobType').val();
			var jobName = $('#job').val();
			var experience = $('#experience').val();
			var income = $('#income').val();
			var companyType = $('#companyType').val();
			var jobProvince = $('#selectJobProvince option:selected').text();
			var jobCity = $('#selectJobCity option:selected').text();
			var jobRegion = $('#selectJobRegion option:selected').text();
			
			var trainingExperience = $('#trainingExperience').val();
			var applyType = $('#applyType').val();
	        var isTrained = $('#isTrained').val();
	        var otherTrainingTime = $('#otherTrainingTime').val();
	        var certificationGrade = $('#certificationGrade').val();
	        var certificationDate = $('#certificationDate').val()+' 00:00:00';
	        var certificationDepartment = $('#certificationDepartment').val();
	        var hasNewTypeCertification = 0;
	        if (typeof($("#hasNewTypeCertification").attr("checked"))!="undefined") {
	        	hasNewTypeCertification = 1;
	        }
	        var hasNoCertification = 0;
	        if (typeof($("#hasNoCertification").attr("checked"))!="undefined") {
	        	hasNoCertification = 1;
	        }
	        var hasNewTypeTrainingCertification = 0 ;
	        if (typeof($("#hasNewTypeTrainingCertification").attr("checked"))!="undefined") {
	        	hasNewTypeTrainingCertification = 1 ;
	        }
	        var hasNationalCertification = 0;
	        if (typeof($("#hasNationalCertification").attr("checked"))!="undefined") {
	        	hasNationalCertification = 1;
	        }

	        var certificationTitle = $('#certificationTitle').val();
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if (userName == ''){
				alert("姓名必须填写");
				return;
			}
			if (userGender== ''){
				alert("性别必须填写");
				return;
			}
			if (userBirthday == ''){
				alert("出生日期必须填写");
				return;
			}
			if (nationality ==''){
				alert("民族必须填写");
				return;
			}
			if (education==''){
				alert("文化程度必须填写");
				return;
			}
			if (politicalStatus==''){
				alert("政治面貌必须填写");
				return;
			}
			if (identityNumber == ''){
				alert("身份证号必须填写");
				return;
			}
			if(reg.test(identityNumber) === false)
			{
			    alert("身份证输入不合法");
			    return ;
			}
			if (userMobile == ''){
				alert("手机必须填写");
				return;
			}
	        if (province == '' || city == '' || region == ''|| province == '全部' || city == '全部' || region == '全部'|| province == '请选择' || city == '请选择' || region == '请选择'){
				alert("户籍所在地必须填写");
				return;
			}
	        if(isTrained == ''){
	   			alert("学习培训经历必须填写");
   				return;
	   		 }
	        if(declareType == '1' || declareType == '2'|| declareType == '5'){
	   		 if (farmerType == ''){
	   				alert("人员类别必须填写");
	   				return;
	   			}
	   		 if(applyType == ''){
	   			alert("申请方式必须填写");
   				return;
	   		 }
	           if (industryTypeId1 == '' || industryId1 == '' || industryScale1 == '' || experience1 == '' || industryUnit1 == '' ){
	   			alert("主体产业1必须填写");
	   			return;
	   		}
	           if (lastYearIncome == ''){
	   			alert("上年度产业收入必须填写");
	   			return;
	   		}
	           if (industryProvince == '' || industryRegion == '' || industryCity == ''||industryProvince == '全部' || industryRegion == '全部' || industryCity == '全部'||industryProvince == '请选择' || industryRegion == '请选择' || industryCity == '请选择'){
	   			alert("产业所在地必须填写");
	   			return;
	   		}	
	        }
	        if(declareType == '3' || declareType == '4'){
	        	if (jobType == '' || jobName == ''){
	    			alert("从业工种/岗位必须填写");
	    			return;
	    		}
	    		if (companyType==''){
	    			alert("从业单位类别必须填写");
	    			return;
	    		}
	    		if (jobProvince == '' || jobRegion == '' || jobCity == ''||jobProvince == '全部' || jobRegion == '全部' || jobCity == '全部'||jobProvince == '请选择' || jobRegion == '请选择' || jobCity == '请选择'){
	    			alert("工作地点必须填写");
	    			return;
	    		}	
	    		if (typeof(experience)=="undefined"||experience==''){
	    			alert("从业年限必须填写");
	    			return;
	    		}
	    		if (typeof(income)=="undefined"||income==''){
	    			alert("从事岗位年收入必须填写");
	    			return;
	    		}
		     }
			//保留表单验证
			
			//表单提交
			 $.ajax({
		            url: 'DeclareInfoRecommendManage.do',
		            data: {
		                declareId: declareId,
		                declareType: declareType,
		                status: status,
		                imagePath: imagePath,
		                imageType: imageType,
		                userName: userName,
		                userGender: userGender,
		                userBirthday: userBirthday,
		                nationality: nationality,
		                education: education,
		                major: major,
		                politicalStatus: politicalStatus,
		                identityNumber: identityNumber,
		                userMobile: userMobile,
		                userEmail: userEmail,
		                userQq: userQq,
		                userWechat: userWechat,
		                province: province,
		                city: city,
		                region: region,
		                address: address,
		                farmerType:farmerType,
		                industryProvince: industryProvince,
		                industryCity: industryCity,
		                industryRegion: industryRegion,
		                areaType:areaType,
		                economicAreaType:economicAreaType,
		                lastYearIncome:lastYearIncome,
                        industryTypeId1: industryTypeId1,
                        industryId1: industryId1,
                        industryScale1: industryScale1,
                        experience1: experience1,
                        industryUnit1: industryUnit1,
                        industryTypeId2: industryTypeId2,
                        industryId2: industryId2,
                        industryScale2: industryScale2,
                        experience2: experience2,
                        industryUnit2: industryUnit2,
                        industryTypeId3: industryTypeId3,
                        industryId3: industryId3,
                        industryScale3: industryScale3,
                        experience3: experience3,
                        industryUnit3: industryUnit3,
		                hasRegisted:hasRegisted,
		                isExampleFamilyFarm:isExampleFamilyFarm,
		                exampleFarmLevel:exampleFarmLevel,
		                familyFarmType:familyFarmType,
		                farmLandScale:farmLandScale,
		                familyPerson:familyPerson,
		                familyWorkingPerson:familyWorkingPerson,
		                lastYearFamilyIncome:lastYearFamilyIncome,
		                serviceScale:serviceScale,
		                experience:experience,
		                income:income,
		                country: country,
		                jobType: jobType,
		                jobId: jobName,
		                experience: experience,
		                income: income,
		                companyType: companyType,
		                jobProvince: jobProvince,
		                jobCity: jobCity,
		                jobRegion: jobRegion,
		                applyType:applyType,
		                isTrained: isTrained,
		                otherTrainingTime: otherTrainingTime,
		                hasNewTypeCertification:hasNewTypeCertification,
		                certificationGrade:certificationGrade,
		                certificationDate:certificationDate,
		                certificationDepartment:certificationDepartment,
		                hasNoCertification:hasNoCertification,
		                hasNewTypeTrainingCertification:hasNewTypeTrainingCertification,
		                hasNationalCertification:hasNationalCertification,
		                certificationTitle:certificationTitle,
		                trainingExperience:trainingExperience,
		            },
		            type: 'POST',
		            success: function (json) {
		                if (json.code == '1') {
		                	alert(json.message);
		                	window.location = 'DeclareInfoRecommendManage.do?declareId='+<%=bean.getDeclareId() %>+'&isSave=1'
		                }
		                else {
		                    alert(json.message);
		                }
		            }
		        })
		});
	    $('.js-submit').click(function(){
	    	//获取表单数据
			var declareId = $('#declareId').val();
			var declareType = $('#declareType').val();
			var status = $('#status').val();
			var imagePath = $('#imagePath').val();
			var imageType = $('#imageType').val();
			var userName = $('#userName').val();
			var userGender = $('#userGender').val();
			var userBirthday = $('#userBirthday').val();
			var nationality = $('#nationality').val();
			var education = $('#education').val();
			var major = $('#major').val();
			var politicalStatus = $('#politicalStatus').val();
			var identityNumber = $('#identityNumber').val();
			
			var userMobile = $('#userMobile').val();
	        var userEmail = $('#userEmail').val();
	        var userQq = $('#userQq').val();
	        var userWechat = $('#userWechat').val();
	        var province = $('#personProvince option:selected').text();
	        var city = $('#personCity option:selected').text();
	        var region = $('#personRegion option:selected').text();
	        var address = $('#address').val();
	        
	        var farmerType = $('#farmerType').val();
	        var familyWorkingPerson = $('#familyWorkingPerson').val();
	        var areaType = $('#areaType').val();
	        var economicAreaType = $('#economicAreaType').val();
	        
	        var industryTypeId1 = $('#industryTypeId1').val();
	        var industryId1 = $('#industryId1').val();
	        var industryScale1 = $('#industryScale1').val();
	        var experience1 = $('#experience1').val();
	        var industryUnit1 = $('#industryUnit1').val();
	        var industryTypeId2 = $('#industryTypeId2').val();
	        var industryId2 = $('#industryId2').val();
	        var industryScale2 = $('#industryScale2').val();
	        var experience2 = $('#experience2').val();
	        var industryUnit2 = $('#industryUnit2').val();
	        var industryTypeId3 = $('#industryTypeId3').val();
	        var industryId3 = $('#industryId3').val();
	        var industryScale3 = $('#industryScale3').val();
	        var experience3 = $('#experience3').val();
	        var industryUnit3 = $('#industryUnit3').val();
	        
	        var exampleFarmLevel = $('#exampleFarmLevel').val()
	        var familyFarmType = $('#familyFarmType').val()
	        var farmLandScale = $('#farmLandScale').val()
	        var lastYearIncome = $('#lastYearIncome').val();
	        var familyPerson = $('#familyPerson').val();
	        var experience = $('#experience').val();
	        var income = $('#income').val();
	        var serviceScale = $('#serviceScale').val();
	        var lastYearFamilyIncome = $('#lastYearFamilyIncome').val();
	        
	        var industryProvince = $('#selectProvince option:selected').text();
			var industryCity = $('#selectCity option:selected').text();
			var industryRegion = $('#selectRegion option:selected').text();
	        var hasRegisted  = $('#hasRegisted').val(); 
	        var isExampleFamilyFarm  = $('#isExampleFamilyFarm').val(); 
	        
	        var country = $('#country').val();
			var jobType = $('#jobType').val();
			var jobName = $('#job').val();
			var experience = $('#experience').val();
			var income = $('#income').val();
			var companyType = $('#companyType').val();
			var jobProvince = $('#selectJobProvince option:selected').text();
			var jobCity = $('#selectJobCity option:selected').text();
			var jobRegion = $('#selectJobRegion option:selected').text();
			
			var trainingExperience = $('#trainingExperience').val();
			var applyType = $('#applyType').val();
	        var isTrained = $('#isTrained').val();
	        var otherTrainingTime = $('#otherTrainingTime').val();
	        var certificationGrade = $('#certificationGrade').val();
	        var certificationDate = $('#certificationDate').val()+' 00:00:00';
	        var certificationDepartment = $('#certificationDepartment').val();
	        var hasNewTypeCertification = 0;
	        if (typeof($("#hasNewTypeCertification").attr("checked"))!="undefined") {
	        	hasNewTypeCertification = 1;
	        }
	        var hasNoCertification = 0;
	        if (typeof($("#hasNoCertification").attr("checked"))!="undefined") {
	        	hasNoCertification = 1;
	        }
	        var hasNewTypeTrainingCertification = 0 ;
	        if (typeof($("#hasNewTypeTrainingCertification").attr("checked"))!="undefined") {
	        	hasNewTypeTrainingCertification = 1 ;
	        }
	        var hasNationalCertification = 0;
	        if (typeof($("#hasNationalCertification").attr("checked"))!="undefined") {
	        	hasNationalCertification = 1;
	        }

	        var certificationTitle = $('#certificationTitle').val();
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if (userName == ''){
				alert("姓名必须填写");
				return;
			}
			if (userGender== ''){
				alert("性别必须填写");
				return;
			}
			if (userBirthday == ''){
				alert("出生日期必须填写");
				return;
			}
			if (nationality ==''){
				alert("民族必须填写");
				return;
			}
			if (education==''){
				alert("文化程度必须填写");
				return;
			}
			if (politicalStatus==''){
				alert("政治面貌必须填写");
				return;
			}
			if (identityNumber == ''){
				alert("身份证号必须填写");
				return;
			}
			if(reg.test(identityNumber) === false)
			{
			    alert("身份证输入不合法");
			    return ;
			}
			if (userMobile == ''){
				alert("手机必须填写");
				return;
			}
	        if (province == '' || city == '' || region == ''|| province == '全部' || city == '全部' || region == '全部'|| province == '请选择' || city == '请选择' || region == '请选择'){
				alert("户籍所在地必须填写");
				return;
			}
	        if(isTrained == ''){
	   			alert("学习培训经历必须填写");
   				return;
	   		 }
	        if(declareType == '1' || declareType == '2'|| declareType == '5'){
	   		 if (farmerType == ''){
	   				alert("人员类别必须填写");
	   				return;
	   			}
	   		 if(applyType == ''){
	   			alert("申请方式必须填写");
   				return;
	   		 }
	           if (industryTypeId1 == '' || industryId1 == '' || industryScale1 == '' || experience1 == '' || industryUnit1 == '' ){
	   			alert("主体产业1必须填写");
	   			return;
	   		}
	           if (lastYearIncome == ''){
	   			alert("上年度产业收入必须填写");
	   			return;
	   		}
	           if (industryProvince == '' || industryRegion == '' || industryCity == ''||industryProvince == '全部' || industryRegion == '全部' || industryCity == '全部'||industryProvince == '请选择' || industryRegion == '请选择' || industryCity == '请选择'){
	   			alert("产业所在地必须填写");
	   			return;
	   		}	
	        }
	        if(declareType == '3' || declareType == '4'){
	        	if (jobType == '' || jobName == ''){
	    			alert("从业工种/岗位必须填写");
	    			return;
	    		}
	    		if (companyType==''){
	    			alert("从业单位类别必须填写");
	    			return;
	    		}
	    		if (jobProvince == '' || jobRegion == '' || jobCity == ''||jobProvince == '全部' || jobRegion == '全部' || jobCity == '全部'||jobProvince == '请选择' || jobRegion == '请选择' || jobCity == '请选择'){
	    			alert("工作地点必须填写");
	    			return;
	    		}	
	    		if (typeof(experience)=="undefined"||experience==''){
	    			alert("从业年限必须填写");
	    			return;
	    		}
	    		if (typeof(income)=="undefined"||income==''){
	    			alert("从事岗位年收入必须填写");
	    			return;
	    		}
		     }
	        var isSave = $('#isSave').val();
	        if(isSave != '1'){
	        	alert("提交前请先保存");
    			return;
	        }
			location.href='DeclareInfoRecommendAcceptCommit.do?declareId=<%=bean.getDeclareId() %>&action=<%=DeclareInfoRecommendConfig.COMMIT_ACTION_SUBMIT%>';
		});
     
 });
$(function(){    	
	//省市区选择
	showProvince();	
})
function up() {
	$('#loading').show();
    var file_data = $('#file')[0].files; // for multiple files
    //验证文件大小，文件类型
    var file = file_data[0];
    var size = file.size;
    var maxSize = <%=DeclareImageAdminConfig.MAX_SIZE%>;
    if(size>maxSize){
    	alert("文件大小超过限制");
    	return;
    }
    var name = (file.name).toLowerCase();
    if(!(name.lastIndexOf('jpg')>0||name.lastIndexOf('jpeg')>0||name.lastIndexOf('png')>0)){
    	alert("图片类型错误，请上传JPG或者PNG图片");
    	return;
    }
    //文件压缩
    var canvas = document.createElement('canvas');
    var ctx = canvas.getContext('2d');
    var scale = 0;
    var path = window.URL.createObjectURL(file);
    var imgData = "";
    var imgType = file.type;
    //判定压缩比
    if(size<=1024*1024){
    	scale = 1;
    }else{
    	scale = 0.5;
    }
    
    var image = new Image();
    image.onload = function() {
        if (image.width != canvas.width) {
            canvas.width = image.width;
        }
        if (image.height != canvas.height) {
            canvas.height = image.height;
        }
        ctx.clearRect(image,0, 0, canvas.width, canvas.height);
        ctx.drawImage(image, 0, 0);
        imgData = canvas.toDataURL(imgType,scale);
        window.URL.revokeObjectURL(path);
        
      //提交
        for (var i = 0; i < file_data.length; i++) {
            $.ajax({
                url: '/declare/admin/DeclareImageManage.do?declareType=<%=ParamKit.getIntParameter(request, "declareType", 0) %>',
                data: {
                	imgData:imgData,
                	imgType:imgType
                },
                type: 'POST',
                success: function (data) {
                    console.log(data);
                    if(data.code==200){
                        var url=data.message;
                        var img_main = $('#img_main');
                        var img_preview = $('#img_preview');
                        $('#imagePath').val(url);
                        
                        img_preview.attr("src",url);
                        img_preview.attr("width","86");
                        img_preview.attr("height","129");
                        
                        img_main.attr("src",url);
                        img_main.removeClass("plus-img");
                        img_main.addClass("upload-img");
                        img_main.removeAttr('style');
                        img_main.attr('style',"top:0px");
                    }else{
                    	alert(data.message);
                    	$('#loading').hide();
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(errorThrown);
                    alert(errorThrown);
                    $('#loading').hide();
                }
            });
        }
    }
    image.src=path;
    //文件压缩结束
    
    $('#img_preview')[0].onload = function() {
    	$('#loading').hide();
    }
}

function setAjax(id,elm,callback){
        if(id == 'province'){
            $.ajax({
                type: 'POST',
                dataType:'jsonp',
                url: '<%=STATIC_URL%>/m/ProvinceList.action?jsoncallback='+callback,
                success: function(data){
                },
                error: function(){
                    return;
                }
            });
        }else if(id == 'city'){
        	var provinceId = $('#'+elm).val();
            $.ajax({
                type: 'POST',
                dataType:'jsonp',
                url: "<%=STATIC_URL%>/m/CityList.action?provinceId="+provinceId+"&jsoncallback="+callback,
                success: function(data){
                },
                error: function(){
                    return;
                }
            });  
        }else{
        	var cityId = $("#"+elm).val();
            $.ajax({
                type: 'POST',
                dataType:'jsonp',
                url: "<%=STATIC_URL%>/m/RegionList.action?cityId="+cityId+"&jsoncallback="+callback,
                success: function(data){
                },
                error: function(){
                    return;
                }
            });
        }
}


//show
function showProvince(){
    setAjax('province',null,'provinceData');
    setAjax('province',null,'provinceData1')
    setAjax('province',null,'provinceData2')
} 

function provinceData(data){
	var isFix;
	var sel = $("#selectProvince");  
	var selProvince = $("#selProvince").val(); 
	sel.empty();  
	sel.append("<option value = '请选择'>请选择</option>");
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixProvince = "<%=fixProvince%>";
	      	if(fixProvince == item.provinceName){
	      		sel.append("<option value = '"+item.provinceId+"' selected>"+item.provinceName+"</option>");
	      		isFix = true;
	      	}else{
		      	sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
		      	if(item.provinceName == selProvince){
		      		$("#selectProvince").val(item.provinceId);
		      	}
	      	}
	    };
	    sel.change(function(){
	    	showCity();
	    })
	    if(isFix){
    		$("#selectProvince").attr("disabled","disabled");
    		 showCity();
	    }else if(selProvince != ""){
	    	showCity();
	    }
    } else{
   		sel.empty();  
    }
}

function provinceData1(data){
	var isFix;
	var sel = $("#personProvince");  
	var perProvince = $("#perProvince").val();  
	sel.empty();  
	sel.append("<option value = '请选择'>请选择</option>");
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixProvince = "<%=fixProvince%>";
	      	if(fixProvince == item.provinceName){
	      		sel.append("<option value = '"+item.provinceId+"' selected>"+item.provinceName+"</option>");
	      		isFix = true;
	      	}else{
		      	sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
		      	if(item.provinceName == perProvince){
		      		$("#personProvince").val(item.provinceId);
		      	}
	      	}
	    };
	    sel.change(function(){
	    	showCity1();
	    })
	    if(isFix){
    		$("#personProvince").attr("disabled","disabled");
    		 showCity1();
	    }else if(perProvince != ""){
	    	showCity1();
	    }
    } else{
   		sel.empty();  
    }
}
function provinceData2(data){
	var sel = $("#selectJobProvince");  
	var perProvince = $("#jobProvince").val();  
	sel.empty();  
	sel.append("<option value = '请选择'>请选择</option>");
	var items=data.content.provinceList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	sel.append("<option value = '"+item.provinceId+"'>"+item.provinceName+"</option>");
	      	if(item.provinceName == perProvince){
	      		$("#selectJobProvince").val(item.provinceId);
	      	}
	    };
	    sel.change(function(){
	    	showCity2();
	    })
	    if(perProvince != ""){
	    	showCity2();
	    }
    } else{
   		sel.empty();  
    }
}

//show
function showCity(){
 	setAjax('city','selectProvince','cityData');
} 
function showCity1(){
 	setAjax('city','personProvince','cityData1');
} 
function showCity2(){
 	setAjax('city','selectJobProvince','cityData2');
} 

function cityData(data){
	var isFix;
	var sel = $("#selectCity");  
	var selCity = $("#selCity").val(); 
	sel.empty();  
	sel.append("<option value = '全部'>全部</option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	    	var fixCity = "<%=fixCity%>";
	    	if(fixCity == item.cityName){
	      		sel.append("<option value = '"+item.cityId+"' selected>"+item.cityName+"</option>");
	      		isFix = true;
	      	}else{
		      	sel.append("<option value = '"+item.cityId+"'>"+item.cityName+"</option>");
		      	if(item.cityName == selCity){
		      		$("#selectCity").val(item.cityId);
		      	}
	      	}
	    };
	    sel.change(function(){
	    	showRegion();
	    })
	    if(isFix){
    		$("#selectCity").attr("disabled","disabled");
    		showRegion();
        }else if(selCity != ""){
	    	showRegion();
	    }
    } else{
   		sel.empty();  
    }
}
function cityData1(data){
	var isFix;
	var sel = $("#personCity");  
	var perCity = $("#perCity").val(); 
	sel.empty();  
	sel.append("<option value = '全部'>全部</option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixCity = "<%=fixCity%>";
	      	if(fixCity == item.cityName){
	      		sel.append("<option value = '"+item.cityId+"' selected>"+item.cityName+"</option>");
	      		isFix = true;
	      	}else{
		      	sel.append("<option value = '"+item.cityId+"'>"+item.cityName+"</option>");
		    	if(item.cityName == perCity){
		      		$("#personCity").val(item.cityId);
		      	}
	      	}
	    };
	    sel.change(function(){
	    	showRegion1();
	    })
	    if(isFix){
    		$("#personCity").attr("disabled","disabled");
    		showRegion1();
        }else if(perCity != ""){
	    	showRegion1();
	    }
    } else{
   		sel.empty();  
    }
}
function cityData2(data){
	var sel = $("#selectJobCity");  
	var selCity = $("#jobCity").val(); 
	sel.empty();  
	sel.append("<option value = '全部'>全部</option>");
	var items=data.content.cityList;
    	if(items != null) {
	   	for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	sel.append("<option value = '"+item.cityId+"'>"+item.cityName+"</option>");
	      	if(item.cityName == selCity){
	      		$("#selectJobCity").val(item.cityId);
	      	}
	    };
	    sel.change(function(){
	    	showRegion2();
	    })
	    if(selCity != ""){
	    	showRegion2();
	    }
    } else{
   		sel.empty();  
    }
}

//show
function showRegion(){
	var text = $("#selectCity").val();
	if(text == "全部"){
		$("#queryCity").val("");
	}else{
		$("#queryCity").val(text);
	}
	setAjax('region','selectCity','regionData');
} 
function showRegion1(){
	var text1 = $("#personCity").val();
	if(text1 == "全部"){
		$("#queryCity1").val("");
	}else{
		$("#queryCity1").val(text1);
	}
	setAjax('region','personCity','regionData1');
} 
function showRegion2(){
	var text2 = $("#selectJobCity").val();
	if(text2 == "全部"){
		$("#queryCity2").val("");
	}else{
		$("#queryCity2").val(text2);
	}
	setAjax('region','selectJobCity','regionData2');
} 

function regionData(data){
	var isFix;
	var sel = $("#selectRegion");  
	var selRegion = $("#selRegion").val();
	sel.empty();  
	sel.append("<option value = ''>全部</option>");
	var item=data.content;
    if(item != null) {
    	var items = item.regionList;
   		for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	    	var fixRegion = "<%=fixRegion%>";
	      	var queryRegion = "";
	      	if(fixRegion == item.regionName){
	      		sel.append("<option value = '"+item.regionId+"' selected>"+item.regionName+"</option>");
	      		isFix = true;
	      	}else{
		      	sel.append("<option value = '"+item.regionId+"'>"+item.regionName+"</option>");
		      	if(item.regionName == selRegion){
		      		$("#selectRegion").val(item.regionId);
		      	}
	      	}
	    }
   		if(isFix){
    		$("#selectRegion").attr("disabled","disabled");
        }
	    sel.change(function(){
	    	setRegion();
	    })
    } else{
   		sel.empty();  
    }
}
function regionData1(data){
	var isFix;
	var sel = $("#personRegion");  
	var perRegion = $("#perRegion").val(); 
	sel.empty();  
	sel.append("<option value = ''>全部</option>");
	var item=data.content;
    if(item != null) {
    	var items = item.regionList;
   		for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var fixRegion = "<%=fixRegion%>";
	      	var queryRegion = "";
	      	if(fixRegion == item.regionName){
	      		sel.append("<option value = '"+item.regionId+"' selected>"+item.regionName+"</option>");
	      		isFix = true;
	      	}else{
		      	sel.append("<option value = '"+item.regionId+"'>"+item.regionName+"</option>");
		      	if(item.regionName == perRegion){
		      		$("#personRegion").val(item.regionId);
		      	}
	      	}
	    }
   		if(isFix){
    		$("#personRegion").attr("disabled","disabled");
        }
	    sel.change(function(){
	    	setRegion1();
	    })
    } else{
   		sel.empty();  
    }
}
function regionData2(data){
	var sel = $("#selectJobRegion");  
	var selRegion = $("#jobRegion").val();
	sel.empty();  
	sel.append("<option value = ''>全部</option>");
	var item=data.content;
    if(item != null) {
    	var items = item.regionList;
   		for(var i =0;i<items.length;i++) {
	      	var item=items[i];
	      	var queryRegion = "";
	      	sel.append("<option value = '"+item.regionId+"'>"+item.regionName+"</option>");
	      	if(item.regionName == selRegion){
	      		$("#selectJobRegion").val(item.regionId);
	      	}
	    }
	    sel.change(function(){
	    	setRegion2();
	    })
    } else{
   		sel.empty();  
    }
}

function setRegion(){
	var text = $("#selectRegion").val();
	if(text == "全部"){
		$("#queryRegion").val("");
	}else{
		$("#queryRegion").val(text);
	}
}
function setRegion1(){
	var text1 = $("#personRegion").val();
	if(text1 == "全部"){
		$("#queryRegion1").val("");
	}else{
		$("#queryRegion1").val(text1);
	} 
}
function setRegion2(){
	var text2 = $("#selectJobRegion").val();
	if(text2 == "全部"){
		$("#queryRegion2").val("");
	}else{
		$("#queryRegion2").val(text);
	} 
}
</script>
</html>