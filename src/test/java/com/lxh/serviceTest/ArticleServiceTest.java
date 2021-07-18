package com.lxh.serviceTest;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.lxh.pojo.ArticleComment;
import com.lxh.pojo.ArticleInfo;
import com.lxh.service.ArticleService;
import com.lxh.utils.Constant;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 16:36
 */
public class ArticleServiceTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    ArticleService articleServiceImpl = context.getBean("articleServiceImpl", ArticleService.class);
    @Test
    public void listAllArticleTest(){
        for (ArticleInfo articleInfo : articleServiceImpl.listAllArticle(1)) {
            System.out.println(articleInfo);
        }
    }
    @Test
    public void listCommentsAndReplies(){
        Map<String,Integer> map = new HashMap<>();
        map.put(Constant.PAGE_NUM,1);
        map.put(Constant.ARTICLE_ID,1);
        for (ArticleComment articleComment : articleServiceImpl.listArticleComment(map)) {
            System.out.println(articleComment);
        }
    }
    @Test
    public void getFavoritesTest(){
        System.out.println(articleServiceImpl.getArticleFavoritesNumByArticleId(9));
    }
    @Test
    public void getTotalPageNumTest(){
        System.out.println(articleServiceImpl.getTotalPageNum(0));
    }
    @Test
    public void getSingleArticle(){
        System.out.println(articleServiceImpl.getArticleById(1));
    }
}
