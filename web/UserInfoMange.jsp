<%@ page import="com.LiXianghao.event.controller.constant.Constant" %>
<%@ page import="com.LiXianghao.event.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8"  %>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/js/function.js"></script>
<html>
<%--待完善--%>
<head>
    <title></title>
</head>
<%--<%--%>
<%--    User user = (User) session.getAttribute(Constant.USER_OBJ);--%>

<%--%>--%>
<body>
<%--    <div>--%>
<%--        <label>账号：<%=user.getUserAccount()%></label><br/>--%>
<%--        <label>用户名：<%=user.getUserName()%></label><br/>--%>
<%--        <label>性别：<%=user.isUserSex()%></label><br/>--%>
<%--        <label>注册时间：<%=user.getUserRegisterTime()%></label><br/>--%>
<%--    </div>--%>
<input type="button" onclick="javascript:testList();" value="测试1">
<input type="button" onclick="javascript:userGetChatData();" value="测试2">

</body>
</html>
