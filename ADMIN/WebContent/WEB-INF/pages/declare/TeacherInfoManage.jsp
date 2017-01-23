<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@page import="com.lpmas.constant.user.GenderConfig"%>	
 
<%
	TeacherInfoBean bean = (TeacherInfoBean)request.getAttribute("TeacherInfoBean") ;
    Boolean isDeclare = (Boolean)request.getAttribute("IsDeclare") ;
	List<TeacherMajorTypeBean> teacherMajorTypeList =  (List<TeacherMajorTypeBean>)request.getAttribute("TeacherMajorTypeList") ;
	TeacherRegionInfoBean trBean = (TeacherRegionInfoBean)request.getAttribute("TeacherRegionInfoBean") ;
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("adminUserHelper");
	AdminUserInfoBean adminUserInfoBean = adminUserHelper.getAdminUserInfo();
	List<FileInfoBean> attachList =(List<FileInfoBean>)request.getAttribute("attachList") ;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!â[if lte IE 8]>
    <meta http-equiv="X-UA-Compatible" content=âIE=7â³ />
    <![endif]â>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 教师信息录入</title>
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
    <form name="formData" id="formData" method="post" action="TeacherInfoManage.do" onsubmit="javascript:return checkThisForm('formData');"> 
    <input type="hidden" name="teacherId" value="<%=bean.getTeacherId() %>" >
    <input type="hidden" name="province" value="<%=adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)? DeclareAdminConfig.COUNTRY_STR: adminUserInfoBean.getProvince()%>" >
    <input type="hidden" name="city" value="<%=adminUserInfoBean.getCity() %>" >
    <input type="hidden" name="region" value="<%=adminUserInfoBean.getRegion() %>" >
    <input type="hidden" name="status" value="<%=Constants.STATUS_VALID %>" >
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
                                        	<% if(bean.getTeacherId() > 0){
                                        	%>
                                        	师资编辑
                                        	<% 
                                        	}else{
                                        	%>
                                        	师资增加
                                        	<%	
                                        	}
                                        	%> 
                                        </span>&nbsp;&nbsp;
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                        <div class="text_center">
                                        	<div class="printDom" id="printHtml">
                                            <table style=" width: 805px; border: 1px solid #cecece; border-top: 1px solid #FFFFFF; border-collapse: collapse; margin: 15px auto 0" class="gv table_fill" border="1" cellspacing="0">
                                                <tbody>
                                                    <tr class="">
                                                        <td class="tit" style="">
                                                            <span style="color:#ff0000; display:inline;">*</span>
                                                           	 姓名
                                                        </td>
                                                        <td class="con" colspan="4">
                                                           <input type="text" name="teacherName" value="<%=bean.getTeacherName() %>" id="teacherName" checkStr="姓名;txt;true;;100" />
                                                        <td class="tit" colspan="3" style="">
                                                            <span style="color:#ff0000; display:inline;">*</span>
                                                          	  性别
                                                        </td>
                                                        <td class="con" colspan="3">
                                                           <select name="teacherGender" id="teacherGender">
															<%for(StatusBean<Integer, String> gender:GenderConfig.GENDER_LIST){ %>
															<option value="<%=gender.getStatus() %>" <%=(gender.getStatus()==bean.getTeacherGender())?"selected":"" %>><%=gender.getValue() %></option><%} %>
															</select>
                                                        </td>
                                                        <td class="con head_upload" rowspan="6" colspan="2" style="text-align: center">
                                                            <%
                                                               if(bean!=null&&!bean.getTeacherImage().equals("")){
                                                            %>
                                                            	<img id="img_preview" src="<%=bean.getTeacherImage()%>" style="height:160px;width:132px;border-width:0px;">
                                                            <%	   
                                                               }else{
                                                            %>                                                            
                                                            <img id="img_preview" src="<%=STATIC_URL %>/images/headimg.jpg" style="height:160px;width:132px;border-width:0px;">
                                                            <%	   
                                                               }
                                                            %>
                                                             <%if(isDeclare == null) {%>
                                                             <label>
                                                             <input type="file" id="file" name="file" accept="image/*" onchange="up(this);"/>
                                                             <input type="hidden" id="imagePath" name="teacherImage" value="<%=bean.getTeacherImage()%>"/>
                                                             </label>
												            <%} %>
                                                        </td>
                                                    </tr>
                                                    <tr class="">
                                                        <td class="tit">
                                                            <span style="color:#ff0000; display:inline;">*</span>身份证
                                                        </td>
                                                        <td class="con" colspan="4">
                                                            <input type="text" name="identityNumber" value="<%=bean.getIdentityNumber() %>" id="identityNumber"  checkStr="身份证;txt;true;;100" />
                                                        </td>
                                                        <td class="tit" colspan="3">
                                                            <span style="color:#ff0000; display:inline;">*</span>出生日期
                                                        </td>
                                                        <td class="con" colspan="3">
                                                           <input type="text" name="teacherBirthday" id="teacherBirthday"
															onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'1940-01-01',maxDate:'2030-01-01'})" checkStr="出生时间;txt;true;;100"
															value="<%=bean.getTeacherBirthday() == null ? "" : bean.getTeacherBirthday()%>"
															size="20" >
                                                        </td>
                                                    </tr>
                                                    <tr class="">
                                                        <td class="tit">
                                                            <span style="color:#ff0000; display:inline;"></span>所属地区
                                                        </td>
                                                        <td class="con" colspan="10">
                                                            <input type="text" readonly="readonly" value="<%=adminUserInfoBean.getAdminUserLevel().equals(DeclareAdminConfig.ADMIN_LEVEL_COUNTRY)? "国家" : adminUserInfoBean.getProvince()%><%=adminUserInfoBean.getCity()%><%=adminUserInfoBean.getRegion()%>" >
                                                        </td>
                                                    </tr>
                                                    <tr class="">
                                                        <td class="tit">
                                                            <span style="color:#ff0000; display:inline;">*</span>工作单位
                                                        </td>
                                                        <td class="con" colspan="4">
                                                            <input type="text" name="company" value="<%=bean.getCompany() %>" id="company" checkStr="工作单位;txt;true;;100" />
                                                        </td>
                                                        <td class="tit" colspan="3">
                                                            <span style="color:#ff0000; display:inline;">*</span>编号
                                                        </td>
                                                        <td class="con" colspan="3">
                                                           <input type="text" name="teacherNumber" value="<%=bean.getTeacherNumber() %>" id="teacherNumber" checkStr="教师编号;txt;true;;100" />
                                                        </td>
                                                    </tr>
                                                    <tr class="">
                                                        <td class="tit">
                                                            <span style="color:#ff0000; display:inline;">*</span>职称名称
                                                        </td>
                                                        <td class="con" colspan="4">
                                                           <input type="text" name="technicalTitle" value="<%=bean.getTechnicalTitle() %>" id="technicalTitle" checkStr="职称名称;txt;true;;100"/>
                                                        </td>
                                                        <td class="tit" colspan="3">
                                                            职称级别
                                                        </td>
                                                        <td class="con" colspan="3">
                                                           <select  name="technicalGrade" id="technicalGrade" checkStr="职称级别;txt;false;;100">
                                                           <option value=""></option>
														   <%for(StatusBean<String, String> statusBean : TeacherInfoConfig.TECHNICAL_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=(statusBean.getStatus().equals(bean.getTechnicalGrade()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
														   </select>
                                                        </td>
                                                    </tr>
                                                     <tr class="">
                                                        <td class="tit"><span style="color:#ff0000; display:inline;">*</span>类型</td>
                                                        <td class="con" colspan="4">                                                      
                                                            <select  name="teacherType" id="teacherType" checkStr="教师类型;txt;true;;100" >
                                                            <option value=""></option>
														   <%for(StatusBean<Integer, String> statusBean : TeacherInfoConfig.TEACHER_TYPE_LIST){ %><option value="<%=statusBean.getStatus() %>" <%=((statusBean.getStatus()+"").equals(bean.getTeacherType()))?"selected":"" %>><%=statusBean.getValue() %></option><%} %>
														   </select>
                                                        </td>
                                                     </tr>   
                                                    <tr class="">
                                                        <td class="tit"><span style="color:#ff0000; display:inline;">*</span>专业类型</td>
                                                        <td class="con" colspan="4">
                                                           <select name="majorTypeId" id="majorTypeId" >
                                                           <option value=""></option>
															<%for(TeacherMajorTypeBean majorTypeBean : teacherMajorTypeList){ %><option value="<%=majorTypeBean.getMajorId()%>" <%=majorTypeBean.getMajorId() == bean.getMajorTypeId()?"selected":"" %>><%=majorTypeBean.getMajorName() %></option><%} %>
													       </select>
                                                        </td>
                                                        <td class="tit" colspan="3"><span style="color:#ff0000; display:inline;">*</span>专业</td>                                                       
                                                        <td class="con" colspan="3">
                                                        <input type="hidden" id="originalMajorId" name="originalMajorId" value="<%=bean.getMajorId() %>">
                                                            <select name="majorId" id="majorId" checkStr="专业;txt;true;;100"></select>
                                                        </td>
                                                    </tr>
                                                    <tr class="">
                                                        <td class="tit"><span style="color:#ff0000; display:inline;">*</span>主授课程</td>
                                                        <td class="con" colspan="12">
                                                           <input type="text" name="coursesOffer" id="coursesOffer" value="<%=bean.getCoursesOffer()%>" checkStr="主授课程;txt;true;;100">
                                                        </td>
                                                    </tr>
                                                    <tr class="">
                                                        <td class="tit">
                                                            <span style="color:#ff0000; display:inline;">*</span>电子邮箱
                                                        </td>
                                                        <td class="con" colspan="3">
                                                            <input type="text" name="email" value="<%=bean.getEmail() %>" id="email"  checkStr="电子邮箱;mail;true;;100" />
                                                        </td>
                                                        <td class="tit" colspan="4">
                                                            <span style="color: #ff0000; display: inline">*</span>手机
                                                        </td>
                                                        <td class="con" colspan="5">
                                                          <input type="text" name="phone" value="<%=bean.getPhone() %>" id="phone" checkStr="手机号码;digit;true;;" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="tit">相关材料
                                                        </td>
                                                        <td colspan="12" class="con">
                 										 	<%
                 										 		for(FileInfoBean fileBean : attachList){
                 										 	%>
                 										 	<a onclick="javascript:location.href='FileInfoDownload.do?fileId=<%=fileBean.getFileId()%>'"><%=fileBean.getFileName() %></a>&nbsp;&nbsp;&nbsp;
                 										 	<% 		
                 										 		}
                 										 	%>
               											 <br/>
               											 <%if(isDeclare == null) {%>
                 											<input type="file" id="fileUpload" name="fileUpload" multiple="multiple" class="form-control int_normal" onchange="fileUp();">
                                        		              <%} %>
                                        		            <input type="hidden" id="fileId" name="fileId" value=""/>  
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            </div>
                                            <%if(isDeclare == null) {%>
                                            <div class="text_center mt_15"> 
                                			   <input type="submit" class="btn72" id="confirm"  value="确定">&nbsp;&nbsp;&nbsp;&nbsp;
                                			   <input class="btn72" type="button" name="cancel" id="cancel" value="取消" onclick="javascript:history.back()">&nbsp;&nbsp;&nbsp;&nbsp;
                                			   <input type="button" onclick="printme();" value="打 印" class="btn72">
                                            </div>
                                             <%}%>
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
   <script src="<%=STATIC_URL%>/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
   <script>
   
   $(document).ready(function() {
	   $("#majorTypeId").on("change",function(){
			var majorTypeId = $("#majorTypeId").val() ;	
			if(majorTypeId == ""){
				$("#majorId").empty() ;
				return ;
			}
			var major = $("#majorId") ;
			major.empty() ; 
		    major.append("<option value =''></option>"); 
			$.ajax({
		        type: 'POST',
		        dataType:'json',
		        url: "/declare/admin/TeacherMajorInfoJsonList.do?typeId="+majorTypeId,
		        success: function(data){
		        	//拼接字符串 
		        	if(data != null){
		    			 for (var i = 0; i < data.result.length; i++) {
							var item = data.result[i] ;
							var originalId = $('#originalMajorId').val();
							if(originalId == item.majorId){
								major.append("<option value = '"+item.majorId+"' selected>"+item.majorName+"</option>");
					      	}else{
					      		major.append("<option value = '"+item.majorId+"'>"+item.majorName+"</option>");
					      	}
						}  		
		        	}
		        },
		        error: function(){
		            return;
		        }
		    });
		}) ;
		 $('#majorTypeId').trigger('change') ;
	});
  
   function checkThisForm(name){
	      var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	      //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
	   	  var cardNo = $("#identityNumber").val()  ;
	      if(""==cardNo){
	    	  alert("身份证号码不能为空");  
	          $("#identityNumber").focus() ;
	          return  false;  
	      }
	
	      if(reg.test(cardNo) === false)  
	      {  
	          alert("身份证号码格式不合法");  
	          $("#identityNumber").focus() ;
	          return  false;  
	      }   
	      var re4 = /^\d{10,11}$/;
	      var userMobile = $("#phone").val() ;
	      if(re4.test(userMobile) === false)
			{
			    alert("手机号输入不合法");
			    $("phone").focus() ;
			    return false;
			}
	      
	   	return checkForm(name) ;
}
   function up() {
	   $("#confirm").attr({"disabled":"disabled"});
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
	                url: '/declare/admin/DeclareImageManage.do',
	                data: {
	                	imgData:imgData,
	                	imgType:imgType
	                },
	                type: 'POST',
	                success: function (data) {
	                    console.log(data);
	                    if(data.code==200){
	                        var url=data.message;
	                        var img_preview = $('#img_preview');
	                        $('#imagePath').val(url);
	                        img_preview.attr("src",url);
	                        img_preview.attr("width","86");
	                        img_preview.attr("height","129"); 	
	                        $("#confirm").removeAttr("disabled");
	                    }else{
	                    	alert(data.message);
	                    	$("#confirm").removeAttr("disabled");
	                    }
	                },
	                error: function (XMLHttpRequest, textStatus, errorThrown) {
	                	 console.log(errorThrown);
	                    alert(errorThrown);
	                    $("#confirm").removeAttr("disabled");
	                }
	            });
	        }
	    }
	    image.src=path;
	    //文件压缩结束
	    $('#img_preview')[0].onload = function() {
    }
   }
   
   function fileUp() {
	   $("#confirm").attr({"disabled":"disabled"});
	    var file_data = $('#fileUpload')[0].files; // for multiple files
	    for (var i = 0; i < file_data.length; i++) {
	        var fd = new FormData();
	        fd.append('file', file_data[i]);
	        $.ajax({
	        		url: 'FileInfoUpload.do?infoType=<%=FileInfoConfig.INFO_TYPE_TEACHER_ATTACH%>&infoId=<%=bean.getTeacherId() %>',
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
	                	 	$("#confirm").removeAttr("disabled");
	                }else{
	                		alert(data.message);
	                		$("#fileId").val("");
	                	 	$("#confirm").removeAttr("disabled");
	                }
	            },
	            error: function (XMLHttpRequest, textStatus, errorThrown) {
	                console.log(errorThrown);
	            }
	        });
	    }
	}
   </script>
</body>
</html>