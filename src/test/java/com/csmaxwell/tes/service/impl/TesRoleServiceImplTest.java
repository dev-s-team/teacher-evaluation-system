package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.service.TesRoleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class TesRoleServiceImplTest {

    @Autowired
    private TesRoleService tesRoleService;

    @Test
    void deleteRelation() {
        int count = tesRoleService.deleteRelation(10L);
        if (count == 1) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }
}