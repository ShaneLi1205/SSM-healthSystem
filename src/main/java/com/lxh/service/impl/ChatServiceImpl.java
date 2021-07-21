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
     * 用户获得信息时，chatSender为1，需要标记工作者发送的信息为已读
     * 工作者获得信息时，chatSender为0，需要标记用户发送的信息为已读
     * @return 聊天记录的list
     */
    @Override
    public ArrayList<ChatData> listChatData(Map<String, Integer> map) {
        ArrayList<ChatData> chatData = chatMapper.listChatData(map);
        chatMapper.updateChatDataRead(map);
        return chatData;
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
        ArrayList<ChatData> chatData = chatMapper.listNewChatData(map);
        chatMapper.updateChatDataRead(map);
        return chatData;
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
        ArrayList<ChatData> chatData = chatMapper.listNewChatDataByUser(map);
        chatMapper.updateChatDataReadByUser(map);
        return chatData;
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
        ArrayList<ChatData> chatData = chatMapper.listNewChatDataByWorker(map);
        chatMapper.updateChatDataReadByWorker(map);
        return chatData;
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
