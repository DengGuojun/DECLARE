<%@page import="java.util.Map.Entry"%>
<%@page import="com.lpmas.declare.config.DeclareInfoConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.framework.bean.*"  %>
<%@ page import="com.lpmas.framework.page.*"  %>
<%@ page import="com.lpmas.framework.util.*"  %>
<%@ page import="com.lpmas.framework.web.*"  %>
<%@ page import="com.lpmas.framework.config.*"  %>
<%@ page import="com.lpmas.declare.admin.bean.*"  %>
<%@ page import="com.lpmas.declare.invoker.bean.*"  %>
<%@ page import="com.lpmas.declare.admin.business.*"  %>
<%@ page import="com.lpmas.declare.admin.config.*"  %>
<%
	AdminUserHelper adminUserHelper = (AdminUserHelper)request.getAttribute("AdminUserHelper");
	Integer declareInfoCount = (Integer)request.getAttribute("declareInfoCount");
	Integer declareAuthInfoCount = (Integer)request.getAttribute("declareAuthInfoCount");
	Integer teacherInfoCount = (Integer)request.getAttribute("teacherInfoCount");
	Integer classInfoCount = (Integer)request.getAttribute("classInfoCount");
	List<AnnouncementInfoBean> announcementList = (List<AnnouncementInfoBean>)request.getAttribute("AnnouncementList");
	List<MessageReceiverBean> messageList = (List<MessageReceiverBean>)request.getAttribute("MessageList");
	Map<Integer,String> messageCreateUserMap =(Map<Integer,String>)request.getAttribute("MessageCreateUserMap");
	Map<Integer, String> targetPercentMap = (Map<Integer,String>)request.getAttribute("targetPercentMap");
	Map<Integer, Integer> targetFinishedMap = (Map<Integer, Integer>)request.getAttribute("targetFinishedMap");
	Map<Integer, String> teacherNameMap = (Map<Integer, String>)request.getAttribute("teacherNameMap");
	List<TeacherInfoBean> recommendTeacherList = (List<TeacherInfoBean>)request.getAttribute("RecommendTeacherList");
	Map<Integer,String> majorNameMap = (Map<Integer,String>)request.getAttribute("MajorNameMap");
	List<TrainingOrganizationInfoBean> recommendBaseList = (List<TrainingOrganizationInfoBean>)request.getAttribute("RecommendBaseList");
	List<TrainingMaterialInfoBean> recommendMaterialList = (List<TrainingMaterialInfoBean>)request.getAttribute("RecommendMaterialList");
	List<ArticleInfoBean> imageList = (List<ArticleInfoBean>)request.getAttribute("ImageList");
	List<ArticleInfoBean> articleList = (List<ArticleInfoBean>)request.getAttribute("ArticleList");
	Integer year = (Integer)request.getAttribute("year");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!–[if lte IE 8]> 
    <meta http-equiv="X-UA-Compatible" content=”IE=7″ /> 
    <![endif]–> 
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrom=1" />
    <title>新型农民职业培训系统 — 首页</title>
    <%@ include file="../include/header.jsp" %>
    <link href="<%=STATIC_URL %>/css/common_new_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common_new.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/common.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/main.css" type="text/css" rel="stylesheet">
    <link href="<%=STATIC_URL %>/css/SlideMenuStyle.css" type="text/css" rel="Stylesheet">
    <link href="<%=STATIC_URL %>/css/default.css" type="text/css" rel="stylesheet">
     <link rel="stylesheet" type="text/css" href="<%=STATIC_URL %>/css/swiper.css">
    <link rel="stylesheet" type="text/css" href="<%=STATIC_URL %>/css/index_main.css">
