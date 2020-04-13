package com.tang.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Classname ClientStock
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/4/11 14:13
 * @Created by ASUS
 */
@FeignClient(value = "stock-service")
public interface ClientStock {

    @GetMapping("/stock/reduce")
    public Boolean reduceNumber();

}
