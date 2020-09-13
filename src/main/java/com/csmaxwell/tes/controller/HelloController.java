package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * S
 * Created by maxwell on 2020/9/7.
 */
@RestController
public class HelloController {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private TesUserService tesUserService;

    @RequestMapping("/hello")
    public String sayHello() {
        System.out.println("dataSource = " + dataSource);
        return "Hello Spring Boot!";
    }

    /**
     * 根据id获取用户
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/user/{id}")
    public TesUser findById(@PathVariable Integer id) {
        // return tesUserService.findById(id);
        return null;
    }
}
