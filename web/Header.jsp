<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/js/function.js"></script>
<script>
    document.onreadystatechange = subSomething;
    //当页面加载状态改变的时候执行的方法.
    function subSomething()
    {
        if(document.readyState == "complete") {
            //当页面加载状态为完全结束时进入
            if (${userObj != null}){
                getFavorites($("#userId").val());
                getRelation($("#userId").val(),$("#workerId").val());
            } else if (${workerObj != null}){
                getRelation($("#userId").val(),$("#workerId").val());
            } else if (${adminObj != null}){
                getManageData();
            }
        }
    }
</script>
<html>
<body>
<%--储存信息的隐藏输入框--%>
<c:if test="${userObj != null}">
    <input type="hidden" name= "userId" id= "userId" value="${userObj.userId}">
    <input type="hidden" ID="loginID" value="0">
</c:if>
<c:if test="${userObj == null}">
    <input type="hidden" name= "userId" id= "userId" value="0">
</c:if>
<c:if test="${workerObj != null}">
    <input type="hidden" name= "workerId" id= "workerId" value="${workerObj.workerId}">
    <input type="hidden" ID="loginID" value="1">
</c:if>
<c:if test="${workerObj == null}">
    <input type="hidden" name= "workerId" id= "workerId" value="0">
</c:if>

<%--消息框--%>
<div id="hideBackGround" class="hideBackGround"></div>
<div id="chatRelation" class="chatRelation" style="display:none">
    <input type="button" onclick="closeShowRelation()" value="关闭">
    <div id="relationUL" >
    </div>
</div>
<%--收藏夹--%>
<div class="favoritesContainer" id="favorites" style="display: none">
    <div id="favoritesContent" style="overflow-y:auto; overflow-x:auto; width:600px; height:300px;"></div>
</div>
<%--管理员界面--%>
<div class="favoritesContainer" id="manger" style="display: none">
    <ul id="mangeContent" style="overflow-y:auto; overflow-x:auto; width:600px; height:400px;"></ul>
</div>
<%--新增分类框--%>
<div class="favoritesContainer" id="manageClass" style="display: none;">
    <span style="font-size: 20px;">新增文章分类</span> </br>
    <label class="label"> 名称: </label> <input type="text" id="saveClassText" name="saveClassText" onfocus="FocusItem(this)" onblur="CheckItem(this)"><span class="error"></span></br>
    <input type="button" onclick="saveClass()" value="确认新增" >&nbsp;&nbsp;
    <input type="button" onclick="closeSaveClass()" value="取消" >
</div>
<%--转移文章分类--%>
<div class="favoritesContainer" id="changeClass" style="display: none;">
    <input type="hidden" id="articleClassId">
    <span style="font-size: 20px;">转移文章分类至：</span> </br>
    <select name="selectList" id="newClassId">
        <option value=""></option>
    </select></br>
    <input type="button" onclick="changeClass()" value="确认转移" >&nbsp;&nbsp;
    <input type="button" onclick="closeClassOption()" value="取消" ></br>
</div>
<%--登录框--%>
<div id="login"  style="display:none">
    <form action="javascript:login()"  method="post" >
        <span style="font-size: 20px;"> 欢迎登录健康系统</span> <br />
        <label class="label"> 账号: </label> <input type="text" name="account" required id="account" placeholder="请输入账号" /> <br />
        <label class="label"> 密码: </label> <input type="password" name="password" required id="password"  placeholder="请输入密码"  /> <br />
         身份:
        <select name="loginMode" id="loginMode" >
            <option selected="selected" value="0">用户</option>
            <option value="1">工作者</option>
            <option value="2">管理员</option>
        </select><br>
        <input type="submit" class="button" value="登录" class="input2"  />
        <input type="button" class="button" value="注册" class="input1" onclick="closeLogin();openRegister();"/>
        <input type="button" class="button" value="关闭" class="input3" onclick="closeLogin();" />
    </form>
