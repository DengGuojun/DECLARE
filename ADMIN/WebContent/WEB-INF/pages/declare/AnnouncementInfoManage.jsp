<%@page import="com.lpmas.constant.user.GenderConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.constant.*"  %>
<% 
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	AnnouncementInfoBean bean = (AnnouncementInfoBean) request.getAttribute("AnnouncementInfo");
	List<Integer> receiverList = (List<Integer>) request.getAttribute("ReceiverList");
	List<FileInfoBean> attachList = (List<FileInfoBean>) request.getAttribute("AttachList");
	
	String adminUserLevel = (String) request.getAttribute("AdminUserLevel");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!–[if lte IE 8]> 
 <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
 <![endif]–> 
 <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
 <%@ include file="../include/header.jsp" %>
 <script type="text/javascript" src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js"></script>
<script src="<%=STATIC_URL %>js/jquery.ui.widget.js"></script>
<script src="<%=STATIC_URL %>js/jquery.iframe-transport.js"></script>
<script src="<%=STATIC_URL %>js/jquery.fileupload.js"></script>
   	<title>新型农民职业培训系统 — 发布公告</title>
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/SlideMenuStyle.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/default.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/StyleBlue.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/index_main.css" type="text/css" rel="Stylesheet">
    
    
     <style type="text/css">
        
        body
        {
            font-size: 14px;
            font-family: "宋体";
        }
        ol li
        {
            margin: 8px;
        }
        #con
        {
            font-size: 12px;
            margin: 0px auto;
            width: 100%;
            height: 100%;
        }
        #tags
        {
            padding-right: 0px;
            padding-left: 0px;
            padding-bottom: 0px;
            margin: 0px 0px 0px 10px;
            width: 400px;
            padding-top: 0px;
            height: 23px;
            width: 1000px;
        }
        #tags li
        {
            background: url(../images/tagleft.gif) no-repeat left bottom;
            float: left;
            margin-right: 1px;
            list-style-type: none;
            height: 23px;
        }
        #tags li a
        {
            padding-right: 10px;
            padding-left: 10px;
            background: url(../images/tagright.gif) no-repeat right bottom;
            float: left;
            padding-bottom: 0px;
            color: #999;
            line-height: 23px;
            padding-top: 0px;
            height: 23px;
            text-decoration: none;
        }
        #tags li.emptyTag
        {
            background: none transparent scroll repeat 0% 0%;
            width: 4px;
        }
        #tags li.selectTag
        {
            background-position: left top;
            background-repeat: repeat-x;
            margin-bottom: -2px;
            position: relative;
            height: 25px;
        }
        #tags li.selectTag a
        {
            background-position: right top;
            color: #FFF;
            line-height: 25px;
            height: 25px;
        }
        #tagContent
        {
            border-right: #aecbd4 1px solid;
            padding-right: 1px;
            border-top: #aecbd4 1px solid;
            padding-left: 1px;
            padding-bottom: 1px;
            border-left: #aecbd4 1px solid;
            padding-top: 1px;
            border-bottom: #aecbd4 1px solid;
            background-color: #fff;
        }
        .tagContent
        {
            padding-right: 10px;
            display: none;
            padding-left: 5px;
            padding-bottom: 10px;
            width: 98%;
            color: #474747;
            padding-top: 5px;
            height: 498px;
        }
        #tagContent div.selectTag
        {
            display: block;
        }
    
    </style>

