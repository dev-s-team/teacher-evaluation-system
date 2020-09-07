package com.csmaxwell.tes.service;

import com.csmaxwell.tes.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findById() {
        User user = userService.findById(2);
        System.out.println(user);
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setUsername("sjdfkl");
        user.setPassword("123456");
        user.setBirthday("1998-03-31");
        userService.saveUser(user);
        System.out.println(user.getId());
    }
}