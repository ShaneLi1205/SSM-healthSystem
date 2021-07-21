<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" >
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script src="${pageContext.request.contextPath}/js/function.js"></script>
<script src="${pageContext.request.contextPath}/ueditor/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js" type="text/javascript" charset="utf-8"> </script>
<script src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js" type="text/javascript" charset="utf-8-BOM"></script>
<script>
    document.onreadystatechange = subSomething;
    //当页面加载状态改变的时候执行的方法.
    function subSomething()
    {
        if(document.readyState == "complete") {
            //当页面加载状态为完全结束时进入
            if (${workerObj == null}){
                location.href="${pageContext.request.contextPath}/"
            }
        }
    }
</script>
<html>
<head>
    <title>文章发布</title>
</head>
<body>
<div id="s-top-left" class="s-top-left s-isindex-wrap">
    <a href="${pageContext.request.contextPath}/">首页</a>
    <c:choose>
        <c:when test="${workerObj == null}">
            <a href="javascript:openLogin();">登录</a>
            <a href="javascript:openRegister()">注册</a>
        </c:when>
        <c:otherwise>
            <c:if test="${workerObj != null}">
                <span>${workerObj.workerName}</span>
                <a href="${pageContext.request.contextPath}/UserInfoMange.jsp">账号管理</a>
                <a href="${pageContext.request.contextPath}/Action?method=logout">退出登录</a>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>
<div class="article-release">
    <! -- 在form中设置隐藏控件，用来存储JS中的值 -->
    <input type="hidden" name="articleContent" id="articleContent">
    <input type="hidden" name="articleSummary" id="articleSummary">
    <input type="hidden" name="workerId" id="workerId" value="${workerObj.getWorkerId()}">
    <div class="center">
        文章标题：<input type="text" name="articleTitle" id="articleTitle"  placeholder="请输入文章标题(30字以内)" maxlength="30"></br></br>
        文章类型：
        <select name="selectList" id="articleReleaseClassId" >
                <c:forEach var="articleClass" items="${articleClassList}">
                    <option  value="${articleClass.articleClassId}">${articleClass.articleClassName}</option>
                </c:forEach>
        </select>
        </br></br>
        <div>
            文章内容：
            <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
        </div>
        </br></br>
        <div id="btns">
            <div>
                <button onclick="workerReleaseArticle()">发布</button>
            </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    window.UEDITOR_HOME_URL = "/LiXianghao_event/web/ueditor";
    var ue = UE.getEditor('editor',{
        toolbars:[['Source','bold','italic','inserttitle','paragraph','forecolor','simpleupload','Undo','Redo']],

    });
    function saveContent() {
        document.getElementById('articleContent').value = UE.getEditor('editor').getContent();
        document.getElementById('articleSummary').value = UE.getEditor('editor').getContentTxt();
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
</script>
<style type="text/css">
    div{
        width:100%;
    }
    .article-release{
        min-width: 400px;
        height: 200px;
        padding-left: 20vw;
        padding-bottom: 100px;
        margin-bottom: 100px;
        border-bottom: 100px;
        padding-top: 100px;
    }
</style>
</body>
</html>