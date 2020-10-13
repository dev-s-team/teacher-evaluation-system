package com.csmaxwell.tes.service;

import com.csmaxwell.tes.domain.TesUser;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class TesUserServiceTest {

    @Autowired
    private TesUserService tesUserService;

    @Test
    public void findAll() {
        List<TesUser> userList = tesUserService.findAll();
        for (TesUser tesUser : userList) {
            System.out.println(tesUser);
        }
    }

    @Test
    public void countStudent() {
        int count = tesUserService.countStudent();
        System.out.println(count);
    }
}