package com.csmaxwell.tes.controller;

import com.csmaxwell.tes.service.TesClassService;
import com.csmaxwell.tes.service.TesRoleService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * S
 * Created by maxwell on 2020/9/17.
 */
@Api(tags = "TesClassController", description = "班级管理")
@RestController
@RequestMapping("/class")
public class TesClassController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TesClassController.class);

    @Autowired
    private TesClassService tesClassService;


}
