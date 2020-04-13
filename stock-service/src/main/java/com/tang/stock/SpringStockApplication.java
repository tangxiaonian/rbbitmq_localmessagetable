package com.tang.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Classname SpringStockApplication
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 12:25
 * @Created by ASUS
 */
@MapperScan(basePackages = {"com.tang.stock.mapper"})
@SpringBootApplication
public class SpringStockApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringStockApplication.class, args);

    }

}