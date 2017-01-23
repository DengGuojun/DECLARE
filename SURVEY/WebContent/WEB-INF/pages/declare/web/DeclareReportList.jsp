<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lpmas.framework.config.*"%>
<%@ page import="com.lpmas.framework.bean.*"%>
<%@ page import="com.lpmas.framework.page.*"%>
<%@ page import="com.lpmas.framework.util.*"%>
<%@ page import="com.lpmas.framework.web.*"%>
<%@ page import="com.lpmas.declare.config.*"  %>
<%@ page import="com.lpmas.declare.bean.*"  %>
<%
	List<DeclareReportBean> list = (List<DeclareReportBean>)request.getAttribute("DeclareReportList");
	PageBean PAGE_BEAN = (PageBean)request.getAttribute("PageResult");
	List<String[]> COND_LIST = (List<String[]>)request.getAttribute("CondList");
%>
<%@ include file="../../include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资助项目</title>
<link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet" />
<script type='text/javascript' src="<%=STATIC_URL %>/js/jquery.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/admin_common.js"></script>
<script type='text/javascript' src="<%=STATIC_URL %>/js/ui.js"></script>
</head>
<body class="article_bg">
<p class="article_tit">资助项目申请信息列表</p>
<form name="formSearch" method="post" action="DeclareReportList.do">
<div class="search-box">
  	<em class="em1">姓名：</em>
    <input type="text" name="userName" id="userName" value="<%=ParamKit.getParameter(request, "userName", "") %>" size="20"/>
    <em class="em1">审核状态：</em>
    <select name="declareStatus" id="declareStatus">
    	<%String declareStatus = ParamKit.getParameter(request, "declareStatus", "");%>
    	<option value="" ></option>
        <option value="SUBMIT" <%=("SUBMIT".equals(declareStatus))?"selected":"" %>>待审核</option>
        <option value="APPROVE" <%=("APPROVE".equals(declareStatus))?"selected":"" %>>通过</option>
        <option value="NOT_APPROVE" <%=("NOT_APPROVE".equals(declareStatus))?"selected":"" %>>不通过</option>
    </select>
    <input name="" type="submit" class="search_btn_sub" value="查询"/>
  </div>
   <table width="100%" border="0"  cellpadding="0" class="table_style">
    <tr>
      <th><input type="checkbox" id="checkAllApprove"></th>
      <th>申报ID</th>
      <th>姓名</th>
      <th>申报类型</th>
      <th>申报状态</th>
      <th>申报时间</th>
      <th>操作</th>
    </tr>
    <%
    for(DeclareReportBean bean:list){%> 
    <tr>
      <td><input type="checkbox" name="checkDeclare" id="check_<%=bean.getDeclareId() %>" value="<%=bean.getDeclareId()%>"></td>
      <td><%=bean.getDeclareId() %></td>
      <td><%=bean.getUserName() %></td>
      <td><%=DeclareInfoConfig.DECLARE_TYPE_MAP.get(bean.getDeclareType())%></td>
      <td><%=DeclareInfoConfig.DECLARE_STATUS_MAP.get(bean.getDeclareStatus()).equals("已提交") ? "待审核": DeclareInfoConfig.DECLARE_STATUS_MAP.get(bean.getDeclareStatus())%></td>
      <td><%=DateKit.formatTimestamp(bean.getCreateTime(), DateKit.DEFAULT_DATE_TIME_FORMAT)%></td>
      <td align="center">
      	<a href="/declare/DeclareReportManage.do?declareId=<%=bean.getDeclareId()%>">查看</a> 
      	<%if(!bean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_APPROVE)){ %>
      	<a onclick="approve(<%=bean.getDeclareId()%>);">通过</a>
      	<%} %>
      	<%if(!bean.getDeclareStatus().equals(DeclareInfoConfig.DECLARE_STATUS_NOT_APPROVE)){ %>
      	<a href="/declare/DeclareInfoApprove.do?declareId=<%=bean.getDeclareId()%>&approveAction=1">不通过</a>
      	<%} %>
      </td>
    </tr>	
    <%} %>
  </table>
</form>
<ul class="page_info">
<li class="page_left_btn">
  	<input type="button" name="button" id="button" value="审核通过">
</li>
<%@ include file="../../include/page.jsp" %>
</ul>
</body>
<script type='text/javascript'>
$(function() {
    $("#checkAllApprove").click(function() {
         $('input[name="checkDeclare"]').attr("checked",this.checked); 
     });
     var $subBox = $("input[name='checkDeclare']");
     $subBox.click(function(){
         $("#checkAllApprove").attr("checked",$subBox.length == $("input[name='checkDeclare']:checked").length ? true : false);
     });   
 });
$(document).ready(function() {
	$("#button").click(
		function() {
			 var checkDeclareList="";
			  $("input[name='checkDeclare']:checked").each(function () {
		         if(checkDeclareList!="")    
		         {    
		        	 checkDeclareList+=",";    
		          }    
		         checkDeclareList += $(this).val(); 
		     });
		var url = "DeclareInfoApprove.do?checkDeclareList="+ checkDeclareList;
	    window.location.href= url
	});
});
function approve(declareId) {
	 $("#check_"+ declareId).attr("checked",true);
	 document.getElementById("button").click(); 
}
</script>
</html>