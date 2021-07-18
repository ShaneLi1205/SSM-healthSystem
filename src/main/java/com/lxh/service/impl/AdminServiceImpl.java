package com.lxh.service.impl;

import com.lxh.dao.AdminMapper;
import com.lxh.pojo.Admin;
import com.lxh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Author: LXH
 * @Date: 2021/7/18 9:27
 */
@Service
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(@Qualifier("adminMapper") AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
     * 管理员登录验证
     *
     * @param admin 登录信息
     * @return 登录成功后返回管理员信息
     */
    @Override
    public Admin getAdmin(Admin admin) {
        return adminMapper.getAdmin(admin);
    }

    /**
     * 验证管理员账号是否存在
     *
     * @param checkAccount 输入的账号
     * @return 返回查询到的管理员
     */
    @Override
    public Admin getAdminAccount(String checkAccount) {
        return adminMapper.getAdminAccount(checkAccount);
    }

    /**
     * 验证管理员名称是否存在
     *
     * @param checkName 检查的用户名
     * @return 返回查询到的管理员
     */
    @Override
    public Admin getAdminName(String checkName) {
        return adminMapper.getAdminName(checkName);
    }
}
