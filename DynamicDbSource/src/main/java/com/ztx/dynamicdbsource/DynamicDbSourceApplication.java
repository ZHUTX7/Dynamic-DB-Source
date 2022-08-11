package com.ztx.dynamicdbsource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.ztx.dynamicdbsource.mapper"})
public class DynamicDbSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDbSourceApplication.class, args);
    }

}
