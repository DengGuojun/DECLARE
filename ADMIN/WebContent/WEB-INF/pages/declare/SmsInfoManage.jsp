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
	SmsInfoBean bean = (SmsInfoBean)request.getAttribute("SmsInfo");
	AdminUserHelper adminUserHelper =(AdminUserHelper) request.getAttribute("AdminUserHelper") ;
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!–[if lte IE 8]> 
  <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
  <![endif]–> 
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
  <title>新型农民职业培训系统 — 发送短信</title>
   <%@ include file="../include/header.jsp" %>
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/SlideMenuStyle.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/default.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/StyleBlue.css" type="text/css" rel="Stylesheet">
    <link rel="stylesheet" type="text/css" href="<%=STATIC_URL %>/css/zTreeStyle/zTreeStyle.css">
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
	ul.ztree{
            margin-top: 10px;
            border: 1px solid #617775;
            background: #f0f6e4;
            width: 320px;
            height: 360px;
            overflow-y: scroll;
            overflow-x: auto;
        }
  </style>

</head>
<body class="body-index">
<%@include file="../nav/navigation.jsp" %>
  <form name="formData" id="formData" method="post" action="SmsInfoManage.do" onsubmit="javascript:return checkForm('formData');" >
  <input type="hidden" name="senderId" value="<%=bean.getSenderId()%>"/>
  <input type="hidden" name="status" value="<%=bean.getStatus()%>"/>
  
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
                                        <span style="float: left;">发送短信</span>
                                    </div>
                                    <div class="right_bg" style="padding-left: 5px;">
                                        <div style="width: 600px; margin: 0 auto;">
                                            <div class="form-group">
                                                <label class="int_default">接收者：</label>
                                                <div class="form-control fl">
                                                <input type="text" readonly id="citySel" name="receiverList" onclick="showMenu();" class="form-control int_normal"  />
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="int_default">手机号码：</label>
                                                <input type="text" name="mobile" class="form-control int_normal" >
                                                <span>(以逗号“,”隔开)</span>
                                            </div>
                                            <div class="form-group">
                                                <label class="int_default">短信内容：</label>
                                                <textarea  name="content" class="form-control int_normal" rows="20" checkStr="内容;txt;true;;100000"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label class="int_default">授权码：</label>
                                                <input type="text" name="code" class="form-control int_normal" >
                                            </div>
                                            <div class="text_center mt_15">
                                                <input type="submit" class="btn72 mt_5" value="发 送">
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
	<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
        <ul id="treeDemo" class="ztree" ></ul>
    </div>
  </form>
  
  <div class="contents-footer"></div>
  <script type="text/javascript" src="<%=STATIC_URL%>/js/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="<%=STATIC_URL%>/js/jquery.ztree.excheck.min.js"></script>
  <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
</body>

<script type="text/javascript">
		//zTree
		var setting = {
			async: {
				enable: true,
				url:"SmsGroupSelect.do",
				autoParam:["id", "name", "level"],
				dataFilter: filter
			},
		    check: {
		        enable: true,
		        chkboxType: {"Y": "ps","N": "ps"}
		    },
		    view: {
		        dbclickExpand: false
		    },
		    data: {
		        simpleData: {
		            enable: true
		        }
		    },
		    callback: {
		        beforeClick: beforeClick,
		        onCheck: onCheck
		    }
		};
		
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}


		function beforeClick(treeId, treeNode){
		    var zTree = $.fn.zTree.getZTreeObj('treeDemo');
		    zTree.checkNode(treeNode, !treeNode.checked, null, true);
		    return false;
		}

		function onCheck(e, treeId, treeNode){
		    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		    nodes = zTree.getCheckedNodes(true),
		    v = "";
		    for (var i=0, l=nodes.length; i<l; i++) {
		        v += nodes[i].name + ",";
		    }
		    if (v.length > 0 ) v = v.substring(0, v.length-1);
		    var cityObj = $("#citySel");
		    cityObj.attr("value", v);
		}

		function showMenu() {
		    var cityObj = $("#citySel");
		    var cityOffset = $("#citySel").offset();
		    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		    $("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
		    $("#menuContent").fadeOut("fast");
		    $("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
		    if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		        hideMenu();
		    }
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
		});

</script>

</html>