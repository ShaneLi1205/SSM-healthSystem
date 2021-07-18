package com.lxh.service.impl;

import com.lxh.dao.ChatMapper;
import com.lxh.pojo.ChatData;
import com.lxh.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 12:17
 */
@Service
public class ChatServiceImpl implements ChatService {

    private ChatMapper chatMapper;

    @Autowired
    public ChatServiceImpl(@Qualifier("chatMapper") ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }

    /**
     * 进入聊天界面，获得聊天记录
     *
     * @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 聊天记录的list
     */
    @Override
    public ArrayList<ChatData> listChatData(Map<String, Integer> map) {
        return chatMapper.listChatData(map);
    }

    /**
     * 在聊天窗口，用户实时获得新信息
     * 仅获得用户者（chatSender=0）或工作者（chatSender=1）发送的新消息
     * 用户要获得工作者的信息，工作者要获得用户的信息
     *
     * @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 聊天记录的list
     */
    @Override
    public ArrayList<ChatData> listNewChatData(Map<String, Integer> map) {
        return chatMapper.listNewChatData(map);
    }

    /**
     * 在聊天窗口，用户实时获得新信息
     * 仅获得工作者发送的新消息
     *
     * @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 聊天记录的list
     */
    @Override
    public ArrayList<ChatData> listNewChatDataByUser(Map<String, Integer> map) {
        return chatMapper.listNewChatDataByUser(map);
    }

    /**
     * 在聊天窗口，工作者实时获得新信息
     * 仅获得用户发送的新信息
     *
     * @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 聊天信息的list
     */
    @Override
    public ArrayList<ChatData> listNewChatDataByWorker(Map<String, Integer> map) {
        return chatMapper.listNewChatDataByWorker(map);
    }

    /**
     * 在聊天窗口，用户获得新消息后，标记已读
     * 进入聊天窗口时，读取全部信息，标记已读
     *
     * @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 影响行数
     */
    @Override
    public int updateChatDataReadByUser(Map<String, Integer> map) {
        return chatMapper.updateChatDataReadByUser(map);
    }

    /**
     * 在聊天窗口，工作者获得新消息后，标记已读
     * 进入聊天窗口，读取全部信息，标记已读
     *
     * @param map map中获取聊天的工作者Id（workerId）和用户Id（userId）
     * @return 影响行数
     */
    @Override
    public int updateChatDataReadByWorker(Map<String, Integer> map) {
        return chatMapper.updateChatDataReadByWorker(map);
    }

    /**
     * 用户（chatSender=0）或工作者（chatSender=1）发送信息
     * @param chatData 聊天的信息
     * @return 影响行数
     */
    @Override
    public int saveNewChatData(ChatData chatData) {
        return chatMapper.saveNewChatData(chatData);
    }

    /**
     * 工作者发送信息
     *
     * @param chatData 聊天的信息
     * @return 影响行数
     */
    @Override
    public boolean saveNewWorkerChatData(ChatData chatData) {
        return chatMapper.saveNewWorkerChatData(chatData) == 1;
    }

    /**
     * 用户发送信息
     *
     * @param chatData 聊天的信息
     * @return 影响行数
     */
    @Override
    public boolean saveNewUserChatData(ChatData chatData) {
        return chatMapper.saveNewUserChatData(chatData) == 1;
    }

    /**
     * 查询已发起的聊天的用户和工作者关系
     *
     * @param map 查询工作者或者用户视角的聊天关系
     * @return 聊天对象
     */
    @Override
    public ArrayList<ChatData> listChatRelation(Map<String, Integer> map) {
        return chatMapper.listChatRelation(map);
    }
}
