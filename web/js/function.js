/**
 * 项目地址
 * @type {boolean}
 */
var projectURL = "" ;

var isAvailable = true;
var workerAva = projectURL+"/WebImage/worker.png";
var userAva = projectURL+"/WebImage/me.png";

/**
 * 打开登录界面
 */
function openLogin(){
    document.getElementById("login").style.display="";
    document.getElementById("hideBackGround").style.display="block";
    document.getElementById("hideBackGround").style.height=document.body.clientHeight+"px";
}
function closeLogin(){
    document.getElementById("login").style.display="none";
    document.getElementById("hideBackGround").style.display="none";
}

/**
 * 打开注册界面
 */
function openRegister(){
    document.getElementById("register").style.display="";
    document.getElementById("hideBackGround").style.display="block";
    document.getElementById("hideBackGround").style.height=document.body.clientHeight+"px";
}
function closeRegister(){
    document.getElementById("register").style.display="none";
    document.getElementById("hideBackGround").style.display="none";
}

/**
 * 焦点时移除错误信息
 * @param obj 输入框
 * @constructor
 */
function FocusItem(obj){
    $(obj).next('span').html('').removeClass('error');
}
/**
 * 失去焦点时检查信息
 * @param obj 输入框
 * @constructor
 */
function CheckItem(obj){
    var message = $(obj).next('span');
    var accountCheck = /^[a-zA-Z0-9_-]{4,16}$/;
    var userNameCheck = /^[A-Za-z0-9_\u4e00-\u9fa5]{3,10}$/;
    switch ($(obj).attr('name')){
        case"account":
            if (obj.value == ""){
                message.html("账号不可为空");
                message.addClass('error');
                isAvailable = false;
            } else if (!accountCheck.test(obj.value)){
                message.html("账号格式错误")
                message.addClass('error');
                isAvailable = false;
            } else {
                $.ajax({
                    url : projectURL+"/registerCheck",
                    type : "POST",
                    data : {
                        'account':$("#registerAccount").val()
                    },
                    dataType : "json",
                    success : function(data){
                        if (data.flag == false){
                            message.html(data.message);
                            message.addClass('error');
                            isAvailable = false;
                        } else {
                            message.html().removeClass('error');
                            isAvailable = true;
                        }
                    }
                })
            }
            break;
        case"name":
            if (obj.value == ""){
                message.html("用户名不可为空");
                message.addClass('error');
                isAvailable = false;
            } else if (!userNameCheck.test(obj.value)){
                message.html("用户名格式错误");
                message.addClass('error');
                isAvailable = false;
            } else {
                $.ajax({
                    url : projectURL+"/registerCheck",
                    type : "POST",
                    data : {
                        'username':$("#registerName").val()
                    },
                    dataType : "json",
                    success : function(data){
                        if (data.flag == false){
                            message.html(data.message);
                            message.addClass('error');
                            isAvailable = false;
                        } else {
                            isAvailable = true;
                        }
                    }
                })
            }
            break;
        case"password":
            if (obj.value == ""){
                message.html("密码不可为空");
                message.addClass('error');
                isAvailable = false;
            } else if (!userNameCheck.test(obj.value)){
                message.html("密码格式错误");
                message.addClass('error');
                isAvailable = false;
            } else {
                isAvailable = true;
            }
            break;
        case"confirmPassword":
            if (obj.value == ""){
                message.html("密码确认不可为空");
                message.addClass('error');
                isAvailable = false;
            } else if ($(obj).val() != $("#registerPassword").val()) {
                message.html("密码不一致");
                message.addClass('error');
                isAvailable = false;
            } else {
                isAvailable = true;
            }
            break;
        case "saveClassText":
            if (obj.value == ""){
                message.html("名称不可为空");
                message.addClass('error');
                isAvailable = false;
            } else if (!userNameCheck.test(obj.value)){
                message.html("名称格式错误");
                message.addClass('error');
                isAvailable = false;
            } else {
                $.ajax({
                    url : projectURL+"/checkArticleClassName",
                    type : "POST",
                    data : {
                        'articleClassName':$("#saveClassText").val()
                    },
                    dataType : "json",
                    success : function(data){
                        if (data.flag == false){
                            message.html(data.message);
                            message.addClass('error');
                            isAvailable = false;
                        } else {
                            $("#manageClass").removeClass('error');
                            isAvailable = true;
                        }
                    }
                })
            }
            break;
    }
}

/**
 * 注册时检查各个信息的正确性
 * @param form 信息表单
 * @returns {boolean} 是否通过验证
 */
function CheckForm(form){
    var checkInput = form.getElementsByTagName('input');
    for (var i = 0; i < checkInput.length; i++){
        if(checkInput[i] != null){
            CheckItem(checkInput[i]);
        }
    }
    return isAvailable;
}

/**
 * 改变文章类型时显示文章列表
 */
function changeArticleClass(){
    var classId = $("#classId").val();
    window.location.href=projectURL+"/Article?method=showArticleList&pageNum=1&classId="+classId;
}

/**
 * 搜索文章
 */
function searchArticle(){
    var keyword = $("#searchBar").val();
    searchPageInit(1,keyword);
}

/**
 * 检查用户是否点赞
 */
function isLike(){
    var userId = $("#userId").val();
    var articleId = $("#articleId").val();
    $.ajax({
        url : projectURL+"/checkLike",
        type : "POST",
        data : {
            'articleId':articleId,
            'userId':userId
        },
        dataType : "json",
        success : function(data){
            if (data.flag == true){
                $("#likeImage").attr('src',projectURL+"/WebImage/isLike.png");
                $("#likeImage").attr('title',data.message);
                $("#likeMark").val('1');
            } else {
                $("#likeImage").attr('src',projectURL+"/WebImage/like.png");
                $("#likeImage").attr('title',data.message);
                $("#likeMark").val('0');
            }
        }
    })

}

/**
 * 检查用户是否收藏
 */
