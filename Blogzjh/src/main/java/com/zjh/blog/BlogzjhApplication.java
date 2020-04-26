package com.zjh.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjh.blog")
public class BlogzjhApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogzjhApplication.class, args);
    }

}
