package com.csmaxwell.tes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.csmaxwell.tes.mapper")
public class TesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesApplication.class, args);
    }

}