function isFavorites(){
    var userId = $("#userId").val();
    var articleId = $("#articleId").val();
    $.ajax({
        url : projectURL+"/checkFavorites",
        type : "POST",
        data : {
            'articleId':articleId,
            'userId':userId
        },
        dataType : "json",
        success : function(data){
            if (data.flag == true){
                $("#starImage").attr('src',projectURL+"/WebImage/isStar.png");
                $("#starImage").attr('title',data.message);
                $("#starMark").val('1');
            } else {
                $("#starImage").attr('src',projectURL+"/WebImage/star.png");
                $("#starImage").attr('title',data.message);
                $("#starMark").val('0');
            }
        }
    })
}

/**
 * 到网页底部
 */
function goBottom() {
    window.scrollTo(0, document.documentElement.scrollHeight-document.documentElement.clientHeight);
}

/**
 * 到网页顶部
 */
function goTop() {
    window.scrollTo(0, 0);
}

/**
 * 点赞操作
 */
function userLikeArticle(){
    var userId = $("#userId").val();
    var articleId = $("#articleId").val();
    var likeMark = $("#likeMark").val();
    var url;
    if (userId == null){
        openLogin();
    } else if (userId != null){
        if (likeMark == '0'){
            //未点赞则点赞
            $.ajax({
                url : projectURL+"/userLike",
                type : "POST",
                data : {
                    'articleId':articleId,
                    'userId':userId,
                    'articleLikeId':likeMark
                },
                dataType : "json",
                success : function(data){
                    if (data.flag == true){
                        $("#likeImage").attr('src',projectURL+"/WebImage/isLike.png");
                        $("#likeImage").attr('title',data.message);
                        $("#likeMark").val('1');
                    } else {
                        alert(data.message);
                    }
                }
            })
        } else {
            //已点赞取消点赞
            $.ajax({
                url : projectURL+"/userLike",
                type : "POST",
                data : {
                    'articleId':articleId,
                    'userId':userId,
                    'articleLikeId':likeMark
                },
                dataType : "json",
                success : function(data){
                    if (data.flag == true){
                        $("#likeImage").attr('src',projectURL+"/WebImage/like.png");
                        $("#likeImage").attr('title',data.message);
                        $("#likeMark").val('0');
                    } else {
                        alert(data.message);
                    }
                }
            })
        }
    }
}

/**
 * 收藏操作
 */
function userFavorites() {
    var userId = $("#userId").val();
    var articleId = $("#articleId").val();
    var starMark = $("#starMark").val();
    var url;
    if (userId == null) {
        openLogin();
    } else if (userId != null) {
        if (starMark == '0') {
            //未收藏则收藏
            $.ajax({
                url: projectURL+"/userFavorites",
                type: "POST",
                data: {
                    'articleId': articleId,
                    'userId': userId,
                    'userFavoritesId': starMark
                },
                dataType: "json",
                success: function (data) {
                    if (data.flag == true) {
                        $("#starImage").attr('src', projectURL+"/WebImage/isStar.png");
                        $("#starImage").attr('title', data.message);
                        $("#starMark").val('1');
                    } else {
                        alert(data.message);
                    }
                }
            })
        } else {
            //已收藏则取消收藏
            $.ajax({
                url: projectURL+"/userFavorites",
                type: "POST",
                data: {
                    'articleId': articleId,
                    'userId': userId,
                    'userFavoritesId': starMark
                },
                dataType: "json",
                success: function (data) {
                    if (data.flag == true) {
                        $("#starImage").attr('src', projectURL+"/WebImage/star.png");
                        $("#starImage").attr('title', data.message);
                        $("#starMark").val('0');
                    } else {
                        alert(data.message);
                    }
                }
            })
        }
    }
}

/**
 * 用户评论
 */
function userComment(){
    var userId = $("#userId").val();
    var articleId = $("#articleId").val();
    var comment = $("#commentArea").val();
    var url;
    if (userId == null){
        openLogin();
    } else if (comment.length>200){
        alert('评论过长');
    } else {
        $.ajax({
            url: projectURL+"/userComment",
            type: "POST",
            data: {
                'articleId': articleId,
                'userId': userId,
                'articleCommentContent': comment
            },
            dataType: "json",
            success: function (data) {
                if (data.flag == true) {
                    alert(data.message);
                    location.reload();
                } else {
                    alert(data.message);
                }
            }
        })
    }
}

/**
 * 发布文章
 */
function workerReleaseArticle(){
    var workerId = $("#workerId").val();
    var articleContent = UE.getEditor('editor').getContent();
    var articleSummary = UE.getEditor('editor').getContentTxt();
    var articleTitle = $("#articleTitle").val();
    var classId = $("#articleReleaseClassId").val();
    if (articleTitle.length > 30){
        alert("标题长度超出");
    } else {
        $.ajax({
            url : projectURL+"/getWorkerState",
            type : "POST",
            data : {
                'workerId' : workerId
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    $.ajax({
                        url : projectURL+"/releaseArticle",
                        type : "POST",
                        cache: false,
                        async : false,
                        data : {
                            'workerId':workerId,
                            'articleContent':articleContent,
                            'articleSummary':articleSummary,
                            'articleTitle':articleTitle,
                            'articleClassId':classId},
                        dataType : "json",
                        success : function(data){
                            if (data.flag == true){
                                alert(data.message);
                                window.location.href = projectURL+"/index.jsp"
                            } else {
                                alert(data.message);
                            }
                        }
                    })
                } else {
                    alert("账号被封禁至："+data.data);
                }
            }
        })


    }
}

/**
 * 登录
 */
function login(){
    var account = $("#account").val();
    var password = $("#password").val();
    var loginMode = $("#loginMode").val();
    if (loginMode == "0"){
        $.ajax({
            url : projectURL+"/userLogin",
            type : "POST",
            data : {
                'userAccount':account,
                'userPassword':password,
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    location.reload();
                } else {
                    alert(data.message);
                }
            }
        })
    } else if (loginMode == "1"){
        $.ajax({
            url : projectURL+"/workerLogin",
            type : "POST",
            data : {
                'workerAccount':account,
                'workerPassword':password,
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    location.reload();
                } else {
                    alert(data.message);
                }
            }
        })
    } else if (loginMode == "2"){
        $.ajax({
            url : projectURL+"/adminLogin",
            type : "POST",
            data : {
                'adminAccount':account,
                'adminPassword':password,
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    location.reload();
                } else {
                    alert(data.message);
                }
            }
        })
    }


}

