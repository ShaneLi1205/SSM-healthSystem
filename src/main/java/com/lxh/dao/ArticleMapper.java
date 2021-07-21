package com.lxh.dao;

import com.lxh.pojo.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 8:56
 */
public interface ArticleMapper {

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
     * 获得指定文章的评论
     * @param map 保存参数
     * articleId 文章ID
     * pageNum 页码
     * @return 文章评论的list
     */
    ArrayList<ArticleComment> listArticleComment(Map<String,Integer> map);

    /**
     * 搜索评论的回复
     * @param articleCommentId 需要加载评论的回复id
     * @return 回复的list
     */
    ArrayList<CommentReply> listCommentReplies(int articleCommentId);

    /**
     * 保存新文章的基本信息
     * @param articleInfo 保存新文章基本信息的对象
     * @return 影响行数
     */
    int saveNewArticleInfo(ArticleInfo articleInfo);

    /**
     * 用户对文章点赞(article_like表)
     * @param articleLike 保存参数
     * articleId 文章ID
     * userId 用户ID
     * @return 影响行数
     */
    int saveNewArticleLike(ArticleLike articleLike);

    /**
     * 用户取消对文章点赞(article_like表)
     * @param articleLike 保存参数
     * articleId 文章ID
     * userId 用户ID
     * @return 影响行数
     */

    int deleteArticleLike(ArticleLike articleLike);

    /**
     * 用户对文章点赞(article_info表),增加info表的点赞数
     * @param articleId 文章ID
     * @return 影响行数
     */
    int saveArticleLikeInfo(int articleId);

    /**
     * 用户取消对文章点赞(article_info表)，减少info表的点赞数
     * @param articleId 文章ID
     * @return 影响行数
     */
    int updateArticleLikeInfo(int articleId);

    /**
     * 用户对文章进行评论
     * @param articleComment 保存新评论信息的对象
     * @return 影响行数
     */
    int saveNewComment(ArticleComment articleComment);

    /**
     * 评论后评论数量变更
     * @param articleId 文章Id
     * @return 影响行数
     */
    int saveNewCommentNum(int articleId);

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
    ArticleLike getArticleLike(ArticleLike articleLike);

    /**
     * 删除文章
     * @param articleId 文章id
     * @return 影响行数
     */
    int deleteArticle(int articleId);

    /**
     * 删除收藏
     * 用于删除文章时删除附属信息
     * @param articleId 文章ID
     * @return 影响行数
     */
    int deleteArticleFavorites(int articleId);

    /**
     * 删除点赞
     * 用于删除文章时删除附属信息
     * @param articleId 文章id
     * @return 影响行数
     */
    int deleteArticleLikes(int articleId);

    /**
     * 删除评论
     * 用于删除文章时删除附属信息
     * @param articleId 文章id
      * @return 影响行数
     */
    int deleteArticleComment(int articleId);

    /**
     * 新增文章分类
     * @param articleClassName 文章分类名
     * @return 影响行数
     */
    int saveArticleClass(String articleClassName);

    /**
     * 删除文章分类
     * @param articleClassId 文章分类Id
     * @return 影响行数
     */
    int deleteArticleClass(int articleClassId);

    /**
     * 改变某类文章分类
     * @param map 参数
     * updateClassId 新的文章分类ID
     * originalClassId  旧的文章分类ID
     * @return 影响行数
     */
    int updateArticleClass(Map<String,Integer> map);

    /**
     * 检查文章分类名称是否可用
     * @param articleClassName 检查的文章名称
     * @return 文章分类信息
     */
    ArticleClass checkArticleClassName(String articleClassName);

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
    int saveCommentReply(CommentReply commentReply);

    /**
     * 删除文章回复
     * @param articleCommentId 评论ID
     * @return 影响行数
     */
    int deleteCommentByCommentId(int articleCommentId);

    /**
     * 删除文章评论下的回复
     * 与删除文章回复一起使用
     * @param articleCommentId 评论ID
     * @return  影响行数
     */
    int deleteCommentReplyByCommentId(int articleCommentId);

    /**
     * 删除评论下的某条回复
     * @param replyId 回复ID
     * @return 影响行数
     */
    int deleteReplyByReplyId(int replyId);
}
