package com.lxh.serviceTest;

import com.lxh.pojo.User;
import com.lxh.service.UserService;
import com.lxh.bean.Constant;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LXH
 * @Date: 2021/7/18 10:45
 */
public class UserServiceTest {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userServiceImpl = context.getBean("userServiceImpl", UserService.class);

    @Test
    public void listAllUserTest(){
        for (User user : userServiceImpl.listAllUser()) {
            System.out.println(user);
        }
    }
    @Test
    public void userRegisterTest(){
        User user = new User();
        user.setUserAccount("12345678");
        user.setUserPassword("12345678");
        user.setUserSex(true);
        user.setUserName("SSMTest");
        System.out.println(userServiceImpl.saveNewUser(user));
    }
    @Test
    public void getUserFavoritesTest(){
        Map<String,Integer> map = new HashMap<>();
        map.put(Constant.USER_ID,1);
        map.put(Constant.ARTICLE_ID,2);
        System.out.println(userServiceImpl.getUserIsFavourites(map));
    }
    @Test
    public void checkUser(){
        System.out.println(userServiceImpl.checkUserAccountAndName("17255654","黎木木"));
    }
}
