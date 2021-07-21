package com.lxh.service;

import com.lxh.pojo.Worker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 10:16
 */
@Service
@Transactional(propagation = Propagation.NESTED, timeout = 1000, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)

public interface WorkerService {
    /**
     * 新工作者注册
     * @param worker 保存新用户数据的对象
     * @return 影响行数
     */
    boolean saveNewWorker(Worker worker);

    /**
     * 获得所有工作者的名称和ID
     * @return 所有工作者的list
     */
    ArrayList<Worker> listAllWorkerNameAndId();

    /**
     * 工作者登录验证
     * @param worker 封装登录信息
     * @return 登录成功后返回worker信息
     */
    Worker getWorker(Worker worker);

    /**
     * 注册账号检查
     * @param checkAccount 需要检查的名账号
     * @return 搜索到的信息
     */
    boolean getWorkerAccount(String checkAccount);

    /**
     * 注册用户名检查
     * @param checkName 需要检查的用户名
     * @return 搜索到的信息
     */
    boolean getWorkerName(String checkName);

    /**
     * 获得工作者的账号状态
     * @param workerId 工作者ID
     * @return 工作者信息
     */
    Worker getWorkerBanTime(int workerId);

    /**
     * 设置工作者的账号状态
     * @param map 工作者ID(WorkerId)和封禁到的天数(banDays)、
     * @return 影响行数
     */
    boolean saveWorkerBanTime(Map<String,Integer> map);
}
