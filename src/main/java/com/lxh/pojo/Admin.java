package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 管理员表实体类
 * @author LXH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private int adminId;
    private boolean adminSex;
    private String adminAccount;
    private String adminPassword;
    private String adminName;
}