</div>
<%--注册框--%>
<div id="register"  style="display:none">
    <form action="javascript:register()" method="post" onsubmit="return CheckForm(this)">
        <span style="font-size: 20px;"> 欢迎注册C.A.T健康</span> <br />
        <label class="label"> 账号: </label> <input type="text" name="account" id="registerAccount" required placeholder="请输入账号" onfocus="FocusItem(this)" onblur="CheckItem(this)"/> <span class="error"></span><br />
        <label class="label"> 用户名: </label> <input type="text" name="name" id="registerName" required placeholder="请输入用户名" onfocus="FocusItem(this)" onblur="CheckItem(this)"/> <span class="error"></span><br />
        <label class="label"> 密码: </label> <input type="password" name="password" id="registerPassword"  required placeholder="请输入密码" onfocus="FocusItem(this)" onblur="CheckItem(this)" /> <span class="error"></span><br />
        <label class="label"> 确认密码: </label> <input type="password" name="confirmPassword"  required placeholder="请确认密码" onfocus="FocusItem(this)" onblur="CheckItem(this)"/> <span class="error"></span><br/>
        <label class="label"> 性别:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label>
        <input name="sex" type="radio" checked="checked" value="0"/>男
        <input name="sex" type="radio" value="1"/>女 <br/>
        <input type="submit" class="button" value="注册"  />
        <input type="button" class="button" value="关闭"  onclick="closeRegister();" />
    </form>
</div>
</div>
<%--左侧导航栏--%>
<div id="s-top-left" class="s-top-left s-isindex-wrap">
    <a href="${pageContext.request.contextPath}/">首页</a>
    <c:choose>
        <c:when test="${userObj == null && workerObj == null && adminObj == null}">
            <a href="javascript:openLogin();">登录</a>
            <a href="javascript:openRegister()">注册</a>
        </c:when>
        <c:otherwise>
            <c:if test="${userObj != null}">
                <span>${userObj.userName}</span>
<%--                <a href="${pageContext.request.contextPath}/UserInfoMange.jsp">账号管理</a>--%>
                <a href="javascript:logout()">退出登录</a>
            </c:if>
            <c:if test="${workerObj != null}">
                <span>${workerObj.workerName}</span>
<%--                <a href="${pageContext.request.contextPath}/UserInfoMange.jsp">账号管理</a>--%>
                <a href="${pageContext.request.contextPath}/Action?method=logout">退出登录</a>

            </c:if>
            <c:if test="${adminObj != null}">
                <span>${adminObj.adminName}</span>
                <%--                <a href="${pageContext.request.contextPath}/UserInfoMange.jsp">账号管理</a>--%>
                <a href="${pageContext.request.contextPath}/Action?method=logout">退出登录</a>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>
<%--右侧导航栏--%>
<div id="s-top-right" class="s-top-right s-isindex-wrap">
    <c:choose>
        <c:when test="${userObj != null}">
            <a href="javascript:showFavorites('${userObj.userId}')">收藏夹</a>
            <a href="javascript:showChatRelation('${userObj.userId}','0');">消息</a>
        </c:when>
        <c:otherwise>
            <c:if test="${workerObj != null}">
                <a href="javascript:showChatRelation('0','${workerObj.workerId}')">消息</a>
                <a href="javascript:openReleasePage(${workerObj.workerId})">发布文章</a>
            </c:if>
            <c:if test="${adminObj != null}">
                <a href="${pageContext.request.contextPath}/report.jsp">举报处理</a>
                <a href="javascript:openManage()">文章数据与管理</a>
            </c:if>
        </c:otherwise>
    </c:choose>
</div>
<%--搜索框--%>
<div id="searchBarWrap">
    <input id="searchBar" type="text" name="searchbar" required placeholder="搜索文章"/>
    <button id="searchBtn" onclick="javascript:searchArticle();">搜索<i class="fa fa-search"></i></button>
</div>
</body>
</html>
<style>
    .chatRelation{
        POSITION:fixed; left:50%; top:50%;
        width:600px; height:420px;
        margin-left:-300px;
        margin-top:-200px;
        border:1px solid #888;
        background-color: whitesmoke; text-align: center;
        line-height: 60px; z-Index:3;
        box-shadow: 0 15px 25px rgba(0,0,0,.5);
        border-radius: 10px;
    }
    #favorites,#manger,#manageClass{
        POSITION:absolute; left:50%; top:50%;
        width:600px; height:400px;
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
    #changeClass{
        POSITION:absolute; left:58%; top:60%;
        width:300px; height:200px;
        margin-left:-300px;
        margin-top:-200px;
        border:1px solid #888;
        background-color: whitesmoke;
        text-align: center;
        line-height: 40px; z-Index:3;
        box-shadow: 0 15px 25px rgba(0,0,0,.5);
        border-radius: 10px;
    }
    #manageClass{
        POSITION:fixed; left:55%; top:55%;
        width:400px; height:200px;
        margin-left:-300px;
        margin-top:-200px;
        border:1px solid #888;
        background-color: whitesmoke; text-align: center;
        line-height: 60px; z-Index:3;
        box-shadow: 0 15px 25px rgba(0,0,0,.5);
        border-radius: 10px;
    }
    #mangeContent{
        text-align: center;
    }
</style>