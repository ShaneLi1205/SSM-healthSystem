<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--不可用--%>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/js/function.js"></script>
<html>
<head>
</head>
<body>
<div id="hideBackGround" class="hideBackGround"></div>
<div id="login"  style="display:none">
    <form action="${pageContext.request.contextPath}/Action?method=login" method="post">
        <span style="font-size: 20px;"> 欢迎登录C.A.T健康</span> <br />
        <label class="label"> 账号: </label> <input type="text" name="account" required id="account" placeholder="请输入账号" /> <br />
        <label class="label"> 密码: </label> <input type="password" name="password" required id="password" placeholder="请输入密码"  /> <br />
        <input type="submit" class="button" value="登录" class="input2" />
        <input type="button" class="button" value="注册" class="input1" onclick="closeLogin();openRegister();"/>
        <input type="button" class="button" value="关闭" class="input3" onclick="closeLogin();" />
    </form>
</div>
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
<a href="javascript:openLogin();">登录</a>
<a href="javascript:openRegister()">注册</a>
</body>
</html>
