package com.lxh.service.impl;

import com.lxh.dao.WorkerMapper;
import com.lxh.pojo.Worker;
import com.lxh.service.WorkerService;
import com.lxh.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: LXH
 * @Date: 2021/7/18 10:16
 */
@Service
public class WorkerServiceImpl implements WorkerService {
    private WorkerMapper workerMapper;

    @Autowired
    public WorkerServiceImpl(@Qualifier("workerMapper") WorkerMapper workerMapper) {
        this.workerMapper = workerMapper;
    }

    /**
     * 新工作者注册
     *
     * @param worker 保存新用户数据的对象
     * @return 影响行数
     */
    @Override
    public boolean saveNewWorker(Worker worker) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(System.currentTimeMillis());
        worker.setWorkerBanTime(currentTime);
        return workerMapper.saveNewWorker(worker) == 1;
    }

    /**
     * 获得所有工作者的名称和ID
     *
     * @return 所有工作者的list
     */
    @Override
    public ArrayList<Worker> listAllWorkerNameAndId() {
        return workerMapper.listAllWorkerNameAndId();
    }

    /**
     * 工作者登录验证
     *
     * @param worker 封装登录信息
     * @return 登录成功后返回worker信息
     */
    @Override
    public Worker getWorker(Worker worker) {
        return workerMapper.getWorker(worker);
    }

    /**
     * 注册账号检查
     *
     * @param checkAccount 需要检查的名账号
     * @return 搜索到的信息
     */
    @Override
    public boolean getWorkerAccount(String checkAccount) {
        return workerMapper.getWorkerAccount(checkAccount) == null;
    }

    /**
     * 注册用户名检查
     *
     * @param checkName 需要检查的用户名
     * @return 搜索到的信息
     */
    @Override
    public boolean getWorkerName(String checkName) {
        return workerMapper.getWorkerName(checkName) == null;
    }

    /**
     * 获得工作者的账号状态
     *
     * @param workerId 工作者ID
     * @return 工作者信息
     */
    @Override
    public Worker getWorkerBanTime(int workerId) {
        return workerMapper.getWorkerBanTime(workerId);
    }

    /**
     * 设置工作者的账号状态
     *
     * @param map 工作者ID(WorkerId)和封禁到的天数(banDays)、
     * @return 影响行数
     */
    @Override
    public boolean saveWorkerBanTime(Map<String,Integer> map) {
        //保存信息的对象
        Worker worker = new Worker();
        //获取工作者ID
        int workerId = map.get(Constant.WORKER_ID);
        //获取封禁时间
        int banDays = map.get(Constant.BAN_DAYS);
        //保存用户信息到对象
        worker.setWorkerId(workerId);
        //calendar对象获取最终解封日期
        Calendar calendar = new GregorianCalendar();
        //simpleDateFormat获取所需的日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //saveDateStr保存最终的解封时间
        String saveDateStr = null;
        //保存Date类的解封日期
        Date saveDate;
        //获得工作者当前的封禁日期
        String currentBanTime = workerMapper.getWorkerBanTime(workerId).getWorkerBanTime();
        //获取当前时间
        String currentTime = simpleDateFormat.format(new Date());
        //处理封禁日期
        if (banDays == 0){
            //解封
            saveDateStr = currentTime;
        } else {
            if (currentTime.compareTo(currentBanTime) >= 0) {
                //获取当前时间和日期
                Date currentDate = new Date();
                calendar.setTime(currentDate);
                //获取封禁解除的时间和日期
                calendar.add(Calendar.DATE, banDays);
                saveDate = calendar.getTime();
                //解封日期的String
                saveDateStr = simpleDateFormat.format(saveDate);
            } else {
                //延长封禁时间
                try {
                    //获取封禁开始的时间
                    Date beginDate = simpleDateFormat.parse(currentBanTime);
                    calendar.setTime(beginDate);
                    //获取封禁解除的时间和日期
                    calendar.add(Calendar.DATE, banDays);
                    saveDate = calendar.getTime();
                    //解封日期的String
                    saveDateStr = simpleDateFormat.format(saveDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        //保存封禁日期到对象
        worker.setWorkerBanTime(saveDateStr);
        return workerMapper.saveWorkerBanTime(worker) == 1;
    }

}
