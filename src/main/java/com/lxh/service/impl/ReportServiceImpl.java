package com.lxh.service.impl;

import com.lxh.dao.ReportMapper;
import com.lxh.pojo.Report;
import com.lxh.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @Author: LXH
 * @Date: 2021/7/18 11:09
 */
@Service
public class ReportServiceImpl implements ReportService {

    private ReportMapper reportMapper;

    @Autowired
    public ReportServiceImpl(@Qualifier("reportMapper") ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    /**
     * 获得所有举报信息
     *
     * @return 返回所有举报信息
     */
    @Override
    public ArrayList<Report> listAllReport() {
        return reportMapper.listAllReport();
    }

    /**
     * 保存新的举报
     *
     * @param report 举报信息
     * @return 影响行数
     */
    @Override
    public boolean saveReport(Report report) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        report.setReportTime(simpleDateFormat.format(System.currentTimeMillis()));
        return reportMapper.saveReport(report) == 1;
    }

    /**
     * 删除举报
     *
     * @param reportId 举报信息的ID
     * @return 影响行数
     */
    @Override
    public boolean deleteReport(int reportId) {
        return reportMapper.deleteReport(reportId) == 1;
    }
}
