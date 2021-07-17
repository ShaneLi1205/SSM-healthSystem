package com.lxh.dao;

import com.lxh.pojo.ArticleClass;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: LXH
 * @Date: 2021/7/17 22:34
 */
@Repository
public interface ArticleClassMapper {
    /**
     * 获取所有文章分类信息
     * @return 文章分类信息list
     */
    public List<ArticleClass> listAllArticleClass();
}
