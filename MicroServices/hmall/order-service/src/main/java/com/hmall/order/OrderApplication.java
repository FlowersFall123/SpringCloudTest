package com.hmall.order;

import com.hmall.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.hmall.order.mapper")
@EnableFeignClients(basePackages = "com.hmall.api.client",defaultConfiguration = DefaultFeignConfig.class) // 指向公共模块Feign接口包
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}