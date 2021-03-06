package com.lxh.dao;

import com.lxh.pojo.Admin;

import java.sql.Connection;

/**
 * @Author: LXH
 * @Date: 2021/7/18 8:56
 */
public interface AdminMapper {
    /**
     * 管理员登录验证
     * @param admin 登录信息
     * @return 登录成功后返回管理员信息
     */
    Admin getAdmin(Admin admin);

    /**
     * 验证管理员账号是否存在
     * @param checkAccount 输入的账号
     * @return 返回查询到的管理员
     */
    Admin getAdminAccount(String checkAccount);

    /**
     * 验证管理员名称是否存在
     * @param checkName 检查的用户名
     * @return 返回查询到的管理员
     */
    Admin getAdminName(String checkName);
}
