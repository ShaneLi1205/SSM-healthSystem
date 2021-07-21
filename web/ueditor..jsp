<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UEditor测试</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <style type="text/css">
        div {
            width: 100%;
        }
    </style>
</head>

<body>

<h1>UEditor测试</h1>
<form action="${pageContext.request.contextPath}/ueditor/uploading">
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
    <input type="submit" value="提交">
</form>

</body>

<script type="text/javascript">
    // 实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
</script>
</html>