</head>
<body >
<%@include file="../nav/navigation.jsp" %>
  <div class="container">
		<div class="col-3">
			<div class="inner-body">
				<!-- 公告通知 -->
				<div class="section-md">
					<div class="head-title hd-line">
						<h2>公告通知</h2>
					</div>
					<div class="section-body">
						<div class="news-list">
						<%for(AnnouncementInfoBean announcementInfoBean : announcementList){ %>
							<div class="list-item bor">
								<div class="item-tit"><div class="content"><a href="AnnouncementInfoView.do?announcementId=<%=announcementInfoBean.getAnnouncementId() %>"><%=announcementInfoBean.getAnnouncementTitle() %></a></div></div>
								<div class="item-sub"><%=announcementInfoBean.getCreateTime()==null? "":DateKit.formatTimestamp(announcementInfoBean.getCreateTime(), DateKit.DEFAULT_DATE_FORMAT) %></div>
							</div>
						<%} %>
						</div>
					</div>
					<div class="read-more"><a href="/declare/admin/AnnouncementInfoList.do">更多 &gt;</a></div>
				</div>
				<!-- 内部邮件 -->
				<div class="section-md">
					<div class="head-title hd-line">
						<h2>内部邮件
							<a href="/declare/admin/MessageInfoReceiveList.do">收到邮件</a>
							<a href="/declare/admin/MessageInfoSendList.do">已发送</a>
						</h2>
					</div>
					<div class="section-body">
						<div class="news-list">
						<%for(MessageReceiverBean messageReceiverBean : messageList){ %>
							<div class="list-item bor">
								<div class="item-tit"><div class="content"><a onclick="javascript:location.href='MessageInfoView.do?messageId=<%=messageReceiverBean.getMessageId()%>'"><%=messageReceiverBean.getMessageTitle() %></a></div></div>
								<div class="item-sub"><%=messageReceiverBean.getCreateTime()==null? "":DateKit.formatTimestamp(messageReceiverBean.getCreateTime(), DateKit.DEFAULT_DATE_FORMAT) %></div>
							</div>
						<%} %>
						</div>
					</div>
					<div class="read-more"><a href="/declare/admin/MessageInfoReceiveList.do">更多 &gt;</a></div>
				</div>
				<!-- 常见问题 -->
				<!-- <div class="section-md">
					<div class="head-title hd-line">
						<h2>常见问题
						</h2>
					</div>
					<div class="section-body">
						<div class="news-list">
							<div class="list-item">
								<div class="item-tit"><div class="content">工作考核工作考核工作考核工作考核工作考核工作考核工作考核工作考核工作考核工作考核</div></div>
							</div>
							<div class="list-item">
								<div class="item-tit"><div class="content">工作考核工作考核工作考核工作考核工作考核工作考核工作考核工作考核工作考核工作考核</div></div>
							</div>
						</div>
					</div>
					<div class="read-more"><a>更多 &gt;</a></div>
				</div> -->
			</div>
		</div>
		<div class="col-6 pt-8">
			<div class="section-md">
				<!-- 轮播图 -->
				<div class="fl section-swiper">
					<div class="swiper-container index-swiper">
					  <div class="swiper-wrapper">
					  	  <%for(ArticleInfoBean article: imageList) {%>
					      <div class="swiper-slide">
					      	<div class="media-item">
					      		<a href="<%=article.getArticleUrl()%>" target="blank">
					      		<img src="<%=article.getPicture1()%>"/>
					      		<div class="ft-txt"><%=article.getArticleTitle() %></div>
					      		</a>
					      	</div>
					      </div>
					      <%} %>
					  </div>
					  <div class="pagination"></div>
					</div>
				</div>
				<!-- 动态资讯-->
				<div class="mg-left box-extra">
					<div class="head-title font-gen">
						<h2>动态资讯
						</h2>
					</div>
					<div class="section-body">
						<div class="news-list">
						<%for(ArticleInfoBean article: articleList) {%>
							<div class="list-item one">
								<div class="item-tit"><div class="content"><a href="<%=article.getArticleUrl()%>" target="blank"><%=article.getArticleTitle() %></a></div></div>
							</div>
						<%} %>
						</div>
					</div>
				</div>
			</div>
			<!-- 培育对象入库情况 -->
			<div class="section-md" >
				<a href="DeclareInfoRecommendList.do" style="text-decoration:none">
				<div class="head-title hd-line">
					<h2>培育对象入库情况</h2>
				</div>
				<div class="sub-year"><%=year %>年度</div>
				<div class="section-body">
					<ul class="card-show">
						<li>
							<div class="card-item">
								<div class="num" dataAsync="<%=IndexInfoConfig.GET_YOUNG_FARMER_DECLARE_COUNT%>"></div>
								<div class="tit">现代青年农场主</div>
							</div>
						</li>
						<li>
							<div class="card-item">
								<div class="num" dataAsync="<%=IndexInfoConfig.GET_LEADER_FARMER_DECLARE_COUNT%>"></div>
								<div class="tit">新型农业经营主体带头人</div>
							</div>
						</li>
						<li>
							<div class="card-item">
								<div class="num" dataAsync="<%=IndexInfoConfig.GET_PRODUCT_FARMER_DECLARE_COUNT%>"></div>
								<div class="tit">生产经营型</div>
							</div>
						</li>
						<li>
							<div class="card-item">
								<div class="num" dataAsync="<%=IndexInfoConfig.GET_TECHNICAL_FARMER_DECLARE_COUNT%>"></div>
								<div class="tit">专业技能型</div>
							</div>
						</li>
						<li>
							<div class="card-item">
								<div class="num" dataAsync="<%=IndexInfoConfig.GET_SERVICE_FARMER_DECLARE_COUNT%>"></div>
								<div class="tit">专业服务型</div>
							</div>
						</li>
					</ul>
				</div>
				</a>
			</div>

			<!-- 教育培训进展情况 -->
			<div class="section-md">
				<a href="TrainingClassInfoList.do?trainingType=1" style="text-decoration:none">
				<div class="head-title hd-line">
					<h2> 教育培训进展情况 </h2>
				</div>
				<div class="sub-year"><%=year %>年度</div>
				<div class="section-body">
					<div class="progress-box">
						<div class="table-style">
							<div class="table-cell">
								<div class="progress-bar">
									<div class="line" style="width: <%=Double.valueOf(targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER))>100? "100%": targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER) +"%"%>"></div>
								</div>
								<div class="progress-label"><%=targetFinishedMap.get(DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER)%>人<em><%=targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_YOUNG_FARMER)+"%"%></em></div>
							</div>
							<div class="table-cell progress-tit">现代农业青年农场主</div>
						</div>
					</div>
					<div class="progress-box">
						<div class="table-style">
							<div class="table-cell">
								<div class="progress-bar">
									<div class="line" style="width: <%=Double.valueOf(targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER))>100? "100%": targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER) +"%"%>"></div>
								</div>
								<div class="progress-label"><%=targetFinishedMap.get(DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER)%>人<em><%=targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_LEADER_FARMER)+"%"%></em></div>
							</div>
							<div class="table-cell progress-tit">新型农业经营主体带头人</div>
						</div>
					</div>
					<div class="progress-box">
						<div class="table-style">
							<div class="table-cell">
								<div class="progress-bar">
									<div class="line" style="width: <%=Double.valueOf(targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER))>100? "100%": targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER) +"%"%>"></div>
								</div>
								<div class="progress-label"><%=targetFinishedMap.get(DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER)%>人<em><%=targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_PRODUCT_FARMER)+"%"%></em></div>
							</div>
							<div class="table-cell progress-tit">生产经营型</div>
						</div>
					</div>
					<div class="progress-box">
						<div class="table-style">
							<div class="table-cell">
								<div class="progress-bar">
									<div class="line" style="width: <%=Double.valueOf(targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER))>100? "100%": targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER) +"%"%>"></div>
								</div>
								<div class="progress-label"><%=targetFinishedMap.get(DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER)%>人<em><%=targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_TECHNICAL_FARMER)+"%"%></em></div>
							</div>
							<div class="table-cell progress-tit">专业技能型</div>
						</div>
					</div>
					<div class="progress-box">
						<div class="table-style">
							<div class="table-cell">
								<div class="progress-bar">
									<div class="line" style="width: <%=Double.valueOf(targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER))>100? "100%": targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER) +"%"%>"></div>
								</div>
								<div class="progress-label"><%=targetFinishedMap.get(DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER)%>人<em><%=targetPercentMap.get(DeclareInfoConfig.DECLARE_TYPE_SERVICE_FARMER)+"%"%></em></div>
							</div>
							<div class="table-cell progress-tit">专业服务型</div>
						</div>
					</div>
				</div>
				</a>
			</div>

			<!-- 认定进展情况 -->
			<div class="section-md">
				<a href="TrainingOrganizationInfoList.do?organizationType=ORGANIZATION_AUTHORIZEDE" style="text-decoration:none">
				<div class="head-title hd-line">
					<h2>认定进展情况</h2>
				</div>
				<div class="sub-year"><%=year %>年度</div>
				<div class="section-body">
					<ul class="card-show big">
						<li>
							<div class="card-item">
								<div class="num" dataAsync="<%=IndexInfoConfig.GET_AUTH_PRODUCT_FARMER_DECLARE_COUNT%>"></div>
								<div class="tit">生产经营型</div>
							</div>
						</li>
						<li>
							<div class="card-item">
								<div class="num" dataAsync="<%=IndexInfoConfig.GET_AUTH_TECHNICAL_AND_SERVICE_FARMER_DECLARE_COUNT%>"></div>
								<div class="tit">专业技能型&专业服务型</div>
							</div>
						</li>
					</ul>
				</div>
				</a>
			</div>
		</div>
		<div class="col-3">
			<div class="inner-body">
				<!-- 新型职业农民培育师资 -->
				<div class="section-md">
					<div class="head-title hd-line">
						<h2>新型职业农民培育师资</h2>
					</div>
					<div class="section-body">
						<ul class="card-show half">
							<li>
								<div class="card-item">
									<div class="num" dataAsync="<%=IndexInfoConfig.GET_TEACHER_COUNT%>"></div>
									<div class="tit">总人数</div>
								</div>
							</li>
							<li>
								<div class="card-item">
									<div class="num" dataAsync="<%=IndexInfoConfig.GET_TEACHER_COUNT_INCRESAE_THIS_YEAR%>"></div>
									<div class="tit">今年新增</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<!-- 新型职业农民培育基地 -->
				<div class="section-md">
					<div class="head-title hd-line">
						<h2>新型职业农民培育基地</h2>
					</div>
					<div class="section-body">
						<ul class="card-show half">
							<li>
								<div class="card-item">
									<div class="num" dataAsync="<%=IndexInfoConfig.GET_BASE_TRAINING_COUNT%>"></div>
									<div class="tit">基地总数</div>
								</div>
							</li>
							<li>
								<div class="card-item">
									<div class="num" dataAsync="<%=IndexInfoConfig.GET_BASE_TRAINING_COUNT_INCRESAE_THIS_YEAR%>"></div>
									<div class="tit">今年新增</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<!-- 新型职业农民培育教材 -->
				<div class="section-md">
					<div class="head-title hd-line">
						<h2>新型职业农民培育教材</h2>
					</div>
					<div class="section-body">
						<ul class="card-show half">
							<li>
								<div class="card-item">
									<div class="num" dataAsync="<%=IndexInfoConfig.GET_TRAINING_MATERIAL_COUNT%>"></div>
									<div class="tit">教材总数</div>
								</div>
							</li>
							<li>
								<div class="card-item">
									<div class="num" dataAsync="<%=IndexInfoConfig.GET_TRAINING_MATERIAL_COUNT_INCRESAE_THIS_YEAR%>"></div>
									<div class="tit">今年新增</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
				<!-- 推荐师资 -->
				<div class="section-md">
					<div class="head-title hd-line">
						<h2>推荐师资</h2>
					</div>
					<table class="table-box">
						<tbody>
							<tr class="th-head">
								<th>名称</th>
								<th>专业</th>
							</tr>
							<%for(TeacherInfoBean teacher : recommendTeacherList){ %>
								<tr>
									<td class="tl"><%=teacher.getTeacherName() %></td>
									<td><%=majorNameMap.get(teacher.getTeacherId()) %></td>
								</tr>
							<%} %>
						</tbody>
					</table>
				</div>
				<!-- 推荐基地 -->
				<div class="section-md">
					<div class="head-title hd-line">
						<h2>推荐基地</h2>
					</div>
					<table class="table-box">
						<tbody>
							<tr class="th-head">
								<th>名称</th>
								<th>地区</th>
							</tr>
							<%for(TrainingOrganizationInfoBean base : recommendBaseList){ %>
								<tr>
									<td  class="tl"><%=base.getOrganizationName()%></td>
									<td><%=base.getProvince() %><%=base.getCity() %><%=base.getRegion() %></td>
								</tr>
							<%} %>
						</tbody>
					</table>
				</div>
				<!-- 推荐教材 -->
				<div class="section-md">
					<div class="head-title hd-line">
						<h2>推荐教材</h2>
					</div>
					<table class="table-box">
						<tbody>
							<tr class="th-head">
								<th>名称</th>
								<th>分类</th>
							</tr>
							<%for(TrainingMaterialInfoBean material : recommendMaterialList){ %>
								<tr>
									<td class="tl"><%=material.getMaterialName() %></td>
									<td><%=material.getIndustry()%></td>
								</tr>
							<%} %>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="footer"></div>
	<script type="text/javascript" src="<%=STATIC_URL %>/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=STATIC_URL %>/js/swiper.js"></script>
    <script src="<%=STATIC_URL%>/js/app.js" type="text/javascript"></script>
    <script src="<%=STATIC_URL%>/js/index_info.js" type="text/javascript"></script>
</body>
<script>
$(document).ready(function(){
	init();
	refreshDataAsync();
});
</script>

 <script type="text/javascript">
    	$(function(){
    		var indexSwiper = $('.index-swiper').swiper({
    			autoplay: 5000,
    			speed: 600,
    			loop: true,
    			pagination : '.pagination',
    			paginationClickable :true
    		})
    	})
    </script>
</html>