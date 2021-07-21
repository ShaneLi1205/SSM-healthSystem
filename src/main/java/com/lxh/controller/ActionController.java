package com.lxh.controller;

import com.alibaba.fastjson.JSON;
import com.lxh.bean.Constant;
import com.lxh.pojo.ChatData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *登录等操作的接口
 * @Author: LXH
 * @Date: 2021/7/21 9:21
 */
@RestController
public class ActionController {


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
