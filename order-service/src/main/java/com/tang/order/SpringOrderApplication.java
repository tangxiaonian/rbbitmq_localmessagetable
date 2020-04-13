package com.tang.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Classname SpringOrderApplication
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 12:25
 * @Created by ASUS
 */
@MapperScan(basePackages = {"com.tang.order.mapper"})
@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class SpringOrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringOrderApplication.class, args);

    }

}