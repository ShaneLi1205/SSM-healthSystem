package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 举报信息
 * @author LXH
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private int reportId;
    private int userId;
    private int articleId;
    private int workerId;
    private String reportContent;
    private String reportReason;
    private String reportTime;
    private String userName;
    private String workerName;
    private String articleTitle;
}
