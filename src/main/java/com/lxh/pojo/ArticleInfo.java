package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章基本信息表实体类
 * @author LXH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleInfo {
    private int articleId;
    private int workerId;
    private int articleClassId;
    private int articleViewNum;
    private int articleLikeNum;
    private int articleCommentNum;
    private String articleTitle;
    private String articleReleaseTime;
    private String articleClassName;
    private String articleContent;
    private String articleSummary;
    private String workerName;

}
