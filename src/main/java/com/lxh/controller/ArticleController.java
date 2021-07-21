package com.lxh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxh.bean.Constant;
import com.lxh.bean.ResultInfo;
import com.lxh.bean.StatusCode;
import com.lxh.pojo.ArticleClass;
import com.lxh.pojo.ArticleComment;
import com.lxh.pojo.ArticleInfo;
import com.lxh.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/20 22:43
 */
@Controller
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(@Qualifier("articleServiceImpl") ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("/getArticleClass")
    @ResponseBody
    public String getArticleClass(HttpServletRequest request){
        ArrayList<ArticleClass> articleClasses = articleService.listAllArticleClass();
        request.getSession().setAttribute(Constant.ARTICLE_CLASS_LIST,articleClasses);
        return JSONObject.toJSONString(articleClasses);
    }

    @RequestMapping("/getArticleList")
    @ResponseBody
    public String getArticleList(Integer pageNum, Integer classId){

        String jsonStr;
        //获取页码和文章类型相关参数
        //当前页码，默认第一页
        int defaultPageNum = 1;
        //页面分类，默认全部
        int defaultClassId = 0;

        if(pageNum != null){
            defaultPageNum = pageNum;
        }
        if(classId != null ){
            defaultClassId = classId;
        }

        //获取文章
        ArrayList<ArticleInfo> articleInfoArrayList;
        //获得分类下的文章
        if (defaultClassId == 0){
            articleInfoArrayList = articleService.listAllArticle(defaultPageNum);
        } else {
            Map<String,Integer> map = new HashMap<>(3);
            map.put(Constant.PAGE_NUM,defaultPageNum);
            map.put(Constant.ARTICLE_CLASS_ID,defaultClassId);
            articleInfoArrayList = articleService.listArticleByClass(map);
        }
        jsonStr = JSON.toJSONString(articleInfoArrayList);
        return jsonStr;
    }

    @RequestMapping("/getTotalPageNumByClassId")
    @ResponseBody
    public String getTotalPageNumByClassId(Integer classId) {
        int defaultClassId = 0;
        if (classId != null){
            defaultClassId = classId;
        }
        return JSONObject.toJSONString(new ResultInfo<>(true, StatusCode.SUCCESS.getMessage(),articleService.getTotalPageNum(defaultClassId)));
    }

    @RequestMapping("/getArticleDetail/{articleId}/{commentPageNum}")
    public String getArticleDetail(@PathVariable Integer articleId,@PathVariable Integer commentPageNum, HttpServletRequest request, HttpServletResponse response){
        //获得文章Id和评论页码参数
        int defaultArticleId = 1;
        int defaultCommentPageNum = 1;
        if (articleId != null){
            defaultArticleId = articleId;
        }
        if (commentPageNum != null){
            defaultCommentPageNum = commentPageNum;
        }
        //获得总页数
        int commentTotalPageNum = articleService.getArticleCommentNumById(defaultArticleId);
        Map<String,Integer> map = new HashMap<>(3);
        map.put(Constant.ARTICLE_ID,defaultArticleId);
        map.put(Constant.COMMENT_CURRENT_PAGE_NUM,defaultCommentPageNum);
        //存入数据以供页面读取
        request.setAttribute(Constant.ARTICLE_ID,defaultArticleId);
        request.setAttribute(Constant.COMMENT_CURRENT_PAGE_NUM,commentPageNum);
        request.setAttribute(Constant.COMMENT_TOTAL_PAGE_NUM,commentTotalPageNum);
        //获取文章的内容及相关评论信息
        ArticleInfo articleInfo = articleService.getArticleById(defaultArticleId);
        if (articleInfo == null){
            try {
                response.sendRedirect(Constant.ERROR_PAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ArrayList<ArticleComment> articleCommentArrayList = articleService.listArticleComment(map);
        //设置数据
        request.removeAttribute(Constant.ARTICLE_INFO_OBJ);
        request.setAttribute(Constant.ARTICLE_INFO_OBJ,articleInfo);
        request.setAttribute(Constant.ARTICLE_COMMENT_LIST,articleCommentArrayList);
        return "forward:/ArticleDetail.jsp";
    }
    @RequestMapping("/deleteArticle")
    @ResponseBody
    public String deleteArticle(int articleId) {
        String jsonStr;
        if (articleService.deleteArticleInfo(articleId)) {
            jsonStr = JSON.toJSONString(new ResultInfo<>(true, StatusCode.DELETE_SUCCESS.getMessage()));
        } else {
            jsonStr = JSON.toJSONString(new ResultInfo<>(false, StatusCode.DELETE_FAIL.getMessage()));
        }
        return jsonStr;
    }
}
