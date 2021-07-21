<%@ page contentType="text/html;charset=UTF-8" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" >
<script charset="UTF-8" src="${pageContext.request.contextPath}/js/function.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script>
    document.onreadystatechange = subSomething;
    //当页面加载状态改变的时候执行这个方法.
    function subSomething()
    {
        if(document.readyState == "complete") {
            //当页面加载状态为完全结束时进入
            GetAllChatData(${userId},${workerId},${ID})
        }
    }
    window.setInterval(getNewChatData,1000);
</script>
<input type="hidden" name= "userId" id= "cuserId" value="${userId}" >
<input type="hidden" name="workerId" id="cworkerId" value="${workerId}">
<input type="hidden" id="cloginID" value="${ID}">
<html>
<head>
    <title>咨询</title>
</head>
<body >
<div class="container">
    <div class="content" id="messageContainer">

    </div>
    <div class="input-area">
        <textarea name="text" id="textarea"></textarea>
        <div class="button-area">
            <button id="send-btn" onclick="SendMsg(${userId},${workerId},${ID})">发 送</button>
        </div>
    </div>
</div>
</body>
</html>
<style>
    *{
        padding: 0;
        margin: 0;
    }
    body{
        height: 100vh;
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .container{
        height: 760px;
        width: 900px;
        border-radius: 4px;
        border: 0.5px solid #e0e0e0;
        background-color: #f5f5f5;
        display: flex;
        flex-flow: column;
        overflow: hidden;
    }
    .content{
        width: calc(100% - 40px);
        padding: 20px;
        overflow-y: scroll;
        flex: 1;
    }
    .content:hover::-webkit-scrollbar-thumb{
        background:rgba(0,0,0,0.1);
    }
    .bubble{
        max-width: 400px;
        padding: 10px;
        border-radius: 5px;
        position: relative;
        color: #000;
        word-wrap:break-word;
        word-break:normal;
    }
    .item-left .bubble{
        margin-left: 15px;
        background-color: #fff;
    }
    .item-left .bubble:before{
        content: "";
        position: absolute;
        width: 0;
        height: 0;
        border-left: 10px solid transparent;
        border-top: 10px solid transparent;
        border-right: 10px solid #fff;
        border-bottom: 10px solid transparent;
        left: -20px;
    }
    .item-right .bubble{
        margin-right: 15px;
        background-color: #9eea6a;
    }
    .item-right .bubble:before{
        content: "";
        position: absolute;
        width: 0;
        height: 0;
        border-left: 10px solid #9eea6a;
        border-top: 10px solid transparent;
        border-right: 10px solid transparent;
        border-bottom: 10px solid transparent;
        right: -20px;
    }
    .item{
        margin-top: 15px;
        display: flex;
        width: 100%;
    }
    .item.item-right{
        justify-content: flex-end;
    }
    .item.item-center{
        justify-content: center;
    }
    .item.item-center span{
        font-size: 12px;
        padding: 2px 4px;
        color: #fff;
        background-color: #dadada;
        border-radius: 3px;
        -moz-user-select:none; /*火狐*/
        -webkit-user-select:none; /*webkit浏览器*/
        -ms-user-select:none; /*IE10*/
        -khtml-user-select:none; /*早期浏览器*/
        user-select:none;
    }

    .avatar img{
        width: 42px;
        height: 42px;
        border-radius: 50%;
    }
    .input-area{
        border-top:0.5px solid #e0e0e0;
        height: 150px;
        display: flex;
        flex-flow: column;
        background-color: #fff;
    }
    textarea{
        flex: 1;
        padding: 5px;
        font-size: 14px;
        border: none;
        cursor: pointer;
        overflow-y: auto;
        overflow-x: hidden;
        outline:none;
        resize:none;
    }
    .button-area{
        display: flex;
        height: 40px;
        margin-right: 10px;
        line-height: 40px;
        padding: 5px;
        justify-content: flex-end;
    }
    .button-area button{
        width: 80px;
        border: none;
        outline: none;
        border-radius: 4px;
        float: right;
        cursor: pointer;
    }

    /* 设置滚动条的样式 */
    ::-webkit-scrollbar {
        width:10px;
    }
    /* 滚动槽 */
    ::-webkit-scrollbar-track {
        border-radius:8px;
    }
    /* 滚动条滑块 */
    ::-webkit-scrollbar-thumb {
        border-radius:10px;
        background:rgba(0,0,0,0);
    }
</style>
