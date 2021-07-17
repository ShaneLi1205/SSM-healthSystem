package com.lxh.service;

import com.lxh.dao.ArticleClassMapper;
import com.lxh.pojo.ArticleClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LXH
 * @Date: 2021/7/17 22:38
 */
@Service
public class ArticleClassServiceImpl implements ArticleClassService{

    private ArticleClassMapper articleClassMapper;

    @Autowired
    public ArticleClassServiceImpl(@Qualifier("articleClassMapper") ArticleClassMapper articleClassMapper) {
        this.articleClassMapper = articleClassMapper;
    }

    /**
     * 获取所有文章分类信息
     *
     * @return 文章分类信息list
     */
    @Override
    public List<ArticleClass> listAllArticleClass() {
        return articleClassMapper.listAllArticleClass();
    }
}
