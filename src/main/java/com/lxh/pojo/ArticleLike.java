package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章点赞表实体类
 * @author LXH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleLike {
    private int userId;
    private int articleId;
    private int articleLikeId;
}