/**
 * 注册
 */
function register(){
    var account = $("#registerAccount").val();
    var password = $("#registerPassword").val();
    var name = $("#registerName").val();
    var sex = $("input[name='sex']:checked").val();
    $.ajax({
        url : projectURL+"/userRegister",
        type : "POST",
        data : {
            'userAccount':account,
            'userPassword':password,
            'userName':name,
            'userSex':sex
        },
        dataType : "json",
        success : function(data){
            if (data.flag == true){
                alert(data.message);
                location.reload();
            } else {
                alert(data.message);
            }
        }
    })
}

/**
 * 退出登录
 */
function logout(){
    $.ajax({
        url : projectURL+"/logout",
        type : "POST",
        success : function(data){
            location.reload();
        }
    })

}

/**
 * 测试用
 */
function testList(){
    $.ajax({
        url : projectURL+"/Action?method=testJsonList",
        type : "POST",
        success : function(data){
            var jsonData = JSON.parse(data);
            console.log(jsonData[1].articleClassId);
            for (var o in jsonData){
                console.log(jsonData[o].articleClassId+'   '+jsonData[o].articleClassName)
            }
        }
    })
}

/**
 * 废弃
 */
function userGetChatData(){
    var userId = 1;
    var workerId = 1;
    var ID = 0;
    $.ajax({
        url : projectURL+"/Action?method=getChatData",
        type : "POST",
        data : {
            'userId':userId,
            'workerId':workerId,
            'ID':ID,
        },
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("messageContainer");
            for (var o in data ){
                if (data[o].chatSender == false){
                    var str = "<div class=\"item item-right\">\n" +
                        "            <div class=\"bubble bubble-right\">"+data[o].chatContent+"</div>\n" +
                        "            <div class=\"avatar\">\n" +
                        "                <img src=\""+projectURL+"/WebImage/me.png\" />\n" +
                        "            </div>\n" +
                        "        </div>";
                    msgs.innerHTML = msgs.innerHTML + str;
                } else {
                    var str = "<div class=\"item item-left\">\n" +
                        "            <div class=\"avatar\">\n" +
                        "                <img src=\""+projectURL+"/WebImage/worker.png\" />\n" +
                        "            </div>\n" +
                        "            <div class=\"bubble bubble-left\">"+data[o].chatContent+"</div>\n" +
                        "        </div>";

                    msgs.innerHTML = msgs.innerHTML + str;
                }
            }

        }
    })
}
/**
 * 废弃
 */
function workerGetChatData(){
    var userId = 1;
    var workerId = 1;
    var ID = 1;
    $.ajax({
        url : projectURL+"/Action?method=getChatData",
        type : "POST",
        data : {
            'userId':userId,
            'workerId':workerId,
            'ID':ID,
        },
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("messageContainer");
            var nameStr = "<div class=\"item item-center\"><span>"+data[0].userName+"</span></div>"
            msgs.innerHTML = msgs.innerHTML + nameStr;
            for (var o in data ){
                if (data[o].chatSender == true){
                    var str = "<div class=\"item item-right\">\n" +
                        "            <div class=\"bubble bubble-right\">"+data[o].chatContent+"</div>\n" +
                        "            <div class=\"avatar\">\n" +
                        "                <img src=\""+projectURL+"/WebImage/worker.png\" />\n" +
                        "            </div>\n" +
                        "        </div>";
                    msgs.innerHTML = msgs.innerHTML + str;
                } else {
                    var str = "<div class=\"item item-left\">\n" +
                        "            <div class=\"avatar\">\n" +
                        "                <img src=\""+projectURL+"/WebImage/me.png\" />\n" +
                        "            </div>\n" +
                        "            <div class=\"bubble bubble-left\">"+data[o].chatContent+"</div>\n" +
                        "        </div>";

                    msgs.innerHTML = msgs.innerHTML + str;
                }
            }

        }
    })
}
/**
 * 在聊天界面获得新消息
 */
function listNewChatData(){
    var userId = $("#cuserId").val();
    var workerId = $("#cworkerId").val();
    var ID = $("#cloginID").val();
    var charSender;
    var method = false;
    if(ID == '0'){
        method = false;
        var leftAva = workerAva;
        var rightAva = userAva;
        charSender = 1;
    } else {
        method = true;
        var leftAva = userAva;
        var rightAva = workerAva;
        charSender = 0;
    }
    $.ajax({
        url : projectURL+"/listNewChatData"+"?"+new Date().getTime(),
        type : "POST",
        data : {
            'userId':userId,
            'workerId':workerId,
            'chatSender':charSender
        },
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("messageContainer");
            for (var o in data ){
                if (data[o].chatSender == method){
                    var str = "<div class=\"item item-right\">\n" +
                        "            <div class=\"bubble bubble-right\">"+data[o].chatContent+"</div>\n" +
                        "            <div class=\"avatar\">\n" +
                        "                <img src="+rightAva+" />\n" +
                        "            </div>\n" +
                        "        </div>";
                    msgs.innerHTML = msgs.innerHTML + str;
                } else {
                    var str = "<div class=\"item item-left\">\n" +
                        "            <div class=\"avatar\">\n" +
                        "                <img src="+leftAva+" />\n" +
                        "            </div>\n" +
                        "            <div class=\"bubble bubble-left\">"+data[o].chatContent+"</div>\n" +
                        "        </div>";

                    msgs.innerHTML = msgs.innerHTML + str;
                }
            }
        }
    })
}
/**
 * 废弃
 */
