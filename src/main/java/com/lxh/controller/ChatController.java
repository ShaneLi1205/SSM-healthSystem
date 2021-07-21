package com.lxh.controller;

import com.alibaba.fastjson.JSON;
import com.lxh.bean.Constant;
import com.lxh.pojo.ChatData;
import com.lxh.service.ChatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/21 15:02
 */
@RestController
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    @RequestMapping("/showChatRelation")
    public String showChatRelation(Integer userId,Integer workerId){
        Map<String,Integer> map = new HashMap<>(2);
        map.put(Constant.WORKER_ID,workerId);
        map.put(Constant.USER_ID,userId);
        System.out.println("userId"+userId+"  workerId"+workerId);
        ArrayList<ChatData> chatDataArrayList = chatService.listChatRelation(map);
        return JSON.toJSONString(chatDataArrayList);
    }
}
