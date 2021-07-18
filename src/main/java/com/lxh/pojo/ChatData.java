package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
/**
 * 聊天信息实体类
 * @author LXH
 * @date 2021/7/18 9:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatData {
    private int chatId;
    private int userId;
    private int workerId;
    private boolean chatSender;
    private boolean chatRead;
    private String chatContent;
    private String userName;
    private String workerName;
    private Timestamp chatTime;
}
