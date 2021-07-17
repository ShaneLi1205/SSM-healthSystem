package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表实体类
 * @author LXH
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
