package com.lxh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
/**
 * @author LXH
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
