<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" >
<link href="css/style.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/js/function.js"></script>
<jsp:include page="Header.jsp"/>

<html>
<head>
    <title>健康服务首页</title>
</head>
<body onload="javascript:homePageInit(1,0)">
<input type="hidden" id="keyword" value="none">
<div id="main_content" class="hero">
    <div id="main" class="main">
        <div id="side_nav" class="side-left tab-bar">
        </div>
        <div id="main_flow" class="main-flow">
            <div class="card">
                <div id="selectClassDiv">
                    文章类型：
                    <select name="selectList" id="classId" onchange="changeArticleListByClass()">
                        <option selected="selected" value="0">全部</option>
                    </select>
                </div>
                <div class="card headline">
                </div>
                <div id="post_list" class="post-list">
                </div>
                <div id="pager_bottom">
                    <div id="paging_block">
                        <div class="pager" id="pageContainer">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
