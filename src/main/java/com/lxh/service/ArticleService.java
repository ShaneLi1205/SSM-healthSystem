package com.lxh.service;

import com.lxh.pojo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 16:16
 */
@Service

public interface ArticleService {

    /**
     * 用户点赞或取消点赞
     */
    boolean saveUserLikeChange(ArticleLike articleLike);
    /**
     * 用户点赞操作
     * @param articleLike 参数
     * userId    点赞的用户ID
     * articleId 点赞的文章ID
     * @return 点赞成功与否
     */
    boolean addArticleLike(ArticleLike articleLike);

    /**
     * 用户取消点赞操作
     * @param articleLike 参数
     * userId    点赞的用户ID
     * articleId 点赞的文章ID
     * @return 取消点赞成功与否
     */
    boolean deleteArticleLike(ArticleLike articleLike);
    /**
     * 获得页码内的文章的基本信息
     * @param pageNum 页码
     * @return 页码内文章的基本信息的list
     */
    ArrayList<ArticleInfo> listAllArticle(int pageNum);

    /**
     * 获得页码内某类文章的信息
     * @param map 保存参数
     * pageNum 页码
     * articleClassId 文章类别ID
     * @return 页码内某类文章的信息的list
     */
    ArrayList<ArticleInfo> listArticleByClass(Map<String,Integer> map);

    /**
     * 获得指定文章的评论和回复
     * @param map 保存参数
     * articleId 文章ID
     * pageNum 页码
     * @return 文章评论的list
     */
    ArrayList<ArticleComment> listArticleComment(Map<String,Integer> map);



    /**
     * 保存新文章的基本信息
     * @param articleInfo 保存新文章基本信息的对象
     * @return 影响行数
     */
    boolean saveNewArticleInfo(ArticleInfo articleInfo);


    /**
     * 用户对文章进行评论
     * @param articleComment 保存新评论信息的对象
     * @return 影响行数
     */
    boolean saveNewComment(ArticleComment articleComment);

    /**
     * 获得文章的收藏量
     * @param articleId 文章ID
     * @return 影响行数
     */
    int getArticleFavoritesNumByArticleId(int articleId);

    /**
     * 获取所有分类
     * @return 分类的list
     */
    ArrayList<ArticleClass> listAllArticleClass();

    /**
     * 获得文章总页码
     * @param articleClassId 文章ID
     * @return 总页码
     */
    int getTotalPageNum(int articleClassId);

    /**
     * 获取单个文章的内容
     * @param articleId 文章ID
     * @return 保存文章内容的list
     */
    ArticleInfo getArticleById(int articleId);

    /**
     * 获得文章的评论数
     * @param articleId 文章id
     * @return 评论数
     */
    int getArticleCommentNumById(int articleId);

    /**
     * 搜索文章
     * @param map 保存参数
     * pageNum 页码
     * keyword 关键词
     * @return 文章信息list
     */
    ArrayList<ArticleInfo> listArticleByKeyword(Map<String,Object> map);

    /**
     * 获得搜索文章的总页数
     * @param keyword 关键词
     * @return 总页数
     */
    int getTotalPageNumInSearch(String keyword);

    /**
     * 检查用户是否点赞
     * @param articleLike 保存参数
     * articleId 文章Id
     * userID 用户Id
     * @return 点赞信息
     */
    boolean getArticleLike(ArticleLike articleLike);


    /**
     * 删除文章及其附属信息
     * @param articleId 文章id
     * @return 影响行数
     */
    boolean deleteArticleInfo(int articleId);



    /**
     * 新增文章分类
     * @param articleClassName 文章分类名
     * @return 影响行数
     */
    boolean saveArticleClass(String articleClassName);

    /**
     * 删除文章分类
     * @param articleClassId 文章分类Id
     * @return 影响行数
     */
    boolean deleteArticleClass(int articleClassId);

    /**
     * 改变某类文章分类
     * @param map 参数
     * updateClassId 新的文章分类ID
     * originalClassId  旧的文章分类ID
     * @return 影响行数
     */
    boolean updateArticleClass(Map<String,Integer> map);

    /**
     * 检查文章分类名称是否可用
     * @param articleClassName 检查的文章名称
     * @return 文章分类信息
     */
    boolean checkArticleClassName(String articleClassName);

    /**
     * 获得文章的评论及回复
     * @param map 保存参数
     * articleId 文章Id
     * pageNum 页码
     * @return 评论信息
     */
    ArrayList<ArticleComment> listCommentReply(Map<String,Integer> map);

    /**
     * 回复评论
     * @param commentReply 回复信息对象
     *  articleCommentId 评论ID
     *  userId 回复评论的用户的ID
     *  commentReplyContent 回复的内容
     * @return 影响行数，用于判断保存是否成功
     */
    boolean saveCommentReply (CommentReply commentReply);

    /**
     * 删除评论及其下的回复
     * @param articleCommentId 文章评论ID
     * @return 删除成功与否
     */
    boolean deleteCommentAndReply(int articleCommentId);


    /**
     * 删除评论下的某条回复
     * @param replyId 回复ID
     * @return 影响行数
     */
    boolean deleteReplyByReplyId(int replyId);
}
