<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%
	String callbackFun = ParamKit.getParameter(request, "callbackFun", "callbackFun");
    String clickId = (String)request.getAttribute("clickId");
	List<TrainingMaterialInfoBean> list = (List<TrainingMaterialInfoBean>)request.getAttribute("TrainingMaterialInfoList");
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
    <title>新型农民职业培训系统 — 选择培训教材</title>
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/StyleBlue.css" type="text/css" rel="Stylesheet">
    
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
    <div class="warpDefaultMgr">
            <table class="table_wrp">
                <tbody>
                    <tr>
                        <td valign="top">
                            <div class="detail_right">
                                <div class="content_wrap">
                                    <div class="detail_right_title h1 white">
                                      <span style="float: left;">选择培训教材</span></div>
                                    <div class="right_bg">
                                      <input type="hidden" name="clickId" id="clickId" value="<%=clickId %>"/>
                                      <div>
                                        <table class="editView" style="width: 98%">
                                            <tbody>
                                                <tr>
                                                    <td align="right" class="td_head" style="width:30%;">
                                                        <span style="color:#FE7200;">*</span>
                                                        <span>培训教材</span>
                                                    </td>
                                                    <td align="left" class="td_data">
                                                          <select style="background-color:White;" name="materialName" id="materialName">
			                                                <option value="" >全部</option>
														    	<%
														    	String materialName = ParamKit.getParameter(request, "materialName", "");
														    	for(TrainingMaterialInfoBean trainingMaterialInfoBean : list){ %>
														        <option value="<%=trainingMaterialInfoBean.getMaterialName() %>" <%=(trainingMaterialInfoBean.getMaterialName().equals(materialName))?"selected":"" %>><%=trainingMaterialInfoBean.getMaterialName() %></option>
														    <%} %>
			                                            </select>
                                                    </td>
                                                </tr>                                              
                                            </tbody>
                                        </table>
                                        <div class="text_center mt_5" style="width: 98%">
                                            <input type="button" value="确定" class="btn72" onclick="callbackTo()"/>
                                        </div>
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
<script src="<%=STATIC_URL%>/js/Public.js" type="text/javascript"></script>
<script src="<%=STATIC_URL%>/js/InsertFlash.js" type="text/javascript"></script>
<script src="<%=STATIC_URL%>/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
</body>
<script>
function callbackTo(){
	var value = $('#materialName').val();
	var clickId = $('#clickId').val();
	if (value == ''){
		alert("请选择培训教材");
		return;
	}
	self.parent.<%=callbackFun %>(value,clickId);
	try{ self.parent.jQuery.fancybox.close(); }catch(e){console.log(e);}
}
</script>
</html>