function workerGetNewChatData(){
    var userId = 1;
    var workerId = 1;
    var ID = 1;
    $.ajax({
        url : projectURL+"/Action?method=getNewChatData"+"&"+new Date().getTime(),
        type : "POST",
        data : {
            'userId':userId,
            'workerId':workerId,
            'ID':ID,
        },
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("messageContainer");
            for (var o in data ){
                if (data[o].chatSender == true){
                    var str = "<div class=\"item item-right\">\n" +
                        "            <div class=\"bubble bubble-right\">"+data[o].chatContent+"</div>\n" +
                        "            <div class=\"avatar\">\n" +
                        "                <img src=\""+projectURL+"/WebImage/worker.png\" />\n" +
                        "            </div>\n" +
                        "        </div>";
                    msgs.innerHTML = msgs.innerHTML + str;
                } else {
                    var str = "<div class=\"item item-left\">\n" +
                        "            <div class=\"avatar\">\n" +
                        "                <img src=\""+projectURL+"/WebImage/me.png\" />\n" +
                        "            </div>\n" +
                        "            <div class=\"bubble bubble-left\">"+data[o].chatContent+"</div>\n" +
                        "        </div>";

                    msgs.innerHTML = msgs.innerHTML + str;
                }
            }

        }
    })
}

/**
 * 打开聊天页面
 */
function OpenCommunication(UserId,WorkerId,ID){
    var charSender
    if (ID == '0'){
        charSender = 1;
    } else {
        charSender = 0;
    }
    if (UserId == ''){
        openLogin();
    } else {
        $.ajax({
            url : projectURL+"/listChatData"+"?"+new Date().getTime(),
            type : "POST",
            data : {
                'userId':UserId,
                'workerId':WorkerId,
                'chatSender': charSender,
                'ID':ID
            },
            dataType : "json",
            success : function(data){
                window.open(projectURL+"/CommunicationPage.jsp",'_blank');
            }
        })
    }
}



/**
 * 打开聊天界面获得聊天记录
 */
function GetAllChatData(userId,workerId,ID){
    var method = false;
    var charSender;
    if(ID == '0'){
        method = false;
        var leftAva = workerAva;
        var rightAva = userAva;
        charSender = 1;
    } else {
        method = true;
        var leftAva = userAva;
        var rightAva = workerAva;
        charSender = 0;
    }
    $(document).ready(
        $.ajax({
            url : projectURL+"/listChatData"+"?"+new Date().getTime(),
            type : "POST",
            data : {
                'userId':userId,
                'workerId':workerId,
                'chatSender':charSender
            },
            dataType : "json",
            success : function(data){
                var msgs = document.getElementById("messageContainer");
                if (method == true){
                    var nameStr = "<div class=\"item item-center\"><span>"+data[0].userName+"</span></div>\n"
                } else {
                    var nameStr = "<div class=\"item item-center\"><span>"+data[0].workerName+"</span></div>\n"
                }
                msgs.innerHTML = msgs.innerHTML + nameStr;
                for (var o in data ){
                    if (data[o].chatSender == method){
                        var str = "<div class=\"item item-right\">\n" +
                            "            <div class=\"bubble bubble-right\">"+data[o].chatContent+"</div>\n" +
                            "            <div class=\"avatar\">\n" +
                            "                <img src="+rightAva+" />\n" +
                            "            </div>\n" +
                            "        </div>";
                        msgs.innerHTML = msgs.innerHTML + str;
                    } else {
                        var str = "<div class=\"item item-left\">\n" +
                            "            <div class=\"avatar\">\n" +
                            "                <img src="+leftAva+" />\n" +
                            "            </div>\n" +
                            "            <div class=\"bubble bubble-left\">"+data[o].chatContent+"</div>\n" +
                            "        </div>";

                        msgs.innerHTML = msgs.innerHTML + str;
                    }
                }
            }
        })
    )
}

/**
 * 展示聊天关系
 */
function showChatRelation(){
    document.getElementById("chatRelation").style.display="";
    document.getElementById("hideBackGround").style.display="block";
    document.getElementById("hideBackGround").style.height=document.body.clientHeight+"px";
}
/**
 * 关闭关系窗口
 */
function closeShowRelation(){
    document.getElementById("chatRelation").style.display="none";
    document.getElementById("hideBackGround").style.display="none";
}

/**
 * 打开收藏夹
 */
function showFavorites(){
    document.getElementById("favorites").style.display="";
    document.getElementById("hideBackGround").style.display="block";
    document.getElementById("hideBackGround").style.height=document.body.clientHeight+"px";
}

/**
 * 获得收藏夹内容
 */
function getFavorites(userId){
    var str
    $.ajax({
        url : projectURL+"/listUserFavorites"+"?time="+new Date().getTime(),
        type : "POST",
        data : {
            'userId':userId
        },
        dataType : "json",
        success : function(data){
            $("#favoritesContent").empty();
            var msgs = document.getElementById("favoritesContent");
            str = "<div class=\"singleFavorites\">\n" +
                "            <input type=\"button\" onclick=\"closeShowFavorites()\" value=\"关闭\">\n" +
                "            <input type=\"button\" onclick=\"getFavorites("+userId+")\" value=\"刷新\">\n" +
                "        </div>"
            msgs.innerHTML = msgs.innerHTML + str;
            if (data.length > 0){
                for (var o in data){
                    str = "<div class=\"singleFavorites\">\n" +
                        "            <a class=\"post-item-title\" href=\""+projectURL+"/getArticleDetail/"+data[o].articleId+"/1\" target=\"_blank\">"+data[o].articleTitle+"</a>\n" +
                        "        </div>"
                    msgs.innerHTML = msgs.innerHTML + str;
                }
            }
        }
    })
}
/**
 * 关闭关系窗口
 */
function closeShowFavorites(){
    document.getElementById("favorites").style.display="none";
    document.getElementById("hideBackGround").style.display="none";
}
function getRelation(userId,workerId){
    $.ajax({
        url : projectURL+"/showChatRelation",
        type : "POST",
        data : {
            'userId':userId,
            'workerId':workerId
        },
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("relationUL");
            if (data.length > 0){
                if (workerId == '0'){
                    for (var o in data ){
                        var str = "<div><a href=\"javascript:OpenCommunication(\'"+userId+"\'\,\'"+data[o].workerId+"\'\,\'"+0+"\')\">"+data[o].workerName+"</a> </div>"
                        msgs.innerHTML = msgs.innerHTML + str;
                    }
                } else {
                    for (var o in data ){
                        var str = "<div><a href=\"javascript:OpenCommunication(\'"+data[o].userId+"\'\,\'"+workerId+"\'\,\'"+1+"\')\">"+data[o].userName+"</a> </div>"
                        msgs.innerHTML = msgs.innerHTML + str;
                    }
                }
            }
        }
    })
}
/**
 * 发送信息
 * @param userId 用户ID
 * @param workerId 工作者ID
 * @param ID 发送者身份
 */
