package com.lxh.serviceTest;

import com.lxh.pojo.Report;
import com.lxh.service.ReportService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: LXH
 * @Date: 2021/7/18 11:12
 */
public class ReportServiceTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    ReportService reportService = context.getBean("reportServiceImpl",ReportService.class);

    @Test
    public void listAllReportTest(){
        for (Report report : reportService.listAllReport()) {
            System.out.println(report);
        }
    }
}
