<%@page import="org.logicalcobwebs.concurrent.FJTask.Par"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	List<TeacherStatisticsBean> teacherStatisticsList = (List<TeacherStatisticsBean>)request.getAttribute("TeacherStatisticsList");
	String province = ParamKit.getParameter(request, "province","") ;
 	String city = ParamKit.getParameter(request, "city","") ;
 	int type = ParamKit.getIntParameter(request, "type", 1);
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 师资统计</title>
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
    <form name="ctl01" method="post"  id="ctl01">
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
                                      <span id="ctl00_ContentPlaceHolder1_Label1" style="float: left;">入库师资统计 </span></div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                    	<div class="form-group form-horizontal">
                                    		<select class="form-control" name="type" id="type">
                                                <option value="1" <%=1==type?"selected":"" %>>按职称统计</option>
                                                <option value="2" <%=2==type?"selected":"" %>>按类型统计</option>
                                            </select>
                                         </div> 
                                         <%
                                        if(StringKit.isValid(province)||StringKit.isValid(city)){
                                        %>
                                        	<input type="button" value="返回" class="btn72" onclick="javascript:history.back()"/>
                                        <%	 
                                         }
                                         %>
            								<input type="hidden" id="province" name="province" value="<%=ParamKit.getParameter(request, "province","")%>"> 
            								<input type="hidden" name="city" id='city' value="<%=ParamKit.getParameter(request, "city","")%>">
                                         <div>
                                         	 <!-- 等级  -->
                                            <table id="teacherStatistics" class="gv" cellspacing="0" rules="all" border="1" style="border-collapse:collapse; width: 97%; ">
                                              <tbody>
                                               <%if(type == 1){ %>
                                                <tr class="gv_Head">
                                                  <th style="width:30px;">序号</th>
                                                  <th><a>地区</a></th>
                                                  <th><a>总数</a></th>
                                                  <th><a>正高</a></th>
                                                  <th><a>副高</a></th>
                                                  <th><a>中级</a></th>
                                                  <th><a>初级</a></th>
                                                  <th><a>无</a></th>
                                                
                                                </tr>
	                            					<% int rowCount =1;
	                            					for(TeacherStatisticsBean teacherStaticsBean : teacherStatisticsList){
	                            					%>
	                            					 	 <tr class="gv_Item">
	                                                  <td><%=rowCount %></td>
	                                                  <td>
	                                                  <%if(teacherStaticsBean.isSum()){ %>
	                                                  <a href="TeacherStatisticsList.do?level=<%=teacherStaticsBean.getLevel()%>&province=<%=teacherStaticsBean.getProvince()%>&city=<%=teacherStaticsBean.getCity()%>"><%=teacherStaticsBean.getProvince()+teacherStaticsBean.getCity()+teacherStaticsBean.getRegion() %></a>
	                                                  <%}else{ %>
	                                                  <%=teacherStaticsBean.getProvince()+teacherStaticsBean.getCity()+teacherStaticsBean.getRegion() %>
	                                                  <%} %>
	                                                  </td>
	                                                  <td><%=teacherStaticsBean.getCount() %></td>
	                                                  <td><%=teacherStaticsBean.getSeniorCount() %></td>
	                                                  <td><%=teacherStaticsBean.getSubSeniorCount() %></td>
	                                                  <td><%=teacherStaticsBean.getMiddleCount() %></td>
	                                                  <td><%=teacherStaticsBean.getPrimaryCount() %></td>
	                                                  <td><%=teacherStaticsBean.getOtherLevelCount() %></td>
	                                                    </tr>
	                            					<%	rowCount++ ;
	                            					}
                                             }else{
                                             	%>
                                                    <tr class="gv_Head">
                                                      <th style="width:30px;">序号</th>
                                                      <th><a>地区</a></th>
                                                      <th><a>总数</a></th>
                                                      <th><a>种植业</a></th>
                                                      <th><a>养殖业</a></th>
                                                      <th><a>农村工程与服务</a></th>
                                                      <th><a>农村经营与农村管理</a></th>
                                                      <th><a>现代农业</a></th>
                                                      <th><a>公共基础</a></th>
                                                      <th><a>其他</a></th>
                                                    </tr>
                                					<% int rowCount =1;
	                            					for(TeacherStatisticsBean teacherStaticsBean : teacherStatisticsList){
	                            					%>
	                            					 	 <tr class="gv_Item">
	                                                  <td><%=rowCount %></td>
	                                                  <td>
	                                                  <%if(teacherStaticsBean.isSum()){ %>
	                                                  <a href="TeacherStatisticsList.do?type=2&level=<%=teacherStaticsBean.getLevel()%>&province=<%=teacherStaticsBean.getProvince()%>&city=<%=teacherStaticsBean.getCity()%>"><%=teacherStaticsBean.getProvince()+teacherStaticsBean.getCity()+teacherStaticsBean.getRegion() %></a>
	                                                  <%}else{ %>
	                                                  <%=teacherStaticsBean.getProvince()+teacherStaticsBean.getCity()+teacherStaticsBean.getRegion() %>
	                                                  <%} %>
	                                                  </td>
	                                                  <td><%=teacherStaticsBean.getCount() %></td>
	                                                  <td><%=teacherStaticsBean.getTypePlantCount() %></td>
	                                                  <td><%=teacherStaticsBean.getTypeBreedCount() %></td>
                                                  	 <td><%=teacherStaticsBean.getTypeEngineeringAndServerCount() %></td>
                                                  	 <td><%=teacherStaticsBean.getTypePublicManageCount() %></td>
                                                  	 <td><%=teacherStaticsBean.getTypeModernAgricultureCount() %></td>
                                                  	 <td><%=teacherStaticsBean.getTypePublicFoundationCount() %></td>
                                                  	 <td><%=teacherStaticsBean.getTypeOtherCount() %></td>
                            					</tr>
	                            					<%	rowCount++ ;
	                            					}%>
                                                  </tbody>
                                                </table>
                                             	<%	
                                             	} %>
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

    </form>
    <div class="contents-footer"></div>
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
    <script>
    $(document).ready(function(){
    	
    }) ;
    
	$("#type").change(function(){
		var type = $("#type").val() ;
		 
		var province = $("#province").val() ;
		var city = $("#city").val() ;
	 
		var urlStr = "" ;
		if(province!=""){
			urlStr="&province="+province ;
		}
		if(city!=""){
			urlStr="&city="+city ;
		}
		location.href="TeacherStatisticsList.do?type="+type+urlStr ;
	})
    </script>
</body>
</html>