function SendMsg(userId,workerId,ID)
{
    var text = document.getElementById("textarea");
    if (text.value == "" || text.value == null)
    {
        alert("发送信息为空，请输入！")
    } else {
        var formatContent = SendMsgDispose(text.value)
        $.ajax({
            url : projectURL+"/sendMessage",
            type : "POST",
            data : {
                'userId':userId,
                'workerId':workerId,
                'chatSender':ID,
                'chatContent':formatContent
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    AddMsg(formatContent,ID);
                    text.value = "";
                } else {
                    alert(data.message);
                }
            }
        })

    }
}

/**
 * 处理信息中的换行和空格
 * @param detail 原内容
 * @returns {*} 处理后的内容
 * @constructor
 */
function SendMsgDispose(detail)
{
    detail = detail.replace("\n", "<br>").replace(" ", "&nbsp;")
    return detail;
}

/**
 * 发送成功后在发送者页面内增加信息
 * @param content 内容
 * @param ID 发送者身份
 */
function AddMsg(content,ID)
{
    var str = CreateMsg(content,ID);
    var msgs = document.getElementById("messageContainer");
    msgs.innerHTML = msgs.innerHTML + str;
}

/**
 * 生成聊天界面内容的字符串
 * @param content
 * @param ID
 * @returns {string}
 * @constructor
 */
function CreateMsg(content,ID)
{

    var str = "";
    if(ID == '0')
    {
        str = "<div class=\"item item-right\">\n" +
            "            <div class=\"bubble bubble-right\">"+content+"</div>\n" +
            "            <div class=\"avatar\">\n" +
            "                <img src="+userAva+" />\n" +
            "            </div>\n" +
            "        </div>";
    }
    else {
        str = "<div class=\"item item-right\">\n" +
            "            <div class=\"bubble bubble-right\">"+content+"</div>\n" +
            "            <div class=\"avatar\">\n" +
            "                <img src="+workerAva+" />\n" +
            "            </div>\n" +
            "        </div>";
    }
    return str;
}

/**
 * 删除文章
 */
function deleteArticle(articleId){
    var msg = "您确定要删除文章吗？\n\n请注意，删除操作不可逆！";
    if (confirm(msg)==true){
        $.ajax({
            url : projectURL+"/deleteArticle",
            type : "POST",
            data : {
               'articleId':articleId
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    alert(data.message)
                    window.location.href="/index.jsp";
                } else {
                    alert(data.message);
                }
            }
        })
    }
}
/*
 * 主页初始化
 */
function homePageInit(pageNum,classId){
    getArticleClass();
    getArticleList(pageNum,classId);
    changeArticleListByClass(pageNum)
}
/**
 * 获取文章列表
 */
function getArticleList(pageNum,classId){
    $.ajax({
        url : projectURL+"/getArticleList",
        type : "POST",
        data : {
            'pageNum':pageNum,
            'classId':classId
        },
        dataType : "json",
        success : function(data){
            showList(data,"post_list")
        }
    })
}
/**
 * 文章列表页码处理
 */
function showArticleListPage(pageNum){
    var classId = $("#classId").val();
    $.ajax({
        url : projectURL+"/getTotalPageNumByClassId",
        type : "POST",
        data :{
            'classId' : classId
        },
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("pageContainer");
            $("#pageContainer").empty();
            if (data.flag == true){
                var str = " <span>当前"+pageNum+"/"+data.data+"页</span>\n" +
                    "                            <a href=\"javascript:changeArticleListPage(1\,"+data.data+")\" >首页</a>\n" +
                    "                            <a href=\"javascript:changeArticleListPage("+(pageNum-1)+"\,"+data.data+")\">上一页</a>\n" +
                    "                            <a href=\"javascript:changeArticleListPage("+(pageNum+1)+"\,"+data.data+")\">下一页</a>\n" +
                    "                            <a href=\"javascript:changeArticleListPage("+data.data+"\,"+data.data+")\">尾页</a>"
                msgs.innerHTML = msgs.innerHTML + str;
            } else {
                alert(data.message)
            }
        }
    })
}

/**
 * 文章列表翻页
 */
function changeArticleListPage(pageNum,totalPageNum){
    var classId = $("#classId").val();
    if (pageNum > totalPageNum){
        pageNum = totalPageNum;
    } else if (pageNum < 1){
        pageNum = 1;
    }
    getArticleList(pageNum,classId);
    showArticleListPage(pageNum);
}

/**
 * 获得文章类型
 */
function getArticleClass(){
    $.ajax({
        url : "/getArticleClass",
        type : "POST",
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("classId");
                    for (var o in data ){
                        var str = "<option value=\""+data[o].articleClassId+"\">"+data[o].articleClassName+"</option>"
                        msgs.innerHTML = msgs.innerHTML + str;
                    }
        }
    })
}

/**
 * 改变文章分类
 */
function changeArticleListByClass(){
    //获得分类
    var classId = $("#classId").val();
    //获得文章列表
    getArticleList(1,classId);
    //更新分页
    showArticleListPage(1)
}

/**
 * 管理员
 */
