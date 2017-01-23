<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>创业资助登记</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link type="text/css" rel="stylesheet" href="<%=STATIC_URL %>css/style.css">
</head>
<body>
<section class="g-pa">
    <a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=2">
        <div class="form-box">
            生产经营型职业农民创业资助登记
        </div>
    </a>
    <a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=3">
        <div class="form-box">
            专业技能型职业农民创业资助登记
        </div>
    </a>
    <a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=4">
        <div class="form-box">
            专业服务型职业农民创业资助登记
        </div>
    </a>
    <a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=1">
        <div class="form-box">
            现代青年农场主计划创业资助登记
        </div>
    </a>
    <a href="<%=PORTAL_URL %>declare/DeclareInfoManage.do?declareType=5">
        <div class="form-box">
            新型农业经营主体带头人轮训计划创业资助登记
        </div>
    </a>
     <a href="<%=PORTAL_URL%>wap/about.html">
        <div class="g-mt10 formdetail-box">
            <p><span class="bor-r detail-icon">i</span>登记说明</p>
        </div>
    </a>
</section>
</body>
</html>