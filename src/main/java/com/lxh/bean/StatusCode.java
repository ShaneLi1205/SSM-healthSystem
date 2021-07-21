package com.lxh.bean;

public enum StatusCode {
    //响应码
    LOGIN_SUCCESS("LoginSuccess","登录成功"),
    LOGIN_FAIL("LoginFail","登录失败"),
    ACCOUNT_AVAILABLE("AccountAvailable","账号可用"),
    ACCOUNT_EXIST("AccountExist","账号已存在"),
    ARTICLE_CLASS_NAME_AVAILABLE("ArticleClassNameAvailable","名称可用"),
    ARTICLE_CLASS_NAME_EXIST("ArticleClassNameExist","名称已存在"),
    NAME_AVAILABLE("NameAvailable","用户名可用"),
    NAME_EXIST("UserExist","用户名已存在"),
    REGISTER_SUCCESS("RegisterSuccess","注册成功"),
    REGISTER_FAIL("RegisterFail","注册失败"),
    USER_NOT_FOUND("UserNotFound","用户不存在"),
    LOGOUT_SUCCESS("LogoutSuccess","退出登录成功"),
    LOGOUT_FAIL("LogoutFail","退出登录失败"),
    USER_LIKE("UserLike","已点赞"),
    USER_NOT_LIKE("UserNotLike","未点赞"),
    USER_FAVORITES("UserFavorites","已收藏"),
    USER_NOT_FAVORITES("UserNotFavorites","未收藏"),
    USER_LIKE_SUCCESS("UserLikeSuccess","点赞成功"),
    USER_LIKE_FAIL("UserLikeFail","点赞失败"),
    USER_CANCEL_LIKE_SUCCESS("UserCancelLikeSuccess","取消点赞成功"),
    USER_CANCEL_LIKE_FAIL("UserCancelLikeFail","取消点赞失败"),
    USER_FAVORITES_SUCCESS("UserFavoritesSuccess","收藏成功"),
    USER_FAVORITES_FAIL("UserFavoritesFail","收藏失败"),
    USER_CANCEL_FAVORITES_SUCCESS("UserCancelFavoritesSuccess","取消收藏成功"),
    USER_CANCEL_FAVORITES_FAIL("UserCancelFavoritesFail","取消收藏失败"),
    USER_COMMENT_SUCCESS("UserCommentSuccess","评论成功"),
    USER_COMMENT_FAIL("UserCommentFail","评论失败"),
    SAVE_ARTICLE_SUCCESS("SaveArticleSuccess","文章发布成功"),
    SAVE_ARTICLE_FAIL("SaveArticleFail","文章发布失败"),
    UNKNOWN_ERROR("UnknownError","错误"),
    SEND_MESSAGE_SUCCESS("sendMessageSuccess","消息发送成功"),
    SEND_MESSAGE_FAIL("sendMessageFail","消息发送失败"),
    NEW_DATA_NOT_FOUND("NewDataNotFound","无新聊天记录"),
    DELETE_SUCCESS("DeleteSuccess","删除成功"),
    DELETE_FAIL("DeleteFail","删除失败"),
    SUCCESS("Success","成功"),
    SAVE_DATA_SUCCESS("SaveDataSuccess","保存成功"),
    SAVE_DATA_FAIL("SaveDataFail","保存失败"),
    REPORT_SUCCESS("ReportSuccess","举报成功"),
    REPORT_FAIL("ReportFail","举报失败"),
    DATA_FORMAT_ERROR("DataFormatError","数据格式有误"),
    WORKER_STATE_NORMAL("WorkerStateNormal","账号状态正常"),
    WORKER_STATE_BANNED("WorkerStateBanned","账号被封禁"),
    BAN_SUCCESS("BanSuccess","封禁或解封成功"),
    BAN_FAIL("BanSuccess","封禁或解封失败"),
    PARAMETER_ERROR("parameterError","参数错误"),
    UNAVAILABLE ("unavailable","账号或用户名重复"),
    AVAILABLE("available","当前输入可用");

    private String code;
    private String message;

    StatusCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
