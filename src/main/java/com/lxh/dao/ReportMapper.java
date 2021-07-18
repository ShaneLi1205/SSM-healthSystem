package com.lxh.dao;

import com.lxh.pojo.Report;

import java.util.ArrayList;

/**
 * @Author: LXH
 * @Date: 2021/7/18 8:57
 */
public interface ReportMapper {
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
    int saveReport(Report report);

    /**
     * 删除举报
     * @param reportId 举报信息的ID
     * @return 影响行数
     */
    int deleteReport(int reportId);
}
