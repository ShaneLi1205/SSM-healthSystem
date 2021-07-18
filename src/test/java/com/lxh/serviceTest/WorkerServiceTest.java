package com.lxh.serviceTest;

import com.lxh.pojo.User;
import com.lxh.pojo.Worker;
import com.lxh.service.UserService;
import com.lxh.service.WorkerService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: LXH
 * @Date: 2021/7/18 10:48
 */
public class WorkerServiceTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    WorkerService workerServiceImpl = context.getBean("workerServiceImpl", WorkerService.class);

    @Test
    public void listAllWorkerTest(){
        for (Worker worker : workerServiceImpl.listAllWorkerNameAndId()) {
            System.out.println(worker);
        }
    }
    @Test
    public void checkTest(){
        System.out.println(workerServiceImpl.getWorkerName("果壳病人"));
    }

}
