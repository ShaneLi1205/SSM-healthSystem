package com.lxh.service.impl;

import com.lxh.dao.UserMapper;
import com.lxh.pojo.User;
import com.lxh.pojo.UserFavorites;
import com.lxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 10:16
 */
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(@Qualifier("userMapper") UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 获得所有用户的信息
     *
     * @return 所有用户的List
     */
    @Override
    public ArrayList<User> listAllUser() {
        return userMapper.listAllUser();
    }

    /**
     * 获得所有用户的昵称和ID
     *
     * @return 所有用户的名称和ID
     */
    @Override
    public ArrayList<User> listAllUserNameAndId() {
        return userMapper.listAllUserNameAndId();
    }

    /**
     * 新用户注册
     *
     * @param user 保存新用户数据的对象
     * @return 影响行数
     */
    @Override
    public boolean saveNewUser(User user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(System.currentTimeMillis());
        user.setUserRegisterTime(currentTime);
        return userMapper.saveNewUser(user) == 1;
    }

    /**
     * 用户登录验证
     *
     * @param user 用户输入的账号密码
     * @return 登录的用户信息
     */
    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }

    /**
     * 验证用户的账号或用户名
     *
     * @param account  需要验证的账号
     * @param username 需要验证的用户名
     * @return 用户信息
     */
    @Override
    public boolean checkUserAccountAndName(String account, String username) {
        return userMapper.checkUserAccountAndName(account, username) == null;
    }

    /**
     * 用户账号验证
     *
     * @param checkAccount 需要验证的账号
     * @return 验证得到的信息
     */
    @Override
    public boolean getUserAccount(String checkAccount) {
        return userMapper.getUserAccount(checkAccount) == null;
    }

    /**
     * 用户收藏或取消收藏文章
     *
     * @param userFavorites 需要操作的信息
     * @return 操作成功与否
     */
    @Override
    public boolean saveUserFavoritesChange(UserFavorites userFavorites) {
        int result;
        if (userFavorites.getUserFavoritesId() == 0){
            result = userMapper.saveUserFavorites(userFavorites);
        } else {
            result = userMapper.deleteUserFavorites(userFavorites);
        }
        return result == 1;
    }


    /**
     * 用户获得收藏夹的信息
     *
     * @param userId 用户ID
     * @return 用户的收藏信息
     */
    @Override
    public ArrayList<UserFavorites> listUserFavorites(int userId) {
        return userMapper.listUserFavorites(userId);
    }

    /**
     * 用户名称验证
     *
     * @param checkName 验证的用户名
     * @return 验证的信息
     */
    @Override
    public boolean getUserName(String checkName) {
        return userMapper.getUserName(checkName) == null;
    }

    /**
     * 检查用户是否收藏
     *
     * @param userFavorites 包括文章Id（articleId）和用户ID（userId）
     * @return 得到的收藏信息
     */
    @Override
    public boolean getUserIsFavourites(UserFavorites userFavorites) {
        return userMapper.getUserIsFavourites(userFavorites) != null;
    }
}
