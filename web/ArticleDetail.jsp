<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/js/function.js"></script>
<script src="${pageContext.request.contextPath}/ueditor/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js" type="text/javascript" charset="utf-8"> </script>
<script src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js" type="text/javascript" charset="utf-8-BOM"></script>
<jsp:include page="Header.jsp"/>
<html>
<head>
    <title>${articleInfoObj.articleTitle}</title>
</head>
<%--储存信息的隐藏框--%>
<c:if test="${userObj != null}">
    <input type="hidden" name= "userId" id= "reportUserId" value="${userObj.userId}">
    <input type="hidden" name= "userId" id= "userId" value="${userObj.userId}">
    <input type="hidden" id="userName" value="${userObj.userName}">
    <input type="hidden" name="articleId" id="articleId" value="${articleInfoObj.articleId}">
    <input type="hidden" name="workerId" id="workerId" value="${articleInfoObj.workerId}">
    <input type="hidden" ID="loginID" value="0">
</c:if>
<div id="hideBackGround" class="hideBackGround"></div>
<%--举报界面--%>
<div id="report" style="display: none">
    举报原因：<input type="text" id="reportReason"  placeholder="请输入举报原因(30字以内)" maxlength="30"></br></br>
    <div>
        举报描述：
        <script id="editor" type="text/plain" style="width:580px;height:400px;"></script>
    </div>
    <div id="btns">
        <div>
            <button onclick="saveReport();">发布</button>
            <button onclick="closeFrame('report')">取消</button>
        </div>
    </div>
</div>
<%--右下侧按钮--%>
<body onload="javascript:articleDetailInitA()">
<input type="hidden" id="likeMark" value="0">
<input type="hidden" id="starMark" value="0">
    <a href="javascript:void(0);" onclick="goTop()"><img src="WebImage/top.png" title="回到顶部" class="right-toTop"></a>
    <c:if test="${adminObj == null && workerObj == null}">
        <a href="javascript:void(0);" onclick="userLikeArticle()"><img id="likeImage" src="WebImage/like.png"  title="点赞" class="right-like" ></a>
        <a href="javascript:void(0);" onclick="userFavorites()"><img id="starImage" src="WebImage/star.png" title="收藏" class="right-star"></a>
        <a href="javascript:OpenCommunication('${userObj.userId}','${articleInfoObj.workerId}',${ID})"><img src="WebImage/consult.png" title="咨询发布者" class="right-consult" ></a>
    </c:if>
    <a href="javascript:void(0);" onclick="openFrame('report')"><img src="WebImage/report.png" title="举报" class="right-report" href="#bottom"></a>
    <a href="javascript:void(0);" onclick="goBottom()"><img src="WebImage/bottom.png" title="回到底部" class="right-toBottom" href="#bottom"></a>
<%--文章主体内容--%>
<div class="container">
    <div class="center">
        <%--标题--%>
        <div class="article-title"><h1>${articleInfoObj.articleTitle}</h1></div>
        <%--转赞信息--%>
        <div class="artclie-detail-header">
            <div>
                <img class="article-detail-header-image" src="${pageContext.request.contextPath}/WebImage/view.png" alt="浏览">${articleInfoObj.articleViewNum}
                <img class="article-detail-header-image" src="${pageContext.request.contextPath}/WebImage/comment.png" alt="评论">${articleInfoObj.articleCommentNum}
                <img class="article-detail-header-image"  src="${pageContext.request.contextPath}/WebImage/like.png" alt="点赞">${articleInfoObj.articleLikeNum}
            </div>
        </div>
             <div class="article-detail-header-info">
                 <span>作者：${articleInfoObj.workerName}</span>
                 <span>发布时间：${articleInfoObj.articleReleaseTime}</span>
                 <span>文章类型：${articleInfoObj.articleClassName}</span>
                 <c:if test="${adminObj != null}">
                     <a href="javascript:deleteArticle(${articleInfoObj.articleId})" style="color: red;font-size: 12px;font-style: italic;">删除文章</a>
                 </c:if>
                 <c:if test="${workerObj.workerId == articleInfoObj.workerId}">
                     <a href="javascript:deleteArticle(${articleInfoObj.articleId})" style="color: red;font-size: 12px;font-style: italic;">删除文章</a>
                 </c:if>
             </div>
        </div>
        <div>${articleInfoObj.articleContent}</div>
        <div><h3>评论区：</h3></div>
        <c:choose>
            <c:when test="${articleCommentList.size() != 0}">
                <c:forEach items="${articleCommentList}" var="articleComment">
                    <div id="commentDiv${articleComment.articleCommentId}">
                    <ul id="user_evaluate">
                        <li class="evaluate_li">
					 <span class="img">
						<img src="${pageContext.request.contextPath}/WebImage/me.png"  />
					</span>
                    <span class="info" id="info${articleComment.articleCommentId}">
						<h4>${articleComment.userName}${articleComment.workerName}</h4>
						<p>${articleComment.articleCommentContent}</p>
						<p class="time">${articleComment.articleCommentTime}
                            <c:if test="${adminObj != null ||(workerObj.workerId == articleInfoObj.workerId) || (userObj.userId == articleComment.userId)}">
                                    <a href="javascript:deleteCommentAndReply(${articleComment.articleCommentId})" style="color: red">删除</a>
                            </c:if>
                            <c:if test="${userObj != null}">
                                <a href="javascript:openReply(${articleComment.articleCommentId})">回复</a>
                                <p>
                                    <div id="div${articleComment.articleCommentId}" style="display: none">
                                            <input type="text" id="text${articleComment.articleCommentId}">
                                            <input type="button" onclick="saveCommentReply(${articleComment.articleCommentId})" value="发送">
                                    </div>
                                </p>
                            </c:if>
                        </p>
                            <div id="replyContainer${articleComment.articleCommentId}">
                                <c:forEach var="reply" items="${articleComment.commentReplies}">
                                    <div class="reply_info" id="reply${reply.commentReplyId}" >
                                            ${reply.userName}:${reply.commentReplyContent}
                                            <c:if test="${adminObj != null ||(workerObj.workerId == articleInfoObj.workerId) || (userObj.userId == articleComment.userId)}">
                                                <a href="javascript:deleteReply(${reply.commentReplyId})" style="color: red">删除</a>
                                            </c:if>
                                    </div>
                                </c:forEach>
                            </div>
					</span>
                        </li>
                    </ul>
                        </div>
                </c:forEach>
                <c:if test="${userObj == null && workerObj == null && adminObj == null}">
                    <div class="article-detail-footer-info">
                        <span>请登录后评论</span>
                        <jsp:include page="ArticleDetailFooter.jsp"/>
                    </div>
                </c:if>
                <%--翻页栏--%>
                <div class="switch-tag-wrap " >
                    <div id="pager_bottom">
                        <div id="paging_block">
                            <div class="pager" id="commentPageContainer">
                                <span>当前${commentPageNum}/${commentTotalPageNum}页</span>
                                <a href="${pageContext.request.contextPath}/Article?method=getArticleDetail&articleId=${articleInfoObj.articleId}&commentPageNum=1">首页</a>
                                <a href="${pageContext.request.contextPath}/Article?method=getArticleDetail&articleId=${articleInfoObj.articleId}&commentPageNum=${commentPageNum - 1 < 1 ? 1:commentPageNum-1}">上一页</a>
                                <a href="${pageContext.request.contextPath}/Article?method=getArticleDetail&articleId=${articleInfoObj.articleId}&commentPageNum=${commentPageNum + 1> commentTotalPageNum ? commentTotalPageNum : commentPageNum + 1}">下一页</a>
                                <a href="${pageContext.request.contextPath}/Article?method=getArticleDetail&articleId=${articleInfoObj.articleId}&commentPageNum=${commentTotalPageNum}">尾页</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:if test="${articleCommentList.size() == 0}">
                    <div class="article-detail-footer-info">
                        <span>暂无评论</span><br>
                    </div>
                    <c:if test="${userObj == null && workerObj == null && adminObj == null}">
                        <div class="article-detail-footer-info">
                            <span>请登录后评论</span>
                            <jsp:include page="ArticleDetailFooter.jsp"/>
                        </div>
                    </c:if>
                </c:if>
            </c:otherwise>
        </c:choose>
    <div class="comment-div">
        <c:if test="${userObj != null}">
                <div><h3>我要发言：</h3></div>
                <textarea class="comment-textarea" id="commentArea" rows="5" cols="33" placeholder="请友善发言(200字以内)"></textarea>
                <input type="button" value="发表评论" onclick="userComment();">
        </c:if>
    </div>
    </div>

