package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户收藏夹表实体类
 * @author LXH
 * @date 2021/7/18 9:00
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
