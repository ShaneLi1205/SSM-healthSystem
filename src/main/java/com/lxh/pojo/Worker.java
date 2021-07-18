package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工作者实体类
 * @author LXH
 * @date 2021/7/18 9:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private int workerId;
    private boolean workerSex;
    private String workerAccount;
    private String workerPassword;
    private String workerName;
    private String workerRegisterTime;
    private String workerBanTime;
}
