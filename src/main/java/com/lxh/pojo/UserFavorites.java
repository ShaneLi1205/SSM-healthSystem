package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户收藏夹表实体类
 * @author LXH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavorites {
    private int userFavoritesId;
    private int userId;
    private int articleId;
    private String articleTitle;
}
