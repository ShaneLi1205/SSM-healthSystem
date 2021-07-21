package com.lxh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxh.bean.Constant;
import com.lxh.bean.ResultInfo;
import com.lxh.bean.StatusCode;
import com.lxh.pojo.*;
import com.lxh.service.ArticleService;
import com.lxh.service.ChatService;
import com.lxh.service.ReportService;
import com.lxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/21 9:28
 */
@RestController
public class LowAuthorityController {


    private UserService userService;
    private ChatService chatService;
    private ArticleService articleService;
    @Autowired
    private ReportService reportService;

    public LowAuthorityController(UserService userService, ChatService chatService, ArticleService articleService) {
        this.userService = userService;
        this.chatService = chatService;
        this.articleService = articleService;
    }

    /**
     * 用户获得收藏夹
     * @param userId 用户ID
     * @return 收藏夹信息
     */
    @RequestMapping("/listUserFavorites")
    public String listUserFavorites(Integer userId){
        List<UserFavorites> userFavorites;
        if (userId != null){
            userFavorites = userService.listUserFavorites(userId);
            return JSONObject.toJSONString(userFavorites);
        } else {
          return JSONObject.toJSONString(new ResultInfo<>(false, StatusCode.PARAMETER_ERROR.getMessage()));
        }
    }

    @RequestMapping("/showChatRelation")
    public String showChatRelation(Integer userId,Integer workerId){
        Map<String,Integer> map = new HashMap<>(2);
        map.put(Constant.WORKER_ID,workerId);
        map.put(Constant.USER_ID,userId);
        System.out.println("userId"+userId+"  workerId"+workerId);
        ArrayList<ChatData> chatDataArrayList = chatService.listChatRelation(map);
        return JSON.toJSONString(chatDataArrayList);
    }

    @RequestMapping("/listNewChatData")
    public String listNewChatData(ChatData chatData){
        return JSON.toJSONString(chatService.listNewChatData(chatData));
    }
    @RequestMapping("/listChatData")
    public String listChatData(ChatData chatData,String ID,HttpServletRequest request){
        //获取需要获得记录的用户和工作者的ID
        request.getSession().removeAttribute(Constant.USER_ID);
        request.getSession().removeAttribute(Constant.WORKER_ID);
        request.getSession().removeAttribute(Constant.ID);
        //保存信息到Session
        request.getSession().setAttribute(Constant.USER_ID,chatData.getUserId());
        request.getSession().setAttribute(Constant.WORKER_ID,chatData.getWorkerId());
        request.getSession().setAttribute(Constant.ID,ID);
        return JSON.toJSONString(chatService.listChatData(chatData));
    }
    @RequestMapping("/sendMessage")
    public String sendMessage(ChatData chatData){
        ResultInfo<Object> resultInfo;
        if (chatService.saveNewChatData(chatData)){
            resultInfo = new ResultInfo<>(true,StatusCode.SEND_MESSAGE_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.SEND_MESSAGE_FAIL.getMessage());
        }

        return JSON.toJSONString(resultInfo);
    }

    @RequestMapping("/checkLike")
    public String checkLike(ArticleLike articleLike){
        ResultInfo<Object> resultInfo;
        if (articleService.getArticleLike(articleLike)){
            resultInfo = new ResultInfo<>(true,StatusCode.USER_LIKE.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.USER_NOT_LIKE.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }

    @RequestMapping("/checkFavorites")
    public String checkFavorites(UserFavorites userFavorites){
        ResultInfo<Object> resultInfo;
        if (userService.getUserIsFavourites(userFavorites)){
             resultInfo = new ResultInfo<>(true,StatusCode.USER_FAVORITES.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.USER_NOT_FAVORITES.getMessage());
        }
       return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/userFavorites")
    public String userFavorites(UserFavorites userFavorites){
        ResultInfo<Object> resultInfo;
        if (userService.saveUserFavoritesChange(userFavorites)){
            resultInfo = new ResultInfo<>(true,StatusCode.ACTION_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.ACTION_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/userLike")
    public String userLike(ArticleLike articleLike){
        ResultInfo<Object> resultInfo;
        if (articleService.saveUserLikeChange(articleLike)){
            resultInfo = new ResultInfo<>(true,StatusCode.ACTION_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.ACTION_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("userComment")
    public String userComment(ArticleComment articleComment){
        ResultInfo<Object> resultInfo;
        if (articleService.saveNewComment(articleComment)){
            resultInfo = new ResultInfo<>(true,StatusCode.USER_COMMENT_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.USER_COMMENT_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/saveReport")
    public String saveReport(Report report){
        ResultInfo<Object> resultInfo;
        if (reportService.saveReport(report)){
            resultInfo = new ResultInfo(true,StatusCode.REPORT_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.REPORT_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/deleteCommentReply")
    public String deleteCommentReply(int commentReplyId){
        ResultInfo<Object> resultInfo;
        if (articleService.deleteReplyByReplyId(commentReplyId)){
            resultInfo = new ResultInfo(true,StatusCode.DELETE_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.DELETE_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/deleteCommentAndReply")
    public String deleteComment(int articleCommentId){
        ResultInfo<Object> resultInfo;
        if (articleService.deleteCommentAndReply(articleCommentId)){
            resultInfo = new ResultInfo(true,StatusCode.DELETE_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.DELETE_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
    @RequestMapping("/saveCommentReply")
    public String saveCommentReply(CommentReply commentReply){
        ResultInfo<Object> resultInfo;
        if (articleService.saveCommentReply(commentReply)){
            resultInfo = new ResultInfo(true,StatusCode.USER_COMMENT_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo(false,StatusCode.USER_COMMENT_FAIL.getMessage());
        }
        return JSON.toJSONString(resultInfo);
    }
}
