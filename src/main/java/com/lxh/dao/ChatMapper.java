package com.lxh.dao;

import com.lxh.pojo.ChatData;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 8:56
 */
public interface ChatMapper {
    /**
     * 进入聊天界面，获得聊天记录
     * @param chatData map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 聊天记录的list
     */
    ArrayList<ChatData> listChatData(ChatData chatData);

    /**
     * 在聊天窗口，用户实时获得新信息
     * 仅获得用户（chatSender=0）或工作者（chatSender=1）发送的新消息
     * 用户要获得工作者的信息，工作者要获得用户的信息
     *  @Param chatData 中获取聊天的工作者Id（workerId）和用户Id（userId）
     *  @return 聊天记录的list
     */
    ArrayList<ChatData> listNewChatData(ChatData chatData);

    /**
     * 在聊天窗口，用户实时获得新信息
     * 仅获得工作者发送的新消息
     *  @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     *  @return 聊天记录的list
     */
    ArrayList<ChatData> listNewChatDataByUser(Map<String,Integer> map);

    /**
     * 在聊天窗口，工作者实时获得新信息
     * 仅获得用户发送的新信息
     * @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 聊天信息的list
     */
    ArrayList<ChatData> listNewChatDataByWorker(Map<String,Integer> map);

    /**
     * 在聊天窗口，用户获得新消息后，标记已读
     * 进入聊天窗口时，读取全部信息，标记已读
     * 用户者（chatSender=0） 工作者（chatSender=1）
     * 用户需要标记工作者发送的信息，工作者需要标记用户发送的信息
     * @param chatData map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 影响行数
     */
    int updateChatDataRead(ChatData chatData);

    /**
     * 在聊天窗口，用户获得新消息后，标记已读
     * 进入聊天窗口时，读取全部信息，标记已读
     * @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 影响行数
     */
    int updateChatDataReadByUser(Map<String,Integer> map);

    /**
     * 在聊天窗口，工作者获得新消息后，标记已读
     * 进入聊天窗口，读取全部信息，标记已读
     * @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 影响行数
     */
    int updateChatDataReadByWorker(Map<String,Integer> map);


    /**
     * 用户（chatSender=0）或工作者（chatSender=1）发送信息
     * @param chatData 聊天的信息
     * @return 影响行数
     */
    int saveNewChatData(ChatData chatData);

    /**
     * 工作者发送信息
     * @param chatData 聊天的信息
     * @return 影响行数
     */
    int saveNewWorkerChatData(ChatData chatData);

    /**
     * 用户发送信息
     * @param chatData 聊天的信息
     * @return 影响行数
     */
    int saveNewUserChatData(ChatData chatData);


    /**
     * 查询已发起的聊天的用户和工作者关系
     * @param map 查询工作者或者用户视角的聊天关系
     * @return 聊天对象
     */
    ArrayList<ChatData> listChatRelation(Map<String,Integer> map);
}
