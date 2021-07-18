package com.lxh.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章分类信息实体类
 * @Author: LXH
 * @Date: 2021/7/17 23:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleClass {
    private int articleClassId;
    private int articleClassView;
    private int articleClassNum;
    private String articleClassName;
}