function openManage(){
    document.getElementById("manger").style.display="";
    document.getElementById("hideBackGround").style.display="block";
    document.getElementById("hideBackGround").style.height=document.body.clientHeight+"px";
}
function closeManage(){
    document.getElementById("manger").style.display="none";
    document.getElementById("hideBackGround").style.display="none";
}
function getManageData(){
    var str
    $.ajax({
        url : projectURL+"/getArticleClass"+"?"+new Date().getTime(),
        type : "POST",
        dataType : "json",
        success : function(data){
            $("#mangeContent").empty();
            var msgs = document.getElementById("mangeContent");
            str = "<div class=\"singleFavorites\">\n" +
                "            <input type=\"button\" onclick=\"closeManage()\" value=\"关闭\">\n" +
                "            <input type=\"button\" onclick=\"getManageData()\" value=\"刷新\">\n" +
                "            <input type=\"button\" onclick=\"openSaveClass()\" value=\"新增\">\n" +
                "        </div>"
            msgs.innerHTML = msgs.innerHTML + str;
            for (var o in data){
                str = "<li>"+"类名："+data[o].articleClassName+
                    "&nbsp;&nbsp;&nbsp;&nbsp;阅读量："+data[o].articleClassView+
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;文章数量："
                    +data[o].articleClassNum+"<a href=\'javascript:deleteArticleClass("+data[o].articleClassId+"\,"+data[o].articleClassNum+")\' style=\'color: red\'>&nbsp;&nbsp;&nbsp删除</a> <a href='javascript:showClassOption("+data[o].articleClassId+")'>转移</a> </li>"
                msgs.innerHTML = msgs.innerHTML + str;
            }
        }
    })
}

/**
 * 添加新分类
 * @param className
 */
function saveClass(){
    var className = $("#saveClassText").val();
    if (isAvailable){
        $.ajax({
            url : projectURL+"/saveArticleClass"+"?"+new Date().getTime(),
            type : "POST",
            data : {
                'articleClassName' : className
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    alert(data.message);
                    closeSaveClass();
                    getManageData();
                    $("#saveClassText").val('');
                } else{
                    alert(data.message);
                }
            }
        })
    } else {
        alert("请检查输入的信息");
    }
}
/**
 * 打开添加界面
 */
function openSaveClass(){
    document.getElementById("manageClass").style.display="";
    document.getElementById("hideBackGround").style.display="block";
    document.getElementById("hideBackGround").style.height=document.body.clientHeight+"px";
}
function closeSaveClass(){
    document.getElementById("manageClass").style.display="none";
    document.getElementById("hideBackGround").style.display="none";
}
function updateArticleClass(originalClassId,updateClassId){
    $.ajax({
        url : projectURL+"/updateArticleClass"+"?"+new Date().getTime(),
        type : "POST",
        data : {
            'originalClassId' : originalClassId,
            'updateClassId' : updateClassId
        },
        dataType : "json",
        success : function(data){
            alert(data.message);
        }
    })
}
function changeClass(){
    var originalClassId = $("#articleClassId").val();
    var updateClassId = $("#newClassId").val();
    $.ajax({
        url : projectURL+"/updateArticleClass",
        type : "POST",
        data : {
          'originalClassId' : originalClassId,
          'updateClassId' : updateClassId
        },
        dataType : "json",
        success : function(data){
           if (data.flag == true){
               alert(data.message);
               closeClassOption();
               getManageData();
           } else {
               alert(data.message);
           }
        }
    })
}
function closeClassOption(){
    document.getElementById("changeClass").style.display="none";
}
function showClassOption(articleClassId){
    document.getElementById("changeClass").style.display="";
    $("#articleClassId").val(articleClassId);
    $.ajax({
        url : projectURL+"/getArticleClass",
        type : "POST",
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("newClassId");
            $("#newClassId").empty();
            for (var o in data ){
                if (data[o].articleClassId != articleClassId){
                    var str = "<option value=\""+data[o].articleClassId+"\">"+data[o].articleClassName+"</option>"
                    msgs.innerHTML = msgs.innerHTML + str;
                }
            }
        }
    })
}
function deleteArticleClass(classId,articleClassNum){
    var msg = "确认删除该分类吗？"
    if (articleClassNum > 0){
        alert("无法删除，该分类下有文章")
    } else {
        if (confirm(msg) == true){
            $.ajax({
                url : projectURL+"/deleteArticleClass"+"?"+new Date().getTime(),
                type : "POST",
                data : {
                    'articleClassId' : classId
                },
                dataType : "json",
                success : function(data){
                    if (data.flag == true){
                        alert(data.message);
                        getManageData();
                    } else {
                        alert(data.message);
                    }
                }
            })
        }
    }
}
function openReply(articleCommentId){
    var divId = "div"+articleCommentId;
    document.getElementById(divId).style.display="";
}
function saveCommentReply(articleCommentId){
    var divId = "div"+articleCommentId;
    var content = "text"+articleCommentId;
    var replyContainer = "replyContainer"+articleCommentId;
    var textContent = $("#text"+articleCommentId).val();
    if(textContent.length > 200){
        alert('评论不能超过200字')
    } else {
        $.ajax({
            url : projectURL+"/saveCommentReply"+"?"+new Date().getTime(),
            type : "POST",
            data : {
                'userId' : $("#userId").val(),
                'articleCommentId' : articleCommentId,
                'commentReplyContent' : textContent
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    var msgs = document.getElementById(replyContainer);
                    var str = "<div class=\"reply_info\" >"+$("#userName").val()+"\:"+textContent+"</div>";
                    msgs.innerHTML = msgs.innerHTML + str;
                    $("#text"+articleCommentId).val('');
                    document.getElementById(divId).style.display="none";
                } else {
                    alert(data.message);
                }
            }
        })
    }
}

/**
 * 删除评论及回复
 */
function deleteCommentAndReply(articleCommentId){
    var msg = "确认删除该回复吗？"
    if (confirm(msg) == true){
        var infoId = "commentDiv"+articleCommentId;
        $.ajax({
            url : projectURL+"/deleteCommentAndReply"+"?"+new Date().getTime(),
            type : "POST",
            data : {
                'articleCommentId' : articleCommentId
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    $("#"+infoId).remove();
                } else {
                    alert(data.message);
                }
            }
        })
    }
}
/**
 * 删除评论的回复
 */
function deleteReply(replyId){
    var msg = "确认删除该回复吗？"
    if (confirm(msg) == true){
        var reply = "reply"+replyId;
        $.ajax({
            url : projectURL+"/deleteCommentReply"+"?"+new Date().getTime(),
            type : "POST",
            data : {
                'commentReplyId' : replyId
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    $("#"+reply).remove();
                } else {
                    alert(data.message);
                }
            }
        })
    }
}
/**
 * 打开和关闭界面
 */
