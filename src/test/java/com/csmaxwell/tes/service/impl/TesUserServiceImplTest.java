package com.csmaxwell.tes.service.impl;

import com.csmaxwell.tes.domain.TesCourse;
import com.csmaxwell.tes.service.TesUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class TesUserServiceImplTest {

    @Autowired
    private TesUserService tesUserService;

    @Test
    void findCourseListById() {
        List<TesCourse> courseList = tesUserService.findCourseListById(3L);
        for (TesCourse tesCourse : courseList) {
            System.out.println(tesCourse.getName());
        }
    }
}