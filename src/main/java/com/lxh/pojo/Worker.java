package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工作者实体类
 * @author LXH
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