function openFrame(Id){
    if ($("#userId").val() == 0){
        openLogin();
        console.log($("#userId").val())
    } else {
        document.getElementById(Id).style.display="";
        document.getElementById("hideBackGround").style.display="block";
        document.getElementById("hideBackGround").style.height=document.body.clientHeight+"px";
    }

}
function closeFrame(Id){
    document.getElementById(Id).style.display="none";
    document.getElementById("hideBackGround").style.display="none";
}
function listReport(){
    $.ajax({
        url : projectURL+"/listReport",
        type : "POST",
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("reportlist");

            $("#reportlist").empty();
            for (var o in data ){
                console.log(data[o].workerName)
                var str = "                        <article class=\"post-item\" >\n" +
                    "                            <section class=\"post-item-body\">\n" +
                    "                                <div class=\"post-item-text\">\n" +
                    "                                    <a class=\"post-item-title\" href=\""+projectURL+"/getArticleDetail/"+data[o].articleId+"/1\" target=\"_blank\">点击跳转到被举报文章："+data[o].articleTitle+"</a>\n" +

                    "                                    <p class=\"post-item-summary\"> 举报原因：\n" +
                    data[o].reportReason+
                    "                                    </p>\n" +
                    "                                    <p class=\"post-item-summary\"> 被举报人：\n" +
                    data[o].workerName+"<a href=\'javascript:banWorkerOption("+data[o].workerId+")\' style='color: red'>&nbsp;&nbsp;封禁</a>"+
                    "                                    </p>\n" +
                    "                                </div>\n" +
                    "                                 <div>" +
                    data[o].reportContent+
                    "                                 </div>   "+
                    "                                <footer class=\"post-item-foot\">\n" +
                    "                                    <a href=\"用户首页 用户名\" class=\"post-item-author\"><span> 举报用户："+data[o].userName+"<a href=\'javascript:void(0)\' onclick=\'deleteReport("+data[o].reportId+")\' style='color: #6ce26c'>已处理？点击删除举报</a></span></a>\n" +
                    "                                    <span class=\"post-meta-item\">\n" +
                    "                            <span>"+data[o].reportTime+"</span>\n" +
                    "                                 </span>\n" +
                    "                                    <div class=\"post-meta-item btn \">\n" +
                    "                                        <a href=\'java:void(0)\' \n" +
                    "                                    </div>\n" +
                    "                                    <span id=\"digg_tip_14730648\" class=\"digg-tip\" style=\"color: #ff0000\"></span>\n" +
                    "                                </footer>\n" +
                    "                            </section>\n" +
                    "                            <figure>\n" +
                    "                            </figure>\n" +
                    "                        </article>\n"
                msgs.innerHTML = msgs.innerHTML + str;
            }
        }
    })
}
function deleteReport(reportId){
    var msg = "确认举报已处理吗？"
    if (confirm(msg) == true){
        $.ajax({
            url : projectURL+"/deleteReport"+"?"+new Date().getTime(),
            type : "POST",
            data : {
                'reportId' : reportId
            },
            dataType : "json",
            success : function(data){
                if (data.flag == true){
                    listReport();
                } else {
                    alert(data.message);
                }
            }
        })
    }
}
function saveReport(){
    var userId = $("#userId").val();
    var articleId = $("#articleId").val();
    var reportReason = $("#reportReason").val();
    var reportContent = UE.getEditor('editor').getContent();
    $.ajax({
        url : projectURL+"/saveReport"+"?"+new Date().getTime(),
        type : "POST",
        data : {
            'userId' : userId,
            'articleId' : articleId,
            'reportReason' : reportReason,
            'reportContent' : reportContent
        },
        dataType : "json",
        success : function(data){
            if (data.flag == true){
                alert(data.message);
                closeFrame('report');
            } else {
                alert(data.message);
            }
        }
    })
}

/**
 * 读取文章评论
 */
function showArticleComment(commentPageNum){
    var articleId = $("#articleId").val();
    $.ajax({
        url : projectURL+"/Action?method="+"&"+new Date().getTime(),
        type : "POST",
        data : {
            'articleId' : articleId,
            'commentPageNum':commentPageNum
        },
        dataType : "json",
        success : function(data){
            if (data.size > 0){
                var msg = document.getElementById("commentPageContainer");
                $("#commentPageContainer")
            }

        }
    })
}
/**
 * 文章评论页码处理
 */
function showArticleCommentPage(pageNum){
    var articleId = $("#articleId").val();
    $.ajax({
        url : projectURL+"/Article?method=getTotalCommentPageNum",
        type : "POST",
        data :{
            'articleId' : articleId
        },
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("pageContainer");
            $("#pageContainer").empty();
            if (data.flag == true){
                var str = " <span>当前"+pageNum+"/"+data.data+"页</span>\n" +
                    "                            <a href=\"javascript:changeCommentPage(1\,"+data.data+")\" >首页</a>\n" +
                    "                            <a href=\"javascript:changeCommentPage("+(pageNum-1)+"\,"+data.data+")\">上一页</a>\n" +
                    "                            <a href=\"javascript:changeCommentPage("+(pageNum+1)+"\,"+data.data+")\">下一页</a>\n" +
                    "                            <a href=\"javascript:changeCommentPage("+data.data+"\,"+data.data+")\">尾页</a>"
                msgs.innerHTML = msgs.innerHTML + str;
            } else {
                alert(data.message)
            }
        }
    })
}

/**
 * 评论翻页
 */
function changeCommentPage(pageNum,totalPageNum){
    var articleId = $("#articleId").val();
    if (pageNum > totalPageNum){
        pageNum = totalPageNum;
    } else if (pageNum < 1){
        pageNum = 1;
    }
    showArticleListPage(pageNum);
}

/**
 * 搜索页面
 */
function searchPageInit(pageNum,keyword){
    $("#keyword").val(keyword);
    console.log("Init"+keyword);
    $("#pageContainer").empty();
    $("#classId").empty();
    $("#post_list").empty();
    $("#selectClassDiv").remove();
    getArticleListBySearch(1);
    showArticleListPageBySearch(1);
}
/**
 * 获取文章列表
 */