</body>
<a id="bottom"></a>
</html>
<script>
    function articleDetailInitA()
    {
        if(document.readyState == "complete" && ${userObj != null}) {
            //当页面加载状态为完全结束时进入
            isLike();
            isFavorites();
        }
    }
</script>
<style>
    .comment-div{
        margin-bottom: 100px;
        padding-bottom: 100px;
        border-bottom: 100px;
    }
    .right-toTop
    {
        width: 40px;
        height:40px;
        position: fixed;/*这是必须的*/
        z-index: 999;
        left:95%;/*这是必须的*/
        bottom:300px;/*这是必须的*/
        background:bisque;
        border-radius: 10px;
    }
    .right-like
    {
        width: 40px;
        height:40px;
        position: fixed;/*这是必须的*/
        z-index: 999;
        left:95%;/*这是必须的*/
        bottom:250px;/*这是必须的*/
        background:bisque;
        border-radius: 10px;
    }
    .right-star
    {
        width: 40px;
        height:40px;
        position: fixed;/*这是必须的*/
        z-index: 999;
        left:95%;/*这是必须的*/
        bottom:200px;/*这是必须的*/
        background:bisque;
        border-radius: 10px;
    }
    .right-consult{
        width: 40px;
        height:40px;
        position: fixed;/*这是必须的*/
        z-index: 999;
        left:95%;/*这是必须的*/
        bottom:150px;/*这是必须的*/
        background:bisque;
        border-radius: 10px;
    }
    .right-report{
        width: 40px;
        height:40px;
        position: fixed;/*这是必须的*/
        z-index: 999;
        left:95%;/*这是必须的*/
        bottom:100px;/*这是必须的*/
        background:bisque;
        border-radius: 10px;
    }
    .right-toBottom{
        width: 40px;
        height:40px;
        position: fixed;/*这是必须的*/
        z-index: 999;
        left:95%;/*这是必须的*/
        bottom:50px;/*这是必须的*/
        background:bisque;
        border-radius: 10px;
    }
     .comment-textarea {
        font-size: .8rem;
        letter-spacing: 1px;
         resize: none;
    }
    .comment-textarea {
        padding: 10px;
        line-height: 1.5;
        border-radius: 5px;
        border: 1px solid #ccc;
        box-shadow: 1px 1px 1px #999;
        width: 500px;
        height: 100px;
    }
    #report{
         POSITION:absolute;
         left:50%;
         top:30%;
         width:600px; height:800px;
         margin-left:-300px;
         margin-top:-200px;
         border:1px solid #888;
         background-color: whitesmoke;
         text-align: center;
         line-height: 40px; z-Index:3;
         box-shadow: 0 15px 25px rgba(0,0,0,.5);
         border-radius: 10px;
     }
</style>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/uditor/lang/zh-cn/zh-cn.js"></script>
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