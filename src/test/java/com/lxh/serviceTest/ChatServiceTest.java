package com.lxh.serviceTest;

import com.lxh.pojo.ChatData;
import com.lxh.service.ChatService;
import com.lxh.bean.Constant;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 12:16
 */
public class ChatServiceTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    ChatService chatServiceImpl = context.getBean("chatServiceImpl", ChatService.class);

    @Test
    public void sendChatTest(){
        ChatData chatData = new ChatData();
        chatData.setUserId(1);
        chatData.setWorkerId(1);
        chatData.setChatContent("SSM chat test");
        chatData.setChatSender(false);
        chatServiceImpl.saveNewChatData(chatData);
    }
    @Test
    public void listChatTest(){
//        Map<String,Integer > map = new HashMap<>();
//        map.put(Constant.WORKER_ID,1);
//        map.put(Constant.USER_ID,1);
        ChatData chatData = new ChatData();
        chatData.setUserId(1);
        chatData.setWorkerId(1);
        for (ChatData listChatDatum : chatServiceImpl.listChatData(chatData)) {
            System.out.println(listChatDatum);
        }
    }
    @Test
    public void listNewChatTest(){
//        Map<String,Integer> map = new HashMap<>();
//        map.put(Constant.WORKER_ID,1);
//        map.put(Constant.USER_ID,1);
//        map.put(Constant.CHAT_SENDER,0);
        ChatData chatData = new ChatData();
        chatData.setUserId(1);
        chatData.setWorkerId(1);
        for (ChatData listChatDatum : chatServiceImpl.listNewChatData(chatData)) {
            System.out.println(listChatDatum);
        }
    }
}
