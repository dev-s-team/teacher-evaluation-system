package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.domain.TesUser;
import com.csmaxwell.tes.service.TesUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

/**
 * S
 * Created by maxwell on 2020/9/7.
 */
@Api(tags = "HelloController", description = "你好测试管理")
@RestController
@RequestMapping("/test")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DataSource dataSource;
    @Autowired
    private TesUserService tesUserService;

    @ApiOperation("你好")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        System.out.println("dataSource = " + dataSource);
        return "Hello Spring Boot!";
    }

    /**
     * 根据id获取用户
     *
     * @param id 用户id
     * @return 用户
     */
    @ApiOperation("根据id获取用户信息")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TesUser findById(@PathVariable Integer id) {
        return tesUserService.findById(id);
    }

    @ApiOperation("获取所有用户信息")
    @RequestMapping(value = "/list")
    public List<TesUser> findAll() {
        return tesUserService.findAll();
    }


}
