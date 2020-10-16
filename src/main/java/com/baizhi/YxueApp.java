package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
                                //扫描日志注解
@MapperScan({"com.baizhi.Dao","com.baizhi.Log.dao"})
//过滤器注解
@ServletComponentScan
public class YxueApp {
    public static void main(String[] args) {
        SpringApplication.run(YxueApp.class, args);
    }
}




