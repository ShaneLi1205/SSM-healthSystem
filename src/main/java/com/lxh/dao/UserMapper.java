package com.lxh.dao;

import com.lxh.pojo.User;
import com.lxh.pojo.UserFavorites;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 8:56
 */
public interface UserMapper {
    /**
     * 获得所有用户的信息
     * @return 所有用户的List
     */
    ArrayList<User> listAllUser();

    /**
     * 获得所有用户的昵称和ID
     * @return 所有用户的名称和ID
     */
    ArrayList<User> listAllUserNameAndId();
    /**
     * 新用户注册
     * @param user 保存新用户数据的对象
     * @return 影响行数
     */
    int saveNewUser(User user);

    /**
     * 用户登录验证
     * @param user 用户输入的账号密码
     * @return 登录的用户信息
     */
    User getUser(User user);

    /**
     * 验证用户的账号或用户名
     * @param account 需要验证的账号
     * @param username  需要验证的用户名
     * @return 用户信息
     */
    User checkUserAccountAndName(@Param("account") String account,@Param("username") String username);
    /**
     * 用户账号验证
     * @param checkAccount 需要验证的账号
     * @return 验证得到的信息
     */
    User getUserAccount(String checkAccount);

    /**
     * 用户收藏文章
     * @param userFavorites 包括文章Id（articleId）和用户ID（userId）
     * @return 影响行数
     */
    int saveUserFavorites(UserFavorites userFavorites);

    /**
     * 用户取消收藏文章
     * @param userFavorites 包括文章Id（articleId）和用户ID（userId）
     * @return 影响行数
     */
    int deleteUserFavorites(UserFavorites userFavorites);

    /**
     * 用户获得收藏夹的信息
     * @param userId 用户ID
     * @return 用户的收藏信息
     */
    ArrayList<UserFavorites> listUserFavorites(int userId);

    /**
     * 用户名称验证
     * @param checkName 验证的用户名
     * @return 验证的信息
     */
    User getUserName(String checkName);

    /**
     * 检查用户是否收藏
     * @param userFavorites 包括文章Id（articleId）和用户ID（userId）
     * @return 得到的收藏信息
     */
    UserFavorites getUserIsFavourites(UserFavorites userFavorites);
}
