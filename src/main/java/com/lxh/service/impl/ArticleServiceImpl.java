package com.lxh.service.impl;

import com.lxh.dao.ArticleMapper;
import com.lxh.pojo.ArticleClass;
import com.lxh.pojo.ArticleComment;
import com.lxh.pojo.ArticleInfo;
import com.lxh.pojo.CommentReply;
import com.lxh.service.ArticleService;
import com.lxh.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 16:16
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(@Qualifier("articleMapper") ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 用户点赞操作
     * @param map 参数
     * userId    点赞的用户ID
     * articleId 点赞的文章ID
     * @return 点赞成功与否
     */
    @Override
    public boolean addArticleLike(Map<String,Integer> map) {
        int articleId = map.get(Constant.ARTICLE_ID);
        return articleMapper.saveArticleLikeInfo(articleId) ==1 && articleMapper.saveNewArticleLike(map) == 1;
    }

    /**
     * 用户取消点赞操作
     * @param map 参数
     * userId    点赞的用户ID
     * articleId 点赞的文章ID
     * @return 取消点赞成功与否
     */
    @Override
    public boolean removeArticleLike(Map<String,Integer> map) {
        int articleId = map.get(Constant.ARTICLE_ID);
        return articleMapper.deleteArticleLikeInfo(articleId) ==1 && articleMapper.deleteArticleLike(map) == 1;

    }

    /**
     * 获得页码内的文章的基本信息
     *
     * @param pageNum 页码
     * @return 页码内文章的基本信息的list
     */
    @Override
    public ArrayList<ArticleInfo> listAllArticle(int pageNum) {
        pageNum = (pageNum-1)*Constant.RECORD_IN_SINGLE_PAGE;
        return articleMapper.listAllArticle(pageNum);
    }

    /**
     * 获得页码内某类文章的信息
     *
     * @param map 保存参数
     *            pageNum 页码
     *            articleClassId 文章类别ID
     * @return 页码内某类文章的信息的list
     */
    @Override
    public ArrayList<ArticleInfo> listArticleByClass(Map<String, Integer> map) {
        return articleMapper.listArticleByClass(map);
    }

    /**
     * 获得指定文章的评论和回复
     *
     * @param map 保存参数
     *            articleId 文章ID
     *            pageNum 页码
     * @return 文章评论的list
     */
    @Override
    public ArrayList<ArticleComment> listArticleComment(Map<String, Integer> map) {
        int pageNum = (map.get(Constant.PAGE_NUM)-1)*Constant.RECORD_IN_SINGLE_PAGE;
        map.put(Constant.PAGE_NUM,pageNum);
        ArrayList<ArticleComment> articleComments = articleMapper.listArticleComment(map);
        for (ArticleComment articleComment : articleComments) {
            articleComment.setCommentReplies(articleMapper.listCommentReplies(articleComment.getArticleCommentId()));
        }
        return articleComments;
    }



    /**
     * 保存新文章的基本信息
     *
     * @param articleInfo 保存新文章基本信息的对象
     * @return 影响行数
     */
    @Override
    public boolean saveNewArticleInfo(ArticleInfo articleInfo) {
        //获得当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(System.currentTimeMillis());
        //设置发布时间
        articleInfo.setArticleReleaseTime(currentTime);
        return articleMapper.saveNewArticleInfo(articleInfo) == 1;
    }

    /**
     * 用户对文章进行评论
     *
     * @param articleComment 保存新评论信息的对象
     * @return 影响行数
     */
    @Override
    public boolean saveNewComment(ArticleComment articleComment) {
        //获得当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(System.currentTimeMillis());
        //设置发布时间
        articleComment.setArticleCommentTime(currentTime);
        return articleMapper.saveNewComment(articleComment) == 1 && articleMapper.saveNewCommentNum(articleComment.getArticleId()) == 1;
    }



    /**
     * 获得文章的收藏量
     *
     * @param articleId 文章ID
     * @return 影响行数
     */
    @Override
    public int getArticleFavoritesNumByArticleId(int articleId) {
        return articleMapper.getArticleFavoritesNumByArticleId(articleId);
    }

    /**
     * 获取所有分类
     *
     * @return 分类的list
     */
    @Override
    public ArrayList<ArticleClass> listAllArticleClass() {
        return articleMapper.listAllArticleClass();
    }

    /**
     * 获得文章总页码
     *
     * @param articleClassId 文章ID
     * @return 总页码
     */
    @Override
    public int getTotalPageNum(int articleClassId) {
        int pageNum = articleMapper.getTotalPageNum(articleClassId);
        if (pageNum % Constant.RECORD_IN_SINGLE_PAGE != 0){
            pageNum = pageNum/Constant.RECORD_IN_SINGLE_PAGE +1;
        } else {
            pageNum = pageNum/Constant.RECORD_IN_SINGLE_PAGE;
        }
        return pageNum;
    }

    /**
     * 获取单个文章的内容
     *
     * @param articleId 文章ID
     * @return 保存文章内容的list
     */
    @Override
    public ArticleInfo getArticleById(int articleId) {
        return articleMapper.getArticleById(articleId);
    }

    /**
     * 获得文章的评论总页数
     * @param articleId 文章id
     * @return 评论总页数
     */
    @Override
    public int getArticleCommentNumById(int articleId) {
        int pageNum = articleMapper.getArticleCommentNumById(articleId);
        if (pageNum % Constant.RECORD_IN_SINGLE_PAGE != 0){
            pageNum = pageNum/Constant.RECORD_IN_SINGLE_PAGE +1;
        } else {
            pageNum = pageNum/Constant.RECORD_IN_SINGLE_PAGE;
        }
        return pageNum;
    }

    /**
     * 搜索文章
     *
     * @param map 保存参数
     *            pageNum 页码
     *            keyword 关键词
     * @return 文章信息list
     */
    @Override
    public ArrayList<ArticleInfo> listArticleByKeyword(Map<String, Object> map) {
        map.put(Constant.PAGE_NUM,(((Integer)map.get(Constant.PAGE_NUM)-1)*15));
        return articleMapper.listArticleByKeyword(map);
    }

    /**
     * 获得搜索文章的总页数
     *
     * @param keyword 关键词
     * @return 总页数
     */
    @Override
    public int getTotalPageNumInSearch(String keyword) {
        int pageNum = articleMapper.getTotalPageNumInSearch(keyword);
        if (pageNum % Constant.RECORD_IN_SINGLE_PAGE != 0){
            pageNum = pageNum/Constant.RECORD_IN_SINGLE_PAGE +1;
        } else {
            pageNum = pageNum/Constant.RECORD_IN_SINGLE_PAGE;
        }
        return pageNum;
    }

    /**
     * 检查用户是否点赞
     *
     * @param map 保存参数
     *            articleId 文章Id
     *            userID 用户Id
     * @return 点赞信息
     */
    @Override
    public boolean getArticleLike(Map<String, Integer> map) {
        return articleMapper.getArticleLike(map) != null;
    }

    /**
     * 删除文章及其附属信息
     *
     * @param articleId 文章id
     * @return 影响行数
     */
    @Override
    public boolean deleteArticleInfo(int articleId) {

        return articleMapper.deleteArticleFavorites(articleId) >= 1
                && articleMapper.deleteArticleLikes(articleId) >= 1
                && articleMapper.deleteArticleComment(articleId) >= 1
                && articleMapper.deleteArticle(articleId) == 1;
    }

    /**
     * 新增文章分类
     *
     * @param articleClassName 文章分类名
     * @return 影响行数
     */
    @Override
    public boolean saveArticleClass(String articleClassName) {
        return articleMapper.saveArticleClass(articleClassName) == 1;
    }

    /**
     * 删除文章分类
     *
     * @param articleClassId 文章分类Id
     * @return 影响行数
     */
    @Override
    public boolean deleteArticleClass(int articleClassId) {
        return articleMapper.deleteArticleClass(articleClassId) == 1;
    }

    /**
     * 改变某类文章分类
     *
     * @param map 参数
     *            updateClassId 新的文章分类ID
     *            originalClassId  旧的文章分类ID
     * @return 影响行数
     */
    @Override
    public boolean updateArticleClass(Map<String, Integer> map) {
        return articleMapper.updateArticleClass(map) >= 1;
    }

    /**
     * 检查文章分类名称是否可用
     *
     * @param articleClassName 检查的文章名称
     * @return 文章分类信息
     */
    @Override
    public boolean checkArticleClassName(String articleClassName) {
        return articleMapper.checkArticleClassName(articleClassName) == null;
    }

    /**
     * 获得文章的评论及回复
     *
     * @param map 保存参数
     *            articleId 文章Id
     *            pageNum 页码
     * @return 评论信息
     */
    @Override
    public ArrayList<ArticleComment> listCommentReply(Map<String, Integer> map) {
        return null;
    }

    /**
     * 回复评论
     *
     * @param commentReply 回复信息对象
     *                     articleCommentId 评论ID
     *                     userId 回复评论的用户的ID
     *                     commentReplyContent 回复的内容
     * @return 影响行数，用于判断保存是否成功
     */
    @Override
    public boolean saveCommentReply(CommentReply commentReply) {
        return articleMapper.saveCommentReply(commentReply) == 1;
    }

    /**
     * 删除评论及其下的回复
     *
     * @param articleCommentId 文章评论ID
     * @return 删除成功与否
     */
    @Override
    public boolean deleteCommentAndReply(int articleCommentId) {
        return articleMapper.deleteCommentReplyByCommentId(articleCommentId) >=1 && articleMapper.deleteCommentByCommentId(articleCommentId) == 1;
    }

    /**
     * 删除评论下的某条回复
     *
     * @param replyId 回复ID
     * @return 影响行数
     */
    @Override
    public boolean deleteReplyByReplyId(int replyId) {
        return articleMapper.deleteReplyByReplyId(replyId) == 1;
    }
}
