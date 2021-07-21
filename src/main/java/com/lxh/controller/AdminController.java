package com.lxh.controller;

import com.alibaba.fastjson.JSONObject;
import com.lxh.bean.Constant;
import com.lxh.bean.ResultInfo;
import com.lxh.bean.StatusCode;
import com.lxh.pojo.Admin;
import com.lxh.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: LXH
 * @Date: 2021/7/21 9:28
 */
@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

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
}
