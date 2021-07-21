package com.lxh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxh.bean.Constant;
import com.lxh.bean.ResultInfo;
import com.lxh.bean.StatusCode;
import com.lxh.pojo.User;
import com.lxh.pojo.UserFavorites;
import com.lxh.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: LXH
 * @Date: 2021/7/21 9:28
 */
@RestController
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     * @param user 用户信息
     * @return 登录结果
     */
    @RequestMapping("/userLogin")
    public String userLogin(User user, HttpServletRequest request){
        User userInfo = userService.getUser(user);
        ResultInfo<Object> resultInfo;
        if (userInfo != null){
            request.getSession().setAttribute(Constant.USER_SESSION, request.getSession().getId());
            request.getSession().setAttribute(Constant.USER_OBJ, userInfo);
            resultInfo = new ResultInfo<>(true, StatusCode.LOGIN_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.LOGIN_FAIL.getMessage());
        }
        return JSONObject.toJSONString(resultInfo);
    }
    @RequestMapping("/userRegister")
    public String userRegister(User user,HttpServletRequest request){
        String jsonStr;
        ResultInfo<Object> resultInfo;
        //将信息存储到数据库
        if (userService.saveNewUser(user)){
            //获得注册成功的用户信息
            user = userService.getUser(user);
            request.getSession().setAttribute(Constant.USER_SESSION,request.getSession().getId());
            request.getSession().setAttribute(Constant.USER_OBJ,user);
            //返回信息到页面
            resultInfo = new ResultInfo<>(true, StatusCode.REGISTER_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false, StatusCode.REGISTER_FAIL.getMessage());
        }
        jsonStr = JSON.toJSONString(resultInfo);
        return jsonStr;
    }
    @RequestMapping("/registerCheck")
    public String registerCheck(String account, String username){
        ResultInfo<Object> resultInfo;
        if (userService.checkUserAccountAndName(account, username)){
            resultInfo = new ResultInfo<>(true,StatusCode.AVAILABLE.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.UNAVAILABLE.getMessage());
        }
        return JSONObject.toJSONString(resultInfo);
    }
    @RequestMapping("/listUserFavorites")
    public String listUserFavorites(Integer userId){
        List<UserFavorites> userFavorites;
        if (userId != null){
            userFavorites = userService.listUserFavorites(userId);
            return JSONObject.toJSONString(userFavorites);
        } else {
          return JSONObject.toJSONString(new ResultInfo<Object>(false,StatusCode.PARAMETER_ERROR.getMessage()));
        }
    }
}