</head>
<body class="article_bg">
	<%@include file="../nav/navigation.jsp" %>
	<form id="formData" name="formData" method="post" action="AnnouncementInfoManage.do" onsubmit="javascript:return checkThisForm('formData');">
	<input type="hidden" name="announcementId" id="announcementId" value="<%=bean.getAnnouncementId() %>"/>
	<input type="hidden" name="announcementStatus" id="announcementStatus" value="<%=bean.getAnnouncementStatus() %>"/>
	<input type="hidden" name="status" id="status" value="<%=bean.getStatus() %>"/>
    <div class="warpDefaultMgr">
        <table class="table_wrp">
            <tbody>
                    <tr>
                        <td style="width: 191px; background-color: #f2f2f2;" valign="top">
							<%@include file="../include/project_management_left.jsp" %>
                        </td>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap fixed_height">
                                    <div class="detail_right_title h1 white">
                                        <span style="float: left;">新增公告</span>
                                    </div>
                                    <div>
                                    	<a class="turnBlack" onclick="javascript:history.back()">&lt;返回上一页</a>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                        <div style="width: 500px; margin: 0 auto;">
                                        	<div class="form-group">
                                        		<label class="fl">接收者：</label>
                                        		<div class="form-control fl">
                                        			<%if(adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)) {%>
                                        			<label class="block w_auto">
                                        				<input type="checkbox" name="countryUser" <%=receiverList.contains(1) ? "checked" : ""  %>> 国家用户
                                        			</label>
                                        			<%}%>
                                        			<%if(adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)
                                        					||adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)
                                        					) {%>
                                        			<label class="block w_auto">
                                        				<input type="checkbox" name="provinceUser" <%=receiverList.contains(2) ? "checked" : ""  %>> 省级用户
                                        			</label>
                                        			<%} %>
                                        			<%if(adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)
                                        					||adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)
                                        					||adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)
                                        					) {%>
                                        			<label class="block w_auto">
                                        				<input type="checkbox" name="cityUser" <%=receiverList.contains(3) ? "checked" : ""  %>> 市级用户
                                        			</label>
                                        			<%} %>
                                        			<label class="block w_auto">
                                        				<input type="checkbox" name="regionUser" <%=receiverList.contains(4) ? "checked" : ""  %>> 县级用户
                                        			</label>
                                        			<%if(adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)
                                        					||adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)
                                        					) {%>
                                        			<label class="block w_auto">
                                        				<input type="checkbox" name="luruProvinceUser" <%=receiverList.contains(5) ? "checked" : ""  %>> 省级录入用户
                                        			</label>
                                        			<%} %>
                                        			<%if(adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)
                                        					||adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)
                                        					||adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)
                                        					) {%>
                                        			<label class="block w_auto">
                                        				<input type="checkbox" name="luruCityUser" <%=receiverList.contains(6) ? "checked" : ""  %>> 市级录入用户
                                        			</label>
                                        			<%} %>
                                        			<label class="block w_auto">
                                        				<input type="checkbox" name="luruRegionUser" <%=receiverList.contains(7) ? "checked" : ""  %>> 县级录入用户
                                        			</label>
                                        			<%if(adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)
                                        					||adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)
                                        					) {%>
                                        			<label class="block w_auto">
                                        				<input type="checkbox"  name="rendingProvinceUser" <%=receiverList.contains(8) ? "checked" : ""  %>> 省级认定用户
                                        			</label>
                                        			<%} %>
                                        			<%if(adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)
                                        					||adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_PROVINCE)
                                        					||adminUserLevel.equals(DeclareAdminConfig.ADMIN_LEVEL_CITY)
                                        					) {%>
                                        			<label class="block w_auto">
                                        				<input type="checkbox"  name="rendingCityUser" <%=receiverList.contains(9) ? "checked" : ""  %>> 市级认定用户
                                        			</label>
                                        			<%} %>
                                        			<label class="block w_auto">
                                        				<input type="checkbox"  name="rendingRegionUser" <%=receiverList.contains(10) ? "checked" : ""  %>> 县级认定用户
                                        			</label>
                                        		</div>
                                                <div class="clearfix"></div>
                                        	</div>
                                        	<%if(attachList.size() >0) {%>
                                        	<div class="form-group">
                                        		<label class="fl">附件：</label>
                                        		<div class="form-control fl">
                                        			<%for(FileInfoBean fileInfoBean : attachList){ %>
                                        			<label class="block w_auto" >
                                        				<a onclick="javascript:location.href='FileInfoDownload.do?fileId=<%=fileInfoBean.getFileId()%>'"><%=fileInfoBean.getFileName() %></a>
                                        			</label>
                                        			<%} %>
                                        		</div>
                                                <div class="clearfix"></div>
                                        	</div>
                                        	<%} %>
                                        	<div class="form-group">
                                        		<label>标题：</label>
                                        		<input type="text" name="announcementTitle" id="announcementTitle" value="<%=bean.getAnnouncementTitle() %>" class="form-control int_normal" checkStr="公告标题;txt;true;;100">
                                        	</div>
                                        	<div class="form-group">
                                        		<label>内容：</label>
                                        		<textarea name="announcementContent" id="announcementContent"  class="form-control int_normal" rows="15"><%=bean.getAnnouncementContent() %></textarea>
                                        	</div>
                                        	<div class="form-group">
                                        		<label>附件：</label>
                                        		<input type="file" id="file" name="file" multiple="multiple" class="form-control int_normal" onchange="up();">
                                        		<input type="hidden" id="fileId" name="fileId" value=""/> 
                                        	</div>
                                        	<div class="text_center mt_15">
                                        		<input type="submit" class="btn72 mt_5" id="confirm" value="保 存">
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
<script>
function up() {
	$("#confirm").attr({"disabled":"disabled"});
    var file_data = $('#file')[0].files; // for multiple files
    var count  = 0;
    for (var i = 0; i < file_data.length; i++) {
        var fd = new FormData();
        fd.append('file', file_data[i]);
        $.ajax({
        		url: 'FileInfoUpload.do?infoType=<%=FileInfoConfig.INFO_TYPE_ANNOUNCEMENT_ATTACH%>&infoId=<%=bean.getAnnouncementId()%>',
            data: fd,
            contentType: false,
            processData: false,
            type: 'POST',
            success: function (data) {
                console.log(data);
                if(data.code == '1'){
                		var dataObj=eval("("+data.message+")");
                		var orgId= $("#fileId").val();
                		$("#fileId").val(orgId+","+dataObj);
                		var fiId = $("#fileId").val();
                }else{
                		alert(data.message);
                		$("#fileId").val("");
                }
                count ++;
                if(count == file_data.length){
                		$("#confirm").removeAttr("disabled");
                		count = 0;
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    }
}
</script>
</html>