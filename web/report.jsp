<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" >
<link href="css/style.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/js/function.js"></script>
<jsp:include page="Header.jsp"/>
<html>
<head>
    <title>举报信息</title>
</head>
<%--封禁界面--%>
<input type="hidden" id="banWorkerId">
<div  id="banManage" style="display: none">
    <div id="mangeBanContent" style="overflow-y:auto; overflow-x:auto;">
        <br>
        <label id="markSpan"></label> <span class="message" style="text-align: center;display: block"></span> <br>
        <button class="banButton" onclick="banWorker(1)">封禁1天</button><br><br>
        <button class="banButton" onclick="banWorker(3)">封禁3天</button><br><br>
        <button class="banButton" onclick="banWorker(7)">封禁7天</button><br><br>
        <button class="banButton" onclick="banWorker(30)">封禁30天</button><br><br>
        <button class="banButton" onclick="banWorker(0)">解封</button><br><br>
        <button onclick="closeFrame('banManage')">关闭</button>
    </div>
</div>
<body onload="javascript:listReport()">
<div id="main_content" class="hero">
    <div id="main" class="main">
        <div id="main_flow" class="main-flow">
            <div class="card">
                <div id="reportlist" class="post-list">

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<style>
    #banManage{
        POSITION:absolute; left:50%; top:50%;
        width:600px; height:500px;
        margin-left:-300px;
        margin-top:-200px;
        border:1px solid #888;
        background-color: whitesmoke;
        text-align: center;
        line-height: 40px; z-Index:3;
        box-shadow: 0 15px 25px rgba(0,0,0,.5);
        border-radius: 10px;
        display:table;
        overflow:auto;
    }
    .banButton{
        size: A5;
    }
</style>