function getArticleListBySearch(pageNum){
    var articleSearchKeyword = $("#keyword").val();
    $.ajax({
        url : projectURL+"/Article?method=getArticleListBySearch",
        type : "POST",
        data : {
            'pageNum':pageNum,
            'articleSearchKeyword':articleSearchKeyword
        },
        dataType : "json",
        success : function(data){
            showList(data,"post_list")
        }
    })
}
/**
 * 文章列表页码处理
 */
function showArticleListPageBySearch(pageNum){
    var articleSearchKeyword = $("#keyword").val();
    $.ajax({
        url : projectURL+"/Article?method=getTotalPageNumBySearch",
        type : "POST",
        data :{
            'articleSearchKeyword' : articleSearchKeyword
        },
        dataType : "json",
        success : function(data){
            var msgs = document.getElementById("pageContainer");
            $("#pageContainer").empty();
            if (data.flag == true){
                var str = " <span>当前"+pageNum+"/"+data.data+"页</span>\n" +
                    "                            <a href=\"javascript:changeArticleListPageBySearch(1\,"+data.data+")\" >首页</a>\n" +
                    "                            <a href=\"javascript:changeArticleListPageBySearch("+(pageNum-1)+"\,"+data.data+")\">上一页</a>\n" +
                    "                            <a href=\"javascript:changeArticleListPageBySearch("+(pageNum+1)+"\,"+data.data+")\">下一页</a>\n" +
                    "                            <a href=\"javascript:changeArticleListPageBySearch("+data.data+"\,"+data.data+")\">尾页</a>"
                msgs.innerHTML = msgs.innerHTML + str;
            } else {
                alert(data.message)
            }
        }
    })
}

/**
 * 文章列表翻页
 */
function changeArticleListPageBySearch(pageNum,totalPageNum){
    var keyword = $("#keyword").val();
    if (pageNum > totalPageNum){
        pageNum = totalPageNum;
    } else if (pageNum < 1){
        pageNum = 1;
    }
    getArticleListBySearch(pageNum,keyword);
    showArticleListPageBySearch(pageNum);
}
function showList(data,id){
    var msgs = document.getElementById(id);
    var id = "#"+id;
    $(id).empty();
    for (var o in data ){
        var str = "                        <article class=\"post-item\" >\n" +
            "                            <section class=\"post-item-body\">\n" +
            "                                <div class=\"post-item-text\">\n" +
            "                                    <a class=\"post-item-title\" href=\""+projectURL+"/getArticleDetail/"+data[o].articleId+"/1\" target=\"_blank\">"+data[o].articleTitle+"</a>\n" +
            "                                    <p class=\"post-item-summary\">\n" +
            "                                        <a href=\"用户首页 头像\">\n" +
            "                                            <img src=\""+projectURL+"/Avator/CAT.png\"  width=\"40px\" height=\"40px\" class=\"avatar\" alt=\"博主头像\">\n" +
            "                                        </a>\n" +
            data[o].articleSummary+
            "                                    </p>\n" +
            "                                </div>\n" +
            "                                <footer class=\"post-item-foot\">\n" +
            "                                    <a href=\"用户首页 用户名\" class=\"post-item-author\"><span>"+data[o].workerName+"</span></a>\n" +
            "                                    <span class=\"post-meta-item\">\n" +
            "                            <span>"+data[o].articleReleaseTime+"</span>\n" +
            "                                 </span>\n" +
            "                                    <div class=\"post-meta-item btn \">\n" +
            "                                        <img class=\"homepage-footer-image\"  src=\""+projectURL+"/WebImage/like.png\" alt=\"点赞\">\n" +
            "                                        <span id=\"digg_count_14730648\">"+data[o].articleLikeNum+"</span>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"post-meta-item btn\">\n" +
            "                                        <img class=\"homepage-footer-image\" src=\""+projectURL+"/WebImage/comment.png\" alt=\"评论\">\n" +
            "                                        <span>"+data[o].articleCommentNum+"</span>\n" +
            "                                    </div>\n" +
            "                                    <div class=\"post-meta-item btn\">\n" +
            "                                        <img class=\"homepage-footer-image\" src=\""+projectURL+"/WebImage/view.png\" alt=\"浏览\">\n" +
            "                                        <span>"+data[o].articleViewNum+"</span>\n" +
            "                                    </div>\n" +
            "                                </footer>\n" +
            "                            </section>\n" +
            "                            <figure>\n" +
            "                            </figure>\n" +
            "                        </article>\n"
        msgs.innerHTML = msgs.innerHTML + str;
    }
}
function openReleasePage(workerId){
    $.ajax({
        url : projectURL+"/getWorkerState"+"?"+new Date().getTime(),
        type : "POST",
        data : {
            'workerId' : workerId
        },
        dataType : "json",
        success : function(data){
            if (data.flag == true){
                location.href=projectURL+"/ArticleRelease.jsp"

            } else {
                alert("账号被封禁至："+data.data);
            }
        }
    })

}
function banWorkerOption(workerId){
    $("#banWorkerId").val(workerId);
    document.getElementById("banManage").style.display="";
    tipWorkerState(workerId);
}
function tipWorkerState(workerId){
    $.ajax({
        url : projectURL+"/getWorkerState"+"?"+new Date().getTime(),
        type : "POST",
        data : {
            'workerId' : workerId
        },
        dataType : "json",
        success : function(data){
            var message = $("#markSpan").next('span');
            if (data.flag == true){
                message.html("账号状态正常");
                message.addClass('message');
            } else {

                message.html("封禁至："+data.data);
                message.addClass('message');
            }
        }
    })
}
function banWorker(banDays){
    var workerId = $("#banWorkerId").val()
    $.ajax({
        url : projectURL+"/saveBanWorker"+"?"+new Date().getTime(),
        type : "POST",
        data : {
            'workerId' : workerId,
            'banDays' : banDays
        },
        dataType : "json",
        success : function(data){
            var message = $("#markSpan").next('span');
            if (data.flag == true){
                alert(data.message);
                tipWorkerState(workerId);
            } else {
                alert(data.message);
            }
        }
    })
}
function goHome(){
    location.href= projectURL+"/index.jsp";
    window.location(projectURL+"/index.jsp");
}