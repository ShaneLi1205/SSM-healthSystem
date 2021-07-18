package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表实体类
 * @author LXH
 * @date 2021/7/18 9:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userId;
    private String userAccount;
    private String userPassword;
    private String userName;
    private boolean userSex;
    private String userRegisterTime;
}
