package com.lxh.service;

import com.lxh.pojo.ArticleClass;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LXH
 * @Date: 2021/7/17 22:35
 */
@Service
public interface ArticleClassService {


    /**
     * 获取所有文章分类信息
     * @return 文章分类信息list
     */
    public List<ArticleClass> listAllArticleClass();
}
