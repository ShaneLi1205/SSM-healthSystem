package com.lxh.service;

import com.lxh.pojo.Report;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Author: LXH
 * @Date: 2021/7/18 11:08
 */
@Service
@Transactional(propagation = Propagation.NESTED, timeout = 1000, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)

public interface ReportService {
    /**
     * 获得所有举报信息
     * @return 返回所有举报信息
     */
    ArrayList<Report> listAllReport();

    /**
     * 保存新的举报
     * @param report 举报信息
     * @return 影响行数
     */
    boolean saveReport(Report report);

    /**
     * 删除举报
     * @param reportId 举报信息的ID
     * @return 影响行数
     */
    boolean deleteReport(int reportId);
}
