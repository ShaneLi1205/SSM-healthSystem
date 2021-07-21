package com.lxh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxh.bean.Constant;
import com.lxh.bean.ResultInfo;
import com.lxh.bean.StatusCode;
import com.lxh.pojo.Admin;
import com.lxh.pojo.User;
import com.lxh.pojo.Worker;
import com.lxh.service.AdminService;
import com.lxh.service.UserService;
import com.lxh.service.WorkerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * 基本权限操作的接口
 * @Author: LXH
 * @Date: 2021/7/21 9:21
 */
@RestController
public class BasicAuthorityController {

    private AdminService adminService;
    private UserService userService;
    private WorkerService workerService;


    public BasicAuthorityController(AdminService adminService, UserService userService, WorkerService workerService) {
        this.adminService = adminService;
        this.userService = userService;
        this.workerService = workerService;
    }

    /**
     * 用户注册
     * @param user 注册信息
     * @param request 请求
     * @return 注册成功与否
     */
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

    /**
     * 检查用户名或账号重复
     * @param account 账号
     * @param username 用户名
     * @return 是否可用
     */
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

    /**
     * 工作者登录
     * @param worker 工作者信息
     * @param request 请求
     * @return 登录状态
     */
    @RequestMapping("/workerLogin")
    public String workerLogin(Worker worker, HttpServletRequest request){
        Worker workerInfo = workerService.getWorker(worker);
        ResultInfo<Object> resultInfo;
        if (workerInfo != null){
            request.getSession().setAttribute(Constant.WORKER_SESSION, request.getSession().getId());
            request.getSession().setAttribute(Constant.WORKER_OBJ, workerInfo);
            resultInfo = new ResultInfo<>(true, StatusCode.LOGIN_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.LOGIN_FAIL.getMessage());
        }
        return JSONObject.toJSONString(resultInfo);
    }

    /**
     * 管理员登录
     * @param admin 管理员信息
     * @param request 请求
     * @return 登录状态
     */
    @RequestMapping("/adminLogin")
    public String adminLogin(Admin admin, HttpServletRequest request){
        Admin adminInfo = adminService.getAdmin(admin);
        ResultInfo<Object> resultInfo;
        if (admin != null){
            request.getSession().setAttribute(Constant.ADMIN_SESSION, request.getSession().getId());
            request.getSession().setAttribute(Constant.ADMIN_OBJ, adminInfo);
            resultInfo = new ResultInfo<>(true, StatusCode.LOGIN_SUCCESS.getMessage());
        } else {
            resultInfo = new ResultInfo<>(false,StatusCode.LOGIN_FAIL.getMessage());
        }
        return JSONObject.toJSONString(resultInfo);
    }

    /**
     * 退出登录
     * @param request 请求
     * @param response 响应
     */
    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        //移除所有session
        request.getSession().removeAttribute(Constant.USER_SESSION);
        request.getSession().removeAttribute(Constant.USER_OBJ);
        request.getSession().removeAttribute(Constant.ADMIN_SESSION);
        request.getSession().removeAttribute(Constant.ADMIN_OBJ);
        request.getSession().removeAttribute(Constant.WORKER_SESSION);
        request.getSession().removeAttribute(Constant.WORKER_OBJ);
        request.getSession().removeAttribute(Constant.USER_ID);
        request.getSession().removeAttribute(Constant.WORKER_ID);
        request.getSession().removeAttribute(Constant.ID);
        //回到登录前页面
        String requestUrl = request.getHeader("referer");
        try {
            response.sendRedirect(requestUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
